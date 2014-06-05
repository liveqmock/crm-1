package com.deppon.crm.module.organization.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

/**
 * 
 *作 者：张斌 最后修改
 *时间：2011年10月20日
 * 描 述： 与员工信息有关的异常（id为Null,Employee对象为空）
 */
public class EmployeeException extends BusinessException {

	private static final long serialVersionUID = -3197608378148829833L;

	private static final String IS_NULL_ERROR_CODE = "i18n.organization.EmployeeIsNullException";

	private static final String ID_NULL_ERROR_CODE = "i18n.organization.EmployeeIdNullException";
	/**
	 *异常的构造方法
	 * @param EmployeeExceptionType errorType
	 * @since JDK1.6
	 */
	public EmployeeException(EmployeeExceptionType errorType) {
		super();
		if (errorType.equals(EmployeeExceptionType.IsNull)) {
			this.errCode = IS_NULL_ERROR_CODE;
		}
		if (errorType.equals(EmployeeExceptionType.IdNull)) {
			this.errCode = ID_NULL_ERROR_CODE;
		}
	}
}
