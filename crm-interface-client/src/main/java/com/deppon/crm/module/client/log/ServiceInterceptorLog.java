package com.deppon.crm.module.client.log;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.message.Message;

public class ServiceInterceptorLog implements Interceptor<Message> {

	@Override
	public void handleMessage(Message message) throws Fault {
		
	}

	@Override
	public void handleFault(Message message) {		
		
	}

}
