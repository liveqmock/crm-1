package com.deppon.crm.module.scheduler.server.job;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


import com.deppon.crm.module.gis.server.service.IGoogleRegion2BaiduService;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;


/**
 * 
 * <p>
 * Description:google地图区域切换到百度<br />
 * </p>
 * 
 * @title GoogleRegion2BaiduJob.java
 * @package com.deppon.crm.module.scheduler.server.job
 * @author roy
 * @version 0.1 2013-5-16
 */
public class GoogleRegion2BaiduJob extends GridJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		 IGoogleRegion2BaiduService googleRegion2BaiduService = getBean(
				"googleRegion2BaiduService", IGoogleRegion2BaiduService.class);
		System.out.println("begin time:" + new Date() + ":GoogleRegion2BaiduJob");
		googleRegion2BaiduService.executeGoogleRegion2Baidu();
		System.out.println("end time:" + new Date() + ":GoogleRegion2BaiduJob");
	}
}