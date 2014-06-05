package com.deppon.crm.module.backfreight.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

/**
 * Description:退运费异常
 * BackFreightExeception.java Create on 2012-10-29 上午10:15:50 
 * @author 华龙
 * @version 1.0
 */
public class BackFreightException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1322774313212694275L;

	/**
	 * @fields serialVersionUID
	 */


	/**
	 * constructer
	 */
	public BackFreightException(BackFreightExceptionType type) {
		this.errCode = BackFreightExceptionType.getValue(type);
	}
}
