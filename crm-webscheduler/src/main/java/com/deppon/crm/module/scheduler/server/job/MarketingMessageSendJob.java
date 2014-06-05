package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.marketing.server.manager.IMessageSendManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

public class MarketingMessageSendJob extends GridJob{

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		IMessageSendManager messageSendManager = getBean("messageSendManager",
				IMessageSendManager.class);
		Log logger = LogFactory.getLog(MarketingMessageSendJob.class);
		logger.info(new Date() + " ----MarketingMessageSendJob----start");	
		messageSendManager.sendMessageFromExcel();
		logger.info(new Date() + " ----MarketingMessageSendJob----end");	
	}

}
