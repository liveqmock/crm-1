package com.deppon.crm.module.organization.shared.exception;                

/**
 * 异常类型枚举
 */
public enum DepartmentExceptionType {
	
	/**
	 * 区域对象Id为空
	 */
	DepartmentIdNull, 
	
	/**
	 * 区域对象为空
	 */
	DepartmentIsNull,
	/**
	 * 部门名称不得超过50位！
	 */
	DeptNameTooLong
}
