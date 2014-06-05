package com.deppon.crm.module.order.dao;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.crm.module.order.server.dao.impl.OrderReminderDao;
import com.deppon.crm.module.order.shared.domain.OrderAcceptConfig;
import com.deppon.foss.framework.server.context.AppContext;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = "classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml")
public class OrderReminderDaoGetOrderAccpetConfigTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	static {
		AppContext.initAppContext("application", "", "");
	}
	@Autowired
	private OrderReminderDao dao;

	@Before
	public void setUp() {
		executeSqlScript("fixtures/init_order_acceptconfig.sql", false);
	}

	@Test
	public void test() {
		OrderAcceptConfig config = dao.getOrderAcceptConfig();
		assertEquals(5, config.getWarnTime());
		assertEquals(30, config.getLockTime());
	}
}
