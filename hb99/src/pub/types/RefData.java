package pub.types;

import org.springframework.ui.ExtendedModelMap;
 
public class RefData extends ExtendedModelMap {
 
	private static final long serialVersionUID = 5559759597342857249L;

	public RefData put(Object attributeValue) {
		addAttribute(attributeValue);
		return this;
	}
	
}
