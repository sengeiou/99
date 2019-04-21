package pub.types;

import java.io.Serializable;
 
public class IdText implements Serializable {
	 
	private static final long serialVersionUID = -286766548785891716L;
	public String id;
	public String text;

	public IdText() {

	}

	public IdText(String id, String text) {
		this.id = id;
		this.text = text;
	}

	public <T1, T2> IdText(T1 id, T2 text) {
		this(id == null? null: id.toString(), text == null ? "" : text.toString());
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public Integer getIntId() {
		return Integer.valueOf(id);
	}
}
