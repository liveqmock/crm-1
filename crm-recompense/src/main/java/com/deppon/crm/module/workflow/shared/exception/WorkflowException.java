package com.deppon.crm.module.workflow.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

/**
 * 
 * @ClassName: WorkflowException
 * @Description: 工作流异常
 * @author andy
 * @date 2013-8-13
 * 
 */
public class WorkflowException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * constructer
	 */
	public WorkflowException(WorkflowExceptionType type) {
		this.errCode = WorkflowExceptionType.getValue(type);
	}
}
