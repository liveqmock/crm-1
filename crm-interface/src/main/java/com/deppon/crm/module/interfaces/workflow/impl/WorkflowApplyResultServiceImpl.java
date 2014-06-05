package com.deppon.crm.module.interfaces.workflow.impl;

import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.crm.module.backfreight.server.manager.IBackFreightManager;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.crm.module.interfaces.common.util.IntefacesTool;
import com.deppon.crm.module.interfaces.workflow.IWorkflowApplyResultService;
import com.deppon.crm.module.interfaces.workflow.domain.WorkflowApplyResultInfo;
import com.deppon.crm.module.servicerecovery.server.manager.IServiceRecoveryManager;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * @作者：罗典
 * @描述：工作流审批结果返回
 * @时间：2012-11-12
 * */
@WebService(endpointInterface = "com.deppon.crm.module.interfaces.workflow.IWorkflowApplyResultService")
public class WorkflowApplyResultServiceImpl implements
		IWorkflowApplyResultService {
	private static Log log = LogFactory.getLog(WorkflowApplyResultServiceImpl.class);
	private IBackFreightManager backFreightManager;
	private IServiceRecoveryManager serviceRecoveryManager;

	/**
	 * @作者：罗典
	 * @描述：退运费审批工作流结果返回
	 * @时间：2012-11-18
	 * @参数：工作流审批参数
	 * @返回：是否成功
	 * */
	@Override
	public boolean returnBackFreightResult(String info)throws CrmBusinessException {
		try {
			log.info(info);
			WorkflowApplyResultInfo resultInfo = (WorkflowApplyResultInfo) JsonMapperUtil
					.readValue(info, WorkflowApplyResultInfo.class);
			backFreightManager.returnBackFreightStatus(
					resultInfo.getWorkflowNumber(),
					resultInfo.getExminePerson(), resultInfo.isExmineResult(),
					resultInfo.getExamineDate(),
					resultInfo.getExmaineSuggestion());
		} catch (GeneralException e) {
			String errorMsg = IntefacesTool.getMessage("backfreight",
					e.getErrorCode());
			throw new CrmBusinessException("1005",errorMsg);
		}catch(Exception e){
			e.printStackTrace();
			throw new CrmBusinessException("1005",e.getMessage());
		}
		return true;
	}

	/**
	 * @作者：罗典
	 * @描述：服务补救审批工作流结果返回
	 * @时间：2012-11-18
	 * @参数：工作流审批参数
	 * @返回：是否成功
	 * */
	@Override
	public boolean returnServiceRecoveryResult(String info)throws CrmBusinessException {
		try {
			WorkflowApplyResultInfo resultInfo = (WorkflowApplyResultInfo) JsonMapperUtil
					.readValue(info, WorkflowApplyResultInfo.class);
			return serviceRecoveryManager.returnServiceRecoveryStatus(
					resultInfo.getWorkflowNumber(),
					resultInfo.getExminePerson(), resultInfo.isExmineResult(),
					resultInfo.getExamineDate(),
					resultInfo.getExmaineSuggestion());
		} catch (GeneralException e) {
			String errorMsg = IntefacesTool.getMessage("serviceRecovery",
					e.getErrorCode());
			throw new CrmBusinessException("1005",errorMsg);
		}catch (Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException("1005",e.getMessage());
		}
	}

	public IBackFreightManager getBackFreightManager() {
		return backFreightManager;
	}

	public void setBackFreightManager(IBackFreightManager backFreightManager) {
		this.backFreightManager = backFreightManager;
	}

	public IServiceRecoveryManager getServiceRecoveryManager() {
		return serviceRecoveryManager;
	}

	public void setServiceRecoveryManager(
			IServiceRecoveryManager serviceRecoveryManager) {
		this.serviceRecoveryManager = serviceRecoveryManager;
	}

}
