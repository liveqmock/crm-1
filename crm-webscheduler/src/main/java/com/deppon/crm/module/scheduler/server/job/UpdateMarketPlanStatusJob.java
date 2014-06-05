package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.coupon.server.manager.ICouponForSchedual;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

public class UpdateMarketPlanStatusJob extends GridJob {
	/**
	 * <p>
	 * Description: 每天检查更新已启用的营销计划 状态是否过期，<br/>
	 * 	过期的把状态置为：已结束<br/>
	 * </p> 
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @return boolean
	 */
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		ICouponForSchedual couponForSchedualImpl = getBean(
				"couponForSchedualImpl", ICouponForSchedual.class);
		Log logger = LogFactory.getLog(UpdateMarketPlanStatusJob.class);
		System.out.println("updateMarketPlanStatesByOverdue start");
		couponForSchedualImpl.updateMarketPlanStatesByOverdue();
		System.out.println("updateMarketPlanStatesByOverdue end");
		logger.info(new Date() + " UpdateMarketPlanStatusJob");	
	}
}
