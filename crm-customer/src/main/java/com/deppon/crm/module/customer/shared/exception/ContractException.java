/**
 * @description
 * @author 赵斌
 * @2012-4-17
 * @return
 */
package com.deppon.crm.module.customer.shared.exception;

import com.deppon.foss.framework.exception.GeneralException;

/**
 * @author 赵斌
 *
 */
public class ContractException extends GeneralException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 735453080528029971L;
	
	/**
	 * constructer
	 */
	public ContractException(ContractExceptionType errorType) {
		super();
		this.errCode = errorType.getErrCode();
	}
	
	public ContractException(ContractExceptionType errorType,Object... arguments){
		this(errorType);
		super.setErrorArguments(arguments);
	}

}
