package com.deppon.crm.module.duty.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

public class SearchDutyResultVO extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	//责任ID
	private String dutyId;
	//凭证号
	private String voucherNumber;
	//处理编号
	private String dealNumber;
	//上报类型
	private String reportType;
	//重复工单码
	private String rebindNo;
	//来电人
	private String caller;
	//上报人
	private String reporter;
	//上报时间
	private Date reportTime;
	//责任划分人ID
	private String appDutyUserId;
	//责任划分人
	private String appDutyUser;
	//责任划分时间
	private Date appDutyTime;
	//工单编号
	private String complaintid;
	
	//划分结果id
	private String dutyResultId;
	//责任人ID
	private String dutyPerId;
	//划分类型
	private String divideType;
	//责任人名称
	private String dutyPerName;
	//反馈时限
	private Date dutyDeadLine;
	//责任状态
	private String dutyStatus;
	//调查结果
	private String surveyResult;
	
	// 业务模式 快递 EXPRESS 零担UNEXPRESS
	private String businessModel;
	
	//是否为 400 责任部门，0 普通部门，1特殊部门。
	private String dutyDepartmentCC;
	
	private String dutyDeptId;//责任部门编号/个人所在责任部门编号
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
	/**
	 * @return the voucherNumber
	 */
	public String getVoucherNumber() {
		return voucherNumber;
	}
	/**
	 * @param voucherNumber the voucherNumber to set
	 */
	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}
	/**
	 * @return the dealNumber
	 */
	public String getDealNumber() {
		return dealNumber;
	}
	/**
	 * @param dealNumber the dealNumber to set
	 */
	public void setDealNumber(String dealNumber) {
		this.dealNumber = dealNumber;
	}
	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}
	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	/**
	 * @return the rebindNo
	 */
	public String getRebindNo() {
		return rebindNo;
	}
	/**
	 * @param rebindNo the rebindNo to set
	 */
	public void setRebindNo(String rebindNo) {
		this.rebindNo = rebindNo;
	}
	/**
	 * @return the caller
	 */
	public String getCaller() {
		return caller;
	}
	/**
	 * @param caller the caller to set
	 */
	public void setCaller(String caller) {
		this.caller = caller;
	}
	/**
	 * @return the reporter
	 */
	public String getReporter() {
		return reporter;
	}
	/**
	 * @param reporter the reporter to set
	 */
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	/**
	 * @return the reportTime
	 */
	public Date getReportTime() {
		return reportTime;
	}
	/**
	 * @param reportTime the reportTime to set
	 */
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	/**
	 * @return the appDutyUserId
	 */
	public String getAppDutyUserId() {
		return appDutyUserId;
	}
	/**
	 * @param appDutyUserId the appDutyUserId to set
	 */
	public void setAppDutyUserId(String appDutyUserId) {
		this.appDutyUserId = appDutyUserId;
	}
	/**
	 * @return the appDutyUser
	 */
	public String getAppDutyUser() {
		return appDutyUser;
	}
	/**
	 * @param appDutyUser the appDutyUser to set
	 */
	public void setAppDutyUser(String appDutyUser) {
		this.appDutyUser = appDutyUser;
	}
	/**
	 * @return the appDutyTime
	 */
	public Date getAppDutyTime() {
		return appDutyTime;
	}
	/**
	 * @param appDutyTime the appDutyTime to set
	 */
	public void setAppDutyTime(Date appDutyTime) {
		this.appDutyTime = appDutyTime;
	}
	/**
	 * @return the dutyPerId
	 */
	public String getDutyPerId() {
		return dutyPerId;
	}
	/**
	 * @param dutyPerId the dutyPerId to set
	 */
	public void setDutyPerId(String dutyPerId) {
		this.dutyPerId = dutyPerId;
	}
	/**
	 * @return the dutyPerName
	 */
	public String getDutyPerName() {
		return dutyPerName;
	}
	/**
	 * @param dutyPerName the dutyPerName to set
	 */
	public void setDutyPerName(String dutyPerName) {
		this.dutyPerName = dutyPerName;
	}
	/**
	 * @return the dutyDeadLine
	 */
	public Date getDutyDeadLine() {
		return dutyDeadLine;
	}
	/**
	 * @param dutyDeadLine the dutyDeadLine to set
	 */
	public void setDutyDeadLine(Date dutyDeadLine) {
		this.dutyDeadLine = dutyDeadLine;
	}
	/**
	 * @return the dutyStatus
	 */
	public String getDutyStatus() {
		return dutyStatus;
	}
	/**
	 * @param dutyStatus the dutyStatus to set
	 */
	public void setDutyStatus(String dutyStatus) {
		this.dutyStatus = dutyStatus;
	}
	/**
	 * @return the surveyResult
	 */
	public String getSurveyResult() {
		return surveyResult;
	}
	/**
	 * @param surveyResult the surveyResult to set
	 */
	public void setSurveyResult(String surveyResult) {
		this.surveyResult = surveyResult;
	}
	/**
	 * @return the dutyResultId
	 */
	public String getDutyResultId() {
		return dutyResultId;
	}
	/**
	 * @param dutyResultId the dutyResultId to set
	 */
	public void setDutyResultId(String dutyResultId) {
		this.dutyResultId = dutyResultId;
	}
	/**
	 * @return the divideType
	 */
	public String getDivideType() {
		return divideType;
	}
	/**
	 * @param divideType the divideType to set
	 */
	public void setDivideType(String divideType) {
		this.divideType = divideType;
	}
	/**
	 * @return the complaintid
	 */
	public String getComplaintid() {
		return complaintid;
	}
	/**
	 * @param complaintid the complaintid to set
	 */
	public void setComplaintid(String complaintid) {
		this.complaintid = complaintid;
	}
	public String getBusinessModel() {
		return businessModel;
	}
	public void setBusinessModel(String businessModel) {
		this.businessModel = businessModel;
	}
	/**
	 * @return the dutyDepartmentCC
	 */
	public String getDutyDepartmentCC() {
		return dutyDepartmentCC;
	}
	/**
	 * @param dutyDepartmentCC the dutyDepartmentCC to set
	 */
	public void setDutyDepartmentCC(String dutyDepartmentCC) {
		this.dutyDepartmentCC = dutyDepartmentCC;
	}
	/**
	 * @return the dutyDeptId
	 */
	public String getDutyDeptId() {
		return dutyDeptId;
	}
	/**
	 * @param dutyDeptId the dutyDeptId to set
	 */
	public void setDutyDeptId(String dutyDeptId) {
		this.dutyDeptId = dutyDeptId;
	}
	
}
