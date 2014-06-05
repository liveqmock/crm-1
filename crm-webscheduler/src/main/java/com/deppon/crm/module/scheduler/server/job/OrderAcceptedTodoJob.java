package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.deppon.crm.module.order.server.manager.IOrderManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**
 * 
 * <p>
 * Description:定时器声明-订单已受理提醒任务<br />
 * </p>
 * 
 * @title OrderAcceptedTodoJob.java
 * @package com.deppon.crm.module.scheduler.server.job
 * @author 黄展明
 * @version 0.1 2013-10-18
 */
public class OrderAcceptedTodoJob extends GridJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		System.out
				.println("begin time:" + new Date() + ":OrderAcceptedTodoJob");
		IOrderManager orderManager = getBean("orderManager",
				IOrderManager.class);
		orderManager.generateOrderAccepted();
		System.out.println("end time:" + new Date() + ":OrderAcceptedTodoJob");
	}

}
