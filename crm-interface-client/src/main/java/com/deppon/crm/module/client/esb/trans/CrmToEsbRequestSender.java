package com.deppon.crm.module.client.esb.trans;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.deppon.crm.module.client.esb.domain.ESBClientHeader;

/**
 * @作者：罗典
 * @时间：2012-11-2
 * @描述：ESB请求消息发送
 * */
public class CrmToEsbRequestSender {
	private JmsTemplate template;
	// MQ队列名
	private String qName;
	Log logger = LogFactory.getLog(CrmToEsbRequestSender.class);

	public  void send(final String msg, final ESBClientHeader header) {
		template.send(qName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				Message message = session.createTextMessage(msg);
				EsbUtil.buildRequestJMSProperties(message, header);
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
