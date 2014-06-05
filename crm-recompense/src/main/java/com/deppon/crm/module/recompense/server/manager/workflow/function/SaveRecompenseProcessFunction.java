package com.deppon.crm.module.recompense.server.manager.workflow.function;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.AwardItem;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.MessageReminder;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.ResponsibleDept;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowContext;
import com.opensymphony.workflow.WorkflowException;

/**
 * 
 * <p>
 * Description:保存理赔处理<br />
 * </p>
 * 
 * @title SaveRecompenseProcessFunction.java
 * @package com.deppon.crm.module.recompense.server.manager.workflow.function
 * @author roy
 * @version 0.1 2013-1-21
 */
public class SaveRecompenseProcessFunction implements FunctionProvider {
	private RecompenseService recompenseService;

	public RecompenseService getRecompenseService() {
		return recompenseService;
	}

	public void setRecompenseService(RecompenseService recompenseService) {
		this.recompenseService = recompenseService;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {
		// 2012-3-27之后代码
		RecompenseApplication recompense = (RecompenseApplication) transientVars
				.get(Constants.RECOMPENSE_APPLICATION);

		if (recompense.getWaybill().getTransType()
				.equals(Constants.TRANS_EXPRESS)) {
			if (recompense.getRealAmount() != null
					&& recompense.getRealAmount() <= 200) {
				transientVars.put("toApproved", true);
			} else {
				transientVars.put("toApproved", false);
			}

		} else {
			if (recompense.getRealAmount() != null
					&& recompense.getRealAmount() <= 1000) {
				transientVars.put("toApproved", true);
			} else {
				transientVars.put("toApproved", false);
			}
		}

		transientVars.put("recomepseType", recompense.getRecompenseMethod());
		Map<String, List<DeptCharge>> deptChargeMap = (Map<String, List<DeptCharge>>) transientVars
				.get(Constants.RECOMPENSE_DEPTCHARGE_MAP);
		Map<String, List<ResponsibleDept>> responsibleDeptMap = (Map<String, List<ResponsibleDept>>) transientVars
				.get(Constants.RECOMPENSE_RESPONSIBLEDEPT_MAP);
		Map<String, List<MessageReminder>> messageReminderMap = (Map<String, List<MessageReminder>>) transientVars
				.get(Constants.RECOMPENSE_MESSAGEREMINDER_MAP);
		Map<String, List<AwardItem>> awardItemMap = (Map<String, List<AwardItem>>) transientVars
				.get(Constants.RECOMPENSE_AWARDITEM_MAP);
		recompenseService.updateRecompenseProcessInfo(recompense,
				deptChargeMap, responsibleDeptMap, messageReminderMap,
				awardItemMap);

	}

}
