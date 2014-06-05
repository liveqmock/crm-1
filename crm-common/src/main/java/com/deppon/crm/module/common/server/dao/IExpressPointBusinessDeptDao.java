package com.deppon.crm.module.common.server.dao;

import com.deppon.crm.module.common.shared.domain.ExpressPointBusinessDept;
/**
 * 
 * Description:点部与营业部映射关系DAO接口<br />
 * @title      IExpressPointBusinessDeptDao.java
 * @package    com.deppon.crm.module.common.server.dao
 * @author     陈道兵
 * @version    0.1 2013-08-01
 */
public interface IExpressPointBusinessDeptDao {
	/**
	 * Description:插入点部与营业部映射关系<br />
	 * @author     陈道兵
	 * @version    0.1 2013-8-1
	 * @param      dept
	 * @return     void
	 */
	void insertExpressPointBusinessDept(ExpressPointBusinessDept dept);
	/**
	 * Description:根据营业部标杆编码更新对应的点部<br />
	 * @author     陈道兵
	 * @version    0.1 2013-8-1
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
	void deleteExpressPointBusinessDeptByDeptCode(String Deptcode,String pointCode);
	
	int getExpressPointBusinessDeptByApplyDeptCode(String deptCode);
}
