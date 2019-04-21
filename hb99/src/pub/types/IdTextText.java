package pub.types;

import java.io.Serializable;
 
public class IdTextText implements Serializable {
 
	private static final long serialVersionUID = -286766548785891716L;
	public String id;
	public String text;
	public String ustext;

	public IdTextText() {

	}
	
	public IdTextText(String id, String text) {
		this.id = id;
		this.text = text;
	}

	public IdTextText(String id, String text, String ustext) {
		this.id = id;
		this.text = text;
		this.ustext = ustext;
	}
	
	public <T1, T2, T3> IdTextText(T1 id, T2 text,T3 ustext) {
		this(id == null? null: id.toString(), text == null ? "" : text.toString(), ustext == null ? "" : ustext.toString());
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

	public String getUstext() {
		return ustext;
	}

	public void setUstext(String ustext) {
		this.ustext = ustext;
	}
}
