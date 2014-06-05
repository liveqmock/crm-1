package com.deppon.crm.module.authorization.shared.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.entity.BaseEntity;
import com.deppon.foss.framework.entity.IRole;
import com.deppon.foss.framework.entity.IUser;

/**
 * 用户实体对象
 * @author Administrator
 *
 */
public class User extends BaseEntity implements IUser {

	private static final long serialVersionUID = 6334973378782503164L;

	//职员对象
	private Employee empCode;

	//用户登录名
	private String loginName;

	//用户登录密码
	private String password;

	//用户最后登录时间
	private Date lastLogin;

	//用户状态(0:未启用 1：启用)
	private Byte status;

	//用户启用时间
	private Date invalidDate;

	//用户禁用时间
	private Date validDate;

	//用户所分配的角色信息
	@SuppressWarnings("unused")
	private List<IRole> roles;

	//用户所拥有的功能信息ID集合
	//private Set<String> functionCodes;

	//用户所拥有的角色信息ID集合
	private Set<String> roleids;
	
	//用户所拥有的部门信息ID集合
	private Set<String> deptids;
	
	private Set<String> accessUris;

	public Set<String> getAccessUris() {
		return accessUris;
	}

	public void setAccessUris(Set<String> accessUris) {
		this.accessUris = accessUris;
	}

	public Employee getEmpCode() {
		return this.empCode;
	}

	//public Set<String> getFunctionCodes() {
	//	return functionCodes;
	//}


	public Date getLastLogin() {
		return this.lastLogin;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public String getPassword() {
		return this.password;
	}

	public Set<String> getRoleids() {
		return roleids;
	}

	@Deprecated
	public List<IRole> getRoles() {
		return null;
	}

	public Byte getStatus() {
		return this.status;
	}

	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
	}

	//public void setFunctionCodes(Set<String> functionCodes) {
	//	this.functionCodes = functionCodes;
	//}

	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoleids(Set<String> roleids) {
		this.roleids = roleids;
	}

	@Deprecated
	public void setRoles(List<IRole> roles) {
		this.roles = roles;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Set<String> getDeptids() {
		return deptids;
	}

	public void setDeptids(Set<String> deptids) {
		this.deptids = deptids;
	}

	@Override
	public Set<String> queryAccessUris() {
		// TODO Auto-generated method stub
		return accessUris;
	}

	@Override
	public void setUserName(String userName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return loginName;
	}


}
