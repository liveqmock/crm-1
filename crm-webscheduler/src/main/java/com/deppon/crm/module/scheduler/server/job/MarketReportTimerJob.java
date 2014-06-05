package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.complaint.server.manager.IComplaintManager;
import com.deppon.crm.module.marketing.server.manager.IMarketAssessJobManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

public class MarketReportTimerJob extends GridJob{
	/**
	 * <p>
	 * Description:营销效果评估报表-定时器<br />
	 * </p>
	 * 
	 * @package com.deppon.crm.module.scheduler.server.manager
	 * @author 钟琼
	 * @version 0.1 2013-01-25
	 */
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		IMarketAssessJobManager marketAssessJobManager = getBean("marketAssessJobManagerImpl",
				IMarketAssessJobManager.class);
		Log logger = LogFactory.getLog(MarketReportTimerJob.class);
		System.out.println("marketAssessJobManager---->START"+new Date());
		marketAssessJobManager.autoCreateMarketReport();
		System.out.println("marketAssessJobManager---->END"+new Date());
		logger.info(new Date() + " MarketReportTimerJob");	
	}

}
