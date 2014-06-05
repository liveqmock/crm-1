package com.deppon.crm.module.client.workflow.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.ws.Holder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.exception.ErrorMessageCode;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.crm.module.client.workflow.IRecompenseApplyOperate;
import com.deppon.crm.module.client.workflow.domain.BackFreightInfo;
import com.deppon.crm.module.client.workflow.domain.MuchRecompenseInfo;
import com.deppon.crm.module.client.workflow.domain.NormalRecompenseInfo;
import com.deppon.crm.module.client.workflow.domain.ServiceRecoveryInfo;
import com.deppon.crm.module.client.workflow.domain.WorkFlowApplyInfo;
import com.deppon.esb.ws.ESBService;
import com.deppon.esb.ws.EsbJsonRequest;
import com.deppon.esb.ws.EsbJsonResponse;
import com.deppon.esb.ws.Exception_Exception;
import com.deppon.fin.badDebt.IBadDebtInterService;
import com.deppon.foss.crm.CommonException;
import com.deppon.foss.crm.CustomerService;
import com.deppon.foss.crm.ESBHeader;
import com.deppon.foss.crm.ServiceChargeStatusUpdateRequest;
import com.deppon.foss.crm.ServiceChargeStatusUpdateResponse;
import com.deppon.oa.goodDelay.OAErrorForCRMImplService;
import com.deppon.oa.goodDelay.QueryGoodDelaySQLException;
import com.deppon.oa.oaWorkflow.CreateWorkFlowWorkFlowException;
import com.deppon.oa.oaWorkflow.WorkFlowService;

/**
 * 
 * @author davidcun @2012-5-3
 */
public class RecompenseApplyOperateImpl implements IRecompenseApplyOperate {
	private static Log logger = LogFactory
			.getLog(RecompenseApplyOperateImpl.class);
	// ESB服务
	private ESBService esbService;
	// 工作流接口服务
	private WorkFlowService workFlowService;
	// 时效差错查询服务
	private OAErrorForCRMImplService forCRMerrorService;
	// 坏账查询服务
	private IBadDebtInterService badDebtInterService;
	// FOSS提供给CRM服务
	private CustomerService customerService;

	/**
	 * @作者：罗典
	 * @描述：劳务费状态修改
	 * @时间：2012-12-4
	 * @参数：运单号
	 * @返回：
	 * */
	@Override
	public boolean ServiceChargeStatusUpdate(String waybillNum, boolean status)
			throws CrmBusinessException {
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setVersion("0.1");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setEsbServiceCode(Constant.SVCCHARGE_STATUS_UPDATE);
		esbHeader.setBusinessId(waybillNum);
		esbHeader.setBusinessDesc1(Boolean.toString(status));
		esbHeader.setSourceSystem("CRM");
		esbHeader.setExchangePattern(1);
		esbHeader.setMessageFormat("SOAP");
		Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
		ServiceChargeStatusUpdateRequest request = new ServiceChargeStatusUpdateRequest();
		request.setStatus(status);
		request.setWaybillNumber(waybillNum);
		ServiceChargeStatusUpdateResponse response = null;
		try {
			response = customerService.updateServiceChargeStatus(holder,
					request);
		} catch (CommonException e) {
			throw new CrmBusinessException("1005", e.getMessage());
		} catch (Exception e) {
			throw new CrmBusinessException("1005", e.getMessage());
		}
		if (response.isResult()) {
			return true;
		} else {
			throw new CrmBusinessException("1005", response.getReason());
		}
	}

	/**
	 * @作者：罗典
	 * @描述：校验财务是否存在有效坏账信息
	 * @时间：2012-11-19
	 * @参数：运单号
	 * @返回：是否存在有效坏账
	 * */
	@Override
	public boolean checkBadDebt(String waybillNum) throws CrmBusinessException {
		try {
			return badDebtInterService.checkBadDebt(waybillNum);
		} catch (com.deppon.fin.badDebt.Exception_Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException("1002", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException("1002", e.getMessage());
		}
	}

	/**
	 * @作者：罗典
	 * @描述：校验OA是否存在有效时效延误差错
	 * @时间：2012-11-19
	 * @参数：运单号
	 * @返回：是否存在
	 * */
	@Override
	public boolean checkOaGoodDelayError(String waybillNum)
			throws CrmBusinessException {
		try {
			return forCRMerrorService.queryGoodDelay(waybillNum);
		} catch (QueryGoodDelaySQLException e) {
			e.printStackTrace();
			throw new CrmBusinessException("1002", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException("1002", e.getMessage());
		}
	}

	/**
	 * @作者：罗典
	 * @描述：退运费申请接口
	 * @时间：2012-11-19
	 * @参数：退运费工作流申请实体接口
	 * @返回：工作流号
	 * */
	@Override
	public String applyBackFreight(BackFreightInfo backFreightInfo)
			throws CrmBusinessException {
		try {
			String jsonBody = JsonMapperUtil.writeValue(backFreightInfo);
			logger.info(jsonBody);
			return workFlowService.createWorkFlow(jsonBody,
					Constant.BACK_FREIGHT);
		} catch (CreateWorkFlowWorkFlowException e) {
			e.printStackTrace();
			throw new CrmBusinessException("1002", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException("1002", e.getMessage());
		}
	}

	/**
	 * @作者：罗典
	 * @描述：服务补救工作流申请接口
	 * @时间：2012-11-19
	 * @参数：服务补救工作流申请实体接口
	 * @返回：工作流号
	 * */
	@Override
	public String applyServiceRecovery(ServiceRecoveryInfo serviceRecoveryInfo)
			throws CrmBusinessException {
		try {
			String jsonBody = JsonMapperUtil.writeValue(serviceRecoveryInfo);
			logger.info(jsonBody);
			return workFlowService.createWorkFlow(jsonBody,
					Constant.SERVICE_RECOVERY);
		} catch (CreateWorkFlowWorkFlowException e) {
			e.printStackTrace();
			throw new CrmBusinessException("1002", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException("1002", e.getMessage());
		}
	}

	/**
	 * 0007:1
	 */
	@Override
	public String applyNormalRecompense(
			NormalRecompenseInfo normalRecompenseInfo)
			throws CrmBusinessException {
		return esb2oa(normalRecompenseInfo, Constant.RECOMPENSE_NORMAL);
	}

	/**
	 * 0007:21
	 */
	@Override
	public String applyMuchRecompense(MuchRecompenseInfo muchRecompenseInfo)
			throws CrmBusinessException {
		return esb2oa(muchRecompenseInfo, Constant.RECOMPENSE_MUCH);
	}

	private String esb2oa(Object source, String serviceCode)
			throws CrmBusinessException {
		EsbJsonRequest ejr = new EsbJsonRequest();
		WorkFlowApplyInfo info = new WorkFlowApplyInfo();
		ejr.setSystemName(Constant.SYSTEM_NAME);
		ejr.setServiceCode(Constant.ESB2OA_WORKFLOWAPPLY);
		String bizInfo = JsonMapperUtil.writeValue(source);
		logger.info(bizInfo);
		info.setBizInfo(bizInfo);
		info.setBizType(serviceCode);
		ejr.setBody(JsonMapperUtil.writeValue(info));
		try {
			EsbJsonResponse response = esbService.process(ejr);
			if ("SUCCESS".equalsIgnoreCase(response.getStatus())) {
				return response.getResponse();
			} else {
				throw new CrmBusinessException(
						ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,
						JsonMapperUtil.writeValue(ejr) + response.getMessage());
			}

		} catch (Exception_Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException(
					ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e,
					JsonMapperUtil.writeValue(ejr)
							+ e.getFaultInfo().getMessage());
		}
	}

	public static void main(String[] args) {
		NormalRecompenseInfo recompenseInfo = new NormalRecompenseInfo();
		List<NormalRecompenseInfo.IndeptCharges> ins = new ArrayList<NormalRecompenseInfo.IndeptCharges>();

		NormalRecompenseInfo.IndeptCharges inss = new NormalRecompenseInfo.IndeptCharges();

		inss.setCharges(123);

		System.out.println(inss.getCharges());
		recompenseInfo.setIndeptCharges(ins);
	}

	public ESBService getEsbService() {
		return esbService;
	}

	public void setEsbService(ESBService esbService) {
		this.esbService = esbService;
	}

	public WorkFlowService getWorkFlowService() {
		return workFlowService;
	}

	public void setWorkFlowService(WorkFlowService workFlowService) {
		this.workFlowService = workFlowService;
	}

	public OAErrorForCRMImplService getForCRMerrorService() {
		return forCRMerrorService;
	}

	public void setForCRMerrorService(
			OAErrorForCRMImplService forCRMerrorService) {
		this.forCRMerrorService = forCRMerrorService;
	}

	public IBadDebtInterService getBadDebtInterService() {
		return badDebtInterService;
	}

	public void setBadDebtInterService(IBadDebtInterService badDebtInterService) {
		this.badDebtInterService = badDebtInterService;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

}
