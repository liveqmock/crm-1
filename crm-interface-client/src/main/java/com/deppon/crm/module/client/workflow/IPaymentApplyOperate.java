package com.deppon.crm.module.client.workflow;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.workflow.domain.AccountInfo;
import com.deppon.crm.module.client.workflow.domain.PaymentInfo;
import com.deppon.erp.payment.DepClaimsBill;
import com.deppon.foss.crm.ClaimsPayBillGenerateRequest;

/**
 * 付款申请审批工作流
 * @author davidcun @2012-4-6
 */
public interface IPaymentApplyOperate {

	/**
	 * @作者：罗典
	 * @时间：2012-6-27
	 * @描述：提交FOSS付款申请
	 * @参数：
	 */
	public boolean claimsApbill(ClaimsPayBillGenerateRequest claimsRequest) throws CrmBusinessException;
	/**
	 * @作者：罗典
	 * @时间：2012-6-27
	 * @描述：提交ERP付款申请
	 * @参数：paymentinfos 付款主要信息，deptChargeInfos 部门费用信息
	 */
	public String submitPaymentInfo(List<String> paymentinfos,List<String> deptChargeInfos) throws CrmBusinessException;
	
	/**
	 * @作者：罗典
	 * @时间：2012-8-30
	 * @描述：根据时间查询付款申请信息
	 * @参数：申请时间段，起始时间，结束时间
	 * */
	public List<DepClaimsBill> queryDepClaimsByApproveDate(Date beginDate,Date endDate)throws CrmBusinessException;
	
	/**
	 * @作者：罗典
	 * @时间：2012-8-30
	 * @描述：根据申请付款理赔单集合查询相应付款单状态
	 * @参数：
	 * */
	public List<DepClaimsBill> queryDepClaimsByNumList(List<String> recompenseNumList)throws CrmBusinessException;
	
	/**
	 * <p>需求：FIN-1</p>
	 * 常规理赔或者多陪付款工作流
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-6
	 *
	 */
	public String paymentApply(PaymentInfo paymentInfo) throws CrmBusinessException;
	
	/**
	 * <p>需求：FIN-3</p>
	 * 在线理赔付款工作流
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-6
	 *
	 */
	public String paymentOnlineApply(PaymentInfo paymentInfo) throws CrmBusinessException;
	
	/**
	 * <p>需求：FIN-4</p>
	 * 通过工号查询营业员的账户信息，支持现金付款的流程。
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-12
	 *
	 */
	public AccountInfo queryAccount(String jobNumber) throws CrmBusinessException;
	
}
