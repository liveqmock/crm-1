package com.deppon.crm.module.complaint.shared.domain;

import java.util.List;

import com.deppon.foss.framework.entity.BaseEntity;

public class BasicBusScopeVO extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//业务项Id
	private String busItemId;
	//业务项名称
	private String busItemName;
	//业务范围ID
	private String busScopeId;
	//业务范围名称
	private String busScopeName;
	//上报类型
	private String reportType;
	//业务类型集合
	private List<BasicBusType> busTypes;
	
	public String getBusItemId() {
		return busItemId;
	}
	public void setBusItemId(String busItemId) {
		this.busItemId = busItemId;
	}
	public String getBusItemName() {
		return busItemName;
	}
	public void setBusItemName(String busItemName) {
		this.busItemName = busItemName;
	}
	public String getBusScopeId() {
		return busScopeId;
	}
	public void setBusScopeId(String busScopeId) {
		this.busScopeId = busScopeId;
	}
	public String getBusScopeName() {
		return busScopeName;
	}
	public void setBusScopeName(String busScopeName) {
		this.busScopeName = busScopeName;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public List<BasicBusType> getBusTypes() {
		return busTypes;
	}
	public void setBusTypes(List<BasicBusType> busTypes) {
		this.busTypes = busTypes;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
