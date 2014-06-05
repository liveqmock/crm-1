package com.deppon.crm.module.interfaces.common;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

import com.deppon.crm.module.client.esb.trans.EsbUtil;

public class SoapMessageInInterceptor extends AbstractSoapInterceptor {
	private  static SAAJInInterceptor saa = new SAAJInInterceptor();
	public SoapMessageInInterceptor() {
		super(Phase.INVOKE);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		 SOAPMessage mess = message.getContent(SOAPMessage.class);
	        if (mess == null) {
	            saa.handleMessage(message);
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
	        EsbUtil.processServerInHeader(head);
	}
}
