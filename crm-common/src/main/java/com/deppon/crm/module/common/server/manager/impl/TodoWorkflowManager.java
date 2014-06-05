package com.deppon.crm.module.common.server.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.server.manager.ITodoWorkflowManager;
import com.deppon.crm.module.common.server.service.ITodoWorkflowService;
import com.deppon.crm.module.common.shared.domain.TodoWorkflow;
import com.deppon.crm.module.common.shared.domain.TodoWorkflowMap;

public class TodoWorkflowManager implements ITodoWorkflowManager {
	private ITodoWorkflowService todoWorkflowService;

	public ITodoWorkflowService getTodoWorkflowService() {
		return todoWorkflowService;
	}

	public void setTodoWorkflowService(ITodoWorkflowService todoWorkflowService) {
		this.todoWorkflowService = todoWorkflowService;
	}

	@Override
	public void generateTodoWorkflow(long workflowId, String workflowName,
			String fromStatus, String toStatus, String applicationId,
			String applicationDesc, Map deptMap) {
		List<TodoWorkflowMap> twmList = todoWorkflowService
				.searchTodoWorkflowMap(workflowName, fromStatus, toStatus);
		List<TodoWorkflow> todoWorkflowList = new ArrayList<TodoWorkflow>();
		for (TodoWorkflowMap todoWorkflowMap : twmList) {
			String ownerDept = getDepartment(todoWorkflowMap.getRoleId(),
					deptMap);
			TodoWorkflow todoWorkflow = new TodoWorkflow();
			todoWorkflow.setWorkflowId(workflowId);
			todoWorkflow.setWorkflowName(workflowName);
			todoWorkflow.setApplicationId(applicationId);
			todoWorkflow.setApplicationStatus(todoWorkflowMap
					.getApplicationStatus());
			todoWorkflow.setApplicationStatusDesc(todoWorkflowMap
					.getApplicationStatusDesc());
			todoWorkflow.setRoleId(todoWorkflowMap.getRoleId());
			todoWorkflow.setDeptId(ownerDept);
			todoWorkflow.setApplicationName(todoWorkflowMap
					.getApplicationName());
			todoWorkflow.setApplicationDesc(applicationDesc);
			todoWorkflow.setUrl(todoWorkflowMap.getUrl());
			todoWorkflow.setTodoFlag("TODO");
			todoWorkflowList.add(todoWorkflow);
		}
		todoWorkflowService.updateTodoflagByWorkflowId(workflowId, "DONE");
		todoWorkflowService.addTodoWorkflowList(todoWorkflowList);
	}

	@Override
	public void generateTodoWorkflow(long workflowId, String workflowName,
			List<TodoWorkflowMap> todoMapList, String applicationId,
			String applicationDesc, Map deptMap) {
		List<TodoWorkflow> todoWorkflowList = new ArrayList<TodoWorkflow>();
		for (TodoWorkflowMap todoWorkflowMap : todoMapList) {
			String ownerDept = getDepartment(todoWorkflowMap.getRoleId(),
					deptMap);
			TodoWorkflow todoWorkflow = new TodoWorkflow();
			todoWorkflow.setWorkflowId(workflowId);
			todoWorkflow.setWorkflowName(workflowName);
			todoWorkflow.setApplicationId(applicationId);
			todoWorkflow.setApplicationStatus(todoWorkflowMap
					.getApplicationStatus());
			todoWorkflow.setApplicationStatusDesc(todoWorkflowMap
					.getApplicationStatusDesc());
			todoWorkflow.setRoleId(todoWorkflowMap.getRoleId());
			todoWorkflow.setDeptId(ownerDept);
			todoWorkflow.setApplicationName(todoWorkflowMap
					.getApplicationName());
			todoWorkflow.setApplicationDesc(applicationDesc);
			todoWorkflow.setUrl(todoWorkflowMap.getUrl());
			todoWorkflow.setTodoFlag("TODO");
			todoWorkflowList.add(todoWorkflow);
		}
		todoWorkflowService.updateTodoflagByWorkflowId(workflowId, "DONE");
		todoWorkflowService.addTodoWorkflowList(todoWorkflowList);

	}

	private String getDepartment(String roleId, Map deptMap) {
		String deptId = (String) deptMap.get("deptId");
		String bigAreaId = (String) deptMap.get("bigAreaId");
		if (roleId.equals("4")) {
			return bigAreaId;
		} else {
			return deptId;
		}
	}

	@Override
	public List<TodoWorkflow> getTodoWorkflowByWorkflowId(String workflowName,
			String workflowId, String roleId, String[] deptIds) {
		return todoWorkflowService.getTodoWorkflowByWorkflowId(workflowName,
				workflowId, roleId, deptIds, "TODO");
	}

	@Override
	public List<TodoWorkflow> getTodoWorkflowByWorkflowName(
			String workflowName, String roleId, String[] deptIds) {
		return todoWorkflowService.getTodoWorkflowByWorkflowName(workflowName,
				roleId, deptIds, "TODO");
	}

	@Override
	public boolean updateTodoflagByWorkflowId(Long workflowId, String todoFlag) {
		return todoWorkflowService.updateTodoflagByWorkflowId(workflowId,
				todoFlag);
	}

	@Override
	public boolean updateTodoflagById(String id, String todoFlag) {
		return todoWorkflowService.updateTodoflagById(id, todoFlag);
	}

	@Override
	public void addTodoWorkflow(TodoWorkflow todoWorkflow) {
		todoWorkflowService.addTodoWorkflow(todoWorkflow);
	}

	@Override
	public void addTodoWorkflowList(List<TodoWorkflow> todoWorkflowList) {
		todoWorkflowService.addTodoWorkflowList(todoWorkflowList);
	}

	@Override
	public List<TodoWorkflowMap> searchTodoWorkflowMap(String workflowName,
			String fromStatus, String toStatus) {
		return todoWorkflowService.searchTodoWorkflowMap(workflowName,
				fromStatus, toStatus);
	}

}