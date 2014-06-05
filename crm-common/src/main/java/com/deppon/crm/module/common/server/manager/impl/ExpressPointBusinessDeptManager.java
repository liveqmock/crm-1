package com.deppon.crm.module.common.server.manager.impl;

import com.deppon.crm.module.common.server.manager.IExpressPointBusinessDeptManager;
import com.deppon.crm.module.common.server.service.IExpressPointBusinessDeptService;
import com.deppon.crm.module.common.shared.domain.ExpressPointBusinessDept;

/**
 * 
 * Description:快递点部与营业部映射关系manager实现类<br />
 * 
 * @title ExpressPointBusinessDeptManager.java
 * @package com.deppon.crm.module.common.server.manager.impl
 * @author 陈道兵
 * @version 0.1 2013-08-01
 */
public class ExpressPointBusinessDeptManager implements
		IExpressPointBusinessDeptManager {

	private IExpressPointBusinessDeptService expressPointBusinessDeptService;

	public IExpressPointBusinessDeptService getExpressPointBusinessDeptService() {
		return expressPointBusinessDeptService;
	}

	public void setExpressPointBusinessDeptService(
			IExpressPointBusinessDeptService expressPointBusinessDeptService) {
		this.expressPointBusinessDeptService = expressPointBusinessDeptService;
	}

	public void operateExpointForInterface() {

	}

	/**
	 * Description:插入点部与营业部映射关系<br />
	 * 
	 * @author 陈道兵
	 * @version 0.1 2013-7-31
	 * @param dept
	 * @return void
	 */
	@Override
	public void insertExpressPointBusinessDept(ExpressPointBusinessDept dept) {
		if (dept != null) {
			 expressPointBusinessDeptService
					.insertExpressPointBusinessDept(dept);
		} else {
		
		}
	}

	/**
	 * Description:根据营业部标杆编码更新对应的点部<br />
	 * 
	 * @author 陈道兵
	 * @version 0.1 2013-7-31
	 * @param dept
	 * @return void
	 */
	@Override
	public void updateExpressPointBusinessDept(ExpressPointBusinessDept dept) {
		if (dept != null && dept.getId() != null && !"".equals(dept.getId())) {
			expressPointBusinessDeptService
					.updateExpressPointBusinessDept(dept);
		}

	}

	/**
	 * Description:根据营业部标杆编码查询对应的点部<br />
	 * 
	 * @author 陈道兵
	 * @version 0.1 2013-7-31
	 * @param deptCode
	 * @return ExpressPointBusinessDept
	 */
	@Override
	public ExpressPointBusinessDept getExpressPointBusinessDeptByDeptCode(
			String deptCode) {
		if (deptCode != null && !"".equals(deptCode)) {
			return expressPointBusinessDeptService
					.getExpressPointBusinessDeptByDeptCode(deptCode);
		} else {
			return null;
		}
	}

	@Override
	public void deleteExpressPointBusinessDeptByDeptCode(String deptCode,
			String pointCode) {
		expressPointBusinessDeptService
				.deleteExpressPointBusinessDeptByDeptCode(deptCode, pointCode);

	}

}
