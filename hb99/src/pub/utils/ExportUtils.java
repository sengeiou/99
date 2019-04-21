package pub.utils;

import java.util.ArrayList;
import java.util.List;

import pub.excel.ColModel;
import pub.excel.ExcelModel;
import pub.excel.ExcelUtil;
import pub.excel.SheetModel;
import pub.models.listview.ListViewModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class ExportUtils {

	public static void exportToExcel(HttpServletResponse response,
			ListViewModel model, String name) { 
		List<ListViewModel> modelList = new ArrayList<ListViewModel>();
		List<String> nameList = new ArrayList<String>();
		modelList.add(model);
		nameList.add(name);
		exportToExcel(response, modelList, nameList, name);
	}

	/**
	 * 通用导出excel报表,可增加多个sheet表.
	 * 
	 * @param response
	 * @param modelList 数据模块list
	 * @param nameList  对应sheet名字list
	 * @param excelName 导出excel表名
	 */
	public static void exportToExcel(HttpServletResponse response,
			List<ListViewModel> modelList, List<String> nameList,
			String excelName) {
		ExcelModel excelData = null;
		try {
			excelData = listViewModelToExcelModel(modelList, nameList);
			excelName = new String(excelName.getBytes("GB2312"), "iso8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ excelName + ".xls");
			response.setContentType("application/vnd.ms-excel");
			ExcelUtil.exportExcel(response.getOutputStream(), excelData);
		} catch (Exception e) {
			if (excelData == null) {
				e.printStackTrace();
			} else {
				throw new RuntimeException("导出Excel失败：" + e.getMessage(), e);
			}
		}
	}

	private static ExcelModel listViewModelToExcelModel(
			List<ListViewModel> modelList, List<String> nameList) {
		ExcelModel excelModel = new ExcelModel();
		if (modelList != null && nameList != null
				&& modelList.size() == nameList.size()) {
			for (int modelIndex = 0; modelIndex < modelList.size(); modelIndex++) {
				ListViewModel model = (ListViewModel) modelList.get(modelIndex);
				String name = nameList.get(modelIndex).toString();
				name = "信息内容";
				excelModel = excelModelAddSheet(excelModel, model, name);
			}
		} else {
			excelModel = null;
		}

		return excelModel;
	}

	private static ExcelModel excelModelAddSheet(ExcelModel excelModel,
			ListViewModel model, String name) {
		int colCount = model.getColCount();
		int rowCount = model.getRowCount();
		final int MAX_SHEET_ROW = 30000;

		int startRow = 0;
		int sheetIndex = 0;
		do {
			SheetModel sheetModel = new SheetModel();
			sheetModel.setSheetName(name + sheetIndex++);

			int endRow = Math.min(startRow + MAX_SHEET_ROW - 1, rowCount - 1);
			for (int col = 0; col < colCount; col++) {
				ColModel colModel = new ColModel();
				colModel.setColTitle(model.getColTitle(col));

				Object[] values = new Object[endRow - startRow + 1];
				for (int row = startRow; row <= endRow; row++) {
					values[row - startRow] = model.getCellValue(row, col);
				}
				colModel.setValues(values);
				sheetModel.addColModel(colModel);
			}
			excelModel.addSheet(sheetModel);
			startRow = endRow + 1;
		} while (startRow < rowCount);

		return excelModel;
	}

	public static void exportTemplate(String templateFilePath,
			String exportFileName, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (!exportFileName.endsWith(".xls")) {
			exportFileName += ".xls";
		}
		exportFileName = new String(exportFileName.getBytes("GB2312"), "iso8859-1");
		response.setHeader("Content-disposition", "attachment; filename=" + exportFileName);
		response.setContentType("application/msexcel");
		request.getRequestDispatcher(templateFilePath).forward(request, response);
	}

}
