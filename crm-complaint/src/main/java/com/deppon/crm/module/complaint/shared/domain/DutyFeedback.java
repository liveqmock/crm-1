package com.deppon.crm.module.complaint.shared.domain;

public class DutyFeedback {
	//责任划分ID
	private String detailId;
	//反馈人ID
	private String feedUserId;
	//反馈人姓名
	private String feedUserName;
	//反馈部门ID
	private String feedDeptId;
	//反馈部门名称
	private String feedDeptName;
	//反馈内容
	private String describe;
	//统计员ID
	private String statUserId;
	//统计员姓名
	private String statUserName;
	//统计员所属部门ID
	private String statDeptId;
	//统计员审批意见
	private String statDecision;
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
	//审批依据
	private String according;
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
	public String getStatUserId() {
		return statUserId;
	}
	public void setStatUserId(String statUserId) {
		this.statUserId = statUserId;
	}
	public String getStatUserName() {
		return statUserName;
	}
	public void setStatUserName(String statUserName) {
		this.statUserName = statUserName;
	}
	public String getStatDeptId() {
		return statDeptId;
	}
	public void setStatDeptId(String statDeptId) {
		this.statDeptId = statDeptId;
	}
	public String getStatDecision() {
		return statDecision;
	}
	public void setStatDecision(String statDecision) {
		this.statDecision = statDecision;
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
	public String getAccording() {
		return according;
	}
	public void setAccording(String according) {
		this.according = according;
	}

}
