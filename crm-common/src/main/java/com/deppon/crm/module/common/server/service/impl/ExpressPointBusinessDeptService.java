package com.deppon.crm.module.common.server.service.impl;

import com.deppon.crm.module.common.server.dao.IExpressPointBusinessDeptDao;
import com.deppon.crm.module.common.server.service.IExpressPointBusinessDeptService;
import com.deppon.crm.module.common.shared.domain.ExpressPointBusinessDept;

/**
 * Description:点部与营业部映射关系service<br />
 * @title      ExpressPointBusinessDeptService.java
 * @package    com.deppon.crm.module.common.server.service.impl
 * @author     陈道兵
 * @version    0.1 2013-08-01
 */
public class ExpressPointBusinessDeptService implements
		IExpressPointBusinessDeptService {

	private IExpressPointBusinessDeptDao expressPointBusinessDeptDao;
	
	public IExpressPointBusinessDeptDao getExpressPointBusinessDeptDao() {
		return expressPointBusinessDeptDao;
	}

	public void setExpressPointBusinessDeptDao(
			IExpressPointBusinessDeptDao expressPointBusinessDeptDao) {
		this.expressPointBusinessDeptDao = expressPointBusinessDeptDao;
	}
	
	/**
	 * Description:插入点部与营业部映射关系<br />
	 * @author     陈道兵
	 * @version    0.1 2013-7-31
	 * @param      dept
	 * @return     void
	 */
	@Override
	public void insertExpressPointBusinessDept(ExpressPointBusinessDept dept) {
		  expressPointBusinessDeptDao.insertExpressPointBusinessDept(dept);
	}
	
	/**
	 * Description:根据营业部标杆编码更新对应的点部<br />
	 * @author     陈道兵
	 * @version    0.1 2013-7-31
	 * @param      dept
	 * @return     void
	 */
	@Override
	public void updateExpressPointBusinessDept(ExpressPointBusinessDept dept) {
		 expressPointBusinessDeptDao.updateExpressPointBusinessDept(dept);
	}

	/**
	 * Description:根据营业部标杆编码查询对应的点部<br />
	 * @author     陈道兵
	 * @version    0.1 2013-7-31
	 * @param      deptCode
	 * @return     ExpressPointBusinessDept
	 */
	@Override
	public ExpressPointBusinessDept getExpressPointBusinessDeptByDeptCode(
			String deptCode) {
		return expressPointBusinessDeptDao.getExpressPointBusinessDeptByDeptCode(deptCode);
	}

	@Override
	public void deleteExpressPointBusinessDeptByDeptCode(String deptCode,String pointCode) {
		 expressPointBusinessDeptDao.deleteExpressPointBusinessDeptByDeptCode(deptCode, pointCode);
	}

}
