package com.deppon.crm.module.recompense.server.manager.workflow.function;

import java.util.Map;

import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;

public class RecompenseBackFunction implements FunctionProvider{
	// 理赔service
	private RecompenseService recompenseService;

	public RecompenseService getRecompenseService() {
		return recompenseService;
	}

	public void setRecompenseService(RecompenseService recompenseService) {
		this.recompenseService = recompenseService;
	}
	public void execute(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {
	//在线理赔和正常理赔 理赔退回前执行 
	}
}
