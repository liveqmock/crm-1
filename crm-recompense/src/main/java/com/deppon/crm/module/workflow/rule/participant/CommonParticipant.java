package com.deppon.crm.module.workflow.rule.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import com.deppon.bpms.module.shared.domain.BpmsParticipant;
import com.deppon.bpmsapi.module.client.util.BPMSConstant;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.shared.domain.Overpay;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.UserRoleDeptRelation;
import com.deppon.crm.module.servicerecovery.server.service.IServiceRecoveryService;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.workflow.server.manager.ISignetManagerManager;
import com.deppon.crm.module.workflow.server.service.INormalClaimService;
import com.deppon.crm.module.workflow.server.util.NormalClaimUtil;
import com.deppon.crm.module.workflow.shared.domain.NormalClaim;
import com.deppon.crm.module.workflow.shared.domain.SignetManager;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.context.WebApplicationContextHolder;
import com.eos.system.utility.StringUtil;


/**
 * <pre>
 * 功能:通用规则-选人规则
 * 作者：andy
 * 日期：2013-7-30 上午9:42:36
 * </pre>
 */
public class CommonParticipant {
	// 日志记录对象
	private static Logger log = Logger.getLogger(CommonParticipant.class);

	//MAP 
	//PMSConstant.PROCESS_INST_ID            流程实例ID
	//BPMSConstant.USER_ID                   登录ID
	//BPMSConstant.BIZCODE                   业务编码
	//BPMSConstant.APPLYER_CODE              申请人工号
	
	//typeCode： PART_POST（岗位）， PART_PERSON（人员）， 
	//          PART_MANAGER（ 机构负责人）， PART_ORG（机构）， 
	//          PART_ORG_POST（机构下的岗位）， PART_ORG_STAFF（机构直管人员）
	
	//理赔研究小组
	public static final String RECOMPENSEDEPTCODE = "W010002010804";
	//快递市场营销组
	public static final String EXPRESS_MARKETING_GROUP = "W01221904";
	
	//经营本部
	public static final String SOUTHCHINAOPERATE   = "W0133";  //华南经营本部
	public static final String CENTRALCHINAOPERATE = "W0132";  //华中经营本部
	public static final String NORTHCHINAOPERATE   = "W0131";  //华北经营本部
	
	public static final String DEPTLEVELBUSINESS     = "4";    // 事业部级别
	public static final String DEPTLEVELFORLARGEAREA = "5";    // 大区级别
	public static final String DEPTLEVELFORAREA      = "6";    // 区域级别
	public static final String DEPTLEVELDEF          = "7";    // 营业部、派送部 级别
	
	public static final String EXPRESSBRANCH         = "快递分部";
	public static final String EXPRESSLARGEAREA      = "快递大区";
	public static final String LARGEAREA             = "大区";
	public static final String BUSINESSDEPARTMENT    = "事业部";

	/**
	 * 
	 * <pre>
	 * 方法体说明：获取区域经理
	 * 作者：andy
	 * 日期： 2013-8-14 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getAreaManager(Map<?, ?> map) {
		Department dept = getDept(map);
		if(dept != null) {
			if(this.DEPTLEVELDEF.equals(dept.getDeptLevel()+"")) {
				return getBpmsParticipant(dept);
			}else if(this.DEPTLEVELFORAREA.equals(dept.getDeptLevel()+"")) {
				return queryBpmsParticipant(dept);
			}
		}
		return null;
	}
	
	private Department getDept(Map<?, ?> map) {
		List<BpmsParticipant> list = new ArrayList<BpmsParticipant>();
		String bizCode = map.get(BPMSConstant.BIZCODE).toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		INormalClaimService normalClaimService = (INormalClaimService) wac
				.getBean("normalClaimService");
		NormalClaim normalClaim = normalClaimService.getNormalClaimByWorkflowNo(bizCode);
		if(normalClaim != null) {
			IDepartmentService departService = getDeptService();
			Department department = departService.getDepartmentById(normalClaim.getReportDept());
			return department;
		}
		return null;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：获取大区经理
	 * 作者：andy
	 * 日期： 2013-8-14 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getLargeAreaManager(Map<?, ?> map) {
		IDepartmentService departService = getDeptService();
		Department department = departService.getDepartmentById(getDept(map).getParentCode().getId());
		if(department != null) {
			if(this.DEPTLEVELFORAREA.equals(department.getDeptLevel()+"")) {
				return getBpmsParticipant(department);
			}else if(this.DEPTLEVELFORLARGEAREA.equals(department.getDeptLevel()+"")) {
				return queryBpmsParticipant(department);
			}
		}
		return null;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：获取快递大区经理
	 * 作者：andy
	 * 日期： 2013-8-14 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getExpressDeliveryManager(Map<?, ?> map) {
		String bizCode = map.get(BPMSConstant.BIZCODE).toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		INormalClaimService normalClaimService = (INormalClaimService) wac
				.getBean("normalClaimService");
		NormalClaim normalClaim = normalClaimService.getNormalClaimByWorkflowNo(bizCode);
		if(normalClaim != null) {
			String reportDeptId = normalClaim.getReportDept();
			IDepartmentService departmentService = (IDepartmentService) wac
					.getBean("departmentService");
			List<Department> deptList = departmentService.getAllParentDeptByDeptId(reportDeptId);
			for(int i = 0 ; i < deptList.size(); i++) {
				Department department = deptList.get(i);
				if(department != null && department.getDeptName().endsWith(this.EXPRESSLARGEAREA)) {
					return queryBpmsParticipant(department);
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：获取快递分部总
	 * 作者：andy
	 * 日期： 2013-8-14 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getComExpressDeliveryManager(Map<?, ?> map) {
		String bizCode = map.get(BPMSConstant.BIZCODE).toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		INormalClaimService normalClaimService = (INormalClaimService) wac
				.getBean("normalClaimService");
		NormalClaim normalClaim = normalClaimService.getNormalClaimByWorkflowNo(bizCode);
		if(normalClaim != null) {
			String reportDeptId = normalClaim.getReportDept();
			IDepartmentService departmentService = (IDepartmentService) wac
					.getBean("departmentService");
			List<Department> deptList = departmentService.getAllParentDeptByDeptId(reportDeptId);
			for(int i = 0 ; i < deptList.size(); i++) {
				Department department = deptList.get(i);
				if(department != null && department.getDeptName().endsWith(this.EXPRESSBRANCH)) {
					return queryBpmsParticipant(department);
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：获取试点城市大区总经理
	 * 作者：andy
	 * 日期： 2013-8-14 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getExpressDeliveryAreaManager(Map<?, ?> map) {
		String bizCode = map.get(BPMSConstant.BIZCODE).toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		INormalClaimService normalClaimService = (INormalClaimService) wac
				.getBean("normalClaimService");
		NormalClaim normalClaim = normalClaimService.getNormalClaimByWorkflowNo(bizCode);
		if(normalClaim != null) {
			String reportDeptId = normalClaim.getReportDept();
			IDepartmentService departmentService = (IDepartmentService) wac
					.getBean("departmentService");
			List<Department> deptList = departmentService.getAllParentDeptByDeptId(reportDeptId);
			for(int i = 0 ; i < deptList.size(); i++) {
				Department department = deptList.get(i);
				if(department != null && department.getDeptName().endsWith(this.EXPRESSBRANCH)) {
					if(i+1 <= deptList.size()) {
						return queryBpmsParticipant(deptList.get(i+1));  // 取“快递分部”的上级部门
					}else{
						IDepartmentService departService = getDeptService();
						department = departService.getDepartmentById(department.getId());
						return getBpmsParticipant(department);
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：获取试点城市事业部总裁
	 * 作者：andy
	 * 日期： 2013-8-14 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getExpressDeliveryBusManager(Map<?, ?> map) {
		String bizCode = map.get(BPMSConstant.BIZCODE).toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		INormalClaimService normalClaimService = (INormalClaimService) wac
				.getBean("normalClaimService");
		NormalClaim normalClaim = normalClaimService.getNormalClaimByWorkflowNo(bizCode);
		if(normalClaim != null) {
			String reportDeptId = normalClaim.getReportDept();
			IDepartmentService departmentService = (IDepartmentService) wac
					.getBean("departmentService");
			List<Department> deptList = departmentService.getAllParentDeptByDeptId(reportDeptId);
			for(int i = 0 ; i < deptList.size(); i++) {
				Department department = deptList.get(i);
				if(department != null && department.getDeptName().endsWith(this.BUSINESSDEPARTMENT)) {
					return queryBpmsParticipant(department);
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：获取事业部总裁
	 * 作者：andy
	 * 日期： 2013-8-14 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getBusinessManager(Map<?, ?> map) {
		IDepartmentService departService = getDeptService();
		Department department = departService.getDepartmentById(getDept(map).getParentCode().getId());
		department = departService.getDepartmentById(department.getParentCode().getId());
		if(department != null) {
			if(this.DEPTLEVELFORLARGEAREA.equals(department.getDeptLevel()+"")) {
				return getBpmsParticipant(department);
			}else if(this.DEPTLEVELBUSINESS.equals(department.getDeptLevel()+"")) {
				return queryBpmsParticipant(department);
			}
		}
		return null;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：获取理赔研究组负责人
	 * 作者：andy
	 * 日期： 2013-8-14 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getClaimStudyGroup(Map<?, ?> map) {
		List<BpmsParticipant> list = new ArrayList<BpmsParticipant>();
		IDepartmentService departService = getDeptService();
		Department department = departService.getDeptByCode(this.RECOMPENSEDEPTCODE);
		return queryBpmsParticipant(department);
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：获取快递市场营销组负责人
	 * 作者：andy
	 * 日期： 2014-2-24 上午9:47:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getExpressMarketingGroup(Map<?, ?> map) {
		List<BpmsParticipant> list = new ArrayList<BpmsParticipant>();
		IDepartmentService departService = getDeptService();
		Department department = departService.getDeptByCode(this.EXPRESS_MARKETING_GROUP);
		return queryBpmsParticipant(department);
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：获取经营本部负责人
	 * 作者：andy
	 * 日期： 2013-8-14 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getOperationManager(Map<?, ?> map) {
		List<BpmsParticipant> list = new ArrayList<BpmsParticipant>();
		String processInstId = map.get(BPMSConstant.PROCESS_INST_ID).toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		INormalClaimService normalClaimService = (INormalClaimService) wac
				.getBean("normalClaimService");
		NormalClaim normalClaim = normalClaimService.getNormalClaim(Long.parseLong(processInstId));
		if(normalClaim != null) {
			//所属区域 normalClaim.getAreaId()
			IDepartmentService departService = getDeptService();
			List<Department> deptList = departService.getAllParentDeptByDeptId(normalClaim.getAreaId());
			for(int i = 0; i < deptList.size(); i++) {
				Department department = (Department)deptList.get(i);
				if(department != null) {
					if(department.getDeptCode().equalsIgnoreCase(this.SOUTHCHINAOPERATE) 
							|| department.getDeptCode().equalsIgnoreCase(this.CENTRALCHINAOPERATE) 
							||department.getDeptCode().equalsIgnoreCase(this.NORTHCHINAOPERATE)) {
						return queryBpmsParticipant(department);
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：获取印章管理员
	 * 作者：andy
	 * 日期： 2013-8-14 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getSignetManager(Map<?, ?> map) {
		List<BpmsParticipant> list = new ArrayList<BpmsParticipant>();
		String processInstId = map.get(BPMSConstant.PROCESS_INST_ID).toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		INormalClaimService normalClaimService = (INormalClaimService) wac
				.getBean("normalClaimService");
		NormalClaim normalClaim = normalClaimService.getNormalClaim(Long.parseLong(processInstId));
		if(normalClaim != null) {
			String areaId = normalClaim.getAreaId();
			ISignetManagerManager signetManagerManager = (ISignetManagerManager) wac
					.getBean("signetManagerManager");
			IDepartmentService departService = getDeptService();
			Department department = departService.getDepartmentById(areaId);
			SignetManager signetManager = null;
			if(this.DEPTLEVELFORLARGEAREA.equals(department.getDeptLevel()+"")) {   //所属区域为大区级别
				signetManager = signetManagerManager.getSignetManagerByDeptId(department.getParentCode().getId());
			}else if(this.DEPTLEVELBUSINESS.equals(department.getDeptLevel()+"")) { //所属区域为事业部级别
				signetManager = signetManagerManager.getSignetManagerByDeptId(department.getId());
			}else if(this.DEPTLEVELFORAREA.equals(department.getDeptLevel()+"")) {  //区域级别
				department = departService.getDepartmentById(department.getParentCode().getId());
				signetManager = signetManagerManager.getSignetManagerByDeptId(department.getParentCode().getId());
			}
			
			if(signetManager != null) {
				BpmsParticipant bp = new BpmsParticipant();
				bp.setId(signetManager.getEmpCode());
				bp.setName(signetManager.getEmpName());
				bp.setTypeCode(BPMSConstant.PART_PERSON);
				list.add(bp);
				return list.toArray(new BpmsParticipant[list.size()]);
			}
		}
		return null;
	}
	
	private BpmsParticipant[] queryBpmsParticipant(Department department) {
		List<BpmsParticipant> list = new ArrayList<BpmsParticipant>();
		if(department != null) {
			BpmsParticipant bp = new BpmsParticipant();
			bp.setId(department.getStandardCode());
			bp.setName(department.getDeptName());
			bp.setTypeCode(BPMSConstant.PART_MANAGER);
			list.add(bp);
			return list.toArray(new BpmsParticipant[list.size()]);
		}
		return null;
	}
	
	private BpmsParticipant[] getBpmsParticipant(Department department) {
		List<BpmsParticipant> list = new ArrayList<BpmsParticipant>();
		if(department != null) {
			BpmsParticipant bp = new BpmsParticipant();
			bp.setId(department.getParentCode().getStandardCode());
			bp.setName(department.getParentCode().getDeptName());
			bp.setTypeCode(BPMSConstant.PART_MANAGER);
			list.add(bp);
			return list.toArray(new BpmsParticipant[list.size()]);
		}
		return null;
	}
	
	private IDepartmentService getDeptService() {
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IDepartmentService departmentService = (IDepartmentService) wac
				.getBean("departmentService");
		return departmentService;
	}
	
	
	
	
	
	/******************************   多赔start   ******************************/
	/**
	 * 
	 * <pre>
	 * 方法体说明：区域经理
	 * 作者：andy
	 * 日期： 2014-1-8  15:17:32
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getOverpayAreaManager(Map<?, ?> map) {
		User user = (User) UserContext.getCurrentUser();
		// 申请部门（营业部5和7、点部 level 6和7）
		String deptId = user.getEmpCode().getDeptId().getId();
		IDepartmentService departService = getDeptService();
		Department department = departService.getDepartmentById(deptId);
		if(department != null) {
			if(this.DEPTLEVELFORAREA.equals(department.getDeptLevel()+"")) {    // 6-区域级别
				return queryBpmsParticipant(department);
			}else if(this.DEPTLEVELDEF.equals(department.getDeptLevel()+"")) {  // 7-营业部、派送部 级别
				return getBpmsParticipant(department);
			}else {
				// 5-大区级别  （香港营业部，暂不处理）
			}
		}
		return null;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：大区总经理
	 * 作者：andy
	 * 日期： 2014-1-8  15:17:32
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getOverpayLargeAreaManager(Map<?, ?> map) {
		User user = (User) UserContext.getCurrentUser();
		// 申请部门（营业部5和7、点部 level 6和7）
		String deptId = user.getEmpCode().getDeptId().getId();
		IDepartmentService departService = getDeptService();
		Department department = departService.getDepartmentById(deptId);
		if(department != null) {
			if(this.DEPTLEVELFORAREA.equals(department.getDeptLevel()+"")) {    // 6-区域级别
				return getBpmsParticipant(department);
			}else if(this.DEPTLEVELDEF.equals(department.getDeptLevel()+"")) {  // 7-营业部、派送部 级别
				department = departService.getDepartmentById(department.getParentCode().getId());
				return getBpmsParticipant(department);
			}else {
				// 5-大区级别  （香港营业部，暂不处理）
			}
		}
		return null;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：获取事业部总裁
	 * 作者：andy
	 * 日期： 2013-8-14 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getOverpayBusinessManager(Map<?, ?> map) {
		String bizCode = map.get(BPMSConstant.BIZCODE).toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		RecompenseService recompenseService = (RecompenseService) wac.
				getBean("recompenseService");
		
		Overpay overPay = recompenseService.getOverPay(bizCode);
		if(overPay != null) {
			String deptId = overPay.getOverpayBu().getId(); // 事业部
			IDepartmentService departService = getDeptService();
			Department department = departService.getDepartmentById(deptId);
			return queryBpmsParticipant(department);
		}
		return null;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：获取经营本部负责人
	 * 作者：andy
	 * 日期： 2013-8-14 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getOverpayOperationManager(Map<?, ?> map) {
		String bizCode = map.get(BPMSConstant.BIZCODE).toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		RecompenseService recompenseService = (RecompenseService) wac.
				getBean("recompenseService");
		
		Overpay overPay = recompenseService.getOverPay(bizCode);
		if(overPay != null) {
			String deptId = overPay.getOverpayBu().getId(); // 事业部
			IDepartmentService departService = getDeptService();
			Department department = departService.getDepartmentById(deptId);
			return getBpmsParticipant(department);
		}
		return null;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：获取事业部理赔专员
	 * 作者：andy
	 * 日期： 2013-8-14 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getOverpayClaimsSpecialist(Map<?, ?> map) {
		String bizCode = map.get(BPMSConstant.BIZCODE).toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		RecompenseService recompenseService = (RecompenseService) wac.
				getBean("recompenseService");
		RecompenseApplication recApplication =  recompenseService.getRecompense(bizCode);
		if(recApplication != null) {
			String deptId = recApplication.getDeptId();   //所属区域
			List<UserRoleDeptRelation> urdrList = recompenseService.getUserRoleDeptRelationByDeptId(deptId);
			if(urdrList.size() > 0) {
				UserRoleDeptRelation urdr = urdrList.get(0);
				if(urdr != null) {
					List<BpmsParticipant> list = new ArrayList<BpmsParticipant>();
					BpmsParticipant bp = new BpmsParticipant();
					bp.setId(urdr.getUser().getEmpCode().getEmpCode());
					bp.setName(urdr.getUser().getEmpCode().getEmpName());
					bp.setTypeCode(BPMSConstant.PART_PERSON);
					list.add(bp);
					return list.toArray(new BpmsParticipant[list.size()]);
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：获取印章管理员
	 * 作者：andy
	 * 日期： 2013-8-14 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getOverpaySignetManager(Map<?, ?> map) {
		List<BpmsParticipant> list = new ArrayList<BpmsParticipant>();
		String bizCode = map.get(BPMSConstant.BIZCODE).toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		RecompenseService recompenseService = (RecompenseService) wac.
				getBean("recompenseService");
		RecompenseApplication recApplication =  recompenseService.getRecompense(bizCode);
		if(recApplication != null) {
			String areaId = recApplication.getDeptId();  // 所属区域
			ISignetManagerManager signetManagerManager = (ISignetManagerManager) wac
					.getBean("signetManagerManager");
			IDepartmentService departService = getDeptService();
			Department department = departService.getDepartmentById(areaId);
			SignetManager signetManager = null;
			if(this.DEPTLEVELFORLARGEAREA.equals(department.getDeptLevel()+"")) {   //所属区域为大区级别
				signetManager = signetManagerManager.getSignetManagerByDeptId(department.getParentCode().getId());
			}else if(this.DEPTLEVELBUSINESS.equals(department.getDeptLevel()+"")) { //所属区域为事业部级别
				signetManager = signetManagerManager.getSignetManagerByDeptId(department.getId());
			}else if(this.DEPTLEVELFORAREA.equals(department.getDeptLevel()+"")) {  //区域级别
				department = departService.getDepartmentById(department.getParentCode().getId());
				signetManager = signetManagerManager.getSignetManagerByDeptId(department.getParentCode().getId());
			}
			
			if(signetManager != null) {
				BpmsParticipant bp = new BpmsParticipant();
				bp.setId(signetManager.getEmpCode());
				bp.setName(signetManager.getEmpName());
				bp.setTypeCode(BPMSConstant.PART_PERSON);
				list.add(bp);
				return list.toArray(new BpmsParticipant[list.size()]);
			}
		}
		return null;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：获取快递大区经理
	 * 作者：andy
	 * 日期： 2013-8-14 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getOverpayExpressDeliveryManager(Map<?, ?> map) {
		String bizCode = map.get(BPMSConstant.BIZCODE).toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		RecompenseService recompenseService = (RecompenseService) wac.
				getBean("recompenseService");
		
		Overpay overPay = recompenseService.getOverPay(bizCode);
		if(overPay != null) {
			String applyDeptId = overPay.getApplyDeptId();
			IDepartmentService departmentService = (IDepartmentService) wac
					.getBean("departmentService");
			List<Department> deptList = departmentService.getAllParentDeptByDeptId(applyDeptId);
			for(int i = 0; i < deptList.size(); i++) {
				Department deptment = deptList.get(i);
				if(deptment != null && deptment.getDeptName().endsWith(this.EXPRESSLARGEAREA)) {
					return queryBpmsParticipant(deptment);
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：获取快递分部总
	 * 作者：andy
	 * 日期： 2013-8-14 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getOverpayComExpressDeliveryManager(Map<?, ?> map) {
		String bizCode = map.get(BPMSConstant.BIZCODE).toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		RecompenseService recompenseService = (RecompenseService) wac.
				getBean("recompenseService");
		
		Overpay overPay = recompenseService.getOverPay(bizCode);
		if(overPay != null) {
			String applyDeptId = overPay.getApplyDeptId();
			IDepartmentService departmentService = (IDepartmentService) wac
					.getBean("departmentService");
			List<Department> deptList = departmentService.getAllParentDeptByDeptId(applyDeptId);
			for(int i = 0; i < deptList.size(); i++) {
				Department deptment = deptList.get(i);
				if(deptment != null && deptment.getDeptName().endsWith(this.EXPRESSBRANCH)) {
					return queryBpmsParticipant(deptment);
				}
			}
		}
		return null;
	}
	
	/******************************   多赔end   ******************************/
	
	
	/******************************   服务补救start   ******************************/
	/**
	 * 
	 * <pre>
	 * 方法体说明：服务补救申请人上级审批
	 * 作者：andy
	 * 日期： 2013-8-14 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getServiceManager(Map<?, ?> map) {
		User user = (User) UserContext.getCurrentUser();
		String currDeptId = user.getEmpCode().getDeptId().getId();  // 申请人部门
		IDepartmentService departService = getDeptService();
		Department department = departService.getDepartmentById(currDeptId);
		if(department != null) {
			return getBpmsParticipant(department);
		}
		return null;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：获取当地财务部
	 * 作者：andy
	 * 日期： 2013-8-14 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getLocalFinancialDept(Map<?, ?> map) {
		String processId = map.get(BPMSConstant.PROCESS_INST_ID).toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IServiceRecoveryService serviceRecoveryService = (IServiceRecoveryService) wac.
				getBean("serviceRecoveryService");
		ServiceRecovery serviceRecovery = serviceRecoveryService.getServiceRecoveryByOaWorkFlowNum(processId);
		if(serviceRecovery != null) {
			String financeDeptId = serviceRecovery.getFinanceDept();
			IDepartmentService departService = getDeptService();
			Department department = departService.getDepartmentById(financeDeptId);
			return queryBpmsParticipant(department);
		}
		return null;
	}
	/******************************   服务补救end   ******************************/
}
