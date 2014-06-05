package com.deppon.crm.module.authorization.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;
/**
 * 
 * 作 者：张斌 最后修改
 * 时间：2011年10月24日 
 * 描 述： 与功能信息有关的异常
 */
public class FunctionException extends BusinessException {

	private static final long serialVersionUID = -3422399009620737953L;

	private static final String FUNCTION_ID_NULL_ERROR_CODE = "i18n.authorization.FunctionIdNullException";
	
	private static final String FUNCTION_OBJECT_NULL_ERROR_CODE="i18n.authorization.FunctionNotFoundException";
	
	private static final String FUNCTIONCODE_CREATE_ERROR_CODE="i18n.authorization.FunctionCodeCreateErrorException";
	
	private static final String FUNCTIONCODE_NULL_ERROR_CODE="i18n.authorization.FunctionCodeNullErrorException";
	
	private static final String FUNCTION_NAME_IS_NULL_CODE="i18n.authorization.FunctionNameIsNullException";
	
	private static final String FUNCTION_URI_IS_ERROR_CODE="i18n.authorization.FunctionURIIsErrorException";
	
	private static final String PARENT_FUNCTION_NULL_ERROR_CODE="i18n.authorization.ParentFunctionNullErrorException";
	
	private static final String FUNCTION_CHECK_NULL_ERROR_CODE="i18n.authorization.FunctionCheckNullErrorException";
	
	private static final String FUNCTION_TYPE_NULL_ERROR_CODE="i18n.authorization.FunctionTypeNullErrorException";

	private static final String FUNCTION_VALIDFLAG_NULL_ERROR_CODE="i18n.authorization.FunctionValidFlagNullErrorException";
	
	private static final String FUNCTION_PARENT_END = "i18n.authorization.ParentEndException";
	/**
	 *异常的构造方法
	 * @param FunctionExceptionType errorType
	 * @since JDK1.6
	 */
	public FunctionException(FunctionExceptionType errorType){
		super();
		if(errorType.equals(FunctionExceptionType.FunctionIdNull)){
			this.errCode = FUNCTION_ID_NULL_ERROR_CODE;
		}
		if(errorType.equals(FunctionExceptionType.ParentEnd)){
			this.errCode = FUNCTION_PARENT_END;
		}
		if(errorType.equals(FunctionExceptionType.FunctionIsNull)){
			this.errCode = FUNCTION_OBJECT_NULL_ERROR_CODE;
		}
		if(errorType.equals(FunctionExceptionType.FunctionCodeCreateError)){
			this.errCode = FUNCTIONCODE_CREATE_ERROR_CODE;
		}
		if(errorType.equals(FunctionExceptionType.FunctionCodeNullError)){
			this.errCode = FUNCTIONCODE_NULL_ERROR_CODE;
		}
		if(errorType.equals(FunctionExceptionType.FunctionNameIsNull)){
			this.errCode = FUNCTION_NAME_IS_NULL_CODE;
		}
		if(errorType.equals(FunctionExceptionType.FunctionURIIsError)){
			this.errCode = FUNCTION_URI_IS_ERROR_CODE;
		}
		if(errorType.equals(FunctionExceptionType.ParentFunctionNullError)){
			this.errCode = PARENT_FUNCTION_NULL_ERROR_CODE;
		}
		if(errorType.equals(FunctionExceptionType.FunctionCheckNullError)){
			this.errCode = FUNCTION_CHECK_NULL_ERROR_CODE;
		}
		if(errorType.equals(FunctionExceptionType.FunctionTypeNullError)){
			this.errCode = FUNCTION_TYPE_NULL_ERROR_CODE;
		}
		if(errorType.equals(FunctionExceptionType.FunctionValidFlagNullError)){
			this.errCode = FUNCTION_VALIDFLAG_NULL_ERROR_CODE;
		}
	}
}
