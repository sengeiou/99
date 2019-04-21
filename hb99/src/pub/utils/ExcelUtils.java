package pub.utils;
 
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import pub.utils.support.ExcelRecordProcessor;
 
public class ExcelUtils {

	public static List<Object[]> parse(InputStream is) throws Exception {
		POIFSFileSystem poifs = new POIFSFileSystem(is);
		InputStream dis = poifs.createDocumentInputStream("Workbook");
		HSSFRequest req = new HSSFRequest();
		ExcelRecordProcessor processor = new ExcelRecordProcessor();
		req.addListenerForAllRecords(processor);
		HSSFEventFactory factory = new HSSFEventFactory();
		factory.processEvents(req, dis);
		dis.close();
		return processor.getResult();
	} 

}
