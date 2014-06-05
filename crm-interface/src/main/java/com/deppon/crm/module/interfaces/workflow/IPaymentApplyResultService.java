package com.deppon.crm.module.interfaces.workflow;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.workflow.domain.WorkflowApplyResultInfo;

/**
 * 付款工作流结果返回
 * 
 * @author davidcun @2012-4-6
 */
@WebService
public interface IPaymentApplyResultService {

	/**
	 * <p>
	 * 需求：FIN-2
	 * </p>
	 * 付款工作流审批结果返回，包括在线理赔付款、常规理赔付款、多赔付款
	 * 
	 * @param resultInfo
	 *            工作流结果审批结果
	 * @return 操作是否成功
	 * @Throws {@link CrmBusinessException}
	 * @author davidcun 2012-4-6
	 */
	public boolean returnPaymentApplyResult(
			@WebParam(name = "resultInfo") WorkflowApplyResultInfo resultInfo)
			throws CrmBusinessException;

	/**
	 * <p>
	 * 需求：黄展明
	 * </p>
	 * 理赔付款ERP状态反馈
	 * 
	 * @param 凭证号
	 *            voucherNum, 付款状态 1 通过，0 不通过 paymentResult
	 * @return 操作是否成功
	 * @Throws {@link CrmBusinessException}
	 * @author davidcun 2012-4-6
	 */
	public boolean returnVoucherPaymentResult(
			@WebParam(name = "voucherNum") String voucherNum,
			@WebParam(name = "paymentResult") int paymentResult)
			throws CrmBusinessException;
}
