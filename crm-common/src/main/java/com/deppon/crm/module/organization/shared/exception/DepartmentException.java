package com.deppon.crm.module.organization.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

/**
 * 
 * 作 者：张斌 最后修改
 * 时间：2011年10月20日 
 * 描 述： 与部门信息有关的异常（id为Null,Department对象为空）
 */
public class DepartmentException extends BusinessException {

	private static final long serialVersionUID = -5417345475365692960L;

	private static final String department_ID_NULL_ERROR_CODE = "i18n.organization.DepartmentIdNullException";

	private static final String department_OBJECT_NULL_ERROR_CODE = "i18n.organization.DepartmentNotFoundException";
	//部门名称不得超过50位！
	private static final String department_DEPTNAME_TOOLONG="i18n.organization.DepartmentDeptNameTooLong";
	/**
	 *异常的构造方法
	 * @param DepartmentExceptionType errorType
	 * @since JDK1.6
	 */
	public DepartmentException(DepartmentExceptionType errorType) {
		super();
		if (errorType.equals(DepartmentExceptionType.DepartmentIdNull)) {
			this.errCode = department_ID_NULL_ERROR_CODE;
		}
		if (errorType.equals(DepartmentExceptionType.DepartmentIsNull)) {
			this.errCode = department_OBJECT_NULL_ERROR_CODE;
		}
		if (errorType.equals(DepartmentExceptionType.DeptNameTooLong)) {
			this.errCode = department_DEPTNAME_TOOLONG;
		}
	}
}
