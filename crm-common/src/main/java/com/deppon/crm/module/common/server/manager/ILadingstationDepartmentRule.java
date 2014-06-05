package com.deppon.crm.module.common.server.manager;

import com.deppon.crm.module.common.shared.domain.LadingstationDepartment;

public interface ILadingstationDepartmentRule {
	
	//创建
	public static final Integer OPERATION_TYPE_CREATE=0;
	
	//修改
	public static final Integer OPERATION_TYPE_MODIFY=1;
	
	//批量导入
	public static final Integer OPERATION_TYPE_IMPORT=2;
	
	
	/**
	 * 校验始发网点与受理部门关系的方法
	 * @param ld
	 * @return 错误信息
	 */
	public String validate(LadingstationDepartment ld,Integer operationType);
}
