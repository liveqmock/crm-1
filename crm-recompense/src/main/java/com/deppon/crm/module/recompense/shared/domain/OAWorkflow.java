/**   
 * @title Workflow.java
 * @package com.deppon.crm.recompense.domain
 * @description what to do
 * @author 潘光均
 * @update 2012-2-15 上午9:07:49
 * @version V1.0   
 */
package com.deppon.crm.module.recompense.shared.domain;

import java.util.Date;

import com.deppon.crm.module.authorization.shared.domain.User;



public class OAWorkflow {
	private String id;
	//工作流号
	private String workflowNum;
	//工作流类型
	private int workflowType;
	//工作流状态
	private int workflowStatus;
	//提交人
	private User commiter;
	//提交时间
	private Date commitDate;
	//审核人
	private User auditPeople;
	//审核时间
	private Date auditDate;
	//审核意见
	private String auditopinion;
	//关联理赔单
	private String recompenseId;
	//关联付款单信息
	private PaymentBill paymentBill;
	//新工作流号ICRM+年月日+6位数字
	private String workflowNo;
	//加密后的新工作流号
	private String workflowNumEnc;

	public OAWorkflow() {
	}

	/**
	 * constructer
	 * 
	 * @param workflowNum
	 * @param workflowType
	 * @param workflowStatus
	 * @param commiter
	 * @param commitDate
	 * @param auditPeople
	 * @param auditDate
	 * @param auditopinion
	 * @param recompenseId
	 * @param paymentBill
	 */
	public OAWorkflow(String workflowNum, int workflowType, int workflowStatus,
			User commiter, Date commitDate, User auditPeople, Date auditDate,
			String auditopinion, String recompenseId, PaymentBill paymentBill,String workflowNo) {
		super();
		this.workflowNum = workflowNum;
		this.workflowType = workflowType;
		this.workflowStatus = workflowStatus;
		this.commiter = commiter;
		this.commitDate = commitDate;
		this.auditPeople = auditPeople;
		this.auditDate = auditDate;
		this.auditopinion = auditopinion;
		this.recompenseId = recompenseId;
		this.paymentBill = paymentBill;
		this.workflowNo = workflowNo;
	}
	/**
	 * @return id : return the property id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id : set the property id.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return workflowNum : return the property workflowNum.
	 */
	public String getWorkflowNum() {
		return workflowNum;
	}
	/**
	 * @param workflowNum : set the property workflowNum.
	 */
	public void setWorkflowNum(String workflowNum) {
		this.workflowNum = workflowNum;
	}
	/**
	 * @return workflowType : return the property workflowType.
	 */
	public int getWorkflowType() {
		return workflowType;
	}
	/**
	 * @param workflowType : set the property workflowType.
	 */
	public void setWorkflowType(int workflowType) {
		this.workflowType = workflowType;
	}
	/**
	 * @return workflowStatus : return the property workflowStatus.
	 */
	public int getWorkflowStatus() {
		return workflowStatus;
	}
	/**
	 * @param workflowStatus : set the property workflowStatus.
	 */
	public void setWorkflowStatus(int workflowStatus) {
		this.workflowStatus = workflowStatus;
	}

	/**
	 * @return commitDate : return the property commitDate.
	 */
	public Date getCommitDate() {
		return commitDate;
	}
	/**
	 * @param commitDate : set the property commitDate.
	 */
	public void setCommitDate(Date commitDate) {
		this.commitDate = commitDate;
	}
	
	/**
	 * @return auditDate : return the property auditDate.
	 */
	public Date getAuditDate() {
		return auditDate;
	}
	/**
	 * @param auditDate : set the property auditDate.
	 */
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	/**
	 * @return auditopinion : return the property auditopinion.
	 */
	public String getAuditopinion() {
		return auditopinion;
	}
	/**
	 * @param auditopinion : set the property auditopinion.
	 */
	public void setAuditopinion(String auditopinion) {
		this.auditopinion = auditopinion;
	}
	/**
	 * @return paymentBill : return the property paymentBill.
	 */
	public PaymentBill getPaymentBill() {
		return paymentBill;
	}
	/**
	 * @param paymentBill : set the property paymentBill.
	 */
	public void setPaymentBill(PaymentBill paymentBill) {
		this.paymentBill = paymentBill;
	}
	/**
	 * @return commiter : return the property commiter.
	 */
	public User getCommiter() {
		return commiter;
	}
	/**
	 * @param commiter : set the property commiter.
	 */
	public void setCommiter(User commiter) {
		this.commiter = commiter;
	}
	/**
	 * @return auditPeople : return the property auditPeople.
	 */
	public User getAuditPeople() {
		return auditPeople;
	}
	/**
	 * @param auditPeople : set the property auditPeople.
	 */
	public void setAuditPeople(User auditPeople) {
		this.auditPeople = auditPeople;
	}
	/**
	 * @return recompenseId : return the property recompenseId.
	 */
	public String getRecompenseId() {
		return recompenseId;
	}
	/**
	 * @param recompenseId : set the property recompenseId.
	 */
	public void setRecompenseId(String recompenseId) {
		this.recompenseId = recompenseId;
	}

	public String getWorkflowNo() {
		return workflowNo;
	}

	public void setWorkflowNo(String workflowNo) {
		this.workflowNo = workflowNo;
	}

	public String getWorkflowNumEnc() {
		return workflowNumEnc;
	}

	public void setWorkflowNumEnc(String workflowNumEnc) {
		this.workflowNumEnc = workflowNumEnc;
	}

	
	
	
}
