package pub.excel;

import jxl.*;
import jxl.format.Alignment;
import jxl.write.*;
import jxl.write.Number;

import javax.servlet.http.HttpServletResponse; 
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ExcelUtil {

	public static final int METHOD_ONE = 1;
	public static final int METHOD_TWO = 2;
	public static final int METHOD_THREE = 3;
	public static final int METHOD_FOUR = 3;

	public static void exportExcel(OutputStream os, ExcelModel excelModel)
			throws Exception {

		WritableWorkbook workBook = Workbook.createWorkbook(os);
		List<SheetModel> sheetModelList = excelModel.getSheetList();
		for (SheetModel sheetModel : sheetModelList) {
			createWritableSheet(workBook, sheetModel);
		}
		workBook.write();
		workBook.close();
	}

	private static void createWritableSheet(WritableWorkbook workBook,
			SheetModel sheetModel) throws Exception {
		WritableSheet writableSheet = workBook.createSheet(sheetModel
				.getSheetName(), workBook.getSheets().length);

		// 5.0设置字段头的格式\布局
		WritableFont fieldTitle = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.BOLD);
		WritableCellFormat fieldTitleFormat = new WritableCellFormat(fieldTitle);
		fieldTitleFormat.setAlignment(Alignment.CENTRE);

		// 7.0设置字段内容的格式\布局
		WritableFont fieldContent = new WritableFont(WritableFont.ARIAL, 10);
		WritableCellFormat fieldContentFormat = new WritableCellFormat(
				fieldContent);
		fieldContentFormat.setAlignment(Alignment.CENTRE);
		// TODO[COMMON] 是否有效率更高的做法,能否判断一列的类型，再转化一列数据
		// 8.0设置日期字段内容的格式\布局
		jxl.write.DateFormat df = new jxl.write.DateFormat(
				"yyyy-MM-dd HH:mm:ss");
		jxl.write.WritableCellFormat wcfDF = new jxl.write.WritableCellFormat(
				df);
		// 保留两位小数
		NumberFormat fivedps = new NumberFormat("#.##");
		WritableCellFormat numberFormat = new WritableCellFormat(fivedps);
		numberFormat.setAlignment(Alignment.CENTRE);
		// 9.0遍历每一行

		int columnCount = sheetModel.getColSize();
		int dataRowCount = sheetModel.getDataRowSize();
		// 6.0把字段头增加到sheet上
		CellView cellView = new CellView();
		cellView.setAutosize(true);
		for (int c = 0; c < columnCount; c++) {
			ColModel colModel = sheetModel.getCol(c);
			writableSheet.addCell(new Label(c, 0, colModel.getColTitle(),
					fieldTitleFormat));
			Object[] values = colModel.getValues();
			// TODO[COMMON] 以后再添加

			if (colModel.getTypeObject() instanceof String) {
				for (int r = 0; r < dataRowCount; r++) {
					if (values[r] == null) {
						writableSheet.addCell(new Blank(c, r + 1));
						continue;
					}
					writableSheet.setColumnView(c, cellView);

					if (values[r] instanceof BigDecimal) {
						writableSheet.addCell(new Label(c, r + 1,
								((BigDecimal) values[r]).toString(),
								fieldContentFormat));
					} else {
						writableSheet.addCell(new Label(c, r + 1,
								(String) values[r], fieldContentFormat));
					}
				}
			} else if (colModel.getTypeObject() instanceof BigDecimal) {
				for (int r = 0; r < dataRowCount; r++) {
					if (values[r] == null) {
						writableSheet.addCell(new Blank(c, r + 1));
						continue;
					}
					// TODO[COMMON]还会有问题
					BigDecimal bigDecimal = ((BigDecimal) values[r]);
					jxl.write.Number number = null;
					if (values[r].toString().indexOf(".") > 0) {
						number = new jxl.write.Number(c, r + 1, bigDecimal
								.doubleValue(), numberFormat);
					} else {
						number = new jxl.write.Number(c, r + 1,
								((BigDecimal) values[r]).intValue(),
								fieldContentFormat);
					}
					writableSheet.addCell(number);
				}
			}

			else if (colModel.getTypeObject() instanceof Timestamp) {

				for (int r = 0; r < dataRowCount; r++) {
					if (values[r] == null) {
						writableSheet.addCell(new Blank(c, r + 1));
						continue;
					}
					writableSheet.setColumnView(c, cellView);
					writableSheet.addCell(new DateTime(c, r + 1,
							(Date) values[r], wcfDF));
				}
			} else if (colModel.getTypeObject() instanceof Date) {

				for (int r = 0; r < dataRowCount; r++) {
					if (values[r] == null) {
						writableSheet.addCell(new Blank(c, r + 1));
						continue;
					}
					writableSheet.setColumnView(c, cellView);
					writableSheet.addCell(new DateTime(c, r + 1,
							(Date) values[r], wcfDF));
				}
			} else if (colModel.getTypeObject() instanceof Double) {
				for (int r = 0; r < dataRowCount; r++) {
					if (values[r] == null) {
						writableSheet.addCell(new Blank(c, r + 1));
						continue;
					}
					jxl.write.Number number = new jxl.write.Number(c, r + 1,
							((Double) values[r]), fieldContentFormat);
					writableSheet.addCell(number);
				}
			} else if (colModel.getTypeObject() instanceof Integer) {
				for (int r = 0; r < dataRowCount; r++) {
					if (values[r] == null) {
						writableSheet.addCell(new Blank(c, r + 1));
						continue;
					}
					jxl.write.Number number = new jxl.write.Number(c, r + 1,
							(Integer) values[r], fieldContentFormat);
					writableSheet.addCell(number);
				}
			} else if (colModel.getTypeObject() == null) {
				for (int r = 0; r < dataRowCount; r++) {
					writableSheet.addCell(new Blank(c, r + 1));
				}
			}
		}
	}

	public static ExcelModel importExcel(InputStream is) throws Exception {
		// 1.0从输入流得到工作本(Excel)
		Workbook workbook = Workbook.getWorkbook(is);
		// 2.0得到sheet数组
		jxl.Sheet[] sheets = workbook.getSheets();
		// 3.0准备ExcelDoc模型
		ExcelModel excelModel = new ExcelModel();
		// 4.0遍历每一个sheet
		for (jxl.Sheet sheet : sheets) {
			// 5.0准备Sheet数据模型
			SheetModel sheetModel = new SheetModel();
			// 6.0设置sheet名称
			sheetModel.setSheetName(sheet.getName());
			// 7.0得到当前sheet的列数
			int colCount = sheet.getColumns();

			// 8.0遍历每一个列
			for (int i = 0; i < colCount; i++) {
				// 9.0得到当前列的标题
				String colTitle = sheet.getCell(i, 0).getContents();
				// 11.0得到当前列的内容(不包括头),
				Object[] values = returnValues(sheet.getColumn(i), sheet
						.getRows());
				// 12.0把当前列的内容增加到sheet中
				ColModel colModel = new ColModel();
				colModel.setColTitle(colTitle);
				colModel.setValues(values);
				sheetModel.addColModel(colModel);
			}
			// 13.0添加sheet到excelDoc中
			excelModel.addSheet(sheetModel);
		}
		return excelModel;
	}

	/**
	 * <li>从一列cells中获取相应类型的值 //TODO[COMMON] 有逻辑问题
	 * 
	 * @param cells
	 *            代表一个sheet的一列
	 * @param colType
	 *            此列cell的数据类型
	 * @return
	 */
	private static Object[] returnValues(Cell[] cells, int maxRow)
			throws Exception {
		List<Object> list = new ArrayList<Object>();
		// TODO[COMMON] 只处理了三种数据类型，以后添加
		// TODO[COMMON] 现在对每一个类型进行相同的try-catch,以后可能要分别对待

		for (int j = 1; j < maxRow; j++) {
			try {
				CellType colType = cells[j].getType();
				if (colType == CellType.LABEL) {

					LabelCell labelCell = (LabelCell) cells[j];
					list.add(labelCell.getString());

				} else if (colType == CellType.NUMBER) {
					NumberCell numberCell = (NumberCell) cells[j];
					String content = numberCell.getContents();
					if (content.indexOf('.') != -1) {
						list.add(numberCell.getValue());
						continue;
					}
					list.add(BigInteger.valueOf(Long.parseLong(content)));
				} else if (colType == CellType.DATE) {
					DateCell dateCell = (DateCell) cells[j];
					list.add(dateCell.getDate());
				} else if (colType == CellType.EMPTY) {
					list.add(null);
				}
			} catch (IndexOutOfBoundsException e) {
				list.add(null);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

		return list.toArray();
	}

	// add by HuangGuoWei
	public static void exportExcelByObject(HttpServletResponse response,
			String excelName, List<Object[]> rslist, String tit[], String[] nbs) {
		try {
			if (tit != null && tit.length > 0) {
				response.setContentType("application/vnd.ms-excel");
				excelName = new String(excelName.getBytes("GB2312"),
						"iso8859-1");
				response.setHeader("Content-Disposition",
						"attachment; filename=" + excelName + ".xls");
				WritableWorkbook wb = Workbook.createWorkbook(response
						.getOutputStream());
				WritableSheet sheet = wb.createSheet("Export Date", 0);

				Label label = null;// new Label(0,0,"date");

				for (int i = 0; i < tit.length; ++i) {
					label = new Label(i, 0, tit[i]);
					sheet.addCell(label);
					sheet.setColumnView(i, 15);
				}

				int j = 1;
				for (int ii = 0; ii < rslist.size(); ii++) {
					Object[] obj = (Object[]) rslist.get(ii);
					for (int i = 1; i <= tit.length; ++i) {
						String temp = "";
						if (obj[i - 1] != null
								&& !obj[i - 1].toString().equals("null")) {
							temp = obj[i - 1].toString();
						}

						if (nbs[i - 1].equals("1")) {
							Number nb = new Number(i - 1, j, 0);
							try {
								nb = new Number(i - 1, j, Long.parseLong(temp));
							} catch (Exception e) {
							}
							sheet.addCell(nb);
						} else {
							label = new Label(i - 1, j, temp);
							sheet.addCell(label);
						}
					}
					j++;
				}

				wb.write();
				wb.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	@SuppressWarnings("rawtypes")
	public static void exportExcelByList(HttpServletResponse response,
			String excelName, List<HashMap> rslist, String[] col, String tit[],
			String[] nbs) {
		try {
			if (col != null && col.length > 0) {
				response.setContentType("application/vnd.ms-excel");
				excelName = new String(excelName.getBytes("GB2312"),
						"iso8859-1");
				response.setHeader("Content-Disposition",
						"attachment; filename=" + excelName + ".xls");
				WritableWorkbook wb = Workbook.createWorkbook(response
						.getOutputStream());
				WritableSheet sheet = wb.createSheet("Export Date", 0);

				Label label = null;// new Label(0,0,"date");

				for (int i = 0; i < tit.length; ++i) {
					label = new Label(i, 0, tit[i]);
					sheet.addCell(label);
					sheet.setColumnView(i, 15);
				}

				int j = 1;
				for (int ii = 0; ii < rslist.size(); ii++) {
					HashMap itemmap = (HashMap) rslist.get(ii);
					for (int i = 1; i <= col.length; ++i) {
						String temp = "";
						if (itemmap.get(col[i - 1]) != null) {
							temp = itemmap.get(col[i - 1]).toString();
						}
						if (nbs[i - 1].equals("1")) {
							Number nb = new Number(i - 1, j, Long
									.parseLong(temp));
							sheet.addCell(nb);
						} else {
							label = new Label(i - 1, j, temp);
							sheet.addCell(label);
						}
					}
					j++;
				}

				wb.write();
				wb.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
