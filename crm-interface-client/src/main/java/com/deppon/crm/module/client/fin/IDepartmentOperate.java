package com.deppon.crm.module.client.fin;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;

/**
 * @作者：罗典
 * @时间：2012-9-4
 * @描述：调用外部部门信息接口
 * */
public interface IDepartmentOperate {
	/**
	 * @作者：罗典
	 * @时间：2012-9-4
	 * @描述：根据部门标杆编码从财务部门获取子公司名称
	 * */
	public String querySubCompanyNameByCode(String deptCode) throws CrmBusinessException;
}
