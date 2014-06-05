package com.deppon.crm.module.marketing.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

public class BusinessOpportunityException extends BusinessException {

	private static final long serialVersionUID = 3090182347198293366L;

	public BusinessOpportunityException(
			BusinessOpportunityExceptionType errorType) {
		super();
		this.errCode = errorType.getErrCode();
	}

	public BusinessOpportunityException(
			BusinessOpportunityExceptionType errorType, Object... args) {
		// 父类构造器
		super();
		// 变量初始化
		this.errCode = errorType.getErrCode();
		// 错误参数
		this.setErrorArguments(args);
	}
}
