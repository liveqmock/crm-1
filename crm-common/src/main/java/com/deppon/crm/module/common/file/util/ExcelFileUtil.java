package com.deppon.crm.module.common.file.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.deppon.crm.module.common.file.domain.ExcelCellTemplate;
import com.deppon.crm.module.common.file.domain.ExcelTemplate;
import com.deppon.crm.module.common.file.domain.FileTemplate;
import com.deppon.crm.module.common.file.domain.exception.FileException;
import com.deppon.crm.module.common.file.domain.exception.FileExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

public class ExcelFileUtil {

	
	private  static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * <p>根据Excel模板，获取具体的Excel模板信息</p>
	 * @param FileTemplate  type
	 * @return ExcelTemplate
	 * */
	@SuppressWarnings("serial")
	public static ExcelTemplate getExcelTemplateByCode(FileTemplate type){
		// 获得模板存放路径
		String excelExportPath = PropertiesUtil.getInstance()
				.getProperty("excelExportTemplatePath").trim();
		String realPath = excelExportPath + "/" + type + ".xls";
		System.out.println("excel模板存放路径：" + realPath);
		// 判断模板是否存在
		File f = new File(realPath);
		if (!f.exists()) {
			System.out.println("excel模板不存在！");
			FileException fe = new FileException(
					FileExceptionType.FILE_EXCEPTION_FILENOTEXISTS);
			throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
					new Object[] {}) {
			};
		}

		// 解析模板
		ExcelTemplate et = new ExcelTemplate();
		et.setName(type.templateName());
		et.setKey(type.templateName());
		et.setClazz(type.templateName());
		List<ExcelCellTemplate> attrList = new ArrayList<ExcelCellTemplate>();
		ExcelCellTemplate ect = new ExcelCellTemplate();
		if (FileTemplate.Contract_Template.templateName().equals(
				type.templateName())) {

		} else if (FileTemplate.Cust360_Analysis_Tempalte.templateName()
				.equals(type.templateName())) {

		}
		et.setEctList(attrList);
		return et;
	}
	
	/**
	 * 读取EXCEL文件
	 * */
	@SuppressWarnings("rawtypes")
	public static List readExcel(File file ,ExcelTemplate template) throws Exception{
		if(file==null || file.length()<=0) return null;
		String fileName=file.getName();
		int excelVersion=2003;
		if(fileName.endsWith(".xls")){
			excelVersion=2003;
		}else if(fileName.endsWith(".xlsx")){
			excelVersion=2007;
		}else{
			return null;
		}
		
		Workbook wb=null;
		FileInputStream is =new FileInputStream(file);
		if(excelVersion==2003){
			wb= new HSSFWorkbook(is);
		}else if(excelVersion==2007){
			wb = new XSSFWorkbook(is);
		}
		List dataList=new ArrayList();
		readWorkBook(wb,template,dataList);
		is.close();
		return dataList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void readWorkBook(Workbook workBook ,ExcelTemplate template ,List dataList) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SecurityException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException, ParseException{
		Sheet sheet=workBook.getSheetAt(0);
		int rows= sheet.getPhysicalNumberOfRows();
		for (int rowIndex = template.getDataBeginRow(); rowIndex < rows; rowIndex++) {
			Row row=sheet.getRow(rowIndex);
			if (row == null) {
				continue;
			}
			Class clazz=Class.forName(template.getClazz());
			Object entity = Class.forName(template.getClazz()).newInstance();
			readRow(row ,template,entity,clazz);
			dataList.add(entity);
		}
	}
	
	private static void readRow(Row row ,ExcelTemplate template ,Object entity ,Class clazz) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ParseException{
		for (ExcelCellTemplate attr : template.getEctList()) {
			Cell cell= row.getCell(attr.getIndex());
			if(cell==null)
				continue ;
			readCell(cell  ,attr.getDataKey() ,entity,clazz);
		}
	}
	
	private static void readCell(Cell cell ,String dataKey ,Object entity,Class clazz) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ParseException{
		
		setObjVal(cell.getStringCellValue() ,dataKey ,entity ,clazz);
		
	}
	
	/**
	 * 设置对象值
	 * @throws ParseException 
	 * */
	private static void setObjVal(String value , String property ,Object entity ,Class clazz ) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, ParseException{
		Field[] fields=clazz.getDeclaredFields();
		for (Field field : fields) {
			String fieldName=field.getName();
			if(fieldName.equals(property)){
				String fieldType=field.getType().getCanonicalName();
				String stringLetter=fieldName.substring(0,1).toUpperCase();
				String setName="set"+stringLetter+fieldName.substring(1);
				Method setMethod=clazz.getMethod(setName, new Class[]{field.getType()});
				Object objVal=null;
				
				if("java.lang.String".equals(fieldType)){
					objVal=value;
					
				}else if("java.util.Date".equals(fieldType)){
					objVal = formatter.parse(value);
					
				}else if("java.lang.Integer".equals(fieldType) || "int".equals(fieldType)){
				    objVal= Integer.parseInt(value);
				    
				}else if("java.lang.Float".equals(fieldType) || "float".equals(fieldType)){
					objVal=Float.parseFloat(value);
					
				}else if("java.lang.Double".equals(fieldType) || "double".equals(fieldType)){
					objVal=Double.parseDouble(value);
				}
				
				setMethod.invoke(entity, new Object[]{objVal});
			}
		}
		
	}
	
	
	/**
	 * 生成EXCEL文件
	 * @throws Exception 
	 * */
	public static File generateExcel(List dataList ,ExcelTemplate template,String filePath) throws Exception{
		File file=new File(filePath);
		if (file.exists() == false)
			file.createNewFile();
		createExcel(dataList, template, file);
		return file;
		
	}
	
	private  static void createExcel(List dataList,ExcelTemplate template ,File file) throws IOException, ClassNotFoundException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
	        FileOutputStream out = new FileOutputStream(file);
	        HSSFWorkbook wb = new HSSFWorkbook();
	        HSSFSheet s = wb.createSheet();

	        HSSFRow r = null;
	        HSSFCell c = null;
	        
	        HSSFCellStyle cs = wb.createCellStyle();

	        HSSFFont f = wb.createFont();
	        f.setFontHeightInPoints((short) 12);
	        f.setColor(HSSFColor.RED.index);
	        f.setBoldweight(f.BOLDWEIGHT_BOLD);

	        cs.setFont(f);
	        
	        HSSFFont f2 = wb.createFont();
	        f2.setFontHeightInPoints((short) 10);
	        f2.setColor(HSSFColor.WHITE.index);
	        f2.setBoldweight(f2.BOLDWEIGHT_BOLD);
	        
	        HSSFCellStyle cs2 = wb.createCellStyle();
	        cs2.setBorderBottom(cs2.BORDER_THIN);
	        cs2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        cs2.setFillForegroundColor(HSSFColor.RED.index);
	        cs2.setFont(f2);

	        wb.setSheetName(0, "HSSF Test");
	        
	        Class clazz=Class.forName(template.getClazz());
	        for (int i = 0; i < dataList.size(); i++) {
	        	Object entity=dataList.get(i);
	        	
	        	if(entity.getClass() !=clazz ){
	        		continue;
	        	}
	        	
	        	r=s.createRow(i+1);
				r.setHeight((short)0x249);
			for (int j = 0; j < template.getEctList().size(); j++) {
				ExcelCellTemplate cellTemplate = template.getEctList().get(j);
				   c=r.createCell(j);
				   Object objVal= getObjVal(entity, clazz, cellTemplate.getDataKey());
				   if(objVal!=null)
					   c.setCellValue(objVal.toString());	
				}
				
			}
	        wb.write(out);
	        out.close();
	    }
	
	
	private static Object getObjVal(Object entity, Class clazz ,String propertyName) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
			Field[] fields=clazz.getDeclaredFields();
			for (Field field : fields) {
				String fieldName=field.getName();
				if(fieldName.equals(propertyName)){
					String fieldType=field.getType().getCanonicalName();
					String stringLetter=fieldName.substring(0,1).toUpperCase();
					String getName="get"+stringLetter+fieldName.substring(1);
					Method getMethod=clazz.getMethod(getName, new Class[]{});
					Object resultObj=getMethod.invoke(entity, new Object[]{});
					return resultObj;
				}
		    }
			return null;
	}
	
}
