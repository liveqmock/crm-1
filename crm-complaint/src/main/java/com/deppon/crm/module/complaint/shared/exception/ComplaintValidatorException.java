package com.deppon.crm.module.complaint.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

/**
 * <p>
 * Description:工单校验异常<br />
 * </p>
 * @title ComplaintValidatorException.java
 * @package com.deppon.crm.module.complaint.shared.exception 
 * @author Weill
 * @version 0.1 2012-4-20
 */
public class ComplaintValidatorException extends BusinessException {
	// 工单异常枚举 ComplaintExceptionType
	public ComplaintValidatorException(ComplaintExceptionType errorType){
		super();
		this.errCode = errorType.getErrorCode();
		
	}
}
