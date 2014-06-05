package com.deppon.crm.module.marketing.bpsworkflow.branch;

import java.util.Map;

import org.springframework.web.context.WebApplicationContext;

import com.deppon.bpmsapi.module.client.util.BPMSConstant;
import com.deppon.crm.module.frameworkimpl.server.cache.ServerParameterCache;
import com.deppon.crm.module.marketing.server.manager.IMarketActivityManager;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivity;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.server.context.WebApplicationContextHolder;

public class MarketingBranch {

	/**
	 * 
	* @Title: isMarketingManagerDept 
	* @Description: 是否是事业部营销管理组
	* @author LiuY 
	* @@param map
	* @@return boolean true 是
	* @throws
	 */
	public boolean isMarketingManagerDept(Map<?, ?> map){
		MarketActivity marketActivity = new MarketActivity();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
		// 获得WebApplicationContext
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IMarketActivityManager marketActivityManager = (IMarketActivityManager)wac.getBean("marketActivityManager");
		marketActivity = marketActivityManager.searchMarketActivityByWorkflowNum(bizCode);
		IDepartmentService departmentService = (IDepartmentService) wac
				.getBean("departmentService");
		String deptId = marketActivity.getDeptId();
		Department dept = departmentService.getDepartmentById(deptId);
		String deptName = dept.getDeptName();
		String marketManagerDept=(String)CacheManager.getInstance().getCache(ServerParameterCache.UUID).get("MARKETING_MANAGEMENT_DEPARTMENT");
		if(deptName.endsWith(marketManagerDept)){
			return true;
		}
		return false;
	}
}
