package com.deppon.crm.module.custrepeat.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;
/**
 * 
* @ClassName: RepetitiveCustWorkFlowInfo 
* @Description: 疑似重复客户工作流信息
* @author LiuY
* @date 2014-3-4 上午11:09:15 
*
 */
public class RepetitiveCustWorkFlowInfo extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String repetitveCustWorkFlowId;    	    //工作流编号
	private String customerCode;					//客户编码
	private String customerName;					//客户名称
	private String telphoneNum;						//固定电话号码
	private String deptName;						//所属部门名称
	private String deptId;							//所属部门ID
	private String areaName;						//所属小区名称
	private String regionName;						//所属大区名称
	private String bussDeptName;					//所属事业部名称
	private String bigCust;							//1为大客户
	private String contract;						//1为存在有效合同，0为不存在有效合同
	private String histryContract;					//1为存在历史合同，0为不存在历史合同
	private double threeMonMoney;					//近三个月发货金额
	private String bankCard;						//银行卡号
	private String repetitveCustWorkFlowStatus;		//工作流状态
	private String proposer;						//申请人
	private Date createTime;						//申请时间
	private String customerLevel;					//客户等级
	private String workFlowType;   					//工作流类型
	private String customerGroup;					//客户类别 固定客户、潜客、散客
	private String disposeOpinion;					//处理意见
	private String memberId ; 						//客户ID
	/**
	 * @return the repetitveCustWorkFlowId
	 */
	public String getRepetitveCustWorkFlowId() {
		return repetitveCustWorkFlowId;
	}
	/**
	 * @param repetitveCustWorkFlowId the repetitveCustWorkFlowId to set
	 */
	public void setRepetitveCustWorkFlowId(String repetitveCustWorkFlowId) {
		this.repetitveCustWorkFlowId = repetitveCustWorkFlowId;
	}
	/**
	 * @return the customerCode
	 */
	public String getCustomerCode() {
		return customerCode;
	}
	/**
	 * @param customerCode the customerCode to set
	 */
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return the telphoneNum
	 */
	public String getTelphoneNum() {
		return telphoneNum;
	}
	/**
	 * @param telphoneNum the telphoneNum to set
	 */
	public void setTelphoneNum(String telphoneNum) {
		this.telphoneNum = telphoneNum;
	}
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}
	/**
	 * @param areaName the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	/**
	 * @return the regionName
	 */
	public String getRegionName() {
		return regionName;
	}
	/**
	 * @param regionName the regionName to set
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	/**
	 * @return the bussDeptName
	 */
	public String getBussDeptName() {
		return bussDeptName;
	}
	/**
	 * @param bussDeptName the bussDeptName to set
	 */
	public void setBussDeptName(String bussDeptName) {
		this.bussDeptName = bussDeptName;
	}
	/**
	 * @return the bigCust
	 */
	public String getBigCust() {
		return bigCust;
	}
	/**
	 * @param bigCust the bigCust to set
	 */
	public void setBigCust(String bigCust) {
		this.bigCust = bigCust;
	}
	/**
	 * @return the contract
	 */
	public String getContract() {
		return contract;
	}
	/**
	 * @param contract the contract to set
	 */
	public void setContract(String contract) {
		this.contract = contract;
	}
	/**
	 * @return the histryContract
	 */
	public String getHistryContract() {
		return histryContract;
	}
	/**
	 * @param histryContract the histryContract to set
	 */
	public void setHistryContract(String histryContract) {
		this.histryContract = histryContract;
	}
	
	/**
	 * @return the bankCard
	 */
	public String getBankCard() {
		return bankCard;
	}
	/**
	 * @param bankCard the bankCard to set
	 */
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	/**
	 * @return the repetitveCustWorkFlowStatus
	 */
	public String getRepetitveCustWorkFlowStatus() {
		return repetitveCustWorkFlowStatus;
	}
	/**
	 * @param repetitveCustWorkFlowStatus the repetitveCustWorkFlowStatus to set
	 */
	public void setRepetitveCustWorkFlowStatus(String repetitveCustWorkFlowStatus) {
		this.repetitveCustWorkFlowStatus = repetitveCustWorkFlowStatus;
	}
	/**
	 * @return the proposer
	 */
	public String getProposer() {
		return proposer;
	}
	/**
	 * @param proposer the proposer to set
	 */
	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the customerLevel
	 */
	public String getCustomerLevel() {
		return customerLevel;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	/**
	 * @param customerLevel the customerLevel to set
	 */
	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}
	/**
	 * @return the workFlowType
	 */
	public String getWorkFlowType() {
		return workFlowType;
	}
	/**
	 * @param workFlowType the workFlowType to set
	 */
	public void setWorkFlowType(String workFlowType) {
		this.workFlowType = workFlowType;
	}
	/**
	 * @return the deptId
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * @return the threeMonMoney
	 */
	public double getThreeMonMoney() {
		return threeMonMoney;
	}
	/**
	 * @param threeMonMoney the threeMonMoney to set
	 */
	public void setThreeMonMoney(double threeMonMoney) {
		this.threeMonMoney = threeMonMoney;
	}
	/**
	 * @return the custGroup
	 */
	public String getCustomerGroup() {
		return customerGroup;
	}
	/**
	 * @param customerGroup the customerGroup to set
	 */
	public void setCustomerGroup(String customerGroup) {
		this.customerGroup = customerGroup;
	}
	/**
	 * @return the disposeOpinion
	 */
	public String getDisposeOpinion() {
		return disposeOpinion;
	}
	/**
	 * @param disposeOpinion the disposeOpinion to set
	 */
	public void setDisposeOpinion(String disposeOpinion) {
		this.disposeOpinion = disposeOpinion;
	}
}
