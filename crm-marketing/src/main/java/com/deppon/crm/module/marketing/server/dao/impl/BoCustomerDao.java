package com.deppon.crm.module.marketing.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.marketing.server.dao.IBoCustomerDao;
import com.deppon.crm.module.marketing.shared.domain.BoCustomerCondition;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * Title: BoCustomerDao.java Description: 商机客户查询
 * 
 * @author huangzhanming
 * @created 2014-3-18 上午9:24:08
 */
@SuppressWarnings("unchecked")
public class BoCustomerDao extends iBatis3DaoImpl implements IBoCustomerDao {

	private static final String BOCUSTOMER_NAMESPACE = "com.deppon.crm.module.marketing.shared.domain.BoCustomer.";
	private static final String QUERY_BY_CONDITION = "queryMemberByConditionForBo";
	private static final String QUERY_DEPT_BY_SUBDEPT = "queryDeptBySubDept";
	private static final String QUERY_DEPT_BY_PARENTDEPT = "queryDeptByParentDept";
	private static final String COUNT_DEPT_BY_PARENTDEPT = "countDeptByParentDept";
	private static final String QUERY_MANAGER_BY_CUSTID = "queryCustDeptManagerId";

	/**
	 * @discription 查询商机客户信息
	 * @author huangzhanming
	 * @created 2014-3-18 上午9:23:39
	 * @param condition
	 * @return
	 * @see com.deppon.crm.module.marketing.server.dao.IBoCustomerDao#queryMemberByConditionForBo(com.deppon.crm.module.marketing.shared.domain.BoCustomerCondition)
	 */
	@Override
	public List<MemberResult> queryMemberByConditionForBo(
			BoCustomerCondition condition) {
		return (List<MemberResult>) this.getSqlSession().selectList(
				BOCUSTOMER_NAMESPACE + QUERY_BY_CONDITION, condition);
	}

	/**
	 * @discription 查询所属上级部门
	 * @author huangzhanming
	 * @created 2014-3-18 上午9:26:07
	 * @param parentName
	 * @param subId
	 * @return
	 * @see com.deppon.crm.module.marketing.server.dao.IBoCustomerDao#queryDeptBySubDept(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public String queryDeptBySubDept(String parentName, String subId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentName", parentName);
		map.put("subId", subId);
		return (String) this.getSqlSession().selectOne(
				BOCUSTOMER_NAMESPACE + QUERY_DEPT_BY_SUBDEPT, map);
	}

	/**
	 * @discription 查询部门的所属部门分页查询
	 * @author huangzhanming
	 * @created 2014-3-18 上午9:26:50
	 * @param deptName
	 * @param parentId
	 * @param start
	 * @param limit
	 * @return
	 * @see com.deppon.crm.module.marketing.server.dao.IBoCustomerDao#queryDeptByParentDept(java.lang.String,
	 *      java.lang.String, int, int)
	 */
	@Override
	public List<Department> queryDeptByParentDept(String deptName,
			String parentId, int start, int limit) {
		if (start == 0) {
			start = 1;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deptName", "%" + deptName + "%");
		map.put("parentId", parentId);
		map.put("start", start);
		map.put("limit", limit);
		return (List<Department>) this.getSqlSession().selectList(
				BOCUSTOMER_NAMESPACE + QUERY_DEPT_BY_PARENTDEPT, map);
	}

	/**
	 * @discription 查询部门的所属部门分页计数
	 * @author huangzhanming
	 * @created 2014-3-18 上午9:27:48
	 * @param deptName
	 * @param parentId
	 * @return
	 * @see com.deppon.crm.module.marketing.server.dao.IBoCustomerDao#countDeptByParentDept(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Long countDeptByParentDept(String deptName, String parentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deptName", "%" + deptName + "%");
		map.put("parentId", parentId);
		return (Long) this.getSqlSession().selectOne(
				BOCUSTOMER_NAMESPACE + COUNT_DEPT_BY_PARENTDEPT, map);
	}

	/**
	 * 查询客户所属部门的负责人ID
	 * 
	 * @param custId
	 */
	@Override
	public String queryCustDeptManagerId(String custId) {
		return (String) this.getSqlSession().selectOne(
				BOCUSTOMER_NAMESPACE + QUERY_MANAGER_BY_CUSTID, custId);
	}

}
