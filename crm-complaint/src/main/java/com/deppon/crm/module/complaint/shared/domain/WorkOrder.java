package com.deppon.crm.module.complaint.shared.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

public class WorkOrder extends BaseEntity {
	/**
	 * 主键id
	 */
	private BigDecimal fid;	

	/**
	 * 投诉(工单)ID
	 */
	private BigDecimal complaintId;
	
	/**
	 * 操作人ID
	 */
	private BigDecimal operatorId;
	
	/**
	 * 操作人名字
	 */
	private String operatorName;
	
	/**
	 * 操作时间
	 */
	private Date operatorTime;
	
	/**
	 * 操作类型
	 */
	private String operatorType;
	
	/**
	 * 操作记录（动作）
	 */
	private String operatorRecord;
	
	/**
	 * 当前状态
	 */
	private String currentState;
	
	/**
	 * 前一状态
	 */
	private String previousState;
	
	/**
	 * 操作人角色ID
	 */
	private BigDecimal operatorRoleId;
	/**
	 * 工单对象
	 */
	private Complaint complaint;
	
	/**
	 * 调查建议(7.5新增)
	 */
	private String suggestion;
	// suggestion get 方法
	public String getSuggestion() {
		return suggestion;
	}
	// suggestion set 方法
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	// fid get 方法
	public BigDecimal getFid() {
		return fid;
	}
	// fid set 方法
	public void setFid(BigDecimal fid) {
		this.fid = fid;
	}
	// complaintId get 方法
	public BigDecimal getComplaintId() {
		return complaintId;
	}
	// complaintId set 方法
	public void setComplaintId(BigDecimal complaintId) {
		this.complaintId = complaintId;
	}
	// complaintId get 方法
	public BigDecimal getOperatorId() {
		return operatorId;
	}
	// complaintId set 方法
	public void setOperatorId(BigDecimal operatorId) {
		this.operatorId = operatorId;
	}
	// operatorName get 方法
	public String getOperatorName() {
		return operatorName;
	}
	// operatorName set 方法
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	// operatorTime get 方法
	public Date getOperatorTime() {
		return operatorTime;
	}
	// operatorTime set 方法
	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}
	// operatorType get 方法
	public String getOperatorType() {
		return operatorType;
	}
	// operatorType set 方法
	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}
	// operatorRecord get 方法
	public String getOperatorRecord() {
		return operatorRecord;
	}
	// operatorRecord set 方法
	public void setOperatorRecord(String operatorRecord) {
		this.operatorRecord = operatorRecord;
	}
	// currentState get 方法
	public String getCurrentState() {
		return currentState;
	}
	// currentState set 方法
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
	// previousState get 方法
	public String getPreviousState() {
		return previousState;
	}
	// previousState set 方法
	public void setPreviousState(String previousState) {
		this.previousState = previousState;
	}
	// operatorRoleId get 方法
	public BigDecimal getOperatorRoleId() {
		return operatorRoleId;
	}
	// operatorRoleId set 方法
	public void setOperatorRoleId(BigDecimal operatorRoleId) {
		this.operatorRoleId = operatorRoleId;
	}
	// complaint get 方法
	public Complaint getComplaint() {
		return complaint;
	}
	// complaint set 方法
	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

}
