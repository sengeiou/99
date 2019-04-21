package pub.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.eventusermodel.FormatTrackingHSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.hssf.eventusermodel.MissingRecordAwareHSSFListener;
import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.BoundSheetRecord;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.LabelRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SSTRecord;
import org.apache.poi.hssf.record.StringRecord;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
 
@SuppressWarnings("unused")
public class Parser implements HSSFListener {

	private List<Sheet> sheets;
	private Sheet sheet;
	private List<Object> row;
	private int lastColumnCount = 10;

	private int minColumns;
	private POIFSFileSystem fs;

	private int lastRowNumber = -1;
	private int lastColumnNumber;

	private SSTRecord sstRecord;
	private FormatTrackingHSSFListener formatListener;

	private int sheetIndex = -1;
	private BoundSheetRecord[] orderedBSRs;
	private List<BoundSheetRecord> boundSheetRecords = new ArrayList<BoundSheetRecord>();

	private int nextRow;
	private int nextColumn;
	private boolean outputNextStringRecord;

	public Parser(InputStream is) throws IOException {
		this.fs = new POIFSFileSystem(is);
		this.minColumns = 0;
		sheets = new ArrayList<Sheet>();
	}

	public void process() throws IOException {
		MissingRecordAwareHSSFListener listener = new MissingRecordAwareHSSFListener(this);
		formatListener = new FormatTrackingHSSFListener(listener);

		HSSFEventFactory factory = new HSSFEventFactory();
		HSSFRequest request = new HSSFRequest();

		short[] rectypes = new short[]{BOFRecord.sid, BoundSheetRecord.sid, SSTRecord.sid,
			FormulaRecord.sid, StringRecord.sid, LabelRecord.sid,
			LabelSSTRecord.sid, NumberRecord.sid, ExtendedFormatRecord.sid
		};
		for (int n = 0; n < rectypes.length; n++) {
			request.addListener(formatListener, rectypes[n]);
		}

		factory.processWorkbookEvents(request, fs);
	}

	public void processRecord(Record record) {
		int thisRow = -1;
		int thisColumn = -1;
		String thisStr = null;

		short sid = record.getSid();
		if (sid == BOFRecord.sid) {
			BOFRecord br = (BOFRecord) record;
			if (br.getType() == BOFRecord.TYPE_WORKSHEET) {

				sheetIndex++;
				if (orderedBSRs == null) {
					orderedBSRs = BoundSheetRecord.orderByBofPosition(boundSheetRecords);
				}
				BoundSheetRecord orderedBSR = orderedBSRs[sheetIndex];
				if (orderedBSR.isHidden()) {
					sheet = null;
				}
				else {
					sheet = new Sheet();
					sheet.setName(orderedBSR.getSheetname());
					sheet.setIndex(sheetIndex + 1);
					sheet.setRows(new ArrayList<List<Object>>());
					sheets.add(sheet);
				}
			}
		}
		else if (sid == BoundSheetRecord.sid) {
			boundSheetRecords.add((BoundSheetRecord) record);
		}
		else if (sid == SSTRecord.sid) {
			sstRecord = (SSTRecord) record;
		}
		else if (sheet == null) {
			return;
		}

		switch (sid) {
		case FormulaRecord.sid:
			FormulaRecord frec = (FormulaRecord) record;

			thisRow = frec.getRow();
			thisColumn = frec.getColumn();

			if (Double.isNaN(frec.getValue())) {
				outputNextStringRecord = true;
				nextRow = frec.getRow();
				nextColumn = frec.getColumn();
			}
			else {
				thisStr = formatListener.formatNumberDateCell(frec);
			}
			break;
		case StringRecord.sid:
			if (outputNextStringRecord) {
				StringRecord srec = (StringRecord) record;
				thisStr = srec.getString();
				thisRow = nextRow;
				thisColumn = nextColumn;
				outputNextStringRecord = false;
			}
			break;

		case LabelRecord.sid:
			LabelRecord lrec = (LabelRecord) record;

			thisRow = lrec.getRow();
			thisColumn = lrec.getColumn();
			thisStr = lrec.getValue();
			break;
		case LabelSSTRecord.sid:
			LabelSSTRecord lsrec = (LabelSSTRecord) record;

			thisRow = lsrec.getRow();
			thisColumn = lsrec.getColumn();
			if (sstRecord != null) {
				thisStr = sstRecord.getString(lsrec.getSSTIndex()).toString();
			}
			break;
		case NumberRecord.sid:
			NumberRecord numrec = (NumberRecord) record;

			thisRow = numrec.getRow();
			thisColumn = numrec.getColumn();

			thisStr = formatListener.formatNumberDateCell(numrec);
			break;
		default:
			break;
		}

		if (thisRow != -1 && thisRow != lastRowNumber) {
			lastColumnNumber = -1;
			row = new ArrayList<Object>(lastColumnCount + 1);
			sheet.getRows().add(row);
		}

		if (thisStr != null) {
			for (int n = lastColumnNumber + 1; n < thisColumn; n++) {
				row.add(null);
			}
			row.add(thisStr);
			lastColumnCount = thisColumn;
		}

		if (thisRow > -1) {
			lastRowNumber = thisRow;
		}
		if (thisColumn > -1) {
			lastColumnNumber = thisColumn;
		}

		if (record instanceof LastCellOfRowDummyRecord) {
			lastColumnNumber = -1;
			row = null;
		}
	}

	public static List<Sheet> parse(InputStream is) {
		try {
			Parser parser = new Parser(is);
			parser.process();
			return parser.sheets;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void test() throws Exception {
		FileInputStream fis = new FileInputStream("d:\\query-.xls");
		List<Sheet> sheets = null;
		try {
			sheets = Parser.parse(fis);
		}
		finally {
			fis.close();
		}
		for(Sheet sheet : sheets){
			System.out.println("index="+sheet.getIndex()+" name="+sheet.getName());
			for(List<Object> o : sheet.getRows()){
				System.out.println("开始");
				for(Object oo : o){
					System.out.println("值=="+oo.toString());
				}
			}
		}
		System.out.println("??");
	}

	public static void main(String[] args) {
		try {
			test();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
