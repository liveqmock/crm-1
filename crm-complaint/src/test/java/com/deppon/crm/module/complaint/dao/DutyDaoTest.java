package com.deppon.crm.module.complaint.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.duty.server.dao.IDutyDao;
import com.deppon.crm.module.duty.shared.domain.SearchDutyCondition;

public class DutyDaoTest {
	private IDutyDao dutyDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	@Before
	public void setUp() throws Exception {
		try {
			if (ctx == null) {
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			dutyDao = (IDutyDao) ctx
					.getBean("dutyDao");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testSearchDutyByCondition(){
		SearchDutyCondition sdc = new SearchDutyCondition();
		sdc.setRowNum("1");
		dutyDao.searchDutyByCondition(sdc);
	}
}
