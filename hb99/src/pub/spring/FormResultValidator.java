package pub.spring;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;

import pub.types.FormResult;
 
@SuppressWarnings({"rawtypes", "unchecked"})
public class FormResultValidator extends AbstractValidationRules {

	public static org.springframework.validation.Validator staticValidator;
	private Object bean;
	private FormResult result;
	private String nestedPath = "";

	private String fieldName;
	private Object fieldValue;
	private boolean noValue;
	private boolean hasError;

	public FormResultValidator(Object bean, FormResult result) {
		this.bean = bean;
		this.result = result;
	}

	public static FormResultValidator of(Object bean, FormResult result) {
		return new FormResultValidator(bean, result);
	}

	public FormResultValidator staticValidate() { 
		assert false: "not implemented";
		return this;
	}
 
	public Object getBean() {
		return bean;
	}
 
	public FormResultValidator getNestedRules(String path) {
		Object childBean;
		try {
			childBean = PropertyUtils.getProperty(bean, path);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		FormResultValidator validator = new FormResultValidator(childBean, result);
		validator.nestedPath = this.nestedPath + path + ".";
		return validator;
	}
 
	public FormResultValidator on(String field) {
		fieldName = field;
		try {
			fieldValue = PropertyUtils.getProperty(bean, field);
			String fullField = nestedPath + fieldName;
			hasError = result.getErrors().containsKey(fullField);
			noValue = fieldValue == null || fieldValue.toString().length() == 0;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return this;
	}
 
	public FormResultValidator required(String errorMsg) {
		if (hasError) return this;
		if(noValue) {
			reject(RuleNames.required, errorMsg, "不可为空");
		}
		return this;
	}
 
	public FormResultValidator min(Object valueToCompare, String errorMsg) {
		if (hasError || noValue) return this;

		Comparable comparable1 = (Comparable) fieldValue;
		Comparable comparable2 = (Comparable) ConvertUtils.convert(
			valueToCompare.toString(), fieldValue.getClass());

		if (comparable1.compareTo(comparable2) < 0) {
			reject(RuleNames.min, errorMsg, "最小值为${rule.min}", valueToCompare);
		}
		return this;
	}
 
	public FormResultValidator max(Object valueToCompare, String errorMsg) {
		if (hasError || noValue) return this;

		Comparable comparable1 = (Comparable) fieldValue;
		Comparable comparable2 = (Comparable) ConvertUtils.convert(
			valueToCompare.toString(), fieldValue.getClass());

		if (comparable1.compareTo(comparable2) > 0) {
			reject(RuleNames.max, errorMsg, "最大值为${rule.max}", valueToCompare);
		}
		return this;
	}
 
	public FormResultValidator telno(String errorMsg) {
		if (hasError || noValue) return this;
		return this;
	}
 
	public FormResultValidator minLength(int minLength, String errorMsg) {
		if (hasError) return this;
		if (!noValue && fieldValue.toString().length() < minLength) {
			reject(RuleNames.minLength, errorMsg, "不可少于${rule.minLength}个字符", minLength);
		}
		return this;
	}
 
	public FormResultValidator maxLength(int maxLength, String errorMsg) {
		if(hasError || noValue) return this;
		if (fieldValue.toString().length() > maxLength) {
			String defaultMsg = "不可超过${rule.maxLength}个字符";
			reject(RuleNames.maxLength, errorMsg, defaultMsg, maxLength);
		}
		return this;
	}
 
	public FormResultValidator custom(CustomValidationRule rule) {
		if(hasError || noValue) return this;
		String errorMsg = rule.validate(fieldValue);
		if (errorMsg != null) {
			reject(RuleNames.general, errorMsg, "无效");
		}
		return this;
	}
 
	public FormResultValidator number(String errorMsg) {
		if(hasError || noValue) return this;
		try {
			Double.valueOf(fieldValue.toString());
		}
		catch(Exception e) {
			String defaultMsg = "请输入数值";
			reject(RuleNames.number, errorMsg, defaultMsg);
		}
		return this;
	}
 
	public FormResultValidator integer(String errorMsg) {
		if(hasError || noValue) return this;
		try {
			Integer.valueOf(fieldValue.toString());
		}
		catch(Exception e) {
			String defaultMsg = "请输入整数";
			reject(RuleNames.integer, errorMsg, defaultMsg);
		}
		return this;
	}
 
	public FormResultValidator email(String errorMsg) {
		if(hasError || noValue) return this;
		String s = fieldValue.toString();
		int atPos = s.indexOf('@');
		if(atPos < 1 || atPos == s.length() - 1) {
			String defaultMsg = "不是有效的email";
			reject(RuleNames.integer, errorMsg, defaultMsg);
		}
		return this;
	}
 
	public FormResultValidator url(String errorMsg) {
		if(hasError || noValue) return this;
		String s = fieldValue.toString();
		Pattern pattern = Pattern.compile("^[a-zA-z]+://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(s);
		if(!matcher.matches()) {
			String defaultMsg = "不是有效的URL.";
			reject(RuleNames.url, errorMsg, defaultMsg);
		}
		return this;
	}
	
    public FormResultValidator regex(String regexStr,String errorMsg) {
        if(hasError || noValue) return this;
        String s = fieldValue.toString();
        Pattern pattern = Pattern.compile(regexStr);
        Matcher matcher = pattern.matcher(s);
        if(!matcher.matches()) {
            String defaultMsg = "非法的值";
            reject(RuleNames.regex, errorMsg, defaultMsg);
        }
        return this;
    }
    
    public FormResultValidator dateLessThan(String targetFiledName, String errorMsg) {
        if (hasError || noValue) return this;
        try {
            String sourceDate = fieldValue.toString();
            String targetDate = PropertyUtils.getProperty(bean, targetFiledName).toString();
            if (sourceDate.compareTo(targetDate) >= 0) {
                String defaultMsg = "日期应该小于指定日期";
                reject(RuleNames.dateLessThan, errorMsg, defaultMsg);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return this;
    }
 
	public FormResultValidator digits(String errorMsg) {
		if(hasError || noValue) return this;
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
		result.getErrors().put(field, message == null? defaultMessage: message);
	}

}
