package com.deppon.crm.module.marketingReport.server.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.common.file.domain.exception.FileException;
import com.deppon.crm.module.common.file.domain.exception.FileExceptionType;
import com.deppon.crm.module.marketing.server.manager.IWarnLostCustManager;
import com.deppon.crm.module.marketing.shared.domain.WarnLostCust;
import com.deppon.crm.module.marketingReport.server.manager.IWarnLostCustReportManager;
import com.deppon.crm.module.report.shared.domain.WarnLostCustDaily;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;
/**
 * 
 * 预警客户监控Action
 *
 */
public class WarnLostCustReportAction extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7324431045919828067L;
	//流失报表manager
	private IWarnLostCustReportManager warnLostCustReportManager;
	//流失预警客户manager
	private IWarnLostCustManager warnLostCustManager;
	//日报表集合
	private List<WarnLostCustDaily> warnLostCustDailyList;
	//开始时间
	private Date beginTime;
	//结束时间
	private Date endTime;
	//部门id
	private String deptid;
	//流失预警每日报表
	private WarnLostCustDaily warnLostCustDailyTotal;
	//传入前台的list
	private List<WarnLostCust> warnLostCustList;
	//dep 营业部  area 小区 bigarea 大区 carrer 事业部   card 本部
	private String deptLevel;
	//导出文件的文件输入流
	private InputStream excelStream; 
	//导出文件名
	private String exportExcelName;     
	 //文件的下载地址
	private String downloadurl;              
	///misc/crmfile/warnLostCustReport/
	private static final String filepath="/misc/crmfile/warnLostCustReport/";       //文件路径
	
	/*
	 * 日报表统计
	 * 
	 */
	public String queryWarnCustReportDailyBatch(){
		warnLostCustDailyList = warnLostCustReportManager.getWarnCustReportDailyBatch(deptid,beginTime,endTime, start, limit, deptLevel);
		totalCount = (long) warnLostCustReportManager.getWarnCustReportCount(deptid, beginTime, endTime, deptLevel);
		return SUCCESS;
	}
	/*
	 * 日报表统计的最后一条统计数据
	 * 
	 * 
	 */
	public String queryWarnLostCustDailyTotal(){
		warnLostCustDailyTotal = warnLostCustReportManager.getWarnLostReportTotal(deptid, beginTime, endTime, deptLevel);
		return SUCCESS;
	}
	/*
	 * 日报表明细
	 * 
	 * 
	 */
	@JSON
	public String queryWarnLostCust(){
		warnLostCustList = warnLostCustManager.searchWarnLostCust(deptid,null,null, beginTime, endTime,start,limit);
		int totalCounts = warnLostCustManager.searchWarnLostCustCount(deptid, null, null, beginTime, endTime);
		totalCount = (long)totalCounts ;
		return SUCCESS;
	}
	/**
	 * 导出日报表
	 * @author 148706
	 * @throws Exception
	 * 时间： 2014-5-4
	 * 
	 * 
	 */
	public String exportWarnLostCustReportExcel() throws Exception{
		//设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		//设置日期格式
		SimpleDateFormat dfpath = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String ExcelName=df.format(new Date());
			String ExcelPath=dfpath.format(new Date());
			//得到流
			excelStream=warnLostCustReportManager.exportExcel(deptid, beginTime, endTime,deptLevel);
			//新建文件目录 
			File filedir=new File(filepath+ExcelPath);       
			 //新建文件  
			File file=new File(filepath+ExcelPath+"//"+ExcelName);   
			//如果不存在目录则创建
			if(!filedir.exists()){                                
				//新建目录
				filedir.mkdirs();
				//新建文件
				file.createNewFile();
			}else{
				//路径存在时，直接创建文件
				file.createNewFile();
			}
			FileOutputStream fos=new FileOutputStream(file);
			   int bytesRead = 0;
			   byte[] buffer = new byte[8192];
			   while ((bytesRead = excelStream.read(buffer, 0, 8192)) != -1) {
			    fos.write(buffer, 0, bytesRead);
			   }
			   fos.close();
			   //向前台返回文件的下载url
			   downloadurl=file.getAbsolutePath();  
		} catch (Exception e) {
			//抛出异常
			e.printStackTrace();
			throw e;
		}
		return SUCCESS;
	}
	/**
	 * 导出流失预警客户详细
	 * 
	 * @author 148706
	 * @throws Exception
	 * 时间： 2014-5-4
	 * 
	 */
	public String exportWarnLostCustDetail() throws Exception{
		//设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		//设置日期格式
		SimpleDateFormat dfpath = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//文件名字符串
			String ExcelName=df.format(new Date());
			//生成文件路径的文件夹
			String ExcelPath=dfpath.format(new Date());
			//得到流
			excelStream=warnLostCustManager.searchWarnLostCust(deptid, beginTime, endTime);
			 //新建文件目录 
			File filedir=new File(filepath+ExcelPath);   
		     //新建文件  
			File file=new File(filepath+ExcelPath+"//"+ExcelName);     
			 //如果不存在目录则创建
			if(!filedir.exists()){                               
				//新建目录
				filedir.mkdirs();
				//新建文件
				file.createNewFile();
			}else{
				//如果存在，直接创建文件
				file.createNewFile();
			}
			//将文件转换成流
			FileOutputStream fos=new FileOutputStream(file);
			int bytesRead = 0;
			   byte[] buffer = new byte[8192];
			   while ((bytesRead = excelStream.read(buffer, 0, 8192)) != -1) {
			    fos.write(buffer, 0, bytesRead);
			   }
			   fos.close();
			   //向前台返回文件的下载url
			   downloadurl=file.getAbsolutePath();  
		} catch (Exception e) {
			e.printStackTrace();
			//抛出异常
			throw e;
		}
		return SUCCESS;
	}
	
	/*
	 * 下载已生成的的文件
	 */
	public String downloadDetail() throws Exception{
		try {
			exportExcelName="123.xls";
			InputStream inputStream = new FileInputStream(downloadurl);//得到文件
			excelStream =inputStream; 
		} catch (FileNotFoundException e) {
			 //抛出文件异常
			FileException fe = new FileException(
					FileExceptionType.FILE_EXCEPTION_FILENOTEXPORT);
			throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
					new Object[] {}) {
						private static final long serialVersionUID = -8025380332272242072L;
			};
		}
		return SUCCESS;
	}
	/**
	 * @param deptids the deptid to set
	 */
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the warnLostCustDailyList
	 */
	public List<WarnLostCustDaily> getWarnLostCustDailyList() {
		return warnLostCustDailyList;
	}
	/**
	 * @param warnLostCustManager the warnLostCustManager to set
	 */
	public void setWarnLostCustManager(IWarnLostCustManager warnLostCustManager) {
		this.warnLostCustManager = warnLostCustManager;
	}
	/**
	 * @return the warnLostCustList
	 */
	public List<WarnLostCust> getWarnLostCustList() {
		return warnLostCustList;
	}
	/**
	 * @param warnLostCustReportManager the warnLostCustReportManager to set
	 */
	public void setWarnLostCustReportManager(
			IWarnLostCustReportManager warnLostCustReportManager) {
		this.warnLostCustReportManager = warnLostCustReportManager;
	}
	/**
	 * @param deptLevel the deptLevel to set
	 */
	public void setDeptLevel(String deptLevel) {
		this.deptLevel = deptLevel;
	}
	/**
	 * @return the warnLostCustDailyTotal
	 */
	public WarnLostCustDaily getWarnLostCustDailyTotal() {
		return warnLostCustDailyTotal;
	}
	/**
	 * @return excelStream
	 */
	public InputStream getExcelStream() {
		return excelStream;
	}
	/**
	 *  excelStream  的set方法
	 */
	public void setExcelStream(FileInputStream excelStream) {
		this.excelStream = excelStream;
	}
	/**
	 * exportExcelName 的get方法
	 */
	public String getExportExcelName() {
		return exportExcelName;
	}
	/**
	 * downloadurl 的get方法
	 */
	public String getDownloadurl() {
		return downloadurl;
	}
	/**
	 * downloadurl 的set方法
	 */
	public void setDownloadurl(String downloadurl) {
		this.downloadurl = downloadurl;
	}
}
