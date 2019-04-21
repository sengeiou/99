package pub.spring;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.commontemplate.tools.TemplateRenderer;
import org.springframework.core.Conventions;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import pub.functions.StrFuncs;
import pub.types.Desc;
import pub.web.ServletUtils;  

@SuppressWarnings("unchecked")
public class JsValidator extends AbstractValidationRules {

	private static class RuleInfo {
		String ruleName;
		Object ruleValue;
		String message;

		public RuleInfo(String ruleName, Object ruleValue) {
			this(ruleName, ruleValue, null);
		}

		public RuleInfo(String ruleName, Object ruleValue, String message) {
			this.ruleName = ruleName;
			this.ruleValue = ruleValue;
			this.message = message;
		}
	}

	private Object bean;
	private String formId;

	private String nestedPath = "";
	private List<String> orderedFields;
	private Map<String, List<RuleInfo>> fieldRulesMap;
	private List<RuleInfo> fieldRules;
	@SuppressWarnings("unused")
	private String fieldName;

	public JsValidator(Object bean) {
		this(bean, Conventions.getVariableName(bean));
	}

	public JsValidator(Object bean, String formId) {
		this.bean = bean;
		this.formId = formId;
		orderedFields = new ArrayList<String>();
		fieldRulesMap = new HashMap<String, List<RuleInfo>>();
	}

	public JsValidator(JsValidator parentValidator, Object bean, String path) {
		this.nestedPath = parentValidator.nestedPath + path + ".";
		this.bean = bean;
		this.orderedFields = parentValidator.orderedFields;
		this.fieldRulesMap = parentValidator.fieldRulesMap;
		this.formId = null;
	}
 
	public Object getBean() {
		return bean;
	}
 
	public JsValidator getNestedRules(String path) {
		Object childBean = null;
		try {
			if (bean != null) {
				childBean = PropertyUtils.getProperty(bean, path);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		JsValidator validator = new JsValidator(this, childBean, path);
		return validator;
	}
 
	public JsValidator on(String field) { 
		field = nestedPath + field;
		List<RuleInfo> rules = fieldRulesMap.get(field);
		if (rules == null) {
			rules = new ArrayList<RuleInfo>();
			fieldRulesMap.put(field, rules);
			orderedFields.add(field);
		}
		fieldRules = rules;
		fieldName = field;
		return this;
	}
 
	public JsValidator required(String errorMsg) {
		fieldRules.add(new RuleInfo(RuleNames.required, true, errorMsg));
		return this;
	}
 
	public JsValidator min(Object valueToCompare, String errorMsg) {
		fieldRules.add(new RuleInfo(RuleNames.min, valueToCompare, errorMsg));
		return this;
	}
 
	public JsValidator max(Object valueToCompare, String errorMsg) {
		fieldRules.add(new RuleInfo(RuleNames.max, valueToCompare, errorMsg));
		return this;
	}
	 
	public JsValidator url(String errorMsg) {
		fieldRules.add(new RuleInfo(RuleNames.url, true, errorMsg));
		return this;
	}	
	
    public JsValidator regex(String regex ,String errorMsg) {
    	regex = regex.replaceAll("\\\\", "longshun");
        fieldRules.add(new RuleInfo(RuleNames.regex, "'" + regex + "'", errorMsg));
        return this;
    }
 
	public JsValidator telno(String errorMsg) {
		fieldRules.add(new RuleInfo(RuleNames.telno, true, errorMsg));
		return this;
	}
	
	public JsValidator dateLessThan(String targetFiledName, String errorMsg) {
		targetFiledName = "'" + nestedPath + targetFiledName + "'";
		fieldRules.add(new RuleInfo(RuleNames.dateLessThan, targetFiledName, errorMsg));
		return this;
	}
 
	public JsValidator minLength(int minLength, String errorMsg) {
		fieldRules.add(new RuleInfo(RuleNames.minLength, minLength, errorMsg));
		return this;
	}
 
	public JsValidator maxLength(int maxLength, String errorMsg) {
		fieldRules.add(new RuleInfo(RuleNames.maxLength, maxLength, errorMsg));
		return this;
	}
 
	@SuppressWarnings("deprecation")
	public JsValidator custom(CustomValidationRule rule) {
		String ruleValue = null;
		Map<String, Object> remoteOptions = new HashMap<String, Object>();
		String home = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getContextPath();
		String remoteUrl = home + "/common/validate.json";
		remoteOptions.put("url", remoteUrl);
		remoteOptions.put("type", "POST");

		Map<String, Object> postData = new HashMap<String, Object>();
		String ruleClass = rule.getClass().getName();
		postData.put("ruleClass", ruleClass);

		ObjectMapper mapper = new ObjectMapper();
		mapper.getSerializationConfig().set(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

		String ruleData;
		StringWriter sw = new StringWriter();
		try {
			mapper.writeValue(sw, rule);
			sw.flush();
			ruleData = sw.toString();
			sw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		postData.put("ruleData", ruleData);
		remoteOptions.put("data", postData);

		sw = new StringWriter();
		try {
			mapper.writeValue(sw, remoteOptions);
			sw.flush();
			ruleValue = sw.toString();
			sw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		fieldRules.add(new RuleInfo("remote", ruleValue));
		return this;
	}
 
	public JsValidator number(String errorMsg) {
		fieldRules.add(new RuleInfo(RuleNames.number, true, errorMsg));
		return this;
	}
	 
	public JsValidator integer(String errorMsg) {
		fieldRules.add(new RuleInfo(RuleNames.integer, true, errorMsg));
		return this;
	}
 
	public JsValidator email(String errorMsg) {
		fieldRules.add(new RuleInfo(RuleNames.email, true, errorMsg));
		return this;
	}
 
	public JsValidator digits(String errorMsg) {
		fieldRules.add(new RuleInfo(RuleNames.digits, true, errorMsg));
		return this;
	}

	private String resolveMessage(String field, RuleInfo ruleInfo) {
		Map<String, Object> argumentModel = new HashMap<String, Object>();

		String fieldDesc = "";
		try {
			PropertyDescriptor propDesc = PropertyUtils.getPropertyDescriptor(bean, field);
			Desc desc = propDesc.getReadMethod().getAnnotation(Desc.class);
			if (desc != null) {
				fieldDesc = desc.value();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> fieldInfo = new HashMap<String, Object>();
		fieldInfo.put("desc", fieldDesc);
		fieldInfo.put("value", "");
		argumentModel.put("field", fieldInfo);

		Map<String, Object> ruleInfoMap = new HashMap<String, Object>();
		ruleInfoMap.put(ruleInfo.ruleName, ruleInfo.ruleValue);
		argumentModel.put("rule", ruleInfoMap);

		TemplateRenderer templateRenderer = new TemplateRenderer(ruleInfo.message);
		String message = templateRenderer.putAll(argumentModel).evaluate();
		return message;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		String isView = ServletUtils.getRequest().getParameter("isView");
		String isAudit = ServletUtils.getRequest().getParameter("isAudit");
		if (!StrFuncs.isEmpty(isView) && "1".equals(isView)) {
			sb.append("$('form').find('input, select, textarea').prop('disabled', true);\n");
		}else if(!StrFuncs.isEmpty(isAudit) && "1".equals(isAudit)) {
			sb.append("$('form').find('input, select, textarea').not('.canOperate').prop('disabled', true);\n");
		}
		sb.append("$('#").append(formId).append("').validate({\n");
		sb.append("\trules: {\n"); 
		for (int n = 0; n < orderedFields.size(); n++) {
			String field = orderedFields.get(n);
			if (n > 0) {
				sb.append(",\n");
			}
			sb.append("\t\t'").append(field).append("': {\n");
			List<RuleInfo> ruleInfos = fieldRulesMap.get(field);
			for (int i = 0; i < ruleInfos.size(); i++) {
				RuleInfo ruleInfo = ruleInfos.get(i);
				if (i > 0) {
					sb.append(",\n");
				}
				sb.append("\t\t\t").append(ruleInfo.ruleName)
					.append(": ").append(ruleInfo.ruleValue);
			}
			sb.append("\n\t\t}");
		}
		sb.append("\n\t},\n");
		sb.append("\tmessages: {\n");
		for (int n = 0; n < orderedFields.size(); n++) {
			String field = orderedFields.get(n);
			if (n > 0) {
				sb.append(",\n");
			}
			sb.append("\t\t'").append(field).append("': {\n");
			List<RuleInfo> ruleInfos = fieldRulesMap.get(field);
			boolean firstIter = true;
			for (RuleInfo ruleInfo: ruleInfos) {
				String message = ruleInfo.message;
				if (message == null || message.length() == 0) {
					continue;
				}
				if (!firstIter) {
					sb.append(",\n");
				}
				message = resolveMessage(field, ruleInfo);
				sb.append("\t\t\t").append(ruleInfo.ruleName)
					.append(": '").append(message).append("'");
				firstIter = false;
			}
			sb.append("\n\t\t}");
		}
		sb.append("\n\t}\n");

		sb.append("});\n");
		return sb.toString();
	}

	public static CustomValidationRule deserialize(String ruleString) {
		CustomValidationRule rule = null;
		int pos = ruleString.indexOf(':');
		String ruleClassName = ruleString.substring(0, pos);
		String ruleData = ruleString.substring(pos + 1);
		Class<? extends CustomValidationRule> ruleClass = null;
		try {
			ruleClass = (Class<CustomValidationRule>) Class.forName(ruleClassName);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		if (ruleData == null || ruleData.length() == 0) {
			try {
				return (CustomValidationRule) ruleClass.newInstance();
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			rule = mapper.readValue(ruleData, ruleClass);
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return rule;
	}

	public static CustomValidationRule deserialize(String ruleClassName, String ruleData) {
		CustomValidationRule rule = null;
		Class<? extends CustomValidationRule> ruleClass = null;
		try {
			ruleClass = (Class<CustomValidationRule>) Class.forName(ruleClassName);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			rule = mapper.readValue(ruleData, ruleClass);
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return rule;
	}

}
