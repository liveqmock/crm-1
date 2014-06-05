package com.deppon.crm.module.client.test.thread;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.crm.module.client.test.service.InterfaceESBService;
import com.deppon.crm.module.client.test.service.InterfaceGiftService;

public class CrmRunTestThread implements Runnable {

	// 1: returnContractApplyResult，2:returnNormalRecompenseResult
	// 3: returnMuchRecompenseResult 4: returnGiftApplyResult
	private Log log = LogFactory.getLog(CrmRunTestThread.class);
	
	
	private int handleType;
	private String workflowId;
	@Resource
	InterfaceESBService interfaceESBService;
	@Resource
	InterfaceGiftService interfaceGiftService;

	public void run() {
		try {
			Thread.sleep(60000);// 休眠1分钟
			log.info("CrmRunTestThread 调用工作流接口开始，工作流号:"+workflowId+"  类型为:"+handleType);
			try {
				if (handleType == 1) {
					interfaceESBService.returnContractApplyResult(workflowId);
				} else if (handleType == 2) {
					interfaceESBService.returnNormalRecompenseResult(workflowId);
				} else if (handleType == 3) {
					interfaceESBService.returnMuchRecompenseResult(workflowId);
				} else if (handleType == 4) {
					interfaceGiftService.returnGiftApplyResult(workflowId);
				}
				log.info("CrmRunTestThread 调用工作流接口成功，工作流号:"+workflowId+"  类型为:"+handleType);
			} catch (Exception e) {
				log.info("CrmRunTestThread **调用工作流接口失败，工作流号:"+workflowId+"  类型为:"+handleType+"失败原因 "+e.getMessage(),e);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setHandleType(int handleType, String workflowId) {
		this.handleType = handleType;
		this.workflowId = workflowId;
	}

	public InterfaceESBService getInterfaceESBService() {
		return interfaceESBService;
	}

	public void setInterfaceESBService(InterfaceESBService interfaceESBService) {
		this.interfaceESBService = interfaceESBService;
	}

	public InterfaceGiftService getInterfaceGiftService() {
		return interfaceGiftService;
	}

	public void setInterfaceGiftService(InterfaceGiftService interfaceGiftService) {
		this.interfaceGiftService = interfaceGiftService;
	}
	
	
}
