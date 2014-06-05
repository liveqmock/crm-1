package com.deppon.crm.module.interfaces.workflow.impl;

import java.util.Date;

import javax.jws.WebService;

import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.crm.module.interfaces.common.util.IntefacesTool;
import com.deppon.crm.module.interfaces.workflow.IPaymentApplyResultService;
import com.deppon.crm.module.interfaces.workflow.domain.WorkflowApplyResultInfo;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.foss.framework.exception.GeneralException;

@WebService(endpointInterface = "com.deppon.crm.module.interfaces.workflow.IPaymentApplyResultService")
public class PaymentApplyResultServiceImpl implements
		IPaymentApplyResultService {
	// 理赔模块
	RecompenseManager recompenseManager;
	// 员工模块
	IUserService userService;

	/**
	 * @作者：罗典
	 * @时间：2012-4-23
	 * @描述：在线理赔付款工作流审批（此方法已废弃）
	 * */
	@Override
	public boolean returnPaymentApplyResult(WorkflowApplyResultInfo resultInfo)
			throws CrmBusinessException {
		// 登录用户名不能为空
		if (resultInfo.getExminePerson() == null
				|| resultInfo.getExminePerson().equals("")) {
			throw new CrmBusinessException("0019");
		}
		// 获取用户信息
		User user = userService.findByLoginName(resultInfo.getExminePerson());
		// 工作流编号
		String workflowNum = resultInfo.getWorkflowNumber();
		// 失败原因
		String reason = resultInfo.getExminePerson();
		// 审批时间
		Date approveDate = resultInfo.getExamineDate();
		// 审批成功
		if (resultInfo.isExmineResult()) {
			// recompenseManager.payPaymentApprove(workflowNum, user, reason,
			// approveDate);
		}
		// 审批失败
		else {
			// recompenseManager.payPaymentRefuse(workflowNum, user, reason,
			// approveDate);
		}
		return true;
	}
	/**
	 * @作者：罗典
	 * @描述：理赔付款ERP状态反馈
	 * @参数 凭证号 voucherNum, 付款状态 1 通过，0 不通过 
	 * @返回值 boolean 操作是否成功
	 */
	@Override
	public boolean returnVoucherPaymentResult(String voucherNum,
			int paymentResult) throws CrmBusinessException {
		try{
			if (paymentResult == 0) {
				// 不通过
				recompenseManager.paymentRefuse(voucherNum);
			} else if (paymentResult == 1) {
				// 通过
				recompenseManager.paymentApprove(voucherNum);
			} else {
				// 理赔付款ERP状态反馈值类型有误
				throw new CrmBusinessException("0031");
			}
		} catch (GeneralException e) {
			String errorMsg = IntefacesTool.getMessage("recompense",
					e.getErrorCode());
			throw new CrmBusinessException("1005",errorMsg);
		} catch(Exception e){
			throw new CrmBusinessException("1005","voucherNum:"+
					voucherNum+",paymentResult:"+paymentResult+e);
		}
		return true;
	}

	public RecompenseManager getRecompenseManager() {
		return recompenseManager;
	}

	public void setRecompenseManager(RecompenseManager recompenseManager) {
		this.recompenseManager = recompenseManager;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
