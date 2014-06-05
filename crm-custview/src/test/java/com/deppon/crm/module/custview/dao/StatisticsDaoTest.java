package com.deppon.crm.module.custview.dao;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.custview.server.dao.IStatisticsDao;
import com.deppon.crm.module.custview.shared.domain.ComplaintStatistics;
import com.deppon.crm.module.custview.shared.domain.RecStatistics;
import com.deppon.foss.framework.server.context.AppContext;

public class StatisticsDaoTest extends TestCase{
	private static ClassPathXmlApplicationContext factory;
	private static IStatisticsDao statisticsDao;

	String custId = "525";

	static{
		AppContext.initAppContext("application","","");// classpath*:
		String[] resource = { "classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml" };
		factory = new ClassPathXmlApplicationContext(resource);
		statisticsDao = (IStatisticsDao) factory.getBean("statisticsDao");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRecStatisticsListByCustId() {
		List<RecStatistics> list = this.statisticsDao
				.getRecStatisticsListByCustId(custId);
	}

	@Test
	public void testGetComplaintStatisticsListByCustId() {
		List<ComplaintStatistics> list = this.statisticsDao
				.getComplaintStatisticsListByCustId(custId);
	}

	@Test
	public void testGetComplaintStatisticsByCustId() {
		List list = this.statisticsDao.getComplaintStatisticsByCustId(custId);
	}

	@Test
	public void testGetOperationAnalysisListByCustId() {
		this.statisticsDao.getOperationAnalysisListByCustId(null,custId, new Date(
				2012, 1, 1), new Date(2012, 1, 1));
	}

	@Test
	public void testGetCustLatelyTradeDateByCustId() {
		this.statisticsDao.getCustLatelyTradeDateByCustId(custId);
	}
}
