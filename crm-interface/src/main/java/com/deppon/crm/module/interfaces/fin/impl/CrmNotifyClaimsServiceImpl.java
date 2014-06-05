package com.deppon.crm.module.interfaces.fin.impl;

import java.math.BigDecimal;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.common.util.IntefacesTool;
import com.deppon.crm.module.interfaces.fin.ICrmNotifyClaimsService;
import com.deppon.crm.module.interfaces.fin.domain.NotifyClaimsStateRequest;
import com.deppon.crm.module.interfaces.fin.domain.NotifyClaimsStateResponse;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.foss.framework.exception.GeneralException;

public class CrmNotifyClaimsServiceImpl implements ICrmNotifyClaimsService{

	// 理赔模块
	private RecompenseManager recompenseManager;
	/**
	 * @作者：吴根斌
	 * @描述：通知理赔支付状态接口 (接口编码：CRM_NOTIFY_CLAIMS_STATE)
	 * @时间：2013-04-26
	 * @参数：支付状态信息
	 * @返回：处理情况
	 * 报账系统
	 * */
	@Override
	public NotifyClaimsStateResponse notifyClaimState(
			NotifyClaimsStateRequest notifyClaimsStateRequest) {
		NotifyClaimsStateResponse response= new NotifyClaimsStateResponse();
		String voucherNum=notifyClaimsStateRequest.getVoucherNumber();
		String paymentResult=notifyClaimsStateRequest.getPaymentResults();
//		String departmentCode=notifyClaimsStateRequest.getDepartmentCode();
//		BigDecimal paymentMoney=notifyClaimsStateRequest.getPaymentMoney();
		if(voucherNum==null||voucherNum.equals("")){
			response.setFailedReason("voucherNum empty");
			response.setIsSuccess(false);
			return response;
		}else if(paymentResult==null||paymentResult.equals("")){
			response.setFailedReason("paymentResult empty");
			response.setIsSuccess(false);
			return response;
		}
		try{
			voucherNum=notifyClaimsStateRequest.getVoucherNumber();
			paymentResult=notifyClaimsStateRequest.getPaymentResults();
			
			if (paymentResult.equals("0")) {
				// 不通过
				recompenseManager.paymentRefuse(voucherNum);
				response.setIsSuccess(true);
				response.setFailedReason("");
			} else if (paymentResult.equals("1")) {
				// 通过
				recompenseManager.paymentApprove(voucherNum);
				response.setIsSuccess(true);
				response.setFailedReason("");
			} else {
				// 理赔付款ERP状态反馈值类型有误
				response.setIsSuccess(false);
				response.setFailedReason(new CrmBusinessException("0031",paymentResult).getMessage());
			}
		} catch (GeneralException e) {
			String errorMsg = IntefacesTool.getMessage("recompense",
					e.getErrorCode());	
			response.setIsSuccess(false);
			response.setFailedReason(errorMsg);
			
		} catch(Exception e){
			response.setIsSuccess(false);
			response.setFailedReason(e.getMessage());
		}
		return response;
	}
	
	public RecompenseManager getRecompenseManager() {
		return recompenseManager;
	}

	public void setRecompenseManager(RecompenseManager recompenseManager) {
		this.recompenseManager = recompenseManager;
	}
}
