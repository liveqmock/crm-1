package com.deppon.crm.module.recompense.server.manager.workflow.function;

import java.util.Date;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.ITodoWorkflowManager;
import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.service.impl.ClaimService;
import com.deppon.crm.module.recompense.server.utils.ClaimConstants;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.Claim;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.foss.framework.server.context.UserContext;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowContext;
import com.opensymphony.workflow.WorkflowException;
/**
 * 
 * <p>
 * Description:删除理赔<br />
 * </p>
 * @title DeleteRecompenseFunction.java
 * @package com.deppon.crm.module.recompense.server.manager.workflow.function 
 * @author roy
 * @version 0.1 2013-1-21
 */
public class DeleteRecompenseFunction implements FunctionProvider {
	//理赔service
	private RecompenseService recompenseService;
	//工作流manager
	private ITodoWorkflowManager todoWorkflowManager;
	
	// 索赔service
	private ClaimService claimService;

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
		RecompenseApplication app = (RecompenseApplication) transientVars
				.get(Constants.RECOMPENSE_APPLICATION);
		todoWorkflowManager.updateTodoflagByWorkflowId(app.getWorkflowId(),
				"DONE");
		// 更新在线理赔状态
		if (app.getRecompenseMethod().equals(Constants.ONLINE_TYPE)) {
			recompenseService.updateOnlineApplyStatusByRecompenseId(
					app.getId(), Constants.STATUS_REJECTED, null);
		}
		//更新索赔状态 
		RecompenseApplication newApp=recompenseService.getRecompenseApplicationById(app.getId());
		Claim claim = claimService.getClaimByWaybillNumber(newApp.getWaybill()
				.getWaybillNumber());
		User user = (User) UserContext.getCurrentUser();
		if (null != claim) {
			// 更新对应单号的索赔状态为待处理
			claim.setClaimStatus(ClaimConstants.CLAIM_STATUS_WAIT_DO);
			claim.setStatusSeq("1");
			claim.setModifyDate(new Date());
			claim.setModifyUser(user.getEmpCode().getId());
			// 执行更新操作
			claimService.updateClaim(claim);
		}
		//根据id删除
		recompenseService.deleteRecompenseApplication(app.getId());

	}

	/**
	 * <p>
	 * Description:claimService<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-14
	 */
	public ClaimService getClaimService() {
		return claimService;
	}

	/**
	 * <p>
	 * Description:claimService<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-14
	 */
	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

}
