package pub.web;
 
public class PageDataInfo {

	private Object data; 
	private long lastVisitTime;

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public long getLastVisitTime() {
		return lastVisitTime;
	}
	public void setLastVisitTime(long lastVisitTime) {
		this.lastVisitTime = lastVisitTime;
	}
}
