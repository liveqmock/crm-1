package com.deppon.crm.module.common.file.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.deppon.crm.module.common.file.domain.exception.FileException;
import com.deppon.crm.module.common.file.domain.exception.FileExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

public class ExcelReader implements IExcelReader {

	// 总行数
	private int totalRows = 0;
	// 总列数
	private int totalCells = 0;
	// 当前记录
	private int currentRow = 0;
	// 空白记录
	private int emptyRow = 0;
	// 是否Excel2003
	private boolean isExcel2003 = false;
	// 当前文件
	private File file = null;

	// 构造方法
	public ExcelReader(File importFile) {
		String newPath = FileDlpUtil.getDecryptFile(importFile.getPath());
		importFile = new File(newPath);
		setConfig(importFile);
		this.file = importFile;
	}

	@Override
	public boolean hasNextRow() {
		return currentRow < totalRows - 1;
	}

	@Override
	public Object[] getNextRow() {
		currentRow++;
		// Workbook wb = getWorkbook(file, excel2003);
		return getWorkbookRow(getWorkbook(file, isExcel2003), currentRow,
				totalCells);

	}

	@Override
	public String[] getHeader() {
		if (totalRows > 0) {
			// Workbook wb = getWorkbook(file, excel2003);
			return getWorkbookHeader(getWorkbook(file, isExcel2003), totalCells);
		} else {
			FileException fe = new FileException(
					FileExceptionType.FILE_EXCEPTION_FILENOTEXCEL);
			throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
					new Object[] {}) {
			};
		}

	}

	/**
	 * 
	 * @Title: read
	 * @Description: 根据文件名读取excel文件
	 * @param @param fileName
	 * @param @return 设定文件
	 * @return List<List<Object>> 返回类型
	 * @throws
	 */
	// public List<List<Object>> read(String fileName) {
	// List<List<Object>> dataLst = new ArrayList<List<Object>>();
	//
	// if (!validateExtension(fileName)) {
	// return dataLst;
	// }
	//
	// // 检查文件是否存在
	// File file = new File(fileName);
	// if (!validateFileExist(file)) {
	// return dataLst;
	// }
	// boolean isExcel2003 = isExcel2003(fileName);
	//
	// dataLst = parseExcel(getWorkbook(file, isExcel2003));
	// // 返回最后读取的结果
	// return dataLst;
	// }

	// public List<List<Object>> read(File importFile) {
	// List<List<Object>> dataLst = new ArrayList<List<Object>>();
	//
	// String fileName = importFile.getName();
	// if (!validateExtension(fileName)) {
	// return dataLst;
	// }
	//
	// // 检查文件是否存在
	// File file = importFile;
	// if (!validateFileExist(file)) {
	// return dataLst;
	// }
	// boolean isExcel2003 = isExcel2003(fileName);
	//
	// dataLst = parseExcel(getWorkbook(file, isExcel2003));
	// // 返回最后读取的结果
	// return dataLst;
	// }

	public static boolean validateExtension(String fileName) {
		// 检查文件名是否为空或者是否是Excel格式的文件
		if (fileName == null || !fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
			FileException fe = new FileException(
					FileExceptionType.FILE_EXCEPTION_FILENOTEXCEL);
			throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
					new Object[] {}) {
			};
		}
		return true;
	}

	public static boolean validateFileExist(File file) {
		if (file == null || !file.exists()) {
			FileException fe = new FileException(
					FileExceptionType.FILE_EXCEPTION_FILENOTEXISTS);
			throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
					new Object[] {}) {
			};
		}
		return true;
	}

	public static boolean isExcel2003(String fileName) {
		// 对文件的合法性进行验证
		if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
			return false;
		}
		return true;
	}

	// public File getFile(File importFile) {
	// String fileName = importFile.getName();
	// if (!validateExtension(fileName)) {
	// return importFile;
	// }
	//
	// // 检查文件是否存在
	// if (!validateFileExist(importFile)) {
	// return importFile;
	// }
	// boolean isExcel2003 = isExcel2003(fileName);
	//
	// // 返回最后读取的结果
	// return importFile;
	// }

	// public Workbook getWorkbook(String fileName) {
	// Workbook wb = null;
	//
	// if (!validateExtension(fileName)) {
	// return wb;
	// }
	//
	// // 检查文件是否存在
	// File file = new File(fileName);
	// if (!validateFileExist(file)) {
	// return wb;
	// }
	// boolean isExcel2003 = isExcel2003(fileName);
	//
	// wb = getWorkbook(file, isExcel2003);
	// return wb;
	// }

	// 调用本类提供的根据文件读取的方法
	public static Workbook getWorkbook(File file, boolean isExcel2003) {
		try {
			InputStream inputStream = new FileInputStream(file);
			// 根据版本选择创建Workbook的方式
			Workbook wb = isExcel2003 ? new HSSFWorkbook(inputStream)
					: new XSSFWorkbook(inputStream);
			inputStream.close();
			return wb;
		} catch (IOException e) {
			e.printStackTrace();
			FileException fe = new FileException(
					FileExceptionType.FILE_EXCEPTION_FILENOHEADER);
			throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
					new Object[] {}) {
			};
		}
	}

	private void setConfig(File importFile) {
		String fileName = importFile.getName();
		if (!validateExtension(fileName)) {
			return;
		}
		// 检查文件是否存在
		if (!validateFileExist(importFile)) {
			return;
		}
		this.isExcel2003 = isExcel2003(fileName);

		Workbook wb = getWorkbook(importFile, this.isExcel2003);
		// 得到第一个shell
		Sheet sheet = wb.getSheetAt(0);
		this.totalRows = sheet.getPhysicalNumberOfRows();
		if (this.totalRows >= 1 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
	}

	/**
	 * 
	 * @Title: read
	 * @Description: 读取数据
	 * @param @param wb
	 * @param @return
	 * @return List<List<Object>>
	 * @throws
	 */
	// private List<List<Object>> parseExcel(Workbook wb) {
	// List<List<Object>> dataLst = new ArrayList<List<Object>>();
	//
	// // 得到第一个shell
	// setConfig(wb);
	// Sheet sheet = wb.getSheetAt(0);
	//
	// // 循环Excel的行
	// for (int r = 0; r < this.totalRows; r++) {
	// Row row = sheet.getRow(r);
	// if (row != null) {
	// dataLst.add(Arrays.asList(parseRow(row, getTotalCells())));
	// }
	// }
	// return dataLst;
	// }

	private Object[] parseRow(Row row, int totalCells) {
		Object[] rowData = new Object[totalCells];
		// 循环Excel的列
		for (short i = 0; i < totalCells; i++) {
			Cell cell = row.getCell(i);
			Object cellValue = null;
			if (cell == null) {
				cellValue = null;
			} else

			// 处理数字型的,自动去零
			if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
				// 在excel里,日期也是数字,在此要进行判断
				if (DateUtil.isCellDateFormatted(cell)) {
					cellValue = cell.getDateCellValue();
				} else {
					cellValue = cell.getNumericCellValue();
				}
			}
			// 处理字符串型
			else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
				cellValue = cell.getStringCellValue();
			}
			// 处理布尔型
			else if (Cell.CELL_TYPE_BOOLEAN == cell.getCellType()) {
				cellValue = cell.getBooleanCellValue();
			}
			// 其它的,非以上几种数据类型
			else {
				cellValue = cell.toString();
			}
			rowData[i] = cellValue;
		}
		return rowData;
	}

	private String[] parseHeader(Row row, int totalCells) {
		String[] header = new String[totalCells];
		// 循环Excel的列
		for (short i = 0; i < totalCells; i++) {
			Cell cell = row.getCell(i);
			String cellValue = null;
			if (cell == null) {
				cellValue = null;
			} else {
				cellValue = cell.toString();
			}
			header[i] = cellValue;
		}
		return header;
	}

	private Object[] getWorkbookRow(Workbook wb, int rowId, int totalCells) {
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow(rowId + emptyRow);
		while (row == null) {
			emptyRow++;
			row = sheet.getRow(rowId + emptyRow);
		}
		Object[] rolData = parseRow(row, totalCells);
		return rolData;
	}

	private String[] getWorkbookHeader(Workbook wb, int totalCells) {
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow(0);
		String[] header = parseHeader(row, totalCells);
		return header;
	}

	/**
	 * 
	 * @Title: main
	 * @Description: 测试
	 * @param @param args
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public static void main(String[] args) throws Exception {
		// String strPath = "D://TTTT.xls";
		// List<List<Object>> dataLst = new POIExcelUtil().read(strPath);
		// String filePath = beauticianResource.getimportFileName()0;//
		// File importFile = beauticianResource.getUpload()0;
		String strPath = "D://test//PotentialCust.xls";
		Runtime r = Runtime.getRuntime();
		System.out.println("Start:");
		System.out.println(r.totalMemory());
		System.out.println(r.freeMemory());
		File importFile = new File(strPath);
		System.out.println("New File:");
		System.out.println(r.totalMemory());
		System.out.println(r.freeMemory());
		System.out.println();

		System.out.println(importFile.getName());
		System.out.println(importFile.getAbsolutePath());
		System.out.println(importFile.getCanonicalPath());
		System.out.println(importFile.getParent());
		System.out.println(importFile.getPath());
		System.out.println(importFile.getTotalSpace());
		System.out.println(importFile.getUsableSpace());
		System.out.println(importFile.getAbsoluteFile());
		System.out.println(importFile.getCanonicalFile());
		System.out.println(importFile.getParentFile());
		IExcelReader reader = new ExcelReader(importFile);
		String[] header = reader.getHeader();
		while (reader.hasNextRow()) {
			Object[] row = reader.getNextRow();
			for (int i = 0; i < row.length; i++) {
				String colName = header[i];
				Object obj = row[i];
				if (colName.equals("姓名")) {
					System.out.println(obj);
					// TODO
				} else if (header[i].equals("地址")) {
					// TODO
				} else if (header[i].equals("电话")) {
					// TODO
				}
			}
		}

		reader = new ExcelReader(importFile);
		StringBuffer headerDetail = new StringBuffer();
		for (String hr : header) {
			headerDetail.append(",").append(hr);
		}
		if (headerDetail.length() > 0) {
			System.out.println(headerDetail.deleteCharAt(0).toString());
		}
		while (reader.hasNextRow()) {
			Object[] row = reader.getNextRow();
			StringBuffer rowData = new StringBuffer();
			for (Object obj : row) {
				rowData.append(",").append(obj);
			}
			if (rowData.length() > 0) {
				System.out.println(rowData.deleteCharAt(0).toString());
			}
		}

		// List<List<Object>> dataLst = new ExcelReader().read(importFile);
		// for (List<Object> innerLst : dataLst) {
		// StringBuffer rowData = new StringBuffer();
		// for (Object dataStr : innerLst) {
		// rowData.append(",").append(dataStr);
		// }
		// if (rowData.length() > 0) {
		// System.out.println(rowData.deleteCharAt(0).toString());
		// }
		// }
	}

	/**
	 *@user pgj
	 *2013-7-31下午3:27:43
	 * @return totalRows : return the property totalRows.
	 */
	@Override
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 *@user pgj
	 *2013-7-31下午3:27:43
	 * @return totalCells : return the property totalCells.
	 */
	@Override
	public int getTotalCells() {
		return totalCells;
	}

}