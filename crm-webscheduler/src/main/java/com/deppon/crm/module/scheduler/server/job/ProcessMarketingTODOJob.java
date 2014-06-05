package com.deppon.crm.module.scheduler.server.job;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.marketing.server.manager.IPlanManager;
import com.deppon.crm.module.marketing.server.manager.IScheduleManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

public class ProcessMarketingTODOJob extends GridJob {
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		IPlanManager planManager = getBean("planManager", IPlanManager.class);
		IScheduleManager scheduleManager = getBean("scheduleManager",
				IScheduleManager.class);
		Logger logger = Logger.getLogger(ProcessMarketingTODOJob.class);
		try {
			// 计划信息统计
			boolean planJob = planManager.processPlanTODO();
			
			// 日程信息统计
			boolean scheJob = scheduleManager.processScheduleTODO();
			if (planJob && scheJob){
				logger.debug("任务正常执行完成");
			}
		} catch (Exception e) {
		}
	}
}
