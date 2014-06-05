package com.deppon.crm.module.duty.shared.domain;

import java.util.Date;

public class DutyQueryResult{
	// 工单责任id
	private String dutyId;
	//工单责任划分ID
	private String detailId;
	// 工单id
	private String complaintId;
	//凭证号
	private String voucherNumber;
	//处理编号
	private String dealNumber;
	//上报类型
	private String reportType;
	//来电人
	private String caller;
	// 责任部门
	private String appDutyDept;
	// 反馈时限
	private Date dutyDeadLine;
	// 责任状态
	private String dutyStatus;
	//调查结果
	private String surveyResult;
	// 反馈人
	private String feedUser;
	// 反馈时间
	private Date feedTime;
	//责任划分人
	private String appDutyUser;
	//责任划分时间
	private Date appDutyTime;
	//处理人
	private String operateName;
	//处理时间
	private Date operateDate;
	//上报人
	private String reporter;
	//上报时间
	private Date reportTime;
	//处理经过
	private String treatProcess;
	
	// 业务模式 快递 EXPRESS 零担UNEXPRESS
	private String businessModel;
	//反馈编号
	private String feedbackId;
	/**
	 *@return  operateName;
	 */
	public String getOperateName() {
		return operateName;
	}
	/**
	 * @param operateName : set the property operateName.
	 */
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	/**
	 *@return  operateDate;
	 */
	public Date getOperateDate() {
		return operateDate;
	}
	/**
	 * @param operateDate : set the property operateDate.
	 */
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	/**
	 * @return the complaintId
	 */
	public String getComplaintId() {
		return complaintId;
	}
	/**
	 * @param complaintId the complaintId to set
	 */
	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
	}
	/**
	 * @return the detailId
	 */
	public String getDetailId() {
		return detailId;
	}
	/**
	 * @param detailId the detailId to set
	 */
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	/**
	 * @return the treatProcess
	 */
	public String getTreatProcess() {
		return treatProcess;
	}
	/**
	 * @param treatProcess the treatProcess to set
	 */
	public void setTreatProcess(String treatProcess) {
		this.treatProcess = treatProcess;
	}
	/**
	 * @return voucherNumber : return the property voucherNumber.
	 */
	public String getVoucherNumber() {
		return voucherNumber;
	}
	/**
	 * @param voucherNumber : set the property voucherNumber.
	 */
	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}
	/**
	 * @return dealNumber : return the property dealNumber.
	 */
	public String getDealNumber() {
		return dealNumber;
	}
	/**
	 * @param dealNumber : set the property dealNumber.
	 */
	public void setDealNumber(String dealNumber) {
		this.dealNumber = dealNumber;
	}
	/**
	 * @return reportType : return the property reportType.
	 */
	public String getReportType() {
		return reportType;
	}
	/**
	 * @param reportType : set the property reportType.
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	/**
	 * @return caller : return the property caller.
	 */
	public String getCaller() {
		return caller;
	}
	/**
	 * @param caller : set the property caller.
	 */
	public void setCaller(String caller) {
		this.caller = caller;
	}
	/**
	 * @return appDutyDept : return the property appDutyDept.
	 */
	public String getAppDutyDept() {
		return appDutyDept;
	}
	/**
	 * @param appDutyDept : set the property appDutyDept.
	 */
	public void setAppDutyDept(String appDutyDept) {
		this.appDutyDept = appDutyDept;
	}
	/**
	 * @return dutyDeadLine : return the property dutyDeadLine.
	 */
	public Date getDutyDeadLine() {
		return dutyDeadLine;
	}
	/**
	 * @param dutyDeadLine : set the property dutyDeadLine.
	 */
	public void setDutyDeadLine(Date dutyDeadLine) {
		this.dutyDeadLine = dutyDeadLine;
	}
	/**
	 * @return dutyStatus : return the property dutyStatus.
	 */
	public String getDutyStatus() {
		return dutyStatus;
	}
	/**
	 * @param dutyStatus : set the property dutyStatus.
	 */
	public void setDutyStatus(String dutyStatus) {
		this.dutyStatus = dutyStatus;
	}
	/**
	 * @return surveyResult : return the property surveyResult.
	 */
	public String getSurveyResult() {
		return surveyResult;
	}
	/**
	 * @param surveyResult : set the property surveyResult.
	 */
	public void setSurveyResult(String surveyResult) {
		this.surveyResult = surveyResult;
	}
	/**
	 * @return feedUser : return the property feedUser.
	 */
	public String getFeedUser() {
		return feedUser;
	}
	/**
	 * @param feedUser : set the property feedUser.
	 */
	public void setFeedUser(String feedUser) {
		this.feedUser = feedUser;
	}
	/**
	 * @return feedTime : return the property feedTime.
	 */
	public Date getFeedTime() {
		return feedTime;
	}
	/**
	 * @param feedTime : set the property feedTime.
	 */
	public void setFeedTime(Date feedTime) {
		this.feedTime = feedTime;
	}
	/**
	 * @return appDutyUser : return the property appDutyUser.
	 */
	public String getAppDutyUser() {
		return appDutyUser;
	}
	/**
	 * @param appDutyUser : set the property appDutyUser.
	 */
	public void setAppDutyUser(String appDutyUser) {
		this.appDutyUser = appDutyUser;
	}
	/**
	 * @return appDutyTime : return the property appDutyTime.
	 */
	public Date getAppDutyTime() {
		return appDutyTime;
	}
	/**
	 * @param appDutyTime : set the property appDutyTime.
	 */
	public void setAppDutyTime(Date appDutyTime) {
		this.appDutyTime = appDutyTime;
	}
	/**
	 * @return reporter : return the property reporter.
	 */
	public String getReporter() {
		return reporter;
	}
	/**
	 * @param reporter : set the property reporter.
	 */
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	/**
	 * @return reportTime : return the property reportTime.
	 */
	public Date getReportTime() {
		return reportTime;
	}
	/**
	 * @param reportTime : set the property reportTime.
	 */
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	/**
	 * @return the dutyId
	 */
	public String getDutyId() {
		return dutyId;
	}
	/**
	 * @param dutyId the dutyId to set
	 */
	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}
	public String getBusinessModel() {
		return businessModel;
	}
	public void setBusinessModel(String businessModel) {
		this.businessModel = businessModel;
	}
	/**
	 * @return the feedbackId
	 */
	public String getFeedbackId() {
		return feedbackId;
	}
	/**
	 * @param feedbackId the feedbackId to set
	 */
	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}
	
}
