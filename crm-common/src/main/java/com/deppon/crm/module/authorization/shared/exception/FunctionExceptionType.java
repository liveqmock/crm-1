package com.deppon.crm.module.authorization.shared.exception;                

/**
 * 异常类型枚举
 */
public enum FunctionExceptionType {
	
	/**
	 * 功能对象Id为空
	 */
	FunctionIdNull, 
	
	/**
	 * 功能对象为空
	 */
	FunctionIsNull, 
	
	/**
	 * 功能编码创建失败
	 */
	FunctionCodeCreateError, 
	
	/**
	 * 功能名为空
	 */
	FunctionNameIsNull, 
	
	/**
	 * 功能URI格式不正确
	 */
	FunctionURIIsError, 
	
	/**
	 * 上级功能对象不能为空
	 */
	ParentFunctionNullError, 
	
	/**
	 * 功能是否权限检查选项不能为空
	 */
	FunctionCheckNullError, 
	
	/**
	 * 功能类型选项不能为空
	 */
	FunctionTypeNullError,
	
	/**
	 * 功能编码不能为空
	 */
	FunctionCodeNullError,
	
	/**
	 * 功能是否有效选项不能为空
	 */
	FunctionValidFlagNullError,
	
	/**
	 * 父功能禁用
	 */
	ParentEnd

}
