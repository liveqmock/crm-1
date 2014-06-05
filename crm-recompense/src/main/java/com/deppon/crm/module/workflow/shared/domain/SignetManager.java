package com.deppon.crm.module.workflow.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * Description:印章管理员<br />
 * </p>
 * @title SignetManager.java
 * @package com.deppon.crm.module.workflow.shared.domain 
 * @author liuHuan
 * @version 0.1 2013-7-31
 */
public class SignetManager extends BaseEntity{

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	//员工id
	private Integer empId;
	
	//部门id
	private Integer deptId;
	
	//员工工号
	private String empCode;
	
	//员工姓名
	private String empName;
	
	//部门名称 
	private String deptName;

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}



	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	
	
	

}
