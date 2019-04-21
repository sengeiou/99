package pub.excel;
 
public class ColModel extends BaseModel { 
	private static final long serialVersionUID = 5632348303161750664L;
	/** 列标题 */
	public String colTitle = null;
	/** 列的值:使用者保证每一列的行数相等 */
	private Object[] values = null;

	public String getColTitle() {
		return colTitle;
	}

	public void setColTitle(String colTitle) {

		this.colTitle = colTitle;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {

		this.values = values;
	}

	public Object getTypeObject() {
		int len = values.length;
		if (len == 0) {
			return null;
		}
		Object obj = values[--len];
		while (obj == null && len != 0) {
			obj = values[--len];
		}
		return obj;
	}
}
