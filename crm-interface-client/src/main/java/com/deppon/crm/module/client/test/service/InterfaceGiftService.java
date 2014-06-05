package com.deppon.crm.module.client.test.service;

import java.util.Date;

import javax.annotation.Resource;

import com.deppon.interfaces.gift.CrmBusinessException;
import com.deppon.interfaces.gift.IGiftApplyResultService;
import com.deppon.interfaces.gift.WorkflowApplyResultInfo;

public class InterfaceGiftService {
	@Resource
	IGiftApplyResultService giftApplyResultService;

	public void returnGiftApplyResult(String workflowId) {
		WorkflowApplyResultInfo info = new WorkflowApplyResultInfo();
		info.setWorkflowNumber(workflowId);
		info.setExmineResult(true);
		info.setExminePerson("075586");
		info.setExamineDate(new Date());
		try {
			giftApplyResultService.returnGiftApplyResult(info);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}

	public IGiftApplyResultService getGiftApplyResultService() {
		return giftApplyResultService;
	}

	public void setGiftApplyResultService(
			IGiftApplyResultService giftApplyResultService) {
		this.giftApplyResultService = giftApplyResultService;
	}
	
	
}
