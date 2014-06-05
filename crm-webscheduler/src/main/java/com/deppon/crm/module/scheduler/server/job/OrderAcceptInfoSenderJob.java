package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.order.server.manager.IOrderManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**
 * 
 * <p>
 * Description:定时器声明-订单锁屏信息发送<br />
 * </p>
 * 
 * @title OrderAcceptInfoSenderJob.java
 * @package com.deppon.crm.module.scheduler.server.job
 * @author huangzm
 */
public class OrderAcceptInfoSenderJob extends GridJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("begin time:" + new Date()
				+ ":OrderAcceptInfoSenderJob");
		IOrderManager orderManager = getBean("orderManager",
				IOrderManager.class);
		orderManager.sendFossOrderAcceptInfo();
		System.out.println("end time:" + new Date()
				+ ":OrderAcceptInfoSenderJob");
	}

}
