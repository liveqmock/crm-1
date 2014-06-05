package com.deppon.crm.module.client.sync.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHelper {

	private static ApplicationContext context;

	public static ApplicationContext getApplicationContext() {
		if (context == null) {
			synchronized (SpringHelper.class) {
				if (context == null) {
					context = new ClassPathXmlApplicationContext(
							new String[] {
									"com/deppon/crm/module/client/server/META-INF/spring.sync.sender.mutil.thread.xml"});
				}
			}
		}
		return context;
	}
}