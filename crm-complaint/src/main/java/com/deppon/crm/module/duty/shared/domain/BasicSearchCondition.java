package com.deppon.crm.module.duty.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

public class BasicSearchCondition extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//业务范围Id
	private String busScopeId;
	//基础资料查询类型
	private String basicType;
	//上报类型
	private String reportType;
	//基础资料查询内容
	private String basicContent;
	/**
	 * @return basicType : return the property basicType.
	 */
	public String getBasicType() {
		return basicType;
	}
	/**
	 * @param basicType : set the property basicType.
	 */
	public void setBasicType(String basicType) {
		this.basicType = basicType;
	}
	/**
	 * @return reportType : return the property reportType.
	 */
	public String getReportType() {
		return reportType;
	}
	/**
	 * @param reportType : set the property reportType.
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	/**
	 * @return basicContent : return the property basicContent.
	 */
	public String getBasicContent() {
		return basicContent;
	}
	/**
	 * @param basicContent : set the property basicContent.
	 */
	public void setBasicContent(String basicContent) {
		this.basicContent = basicContent;
	}
	/**
	 * @return busScopeId : return the property busScopeId.
	 */
	public String getBusScopeId() {
		return busScopeId;
	}
	/**
	 * @param busScopeId : set the property busScopeId.
	 */
	public void setBusScopeId(String busScopeId) {
		this.busScopeId = busScopeId;
	}
	
}
