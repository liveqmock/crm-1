package com.deppon.crm.module.backfreight.shared.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:退运费搜索实体 BackFreightSearchCondition.java Create on 2012-10-29
 * 上午10:29:25
 * 
 * @author 华龙
 * @version 1.0
 */
public class BackFreightSearchCondition {

	/** 所属子公司 */
	private String subsidiary;
	/** 运输类型 */
	private String tranType;
	/** 申请状态 */
	private String applyStatus;
	/** 申请部门 */
	private String applyDept;
	/** 开始时间 */
	private Date startDate;
	/** 结束时间 */
	private Date endDate;
	/** 支付类型 */
	private String paymentType;
	private int start;
	private int limit;
	private List<String> waybillNumbers=new ArrayList<String>();

	public String getSubsidiary() {
		return subsidiary;
	}

	public void setSubsidiary(String subsidiary) {
		this.subsidiary = subsidiary;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public String getApplyDept() {
		return applyDept;
	}

	public void setApplyDept(String applyDept) {
		this.applyDept = applyDept;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public List<String> getWaybillNumbers() {
		return waybillNumbers;
	}

	public void setWaybillNumbers(List<String> waybillNumbers) {
		this.waybillNumbers = waybillNumbers;
	}

	

}
