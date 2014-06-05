package com.deppon.crm.module.servicerecovery.shared.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Description:运单实体 Waybill.java Create on 2012-10-29 下午3:30:05
 * 
 * @author 华龙
 * @version 1.0
 */
public class Waybill {
	// 运单号
	private String waybillNumber;
	// 运输类型
	private String tranType;
	// 总费用(公布价运费 + 增值服务费用 - 优惠总费用=到付+预付—代收)
	private BigDecimal totalCharge;
	// 开单金额
	private BigDecimal waybillAmount;
	// 劳务费
	private BigDecimal laborRebate;
	// 公布价运费（重量/体积 * 费率 * 折扣）
	private BigDecimal publishCharge;
	// 是否签收
	private Boolean isSigned;
	//是否正常签收
	private Boolean isNormalSigned;
	// 签收时间
	private Date signedDate;
	// 总件数
	private Integer totalPackage;
	// 付款方式
	private String paymentType;
	// 发货部门Id
	private String senderDeptId;
	// 收货部门Id
	private String consigneeDeptId;
	// 出发部门标杆编码
	private String senderDeptStandardCode;
	// 到达部门标杆编码
	private String consigneeDeptStandardCode;
	// 发货人名称
	private String senderCustomerName;
	// 发货客户编码
	private String senderCustomerNum;
	// 发货客户级别
	private String senderCustomerLevel;
	// 发货客户联系人
	private String senderContactName;
	// 收货人名称
	private String consigneeCustomerName;
	// 收货客户编码
	private String consigneeCustomerNum;
	// 收货客户级别
	private String consigneeCustomerLevel;
	// 收货客户联系人
	private String consigneeContactName;
	// 客户编号
	private String customerNum;
	// 客户姓名
	private String customerName;
	// 客户等级
	private String customerLevel;
	// 联系人姓名
	private String contactName;
	/* 配载部门 */
	private String stowageDept;
	// 配载部门 标杆标杆编码
	private String stowageDeptStandardCode;
	// 配载部门名
	private String stowageDeptName;
	// 所属子公司
	private String subsidiary;
	//SHIPPER:出发方 CONSIGNEE:到达方
	private String party;

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getWaybillNumber() {
		return waybillNumber;
	}

	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public BigDecimal getTotalCharge() {
		return totalCharge;
	}

	public void setTotalCharge(BigDecimal totalCharge) {
		this.totalCharge = totalCharge;
	}

	public BigDecimal getWaybillAmount() {
		return waybillAmount;
	}

	public void setWaybillAmount(BigDecimal waybillAmount) {
		this.waybillAmount = waybillAmount;
	}

	public BigDecimal getLaborRebate() {
		return laborRebate;
	}

	public void setLaborRebate(BigDecimal laborRebate) {
		this.laborRebate = laborRebate;
	}

	public BigDecimal getPublishCharge() {
		return publishCharge;
	}

	public void setPublishCharge(BigDecimal publishCharge) {
		this.publishCharge = publishCharge;
	}

	public Boolean getIsSigned() {
		return isSigned;
	}

	public void setIsSigned(Boolean isSigned) {
		this.isSigned = isSigned;
	}

	public Integer getTotalPackage() {
		return totalPackage;
	}

	public void setTotalPackage(Integer totalPackage) {
		this.totalPackage = totalPackage;
	}

	public Date getSignedDate() {
		return signedDate;
	}

	public void setSignedDate(Date signedDate) {
		this.signedDate = signedDate;
	}

	public String getSenderDeptId() {
		return senderDeptId;
	}

	public void setSenderDeptId(String senderDeptId) {
		this.senderDeptId = senderDeptId;
	}

	public String getConsigneeDeptId() {
		return consigneeDeptId;
	}

	public void setConsigneeDeptId(String consigneeDeptId) {
		this.consigneeDeptId = consigneeDeptId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getStowageDept() {
		return stowageDept;
	}

	public void setStowageDept(String stowageDept) {
		this.stowageDept = stowageDept;
	}

	public String getStowageDeptStandardCode() {
		return stowageDeptStandardCode;
	}

	public void setStowageDeptStandardCode(String stowageDeptStandardCode) {
		this.stowageDeptStandardCode = stowageDeptStandardCode;
	}

	public String getStowageDeptName() {
		return stowageDeptName;
	}

	public void setStowageDeptName(String stowageDeptName) {
		this.stowageDeptName = stowageDeptName;
	}

	public String getSenderDeptStandardCode() {
		return senderDeptStandardCode;
	}

	public void setSenderDeptStandardCode(String senderDeptStandardCode) {
		this.senderDeptStandardCode = senderDeptStandardCode;
	}

	public String getConsigneeDeptStandardCode() {
		return consigneeDeptStandardCode;
	}

	public void setConsigneeDeptStandardCode(String consigneeDeptStandardCode) {
		this.consigneeDeptStandardCode = consigneeDeptStandardCode;
	}

	public String getSenderCustomerName() {
		return senderCustomerName;
	}

	public void setSenderCustomerName(String senderCustomerName) {
		this.senderCustomerName = senderCustomerName;
	}

	public String getSenderCustomerNum() {
		return senderCustomerNum;
	}

	public void setSenderCustomerNum(String senderCustomerNum) {
		this.senderCustomerNum = senderCustomerNum;
	}

	public String getSenderCustomerLevel() {
		return senderCustomerLevel;
	}

	public void setSenderCustomerLevel(String senderCustomerLevel) {
		this.senderCustomerLevel = senderCustomerLevel;
	}

	public String getSenderContactName() {
		return senderContactName;
	}

	public void setSenderContactName(String senderContactName) {
		this.senderContactName = senderContactName;
	}

	public String getConsigneeCustomerName() {
		return consigneeCustomerName;
	}

	public void setConsigneeCustomerName(String consigneeCustomerName) {
		this.consigneeCustomerName = consigneeCustomerName;
	}

	public String getConsigneeCustomerNum() {
		return consigneeCustomerNum;
	}

	public void setConsigneeCustomerNum(String consigneeCustomerNum) {
		this.consigneeCustomerNum = consigneeCustomerNum;
	}

	public String getConsigneeCustomerLevel() {
		return consigneeCustomerLevel;
	}

	public void setConsigneeCustomerLevel(String consigneeCustomerLevel) {
		this.consigneeCustomerLevel = consigneeCustomerLevel;
	}

	public String getConsigneeContactName() {
		return consigneeContactName;
	}

	public void setConsigneeContactName(String consigneeContactName) {
		this.consigneeContactName = consigneeContactName;
	}

	public String getSubsidiary() {
		return subsidiary;
	}

	public void setSubsidiary(String subsidiary) {
		this.subsidiary = subsidiary;
	}

	public Boolean getIsNormalSigned() {
		return isNormalSigned;
	}

	public void setIsNormalSigned(Boolean isNormalSigned) {
		this.isNormalSigned = isNormalSigned;
	}

}
