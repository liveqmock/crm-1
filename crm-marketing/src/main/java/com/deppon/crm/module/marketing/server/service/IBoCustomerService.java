package com.deppon.crm.module.marketing.server.service;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.marketing.shared.domain.BoCustomerCondition;
import com.deppon.crm.module.organization.shared.domain.Department;

/**
 * Title: IBoCustomerService.java Description: 商机客户查询
 * 
 * @author huangzhanming
 * @created 2014-3-25 上午11:29:56
 */
public interface IBoCustomerService {

	/**
	 * 
	 * @discription 查询商机客户信息
	 * @author huangzhanming
	 * @created 2014-3-25 上午11:31:34
	 * @param condition
	 * @return
	 */
	public List<MemberResult> queryMemberByConditionForBo(
			BoCustomerCondition condition);

	/**
	 * 
	 * @discription 查询所属上级部门
	 * @author huangzhanming
	 * @created 2014-3-25 上午11:31:14
	 * @param parentName
	 * @param subId
	 * @return
	 */
	public String queryDeptBySubDept(String parentName, String subId);

	/**
	 * @discription 查询部门的所属部门分页查询
	 * @author huangzhanming
	 * @created 2014-3-25 上午11:31:55
	 * @param deptName
	 * @param parentId
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Department> queryDeptByParentDept(String deptName,
			String parentId, int start, int limit);

	/**
	 * @discription 查询部门的所属部门分页计数
	 * @author huangzhanming
	 * @created 2014-3-25 上午11:32:12
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
