package com.deppon.crm.module.bpsworkflow.server.manager.impl;
 

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.bpms.module.shared.domain.ApprovalInfo;
import com.deppon.bpms.module.shared.domain.CommonConstant;
import com.deppon.bpms.module.shared.domain.ProcessInfo;
import com.deppon.bpms.module.shared.domain.WorkFlowInfo;
import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.bps.server.manager.IBpsWorkflowManager;
import com.deppon.crm.module.bps.shared.domain.BpsWorkflowCondition;
import com.deppon.crm.module.bps.shared.domain.WorkFlowInfoEnc;
import com.deppon.crm.module.bps.shared.domain.WorkflowApprove;
import com.deppon.crm.module.bpsworkflow.server.manager.ICRMWorkflowManager;
import com.deppon.crm.module.bpsworkflow.server.util.BpsConstant;
import com.deppon.crm.module.bpsworkflow.server.util.BpsUtils;
import com.deppon.crm.module.bpsworkflow.shared.exception.NoWorkFlowException;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.customer.server.manager.IContractWorkflowManager;
import com.deppon.crm.module.customer.server.service.IContractService;
import com.deppon.crm.module.customer.shared.domain.ContractOperatorLog;
import com.deppon.crm.module.customer.shared.domain.ContractWorkflowInfo;
import com.deppon.crm.module.custrepeat.server.manager.IRepeatedCustManager;
import com.deppon.crm.module.custrepeat.shared.domain.RepetitiveCustWorkFlowInfo;
import com.deppon.crm.module.frameworkimpl.server.util.EncryptUtil;
import com.deppon.crm.module.keycustomer.server.manager.IKeyCustomerManager;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerWorkflowInfo;
import com.deppon.crm.module.marketing.server.manager.IMarketActivityManager;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivity;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.server.service.IEmployeeService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.recompense.shared.domain.AwardItem;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.IssueItem;
import com.deppon.crm.module.recompense.shared.domain.Overpay;
import com.deppon.crm.module.recompense.shared.domain.ResponsibleDept;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.workflow.server.manager.INormalClaimManager;
import com.deppon.crm.module.workflow.shared.domain.NormalClaim;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.shared.util.classes.BeanUtils;
import com.deppon.foss.framework.shared.util.string.StringUtil;

/**
 * 
 * <p>
 * Description:工作流管理<br />
 * </p>
 * @title WorkflowManager.java
 * @package com.deppon.crm.module.workflow.server.manager.impl 
 * @author 106138
 * @version 0.1 2014-2-27
 */
public class CRMWorkflowManager implements ICRMWorkflowManager {
	// 合同service
	private IContractService contractService;
	// 用户service
	private IUserService userService;
	// 部门service
	private IDepartmentService departmentService;
	// 员工service
	private IEmployeeService employeeService;
	// 工作流审批
	private WorkflowApproveOperate workflowApproveOperate;
	// bps工作流
	private IBpsWorkflowManager bpsWorkflowManager;
	//大客户manager
	private IKeyCustomerManager keyCustomerManager;
	//疑似重复客户工作流
	private IRepeatedCustManager repeatedCustManager; 
	//营销manager
	private IMarketActivityManager marketActivityManager;
	//合同manager
	private IContractWorkflowManager contractWorkflowManager;
	//理赔manager
	private INormalClaimManager normalClaimManager;
	//查询待处理工作流
	public Map<String, Object> findToHandleWorkflow(
			BpsWorkflowCondition workflowCondition) {
		/**
		 * 把前台传过来的工作流类型转换为流程实例
		 */
		if (null != workflowCondition) {
			if (null == workflowCondition.getType()) {
				workflowCondition.setType("CONTRACT_LTT_NEW");
			}
			//把工作流类型转换为对应的流程实例
			String type = BpsUtils.convertSearchWorkFowType(workflowCondition.getType());
			//若类型不为空
			if (StringUtil.isNotEmpty(type)) {
				workflowCondition.setType(type);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != workflowCondition&&!StringUtil.isEmpty(workflowCondition.getDeptId())) {
			//查询部门信息
			Department dep = departmentService.getDepartmentById(workflowCondition.getDeptId());
			if (dep != null) {
				//将部门标杆编码赋值给workflowCondition
				workflowCondition.setFstandardcode(dep.getDeptCode());
			}
		}
		if (!StringUtil.isEmpty(workflowCondition.getApplerId())) {
			//查询员工信息
			Employee emp = employeeService.getEmpById(workflowCondition.getApplerId());
			if (emp != null) {
				//将员工工号赋值给workflowCondition
				workflowCondition.setApplerId(emp.getEmpCode());
			}
		}
		//查询待处理工作流
		List<WorkFlowInfo> list = queryTodoItems(workflowCondition.getStart(),
				workflowCondition.getLimit(), workflowCondition.getType(),
				workflowCondition.getApplerId(),
				workflowCondition.getFstandardcode(),
				workflowCondition.getState(), workflowCondition.getStartTime(),
				workflowCondition.getEndTime(),
				workflowCondition.getWorkflowNo());
		//查询待处理工作流条数
		long totalCount = queryTodoCounts(workflowCondition.getType(),
				workflowCondition.getApplerId(),
				workflowCondition.getFstandardcode(),
				workflowCondition.getState(), workflowCondition.getStartTime(),
				workflowCondition.getEndTime(),
				workflowCondition.getWorkflowNo());
		List<WorkFlowInfoEnc> listEnc = new ArrayList<WorkFlowInfoEnc>();
		for (WorkFlowInfo wfi : list) {
			WorkFlowInfoEnc wfie = new WorkFlowInfoEnc();
			BeanUtils.copyProperties(wfi, wfie);
			wfie.setProcessinstidEnc(EncryptUtil.encrypt(wfi.getProcessinstid()
					+ "", BpsConstant.WORKFLOW_DESC_KEY));
			wfie.setWorkItemIdEnc(EncryptUtil.encrypt(wfi.getWorkItemId() + "",
					BpsConstant.WORKFLOW_DESC_KEY));
			wfie.setBusiNoEnc(EncryptUtil.encrypt(wfi.getBusino(),
			BpsConstant.WORKFLOW_DESC_KEY));
			listEnc.add(wfie);
		}

		map.put("list", listEnc);
		map.put("totalCount", totalCount);
		return map;
	}
	/**
	 * 
	* @Title: queryTodoCounts 
	* @Description: 查询待处理工作流条数
	* @author LiuY 
	* @param claimsProcessDefinitionName 流程定义名称
	* @param applerId 起草人
	* @param fstandardcode 部门标杆编码
	* @param state 工作流状态
	* @param startTime 开始时间
	* @param endTime 结束时间
	* @param workflowNo 工作流号
	* @return long 
	* @throws
	 */
	private long queryTodoCounts(String claimsProcessDefinitionName,
			String applerId, String fstandardcode, String state,
			Date startTime, Date endTime, String workflowNo) {
		User user = (User) UserContext.getCurrentUser();
		String empCode = user.getEmpCode().getEmpCode();
		Map<String, Object> map = new HashMap<String, Object>();
		//当前用户ID
		map.put(CommonConstant.USERID, empCode);
		// 业务系统编码
		map.put(CommonConstant.SYSCODE, BpsConstant.CRM_BIZ_CODE); 
		//流程定义名称
		map.put(CommonConstant.PROCESSDEFNAME, claimsProcessDefinitionName);
		if (StringUtil.isEmpty(workflowNo)) {
			if (!StringUtil.isEmpty(state)) {
				//审批状态
				map.put(CommonConstant.CONDITION, state);
			}
			// 开始时间
			if (null != startTime) {
				//设置开始时间" 00:00:00";
				Date startDate = formatDate(formatDate(startTime)+ BpsConstant.STARTTIME);
				map.put(CommonConstant.STARTTIME, startDate);
			}
			// 结束时间
			if (null != endTime) {
				//设置结束时间" 23:59:59"
				Date endDate = formatDate(formatDate(endTime)+ BpsConstant.ENDTIME);
				map.put(CommonConstant.ENDTIME, endDate);
			}
			//起草人
			if (!StringUtil.isEmpty(applerId)) {
				map.put(CommonConstant.APPLERID, applerId);
			}
			//部门标杆编码
			if (!StringUtil.isEmpty(fstandardcode)) {
				map.put(CommonConstant.DEPARTMENTID, fstandardcode);
			}
		} else {
			//当工作流号不为空时，将工作流号赋值给map,忽略时间、起草人、部门、审批状态的查询条件
			map.put(CommonConstant.BUSINO, workflowNo);
		}
		//查询条数
		long num = bpsWorkflowManager.queryTodoCounts(map);
		return num;
	}
	/**
	 * 
	* @Title: queryTodoItems 
	* @Description: 查询待处理工作流 
	* @author LiuY 
	* @param start 
	* @param limit
	* @param claimsProcessDefinitionName 流程定义名称
	* @param applerId 起草人
	* @param fstandardcode 部门标杆编码
	* @param state 工作流状态
	* @param startTime 开始时间
	* @param endTime 结束时间 
	* @param workflowNo 工作流号
	* @return List<WorkFlowInfo> 工作流信息集合
	* @throws
	 */
	private List<WorkFlowInfo> queryTodoItems(int start, int limit,
			String claimsProcessDefinitionName, String applerId,
			String fstandardcode, String state, Date startTime, Date endTime,
			String workflowNo) {
		//获取当前用户
		User user = (User) UserContext.getCurrentUser();
		//获取员工工号
		String empCode = user.getEmpCode().getEmpCode();
		Map<String, Object> map = new HashMap<String, Object>();
		// 当前登录者ID
		map.put(CommonConstant.USERID, empCode); 
		// 业务编码
		map.put(CommonConstant.SYSCODE, BpsConstant.CRM_BIZ_CODE); 
		//流程定义名称
		map.put(CommonConstant.PROCESSDEFNAME, claimsProcessDefinitionName);
		//如果工作流好为空
		if (StringUtil.isEmpty(workflowNo)) {
			if (!StringUtil.isEmpty(state)) {
				map.put(CommonConstant.CONDITION, state);
			}
			// 开始时间
			if (null != startTime) {
				//设置开始时间" 00:00:00";
				Date startDate = formatDate(formatDate(startTime)+ BpsConstant.STARTTIME);
				map.put(CommonConstant.STARTTIME, startDate);
			}
			// 结束时间
			if (null != endTime) {
				//设置结束时间" 23:59:59"
				Date endDate = formatDate(formatDate(endTime)+ BpsConstant.ENDTIME);
				map.put(CommonConstant.ENDTIME, endDate);
			}
			// 申请人工号
			if (!StringUtil.isEmpty(applerId)) {
				map.put(CommonConstant.APPLERID, applerId);
			}
			// 申请人所在部门编码
			if (!StringUtil.isEmpty(fstandardcode)) {
				map.put(CommonConstant.DEPARTMENTID, fstandardcode);
			}
		} else {
			//当工作流号不为空时，将工作流号赋值给map,忽略时间、起草人、部门、审批状态的查询条件
			map.put(CommonConstant.BUSINO, workflowNo);
		}
		map.put(CommonConstant.START, start);
		map.put(CommonConstant.LIMIT, limit);
		//查询待处理工作流信息
		List<WorkFlowInfo> list = bpsWorkflowManager.queryTodoItems(map);
		return list;
	}
	/**
	 * 
	* @Title: findHandledWorkflow 
	* @Description: 查询已处理工作流
	* @author LiuY 
	* @param workflowCondition
	* @return 
	* @throws
	 */
	public Map<String, Object> findHandledWorkflow(
			BpsWorkflowCondition workflowCondition) {
		/**
		 * 把前台传过来的工作流类型转换为流程实例
		 */
		if (null != workflowCondition) {
			//把工作流类型转换为对应的流程实例
			String type = BpsUtils.convertSearchWorkFowType(workflowCondition.getType());
			if (StringUtil.isNotEmpty(type)) {
				workflowCondition.setType(type);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != workflowCondition&&!StringUtil.isEmpty(workflowCondition.getDeptId())) {
			//获取部门信息
			Department dep = getDepartmentService().getDepartmentById(workflowCondition.getDeptId());
			if (dep != null) {
				//将部门标杆编码赋值给workflowCondition
				workflowCondition.setFstandardcode(dep.getDeptCode());
			}
		}
		if (!StringUtil.isEmpty(workflowCondition.getApplerId())) {
			//获取员工信息
			Employee emp = getEmployeeService().getEmpById(workflowCondition.getApplerId());
			//若员工信息为空
			if (emp != null) {
				//将员工工号赋值给workflowCondition的起草人
				workflowCondition.setApplerId(emp.getEmpCode());
			}
		}
		//查询处理完成的工作流信息
		List<WorkFlowInfo> list = queryFinishedItems(
				workflowCondition.getStart(), workflowCondition.getLimit(),
				workflowCondition.getType(), workflowCondition.getApplerId(),
				workflowCondition.getFstandardcode(),
				workflowCondition.getState(), workflowCondition.getStartTime(),
				workflowCondition.getEndTime(),
				workflowCondition.getWorkflowNo());
		//查询处理完成的工作流条数
		long totalCount = queryFinishedCounts(workflowCondition.getType(),
				workflowCondition.getApplerId(),
				workflowCondition.getFstandardcode(),
				workflowCondition.getState(), workflowCondition.getStartTime(),
				workflowCondition.getEndTime(),
				workflowCondition.getWorkflowNo());
		//获取到工作流信息，加密后传到前台
		List<WorkFlowInfoEnc> listEnc = new ArrayList<WorkFlowInfoEnc>();
		for (WorkFlowInfo wfi : list) {
			//创建加密工作流对象
			WorkFlowInfoEnc wfie = new WorkFlowInfoEnc();
			//复制对象
			BeanUtils.copyProperties(wfi, wfie);
			//加密流程id
			wfie.setProcessinstidEnc(EncryptUtil.encrypt(wfi.getProcessinstid()+ "", BpsConstant.WORKFLOW_DESC_KEY));
			//加密工作项id
			wfie.setWorkItemIdEnc(EncryptUtil.encrypt(wfi.getWorkItemId() + "",BpsConstant.WORKFLOW_DESC_KEY));
			//赋值流程定义名称
			wfie.setProcessinstname(wfie.getProcessCHName());
			//加密ICRM工作流号
			wfie.setBusiNoEnc(EncryptUtil.encrypt(wfi.getBusino(),BpsConstant.WORKFLOW_DESC_KEY));
			//在List中添加对象
			listEnc.add(wfie);
		}
		map.put("list", listEnc);
		map.put("totalCount", totalCount);
		return map;

	}
	/**
	 * 
	* @Title: queryFinishedCounts 
	* @Description: 查询审批完成的工作流条数
	* @author LiuY 
	* @param definitionName 流程定义名称
	* @param applerId 起草人
	* @param departmentId 部门标杆编码
	* @param state 状态
	* @param startTime 开始时间
	* @param endTime 结束时间
	* @param workflowNo 工作流号
	* @return long
	* @throws
	 */
	private long queryFinishedCounts(String definitionName, String applerId,
			String departmentId, String state, Date startTime, Date endTime,
			String workflowNo) {
		//获取当前登录人
		User user = (User) UserContext.getCurrentUser();
		//获取员工工号
		String empCode = user.getEmpCode().getEmpCode();
		Map<String, Object> map = new HashMap<String, Object>();
		// 当前登录者id
		map.put(CommonConstant.USERID, empCode); 
		//ICRM
		map.put(CommonConstant.SYSCODE, BpsConstant.CRM_BIZ_CODE);
		//流程定义名称
		map.put(CommonConstant.PROCESSDEFNAME, definitionName);
		// 业务系统编码
		if (StringUtil.isEmpty(workflowNo)) {
			if (!StringUtil.isEmpty(state)) {
				map.put(CommonConstant.CONDITION, state);
			}
			// 开始时间
			if (null != startTime) {
				Date startDate = formatDate(formatDate(startTime)+ BpsConstant.STARTTIME);
				map.put(CommonConstant.STARTTIME, startDate);
			}
			// 结束时间
			if (null != endTime) {
				Date endDate = formatDate(formatDate(endTime)+ BpsConstant.ENDTIME);
				map.put(CommonConstant.ENDTIME, endDate);
			}
			// 申请人工号
			if (!StringUtil.isEmpty(applerId)) {
				map.put(CommonConstant.APPLERID, applerId); 
			}
			// 申请人所在部门编码
			if (!StringUtil.isEmpty(departmentId)) {
				map.put(CommonConstant.DEPARTMENTID, departmentId);
			}
		} else {
			//当工作流号不为空时，将工作流号赋值给map,忽略时间、起草人、部门、审批状态的查询条件
			map.put(CommonConstant.BUSINO, workflowNo);
		}
		// 返回已办数目
		long num = bpsWorkflowManager.queryFinishedCounts(map);
		return num;
	}
	/**
	 * 
	* @Title: queryFinishedItems 
	* @Description: 查询审批完成的工作流信息 
	* @author LiuY 
	* @param start 从start个开始
	* @param limit  查询的长度
	* @param definitionName 流程定义名称
	* @param applerId 起草人
	* @param departmentId 部门标杆编码
	* @param state 工作流状态
	* @param startTime 开始时间
	* @param endTime 结束时间
	* @param workflowNo 工作流号
	* @return List<WorkFlowInfo> 工作流信息集合
	* @throws
	 */
	private List<WorkFlowInfo> queryFinishedItems(int start, int limit,
			String definitionName, String applerId, String departmentId,
			String state, Date startTime, Date endTime, String workflowNo) {
		//获取当前登录人
		User user = (User) UserContext.getCurrentUser();
		//获取当前登录人工号
		String empCode = user.getEmpCode().getEmpCode();
		Map<String, Object> map = new HashMap<String, Object>();
		 // 当前登录者id
		map.put(CommonConstant.USERID, empCode);
		// 业务系统编码
		map.put(CommonConstant.SYSCODE, BpsConstant.CRM_BIZ_CODE); 
		// 流程定义名称
		map.put(CommonConstant.PROCESSDEFNAME, definitionName);
		//当工作流号为空时
		if (StringUtil.isEmpty(workflowNo)) {
			if (!StringUtil.isEmpty(state)) {
				map.put(CommonConstant.CONDITION, state);
			}
			// 开始时间
			if (null != startTime) {
				//设置开始时间为 日期+" 00:00:00"
				Date startDate = formatDate(formatDate(startTime)+ BpsConstant.STARTTIME);
				map.put(CommonConstant.STARTTIME, startDate);
			}
			// 结束时间
			if (null != endTime) {
				//设置结束时间为 日期+" 23:59:59"
				Date endDate = formatDate(formatDate(endTime)+ BpsConstant.ENDTIME);
				map.put(CommonConstant.ENDTIME, endDate);
			}
			// 申请人工号
			if (!StringUtil.isEmpty(applerId)) {
				map.put(CommonConstant.APPLERID, applerId); 
			}
			// 申请人所在部门编码
			if (!StringUtil.isEmpty(departmentId)) {
				map.put(CommonConstant.DEPARTMENTID, departmentId);
			}
		}
		// 分页开始位置
		map.put(CommonConstant.START, start); 
		// 每页显示的条数
		map.put(CommonConstant.LIMIT, limit); 
		if (!StringUtil.isEmpty(workflowNo)) {
			map.put(CommonConstant.BUSINO, workflowNo);
		}
		// 已审批流程集合
		List<WorkFlowInfo> list = bpsWorkflowManager.queryFinishedItems(map);
		for (WorkFlowInfo w : list) {
			w.setProcessDefName(definitionName);
		}
		return list;
	}
	/**
	 * 
	 * <p>
	 * Description:工作流查询<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-15
	 * @param workflowCondition
	 * @return
	 * Map<String,Object>
	 */
	public Map<String, Object> findWorkflow(
			BpsWorkflowCondition workflowCondition) {
		/**
		 * 把前台传过来的工作流类型转换为流程实例
		 */
		if (null != workflowCondition) {
			String type = BpsUtils.convertSearchWorkFowType(workflowCondition.getType());
			if (StringUtil.isNotEmpty(type)) {
				workflowCondition.setType(type);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		//查询我的工作流信息
		List<WorkFlowInfo> list = queryMyWorkFlow(workflowCondition.getStart(),
				workflowCondition.getLimit(), workflowCondition.getType(),
				workflowCondition.getState(), workflowCondition.getStartTime(),
				workflowCondition.getEndTime(),
				workflowCondition.getWorkflowNo());
		//查询我的工作流条数
		long totalCount = queryMyWorkFlowCount(workflowCondition.getType(),
				workflowCondition.getState(), workflowCondition.getStartTime(),
				workflowCondition.getEndTime(),
				workflowCondition.getWorkflowNo());
		//定义加密工作流集合
		List<WorkFlowInfoEnc> listEnc = new ArrayList<WorkFlowInfoEnc>();
		for (WorkFlowInfo wfi : list) {
			//定义加密工作流对象
			WorkFlowInfoEnc wfie = new WorkFlowInfoEnc();
			//复制对象
			BeanUtils.copyProperties(wfi, wfie);
			//加密流程id
			wfie.setProcessinstidEnc(EncryptUtil.encrypt(wfi.getProcessinstid()+ "", BpsConstant.WORKFLOW_DESC_KEY));
			//加密工作项ID
			wfie.setWorkItemIdEnc(EncryptUtil.encrypt(wfi.getWorkItemId() + "",BpsConstant.WORKFLOW_DESC_KEY));
			//加密工作流号
			wfie.setBusiNoEnc(EncryptUtil.encrypt(wfi.getBusino(),BpsConstant.WORKFLOW_DESC_KEY));
			listEnc.add(wfie);
		}
		map.put("list", listEnc);
		map.put("totalCount", totalCount);
		return map;
	}
	/**
	 * 
	* @Title: queryApprovalInfoByProcessInstID 
	* @Description: 查询工作流审批描述
	* @author LiuY 
	* @param proId
	* @return 
	* @throws
	 */
	@Override
	public List<ApprovalInfo> queryApprovalInfoByProcessInstID(long proId) {
		//查询工作流审批记录
		List<ApprovalInfo> list = bpsWorkflowManager.queryApprovalInfoByProcessInstID(proId);
		for (ApprovalInfo app : list) {
			//获取当前登录人
			Employee emp = employeeService.getEmpByCode(app.getUserid());
			if (emp != null) {
				// 此处借存审批人职位
				app.setBusino(emp.getPosition());
			}
		}
		return list;
	}
	/**
	 * 
	 * <p>
	 * Description工作流审批<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-17
	 * @param workflowApprove
	 * @return
	 *
	 */
	@Transactional
	public boolean workflowApprove(WorkflowApprove workflowApprove) {
		//调用审批方法
		return workflowApproveOperate.workflowApprove(workflowApprove);
	}
	/**
	 * 
	* @Title: oneKeyForApprove 
	* @Description: 一键审批
	* @author LiuY 
	* @param workflowApprove
	* @return 
	* @throws
	 */
	@Override
	public boolean oneKeyForApprove(WorkflowApprove workflowApprove) {
		//调用一键审批方法
		return workflowApproveOperate.oneKeyForApprove(workflowApprove);
	}
	/**
	 * 
	 * <p>
	 * Description:获得当前审批人<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-11-28
	 * @param proId
	 * @return
	 * 
	 */
	@Override
	public String getCurrentApproval(long proId) {
		//获取当前审批人
		return bpsWorkflowManager.getCurrentApproval(proId);
	}
	/**
	 * 
	 * <p>
	 * Description:根据条件查询工作流条数<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-11-16
	 * @param claimsProcessDefinitionName
	 * @param state
	 * @param startTime
	 * @param endTime
	 * @param workflowNo
	 * @return long
	 */
	private long queryMyWorkFlowCount(String processDefName, String state,Date startTime, Date endTime, String workflowNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		//获取当前用户
		User user = (User) UserContext.getCurrentUser();
		String empCode = user.getEmpCode().getEmpCode();
		if (StringUtil.isEmpty(workflowNo)) {
			// 当前登录者id
			map.put(CommonConstant.USERID, empCode);
			// 申请人所在部门编码
			// map.put(CommonConstant.DEPARTMENTID,
			// user.getEmpCode().getDeptId().getStandardCode());
		} else {
			//根据不同的工作流类型查询不同的工作流信息表
			String newEmpCode = getEmpCodeByWorkFlowNum(processDefName,workflowNo);
			//如果根据编号和类型没有查到有效工作流 返回 一个新的集合
			if (BpsConstant.NOWORKFLOW.equals(newEmpCode)) {
				return 0L;
			} else {
				map.put(CommonConstant.USERID, newEmpCode);
			}
		}
		// 业务系统编码
		map.put(CommonConstant.SYSCODE, BpsConstant.CRM_BIZ_CODE);
		// 流程定义名称
		map.put(CommonConstant.PROCESSDEFNAME, processDefName);
		if (StringUtil.isEmpty(workflowNo)) {
			if (!StringUtil.isEmpty(state)) {
				// 审批状态：审批中
				map.put(CommonConstant.CONDITION, state);
			}
			// 开始时间
			if (null != startTime) {
				//设置开始时间为日期+" 00:00:00"
				Date startDate = formatDate(formatDate(startTime)+ BpsConstant.STARTTIME);
				map.put(CommonConstant.STARTTIME, startDate);
			}
			// 结束时间
			if (null != endTime) {
				//设置结束时间为 日期+ 23:59:59"
				Date endDate = formatDate(formatDate(endTime)+ BpsConstant.ENDTIME);
				map.put(CommonConstant.ENDTIME, endDate);
			}
		} else {
			//根据工作流号查询工作流起草人工号
			String newEmpCode = getEmpCodeByWorkFlowNum(processDefName,workflowNo);
			map.put(CommonConstant.USERID, newEmpCode);
		}
		//查询未处理条数
		return bpsWorkflowManager.queryMyProcessCount(map);
	}

	/**
	 * 
	 * <p>
	 * Description:根据条件查询工作流<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-11-16
	 * @param start
	 * @param limit
	 * @param claimsProcessDefinitionName
	 * @param state
	 * @param startTime
	 * @param endTime
	 * @param workflowNo
	 * @return List<WorkFlowInfo>
	 */
	private List<WorkFlowInfo> queryMyWorkFlow(int start, int limit,
			String processDefName, String state, Date startTime, Date endTime,
			String workflowNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) UserContext.getCurrentUser();
		String empCode = user.getEmpCode().getEmpCode();
		if (StringUtil.isEmpty(workflowNo)) {
			// 当前登录者id
			map.put(CommonConstant.USERID, empCode);
		} else {
			//根据不同的工作流类型查询不同的工作流信息表
			String newEmpCode = getEmpCodeByWorkFlowNum(processDefName,workflowNo);
			//如果根据编号和类型没有查到有效工作流 返回 一个新的集合
			if (BpsConstant.NOWORKFLOW.equals(newEmpCode)) {
				return new ArrayList<WorkFlowInfo>();
			} else {
				map.put(CommonConstant.USERID, newEmpCode);
			}
		}
		// 业务系统编码
		map.put(CommonConstant.SYSCODE, BpsConstant.CRM_BIZ_CODE);
		// 流程定义名称
		map.put(CommonConstant.PROCESSDEFNAME, processDefName);
		if (StringUtil.isEmpty(workflowNo)) {
			if (!StringUtil.isEmpty(state)) {
				// 审批状态：审批中
				map.put(CommonConstant.CONDITION, state);
			}
			// 开始时间
			if (null != startTime) {
				//设置开始时间" 00:00:00"
				Date startDate = formatDate(formatDate(startTime)+ BpsConstant.STARTTIME);
				map.put(CommonConstant.STARTTIME, startDate);
			}
			// 结束时间
			if (null != endTime) {
				//设置结束时间 " 23:59:59"
				Date endDate = formatDate(formatDate(endTime)+ BpsConstant.ENDTIME);
				map.put(CommonConstant.ENDTIME, endDate);
			}
		}
		// 分页开始位置
		map.put(CommonConstant.START, start);
		// 每页显示的条数
		map.put(CommonConstant.LIMIT, limit);
		if (!StringUtil.isEmpty(workflowNo)) {
			map.put(CommonConstant.BUSINO, workflowNo);
		}
		//查询我的工作流信息
		List<WorkFlowInfo> wfList = bpsWorkflowManager.queryMyWorkFlow(map);
		return wfList;
	}
	/**
	 * 
	* @Title: formatDate 
	* @Description: 时间信息格式化 
	* @author LiuY 
	* @param date
	* @return String
	* @throws
	 */
	private String formatDate(Date date) {
		//时间格式"yyyy-MM-dd"
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	/**
	 * 
	* @Title: formatDate 
	* @Description: 将字符串类型的时间转化为Date类型
	* @author LiuY 
	* @param date
	* @return Date
	* @throws
	 */
	private Date formatDate(String date) {
		try {
			//将字符串类型的时间转化为Date类型
			return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
	/**
	 * 
	* @Title: findContractWorkflowInfoByWorkNo 
	* @Description: 根据工作流号，查询合同工作流信息
	* @author LiuY 
	* @param workItemId 工作流号
	* @param processType 工作流类型(合同工作流)
	* @return  合同工作流信息
	* @throws
	 */
	public ContractWorkflowInfo findContractWorkflowInfoByWorkNo(String workItemId, String processType) {
		ContractWorkflowInfo contractWorkflowInfo = null;
		if (StringUtil.isNotEmpty(workItemId)) {
			//调用contractWorkflowManager的查询合同工作流信息的方法
			contractWorkflowInfo = contractWorkflowManager.findContractWorkflowInfoByWorkNo(workItemId, processType);
		}
		return contractWorkflowInfo;
	}
	/**
	 * 
	* @Title: findBigCustomerWorkflowInfoByWorkNo 
	* @Description: 根据工作流号，查询大客户工作流信息
	* @author LiuY 
	* @param workItemId 工作流号
	* @param processType 工作流类型（大客户工作流）
	* @return  大客户准入准出工作流信息
	* @throws
	 */
	public KeyCustomerWorkflowInfo findBigCustomerWorkflowInfoByWorkNo(String workItemId, String processType) {
		KeyCustomerWorkflowInfo keyCustomerWorkflowInfo = null;
		if (StringUtil.isNotEmpty(workItemId)) {
			//调用keyCustomerManager的查询大客户工作流信息的方法
			keyCustomerWorkflowInfo = keyCustomerManager.findWorkflowInfoByBusino(workItemId);
		}
		return keyCustomerWorkflowInfo ;
	}
	/**
	 * 
	* @Title: findRepetitiveCustWorkFlowInfoByWorkNo 
	* @Description: 根据工作流号，查询疑似重复工作流信息
	* @author LiuY 
	* @param workItemId 工作流号
	* @param processType 工作流类型（疑似重复工作流）
	* @return  疑似重复工作流信息
	* @throws
	 */
	public RepetitiveCustWorkFlowInfo findRepetitiveCustWorkFlowInfoByWorkNo(String workItemId, String processType) {
		RepetitiveCustWorkFlowInfo repetitiveCustWorkFlowInfo = null;
		if (StringUtil.isNotEmpty(workItemId)) {
			//调用repeatedCustManager的查询疑似重复客户工作流方法
			repetitiveCustWorkFlowInfo = repeatedCustManager.findRepetitiveCustWorkFlowInfoByWorkNo(workItemId, processType);
		}
		return repetitiveCustWorkFlowInfo;
	}
	/**
	 * 
	* @Title: findMarketActivityInfoByWorkNum 
	* @Description: 根据工作流号，查询区域营销工作流信息 
	* @author LiuY 
	* @param workItemId 工作流号
	* @param processType 工作流类型（区域营销工作流）
	* @return  区域营销工作流信息
	* @throws
	 */
	public MarketActivity findMarketActivityInfoByWorkNum(String workItemId,String processType){
		MarketActivity marketActivity = null;
		if(StringUtil.isNotEmpty(workItemId)){
			//调用marketActivityManager的查询区域营销工作流的方法
			marketActivity = marketActivityManager.searchMarketActivityByWorkflowNum(workItemId);
		}
		return marketActivity;
	}
	/**
	 * 
	* @Title: getNormalClaim 
	* @Description: 根据OA工作流号，获取常规理赔工作流信息
	* @author LiuY 
	* @param processInstId OA工作流号
	* @return 常规理赔工作流信息
	* @throws
	 */
	@Override
	public NormalClaim getNormalClaim(Long processInstId) {
		//调用normalClaimManager的查询常规理赔工作流的方法
		NormalClaim nc = normalClaimManager.getNormalClaim(processInstId);
		return nc;
	}
	/**
	 * 
	* @Title: getDeptChargeByProcessinstId 
	* @Description: 入部门费用
	* @author LiuY 
	* @param processInstId
	* @return 
	* @throws
	 */
	@Override
	public List<DeptCharge> getDeptChargeByProcessinstId(long processInstId) {
		List<DeptCharge> deptChargeList = normalClaimManager.getDeptChargeByProcessinstId(processInstId);
		return deptChargeList;
	}
	/**
	 * 
	* @Title: getIssueItemByProcessinstId 
	* @Description: 出险信息
	* @author LiuY 
	* @param processInstId
	* @return 
	* @throws
	 */
	@Override
	public List<IssueItem> getIssueItemByProcessinstId(long processInstId) {
		List<IssueItem> issueItemList = normalClaimManager.getIssueItemByProcessinstId(processInstId);
		return issueItemList;
	}
	/**
	 * 
	* @Title: getAwardItemByProcessinstId 
	* @Description: 奖罚明细
	* @author LiuY 
	* @param processInstId
	* @return 
	* @throws
	 */
	@Override
	public List<AwardItem> getAwardItemByProcessinstId(long processInstId) {
		List<AwardItem> awardItemList = normalClaimManager.getAwardItemByProcessinstId(processInstId);
		return awardItemList;
	}
	/**
	 * 
	* @Title: getResponsibleDeptByProcessinstId 
	* @Description: 奖罚明细
	* @author LiuY 
	* @param processInstId
	* @return 
	* @throws
	 */
	@Override
	public List<ResponsibleDept> getResponsibleDeptByProcessinstId(long processInstId) {
		List<ResponsibleDept> responsibleDeptList = normalClaimManager.getResponsibleDeptByProcessinstId(processInstId);
		return responsibleDeptList;
	}
	/**
	 * 
	* @Title: getDetailOverpayByWorkNumber 
	* @Description: 根据工作流号，获取多赔工作流信息
	* @author LiuY  
	* @param workNumber 工作流号
	* @return  多赔工作流信息
	* @throws
	 */
	@Override
	public Overpay getDetailOverpayByWorkNumber(String workNumber) {
		 return normalClaimManager.getDetailOverpayByWorkNumber(workNumber);
	}
	/**
	 * 
	* @Title: getOverpayFileList 
	* @Description: 根据工作流号，获取多赔文件信息
	* @author LiuY 
	* @param processInstId oa工作流号
	* @return 文件信息
	* @throws
	 */
	@Override
	public List<FileInfo> getOverpayFileList(String processInstId){
		return normalClaimManager.getOverpayFileList(processInstId);
	}
	/**
	 * 
	* @Title: getServiceRecoveryByOaWorkflowNum 
	* @Description: 根据工作流号，获取服务补救工作流信息
	* @author LiuY 
	* @param workflowNo 工作流号
	* @return 服务补救工作流信息
	* @throws
	 */
	public ServiceRecovery getServiceRecoveryByOaWorkflowNum(String workflowNo){
		ServiceRecovery serviceRecovery = normalClaimManager.getServiceRecoveryByOaWorkflowNum(workflowNo);
		return serviceRecovery;
	}
//	/**
//	 * 
//	 * <p>
//	 * Description:根据工作流号查询操作人部门<br />
//	 * </p>
//	 * 
//	 * @author pgj
//	 * @version 0.1 2013-11-28
//	 * @param workflowNo
//	 * @return Department
//	 */
//	public User searchOperaDeptByWorkflowId(String workflowNo) {
//		List<ContractOperatorLog> contractLogList = contractService
//				.searchOperaLogByWorkflowId(workflowNo);
//		String userId = contractLogList.get(0).getCreateUser();
//		Employee emp = employeeService.getEmpById(userId);
//		User user = userService.findByLoginName(emp.getEmpCode());
//		return userService.getUserRoleFunDeptById(user.getId());
//	}
//	public boolean contractApprove(String busino, Long workItemid,
//			Long procesId, boolean wkStatus, String wkman,
//			String approveOpinion, Date wktime) {
//		//d调用bpsjar包 的方法进行审批 返回一个map结果集 包含三个参数 success  isEnd isDisagree
//		Map<String, Object> map = bpsWorkflowManager.workflowApprove(
//				workItemid, procesId, wkStatus?"0":"1",
//				approveOpinion, null);
//		//获得审批结果是否成功
//		Boolean isSuccess=(Boolean) map.get("sucess");
//		if(!isSuccess){
//			return isSuccess;
//		}
//		//如果是是结束 则更新合同操作日志未同意
//		if ((Boolean) map.get("isEnd")) {
//			//true
////			contractManager.contractApprove(busino, (Boolean) map.get("isEnd"),
////					wkman, wktime);
//		}
//		//如果不同意 则更新合同操作日志为不同意
//		if ((Boolean) map.get("isDisAgree")) {
//			//fasle
////			contractManager.contractApprove(busino, false,
////					wkman, wktime);
//		}
//		return isSuccess;
//	}

	/**
	 * 根据工作流号获取工作流信息
	 * @param workFlowNo
	 * @param workFlowType
	 * @return
	 */
	@Override
	public WorkFlowInfo findWorkFlowInfoEncByBusino(String workFlowNo,String workFlowType){
		 String processDefName = "";
		if (null == workFlowType || "".equals(workFlowType)) {
				workFlowType = "CONTRACT_LTT_NEW";
		}
		//把工作流转化成对应流程实例
		String type = BpsUtils.convertSearchWorkFowType(workFlowType);
		if (StringUtil.isNotEmpty(type)) {
		  processDefName = type;
		}
		List<WorkFlowInfo> workFlowList = this.queryMyWorkFlow(0, 20, processDefName, null, null, null, workFlowNo);
		if(null == workFlowList || workFlowList.size()!=1){
			throw new NoWorkFlowException("工作流不存在，请重新选择！",new Object[]{});
		}
		WorkFlowInfo wfi = workFlowList.get(0);
		return wfi;
	}


	/**
	 * <p>
	 *	Description: 根据工作流编号和类型，返回工作流的起草人
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-11
	 * @param type	工作流类型（流程定义名称区分）
	 * @param workFlowNum 工作流编号
	 * @return
	 * String empCode 工作流的创建人
	 */
	private String getEmpCodeByWorkFlowNum(String type ,String workFlowNum){
		if(BpsConstant.LTT_NEW_PROCESSNAME.equals(type)
		   ||BpsConstant.EX_NEW_PROCESSNAME.equals(type)
		   ||BpsConstant.LTT_UPDATE_PROCESSNAME.equals(type)
		   ||BpsConstant.EX_UPDATE_PROCESSNAME.equals(type)
		   ||BpsConstant.CANCEL_PROCESSNAME.equals(type)){
			// 根据工作流号去查询合同操作日志，如果返回为空，则无工作流
			List<ContractOperatorLog> contractLogList = contractService.searchOperaLogByWorkflowId(workFlowNum);
			if (contractLogList != null && contractLogList.size() >= 1) {
				String userId = contractLogList.get(0).getCreateUser();
				Employee e = employeeService.getEmpById(userId);
				return e.getEmpCode();
			}else{
				return BpsConstant.NOWORKFLOW;
			}
			//大客户
		}else if(BpsConstant.KEYCUST_PROCESSNAME.equals(type)){
			//查询大客户工作流信息，如果返回为空，则无工作流
			KeyCustomerWorkflowInfo info = keyCustomerManager.findWorkflowInfoByBusino(workFlowNum);
			if(info != null ){
				String userId = info.getCreateUser();
				Employee e = employeeService.getEmpById(userId);
				return e.getEmpCode();
			}else{
				return BpsConstant.NOWORKFLOW;
			}
			//疑似重复客户
		}else if(BpsConstant.CUSTREPEAT_PROCESSNAME.equals(type)){
			//查询疑似重复客户工作流信息，如果返回为空，则无工作流
			RepetitiveCustWorkFlowInfo info = repeatedCustManager.findRepetitiveCustWorkFlowInfoByWorkNo(workFlowNum,BpsConstant.CUSTREPEAT_PROCESSNAME);
			if(info != null ){
				String userId = info.getProposer();
				Employee e = employeeService.getEmpById(userId);
				return e.getEmpCode();
			}else{
				return BpsConstant.NOWORKFLOW;
			}
			//区域营销活动
		}else if(BpsConstant.MARKETING_PROCESSNAME.equals(type)){
			//查询区域营销工作流信息，如果返回为空，则无工作流
			MarketActivity marketActivity = marketActivityManager.searchMarketActivityByWorkflowNum(workFlowNum);
			if(marketActivity != null ){
				String userId = marketActivity.getCreateUser();
				Employee e = employeeService.getEmpById(userId);
				return e.getEmpCode();
			}else{
				return BpsConstant.NOWORKFLOW;
			}
		//常规理赔
		}else if(BpsConstant.RECOMPENSE_PROCESS_DEFINITION_NAME.equals(type)){
			//查询常规理赔工作流信息，如果返回为空，则无工作流
			NormalClaim info = normalClaimManager.getNormalClaimByWorkflowNo(workFlowNum);
			if(info != null){
				String userId = info.getApplyPersonCode();
				Employee e = employeeService.getEmpByCode(userId);
				return e.getEmpCode();
			}else{
				return BpsConstant.NOWORKFLOW;
			}
			//服务补救
		}else if(BpsConstant.SERVICE_PROCESS_DEFINITION_NAME.equals(type)){
			//根据工作流号，查询OA工作流信息
			ProcessInfo process = bpsWorkflowManager.searchProcessInfoByBusino(workFlowNum);
			String processId = String.valueOf(process.getProcessinstid());
			//查询服务补救工作流信息，如果返回为空，则无工作流
			ServiceRecovery info = normalClaimManager.getServiceRecoveryByOaWorkflowNum(processId);
			if(info != null){
				String userId = info.getCreateUser();
				Employee e = employeeService.getEmpById(userId);
				return e.getEmpCode();
			}else{
				return BpsConstant.NOWORKFLOW;
			}
			//多赔
		}else if(BpsConstant.OVERPAY_PROCESS_DEFINITION_NAME.equals(type)){
			//根据工作流号，查询OA工作流信息
			ProcessInfo process = bpsWorkflowManager.searchProcessInfoByBusino(workFlowNum);
			String processId = String.valueOf(process.getProcessinstid());
			//查询多赔工作流信息，如果返回为空，则无工作流
			Overpay info = normalClaimManager.getDetailOverpayByWorkNumber(processId);
			if(info != null){
				String userId = info.getCreateUser();
				Employee e = employeeService.getEmpById(userId);
				return e.getEmpCode();
			}else{
				return BpsConstant.NOWORKFLOW;
			}
		}
		return BpsConstant.NOWORKFLOW;
	}

	/**
	 * @return the repeatedCustManager
	 */
	public IRepeatedCustManager getRepeatedCustManager() {
		return repeatedCustManager;
	}

	/**
	 * @param repeatedCustManager the repeatedCustManager to set
	 */
	public void setRepeatedCustManager(IRepeatedCustManager repeatedCustManager) {
		this.repeatedCustManager = repeatedCustManager;
	}

	/**
	 * @return the bpsWorkflowManager
	 */
	public IBpsWorkflowManager getBpsWorkflowManager() {
		return bpsWorkflowManager;
	}

	/**
	 * @return the marketActivityManager
	 */
	public IMarketActivityManager getMarketActivityManager() {
		return marketActivityManager;
	}

	/**
	 * @param marketActivityManager the marketActivityManager to set
	 */
	public void setMarketActivityManager(
			IMarketActivityManager marketActivityManager) {
		this.marketActivityManager = marketActivityManager;
	}
	/**
	 * @return the contractService
	 */
	public IContractService getContractService() {
		return contractService;
	}
	/**
	 * @param contractService the contractService to set
	 */
	public void setContractService(IContractService contractService) {
		this.contractService = contractService;
	}
	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}
	/**
	 * @param userService the userService to set
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	/**
	 * @return the departmentService
	 */
	public IDepartmentService getDepartmentService() {
		return departmentService;
	}
	/**
	 * @param departmentService the departmentService to set
	 */
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	/**
	 * @return the employeeService
	 */
	public IEmployeeService getEmployeeService() {
		return employeeService;
	}
	/**
	 * @param employeeService the employeeService to set
	 */
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	/**
	 * @return the workflowApproveOperate
	 */
	public WorkflowApproveOperate getWorkflowApproveOperate() {
		return workflowApproveOperate;
	}
	/**
	 * @param workflowApproveOperate the workflowApproveOperate to set
	 */
	public void setWorkflowApproveOperate(
			WorkflowApproveOperate workflowApproveOperate) {
		this.workflowApproveOperate = workflowApproveOperate;
	}
	/**
	 * @return the keyCustomerManager
	 */
	public IKeyCustomerManager getKeyCustomerManager() {
		return keyCustomerManager;
	}
	/**
	 * @param keyCustomerManager the keyCustomerManager to set
	 */
	public void setKeyCustomerManager(IKeyCustomerManager keyCustomerManager) {
		this.keyCustomerManager = keyCustomerManager;
	}
	/**
	 * @return the contractWorkflowManager
	 */
	public IContractWorkflowManager getContractWorkflowManager() {
		return contractWorkflowManager;
	}
	/**
	 * @param contractWorkflowManager the contractWorkflowManager to set
	 */
	public void setContractWorkflowManager(
			IContractWorkflowManager contractWorkflowManager) {
		this.contractWorkflowManager = contractWorkflowManager;
	}
	/**
	 * @return the normalClaimManager
	 */
	public INormalClaimManager getNormalClaimManager() {
		return normalClaimManager;
	}
	/**
	 * @param normalClaimManager the normalClaimManager to set
	 */
	public void setNormalClaimManager(INormalClaimManager normalClaimManager) {
		this.normalClaimManager = normalClaimManager;
	}
	/**
	 * @param bpsWorkflowManager the bpsWorkflowManager to set
	 */
	public void setBpsWorkflowManager(IBpsWorkflowManager bpsWorkflowManager) {
		this.bpsWorkflowManager = bpsWorkflowManager;
	}
}
