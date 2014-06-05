package com.deppon.crm.module.recompense.server.manager.workflow.function;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.server.manager.ITodoWorkflowManager;
import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowContext;
import com.opensymphony.workflow.WorkflowException;
/**
 * 
 * <p>
 * Description:免除理赔<br />
 * </p>
 * @title ExemptRecompenseFunction.java
 * @package com.deppon.crm.module.recompense.server.manager.workflow.function 
 * @author roy
 * @version 0.1 2013-1-21
 */
public class ExemptRecompenseFunction implements FunctionProvider {
	//理赔service
	private RecompenseService recompenseService;
	//工作流manager
	private ITodoWorkflowManager todoWorkflowManager;

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

	@Override
	public void execute(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {
		//或者理赔信息
		RecompenseApplication appInfo = (RecompenseApplication) transientVars
				.get(Constants.RECOMPENSE_APPLICATION);
		//根据id查询理赔app
		RecompenseApplication app = recompenseService
				.getRecompenseApplicationById(appInfo.getId());
		app.setCostExplain(appInfo.getCostExplain());
		Double zero = 0d;
		app.setNormalAmount(zero);
		app.setRealAmount(zero);
		//设置如部门费用
		List<DeptCharge> deptCharges = app.getDeptChargeList();
		if (deptCharges != null) {
			for (DeptCharge deptCharge : deptCharges) {
				deptCharge.setAmount(zero);
			}
		}
		//更新
		todoWorkflowManager.updateTodoflagByWorkflowId(app.getWorkflowId(),
				"DONE");
		recompenseService.updateRecompenseExemptInfo(app);

	}

}
