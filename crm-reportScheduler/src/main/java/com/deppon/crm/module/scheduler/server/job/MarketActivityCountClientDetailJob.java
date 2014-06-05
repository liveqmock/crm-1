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
 * Description: 根据客户群状态查询待下发的客户群并生成相应的回访计划、日程以及明细报表<br/>
 * 
 * 每日凌晨创建客户群日程
 * 生成客户群明细报表T_MARK_CUSTCONDITION
 * 
 * </p>
 * @title MarketActivityCreateClientPlanJob
 * @package com.deppon.crm.module.scheduler.server.job
 * @author ZhouYuan
 * @version 1.0
 * @date 2014-04-10
 *
 */
public class MarketActivityCountClientDetailJob extends GridJob implements StatefulJob{
	private static IMarketActivityManager marketActivityManager;
	
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		marketActivityManager = getBean("marketActivityManager", MarketActivityManager.class);
		Log logger = LogFactory.getLog(MarketActivityCountClientDetailJob.class);
		logger.info("MarketActivityCountClientDetailJob开始时间：" + new Date());
		
		marketActivityManager.countClientBaseDetail();
		
		logger.info("MarketActivityCountClientDetailJob结束时间：" + new Date());
	}
}
