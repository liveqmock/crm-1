package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.duty.server.manager.IDutyManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**
 * 
 * <p>
 * Description:ActionLog 日志监控<br />
 * </p>
 * 
 * @title ActionLogJob.java
 * @package com.deppon.crm.module.scheduler.server.job
 * @author 华龙
 * @version 0.1 2013-2-20
 */
public class DutyFeedBackOvertimeJob extends GridJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("DutyFeedBackOvertimeJob begin time:" + new Date());
		IDutyManager dutyManager = getBean("dutyManager", IDutyManager.class);
		dutyManager.feedBackExtended();
		System.out.println("DutyFeedBackOvertimeJob end time:" + new Date());
	}
}