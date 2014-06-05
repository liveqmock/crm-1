package com.deppon.crm.module.frameworkimpl.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;
/**
 * 
 * 作 者：ztjie最后修改
 * 时间：2011年10月24日 
 * 描 述： 与令牌信息有关的异常
 */
public class TokenException extends BusinessException {

	private static final long serialVersionUID = -3422399009620737953L;

	private static final String TOKEN_ID_NULL_ERROR_CODE = "error.module.frameworkImpl.TokenIdNullException";
	
	private static final String TOKEN_OBJECT_NULL_ERROR_CODE="error.module.frameworkImpl.TokenNullException";
	
	private static final String TOKEN_KEY_NO_STRING_TYPE = "error.module.frameworkImpl.TokenKeyNoStringTypeException";
	
	/**
	 *异常的构造方法
	 * @param TokenExceptionType errorType
	 * @since JDK1.6
	 */
	public TokenException(TokenExceptionType errorType){
		super();
		if(errorType.equals(TokenExceptionType.TokenIdNull)){
			this.errCode = TOKEN_ID_NULL_ERROR_CODE;
		}
		if(errorType.equals(TokenExceptionType.TokenIsNull)){
			this.errCode = TOKEN_OBJECT_NULL_ERROR_CODE;
		}
		if(errorType.equals(TokenExceptionType.TokenKeyNoStringType)){
			this.errCode = TOKEN_KEY_NO_STRING_TYPE;
		}
	}
}
