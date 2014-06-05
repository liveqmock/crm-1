package com.deppon.crm.module.interfaces.common;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

import com.deppon.crm.module.client.esb.trans.EsbUtil;

public class SoapMessageOutInterceptor extends AbstractSoapInterceptor {
	public SoapMessageOutInterceptor() {
		super(Phase.WRITE);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		Header header = EsbUtil.createServerOutHeader();
		message.getHeaders().add(header);
	}
}
