package com.deppon.crm.module.complaint.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

public class BasicSearchCondition extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//基础资料查询类型
	private String basicType;
	//基础资料查询内容
	private String basicContent;
	public String getBasicType() {
		return basicType;
	}
	public void setBasicType(String basicType) {
		this.basicType = basicType;
	}
	public String getBasicContent() {
		return basicContent;
	}
	public void setBasicContent(String basicContent) {
		this.basicContent = basicContent;
	}
		
}
