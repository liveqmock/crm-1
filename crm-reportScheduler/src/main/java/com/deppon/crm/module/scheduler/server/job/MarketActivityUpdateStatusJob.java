package com.deppon.crm.module.scheduler.server.job;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.deppon.crm.module.marketing.server.manager.IMarketActivityManager;
import com.deppon.crm.module.marketing.server.manager.impl.MarketActivityManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**
 * <p>
 * Description: 修改市场推广活动及关联客户群状态<br/>
 * 
 * 每五分钟执行一次
 * 
 * </p>
 * @title MarketActivityUpdateStatusJob
 * @package com.deppon.crm.module.scheduler.server.job
 * @author ZhouYuan
 * @version 1.0
 * @date 2014-04-10
 *
 */
public class MarketActivityUpdateStatusJob extends GridJob implements StatefulJob{
	private static IMarketActivityManager marketActivityManager;
	
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		marketActivityManager = getBean("marketActivityManager", MarketActivityManager.class);
		Log logger = LogFactory.getLog(MarketActivityUpdateStatusJob.class);
		logger.info("MarketActivityUpdateStatusJob开始时间：" + new Date());
		marketActivityManager.updateCompleteOrDeleteUselessActivities();
		logger.info("MarketActivityUpdateStatusJob结束时间：" + new Date());
	}
}
