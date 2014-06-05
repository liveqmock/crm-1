package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.foss.framework.server.components.jobgrid.GridJob;

public class NextJob extends GridJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {

		System.out.println(new Date() + ":I'm NextJob");
		// getProperty(context, "nexttrigger");
	}

}
