package pub.types;

import java.io.Serializable;
 
public class SortableData implements Serializable {
	 
	private static final long serialVersionUID = -510631820479592199L;
	private String sort;
	private String dir;

	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
}
