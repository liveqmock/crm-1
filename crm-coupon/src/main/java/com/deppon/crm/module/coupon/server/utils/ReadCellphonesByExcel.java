package com.deppon.crm.module.coupon.server.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.deppon.crm.module.common.file.util.FileDlpUtil;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphone;
import com.deppon.crm.module.coupon.shared.domain.MarketingCouponConstance;
import com.deppon.crm.module.coupon.shared.exception.CouponExceptionType;
import com.deppon.crm.module.coupon.shared.exception.ExceptionUtils;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReadCellphonesByExcel.java
 * @package com.deppon.crm.module.coupon.server.util 
 * @author ZhouYuan
 * @version 0.1 2012-11-22
 */
public class ReadCellphonesByExcel {
	/**
	 * <p>
	 * Description: 读取excel文件将文件中的手机号码封装成一个CouponCellphone对象<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-20
	 * @param filePath
	 * @return List<CouponCellphone>
	 */
	public static List<CouponCellphone> getMoblies(File file,String fileName){
		long start = 0;
		List<CouponCellphone> list = new LinkedList<CouponCellphone>();
		
		Workbook workbook = null;
		
		
		InputStream inputStream = null;
		//解密excel 
		String newPath = FileDlpUtil.getDecryptFile(file.getPath());
		
		file = new File(newPath);
		try {
			inputStream = new FileInputStream(file);
		
			//根据文件后缀名创建相应excel的workbook
			if(fileName.matches("^.+\\.(?i)(xlsx)$")){
				//创建excel2007版本的工作簿
				workbook = new XSSFWorkbook(inputStream);
			}else if(fileName.matches("^.+\\.(?i)(xls)$")){
				//创建excel2003版本的工作簿
				workbook = new HSSFWorkbook(inputStream);
			}else{
				//文件格式类型错误 
				ExceptionUtils.createCouponException(CouponExceptionType.excelTypeError);
			}
			if( workbook != null){
				for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
					//创建一个sheet
					Sheet sheet = workbook.getSheetAt(numSheet);
					if(sheet == null || sheet.getPhysicalNumberOfRows()<=0){
						continue;
					}
					 //文件总行数
					int totalRow = sheet.getLastRowNum()+1;
					
					//每行单元格数
					int totalCell = sheet.getRow(0).getLastCellNum();
					
					//电话号码保存在第几行
					int phoneCellNum = 0;
					//总数是否大于0,现有电话号码总数是否大于0
					if(totalRow >0&&totalCell>0){
						for(int cellNum =0;cellNum<totalCell;cellNum++){
							if(sheet.getRow(0).getCell(cellNum)!=null){
								phoneCellNum = cellNum;
							}
						} 
						for(int rowNum = 1; rowNum<totalRow; rowNum++){
							// 处理数字型的,自动去零
							String phone = null;
							if(sheet.getRow(rowNum) != null &&
									sheet.getRow(rowNum).getCell(phoneCellNum) != null){
								Cell phoneCell = sheet.getRow(rowNum).getCell(phoneCellNum);
								if (Cell.CELL_TYPE_NUMERIC == phoneCell.getCellType()) {
									// 在excel里,日期也是数字,在此要进行判断
									if (DateUtil.isCellDateFormatted(phoneCell)) {
										phone = phoneCell.getDateCellValue().toString();
									} else {
										phone = new Double(phoneCell.getNumericCellValue()).toString();
									}
								}
								// 处理字符串型
								else if (Cell.CELL_TYPE_STRING == phoneCell.getCellType()) {
									phone = phoneCell.getStringCellValue();
								}
								// 处理布尔型
								else if (Cell.CELL_TYPE_BOOLEAN == phoneCell.getCellType()) {
									phone = new Boolean(phoneCell.getBooleanCellValue()).toString();
								}
								// 其它的,非以上几种数据类型
								else {
									phone = phoneCell.toString();
								}
								CouponCellphone cp = new CouponCellphone();
								cp.setCellphone(phone);
								//判断手机号是否有效
								cp.setValidity(CouponValidator.checkCellphoneValidity(phone));
								cp.setSendStatus(MarketingCouponConstance.PHONE_SEND_STATUS_UNSEND);
								list.add(cp);
							}
						}
					}
				}
			}
			}catch(Exception e){
				//文件解析错误
				ExceptionUtils.createCouponException(CouponExceptionType.fileAnalyticError);
			}finally{
				try {
					if( inputStream != null){
						inputStream.close();
					}
				} catch (IOException e) {
					ExceptionUtils.createCouponException(CouponExceptionType.fileAnalyticError);
				}
			}
		System.out.println("---------end-----------"+System.currentTimeMillis());
		System.out.println((System.currentTimeMillis()-start)/1000.0);
		return list;
		
	}

}
