package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.coupon.server.manager.ICouponForSchedual;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

public class CreateCouponHandJob  extends GridJob {
	/**
	 * <p>
	 * Description:手动发券创建优惠券后台任务<br/>
	 * </p> 
	 * @author ZhouYuan
	 * @version 0.1 2012-12-05
	 * @return boolean
	 */
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		ICouponForSchedual couponForSchedualImpl = getBean(
				"couponForSchedualImpl", ICouponForSchedual.class);
		System.out.println("createCouponHandBackground start");
			couponForSchedualImpl.createCouponHandBackground();
		System.out.println("createCouponHandBackground end");
		Log logger = LogFactory.getLog(CreateCouponHandJob.class);
		logger.info(new Date() + " CreateCouponHandJob");	
	}
}
