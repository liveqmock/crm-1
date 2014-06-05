package com.deppon.crm.module.sysmail.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

/**
 * 
 * <p>
 * 邮件管理异常类型<br />
 * </p>
 * @title MailException.java
 * @package com.deppon.crm.module.sysmail.shared.exception 
 * @author suyujun
 * @version 0.1 2013-9-18
 */
public class MailException extends BusinessException {
	private static final long serialVersionUID = -1699670080098014105L;
	public MailException(MailExceptionType errorType){
		super();
		this.errCode=errorType.getErrCode();
	}
}
