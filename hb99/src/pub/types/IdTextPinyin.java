package pub.types;

import java.io.Serializable;

public class IdTextPinyin extends IdText implements Serializable {
 
	private static final long serialVersionUID = -4579663131459551207L;

	private String pinyin;
 
	public String getPinyin() {
		return pinyin;
	}
 
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

}
