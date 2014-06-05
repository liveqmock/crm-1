package com.deppon.crm.module.customer.bpsworkflow.participant;

import java.util.Map;

import org.springframework.web.context.WebApplicationContext;

import com.deppon.bpms.module.shared.domain.BpmsParticipant;
import com.deppon.bpmsapi.module.client.util.BPMSConstant;
import com.deppon.crm.module.customer.bpsworkflow.util.BpsUtils;
import com.deppon.crm.module.customer.server.manager.IBaseDataManager;
import com.deppon.crm.module.keycustomer.server.manager.IKeyCustomerManager;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerWorkflowInfo;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.context.WebApplicationContextHolder;
/**
 * 
 * <p>
 * Description:大客户参与者规则<br />
 * </p>
 * @title KeyCustomerParticipant.java
 * @package com.deppon.crm.module.customer.bpsworkflow.participant 
 * @author 106138
 * @version 0.1 2014年4月18日
 */
public class KeyCustomerParticipant {

	
	/**
	 * <p>
	 *	Description: 获取区域经理
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
		Department department = departmentService.getDepartmentById(getDept(map)
				.getParentCode().getId());
		if (department != null) {
			return BpsUtils.queryParentBpmsParticipant(department);
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
		Department department = departmentService.getDepartmentById(getDept(map)
				.getParentCode().getId());
		department = departmentService.getDepartmentById(department.getParentCode()
				.getId());
		if (department != null) {
			return BpsUtils.queryParentBpmsParticipant(department);
		}
		return null;
	}
	/**
	 * <p>
	 *	Description: 获取事业部营销管理组负责人
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-6
	 * @param map
	 * @return
	 * BpmsParticipant[]
	 */
	public BpmsParticipant[] getBussDivesionManager(Map<?, ?> map){
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IBaseDataManager baseDataManager = (IBaseDataManager) wac
				.getBean("baseDataManager");
		Department department =baseDataManager.getBusinessDivesionOfficeMarketingGroup(getDept(map));
		if (department != null) {
			return BpsUtils.queryParentBpmsParticipant(department);
		}
		return null;
	}
	
	/**
	 * <p>
	 *	Description: 根据工作流信息的归属营业部
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-6
	 * @param map
	 * @return
	 * Department
	 */
	public  Department getDept(Map<?, ?> map) {
		KeyCustomerWorkflowInfo info = new KeyCustomerWorkflowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE);
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IKeyCustomerManager keyCustomerManager = (IKeyCustomerManager) wac
				.getBean("keyCustomerManager");
		IDepartmentService departmentService = (IDepartmentService) wac
				.getBean("departmentService");
			info = keyCustomerManager.findWorkflowInfoByBusino(
					bizCode);
		Department dept = departmentService.getDepartmentById(info
				.getDeptId());
		return dept;
	}

}
