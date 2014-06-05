package com.deppon.crm.module.client.common;
 
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.sf.cglib.proxy.Enhancer;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.jfree.util.Log;

import com.deppon.crm.module.client.common.domain.InterfaceConfig;
import com.deppon.crm.module.client.log.InterfaceInterceptor;
import com.deppon.foss.framework.server.components.logger.LogBuffer;

public class ServiceAccessProxyFactory {

	private static ServiceAccessProxyFactory serviceAccessProxyFactory=null;
	private InterfaceProvider interfaceProvider = null;
	private InterfaceSerialize interfaceSerialize = null;
	private LogBuffer logBuffer;
//	private Map<String, Object> proxyService;
	
	private ServiceAccessProxyFactory(){
//		proxyService = new HashMap<String, Object>();
//		interfaceProvider = new FileInterfaceProvier(Constant.INTERFACE_DEFAULT_FILE_PATH);
//		interfaceSerialize = new FileInterfaceSerialize(Constant.INTERFACE_DEFAULT_FILE_PATH);
	}
	
	public static ServiceAccessProxyFactory createInstance(){
		if (serviceAccessProxyFactory==null) {
			synchronized(ServiceAccessProxyFactory.class){
				if (serviceAccessProxyFactory==null) {
					serviceAccessProxyFactory = new ServiceAccessProxyFactory();
				}
			}
		}
		return serviceAccessProxyFactory;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T createProxyService(Class<T> cls){
		if (cls==null) {
			throw new NullPointerException("ServiceAccessProxyFactory.createProxyService arguments is null");
		}
		/*if (proxyService.containsKey(cls.getName())) {
			return (T) proxyService.get(cls.getName());
		}*/
		
		InterfaceConfig interfaceDetail = interfaceProvider.load().get(cls.getName());
		if (interfaceDetail==null) {
			Log.error(String.format("ServiceAccessProxyFactory create object error ," +
					"because can not find InterfaceConfig about %s ,so this class will to be record no log",cls.getName()));
			try {
				return cls.newInstance();
			} catch (InstantiationException e) {
				
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(cls);
		if (logBuffer==null) {
			synchronized (ServiceAccessProxyFactory.class) {
				if (logBuffer==null) {
					logBuffer = new LogBuffer();
				}
			}
		}
		enhancer.setCallback(new InterfaceInterceptor(interfaceDetail,logBuffer));
		Object obj = enhancer.create();
//		proxyService.put(cls.getName(), obj);
		return (T) obj;
	}
	
	public InterfaceProvider getInterfaceProvider() {
		return interfaceProvider;
	}
	
	public void setInterfaceProvider(InterfaceProvider interfaceProvider) {
		this.interfaceProvider = interfaceProvider;
	}
	
	public InterfaceSerialize getInterfaceSerialize() {
		return interfaceSerialize;
	}
	
	public void setInterfaceSerialize(InterfaceSerialize interfaceSerialize) {
		this.interfaceSerialize = interfaceSerialize;
	}
	
	public void setLogBuffer(LogBuffer logBuffer) {
		this.logBuffer = logBuffer;
	}
	
	public static void main(String[] args) {
		Properties properties = new Properties();
		System.out.println(properties.get("adsf"));
		Map<String, String> map = new HashMap<String, String>();
		System.out.println(map.get("asdf"));
		System.out.println(properties.getClass().getName());
	}
	
	void timeout(){
		
		Client client = ClientProxy.getClient(new Object());
		client.getInInterceptors().add(new LoggingInInterceptor());
		client.getOutInterceptors().add(new LoggingOutInterceptor());
		
		HTTPConduit httpConduit = (HTTPConduit) client.getConduit();

		HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
		httpClientPolicy.setReceiveTimeout(3000);
		httpClientPolicy.setAllowChunking(false);
		httpClientPolicy.setConnectionTimeout(3000);
		
		httpConduit.setClient(httpClientPolicy);
	}
	
}
