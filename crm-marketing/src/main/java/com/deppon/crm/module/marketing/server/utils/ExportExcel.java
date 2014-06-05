package com.deppon.crm.module.marketing.server.utils;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.deppon.foss.framework.exception.BusinessException;
/**   
 * <p>
 * Description:将对象属性保存至EXCEL文件中<br />
 * </p>
 * @title exportExcel.java
 * @package com.deppon.crm.module.marketing.server.utils
 * para:String[] head 文件头及第一行描述各个列的含义   名字    性别              工作                年龄
 *      String[] keys 与文件头一一对应的对象属性名称 name gender  profession  age
 *      List<Object>   保存的对象列表
 *      String xlsName 文件名
 *      String sheetName sheet 名
 * @author zzw
 * @version 2014-3-17
 */
public class ExportExcel {
	public static File resultSetToExcel(String[] head,String[] keys,List<?> rs,String xlsName,String sheetName) throws Exception
	{
		if(rs==null||rs.size()<=0){
			return null;
		}
		List<String> list=new ArrayList<String>();
		list=Arrays.asList(keys);
	HSSFWorkbook workbook = new HSSFWorkbook();
	HSSFSheet sheet = workbook.createSheet();
	HSSFCell cell;
	workbook.setSheetName(0, sheetName);
	HSSFCellStyle style = workbook.createCellStyle();
	HSSFRow row= sheet.createRow((short)0);	
	int nColumn=head.length;
	int kColumn=keys.length;
	if(nColumn!=kColumn){
		throw new BusinessException("resultSetToExcel(String[] head,String[] keys..) 方法中head 与 keys 长度不一致");
	}
	row= sheet.createRow((short)1);
	for(int i=0;i<nColumn;i++)
	{
		row.setHeight((short)500);
		cell = row.createCell(i);
		//设置样式
		//背景
		style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);  
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
	    style.setBorderBottom(HSSFCellStyle.BORDER_THICK);  
	    style.setBorderLeft(HSSFCellStyle.BORDER_THICK);  
	    style.setBorderRight(HSSFCellStyle.BORDER_THICK);  
	    style.setBorderTop(HSSFCellStyle.BORDER_THICK);  
	    style.setBottomBorderColor(HSSFColor.BLUE_GREY.index);
	    //居中
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
	    //设置字体
	    HSSFFont font = workbook.createFont();  
        font.setFontHeightInPoints((short) 15);  
        //字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
		cell.setCellStyle(style);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(head[i]);
	}
	Class<? extends Object> c=rs.get(0).getClass();
	Field[] fs=c.getDeclaredFields();
	int i=2;
	String s="";
	String fieldStr="";
	Object va;
	style = workbook.createCellStyle();
	for(Object obj:rs)
	{ 
		row= sheet.createRow((short)i);
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
		for(Field field:fs){
			 fieldStr=field.getName();
			if(list.contains(fieldStr)){
				field.setAccessible(true);
				//获取属性值
				 va=field.get(obj);
				if(va instanceof Date){
					DateFormat  format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
					s=format.format(va);  
					va=s;
				}
				if(va==null){
					va="";
				}
				int index=list.indexOf(fieldStr);
				cell = row.createCell(index);
				cell.setCellStyle(style);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(va.toString());
			}
		}
		i++;
	}
		sheet.autoSizeColumn(1, true);
		File f = new File(xlsName);
		FileOutputStream fo=new FileOutputStream(f);
		workbook.write(fo);
		long size=f.length();
		if(((size/1024)/1024)>100){
			return null;
		}
		fo.flush();
		fo.close();
		return f;
	}
}
