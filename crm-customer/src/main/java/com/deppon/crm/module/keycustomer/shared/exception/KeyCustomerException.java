package com.deppon.crm.module.keycustomer.shared.exception;

import com.deppon.foss.framework.exception.GeneralException;
/**
 * 
 * <p>
 * Description:大客户管理异常类<br />
 * </p>
 * @title KeyCustomerException.java
 * @package com.deppon.crm.module.keycustomer.shared.exception 
 * @author 106138
 * @version 0.1 2014-3-4
 */ 
public class KeyCustomerException  extends GeneralException {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 1L;


	public KeyCustomerException(KeyCustomerExceptionType errorType) {
		super();
		this.errCode = errorType.getErrCode();

	}


	public KeyCustomerException(KeyCustomerExceptionType errorType,
			Object[] objects) {
		super();
		this.errCode = errorType.getErrCode();
		setErrorArguments(objects);
	}
}
