package com.deppon.crm.module.duty.shared.domain;

import java.util.Date;

public class DutyFeedback {
	//责任反馈ID
	private String feedbackId;
	//责任划分结果ID
	private String detailId;
	//工单责任ID
	private String dutyId;
	//反馈人ID
	private String feedUserId;
	//反馈人姓名
	private String feedUserName;
	//反馈部门/责任部门ID
	private String feedDeptId;
	//反馈部门/责任部门名称
	private String feedDeptName;
	//反馈内容
	private String describe;
	
	//呼叫中心审批员ID
	private String callCenUserId;
	//呼叫中心审批员姓名
	private String callCenUser;
	//呼叫中心部门ID
	private String callCenDeptId;
	//呼叫中心审批意见
	private String callCenDescision;
	//工单责任状态
	private String status;
	//审批状态
	private String appStatus;
	//是否为补充反馈
	private String addFeedStatus;
	
	// 反馈时间
	private Date feedBackTime;	
	
	// 呼叫中心时间
	private Date callcenterTime;
	//是否有附件
	private String haveFeedAttach;
	//审批结果，对应责任划分结果表中的调查结果字段
	private String approvalResult;
	//“退回营业部重新反馈”勾选框的对话框是否选择
	private boolean isSelected;
	//来电人
	private String caller;
	//上报类型
	private String reportType;
	/**
     * 重复工单绑定码,即重复投拆绑定
     */
    private String rebindNo;
    //凭证号
    private String voucherNumber;
    //处理编号
    private String dealNumber;
    //调查结果
  	private String surveyResult;
  	//反馈时限
  	private Date dutyDeadLine;
  	//责任部门所在事业部编码
  	private String bussDeptCode;
	
	/**
	 * @return isSelected : return the property isSelected.
	 */
	public boolean getIsSelected() {
		return isSelected;
	}
	/**
	 * @param isSelected : set the property isSelected.
	 */
	public void setIsSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	/**
	 * @param dutyId the dutyId to get
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
	 * @param approvalResult the approvalResult to get
	 */
	public String getApprovalResult() {
		return approvalResult;
	}
	/**
	 * @param approvalResult the approvalResult to set
	 */
	public void setApprovalResult(String approvalResult) {
		this.approvalResult = approvalResult;
	}
	public String getHaveFeedAttach() {
		return haveFeedAttach;
	}
	public void setHaveFeedAttach(String haveFeedAttach) {
		this.haveFeedAttach = haveFeedAttach;
	}
	/**
	 * @return the feedBackTime
	 */
	public Date getFeedBackTime() {
		return feedBackTime;
	}
	public void setFeedBackTime(Date feedBackTime) {
		this.feedBackTime = feedBackTime;
	}
	
	/**
	 * @return the callcenterTime
	 */
	public Date getCallcenterTime() {
		return callcenterTime;
	}
	/**
	 * @param callcenterTime the callcenterTime to set
	 */
	public void setCallcenterTime(Date callcenterTime) {
		this.callcenterTime = callcenterTime;
	}
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getFeedUserId() {
		return feedUserId;
	}
	public void setFeedUserId(String feedUserId) {
		this.feedUserId = feedUserId;
	}
	public String getFeedUserName() {
		return feedUserName;
	}
	public void setFeedUserName(String feedUserName) {
		this.feedUserName = feedUserName;
	}
	public String getFeedDeptId() {
		return feedDeptId;
	}
	public void setFeedDeptId(String feedDeptId) {
		this.feedDeptId = feedDeptId;
	}
	public String getFeedDeptName() {
		return feedDeptName;
	}
	public void setFeedDeptName(String feedDeptName) {
		this.feedDeptName = feedDeptName;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	public String getCallCenUserId() {
		return callCenUserId;
	}
	public void setCallCenUserId(String callCenUserId) {
		this.callCenUserId = callCenUserId;
	}
	public String getCallCenUser() {
		return callCenUser;
	}
	public void setCallCenUser(String callCenUser) {
		this.callCenUser = callCenUser;
	}
	public String getCallCenDeptId() {
		return callCenDeptId;
	}
	public void setCallCenDeptId(String callCenDeptId) {
		this.callCenDeptId = callCenDeptId;
	}
	public String getCallCenDescision() {
		return callCenDescision;
	}
	public void setCallCenDescision(String callCenDescision) {
		this.callCenDescision = callCenDescision;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getAddFeedStatus() {
		return addFeedStatus;
	}
	public void setAddFeedStatus(String addFeedStatus) {
		this.addFeedStatus = addFeedStatus;
	}
	
	public String getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
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
	 * @return rebindno : return the property rebindno.
	 */
	public String getRebindNo() {
		return rebindNo;
	}
	/**
	 * @param rebindNo : set the property rebindNo.
	 */
	public void setRebindNo(String rebindNo) {
		this.rebindNo = rebindNo;
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
	 * @return the bussDeptCode
	 */
	public String getBussDeptCode() {
		return bussDeptCode;
	}
	/**
	 * @param bussDeptCode the bussDeptCode to set
	 */
	public void setBussDeptCode(String bussDeptCode) {
		this.bussDeptCode = bussDeptCode;
	}
	
}
