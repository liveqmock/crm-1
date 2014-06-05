package com.deppon.crm.module.customer.bpsworkflow.branch;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.deppon.bpmsapi.module.client.util.BPMSConstant;
import com.deppon.crm.module.custrepeat.server.manager.IRepeatedCustManager;
import com.deppon.crm.module.custrepeat.shared.domain.RepetitiveCustWorkFlowInfo;
import com.deppon.crm.module.frameworkimpl.server.cache.ServerParameterCache;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.server.service.IEmployeeService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.server.context.WebApplicationContextHolder;

/**
 * 
* @ClassName: RepetitiveCustBranch 
* @Description: 疑似重复客户分支规则
* @author LiuY
* @date 2014-3-7 上午11:12:57 
*
 */
public class RepetitiveCustBranch {

	private static Logger logger = Logger.getLogger(RepetitiveCustBranch.class);
	private static final String PLATINUM="PLATINUM";
	private static final String DIAMOND = "DIAMOND";
	/**
	 * <p>
	 *	Description: 判断工作流起草部门是否是客户的归属部门
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-7
	 * @param map
	 * @return
	 * boolean
	 */
	public boolean isSameDept(Map<?, ?> map){
		RepetitiveCustWorkFlowInfo info = new RepetitiveCustWorkFlowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
		// 获得流程定义名称
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME).toString();
		// 获得WebApplicationContext
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IRepeatedCustManager repeatedCustManager = (IRepeatedCustManager)wac.getBean("repeatedCustManager");
		IEmployeeService empService = (IEmployeeService) wac.getBean("employeeService");
		//获取工作流详情信息
		info = repeatedCustManager.findRepetitiveCustWorkFlowInfoByWorkNo(bizCode, processDefName);
		String deptId = "" ;
		if(null != info){
			//获取申请人所在部门信息
			Employee currUser = empService.getEmpById(info.getProposer());
			logger.error("疑似工作流："+currUser.getEmpCode());
			logger.error("疑似工作流："+currUser.getId());
			logger.error("疑似工作流："+currUser.getDeptId());
			deptId = currUser.getDeptId().getId().toString();	
			logger.error("----------------------------------------- end");
		}
		//判断工作流起草部门是否是客户的归属部门
		if(null != info && info.getDeptId().equals(deptId)){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	* @Title: isBelongToSendCenter 
	* @Description: 判断归属部门是否是派送中心
	* @author LiuY 
	* @param map
	* @return boolean
	* @throws
	 */
	public boolean isBelongToSendCenter(Map<?, ?> map){
		RepetitiveCustWorkFlowInfo info = new RepetitiveCustWorkFlowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
		// 获得流程定义名称
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME).toString();
		// 获得WebApplicationContext
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IRepeatedCustManager repeatedCustManager = (IRepeatedCustManager)wac.getBean("repeatedCustManager");
		IDepartmentService departmentService = (IDepartmentService) wac
				.getBean("departmentService");
		//获取工作流详情信息
		info = repeatedCustManager.findRepetitiveCustWorkFlowInfoByWorkNo(bizCode, processDefName);
		String sendCenterDept=(String)CacheManager.getInstance().getCache(ServerParameterCache.UUID).get("CUSTOMER_REPEATEWORKFLOW_SENDCENTER");
		if(null != info){
			//获取申请人所在部门信息,如果部门为6，即区域级别，则返回true
			Department dept = departmentService.getDepartmentById(info
					.getDeptId());
			if(null != dept && info.getDeptName().endsWith(sendCenterDept)){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * <p>
	 *	Description: 判断归属部门是否是400销售管理组
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-12
	 * @param map
	 * @return
	 * boolean
	 */
	public boolean isSalesManagerDept(Map<?, ?> map){
		RepetitiveCustWorkFlowInfo info = new RepetitiveCustWorkFlowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
		// 获得流程定义名称
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME).toString();
		// 获得WebApplicationContext
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IRepeatedCustManager repeatedCustManager = (IRepeatedCustManager)wac.getBean("repeatedCustManager");
		info = repeatedCustManager.findRepetitiveCustWorkFlowInfoByWorkNo(bizCode, processDefName);
		String esalesDept=(String)CacheManager.getInstance().getCache(ServerParameterCache.UUID).get("CUSTOMER_REPEATEWORKFLOW_ESALESDEPT");
		if(null != info && esalesDept.equals(info.getDeptName())){
			return true;
		}
		return false;
	}
	/**
	 * <p>
	 *	Description: 判断是否是铂金用户
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-12
	 * @param map
	 * @return
	 * boolean
	 */
	public boolean isPlatinumCust(Map<?, ?> map){
		RepetitiveCustWorkFlowInfo info = new RepetitiveCustWorkFlowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
		// 获得流程定义名称
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME).toString();
		// 获得WebApplicationContext
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IRepeatedCustManager repeatedCustManager = (IRepeatedCustManager)wac.getBean("repeatedCustManager");
		info = repeatedCustManager.findRepetitiveCustWorkFlowInfoByWorkNo(bizCode, processDefName);
		if(null != info && PLATINUM.equals(info.getCustomerLevel())){
			return true;
		}
		return false;
	}
	/**
	 * <p>
	 *	Description: 判断是否是钻石用户
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-12
	 * @param map
	 * @return
	 * boolean
	 */
	public boolean isDiamondCust(Map<?, ?> map){
		RepetitiveCustWorkFlowInfo info = new RepetitiveCustWorkFlowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
		// 获得流程定义名称
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME).toString();
		// 获得WebApplicationContext
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IRepeatedCustManager repeatedCustManager = (IRepeatedCustManager)wac.getBean("repeatedCustManager");
		info = repeatedCustManager.findRepetitiveCustWorkFlowInfoByWorkNo(bizCode, processDefName);
		if(null != info && DIAMOND.equals(info.getCustomerLevel())){
			return true;
		}
		return false;
	}
}
