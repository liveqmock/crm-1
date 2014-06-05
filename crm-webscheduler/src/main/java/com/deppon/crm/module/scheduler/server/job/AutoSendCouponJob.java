package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.coupon.server.manager.ICouponForSchedual;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

public class AutoSendCouponJob extends GridJob {
	/**
	 * <p>
	 * Description: 优惠券自动返券,每天查询前一天符合已启用营销计划的运单,进行优惠券发送<br/>
	 * </p> 
	 * @author ZhouYuan
	 * @version 0.1 2012-11-29
	 * @return boolean
	 */
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		ICouponForSchedual couponForSchedualImpl = getBean(
				"couponForSchedualImpl", ICouponForSchedual.class);
		Log logger = LogFactory.getLog(AutoSendCouponJob.class);
		System.out.println("autoSendCoupon start");
		couponForSchedualImpl.autoSendCoupon(new Date(System.currentTimeMillis()-86400000),new Date());
		System.out.println("autoSendCoupon end");
		logger.info(new Date() + " AutoSendCouponJob");	
	}
}
