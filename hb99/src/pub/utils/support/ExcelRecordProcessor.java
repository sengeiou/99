package pub.utils.support;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.eventusermodel.AbortableHSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFUserException;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.BoundSheetRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RowRecord;
import org.apache.poi.hssf.record.SSTRecord;

public class ExcelRecordProcessor extends AbortableHSSFListener {

	private SSTRecord sstrec;
	private String sheetName;
	private List<Object[]> result;
	private Integer _firstCol;
	private Integer _lastCol;

	@Override
	public short abortableProcessRecord(Record record) throws HSSFUserException { 
		switch (record.getSid()) {
		case BOFRecord.sid:
			BOFRecord bof = (BOFRecord) record;
			if (bof.getType() == BOFRecord.TYPE_WORKSHEET) {
				if (result != null) {
					return -1;
				}
				result = new ArrayList<Object[]>();
			}
			break;
		case BoundSheetRecord.sid:
			if (this.sheetName == null) {
				BoundSheetRecord bsr = (BoundSheetRecord) record;
				this.sheetName = bsr.getSheetname(); // #1
			}
			break;
		case RowRecord.sid:
			if (_firstCol == null) {
				RowRecord rowrec = (RowRecord) record;
				_firstCol = rowrec.getFirstCol();
				_lastCol = rowrec.getLastCol();
			}
			break;
		case NumberRecord.sid:
			NumberRecord nrec = (NumberRecord) record;
			setCell(nrec.getRow(), nrec.getColumn() - _firstCol, nrec
					.getValue());
			break;
		case SSTRecord.sid:
			sstrec = (SSTRecord) record;
			break;
		case LabelSSTRecord.sid:
			LabelSSTRecord lrec = (LabelSSTRecord) record;
			String value = sstrec.getString(lrec.getSSTIndex()).toString();
			setCell(lrec.getRow(), lrec.getColumn() - _firstCol, value);
			break;
		}
		return 0;
	}

	private void setCell(int rowIndex, int colIndex, Object value) {
		Object[] row = getRow(rowIndex);
		if (colIndex + _firstCol > _lastCol) {
			return;
		}
		row[colIndex] = value;
	}

	private Object[] getRow(int rowIndex) {
		Object[] row = null;
		if (rowIndex > result.size() - 1) {
			for (int n = result.size(); n < rowIndex; n++) {
				result.add(null);
			}
			row = new Object[_lastCol - _firstCol + 1];
			result.add(row);
		} else {
			row = result.get(rowIndex);
		}
		return row;
	}

	public List<Object[]> getResult() {
		return result;
	}

	public String getSheetName() {
		return sheetName;
	}
}
