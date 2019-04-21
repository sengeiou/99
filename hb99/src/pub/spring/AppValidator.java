package pub.spring;

import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.validation.Errors;

import pub.types.Desc;
  
@SuppressWarnings({"rawtypes", "unchecked"})
public class AppValidator extends AbstractValidationRules {
	
	public static org.springframework.validation.Validator staticValidator;
	private Object bean;
	private Errors errors;
	private String nestedPath = "";

	private String fieldName;
	private Object fieldValue;
	private boolean noValue;
	private boolean hasError;

	public AppValidator(Object bean, Errors errors) {
		this.bean = bean;
		this.errors = errors;
	}

	public static AppValidator of(Object bean, Errors errors) {
		return new AppValidator(bean, errors);
	}

	public AppValidator staticValidate() {
		if (staticValidator != null) {
			staticValidator.validate(bean, errors);
		}
		return this;
	}
 
	public Object getBean() {
		return bean;
	}
	 
	public AppValidator getNestedRules(String path) {
		Object childBean;
		try {
			childBean = PropertyUtils.getProperty(bean, path);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		AppValidator validator = new AppValidator(childBean, errors);
		validator.nestedPath = this.nestedPath + path + ".";
		return validator;
	}
 
	public AppValidator on(String field) {
		fieldName = field;
		try {
			fieldValue = PropertyUtils.getProperty(bean, field);
			String fullField = nestedPath + fieldName;
			hasError = errors.hasFieldErrors(fullField);
			noValue = fieldValue == null || fieldValue.toString().length() == 0;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return this;
	}
 
	public AppValidator required(String errorMsg) {
		if (hasError) return this;
		if (noValue) {
			reject(RuleNames.required, errorMsg, "不可为空");
		}
		return this;
	}
  
	public AppValidator min(Object valueToCompare, String errorMsg) {
		if (hasError || noValue) return this;

		Comparable comparable1 = (Comparable) fieldValue;
		Comparable comparable2 = (Comparable) ConvertUtils.convert(
			valueToCompare.toString(), fieldValue.getClass());

		if (comparable1.compareTo(comparable2) < 0) {
			reject(RuleNames.min, errorMsg, "最小值为${rule.min}", valueToCompare);
		}
		return this;
	}
 
	public AppValidator max(Object valueToCompare, String errorMsg) {
		if (hasError || noValue) return this;

		Comparable comparable1 = (Comparable) fieldValue;
		Comparable comparable2 = (Comparable) ConvertUtils.convert(
			valueToCompare.toString(), fieldValue.getClass());

		if (comparable1.compareTo(comparable2) > 0) {
			reject(RuleNames.max, errorMsg, "最大值为${rule.max}", valueToCompare);
		}
		return this;
	}
 
	public AppValidator telno(String errorMsg) {
		if (hasError || noValue) return this;
		return this;
	}
 
	public AppValidator minLength(int minLength, String errorMsg) {
		if (hasError) return this;
		if (!noValue && fieldValue.toString().length() < minLength) {
			reject(RuleNames.minLength, errorMsg, "不可少于${rule.minLength}个字符", minLength);
		}
		return this;
	}
 
	public AppValidator maxLength(int maxLength, String errorMsg) {
		if (hasError || noValue) return this;
		if (fieldValue.toString().length() > maxLength) {
			String defaultMsg = "不可超过${rule.maxLength}个字符";
			reject(RuleNames.maxLength, errorMsg, defaultMsg, maxLength);
		}
		return this;
	}
 
	public AppValidator custom(CustomValidationRule rule) {
		if (hasError || noValue) return this;
		String errorMsg = rule.validate(fieldValue);
		if (errorMsg != null) {
			reject(RuleNames.general, errorMsg, "无效");
		}
		return this;
	}
 
	public AppValidator number(String errorMsg) {
		if (hasError || noValue) return this;
		try {
			Double.valueOf(fieldValue.toString());
		}
		catch(Exception e) {
			String defaultMsg = "请输入数值";
			reject(RuleNames.number, errorMsg, defaultMsg);
		}
		return this;
	}
 
	public AppValidator integer(String errorMsg) {
		if (hasError || noValue) return this;
		try {
			Integer.valueOf(fieldValue.toString());
		}
		catch(Exception e) {
			String defaultMsg = "请输入整数";
			reject(RuleNames.integer, errorMsg, defaultMsg);
		}
		return this;
	}
 
	public AppValidator email(String errorMsg) {
		if (hasError || noValue) return this;
		String s = fieldValue.toString();
		int atPos = s.indexOf('@');
		if (atPos < 1 || atPos == s.length() - 1) {
			String defaultMsg = "不是有效的email";
			reject(RuleNames.integer, errorMsg, defaultMsg);
		}
		return this;
	}
	 
	public AppValidator url(String errorMsg) {
		if (hasError || noValue) return this;
		String s = fieldValue.toString();
		Pattern pattern = Pattern.compile("^[a-zA-z]+://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(s);
		if (!matcher.matches()) {
			String defaultMsg = "不是有效的URL.";
			reject(RuleNames.url, errorMsg, defaultMsg);
		}
		return this;
	}
	
    public AppValidator regex(String regexStr, String errorMsg) {
        if (hasError || noValue) return this;
        String s = fieldValue.toString();
        Pattern pattern = Pattern.compile(regexStr);
        Matcher matcher = pattern.matcher(s);
        if (!matcher.matches()) {
            String defaultMsg = "非法的值";
            reject(RuleNames.regex, errorMsg, defaultMsg);
        }
        return this;
    }
    
    public AppValidator dateLessThan(String targetFiledName, String errorMsg) {
        if (hasError || noValue) return this;
        try {
            Date sourceDate = (Date) fieldValue;
            Date targetDate = (Date) PropertyUtils.getProperty(bean, targetFiledName);
            if (sourceDate.compareTo(targetDate) >= 0) {
                String defaultMsg = "日期应该小于指定日期";
                reject(RuleNames.dateLessThan, errorMsg, defaultMsg);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return this;
    }
 
	public AppValidator digits(String errorMsg) {
		if (hasError || noValue) return this;
		String s = fieldValue.toString();
		for (int n = 0; n < s.length(); n++) {
			char c = s.charAt(n);
			if (!Character.isDigit(c)) {
				String defaultMsg = "只允许输入数字";
				reject(RuleNames.digits, errorMsg, defaultMsg);
				break;
			}
		}
		return this;
	}

	private void reject(String ruleName, String message, String defaultMessage) {
		reject(ruleName, message, defaultMessage, null);
	}
	
	private void reject(String ruleName, String message, String defaultMessage, Object ruleAttributes) {
		hasError = true;
		String field = nestedPath + (fieldName == null? "": fieldName);

		String code = "invalid." + ruleName;
		if (message != null) {
			defaultMessage = message;
		}
		String fieldDesc = "";
		try {
			PropertyDescriptor propDesc = PropertyUtils.getPropertyDescriptor(bean, fieldName);
			Desc desc = propDesc.getReadMethod().getAnnotation(Desc.class);
			if (desc != null) {
				fieldDesc = desc.value();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> argumentModel = new HashMap<String, Object>();

		Map<String, Object> fieldInfo = new HashMap<String, Object>();
		fieldInfo.put("desc", fieldDesc);
		fieldInfo.put("value", fieldValue);
		argumentModel.put("field", fieldInfo);

		Map<String, Object> ruleInfo = new HashMap<String, Object>();
		if (ruleAttributes != null) {
			if (ruleAttributes instanceof Map) {
				ruleInfo.putAll((Map) ruleAttributes);
			}
			else {
				ruleInfo.put(ruleName, ruleAttributes);
			}
		}
		argumentModel.put("rule", ruleInfo);
		errors.rejectValue(field, code, new Object[] {argumentModel}, defaultMessage);
	}

}
