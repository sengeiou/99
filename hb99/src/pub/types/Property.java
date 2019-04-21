package pub.types;

import java.io.Serializable;
 
public class Property implements Serializable {
 
	private static final long serialVersionUID = -4572195366320347405L;
	private String name;
	private Object value;
	private String displayName;
	private String displayValue;
	private Object group;

	public Property() {
	}

	public Property(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public Property(String name, Object value, Object group) {
		this.name = name;
		this.value = value;
		this.group = group;
	}

	public Property(String name, Object value, String displayName, String displayValue, Object group) {
		this.name = name;
		this.value = value;
		this.displayName = displayName;
		this.displayValue = displayValue;
		this.group = group;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getDisplayName() {
		return displayName == null? name: displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getDisplayValue() {
		return displayValue == null? (value == null? "": value.toString()): displayValue;
	}
	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}
	public Object getGroup() {
		return group;
	}
	public void setGroup(Object group) {
		this.group = group;
	}
}
