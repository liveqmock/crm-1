package com.deppon.crm.module.client.esb.trans;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class CrmToEsbExceptionSender {
	private JmsTemplate template;
	private String qName;
	Log logger = LogFactory.getLog(CrmToEsbStatusSender.class);

	public void send(final String msg, final Message myMessage) {
		template.send(qName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = (TextMessage) myMessage;
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
