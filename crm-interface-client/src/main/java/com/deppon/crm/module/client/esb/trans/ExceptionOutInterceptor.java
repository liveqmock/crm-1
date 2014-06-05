package com.deppon.crm.module.client.esb.trans;

import java.util.List;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

public class ExceptionOutInterceptor extends AbstractSoapInterceptor {

	public ExceptionOutInterceptor() {
		super(Phase.SEND);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		List<Header> headerList = message.getHeaders();
		headerList.add(EsbUtil.createClientOutHeader());
	}

}
