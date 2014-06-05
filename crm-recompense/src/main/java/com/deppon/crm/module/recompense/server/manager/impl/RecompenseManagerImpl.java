package com.deppon.crm.module.recompense.server.manager.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.bpmsapi.module.server.api.exception.BPMSException;
import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.backfreight.server.util.BackFreightConstant;
import com.deppon.crm.module.bps.server.manager.IBpsWorkflowManager;
import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.client.order.domain.OaAccidentInfo;
import com.deppon.crm.module.client.sms.domain.SmsInformation;
import com.deppon.crm.module.client.workflow.domain.AccountInfo;
import com.deppon.crm.module.client.workflow.domain.MuchRecompenseInfo;
import com.deppon.crm.module.client.workflow.domain.NormalRecompenseInfo;
import com.deppon.crm.module.client.workflow.domain.NormalRecompenseInfo.AccidentDescription;
import com.deppon.crm.module.client.workflow.domain.NormalRecompenseInfo.IndeptCharges;
import com.deppon.crm.module.client.workflow.domain.NormalRecompenseInfo.RewardPunishment;
import com.deppon.crm.module.common.file.util.MoneyUtil;
import com.deppon.crm.module.common.server.manager.ITodoWorkflowManager;
import com.deppon.crm.module.common.server.manager.impl.BankInfoManager;
import com.deppon.crm.module.common.server.manager.impl.BankProvinceCityManagerImpl;
import com.deppon.crm.module.common.server.manager.impl.ExpressPointBusinessDeptManager;
import com.deppon.crm.module.common.server.manager.impl.MessageManager;
import com.deppon.crm.module.common.server.manager.impl.PilotCityManager;
import com.deppon.crm.module.common.server.util.DataDictionaryUtil;
import com.deppon.crm.module.common.server.workflow.WorkflowManager;
import com.deppon.crm.module.common.shared.domain.BankView;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.common.shared.domain.ExpressPointBusinessDept;
import com.deppon.crm.module.customer.server.manager.impl.AlterMemberManager;
import com.deppon.crm.module.customer.server.manager.impl.MemberManager;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.frameworkimpl.server.util.EncryptUtil;
import com.deppon.crm.module.organization.server.service.impl.DepartmentService;
import com.deppon.crm.module.organization.server.service.impl.EmployeeService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.recompense.server.manager.RecompenseValidator;
import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.AwardItem;
import com.deppon.crm.module.recompense.shared.domain.BankAccount;
import com.deppon.crm.module.recompense.shared.domain.CellphoneMessageInfo;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.GetRecompenseByWayBill;
import com.deppon.crm.module.recompense.shared.domain.GoodsTrans;
import com.deppon.crm.module.recompense.shared.domain.IssueItem;
import com.deppon.crm.module.recompense.shared.domain.Message;
import com.deppon.crm.module.recompense.shared.domain.MessageReminder;
import com.deppon.crm.module.recompense.shared.domain.OAWorkflow;
import com.deppon.crm.module.recompense.shared.domain.OnlineApply;
import com.deppon.crm.module.recompense.shared.domain.OnlineApplyCondition;
import com.deppon.crm.module.recompense.shared.domain.Overpay;
import com.deppon.crm.module.recompense.shared.domain.Payment;
import com.deppon.crm.module.recompense.shared.domain.RecSmsInformation;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.RecompenseAttachment;
import com.deppon.crm.module.recompense.shared.domain.RecompenseForCC;
import com.deppon.crm.module.recompense.shared.domain.RecompenseSearchCondition;
import com.deppon.crm.module.recompense.shared.domain.ResponsibleDept;
import com.deppon.crm.module.recompense.shared.domain.TodoReminder;
import com.deppon.crm.module.recompense.shared.domain.UserRoleDeptRelation;
import com.deppon.crm.module.recompense.shared.domain.Waybill;
import com.deppon.crm.module.recompense.shared.exception.RecompenseException;
import com.deppon.crm.module.recompense.shared.exception.RecompenseExceptionType;
import com.deppon.crm.module.recompense.shared.exception.RecompenseMonitorException;
import com.deppon.crm.module.recompense.shared.exception.RecompenseMonitorExceptionType;
import com.deppon.crm.module.workflow.server.manager.INormalClaimManager;
import com.deppon.crm.module.workflow.server.service.INormalClaimService;
import com.deppon.crm.module.workflow.server.util.NormalClaimUtil;
import com.deppon.crm.module.workflow.shared.domain.NormalClaim;
import com.deppon.erp.payment.DepClaimsBill;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.shared.util.string.StringUtil;

/**
 * @ClassName: RecompenseManager
 * @Description: 理赔业务管理
 * @author
 * @date 2012-2-21 上午10:40:54
 * @see <a href="package.html#section">查看流程图</a>
 * 
 */
public class RecompenseManagerImpl implements RecompenseManager {
	
	private static String CLAIMS_STUDY_DEPARTMENT = "DP08840";
	// 理赔服务
	private RecompenseService recompenseService;

	// 工作流管理
	private WorkflowManager workflowManager;

	// 部门-没有manager
	private DepartmentService departmentService;

	// 用戶
	private IUserService userService;

	// 客户
	private AlterMemberManager alterMemberManager;

	// 待办
	private MessageManager messageManager;
	// 工作流
	private ITodoWorkflowManager todoWorkflowManager;
	// 会员manger
	private MemberManager memberManager;

	private BankInfoManager bankInfoManager;
	// 银行城市信息manager
	private BankProvinceCityManagerImpl bankProvinceCityManager;


	// 试点城市manager
	private PilotCityManager pilotCityManager;

	private INormalClaimManager normalClaimManager;
	private INormalClaimService normalClaimService;

	// 营业部与点部映射关系
	private ExpressPointBusinessDeptManager expressPointBusinessDeptManager;
	
	// bps工作流
	private IBpsWorkflowManager bpsWorkflowManager;

	private EmployeeService employeeService ;

	public void setEmployeeService(EmployeeService employeeService){
		this.employeeService = employeeService;
	}

	public EmployeeService getEmployeeService(){
		return this.employeeService;
	}
	
	public INormalClaimManager getNormalClaimManager() {
		return normalClaimManager;
	}

	public void setNormalClaimManager(INormalClaimManager normalClaimManager) {
		this.normalClaimManager = normalClaimManager;
	}

	public INormalClaimService getNormalClaimService() {
		return normalClaimService;
	}

	public void setNormalClaimService(INormalClaimService normalClaimService) {
		this.normalClaimService = normalClaimService;
	}

	private int SMS_SIZE = 500;

	public BankProvinceCityManagerImpl getBankProvinceCityManager() {
		return bankProvinceCityManager;
	}

	public void setBankProvinceCityManager(
			BankProvinceCityManagerImpl bankProvinceCityManager) {
		this.bankProvinceCityManager = bankProvinceCityManager;
	}

	public void setMemberManager(MemberManager memberManager) {
		this.memberManager = memberManager;
	}

	public ITodoWorkflowManager getTodoWorkflowManager() {
		return todoWorkflowManager;
	}

	public void setTodoWorkflowManager(ITodoWorkflowManager todoWorkflowManager) {
		this.todoWorkflowManager = todoWorkflowManager;
	}

	public void setBankInfoManager(BankInfoManager bankInfoManager) {
		this.bankInfoManager = bankInfoManager;
	}

	public IBpsWorkflowManager getBpsWorkflowManager() {
		return bpsWorkflowManager;
	}

	public void setBpsWorkflowManager(IBpsWorkflowManager bpsWorkflowManager) {
		this.bpsWorkflowManager = bpsWorkflowManager;
	}

	/**
	 * @return messageManager : return the property messageManager.
	 */
	public MessageManager getMessageManager() {
		return messageManager;
	}

	/**
	 * @param messageManager
	 *            : set the property messageManager.
	 */
	public void setMessageManager(MessageManager messageManager) {
		this.messageManager = messageManager;
	}

	/**
	 * @return alterMemberManager : return the property alterMemberManager.
	 */
	public AlterMemberManager getAlterMemberManager() {
		return alterMemberManager;
	}

	/**
	 * @param alterMemberManager
	 *            : set the property alterMemberManager.
	 */
	public void setAlterMemberManager(AlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}

	public RecompenseService getRecompenseService() {
		return recompenseService;
	}

	public void setRecompenseService(RecompenseService recompenseService) {
		this.recompenseService = recompenseService;
	}

	public WorkflowManager getWorkflowManager() {
		return workflowManager;
	}

	public void setWorkflowManager(WorkflowManager workflowManager) {
		this.workflowManager = workflowManager;
	}

	public RecompenseManagerImpl() throws Exception {
	}

	// ***************************************************工作流********************************************************
	/**
	 * @Title: performAction
	 * @Description: 执行理赔新增、修改保存、删除、提交等业务操作
	 * @param @param app 业务数据
	 * @param @param actionId 返回当前执行的事件Id
	 * @param @param userId 当前操作人员
	 * @return void 返回类型
	 */
	@Override
	@Transactional
	public void performAction(Map appMap, int actionId, String userId) {

		if (validateRecompenseData(appMap, actionId)) {
			// 获取传入的理赔单
			RecompenseApplication app = (RecompenseApplication) appMap
					.get(Constants.RECOMPENSE_APPLICATION);
			Long workflowId = app.getWorkflowId();
			if (workflowId == null) {
				// 理赔单初始化
				workflowId = workflowManager.initWorkflow(userId,
						Constants.RECOMPENSE_WORKFLOW_NAME,
						Constants.RECOMPENSE_WORKFLOW_INIT, appMap);
				app.setWorkflowId(workflowId);
			}
			// 理赔调用工作流
			workflowManager.doAction(userId, workflowId, actionId, appMap);
		}
	}

	/**
	 * 
	 * @Title: validateRecompenseData
	 * @Description: 检查理赔单数据
	 * @param @param appMap
	 * @param @param actionId
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean validateRecompenseData(Map appMap, int actionId) {
		boolean result = false;
		// 获取理赔单数据
		RecompenseApplication app = (RecompenseApplication) appMap
				.get(Constants.RECOMPENSE_APPLICATION);
		Map<String, List<IssueItem>> issueItemModifyMap = (Map<String, List<IssueItem>>) appMap
				.get(Constants.RECOMPENSE_ISSUEITEM_MAP);
		if (issueItemModifyMap != null) {
			issueItemModifyMap.put(Constants.LIST_TYPE_ORIGINAL,
					app.getIssueItemList());
		}
		Map<String, List<GoodsTrans>> goodsTransModifyMap = (Map<String, List<GoodsTrans>>) appMap
				.get(Constants.RECOMPENSE_GOODSTRANS_MAP);
		if (goodsTransModifyMap != null) {
			goodsTransModifyMap.put(Constants.LIST_TYPE_ORIGINAL,
					app.getGoodsTransList());
		}

		Map<String, List<DeptCharge>> deptChargeModifyMap = (Map<String, List<DeptCharge>>) appMap
				.get(Constants.RECOMPENSE_DEPTCHARGE_MAP);
		if (deptChargeModifyMap != null) {
			deptChargeModifyMap.put(Constants.LIST_TYPE_ORIGINAL,
					app.getDeptChargeList());
		}
		// 校验不用业务操作的数据
		switch (actionId) {
		case Constants.RECOMPENSE_SUBMIT_ACTION:
			if (app.getWaybill() != null
					&& getWaybillInfoByNo(app.getWaybill().getWaybillNumber(),
							app.getRecompenseType()) != null) {
				return RecompenseValidator.validateRecompenseReportCreate(app);
			} else {
				return false;
			}
		case Constants.RECOMPENSE_REPORT_SAVE_ACTION:
			return RecompenseValidator.validateRecompenseReportUpdate(app,
					issueItemModifyMap, goodsTransModifyMap);
		case Constants.RECOMPENSE_OVERPAY_SUBMIT_ACTION:
			return RecompenseValidator.validateRecompenseOverpay(app);
		default:
			result = true;
			break;
		}
		return result;
	}

	// ***************************************************查询和其他********************************************************

	/**
	 * 
	 * @Title: getRecompenseApplicationByUser
	 * @Description: 查询当前用户的理赔
	 * @param @param userId
	 * @param @return
	 * @return List<RecompenseApplication> 返回类型
	 * @throws
	 */
	@Override
	@Transactional
	public List<RecompenseApplication> getRecompenseApplicationByUser(
			String userId) {
		List<RecompenseApplication> appList = recompenseService
				.getUnfinishedRecompenseAppByUser(userId);
		return appList;
	}

	/**
	 * 
	 * @Title: getRecompenseApplicationByCustomer
	 * @Description: 查询客户已有理赔记录，排除当前理赔单
	 * @param @param customerId
	 * @param @param recompenseId
	 * @param @param startTime
	 * @param @param endTime
	 * @param @return
	 * @return List<RecompenseApplication> 返回类型
	 * @throws
	 */
	@Override
	@Transactional
	public List<RecompenseApplication> getRecompenseApplicationByCustomer(
			String customerId, String recompenseId, Date startTime, Date endTime) {
		List<RecompenseApplication> appList = recompenseService
				.getCustomerHistoryRecompenseApp(customerId, recompenseId,
						startTime, endTime);
		return appList;
	}

	/**
	 * 
	 * @Title: getRecompenseApplicationStatByCustomer
	 * @Description: 查询客户已有理赔记录统计，排除当前理赔单
	 * @param @param customerId
	 * @param @param recompenseId
	 * @param @param startTime
	 * @param @param endTime
	 * @param @return
	 * @return RecompenseApplication 返回类型
	 * @throws
	 */
	@Transactional
	public RecompenseApplication getRecompenseApplicationStatByCustomer(
			List<RecompenseApplication> appList) {
		double recompenseAmount = 0d;
		double normalAmount = 0d;
		double realAmount = 0d;
		double insurAmount = 0d;
		if (appList != null && appList.size() > 0) {
			for (RecompenseApplication rex : appList) {
				if (rex.getRecompenseAmount() != null) {
					recompenseAmount = recompenseAmount
							+ rex.getRecompenseAmount();
				}
				if (rex.getNormalAmount() != null) {
					normalAmount = normalAmount + rex.getNormalAmount();
				}
				if (rex.getRealAmount() != null) {
					realAmount = realAmount + rex.getRealAmount();
				}
				if (rex.getWaybill() != null
						&& rex.getWaybill().getInsurAmount() != null) {
					insurAmount = insurAmount
							+ rex.getWaybill().getInsurAmount();
				}
			}
		}
		RecompenseApplication app = new RecompenseApplication();
		app.setRecompenseAmount(recompenseAmount);
		app.setNormalAmount(normalAmount);
		app.setRealAmount(realAmount);
		Waybill wb = new Waybill();
		wb.setInsurAmount(insurAmount);
		app.setWaybill(wb);
		return app;

	}

	/**
	 * @Title: getRecompenseApplicationById
	 * @Description: 按理赔单的Id查询理赔单信息
	 * @param @param userId 操作人员
	 * @param @param recompenseId 理赔单Id
	 * @param @return 理赔单信息
	 * @return RecompenseApplication 返回类型
	 */
	@Override
	@Transactional
	public Map getRecompenseApplicationById(String recompenseId, User user) {
		Map map = new HashMap();
		RecompenseApplication app = recompenseService
				.getRecompenseApplicationById(recompenseId);
		map.put(Constants.RECOMPENSE_APPLICATION, app);
		map.put(Constants.RECOMPENSE_CURRENT_USER, user);
		List<String> deptIds = recompenseService.getDeptIdsByUserId(user
				.getId());
		map.put(Constants.RECOMPENSE_ADMIN_DEPT_LIST, deptIds);
		int[] actions;
		int[] steps;
		if (app.getWorkflowId() != null && app.getWorkflowId() > 0) {
			actions = workflowManager.getAvailableActions(user.getId(),
					app.getWorkflowId(), map);
			steps = workflowManager.getCurrentSteps(user.getId(),
					app.getWorkflowId());
		} else {
			actions = new int[0];
			steps = new int[0];
		}
		map.put("actions", actions);
		map.put("steps", steps);

		return map;
	}

	/**
	 * 
	 * @description 根据条件查询理赔-如果运单号或客户编号不为空时其他条件失效.
	 * @author 安小虎
	 * @version 0.1 2012-4-5
	 * @param 理赔查询对象
	 * @date 2012-4-5
	 * @return 理赔对对象集合
	 * @update 2012-4-5 上午9:30:16
	 */
	@Override
	@Transactional
	public List<RecompenseApplication> searchRecompenseByCondition(
			RecompenseSearchCondition condition, User user) {
		String recompenseMethod = condition.getRecompenseMethod();
		if (Constants.ALL.equals(recompenseMethod)) {
			condition.setRecompenseMethod(null);
		} else if (Constants.OVERPAY_TYPE.equals(recompenseMethod)) {
			condition.setOverpay(true);
			condition.setRecompenseMethod(null);
		}
		String recompenseType = condition.getRecompenseType();
		if (Constants.ALL.equals(recompenseType)) {
			condition.setRecompenseType(null);
		}
		String transType = condition.getTransType();
		if (Constants.ALL.equals(transType)) {
			condition.setTransType(null);
		}
		String roleId = getUserRoleId(user);
		String recompenseState = condition.getRecompenseState();
		List<String> list = genRecompenseStateList(roleId,
				condition.isInitSearch(), recompenseState);
		condition.setRecompenseStateList(list);
		String[] deptIds = getUserDeptIds(user);
		condition = setRecompenseDeptConditiont(condition, roleId, deptIds,
				user);
		if (roleId.equals(Constants.ROLE_ADMIN)) {
			condition.setRoleType(1);
		} else if (roleId.equals(Constants.ROLE_MANAGER)) {
			condition.setRoleType(2);
		} 
		return recompenseService.searchRecompenseByCondition(condition);
	}

	private RecompenseSearchCondition convertRecompense(
			RecompenseSearchCondition condition) {
		String recompenseAmount = condition.getRecompenseAmount();
		String realAmount = condition.getRealAmount();

		if (recompenseAmount != null && !"".equals(recompenseAmount)) {
			if ("0-1000".equals(recompenseAmount)) {
				condition.setBeginRecompenseAmount("");
				condition.setEndRecompenseAmount("1000");
			} else if ("1000-3000".equals(recompenseAmount)) {
				condition.setBeginRecompenseAmount("1000");
				condition.setEndRecompenseAmount("3000");
			} else if ("3000-5000".equals(recompenseAmount)) {
				condition.setBeginRecompenseAmount("3000");
				condition.setEndRecompenseAmount("5000");
			} else if ("5000-10000".equals(recompenseAmount)) {
				condition.setBeginRecompenseAmount("5000");
				condition.setEndRecompenseAmount("10000");
			} else if ("10000-".equals(recompenseAmount)) {
				condition.setBeginRecompenseAmount("10000");
				condition.setEndRecompenseAmount("");
			}
		}

		if (realAmount != null && !"".equals(realAmount)) {
			if ("0-1000".equals(realAmount)) {
				condition.setBeginRealAmount("");
				condition.setEndRealAmount("1000");
			} else if ("1000-3000".equals(realAmount)) {
				condition.setBeginRealAmount("1000");
				condition.setEndRealAmount("3000");
			} else if ("3000-5000".equals(realAmount)) {
				condition.setBeginRealAmount("3000");
				condition.setEndRealAmount("5000");
			} else if ("5000-10000".equals(realAmount)) {
				condition.setBeginRealAmount("5000");
				condition.setEndRealAmount("10000");
			} else if ("10000-".equals(realAmount)) {
				condition.setBeginRealAmount("10000");
				condition.setEndRealAmount("");
			}
		}
		return condition;
	}

	/**
	 * 
	 * <p>
	 * Description:设置理赔条件<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param condition
	 * @param roleId
	 * @param deptIds
	 * @return RecompenseSearchCondition
	 */
	private RecompenseSearchCondition setRecompenseDeptConditiont(
			RecompenseSearchCondition condition, String roleId,
			String[] deptIds, User user) {

		if (roleId.equals(Constants.ROLE_CASHER)) {
			condition.setReportDeptIdList(null);
			condition.setCashierDept(deptIds[0]);
		} else if (user.getRoleids().contains(Constants.ROLE_EXPRESS)) {
			condition.setReportDeptIdList(null);
			condition.setConfirmAmountDept(deptIds[0]);
		} else if (roleId.equals(Constants.ROLE_MANAGER)
				|| roleId.equals(Constants.ROLE_ATTENDANT)) {
			condition.setReportDeptIdList(Arrays.asList(deptIds));
		} else if (roleId.equals(Constants.ROLE_ADMIN)
				&& (condition.getReportDept() == null || "".equals(condition
						.getReportDept().trim()))
				&& (condition.getBelongArea() == null || "".equals(condition
						.getBelongArea().trim()))) {
			condition.setDeptIdList(Arrays.asList(deptIds));
		} else {
			if (condition.getReportDept() != null
					&& !"".equals(condition.getReportDept().trim())) {
				String[] reportDeptIdList = { condition.getReportDept() };
				condition.setReportDeptIdList(Arrays.asList(reportDeptIdList));
			}
			if (condition.getBelongArea() != null
					&& !"".equals(condition.getBelongArea().trim())) {
				String[] deptIdList = { condition.getBelongArea() };
				condition.setDeptIdList(Arrays.asList(deptIdList));
			}
		}
		return condition;
	}

	/**
	 * 
	 * 
	 * <p>
	 * Description:获得理赔的状态列表<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param roleId
	 * @param initSearch
	 * @param recompenseState
	 * @return List<String>
	 */
	private List<String> genRecompenseStateList(String roleId,
			boolean initSearch, String recompenseState) {
		List<String> list = new ArrayList<String>();
		if (initSearch) {
			if (roleId.equals(Constants.ROLE_ADMIN)) {
				list.add(Constants.STATUS_SUBMITED);
				list.add(Constants.STATUS_DOC_CONFIRMED);
				list.add(Constants.STATUS_AMOUNT_CONFIRMED);
				list.add(Constants.STATUS_OVERPAY_APPROVED);
			} else if (roleId.equals(Constants.ROLE_MANAGER)) {
				list.add(Constants.STATUS_SUBMITED);
				list.add(Constants.STATUS_HANDLED);
				list.add(Constants.STATUS_PAY_FAILED);
				list.add(Constants.STATUS_APPROVED);
				
			} else {
				list = null;
			}
		} else {
			if (recompenseState != null && !"".equals(recompenseState)
					&& !"ALL".equals(recompenseState)) {
				list.add(recompenseState);
			} else {
				list = null;
			}
		}
		return list;
	}

	/**
	 * 
	 * @Title: searchRecompenseForMonitor
	 * @Description: 查询理赔监控
	 * @param @param condition
	 * @param @return
	 * @return List<RecompenseApplication> 返回类型
	 * @throws
	 */
	@Override
	@Transactional
	public List<RecompenseApplication> searchRecompenseForMonitor(
			RecompenseSearchCondition condition) {
		String recompenseMethod = condition.getRecompenseMethod();
		if ("ALL".equals(recompenseMethod)) {
			condition.setRecompenseMethod(null);
		}
		String recompenseType = condition.getRecompenseType();
		if ("ALL".equals(recompenseType)) {
			condition.setRecompenseType(null);
		}
		String custLevel = condition.getCustomerLevel();
		if ("ALL".equals(custLevel)) {
			condition.setCustomerLevel(null);
		}
		String blongArea = condition.getBelongArea();
		if ("ALL".equals(blongArea)) {
			condition.setBelongArea(null);
		} else {
			if (blongArea != null && !(blongArea.trim()).equals("")) {
				condition.getDeptIdList().add(blongArea);
			}
		}
		String recompenseState = condition.getRecompenseState();
		if (recompenseState != null && !"".equals(recompenseState)
				&& !"ALL".equals(recompenseState)) {
			List<String> list = new ArrayList<String>();
			list.add(recompenseState);
			condition.setRecompenseStateList(list);
		} else {
			condition.setRecompenseStateList(null);
		}
		return recompenseService.searchRecompenseByCondition(condition);
	}

	/**
	 * 
	 * @Title: getRecompenseCountForMonitor
	 * @Description: 理赔监控计数
	 * @param @param condition
	 * @param @return
	 * @return int 返回类型
	 * @throws
	 */
	@Override
	@Transactional
	public int getRecompenseCountForMonitor(RecompenseSearchCondition condition) {
		condition = convertRecompense(condition);
		String recompenseMethod = condition.getRecompenseMethod();
		if ("ALL".equals(recompenseMethod)) {
			condition.setRecompenseMethod(null);
		}
		String recompenseType = condition.getRecompenseType();
		if ("ALL".equals(recompenseType)) {
			condition.setRecompenseType(null);
		}
		String transType = condition.getTransType();
		if (Constants.ALL.equals(transType)) {
			condition.setTransType(null);
		}
		String custLevel = condition.getCustomerLevel();
		if ("ALL".equals(custLevel)) {
			condition.setCustomerLevel(null);
		}
		String blongArea = condition.getBelongArea();
		if ("ALL".equals(blongArea)) {
			condition.setBelongArea(null);
		} else {
			if (blongArea != null && !(blongArea.trim()).equals("")) {
				condition.getDeptIdList().add(blongArea);
			}
		}
		String recompenseState = condition.getRecompenseState();
		if (recompenseState != null && !"".equals(recompenseState)
				&& !"ALL".equals(recompenseState)) {
			List<String> list = new ArrayList<String>();
			list.add(recompenseState);
			condition.setRecompenseStateList(list);
		} else {
			condition.setRecompenseStateList(null);
		}
		return this.recompenseService.getRecompenseCountByCondition(condition);
	}

	/**
	 * 
	 * @Title: getWorkFlowByOwner
	 * @Description: 获取用户工作流
	 * @param @param userId
	 * @param @return
	 * @return List 返回类型
	 * @throws
	 */
	@Override
	@Transactional
	public List getWorkFlowByOwner(String userId) {
		String workflowName = Constants.RECOMPENSE_WORKFLOW_NAME;
		return workflowManager.getWorkflowByOwner(userId, workflowName);
	}

	/**
	 * 
	 * @Title: deleteRecompenseApplication
	 * @Description: 经理删除未提交理赔
	 * @param @param userId
	 * @param @param recompenseId
	 * @param @param workflowId
	 * @return void 返回类型
	 * @throws
	 */
	@Override
	@Transactional
	public void deleteRecompenseApplication(String userId, String recompenseId,
			long workflowId) {
		workflowManager.killWorkflow(userId, workflowId);

	}

	/**
	 * 
	 * @Title: addMessageForRecompense
	 * @Description: 增加理赔的跟进消息
	 * @param @param reportDept
	 * @param @param deptId
	 * @param @param message
	 * @param @param user
	 * @param @return
	 * @return List<Message> 返回类型
	 * @throws
	 */
	@Override
	@Transactional
	public List<Message> addMessageForRecompense(String reportDept,
			String deptId, Message message, User user) {
		
		String roleId = getUserRoleId(user);
		String toRole = "";
		if (roleId.equals(Constants.ROLE_ADMIN)) {
			toRole = Constants.ROLE_MANAGER;
		} else {
			toRole = Constants.ROLE_ADMIN;
		}
		List<String> userIds = getMessageUserIds(toRole, reportDept, deptId);
		com.deppon.crm.module.common.shared.domain.Message todoMessage = new com.deppon.crm.module.common.shared.domain.Message();
		todoMessage.setTasktype(Constants.TODO_TASK_TYPE);
		todoMessage.setTaskcount(1);
		if (userIds != null && userIds.size() > 0) {
			RecompenseApplication app = recompenseService
					.getRecompenseApplicationById(message.getRecompenseId());
			String msg = Constants.RECOMPENSE_VOUCHER + ":"
					+ app.getWaybill().getWaybillNumber() + ","
					+ message.getContent();
			todoMessage.setIshaveinfo(msg);
			for (String userId : userIds) {
				todoMessage.setUserid(Integer.parseInt(userId));
				messageManager.addMessage(todoMessage);
			}
		}
		message.setUserId(user.getId());
		message.setUserName(user.getEmpCode().getEmpName());
		message.setCreateTime(new Date());
		recompenseService.insertMessage(message);

		return recompenseService.getMessageByRecompenseId(message
				.getRecompenseId());
	}

	/**
	 * 
	 * @Title: addTodoMessage
	 * @Description: 增加代办消息
	 * @param @param todoMessage
	 * @return void 返回类型
	 * @throws
	 */
	@Transactional
	public void addTodoMessage(
			com.deppon.crm.module.common.shared.domain.Message todoMessage) {
		messageManager.addMessage(todoMessage);
	}

	/**
	 * 
	 * @Title: getMessageUserIds
	 * @Description: 查询角色所属部门
	 * @param @param roleId
	 * @param @param reportDept
	 * @param @param deptId
	 * @param @return
	 * @return List<String> 返回类型
	 * @throws
	 */
	@Transactional
	public List<String> getMessageUserIds(String roleId, String reportDept,
			String deptId) {
		List<String> userIds = new ArrayList<String>();
		if (roleId.equals(Constants.ROLE_ADMIN)) {
			List<UserRoleDeptRelation> urdrList = recompenseService
					.getUserRoleDeptRelationByDeptId(deptId);
			for (UserRoleDeptRelation userRoleDeptRelation : urdrList) {
				userIds.add(userRoleDeptRelation.getUser().getId());
			}
		} else {
			List<User> userList = userService.queryUsersByDeptAndRole(
					reportDept, roleId);
			for (User item : userList) {
				userIds.add(item.getId());
			}
		}
		return userIds;
	}

	/**
	 * 
	 * @Title: getWaybillInfoByNo
	 * @Description: 根据凭证号获得运单
	 * @param @param voucherNo
	 * @param @param recompenseMethod
	 * @param @return
	 * @return Waybill 返回类型
	 * @throws
	 */
	@Override
	@Transactional
	public Waybill getWaybillInfoByNo(String voucherNo, String recompenseMethod) {

		Waybill wb = null;
		RecompenseApplication oldApp = recompenseService
				.getRecompenseApplicationByVoucherNo(voucherNo);

		FossWaybillInfo waybillInfo = this.recompenseService
				.getWaybillRecompense(voucherNo);
		// 把运输性质转换为中文
		String tranType = "";
		if (null != waybillInfo) {
			// 汽运
			if (Constants.TRANS_VEHICLE.equals(waybillInfo.getTranType())) {
				tranType = Constants.TRANS_VEHICLE_NAME;
				// 空运
			} else if (Constants.TRANS_AIRCRAFT.equals(waybillInfo
					.getTranType())) {
				tranType = Constants.TRANS_AIRCRAFT_NAME;
				// 快递
			} else if (Constants.TRANS_EXPRESS
					.equals(waybillInfo.getTranType())) {
				tranType = Constants.TRANS_EXPRESS_NAME;
			}

		}

		OaAccidentInfo unbilledOaAccident = this.recompenseService
				.getAccidentByAccidentCode(voucherNo);
		List<OaAccidentInfo> waybillAccidentList = this.recompenseService
				.getAccidentByWaybillNum(voucherNo);
		if (RecompenseValidator.canCreateRecompense(recompenseMethod,
				waybillInfo, waybillAccidentList, unbilledOaAccident, oldApp)) {
			if (Constants.UNBILLED.equals(recompenseMethod)) {
				wb = new Waybill(voucherNo, unbilledOaAccident.getCargoName(),
						unbilledOaAccident.getTransportType(),
						unbilledOaAccident.getReceivingDept(),
						unbilledOaAccident.getInsuredAmount(),
						unbilledOaAccident.getContactName(),
						unbilledOaAccident.getContactType(),
						unbilledOaAccident.getStartStation(),
						unbilledOaAccident.getDestinationStation(),
						unbilledOaAccident.getPackaging(),
						unbilledOaAccident.getCargoWeight(),
						unbilledOaAccident.getDateShiped(), null, null);
				// 把运输类型转换中文到英文
				if (null != unbilledOaAccident.getTransportType()) {
					// 汽运
					if (Constants.TRANS_VEHICLE_NAME.equals(unbilledOaAccident
							.getTransportType())) {
						wb.setTransType(Constants.TRANS_VEHICLE);
						// 空运
					} else if (Constants.TRANS_AIRCRAFT_NAME
							.equals(unbilledOaAccident.getTransportType())) {
						wb.setTransType(Constants.TRANS_AIRCRAFT);
						// 快递
					} else if (Constants.TRANS_EXPRESS_NAME
							.equals(unbilledOaAccident.getTransportType())) {
						wb.setTransType(Constants.TRANS_EXPRESS);
					}
				} else {
					wb.setTransType(Constants.TRANS_VEHICLE);
				}

			} else {
				String tel = waybillInfo.getSenderMobile();
				if (tel == null) {
					tel = waybillInfo.getSenderPhone();
				} else if (waybillInfo.getSenderPhone() != null) {
					tel = waybillInfo.getSenderPhone() + "/" + tel;
				}

				wb = new Waybill(voucherNo, waybillInfo.getGoodName(),
						waybillInfo.getTranType(),
						waybillInfo.getReceiveDeptName(), waybillInfo
								.getInsuranceValue().doubleValue(),
						waybillInfo.getSender(), tel,
						waybillInfo.getDeparture(),
						waybillInfo.getDestination(), waybillInfo.getPacking(),
						waybillInfo.getPieces() + "/" + waybillInfo.getWeight()
								+ "/" + waybillInfo.getCubage(),
						waybillInfo.getSendTime(),
						waybillInfo.getSenderNumber(),
						waybillInfo.getConsigneeNumber());
			}
		}
		return wb;
	}

	/**
	 * 
	 * <p>
	 * Description:获取运单信息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param waybillNum
	 * @return Waybill
	 */
	@Transactional
	public Waybill getWaybillByWaybillNum(String waybillNum) {
		Waybill wb = null;
		User user = (User) UserContext.getCurrentUser();
		RecompenseApplication oldApp = recompenseService
				.getRecompenseApplicationByVoucherNo(waybillNum);
		List<OaAccidentInfo> waybillAccidentList = this.recompenseService
				.getAccidentByWaybillNum(waybillNum);
		FossWaybillInfo waybillInfo = this.recompenseService
				.getWaybillRecompense(waybillNum);
		// 如果运单号查询不到抛出异常提示
		if (null == waybillInfo) {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_NOEXIST_WAYBILL_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
		if (RecompenseValidator.canCreateOnlineRecompense(waybillInfo,
				waybillAccidentList, oldApp)) {
			String tel = waybillInfo.getSenderMobile();
			if (tel == null) {
				tel = waybillInfo.getSenderPhone();
			} else if (waybillInfo.getSenderPhone() != null) {
				tel = waybillInfo.getSenderPhone() + "/" + tel;
			}

			wb = new Waybill(waybillNum, waybillInfo.getGoodName(),
					waybillInfo.getTranType(),
					waybillInfo.getReceiveDeptName(), waybillInfo
							.getInsuranceValue().doubleValue(),
					waybillInfo.getSender(), tel, waybillInfo.getDeparture(),
					waybillInfo.getDestination(), waybillInfo.getPacking(),
					waybillInfo.getPieces() + "/" + waybillInfo.getWeight()
							+ "/" + waybillInfo.getCubage(),
					waybillInfo.getSendTime(), waybillInfo.getSenderNumber(),
					waybillInfo.getConsigneeNumber());

		}

		return wb;
	}
	
	/**
	 * 
	 * <p>
	 * Description:在线理赔监控查看详情是获取运单号
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-11-16下午4:58:38
	 * @param waybillNum
	 * @return
	 * Waybill
	 * @update 2013-11-16下午4:58:38
	 */
	@Transactional
	public Waybill getWaybillByNumForOnlineMonitor(String waybillNum) {
		Waybill wb = null;
		User user = (User) UserContext.getCurrentUser();
		RecompenseApplication oldApp = recompenseService
				.getRecompenseApplicationByVoucherNo(waybillNum);
		List<OaAccidentInfo> waybillAccidentList = this.recompenseService
				.getAccidentByWaybillNum(waybillNum);
		FossWaybillInfo waybillInfo = this.recompenseService
				.getWaybillRecompense(waybillNum);
		// 如果运单号查询不到抛出异常提示
		if (null == waybillInfo) {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_NOEXIST_WAYBILL_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
		
		//设置电话
		String tel = waybillInfo.getSenderMobile();
		if (tel == null) {
			tel = waybillInfo.getSenderPhone();
		} else if (waybillInfo.getSenderPhone() != null) {
			tel = waybillInfo.getSenderPhone() + "/" + tel;
		}
		
		//组装运单信息
		wb = new Waybill(waybillNum, waybillInfo.getGoodName(),
				waybillInfo.getTranType(),
				waybillInfo.getReceiveDeptName(), waybillInfo
						.getInsuranceValue().doubleValue(),
				waybillInfo.getSender(), tel, waybillInfo.getDeparture(),
				waybillInfo.getDestination(), waybillInfo.getPacking(),
				waybillInfo.getPieces() + "/" + waybillInfo.getWeight()
						+ "/" + waybillInfo.getCubage(),
				waybillInfo.getSendTime(), waybillInfo.getSenderNumber(),
				waybillInfo.getConsigneeNumber());
		return wb;
	}
	
	/**
	 * 
	 * <p>
	 * Description:取消创建在线理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param WaybillNum
	 * @return
	 * 
	 */
	public boolean canCreateOnlineApply(String WaybillNum) {
		try {
			Waybill wb = getWaybillByWaybillNum(WaybillNum);
			if (null != wb) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * 
	 * @Title: getAccidentByNo
	 * @Description: 根据凭证号获得差错信息
	 * @param @param voucherNo
	 * @param @param recompenseMethod
	 * @param @return
	 * @return List<OaAccidentInfo> 返回类型
	 * @throws
	 */
	@Override
	@Transactional
	public List<OaAccidentInfo> getAccidentByNo(String voucherNo,
			String recompenseMethod) {
		List<OaAccidentInfo> list = new ArrayList<OaAccidentInfo>();
		if (Constants.UNBILLED.equals(recompenseMethod)) {
			// 1、未开单类理赔：差错信息取OA未开单差错的对应差错编号的处理结果信息及处理人信息。
			OaAccidentInfo oaai = this.recompenseService
					.getAccidentByAccidentCode(voucherNo);
			list.add(oaai);
		} else {
			// 2、已开单理赔（异常签收理赔及丢货理赔）：差错信息取该理赔单号派送出库时填写的签收情况及OA差错信息
			list = this.recompenseService.getAccidentByWaybillNum(voucherNo);
		}
		return list;
	}

	/**
	 * 
	 * @Title: getRecompenseForMonitoring
	 * @Description: 查询理赔监控
	 * @param @param condition
	 * @param @return
	 * @return List<RecompenseApplication> 返回类型
	 * @throws
	 */
	@Override
	@Transactional
	public List<RecompenseApplication> getRecompenseForMonitoring(
			RecompenseSearchCondition condition) {
		condition = convertRecompense(condition);
		if (Constants.OVERPAY_TYPE.equals(condition.getRecompenseMethod())) {
			condition.setOverpay(true);
			condition.setRecompenseMethod(null);
		}
		String transType = condition.getTransType();
		if (Constants.ALL.equals(transType)) {
			condition.setTransType(null);
		}
		List<RecompenseApplication> appList = recompenseService
				.searchRecompenseByCondition(condition);
		return appList;
	}

	/**
	 * 
	 * @Title: reminderForRecompenseMonitor
	 * @Description: 理赔监控催办
	 * @param @param id
	 * @param @return
	 * @return boolean 返回类型
	 * @throws
	 */
	@Override
	@Transactional
	public boolean reminderForRecompenseMonitor(String id) {
		RecompenseApplication recompense = recompenseService
				.getRecompenseApplicationById(id);

		// 1、催办后，对应大区的理赔专员，系统信息提醒催办信息（待办事宜）
		com.deppon.crm.module.common.shared.domain.Message message = new com.deppon.crm.module.common.shared.domain.Message();
		message.setTasktype(Constants.TODO_TASK_TYPE);
		message.setTaskcount(1);
		message.setIshaveinfo(recompense.getWaybill().getWaybillNumber()
				+ " 理赔催办");

		List<String> userIds = getMessageUserIds(Constants.ROLE_ADMIN,
				recompense.getReportDept(), recompense.getDeptId());
		if (userIds != null && userIds.size() > 0) {
			for (String userId : userIds) {
				message.setUserid(Integer.parseInt(userId));
				messageManager.addMessage(message);
			}
		}

		// 2、催单次数加1
		int count = 0;
		Integer hastenCount = recompense.getHastenCount();
		if (hastenCount != null && hastenCount != 0) {
			count = hastenCount + 1;
		}
		RecompenseApplication rec = new RecompenseApplication();
		rec.setId(id);
		rec.setHastenCount(count);
		rec.setLastHastenTime(new Date());
		return recompenseService.updateRecompense(rec);
	}

	// *************************************大区设置***************************************

	/**
	 * 
	 * @Title: getUserRoleDeptRelationByUserId
	 * @Description: 查询当前角色已设置的大区
	 * @param @param userId
	 * @param @return
	 * @return List<UserRoleDeptRelation> 返回类型
	 * @throws
	 */
	@Override
	public List<UserRoleDeptRelation> getUserRoleDeptRelationByUserId(
			String userId) {
		return recompenseService.getUserRoleDeptRelationByUserId(userId);
	}

	/**
	 * 
	 * @Title: getAllUserRoleDeptRelation
	 * @Description: 得到已有得大区设置信息
	 * @param @param start
	 * @param @param limit
	 * @param @return
	 * @return List<UserRoleDeptRelation> 返回类型
	 * @throws
	 */
	@Override
	public List<UserRoleDeptRelation> getAllUserRoleDeptRelation(int start,
			int limit) {
		return recompenseService.getAllUserRoleDeptRelation(start, limit);
	}

	/**
	 * 
	 * @Title: deleteUserRoleDeptRelationById
	 * @Description: 根据id删除大区设置信息
	 * @param @param id
	 * @return void 返回类型
	 * @throws
	 */
	@Override
	public void deleteUserRoleDeptRelationById(String id) {
		recompenseService.deleteUserRoleDeptRelationById(id);
	}

	/**
	 * 
	 * @Title: insertUserRoleDepartment
	 * @Description: 大区设置
	 * @param @param userId
	 * @param @param roleId
	 * @param @param deptId
	 * @return void 返回类型
	 * @throws
	 */
	@Override
	public void insertUserRoleDepartment(String userId, String roleId,
			String deptId) {
		recompenseService.insertUserRoleDepartment(userId, roleId, deptId);
	}

	/**
	 * 
	 * @Title: searchOnlineApplyByCondition
	 * @Description: 在线理赔申请查询
	 * @param @param waybillNum
	 * @param @param deptId
	 * @param @param start
	 * @param @param limit
	 * @param @return
	 * @return List<OnlineApply> 返回类型
	 * @throws
	 */
	@Override
	@Transactional
	public List<OnlineApply> searchOnlineApplyByCondition(String waybillNum,
			String deptId, int start, int limit) {
		if (waybillNum != null && !"".equals(waybillNum)) {
			return this.recompenseService
					.searchOnlineApplyByWaybillNum(waybillNum);
		} else {
			List<String> statusList = new ArrayList<String>();
			statusList.add(Constants.STATUS_WAIT_ACCEPT);// 待处理
			statusList.add(Constants.STATUS_ONLINE_REFUSED);// 在线理赔审批退回
			return this.recompenseService.searchOnlineApplyByCondition(deptId,
					statusList, start, limit);
		}

	}

	/**
	 * 
	 。 * @description 在线理赔申请受理.
	 * 
	 * @author 安小虎
	 * @version 0.1 2012-4-9
	 * @param 在线理赔申请对象
	 * @date 2012-4-9
	 * @return boolean：成功与否
	 * @update 2012-4-9 上午10:10:17
	 */
	@SuppressWarnings("serial")
	@Override
	@Transactional
	public RecompenseApplication handleOnlineApply(String id, User user) {
		OnlineApply onlineApply = this.recompenseService.getOnlineApplyById(id);
		String status = onlineApply.getStatus();
		String applyDept = onlineApply.getApplyDeptId();
		String currentDept = user.getEmpCode().getDeptId().getId();
		
		if (null != onlineApply && null != applyDept
				&& !applyDept.equals(currentDept)) {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.ONLINEAPPLY_ACCEPT_DEPT_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
		// 校验状态
		if (status == null || !Constants.STATUS_WAIT_ACCEPT.equals(status)) {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_STATUS_WAIT_ACCEPT);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}

		// 1、校验 只需判断wb！=null
		Waybill wb = getWaybillByWaybillNum(onlineApply.getWaybillNumber());
		if (wb != null) {

			// 2、把在线理赔申请转换为理赔单
			RecompenseApplication recompense = new RecompenseApplication();
			recompense.setOnlineApplyId(id);
			recompense.setRecompenseMethod(onlineApply.getRecompenseMethod());
			recompense.setRecompenseType(onlineApply.getRecompenseType());
			recompense.setCompanyName(onlineApply.getCustomerId());
			recompense.setOpenName(onlineApply.getOpenName());
			recompense.setAccount(onlineApply.getAccount());
			recompense.setBankName(onlineApply.getBankName());
			recompense.setBranchName(onlineApply.getBranchName());
			
			if (onlineApply.getMobile() != null) {
				recompense.setCompanyPhone(onlineApply.getMobile());
			} else {
				recompense.setCompanyPhone(onlineApply.getTelphone());
			}

			recompense.setWaybill(wb);
			String applyPart = onlineApply.getApplyPart();
			Member customer = null;
			if (applyPart != null && applyPart.equals("receive")) {
				recompense.setClaimParty("2");
				customer = memberManager.getMemberByCustNumber(wb
						.getArriveCustomerId());
			} else {
				recompense.setClaimParty("1");
				customer = memberManager.getMemberByCustNumber(wb
						.getLeaveCustomerId());
			}
			recompense.setCustomer(customer);
			recompense.setRecompenseAmount(onlineApply.getRecompenseAmount());

			List<RecompenseAttachment> attachList = new ArrayList<RecompenseAttachment>();
			if (null != onlineApply.getFrontImage()
					&& !"".equals(onlineApply.getFrontImage())) {
				RecompenseAttachment frontImage = new RecompenseAttachment();
				frontImage.setAttachName("证件正面."
						+ getExtensionName(onlineApply.getFrontImage()));
				frontImage.setAttachAddress(onlineApply.getFrontImage());
				attachList.add(frontImage);
			}
			if (null != onlineApply.getBackImage()
					&& !"".equals(onlineApply.getBackImage())) {
				RecompenseAttachment backImage = new RecompenseAttachment();
				backImage.setAttachName("证件背面."
						+ getExtensionName(onlineApply.getBackImage()));
				backImage.setAttachAddress(onlineApply.getBackImage());
				attachList.add(backImage);
			}
			recompense.setAttachmentList(attachList);

			// recompense.setStatus(Constants.STATUS_SUBMITED);// 未处理
			return recompense;
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param filename
	 * @return String
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/**
	 * 
	 * @description 根据大区ID查询大区.
	 * @author 安小虎
	 * @version 0.1 2012-4-11
	 * @param 大区ID
	 * @date 2012-4-11
	 * @return 大区对象
	 * @update 2012-4-11 上午9:28:55
	 */
	@Override
	public Department getAreaById(String id) {
		return this.getDepartmentService().queryById(id);
	}

	/**
	 * 
	 * @Title: getBigAreaByDeptId
	 * @Description: 获取大区对象
	 * @param @param standcode
	 * @param @return
	 * @return Department 返回类型
	 * @throws
	 */
	@Override
	public Department getBigAreaByDeptId(String standcode) {
		return this.searchBigArea(standcode,false);
	}

	/**
	 * 
	 * @Title: getBizDeptByDeptId
	 * @Description: 获取事业部对象
	 * @param @param deptId
	 * @param @return
	 * @return Department 返回类型
	 * @throws
	 */
	public Department getBizDeptByDeptId(String deptId) {
		return this.getDepartmentService().getBizDeptByDeptId(deptId);
	}

	public List<Department> getAllBizDeptList() {
		return this.getDepartmentService().getBizDeptList();
	}

	@Override
	public Department getBigAreaByDeptCode(String deptCode) {
		return this.getDepartmentService().getBigAreaByDeptCode(deptCode);
	}

	@Override
	public Department getDeptByStandardCode(String deptCode) {
		return this.getDepartmentService().getDeptByStandardCode(deptCode);
	}

	@Override
	public List<Department> getBigAreaList() {
		return getDepartmentService().getBigAreaList();
	}

	public List<Department> getBigAreaListByName(String areaName) {
		return getDepartmentService().getBigAreaListByName(areaName);
	}

	/**
	 * 
	 * <p>
	 * Description:根据用户名获取用户列表<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param userName
	 * @param limit
	 * @return
	 * 
	 */
	@Transactional
	public List<User> getUserListByUserName(String userName, int limit) {
		int start = 0;
		userName = "%" + userName + "%";
		User user = new User();
		Employee employee = new Employee();
		employee.setEmpName(userName);
		user.setEmpCode(employee);
		return userService.queryAll(user, limit, start);
	}

	/**
	 * 
	 * <p>
	 * Description:根据名字获取部门别表<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param deptName
	 * @param limit
	 * @return
	 * 
	 */
	@Transactional
	public List<Department> getDeptListByDeptName(String deptName, int limit) {
		int start = 0;
		deptName = "%" + deptName + "%";
		Department dept = new Department();
		dept.setDeptName(deptName);
		return getDepartmentService().getDepartmentByDeptNameRow(deptName,
				limit);
	}

	/**
	 * 
	 * <p>
	 * Description:获取用户列表<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param empCode
	 * @param limit
	 * @return
	 * 
	 */
	@Transactional
	public List<User> getUserListByEmpCode(String empCode, int limit) {
		int start = 0;
		User user = new User();
		Employee employee = new Employee();
		employee.setEmpCode(empCode);
		user.setEmpCode(employee);
		return userService.queryAll(user, limit, start);
	}

	/**
	 * 
	 * <p>
	 * Description:在线理赔申请拒绝<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param id
	 * @return
	 * 
	 */
	@SuppressWarnings("serial")
	@Override
	@Transactional
	public boolean refuseOnlineApply(String id, User user) {
		OnlineApply oa = this.recompenseService.getOnlineApplyById(id);
		String status = oa.getStatus();
		String applyDept = oa.getApplyDeptId();
		String currentDept = user.getEmpCode().getDeptId().getId();
		if (null != oa && null != applyDept && !applyDept.equals(currentDept)) {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.ONLINEAPPLY_ACCEPT_DEPT_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
		// 校验状态
		if (status == null || !Constants.STATUS_WAIT_ACCEPT.equals(status)) {
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_STATUS_WAIT_ACCEPT);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}

		OnlineApply onlineApply = new OnlineApply();
		onlineApply.setId(id);
		// 设置在线理赔状态为已拒绝
		onlineApply.setStatus(Constants.STATUS_REJECTED);
		return this.recompenseService.updateOnlineApply(onlineApply);
	}

	@Override
	@Transactional
	public Long searchOnlineApplyCountByCondition(String waybillNum,
			String deptId) {
		if (waybillNum != null && !"".equals(waybillNum)) {
			Long result = 0L;
			List<OnlineApply> oaList = (List<OnlineApply>) this.recompenseService
					.searchOnlineApplyByWaybillNum(waybillNum);
			if (oaList != null && oaList.size() > 0) {
				result = 1L;
			}
			return result;
		} else {
			List<String> statusList = new ArrayList<String>();
			statusList.add(Constants.STATUS_WAIT_ACCEPT);// 待处理
			statusList.add(Constants.STATUS_ONLINE_REFUSED);// 在线理赔审批退回
			return this.recompenseService.getOnlineApplyCountByCondition(
					deptId, statusList);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:根据条件查询理赔总数<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param condition
	 * @param user
	 * @return
	 * 
	 */
	@Override
	@Transactional
	public int getRecompenseCountByCondition(
			RecompenseSearchCondition condition, User user) {
		String recompenseMethod = condition.getRecompenseMethod();
		if ("ALL".equals(recompenseMethod)) {
			condition.setRecompenseMethod(null);
		} else if (Constants.OVERPAY_TYPE.equals(recompenseMethod)) {
			condition.setOverpay(true);
			condition.setRecompenseMethod(null);
		}
		String recompenseType = condition.getRecompenseType();
		if ("ALL".equals(recompenseType)) {
			condition.setRecompenseType(null);
		}
		String transType = condition.getTransType();
		if (Constants.ALL.equals(transType)) {
			condition.setTransType(null);
		}
		String roleId = getUserRoleId(user);
		String recompenseState = condition.getRecompenseState();
		List<String> list = genRecompenseStateList(roleId,
				condition.isInitSearch(), recompenseState);
		condition.setRecompenseStateList(list);
		String[] deptIds = getUserDeptIds(user);
		condition = setRecompenseDeptConditiont(condition, roleId, deptIds,
				user);
		return this.recompenseService.getRecompenseCountByCondition(condition);
	}

	/**
	 * 
	 * <p>
	 * Description:发送短信<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param waybillNum
	 * @param deptId
	 * @param amount
	 * @param customePhone
	 * @param mrList
	 *            void
	 */
	@Transactional
	public void sendMobileMessage(String waybillNum, String deptId,
			Double amount, String customePhone, List<MessageReminder> mrList,
			String empCode, String deptCode) {
		if (amount > 1000 && customePhone != null && !customePhone.equals("")) {
			Department dept = getDepartmentService().queryById(deptId);
			String deptPhone = "--";
			if (dept != null) {
				deptPhone = dept.getPhone();
			}
			String customerMessage = MessageFormat.format(
					Constants.RECOMPENSE_CUSTOMER_MESSAGE,
					new Object[] { deptPhone });
			// recompenseService.sendSms(customePhone, customerMessage);
			recompenseService.sendSmsInfo(customePhone, customerMessage,
					empCode, deptCode);

		}
		if (mrList != null && mrList.size() > 0) {
			for (MessageReminder messageReminder : mrList) {
				String managerMessage = MessageFormat.format(
						Constants.RECOMPENSE_MANAGER_MESSAGE, new Object[] {
								waybillNum, amount });
				// recompenseService.sendSms(messageReminder.getPhoneNum(),managerMessage);
				recompenseService.sendSmsInfo(messageReminder.getPhoneNum(),
						managerMessage, empCode, deptCode);
			}
		}
	}

	/**
	 * 
	 * <p>
	 * Description:生成报表时间<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param userId
	 * @param oldStatus
	 * @param newStatus
	 * @return
	 * 
	 */
	@Transactional
	public RecompenseApplication generateRecompenseForReport(String userId,
			String oldStatus, String newStatus) {
		RecompenseApplication app = new RecompenseApplication();
		Date doneTime = new Date();
		app.setModifyUser(userId);
		app.setModifyDate(doneTime);
		if (oldStatus == null || newStatus == null) {
			return app;
		}
		if (oldStatus.equals(Constants.STATUS_SUBMITED)
				&& newStatus.equals(Constants.STATUS_DOC_CONFIRMED)) {
			app.setDocConfirmedTime(doneTime);
			app.setDocConfirmedMan(userId);
		} else if ((oldStatus.equals(Constants.STATUS_DOC_CONFIRMED) || oldStatus
				.equals(Constants.STATUS_DOC_CONFIRMED))
				&& newStatus.equals(Constants.STATUS_HANDLED)) {
			app.setHandledTime(doneTime);
			app.setHandledMan(userId);
		} else if (oldStatus.equals(Constants.STATUS_HANDLED)
				&& newStatus.equals(Constants.STATUS_AMOUNT_CONFIRMED)) {
			app.setAmountConfirmedTime(doneTime);
			app.setAmountConfirmedMan(userId);
		} else if (oldStatus.equals(Constants.STATUS_AMOUNT_CONFIRMED)
				&& newStatus.equals(Constants.STATUS_IN_OA_APPROVE)) {
			app.setNormalApproveSubmitTime(doneTime);
			app.setNormalApproveSubmitMan(userId);
		} else if (oldStatus.equals(Constants.STATUS_IN_OA_APPROVE)
				&& newStatus.equals(Constants.STATUS_APPROVED)) {
			app.setLastApprovedTime(doneTime);
			app.setLastApprovedMan(userId);
		} else if (oldStatus.equals(Constants.STATUS_SUBMITED)
				&& newStatus.equals(Constants.STATUS_APPROVED)) {
			app.setLastApprovedTime(doneTime);
			app.setLastApprovedMan(userId);
		} else if (oldStatus.equals(Constants.STATUS_APPROVED)
				&& newStatus.equals(Constants.STATUS_IN_PAYMENT)) {
			app.setPaymentSubmitTime(doneTime);
			app.setPaymentSubmitMan(userId);
		} else if (oldStatus.equals(Constants.STATUS_IN_PAYMENT)
				&& newStatus.equals(Constants.STATUS_PAID)) {
			app.setAmountPaidTime(doneTime);
			app.setAmountPaidMan(userId);
			// 多赔已审批
		} else if (oldStatus.equals(Constants.STATUS_OVERPAY_APPROVED)
				&& newStatus.equals(Constants.STATUS_APPROVED)) {
			app.setLastApprovedTime(doneTime);
			app.setLastApprovedMan(userId);
			// 金额确认
		} else if (oldStatus.equals(Constants.STATUS_AMOUNT_CONFIRMED)
				&& newStatus.equals(Constants.STATUS_APPROVED)) {
			app.setLastApprovedTime(doneTime);
			app.setLastApprovedMan(userId);
			// 在线理赔付款失败
		} else if (oldStatus.equals(Constants.STATUS_ONLINE_PAY_FAILED)
				&& newStatus.equals(Constants.STATUS_APPROVED)) {
			app.setLastApprovedTime(doneTime);
			app.setLastApprovedMan(userId);
		}
		return app;
	}

	/**
	 * 
	 * <p>
	 * Description:保存理賠處理<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param appMap
	 * 
	 */
	@Transactional
	public void saveRecompenseProcessInfo(Map appMap) {
		User user = (User) appMap.get(Constants.RECOMPENSE_CURRENT_USER);
		String empCode = user.getEmpCode().getEmpCode();
		String deptCode = user.getEmpCode().getDeptId().getStandardCode();
		String status = Constants.STATUS_HANDLED;
		RecompenseApplication app = (RecompenseApplication) appMap
				.get(Constants.RECOMPENSE_APPLICATION);
		RecompenseApplication oldApp = recompenseService
				.getRecompenseApplicationById(app.getId());
		List<String> statusList = new ArrayList<String>();
		statusList.add(Constants.STATUS_DOC_CONFIRMED);
		statusList.add(Constants.STATUS_HANDLED);
		if (RecompenseValidator.validateRecompenseStatus(oldApp.getStatus(),
				statusList)) {
			app.setStatus(Constants.STATUS_HANDLED);
			app.setSendMsgTime(1);
			Map<String, List<DeptCharge>> deptChargeMap = (Map<String, List<DeptCharge>>) appMap
					.get(Constants.RECOMPENSE_DEPTCHARGE_MAP);
			Map<String, List<ResponsibleDept>> responsibleDeptMap = (Map<String, List<ResponsibleDept>>) appMap
					.get(Constants.RECOMPENSE_RESPONSIBLEDEPT_MAP);
			Map<String, List<MessageReminder>> messageReminderMap = (Map<String, List<MessageReminder>>) appMap
					.get(Constants.RECOMPENSE_MESSAGEREMINDER_MAP);
			Map<String, List<AwardItem>> awardItemMap = (Map<String, List<AwardItem>>) appMap
					.get(Constants.RECOMPENSE_AWARDITEM_MAP);
			Map deptMap = new HashMap();
			deptMap.put("deptId", app.getReportDept());
			deptMap.put("bigAreaId", app.getDeptId());
			todoWorkflowManager.generateTodoWorkflow(app.getWorkflowId(),
					Constants.RECOMPENSE_WORKFLOW_NAME, oldApp.getStatus(),
					Constants.STATUS_HANDLED, app.getId(), app.getWaybill()
							.getWaybillNumber(), deptMap);
			recompenseService.updateRecompenseProcessInfo(app, deptChargeMap,
					responsibleDeptMap, messageReminderMap, awardItemMap);
			if (messageReminderMap != null
					&& (oldApp.getSendMsgTime() == null || oldApp
							.getSendMsgTime() == 0)) {
				sendMobileMessage(oldApp.getWaybill().getWaybillNumber(),
						oldApp.getReportDept(), app.getRealAmount(),
						oldApp.getCompanyPhone(),
						messageReminderMap.get(Constants.LIST_TYPE_ADD),
						empCode, deptCode);
			}
			RecompenseApplication statusApp = generateRecompenseForReport(
					user.getId(), oldApp.getStatus(), status);
			statusApp.setId(app.getId());
			statusApp.setStatus(status);
			recompenseService.updateRecompenseStatusInfo(statusApp);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:确认理赔金额<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param appId
	 * @param user
	 * 
	 */
	@Transactional
	public void confirmRecompenseAmountInfo(String appId, User user) {
		String status = Constants.STATUS_AMOUNT_CONFIRMED;
		RecompenseApplication oldApp = recompenseService
				.getRecompenseApplicationById(appId);
		if (RecompenseValidator.validateRecompenseStatus(oldApp.getStatus(),
				Constants.STATUS_HANDLED)) {
			Map deptMap = new HashMap();
			deptMap.put("deptId", oldApp.getReportDept());
			deptMap.put("bigAreaId", oldApp.getDeptId());
			todoWorkflowManager.generateTodoWorkflow(oldApp.getWorkflowId(),
					Constants.RECOMPENSE_WORKFLOW_NAME, oldApp.getStatus(),
					status, oldApp.getId(), oldApp.getWaybill()
							.getWaybillNumber(), deptMap);
			RecompenseApplication statusApp = generateRecompenseForReport(
					user.getId(), oldApp.getStatus(), status);
			statusApp.setId(appId);
			statusApp.setStatus(status);
			recompenseService.updateRecompenseStatusInfo(statusApp);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:取消理赔金额<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param appId
	 * @param user
	 * 
	 */
	@Transactional
	public void cancelRecompenseProcessInfo(String appId, User user) {
		String status = Constants.STATUS_DOC_CONFIRMED;
		RecompenseApplication oldApp = recompenseService
				.getRecompenseApplicationById(appId);
		if (RecompenseValidator.validateRecompenseStatus(oldApp.getStatus(),
				Constants.STATUS_HANDLED)) {
			Map deptMap = new HashMap();
			deptMap.put("deptId", oldApp.getReportDept());
			deptMap.put("bigAreaId", oldApp.getDeptId());
			todoWorkflowManager.generateTodoWorkflow(oldApp.getWorkflowId(),
					Constants.RECOMPENSE_WORKFLOW_NAME, oldApp.getStatus(),
					status, oldApp.getId(), oldApp.getWaybill()
							.getWaybillNumber(), deptMap);
			RecompenseApplication statusApp = generateRecompenseForReport(
					user.getId(), oldApp.getStatus(), status);
			statusApp.setId(appId);
			statusApp.setStatus(status);
			recompenseService.updateRecompenseStatusInfo(statusApp);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:这多赔工作流拒绝<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param workflowNum
	 * @param approver
	 * @param reason
	 * @param approveDate
	 * 
	 */
	@Override
	@Transactional
	public void oaOverpayRefuse(String workflowNum, User approver,
			String reason, Date approveDate) {
		String userId = Constants.SYSTEM_SUPER_USER_ID;
		User user = userService.getUserRoleFunDeptById(userId);
		int actionId = Constants.RECOMPENSE_OVERPAY_APPROVE_REJECT_ACTION;
		OAWorkflow oaWorkflow = recompenseService
				.getWorkflowByWorkflowNum(workflowNum);
		if (RecompenseValidator.validateOAWorkflowNull(oaWorkflow)) {
			RecompenseApplication app = recompenseService
					.getRecompenseApplicationById(oaWorkflow.getRecompenseId());
			if (RecompenseValidator.validateRecompenseNull(app)
					&& RecompenseValidator.validateRecompenseStatus(
							app.getStatus(),
							Constants.STATUS_IN_OVERPAY_APPROVE)) {
				oaWorkflow.setAuditPeople(approver);
				oaWorkflow.setAuditDate(approveDate);
				oaWorkflow.setAuditopinion(reason);
				oaWorkflow.setWorkflowStatus(Constants.WORKFLOW_STATUS_REFUSE);
				recompenseService.updateWorkflow(oaWorkflow);
				Map appMap = new HashMap();
				appMap.put(Constants.RECOMPENSE_CURRENT_USER, user);
				appMap.put(Constants.RECOMPENSE_APPLICATION, app);
				performAction(appMap, actionId, approver.getId());
			}
		}

	}

	/**
	 * 
	 * <p>
	 * Description:多赔工作流审批通过<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param workflowNum
	 * @param approver
	 * @param reason
	 * @param approveDate
	 * 
	 */
	@Override
	@Transactional
	public void oaOverpayApprove(String workflowNum, User approver,
			String reason, Date approveDate) {
		String userId = Constants.SYSTEM_SUPER_USER_ID;
		User user = userService.getUserRoleFunDeptById(userId);
		int actionId = Constants.RECOMPENSE_OVERPAY_APPROVE_CONFIRM_ACTION;
		OAWorkflow oaWorkflow = recompenseService
				.getWorkflowByWorkflowNum(workflowNum);
		if (RecompenseValidator.validateOAWorkflowNull(oaWorkflow)) {
			RecompenseApplication app = recompenseService
					.getRecompenseApplicationById(oaWorkflow.getRecompenseId());
			if (RecompenseValidator.validateRecompenseNull(app)
					&& RecompenseValidator.validateRecompenseStatus(
							app.getStatus(),
							Constants.STATUS_IN_OVERPAY_APPROVE)) {
				oaWorkflow.setAuditPeople(approver);
				oaWorkflow.setAuditDate(approveDate);
				oaWorkflow.setAuditopinion(reason);
				oaWorkflow.setWorkflowStatus(Constants.WORKFLOW_STATUS_APPROVE);
				recompenseService.updateWorkflow(oaWorkflow);
				// 更新多赔信息
				Overpay overpay = recompenseService.getOverpayByWorkflowNum(
						workflowNum, app.getId());
				recompenseService.updateRecompenseOverpayById(app.getId(),
						overpay.getId());
				if (app.getNormalAmount() != null
						&& overpay.getOverpayAmount() != null) {
					double realAmount = app.getNormalAmount()
							+ overpay.getOverpayAmount();
					app.setRealAmount(realAmount);
					recompenseService.updateRecompense(app);
				}
				// 执行工作流操作
				Map appMap = new HashMap();
				appMap.put(Constants.RECOMPENSE_CURRENT_USER, user);
				appMap.put(Constants.RECOMPENSE_APPLICATION, app);
				performAction(appMap, actionId, approver.getId());
			}
		}
	}

	/**
	 * 
	 * <p>
	 * Description:Oa审批退回<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param workflowNum
	 * @param approver
	 * @param reason
	 * @param approveDate
	 * 
	 */
	@Override
	@Transactional
	public void oaNormalRefuse(String workflowNum, User approver,
			String reason, Date approveDate) {
		String userId = Constants.SYSTEM_SUPER_USER_ID;
		User user = userService.getUserRoleFunDeptById(userId);
		int actionId = Constants.RECOMPENSE_OA_APPROVE_REJECT_ACTION;
		OAWorkflow oaWorkflow = recompenseService
				.getWorkflowByWorkflowNum(workflowNum);
		if (RecompenseValidator.validateOAWorkflowNull(oaWorkflow)) {
			RecompenseApplication app = recompenseService
					.getRecompenseApplicationById(oaWorkflow.getRecompenseId());
			if (RecompenseValidator.validateRecompenseNull(app)
					&& RecompenseValidator.validateRecompenseStatus(
							app.getStatus(), Constants.STATUS_IN_OA_APPROVE)) {
				oaWorkflow.setAuditPeople(approver);
				oaWorkflow.setAuditDate(approveDate);
				oaWorkflow.setAuditopinion(reason);
				oaWorkflow.setWorkflowStatus(Constants.WORKFLOW_STATUS_REFUSE);
				recompenseService.updateWorkflow(oaWorkflow);
				Map appMap = new HashMap();
				appMap.put(Constants.RECOMPENSE_CURRENT_USER, user);
				appMap.put(Constants.RECOMPENSE_APPLICATION, app);
				performAction(appMap, actionId, approver.getId());
			}
		}
	}

	/**
	 * 
	 * <p>
	 * Description:oa审批通过<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param workflowNum
	 * @param approver
	 * @param reason
	 * @param approveDate
	 * 
	 */
	@Override
	@Transactional
	public void oaNormalApprove(String workflowNum, User approver,
			String reason, Date approveDate) {
		String userId = Constants.SYSTEM_SUPER_USER_ID;
		User user = userService.getUserRoleFunDeptById(userId);
		int actionId = Constants.RECOMPENSE_OA_APPROVE_CONFIRM_ACTION;
		OAWorkflow oaWorkflow = recompenseService
				.getWorkflowByWorkflowNum(workflowNum);
		if (RecompenseValidator.validateOAWorkflowNull(oaWorkflow)) {
			RecompenseApplication app = recompenseService
					.getRecompenseApplicationById(oaWorkflow.getRecompenseId());
			if (RecompenseValidator.validateRecompenseNull(app)
					&& RecompenseValidator.validateRecompenseStatus(
							app.getStatus(), Constants.STATUS_IN_OA_APPROVE)) {
				oaWorkflow.setAuditPeople(approver);
				oaWorkflow.setAuditDate(approveDate);
				oaWorkflow.setAuditopinion(reason);
				oaWorkflow.setWorkflowStatus(Constants.WORKFLOW_STATUS_APPROVE);
				recompenseService.updateWorkflow(oaWorkflow);
				Map appMap = new HashMap();
				appMap.put(Constants.RECOMPENSE_CURRENT_USER, user);
				appMap.put(Constants.RECOMPENSE_APPLICATION, app);
				performAction(appMap, actionId, approver.getId());
			}
		}

	}

	/**
	 * 
	 * <p>
	 * Description:这根据部门id查询部门信息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param deptId
	 * @return
	 * 
	 */
	public Department getDepartmentByDeptId(String deptId) {
		return getDepartmentService().getDepartmentById(deptId);
	}

	/**
	 * 
	 * <p>
	 * Description:获取userid<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param userId
	 * @return
	 * 
	 */
	@Override
	@Transactional
	public List<String> getDeptIdsByUserId(String userId) {
		return recompenseService.getDeptIdsByUserId(userId);
	}

	/**
	 * 
	 * <p>
	 * Description:获取用户部门ids<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param user
	 * @return
	 * 
	 */
	@Transactional
	public String[] getUserDeptIds(User user) {
		if (user == null || user.getRoleids() == null) {
			return new String[0];
		}
		List<String> deptIds = new ArrayList<String>();
		if (user.getRoleids().contains(Constants.ROLE_ADMIN)) {
			deptIds = recompenseService.getDeptIdsByUserId(user.getId());
		} else {
			deptIds.add(user.getEmpCode().getDeptId().getId());
		}
		return deptIds.toArray(new String[deptIds.size()]);
	}

	public User getUserById(String userId) {
		return userService.getUserById(userId);
	}

	/**
	 * 
	 * <p>
	 * Description:获取userroleId<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param user
	 * @return
	 * 
	 */
	@Transactional
	public String getUserRoleId(User user) {
		if (user == null || user.getRoleids() == null) {
			return "0";
		}
		if (user.getRoleids().contains(Constants.ROLE_MANAGER)) {
			return Constants.ROLE_MANAGER;
		} else if (user.getRoleids().contains(Constants.ROLE_ADMIN)) {
			return Constants.ROLE_ADMIN;
		} else if (user.getRoleids().contains(Constants.ROLE_CASHER)) {
			return Constants.ROLE_CASHER;
		} else if (user.getRoleids().contains(Constants.ROLE_OACLIENT)) {
			return Constants.ROLE_OACLIENT;
		} else if (user.getRoleids().contains(Constants.ROLE_ERPCLIENT)) {
			return Constants.ROLE_ERPCLIENT;
		} else if (user.getRoleids().contains(Constants.ROLE_ATTENDANT)) {
			return Constants.ROLE_ATTENDANT;
		} else if (user.getRoleids().contains(Constants.ROLE_EXPRESS)) {
			return Constants.ROLE_EXPRESS;
		}
		return "0";
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
	 * 
	 * <p>
	 * Description根据凭证号查询理赔信息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param voucherNo
	 * @return
	 * 
	 */
	@Override
	public RecompenseApplication getRecompenseApplicationByVoucherNo(
			String voucherNo) {
		return recompenseService.getRecompenseApplicationByVoucherNo(voucherNo);
	}

	/**
	 * 
	 * <p>
	 * Description:根据收银员工号查询银行信息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param empCode
	 * @return
	 * 
	 */
	@Transactional
	public BankAccount getBankAccountByEmpCode(String empCode) {
		BankAccount bankAccount = new BankAccount();
		AccountInfo account = recompenseService.queryAccount(empCode);
		bankAccount.setAccount(account.getAccountNumber());
		bankAccount.setBankName(account.getBank());
		bankAccount.setBranchName(account.getSubbranch());
		bankAccount.setCity(account.getCity());
		bankAccount.setProvince(account.getProvince());
		bankAccount.setOpenName(account.getAccountName());
		return bankAccount;
	}

	/**
	 * 
	 * <p>
	 * Description:根据运单号更新在线理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param waybillNum
	 * @return OnlineApply
	 */
	@Transactional
	public OnlineApply getOnlineApplyByWaybillNum(String waybillNum) {
		// 查询在线理赔list
		List<OnlineApply> oaList = recompenseService
				.searchOnlineApplyByWaybillNum(waybillNum);
		if (oaList != null && oaList.size() > 0) {
			return oaList.get(0);
		} else {
			return null;
		}

	}

	/**
	 * 
	 * <p>
	 * Description:创建在线理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param onlineApply
	 * @return
	 * 
	 */
	@Override
	@Transactional
	public boolean createOnlineApply(OnlineApply onlineApply) {
		OnlineApply onlineApplyOld = getOnlineApplyByWaybillNum(onlineApply
				.getWaybillNumber());
		if (onlineApplyOld == null) {
			if(!onlineApply.getAccount().matches("^[0-9]+$")){
				RecompenseException re = new RecompenseException(
						RecompenseExceptionType.ONLINEAPPLY_ACCOUNT_NOT_ALL_NUM);
				throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
						new Object[] {}) {
				};
			}
			if(onlineApply.getRecompenseAmount()>1000){
				RecompenseException re = new RecompenseException(
						RecompenseExceptionType.ONLINEAPPLY_AMOUNT_TOO_BIG);
				throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
						new Object[] {}) {
				};
			}
			onlineApply.setStatus(Constants.STATUS_WAIT_ACCEPT);
			String accidentType = getAccidentTypeForOnlineApply(onlineApply
					.getWaybillNumber());
			onlineApply.setRecompenseType(accidentType);
			if (onlineApply.getCreateDate() == null) {
				onlineApply.setCreateDate(new Date());
			}
			// 根据运单号查询出运单信息
			FossWaybillInfo waybillInfo = this.recompenseService
					.getWaybillRecompense(onlineApply.getWaybillNumber());
			// 如果运输类型为快递的话
			String standCode = null;
			if (null != waybillInfo
					&& Constants.TRANS_EXPRESS
							.equals(waybillInfo.getTranType())) {
				Department department = getDepartmentService()
						.getDepartmentById(onlineApply.getApplyDeptId());
				standCode = department.getStandardCode();
				ExpressPointBusinessDept exPointBusinessDept = getExpressPointBusinessDeptManager()
						.getExpressPointBusinessDeptByDeptCode(
								department.getStandardCode());
				// 如果有对应的点部，则为试点城市，上报人为快递经理
				if (null != exPointBusinessDept) {
					Department exDepartment = getDepartmentService()
							.getDeptByStandardCode(
									exPointBusinessDept.getExpressPointCode());
					if(exDepartment!=null){
						standCode = exDepartment.getStandardCode();
						Employee expressPrincipal = employeeService.
								getEmpByCode(exDepartment.getPrincipal());
						if(expressPrincipal!=null&&
								Constants.RECOMPENSE_EXPRESS_POSITION_MANAGER.
									equals(expressPrincipal.getPosition())){
							onlineApply.setApplyDeptId(exDepartment.getId());
						}
					}
				}

			}
			Department areaDepartment = null;
			if(Constants.TRANS_EXPRESS
							.equals(waybillInfo.getTranType())){
				areaDepartment = this.searchBigArea(standCode, false);
			}else{
				areaDepartment = departmentService
						.getBigAreaByDeptId(onlineApply.getApplyDeptId());
			}
			onlineApply.setBelongsAreaName(areaDepartment.getDeptName());
			onlineApply.setBelongsArea(areaDepartment.getId());
			recompenseService.insertOnlineApply(onlineApply);
			messageManager.addMessage(getOnlineApplyTodoMessage(onlineApply));
			return true;
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * Description:获得在线理赔出险类型<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param waybillNum
	 * @return String
	 */
	private String getAccidentTypeForOnlineApply(String waybillNum) {
		// 查询oa差错信息
		List<OaAccidentInfo> waybillAccidentList = this.recompenseService
				.getAccidentByWaybillNum(waybillNum);
		String accidentType = "";
		if (waybillAccidentList != null && waybillAccidentList.size() > 0) {
			for (OaAccidentInfo oaAccidentInfo : waybillAccidentList) {
				if (oaAccidentInfo.getAccidentType() == Constants.OA_ACCIDENT_ABNORMAL_SIGN) {
					accidentType = Constants.ABNORMAL_SIGN;
					break;
				}

				if (oaAccidentInfo.getAccidentType() == Constants.OA_ACCIDENT_LOST_GOODS) {
					accidentType = Constants.LOST_GOODS;
					break;
				}
			}
		}
		if (accidentType.equals("")) {
			accidentType = Constants.ABNORMAL_SIGN;
		}
		return accidentType;
	}

	/**
	 * 
	 * <p>
	 * Description:更新在线理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param onlineApply
	 * @return
	 * 
	 */
	@Transactional
	public boolean updateOnlineApply(OnlineApply onlineApply) {
		if(!onlineApply.getAccount().matches("^[0-9]+$")){
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.ONLINEAPPLY_ACCOUNT_NOT_ALL_NUM);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
		if(onlineApply.getRecompenseAmount()>1000){
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.ONLINEAPPLY_AMOUNT_TOO_BIG);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
		// 获取在线理赔
		OnlineApply onlineApplyOld = getOnlineApplyByWaybillNum(onlineApply
				.getWaybillNumber());
		// 校验
		if (onlineApplyOld != null
				&& (onlineApplyOld.getStatus().equals(
						Constants.STATUS_WAIT_ACCEPT)
						|| onlineApplyOld.getStatus().equals(
								Constants.STATUS_REJECTED) || onlineApplyOld
						.getStatus().equals(Constants.STATUS_INVALID))) {
			onlineApply.setId(onlineApplyOld.getId());
			onlineApply.setStatus(Constants.STATUS_WAIT_ACCEPT);
			String accidentType = getAccidentTypeForOnlineApply(onlineApply
					.getWaybillNumber());
			onlineApply.setRecompenseType(accidentType);
			// 根据运单号查询出
			FossWaybillInfo waybillInfo = this.recompenseService
					.getWaybillRecompense(onlineApply.getWaybillNumber());
			// 如果运输类型为快递的话
			if (null != waybillInfo
					&& Constants.TRANS_EXPRESS
							.equals(waybillInfo.getTranType())) {
				Department department = getDepartmentService()
						.getDepartmentById(onlineApply.getApplyDeptId());
				ExpressPointBusinessDept exPointBusinessDept = getExpressPointBusinessDeptManager()
						.getExpressPointBusinessDeptByDeptCode(
								department.getStandardCode());
				// 如果有对应的点部，则为试点城市，上报人为快递经理
				if (null != exPointBusinessDept) {
					Department exDepartment = getDepartmentService()
							.getDeptByStandardCode(
									exPointBusinessDept.getExpressPointCode());
					onlineApply.setApplyDeptId(exDepartment.getId());

				}

			}
			Department areaDepartment = departmentService
					.getBigAreaByDeptId(onlineApply.getApplyDeptId());
			onlineApply.setBelongsAreaName(areaDepartment.getDeptName());
			onlineApply.setBelongsArea(areaDepartment.getId());
			recompenseService.updateOnlineApply(onlineApply);
			return true;
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * Description: 取消在线理赔申请<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param onlineUser
	 * @param waybillNum
	 * @return
	 * 
	 */
	@Transactional
	public boolean cancelOnlineApply(String onlineUser, String waybillNum) {
		// 根据运单号获得在线理赔
		OnlineApply onlineApply = getOnlineApplyByWaybillNum(waybillNum);
		if (onlineApply != null
				&& onlineApply.getOnlineUser().equals(onlineUser)
				&& (onlineApply.getStatus()
						.equals(Constants.STATUS_WAIT_ACCEPT) || onlineApply
						.getStatus().equals(Constants.STATUS_REJECTED))) {
			onlineApply.setStatus(Constants.STATUS_INVALID);
			// 更新在线理赔状态
			recompenseService.updateOnlineApply(onlineApply);
			return true;
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * Description:在线理赔重新付款<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param onlineApply
	 * @return
	 * 
	 */
	@Transactional
	public boolean repayOnlineApply(OnlineApply onlineApply) {
		// 设置在线理赔重新付款的id
		int actionId = Constants.RECOMPENSE_ONLINE_REPAY_ACTION;
		// 查询出运单号
		String waybillNum = onlineApply.getWaybillNumber();
		OnlineApply onlineApplyOld = getOnlineApplyByWaybillNum(waybillNum);
		if (onlineApplyOld == null
				|| !onlineApplyOld.getOnlineUser().equals(
						onlineApply.getOnlineUser())
				|| !onlineApplyOld.getStatus().equals(
						Constants.STATUS_ONLINE_PAY_FAILED)) {
			return false;
		}
		// 查询理赔信息
		RecompenseApplication app = recompenseService
				.getRecompenseApplicationById(onlineApplyOld.getRecompenseId());
		if (app == null) {
			return false;
		}
		User reporter = new User();
		Set<String> roleIds = new HashSet<String>();
		roleIds.add("3");
		reporter.setRoleids(roleIds);

		onlineApply.setId(onlineApplyOld.getId());
		onlineApply.setStatus(Constants.STATUS_IN_PAYMENT);
		recompenseService.updateOnlineApply(onlineApply);
		Map appMap = new HashMap();
		// 设置下一步的appmap
		appMap.put(Constants.RECOMPENSE_CURRENT_USER, reporter);
		appMap.put(Constants.RECOMPENSE_APPLICATION, app);
		performAction(appMap, actionId, "online");
		if (null != app
				&& app.getRecompenseMethod().equals(Constants.ONLINE_TYPE)) {
			String todoContent = MessageFormat.format(
					Constants.ONLINE_MODIFY_CARDNUMBER_TODO_MESSAGE,
					new Object[] { app.getWaybill().getWaybillNumber() });
			com.deppon.crm.module.common.shared.domain.Message message = new com.deppon.crm.module.common.shared.domain.Message();
			message.setTasktype(Constants.TODO_TASK_TYPE);
			message.setIshaveinfo(todoContent);
			message.setTaskcount(1);
			message.setDeptId(Integer.parseInt(app.getReportDept()));
			messageManager.addMessage(message);

		}

		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:根据运单号查询在线理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param waybillNum
	 * @return
	 * 
	 */
	@Transactional
	public String getOnlineApplyStatusByWaybillNum(String waybillNum) {
		String status = null;

		OnlineApply onlineApply = getOnlineApplyByWaybillNum(waybillNum);
		if (onlineApply != null) {
			status = onlineApply.getStatus();
		}
		return status;
	}

	/**
	 * 
	 * <p>
	 * Description:根据官网账号查询在线理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param onlineUser
	 * @param waybillNum
	 * @return
	 * 
	 */
	@Transactional
	public List<OnlineApply> getOnlineApplyByOnlineUser(String onlineUser,
			String waybillNum) {
		return recompenseService.getOnlineApplyByOnlineUser(onlineUser,
				waybillNum);
	}

	/**
	 * 
	 * <p>
	 * Description:根据条件查询在线理赔集合<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param onlineUser
	 * @param waybillNum
	 * @param startTime
	 * @param endTime
	 * @param start
	 * @param limit
	 * @return
	 * 
	 */
	@Transactional
	public List<OnlineApply> getOnlineApplyByCondition(String onlineUser,
			String waybillNum, Date startTime, Date endTime, int start,
			int limit) {
		return recompenseService.getOnlineApplyByInterCondition(onlineUser,
				waybillNum, startTime, endTime, start, limit);
	}

	/**
	 * 
	 * <p>
	 * Description:统计在线理赔数<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param onlineUser
	 * @param waybillNum
	 * @param startTime
	 * @param endTime
	 * @return
	 * 
	 */

	@Transactional
	public int getOnlineApplyByConditionCount(String onlineUser,
			String waybillNum, Date startTime, Date endTime) {
		return recompenseService.getOnlineApplyByInterConditionCount(
				onlineUser, waybillNum, startTime, endTime);
	}

	/**
	 * 
	 * <p>
	 * Description:根据客户id查询该客户的理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param custId
	 * @return
	 * 
	 */
	@Override
	public List<RecompenseApplication> getRecompenseListByCustId(String custId) {
		return this.recompenseService.getRecompenseListByCustId(custId);
	}

	/**
	 * 
	 * <p>
	 * Description:根据id获取在线理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param id
	 * @return
	 * 
	 */
	@Override
	public OnlineApply getOnlineApplyById(String id) {
		return recompenseService.getOnlineApplyById(id);
	}

	/**
	 * 
	 * <p>
	 * Description:根据理赔id查询在线理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param recompenseId
	 * @return
	 * 
	 */
	@Override
	public OnlineApply getOnlineApplyByRecompenseId(String recompenseId) {
		// 查询在线理赔
		OnlineApply onlineApply = recompenseService
				.getOnlineApplyByRecompenseId(recompenseId);
		// 理赔金额校验
		if (onlineApply.getRecompenseAmount() != null) {
			String chineseAmount = MoneyUtil.toChinese(onlineApply
					.getRecompenseAmount().toString());
			onlineApply.setChineseAmount(chineseAmount);
		}
		return onlineApply;
	}

	/**
	 * 
	 * <p>
	 * Description:根据客户编码查询客户<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param custNumber
	 * @return
	 * 
	 */
	public Member getCustomerByNum(String custNumber) {
		return memberManager.getMemberByCustNumber(custNumber);
	}

	/**
	 * 
	 * <p>
	 * Description:上报时划分理赔费用<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param app
	 * @param oldApp
	 * @return
	 * 
	 */
	@Override
	public RecompenseApplication assignDeptCharge(RecompenseApplication app,
			RecompenseApplication oldApp) {
		String number = app.getWaybill().getWaybillNumber();
		FossWaybillInfo waybillInfo = recompenseService
				.getWaybillRecompense(number);
		Department leaveDept = null;
		User user = (User) UserContext.getCurrentUser();
		if (null != waybillInfo) {
			leaveDept = getDeptByStandardCode(waybillInfo
					.getReceiveDeptNumber());
		}
		app.setConfirmAmountDept(app.getReportDept());
		// 收银员所在部门
		app.setCashierDept(app.getReportDept());
		// 如是是未开单理赔 金额确认部门及付款部门都是报案部门
		if (Constants.UNBILLED.equals(app.getRecompenseType())) {
			// 金额确认部门
			app.setConfirmAmountDept(app.getReportDept());
			// 收银员所在部门
			app.setCashierDept(app.getReportDept());
			List<Department> departments = getDepartmentService()
					.getDepartmentByDeptName(app.getWaybill().getReceiveDept());
			String receiveDeptId = "";
			if (null != departments && departments.size() != 0) {

				receiveDeptId = departments.get(0).getId();
			}
			// 查询如出发部门
			Department receiveDept = getDepartmentService().getDepartmentById(
					receiveDeptId);

			// 设置收银员付款部门为出发部门
			app.setCashierDept(receiveDeptId);
			// 如果当前创建理赔的角色为区域客服
			if (user.getRoleids().contains(Constants.ROLE_CUSTOMSERVICE)) {
				// 则需查询营业部对应的点部。如果为点部就当区域客服自己上报
				ExpressPointBusinessDept exPointBusinessDept = expressPointBusinessDeptManager
						.getExpressPointBusinessDeptByDeptCode(receiveDept
								.getStandardCode());
				// 如果有对应的点部则确认金额部门为点部经理
				if (null != exPointBusinessDept) {
					Department exDept = getDepartmentService()
							.getDeptByStandardCode(
									exPointBusinessDept.getExpressPointCode());
					app.setConfirmAmountDept(exDept.getId());
				} else {
					app.setConfirmAmountDept(app.getReportDept());
				}

			} else {

				app.setConfirmAmountDept(receiveDeptId);
			}

		} else {
			
			// 收银员所在部门
			app.setCashierDept(leaveDept.getId());
			// 校验发货城市是否为试点城市
			app.setConfirmAmountDept(leaveDept.getId());
			app.setConfirmAmountDept(user.getEmpCode().getDeptId().getId());
			app.setCashierDept(user.getEmpCode().getDeptId().getId());
			// 对异常签收和丢货理赔没有客户编码的生成散客的规则
			// 如果是发货方的话 receiveDeptNumber
			String leaveCustomerNum = memberManager
					.getNumberForRecompense(waybillInfo.getSenderMobile(),
							waybillInfo.getSenderPhone(), waybillInfo
									.getSender(), app.getReportDept(), app
									.getWaybill().getLeaveCustomerId(),waybillInfo,BackFreightConstant.LEAVE);
			app.getWaybill().setLeaveCustomerId(leaveCustomerNum);

			// 到达客户

			String arriveCustomerNum = memberManager
					.getNumberForRecompense(waybillInfo.getConsigneeMobile(),
							waybillInfo.getConsigneePhone(),
							waybillInfo.getConsignee(), app.getReportDept(),
							app.getWaybill().getArriveCustomerId(),waybillInfo,BackFreightConstant.ARRIVE);
			app.getWaybill().setArriveCustomerId(arriveCustomerNum);
		
		}
		// 如果前台传到后台的客户为空，则需要根据出发方和到达方把客户编码会写到customerId这个字段
		if (null == app.getCustomer() || null == app.getCustomer().getId()) {
			if (Constants.CLAIMER_SEND.equals(app.getClaimParty())) {
				MemberCondition condition = new MemberCondition();
				condition.setCustNumber(app.getWaybill().getLeaveCustomerId());
				// 根据这个方法查询可以把无效的固定客户查询出来
				List<MemberResult> memberResults = alterMemberManager
						.searchMember(condition);
				if (null != memberResults && memberResults.size() >= 1) {
					app.getCustomer().setId(memberResults.get(0).getCustId());
				}
			} else if (Constants.CLAIMER_RECEIVE.equals(app.getClaimParty())) {
				MemberCondition condition = new MemberCondition();
				condition.setCustNumber(app.getWaybill().getArriveCustomerId());
				List<MemberResult> memberResults = alterMemberManager
						.searchMember(condition);

				if (null != memberResults && memberResults.size() >= 1) {
					app.getCustomer().setId(memberResults.get(0).getCustId());
				}

			}
		}
		// 更新理赔单的费用
		app.setNormalAmount(app.getRecompenseAmount());
		app.setRealAmount(app.getRecompenseAmount());
		//用户所属大区
		Department belongArea = this.searchBigArea(
				user.getEmpCode().getDeptId().getStandardCode(), false);;
		if (waybillInfo == null ) {
			List<ResponsibleDept> responsibleDeptList = new ArrayList<ResponsibleDept>();
			ResponsibleDept responsibleDept = new ResponsibleDept();
			responsibleDept.setDeptName(belongArea.getDeptName());
			responsibleDept.setRecompenseId(app.getId());
			responsibleDept.setDeptId(belongArea.getId());
			responsibleDeptList.add(responsibleDept);
			app.setResponsibleDeptList(responsibleDeptList);
			app.setDeptChargeList(new ArrayList<DeptCharge>());
			return app;
		}
		if(!waybillInfo.getTranType().equals(Constants.TRANS_EXPRESS)){
			// 责任部门划分，报案部门的大区，无论营业部，区域客服，对应的大区都是要找的大区，这边的业务逻辑不需要变动
			Department reportDept = departmentService.getBigAreaByDeptId(app.getCashierDept());
			if (null == reportDept) {
				List<DeptCharge> dcList = new ArrayList<DeptCharge>();
				app.setDeptChargeList(dcList);
				return app;
	
			}
			List<ResponsibleDept> responsibleDeptList = new ArrayList<ResponsibleDept>();
			ResponsibleDept responsibleDept = new ResponsibleDept();
			// 上报部门是否为空校验
			if (reportDept != null) {
				responsibleDept.setDeptId(reportDept.getId());
			}
			// 封装属性
			responsibleDept.setDeptName(reportDept.getDeptName());
			responsibleDept.setRecompenseId(app.getId());
			responsibleDept.setDeptId(reportDept.getId());
			responsibleDeptList.add(responsibleDept);
			app.setResponsibleDeptList(responsibleDeptList);
	
			// 部门费用划分
			List<DeptCharge> dcList = new ArrayList<DeptCharge>();
			// 出发部门
			leaveDept = departmentService.getBigAreaByDeptId(leaveDept.getId());
			DeptCharge leaveCharge = new DeptCharge();
			DeptCharge reportCharge = new DeptCharge();
			// 如果是快递单，费用全部入零担大区
	
			// 快速理赔，出发部门70%，报案部门30% 费用
			if (leaveDept != null) {
				// 出发部门和上报部门相同大区, 该大区就是全部费用
				if (leaveDept.getId().equals(reportDept.getId())) {
					leaveCharge.setRecompenseId(app.getId());
					leaveCharge.setDeptId(leaveDept.getId());
					leaveCharge.setDeptName(leaveDept.getDeptName());
					leaveCharge.setAmount(app.getRealAmount());
					dcList.add(leaveCharge);
				} else {// 否则 3-7开，上报的3，出发的7
					BigDecimal bg = BigDecimal.valueOf(app.getRealAmount() * 0.7);
					double sevenPart = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue();
					// 封装属性
					leaveCharge.setRecompenseId(app.getId());
					leaveCharge.setDeptId(leaveDept.getId());
					leaveCharge.setDeptName(leaveDept.getDeptName());
					leaveCharge.setAmount(sevenPart);
					reportCharge.setRecompenseId(app.getId());
					reportCharge.setDeptId(reportDept.getId());
					reportCharge.setDeptName(reportDept.getDeptName());
					reportCharge.setAmount(app.getRealAmount() - sevenPart);
					dcList.add(leaveCharge);
					dcList.add(reportCharge);
	
				}
				app.setDeptChargeList(dcList);
			}
		}
		if(waybillInfo.getTranType().equals(Constants.TRANS_EXPRESS)){
			//出发营业部
			Department leave = getDeptByStandardCode(waybillInfo
					.getReceiveDeptNumber());
			//到达营业部
			Department arrived = getDeptByStandardCode(waybillInfo
					.getLadingStationNumber());
			//出发大区
			Department leaveExBigArea = this.searchBigArea(waybillInfo
					.getReceiveDeptNumber(), true);
			//到达大区
			Department arriveExBigArea =this.searchBigArea(waybillInfo
					.getLadingStationNumber(), true);
			//入部门费用
			List<DeptCharge> dcListExpress = new ArrayList<DeptCharge>();
			DeptCharge leaveChargeExpress = new DeptCharge();
			DeptCharge reportChargeExpress = new DeptCharge();
			app.setConfirmAmountDept(app.getReportDept());
			//如果出发理赔
			if(arriveExBigArea==null||belongArea.getId().equals(leaveExBigArea.getId())){
				app.setCashierDept(leave.getId());
				//如果是出发方区域客服理赔
				if(user.getRoleids().contains("9")){
					//金额确认为出发对应的点部
					app.setConfirmAmountDept(this.searchExpressPointDept(
							leave.getStandardCode()).getId());
				}
				leaveChargeExpress.setRecompenseId(app.getId());
				leaveChargeExpress.setDeptId(belongArea.getId());
				leaveChargeExpress.setDeptName(belongArea.getDeptName());
				leaveChargeExpress.setAmount(app.getRealAmount());
				dcListExpress.add(leaveChargeExpress);
			}else{
				//到达方理赔
				//到达的区域客服理赔
				if(user.getRoleids().contains("9")){
					//金额确认为到达对应的点部
					app.setConfirmAmountDept(this.searchExpressPointDept(
							arrived.getStandardCode()).getId());
				}
				app.setCashierDept(arrived.getId());
				BigDecimal bg = BigDecimal.valueOf(app.getRealAmount() * 0.7);
				double sevenPart = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
						.doubleValue();
				leaveChargeExpress.setRecompenseId(app.getId());
				leaveChargeExpress.setDeptId(leaveExBigArea.getId());
				leaveChargeExpress.setDeptName(leaveExBigArea.getDeptName());
				leaveChargeExpress.setAmount(sevenPart);
				reportChargeExpress.setRecompenseId(app.getId());
				reportChargeExpress.setDeptId(arriveExBigArea.getId());
				reportChargeExpress.setDeptName(arriveExBigArea.getDeptName());
				reportChargeExpress.setAmount(app.getRealAmount() - sevenPart);
				dcListExpress.add(leaveChargeExpress);
				dcListExpress.add(reportChargeExpress);
			}
			app.setDeptChargeList(dcListExpress);
			List<ResponsibleDept> responsibleDeptListExpress = new ArrayList<ResponsibleDept>();
			ResponsibleDept responsibleDeptExpress = new ResponsibleDept();
			responsibleDeptExpress.setDeptName(belongArea.getDeptName());
			responsibleDeptExpress.setDeptId(belongArea.getId());
			responsibleDeptExpress.setRecompenseId(app.getId());
			responsibleDeptListExpress.add(responsibleDeptExpress);
			app.setResponsibleDeptList(responsibleDeptListExpress);
			//如果出发大区和到达大区系统，但是选择的是到达方理赔
			//金额确认部门和付款部门都为到达
			if(leaveExBigArea!=null&&arriveExBigArea!=null
					&&leaveExBigArea.getId().equals(arriveExBigArea.getId())){
				if (app.getClaimParty().equals("2")) {
					if(user.getRoleids().contains("9")){
						app.setConfirmAmountDept(this.searchExpressPointDept(
								arrived.getStandardCode()).getId());
					}else{
						app.setConfirmAmountDept(arrived.getId());
					}
					app.setCashierDept(arrived.getId());
				}
			}
		}
		return app;

	}

	/**
	 * 
	 * <p>
	 * Description:根据理赔号查询工作流<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param recompenseId
	 * @return
	 * 
	 */
	@Override
	public List<OAWorkflow> getOaWorkflowByRecompenseId(String recompenseId) {
		List<OAWorkflow> list = recompenseService.getOaWorkflowByRecompenseId(recompenseId);
		for(OAWorkflow wf : list){
			wf.setWorkflowNumEnc(EncryptUtil.encrypt(wf.getWorkflowNum(),NormalClaimUtil.WORKFLOW_DESC_KEY));
		}
		return list;
	}

	/**
	 * 
	 * <p>
	 * Description:生成待办<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * 
	 */
	@Override
	public void generateTodoReminder() {
		messageManager.deleteMessageByType(Constants.TODO_SUBMITED_TASK_TYPE);
		List<com.deppon.crm.module.common.shared.domain.Message> submitedRec = getSubmitedTodoMessage();
		if (submitedRec != null && submitedRec.size() > 0) {
			messageManager.addMessageList(submitedRec);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:查询已经提交的待办消息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @return List<com.deppon.crm.module.common.shared.domain.Message>
	 */
	private List<com.deppon.crm.module.common.shared.domain.Message> getSubmitedTodoMessage() {
		// 查询提交的待办消息
		List<TodoReminder> todoReminder = recompenseService
				.generateTodoReminder(Constants.STATUS_SUBMITED);
		List<com.deppon.crm.module.common.shared.domain.Message> messageList = new ArrayList<com.deppon.crm.module.common.shared.domain.Message>();
		// 循环遍历
		for (int i = 0; i < todoReminder.size(); i++) {
			// 获取当前循环到的实体
			TodoReminder reminder = todoReminder.get(i);
			// 新建实体
			com.deppon.crm.module.common.shared.domain.Message message = new com.deppon.crm.module.common.shared.domain.Message();
			// 封装消息
			String msg = MessageFormat.format(
					Constants.RECOMPENSE_TODO_SUBMITED_MESSAGE,
					new Object[] { reminder.getTodoNum() });
			// 设置值
			message.setIshaveinfo(msg);
			message.setTasktype(Constants.TODO_SUBMITED_TASK_TYPE);
			message.setTaskcount(1);
			message.setConditions(Constants.STATUS_SUBMITED);
			// 判断申请部门id是否为空，
			if (reminder.getUserId() != null
					&& !"".equals(reminder.getUserId())) {
				// 不为空的情况设置到消息中
				message.setUserid(Integer.parseInt(reminder.getUserId()));
			}
			message.setDeptId(null);
			messageList.add(message);
		}
		return messageList;
	}

	/**
	 * 
	 * <p>
	 * Description:获取在线理赔待办消息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param onlineApply
	 * @return com.deppon.crm.module.common.shared.domain.Message
	 */

	private com.deppon.crm.module.common.shared.domain.Message getOnlineApplyTodoMessage(
			OnlineApply onlineApply) {
		// 创建消息
		com.deppon.crm.module.common.shared.domain.Message message = new com.deppon.crm.module.common.shared.domain.Message();
		// 封装消息格式
		String msg = MessageFormat.format(
				Constants.RECOMPENSE_TODO_ONLINEAPPLY_MESSAGE,
				new Object[] { onlineApply.getWaybillNumber() });
		// 封装消息属性
		message.setIshaveinfo(msg);
		message.setTasktype(Constants.TODO_TASK_TYPE);
		message.setTaskcount(1);
		message.setConditions("");
		message.setUserid(null);
		// 判断申请部门id是否为空，
		if (onlineApply.getApplyDeptId() != null) {
			// 不为空的情况设置到消息中
			message.setDeptId(Integer.parseInt(onlineApply.getApplyDeptId()));
		}
		// 返回消息
		return message;
	}

	/**
	 * 
	 * <p>
	 * Description:查询付款中的理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param startDate
	 * @param endDate
	 * @return
	 * 
	 */

	public List<String> getRecompenseInPayment(Date startDate, Date endDate) {
		// 新建理赔查询对象
		RecompenseSearchCondition condition = new RecompenseSearchCondition();
		// 设置分页
		condition.setStart(0);
		condition.setLimit(Integer.MAX_VALUE);
		List<String> list = new ArrayList<String>();
		// 理赔状态
		list.add(Constants.STATUS_IN_PAYMENT);
		condition.setRecompenseStateList(list);
		condition.setApproveStartTime(startDate);
		condition.setApproveEndTime(endDate);
		// 查询付款中的理赔
		List<RecompenseApplication> appList = recompenseService
				.searchRecompenseByCondition(condition);
		List<String> appNumList = new ArrayList<String>();
		// 循环遍历
		for (RecompenseApplication app : appList) {
			appNumList.add(app.getWaybill().getWaybillNumber());
		}
		// 根据理赔num查询付款中的理赔
		List<DepClaimsBill> inPaymentList = recompenseService
				.getRecompenseInPayment(appNumList);
		// 遍历
		for (Iterator<String> iter = appNumList.iterator(); iter.hasNext();) {
			String appNum = iter.next();
			for (DepClaimsBill depClaimsBill : inPaymentList) {
				if (appNum.equals(depClaimsBill.getNumber())) {
					if ("10".equals(depClaimsBill.getHandleType())
							|| "20".equals(depClaimsBill.getHandleType())
							|| ("30".equals(depClaimsBill.getHandleType()) && !BigDecimal.ZERO
									.equals(depClaimsBill.getAmountUnVerify()))
							|| "40".equals(depClaimsBill.getHandleType())
							|| "60".equals(depClaimsBill.getHandleType())) {
						iter.remove();
						break;
					}
				}
			}
		}
		// 返回结果
		return appNumList;
	}

	/**
	 * 
	 * <p>
	 * Description:查询已经付款的理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param startDate
	 * @param endDate
	 * @return
	 * 
	 */
	public List<String> getRecompensePaymented(Date startDate, Date endDate) {
		// 查询付款完成的理赔
		List<DepClaimsBill> paymentedList = recompenseService
				.getRecompensePaymented(startDate, endDate);
		List<String> notPaidList = new ArrayList<String>();
		// 查询遍历
		for (DepClaimsBill depClaimsBill : paymentedList) {
			// 根据单号查询理赔
			RecompenseApplication app = recompenseService
					.getRecompenseApplicationByVoucherNo(depClaimsBill
							.getNumber());
			// 校验app是否为空和状态
			if (app == null || !Constants.STATUS_PAID.equals(app.getStatus())) {
				notPaidList.add(depClaimsBill.getNumber());
			}
		}
		return notPaidList;
	}

	/**
	 * 
	 * <p>
	 * Description:根据员工工号调用费控接口查询收银员信息<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2013-1-4
	 * @param employeeNum
	 * @return Payment
	 */
	@Override
	public List<Payment> queryPaymentByEmployeeNum(String employeeNum) {
		if (null != employeeNum && !"".equals(employeeNum)) {
			List<Payment> payments = recompenseService
					.queryPaymentByEmployeeNum(employeeNum);
			if (null != payments && payments.size() == 0) {
				RecompenseException re = new RecompenseException(
						RecompenseExceptionType.PAYMENT_NULL_CASHIER_ACCOUNT);
				throw new GeneralException(re.getErrorCode(), re.getMessage(),
						re, new Object[] {}) {
				};
			}
			return payments;
		} else {

			return null;
		}

	}

	/**
	 * 
	 * <p>
	 * Description:查询付款记录<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2013-1-4
	 * @return List<Payment>
	 * 
	 */
	@Override
	public List<Payment> searchPaymentHistoryByRecompenseId(String recompenseId) {

		List<Payment> list = recompenseService
				.searchPaymentByRecompenseId(recompenseId);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getBank() != null) {
				list.get(i).setBankName(list.get(i).getBank().getName());
			}
			if (list.get(i).getBranch() != null) {
				list.get(i).setBranchName(list.get(i).getBranch().getName());
			}
			if (list.get(i).getBankCity() != null) {
				list.get(i)
						.setBankCityName(list.get(i).getBankCity().getName());
			}
			if (list.get(i).getBankProvice() != null) {
				list.get(i).setBankProviceName(
						list.get(i).getBankProvice().getName());
			}

		}
		return list;
	}

	/**
	 * 
	 * <p>
	 * Description:查询支行信息<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-12下午5:21:10
	 * @return String
	 * @update 2013-1-12下午5:21:10
	 */
	public List<BankView> searchBankInfoByBankView(BankView bankView) {
		if (RecompenseValidator.checkBank(bankView)) {
			return bankInfoManager.getBankInfoByBV(bankView);
		}
		return null;
	}

	@Override
	public void paymentRefuse(String voucherNum) {
		RecompenseApplication app = recompenseService
				.getRecompenseApplicationByVoucherNo(voucherNum);
		int actionId = Constants.RECOMPENSE_PAYMENT_REJECT_ACTION;
		String userId = Constants.SYSTEM_SUPER_USER_ID;
		User user = userService.getUserRoleFunDeptById(userId);
		List<String> statusList = new ArrayList<String>();
		statusList.add(Constants.STATUS_IN_PAYMENT);
		statusList.add(Constants.STATUS_PAID);
		if (RecompenseValidator.validateRecompenseNull(app)
				&& RecompenseValidator.validateRecompenseStatus(
						app.getStatus(), statusList)) {
			Map appMap = new HashMap();
			appMap.put(Constants.RECOMPENSE_APPLICATION, app);
			appMap.put(Constants.RECOMPENSE_CURRENT_USER, user);
			performAction(appMap, actionId, userId);
			if (null != app
					&& app.getRecompenseMethod().equals(Constants.ONLINE_TYPE)) {
				String todoContent = MessageFormat.format(
						Constants.ONLINE_PAYFAIL_TODO_MESSAGE,
						new Object[] { app.getWaybill().getWaybillNumber() });
				com.deppon.crm.module.common.shared.domain.Message message = new com.deppon.crm.module.common.shared.domain.Message();
				message.setTasktype(Constants.TODO_TASK_TYPE);
				message.setIshaveinfo(todoContent);
				message.setTaskcount(1);
				message.setDeptId(Integer.parseInt(app.getReportDept()));
				messageManager.addMessage(message);

			}
		}

	}

	@Override
	public void paymentApprove(String voucherNum) {
		RecompenseApplication app = recompenseService
				.getRecompenseApplicationByVoucherNo(voucherNum);
		int actionId = Constants.RECOMPENSE_PAYMENT_CONFIRM_ACTION;
		String userId = Constants.SYSTEM_SUPER_USER_ID;
		User user = userService.getUserRoleFunDeptById(userId);
		List<String> statusList = new ArrayList<String>();
		statusList.add(Constants.STATUS_IN_PAYMENT);
		statusList.add(Constants.STATUS_PAID);
		if (RecompenseValidator.validateRecompenseNull(app)
				&& RecompenseValidator.validateRecompenseStatus(
						app.getStatus(), statusList)) {
			Map appMap = new HashMap();
			appMap.put(Constants.RECOMPENSE_APPLICATION, app);
			appMap.put(Constants.RECOMPENSE_CURRENT_USER, user);
			performAction(appMap, actionId, userId);
		}
	}

	/**
	 * 
	 * @Description: 查询foss运单参数
	 * @author huangzhanming
	 * @param waybillNum
	 * @return
	 * @return FossWaybillInfo
	 * @date 2013-3-19 上午10:44:23
	 * @version V1.0
	 */
	@Override
	public FossWaybillInfo getFossWaybillInfo(String waybillNum) {
		return recompenseService.getWaybillRecompense(waybillNum);
	}

	public GetRecompenseByWayBill getRecompenseByWayBill(String waybillNum) {
		RecompenseApplication recompenseApplication = recompenseService
				.getRecompenseApplicationByVoucherNo(waybillNum);
		if (recompenseApplication == null) {
			return null;
		}
		recompenseApplication = recompenseService
				.getRecompenseApplicationById(recompenseApplication.getId());
		if (recompenseApplication == null) {
			return null;
		}

		String handledMan = recompenseApplication.getHandledMan();
		handledMan = getEmpName(handledMan);
		if (handledMan != null) {
			recompenseApplication.setHandledMan(handledMan);
		}

		String lastApprovedMan = recompenseApplication.getLastApprovedMan();
		lastApprovedMan = getEmpName(lastApprovedMan);
		if (lastApprovedMan != null) {
			recompenseApplication.setLastApprovedMan(lastApprovedMan);
		}

		String lastApprovedOpinion = null;
		List<OAWorkflow> oaWorkflows = recompenseService
				.getOaWorkflowByRecompenseId(recompenseApplication.getId());
		if (oaWorkflows != null && oaWorkflows.size() > 0) {
			Collections.sort(oaWorkflows, new Comparator<OAWorkflow>() {

				@Override
				public int compare(OAWorkflow o1, OAWorkflow o2) {
					return -o1.getCommitDate().compareTo(o2.getCommitDate());
				}
			});
			lastApprovedOpinion = oaWorkflows.get(0).getAuditopinion();
		}

		String deptCharge = null;
		List<DeptCharge> deptChargeList = recompenseApplication
				.getDeptChargeList();
		if (deptChargeList != null) {
			StringBuilder sb = new StringBuilder();
			for (DeptCharge dc : deptChargeList) {
				sb.append(dc.getDeptName()).append(":").append(dc.getAmount())
						.append(";");
			}
			deptCharge = sb.toString();
		}

		String issueDesc = null;
		List<IssueItem> issueItemList = recompenseApplication
				.getIssueItemList();
		if (issueItemList != null) {
			StringBuilder sb = new StringBuilder();
			for (IssueItem issueItem : issueItemList) {
				sb.append(issueItem.getDescription()).append(";");
			}
			issueDesc = sb.toString();
		}

		String status = null;
		try {
			status = DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.RECOMPENSE_STATUS,
					recompenseApplication.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String insurType = null;
		try {
			insurType = DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.DANGER_TYPE,
					recompenseApplication.getInsurType());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new GetRecompenseByWayBill(recompenseApplication,
				lastApprovedOpinion, deptCharge, issueDesc, status, insurType);
	}

	private String getEmpName(String id) {
		if (id != null) {
			try {
				Integer.parseInt(id);
				User user = userService.getUserById(id);
				if (user != null && user.getEmpCode() != null) {
					return user.getEmpCode().getEmpName();
				}
			} catch (NumberFormatException e) {
			}
		}
		return null;
	}


	private boolean checkNotCreateCust(String waybillNum,
			List<String> createdList) {
		for (String num : createdList) {
			if (num.equals(waybillNum)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<String> findCreateCustList(String type) {
		return recompenseService.findCreateCustList(type);
	}

	@Override
	public boolean insertCreateCust(String waybillnum, String type) {
		return recompenseService.insertCreateCust(waybillnum, type);
	}

	@Override
	public boolean updateCreateCust(String waybillnum, String type) {
		return recompenseService.updateCreateCust(waybillnum, type);
	}

	/**
	 * 理赔监控，获取短信接收人
	 * 
	 * @param recompenseIdList
	 *            理赔ID集合
	 * @param noticeTypes
	 *            通知对象（经理、理赔专员、区域经理、大区总经理、事业部办公室主任、事业部总裁）
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	@Override
	public List<RecSmsInformation> getMessageReceiver(
			List<String> recompenseIdList, String noticeTypes) {
		if ("".equals(noticeTypes)) {
			RecompenseMonitorException re = new RecompenseMonitorException(
					RecompenseMonitorExceptionType.RECOMPENSE_MESSAGERECEIVER_NULL);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {

			};
		}
		List<RecSmsInformation> infoList = new ArrayList<RecSmsInformation>();
		List<RecSmsInformation> list = new ArrayList<RecSmsInformation>();
		if (noticeTypes != null && !"".equals(noticeTypes)) {
			String[] types = noticeTypes.split(",");
			for (int i = 0; i < types.length; i++) {
				if (Constants.MESSAGE_RECEIVER_MANAGER.equals(types[i])) {
					list = recompenseService
							.getMessageReceiverByManager(recompenseIdList);
				} else if (Constants.MESSAGE_RECEIVER_COMMISSIONER
						.equals(types[i])) {
					list = recompenseService
							.getMessageReceiverByCommissioner(recompenseIdList);
				} else if (Constants.MESSAGE_RECEIVER_AREAMANAGER
						.equals(types[i])) {
					list = recompenseService
							.getMessageReceiverByAreaManager(recompenseIdList);
				} else if (Constants.MESSAGE_RECEIVER_GENERALMANAGER
						.equals(types[i])) {
					list = recompenseService
							.getMessageReceiverByGeneralManager(recompenseIdList);
				} else if (Constants.MESSAGE_RECEIVER_DIRECTOR.equals(types[i])) {
					list = recompenseService
							.getMessageReceiverByDirector(recompenseIdList);
				} else if (Constants.MESSAGE_RECEIVER_PRESIDENT
						.equals(types[i])) {
					list = recompenseService
							.getMessageReceiverByPresident(recompenseIdList);
				}
				if (list != null && list.size() > 0) {
					for (int j = 0; j < list.size(); j++) {
						RecSmsInformation recompense = (RecSmsInformation) list
								.get(j);
						if (recompense != null) {
							infoList.add(recompense);
						}
					}
				}
			}
		}
		return infoList;
	}

	/**
	 * 理赔监控，短信发送模板
	 * 
	 * @param status
	 *            处理状态
	 * @param recompenseNum
	 *            运单号
	 * @param reportDeptName
	 *            营业部名称
	 * @param recompenseType
	 *            理赔类型
	 * @param noticeTypes
	 *            通知对象（经理、理赔专员、区域经理、大区总经理、事业部办公室主任、事业部总裁）
	 * @return 返回短信发送模板
	 */
	@Override
	public String getSMSTemplate(String status, String recompenseNum,
			String reportDeptName, String recompenseType, String noticeTypes) {
		if ("".equals(noticeTypes)) {
			RecompenseMonitorException re = new RecompenseMonitorException(
					RecompenseMonitorExceptionType.RECOMPENSE_MESSAGERECEIVER_NULL);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {

			};
		}
		String smsTemplate = "";
		String recDuration = this.getRecDuration(recompenseNum, recompenseType) == null ? ""
				: this.getRecDuration(recompenseNum, recompenseType);
		if (Constants.STATUS_SUBMITED.equalsIgnoreCase(status)) {
			smsTemplate = MessageFormat.format(
					Constants.MESSAGE_SUBMITED_CONTENT, new Object[] {
							recompenseNum, reportDeptName, recDuration });
		} else if (Constants.STATUS_HANDLED.equalsIgnoreCase(status)) {
			if (noticeTypes != null
					&& !"".equals(noticeTypes)
					&& Integer.parseInt(noticeTypes.substring(
							noticeTypes.length() - 1, noticeTypes.length())) >= 3) {
				smsTemplate = MessageFormat.format(
						Constants.MESSAGE_HANDLED_U_CONTENT, new Object[] {
								recompenseNum, reportDeptName, recDuration });
			} else {
				smsTemplate = MessageFormat.format(
						Constants.MESSAGE_HANDLED_D_CONTENT, new Object[] {
								recompenseNum, reportDeptName, recDuration });
			}
		} else if (Constants.STATUS_IN_OA_APPROVE.equalsIgnoreCase(status)) {
			smsTemplate = MessageFormat.format(
					Constants.MESSAGE_INOAAPPROVE_CONTENT, new Object[] {
							recompenseNum, reportDeptName, recDuration });
		} else if (Constants.STATUS_APPROVED.equalsIgnoreCase(status)) {
			smsTemplate = MessageFormat.format(
					Constants.MESSAGE_APPROVED_CONTENT, new Object[] {
							recompenseNum, reportDeptName, recDuration });
		} else if (Constants.STATUS_IN_PAYMENT.equalsIgnoreCase(status)) {
			smsTemplate = MessageFormat.format(
					Constants.MESSAGE_INPAYMENT_CONTENT, new Object[] {
							recompenseNum, reportDeptName, recDuration });
		} else {
			RecompenseMonitorException re = new RecompenseMonitorException(
					RecompenseMonitorExceptionType.RECOMPENSE_SMSTEMPLATE_REQUIRE_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {

			};
		}
		return smsTemplate;
	}

	/**
	 * 理赔监控，短信发送模板(多条短信发送模板)
	 * 
	 * @param status
	 *            处理状态
	 * @param noticeTypes
	 *            通知对象（经理、理赔专员、区域经理、大区总经理、事业部办公室主任、事业部总裁）
	 * @return
	 */
	@Override
	public String getSMSTemplateMore(String status, String noticeTypes) {
		if ("".equals(noticeTypes)) {
			RecompenseMonitorException re = new RecompenseMonitorException(
					RecompenseMonitorExceptionType.RECOMPENSE_MESSAGERECEIVER_NULL);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {

			};
		}
		String smsTemplate = "";
		if (Constants.STATUS_SUBMITED.equalsIgnoreCase(status)) {
			smsTemplate = Constants.MESSAGE_SUBMITED_CONTENT_MORE;
		} else if (Constants.STATUS_HANDLED.equalsIgnoreCase(status)) {
			if (noticeTypes != null
					&& !"".equals(noticeTypes)
					&& Integer.parseInt(noticeTypes.substring(
							noticeTypes.length() - 1, noticeTypes.length())) >= 3) {
				smsTemplate = Constants.MESSAGE_HANDLED_U_CONTENT_MORE;
			} else {
				smsTemplate = Constants.MESSAGE_HANDLED_D_CONTENT_MORE;
			}
		} else if (Constants.STATUS_IN_OA_APPROVE.equalsIgnoreCase(status)) {
			smsTemplate = Constants.MESSAGE_INOAAPPROVE_CONTENT_MORE;
		} else if (Constants.STATUS_APPROVED.equalsIgnoreCase(status)) {
			smsTemplate = Constants.MESSAGE_APPROVED_CONTENT_MORE;
		} else if (Constants.STATUS_IN_PAYMENT.equalsIgnoreCase(status)) {
			smsTemplate = Constants.MESSAGE_INPAYMENT_CONTENT_MORE;
		} else {
			RecompenseMonitorException re = new RecompenseMonitorException(
					RecompenseMonitorExceptionType.RECOMPENSE_SMSTEMPLATE_REQUIRE_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {

			};
		}
		return smsTemplate;
	}

	/**
	 * SmsInformation数组转SmsInformation集合
	 * 
	 * @param SmsInformation
	 *            SmsInformation对象
	 * @return
	 */
	private static List<SmsInformation> convertToSmsInformation(
			SmsInformation[] SmsInformation) {
		List<SmsInformation> smsInfos = new ArrayList<SmsInformation>();
		for (SmsInformation msgInfo : SmsInformation) {
			SmsInformation info = new SmsInformation();
			// 收信人电话
			info.setMobile(msgInfo.getMobile());
			// 消息内容
			info.setMsgContent(msgInfo.getMsgContent());
			// 业务类型
			info.setMsgType(msgInfo.getMsgType());
			// 短信发送人
			info.setSender(msgInfo.getSender());
			// 短信发送部门(标杆编码)
			info.setSendDept(msgInfo.getSendDept());
			smsInfos.add(info);
		}
		return smsInfos;
	}

	/**
	 * 理赔监控，发送短信
	 * 
	 * @param messageInfos
	 *            短信实体
	 */
	@Override
	public void sendSmsInfo(List<CellphoneMessageInfo> messageInfos) {
		List<SmsInformation> smsInfos = new ArrayList<SmsInformation>();
		try {
			for (int i = 0; i < messageInfos.size(); i++) {
				CellphoneMessageInfo messageInfo = (CellphoneMessageInfo) messageInfos
						.get(i);
				SmsInformation smsInfo = new SmsInformation();
				smsInfo.setMobile(messageInfo.getMobile());
				smsInfo.setServiceType(messageInfo.getServiceType());
				smsInfo.setMsgContent(messageInfo.getMsgContent());
				smsInfo.setSendTime(new Timestamp(new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss:SSS").parse(
						messageInfo.getSendTime()).getTime()));
				smsInfo.setLatestSendTime(messageInfo.getLatestSendTime());
				smsInfo.setMsgType(messageInfo.getMsgType());
				smsInfo.setMsgSource(messageInfo.getMsgSource());
				smsInfo.setSender(messageInfo.getSender());
				smsInfo.setSendDept(messageInfo.getSendDept());
				smsInfo.setWaybillNo(messageInfo.getWaybillNo());
				smsInfo.setUnionId(messageInfo.getUnionId());
				smsInfos.add(smsInfo);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// 短信发送，每次仅能发送SMS_SIZE 条
		int q = smsInfos.size() / SMS_SIZE; // 商
		int r = smsInfos.size() % SMS_SIZE; // 余数
		/**
		 * 根据SMS_SIZE处理短信。 通过商、余数进行批量处理。
		 */
		SmsInformation[] info = null;
		for (int j = 0; j <= q; j++) {
			if (j != q) {
				// 每次处理SMS_SIZE条短信（未处理短信数 > SMS_SIZE时）
				info = new SmsInformation[SMS_SIZE];
				System.arraycopy(smsInfos.toArray(), j * SMS_SIZE, info, 0,
						SMS_SIZE);
			} else {
				// 每次处理r条短信（未处理短信数 < SMS_SIZE 时）
				info = new SmsInformation[r];
				System.arraycopy(smsInfos.toArray(), j * SMS_SIZE, info, 0, r);
			}
			recompenseService.sendSmsInfo(convertToSmsInformation(info));
		}
	}

	/**
	 * 理赔监控，发送短信(多条理赔短信发送)
	 * 
	 * @param messageInfos
	 *            短信实体
	 * @param status
	 *            处理状态
	 * @param noticeTypes
	 *            通知对象（经理、理赔专员、区域经理、大区总经理、事业部办公室主任、事业部总裁）
	 */
	@Override
	public void sendSmsInfoMore(List<CellphoneMessageInfo> messageInfos,
			String status, String noticeTypes) {
		List<SmsInformation> smsInfos = new ArrayList<SmsInformation>();
		try {
			for (int i = 0; i < messageInfos.size(); i++) {
				CellphoneMessageInfo messageInfo = (CellphoneMessageInfo) messageInfos
						.get(i);
				String recompenseNum = messageInfo.getWaybillNo();
				String reportDeptName = messageInfo.getSendDeptName();
				String recompenseType = messageInfo.getRecompenseType();
				String msgContent = null;
				if(StringUtil.isEmpty(status)&&StringUtil.isEmpty(noticeTypes)){
					msgContent=this.getSMSTemplateForOnline(recompenseNum,messageInfo.getMsgContent());
				}else{
					msgContent=this.getSMSTemplate(status, recompenseNum,
							reportDeptName, recompenseType, noticeTypes);
				}
				SmsInformation smsInfo = new SmsInformation();
				smsInfo.setMobile(messageInfo.getMobile());
				smsInfo.setServiceType(messageInfo.getServiceType());
				smsInfo.setMsgContent(msgContent);
				smsInfo.setSendTime(new Timestamp(new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss:SSS").parse(
						messageInfo.getSendTime()).getTime()));
				smsInfo.setLatestSendTime(messageInfo.getLatestSendTime());
				smsInfo.setMsgType(messageInfo.getMsgType());
				smsInfo.setMsgSource(messageInfo.getMsgSource());
				smsInfo.setSender(messageInfo.getSender());
				smsInfo.setSendDept(messageInfo.getSendDept());
				smsInfo.setWaybillNo(messageInfo.getWaybillNo());
				smsInfo.setUnionId(messageInfo.getUnionId());
				smsInfos.add(smsInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 短信发送，每次仅能发送SMS_SIZE 条
		int q = smsInfos.size() / SMS_SIZE; // 商
		int r = smsInfos.size() % SMS_SIZE; // 余数
		/**
		 * 根据SMS_SIZE处理短信。 通过商、余数进行批量处理。
		 */
		SmsInformation[] info = null;
		for (int j = 0; j <= q; j++) {
			if (j != q) {
				// 每次处理SMS_SIZE条短信（未处理短信数 > SMS_SIZE时）
				info = new SmsInformation[SMS_SIZE];
				System.arraycopy(smsInfos.toArray(), j * SMS_SIZE, info, 0,
						SMS_SIZE);
			} else {
				// 每次处理r条短信（未处理短信数 < SMS_SIZE 时）
				info = new SmsInformation[r];
				System.arraycopy(smsInfos.toArray(), j * SMS_SIZE, info, 0, r);
			}
			recompenseService.sendSmsInfo(convertToSmsInformation(info));
		}
	}

	private String getSMSTemplateForOnline(String recompenseNum,String msgContent) {
		msgContent = msgContent.replace("***", recompenseNum);
		return msgContent;
	}

	/**
	 * 理赔监控短信发送模板，获取处理天数
	 * 
	 * @param recompenseNum
	 *            运单号
	 * @param recompenseType
	 *            理赔类型
	 * @return 返回处理天数
	 */
	@Override
	public String getRecDuration(String recompenseNum, String recompenseType) {
		String duration = "";
		if (Constants.ONLINE_TYPE.equalsIgnoreCase(recompenseType)) {
			duration = recompenseService.getRecDurationOnline(recompenseNum);
		} else {
			List<RecSmsInformation> list = recompenseService
					.getRecDurationNoOnline(recompenseNum);
			for (Iterator<RecSmsInformation> iter = list.iterator(); iter
					.hasNext();) {
				RecSmsInformation recompense = (RecSmsInformation) iter.next();
				if (recompense != null) {
					duration = recompense.getClaimDuration();
					if (duration == null || "".equals(duration)) {
						duration = recompense.getRecDuration();
					}
				}
			}
		}
		return duration;
	}

	public PilotCityManager getPilotCityManager() {
		return pilotCityManager;
	}

	public void setPilotCityManager(PilotCityManager pilotCityManager) {
		this.pilotCityManager = pilotCityManager;
	}

	@SuppressWarnings("unused")
	@Override
	public boolean submitRecompenseOaApproval(RecompenseApplication recompense,
			User commiter, String reportManCode) {
		try {
			String applyManCode = commiter.getEmpCode().getEmpCode();
			String currentStandardCode = commiter.getEmpCode().getDeptId()
					.getStandardCode();
			NormalRecompenseInfo nri = transNormalRecompenseInfo(recompense,
					reportManCode, applyManCode);
			String workflowNum = "";
			String bizCode = "";
			String recompenseNum = "";
			// workflowNum = recompenseApplyOperate.applyNormalRecompense(nri);

			String processIdAndBizCode = normalClaimManager.createWorkflow(
					recompense.getRecompenseNum(), recompense.getWaybill()
							.getTransType(), recompense.getReportDept(),
					recompense.getReportMan(), recompense.getReportDeptName());
			if (processIdAndBizCode != null && !"".equals(processIdAndBizCode)) {
				workflowNum = processIdAndBizCode.split(",")[0].toString();
				bizCode = processIdAndBizCode.split(",")[1].toString();
				recompenseNum = processIdAndBizCode.split(",")[2].toString();
			}

			String standardCode = getDepartmentService().getDepartmentById(
					recompense.getDeptId()).getStandardCode();
			NormalClaim normalClaim = this.ConvertRecToClaim(nri, workflowNum,
					standardCode, currentStandardCode, bizCode,

					recompense.getReportDept(), reportManCode,
					recompense.getDeptId(), recompenseNum);
			NormalClaim nc = normalClaimService
					.getNormalClaimByWorkflowNo(normalClaim.getWorkflowNo());
			if (nc == null) {

				normalClaimService.insertNormalClaim(normalClaim);
			} else {
				normalClaimService.updateNormalClaim(normalClaim);
			}
			// String workflowNum = "OA_TEST_" +
			// recompense.getWorkflowId().toString();
			OAWorkflow flow = new OAWorkflow();
			// flow.setAuditDate(new Date());
			// flow.setAuditopinion("Testing");
			flow.setCommitDate(new Date());
			flow.setCommiter(commiter);
			flow.setRecompenseId(recompense.getId());
			flow.setWorkflowType(Constants.WORKFLOW_TYPE_NORMAL);
			flow.setWorkflowStatus(Constants.WORKFLOW_STATUS_SUBMIT);
			flow.setWorkflowNum(workflowNum);
			if (flow != null) {
				recompenseService.insertWorkflow(flow);
			}
			return true;
		}
		/*
		 * catch (CrmBusinessException e) { throw new
		 * GeneralException(e.getMessage(), e.getMessage(), e, new Object[] {})
		 * { }; }
		 */
		catch (BPMSException e) {
			e.printStackTrace();
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_INTERFACE);

			throw new GeneralException(e.getMsg(), e.getMsg(), re,
					new Object[] {}) {

			};
		} catch (Exception e) {
			e.printStackTrace();
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_INTERFACE);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	private NormalRecompenseInfo transNormalRecompenseInfo(
			RecompenseApplication app, String reportManCode, String applyManCode) {
		Waybill waybill = app.getWaybill();
		NormalRecompenseInfo nri = new NormalRecompenseInfo();
		// 申请人工号
		nri.setApplyPersonCode(applyManCode);
		// 线索工号,报案人经理工号
		nri.setClueUserId(reportManCode);
		// 运单号/差错编号
		nri.setTransportOrErrorCode(waybill.getWaybillNumber());
		// 保价人/发货联系人
		nri.setInsuredUnits(waybill.getInsured());
		// 联系电话
		nri.setContactPhone(waybill.getTelephone());
		// 运输类型
		nri.setHaulType(waybill.getTransType());
		// 收货部门（名称）
		nri.setReceivingDept(waybill.getReceiveDept());
		// 始发站
		nri.setStartingStation(waybill.getStartStation());
		// 货物名称
		nri.setGoodsName(waybill.getGoodsName());
		// 货物属性：件/重/体
		nri.setGoodsAttribute(waybill.getPwv());
		// 保险金额
		nri.setInsuredAmount(waybill.getInsurAmount());
		// 目标部门，到达部门
		nri.setTargetDept(waybill.getEndStation());
		// 发货日期
		nri.setSendingDate(waybill.getSendDate());
		// 出险日期
		nri.setDangerDate(app.getInsurDate());
		// 所属区域(名称)
		nri.setArea(app.getDeptName());
		// 理赔类型(名称)
		String recompenseType = DataDictionaryUtil.getCodeDesc(
				DataHeadTypeEnum.RECOMPENSE_TYPE, app.getRecompenseType());
		nri.setClaimsType(recompenseType);
		// 冲账方式
		nri.setOffsetTypt("");
		// 报案人(名称)
		nri.setCaseReporter(app.getReportManName());
		// 报案部门(名称)
		nri.setReportDept(app.getReportDeptName());
		// 报案日期
		nri.setReportDate(app.getReportDate());
		// 处理人(名称)
		nri.setHandler(app.getModifyUserName());
		// 处理日期
		nri.setHandleDate(app.getModifyDate());
		// 其他费用说明
		nri.setOtherCost(app.getCostExplain());
		// 索赔金额
		nri.setClaimAmount(app.getRecompenseAmount());
		// 责任部门/大区（如果有多个则进行组装：重庆大区,江门大区）
		List<ResponsibleDept> rdLst = app.getResponsibleDeptList();
		String rdStr = "";
		for (ResponsibleDept responsibleDept : rdLst) {
			rdStr = rdStr + responsibleDept.getDeptName() + ",";
		}
		nri.setResponsibileDept(rdStr);
		// 正常理赔金额
		nri.setNormalAmount(app.getNormalAmount());
		// 实际理赔金额
		nri.setActualClaimsAmount(app.getRealAmount());
		// 入公司费用
		nri.setTocompanyCost(0);
		// 出险信息
		List<AccidentDescription> accidentDescriptionInfos = new ArrayList<NormalRecompenseInfo.AccidentDescription>();
		List<IssueItem> iiLst = app.getIssueItemList();
		for (IssueItem issueItem : iiLst) {
			AccidentDescription ad = new AccidentDescription();
			ad.setAccidentAcount(issueItem.getQuality());
			String insurType = DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.DANGER_TYPE, issueItem.getInsurType());
			ad.setAccidentType(insurType);
			ad.setDescription(issueItem.getDescription());
			accidentDescriptionInfos.add(ad);
		}
		nri.setAccidentDescriptionInfos(accidentDescriptionInfos);

		// 入部门费用
		List<IndeptCharges> indeptCharges = new ArrayList<NormalRecompenseInfo.IndeptCharges>();
		List<DeptCharge> dcLst = app.getDeptChargeList();
		for (DeptCharge deptCharge : dcLst) {
			IndeptCharges idc = new IndeptCharges();
			idc.setCharges(deptCharge.getAmount());
			idc.setDept(deptCharge.getDeptName());
			indeptCharges.add(idc);
		}
		nri.setIndeptCharges(indeptCharges);

		// 奖罚明细列表
		List<RewardPunishment> rewardPunishments = new ArrayList<NormalRecompenseInfo.RewardPunishment>();
		List<AwardItem> aiLst = app.getAwardItemList();
		for (AwardItem awardItem : aiLst) {
			RewardPunishment rp = new RewardPunishment();
			rp.setDisposeTarget(awardItem.getDeptName());
			rp.setMoney(awardItem.getAmount());
			String awardTargetType = DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.AWARD_TARGET_TYPE,
					awardItem.getAwardTargetType());
			rp.setProcessType(awardTargetType);
			String awardType = DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.AWARD_TYPE, awardItem.getAwardType());
			rp.setRewardPunishmentType(awardType);
		}
		nri.setRewardPunishments(rewardPunishments);

		return nri;
	}

	private NormalClaim ConvertRecToClaim(NormalRecompenseInfo nri,
			String workflowNum, String areaCode, String currentStandardCode,
			String bizCode, String reportDept, String reportManCode,
			String areaId, String recompenseNum) {

		NormalClaim normalClaim = new NormalClaim();
		// 工作流编号
		normalClaim.setProcessinstId(workflowNum);
		// 申请人工号
		normalClaim.setApplyPersonCode(nri.getApplyPersonCode());
		// 申请人所在部门标杆编码
		normalClaim.setStandardCode(currentStandardCode);
		// 线索工号,报案人经理工号
		normalClaim.setClueUserId(nri.getClueUserId());
		// 运单号/差错编号
		normalClaim.setTransportOrErrorCode(nri.getTransportOrErrorCode());
		// 保价人/发货联系人
		normalClaim.setInsuredUnits(nri.getInsuredUnits());
		// 联系电话
		normalClaim.setContactPhone(nri.getContactPhone());
		// 运输类型
		normalClaim.setHaulType(nri.getHaulType());
		// 收货部门（名称）
		normalClaim.setReceivingDept(nri.getReceivingDept());
		// 始发站
		normalClaim.setStartingStation(nri.getStartingStation());
		// 货物名称
		normalClaim.setGoodsName(nri.getGoodsName());
		// 货物属性：件/重/体
		normalClaim.setGoodsAttribute(nri.getGoodsAttribute());
		// 保险金额
		normalClaim.setInsuredAmount(nri.getInsuredAmount());
		// 目标部门，到达部门
		normalClaim.setTargetDept(nri.getTargetDept());
		// 发货日期
		normalClaim.setSendingDate(nri.getSendingDate());
		// 出险日期
		normalClaim.setDangerDate(nri.getDangerDate());
		// 所属区域(名称)
		normalClaim.setAreaId(areaId);
		// 所属区域(名称)
		normalClaim.setArea(nri.getArea());
		// 理赔类型(名称)
		normalClaim.setClaimsType(nri.getClaimsType());
		// 冲账方式
		normalClaim.setOffsetType(nri.getOffsetTypt());
		// 报案人(工号)
		normalClaim.setCaseReporter(reportManCode);
		// 报案人(名称)
		normalClaim.setCaseReporterName(nri.getCaseReporter());
		// 报案部门Id
		normalClaim.setReportDept(reportDept);
		// 报案部门(名称)
		normalClaim.setReportDeptName(nri.getReportDept());
		// 报案日期
		normalClaim.setReportDate(nri.getReportDate());
		// 处理人(名称)
		normalClaim.setHandler(nri.getHandler());
		// 处理日期
		normalClaim.setHandleDate(nri.getHandleDate());
		// 其他费用说明
		normalClaim.setOtherCost(nri.getOtherCost());
		// 索赔金额
		normalClaim.setClaimAmount(nri.getClaimAmount());
		// 责任部门/大区（如果有多个则进行组装：重庆大区,江门大区）
		normalClaim.setResponsibileDept(nri.getResponsibileDept());
		// 正常理赔金额
		normalClaim.setNormalAmount(nri.getNormalAmount());
		// 实际理赔金额
		normalClaim.setActualClaimsAmount(nri.getActualClaimsAmount());
		// 入公司费用
		normalClaim.setToCompanyCost(nri.getTocompanyCost());
		// 大区标杆编码
		normalClaim.setAreaCode(areaCode);
		// 流程编号 格式：ICRM年月日+6位数字
		normalClaim.setWorkflowNo(bizCode);
		return normalClaim;
	}

	public ExpressPointBusinessDeptManager getExpressPointBusinessDeptManager() {
		return expressPointBusinessDeptManager;
	}

	public void setExpressPointBusinessDeptManager(
			ExpressPointBusinessDeptManager expressPointBusinessDeptManager) {
		this.expressPointBusinessDeptManager = expressPointBusinessDeptManager;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-8-19
	 * @param phone
	 * @param limit
	 * @param start
	 * @return
	 * 
	 */
	@Override
	public Map<String, Object> searchRecompenseHistoryList(String phone,
			int limit, int start) {
		List<RecompenseForCC> recompenseForCCList = recompenseService
				.searchRecompenseHistoryList(phone, limit, start);
		// 保存转换后的数据
		List<RecompenseForCC> recompenseForCCListNew = new ArrayList<RecompenseForCC>();
		if (null != recompenseForCCList) {

			for (RecompenseForCC rc : recompenseForCCList) {
				// 理赔类型
				String recompenseType = DataDictionaryUtil.getCodeDesc(
						DataHeadTypeEnum.RECOMPENSE_TYPE,
						rc.getRecompenseType());
				// 理赔时间
				String recompenseMethod = DataDictionaryUtil.getCodeDesc(
						DataHeadTypeEnum.RECOMPENSE_WAY,
						rc.getRecompenseMethod());
				RecompenseForCC recompenseForCC = new RecompenseForCC();
				String status = DataDictionaryUtil.getCodeDesc(
						DataHeadTypeEnum.RECOMPENSE_STATUS, rc.getStatus());
				recompenseForCC.setRecompenseAmount(rc.getRecompenseAmount());
				recompenseForCC.setReportDate(rc.getReportDate());
				recompenseForCC.setReportDept(rc.getReportDept());
				recompenseForCC.setRecompenseMethod(recompenseMethod);
				recompenseForCC.setRecompenseType(recompenseType);
				recompenseForCC.setStatus(status);
				recompenseForCC.setWaybillNumber(rc.getWaybillNumber());

				recompenseForCCListNew.add(recompenseForCC);

			}

		}
		// 统计条数
		int count = recompenseService.countRecompenseHistory(phone);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", recompenseForCCListNew);
		map.put("count", count);
		return map;

	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/**
	 * 在线理赔查询
	 */
	@Override
	public Map<String,Object> searchOnlineApply(OnlineApplyCondition condition,
			int limit, int start) {
		condition = transformCondition(condition);
		return recompenseService.searchOnlineApply(condition, limit, start);
	}

	/**
	 * 在线理赔查询条件转换
	 */
	private OnlineApplyCondition transformCondition(
			OnlineApplyCondition condition) {
		String waybillNumber = condition.getWaybillNumber();
		if (!StringUtil.isEmpty(waybillNumber)) {
			condition = new OnlineApplyCondition();
			condition.setWaybillNumber(waybillNumber);
			return condition;
		}
		String claimAmount = condition.getClaimAmount();
		if (!StringUtil.isEmpty(claimAmount)) {
			String[] arr = claimAmount.split("-");
			condition.setClaimAmountStart(Integer.parseInt(arr[0].trim()));
			condition.setClaimAmountEnd(Integer.parseInt(arr[1].trim()));
		}
		return condition;
	}

	@Override
	public List<RecSmsInformation> searchOnlineApplyPerson(List<String> waybillNumberList, String noticeTypes) {
		return recompenseService.searchOnlineApplyPerson(waybillNumberList, noticeTypes);
	}
	
	/**
	 * 运输类型转换中文至英文
	 * @author andy
	 * @version 0.1 2013-11-02
	 * @param app
	 * @return RecompenseApplication
	 */
	@Override
	public RecompenseApplication replaceTranType(RecompenseApplication app) {
		if(app != null && app.getWaybill() != null) {
			if(!StringUtils.isEmpty(app.getWaybill().getTransType())) {
				// 汽运
				if (Constants.TRANS_VEHICLE_NAME.equalsIgnoreCase(app.getWaybill().getTransType())) {
					app.getWaybill().setTransType(Constants.TRANS_VEHICLE);
				}
				// 空运
				else if (Constants.TRANS_AIRCRAFT_NAME.equalsIgnoreCase(app.getWaybill().getTransType())) {
					app.getWaybill().setTransType(Constants.TRANS_AIRCRAFT);
				}
				// 快递
				else if (Constants.TRANS_EXPRESS_NAME.equalsIgnoreCase(app.getWaybill().getTransType())) {
					app.getWaybill().setTransType(Constants.TRANS_EXPRESS);
				}
			}else {
				app.getWaybill().setTransType(Constants.TRANS_VEHICLE);
			}
		}
		return app;
	}

	/**
	 * <p>
	 * Description:
	 * </p>
	 * @author 	zouming
	 * @extends	@see com.deppon.crm.module.recompense.server.manager.RecompenseManager#pressDoForOnline(java.lang.String)
	 * @version 0.1 2013-11-13上午9:37:45
	 * @param waybillnumber
	 * @update 	2013-11-13上午9:37:45
	 */
	@Override
	public void pressDoForOnline(String waybillnumber) {
		List<OnlineApply> onlineApplyList = recompenseService
				.searchOnlineApplyByWaybillNum(waybillnumber);
		OnlineApply onlineApply = null;
		if(onlineApplyList.size()>0){
			onlineApply = onlineApplyList.get(0);
		}

		// 1、催办后，对应部门经理，系统信息提醒催办信息（待办事宜）
		com.deppon.crm.module.common.shared.domain.Message message = new com.deppon.crm.module.common.shared.domain.Message();
		message.setTasktype(Constants.TODO_TASK_TYPE);
		message.setTaskcount(1);
		message.setIshaveinfo("运单号："+onlineApply.getWaybillNumber()
				+ " 在线理赔催办");

		String userId = recompenseService.getManagerIdByDeptId(onlineApply.getApplyDeptId());
		message.setUserid(Integer.parseInt(userId));
		messageManager.addMessage(message);

		// 2、催单次数加1
		OnlineApply online = new OnlineApply();
		Integer hastenCount = onlineApply.getHastenCount();
		if (hastenCount != null && hastenCount != 0) {
			online.setHastenCount(hastenCount+1);
		}else{
			online.setHastenCount(1);
		}
		online.setId(onlineApply.getId());
		online.setLastHastenTime(new Date());
		recompenseService.updateOnlineApply(online);
	}

	/**
	 * <p>
	 * Description:在线理赔监控查看详情
	 * </p>
	 * @author 	zouming
	 * @extends	@see com.deppon.crm.module.recompense.server.manager.RecompenseManager#lookUpOnlineApplyDetail(java.lang.String, com.deppon.crm.module.authorization.shared.domain.User)
	 * @version 0.1 2013-11-16下午4:55:23
	 * @param recompenseId
	 * @param user
	 * @return
	 * @update 	2013-11-16下午4:55:23
	 */
	@Override
	public RecompenseApplication lookUpOnlineApplyDetail(String id,
			User user) {
		OnlineApply onlineApply = this.recompenseService.getOnlineApplyById(id);
		
		// 1、校验 只需判断wb！=null
		Waybill wb = getWaybillByNumForOnlineMonitor(onlineApply.getWaybillNumber());
		if (wb != null) {

			// 2、把在线理赔申请转换为理赔单
			RecompenseApplication recompense = new RecompenseApplication();
			recompense.setOnlineApplyId(id);
			recompense.setRecompenseMethod(onlineApply.getRecompenseMethod());
			recompense.setRecompenseType(onlineApply.getRecompenseType());
			recompense.setCompanyName(onlineApply.getCustomerId());
			if (onlineApply.getMobile() != null) {
				recompense.setCompanyPhone(onlineApply.getMobile());
			} else {
				recompense.setCompanyPhone(onlineApply.getTelphone());
			}

			recompense.setWaybill(wb);
			String applyPart = onlineApply.getApplyPart();
			Member customer = null;
			if (applyPart != null && applyPart.equals("receive")) {
				recompense.setClaimParty("2");
				customer = memberManager.getMemberByCustNumber(wb
						.getArriveCustomerId());
			} else {
				recompense.setClaimParty("1");
				customer = memberManager.getMemberByCustNumber(wb
						.getLeaveCustomerId());
			}
			recompense.setCustomer(customer);
			recompense.setRecompenseAmount(onlineApply.getRecompenseAmount());

			List<RecompenseAttachment> attachList = new ArrayList<RecompenseAttachment>();
			if (null != onlineApply.getFrontImage()
					&& !"".equals(onlineApply.getFrontImage())) {
				RecompenseAttachment frontImage = new RecompenseAttachment();
				frontImage.setAttachName("证件正面."
						+ getExtensionName(onlineApply.getFrontImage()));
				frontImage.setAttachAddress(onlineApply.getFrontImage());
				attachList.add(frontImage);
			}
			if (null != onlineApply.getBackImage()
					&& !"".equals(onlineApply.getBackImage())) {
				RecompenseAttachment backImage = new RecompenseAttachment();
				backImage.setAttachName("证件背面."
						+ getExtensionName(onlineApply.getBackImage()));
				backImage.setAttachAddress(onlineApply.getBackImage());
				attachList.add(backImage);
			}
			recompense.setAttachmentList(attachList);
			return recompense;
		}
		return null;
	}
	
	private MuchRecompenseInfo transMuchRecompenseInfo(
			RecompenseApplication recompense, String reportManCode,
			String reportManName) {
		Overpay overpay = recompense.getOverpay();
		MuchRecompenseInfo mci = new MuchRecompenseInfo();
		// 申请人姓名
		mci.setApplyPersonName(reportManName);
		// 申请工号
		mci.setApplyPersonCode(reportManCode);
		// 多陪单号
		mci.setTransportOrErrorCode(recompense.getWaybill().getWaybillNumber());
		// 多赔金额
		mci.setRecompensiesMoney(overpay.getOverpayAmount());
		// 合计多陪总金额(多赔金额+理赔处理金额)
		mci.setAmountinTotal(overpay.getTotalAmount());

		// 应收账款是否收回
		mci.setHasRepayDebt(overpay.isRecoverYszk());
		// 部门会计(名称)
		mci.setDeptAccountant(overpay.getDeptAccount().getEmpCode()
				.getEmpCode());
		// 申请事由
		mci.setApplyReason(overpay.getOverpayReason());
		// 所属事业部(需要标杆编码)
		mci.setEnterpriseDept(overpay.getOverpayBu().getStandardCode());// ("W0113010301");//
		return mci;
	}
	
	@SuppressWarnings("unused")
	@Override
	public boolean submitRecompenseOverpayApproval(
			RecompenseApplication recompense, User commiter,
			String reportManCode, String reportManName) {
		try {
			MuchRecompenseInfo mri = transMuchRecompenseInfo(recompense,
					reportManCode, reportManName);
			
			String bizCode = bpsWorkflowManager.bizCode();
			
			Overpay pay = recompense.getOverpay();
			pay.setRecompenseId(recompense.getId());
			pay.setWorkflowNo(bizCode);
			pay.setApplyStandardCode(commiter.getEmpCode().getDeptId().getStandardCode());
			pay.setApplyDeptId(commiter.getEmpCode().getDeptId().getId());
			pay.setCreateUser(((User) UserContext.getCurrentUser()).getEmpCode().getId());
			recompenseService.insertOverpay(recompense.getOverpay());
			
			Map<String, String> map = bpsWorkflowManager.createWorkflow(NormalClaimUtil.OVERPAY_PROCESS_DEFINITION_NAME, 
					NormalClaimUtil.OVERPAT_PROCESS_INSTANCE_NORMALCLAIM, 
					NormalClaimUtil.OVERPAY_PROCESS_DESCRIPTION, 
					bizCode);
			
			/*String workflowNum = recompenseApplyOperate
					.applyMuchRecompense(mri);*/
			String workflowNum = map.get("processId");
			
			// 临时调用
			// String workflowNum = "OVERPAY_TEST_"
			// + recompense.getWorkflowId().toString();
			OAWorkflow flow = new OAWorkflow();
			flow.setCommitDate(new Date());
			flow.setCommiter(commiter);
			flow.setRecompenseId(recompense.getId());
			flow.setWorkflowType(Constants.WORKFLOW_TYPE_OVERPAY);
			flow.setWorkflowStatus(Constants.WORKFLOW_STATUS_SUBMIT);
			flow.setWorkflowNum(workflowNum);
			if (flow != null) {
				Overpay overpay = recompense.getOverpay();
				overpay.setRecompenseId(recompense.getId());
				overpay.setWorkNumber(workflowNum);
				overpay.setWorkflowNo(bizCode);
				overpay.setApplyStandardCode(commiter.getEmpCode().getDeptId().getStandardCode());
				overpay.setApplyDeptId(commiter.getEmpCode().getDeptId().getId());
				overpay.setCreateUser(((User) UserContext.getCurrentUser()).getEmpCode().getId());
				recompenseService.updateOverpay(recompense.getOverpay());
				recompenseService.insertWorkflow(flow);
			}
			return true;
		} catch (BPMSException e) {
			e.printStackTrace();
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_INTERFACE);

			throw new GeneralException(e.getMsg(), e.getMsg(), re,
					new Object[] {}) {

			};
		} catch (Exception e) {
			e.printStackTrace();
			RecompenseException re = new RecompenseException(
					RecompenseExceptionType.RECOMPENSE_INTERFACE);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}

	}
	/**
	 * 根据部门标杆编码查询分部或者大区
	 */
	private Department searchBigArea(String standCode,Boolean searchExpressPoint){
		if(standCode ==null||"".equals(standCode)){
			return null;
		}
		if(searchExpressPoint){
			ExpressPointBusinessDept epbd =expressPointBusinessDeptManager
					.getExpressPointBusinessDeptByDeptCode(standCode);
			if(epbd!=null&&epbd.getExpressPointCode()!=null
					&&!"".equals(epbd.getExpressPointCode())){
				standCode=epbd.getExpressPointCode();
			}
		}
		Map<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("standCode",standCode);
		paramsMap.put("lastWord","快递分部");
		Department BigArea = null;
		BigArea = departmentService.getAllParentDeptBystandCode(paramsMap);
		if(BigArea ==null){
			paramsMap.put("lastWord", "大区");
			BigArea = departmentService.getAllParentDeptBystandCode(paramsMap);
		}
		return BigArea;
	}
	/**
	 * 根据部门ID得到对应的点部ID
	 */
	private Department searchExpressPointDept(String StandCode){
		ExpressPointBusinessDept epbd =expressPointBusinessDeptManager
				.getExpressPointBusinessDeptByDeptCode(StandCode);
		return departmentService.getDeptByStandardCode(epbd.getExpressPointCode());
	}
}
