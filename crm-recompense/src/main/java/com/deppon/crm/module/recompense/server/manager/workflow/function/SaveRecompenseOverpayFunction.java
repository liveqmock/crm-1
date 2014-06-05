package com.deppon.crm.module.recompense.server.manager.workflow.function;

import java.util.Map;

import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowContext;
import com.opensymphony.workflow.WorkflowException;

/**
 * 
 * <p>
 * Description:保存多赔<br />
 * </p>
 * 
 * @title SaveRecompenseOverpayFunction.java
 * @package com.deppon.crm.module.recompense.server.manager.workflow.function
 * @author roy
 * @version 0.1 2013-1-21
 */
public class SaveRecompenseOverpayFunction implements FunctionProvider {
	//理赔service
	private RecompenseService recompenseService;
	//理赔manager
	private RecompenseManager recompenseManager;

	public RecompenseService getRecompenseService() {
		return recompenseService;
	}

	public void setRecompenseService(RecompenseService recompenseService) {
		this.recompenseService = recompenseService;
	}

	public RecompenseManager getRecompenseManager() {
		return recompenseManager;
	}

	public void setRecompenseManager(RecompenseManager recompenseManager) {
		this.recompenseManager = recompenseManager;
	}

	@Override
	public void execute(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {

	}

}
