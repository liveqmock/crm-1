package com.deppon.crm.module.common.server.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.server.service.ITodoWorkflowService;
import com.deppon.crm.module.common.shared.domain.TodoWorkflow;
import com.deppon.crm.module.common.shared.domain.TodoWorkflowMap;
import com.deppon.crm.module.organization.server.service.impl.DepartmentService;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowContext;
import com.opensymphony.workflow.spi.WorkflowEntry;
import com.opensymphony.workflow.spi.WorkflowStore;

/**
 * 描述:下一步Owner
 * 
 * @author huangzhanming
 * @date 2012-1-5
 */

public class GenerateTodoWorkflowFunction implements FunctionProvider {
	private ITodoWorkflowService todoWorkflowService;

	// private DepartmentService departmentService;
	//
	// public DepartmentService getDepartmentService() {
	// return departmentService;
	// }
	//
	// public void setDepartmentService(DepartmentService departmentService) {
	// this.departmentService = departmentService;
	// }

	public ITodoWorkflowService getTodoWorkflowService() {
		return todoWorkflowService;
	}

	public void setTodoWorkflowService(ITodoWorkflowService todoWorkflowService) {
		this.todoWorkflowService = todoWorkflowService;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(Map transientVars, Map args, PropertySet ps) {
		WorkflowContext context = (WorkflowContext) transientVars
				.get("context");
		WorkflowEntry entry = (WorkflowEntry) transientVars.get("entry");
		WorkflowStore store = (WorkflowStore) transientVars.get("store");
		long workflowId = entry.getId();
		String workflowName = entry.getWorkflowName();
		String fromStatus = (String) transientVars.get("fromStatus");
		String toStatus = (String) transientVars.get("toStatus");
		// String applicationName = (String) transientVars.get("appName");
		String applicationDesc = (String) transientVars.get("appDesc");
		String deptId = (String) transientVars.get("deptId");
		String bigAreaId = (String) transientVars.get("bigAreaId");
		// 查询当前角色

		// String[] roleList = ((String) args.get("nextRole")).split(",");
		// String[] roleList = { "经理", "专员", "收银员" };
		// 当前理赔单的部门
		// User user = (User) UserContext.getCurrentUser();
		// String deptId = user.getEmpCode().getDeptId().getId();
		// 生成代办事宜
		List<TodoWorkflowMap> twmList = todoWorkflowService
				.searchTodoWorkflowMap(workflowName, fromStatus, toStatus);
		List<TodoWorkflow> todoWorkflowList = new ArrayList<TodoWorkflow>();
		for (TodoWorkflowMap todoWorkflowMap : twmList) {
			String ownerDept = getDepartment(todoWorkflowMap.getRoleId(),
					deptId, bigAreaId);
			TodoWorkflow todoWorkflow = new TodoWorkflow();
			todoWorkflow.setWorkflowId(workflowId);
			todoWorkflow.setWorkflowName(workflowName);
			todoWorkflow.setApplicationId(ps.getString("appId"));
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

	private String getDepartment(String roleId, String deptId, String bigAreaId) {
		if (roleId.equals("4")) {
			// Department dept_l1 = departmentService.queryById(deptId);
			// Department dept_l2 = departmentService.queryById(dept_l1
			// .getParentCode().getId());
			// // DepartmentService deptService=new DepartmentService();
			// return dept_l2.getParentCode().getId();
			return bigAreaId;
		} else {
			return deptId;
		}
	}
}
