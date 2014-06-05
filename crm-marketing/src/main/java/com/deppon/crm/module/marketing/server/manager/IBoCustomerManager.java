package com.deppon.crm.module.marketing.server.manager;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.marketing.shared.domain.BoCustomerCondition;
import com.deppon.crm.module.organization.shared.domain.Department;

/**
 * Title: IBoCustomerManager.java Description: 商机公共逻辑Manager
 * 
 * @author huangzhanming
 * @created 2014-3-25 下午1:57:15
 */
public interface IBoCustomerManager {

	/**
	 * @discription 按照商机条件查找客户
	 * @author huangzhanming
	 * @created 2014-3-25 下午1:57:55
	 * @param condition
	 * @return
	 */
	public List<MemberResult> queryMemberByConditionForBo(
			BoCustomerCondition condition);

	/**
	 * @discription 查询所属事业部
	 * @author huangzhanming
	 * @created 2014-3-25 下午1:58:23
	 * @param deptId
	 * @return
	 */
	public String queryBizDeptBySubDept(String deptId);

	/**
	 * @discription 查询部门的所有子部门
	 * @author huangzhanming
	 * @created 2014-3-25 下午1:58:39
	 * @param deptName
	 * @param parentId
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Department> queryDeptByParentDept(String deptName,
			String parentId, int start, int limit);

	/**
	 * @discription 查询所有部门的子部门计数
	 * @author huangzhanming
	 * @created 2014-3-25 下午1:59:09
	 * @param deptName
	 * @param parentId
	 * @return
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
