package com.deppon.crm.module.recompense.server.manager.workflow.function;

import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
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
 * Description:多赔审批<br />
 * </p>
 * @title SubmitRecompenseOverpayApprovalFunction.java
 * @package com.deppon.crm.module.recompense.server.manager.workflow.function 
 * @author roy
 * @version 0.1 2013-1-21
 */
public class SubmitRecompenseOverpayApprovalFunction implements
		FunctionProvider {
	//理赔service
	private RecompenseService recompenseService;
	//理赔manager
	private RecompenseManager recompenseManager;

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
		RecompenseApplication recompense = (RecompenseApplication) transientVars
				.get(Constants.RECOMPENSE_APPLICATION);
		User user = (User) transientVars.get(Constants.RECOMPENSE_CURRENT_USER);
		recompenseManager.submitRecompenseOverpayApproval(recompense, user,
				user.getEmpCode().getEmpCode(), user.getEmpCode().getEmpName());
		}

}
