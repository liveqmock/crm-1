package com.deppon.crm.module.interfaces.common;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import com.deppon.crm.module.client.esb.trans.CrmToEsbStatusSender;

/**
 * @作者：罗典
 * @描述：ESB状态发送线程
 * @时间：2012-12-14
 * */
public class ESBStatusSendThread extends Thread {
	// 状态发送工具
	private CrmToEsbStatusSender statusSender;
	private TextMessage textMessage;
	private String status;

	public ESBStatusSendThread(CrmToEsbStatusSender sender,
			TextMessage textMessage, String status) {
		this.statusSender = sender;
		this.textMessage = textMessage;
		this.status = status;
	}

	@Override
	public void run() {
		try {
			if (statusSender == null || textMessage == null) {
				throw new RuntimeException(
						"Interface ERROR: CrmToEsbStatusSender or TextMessage is null");
			}
			statusSender.send(status, textMessage);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
