package com.deppon.crm.module.client.esb.trans;

import java.util.Date;

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
 * @描述：ESB状态发送工具
 * @时间：2012-11-3
 * */
public class CrmToEsbStatusSender {
	private JmsTemplate template;
	private String qName;
	Log logger = LogFactory.getLog(CrmToEsbStatusSender.class);

	public void send(final String status, final TextMessage textMessage) throws JMSException{
		String txt = textMessage.getText();
		logger.info(txt);
		template.send(qName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				textMessage.clearBody();
				textMessage.setText(status+"@"+new Date().getTime());
				Message message = (Message)textMessage;
				return message;
			}
		});
		textMessage.clearBody();
		textMessage.setText(txt);
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
