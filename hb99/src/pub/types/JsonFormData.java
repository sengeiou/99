package pub.types;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
 
public class JsonFormData implements Serializable {
 
	private static final long serialVersionUID = -1077468096807205935L;
	private boolean success = true;
	private Map<String, Object> data;
	private Map<String, Object> addon;

	public JsonFormData() {
		data = new HashMap<String, Object>();
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public Map<String, Object> getAddon() {
		return addon;
	}
	public void setAddon(Map<String, Object> addon) {
		this.addon = addon;
	}
}
