package pub.excel;

import java.util.ArrayList;
import java.util.List;
 
public class ExcelModel extends BaseModel {
 
	private static final long serialVersionUID = -3253472111167151473L;
	/** 一个Excel 有多个sheet */
	private List<SheetModel> sheetList = new ArrayList<SheetModel>();

	public List<SheetModel> getSheetList() {
		return sheetList;
	}

	public void setSheetList(List<SheetModel> sheetList) {
		this.sheetList = sheetList;
	}

	public void addSheet(SheetModel sheet) {
		sheetList.add(sheet);
	}
}
