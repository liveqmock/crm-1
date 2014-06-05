package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;

/**
 * 营销效果评估查询条件
 * @author xiaohongye
 *
 */
public class AssessQueryCondition {
//	经营本部ID
	private String managerDeptCode;
//	事业部ID
	private String orgDeptCode;
//	大区ID
	private String bigDeptCode;
//	小区ID
	private String smallDeptCode;
//	营业部ID
	private String salesDeptCode;
//	开始查询时间
	private Date startDate;
//	结束查询时间
	private Date endDate;
//	评估类型
	private String assessType;
	public String getManagerDeptCode() {
		return managerDeptCode;
	}
	public void setManagerDeptCode(String managerDeptCode) {
		this.managerDeptCode = managerDeptCode;
	}
	public String getOrgDeptCode() {
		return orgDeptCode;
	}
	public void setOrgDeptCode(String orgDeptCode) {
		this.orgDeptCode = orgDeptCode;
	}
	public String getBigDeptCode() {
		return bigDeptCode;
	}
	public void setBigDeptCode(String bigDeptCode) {
		this.bigDeptCode = bigDeptCode;
	}
	public String getSmallDeptCode() {
		return smallDeptCode;
	}
	public void setSmallDeptCode(String smallDeptCode) {
		this.smallDeptCode = smallDeptCode;
	}
	public String getSalesDeptCode() {
		return salesDeptCode;
	}
	public void setSalesDeptCode(String salesDeptCode) {
		this.salesDeptCode = salesDeptCode;
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
	public String getAssessType() {
		return assessType;
	}
	public void setAssessType(String assessType) {
		this.assessType = assessType;
	}
	
}
