package com.deppon.crm.module.common.server.service.impl;

import java.util.List;

import com.deppon.crm.module.common.server.dao.ITodoWorkflowDao;
import com.deppon.crm.module.common.server.service.ITodoWorkflowService;
import com.deppon.crm.module.common.shared.domain.TodoWorkflow;
import com.deppon.crm.module.common.shared.domain.TodoWorkflowMap;

public class TodoWorkflowService implements ITodoWorkflowService {
	private ITodoWorkflowDao todoWorkflowDao;

	public ITodoWorkflowDao getTodoWorkflowDao() {
		return todoWorkflowDao;
	}

	public void setTodoWorkflowDao(ITodoWorkflowDao todoWorkflowDao) {
		this.todoWorkflowDao = todoWorkflowDao;
	}

	@Override
	public boolean updateTodoflagByWorkflowId(Long workflowId, String todoFlag) {
		return todoWorkflowDao.updateTodoflagByWorkflowId(workflowId, todoFlag);
	}

	@Override
	public boolean updateTodoflagById(String id, String todoFlag) {
		return todoWorkflowDao.updateTodoflagById(id, todoFlag);
	}

	@Override
	public void addTodoWorkflow(TodoWorkflow todoWorkflow) {
		todoWorkflowDao.addTodoWorkflow(todoWorkflow);
	}

	@Override
	public void addTodoWorkflowList(List<TodoWorkflow> todoWorkflowList) {
		todoWorkflowDao.addTodoWorkflowList(todoWorkflowList);
	}

	@Override
	public List<TodoWorkflowMap> searchTodoWorkflowMap(String workflowName,
			String fromStatus, String toStatus) {
		return todoWorkflowDao.searchTodoWorkflowMap(workflowName, fromStatus,
				toStatus);
	}

	@Override
	public List<TodoWorkflow> getTodoWorkflowByWorkflowId(String workflowName,
			String workflowId, String roleId, String[] deptIds, String todoFlag) {
		return todoWorkflowDao.getTodoWorkflowByWorkflowId(workflowName,
				workflowId, roleId, deptIds, todoFlag);
	}

	@Override
	public List<TodoWorkflow> getTodoWorkflowByWorkflowName(
			String workflowName, String roleId, String[] deptIds,
			String todoFlag) {
		return todoWorkflowDao.getTodoWorkflowByWorkflowName(workflowName,
				roleId, deptIds, todoFlag);
	}

}
