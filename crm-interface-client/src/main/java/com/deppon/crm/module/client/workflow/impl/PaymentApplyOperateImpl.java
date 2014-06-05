package com.deppon.crm.module.client.workflow.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.ws.Holder;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.common.util.NullOrEmptyValidator;
import com.deppon.crm.module.client.workflow.IPaymentApplyOperate;
import com.deppon.crm.module.client.workflow.domain.AccountInfo;
import com.deppon.crm.module.client.workflow.domain.PaymentInfo;
import com.deppon.erp.payment.DepClaimsBill;
import com.deppon.erp.payment.DepClaimsBillService;
import com.deppon.foss.crm.ClaimsPayBillGenerateRequest;
import com.deppon.foss.crm.ClaimsPayBillGenerateResponse;
import com.deppon.foss.crm.CustomerService;
import com.deppon.foss.crm.ESBHeader;

public class PaymentApplyOperateImpl implements IPaymentApplyOperate {
	private DepClaimsBillService depClaimsBillService;
	// FOSS提供给CRM服务
	private CustomerService customerService;
	
	/**
	 * @作者：罗典
	 * @时间：2012-6-27
	 * @描述：提交FOSS付款申请
	 * @参数：
	 */
	@Override
	public boolean claimsApbill(ClaimsPayBillGenerateRequest request) throws CrmBusinessException{
		NullOrEmptyValidator.checkNull(request,"ClaimsPayBillGenerateRequest");
		NullOrEmptyValidator.checkNull(request.getBillNo(),"request.getBillNo()");
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setVersion("0.1");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setEsbServiceCode(Constant.CLAIMSAPBILL);
		esbHeader.setBusinessId(request.getBillNo());
		esbHeader.setSourceSystem("CRM");
		esbHeader.setExchangePattern(1);
		esbHeader.setMessageFormat("SOAP");
		Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
		ClaimsPayBillGenerateResponse response = null;
		try{
			response = customerService.claimsPayBillGenerate(holder, request);
			// 0 失败 1成功
			if(response.getResult()==0){
				throw new CrmBusinessException("1002",response.getReason());
			}
		return true;
		}
		catch(CrmBusinessException e){
			throw e;
		}
		catch(Exception e){
			throw new CrmBusinessException("1002", e.getMessage());
		}
	}
	
	/**
	 * @作者：罗典
	 * @时间：2012-6-27
	 * @描述：提交ERP付款申请
	 * @参数：paymentinfos 付款主要信息，deptChargeInfos 部门费用信息
	 */
	@Override
	public String submitPaymentInfo(List<String> paymentinfos,
			List<String> deptChargeInfos) throws CrmBusinessException {
		String msg = "";
		try{
			msg = depClaimsBillService.insertDepClaimsBill(paymentinfos, deptChargeInfos);
		}catch(Exception e){
			throw new CrmBusinessException("1005",paymentinfos.toString()+deptChargeInfos.toString()+e);
		}
		return msg;
	}
	
	/**
	 * @作者：罗典
	 * @时间：2012-8-30
	 * @描述：根据时间查询付款申请信息
	 * @参数：申请时间段，起始时间，结束时间
	 * */
	@Override
	public List<DepClaimsBill> queryDepClaimsByApproveDate(Date beginDate,
			Date endDate) throws CrmBusinessException {
		List<DepClaimsBill> billList = new ArrayList<DepClaimsBill>();
		try{
			billList = depClaimsBillService.getDepClaimsByApproveDate(beginDate, endDate);
		}catch(Exception e){
			throw new CrmBusinessException("1005",e);
		}
		return billList;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-8-30
	 * @描述：根据申请付款理赔单集合查询相应付款单状态
	 * @参数：
	 * */
	@Override
	public List<DepClaimsBill> queryDepClaimsByNumList(
			List<String> recompenseNumList) throws CrmBusinessException {
		List<DepClaimsBill> billList = new ArrayList<DepClaimsBill>();
		if(recompenseNumList==null||recompenseNumList.size()==0){
			return billList;
		}
		try{
			billList = depClaimsBillService.getDepClaimsByNumber(recompenseNumList);
		}catch(Exception e){
			throw new CrmBusinessException("1005",e);
		}
		return billList;
	}
	
	public DepClaimsBillService getDepClaimsBillService() {
		return depClaimsBillService;
	}

	public void setDepClaimsBillService(DepClaimsBillService depClaimsBillService) {
		this.depClaimsBillService = depClaimsBillService;
	}
	@Override
	public String paymentApply(PaymentInfo paymentInfo)
			throws CrmBusinessException {
		throw new CrmBusinessException("1006");
	}

	@Override
	public String paymentOnlineApply(PaymentInfo paymentInfo)
			throws CrmBusinessException {
		throw new CrmBusinessException("1006");
	}

	@Override
	public AccountInfo queryAccount(String jobNumber)
			throws CrmBusinessException {
		throw new CrmBusinessException("1006");
		
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	
	/*@Override
	 * public String paymentApply(PaymentInfo paymentInfo)
			throws CrmBusinessException {
		
		Check.notNull(paymentInfo, String.format(
				"%s arguments paymentInfo can not be null",
				PaymentApplyOperateImpl.class.getName() + ".paymentApply"));
		try {
			login();

			String temp = marshallerCommonPaymentInfo(paymentInfo);
			if (log.isDebugEnabled()) {
				log.debug("PaymentApplyOperateImpl.paymentApply request is :"
						+ temp);
			}
			temp = fin2CrmService.callCreateCommonPaymentWorkFlow(temp);
			return returnWorkFlowNumber(temp);

		} catch (WSInvokeException e) {
			e.printStackTrace();
			throw new CrmBusinessException(
					ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new CrmBusinessException(
					ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e);
		}
	}

	@Override
	public String paymentOnlineApply(PaymentInfo paymentInfo)
			throws CrmBusinessException {
		Check.notNull(paymentInfo, String
				.format("%s arguments paymentInfo can not be null",
						PaymentApplyOperateImpl.class.getName()
								+ ".paymentOnlineApply"));
		try {
			login();
			String temp = marshallerOnlineRecompensePaymentInfo(paymentInfo);
			if (log.isDebugEnabled()) {
				log.debug("PaymentApplyOperateImpl.paymentApply request is :"
						+ temp);
			}
			temp = fin2CrmService.callCreateOnLinePaymentWorkFlow(temp);
			return returnWorkFlowNumber(temp);
		} catch (WSInvokeException e) {
			e.printStackTrace();
			throw new CrmBusinessException(
					ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new CrmBusinessException(
					ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e);
		}
	}

	private String returnWorkFlowNumber(String result) throws CrmBusinessException{
		if (log.isInfoEnabled()) {
			log.info("PaymentApplyOperateImpl.paymentApply request is :"+result);
		}
		if (result==null || "".equals(result)) {
			log.info("PaymentApplyOperateImpl.paymentApply request is :"+result);
			return null;
		}
		String[] st = result.split(":!:");
		if ("2".equals(st[0])) {
			return st[1];
		}else{
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, st[1]);
		}
	}

	private void login() throws CrmBusinessException, RemoteException {
		WSContext context = easLoginService.login("user", "deppon70", "eas",
				"2011", "L2", 2);
		if (context == null) {
			throw new CrmBusinessException(
					ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,
					"can not login to eos when invoke easLoginService.login(\"user\", \"deppon70\", \"eas\", \"2011\",\"L2\", 2)");
		}
		if (log.isDebugEnabled()) {
			log.debug(context.getUserName() + ":" + context.getPassword());
		}
	}

	@Override
	public AccountInfo queryAccount(String jobNumber)
			throws CrmBusinessException {

		return null;
	}

	private String marshallerCommonPaymentInfo(PaymentInfo paymentInfo) {
		if (paymentInfo == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(paymentInfo.getApplyPersonNumber()).append(":!:")
				.append(paymentInfo.getPayee()).append(":!:")
				.append(paymentInfo.getPayeeMobilePhone()).append(":!:")
				.append(paymentInfo.getProvince()).append(":!:")
				.append(paymentInfo.getCity()).append(":!:")
				.append(paymentInfo.getBank()).append(":!:")
				.append(paymentInfo.getSubbranch()).append(":!:")
				.append(paymentInfo.getAccountNumber()).append(":!:")
				.append(paymentInfo.getAmountMoney()).append(":!:")
				.append(paymentInfo.getPayWay()).append(":!:");
		if (paymentInfo.getWaybillNumber() != null
				&& !"".equals(paymentInfo.getWaybillNumber())) {
			sb.append(paymentInfo.getWaybillNumber());
		} else {
			sb.append(paymentInfo.getErrorNumber());
		}

		return sb.toString();
	}

	private String marshallerOnlineRecompensePaymentInfo(PaymentInfo paymentInfo) {
		if (paymentInfo == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(paymentInfo.getApplyPersonNumber()).append(":!:")
				.append(paymentInfo.getPayee()).append(":!:")
				.append(paymentInfo.getPayeeMobilePhone()).append(":!:")
				.append(paymentInfo.getProvince()).append(":!:")
				.append(paymentInfo.getCity()).append(":!:")
				.append(paymentInfo.getBank()).append(":!:")
				.append(paymentInfo.getSubbranch()).append(":!:")
				.append(paymentInfo.getAccountNumber()).append(":!:")
				.append(paymentInfo.getAmountMoney()).append(":!:")
				.append(paymentInfo.getPayWay()).append(":!:");
		if (paymentInfo.getWaybillNumber() != null
				&& !"".equals(paymentInfo.getWaybillNumber())) {
			sb.append(paymentInfo.getWaybillNumber()).append(":!:");
		} else {
			sb.append(paymentInfo.getErrorNumber()).append(":!:");
		}

		sb.append(paymentInfo.getPartA())
				.append(":!:")
				.append(paymentInfo.getPartB())
				.append(":!:")
				.append(DataFormatUtil.formatDate(
						paymentInfo.getShipmentsDate(), "yyyy-MM-dd"))
				.append(":!:")
				.append(paymentInfo.getStartStation())
				.append(":!:")
				.append(paymentInfo.getDestination())
				.append(":!:")
				.append(paymentInfo.getWaybillNumber())
				.append(":!:")
				.append(paymentInfo.getRecompenseMoney())
				.append(":!:")
				.append(paymentInfo.getRecompenseMoneyText())
				.append(":!:")
				.append(paymentInfo.getPartAsign())
				.append(":!:")
				.append(DataFormatUtil.formatDate(
						paymentInfo.getPartAsignDate(), "yyyy-MM-dd"))
				.append(":!:")
				.append(paymentInfo.getPartBAsign())
				.append(":!:")
				.append(DataFormatUtil.formatDate(
						paymentInfo.getPartBAsignDate(), "yyyy-MM-dd"))
				.append(":!:").append(paymentInfo.getIdentityCard());

		return sb.toString();

	}

	public WSFinanceForCrmFacadeSrvProxy getFin2CrmService() {
		return fin2CrmService;
	}

	public void setFin2CrmService(WSFinanceForCrmFacadeSrvProxy fin2CrmService) {
		this.fin2CrmService = fin2CrmService;
	}

	public EASLoginProxy getEasLoginService() {
		return easLoginService;
	}

	public void setEasLoginService(EASLoginProxy easLoginService) {
		this.easLoginService = easLoginService;
	}*/

}
