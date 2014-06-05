package com.deppon.crm.module.client.esb.trans;

import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

public class SoapMessageInInterceptor extends AbstractSoapInterceptor {
	public SoapMessageInInterceptor() {
		super(Phase.INVOKE);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("]]]]]]]]]]]]");
	}

	/**
	 * 从soap 消息中获取esbHeader
	 * 
	 * @param message
	 */
	protected void inMessageProcessor(SoapMessage message) {
		SOAPMessage mess = message.getContent(SOAPMessage.class);
		if (mess == null) {
			// saa.handleMessage(message);
			mess = message.getContent(SOAPMessage.class);
		}
		SOAPHeader head = null;
		try {
			head = mess.getSOAPHeader();
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		if (head == null) {
			return;
		}
		// 处理接受到的soapHeader
		// WSHeaderHelper.processServerInHeader(head);
	}
}
