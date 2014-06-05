package com.deppon.crm.module.frameworkimpl.shared.exception;                

/**
 * 异常类型枚举
 */
public enum TokenExceptionType {
	
	/**
	 * 令牌Id为空
	 */
	TokenIdNull, 
	
	/**
	 * 令牌为空
	 */
	TokenIsNull, 
	
	/**
	 * 令牌的KEY不是字符类型
	 */
	TokenKeyNoStringType, 
	/**
	 * 令牌有效期为空
	 */
	TokenValidTimeNull
}
