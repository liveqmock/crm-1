package com.deppon.crm.module.recompense.server.manager.workflow.function;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.ITodoWorkflowManager;
import com.deppon.crm.module.common.shared.domain.TodoWorkflowMap;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.OnlineApply;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.spi.WorkflowEntry;

/**
 * 描述:下一步业务状态
 * 
 * @author huangzhanming
 * @date 2012-1-5
 */

public class UpdateRecompenseStatusFunction implements FunctionProvider {
	//理赔service
	private RecompenseService recompenseService;
	//工作流
	private ITodoWorkflowManager todoWorkflowManager;
	//理赔manager
	private RecompenseManager recompenseManager;

	public RecompenseService getRecompenseService() {
		return recompenseService;
	}

	public void setRecompenseService(RecompenseService recompenseService) {
		this.recompenseService = recompenseService;
	}

	public ITodoWorkflowManager getTodoWorkflowManager() {
		return todoWorkflowManager;
	}

	public void setTodoWorkflowManager(ITodoWorkflowManager todoWorkflowManager) {
		this.todoWorkflowManager = todoWorkflowManager;
	}

	public RecompenseManager getRecompenseManager() {
		return recompenseManager;
	}

	public void setRecompenseManager(RecompenseManager recompenseManager) {
		this.recompenseManager = recompenseManager;
	}


	public void execute(Map transientVars, Map args, PropertySet ps) {
		User user = (User) transientVars.get(Constants.RECOMPENSE_CURRENT_USER);
		WorkflowEntry entry = (WorkflowEntry) transientVars.get("entry");
		RecompenseApplication app = (RecompenseApplication) transientVars
				.get(Constants.RECOMPENSE_APPLICATION);
		String appId = app.getId();
		String status = (String) args.get("recompenseStatus");
		RecompenseApplication oldApp = recompenseService
				.getRecompenseApplicationById(appId);
		String deptId = null;
		String bigAreaId = null;
		if (app.getReportDept() == null || "".equals(app.getReportDept())) {
			deptId = oldApp.getReportDept();
		} else {
			deptId = app.getReportDept();
		}
		if (app.getDeptId() == null || "".equals(app.getDeptId())) {
			bigAreaId = oldApp.getDeptId();
		} else {
			bigAreaId = app.getDeptId();
		}
		transientVars.put("deptId", deptId);
		transientVars.put("bigAreaId", bigAreaId);
		todoWorkflowManager.generateTodoWorkflow(entry.getId(),
				entry.getWorkflowName(), oldApp.getStatus(), status,
				app.getId(), oldApp.getWaybill().getWaybillNumber(),
				transientVars);
		// 生成系统代办消息
		List<TodoWorkflowMap> todoMapList = todoWorkflowManager
				.searchTodoWorkflowMap(entry.getWorkflowName(),
						oldApp.getStatus(), status);
		for (TodoWorkflowMap todoMap : todoMapList) {
			com.deppon.crm.module.common.shared.domain.Message todoMessage = new com.deppon.crm.module.common.shared.domain.Message();
			todoMessage.setTasktype(Constants.TODO_TASK_TYPE);
			todoMessage.setTaskcount(1);
			todoMessage.setIshaveinfo(oldApp.getWaybill().getWaybillNumber()
					+ " " + todoMap.getApplicationStatusDesc());
			if (!todoMap.getRoleId().equals(Constants.ROLE_ADMIN)) {//20140110取消理赔专员未处理提醒
				List<String> userIds = recompenseManager.getMessageUserIds(
					todoMap.getRoleId(), oldApp.getReportDept(),
					oldApp.getDeptId());
				for (String userId : userIds) {
					todoMessage.setUserid(Integer.parseInt(userId));
					recompenseManager.addTodoMessage(todoMessage);
				}
			}
			

		}
		// 更新在线理赔状态
		if (oldApp.getRecompenseMethod().equals(Constants.ONLINE_TYPE)) {
			recompenseService.updateOnlineApplyStatusByRecompenseId(appId,
					status, app.getRejectReason());
			// 在线理赔审批后更新信息
			if (status.equals(Constants.STATUS_APPROVED)) {
				OnlineApply onlineApply = recompenseService
						.getOnlineApplyByRecompenseId(appId);
				onlineApply.setPartAsign(onlineApply.getPartA());
				onlineApply.setPartAsignDate(new Date());
				recompenseService.updateOnlineApply(onlineApply);
			}
		}

		String userId = user.getId();
		if (app.getRecompenseMethod().equals(Constants.FAST_TYPE)
				&& oldApp.getStatus().equals(Constants.STATUS_SUBMITED)
				&& (status.equals(Constants.STATUS_HANDLED) || status
						.equals(Constants.STATUS_APPROVED))) {
			userId = Constants.SYSTEM_SUPER_USER_ID;
		}
		// 更新状态
		// RecompenseApplication statusApp = new RecompenseApplication();
		RecompenseApplication statusApp = recompenseManager
				.generateRecompenseForReport(userId, oldApp.getStatus(), status);
		statusApp.setId(appId);
		statusApp.setStatus(status);
		recompenseService.updateRecompenseStatusInfo(statusApp);
	}

}
