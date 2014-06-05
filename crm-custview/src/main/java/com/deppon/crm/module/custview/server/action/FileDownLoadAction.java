package com.deppon.crm.module.custview.server.action;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.custview.server.manager.impl.Member360ViewManager;
import com.deppon.crm.module.custview.shared.domain.TwelveMonth;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/**
 * 
 * @description 文件下载ACTION
 * @author 安小虎
 * @version 0.1 2012-4-16
 * @date 2012-4-16
 */
public class FileDownLoadAction extends AbstractAction {

	// 客户编号
	private String custId;
	//客户名称
	private String custName;
	// 查询订单时间FROM
	private Long searchOrderDateFrom;
	// 查询订单时间TO
	private Long searchOrderDateTo;
	// manager对象
	private Member360ViewManager member360ViewManager;
	//导出的文件
	private InputStream inputStream;
	//文件名称
    private String downloadFileName;
    //客户类别
    private String custType;
	/**
	 * @return inputStream : return the property inputStream.
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @param custId : set the property custId.
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}

	/**
	 * @param custName : set the property custName.
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * @param searchOrderDateFrom : set the property searchOrderDateFrom.
	 */
	public void setSearchOrderDateFrom(Long searchOrderDateFrom) {
		this.searchOrderDateFrom = searchOrderDateFrom;
	}

	/**
	 * @param searchOrderDateTo : set the property searchOrderDateTo.
	 */
	public void setSearchOrderDateTo(Long searchOrderDateTo) {
		this.searchOrderDateTo = searchOrderDateTo;
	}

	/**
	 * @param member360ViewManager : set the property member360ViewManager.
	 */
	public void setMember360ViewManager(Member360ViewManager member360ViewManager) {
		this.member360ViewManager = member360ViewManager;
	}

	/**
	 * @return downloadFileName : return the property downloadFileName.
	 */
	public String getDownloadFileName() {
		return downloadFileName;
	}
	
	/**
	 * @param custType : set the property custType.
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}

	/**
	 * 
	 * @description 下载.
	 * @author 安小虎
	 * @version 0.1 2012-4-17
	 * @date 2012-4-17
	 * @update 2012-4-17 上午10:05:52
	 */
	public String downLoad() {
		Date fromDate = new Date(searchOrderDateFrom);
		Date toDate = new Date(searchOrderDateTo);
		/*
		 * TODO:统计分析结果结构转换
		 */
		List<TwelveMonth> twelveMonthList = member360ViewManager
				.getOperationAnalysisListByCustId(custType,custId,fromDate,toDate);
		/*
		 * 创建 Excel
		 */
		inputStream = member360ViewManager.createExcel(custType,twelveMonthList,fromDate,toDate,custName);
		/*
		 * 获取 Excel 文件名
		 */
		downloadFileName = member360ViewManager.getExcrlName(fromDate, toDate);
		return SUCCESS;
	}
}
