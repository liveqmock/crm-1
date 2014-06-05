package com.deppon.crm.module.complaint.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

public class DutyBusinessAspect extends BaseEntity{
	//上报类型
	private String reportType;
	//业务环节名称
	private String busAspName;
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getBusAspName() {
		return busAspName;
	}
	public void setBusAspName(String busAspName) {
		this.busAspName = busAspName;
	}
	
}
