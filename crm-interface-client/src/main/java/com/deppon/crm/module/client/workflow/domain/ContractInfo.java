package com.deppon.crm.module.client.workflow.domain;

import java.util.Date;
import java.util.List;

/**
 * 合同审批参数
 * 
 * @author davidcun @2012-3-29
 */
public class ContractInfo {
	// 工作流编号
	private int processInstId;
	// 申请人工号
	private String applyPersonCode;
	// 申请人姓名
	private String applyPersonName;
	// 所属事业部
	private String divisionCode;
	// 所属部门
	private String applyPersonDept;
	// 申请类型
	private String applyType;
	// 超系统额定
	private String overRange;
	// 合同序号
	private String contractNumber;
	// 所属子公司
	private String subsidiary;
	// 合同起始日期
	private Date contractStartDate;
	// 合同到期日期
	private Date contractEndDate;
	// 客户编码
	private String customerCode;
	// 客户名称
	private String customerName;
	// 近三月发货金额
	private String amountOfConsignment;
	// 客户全称
	private String customerAllName;
	// 结款方式
	private String nodeSectionType;
	// 结算限额
	private String balanceAccount;
	// 结款日期
	private String settleAccountsDate;
	// 运费折扣
	private String freightDiscount;
	// 优惠类型
	private String preferentialType;
	// 代收费率折扣
	private String generationRateDiscount;
	// 保价费率折扣
	private String chargeRateDiscount;
	// 接货费折扣
	private String cargoFeeDiscount;
	// 送货费折扣
	private String deliveryFeeDiscount;
	// 协议联系人
	private String protocolContactName;
	// 联系人手机
	private String contactPhone;
	// 联系人电话
	private String contactTel;
	// 折扣
	private String discount;
	// 合同修改类型
	private String modifyContractType;
	// 原合同序号
	private String oldContractNumber;
	// 新结算限额
	private String newBalanceAccount;
	// 新运费折扣
	private String newFreightDiscount;
	// 新优惠类型
	private String newPreferentialType;
	// 新代收费率折扣
	private String newGenerationRateDiscount;
	// 新保价费率折扣
	private String newChargeRateDiscount;
	// 新接货费折扣
	private String newCargoFeeDiscount;
	// 新送货费折扣
	private String newDeliveryFeeDiscount;
	// 合同归属部门
	private String contrctAscriptionDept;
	// 申请绑定部门
	private String applyBondingDept;
	// 现有归属部门
	private String ascriptionDept;
	// 申请变更部门
	private String applyRenewalDept;
	// 申请事由
	private String applyReason;
	// 合同附件
	private List<ContractNounInfo> file;
	//价格版本
	private Date priceVersionDate;
	//快递价格版本
	private Date expPriceVersiondate;
	//修改优惠折扣类型
	private String  modifyPreferentialType;
	//快递点部（标杆编码）
	private String expressPointDeptCode;
	//快递优惠类型
	private String expressPreferentialType;
	//修改后快递优惠类型
	private String expNewPreferentialType;
	//快递运费折扣
	private String expFreightDiscount;
	//修改后快递运费折扣
	private String expNewFreightDiscount;
	//快递代收费率折扣
	private String expWxpgenerationRateDiscount;
	//修改后快递代收费率折扣
	private String expNewDiscount;
	//快递保价费率折扣
	private String expChargeRateDiscount;
	//修改后快递报价费率折扣
	private String expNewChargeRateDiscount;
	//快递接货费率折扣
	private String expCargoFeeDiscount;
	//修改后快递接货费率折扣
	private String expNewCargoFeeDiscount;
	//快递送货费率折扣
	private String expDeliVeryFeeDiscount;
	//修改后快递送货汇率 
	private String expNewDeliveryFeeDiscount;
	//快递结款方式
	private String expNodesectionType;
	//快递近三月发货金额
	private String expAmountOfConsignMent;
	//起草人是否是点部经理
	private String fisPointManager;
	
	
	public int getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(int processInstId) {
		this.processInstId = processInstId;
	}

	public String getApplyPersonCode() {
		return applyPersonCode;
	}

	public void setApplyPersonCode(String applyPersonCode) {
		this.applyPersonCode = applyPersonCode;
	}

	public String getApplyPersonName() {
		return applyPersonName;
	}

	public void setApplyPersonName(String applyPersonName) {
		this.applyPersonName = applyPersonName;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	public String getApplyPersonDept() {
		return applyPersonDept;
	}

	public void setApplyPersonDept(String applyPersonDept) {
		this.applyPersonDept = applyPersonDept;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getOverRange() {
		return overRange;
	}

	public void setOverRange(String overRange) {
		this.overRange = overRange;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getSubsidiary() {
		return subsidiary;
	}

	public void setSubsidiary(String subsidiary) {
		this.subsidiary = subsidiary;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAmountOfConsignment() {
		return amountOfConsignment;
	}

	public void setAmountOfConsignment(String amountOfConsignment) {
		this.amountOfConsignment = amountOfConsignment;
	}

	public String getCustomerAllName() {
		return customerAllName;
	}

	public void setCustomerAllName(String customerAllName) {
		this.customerAllName = customerAllName;
	}

	public String getNodeSectionType() {
		return nodeSectionType;
	}

	public void setNodeSectionType(String nodeSectionType) {
		this.nodeSectionType = nodeSectionType;
	}

	public String getBalanceAccount() {
		return balanceAccount;
	}

	public void setBalanceAccount(String balanceAccount) {
		this.balanceAccount = balanceAccount;
	}

	public String getSettleAccountsDate() {
		return settleAccountsDate;
	}

	public void setSettleAccountsDate(String settleAccountsDate) {
		this.settleAccountsDate = settleAccountsDate;
	}

	public String getFreightDiscount() {
		return freightDiscount;
	}

	public void setFreightDiscount(String freightDiscount) {
		this.freightDiscount = freightDiscount;
	}

	public String getPreferentialType() {
		return preferentialType;
	}

	public void setPreferentialType(String preferentialType) {
		this.preferentialType = preferentialType;
	}

	public String getGenerationRateDiscount() {
		return generationRateDiscount;
	}

	public void setGenerationRateDiscount(String generationRateDiscount) {
		this.generationRateDiscount = generationRateDiscount;
	}

	public String getChargeRateDiscount() {
		return chargeRateDiscount;
	}

	public void setChargeRateDiscount(String chargeRateDiscount) {
		this.chargeRateDiscount = chargeRateDiscount;
	}

	public String getCargoFeeDiscount() {
		return cargoFeeDiscount;
	}

	public void setCargoFeeDiscount(String cargoFeeDiscount) {
		this.cargoFeeDiscount = cargoFeeDiscount;
	}

	public String getDeliveryFeeDiscount() {
		return deliveryFeeDiscount;
	}

	public void setDeliveryFeeDiscount(String deliveryFeeDiscount) {
		this.deliveryFeeDiscount = deliveryFeeDiscount;
	}

	public String getProtocolContactName() {
		return protocolContactName;
	}

	public void setProtocolContactName(String protocolContactName) {
		this.protocolContactName = protocolContactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getModifyContractType() {
		return modifyContractType;
	}

	public void setModifyContractType(String modifyContractType) {
		this.modifyContractType = modifyContractType;
	}

	public String getOldContractNumber() {
		return oldContractNumber;
	}

	public void setOldContractNumber(String oldContractNumber) {
		this.oldContractNumber = oldContractNumber;
	}

	public String getNewBalanceAccount() {
		return newBalanceAccount;
	}

	public void setNewBalanceAccount(String newBalanceAccount) {
		this.newBalanceAccount = newBalanceAccount;
	}

	public String getNewFreightDiscount() {
		return newFreightDiscount;
	}

	public void setNewFreightDiscount(String newFreightDiscount) {
		this.newFreightDiscount = newFreightDiscount;
	}

	public String getNewPreferentialType() {
		return newPreferentialType;
	}

	public void setNewPreferentialType(String newPreferentialType) {
		this.newPreferentialType = newPreferentialType;
	}

	public String getNewGenerationRateDiscount() {
		return newGenerationRateDiscount;
	}

	public void setNewGenerationRateDiscount(String newGenerationRateDiscount) {
		this.newGenerationRateDiscount = newGenerationRateDiscount;
	}

	public String getNewChargeRateDiscount() {
		return newChargeRateDiscount;
	}

	public void setNewChargeRateDiscount(String newChargeRateDiscount) {
		this.newChargeRateDiscount = newChargeRateDiscount;
	}

	public String getNewCargoFeeDiscount() {
		return newCargoFeeDiscount;
	}

	public void setNewCargoFeeDiscount(String newCargoFeeDiscount) {
		this.newCargoFeeDiscount = newCargoFeeDiscount;
	}

	public String getNewDeliveryFeeDiscount() {
		return newDeliveryFeeDiscount;
	}

	public void setNewDeliveryFeeDiscount(String newDeliveryFeeDiscount) {
		this.newDeliveryFeeDiscount = newDeliveryFeeDiscount;
	}

	public String getContrctAscriptionDept() {
		return contrctAscriptionDept;
	}

	public void setContrctAscriptionDept(String contrctAscriptionDept) {
		this.contrctAscriptionDept = contrctAscriptionDept;
	}

	public String getApplyBondingDept() {
		return applyBondingDept;
	}

	public void setApplyBondingDept(String applyBondingDept) {
		this.applyBondingDept = applyBondingDept;
	}

	public String getAscriptionDept() {
		return ascriptionDept;
	}

	public void setAscriptionDept(String ascriptionDept) {
		this.ascriptionDept = ascriptionDept;
	}

	public String getApplyRenewalDept() {
		return applyRenewalDept;
	}

	public void setApplyRenewalDept(String applyRenewalDept) {
		this.applyRenewalDept = applyRenewalDept;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public List<ContractNounInfo> getFile() {
		return file;
	}

	public void setFile(List<ContractNounInfo> file) {
		this.file = file;
	}

	/**
	 *@user pgj
	 *2013-8-7下午1:50:50
	 * @return priceVersionDate : return the property priceVersionDate.
	 */
	public Date getPriceVersionDate() {
		return priceVersionDate;
	}

	/**
	 * @param priceVersionDate : set the property priceVersionDate.
	 */
	public void setPriceVersionDate(Date priceVersionDate) {
		this.priceVersionDate = priceVersionDate;
	}

	public String getModifyPreferentialType() {
		return modifyPreferentialType;
	}

	public void setModifyPreferentialType(String modifyPreferentialType) {
		this.modifyPreferentialType = modifyPreferentialType;
	}

	public Date getExpPriceVersiondate() {
		return expPriceVersiondate;
	}

	public void setExpPriceVersiondate(Date expPriceVersiondate) {
		this.expPriceVersiondate = expPriceVersiondate;
	}

	public String getExpressPointDeptCode() {
		return expressPointDeptCode;
	}

	public void setExpressPointDeptCode(String expressPointDeptCode) {
		this.expressPointDeptCode = expressPointDeptCode;
	}

	public String getExpressPreferentialType() {
		return expressPreferentialType;
	}

	public void setExpressPreferentialType(String expressPreferentialType) {
		this.expressPreferentialType = expressPreferentialType;
	}

	public String getExpNewPreferentialType() {
		return expNewPreferentialType;
	}

	public void setExpNewPreferentialType(String expNewPreferentialType) {
		this.expNewPreferentialType = expNewPreferentialType;
	}

	public String getExpFreightDiscount() {
		return expFreightDiscount;
	}

	public void setExpFreightDiscount(String expFreightDiscount) {
		this.expFreightDiscount = expFreightDiscount;
	}

	public String getExpNewFreightDiscount() {
		return expNewFreightDiscount;
	}

	public void setExpNewFreightDiscount(String expNewFreightDiscount) {
		this.expNewFreightDiscount = expNewFreightDiscount;
	}

	public String getExpWxpgenerationRateDiscount() {
		return expWxpgenerationRateDiscount;
	}

	public void setExpWxpgenerationRateDiscount(String expWxpgenerationRateDiscount) {
		this.expWxpgenerationRateDiscount = expWxpgenerationRateDiscount;
	}

	public String getExpNewDiscount() {
		return expNewDiscount;
	}

	public void setExpNewDiscount(String expNewDiscount) {
		this.expNewDiscount = expNewDiscount;
	}

	public String getExpChargeRateDiscount() {
		return expChargeRateDiscount;
	}

	public void setExpChargeRateDiscount(String expChargeRateDiscount) {
		this.expChargeRateDiscount = expChargeRateDiscount;
	}

	public String getExpNewChargeRateDiscount() {
		return expNewChargeRateDiscount;
	}

	public void setExpNewChargeRateDiscount(String expNewChargeRateDiscount) {
		this.expNewChargeRateDiscount = expNewChargeRateDiscount;
	}

	public String getExpCargoFeeDiscount() {
		return expCargoFeeDiscount;
	}

	public void setExpCargoFeeDiscount(String expCargoFeeDiscount) {
		this.expCargoFeeDiscount = expCargoFeeDiscount;
	}

	public String getExpNewCargoFeeDiscount() {
		return expNewCargoFeeDiscount;
	}

	public void setExpNewCargoFeeDiscount(String expNewCargoFeeDiscount) {
		this.expNewCargoFeeDiscount = expNewCargoFeeDiscount;
	}

	public String getExpDeliVeryFeeDiscount() {
		return expDeliVeryFeeDiscount;
	}

	public void setExpDeliVeryFeeDiscount(String expDeliVeryFeeDiscount) {
		this.expDeliVeryFeeDiscount = expDeliVeryFeeDiscount;
	}

	public String getExpNewDeliveryFeeDiscount() {
		return expNewDeliveryFeeDiscount;
	}

	public void setExpNewDeliveryFeeDiscount(String expNewDeliveryFeeDiscount) {
		this.expNewDeliveryFeeDiscount = expNewDeliveryFeeDiscount;
	}

	public String getExpNodesectionType() {
		return expNodesectionType;
	}

	public void setExpNodesectionType(String expNodesectionType) {
		this.expNodesectionType = expNodesectionType;
	}

	public String getExpAmountOfConsignMent() {
		return expAmountOfConsignMent;
	}

	public void setExpAmountOfConsignMent(String expAmountOfConsignMent) {
		this.expAmountOfConsignMent = expAmountOfConsignMent;
	}

	public String getFisPointManager() {
		return fisPointManager;
	}

	public void setFisPointManager(String fisPointManager) {
		this.fisPointManager = fisPointManager;
	}

}
