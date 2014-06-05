package com.deppon.crm.module.complaint.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;
/**
 * <p>
 * Description:工单异常<br />
 * </p>
 * @title ComplaintException.java
 * @package com.deppon.crm.module.complaint.shared.exception 
 * @author
 * @version 0.1 
 */
public class ComplaintException extends BusinessException{
	// ComplaintExceptionType 工单异常枚举
	public ComplaintException(ComplaintExceptionType errorType){
		super();
		this.errCode=errorType.getErrorCode();
	}
	// ComplaintExceptionType 工单异常枚举
	public ComplaintException(ComplaintExceptionType errorType, Object...args){
		super();
		this.errCode=errorType.getErrorCode();
		this.setErrorArguments(args);
	}
}
