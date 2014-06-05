package com.deppon.crm.module.customer.bpsworkflow.participant;

import java.util.Map;

import org.springframework.web.context.WebApplicationContext;

import com.deppon.bpms.module.shared.domain.BpmsParticipant;
import com.deppon.bpmsapi.module.client.util.BPMSConstant;
import com.deppon.crm.module.customer.bpsworkflow.util.BpsUtils;
import com.deppon.crm.module.custrepeat.server.manager.IRepeatedCustManager;
import com.deppon.crm.module.custrepeat.shared.domain.RepetitiveCustWorkFlowInfo;
import com.deppon.crm.module.frameworkimpl.server.cache.ServerParameterCache;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.server.context.WebApplicationContextHolder;

public class RepetitiveCustParticipant {
	
	
	public static final String DEPTLEVELBUSINESS     = "4";    // 事业部级别
	public static final String DEPTLEVELFORLARGEAREA = "5";    // 大区级别
	public static final String DEPTLEVELFORAREA      = "6";    // 区域级别
	public static final String DEPTLEVELDEF          = "7";    // 营业部、派送部 级别
	
	/**
	 * <p>
	 *	Description: 获取次客户所属部门负责人
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-6
	 * @param map
	 * @return
	 * BpmsParticipant[]
	 */
	public BpmsParticipant[] getManager(Map<?, ?> map) {
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IDepartmentService departmentService = (IDepartmentService) wac
				.getBean("departmentService");
		Department dept = departmentService.getDepartmentById(getDept(map).getId());
		return BpsUtils.queryBpmsParticipant(dept);
	}
	
	/**
	 * <p>
	 *	Description: 获取区域经理/销售管理组高级经理
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-6
	 * @param map
	 * @return
	 * BpmsParticipant[]
	 */
	public BpmsParticipant[] getAreaManager(Map<?, ?> map) {
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IDepartmentService departmentService = (IDepartmentService) wac
				.getBean("departmentService");
		Department dept = departmentService.getDepartmentById(getDept(map).getId());
		return BpsUtils.queryParentBpmsParticipant(dept);
	}
	/**
	 * <p>
	 *	Description: 获取大区总经理
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-6
	 * @param map
	 * @return
	 * BpmsParticipant[]
	 */
	public BpmsParticipant[] getLargeAreaManager(Map<?, ?> map) {
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IDepartmentService departmentService = (IDepartmentService) wac
				.getBean("departmentService");
		Department dept = getDept(map);
		//当归属部门级别为7 即营业部级别，则获取其上级的上级部门
		String sendCenterDept=(String)CacheManager.getInstance().getCache(ServerParameterCache.UUID).get("CUSTOMER_REPEATEWORKFLOW_SENDCENTER");
		if(DEPTLEVELDEF.equals(dept.getDeptLevel().toString())){
			Department department = departmentService.getDepartmentById(dept.getParentCode().getId());
			if (department != null) {
				return BpsUtils.queryParentBpmsParticipant(department);
			}
		}
		//当归属部门以派送中心结尾，则获取其上级部门
		if(dept.getDeptName().endsWith(sendCenterDept)){
			Department department = departmentService.getDepartmentById(dept.getId());
			if (department != null) {
				return BpsUtils.queryParentBpmsParticipant(department);
			}
		}
		return null;
	}
	
	/**
	 * <p>
	 *	Description: 获取事业部总裁
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-6
	 * @param map
	 * @return
	 * BpmsParticipant[]
	 */
	public BpmsParticipant[] getBusinessManager(Map<?, ?> map) {
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IDepartmentService departmentService = (IDepartmentService) wac
				.getBean("departmentService");
		Department dept = getDept(map);
		String sendCenterDept=(String)CacheManager.getInstance().getCache(ServerParameterCache.UUID).get("CUSTOMER_REPEATEWORKFLOW_SENDCENTER");
		//当归属部门为7 即营业部级别，则获取其上级的上级的上级部门
		if(DEPTLEVELDEF.equals(dept.getDeptLevel().toString())){
			Department department = departmentService.getDepartmentById(dept.getParentCode().getId());
			department = departmentService.getDepartmentById(department.getParentCode().getId());
			if (department != null) {
				return BpsUtils.queryParentBpmsParticipant(department);
			}
		}
		//当归属部门以派送中心结尾，则获取其上级的上级部门
		if(dept.getDeptName().endsWith(sendCenterDept)){
			Department department = departmentService.getDepartmentById(dept.getParentCode().getId());
			if (department != null) {
				return BpsUtils.queryParentBpmsParticipant(department);
			}
		}
		return null;
	}
	/**
	 * <p>
	 *	Description: 根据工作流信息的归属部门
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-6
	 * @param map
	 * @return
	 * Department
	 */
	public  Department getDept(Map<?, ?> map) {
		RepetitiveCustWorkFlowInfo info = new RepetitiveCustWorkFlowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE);
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME)
				.toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IRepeatedCustManager repeatedCustManager = (IRepeatedCustManager) wac
				.getBean("repeatedCustManager");
		IDepartmentService departmentService = (IDepartmentService) wac
				.getBean("departmentService");
			info = repeatedCustManager.findRepetitiveCustWorkFlowInfoByWorkNo(bizCode, processDefName);
		Department dept = departmentService.getDepartmentById(info
				.getDeptId());
		return dept;
	}
}
