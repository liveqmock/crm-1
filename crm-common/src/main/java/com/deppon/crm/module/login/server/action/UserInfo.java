package com.deppon.crm.module.login.server.action;

import java.util.Date;
import java.util.Set;

public class UserInfo {
	// 用户登录名
	private String loginName;
	// 职员编号
	private String empCode;
	// 人员姓名
	private String empName;
	// 部门编号
	private String deptCode;
	//部门标杆编码
	private String standardCode;
	// 部门名称
	private String deptName;
	// 用户Id
	private String userId;
	//职员ID
	private String empId;
	// 部门Id
	private String deptId;
	//手机号
	private String mobilePhone;
	//职员职位
    private String position;
	

	// 用户所拥有的角色信息ID集合
	private Set<String> roleids;
	
	// 用户所拥有的部门信息ID集合
	private Set<String> deptids;
	//用户最后登录时间
	private Date lastLogin;
	
	//部门所在的城市 0表示大陆 1表示香港
	private String deptCityLocation;

	public String getDeptCityLocation() {
		return deptCityLocation;
	}

	public void setDeptCityLocation(String deptCityLocation) {
		this.deptCityLocation = deptCityLocation;
	}


	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Set<String> getDeptids() {
		return deptids;
	}

	public void setDeptids(Set<String> deptids) {
		this.deptids = deptids;
	}

	public Set<String> getRoleids() {
		return roleids;
	}

	public void setRoleids(Set<String> roleids) {
		this.roleids = roleids;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

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

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getStandardCode() {
		return standardCode;
	}

	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
