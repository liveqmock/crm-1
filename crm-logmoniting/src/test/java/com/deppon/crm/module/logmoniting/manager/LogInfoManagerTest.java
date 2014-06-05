package com.deppon.crm.module.logmoniting.manager;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.logmoniting.server.utils.DateUtil;
import com.deppon.crm.module.logmoniting.shared.domain.BasicLoginfo;
import com.deppon.crm.module.logmoniting.shared.domain.Constant;
import com.deppon.crm.module.logmoniting.shared.domain.LogInfoCondition;
import com.deppon.crm.module.logmoniting.utils.BeanUtils;

public class LogInfoManagerTest extends BeanUtils{

	@Before
	public void setUP() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testSearchLoginfosByCondition(){
		
		Date beginDate = DateUtil.returnPreviousMonth(new Date(), 1);
		Date endDate = new Date();
		
		System.out.println(beginDate.toLocaleString());
		System.out.println(endDate.toLocaleString());
		LogInfoCondition condition = new LogInfoCondition();
		condition.setActionName("CoCo");
		condition.setStatisticalFrequency(Constant.STATISTICALFREQUENCY_MONTH);
		condition.setBeginDate(beginDate);
		condition.setEndDate(endDate);
		
		logInfoManager.searchLogStatisticsByCondition(condition);
	}
	
	@Test
	public void testAssemblingLogInfoDataForOneHour(){
		logInfoManager.assemblingLogInfoDataForOneHour();
	}
	
	@Test
	public void testGetBasicLoginfos(){
		List<BasicLoginfo> list = null;
		LogInfoCondition condition = new LogInfoCondition();
		condition.setStart(20);
		condition.setLimit(20);
		condition.setModulename("coco_coco");
//		condition.setUri("553509117@qq.com4");
		list = logInfoManager.getBasicLoginfos(condition, Constant.WHETHER_PAGING);
		for (BasicLoginfo basicLoginfo : list) {
			System.out.println(basicLoginfo.getUrl() + "--url");
		}
		System.out.println(list.size());
	}
	
	@Test
	public void testGetTopActionLogInfoByCondition(){
		LogInfoCondition condition = new LogInfoCondition();
		condition.setStatisticalMethods(Constant.STATISTICALMETHODS_REQUESTCOUNT);
		condition.setStatisticalFrequency(Constant.STATISTICALFREQUENCY_HOUR);
		condition.setLevel(10);
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, 7);
		calendar.set(Calendar.DAY_OF_MONTH, 27);
		calendar.set(Calendar.HOUR_OF_DAY, 16);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		date = calendar.getTime();
		condition.setBeginDate(date);
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 27);
		calendar.set(Calendar.HOUR_OF_DAY, 17);
		condition.setEndDate(calendar.getTime());
		System.out.println(condition.getBeginDate().toLocaleString());
		System.out.println(condition.getEndDate().toLocaleString());
		logInfoManager.getTopActionLogInfoByCondition(condition);
	}
	
	@Test
	public void testCompleteExceptionErrorCode(){
		logInfoManager.completeExceptionErrorCode();
	}
	
	@Test
	public void testRemoveLogForOneWeek(){
		logInfoManager.removeLogForOneWeek();
	}
	
	@Test
	public void testCompareActionAverageContrastByOneDay(){
		logInfoManager.compareActionAverageContrastByOneDay();
	}
	
	@Test
	public void testCompareTheTopTenActionLogInfoByOneDay(){
		logInfoManager.compareTheTopTenActionLogInfoByOneDay();
	}
	
}
