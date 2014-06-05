/**
 * Filename:	Test.java
 * Description:
 * Copyright:	Copyright (c)2011
 * Company:		IBM
 * @author:		
 * @version:	
 * create at:	2012-2-9 上午10:33:23
 *
 * Modification History:
 * Date			Author			Version			Description
 * ------------------------------------------------------------------------
 * 2012-2-9	    
 */
package com.deppon.crm.module.common.server.workflow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.workflow.InvalidInputException;
import com.opensymphony.workflow.Workflow;
import com.opensymphony.workflow.WorkflowException;
import com.opensymphony.workflow.loader.ActionDescriptor;
import com.opensymphony.workflow.loader.StepDescriptor;
import com.opensymphony.workflow.loader.WorkflowDescriptor;
import com.opensymphony.workflow.query.Expression;
import com.opensymphony.workflow.query.FieldExpression;
import com.opensymphony.workflow.query.NestedExpression;
import com.opensymphony.workflow.query.WorkflowExpressionQuery;
import com.opensymphony.workflow.spi.Step;
import com.opensymphony.workflow.spi.WorkflowEntry;

/**
 * @ClassName: WorkflowManager
 * @Description: 工作流管理
 * @author
 * @date 2012-2-9 上午10:46:21
 * 
 */
public class WorkflowManager {

	// 单例
	private static WorkflowManager instance = null;

	protected Workflow workflow;

	public Workflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	private WorkflowManager() {
	}

	/**
	 * @Title: get
	 * @Description: 单例
	 * @param @return 设定文件
	 * @return WorkflowManager 返回类型
	 * @throws
	 */
	public static WorkflowManager get() {
		if (instance == null) {
			instance = new WorkflowManager();
		}
		return instance;
	}

	private void init(String userId) {
		// workflow = SpringHelper.get().getBasicWorkflow();
	}

	/**
	 * @Title: initWorkFlow
	 * @Description: 初始化工作流
	 * @param @param userId
	 * @param @param workflowName
	 * @param @param id
	 * @param @return
	 * @param @throws ValidateException 设定文件
	 * @return long 返回类型
	 * @throws
	 */
	public long initWorkflow(String userId, String workflowName, int initialId,
			Map appMap) {
		long workflowId = -1;
		if (workflow.canInitialize(workflowName, initialId,appMap)) {
			try {
				workflowId = workflow.initialize(workflowName, initialId,
						appMap);
			} catch (WorkflowException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
				// throw new ValidateException("工作流初始化异常！");
			}
		}
		return workflowId;
	}

	/**
	 * @Title: printCurrentWorkflow
	 * @Description:
	 * @param @param workflowId 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void printCurrentWorkflow(long workflowId) {
		List currentSteps = workflow.getCurrentSteps(workflowId);
		List historySteps = workflow.getHistorySteps(workflowId);
		int[] actions = workflow.getAvailableActions(workflowId,
				Collections.EMPTY_MAP);
		for (int i = 0; i < currentSteps.size(); i++) {
			Step step = (Step) currentSteps.get(0);
			// assertEquals(currentStatus, step.getStatus());
			System.out.println("current step status:" + step.getStatus());
		}

		for (int i = 0; i < historySteps.size(); i++) {
			Step step = (Step) historySteps.get(0);
			// assertEquals(currentStatus, step.getStatus());
			System.out.println("history step status:" + step.getStatus());
		}

	}

	/**
	 * @Title: doAction
	 * @Description: 执行工作流
	 * @param @param workflowId
	 * @param @param actionId
	 * @param @param map
	 * @param @throws InvalidInputException
	 * @param @throws WorkflowException 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void doAction(String userId, long workflowId, int actionId, Map map) {
//		try {
			// init(userId);
			try {
				workflow.doAction(workflowId, actionId, map);
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (WorkflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
	}

	/**
	 * 得到用户指定流程的有效行动
	 * 
	 * @param workflowId
	 * @return actionID和名称的键值对
	 */
	public Map<Integer, String> findAvailableActions(long workflowId) {
		Map<Integer, String> result = new HashMap<Integer, String>();
		int[] availableActions = workflow.getAvailableActions(workflowId, null);
		WorkflowDescriptor wd = workflow.getWorkflowDescriptor(workflow
				.getWorkflowName(workflowId));
		for (int i = 0; i < availableActions.length; i++) {
			int actionId = availableActions[i];
			result.put(actionId, wd.getAction(actionId).getName());
		}
		return result;
	}

	/**
	 * 查询工作流描述
	 * 
	 */
	public WorkflowDescriptor queryWorkflowDescriptor(long workflowId) {
		return workflow.getWorkflowDescriptor(workflow
				.getWorkflowName(workflowId));
	}

	/**
	 * 查询步骤描述，步骤描述中可以得到步骤的名称，步骤的函数
	 */
	@SuppressWarnings("unchecked")
	public StepDescriptor queryStepDescriptor(long workflowId, int stepId) {
		return queryWorkflowDescriptor(workflowId).getStep(stepId);
	}

	/**
	 * 查询所有步骤
	 */
	@SuppressWarnings("unchecked")
	public List<Step> findSteps(long workflowId) {
		List<Step> result = new ArrayList<Step>();
		List currentSteps = workflow.getCurrentSteps(workflowId);
		List historySteps = workflow.getHistorySteps(workflowId);
		result.addAll(currentSteps);
		result.addAll(historySteps);
		return result;
	}

	public List<Step> findHistorySteps(long workflowId) {
		List<Step> result = new ArrayList<Step>();
		List historySteps = workflow.getHistorySteps(workflowId);
		result.addAll(historySteps);
		return result;
	}

	/**
	 * 查询操作的名称
	 */
	public String findActionName(long workflowId, int actionId) {
		ActionDescriptor actionDescriptor = queryWorkflowDescriptor(workflowId)
				.getAction(actionId);
		return actionDescriptor == null ? null : actionDescriptor.getName();
	}

	/**
	 * @throws WorkflowException
	 * @Title: killWorkflow
	 * @Description: 关闭工作流
	 * @param @param workflowId 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void killWorkflow(String userId, long workflowId) {
		if (workflow.canModifyEntryState(workflowId, WorkflowEntry.KILLED)) {
			try {
				workflow.changeEntryState(workflowId, WorkflowEntry.KILLED);
			} catch (WorkflowException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * @Title: getWorkFlowByOwner
	 * @Description: 获取所有者的工作流
	 * @param @param userId
	 * @param @param workflowName
	 * @param @return
	 * @param @throws WorkflowException 设定文件
	 * @return List 返回类型
	 * @throws
	 */
	public List getWorkflowByOwner(String userId, String workflowName) {
		// init(userId);
		Expression ownerExpression = new FieldExpression(FieldExpression.OWNER,
				FieldExpression.CURRENT_STEPS, FieldExpression.EQUALS, userId);
		Expression nameExpression = new FieldExpression(FieldExpression.NAME,
				FieldExpression.ENTRY, FieldExpression.EQUALS, workflowName);
		NestedExpression nestedExpression = new NestedExpression(
				new Expression[] { ownerExpression, nameExpression },
				NestedExpression.AND);
		List list = new ArrayList();
		try {
			list = workflow
					.query(new WorkflowExpressionQuery(nestedExpression));
		} catch (WorkflowException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return list;
	}

	/**
	 * @Title: getAvailableActions
	 * @Description: 获取有效工作流操作
	 * @param @param workflowId
	 * @param @return 设定文件
	 * @return int[] 返回类型
	 * @throws
	 */
	public int[] getAvailableActions(String userId, long workflowId) {
		return workflow.getAvailableActions(workflowId, Collections.EMPTY_MAP);
	}

	/**
	 * 
	 * @Title: getAvailableActions
	 * @Description: 获取有效工作流操作
	 * @param @param userId
	 * @param @param workflowId
	 * @param @param map
	 * @param @return 设定文件
	 * @return int[] 返回类型
	 * @throws
	 */
	public int[] getAvailableActions(String userId, long workflowId, Map map) {
		return workflow.getAvailableActions(workflowId, map);
	}

	/**
	 * @Title: getCurrentSteps
	 * @Description: 获取当前步骤
	 * @param @param workflowId
	 * @param @return 设定文件
	 * @return int[] 返回类型
	 * @throws
	 */
	public int[] getCurrentSteps(String userId, long workflowId) {
		List currentSteps = workflow.getCurrentSteps(workflowId);
		if (currentSteps != null && currentSteps.size() > 0) {
			int[] steps = new int[currentSteps.size()];
			for (int i = 0; i < currentSteps.size(); i++) {
				Step step = (Step) currentSteps.get(i);
				steps[i] = step.getStepId();
			}
			return steps;
		} else {
			return new int[0];
		}
	}

}
