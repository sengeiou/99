package pub.excel;

import java.util.ArrayList;
import java.util.List;

public class SheetModel extends BaseModel {

	private static final long serialVersionUID = -48845157918732165L;
	private String sheetName = null;
	/** 一个ColModel代表一列 */
	private List<ColModel> colModelList = new ArrayList<ColModel>();

	public String getSheetName() {
		return sheetName;
	}

	public ColModel getCol(int index) {
		return colModelList.get(index);
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public void addColModel(ColModel colModel) {
		colModelList.add(colModel);
	}

	public List<ColModel> getColModelList() {
		return colModelList;
	}

	public int getDataRowSize() {
		if (colModelList.size() != 0) {
			return colModelList.get(0).getValues().length;
		}
		return 0;
	}

	public int getColSize() {
		return colModelList.size();
	}

	public List<Object> getRow(int index) {
		List<Object> list = new ArrayList<Object>();
		for (ColModel colModel : colModelList) {
			list.add(colModel.getValues()[index]);
		}
		return list;
	}
}
