package com.deppon.crm.module.client.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextHandler implements ApplicationContextAware {

	private static ApplicationContextHandler contextHandler;
	
	private ApplicationContext applicationContext;
	
	public ApplicationContextHandler() {
		contextHandler = this;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public static ApplicationContextHandler createInstance(){
		return contextHandler;
	}
	
	
}
