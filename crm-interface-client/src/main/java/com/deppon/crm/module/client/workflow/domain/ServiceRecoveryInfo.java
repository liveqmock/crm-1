package com.deppon.crm.module.client.workflow.domain;

import java.util.List;

/**
 * @作者：罗典
 * @描述：服务补救工作流申请实体
 * @时间：2012-11-19
 * */
public class ServiceRecoveryInfo {
	// 申请人工号
	private String applyPersonCode;
	// 申请人姓名
	private String applyName;
	// 申请人职务
	private String position;
	// 所属子公司
	private String subsidiary;
	// 运单号
	private String waybillNo;
	// 减免金额
	private Double deductibleMoney;
	// 减免类型
	private String deductiblecategory;
	// 当地财务部
	private String localFinical;
	// 经手人
	private String handler;
	// 补救原因
	private String reason;
	// 附件
	private List<ContractNounInfo> file;

	public String getApplyPersonCode() {
		return applyPersonCode;
	}

	public void setApplyPersonCode(String applyPersonCode) {
		this.applyPersonCode = applyPersonCode;
	}

	public List<ContractNounInfo> getFile() {
		return file;
	}

	public void setFile(List<ContractNounInfo> file) {
		this.file = file;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSubsidiary() {
		return subsidiary;
	}

	public void setSubsidiary(String subsidiary) {
		this.subsidiary = subsidiary;
	}

	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	public Double getDeductibleMoney() {
		return deductibleMoney;
	}

	public void setDeductibleMoney(Double deductibleMoney) {
		this.deductibleMoney = deductibleMoney;
	}

	public String getDeductiblecategory() {
		return deductiblecategory;
	}

	public void setDeductiblecategory(String deductiblecategory) {
		this.deductiblecategory = deductiblecategory;
	}

	public String getLocalFinical() {
		return localFinical;
	}

	public void setLocalFinical(String localFinical) {
		this.localFinical = localFinical;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
