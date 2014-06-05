package com.deppon.crm.module.custview.shared.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.deppon.crm.module.common.file.domain.exception.FileException;
import com.deppon.crm.module.common.file.domain.exception.FileExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

public class ExcelExporter {
	/**
	 * 
	 * @Title: createExcel
	 * @Description: 生成Excel工作区文件
	 * @param @param headers
	 * @param @param dataList
	 * @param @param sheetName
	 * @param @return
	 * @return XSSFWorkbook
	 * @throws
	 */
	private XSSFWorkbook createExcel(List<String> headers,
			List<List<Object>> dataList, String sheetName) {
		// 工作区
		XSSFWorkbook wb = new XSSFWorkbook();
		// 创建第一个sheet
		XSSFSheet sheet = wb.createSheet(sheetName);
		// 生成第一行
		XSSFRow row = null;
		if (headers != null && headers.size() > 0) {
			row = sheet.createRow(0);
			for (int i = 0; i < headers.size(); i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(headers.get(i));
			}
		}
		// 生成数据区
		if (dataList != null && dataList.size() > 0) {
			for (int j = 0; j < dataList.size(); j++) {
				if (headers != null && headers.size() > 0) {
					row = sheet.createRow(j + 1);
				} else {
					row = sheet.createRow(j);
				}
				List<Object> dataRow = dataList.get(j);
				for (int i = 0; i < dataRow.size(); i++) {
					Cell cell = row.createCell(i);
					Object value = dataRow.get(i);
					cell = transValueToCell(value, cell);
				}
			}
		}
		return wb;
	}

	private Cell transValueToCell(Object value, Cell cell) {
		XSSFRichTextString textValue;
		if (value == null) {
			cell.setCellValue("");
		} else if (value instanceof Integer) {
			int intValue = (Integer) value;
			cell.setCellValue(intValue);
		} else if (value instanceof Float) {
			float fValue = (Float) value;
			textValue = new XSSFRichTextString(String.valueOf(fValue));
			cell.setCellValue(textValue);
		} else if (value instanceof Double) {
			double dValue = (Double) value;
			textValue = new XSSFRichTextString(String.valueOf(dValue));
			cell.setCellValue(textValue);
		} else if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else if (value instanceof Date) {
			cell.setCellValue((Date) value);
		} else {
			// 其它数据类型都当作字符串简单处理
			cell.setCellValue(value.toString());
		}
		return cell;
	}

	/**
	 * 
	 * @Title: exportExcel
	 * @Description: 生成Excel文件
	 * @param @param wb
	 * @param @param filePath
	 * @return void
	 * @throws
	 */
	public static void exportExcel(XSSFWorkbook wb, String filePath,
			String fileName) {
		try {
			File file = new File(filePath);
			// 如果文件夹不存在则创建
			if (!file.exists() && !file.isDirectory()) {
				file.mkdirs();
			}
			// 输出流
			OutputStream os = new FileOutputStream(filePath + "/" + fileName);
			// 写文件
			wb.write(os);
			// 关闭输出流
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			FileException fe = new FileException(
					FileExceptionType.FILE_EXCEPTION_FILENOTEXPORT);
			throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * @Title: exportExcel
	 * @Description: 生成Excel文件
	 * @param @param headers
	 * @param @param dataList
	 * @param @param sheetName
	 * @param @param filePath
	 * @param @throws FileNotFoundException
	 * @param @throws IOException
	 * @return void
	 * @throws
	 */
	public void exportExcel(List<String> headers, List<List<Object>> dataList,
			String sheetName, String filePath, String fileName) {
		XSSFWorkbook wb = createExcel(headers, dataList, sheetName);
		ExcelExporter.exportExcel(wb, filePath, fileName);
	}

	/**
	 * 
	 * @Title: getExportExcel
	 * @Description: 获取导出的文件
	 * @param @param filePath
	 * @param @return
	 * @return File
	 * @throws
	 */
	public static InputStream getExportExcel(String filePath, String fileName) {
		File file = new File(filePath + "/" + fileName);
		if (file == null || !file.exists()) {
			FileException fe = new FileException(
					FileExceptionType.FILE_EXCEPTION_FILENOTEXPORT);
			throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
					new Object[] {}) {
			};
		}
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			FileException fe = new FileException(
					FileExceptionType.FILE_EXCEPTION_FILENOTEXPORT);
			throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
					new Object[] {}) {
			};
		}
	}

	public static void main(String[] args) throws IOException {
		String filePath = "/test";
		String fileName = "create.xlsx";
		String sheetName = "Sheet1";
		List<String> headers = new ArrayList<String>();
		headers.add("上海");
		headers.add("北京");
		headers.add((new Date()).toString());
		List<List<Object>> dataList = new ArrayList<List<Object>>();
		// dataList = new ExcelReader().read(new File("D://考勤2012-04.xls"));
		new ExcelExporter().exportExcel(headers, dataList, sheetName, filePath,
				fileName);
		// File file = new File(filePath);
		System.out.println(new Date() + "Export Excel Finished !!!!!!!!!!!");
	}

	// -----------------------------------360导出---------------------------------------------
	/**.
	 * 
	 * @description 根据文件路径获取该文件的WB(07版)
	 * @author 张斌
	 * @version 0.1 2012-5-25
	 * @param tempFilePath 文件路径
	 * @return XSSFWorkbook  文件生成的WB
	 */
	public XSSFWorkbook getExcel07Wb(String tempFilePath) {
		InputStream inputStream = null;
		XSSFWorkbook wb = null;
		try {
			inputStream = new FileInputStream(tempFilePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			wb = new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wb;
	}

	/**.
	 * 
	 * @description 根据坐标在EXCEL中设置一个值(07版)
	 * @author 张斌
	 * @version 0.1 2012-5-25
	 * @param wb EXCEL的工作薄
	 * @param rowPoint 行坐标
	 * @param cellPoint 列坐标
	 * @param value 设置的值
	 * @return XSSFWorkbook  设置值之后的WB
	 */
	public XSSFWorkbook setExcel07WbValue(XSSFWorkbook wb, int rowPoint,
			int cellPoint, Object value) {
		XSSFSheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow(rowPoint);
		if(row==null){
			row = sheet.createRow(rowPoint);
		}
		Cell cell = row.getCell(cellPoint);
		if(cell==null){
			cell = row.createCell(cellPoint);
		}
		cell = transValueToCell(value, cell);
		return wb;
	}

	/**.
	 * 
	 * @description 根据坐标在EXCEL中设置一组值(07版)
	 * @author 张斌
	 * @version 0.1 2012-5-25
	 * @param wb EXCEL的工作薄
	 * @param rowPoint 行坐标
	 * @param cellPoint 列坐标
	 * @param list 设置的一组值
	 * @return XSSFWorkbook  设置值之后的WB
	 */
	public XSSFWorkbook setExcel07List(XSSFWorkbook wb, int rowPoint,
			int cellPoint, List<List<Object>> list) {
		XSSFSheet sheet = wb.getSheetAt(0);
		for (int i = 0; i < list.size(); i++) {
			Row row = sheet.getRow(rowPoint+i);
			if(row==null){
				row = sheet.createRow(rowPoint);
			}
			List<Object> cellList = list.get(i);
			for (int j = 0; j < cellList.size(); j++) {
				Cell cell = row.getCell(cellPoint + j);
				if(cell==null){
					cell = row.createCell(cellPoint);
				}
				cell = transValueToCell(cellList.get(j), cell);
			}
		}
		return wb;
	}

	/**.
	 * 
	 * @description 根据WB在固定路径中创建生成的EXCEL(07版)
	 * @author 张斌
	 * @version 0.1 2012-5-25
	 * @param wb EXCEL的工作薄
	 * @param filePath 文件路径
	 * @param fileName 文件明
	 * @return realPath  获取该文件的文件全路径
	 */
	public static String createExcel(XSSFWorkbook wb, String filePath,
			String fileName) {
		String realPath = null;
		try {
			File file = new File(filePath);
			// 如果文件夹不存在则创建
			if (!file.exists() && !file.isDirectory()) {
				file.mkdirs();
			}
			realPath = filePath + "/" + fileName;
			// 输出流
			OutputStream os = new FileOutputStream(realPath);
			// 写文件
			wb.write(os);
			// 关闭输出流
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			FileException fe = new FileException(
			FileExceptionType.FILE_EXCEPTION_FILENOTEXPORT);
			throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
					new Object[] {}) {
			};
		}
//		realPath=FileDlpUtil.getEncryptFile(realPath);
		return realPath;
	}

	public static String createExcelDecrypt(XSSFWorkbook wb, String filePath,
			String fileName) {
		String realPath = null;
		try {
			File file = new File(filePath);
			// 如果文件夹不存在则创建
			if (!file.exists() && !file.isDirectory()) {
				file.mkdirs();
			}
			realPath = filePath + "/" + fileName;
			// 输出流
			OutputStream os = new FileOutputStream(realPath);
			// 写文件
			wb.write(os);
			// 关闭输出流
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			FileException fe = new FileException(
			FileExceptionType.FILE_EXCEPTION_FILENOTEXPORT);
			throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
					new Object[] {}) {
			};
		}
		// realPath=FileDlpUtil.getEncryptFile(realPath);
		return realPath;
	}

}