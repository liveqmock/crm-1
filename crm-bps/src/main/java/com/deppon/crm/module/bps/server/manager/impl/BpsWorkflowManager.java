/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BpsWorkflowManager.java
 * @package com.deppon.crm.module.common.server.bpsworkflow 
 * @author pgj
 * @version 0.1 2013-11-13
 */
package com.deppon.crm.module.bps.server.manager.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.bpms.module.shared.domain.ApprovalInfo;
import com.deppon.bpms.module.shared.domain.BpmsParticipant;
import com.deppon.bpms.module.shared.domain.CommonConstant;
import com.deppon.bpms.module.shared.domain.ProcessInfo;
import com.deppon.bpms.module.shared.domain.WorkFlowInfo;
import com.deppon.bpmsapi.module.client.api.IBranchRuleManager;
import com.deppon.bpmsapi.module.client.api.IDpBpmsClientFacade;
import com.deppon.bpmsapi.module.client.api.IParticipantRuleManager;
import com.deppon.bpmsapi.module.client.api.impl.DpBpmsClientFacadeImpl;
import com.deppon.bpmsapi.module.client.domain.BpmsActivity;
import com.deppon.bpmsapi.module.client.domain.BpmsOperateInfo;
import com.deppon.bpmsapi.module.client.domain.BranchRule;
import com.deppon.bpmsapi.module.client.domain.WorkItemInfo;
import com.deppon.bpmsapi.module.client.util.BPMSConstant;
import com.deppon.bpmsapi.module.client.util.BPSClientManagerFactory;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.bps.server.manager.IBpsWorkflowManager;
import com.deppon.crm.module.bps.server.service.IAmountConfigService;
import com.deppon.crm.module.bps.server.util.Constant;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.shared.util.string.StringUtil;
import com.eos.workflow.data.WFWorkItem;
import com.primeton.workflow.api.WFServiceException;

/**
 * <p>
 * Description:BPS工作流Manager<br/>
 * </p>
 * 
 * @title BpsWorkflowManager.java
 * @package com.deppon.crm.module.common.server.bpsworkflow
 * @author pgj
 * @version 0.1 2013-11-13
 */

public class BpsWorkflowManager implements IBpsWorkflowManager {
	// 供普元工作流调用的规则方法调用公共接口方法
	private IBranchRuleManager branchRuleManager;
	// 供普元工作流调用的规则方法调用公共接口方法
	private IParticipantRuleManager participantRuleManager;
	//调用审批金额设置service
	private IAmountConfigService amountConfigService;

	/**
	 * @param branchRuleManager
	 *            : set the property branchRuleManager.
	 */
	public void setBranchRuleManager(IBranchRuleManager branchRuleManager) {
		this.branchRuleManager = branchRuleManager;
	}

	/**
	 * @param participantRuleManager
	 *            : set the property participantRuleManager.
	 */
	public void setParticipantRuleManager(
			IParticipantRuleManager participantRuleManager) {
		this.participantRuleManager = participantRuleManager;
	}

	/**
	 * <p>
	 * Description:获取工作流提供客户端<br />
	 * </p>
	 * 
	 * @author pgj
	 * @version 0.1 2013-11-14
	 * @param brm
	 *            :分支条件
	 * @param prm
	 *            :参与者
	 * @param empCode
	 *            :员工工号
	 * @param empName
	 *            :员工名称
	 * @return IDpBpmsClientFacade
	 */
	@Override
	public IDpBpmsClientFacade getClient() {
		// 分支规则
		//获取当前登陆的用户
		User user = (User) UserContext.getCurrentUser();
		//获得工号
		String empCode = user.getEmpCode().getEmpCode();
		//获得员工姓名
		String empName = user.getEmpCode().getEmpName();
		// 获取客户端API门面接口
		IDpBpmsClientFacade client = new DpBpmsClientFacadeImpl(
				branchRuleManager, participantRuleManager, empCode, empName);
		return client;
	}

	/**
	 * 
	 * <p>
	 * Description:创建工作流<br />
	 * </p>
	 * 
	 * @author pgj
	 * @param processName
	 *            :流程定义名称
	 * @param appName
	 *            :流程实例名称
	 * @param descName
	 *            :流程描述
	 * @param bussinessCode
	 *            :工作流业务编码
	 * @version 0.1 2013-11-14
	 * @return long
	 */
	@Override
	public long createAndStartProcess(String processName, String appName,
			String descName, String bussinessCode) {
		// 获取客户端API门面接口
		IDpBpmsClientFacade client = this.getClient();
		
		// 执行创建并起草方法
		long processId = client.createAndStartProcess(processName, appName,
				descName, bussinessCode);
		return processId;
	}

	/**
	 * 根据流程实例ID，判断当前用户是否可以收回流程
	 * 
	 * @param processInstID
	 *            流程实例ID
	 * @return
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.common.server.bpsworkflow.IBpsWorkflowManager#
	 * drawBackByProcessInstID(java.lang.Long)
	 */
	@Override
	public boolean drawBackByProcessInstID(Long processInstID) {
		//获得流程实例
		IDpBpmsClientFacade client = this.getClient();
		//判断是否收回流程
		boolean result = client.isDrawBackByProcessInstID(processInstID);
		return result;
	}

	/**
	 * 根据工作项ID，判断当前用户是否可以收回流程
	 * 
	 * @param workItemID
	 *            工作项ID
	 * @return
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.common.server.bpsworkflow.IBpsWorkflowManager#
	 * drawBackByWorkItemId(java.lang.Long)
	 */
	@Override
	public boolean drawBackByWorkItemId(Long workItemID) {
		// 获取客户端API门面接口
		IDpBpmsClientFacade client = this.getClient();
		boolean result = client.isDrawBackByWorkItemId(workItemID);
		return result;
	}

	/**
	 * 判断该流程是否可以删除
	 * 
	 * @param processInstID
	 *            流程实例ID
	 * @return
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.common.server.bpsworkflow.IBpsWorkflowManager#
	 * isRemoveProcessInstExt(java.lang.Long)
	 */
	@Override
	public boolean isRemoveProcessInstExt(Long processInstID) {
		// 获取客户端API门面接口
		IDpBpmsClientFacade client = this.getClient();
		boolean result = client.isRemoveProcessInstExt(processInstID);
		return result;
	}

	/**
	 * 执行删除流程操作
	 * 
	 * @param processInstID
	 *            流程实例ID
	 * @return
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.common.server.bpsworkflow.IBpsWorkflowManager#
	 * removeMyProcessInstExt(java.lang.Long)
	 */
	@Override
	public boolean removeMyProcessInstExt(Long processInstID) {
		if (this.isRemoveProcessInstExt(processInstID)) {
			// 获取客户端API门面接口
			IDpBpmsClientFacade client = this.getClient();
			boolean result = client.removeMyProcessInstExt(processInstID);
			return result;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.common.server.bpsworkflow.IBpsWorkflowManager#
	 * workflowApprove(long, long, java.lang.String, java.lang.String,
	 * com.deppon.bpms.module.shared.domain.BpmsParticipant[])
	 */
	@Override
	public Map<String, Object> workflowApprove(long workItemID,
			long processInstID, String approveOperateType,
			String approveOpinion, BpmsParticipant[] parts) {
		// 获取客户端API门面接口
		IDpBpmsClientFacade client = this.getClient();
		boolean result = false;
		boolean isEnd = false;
		boolean isDisAgree = false;
		// 构造审批人操作信息
		BpmsOperateInfo boi = new BpmsOperateInfo();
		// 设置审批类型
		boi.setOperateType(BPMSConstant.APPROVE_OPERATETYPE_AGREE);
		// 设置审批时间
		boi.setOperateDate(new Date());
		// 设置审批意见
		boi.setApproveOpinion(approveOpinion);
		//
		if (BPMSConstant.APPROVE_OPERATETYPE_AGREE==Integer
				.parseInt(approveOperateType)) { // 同意 0
			result = client.send(workItemID, processInstID, boi, null);
			//获得下一个流程实例 如果审批同意为最后一个节点 即为End
			BranchRule[] reles = client.getNextActivities(workItemID,
					processInstID, BPMSConstant.APPROVE_OPERATETYPE_AGREE);
			//如果获得的流程实例为end，则表示流程已经完了。返回一个结果标识
			if (reles[0].getNextActivity().getActivityDefId().equals("End")) {
				isEnd = true;
			}
		} else if (BPMSConstant.APPROVE_OPERATETYPE_DISAGREE == Integer
				.parseInt(approveOperateType)) { // 不同意 1
			// 注:这里的规则数组即是调用 getNextActivities(long workItemId,long
			// processInstId,int operateType)此方法的返回值
			boi.setOperateType(BPMSConstant.APPROVE_OPERATETYPE_DISAGREE);
			result = client.send(workItemID, processInstID, boi, null);
			//返回一个是否不同意的变量 --更新合同操作日志
			isDisAgree = true;
		} else if (BPMSConstant.APPROVE_OPERATETYPE_GOBACK == Integer
				.parseInt(approveOperateType)) { // 退回
			BranchRule[] reles = client.getNextActivities(workItemID,
					processInstID, BPMSConstant.APPROVE_OPERATETYPE_GOBACK);
			// 获取目标回退节点定义ID
			String defId = reles[0].getNextActivity().getActivityDefId();
			// 获取当前活动节点的实例ID
			long actInstId = reles[0].getCurrentActivity().getActivityInstId();
			// 判断节点是否可退回
			result = client.canBackAcitivity(actInstId, defId);
			if (result) {
				// 退回目标活动定义ID数组
				String[] destDefID = new String[1];
				destDefID[0] = defId;
				result = client.backActivity(workItemID, processInstID, boi,
						destDefID);
			}
		} else if (BPMSConstant.APPROVE_OPERATETYPE_TRANSFER
				.equalsIgnoreCase(approveOperateType)) { // 转办
			// 返回值为新生成的工作项ID
			long newWorkItemID = client.transfer(workItemID, parts);
			if (newWorkItemID > 0) {
				result = true;
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		//是否成功审批
		map.put("sucess", result);
		//是否不同意
		map.put("isDisAgree", isDisAgree);
		//审批同意是是否为最后一个节点
		map.put("isEnd", isEnd);
		return map;
	}

	/**
	 * 查询指定用户的指定业务系统的待办流程数目
	 * 
	 * @param processDefName
	 *            流程定义名称
	 * @param applerId
	 *            申请人工号
	 * @param fstandardcode
	 *            申请人所在部门编码
	 * @param state
	 *            1 未提交 2 未审批 3 审批中 4 已同意 5 未同意 6 已退回 7 汇款失败 8 汇款成功
	 * @param startTime
	 *            申请日期
	 * @param endTime
	 *            申请日期
	 * @param workflowNo
	 *            工作流号
	 * @return
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.common.server.bpsworkflow.IBpsWorkflowManager#
	 * queryTodoItems(int, int, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.util.Date, java.util.Date,
	 * java.lang.String)
	 */
	@Override
	public List<WorkFlowInfo> queryTodoItems(Map<String, Object> map) {
		// 获取客户端API门面接口
		IDpBpmsClientFacade client = this.getClient();
		List<WorkFlowInfo> list = client.queryTodoItems(map);
		return list;
	}

	/**
	 * 查询指定用户的指定业务系统的待办流程数目
	 * 
	 * @param processDefName
	 *            流程定义名称
	 * @param applerId
	 *            申请人工号
	 * @param fstandardcode
	 *            申请人所在部门编码
	 * @param state
	 *            1 未提交 2 未审批 3 审批中 4 已同意 5 未同意 6 已退回 7 汇款失败 8 汇款成功
	 * @param startTime
	 *            申请日期
	 * @param endTime
	 *            申请日期
	 * @param workflowNo
	 *            工作流号
	 * @return
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.common.server.bpsworkflow.IBpsWorkflowManager#
	 * queryTodoCounts(java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.util.Date, java.util.Date, java.lang.String)
	 */
	@Override
	public long queryTodoCounts(Map<String, Object> map) {
		// 获取客户端API门面接口
		IDpBpmsClientFacade client = this.getClient();
		return client.queryTodoCounts(map);
	}

	/**
	 * 查询指定用户的指定系统的已审批流程信息
	 * 
	 * @param start
	 *            分页开始
	 * @param limit
	 *            每页条数
	 * @param processDefName
	 *            流程定义名称
	 * @param applerId
	 *            申请人工号
	 * @param departmentId
	 *            申请人所在部门编码
	 * @param state
	 *            1 未提交 2 未审批 3 审批中 4 已同意 5 未同意 6 已退回 7 汇款失败 8 汇款成功
	 * @param startTime
	 *            申请日期
	 * @param endTime
	 *            申请日期
	 * @param workflowNo
	 *            工作流号
	 * @return
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.common.server.bpsworkflow.IBpsWorkflowManager#
	 * queryFinishedItems(int, int, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.util.Date, java.util.Date,
	 * java.lang.String)
	 */
	@Override
	public List<WorkFlowInfo> queryFinishedItems(Map<String, Object> map) {
		// 获取客户端API门面接口
		IDpBpmsClientFacade client = this.getClient();
		// 已审批流程集合
		return client.queryFinishedItems(map);
	}

	/**
	 * 查询指定用户的指定业务系统的已办工作项数目
	 * 
	 * @param start
	 *            分页开始
	 * @param limit
	 *            每页条数
	 * @param processDefName
	 *            流程定义名称
	 * @param applerId
	 *            申请人工号
	 * @param departmentId
	 *            申请人所在部门编码
	 * @param state
	 *            1 未提交 2 未审批 3 审批中 4 已同意 5 未同意 6 已退回 7 汇款失败 8 汇款成功
	 * @param startTime
	 *            申请日期
	 * @param endTime
	 *            申请日期
	 * @param workflowNo
	 *            工作流号
	 * @return
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.common.server.bpsworkflow.IBpsWorkflowManager#
	 * queryFinishedCounts(java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.util.Date, java.util.Date, java.lang.String)
	 */
	@Override
	public Long queryFinishedCounts(Map<String, Object> map) {
		// 获取客户端API门面接口
		IDpBpmsClientFacade client = this.getClient();
		// 已审批流程集合
		return client.queryFinishedCounts(map);
	}

	/**
	 * 查询流程实例下对应的参与者信息
	 * 
	 * @param processInstId
	 *            流程实例ID
	 * @param activityDefId
	 *            环节定义ID
	 * @param flag
	 *            1：查询已处理参与者 2：查询环节配置参与者
	 * @return
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.common.server.bpsworkflow.IBpsWorkflowManager#
	 * getActivityParticipant(java.lang.Long, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public BpmsParticipant[] getActivityParticipant(Long processInstId,
			String activityDefId, String flag) {
		IDpBpmsClientFacade client = this.getClient();
		BpmsParticipant[] bpms = client.getActivityParticipant(processInstId,
				activityDefId, flag);
		return bpms;
	}

	/**
	 * 结束当前流程
	 * 
	 * @param processInstId
	 *            流程实例ID
	 * @return
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.common.server.bpsworkflow.IBpsWorkflowManager#
	 * terminateProcessInstance(java.lang.Long)
	 */
	@Override
	public boolean terminateProcessInstance(Long processInstId) {
		IDpBpmsClientFacade client = this.getClient();
		boolean result = client.terminateProcessInstance(processInstId);
		return result;
	}

	/**
	 * 获取下一步可到达的节点
	 * 
	 * @param workItemId
	 *            工作项ID
	 * @param processInstId
	 *            流程实例ID
	 * @param approveOperateType
	 *            操作类型
	 * @return
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.common.server.bpsworkflow.IBpsWorkflowManager#
	 * getNextActivities(long, long, int)
	 */
	@Override
	public BranchRule[] getNextActivities(long workItemId, long processInstId,
			int operateType) {
		IDpBpmsClientFacade client = this.getClient();
		BranchRule[] br = client.getNextActivities(workItemId, processInstId,
				operateType);
		return br;
	}

	/**
	 * 查询指定用户的指定系统的我申请的流程信息
	 * 
	 * @param start
	 *            分页开始
	 * @param limit
	 *            每页条数
	 * @param processDefName
	 *            流程定义名称
	 * @param state
	 *            1 未提交 2 未审批 3 审批中 4 已同意 5 未同意 6 已退回 7 汇款失败 8 汇款成功
	 * @param startTime
	 *            申请日期
	 * @param endTime
	 *            申请日期
	 * @param workflowNo
	 *            工作流号
	 * @return
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.common.server.bpsworkflow.IBpsWorkflowManager#
	 * queryMyWorkFlow(int, int, java.lang.String, java.lang.String,
	 * java.util.Date, java.util.Date, java.lang.String)
	 */
	@Override
	public List<WorkFlowInfo> queryMyWorkFlow(Map<String, Object> map) {
		IDpBpmsClientFacade client = this.getClient();
		return client.queryMyWorkFlow(map);

	}

	/**
	 * 查询指定用户的指定系统的我申请的流程数目
	 * 
	 * @param processDefName
	 *            流程定义名称
	 * @param state
	 *            1 未提交 2 未审批 3 审批中 4 已同意 5 未同意 6 已退回 7 汇款失败 8 汇款成功
	 * @param startTime
	 *            申请日期
	 * @param endTime
	 *            申请日期
	 * @param workflowNo
	 *            工作流号
	 * @return
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.common.server.bpsworkflow.IBpsWorkflowManager#
	 * queryMyWorkFlowCount(java.lang.String, java.lang.String, java.util.Date,
	 * java.util.Date, java.lang.String)
	 */
	@Override
	public long queryMyWorkFlowCount(Map<String, Object> map) {
		IDpBpmsClientFacade client = this.getClient();
		return client.queryMyProcessCount(map);
	}

	/**
	 * 获取指定流程下所有的活动定义ID和活动定义名称
	 * 
	 * @param processDefName
	 *            流程定义名称
	 * @return
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.common.server.bpsworkflow.IBpsWorkflowManager#
	 * queryActivityByProcessDefID(java.lang.String)
	 */
	@Override
	public List<BpmsActivity> queryActivityByProcessDefID(String processDefName) {
		IDpBpmsClientFacade client = this.getClient();
		return client.queryActivityByProcessDefName(processDefName);
	}

	/**
	 * 根据流程实例ID查询审批记录（包括起草、审批同意、审批不同意、退回、收回、转办等）
	 * 
	 * @param processInstID
	 *            流程实例ID
	 * @return
	 */
	@Override
	public List<ApprovalInfo> queryApprovalInfoByProcessInstID(
			long processInstID) {
		// 获取客户端API门面接口
		IDpBpmsClientFacade client = this.getClient();
		return client.queryApprovalInfoByProcessInstID(processInstID);
	}

	/**
	 * 获取当前审批人信息
	 * 
	 * @param processInstID
	 *            流程实例ID
	 * @return 审批节点 【姓名 工号】
	 */
	@Override
	public String getCurrentApproval(long processInstID) {
		//当前审批人的信息的值
		StringBuffer currentApproval = new StringBuffer();
		//获得门面
		IDpBpmsClientFacade client = this.getClient();
		//获得当前审批人结果集 --这个方法返回的结果长度有限制 出现...
		Long[] workItemIds = client.getWorkItemId(processInstID, null);
		WFWorkItem workItem = null;
		currentApproval.append("当前审批人：");
		boolean current = false;
		//获得当前审批人结果集 -getParticipante和getPartiName一一对应
		WorkItemInfo[] workItemInfos = client.getRuningWorkItems(processInstID,
				"");
		//循环遍历当前审批人 一个节点可能有多个审批人
		for (int i = 0; i < workItemInfos.length; i++) {
			//得到当前审批人的集合
			WorkItemInfo work = workItemInfos[i];
			//工号
			String empCodes = work.getParticipant();
			//姓名
			String empNames = work.getPartiName();
			//转换为数组
			String[] empCodeArray = StringUtils.split(empCodes, "|");
			String[] empNameArray = StringUtils.split(empNames, "|");
			currentApproval.append(work.getActivityInstName() + "      【");
			//转换为姓名 工号
			for (int j = 0; j < empNameArray.length; j++) {
				//如果不为0且最后一个一个值
				if (empCodeArray.length!=0&&j == empCodeArray.length-1) {
					currentApproval.append(empNameArray[j] + "   "
							+ empCodeArray[j]);
				} else {
					currentApproval.append(empNameArray[j] + "   "
							+ empCodeArray[j] + "|");
				}
			}
			currentApproval.append(" 】     ");
		}
		//判断流程是否已经完结
		for (int i = 0; i < workItemIds.length; i++) {
			long workItemId = workItemIds[i];
			try {
				// 取得工作项对象

				workItem = BPSClientManagerFactory.newInstance()
						.getWorkItemManager().queryWorkItemDetail(workItemId);
			} catch (WFServiceException e) {
				e.printStackTrace();
			}
			// 取得当前审批人ID
			String approverID = workItem.getParticipant();
			if (workItem.getActivityInstName() != null && approverID != null) {
				current = true;
			}
		}
		//true 返回当前审批人
		if (current) {
			return currentApproval.toString();
		} else {
			return "流程已完成！";
		}
	}

	/**
	 * 生成业务编码 规则：CRM系统工作流号生成规则：ICRM+年月日+6位数字 年月日为创建时间，格式为：20130802
	 * 6为数字：从000001开始，往上递增至999999
	 * 
	 * @return
	 */
	public synchronized String bizCode() {
		StringBuffer bizCode = new StringBuffer();
		//系统编码
		bizCode.append(Constant.CRM_BIZ_CODE);
		//获得年月日
		bizCode.append(getCurrentYearMonthDay("yyyyMMdd"));
		//得到7位
		bizCode.append(getNextSuffix());
		return bizCode.toString();
	}
	/**
	 * 
	 * <p>
	 * Description:获得当前的年月日<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-12-2
	 * @param dateFormat
	 * @return
	 * String
	 */
	private String getCurrentYearMonthDay(String dateFormat) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		String currentDate = format.format(new Date());
		return currentDate;
	}
	/**
	 * 
	 * <p>
	 * Description:获得序列<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-12-2
	 * @return
	 * String
	 */
	private String getNextSuffix() {
		DecimalFormat df = new DecimalFormat("0000000");
		// 取下一个序号，并更新数据库中的记录，保证下一个序号递增
		/*int nextSuffix = getAmountConfigService().getNextSuffix();
		if (nextSuffix > 0) {
			if (nextSuffix == 9999999) {
				nextSuffix = 1;
				getAmountConfigService().updateNoSuffix(nextSuffix);
			} else {
				nextSuffix = nextSuffix + 1;
				getAmountConfigService().updateNoSuffix(nextSuffix);
			}
		} else {
			nextSuffix = 1;
			getAmountConfigService().insertNoSuffix(nextSuffix);
		}*/
		int nextSuffix = getAmountConfigService().getProcNextSuffix();
		return df.format(nextSuffix);
	}
	/**
	 * 
	 * <p>
	 * Description:创建工作流<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-12-2
	 * @param definitionName 流程定义名称
	 * @param instanceName 
	 * @param description 描述
	 * @param bizCode 业务编码
	 * @return
	 *
	 */
	@Override
	public Map<String, String> createWorkflow(String definitionName,
			String instanceName, String description, String bizCode) {
		// 获取客户端API门面接口
		IDpBpmsClientFacade client = null;
		if(null == client){
			client= this.getClient();
		}
		if (StringUtil.isEmpty(bizCode)) {
			bizCode = bizCode();
		}
		// 执行创建并起草方法
		long processId = client.createAndStartProcess(definitionName,
				instanceName+bizCode, description, bizCode);
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("processId", processId + "");
		map.put("bizCode", bizCode);
		return map;
	}

	public IAmountConfigService getAmountConfigService() {
		return amountConfigService;
	}

	public void setAmountConfigService(IAmountConfigService amountConfigService) {
		this.amountConfigService = amountConfigService;
	}
	/**
	 * 
	 * <p>
	 * Description:查询我的工作流数目<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-12-2
	 * @param map
	 * @return
	 *
	 */
	@Override
	public long queryMyProcessCount(Map<String, Object> map) {
		IDpBpmsClientFacade client = this.getClient();
		return client.queryMyProcessCount(map);
	}
	/**
	 * 根据工作流号获取工作流详情
	 *  业务单据号
	 *	busino
	 *	流程实例Id
	 *	processinstid
	 *	工作流状态
	 *	condition
	 *	工作项状态
	 *	currentState
	 *	当前活动定义Id
	 *	currentActivityDefId
	 *	当前活动名称
	 *	currentActivityName
	 *	工作项id
	 *	workItemId
	 */
	@Override
	public 	ProcessInfo searchProcessInfoByProcessInstId(String processInstId){
		IDpBpmsClientFacade client = this.getClient();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(CommonConstant.PROCESSINSTID, processInstId);
		List<ProcessInfo> processInfoList = client.getProcessInfo(map);
		if(null != processInfoList && processInfoList.size()>0){
			return processInfoList.get(0);
		}
		return null;
	}
	@Override
	public ProcessInfo searchProcessInfoByBusino(String busino){
		IDpBpmsClientFacade client = this.getClient();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(CommonConstant.BUSINO, busino);
		List<ProcessInfo> processInfoList = client.getProcessInfo(map);
		if(null != processInfoList && processInfoList.size()>0){
			return processInfoList.get(0);
		}
		return null;
	}
	/**
	 * 根据工作流号获取工作流详情
	 *  业务单据号
	 *	busino
	 *	流程实例Id
	 *	processinstid
	 *	工作流状态
	 *	condition
	 *	工作项状态
	 *	currentState
	 *	当前活动定义Id
	 *	currentActivityDefId
	 *	当前活动名称
	 *	currentActivityName
	 *	工作项id
	 *	workItemId
	 */
	@Override
	public 	ProcessInfo searchProcessInfoByCondition(Map<String,Object> map){
		IDpBpmsClientFacade client = this.getClient();
		List<ProcessInfo> processInfoList = client.getProcessInfo(map);
		if(null != processInfoList && processInfoList.size()>0){
			return processInfoList.get(0);
		}
		return null;
	}
	
	/**
	 * 获取当前审批人信息
	 * 
	 * @param processInstID
	 *            流程实例ID
	 * @return 审批节点 【姓名 工号】
	 */
	@Override
	public String getOneCurrentApproval(long processInstID) {
		//当前审批人的信息的值
		String currentApproval = new String();
		//获得门面
		IDpBpmsClientFacade client = this.getClient();
		//获得当前审批人结果集 --这个方法返回的结果长度有限制 出现...
		Long[] workItemIds = client.getWorkItemId(processInstID, null);
		WFWorkItem workItem = null;
		boolean current = false;
		//获得当前审批人结果集 -getParticipante和getPartiName一一对应
		WorkItemInfo[] workItemInfos = client.getRuningWorkItems(processInstID,
				"");
		//循环遍历当前审批人 一个节点可能有多个审批人
		for (int i = 0; i < workItemInfos.length; i++) {
			//得到当前审批人的集合
			WorkItemInfo work = workItemInfos[i];
			//工号
			String empCodes = work.getParticipant();
			//姓名
			//转换为数组
			String[] empCodeArray = StringUtils.split(empCodes, "|");
			if(null!=empCodeArray&&empCodeArray.length>0){
			currentApproval=empCodeArray[0];
			}
		}
		//判断流程是否已经完结
		for (int i = 0; i < workItemIds.length; i++) {
			long workItemId = workItemIds[i];
			try {
				// 取得工作项对象

				workItem = BPSClientManagerFactory.newInstance()
						.getWorkItemManager().queryWorkItemDetail(workItemId);
			} catch (WFServiceException e) {
				e.printStackTrace();
			}
			// 取得当前审批人ID
			String approverID = workItem.getParticipant();
			if (workItem.getActivityInstName() != null && approverID != null) {
				current = true;
			}
		}
		//true 返回当前审批人
		if (current) {
			return currentApproval.toString();
		} else {
			return "END";
		}
	}
	@Override
	public long getWorkItemByproId(long proID){
		IDpBpmsClientFacade client = this.getClient();
		//获得当前审批人结果集 --这个方法返回的结果长度有限制 出现...
		Long[] workItemIds = client.getWorkItemId(proID, null);
		if(null!=workItemIds&&workItemIds.length>0){
		return workItemIds[0];
		}
		
		return 0;
		
	}
}
