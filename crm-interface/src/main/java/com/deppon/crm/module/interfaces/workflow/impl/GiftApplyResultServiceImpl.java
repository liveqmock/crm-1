package com.deppon.crm.module.interfaces.workflow.impl;

import java.util.List;

import javax.jws.WebService;

import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.crm.module.client.common.util.XmlMapperUtil;
import com.deppon.crm.module.customer.server.manager.IIntegralManager;
import com.deppon.crm.module.customer.shared.exception.IntegException;
import com.deppon.crm.module.interfaces.common.util.IntefacesTool;
import com.deppon.crm.module.interfaces.workflow.IGiftApplyResultService;
import com.deppon.crm.module.interfaces.workflow.domain.BillHead;
import com.deppon.crm.module.interfaces.workflow.domain.OtherBill;
import com.deppon.crm.module.interfaces.workflow.domain.WorkflowApplyResultInfo;

@WebService(endpointInterface = "com.deppon.crm.module.interfaces.workflow.IGiftApplyResultService")
public class GiftApplyResultServiceImpl implements IGiftApplyResultService {
	// 员工模块
	private IUserService userService;
	// 礼品模块
	private IIntegralManager integralManager;

	/**
	 * @作者：罗典
	 * @描述：礼品申请结果返回
	 * @时间：2012-4-24
	 * */
	@Override
	public boolean returnGiftResult(String xmlInfo) throws CrmBusinessException {
		System.out.println(xmlInfo);
		OtherBill bill = (OtherBill)XmlMapperUtil.readValue(xmlInfo, OtherBill.class);
		BillHead head = bill.getBillHead();
		try {
			integralManager.approvalIntegralConvertGift(
					head.isStatus(), head.getId());
		}catch(IntegException e){
			String errorInfo = IntefacesTool.getMessage("customer", e.getErrorCode());
			throw new CrmBusinessException("1005",errorInfo+head.getId());
		} 
		catch (Exception e) {
			throw new CrmBusinessException("1005",
					xmlInfo + e);
		}
		return true;
	}

	/**
	 * @作者：罗典
	 * @描述：礼品申请结果返回
	 * @时间：2012-4-24
	 * */
	@Override
	public boolean returnGiftApplyResult(WorkflowApplyResultInfo resultInfo)
			throws CrmBusinessException {
		// 登录用户名不能为空
		IntefacesTool.validateStringNull(resultInfo.getExminePerson(), "0019",
				resultInfo);
		// 获取用户信息
		User user = new User();
		user.setLoginName(resultInfo.getExminePerson());
		List<User> userList = userService.queryAll(user);// .findByLoginName(resultInfo.getExminePerson());
		if (userList.size() != 1) {
			throw new CrmBusinessException("0026", resultInfo.getExminePerson());
		}
		user = userList.get(0);
		// 用户名
		// String userName = user.getEmpCode().getEmpName();
		// 工作流编号
		String workflowNum = resultInfo.getWorkflowNumber();
		// 审批时间
		// Date approveDate = resultInfo.getExamineDate();
		try {
			integralManager.approvalIntegralConvertGift(
					resultInfo.isExmineResult(), workflowNum);
		} catch (Exception e) {
			throw new CrmBusinessException("1005",
					JsonMapperUtil.writeValue(resultInfo) + e);
		}
		return true;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IIntegralManager getIntegralManager() {
		return integralManager;
	}

	public void setIntegralManager(IIntegralManager integralManager) {
		this.integralManager = integralManager;
	}

}
