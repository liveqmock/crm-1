package com.deppon.crm.module.client.esb.trans;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * @作者：罗典
 * @时间：2012-11-2
 * @描述：ESB响应消息发送
 * */
public class CrmToEsbResponseSender {
	private JmsTemplate template;
	// MQ队列名
	private String qName;
	Log logger = LogFactory.getLog(CrmToEsbResponseSender.class);

	public  void send(final String msg, final TextMessage textMessage) {
		template.send(qName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				textMessage.clearBody();
				textMessage.setText(msg);
				Message message = (Message)textMessage;
				return message;
			}
		});
	}

	public JmsTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JmsTemplate template) {
		this.template = template;
	}

	public String getqName() {
		return qName;
	}

	public void setqName(String qName) {
		this.qName = qName;
	}
}
