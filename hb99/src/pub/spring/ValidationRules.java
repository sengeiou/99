package pub.spring;

public interface ValidationRules {

	Object getBean();

	ValidationRules getNestedRules(String path);

	ValidationRules on(String field);

	ValidationRules required();

	ValidationRules required(String errorMsg);

	ValidationRules min(Object valueToCompare);

	ValidationRules min(Object valueToCompare, String errorMsg);

	ValidationRules max(Object valueToCompare);

	ValidationRules max(Object valueToCompare, String errorMsg);

	ValidationRules telno();

	ValidationRules telno(String errorMsg);

	ValidationRules minLength(int minLength);

	ValidationRules minLength(int minLength, String errorMsg);

	ValidationRules maxLength(int maxLength);

	ValidationRules maxLength(int maxLength, String errorMsg);

	ValidationRules custom(CustomValidationRule rule);

	ValidationRules number();

	ValidationRules number(String errorMsg);

	ValidationRules integer();

	ValidationRules integer(String errorMsg);

	ValidationRules email();

	ValidationRules email(String errorMsg);

	ValidationRules digits();

	ValidationRules digits(String errorMsg);

	ValidationRules url();

	ValidationRules url(String errorMsg);

	ValidationRules regex(String regex);

	ValidationRules regex(String regex, String errorMsg);

	ValidationRules dateLessThan(String targetFiledName);

	ValidationRules dateLessThan(String targetFiledName, String errorMsg);

}
