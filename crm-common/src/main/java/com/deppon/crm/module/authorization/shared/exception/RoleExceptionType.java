package com.deppon.crm.module.authorization.shared.exception;             

public enum RoleExceptionType {

	/**
	 * 角色对象不能为空
	 */
	RoleIsNull,

	/**
	 * 角色ID不能为空
	 */
	RoleIdNull,
	
	/**
	 * 角色名不能为空
	 */
	RoleNameIsNull, 
	
	/**
	 * 角色状态不能为空
	 */
	RoleStatusIsNull,
	
	/**
	 * 角色已经分配给用户
	 */
	RoleIsDistributed, 
	
	/**
	 * 有重名
	 */
	RoleHadName, 
	
	/**
	 * 有空格
	 */
	RoleHadBlank, 
	
	/**
	 * 角色名的长度过长
	 */
	RoleNameIsLong, 
	
	/**
	 * 角色描述的长度过长
	 */
	RoleDescIsLong 
}
