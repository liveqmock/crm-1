package com.deppon.crm.module.authorization.shared.domain;

import java.util.Collection;

import com.deppon.foss.framework.entity.BaseEntity;
import com.deppon.foss.framework.entity.IRole;

/**
 * 角色对象实体
 * @author Administrator
 *
 */
public class Role extends BaseEntity implements IRole{
	
	private static final long serialVersionUID = -3091688011204362825L;

	//角色名称
	private String roleName;

	//角色描述
	private String roleDesc;

//	//角色状态
//	private Byte status;
//
//	//失效时间
//	private Date invalidDate;
//
//	//生效时间
//	private Date validDate;
	
	//功能对象ID
	private Collection<String> functionIds;
	
	public String getRoleName() {
		return this.roleName;
	}
		 	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
		
	public String getRoleDesc() {
		return this.roleDesc;
	}
		 	
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Collection<String> getFunctionIds() {
		return this.functionIds;
	}
	
	public void setFunctionIds(Collection<String> functionIds){
		this.functionIds = functionIds;
	}
		
	
}
