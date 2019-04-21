package pub.types;

import java.io.Serializable; 

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class ActionResult implements Serializable {

	private static final long serialVersionUID = -1496227779004770791L;
	private int code = 200;
	private String msg;
	private Object data;
	private boolean success = false;

	public ActionResult() {
		
	}

	public ActionResult(Object data) {
		this.data = data;
	}

	public void setResult(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void success() {
		setCode(200);
		setMsg("操作成功");
		setData(null);
	}

	public void success(String msg) {
		setCode(200);
		setMsg(msg);
		setData(null);
	}

	public void success(Object data) {
		setCode(200);
		setMsg("操作成功");
		setData(data);
	}

	public void success(Object data, String msg) {
		setCode(200);
		setMsg(msg);
		setData(data);
	}

	public void fail(String msg) {
		setCode(500);
		setMsg(msg);
	}

    public void fail(int code, String msg) {
        setCode(code);
        setMsg(msg);
    }

	public void loginFailure() {
		setCode(500);
		setMsg("您未登录，请登录后操作");
	}
	
	public void setException(Throwable e) {
		setCode(500);
		setMsg("系统异常，请联系管理员"); 
	}
	
	public static ActionResult decode(String jsonText) {
		ActionResult result = new ActionResult();
		try {
			JSONObject json = JSONObject.fromObject(jsonText);
			result.setCode(200);
			result.setData(json.get("data"));
			result.setMsg(json.getString("msg"));
		}
		catch (JSONException e) {
			e.printStackTrace();
			result.fail("解析异常: " + e.getMessage());
		}
		return result;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
