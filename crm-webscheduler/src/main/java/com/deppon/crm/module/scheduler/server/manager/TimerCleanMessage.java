package com.deppon.crm.module.scheduler.server.manager;

import org.apache.log4j.Logger;

import com.deppon.crm.module.common.server.manager.IMessageManager;
import com.deppon.crm.module.common.server.util.Constant;

public class TimerCleanMessage {
	// 订单service
	private static Logger logger = Logger.getLogger(TimerCleanMessage.class);

	private IMessageManager messageManager;

	public IMessageManager getMessageManager() {
		return messageManager;
	}

	public void setMessageManager(IMessageManager messageManager) {
		this.messageManager = messageManager;
	}

	public void cleanSuperMessageTimer() {
		System.out.println("Clean Super Message !!!!");
		messageManager.deleteMessageByInvalid(Constant.TASK_TYPE_SUPER_MESSAGE);
	}

}
