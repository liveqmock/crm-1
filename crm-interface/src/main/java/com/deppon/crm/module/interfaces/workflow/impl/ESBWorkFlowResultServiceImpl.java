package com.deppon.crm.module.interfaces.workflow.impl;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.exception.ErrorMessageCode;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.crm.module.client.sync.impl.CustomerInfoLog;
import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.crm.module.interfaces.common.util.IntefacesTool;
import com.deppon.crm.module.interfaces.workflow.IESBWorkFlowResultService;
import com.deppon.crm.module.interfaces.workflow.domain.WorkflowApplyResultInfo;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.foss.framework.exception.GeneralException;

@WebService(endpointInterface = "com.deppon.crm.module.interfaces.workflow.IESBWorkFlowResultService")
public class ESBWorkFlowResultServiceImpl implements IESBWorkFlowResultService {

	// 合同模块
	IContractManager contractManager;
	// 理赔模块
	RecompenseManager recompenseManager;
	// 员工模块
	IUserService userService;

	/**
	 * @作者：罗典
	 * @时间：2012-4-25
	 * @描述：合同新增数据返回
	 * */
	@Override
	@Transactional(rollbackFor={CrmBusinessException.class})
	public boolean returnContractApplyResult(WorkflowApplyResultInfo resultInfo)
			throws CrmBusinessException {
		// 登录用户名不能为空
		IntefacesTool.validateStringNull(resultInfo.getExminePerson(), "0019", resultInfo);
		// 获取用户信息
		User user = new User();
		user.setLoginName(resultInfo.getExminePerson());
		List<User> userList = userService.queryAll(user);// .findByLoginName(resultInfo.getExminePerson());
		if (userList.size() != 1) {
			throw new CrmBusinessException("0026");
		}
		user = userList.get(0);
		// 用户名
		String userName = user.getEmpCode().getEmpName();
		// 工作流编号
		String workflowNum = resultInfo.getWorkflowNumber();
		// 审批时间
		Date approveDate = resultInfo.getExamineDate();
		CustomerInfoLog.init();
		try{
			boolean result = contractManager.contractApprove(workflowNum,
					resultInfo.isExmineResult(), userName, approveDate);
			CustomerInfoLog.commit();
			return result;
		}catch (Exception e) {
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_DYNAMIC,
					JsonMapperUtil.writeValue(resultInfo)+e);
		}
	}

	@Override
	public boolean returnNormalRecompenseResult(
			WorkflowApplyResultInfo resultInfo) throws CrmBusinessException {
		// 登录用户名不能为空
		if (resultInfo.getExminePerson() == null
				|| resultInfo.getExminePerson().equals("")) {
			throw new CrmBusinessException("0019");
		}
		// 获取用户信息
		User user = userService.findByLoginName(resultInfo.getExminePerson());
		if (user == null) {
			throw new CrmBusinessException("0026");
		}
		user = userService.getUserRoleFunDeptById(user.getId());
		// 工作流编号
		String workflowNum = resultInfo.getWorkflowNumber();
		// 失败原因
		//String reason = resultInfo.getExminePerson();
		String reason = resultInfo.getExmaineSuggestion();
		// 审批时间
		Date approveDate = resultInfo.getExamineDate();
		try{
			// 审批成功
			if (resultInfo.isExmineResult()) {
				recompenseManager.oaNormalApprove(workflowNum, user, reason,
						approveDate);
			}
			// 审批失败
			else {
				recompenseManager.oaNormalRefuse(workflowNum, user, reason,
						approveDate);
			}
		}catch(GeneralException e){
			String il8nMsg = IntefacesTool.getMessage("recompense", e.getErrorCode());
			throw new CrmBusinessException("1005",JsonMapperUtil.writeValue(resultInfo)+il8nMsg);
		}catch(Exception e){
			throw new CrmBusinessException("1005",JsonMapperUtil.writeValue(resultInfo)+e);
		}
		return true;
	}

	@Override
	public boolean returnMuchRecompenseResult(WorkflowApplyResultInfo resultInfo)
			throws CrmBusinessException {
		// 登录用户名不能为空
		if (resultInfo.getExminePerson() == null
				|| resultInfo.getExminePerson().equals("")) {
			throw new CrmBusinessException("0019");
		}
		// 获取用户信息
		User user = userService.findByLoginName(resultInfo.getExminePerson());
		if (user == null) {
			throw new CrmBusinessException("0026");
		}
		user = userService.getUserRoleFunDeptById(user.getId());
		// 工作流编号
		String workflowNum = resultInfo.getWorkflowNumber();
		// 失败原因
		String reason = resultInfo.getExmaineSuggestion();
		// 审批时间
		Date approveDate = resultInfo.getExamineDate();
		try{
			// 审批成功
			if (resultInfo.isExmineResult()) {
				recompenseManager.oaOverpayApprove(workflowNum, user, reason,
						approveDate);
			}
			// 审批失败
			else {
				recompenseManager.oaOverpayRefuse(workflowNum, user, reason,
						approveDate);
			}
		}catch(GeneralException e){
			String il8nMsg = IntefacesTool.getMessage("recompense", e.getErrorCode());
			throw new CrmBusinessException("1005",JsonMapperUtil.writeValue(resultInfo)+il8nMsg);
		}catch(Exception e){
			throw new CrmBusinessException("1005",JsonMapperUtil.writeValue(resultInfo)+e);
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

	public IContractManager getContractManager() {
		return contractManager;
	}

	public void setContractManager(IContractManager contractManager) {
		this.contractManager = contractManager;
	}

}
