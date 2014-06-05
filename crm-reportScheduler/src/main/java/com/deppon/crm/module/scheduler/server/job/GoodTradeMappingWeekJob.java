package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.customer.server.manager.IGoodTradeMappingManager;
import com.deppon.crm.module.customer.server.manager.impl.GoodTradeMappingManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;
/**
 * 
 * <p>
 * Description:系统更新没有行业信息的客户的行业信息<br />
 * </p>
 * @title GoodTradeMappingWeekJob.java
 * @package com.deppon.crm.module.scheduler.server.job 
 * @author 106138
 * @version 0.1 2014年4月10日
 */
public class GoodTradeMappingWeekJob extends GridJob {
	
		private IGoodTradeMappingManager goodTradeMappingManager;
		@Override
		protected void doExecute(JobExecutionContext context) throws JobExecutionException {
			Log logger = LogFactory.getLog(GoodTradeMappingWeekJob.class);
			logger.info("GoodTradeMappingWeekJob开始时间：" + new Date());
			goodTradeMappingManager=getBean("goodTradeMappingManager", GoodTradeMappingManager.class);
			goodTradeMappingManager.updateCustomerTradeWeek();
			logger.info("GoodTradeMappingWeekJob结束时间：" + new Date());

		}
	

}
