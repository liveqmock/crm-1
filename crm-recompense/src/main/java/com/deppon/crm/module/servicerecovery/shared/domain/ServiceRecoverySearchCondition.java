package com.deppon.crm.module.servicerecovery.shared.domain;

import java.util.Date;
import java.util.List;

/**
 * Description:服务补救申请查询条件实体 ServiceRecoverySearchCondition.java Create on
 * 2012-10-29 上午10:15:50
 * 
 * @author 华龙
 * @version 1.0
 */
public class ServiceRecoverySearchCondition {
	/** 运单号 */
	private List<String> waybillNumbers;
	/** 客户类型 */
	private String customerType;
	/** 减免类型 */
	private String reductionType;
	/** 申请状态 */
	private String applyStatus;
	/** 申请部门 */
	private String applyDept;
	/** 开始时间 */
	private Date startDate;
	/** 结束时间 */
	private Date endDate;
	private String tranType;
	private int start;
	private int limit;
	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getReductionType() {
		return reductionType;
	}

	public void setReductionType(String reductionType) {
		this.reductionType = reductionType;
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
