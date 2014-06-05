package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.coupon.server.manager.ICouponForSchedual;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

public class SendCouponMsgJob extends GridJob {
	/**
	 * <p>
	 * Description: 向短信网关发送优惠券信息<br/>
	 * </p> 
	 * @author ZhouYuan
	 * @version 0.1 2012-11-27
	 * @return boolean
	 */
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		ICouponForSchedual couponForSchedualImpl = getBean(
				"couponForSchedualImpl", ICouponForSchedual.class);
		Log logger = LogFactory.getLog(SendCouponMsgJob.class);
		System.out.println("sendCouponMsg start");
		couponForSchedualImpl.sendCouponMsg();
		System.out.println("sendCouponMsg end");
		logger.info(new Date() + " SendCouponMsgJob");	
	}
}
