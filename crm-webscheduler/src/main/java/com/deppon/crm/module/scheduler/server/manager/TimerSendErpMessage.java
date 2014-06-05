package com.deppon.crm.module.scheduler.server.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.order.domain.ErpRemind;
import com.deppon.crm.module.client.order.impl.OrderOperateImpl;
import com.deppon.crm.module.common.server.manager.IMessageManager;
import com.deppon.crm.module.common.server.util.Constant;
import com.deppon.crm.module.common.shared.domain.Message;

public class TimerSendErpMessage {
	// 订单service
	private static Logger logger = Logger.getLogger(TimerSendErpMessage.class);

	private IMessageManager messageManager;
	private OrderOperateImpl orderOperate;

	public IMessageManager getMessageManager() {
		return messageManager;
	}

	public void setMessageManager(IMessageManager messageManager) {
		this.messageManager = messageManager;
	}

	public OrderOperateImpl getOrderOperate() {
		return orderOperate;
	}

	public void setOrderOperate(OrderOperateImpl orderOperate) {
		this.orderOperate = orderOperate;
	}

	public void sendErpMessageTimer() {
		System.out.println("Send Erp Message !!!!");
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
	}

}
