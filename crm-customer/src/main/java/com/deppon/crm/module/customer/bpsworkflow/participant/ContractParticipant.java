package com.deppon.crm.module.customer.bpsworkflow.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.deppon.bpms.module.shared.domain.BpmsParticipant;
import com.deppon.bpmsapi.module.client.util.BPMSConstant;
import com.deppon.crm.module.customer.bpsworkflow.util.BpsConstant;
import com.deppon.crm.module.customer.server.manager.IContractWorkflowManager;
import com.deppon.crm.module.customer.server.service.IContractService;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.ContractCondition;
import com.deppon.crm.module.customer.shared.domain.ContractWorkflowInfo;
import com.deppon.crm.module.customer.shared.exception.ContractException;
import com.deppon.crm.module.customer.shared.exception.ContractExceptionType;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.context.WebApplicationContextHolder;
import com.eos.system.utility.StringUtil;

/**
 * <pre>
 * 功能:通用规则-选人规则
 * 作者：andy
 * 日期：2013-11-25 上午9:42:36
 * </pre>
 */
public class ContractParticipant {
	// 日志记录对象
	private static Logger log = Logger.getLogger(ContractParticipant.class);
	// MAP
	// PMSConstant.PROCESS_INST_ID 流程实例ID
	// BPMSConstant.USER_ID 登录ID
	// BPMSConstant.BIZCODE 业务编码
	// BPMSConstant.APPLYER_CODE 申请人工号

	// typeCode： PART_POST（岗位）， PART_PERSON（人员），
	// PART_MANAGER（ 机构负责人）， PART_ORG（机构），
	// PART_ORG_POST（机构下的岗位）， PART_ORG_STAFF（机构直管人员）
	// 经营本部
	public static final String SOUTHCHINAOPERATE = "W0133"; // 华南经营本部
	
	

	public static final String CENTRALCHINAOPERATE = "W0132"; // 华中经营本部
	public static final String NORTHCHINAOPERATE = "W0131"; // 华北经营本部
	public static final String WFS_SUBCOM_FINADEPT = "WFS_SUBCOM_FINADEPT"; // 华北经营本部

	private BpmsParticipant[] queryBpmsParticipant(Department department) {
		List<BpmsParticipant> list = new ArrayList<BpmsParticipant>();
		if (department != null) {
			BpmsParticipant bp = new BpmsParticipant();
			bp.setId(department.getStandardCode());
			bp.setName(department.getDeptName());
			bp.setTypeCode(BPMSConstant.PART_MANAGER);
			list.add(bp);
			log.error("------------------------------：下一节点审批（部门标杆编码）："
					+ department.getStandardCode());
			return list.toArray(new BpmsParticipant[list.size()]);
		}
		return null;
	}

	private BpmsParticipant[] queryParentBpmsParticipant(Department department) {
		List<BpmsParticipant> list = new ArrayList<BpmsParticipant>();
		if (department != null) {
			BpmsParticipant bp = new BpmsParticipant();
			WebApplicationContext wac = WebApplicationContextHolder
					.getWebApplicationContext();
		
			IDepartmentService departmentService = (IDepartmentService) wac
					.getBean("departmentService");
			Department parentDept=departmentService.getDepartmentById(department.getParentCode().getId());
			bp.setId(parentDept.getStandardCode());
			bp.setName(parentDept.getDeptName());
			bp.setTypeCode(BPMSConstant.PART_MANAGER);
			list.add(bp);
			log.error("------------------------------：下一节点审批（部门标杆编码）："
					+ parentDept.getStandardCode());
			return list.toArray(new BpmsParticipant[list.size()]);
		}
		return null;
	}

	/******************** 月结客户签订申请（零担） ********************/

	/**
	 * 
	 * <pre>
	 * 方法体说明：财务部高级经理
	 * 作者：andy
	 * 日期： 2013-11-25 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getFinanceSeniorManager(Map<?, ?> map) {
		ContractWorkflowInfo info = new ContractWorkflowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE);
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME)
				.toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IContractWorkflowManager contractWorkflowManager = (IContractWorkflowManager) wac
				.getBean("contractWorkflowManager");

		info = contractWorkflowManager.findContractWorkflowInfoByWorkNo(
				bizCode, processDefName);

		if (StringUtil.isNotEmpty(info.getSignCompany())) {
			List<BpmsParticipant> list = new ArrayList<BpmsParticipant>();
			BpmsParticipant bp = new BpmsParticipant();
			String contractNumber = info.getContractNumber();
			IContractService contractService = (IContractService) wac
					.getBean("contractService");
			ContractCondition con = new ContractCondition();
			con.setContractNum(contractNumber);
			List<Contract> contracts = contractService.searchContract(con, 0,
					Integer.MAX_VALUE);
			if (null == contracts || 0 >= contracts.size()
					|| null == contracts.get(0).getDept()) {
				throw new ContractException(ContractExceptionType.Data_Error);
			}
			String companyName = contracts.get(0).getDept().getCompanyName();
			bp.setId(companyName + "#"
					+ ContractParticipant.WFS_SUBCOM_FINADEPT);

			bp.setName("财务部负责人");
			bp.setTypeCode(BPMSConstant.PART_BY_MAPPING);
			list.add(bp);
			log.error(list.toArray(new BpmsParticipant[list.size()]));
			return list.toArray(new BpmsParticipant[list.size()]);
		}

		return null;
	}

	/**
	 * 
	 * <pre>
	 * 方法体说明：区域经理
	 * 作者：andy
	 * 日期： 2013-11-25 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getAreaManager(Map<?, ?> map) {
		ContractWorkflowInfo info = new ContractWorkflowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE);
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME)
				.toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IContractWorkflowManager contractWorkflowManager = (IContractWorkflowManager) wac
				.getBean("contractWorkflowManager");
		IDepartmentService departmentService = (IDepartmentService) wac
				.getBean("departmentService");
			info = contractWorkflowManager.findContractWorkflowInfoByWorkNo(
					bizCode, processDefName);
		Department dept = departmentService.getDeptByStandardCode(info
				.getContrctAscriptionDept());
		return queryParentBpmsParticipant(dept);
	}

	/**
	 * 
	 * <pre>
	 * 方法体说明：零担大区总经理
	 * 作者：andy
	 * 日期： 2013-11-25 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getLargeAreaManager(Map<?, ?> map) {
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IDepartmentService departmentService = (IDepartmentService) wac
				.getBean("departmentService");
		Department department = departmentService.getDepartmentById(getDept(map)
				.getParentCode().getId());
		if (department != null) {
			return queryParentBpmsParticipant(department);
		}
		return null;
	}

	/**
	 * 
	 * <pre>
	 * 方法体说明：事业部总裁
	 * 作者：andy
	 * 日期： 2013-11-25 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
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
			return queryParentBpmsParticipant(department);
		}
		return null;
	}

	/**
	 * 
	 * <pre>
	 * 方法体说明：事业部合同管理专员
	 * 作者：andy
	 * 日期： 2013-11-25 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getContractManager(Map<?, ?> map) {
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IDepartmentService departmentService = (IDepartmentService) wac
				.getBean("departmentService");
		Department department = departmentService.getDepartmentById(getDept(map)
				.getParentCode().getId());
		department = departmentService.getDepartmentById(department.getParentCode()
				.getId());
		List<BpmsParticipant> list = new ArrayList<BpmsParticipant>();
		if (department != null) {
			BpmsParticipant bp = new BpmsParticipant();
			Department parentDept=departmentService.getDepartmentById(department.getParentCode().getId());
			bp.setId(parentDept.getStandardCode() + "#"
					+ BpsConstant.WFS_CONTRACTAPP_GROUP);
			bp.setName(BpsConstant.CONTRACT_MANAGER);
			bp.setTypeCode(BPMSConstant.PART_BY_MAPPING);
			list.add(bp);
			log.error("------------------------------：下一节点审批（部门标杆编码）："
					+ parentDept.getStandardCode());
			return list.toArray(new BpmsParticipant[list.size()]);
		}
		return null;
	}

	
	/**
	 * 
	 * <pre>
	 * 方法体说明：经营本部负责人
	 * 作者：andy
	 * 日期： 2013-11-25 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getOperationManager(Map<?, ?> map) {
		Department d = getDept(map);
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();

		IDepartmentService departmentService = (IDepartmentService) wac
				.getBean("departmentService");
		List<Department> deptList = departmentService.getAllParentDeptByDeptId(d
				.getId());
		for (int i = 0; i < deptList.size(); i++) {
			Department department = (Department) deptList.get(i);
			if (department != null) {
				if (department.getDeptCode().equalsIgnoreCase(
						ContractParticipant.SOUTHCHINAOPERATE)
						|| department.getDeptCode().equalsIgnoreCase(
								ContractParticipant.CENTRALCHINAOPERATE)
						|| department.getDeptCode().equalsIgnoreCase(
								ContractParticipant.NORTHCHINAOPERATE)) {
					return queryBpmsParticipant(department);
				}
			}
		}

		return null;
	}

	/******************** 月结客户签订申请（快递） ********************/

	/**
	 * 
	 * <pre>
	 * 方法体说明：客户所属营业部经理
	 * 作者：andy
	 * 日期： 2013-11-25 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getSalesManager(Map<?, ?> map) {

		return queryBpmsParticipant(getDept(map));

	}

	/**
	 * 
	 * <pre>
	 * 方法体说明：快递大区总经理
	 * 作者：andy
	 * 日期： 2013-11-25 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getExpressDeliveryManager(Map<?, ?> map) {
		ContractWorkflowInfo info = new ContractWorkflowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME)
				.toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IContractWorkflowManager contractWorkflowManager = (IContractWorkflowManager) wac
				.getBean("contractWorkflowManager");
		IDepartmentService departmentService = (IDepartmentService) wac
				.getBean("departmentService");
			info = contractWorkflowManager.findContractWorkflowInfoByWorkNo(
					bizCode, processDefName);
		// 对应的点部编码不为空
		if (null != info
				& StringUtil.isNotEmpty(info.getExpressPointDeptCode())) {
			Department pointDepartment = departmentService
					.getDeptByStandardCode(info.getExpressPointDeptCode());
			String deptId = pointDepartment.getId();
			List<Department> deptList = departmentService
					.getAllParentDeptByDeptId(deptId);
			for (int i = 0; i < deptList.size(); i++) {
				Department department = deptList.get(i);
				if (department != null
						&& department.getDeptName().endsWith("快递大区")) {
					return queryBpmsParticipant(department);
				}
			}
		}

		return null;

	}

	/**
	 * 
	 * <pre>
	 * 方法体说明：快递分部总
	 * 作者：andy
	 * 日期： 2013-11-25 上午9:51:37
	 * @param map
	 * @return
	 * </pre>
	 */
	public BpmsParticipant[] getComExpressDeliveryManager(Map<?, ?> map) {
		ContractWorkflowInfo info = new ContractWorkflowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME)
				.toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IContractWorkflowManager contractWorkflowManager = (IContractWorkflowManager) wac
				.getBean("contractWorkflowManager");
		IDepartmentService departmentService = (IDepartmentService) wac
				.getBean("departmentService");
			info = contractWorkflowManager.findContractWorkflowInfoByWorkNo(
					bizCode, processDefName);
		// 对应的点部编码不为空
		if (null != info
				& StringUtil.isNotEmpty(info.getExpressPointDeptCode())) {
			Department pointDepartment = departmentService
					.getDeptByStandardCode(info.getExpressPointDeptCode());
			String deptId = pointDepartment.getId();
			List<Department> deptList = departmentService
					.getAllParentDeptByDeptId(deptId);
			for (int i = 0; i < deptList.size(); i++) {
				Department department = deptList.get(i);
				if (department != null
						&& department.getDeptName().endsWith("快递分部")) {
					return queryBpmsParticipant(department);
				}
			}
		}

		return null;
	}

	/**
	 * 
	 * <p>
	 * Description:获得合同归属营业部的部门信息<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-11-27
	 * @param map
	 * @return Department
	 */
	private Department getDept(Map<?, ?> map) {
		ContractWorkflowInfo info = new ContractWorkflowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE);
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME)
				.toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IContractWorkflowManager contractWorkflowManager = (IContractWorkflowManager) wac
				.getBean("contractWorkflowManager");
		IDepartmentService departmentService = (IDepartmentService) wac
				.getBean("departmentService");
			info = contractWorkflowManager.findContractWorkflowInfoByWorkNo(
					bizCode, processDefName);
		Department dept = departmentService.getDeptByStandardCode(info
				.getContrctAscriptionDept());
		return dept;
	}

	 
}
