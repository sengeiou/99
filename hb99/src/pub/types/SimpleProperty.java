package pub.types;

import java.io.Serializable;
 
public class SimpleProperty implements Serializable {
 
	private static final long serialVersionUID = -4621225187503700993L;
	private String name;
	private Object value;
	private Object group;

	public SimpleProperty() {
	}

	public SimpleProperty(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public SimpleProperty(String name, Object value, Object group) {
		this.name = name;
		this.value = value;
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
	public Object getGroup() {
		return group;
	}
	public void setGroup(Object group) {
		this.group = group;
	}
}
