package com.deppon.crm.module.common.server.manager;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.shared.domain.TodoWorkflow;
import com.deppon.crm.module.common.shared.domain.TodoWorkflowMap;

public interface ITodoWorkflowManager {
	public boolean updateTodoflagByWorkflowId(Long workflowId, String todoFlag);

	public boolean updateTodoflagById(String id, String todoFlag);

	public void addTodoWorkflow(TodoWorkflow todoWorkflow);

	public void addTodoWorkflowList(List<TodoWorkflow> todoWorkflowList);

	public void generateTodoWorkflow(long workflowId, String workflowName,
			List<TodoWorkflowMap> todoMapList, String applicationId,
			String applicationDesc, Map deptMap);

	public void generateTodoWorkflow(long workflowId, String workflowName,
			String fromStatus, String toStatus, String applicationId,
			String applicationDesc, Map deptMap);

	public List<TodoWorkflow> getTodoWorkflowByWorkflowId(String workflowName,
			String workflowId, String roleId, String[] deptIds);

	public List<TodoWorkflow> getTodoWorkflowByWorkflowName(
			String workflowName, String roleId, String[] deptIds);

	public List<TodoWorkflowMap> searchTodoWorkflowMap(String workflowName,
			String fromStatus, String toStatus);
}
