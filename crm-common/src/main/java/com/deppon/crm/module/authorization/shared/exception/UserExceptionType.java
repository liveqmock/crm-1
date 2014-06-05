package com.deppon.crm.module.authorization.shared.exception;             

public enum UserExceptionType {
	
	/**
	 * 登录名不能为空
	 */
	LoginNameIsNull, 
	
	/**
	 * 用户ID不能为空
	 */
	UserIdIsNull, 
	
	/**
	 * 用户对象不能为空
	 */
	UserIsNull, 
	
	/**
	 * 用户密码不能为空
	 */
	UserPasswordIsNull,
	
	/**
	 * 用户状态不能为空
	 */
	UserStatusIsNull,
	
	/**
	 * 用户已经存在
	 */
	UserIsExist, 
	
	/**
	 * 部门ID不能为空
	 */
	DeptIdIsNull,
	
	/**
	 * 部门ID集合不能为空
	 */
	DeptIdsIsNull, 

}
