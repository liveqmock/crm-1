/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ContractBranch.java
 * @package com.deppon.crm.module.customer.bpsworkflow.branch 
 * @author pgj
 * @version 0.1 2013-11-16
 */
package com.deppon.crm.module.customer.bpsworkflow.branch;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.web.context.WebApplicationContext;

import com.deppon.bpmsapi.module.client.util.BPMSConstant;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.bps.server.service.IAmountConfigService;
import com.deppon.crm.module.bps.server.util.AmountConfigEntity;
import com.deppon.crm.module.customer.bpsworkflow.util.BpsConstant;
import com.deppon.crm.module.customer.server.manager.IContractWorkflowManager;
import com.deppon.crm.module.customer.server.utils.ExpressAuthDeptUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.ContractWorkflowInfo;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.context.WebApplicationContextHolder;
import com.deppon.foss.framework.shared.util.string.StringUtil;

/**
 * <p>
 * Description:合同分支<br />
 * </p>
 * 
 * @title ContractBranch.java
 * @package com.deppon.crm.module.customer.bpsworkflow.branch
 * @author pgj
 * @version 0.1 2013-11-16
 */

public class ContractBranch {
	// MAP
	// PMSConstant.PROCESS_INST_ID 流程实例ID
	// BPMSConstant.USER_ID 登录ID
	// BPMSConstant.BIZCODE 业务编码
	// BPMSConstant.APPLYER_CODE 申请人工号
	// BPMSConstant.CURRENT_ACTIVITY_DEF_ID 当前节点
	// BPMSConstant.NEXT_ACTIVITY_DEF_ID 下一节点
	/**
	 * 
	 * <pre>
	 * 方法体说明：合同申请---判断发票标记类型
	 * 作者：andy
	 * 日期： 2013-11-25 上午14:18:21
	 * @param map
	 * @return：1-true，否则false
	 * </pre>
	 */
	public boolean isType(Map<?, ?> map) {
		// 根据业务编码获取合同信息
		ContractWorkflowInfo info = new ContractWorkflowInfo();
		// 获得业务编码
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
		// 获得流程定义名称
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME)
				.toString();
		// 获得WebApplicationContext
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		// 得到合同工作流manager
		IContractWorkflowManager contractWorkflowManager = (IContractWorkflowManager) wac
				.getBean("contractWorkflowManager");
		// 根据工作流业务编码及流程定义名称查询出合同详细信息
		info = contractWorkflowManager.findContractWorkflowInfoByWorkNo(
				bizCode, processDefName);
		// 如果对应的合同信息不为空
		if (null != info & StringUtil.isNotEmpty(info.getContractNumber())) {
			// 发票标记为01 返回true
			if (Constant.INVOICE_TYPE_01.equals(info.getInvoiceType())) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 
	 * <pre>
	 * 方法体说明：合同申请---判断是否有零担折扣
	 * 作者：royxhl
	 * 日期： 2013-11-25 上午14:18:21
	 * @param map
	 * @return：true，否则false
	 * </pre>
	 */
	public boolean isLTTDiscount(Map<?, ?> map) {
		ContractWorkflowInfo info = new ContractWorkflowInfo();
		// 获得业务编码
		String bizCode = (String) map.get(BPMSConstant.BIZCODE);
		// 获得流程定义名称
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME)
				.toString();
		// 获得WebApplicationContext
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		// 得到合同工作流manager
		IContractWorkflowManager contractWorkflowManager = (IContractWorkflowManager) wac
				.getBean("contractWorkflowManager");
		// 根据工作流业务编码及流程定义名称查询出合同详细信息
		info = contractWorkflowManager.findContractWorkflowInfoByWorkNo(
				bizCode, processDefName);
		if (null != info & StringUtil.isNotEmpty(info.getContractNumber())) {
			// 返回是否有零担折扣
			return info.isHasLTTDiscount();
		}
		return false;
	}

	/**
	 * 
	 * <pre>
	 * 方法体说明：合同申请---判断是否有快递折扣
	 * 作者：andy
	 * 日期： 2013-11-25 上午14:18:21
	 * @param map
	 * @return：1-true，否则false
	 * </pre>
	 */
	public boolean isExpDiscount(Map<?, ?> map) {
		ContractWorkflowInfo info = new ContractWorkflowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME)
				.toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IContractWorkflowManager contractWorkflowManager = (IContractWorkflowManager) wac
				.getBean("contractWorkflowManager");
		info = contractWorkflowManager.findContractWorkflowInfoByWorkNo(
				bizCode, processDefName);
		if (null != info & StringUtil.isNotEmpty(info.getContractNumber())) {
			// 判断是否有快递折扣
			return info.isHasExpDiscount();
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * Description:是否只修改快递折扣<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-11-27
	 * @param map
	 * @return boolean
	 */
	public boolean isOnlyModifyExpDiscount(Map<?, ?> map) {
		ContractWorkflowInfo info = new ContractWorkflowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME)
				.toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IContractWorkflowManager contractWorkflowManager = (IContractWorkflowManager) wac
				.getBean("contractWorkflowManager");
		info = contractWorkflowManager.findContractWorkflowInfoByWorkNo(
				bizCode, processDefName);
		if (null != info & StringUtil.isNotEmpty(info.getContractNumber())) {

			return info.getModifyContractType().equals("0001");
		}
		return false;
	}

	/**
	 * 是否只修改结算额度
	 * 
	 * @param map
	 * @return
	 */
	public boolean isOnlyModifyBudget(Map<?, ?> map) {
		ContractWorkflowInfo info = new ContractWorkflowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME)
				.toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IContractWorkflowManager contractWorkflowManager = (IContractWorkflowManager) wac
				.getBean("contractWorkflowManager");
		info = contractWorkflowManager.findContractWorkflowInfoByWorkNo(
				bizCode, processDefName);
		if (null != info & StringUtil.isNotEmpty(info.getContractNumber())) {

			return info.getModifyContractType().equals("0100");
		}
		return false;
	}

	/**
	 * 结算额度检验
	 * 
	 * @param map
	 * @return
	 */
	public boolean isOverRange(Map<?, ?> map) {
		ContractWorkflowInfo info = new ContractWorkflowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME)
				.toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IContractWorkflowManager contractWorkflowManager = (IContractWorkflowManager) wac
				.getBean("contractWorkflowManager");
		info = contractWorkflowManager.findContractWorkflowInfoByWorkNo(
				bizCode, processDefName);
		if (null != info & StringUtil.isNotEmpty(info.getContractNumber())
				&& StringUtil.isNotEmpty(info.getOverRange())) {

			return info.getOverRange().equals("true");
		}

		return false;
	}

	/**
	 * 是否只修改优惠折扣
	 * 
	 * @param map
	 * @return
	 */
	public boolean isOnlyModifyDiscount(Map<?, ?> map) {
		ContractWorkflowInfo info = new ContractWorkflowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME)
				.toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IContractWorkflowManager contractWorkflowManager = (IContractWorkflowManager) wac
				.getBean("contractWorkflowManager");
		info = contractWorkflowManager.findContractWorkflowInfoByWorkNo(
				bizCode, processDefName);
		if (null != info & StringUtil.isNotEmpty(info.getContractNumber())) {
			if (info.getModifyContractType().equals("0010")
					|| info.getModifyContractType().equals("0001")
					|| info.getModifyContractType().equals("0011")) {
				return true;
			}

		}
		return false;
	}
	
	/**                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
	 * 是否只修改零担优惠折扣
	 * 
	 * @param map
	 * @return
	 */
	public boolean isModifyLTTDiscount(Map<?, ?> map) {
		ContractWorkflowInfo info = new ContractWorkflowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME)
				.toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IContractWorkflowManager contractWorkflowManager = (IContractWorkflowManager) wac
				.getBean("contractWorkflowManager");
		info = contractWorkflowManager.findContractWorkflowInfoByWorkNo(
				bizCode, processDefName);
		if (null != info & StringUtil.isNotEmpty(info.getContractNumber())) {
			if (info.getModifyContractType().substring(2,3).equals("1")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * Description:是否只修改了发票标记<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-11-27
	 * @param map
	 * @return boolean
	 */
	public boolean ismodifyInvoiceOnly(Map<?, ?> map) {
		ContractWorkflowInfo info = new ContractWorkflowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME)
				.toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IContractWorkflowManager contractWorkflowManager = (IContractWorkflowManager) wac
				.getBean("contractWorkflowManager");
		info = contractWorkflowManager.findContractWorkflowInfoByWorkNo(
				bizCode, processDefName);
		if (null != info & StringUtil.isNotEmpty(info.getContractNumber())) {
			return info.getModifyContractType().equals("1000");
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * Description: Description:类型1：零担、快递近三月发货金额均不为0，或仅快递近三月发货金额为0的月发越折合同，
	 * 或仅零担近三月发货金额为0的非零担月结 ，合同审批流程如下A;<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-11-27
	 * @param map
	 * @return boolean 类型1 返回true 类型2 返回fasle
	 */
	public boolean isTypeOne(Map<?, ?> map) {
		ContractWorkflowInfo info = new ContractWorkflowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME)
				.toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IContractWorkflowManager contractWorkflowManager = (IContractWorkflowManager) wac
				.getBean("contractWorkflowManager");
		info = contractWorkflowManager.findContractWorkflowInfoByWorkNo(
				bizCode, processDefName);
		if (null != info & StringUtil.isNotEmpty(info.getContractNumber())) {
			if ((!info.getExpAmountOfConsignMent().equals("0\\0\\0")  && !info
					.getAmountOfConsignment().equals("0\\0\\0") )
					|| (info.getExpAmountOfConsignMent().equals("0\\0\\0") 
							&& !info.getAmountOfConsignment().equals("0\\0\\0")  && "月发越折"
								.equals(info.getExpressPreferentialType()))
					|| (!info.getExpAmountOfConsignMent().equals("0\\0\\0") 
							&& info.getAmountOfConsignment().equals("0\\0\\0")  && "月结"
								.equals(info.getExpNodesectionType()))
					&& !"月结".equals(info.getNodeSectionType())) {
				return true;
			}

		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * Description:仅快递近三月发货金额为0时，签订快递月结或优惠类型选择运费折扣或两者同时选择，，
	 * 或仅零担近三月发货金额为0的零担月结或非纯月发月送合同，或近三月发货金额同时为0的非纯月发越折、月发月送合同<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2013-12-5
	 * @param map
	 * @return boolean
	 */
	public boolean isTypeTwo(Map<?, ?> map) {
		ContractWorkflowInfo info = new ContractWorkflowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME)
				.toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IContractWorkflowManager contractWorkflowManager = (IContractWorkflowManager) wac
				.getBean("contractWorkflowManager");
		info = contractWorkflowManager.findContractWorkflowInfoByWorkNo(
				bizCode, processDefName);
		if (null != info & StringUtil.isNotEmpty(info.getContractNumber())) {
			if ((info.getExpAmountOfConsignMent() == null
					&& info.getAmountOfConsignment() != null
					&& "月结".equals(info.getExpNodesectionType()) || "运费折扣"
						.equals(info.getExpressPreferentialType()))
					|| (info.getExpAmountOfConsignMent() != null
							&& info.getAmountOfConsignment() == null && "月结"
								.equals(info.getNodeSectionType()))
					|| (info.getExpAmountOfConsignMent() == null && info
							.getAmountOfConsignment() == null)
					&& !("无".equals(info.getExpNodesectionType()) && "月发越折"
							.equals(info.getExpressPreferentialType()))) {
				return true;
			}

		}
		return false;
	}

	/**
	 * 
	 * <pre>
	 * 方法体说明：判断是否经理提交
	 * 作者：andy
	 * 日期： 2013-11-25 上午9:51:37
	 * @param map
	 * @return：是则返回true，否则false
	 * </pre>
	 */
	public boolean isManagerSubmit(Map<?, ?> map) {
		ContractWorkflowInfo info = new ContractWorkflowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME)
				.toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IContractWorkflowManager contractWorkflowManager = (IContractWorkflowManager) wac
				.getBean("contractWorkflowManager");
		info = contractWorkflowManager.findContractWorkflowInfoByWorkNo(
				bizCode, processDefName);
		// 对应的合同序号不为空
		if (null != info & StringUtil.isNotEmpty(info.getContractNumber())) {
			if ("Y".equals(info.getFisPointManager())) {
				return false;
			}
		} else {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * Description:是否修改了发票标记<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-11-27
	 * @param map
	 * @return boolean 修改了 false 未修复true
	 */
	public boolean isModifyInvoiceType(Map<?, ?> map) {
		ContractWorkflowInfo info = new ContractWorkflowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME)
				.toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IContractWorkflowManager contractWorkflowManager = (IContractWorkflowManager) wac
				.getBean("contractWorkflowManager");
		info = contractWorkflowManager.findContractWorkflowInfoByWorkNo(
				bizCode, processDefName);
		if (null != info & StringUtil.isNotEmpty(info.getContractNumber())) {
			if (null != info.getInvoiceType()
					&& null != info.getNewInvoiceType()
					&& !info.getNewInvoiceType().equals(info.getInvoiceType())) {
				return true;
			}

		}
		return false;
	}

	/**
	 * 
	 * <pre>
	 * 方法体说明：判断是否快递大区
	 * 作者：andy
	 * 日期： 2013-11-25 上午14:18:21
	 * @param map
	 * @return：是则返回true，否则false
	 * </pre>
	 */
	public boolean isExpressArea(Map<?, ?> map) {
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
		if (null != info & StringUtil.isNotEmpty(info.getContractNumber())) {
			// 点部标杆编码
			String expressPointDeptCode = info.getExpressPointDeptCode();
			// 如果编码不为空
			if (StringUtil.isNotEmpty(expressPointDeptCode)) {
				// 查询出对应的快递点部
				Department exDept = departmentService
						.getDeptByStandardCode(expressPointDeptCode);
				Department bigArea = null;
				if (null != exDept) {
					bigArea = departmentService.getBigAreaByDeptId(exDept
							.getId());
				}
				if (bigArea != null && !StringUtil.isEmpty(bigArea.getId())) {
					List<Department> deptList = departmentService
							.queryAllChildDeptByDeptId(bigArea.getId());
					for (int i = 0; i < deptList.size(); i++) {
						Department deptment = deptList.get(i);
						// 如果子部门有快递部分 则改部门不是快递大区
						if (deptment != null
								&& deptment.getDeptName().endsWith("快递分部")) { // 有快递分部，则无快递事业部
							return false;
						}
					}
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * <pre>
	 * 方法体说明：零担合同申请流程---结算限额判断
	 * 作者：andy
	 * 日期： 2013-11-25 下午14:18:21
	 * @param map
	 * @return：是则返回true，否则false
	 * </pre>
	 */
	public boolean isLttNewBalanceAccount(Map<?, ?> map) {
		return isBalanceAccount(map, BpsConstant.LTT_NEW_PROCESSNAME);
	}

	/**
	 * 
	 * <pre>
	 * 方法体说明：快递合同申请流程---结算限额判断
	 * 作者：andy
	 * 日期： 2013-11-25 下午14:18:21
	 * @param map
	 * @return：是则返回true，否则false
	 * </pre>
	 */
	public boolean isExNewBalanceAccount(Map<?, ?> map) {
		return isBalanceAccount(map, BpsConstant.EX_NEW_PROCESSNAME);
	}

	/**
	 * 
	 * <pre>
	 * 方法体说明：零担合同修改流程---结算限额判断
	 * 作者：andy
	 * 日期： 2013-11-25 下午14:18:21
	 * @param map
	 * @return：是则返回true，否则false
	 * </pre>
	 */
	public boolean isLttUpdateBalanceAccount(Map<?, ?> map) {
		return isBalanceAccount(map, BpsConstant.LTT_UPDATE_PROCESSNAME);
	}

	/**
	 * 
	 * <pre>
	 * 方法体说明：快递合同修改流程---结算限额判断
	 * 作者：andy
	 * 日期： 2013-11-25 下午14:18:21
	 * @param map
	 * @return：是则返回true，否则false
	 * </pre>
	 */
	public boolean isExUpdateBalanceAccount(Map<?, ?> map) {
		return isBalanceAccount(map, BpsConstant.EX_UPDATE_PROCESSNAME);
	}

	/**
	 * 
	 * <pre>
	 * 方法体说明：结算限额判断
	 * 作者：andy
	 * 日期： 2013-11-25 下午14:18:21
	 * @param map
	 * @param mcDefiniTionName
	 * @return：是则返回true，否则false
	 * </pre>
	 */
	private boolean isBalanceAccount(Map<?, ?> map, String mcDefiniTionName) {
		double balanceAccount = 0; // 结算限额
		ContractWorkflowInfo info = new ContractWorkflowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
 		String currentActivityDefId = map.get(
				BPMSConstant.CURRENT_ACTIVITY_DEF_ID).toString(); // 当前节点
		String nextActivityDefId = map.get(BPMSConstant.NEXT_ACTIVITY_DEF_ID)
				.toString(); // 下一节点
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IContractWorkflowManager contractWorkflowManager = (IContractWorkflowManager) wac
				.getBean("contractWorkflowManager");
		IAmountConfigService amountConfigService = (IAmountConfigService) wac
				.getBean("bpsAmountConfigService");
		info = contractWorkflowManager.findContractWorkflowInfoByWorkNo(
				bizCode, mcDefiniTionName);
		balanceAccount = Double.valueOf(info.getBalanceAccount());
		if (BpsConstant.EX_UPDATE_PROCESSNAME.equals(mcDefiniTionName)|| BpsConstant.LTT_UPDATE_PROCESSNAME.equals(mcDefiniTionName)) {
			balanceAccount = Double.valueOf(info.getNewBalanceAccount());
		}
		BigDecimal amount = new BigDecimal(balanceAccount);
		AmountConfigEntity amountConfigEntity = new AmountConfigEntity();
		amountConfigEntity.setCurrentApproStepNo(currentActivityDefId);
		amountConfigEntity.setTargetApproStepNo(nextActivityDefId);
		amountConfigEntity.setMcDefiniTionName(mcDefiniTionName);
		AmountConfigEntity entity = amountConfigService
				.queryForBranch(amountConfigEntity);
		if (entity != null) {
			BigDecimal minAmount = entity.getMinAmount();
			BigDecimal maxAmount = entity.getMaxAmount();
			if (amount.compareTo(minAmount) > 0
					&& amount.compareTo(maxAmount) <= 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * Description:判断是否营业部经理<br />
	 * </p>
	 * 
	 * @author pgj
	 * 
	 * 
	 * @version 0.1 2013-11-28
	 * @param map
	 * @return boolean
	 */
	public boolean isDeptManager(Map<?, ?> map) {
		String bizCode = (String) map.get(BPMSConstant.BIZCODE);
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IContractWorkflowManager contractWorkflowManager = (IContractWorkflowManager) wac
				.getBean("contractWorkflowManager");// 流程实例ID
		User user = contractWorkflowManager
				.searchOperaDeptByWorkflowId(bizCode);
		return !ExpressAuthDeptUtil.isPointManager(user.getRoleids());
	}
	
	/**
	 * 
	* @Title: isOnlyLIT
	* @Description: 判断是否是纯零担
	* @author chenaichun 
	* @param @param map
	* @param @return    设定文件
	* @date 2014-1-14 上午8:51:13
	* @return boolean    返回类型
	* @throws
	* @update 2014-1-14 上午8:51:13
	 */
	public boolean isOnlyLIT(Map<?, ?> map) {
		ContractWorkflowInfo info = new ContractWorkflowInfo();
		String bizCode = (String) map.get(BPMSConstant.BIZCODE); // 流程实例ID
		String processDefName = map.get(BPMSConstant.PROCESS_DEF_NAME)
				.toString();
		WebApplicationContext wac = WebApplicationContextHolder
				.getWebApplicationContext();
		IContractWorkflowManager contractWorkflowManager = (IContractWorkflowManager) wac
				.getBean("contractWorkflowManager");
		info = contractWorkflowManager.findContractWorkflowInfoByWorkNo(
				bizCode, processDefName);
		if (null != info & StringUtil.isNotEmpty(info.getContractNumber())) {
			if(info.getApplyType().equals("CANCEL")){
				return true;
			}
		}
		return false;
	}
	
}
