package com.deppon.crm.module.custrepeat.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 疑似重复客户实体
 * 
 * @pdOid 62354bca-3acc-40bf-8ad7-5ef0e1af1717
 */
public class RepeatedCustomer extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 客户ID
	 * 
	 * @pdOid 56b9a0e8-f6cd-4fdc-8543-d49f2ab98b81
	 */
	private String custId;
	/**
	 * 客户名称
	 * 
	 * @pdOid 65a5635e-8397-4158-b89b-9e9761a6442a
	 */
	private String custName;
	/**
	 * 银行账号
	 * 
	 * @pdOid 0edf451e-efa2-45f8-a92f-28eef1d0313d
	 */
	private String bankAccount;
	/**
	 * 固定电话
	 * 
	 * @pdOid 0c9a7790-a97c-4dfc-a1e2-9e7e1f0d2e53
	 */
	private String telephone;
	/**
	 * 客户编码
	 * 
	 * @pdOid 77651e18-c97f-4594-9615-660604cd17f1
	 */
	private String custCode;

	/**
	 * 推送的主客户ID
	 * 
	 * @pdOid dffd4367-5970-4a7b-8024-69636dedce75
	 */
	private String mainCustId;

	/**
	 * 近三个月的发货总金额
	 * 
	 * @pdOid a3aaf591-50dd-491e-89a2-2c2aa44d6344
	 */
	private double deliveryMoneyTotal;
	/**
	 * 所属部门ID
	 * 
	 * @pdOid f0473a93-09d5-47e2-85ad-93b46babe918
	 */
	private String deptId;
	/**
	 * 所属部门名称
	 * 
	 * @pdOid f829df85-4920-4862-aac4-2d9bf3397a39
	 */
	private String deptName;
	/**
	 * 所属小区ID
	 * 
	 * @pdOid 649adf38-5695-4bd0-8ffc-e5fe29af1f52
	 */
	private String smallRegionDeptId;
	/**
	 * 所属小区名称
	 * 
	 * @pdOid 75a783a5-1565-4458-9985-6df005aa80f4
	 */
	private String smallRegionDeptName;
	/**
	 * 所属大区ID
	 * 
	 * @pdOid 1cd7773f-0205-404d-8db5-155d8f5b6de2
	 */
	private String bigRegionDeptId;
	/**
	 * 所属大区名称
	 * 
	 * @pdOid 6725eec2-c0fc-4698-866f-74f8a569453c
	 */
	private String bigRegionDeptName;
	/**
	 * 所属事业部Id
	 * 
	 * @pdOid e2b6992b-82a1-418f-abcb-ac20f84c8fe0
	 */
	private String bussDeptId;
	/**
	 * 所属事业部名称
	 * 
	 * @pdOid 97f7ac8d-4d6e-42f2-98c0-057b54e3fa2a
	 */
	private String bussDeptName;
	/**
	 * 是否合同客户
	 */
	private Integer isContractCust;
	/**
	 * 是否存在有效的合同信息
	 * 
	 * @pdOid d185cf1c-f899-41d5-8298-fc6b8311ccd2
	 */
	private Integer isExistValidContract;
	/**
	 * 是否存在历史的合同信息
	 * 
	 * @pdOid 10306336-ca5a-47d2-8fc7-c6bcbc38facc
	 */
	private Integer isExistHistoryContract;
	/**
	 * 是否主客户
	 * 
	 * @pdOid 94954a45-047e-401b-b451-045006912794
	 */
	private Integer isMainCust;
	/**
	 * 联系人姓名
	 */
	private String linkMainName;

	/**
	 * 客户类别
	 */
	private String custGroup;

	/**
	 * 客户等级
	 */
	private String custLevel;
	/**
	 * 客户类型
	 */
	private String custType;
	/**
	 * 是否为大客户
	 */
	private Integer isBigcustomer;
	/**
	 * 疑似重复状态
	 */
	private String repeatStatus;
	
	/**
	 * 是否新加的
	 */
	private Integer isAdd;
	/**
	 * 客户创建时间
	 */
	private Date custCreateTime;

	/**
	 * @return the custId
	 */
	public String getCustId() {
		return custId;
	}

	/**
	 * @param custId
	 *            the custId to set
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}

	/**
	 * @return the custName
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * @param custName
	 *            the custName to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * @return the bankAccount
	 */
	public String getBankAccount() {
		return bankAccount;
	}

	/**
	 * @param bankAccount
	 *            the bankAccount to set
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the custCode
	 */
	public String getCustCode() {
		return custCode;
	}

	/**
	 * @param custCode
	 *            the custCode to set
	 */
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	/**
	 * @return the mainCustId
	 */
	public String getMainCustId() {
		return mainCustId;
	}

	/**
	 * @param mainCustId
	 *            the mainCustId to set
	 */
	public void setMainCustId(String mainCustId) {
		this.mainCustId = mainCustId;
	}

	/**
	 * @return the deliveryMoneyTotal
	 */
	public double getDeliveryMoneyTotal() {
		return deliveryMoneyTotal;
	}

	/**
	 * @param deliveryMoneyTotal
	 *            the deliveryMoneyTotal to set
	 */
	public void setDeliveryMoneyTotal(double deliveryMoneyTotal) {
		this.deliveryMoneyTotal = deliveryMoneyTotal;
	}

	/**
	 * @return the deptId
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId
	 *            the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName
	 *            the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @return the smallRegionDeptId
	 */
	public String getSmallRegionDeptId() {
		return smallRegionDeptId;
	}

	/**
	 * @param smallRegionDeptId
	 *            the smallRegionDeptId to set
	 */
	public void setSmallRegionDeptId(String smallRegionDeptId) {
		this.smallRegionDeptId = smallRegionDeptId;
	}

	/**
	 * @return the smallRegionDeptName
	 */
	public String getSmallRegionDeptName() {
		return smallRegionDeptName;
	}

	/**
	 * @param smallRegionDeptName
	 *            the smallRegionDeptName to set
	 */
	public void setSmallRegionDeptName(String smallRegionDeptName) {
		this.smallRegionDeptName = smallRegionDeptName;
	}

	/**
	 * @return the bigRegionDeptId
	 */
	public String getBigRegionDeptId() {
		return bigRegionDeptId;
	}

	/**
	 * @param bigRegionDeptId
	 *            the bigRegionDeptId to set
	 */
	public void setBigRegionDeptId(String bigRegionDeptId) {
		this.bigRegionDeptId = bigRegionDeptId;
	}

	/**
	 * @return the bigRegionDeptName
	 */
	public String getBigRegionDeptName() {
		return bigRegionDeptName;
	}

	/**
	 * @param bigRegionDeptName
	 *            the bigRegionDeptName to set
	 */
	public void setBigRegionDeptName(String bigRegionDeptName) {
		this.bigRegionDeptName = bigRegionDeptName;
	}

	/**
	 * @return the bussDeptId
	 */
	public String getBussDeptId() {
		return bussDeptId;
	}

	/**
	 * @param bussDeptId
	 *            the bussDeptId to set
	 */
	public void setBussDeptId(String bussDeptId) {
		this.bussDeptId = bussDeptId;
	}

	/**
	 * @return the bussDeptName
	 */
	public String getBussDeptName() {
		return bussDeptName;
	}

	/**
	 * @param bussDeptName
	 *            the bussDeptName to set
	 */
	public void setBussDeptName(String bussDeptName) {
		this.bussDeptName = bussDeptName;
	}

	public String getLinkMainName() {
		return linkMainName;
	}

	public void setLinkMainName(String linkMainName) {
		this.linkMainName = linkMainName;
	}

	/**
	 * @return the custLevel
	 */
	public String getCustLevel() {
		return custLevel;
	}

	/**
	 * @param custLevel
	 *            the custLevel to set
	 */
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}


	/**
	 * @return the custType
	 */
	public String getCustType() {
		return custType;
	}

	/**
	 * @param custType
	 *            the custType to set
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}

	/**
	 * @return the repeatStatus
	 */
	public String getRepeatStatus() {
		return repeatStatus;
	}

	/**
	 * @param repeatStatus
	 *            the repeatStatus to set
	 */
	public void setRepeatStatus(String repeatStatus) {
		this.repeatStatus = repeatStatus;
	}

	/**
	 * @return the isExistValidContract
	 */
	public Integer getIsExistValidContract() {
		return isExistValidContract;
	}

	/**
	 * @param isExistValidContract the isExistValidContract to set
	 */
	public void setIsExistValidContract(Integer isExistValidContract) {
		this.isExistValidContract = isExistValidContract;
	}

	/**
	 * @return the isExistHistoryContract
	 */
	public Integer getIsExistHistoryContract() {
		return isExistHistoryContract;
	}

	/**
	 * @param isExistHistoryContract the isExistHistoryContract to set
	 */
	public void setIsExistHistoryContract(Integer isExistHistoryContract) {
		this.isExistHistoryContract = isExistHistoryContract;
	}

	/**
	 * @return the isMainCust
	 */
	public Integer getIsMainCust() {
		return isMainCust;
	}

	/**
	 * @param isMainCust the isMainCust to set
	 */
	public void setIsMainCust(Integer isMainCust) {
		this.isMainCust = isMainCust;
	}

	/**
	 * @return the isBigcustomer
	 */
	public Integer getIsBigcustomer() {
		return isBigcustomer;
	}

	/**
	 * @param isBigcustomer the isBigcustomer to set
	 */
	public void setIsBigcustomer(Integer isBigcustomer) {
		this.isBigcustomer = isBigcustomer;
	}

	/**
	 * @return the isAdd
	 */
	public Integer getIsAdd() {
		return isAdd;
	}

	/**
	 * @param isAdd the isAdd to set
	 */
	public void setIsAdd(Integer isAdd) {
		this.isAdd = isAdd;
	}

	/**
	 * @return the custGroup
	 */
	public String getCustGroup() {
		return custGroup;
	}

	/**
	 * @param custGroup the custGroup to set
	 */
	public void setCustGroup(String custGroup) {
		this.custGroup = custGroup;
	}

	/**
	 * @return the isContractCust
	 */
	public Integer getIsContractCust() {
		return isContractCust;
	}

	/**
	 * @param isContractCust the isContractCust to set
	 */
	public void setIsContractCust(Integer isContractCust) {
		this.isContractCust = isContractCust;
	}
	/**
	 * custCreateTime
	 * @return custCreateTime
	 */
	public Date getCustCreateTime() {
		return custCreateTime;
	}
	/**
	 * custCreateTime
	 * @param custCreateTime
	 */
	public void setCustCreateTime(Date custCreateTime) {
		this.custCreateTime = custCreateTime;
	}
	
}