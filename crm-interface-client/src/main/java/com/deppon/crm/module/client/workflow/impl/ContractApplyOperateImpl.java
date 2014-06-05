package com.deppon.crm.module.client.workflow.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.exception.ErrorMessageCode;
import com.deppon.crm.module.client.common.util.Check;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.crm.module.client.common.util.NullOrEmptyValidator;
import com.deppon.crm.module.client.workflow.IContractApplyOperate;
import com.deppon.crm.module.client.workflow.domain.ContractApplyType;
import com.deppon.crm.module.client.workflow.domain.ContractInfo;
import com.deppon.crm.module.client.workflow.domain.WorkFlowApplyInfo;
import com.deppon.crm.module.client.workflow.domain.WorkFlowState;
import com.deppon.crm.module.client.workflow.domain.WorkFlowStateList;
import com.deppon.esb.ws.ESBService;
import com.deppon.esb.ws.EsbJsonRequest;
import com.deppon.esb.ws.EsbJsonResponse;
import com.deppon.esb.ws.Exception_Exception;
import com.deppon.oa.deleteWorkflow.DeleteWorkFlowWorkFlowException;
import com.deppon.oa.deleteWorkflow.WorkFlowService;
import com.deppon.oa.workflow.GetWorkFlowApproveStateWorkFlowException;
import com.deppon.oa.workflow.QueryWorkFlowStateService;

public class ContractApplyOperateImpl implements IContractApplyOperate {

	private static Log log = LogFactory.getLog(ContractApplyOperateImpl.class);
	private ESBService esbService;
	private QueryWorkFlowStateService stateService;
	private WorkFlowService workFlowservice;
	/**
	 * @作者：罗典
	 * @时间：2013-1-30
	 * @描述：删除OA工作流(暂时只有客户模块合同功能会使用此接口)
	 * @参数：工作流编号，工作流类型
	 * @返回：是否成功
	 * */
	public boolean deleteWorkflow(String workflowNum,String workflowType)throws CrmBusinessException{
		NullOrEmptyValidator.checkEmpty(workflowNum, "workflowNum");
		NullOrEmptyValidator.checkEmpty(workflowType, "workflowType");
		if (workflowType.equals(ContractApplyType.NEW.toString())) {
			workflowType=Constant.CONTRACT_NEW;
		} else if (workflowType.equals(ContractApplyType.UPDATE.toString())) {
			workflowType=Constant.CONTRACT_UPDATE;
		} else if (workflowType.equals(ContractApplyType.CANCEL.toString())) {
			workflowType=Constant.CONTRACT_CANCEL;
		}else if(workflowType.equals(ContractApplyType.ADD_BELONGDEPT.toString())){
			workflowType=Constant.CONTRACT_ADD_BELONGDEPT;
		}else if(workflowType.equals(ContractApplyType.CONVERT_BELONGDEPT.toString())){
			workflowType=Constant.CONTRACT_CONVERT_BELONGDEPT;
		}
		try {
			return workFlowservice.deleteWorkFlow(workflowType,workflowNum);
		} catch (DeleteWorkFlowWorkFlowException e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e.getMessage());
		}
	}
	
	@Override
	public List<WorkFlowState> queryWorkFlowApproveState(Date beginDate,
			Date endDate, String workflowType) throws CrmBusinessException {
		String workFlowInfo = "";
		WorkFlowStateList stateList = new WorkFlowStateList();
		log.info("beginDate: "+beginDate+" endDate"+endDate+" workflowType: "+workflowType);
		try {
			workFlowInfo = stateService.getWorkFlowApproveState(beginDate,
					endDate, workflowType);
			stateList = JsonMapperUtil.readValue(workFlowInfo,
					WorkFlowStateList.class);
		} catch (GetWorkFlowApproveStateWorkFlowException e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e.getMessage());
		}
		return stateList.getWorkFlowStateList();
	}

	@Override
	public String contractApply(ContractInfo contractInfo,
			ContractApplyType applyType) throws CrmBusinessException {
		NullOrEmptyValidator.checkNull(contractInfo,"contractInfo");
		EsbJsonRequest ejr = new EsbJsonRequest();
		ejr.setServiceCode(Constant.ESB2OA_WORKFLOWAPPLY);
		WorkFlowApplyInfo wf = new WorkFlowApplyInfo();
		if (applyType == ContractApplyType.NEW) {
			contractInfo.setApplyType(Constant.CONTRACT_NEW_CONTENT);
			wf.setBizType(Constant.CONTRACT_NEW);
		} else if (applyType == ContractApplyType.UPDATE) {
			contractInfo.setApplyType(Constant.CONTRACT_UPDATE_CONTENT);
			wf.setBizType(Constant.CONTRACT_UPDATE);
		} else if (applyType == ContractApplyType.CANCEL) {
			contractInfo.setApplyType(Constant.CONTRACT_CANCEL_CONTENT);
			wf.setBizType(Constant.CONTRACT_CANCEL);
		}else if(applyType == ContractApplyType.ADD_BELONGDEPT){
			contractInfo.setApplyType(Constant.CONTRACT_ADD_BELONGDEPT_CONTENT);
			wf.setBizType(Constant.CONTRACT_ADD_BELONGDEPT);
		}else if(applyType == ContractApplyType.CONVERT_BELONGDEPT){
			contractInfo.setApplyType(Constant.CONTRACT_CONVERT_BELONGDEPT_CONTENT);
			wf.setBizType(Constant.CONTRACT_CONVERT_BELONGDEPT);
		}else if (applyType == ContractApplyType.EX_NEW) {
			contractInfo.setApplyType(Constant.CONTRACT_NEW_CONTENT);
			wf.setBizType(Constant.EX_CONTRACT_NEW);
		} else if (applyType == ContractApplyType.EX_UPDATE) {
			contractInfo.setApplyType(Constant.CONTRACT_UPDATE_CONTENT);
			wf.setBizType(Constant.EX_CONTRACT_UPDATE);
		} else if (applyType == ContractApplyType.EX_CANCEL) {
			contractInfo.setApplyType(Constant.CONTRACT_CANCEL_CONTENT);
			wf.setBizType(Constant.EX_CONTRACT_CANCEL);
		}else if(applyType == ContractApplyType.EX_ADD_BELONGDEPT){
			contractInfo.setApplyType(Constant.CONTRACT_ADD_BELONGDEPT_CONTENT);
			wf.setBizType(Constant.EX_CONTRACT_ADD_BELONGDEPT);
		}else if(applyType == ContractApplyType.EX_CONVERT_BELONGDEPT){
			contractInfo.setApplyType(Constant.CONTRACT_CONVERT_BELONGDEPT_CONTENT);
			wf.setBizType(Constant.EX_CONTRACT_CONVERT_BELONGDEPT);
		}
		log.info(JsonMapperUtil.writeValue(contractInfo));
		ejr.setSystemName(Constant.SYSTEM_NAME);
		wf.setBizInfo(JsonMapperUtil.writeValue(contractInfo));
		ejr.setBody(JsonMapperUtil.writeValue(wf));

		try {
			System.out.println(JsonMapperUtil.writeValue(ejr));
			EsbJsonResponse response = esbService.process(ejr);
			if ("SUCCESS".equalsIgnoreCase(response.getStatus())) {
				return response.getResponse();
			} else {
				throw new CrmBusinessException(
						ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,response.getMessage());
			}

		} catch (Exception_Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException(
					ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e, e
							.getFaultInfo().getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e.getMessage());
		}
	}

	// 测试用实现类
	public ESBService getEsbService() {
		return esbService;
	}

	public void setEsbService(ESBService esbService) {
		this.esbService = esbService;
	}

	public QueryWorkFlowStateService getStateService() {
		return stateService;
	}

	public void setStateService(QueryWorkFlowStateService stateService) {
		this.stateService = stateService;
	}

	public WorkFlowService getWorkFlowservice() {
		return workFlowservice;
	}

	public void setWorkFlowservice(WorkFlowService workFlowservice) {
		this.workFlowservice = workFlowservice;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.workflow.IContractApplyOperate#contractApply(com.deppon.crm.module.client.workflow.domain.ContractInfo, com.deppon.crm.module.client.workflow.domain.ContractApplyType, java.lang.String)
	 */
	@Override
	public String contractApply(ContractInfo contractInfo,
			ContractApplyType applyType, String bizCode)
			throws CrmBusinessException {
		//bps实现
		return null;
	}

}
