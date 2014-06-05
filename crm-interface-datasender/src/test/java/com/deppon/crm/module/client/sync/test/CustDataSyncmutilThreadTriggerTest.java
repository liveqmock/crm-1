package com.deppon.crm.module.client.sync.test;

import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerBean;

/**
 * 启动类
 * @author l
 */
public class CustDataSyncmutilThreadTriggerTest {
	
	private static ApplicationContext factory;
	 
	/**
	 * 启动函数
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {  
		String resource = "com/deppon/crm/module/client/server/META-INF/spring.sync.sender.mutil.thread.xml";
        
		factory = new ClassPathXmlApplicationContext(resource); 

		Thread.sleep(60* 1000);
	}
}
