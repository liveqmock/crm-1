package com.deppon.crm.module.marketingReport.server.action;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.marketing.server.manager.IBusinessOpportunityManager;
import com.deppon.crm.module.marketing.shared.domain.BoReportCondition;
import com.deppon.crm.module.marketing.shared.domain.BoReportInfo;
import com.deppon.crm.module.marketingReport.server.manager.IBoReportExportManager;
import com.deppon.foss.framework.server.web.action.AbstractAction;

public class BoReportAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IBusinessOpportunityManager businessOpportunityManager;

	public void setBusinessOpportunityManager(
			IBusinessOpportunityManager businessOpportunityManager) {
		this.businessOpportunityManager = businessOpportunityManager;
	}
	private IBoReportExportManager boReportExportManager;
	
	/**
	 * @param boReportExportManager the boReportExportManager to set
	 */
	public void setBoReportExportManager(
			IBoReportExportManager boReportExportManager) {
		this.boReportExportManager = boReportExportManager;
	}
	//商机效果评估
	private List<BoReportInfo> boReportInfoList;
	
	/**
	 * @return the boReportInfoList
	 */
	public List<BoReportInfo> getBoReportInfoList() {
		return boReportInfoList;
	}
    private BoReportCondition brc;
    
	/**
	 * @param brc the brc to set
	 */
	public void setBrc(BoReportCondition brc) {
		this.brc = brc;
	}
	/**
	 * @return the brc
	 */
	public BoReportCondition getBrc() {
		return brc;
	}
	//导出的文件
	private InputStream inputStream;
	//文件名称
    private String downloadFileName;
    //文件路径
    private String realPath;
    
	/**
	 * @return the realPath
	 */
	public String getRealPath() {
		return realPath;
	}
	/**
	 * @param realPath the realPath to set
	 */
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
	/**
	 * @return downloadFileName : return the property downloadFileName.
	 */
	public String getDownloadFileName() {
		return downloadFileName;
	}
	/**
	 * @param downloadFileName the downloadFileName to set
	 */
	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}
	/**
	 * @return inputStream : return the property inputStream.
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * 
	* @Title: searchBoReport 
	* @Description: 查询商机报表
	* @author zhangbin 
	* @param @return    设定文件 
	* @throws
	 */
	
	public String searchBoReport(){
		boReportInfoList = businessOpportunityManager.queryBoReportByCondition(brc);
		return SUCCESS;
		
	}
	
	/**
	 * 
	 * @description 下载.
	 * @author zhangbin
	 * @version 0.1 2012-4-17
	 * @date 2014-4-17
	 * @update 2014-4-17 上午10:05:52
	 */
	public String createBoReportExcel() {
		boReportInfoList = businessOpportunityManager.queryBoReportByCondition(brc);
		/*
		 * 创建 Excel
		 */
		realPath = boReportExportManager.createExcel(boReportInfoList,brc);
		/*
		 * 获取 Excel 文件名
		 */
	   downloadFileName = boReportExportManager.getExcrlName(brc.getBeginTime(), brc.getEndTime(),brc.getDeptName());
	   return SUCCESS;
	}
	
	/**
	 * 
	 * @description 下载.
	 * @author zhangbin
	 * @version 0.1 2012-4-17
	 * @date 2014-4-17
	 * @update 2014-4-17 上午10:05:52
	 */
	public String exportBoReportExcel() {
		/*
		 * 创建 Excel
		 */
		inputStream = boReportExportManager.getExcel(realPath);
		return SUCCESS;
	}
	
}
