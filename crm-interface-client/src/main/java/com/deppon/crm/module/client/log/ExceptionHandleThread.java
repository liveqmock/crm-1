package com.deppon.crm.module.client.log;

import org.apache.cxf.binding.soap.SoapMessage;

/**
 * @作者：罗典
 * @时间：2012-12-5
 * @描述：异常处理线程(主要用于处理接口调用异常，并发邮件)
 * */
public class ExceptionHandleThread extends Thread {
	private ExceptionHandle handle;
	private SoapMessage soapMessage;

	public ExceptionHandleThread(ExceptionHandle handle, SoapMessage message) {
		this.handle = handle;
		this.soapMessage = message;
	}

	@Override
	public void run() {
		if (handle == null || soapMessage == null) {
			throw new RuntimeException(
					"Interface ERROR: InterfaceExceptionHandle or soapMessage is null");
		}
		String address = soapMessage.getExchange().getEndpoint()
				.getEndpointInfo().getAddress();
		Exception ex = soapMessage.getContent(Exception.class);
		handle.handFault(ex.getMessage(), address);
	}
}
