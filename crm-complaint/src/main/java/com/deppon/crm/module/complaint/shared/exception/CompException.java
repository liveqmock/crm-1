package com.deppon.crm.module.complaint.shared.exception;

import com.deppon.foss.framework.exception.GeneralException;
/**
 * 
 * <p>
 * Description:工单异常类
 * </p>
 * @title BaseInfoException.java
 * @package com.deppon.crm.module.complaint.shared.exception 
 * @author LiuY
 * @version 0.1 2013-9-23
 */
public class CompException extends GeneralException{
	// ComplaintExceptionType 工单异常枚举
		public CompException(ComplaintExceptionType errorType){
			super();  
			this.errCode=errorType.getErrorCode();
		}
		// ComplaintExceptionType 工单异常枚举
		public CompException(ComplaintExceptionType errorType, Object...args){
			super();
			this.errCode=errorType.getErrorCode();
			this.setErrorArguments(args);
		}
		
		// ComplaintExceptionType 工单异常枚举
		public CompException(String errCode, Object...args){
			super();
			this.errCode = errCode;
			this.setErrorArguments(args);
		}
}
