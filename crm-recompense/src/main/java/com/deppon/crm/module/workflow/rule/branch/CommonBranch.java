package com.deppon.crm.module.workflow.rule.branch;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.deppon.crm.module.common.server.dao.IExpressPointBusinessDeptDao;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.Overpay;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.bpmsapi.module.client.util.BPMSConstant;
import com.deppon.crm.module.workflow.server.service.IAmountConfigService;
import com.deppon.crm.module.workflow.server.service.INormalClaimService;
import com.deppon.crm.module.workflow.server.util.AmountConfigEntity;
import com.deppon.crm.module.workflow.server.util.NormalClaimUtil;
import com.deppon.crm.module.workflow.shared.domain.NormalClaim;
import com.deppon.foss.framework.server.context.WebApplicationContextHolder;
import com.eos.system.utility.StringUtil;

/**
 * <pre>
 * 功能:通用审批规则-分支规则
 * 作者：andy
 * 日期：2013-7-29 上午10:10:35
 * </pre>
 */
public class CommonBranch {
	// 日志记录对象
	private static Logger log = Logger.getLogger(CommonBranch.class);

	//MAP 
	//PMSConstant.PROCESS_INST_ID            流程实例ID
	//BPMSConstant.USER_ID                   登录ID
	//BPMSConstant.BIZCODE                   业务编码
	//BPMSConstant.APPLYER_CODE              申请人工号
	//BPMSConstant.CURRENT_ACTIVITY_DEF_ID   当前节点
	//BPMSConstant.NEXT_ACTIVITY_DEF_ID      下一节点
	
	public static final String EXPRESSBRANCH         = "快递分部";
	public static final String EXPRESSLARGEAREA      = "快递大区";
	public static final String BUSINESSDEPARTMENT    = "事业部";
	public static final String EXPRESSSTATISTICSGROUP = "统计组";
	public static final String EXPRESSBRANCHSTATISTICSGROUP = "快递";
	public static final String LARGEAREA             = "大区";

	
	/**
	 * 
	 * <pre>
	 * 方法体说明：判断运输类型是否是快递
	 * 作者：andy
	 * 日期： 2013-8-12 上午9:51:37
	 * @param map
	 * @return：是则返回true，否则false
	 * </pre>
	 */
	public boolean isExpressDelivery(Map<?, ?> map) {
		String bizCode = map.get(BPMSConstant.BIZCODE).toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		INormalClaimService normalClaimService = (INormalClaimService) wac
				.getBean("normalClaimService");
		NormalClaim normalClaim = normalClaimService.getNormalClaimByWorkflowNo(bizCode);
		if(normalClaim != null) {
			String haulType = normalClaim.getHaulType();
			if(Constants.TRANS_EXPRESS.equalsIgnoreCase(haulType)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：判断理赔创建人所在部门是否为快递统计组
	 * 作者：andy
	 * 日期： 2013-8-12 上午9:51:37
	 * @param map
	 * @return：是则返回true，否则false
	 * </pre>
	 */
	public boolean isExpressStatisticsGroup(Map<?, ?> map) {
		String bizCode = map.get(BPMSConstant.BIZCODE).toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		INormalClaimService normalClaimService = (INormalClaimService) wac
				.getBean("normalClaimService");
		NormalClaim normalClaim = normalClaimService.getNormalClaimByWorkflowNo(bizCode);
		if(normalClaim != null) {
			String reportDeptName = normalClaim.getReportDeptName();
			if(!StringUtils.isEmpty(reportDeptName) && 
					(reportDeptName.endsWith(this.EXPRESSSTATISTICSGROUP) || 
							reportDeptName.contains(this.EXPRESSBRANCHSTATISTICSGROUP))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：判断理赔创建人所在部门(报案部门)的上级部门是快递大区还是快递分部
	 * 作者：andy
	 * 日期： 2013-8-12 上午9:51:37
	 * @param map
	 * @return：true-快递大区，false-快递分部
	 * </pre>
	 */
	public boolean isExpressAreaOrBranch(Map<?, ?> map) {
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
			Department deptment = departmentService.getDepartmentById(reportDeptId);
			if(deptment != null && deptment.getParentCode().getDeptName().endsWith(this.EXPRESSLARGEAREA)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：快递分部上级是否为零担大区总
	 * 作者：andy
	 * 日期： 2013-8-12 上午9:51:37
	 * @param map
	 * @return：true-大区，false-事业部
	 * </pre>
	 */
	public boolean isSuperiorLtlLargeArea(Map<?, ?> map) {
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
			for(int i = 0; i < deptList.size(); i++) {
				Department deptment = deptList.get(i);
				if(deptment != null && deptment.getDeptName().endsWith(this.EXPRESSBRANCH)) {
					if(i+1 <= deptList.size()) {
						deptment = deptList.get(i+1);
						if(deptment != null && deptment.getDeptName().endsWith(this.LARGEAREA)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：判断是否是试点城市
	 * 作者：andy
	 * 日期： 2013-8-12 上午13:42:15
	 * @param map
	 * @return：是则返回true，否则false
	 * </pre>
	 */
	public boolean isPilotCity(Map<?, ?> map) {
		String bizCode = map.get(BPMSConstant.BIZCODE).toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		INormalClaimService normalClaimService = (INormalClaimService) wac
				.getBean("normalClaimService");
		NormalClaim normalClaim = normalClaimService.getNormalClaimByWorkflowNo(bizCode);
		if(normalClaim != null) {
			String caseReporter = normalClaim.getCaseReporter();
			List<String> list = normalClaimService.getRoleIdByUserId(caseReporter);
			for(String roleid: list) {
				if(roleid.equals("9") || roleid.equals("10")) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：判断是否有快递事业部(取报案部门所在大区，递归子部门，如果子部门有“快递分部”，则无快递事业部)
	 * 作者：andy
	 * 日期： 2013-8-12 上午13:45:48
	 * @param map
	 * @return：是则返回true，否则false
	 * </pre>
	 */
	public boolean isExpressBusinessDept(Map<?, ?> map) {
		boolean isExpress = true;
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
			Department dept = departmentService.getBigAreaByDeptId(reportDeptId);
			if(dept != null && !StringUtil.isEmpty(dept.getId())) {
				List<Department> deptList = departmentService.queryAllChildDeptByDeptId(dept.getId());
				for(int i = 0; i < deptList.size(); i++) {
					Department deptment = deptList.get(i);
					if(deptment != null && deptment.getDeptName().endsWith(this.EXPRESSBRANCH)) {   // 有快递分部，则无快递事业部
						isExpress = false;
						break;
					}
				}
			}
		}
		return isExpress;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：理赔金额判断
	 * 作者：andy
	 * 日期： 2013-8-13 14:18:21
	 * @param map
	 * @return：是则返回true，否则false
	 * </pre>
	 */
	public boolean isActualClaimsAmount(Map<?, ?> map) {
		double actualClaimsAmount = 0; // 实际理赔金额
		String processInstId = map.get(BPMSConstant.PROCESS_INST_ID).toString();                //流程实例ID
		String currentActivityDefId = map.get(BPMSConstant.CURRENT_ACTIVITY_DEF_ID).toString(); //当前节点
		String nextActivityDefId = map.get(BPMSConstant.NEXT_ACTIVITY_DEF_ID).toString();       //下一节点
		
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		INormalClaimService normalClaimService = (INormalClaimService) wac
				.getBean("normalClaimService");
		NormalClaim normalClaim = normalClaimService.getNormalClaim(Long.parseLong(processInstId));
		if(normalClaim != null) {
			actualClaimsAmount = normalClaim.getActualClaimsAmount();
		}
		BigDecimal amount = new BigDecimal(actualClaimsAmount);
		IAmountConfigService amountConfigService = (IAmountConfigService) wac
				.getBean("amountConfigService");
		AmountConfigEntity amountConfigEntity = new AmountConfigEntity();
		amountConfigEntity.setCurrentApproStepNo(currentActivityDefId);
		amountConfigEntity.setTargetApproStepNo(nextActivityDefId);
		amountConfigEntity.setMcDefiniTionName(NormalClaimUtil.PROCESS_DEFINITION_NAME);
		AmountConfigEntity entity = amountConfigService.queryForBranch(amountConfigEntity);
		if(entity != null) {
			BigDecimal minAmount = entity.getMinAmount();
			BigDecimal maxAmount = entity.getMaxAmount();
			if(amount.compareTo(minAmount) >= 0 && amount.compareTo(maxAmount) <= 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：快递分部上级部门
	 * 作者：andy
	 * 日期： 2014-2-24 9:53:21
	 * @param map
	 * @return：上级为事业部-true，上级为零担大区-false
	 * </pre>
	 */
	public boolean isExpressBranchDept(Map<?, ?> map) {
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
				//快递分部
				if(department != null && department.getDeptName().endsWith(this.EXPRESSBRANCH)) {
					//若快递分部上级部门为事业部
					if(i+1 <= deptList.size()) {
						Department dept = deptList.get(i+1);
						if(dept != null && dept.getDeptName().endsWith(this.BUSINESSDEPARTMENT)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/******************************   多赔start   ******************************/
	/**
	 * 
	 * <pre>
	 * 方法体说明：判断申请类型
	 * 作者：andy
	 * 日期： 2013-8-13 上午14:18:21
	 * @param map
	 * @return：零担true，快递false
	 * </pre>
	 */
	public boolean isTransType(Map<?, ?> map) {
		String bizCode = map.get(BPMSConstant.BIZCODE).toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		RecompenseService recompenseService = (RecompenseService) wac.
				getBean("recompenseService");
		RecompenseApplication recApplication =  recompenseService.getRecompense(bizCode);
		if(recApplication != null) {
			String transType = recApplication.getWaybill().getTransType();
			if(Constants.TRANS_EXPRESS.equalsIgnoreCase(transType)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：多赔金额判断
	 * 作者：andy
	 * 日期： 2013-8-13 上午14:18:21
	 * @param map
	 * @return：是则返回true，否则false
	 * </pre>
	 */
	public boolean isOverpayActualClaimsAmount(Map<?, ?> map) {
		double totalAmount = 0; // 多赔总金额
		String bizCode = map.get(BPMSConstant.BIZCODE).toString();
		String currentActivityDefId = map.get(BPMSConstant.CURRENT_ACTIVITY_DEF_ID).toString(); //当前节点
		String nextActivityDefId = map.get(BPMSConstant.NEXT_ACTIVITY_DEF_ID).toString();       //下一节点
		
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		RecompenseService recompenseService = (RecompenseService) wac.
				getBean("recompenseService");
		
		Overpay overPay = recompenseService.getOverPay(bizCode);
		if(overPay != null) {
			totalAmount = overPay.getTotalAmount();
		}
		BigDecimal amount = new BigDecimal(totalAmount);
		IAmountConfigService amountConfigService = (IAmountConfigService) wac
				.getBean("amountConfigService");
		AmountConfigEntity amountConfigEntity = new AmountConfigEntity();
		amountConfigEntity.setCurrentApproStepNo(currentActivityDefId);
		amountConfigEntity.setTargetApproStepNo(nextActivityDefId);
		amountConfigEntity.setMcDefiniTionName(NormalClaimUtil.OVERPAY_PROCESS_DEFINITION_NAME);
		AmountConfigEntity entity = amountConfigService.queryForBranch(amountConfigEntity);
		if(entity != null) {
			BigDecimal minAmount = entity.getMinAmount();
			BigDecimal maxAmount = entity.getMaxAmount();
			if(amount.compareTo(minAmount) >= 0 && amount.compareTo(maxAmount) <= 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：判断起草人是营业部经理还是快递点部经理
	 * 作者：andy
	 * 日期： 2013-8-13 上午14:18:21
	 * @param map
	 * @return：营业部经理true，快递点部经理false
	 * </pre>
	 */
	public boolean isExpressManagerType(Map<?, ?> map) {
		String bizCode = map.get(BPMSConstant.BIZCODE).toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		RecompenseService recompenseService = (RecompenseService) wac.
				getBean("recompenseService");
		
		Overpay overPay = recompenseService.getOverPay(bizCode);
		if(overPay != null) {
			String applyStandardCode = overPay.getApplyStandardCode();
			IExpressPointBusinessDeptDao dao = (IExpressPointBusinessDeptDao) wac.
					getBean("expressPointBusinessDeptDao");
			int countDept = dao.getExpressPointBusinessDeptByApplyDeptCode(applyStandardCode);
			if(countDept > 0) {
				return false;
			}else{
				return true;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：判断是否有快递大区
	 * 作者：andy
	 * 日期： 2013-8-13 上午14:18:21
	 * @param map
	 * @return：有true，否false
	 * </pre>
	 */
	public boolean isExpressArea(Map<?, ?> map) {
		boolean isTrue = false;
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
					isTrue = true;
					break;
				}
			}
		}
		return isTrue;
	}
	
	/**
	 * 
	 * <pre>
	 * 方法体说明：快递分部上级是否为零担大区总
	 * 作者：andy
	 * 日期： 2013-8-12 上午9:51:37
	 * @param map
	 * @return：true-大区，false-事业部
	 * </pre>
	 */
	public boolean isSuperiorLtlLargeAreaOverPay(Map<?, ?> map) {
		String bizCode = map.get(BPMSConstant.BIZCODE).toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		RecompenseService recompenseService = (RecompenseService) wac.
				getBean("recompenseService");
		RecompenseApplication app = recompenseService.getRecompense(bizCode);
		if(app != null) {
			String reportDeptId = app.getReportDept();
			IDepartmentService departmentService = (IDepartmentService) wac
					.getBean("departmentService");
			List<Department> deptList = departmentService.getAllParentDeptByDeptId(reportDeptId);
			for(int i = 0; i < deptList.size(); i++) {
				Department deptment = deptList.get(i);
				if(deptment != null && deptment.getDeptName().endsWith(this.EXPRESSBRANCH)) {
					if(i+1 <= deptList.size()) {
						deptment = deptList.get(i+1);
						if(deptment != null && deptment.getDeptName().endsWith(this.LARGEAREA)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/******************************   多赔end   ******************************/
	
}