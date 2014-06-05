package com.deppon.crm.module.common.shared.domain;

import com.deppon.foss.framework.exception.BusinessException;

/**
 * Description:服务补救异常
 * ServiceRecoveryException.java Create on 2012-10-29 上午10:15:50 
 * @author 华龙
 * @version 1.0
 */
public class SpecialDayException extends BusinessException {

	/**
	 * @fields serialVersionUID
	 */


	private static final long serialVersionUID = 2931564407630287224L;

	/**
	 * constructer
	 */
	public SpecialDayException(SpecialDayExceptionType type) {
		this.errCode = SpecialDayExceptionType.getValue(type);
	}
}
