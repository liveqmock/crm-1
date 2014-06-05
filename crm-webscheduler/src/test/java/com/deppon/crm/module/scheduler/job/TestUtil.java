package com.deppon.crm.module.scheduler.job;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.foss.framework.server.components.jobgrid.JobGridManager;

public class TestUtil {
	public static ClassPathXmlApplicationContext factory;
	public static JobGridManager jobGridManager;

	static {
		factory = new ClassPathXmlApplicationContext(
				"classpath*:com/deppon/crm/module/scheduler/server/META-INF/spring.xml");
		jobGridManager = (JobGridManager) factory.getBean("jobGridManager");
	}

}
