package com.deppon.crm.module.common.file.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import com.deppon.crm.module.common.file.domain.ExcelExpressConstance;
import com.deppon.crm.module.common.file.domain.exception.FileException;
import com.deppon.crm.module.common.file.domain.exception.FileExceptionType;
import com.deppon.foss.framework.exception.GeneralException;


/**   
 * <p>
 * Description: 生成优惠券报表EXCEL <br />
 * </p>
 * @title CreateCouponExcel.java
 * @package com.deppon.crm.module.coupon.server.utils 
 * @author ZhouYuan
 * @version 0.1 2012-11-22
 */
public class CreateExcel {
	
	/**
	 * 
	 * @Title: createExcelWorkbook
	 * @Description: 创建Workbook
	 * @param title
	 * @param coupons
	 * @return Workbook
	 * @throws
	 */
	public static Workbook createExcelWorkbook(Map<String,String> title,List values){//定义单元格数目
		int cellNum = title.size();
		//获得title的Key值
		Set<String> titleKey = title.keySet();
		//创建工作簿
		Workbook wb = new SXSSFWorkbook(100);
		//创建Sheet
		Sheet sheet = wb.createSheet();
		
		for(int i = 0;i <= values.size();i++){
			//创建行
			sheet.createRow(i);
			//获得Row
			Row row = sheet.getRow(i);
			//Map title中key 的iterator
			Iterator it = titleKey.iterator();
			
			if( i == 0 ){			
				for(int j = 0;it.hasNext();j++){
					Cell cell = row.createCell(j);
					Object value = it.next();
					cell = transValueToCell(value,cell);
				}
			}else{
			
				Object crv = values.get(i-1);
				for(int j =0; it.hasNext(); j++){
					Cell cell = row.createCell(j);
					try {
						Object value = Ognl.getValue(title.get(it.next()),crv);
//						System.out.println("---------------------------------");
//						System.out.println(value);
//						System.out.println("---------------------------------");
						cell = transValueToCell(value,cell);
					} catch (OgnlException e) {
						
						e.printStackTrace();
						FileException fe = new FileException(
								FileExceptionType.FILE_EXCEPTION_OGNLEXPRESSERROR);
						throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
								new Object[] {}) {
						};
					}
				}
			}
		}
		return wb;
	}
	/**
	 * 
	 * @Title: transValueToCell
	 * @Description: 将Object写入Cell单元格
	 * @param value
	 * @param cell
	 * @return Cell
	 * @throws
	 */
	private static Cell transValueToCell(Object value, Cell cell) {
		XSSFRichTextString textValue;
		if (value == null) {
			cell.setCellValue("");
		} else if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
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
			//转换日期格式
			cell.setCellValue( formatDate(
					(Date)value, ExcelExpressConstance.COUPON_EXCEL_DATE_FORMAT));
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
	 * @param wb
	 * @param filePath
	 * @param fileName
	 * @return void
	 * @throws
	 */
	public static void exportExcel(Workbook workbook, String filePath,
			String fileName) {
		OutputStream os = null;
		try {
			File file = new File(filePath);
			// 如果文件夹不存在则创建
			if (!file.exists() && !file.isDirectory()) {
				file.mkdirs();
			}
			// 输出流
			os = new FileOutputStream(filePath + "/" + fileName);
			// 写文件
			workbook.write(os);
		
		} catch (IOException e) {
			e.printStackTrace();
			FileException fe = new FileException(
					FileExceptionType.FILE_EXCEPTION_FILENOTEXPORT);
			throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
					new Object[] {}) {
			};
		}finally{
			// 关闭输出流
			try {
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
	}
	/**
	 * 
	 * @Title: createExcel
	 * @Description: 创建Excel文件
	 * @param title
	 * @param coupons
	 * @param filePath
	 * @param fileName
	 * @return void
	 * @throws
	 */
	public static void createExcel(Map<String,String> title,List values,String fileName,String filePath){
		Workbook wb = createExcelWorkbook(title,values);
		exportExcel(wb,filePath,fileName);
	}

	/**
	 * <p>
	 * Description: 根据transfer的内容转换Date类型的输出格式<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-22
	 * @return
	 * String
	 */
	public static String formatDate(Date date,String transfer){
		SimpleDateFormat sdf = new SimpleDateFormat(transfer);
		return sdf.format(date);
	}
}
