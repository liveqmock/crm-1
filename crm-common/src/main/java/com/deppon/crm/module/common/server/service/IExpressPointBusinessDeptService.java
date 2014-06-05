package com.deppon.crm.module.common.server.service;

import com.deppon.crm.module.common.shared.domain.ExpressPointBusinessDept;

/**
 * Description:点部与营业部映射关系service接口<br />
 * @title      IExpressPointBusinessDeptService.java
 * @package    com.deppon.crm.module.common.server.service
 * @author     陈道兵
 * @version    0.1 2013-08-01
 */
public interface IExpressPointBusinessDeptService {
	
	/**
	 * Description:插入点部与营业部映射关系<br />
	 * @author     陈道兵
	 * @version    0.1 2013-7-31
	 * @param      dept
	 * @return     void
	 */
	void insertExpressPointBusinessDept(ExpressPointBusinessDept dept);
	
	/**
	 * Description:根据营业部标杆编码更新对应的点部<br />
	 * @author     陈道兵
	 * @version    0.1 2013-7-31
	 * @param      dept
	 * @return     void
	 */
	void updateExpressPointBusinessDept(ExpressPointBusinessDept dept);
	
	/**
	 * Description:根据营业部标杆编码查询对应的点部<br />
	 * @author     陈道兵
	 * @version    0.1 2013-7-31
	 * @param      deptCode
	 * @return     ExpressPointBusinessDept
	 */
	ExpressPointBusinessDept getExpressPointBusinessDeptByDeptCode(
			String deptCode);
	/**
	 * 
	 * <p>
	 * Description:删除<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-8-12
	 * @param code
	 * @return
	 * boolean
	 */
	void deleteExpressPointBusinessDeptByDeptCode(String deptCode,String pointCode);
}
