package com.deppon.crm.module.common.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.server.dao.ITodoWorkflowDao;
import com.deppon.crm.module.common.shared.domain.TodoWorkflow;
import com.deppon.crm.module.common.shared.domain.TodoWorkflowMap;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class TodoWorkflowDao extends iBatis3DaoImpl implements ITodoWorkflowDao {
	private static final String NAMESPACE = "com.deppon.crm.module.common.shared.domain.TodoWorkflow.";

	@Override
	public boolean updateTodoflagByWorkflowId(Long workflowId, String todoFlag) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("workflowId", workflowId.toString());
		params.put("todoFlag", todoFlag);
		int updated = getSqlSession().update(
				NAMESPACE + "updateTodoFlagByWorkflowId", params);
		return updated > 0;
	}

	@Override
	public boolean updateTodoflagById(String id, String todoFlag) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		params.put("todoFlag", todoFlag);
		int updated = getSqlSession().update(NAMESPACE + "updateTodoFlagById",
				params);
		return updated > 0;
	}

	@Override
	public void addTodoWorkflow(TodoWorkflow todoWorkflow) {
		this.getSqlSession()
				.insert(NAMESPACE + "addTodoWorkflow", todoWorkflow);

	}

	@Override
	public void addTodoWorkflowList(List<TodoWorkflow> todoWorkflowList) {
		for (TodoWorkflow todoWorkflow : todoWorkflowList) {
			this.getSqlSession().insert(NAMESPACE + "addTodoWorkflow",
					todoWorkflow);
		}

	}

	@Override
	public List<TodoWorkflowMap> searchTodoWorkflowMap(String workflowName,
			String fromStatus, String toStatus) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("workflowName", workflowName);
		params.put("fromStatus", fromStatus);
		params.put("toStatus", toStatus);
		return this.getSqlSession().selectList(
				NAMESPACE + "searchTodoWorkflowMap", params);
	}

	@Override
	public List<TodoWorkflow> getTodoWorkflowByWorkflowId(String workflowName,
			String workflowId, String roleId, String[] deptIds, String todoFlag) {
		Map params = new HashMap();
		params.put("workflowName", workflowName);
		params.put("workflowId", workflowId);
		params.put("roleId", roleId);
		params.put("deptIds", deptIds);
		params.put("todoFlag", todoFlag);
		return this.getSqlSession().selectList(
				NAMESPACE + "getTodoWorkflowByWorkflowId", params);
	}

	@Override
	public List<TodoWorkflow> getTodoWorkflowByWorkflowName(
			String workflowName, String roleId, String[] deptIds,
			String todoFlag) {
		Map params = new HashMap();
		params.put("workflowName", workflowName);
		params.put("roleId", roleId);
		params.put("deptIds", deptIds);
		params.put("todoFlag", todoFlag);
		return this.getSqlSession().selectList(
				NAMESPACE + "getTodoWorkflowByWorkflowName", params);
	}

}
