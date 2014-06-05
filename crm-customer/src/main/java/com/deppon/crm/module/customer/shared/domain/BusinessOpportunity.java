package com.deppon.crm.module.customer.shared.domain;

import java.util.Date;

import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.entity.BaseEntity;

public class BusinessOpportunity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6356770583459533770L;

	// 招投标项目 Char(1)
	private String isBidProject;
	// 预计发货金额 Number
	private int expectDeliveryAmount;
	// 预计成功时间 TIMESTAMP
	private Date expectSuccessDate;
	// 预计成功几率 Number
	private int expectSuccessOdds;
	// 商机状态 Varchar2(20)
	private String boStatus;
	// 商机执行人 Number
	private Employee operator;
	// 商机确认 Char(1)
	private String isBOConfirm;
	// 商机名称 Varchar2(150)
	private String boName;
	// 商机描述 Varchar2(600)
	private String boDesc;
	// 商机阶段 Varchar2(20)
	private String boStep;
	// 商机创建时间 TIMESTAMP
	private Date createTime;
	// 商机创建人 Number
	private Employee creator;
	// 商机编码 Varchar2(20)
	private String boNumber;
	// 客户需求简介 Varchar2(600)
	private String customerDemandDesc;
	// 客户ID Number
	private BusinessOpportunityCustomer customer;
	// 竞争情况信息 Varchar2(600)
	private String competitiveInfo;
	// 解决方案简述 Varchar2(600)
	private String solutionDesc;
	// 关闭原因描述 Varchar2(300)
	private String closeReasonDesc;
	// 关闭原因键值 Varchar2(300)
	private String closeReasonCode;
	// 最后修改时间 TIMESTAMP
	private Date modifyTime;
	// 最后修改人 Number
	private Employee modifier;
	// 所属部门ID Number
	private String deptId;
	// 所属部门
	private String deptName;
	// 所属事业部ID Number
	private String bizId;

	public String getIsBidProject() {
		return isBidProject;
	}

	public void setIsBidProject(String isBidProject) {
		this.isBidProject = isBidProject;
	}

	public int getExpectDeliveryAmount() {
		return expectDeliveryAmount;
	}

	public void setExpectDeliveryAmount(int expectDeliveryAmount) {
		this.expectDeliveryAmount = expectDeliveryAmount;
	}

	public Date getExpectSuccessDate() {
		return expectSuccessDate;
	}

	public void setExpectSuccessDate(Date expectSuccessDate) {
		this.expectSuccessDate = expectSuccessDate;
	}

	public int getExpectSuccessOdds() {
		return expectSuccessOdds;
	}

	public void setExpectSuccessOdds(int expectSuccessOdds) {
		this.expectSuccessOdds = expectSuccessOdds;
	}

	public String getBoStatus() {
		return boStatus;
	}

	public void setBoStatus(String boStatus) {
		this.boStatus = boStatus;
	}

	public Employee getOperator() {
		return operator;
	}

	public void setOperator(Employee operator) {
		this.operator = operator;
	}

	public String getIsBOConfirm() {
		return isBOConfirm;
	}

	public void setIsBOConfirm(String isBOConfirm) {
		this.isBOConfirm = isBOConfirm;
	}

	public String getBoName() {
		return boName;
	}

	public void setBoName(String boName) {
		this.boName = boName;
	}

	public String getBoDesc() {
		return boDesc;
	}

	public void setBoDesc(String boDesc) {
		this.boDesc = boDesc;
	}

	public String getBoStep() {
		return boStep;
	}

	public void setBoStep(String boStep) {
		this.boStep = boStep;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Employee getCreator() {
		return creator;
	}

	public void setCreator(Employee creator) {
		this.creator = creator;
	}

	public String getBoNumber() {
		return boNumber;
	}

	public void setBoNumber(String boNumber) {
		this.boNumber = boNumber;
	}

	public String getCustomerDemandDesc() {
		return customerDemandDesc;
	}

	public void setCustomerDemandDesc(String customerDemandDesc) {
		this.customerDemandDesc = customerDemandDesc;
	}

	public BusinessOpportunityCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(BusinessOpportunityCustomer customer) {
		this.customer = customer;
	}

	public String getCompetitiveInfo() {
		return competitiveInfo;
	}

	public void setCompetitiveInfo(String competitiveInfo) {
		this.competitiveInfo = competitiveInfo;
	}

	public String getSolutionDesc() {
		return solutionDesc;
	}

	public void setSolutionDesc(String solutionDesc) {
		this.solutionDesc = solutionDesc;
	}

	public String getCloseReasonDesc() {
		return closeReasonDesc;
	}

	public void setCloseReasonDesc(String closeReasonDesc) {
		this.closeReasonDesc = closeReasonDesc;
	}

	public String getCloseReasonCode() {
		return closeReasonCode;
	}

	public void setCloseReasonCode(String closeReasonCode) {
		this.closeReasonCode = closeReasonCode;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Employee getModifier() {
		return modifier;
	}

	public void setModifier(Employee modifier) {
		this.modifier = modifier;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
