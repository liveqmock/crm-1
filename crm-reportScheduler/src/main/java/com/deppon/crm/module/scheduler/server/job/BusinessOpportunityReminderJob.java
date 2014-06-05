package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.marketing.server.manager.IBusinessOpportunityManager;
import com.deppon.crm.module.marketing.server.manager.impl.BusinessOpportunityManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

public class BusinessOpportunityReminderJob extends GridJob {
	private IBusinessOpportunityManager businessOpportunityManager;

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		businessOpportunityManager = getBean("businessOpportunityManager",
				BusinessOpportunityManager.class);
		Log logger = LogFactory.getLog(BusinessOpportunityReminderJob.class);
		logger.info("BusinessOpportunityReminderJob_开始时间：" + new Date());
		businessOpportunityManager.execBusinessOpportunityReminder();
		logger.info("BusinessOpportunityReminderJob_结束时间：" + new Date());
	}
}
