package com.deppon.crm.module.recompense.server.manager.workflow.function;

import java.util.Map;

import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.Payment;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;
/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title UpdateReturnPaymentFunction.java
 * @package com.deppon.crm.module.recompense.server.manager.workflow.function 
 * @author 华龙
 * @version 0.1 2013-1-6
 */
public class UpdateReturnPaymentFunction implements FunctionProvider {
	public RecompenseService getRecompenseService() {
		return recompenseService;
	}

	public void setRecompenseService(RecompenseService recompenseService) {
		this.recompenseService = recompenseService;
	}

	private RecompenseService recompenseService;

	@Override
	public void execute(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {
		RecompenseApplication app = (RecompenseApplication) transientVars
				.get(Constants.RECOMPENSE_APPLICATION);
		String recompenseNum = app.getRecompenseNum();
		String paymentId = recompenseService
				.findPaymentIdByRecompenseNum(recompenseNum);
		Payment payment = new Payment();
		payment.setId(paymentId);
		String paymentStatus = (String) args.get("paymentStatus");
		payment.setPaymentStatus(paymentStatus);
		recompenseService.updatePayment(payment);
	}

}
