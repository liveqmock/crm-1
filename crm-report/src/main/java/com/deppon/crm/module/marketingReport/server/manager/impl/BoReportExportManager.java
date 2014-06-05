package com.deppon.crm.module.marketingReport.server.manager.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.common.file.domain.exception.FileException;
import com.deppon.crm.module.common.file.domain.exception.FileExceptionType;
import com.deppon.crm.module.common.file.util.ExcelExporter;
import com.deppon.crm.module.common.file.util.FileUtil;
import com.deppon.crm.module.common.file.util.PropertiesUtil;
import com.deppon.crm.module.marketing.shared.domain.BoReportCondition;
import com.deppon.crm.module.marketing.shared.domain.BoReportInfo;
import com.deppon.crm.module.marketingReport.server.manager.IBoReportExportManager;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * @description 会员360度视图manager实现.
 * @author 安小虎
 * 
 * @version 0.1 2012-4-23
 * @date 2012-4-23
 * 
 */
@Transactional
public class BoReportExportManager implements IBoReportExportManager {
	/*
	 * 文件名称
	 */
	private static final String EXCELNAME = "boReportExcel.xlsx";
	/*
	 * (non-Javadoc)
	 * @see com.deppon.crm.module.custview.server.manager.IMember360ViewManager#createExcel(java.util.List, java.util.Date, java.util.Date, java.lang.String)
	 */
	@Override
	public String createExcel(List<BoReportInfo> boReportInfoList,
			BoReportCondition brc) {
		String path = PropertiesUtil.getInstance()
				.getProperty("excelExportTemplatePath").trim();
		String filePath = null;
		    /* 组织文件路径 */
			filePath= path + "/" + EXCELNAME;
		ExcelExporter exporter = new ExcelExporter();
		/* 获得工作薄 */
		XSSFWorkbook wb = exporter.getExcel07Wb(filePath);
		wb = exporter.setExcel07WbValue(wb, 1, 0, brc.getDeptName());
		for (int i = 0; i < boReportInfoList.size(); i++) {
			    XSSFSheet sheet = wb.getSheetAt(0);
				Row row = sheet.getRow(i+2);
				if(row==null){
					row = sheet.createRow(i+2);
				}
			for(int j = 0;j<33;j++ ){
				if(j==0){
					//部门名称
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getDeptName(), cell);
				}else if(j==1){
					//新建商机数量
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getCreateNum()+"", cell);
				}else if(j==2){
					//当前进行中商机数
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getOngoingNum()+"", cell);
				}else if(j==3){
					// 当前超期进行中商机数
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getExtendedNum()+"", cell);
				}else if(j==4){
					// 当前已休眠商机数
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getDormantNum()+"", cell);
				}else if(j==5){
					//商机预计发货总金额
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getExpectDeliveryAmount()+"", cell);
				}else if(j==6){
					// 当前初步接触阶段商机数
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getContactNum()+"", cell);
				}else if(j==7){
					// 本月初步接触阶段商机回访次数
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getContactReturnVisitNum()+"", cell);
				}else if(j==8){
					// 本月初步接触阶段转化商机数
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getContactTransNum()+"", cell);
				}else if(j==9){
					// 初步接触阶段转化率
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getContactTransRate()+"%", cell);
				}else if(j==10){
					// 当前需求分析阶段商机数
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getAnalyzeNum()+"", cell);
				}else if(j==11){
					// 本月需求分析阶段商机回访次数
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getAnalyzeReturnVisitNum()+"", cell);
				}else if(j==12){
					// 本月需求分析阶段转化商机数
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getAnalyzeTransNum()+"", cell);
				}else if(j==13){
					// 需求分析阶段转化率
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getAnalyzeTransRate()+"%", cell);
				}else if(j==14){
					// 当前制定方案阶段商机数
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getSchemeNum()+"", cell);
				}else if(j==15){
					// 本月制定方案阶段商机回访次数
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getSchemeReturnVisitNum()+"", cell);
				}else if(j==16){
					// 本月制定方案阶段转化商机数
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getSchemeTransNum()+"", cell);
				}else if(j==17){
					// 制定方案阶段转化率
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getSchemeTransRate()+"%", cell);
				}else if(j==18){
					// 当前报价/竞标阶段商机数
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getOfferNum()+"", cell);
				}else if(j==19){
					// 本月报价/竞标阶段商机回访次数
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getOfferReturnVisitNum()+"", cell);
				}else if(j==20){
					// 本月报价/竞标阶段转化商机数
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getOfferTransNum()+"", cell);
				}else if(j==21){
					// 报价/竞标阶段转化率
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getOfferTransRate()+"%", cell);
				}else if(j==22){
					// 当前持续发货阶段商机数
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getDeliverNum()+"", cell);
				}else if(j==23){
					// 本月持续发货阶段商机回访次数
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getDeliverReturnVisitNum()+"", cell);
				}else if(j==24){
					// 本月持续发货阶段转化商机数
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getDeliverTransNum()+"", cell);
				}else if(j==25){
					// 持续发货阶段转化率
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getDeliverTransRate()+"%", cell);
				}else if(j==26){
					// 失败关闭商机数
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getFailureNum()+"", cell);
				}else if(j==27){	
					// 成功关闭商机数
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getSuccessNum()+"", cell);
				}else if(j==28){
					// 商机成功率
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getSuccessRate()+"%", cell);
				}else if(j==29){
					// 开发成功商机对应客户发货量
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getDeliverAmount()+"", cell);
				}else if(j==30){
					// 本月应执行的商机日程数量
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getScheduleNum()+"", cell);
				}else if(j==31){
					// 本月内实际执行的商机日程数量
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getPerformNum()+"", cell);
				}else if(j==32){
					// 本月商机日程完成率
					Cell cell = row.getCell(j);
					if(cell==null){
						cell = row.createCell(j);
					}
					cell = transValueToCell(boReportInfoList.get(i).getPerformRate()+"%", cell);
				}
			}
		}
		String createPath = PropertiesUtil.getInstance()
				.getProperty("excelExportFilePath").trim();
		String fileName = FileUtil.createFileName(".xlsx");
		return ExcelExporter.createExcel(wb, createPath, fileName);
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
	@Override
	public String getExcrlName(Date searchOrderDateFrom, Date searchOrderDateTo,String deptName) {
		/*
		 * 格式化日期
		 */
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String from = sf.format(searchOrderDateFrom);
		String to = sf.format(searchOrderDateTo);
		String downFileName = null;
		if(deptName=="事业部"){
			downFileName = from + "~" + to + "商机效果评估报表（总）.xlsx";
		}else{
			downFileName = from + "~" + to + "商机效果评估报表.xlsx";
		}
		try {
			/*
			 * 字符编码转换
			 */
			downFileName = new String(downFileName.getBytes(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return downFileName;
	}
	@Override
	public InputStream getExcel(String realPath) {
		InputStream inputStream = null;
		try {
			/*
			 * 创建文件输入流
			 */
			inputStream = new FileInputStream(realPath);
		} catch (FileNotFoundException e) {
			/*
			 * 跑出文件异常
			 */
			FileException fe = new FileException(
					FileExceptionType.FILE_EXCEPTION_FILENOTEXPORT);
			throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
					new Object[] {}) {
			};
		}
		return inputStream;
	}
}
