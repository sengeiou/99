package pub.types;

import org.springframework.ui.ExtendedModelMap;
 
public class Data extends ExtendedModelMap {
 
	private static final long serialVersionUID = 3523929634735741097L;

	public Data put(Object attributeValue) {
		addAttribute(attributeValue);
		return this;
	}

}