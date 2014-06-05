package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.coupon.server.manager.ICouponForSchedual;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

public class UpdateCouponStatusJob extends GridJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		ICouponForSchedual couponForSchedualImpl = getBean(
				"couponForSchedualImpl", ICouponForSchedual.class);
		Log logger = LogFactory.getLog(UpdateCouponStatusJob.class);
		System.out.println("updateCouponStatesByOverdue start");
		couponForSchedualImpl.updateCouponStatesByOverdue();
		System.out.println("updateCouponStatesByOverdue end");
		logger.info(new Date() + " UpdateCouponStatusJob");	
	}
}
