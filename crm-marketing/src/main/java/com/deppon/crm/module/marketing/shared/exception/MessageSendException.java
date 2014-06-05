package com.deppon.crm.module.marketing.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

public class MessageSendException extends BusinessException {
	private static final long serialVersionUID = 1L;

	public MessageSendException(MessageSendExceptionType errorType){
		super();
		this.errCode=errorType.getErrCode();
	}
}
