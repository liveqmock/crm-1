package com.deppon.crm.module.keycustomer.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;
/**
 * 
 * <p>
 * Description:大客户工作流信息<br />
 * </p>
 * @title KeyCustomerWorkflowInfo.java
 * @package com.deppon.crm.module.keycustomer.shared.domain 
 * @author 106138
 * @version 0.1 2014-3-4
 */ 
public class KeyCustomerWorkflowInfo  extends BaseEntity{

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 2301047912753501738L;
	//客户工作流号	
	private String busino;
	//客户归属部门
	private String deptId;
	//客户归属部门名字
	private String deptName;
	//客户ID
	private String custId;
	//客户近三月发货金额 
	private String amountOfSignMent;
	//次月发货潜力
	private Integer custPotential;
	//意愿程度	
	private String cooperate;
	//准入申请理由
	private String applicationReason;
	//是否特殊大客户
	private Boolean isSpecialKeyCustomer;
	//工作流类型 
	private String workFlowType;
	//工作流状态  1审批中 2同意 3拒绝
	private String status;
	//审批意见
	private String approvalSuggestin;
	//审批时间
	private Date approvalTime;
	//最后审批人姓名
	private String lastApprover; 
	//客户信用预警次数
	private String creditTimes;
	//联系人编码
	private String contactNum;
	//联系人姓名
	private String contactName;
	//客户编码
	private String customerNum;
	//客户名称
	private String customerName;
	//客户等级
	private String custDegree;
	/**
	 * <p>
	 * Description:busino<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getBusino() {
		return busino;
	}
	/**
	 * <p>
	 * Description:busino<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setBusino(String busino) {
		this.busino = busino;
	}
	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * <p>
	 * Description:deptName<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * <p>
	 * Description:deptName<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * <p>
	 * Description:custId<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * <p>
	 * Description:custId<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 * <p>
	 * Description:amountOfSignMent<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getAmountOfSignMent() {
		return amountOfSignMent;
	}
	/**
	 * <p>
	 * Description:amountOfSignMent<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setAmountOfSignMent(String amountOfSignMent) {
		this.amountOfSignMent = amountOfSignMent;
	}
	/**
	 * <p>
	 * Description:custPotential<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public Integer getCustPotential() {
		return custPotential;
	}
	/**
	 * <p>
	 * Description:custPotential<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setCustPotential(Integer custPotential) {
		this.custPotential = custPotential;
	}
	/**
	 * <p>
	 * Description:cooperate<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getCooperate() {
		return cooperate;
	}
	/**
	 * <p>
	 * Description:cooperate<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setCooperate(String cooperate) {
		this.cooperate = cooperate;
	}
	/**
	 * <p>
	 * Description:applicationReason<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getApplicationReason() {
		return applicationReason;
	}
	/**
	 * <p>
	 * Description:applicationReason<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setApplicationReason(String applicationReason) {
		this.applicationReason = applicationReason;
	}
	/**
	 * <p>
	 * Description:isSpecialKeyCustomer<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public Boolean getIsSpecialKeyCustomer() {
		return isSpecialKeyCustomer;
	}
	/**
	 * <p>
	 * Description:isSpecialKeyCustomer<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setIsSpecialKeyCustomer(Boolean isSpecialKeyCustomer) {
		this.isSpecialKeyCustomer = isSpecialKeyCustomer;
	}
	/**
	 * <p>
	 * Description:workFlowType<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getWorkFlowType() {
		return workFlowType;
	}
	/**
	 * <p>
	 * Description:workFlowType<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setWorkFlowType(String workFlowType) {
		this.workFlowType = workFlowType;
	}
	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * <p>
	 * Description:approvalSuggestin<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getApprovalSuggestin() {
		return approvalSuggestin;
	}
	/**
	 * <p>
	 * Description:approvalSuggestin<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setApprovalSuggestin(String approvalSuggestin) {
		this.approvalSuggestin = approvalSuggestin;
	}
	/**
	 * <p>
	 * Description:approvalTime<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public Date getApprovalTime() {
		return approvalTime;
	}
	/**
	 * <p>
	 * Description:approvalTime<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}
	/**
	 * <p>
	 * Description:lastApprover<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getLastApprover() {
		return lastApprover;
	}
	/**
	 * <p>
	 * Description:lastApprover<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setLastApprover(String lastApprover) {
		this.lastApprover = lastApprover;
	}
	/**
	 * <p>
	 * Description:creditTimes<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getCreditTimes() {
		return creditTimes;
	}
	/**
	 * <p>
	 * Description:creditTimes<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setCreditTimes(String creditTimes) {
		this.creditTimes = creditTimes;
	}
	/**
	 * <p>
	 * Description:contactNum<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getContactNum() {
		return contactNum;
	}
	/**
	 * <p>
	 * Description:contactNum<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	/**
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * <p>
	 * Description:customerNum<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getCustomerNum() {
		return customerNum;
	}
	/**
	 * <p>
	 * Description:customerNum<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	/**
	 * <p>
	 * Description:customerName<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * <p>
	 * Description:customerName<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * <p>
	 * Description:custDegree<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getCustDegree() {
		return custDegree;
	}
	/**
	 * <p>
	 * Description:custDegree<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setCustDegree(String custDegree) {
		this.custDegree = custDegree;
	}
	

}
