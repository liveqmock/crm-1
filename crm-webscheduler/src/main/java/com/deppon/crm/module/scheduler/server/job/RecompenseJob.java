package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**
 * 
 * <p>
 * Description:理赔定时器<br />
 * </p>
 * @title RecompenseJob.java
 * @package com.deppon.crm.module.scheduler.server.job 
 * @author 侯斌飞
 * @version 0.1 2013-2-20
 */
public class RecompenseJob extends GridJob {
	
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("begin time:"+new Date() + ":RecompenseJob");
		RecompenseManager recompenseManager = getBean("recompenseManager",
				RecompenseManager.class); 
		recompenseManager.generateTodoReminder();
		System.out.println("end time:"+new Date() + ":RecompenseJob");
	}
}
