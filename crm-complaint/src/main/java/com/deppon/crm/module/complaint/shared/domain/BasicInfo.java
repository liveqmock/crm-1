package com.deppon.crm.module.complaint.shared.domain;

import java.util.Date;

public class BasicInfo {
	//业务项Id
	private String busItemId;
	//业务项名称
	private String busItemName;
	//业务范围ID
	private String busScopeId;
	//业务范围名称
	private String busScopeName;
	//业务类型Id
	private String busTypeId;
	//业务类型名称
	private String busTypeName;
	//上报类型
	private String reportType;
	//处理语言
	private String dealLanguage;
	//处理时限
	private String procLimit;
	//反馈时限
	private String feedbackLimit;
	//最后修改时间
	private Date lastUpdateTime;
	public String getBusItemId() {
		return busItemId;
	}
	public void setBusItemId(String busItemId) {
		this.busItemId = busItemId;
	}
	public String getBusItemName() {
		return busItemName;
	}
	public void setBusItemName(String busItemName) {
		this.busItemName = busItemName;
	}
	public String getBusScopeId() {
		return busScopeId;
	}
	public void setBusScopeId(String busScopeId) {
		this.busScopeId = busScopeId;
	}
	public String getBusScopeName() {
		return busScopeName;
	}
	public void setBusScopeName(String busScopeName) {
		this.busScopeName = busScopeName;
	}
	public String getBusTypeId() {
		return busTypeId;
	}
	public void setBusTypeId(String busTypeId) {
		this.busTypeId = busTypeId;
	}
	public String getBusTypeName() {
		return busTypeName;
	}
	public void setBusTypeName(String busTypeName) {
		this.busTypeName = busTypeName;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getDealLanguage() {
		return dealLanguage;
	}
	public void setDealLanguage(String dealLanguage) {
		this.dealLanguage = dealLanguage;
	}
	public String getProcLimit() {
		return procLimit;
	}
	public void setProcLimit(String procLimit) {
		this.procLimit = procLimit;
	}
	public String getFeedbackLimit() {
		return feedbackLimit;
	}
	public void setFeedbackLimit(String feedbackLimit) {
		this.feedbackLimit = feedbackLimit;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	
}
