package pub.spring.mvc.view;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.view.AbstractView;

import pub.functions.StrFuncs;
 
public class ExcelView extends AbstractView {

	private static final String CONTENT_TYPE = "application/vnd.ms-excel";

	private static final String EXTENSION = ".xls";
	public static final String rootFolder = "/WEB-INF/view";

	// 下载时显示的文件名属性
	public static final String OUTPUT_FILENAME = "OUTPUT_FILENAME";

	// 模板文件名属性，可不指定.xls后缀，如果与当前action路径配对，不需要写路径；
	// 支持用相对路径表示子路径，全路径必须首字符以'/'开始，表示相对于/WEB-INF/view的文件。
	public static final String TEMPLATE_FILENAME = "TEMPLATE_FILENAME";

	public ExcelView() {
		setContentType(CONTENT_TYPE);
	}

	@Override
	protected boolean generatesDownloadContent() {
		return true;
	}

	@Override
	protected final void renderMergedOutputModel(Map<String, Object> model,
												 HttpServletRequest request,
												 HttpServletResponse response) throws Exception {

		prepareResponseEx(model, request, response);

		String templateFileName = (String) model.get(TEMPLATE_FILENAME);
		String url;
		String uri = request.getServletPath();
		if(StrFuncs.isEmpty(templateFileName)) {
			int dotPos = uri.lastIndexOf('.');
			uri = uri.substring(0, dotPos);
			url = rootFolder + uri + EXTENSION;
		}
		else {
			if (!templateFileName.endsWith(EXTENSION)) {
				templateFileName += EXTENSION;
			}
			if (templateFileName.startsWith("/")) {
				url = rootFolder + templateFileName;
			}
			else {
				templateFileName = "/" + templateFileName;
				int slashPos = uri.lastIndexOf('/');
				uri = uri.substring(0, slashPos);
				url = rootFolder + uri + templateFileName;
			}
		}

		HSSFWorkbook workbook = getTemplateSource(url, request);

		//buildExcelDocument(model, workbook, request, response);
		XLSTransformer transformer = new XLSTransformer();
		transformer.transformWorkbook(workbook, model);

		// Set the content type.
		response.setContentType(getContentType());

		// Should we set the content length here?
		// response.setContentLength(workbook.getBytes().length);

		// Flush byte array to servlet output stream.
		ServletOutputStream out = response.getOutputStream();
		workbook.write(out);
		out.flush();
	}

	protected HSSFWorkbook getTemplateSource(String url, HttpServletRequest request) throws
																					 Exception {
		Resource inputFile = getApplicationContext().getResource(url);

		// Create the Excel document from the source.
		if (logger.isDebugEnabled()) {
			logger.debug("Loading Excel workbook from " + inputFile);
		}
		POIFSFileSystem fs = new POIFSFileSystem(inputFile.getInputStream());
		return new HSSFWorkbook(fs);
	}

	private void prepareResponseEx(Map<String, Object> model, HttpServletRequest request,
								   HttpServletResponse response) {

		String attachmentName = (String) model.get(OUTPUT_FILENAME);
		if (attachmentName == null) {
			String name = request.getServletPath();
			name = name.substring(name.lastIndexOf('/') + 1);
			name = name.substring(0, name.lastIndexOf('.')) + EXTENSION;
			attachmentName = name;
		}
		else if (!attachmentName.endsWith(EXTENSION)) {
			attachmentName += EXTENSION;
		}
		try {
			attachmentName = new String(attachmentName.getBytes("GB2312"), "iso8859-1");
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setContentType(CONTENT_TYPE);
		response.setHeader("Content-disposition", "attachment; filename=" + attachmentName);
	}
}
