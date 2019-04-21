package pub.spring;

public abstract class AbstractValidationRules implements ValidationRules {
 
	public ValidationRules required() {
		return required(null);
	}
 
	public ValidationRules min(Object valueToCompare) {
		return min(valueToCompare, null);
	}
 
	public ValidationRules max(Object valueToCompare) {
		return max(valueToCompare, null);
	}
 
	public ValidationRules telno() {
		return telno(null);
	}

    public ValidationRules regex(String regex) {
        return regex(regex, null);
    }
    
    public ValidationRules dateLessThan(String targetFiledName) {
        return dateLessThan(targetFiledName, null);
    }
	   
	public ValidationRules minLength(int minLength) {
		return minLength(minLength, null);
	}
 
	public ValidationRules maxLength(int maxLength) {
		return maxLength(maxLength, null);
	}
 
	public ValidationRules number() {
		return number(null);
	}
 
	public ValidationRules integer() {
		return integer(null);
	}
 
	public ValidationRules email() {
		return email(null);
	}
	 
	public ValidationRules url() {
		return url(null);
	}
 
	public ValidationRules digits() {
		return digits(null);
	}
	
}
