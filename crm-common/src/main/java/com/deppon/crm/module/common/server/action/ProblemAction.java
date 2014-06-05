package com.deppon.crm.module.common.server.action;

import java.util.List;

import com.deppon.crm.module.common.server.manager.IProblemManager;
import com.deppon.crm.module.common.shared.domain.Problem;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/**
 * 解决问题
 * 
 */
public class ProblemAction  extends AbstractAction{

	private IProblemManager problemManager;

	
	
	public void setProblemManager(IProblemManager problemManager) {
		this.problemManager = problemManager;
	}

	private List<Problem> listProblem;
	public List<Problem> getListProblem() {
		return listProblem;
	}

	public String showProblemList(){
		listProblem=problemManager.queryAllFeedBack();
		return SUCCESS;
	}
}
