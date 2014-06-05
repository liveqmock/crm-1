package com.deppon.crm.module.common.server.service;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.TodoWorkflow;
import com.deppon.crm.module.common.shared.domain.TodoWorkflowMap;
import com.deppon.foss.framework.service.IService;

public interface ITodoWorkflowService extends IService {

	public boolean updateTodoflagByWorkflowId(Long workflowId, String todoFlag);

	public boolean updateTodoflagById(String id, String todoFlag);

	public void addTodoWorkflow(TodoWorkflow todoWorkflow);

	public void addTodoWorkflowList(List<TodoWorkflow> todoWorkflowList);

	public List<TodoWorkflowMap> searchTodoWorkflowMap(String workflowName,
			String fromStatus, String toStatus);

	public List<TodoWorkflow> getTodoWorkflowByWorkflowId(String workflowName,
			String workflowId, String roleId, String[] deptIds, String todoFlag);

	public List<TodoWorkflow> getTodoWorkflowByWorkflowName(
			String workflowName, String roleId, String[] deptIds,
			String todoFlag);
	
}
