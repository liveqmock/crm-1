package com.deppon.crm.module.report.shared.exception;

import com.deppon.foss.framework.exception.GeneralException;
/**
 *	大客户异常 
 * @author yw
 *
 */
public class ReportException extends GeneralException{
	public ReportException(ReportExceptionType type){
		this.errCode = type.getValue(type);
	}
}
