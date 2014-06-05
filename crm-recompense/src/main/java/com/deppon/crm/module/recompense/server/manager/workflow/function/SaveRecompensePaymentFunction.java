package com.deppon.crm.module.recompense.server.manager.workflow.function;

import java.util.Map;

import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.PaymentBill;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowContext;
import com.opensymphony.workflow.WorkflowException;
/**
 * 
 * <p>
 * Description:保存理赔付款单信息<br />
 * </p>
 * @title SaveRecompensePaymentFunction.java
 * @package com.deppon.crm.module.recompense.server.manager.workflow.function 
 * @author roy
 * @version 0.1 2013-1-21
 */
public class SaveRecompensePaymentFunction implements FunctionProvider {
	//理赔service
	private RecompenseService recompenseService;

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
		recompenseService.updateRecompensePaymentInfo(app);

	}

}
