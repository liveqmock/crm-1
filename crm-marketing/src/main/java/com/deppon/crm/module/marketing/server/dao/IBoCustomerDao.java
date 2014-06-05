package com.deppon.crm.module.marketing.server.dao;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.marketing.shared.domain.BoCustomerCondition;
import com.deppon.crm.module.organization.shared.domain.Department;

/**
 * Title: BoCustomerDao.java Description: 商机客户查询
 * 
 * @author huangzhanming
 * @created 2014-3-18 上午9:24:08
 */
public interface IBoCustomerDao {

	/**
	 * @discription 查询商机客户信息
	 * @author huangzhanming
	 * @created 2014-3-18 上午9:23:39
	 * @param condition
	 * @return
	 * @see com.deppon.crm.module.marketing.server.dao.IBoCustomerDao#queryMemberByConditionForBo(com.deppon.crm.module.marketing.shared.domain.BoCustomerCondition)
	 */
	public List<MemberResult> queryMemberByConditionForBo(
			BoCustomerCondition condition);

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
	public String queryDeptBySubDept(String parentName, String subId);

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
	public List<Department> queryDeptByParentDept(String deptName,
			String parentId, int start, int limit);

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
	public Long countDeptByParentDept(String deptName, String parentId);

	/**
	 * 查询客户所属部门的负责人ID
	 * 
	 * @param custId
	 * @return
	 */
	public String queryCustDeptManagerId(String custId);
}
