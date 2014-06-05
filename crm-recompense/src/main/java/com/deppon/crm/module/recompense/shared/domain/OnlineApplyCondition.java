package com.deppon.crm.module.recompense.shared.domain;

import java.util.Date;

/**
 * Description:在线理赔查询条件<br/>
 * @author 杨伟
 */

public class OnlineApplyCondition {
	//运单号
	private String waybillNumber;
	//上报时间
	private Date reportDate;
	//上报时间到
	private Date reportDateTo;
	//状态持续时间(单位:天)
	private Integer acceptTime;
	//状态持续时间到(单位:天)
	private Integer acceptTimeTo;
	//受理状态(在线理赔状态)
	private String onlineApplyStatus;
	//受理部门ID
	private String acceptDept;
	//所属区域ID
	private String belongsArea;
	//索赔金额
	private String claimAmount;
	//索赔金额区间的下限
	private Integer claimAmountStart;
	//索赔金额区间的上限
	private Integer claimAmountEnd;
	//运输类型
	private String transType;
	
	public Integer getClaimAmountStart() {
		return claimAmountStart;
	}
	public void setClaimAmountStart(Integer claimAmountStart) {
		this.claimAmountStart = claimAmountStart;
	}
	public Integer getClaimAmountEnd() {
		return claimAmountEnd;
	}
	public void setClaimAmountEnd(Integer claimAmountEnd) {
		this.claimAmountEnd = claimAmountEnd;
	}
	public String getWaybillNumber() {
		return waybillNumber;
	}
	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public Date getReportDateTo() {
		return reportDateTo;
	}
	public void setReportDateTo(Date reportDateTo) {
		this.reportDateTo = reportDateTo;
	}
	public Integer getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(Integer acceptTime) {
		this.acceptTime = acceptTime;
	}
	public Integer getAcceptTimeTo() {
		return acceptTimeTo;
	}
	public void setAcceptTimeTo(Integer acceptTimeTo) {
		this.acceptTimeTo = acceptTimeTo;
	}
	public String getOnlineApplyStatus() {
		return onlineApplyStatus;
	}
	public void setOnlineApplyStatus(String onlineApplyStatus) {
		this.onlineApplyStatus = onlineApplyStatus;
	}
	public String getAcceptDept() {
		return acceptDept;
	}
	public void setAcceptDept(String acceptDept) {
		this.acceptDept = acceptDept;
	}
	public String getBelongsArea() {
		return belongsArea;
	}
	public void setBelongsArea(String belongsArea) {
		this.belongsArea = belongsArea;
	}
	public String getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(String claimAmount) {
		this.claimAmount = claimAmount;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	
}
