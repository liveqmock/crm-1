package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.deppon.crm.module.complaint.server.manager.IComplaintManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

public class ComplaintTimerJob extends GridJob{

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		IComplaintManager complaintManager = getBean("complaintManager",
				IComplaintManager.class);
		System.out.println("TimerComplaint Call.........." + new Date());
		complaintManager.expireComplaint();
		Log logger = LogFactory.getLog(ComplaintTimerJob.class);
		logger.info(new Date() + " ComplaintTimerJob");
	}
}
