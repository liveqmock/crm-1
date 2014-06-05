package com.deppon.crm.module.authorization.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;
/**
 * 
 * 作 者：张斌 最后修改
 * 时间：2011年10月24日 
 * 描 述： 与角色信息有关的异常
 */
public class RoleException extends BusinessException {

	private static final long serialVersionUID = 590525254182760551L;
	
	private static final String ROLE_IS_NULL_ERROR = "i18n.authorization.RoleIsNullException";
	
	private static final String ROLE_ID_NULL_ERROR = "i18n.authorization.RoleIdNullException";
	
	private static final String ROLE_NAME_NULL_ERROR = "i18n.authorization.RoleNameNullException";
	
	private static final String ROLE_STATUS_NULL_ERROR = "i18n.authorization.RoleStatusNullException";
	
	private static final String ROLE_IS_DISTRIBUTED_ERROR = "i18n.authorization.RoleIsDistributedException";
	
	private static final String ROLE_HAD_NAME_ERROR = "i18n.authorization.RoleHadNameException";
	
	private static final String ROLE_HAD_BLANK_ERROR = "i18n.authorization.RoleHadBlankException";
	
	private static final String ROLE_NAME_IS_LONG_ERROR = "i18n.authorization.RoleNameLongException";

	private static final String ROLE_DESC_IS_LONG_ERROR = "i18n.authorization.RoleDescLongException";
	
	/**
	 *异常的构造方法
	 * @param RoleExceptionType errorType
	 * @since JDK1.6
	 */
	public RoleException(RoleExceptionType errorType){
		super();
		if(errorType.equals(RoleExceptionType.RoleIsNull)){
			this.errCode = ROLE_IS_NULL_ERROR;
		}
		if(errorType.equals(RoleExceptionType.RoleHadName)){
			this.errCode = ROLE_HAD_NAME_ERROR;
		}
		if(errorType.equals(RoleExceptionType.RoleHadBlank)){
			this.errCode = ROLE_HAD_BLANK_ERROR;
		}
		if(errorType.equals(RoleExceptionType.RoleIdNull)){
			this.errCode = ROLE_ID_NULL_ERROR;
		}
		if(errorType.equals(RoleExceptionType.RoleNameIsNull)){
			this.errCode = ROLE_NAME_NULL_ERROR;
		}
		if(errorType.equals(RoleExceptionType.RoleStatusIsNull)){
			this.errCode = ROLE_STATUS_NULL_ERROR;
		}
		if(errorType.equals(RoleExceptionType.RoleIsDistributed)){
			this.errCode = ROLE_IS_DISTRIBUTED_ERROR;
		}
		if(errorType.equals(RoleExceptionType.RoleNameIsLong)){
			this.errCode = ROLE_NAME_IS_LONG_ERROR;
		}
		if(errorType.equals(RoleExceptionType.RoleDescIsLong)){
			this.errCode = ROLE_DESC_IS_LONG_ERROR;
		}
	}
}
