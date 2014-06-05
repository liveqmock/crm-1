package com.deppon.crm.module.authorization.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

public class UserException extends BusinessException {

	private static final long serialVersionUID = -1982586715098015347L;

	private static final String USER_IS_NULL_ERROR_CODE = "i18n.authorization.UserIsNullException";
	
	private static final String USER_ID_NULL_ERROR_CODE = "i18n.authorization.UserIdNullException";
	
	private static final String LOGINNAME_NULL_ERROR_CODE = "i18n.authorization.LoginNameNullException";
	
	private static final String PASSWORD_NULL_ERROR_CODE = "i18n.authorization.UserPasswordNullException";
	
	private static final String USER_STATUS_NULL_ERROR_CODE = "i18n.authorization.UserStatusNullException";
	
	private static final String USER_EXIST_ERROR_CODE = "i18n.authorization.UserIsExistException";
	
	private static final String DEPTID_NULL_ERROR_CODE = "i18n.authorization.DeptIdNullException";
	
	private static final String DEPTIDS_NULL_ERROR_CODE = "i18n.authorization.DeptIdsNullException";
	
	public UserException(UserExceptionType errorType){
		super();
		if(errorType.equals(UserExceptionType.UserIsNull)){
			this.errCode = USER_IS_NULL_ERROR_CODE;
		}
		if(errorType.equals(UserExceptionType.UserIdIsNull)){
			this.errCode = USER_ID_NULL_ERROR_CODE;
		}
		if(errorType.equals(UserExceptionType.LoginNameIsNull)){
			this.errCode = LOGINNAME_NULL_ERROR_CODE;
		}
		if(errorType.equals(UserExceptionType.UserPasswordIsNull)){
			this.errCode = PASSWORD_NULL_ERROR_CODE;
		}
		if(errorType.equals(UserExceptionType.UserStatusIsNull)){
			this.errCode = USER_STATUS_NULL_ERROR_CODE;
		}
		if(errorType.equals(UserExceptionType.UserIsExist)){
			this.errCode = USER_EXIST_ERROR_CODE;
		}
		if(errorType.equals(UserExceptionType.DeptIdIsNull)){
			this.errCode = DEPTID_NULL_ERROR_CODE;
		}
		if(errorType.equals(UserExceptionType.DeptIdsIsNull)){
			this.errCode = DEPTIDS_NULL_ERROR_CODE;
		}
	}
}
