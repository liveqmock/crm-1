package com.deppon.crm.module.client.esb.trans;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

import com.deppon.crm.module.client.common.util.ClientContext;
import com.deppon.crm.module.client.log.ExceptionHandle;
import com.deppon.crm.module.client.log.ExceptionHandleThread;

/**
 * @作者：罗典
 * @描述：SOAP消息拦截器，添加与ESB交互的附加信息
 * @时间：2012-11-6
 * */
public class SoapMessageOutInterceptor extends AbstractSoapInterceptor {
	private ExceptionHandle exceptionHandle;
	/**
	 * @作者：罗典
	 * @描述：SOAP消息的拦截级别，此级别拦截消息发出层异常
	 * @时间：2012-11-6
	 * */
	public SoapMessageOutInterceptor() {
		super(Phase.PRE_STREAM_ENDING);
	}
 
	/**
	 * @作者：罗典
	 * @描述：SOAP消息的拦截，可对其进行统一处理(暂不做处理)
	 * @时间：2012-11-6
	 * */
	@Override
	public void handleMessage(SoapMessage message) throws Fault {
	}

	/**
	 * @作者：罗典
	 * @描述：SOAP消息的拦截，可对其进行统一处理(暂不做处理)
	 * @时间：2012-11-6
	 * */
	@Override
	public void handleFault(SoapMessage message) {
		ExceptionHandleThread thread = new ExceptionHandleThread(exceptionHandle,message);
		ClientContext.getPool().execute(thread);
	}

	public void setExceptionHandle(ExceptionHandle exceptionHandle) {
		this.exceptionHandle = exceptionHandle;
	}

}
