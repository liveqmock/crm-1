package com.deppon.crm.module.bpsworkflow.utils;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.bpsworkflow.server.manager.ICRMWorkflowManager;
import com.deppon.crm.module.bpsworkflow.server.manager.impl.CRMWorkflowManager;
import com.deppon.crm.module.bpsworkflow.server.manager.impl.WorkflowApproveOperate;
import com.deppon.foss.framework.server.context.AppContext;

public class TestUtil {

	public static ClassPathXmlApplicationContext factory;
	public static ICRMWorkflowManager crmWorkflowManager;
	public static WorkflowApproveOperate workflowApproveOperate;
	
	static{
		AppContext.initAppContext("application", "", "");
		String[] resource = { "classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml" };
		factory = new ClassPathXmlApplicationContext(resource);
		crmWorkflowManager = (CRMWorkflowManager) factory.getBean("crmWorkflowManager");
		workflowApproveOperate = (WorkflowApproveOperate)factory.getBean("workflowApproveOperate");
	}
}
