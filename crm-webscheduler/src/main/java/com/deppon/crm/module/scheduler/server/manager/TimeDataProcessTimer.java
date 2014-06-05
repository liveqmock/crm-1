package com.deppon.crm.module.scheduler.server.manager;

import java.util.Date;

import org.apache.log4j.Logger;

import com.deppon.crm.module.scheduler.server.service.TimerPotentialCustomerService;

/**
 * 
 * <p>
 * 数据加工定时器<br />
 * </p>
 * @title TimeDataProcessTimer.java
 * @package com.deppon.crm.module.scheduler.server.manager 
 * @author bxj
 * @version 0.2 2012-7-11
 */
public class TimeDataProcessTimer {
	
	private Logger log = Logger.getLogger(TimeDataProcessTimer.class);
	
	private final static String MEMBERFALL = "MEMBERFALL";
	
	private final static String MEMBERUPGRADE = "MEMBERUPGRADE";
	
	private final static String UPGRADELIST = "UPGRADELIST";
	
	private TimerPotentialCustomerService timerPotentialCustomerService;
	
	
	
	public void setTimerPotentialCustomerService(
			TimerPotentialCustomerService timerPotentialCustomerService) {
		this.timerPotentialCustomerService = timerPotentialCustomerService;
	}

	/**
	 * 
	 * <p>
	 * 散客升级数据加工<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-7-11
	 * void
	 */
	public void upgradeListDataProcess(){
		Date time = new Date();
		upgradeListDataProcess(time);
	}

	public void upgradeListDataProcess(Date time) {
		//读取昨天的数据
		time.setDate(time.getDate()-1);
		
		java.sql.Date timer = new java.sql.Date(time.getTime());
		timerPotentialCustomerService.callStoredProcedure(UPGRADELIST,timer);
	}

	/**
	 * 
	 * <p>
	 * 会员降级数据加工定时器<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-7-11
	 * void
	 */
	public void memberfailDataProcess(){
		Date time = new Date();
		memberfailDataProcess(time);
	}

	public void memberfailDataProcess(Date time) {
		//读取前一年的数据
		time.setYear(time.getYear()-1);
		
		String timer = DateUtil.getYear(time);
		timerPotentialCustomerService.callStoredProcedure(MEMBERFALL,timer);
	}
	/**
	 * 
	 * <p>
	 * 会员升级数据加工定时器<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-7-11
	 * void
	 */
	public void memberupgradeDataProcess(){
		Date time = new Date();
		memberupgradeDataProcess(time);
	}
	
	public void memberupgradeDataProcess(Date time) {
		//读取前一个月的数据
		time.setMonth(time.getMonth()-1);
		
		String timer = DateUtil.getMoth(time);
		timerPotentialCustomerService.callStoredProcedure(MEMBERUPGRADE, timer);
	}

}
