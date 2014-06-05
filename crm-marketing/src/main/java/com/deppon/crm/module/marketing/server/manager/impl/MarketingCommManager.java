/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MarketingCommManager.java
 * @package com.deppon.crm.module.marketing.server.manager.impl 
 * @author ZhuPJ
 * @version 0.1 2012-7-3
 */
package com.deppon.crm.module.marketing.server.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.manager.IMarketingCommManager;
import com.deppon.crm.module.marketing.server.service.ICustomerGroupService;
import com.deppon.crm.module.marketing.server.service.IPlanService;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroup;
import com.deppon.crm.module.organization.shared.domain.Employee;

/**   
 * <p>
 * 营销计划操作封装<br />
 * </p>
 * @title MarketingCommManager.java
 * @package com.deppon.crm.module.marketing.server.manager.impl 
 * @author ZhuPJ
 * @version 0.1 2012-7-3
 */

public class MarketingCommManager implements IMarketingCommManager {
	// 客户分组Service
	private ICustomerGroupService customerGroupService;
	
	// 计划Service
	private IPlanService planService;
	
	/**
	 * @return customerGroupService : return the property customerGroupService.
	 */
	public ICustomerGroupService getCustomerGroupService() {
		return customerGroupService;
	}

	/**
	 * @param customerGroupService : set the property customerGroupService.
	 */
	public void setCustomerGroupService(ICustomerGroupService customerGroupService) {
		this.customerGroupService = customerGroupService;
	}

	/**
	 * @return planService : return the property planService.
	 */
	public IPlanService getPlanService() {
		return planService;
	}

	/**
	 * @param planService : set the property planService.
	 */
	public void setPlanService(IPlanService planService) {
		this.planService = planService;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IMarketingCommManager#getCustomerGroupByPrehuman(java.lang.String, java.lang.String)
	 */
	@Override
	public List<String> getCustomerGroupByPrehuman(String depId, String userId) {
		// 根据分组维护人取ID
		CustomerGroup cg = new CustomerGroup();
		//分组ID
		cg.setDeptId(depId);
		//维护人
		cg.setEmpId(userId);
		//分组条件查询
		return customerGroupService.getCustomerGroupByPrehuman(cg);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IMarketingCommManager#getPrehumanByGroupId(java.lang.String, java.lang.String)
	 */
	@Override
	public List<String> getPrehumanByGroupId(String depId, String groupId) {
		// 根据分组取维护人
		CustomerGroup cg = new CustomerGroup();
		//部门
		cg.setDeptId(depId);
		//分组ＩＤ
		cg.setId(groupId);
		//查询
		return customerGroupService.getPrehumanByGroupId(cg);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IMarketingCommManager#getPlanCreatorList(com.deppon.crm.module.authorization.shared.domain.User, java.lang.String)
	 */
	@Override
	public List<Employee> getPlanCreatorList(User user, String deptId, String planType) {
		// 根据部门信息获取相关部门下的计划创建者
		// 部门信息为空时，获取该用户下所有授权部门的计划创建者
		Map<String, Object> map = new HashMap<String, Object>();
		//部门ID
		if (StringUtils.isNotEmpty(deptId)){
			//执行部门
			map.put("executeDept", deptId);
		}else{
			//授权部门
			Set<String> deps = user.getDeptids();
			String[] depIds = new String[deps.size()];
			deps.toArray(depIds);
			//封装授权部门
			map.put("executeDepts", depIds);
		}
		//查询
		return planService.getPlanCreatorList(map);
	}

}
