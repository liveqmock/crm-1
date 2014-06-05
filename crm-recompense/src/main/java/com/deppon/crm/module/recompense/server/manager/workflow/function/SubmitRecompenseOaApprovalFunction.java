package com.deppon.crm.module.recompense.server.manager.workflow.function;

import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.server.service.impl.DepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;

/**
 * 
 * <p>
 * Description:提交理赔工作流审批<br />
 * </p>
 * 
 * @title SubmitRecompenseOaApprovalFunction.java
 * @package com.deppon.crm.module.recompense.server.manager.workflow.function
 * @author roy
 * @version 0.1 2013-1-21
 */
public class SubmitRecompenseOaApprovalFunction implements FunctionProvider {
	// 理赔service
	private RecompenseService recompenseService;
	// 理赔manager
	private RecompenseManager recompenseManager;
	// 部门service
	private DepartmentService departmentService;

	public RecompenseManager getRecompenseManager() {
		return recompenseManager;
	}

	public void setRecompenseManager(RecompenseManager recompenseManager) {
		this.recompenseManager = recompenseManager;
	}

	public RecompenseService getRecompenseService() {
		return recompenseService;
	}

	public void setRecompenseService(RecompenseService recompenseService) {
		this.recompenseService = recompenseService;
	}

	@Override
	public void execute(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {
		RecompenseApplication app = (RecompenseApplication) transientVars
				.get(Constants.RECOMPENSE_APPLICATION);
		User reportMan = recompenseManager.getUserById(app.getReportMan());
		User user = (User) transientVars.get(Constants.RECOMPENSE_CURRENT_USER);
		app = recompenseService.getRecompenseApplicationById(app.getId());
		// author by xu
		// 由于OA组织架构变更，导致存储在表中的所属区域名字为历史数据，顾在提交工作流的时候根据所属deptId去查询一遍新的部门名字到OA，
		// 或许快递版本优化直接传编码
		Department belongBigArea = departmentService.getDepartmentById(app.getDeptId());
		if (null != belongBigArea) {
			app.setDeptName(belongBigArea.getDeptName());

		}
		recompenseManager.submitRecompenseOaApproval(app, user, reportMan
				.getEmpCode().getEmpCode());
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

}
