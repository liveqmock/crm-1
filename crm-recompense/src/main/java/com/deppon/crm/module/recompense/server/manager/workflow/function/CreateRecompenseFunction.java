package com.deppon.crm.module.recompense.server.manager.workflow.function;

import java.util.Date;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.service.impl.ClaimService;
import com.deppon.crm.module.recompense.server.utils.ClaimConstants;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.Claim;
import com.deppon.crm.module.recompense.shared.domain.OnlineApply;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;

/**
 * 
 * <p>
 * Description:理赔上报<br />
 * </p>
 * @title CreateRecompenseFunction.java
 * @package com.deppon.crm.module.recompense.server.manager.workflow.function 
 * @author roy
 * @version 0.1 2013-1-21
 */

public class CreateRecompenseFunction implements FunctionProvider {
	//理赔service
	private RecompenseService recompenseService;
	//理赔manager
	private RecompenseManager recompenseManager;
	// 索赔service
	private ClaimService claimService;

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

	/**
	 * @Title: execute
	 * @Description: 创建理赔单保存数据操作
	 * @param transientVars
	 *            工作流输入输出参数
	 * @param args
	 *            工作流输入参数
	 * @param ps
	 *            工作流持久化参数
	 * @throws WorkflowException
	 */
	@Override
	public void execute(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {
		User user = (User) transientVars.get(Constants.RECOMPENSE_CURRENT_USER);
		RecompenseApplication app = (RecompenseApplication) transientVars
				.get(Constants.RECOMPENSE_APPLICATION);
		// 分配费用
		app = recompenseManager.assignDeptCharge(app, null);
		
		// 运输类型异常转换（中文转英文存入数据库）
		app = recompenseManager.replaceTranType(app);
		
		if (app.getDeptChargeList() != null
				&& app.getDeptChargeList().size() > 0) {
			transientVars.put("assigned", true);
		} else {
			transientVars.put("assigned", false);
		}

		// 保存理赔数据
		transientVars.put("recomepseType", app.getRecompenseMethod());
		Date currentDate = new Date();
		app.setCreateDate(currentDate);
		app.setCreateUser(user.getId());
		app.setReportDate(currentDate);
		app.setStatus(Constants.STATUS_SUBMITED);
		app.setRecompenseNum(app.getWaybill().getWaybillNumber());
		recompenseService.createRecompenseApplication(app);
		ps.setString(Constants.RECOMPENSE_APPLICATION_ID, app.getId());
		if (app.getRecompenseMethod().equals(Constants.ONLINE_TYPE)) {
			OnlineApply onlineApply = recompenseService.getOnlineApplyById(app
					.getOnlineApplyId());
			onlineApply.setRecompenseId(app.getId());
			onlineApply.setStatus(Constants.STATUS_SUBMITED);
			recompenseService.updateOnlineApply(onlineApply);
			// 根据凭证号查询是否有索赔
		}
		Claim claim = claimService.getClaimByWaybillNumber(app.getWaybill()
				.getWaybillNumber());
		if (null != claim) {
			// 更新对应单号的索赔状态为已处理
			claim.setClaimStatus(ClaimConstants.CLAIM_STATUS_HANDLED);
			claim.setStatusSeq("3");
			claim.setModifyDate(new Date());
			claim.setModifyUser(user.getEmpCode().getId());
			// 执行更新操作
			claimService.updateClaim(claim);
		}
	}

	/**
	 * <p>
	 * Description:claimService<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-7
	 */
	public ClaimService getClaimService() {
		return claimService;
	}

	/**
	 * <p>
	 * Description:claimService<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-3-7
	 */
	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	// private boolean hasDepartment(Map<String, Department> dutyDept) {
	// if (dutyDept.containsKey(Constants.RECOMPENSE_LEAVE_DEPT)
	// && dutyDept.containsKey(Constants.RECOMPENSE_REPORT_DEPT)) {
	// return true;
	// }
	// return false;
	// }
}
