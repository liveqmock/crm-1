package com.deppon.crm.module.client.esb.trans;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.listener.adapter.MessageListenerAdapter;

/**
 * @作者：罗典
 * @描述：FOSS消息监听
 * @时间：2012-11-13
 * */
public class FossToEsbRequestListener extends MessageListenerAdapter {

	/**
	 * @作者：罗典
	 * @描述：重写方法，用于FOSS请求消息处理
	 * @时间：2012-11-22
	 * */
	@Override
	public void onMessage(Message message, Session session) {
		/*try {
			EsbUtil.convertToNewMessage(message, session);
		} catch (JMSException e) {
			e.printStackTrace();
		}*/

	}
}
