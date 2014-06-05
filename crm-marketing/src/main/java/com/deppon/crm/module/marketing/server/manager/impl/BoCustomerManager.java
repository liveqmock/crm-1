package com.deppon.crm.module.marketing.server.manager.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.marketing.server.manager.IBoCustomerManager;
import com.deppon.crm.module.marketing.server.service.IBoCustomerService;
import com.deppon.crm.module.marketing.server.utils.BusinessOpportunityValidator;
import com.deppon.crm.module.marketing.shared.domain.BoCustomerCondition;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportuntiyConstants;
import com.deppon.crm.module.organization.shared.domain.Department;

/**
 * Title: IBoCustomerManager.java Description: 商机公共逻辑Manager
 * 
 * @author huangzhanming
 * @created 2014-3-25 下午1:57:15
 */
@Transactional
public class BoCustomerManager implements IBoCustomerManager {
	private IBoCustomerService boCustomerService;

	public void setBoCustomerService(IBoCustomerService boCustomerService) {
		this.boCustomerService = boCustomerService;
	}

	/**
	 * @discription 按照商机条件查找客户
	 * @author huangzhanming
	 * @created 2014-3-25 下午1:57:55
	 * @param condition
	 * @return
	 */
	public List<MemberResult> queryMemberByConditionForBo(
			BoCustomerCondition condition) {
		// 查询条件为空，抛出异常
		BusinessOpportunityValidator.checkBoCustomerCondition(condition);
		BoCustomerCondition nc = filterCondition(condition);
		return boCustomerService.queryMemberByConditionForBo(nc);
	}

	/**
	 * 查询优先级处理
	 * 
	 * @param condition
	 * @return
	 */
	private BoCustomerCondition filterCondition(BoCustomerCondition condition) {
		BoCustomerCondition nc = new BoCustomerCondition();
		if (null == condition) {
			return nc;
		} else if (StringUtils.isNotEmpty(condition.getCustNumber())) {
			// 客户编码
			nc.setCustNumber(condition.getCustNumber());
			// 默认部门范围
			nc.setParentId(condition.getParentId());
			nc.setDeptType(condition.getDeptType());
		} else if (StringUtils.isNotEmpty(condition.getMobile())) {
			// 手机号
			nc.setMobile(condition.getMobile());
			// 默认部门范围
			nc.setParentId(condition.getParentId());
			nc.setDeptType(condition.getDeptType());
		} else if (StringUtils.isNotEmpty(condition.getLinkmanName())
				&& StringUtils.isNotEmpty(condition.getPhone())) {
			// 联系人
			nc.setLinkmanName(condition.getLinkmanName());
			// 固定电话
			nc.setPhone(condition.getPhone());
			// 默认部门范围
			nc.setParentId(condition.getParentId());
			nc.setDeptType(condition.getDeptType());
		} else {
			nc = condition;
		}
		return nc;
	}

	/**
	 * @discription 查询所属事业部
	 * @author huangzhanming
	 * @created 2014-3-25 下午1:58:23
	 * @param deptId
	 * @return
	 */
	@Override
	public String queryBizDeptBySubDept(String deptId) {
		return boCustomerService.queryDeptBySubDept(
				BusinessOpportuntiyConstants.BIZ_DEPT_CONDITION, deptId);
	}

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
	@Override
	public List<Department> queryDeptByParentDept(String deptName,
			String parentId, int start, int limit) {
		return boCustomerService.queryDeptByParentDept(deptName, parentId,
				start, limit);
	}

	/**
	 * @discription 查询所有部门的子部门计数
	 * @author huangzhanming
	 * @created 2014-3-25 下午1:59:09
	 * @param deptName
	 * @param parentId
	 * @return
	 */
	@Override
	public Long countDeptByParentDept(String deptName, String parentId) {
		return boCustomerService.countDeptByParentDept(deptName, parentId);
	}

	/**
	 * 查询客户所属部门的负责人ID
	 * 
	 * @param custId
	 * @return
	 */
	@Override
	public String queryCustDeptManagerId(String custId) {
		return boCustomerService.queryCustDeptManagerId(custId);
	}

}
