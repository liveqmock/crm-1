package com.deppon.crm.module.common.server.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.deppon.crm.module.common.server.dao.IExpressPointBusinessDeptDao;
import com.deppon.crm.module.common.shared.domain.ExpressPointBusinessDept;
import com.deppon.crm.module.common.shared.domain.HelpDoc;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 
 * Description:快递点部与营业部映射关系DAO<br />
 * 
 * @title ExpressPointBusinessDeptDao.java
 * @package com.deppon.crm.module.common.server.dao.impl
 * @author 陈道兵
 * @version 0.1 2013-08-01
 */
public class ExpressPointBusinessDeptDao extends iBatis3DaoImpl implements
		IExpressPointBusinessDeptDao {

	/**
	 * 快递点部与营业部映射关系命名空间
	 */
	private static final String NAMESPACE = "com.deppon.crm.module.common.shared.domain.ExpressPointBusinessDept.";
	/**
	 * 新增快递点部与营业部映射关系
	 */
	private static final String ADD_EXPRESS_POINT_BUSINESS_DEPT = "addExpressPointBusinessDept";
	/**
	 * 根据营业部标杆编码更新对应的快递点部
	 */
	private static final String UPDATE_EXPRESS_POINT_BUSINESS_DEPT = "updateExpressPointBusinessDept";
	/**
	 * 根据营业部标杆编码查询对应的快递点部
	 */
	private static final String GET_EXPRESS_POINT_BUSINESS_DEPT_BY_DEPTCODE = "getExpressPointBusinessDeptByDeptCode";
	private static final String DELETE_EXPRESS_POINT_BUSINESS_DEPT = "deleteExpressPointBusinessDept";
	
	private static final String GET_EXPRESS_POINT_BUSINESS_DEPT_BY_APPLYDEPTCODE = "getExpressPointBusinessDeptByApplyDeptCode";

	/**
	 * Description:插入快递点部与营业部映射关系<br />
	 * 
	 * @author 陈道兵
	 * @version 0.1 2013-7-31
	 * @param dept
	 * @return void
	 */
	@Override
	public void insertExpressPointBusinessDept(ExpressPointBusinessDept dept) {
		getSqlSession().insert(NAMESPACE + ADD_EXPRESS_POINT_BUSINESS_DEPT,
				dept);

	}

	/**
	 * Description:根据营业部标杆编码更新对应的快递点部<br />
	 * 
	 * @author 陈道兵
	 * @version 0.1 2013-7-31
	 * @param dept
	 * @return void
	 */
	@Override
	public void updateExpressPointBusinessDept(ExpressPointBusinessDept dept) {
		getSqlSession().update(NAMESPACE + UPDATE_EXPRESS_POINT_BUSINESS_DEPT,
				dept);

	}

	/**
	 * Description:根据营业部标杆编码查询对应的快递点部<br />
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
			return (ExpressPointBusinessDept) getSqlSession().selectOne(
					NAMESPACE + GET_EXPRESS_POINT_BUSINESS_DEPT_BY_DEPTCODE,
					deptCode);
		}
		return null;
	}

	@Override
	public void deleteExpressPointBusinessDeptByDeptCode(String deptcode,
			String pointCode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("deptCode", deptcode);
		map.put("pointCode", pointCode);

		getSqlSession().delete(NAMESPACE + DELETE_EXPRESS_POINT_BUSINESS_DEPT,
				map);

	}
	
	/**
	 * Description:判断是否是快递点部<br />
	 * 
	 * @author andy
	 * @version 0.1 2014-1-10
	 * @param deptCode
	 * @return ExpressPointBusinessDept
	 */
	@Override
	public int getExpressPointBusinessDeptByApplyDeptCode(
			String deptCode) {
		if (deptCode != null && !"".equals(deptCode)) {
			return Integer.parseInt((String)getSqlSession().selectOne(
					NAMESPACE + GET_EXPRESS_POINT_BUSINESS_DEPT_BY_APPLYDEPTCODE,
					deptCode));
		}
		return 0;
	}
}
