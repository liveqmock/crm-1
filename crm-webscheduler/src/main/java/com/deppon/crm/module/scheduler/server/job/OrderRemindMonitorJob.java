package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.order.server.manager.IOrderManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**
 * 
 * <p>
 * Description:定时器声明-订单提醒任务<br />
 * </p>
 * 
 * @title OrderRemindMonitorJob.java
 * @package com.deppon.crm.module.scheduler.server.job
 * @author 侯斌飞
 * @version 0.1 2013-2-19
 */
public class OrderRemindMonitorJob extends GridJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("begin time:" + new Date()
				+ ":orderRemindMonitorJob");
		IOrderManager orderManager = getBean("orderManager",
				IOrderManager.class);
		orderManager.remindOrderMonitor();
//		sendErpMessageTimer();
		System.out.println("end time:" + new Date() + ":orderRemindMonitorJob");
	}

/*	public void sendErpMessageTimer() {
		System.out.println("Send Erp Message !!!!");
		IMessageManager messageManager = getBean("messageManager",
				IMessageManager.class);
		OrderOperateImpl orderOperate = getBean("orderOperate",
				OrderOperateImpl.class);
		List<Message> msgList = messageManager.getMessageForErp();
		List<ErpRemind> erpList = new ArrayList<ErpRemind>();
		for (Message message : msgList) {
			ErpRemind erpRemind = new ErpRemind();
			erpRemind.setFmsgNum(message.getTaskcount());
			erpRemind.setFstandardcode(message.getDeptStandardCode());
			if (message.getTasktype().equals(Constant.TASK_TYPE_ORDER_UNASSIGN)) {
				erpRemind.setFsubModule(erpRemind.ORDER_ALLOT_MODULE);
				erpRemind.setFsubModuleNum(erpRemind.ORDER_ALLOT);
				erpRemind.setFdescription(erpRemind.ORDER_ALLOT_DESC);
			} else if (message.getTasktype().equals(
					Constant.TASK_TYPE_ORDER_UNACCEPT)) {
				erpRemind.setFsubModule(erpRemind.ORDER_ACCEPT_MODULE);
				erpRemind.setFsubModuleNum(erpRemind.ORDER_ACCEPT);
				erpRemind.setFdescription(erpRemind.ORDER_ACCEPT_DESC);
			}
			erpRemind.setFlastupdateDate(new Date());
			erpList.add(erpRemind);
			// System.out.println(message.getDeptStandardCode() + "||"
			// + message.getTasktype() + "||" + message.getTaskcount());
		}
		try {
			orderOperate.sendErpRemind(erpList);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}*/
}
