package pub.models.listview;
 
public interface ListViewModel {

	String getColTitle(int col);
	Object getCellValue(int row, int col);
	int getRowCount();
	int getColCount();

}
