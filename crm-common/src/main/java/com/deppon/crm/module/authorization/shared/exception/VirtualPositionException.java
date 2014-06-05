package com.deppon.crm.module.authorization.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;
/**
 * 
 * 作 者：张斌 最后修改
 * 时间：2011年10月24日 
 * 描 述： 与角色信息有关的异常
 */
public class VirtualPositionException extends BusinessException {

	private static final long serialVersionUID = -1699670080098014105L;
	public VirtualPositionException(VirtualPositionExceptionType errorType){
		super();
		this.errCode=errorType.getErrCode();
	}
}
