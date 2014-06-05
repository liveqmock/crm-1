package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.complaint.server.manager.IComplaintManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

public class ComplaintOATimerJob extends GridJob{
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		IComplaintManager complaintManager = getBean("complaintManager",
				IComplaintManager.class);
		System.out.println("complaint_OA_msg_timer .........." + new Date());
		complaintManager.processSendComplaintInfo();
		Log logger = LogFactory.getLog(ComplaintOATimerJob.class);
		logger.info(new Date() + " ComplaintOATimerJob");	
	}
}