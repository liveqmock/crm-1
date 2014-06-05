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
 * Description:系统更新客户行业信息 每月一次<br />
 * </p>
 * @title GoodTradeMappingMonthJob.java
 * @package com.deppon.crm.module.scheduler.server.job 
 * @author 106138
 * @version 0.1 2014年4月10日
 */
public class GoodTradeMappingMonthJob extends GridJob {
	private IGoodTradeMappingManager goodTradeMappingManager;
	@Override
	protected void doExecute(JobExecutionContext context) throws JobExecutionException {
		Log logger = LogFactory.getLog(GoodTradeMappingMonthJob.class);
		logger.info("GoodTradeMappingMonthJob开始时间：" + new Date());
		goodTradeMappingManager=getBean("goodTradeMappingManager", GoodTradeMappingManager.class);
		goodTradeMappingManager.updateCustomerTradeMonth();
		logger.info("CustCreditBadToDoJob_结束时间：" + new Date());

	}
}
