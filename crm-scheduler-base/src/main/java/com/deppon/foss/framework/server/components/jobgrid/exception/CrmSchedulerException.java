package com.deppon.foss.framework.server.components.jobgrid.exception;

import com.deppon.foss.framework.exception.BusinessException;

/**
 * 
 * @ClassName: SchedulerException
 * @Description: 定时器异常
 * @author
 * @date 2012-3-30 下午4:47:58
 * 
 */
public class CrmSchedulerException extends BusinessException {

	private static final long serialVersionUID = -7832283853842525002L;

	public CrmSchedulerException(CrmSchedulerExceptionType type) {
		this.errCode = CrmSchedulerExceptionType.getValue(type);
	}
}
