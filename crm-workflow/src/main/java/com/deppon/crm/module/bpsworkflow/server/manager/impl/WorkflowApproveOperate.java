package com.deppon.crm.module.bpsworkflow.server.manager.impl;
 
import java.util.Date;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.bps.server.manager.IBpsWorkflowManager;
import com.deppon.crm.module.bps.shared.domain.WorkflowApprove;
import com.deppon.crm.module.customer.bpsworkflow.ContractApproveOperate;
import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.crm.module.custrepeat.server.manager.IRepeatedCustManager;
import com.deppon.crm.module.custrepeat.shared.domain.RepetitiveCustWorkFlowInfo;
import com.deppon.crm.module.keycustomer.server.manager.IKeyCustomerManager;
import com.deppon.crm.module.marketing.server.manager.IMarketActivityManager;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.servicerecovery.server.manager.IServiceRecoveryManager;
import com.deppon.crm.module.workflow.server.manager.impl.NormalClaimManagerImpl;
import com.deppon.crm.module.bpsworkflow.server.util.BpsConstant;
import com.deppon.foss.framework.server.context.UserContext;

public class WorkflowApproveOperate {
	// 大客戶manager
	private IKeyCustomerManager keyCustomerManager;
	// 合同操作
	private ContractApproveOperate contractApproveOperate;
	//常规理赔
	private NormalClaimManagerImpl normalClaimManager;
	//bps
	private IBpsWorkflowManager bpsWorkflowManager;
	//疑似重复manager
	private IRepeatedCustManager repeatedCustManager;
	//区域营销manager
	private IMarketActivityManager marketActivityManager;
	//合同manager
	private IContractManager contractManager;
	//服务补救manager
	private IServiceRecoveryManager serviceRecoveryManager;
	//理赔manager
	private RecompenseManager recompenseManager;
	/**
	 * 
	* @Title: workflowApprove 
	* @Description: 调用工作流审批的方法 
	* @author LiuY 
	* @param workflowApprove  审批对象
	* @return boolean
	* @throws
	 */
	public boolean workflowApprove(WorkflowApprove workflowApprove) {
		//转换审批意见为boolean
		boolean status = "0".equals(workflowApprove.getApproveOperateType()) ? true
				: false;
		//获得当前登录人
		User user = (User) UserContext.getCurrentUser();
		//如果是合同工作流，调用合同操作类
		if (workflowApprove.getProcessDefName().equals(
				// 零担合同申请流程名称
				BpsConstant.LTT_NEW_PROCESSNAME)
				// 快递合同申请流程名称
				|| workflowApprove.getProcessDefName().equals(BpsConstant.EX_NEW_PROCESSNAME)
				// 零担合同修改流程名称
				|| workflowApprove.getProcessDefName().equals(BpsConstant.LTT_UPDATE_PROCESSNAME)
				// 快递合同修改流程名称
				|| workflowApprove.getProcessDefName().equals(BpsConstant.EX_UPDATE_PROCESSNAME)
				// 月结合同终止申请流程名称
				|| workflowApprove.getProcessDefName().equals(BpsConstant.CANCEL_PROCESSNAME)) {
			//调用合同工作流审批方法
			contractApproveOperate.contractApprove(workflowApprove.getBusino(),
					workflowApprove.getWorkItemId(), workflowApprove.getProcessInstId(), status, 
					user.getEmpCode().getEmpName(), workflowApprove.getApproveOpinion(),new Date());
			return true;
			//疑似客户
		} else if (workflowApprove.getProcessDefName().equals(BpsConstant.CUSTREPEAT_PROCESSNAME)) {
			//调用Bps审批方法
			Map<String, Object> map = getBpsWorkflowManager().workflowApprove(
					workflowApprove.getWorkItemId(),
					workflowApprove.getProcessInstId(), status ? "0" : "1",
					workflowApprove.getApproveOpinion(), null);
			// 获得审批结果是否成功
			Boolean isSuccess = (Boolean) map.get("sucess");
			if (!isSuccess) {
				//未审批完成则跳出
				return isSuccess;
			}
			//审批是否结束
			if ((Boolean) map.get("isEnd")) {
				// true
				repeatedCustManager.workflowApprove(workflowApprove.getBusino(),
						status, user.getEmpCode().getId(), new Date(),
						workflowApprove.getApproveOpinion());
			}
			//是否是不同意
			if ((Boolean) map.get("isDisAgree")) {
				//调用疑似重复审批方法
				repeatedCustManager.workflowApprove(workflowApprove.getBusino(),
						status, user.getEmpCode().getId(), new Date(),
						workflowApprove.getApproveOpinion());
			}
			return true;
		//大客户	
		} else if (workflowApprove.getProcessDefName().equals(BpsConstant.KEYCUST_PROCESSNAME)) {
			//调用Bps审批方法
			Map<String, Object> map = getBpsWorkflowManager().workflowApprove(
					workflowApprove.getWorkItemId(),
					workflowApprove.getProcessInstId(), status ? "0" : "1",
					workflowApprove.getApproveOpinion(), null);
			// 获得审批结果是否成功
			Boolean isSuccess = (Boolean) map.get("sucess");
			if (!isSuccess) {
				//未审批完成则跳出
				return isSuccess;
			}
			// 如果是是结束
			if ((Boolean) map.get("isEnd")) {
				// true
				keyCustomerManager.workflowApprove(workflowApprove.getBusino(),
						status, user.getEmpCode().getId(), new Date(),
						workflowApprove.getApproveOpinion());
			}
			// 如果不同意
			if ((Boolean) map.get("isDisAgree")) {
				//调用大客户审批操作方法
				keyCustomerManager.workflowApprove(workflowApprove.getBusino(),
						status, user.getEmpCode().getId(), new Date(),
						workflowApprove.getApproveOpinion());
			}
			return true;
		//客户服务相关
		} else if (workflowApprove.getProcessDefName().equals(
				//服务补救
				BpsConstant.SERVICE_PROCESS_DEFINITION_NAME)
				|| workflowApprove.getProcessDefName().equals(
						//多赔
						BpsConstant.OVERPAY_PROCESS_DEFINITION_NAME)
				|| workflowApprove.getProcessDefName().equals(
						//常规理赔
						BpsConstant.RECOMPENSE_PROCESS_DEFINITION_NAME)) {
			//调用客户服务模块相关工作流
			normalClaimManager.workflowApprove(workflowApprove.getWorkItemId(),
					workflowApprove.getProcessInstId(),
					workflowApprove.getApproveOperateType(),
					workflowApprove.getApproveOpinion(), null,
					workflowApprove.getProcessDefName());
			return true;
			//区域营销活动
		}else if(workflowApprove.getProcessDefName().equals(BpsConstant.MARKETING_PROCESSNAME)){
			//调用bps审批方法
			Map<String, Object> map = getBpsWorkflowManager().workflowApprove(
					workflowApprove.getWorkItemId(),
					workflowApprove.getProcessInstId(), status ? "0" : "1",
					workflowApprove.getApproveOpinion(), null);
			//获取是否成功
			Boolean isSuccess = (Boolean) map.get("sucess");
			if (!isSuccess) {
				//未审批完成则跳出
				return isSuccess;
			}
			//是否结束
			if ((Boolean) map.get("isEnd")) {
				//调用区域营销活动审批方法
				marketActivityManager.workflowApprove(workflowApprove.getBusino(),
						status, user.getEmpCode().getId(), new Date(),
						workflowApprove.getApproveOpinion(), user);
			}
			//是否是不同意
			if ((Boolean) map.get("isDisAgree")) {
				//调用区域营销活动审批方法
				marketActivityManager.workflowApprove(workflowApprove.getBusino(),
						status, user.getEmpCode().getId(), new Date(),
						workflowApprove.getApproveOpinion(), user);
			}
			return true;
		}
		return false;
	}
	/**
	 * 
	* @Title: oneKeyForApprove 
	* @Description: 一键审批
	* @author LiuY 
	* @param workflowApprove 审批对象
	* @return boolean
	* @throws
	 */
	public boolean oneKeyForApprove(WorkflowApprove workflowApprove){
		if(null == workflowApprove ){
			return false;
		}
		//获取流程定义名称
		String  processDefName =workflowApprove.getProcessDefName();
		//获取工作流号
		String businessNo = workflowApprove.getBusino();
		//获取审批决策
		boolean status = "0".equals(workflowApprove.getApproveOperateType()) ? true : false;
		//流程号
		long processInstId = workflowApprove.getProcessInstId();
		//获得当前登录人
		User user = (User) UserContext.getCurrentUser();
		if (processDefName.equals(
				// 零担合同申请流程名称
				BpsConstant.LTT_NEW_PROCESSNAME)
				// 快递合同申请流程名称
				|| processDefName.equals(BpsConstant.EX_NEW_PROCESSNAME)
				// 零担合同修改流程名称
				|| processDefName.equals(BpsConstant.LTT_UPDATE_PROCESSNAME)
				// 快递合同修改流程名称
				|| processDefName.equals(BpsConstant.EX_UPDATE_PROCESSNAME)
				// 月结合同终止申请流程名称
				|| processDefName.equals(BpsConstant.CANCEL_PROCESSNAME)) {
			//调用合同审批方法
			contractManager.contractApprove(businessNo, status, user.getEmpCode().getId(), new Date());
			return true;
		//疑似重复客户
		}else if (processDefName.equals(
				BpsConstant.CUSTREPEAT_PROCESSNAME)) {
			if(status){
				//如果审批同意，调用查询疑似重复方法。判断是否已经同意，若是，直接返回
				RepetitiveCustWorkFlowInfo info = repeatedCustManager.findRepetitiveCustWorkFlowInfoByWorkNo(businessNo, processDefName);
				if("2".equals(info.getRepetitveCustWorkFlowStatus())){
					return false;
				}
			}
			//调用疑似重复审批方法
			repeatedCustManager.workflowApprove(businessNo,status, user.getEmpCode().getId(), new Date(),null);
			return true;
		//大客户
		} else if (processDefName.equals(BpsConstant.KEYCUST_PROCESSNAME)) {
			keyCustomerManager.workflowApprove(businessNo,
					status, user.getEmpCode().getId(), new Date(),
					"");
			return true;
		//服务补救
		} else if (processDefName.equals(BpsConstant.SERVICE_PROCESS_DEFINITION_NAME)) {
			//调用服务补救审批方法
			serviceRecoveryManager.returnServiceRecoveryStatus(processInstId+"", user.getEmpCode().getEmpCode(), status, new Date(), "");
			return true;
		//多赔
		}else if(processDefName.equals(BpsConstant.OVERPAY_PROCESS_DEFINITION_NAME)){
			if(status){
				//多赔同意
				recompenseManager.oaOverpayApprove(processInstId+"", user, "", new Date());
			}else{
				//多赔拒绝
				recompenseManager.oaOverpayRefuse(processInstId+"", user, "", new Date());
			}
			return true;
		//常规理赔
		}else if(processDefName.equals(BpsConstant.RECOMPENSE_PROCESS_DEFINITION_NAME)){
			//调用常规理赔审批方法
			normalClaimManager.oaNormalApprove(processInstId+"", user, "", new Date());
			return true;
		//区域营销活动
		}else if(processDefName.equals(BpsConstant.MARKETING_PROCESSNAME)){
			//调用区域营销审批方法
			marketActivityManager.workflowApprove(businessNo,status, user.getEmpCode().getId(), new Date(),"", user);
			return true;
		}
		return false;
	}
	/**
	 * @return the repeatedCustManager
	 */
	public IRepeatedCustManager getRepeatedCustManager() {
		return repeatedCustManager;
	}
	/**
	 * @param repeatedCustManager the repeatedCustManager to set
	 */
	public void setRepeatedCustManager(IRepeatedCustManager repeatedCustManager) {
		this.repeatedCustManager = repeatedCustManager;
	}
	/**
	 * @return the marketActivityManager
	 */
	public IMarketActivityManager getMarketActivityManager() {
		return marketActivityManager;
	}
	/**
	 * @param marketActivityManager the marketActivityManager to set
	 */
	public void setMarketActivityManager(IMarketActivityManager marketActivityManager) {
		this.marketActivityManager = marketActivityManager;
	}
	/**
	 * @return the keyCustomerManager
	 */
	public IKeyCustomerManager getKeyCustomerManager() {
		return keyCustomerManager;
	}
	/**
	 * @param keyCustomerManager the keyCustomerManager to set
	 */
	public void setKeyCustomerManager(IKeyCustomerManager keyCustomerManager) {
		this.keyCustomerManager = keyCustomerManager;
	}
	/**
	 * @return the contractApproveOperate
	 */
	public ContractApproveOperate getContractApproveOperate() {
		return contractApproveOperate;
	}
	/**
	 * @param contractApproveOperate the contractApproveOperate to set
	 */
	public void setContractApproveOperate(
			ContractApproveOperate contractApproveOperate) {
		this.contractApproveOperate = contractApproveOperate;
	}
	/**
	 * @return the normalClaimManager
	 */
	public NormalClaimManagerImpl getNormalClaimManager() {
		return normalClaimManager;
	}
	/**
	 * @param normalClaimManager the normalClaimManager to set
	 */
	public void setNormalClaimManager(NormalClaimManagerImpl normalClaimManager) {
		this.normalClaimManager = normalClaimManager;
	}
	/**
	 * @return the bpsWorkflowManager
	 */
	public IBpsWorkflowManager getBpsWorkflowManager() {
		return bpsWorkflowManager;
	}
	/**
	 * @param bpsWorkflowManager the bpsWorkflowManager to set
	 */
	public void setBpsWorkflowManager(IBpsWorkflowManager bpsWorkflowManager) {
		this.bpsWorkflowManager = bpsWorkflowManager;
	}
	/**
	 * @return the contractManager
	 */
	public IContractManager getContractManager() {
		return contractManager;
	}
	/**
	 * @param contractManager the contractManager to set
	 */
	public void setContractManager(IContractManager contractManager) {
		this.contractManager = contractManager;
	}
	/**
	 * @return the serviceRecoveryManager
	 */
	public IServiceRecoveryManager getServiceRecoveryManager() {
		return serviceRecoveryManager;
	}
	/**
	 * @param serviceRecoveryManager the serviceRecoveryManager to set
	 */
	public void setServiceRecoveryManager(
			IServiceRecoveryManager serviceRecoveryManager) {
		this.serviceRecoveryManager = serviceRecoveryManager;
	}
	/**
	 * @return the recompenseManager
	 */
	public RecompenseManager getRecompenseManager() {
		return recompenseManager;
	}
	/**
	 * @param recompenseManager the recompenseManager to set
	 */
	public void setRecompenseManager(RecompenseManager recompenseManager) {
		this.recompenseManager = recompenseManager;
	}
}
