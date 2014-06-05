package com.deppon.crm.module.marketing.bpsworkflow.participant;

import java.util.List;
import java.util.Map;

import org.springframework.web.context.WebApplicationContext;

import com.deppon.bpms.module.shared.domain.BpmsParticipant;
import com.deppon.bpmsapi.module.client.util.BPMSConstant;
import com.deppon.crm.module.frameworkimpl.server.cache.ServerParameterCache;
import com.deppon.crm.module.marketing.bpsworkflow.util.BpsUtils;
import com.deppon.crm.module.marketing.server.manager.IMarketActivityManager;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivity;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.server.context.WebApplicationContextHolder;

public class MarketingParticipant {

	
	/**
	 * <p>
	 *	Description: 获取到事业部
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-24
	 * @param map
	 * @return
	 * BpmsParticipant[]
	 */
	public BpmsParticipant[] getBusinessDept(Map<?, ?> map,String perContant) {
		Department department = this.getDept(map);
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IDepartmentService departmentService = (IDepartmentService) wac
				.getBean("departmentService");
		//获取 "事业部" 字符串
		String businessDept = (String)CacheManager.getInstance().getCache(ServerParameterCache.UUID)
				.get("BUSSINESS_DEPARTMENT");
		//根据工作流起草部门获取其上级所有部门集合
		List<Department> deptList = departmentService.getAllParentDeptByDeptId(department.getId());
		//获取到对应事业部
		for(Department newDept : deptList){
			//若部门以事业部结尾，则返回事业部
			if(newDept.getDeptName().endsWith(businessDept)){
				return BpsUtils.queryBusiBpmsParticipant(newDept,perContant);
			}
		}
		return null;
	}
	/**
	 * <p>
	 *	Description: 获取当前部门
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-24
	 * @param map
	 * @return
	 * Department
	 */
	public  Department getDept(Map<?, ?> map) {
		MarketActivity marketActivity = new MarketActivity();
		//获取当前工作流号
		String bizCode = (String) map.get(BPMSConstant.BIZCODE);
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IMarketActivityManager marketActivityManager = (IMarketActivityManager)wac
				.getBean("marketActivityManager");
		IDepartmentService departmentService = (IDepartmentService) wac
				.getBean("departmentService");
		//根据工作流号获取营销活动实体
		marketActivity = marketActivityManager.searchMarketActivityByWorkflowNum(bizCode);
		//获取到工作流申请部门
		Department dept = departmentService.getDepartmentById(marketActivity
				.getDeptId());
		return dept;
	}
}
