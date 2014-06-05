package com.deppon.crm.module.servicerecovery.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

public class FinanceDept extends BaseEntity {

	// 财务部门名
	private String deptName;
	// 财务部门编码
	private String standardCode;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getStandardCode() {
		return standardCode;
	}

	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}



}
