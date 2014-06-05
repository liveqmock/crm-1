package com.deppon.crm.module.marketing.server.service.impl;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.marketing.server.dao.IBoCustomerDao;
import com.deppon.crm.module.marketing.server.service.IBoCustomerService;
import com.deppon.crm.module.marketing.shared.domain.BoCustomerCondition;
import com.deppon.crm.module.organization.shared.domain.Department;

/**
 * Title: IBoCustomerService.java Description: 商机客户查询
 * 
 * @author huangzhanming
 * @created 2014-3-25 上午11:29:56
 */
public class BoCustomerService implements IBoCustomerService {
	private IBoCustomerDao boCustomerDao;

	public IBoCustomerDao getBoCustomerDao() {
		return boCustomerDao;
	}

	public void setBoCustomerDao(IBoCustomerDao boCustomerDao) {
		this.boCustomerDao = boCustomerDao;
	}

	/**
	 * 
	 * @discription 查询商机客户信息
	 * @author huangzhanming
	 * @created 2014-3-25 上午11:31:34
	 * @param condition
	 * @return
	 */
	public List<MemberResult> queryMemberByConditionForBo(
			BoCustomerCondition condition) {
		return boCustomerDao.queryMemberByConditionForBo(condition);
	}

	/**
	 * 
	 * @discription 查询所属上级部门
	 * @author huangzhanming
	 * @created 2014-3-25 上午11:31:14
	 * @param parentName
	 * @param subId
	 * @return
	 */
	@Override
	public String queryDeptBySubDept(String parentName, String subId) {
		return boCustomerDao.queryDeptBySubDept(parentName, subId);
	}

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
	@Override
	public List<Department> queryDeptByParentDept(String deptName,
			String parentId, int start, int limit) {
		return boCustomerDao.queryDeptByParentDept(deptName, parentId, start,
				limit);
	}

	/**
	 * @discription 查询部门的所属部门分页计数
	 * @author huangzhanming
	 * @created 2014-3-25 上午11:32:12
	 * @param deptName
	 * @param parentId
	 * @return
	 */
	@Override
	public Long countDeptByParentDept(String deptName, String parentId) {
		return boCustomerDao.countDeptByParentDept(deptName, parentId);
	}

	/**
	 * 查询客户所属部门的负责人ID
	 * 
	 * @param custId
	 * @return
	 */
	@Override
	public String queryCustDeptManagerId(String custId) {
		return boCustomerDao.queryCustDeptManagerId(custId);
	}

}
