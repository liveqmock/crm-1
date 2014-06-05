package com.deppon.crm.module.recompense.server.manager.workflow.function;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.GoodsTrans;
import com.deppon.crm.module.recompense.shared.domain.IssueItem;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.RecompenseAttachment;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;
/**
 * 
 * <p>
 * Description:保存理赔上报<br />
 * </p>
 * @title SaveRecompenseReportFunction.java
 * @package com.deppon.crm.module.recompense.server.manager.workflow.function 
 * @author roy
 * @version 0.1 2013-1-21
 */
public class SaveRecompenseReportFunction implements FunctionProvider {
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
		RecompenseApplication app = (RecompenseApplication) transientVars
				.get(Constants.RECOMPENSE_APPLICATION);
		RecompenseApplication oldApp = recompenseService
				.getRecompenseApplicationById(app.getId());
		app.setReportDate(new Date());
		// 分配费用
		app = recompenseManager.assignDeptCharge(app, oldApp);
		transientVars.put("recomepseType", app.getRecompenseMethod());
		if ((app.getDeptChargeList() != null && app.getDeptChargeList().size() > 0)
				|| (oldApp.getDeptChargeList() != null && oldApp
						.getDeptChargeList().size() > 0)) {
			transientVars.put("assigned", true);
		} else {
			transientVars.put("assigned", false);
		}
		Map<String, List<IssueItem>> issueItemModifyMap = (Map<String, List<IssueItem>>) transientVars
				.get(Constants.RECOMPENSE_ISSUEITEM_MAP);
		Map<String, List<GoodsTrans>> goodsTransModifyMap = (Map<String, List<GoodsTrans>>) transientVars
				.get(Constants.RECOMPENSE_GOODSTRANS_MAP);
		Map<String, List<RecompenseAttachment>> attachmentModifyMap = (Map<String, List<RecompenseAttachment>>) transientVars
				.get(Constants.RECOMPENSE_ATTACHMENT_MAP);
		recompenseService.updateRecompenseReportInfo(app, issueItemModifyMap,
				goodsTransModifyMap, attachmentModifyMap);

	}

}
