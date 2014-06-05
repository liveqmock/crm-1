package com.deppon.crm.module.order.manager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.crm.module.order.server.manager.impl.OrderManager;
import com.deppon.crm.module.order.server.util.Constant;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.order.shared.exception.OrderExceptionType;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.AppContext;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = "classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml")
public class OrderManagerAssertPilotPackageOrderTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	static {
		AppContext.initAppContext("application", "","");
	}
	
	@Autowired
	private OrderManager orderManager;
	
	/**
	 * 插入测试数据
	 */
	@Before
	public void setUp() {
		executeSqlScript("fixtures/init_pilot_city.sql", false);
	}
	
	@Test
	public void cityIsNull(){
		Order order = new Order();
		order.setTransportMode(Constant.ORDER_TRANSTYPE_AGENT_PACKAGE);
		orderManager.assertNotPilotPackageOrder(order, OrderExceptionType.BOOKVIHICE_ENDDEPT_CONNOTNULL);
	}
	
	@Test
	public void transportModeIsNull(){
		Order order = new Order();
		order.setContactCity("深圳");
		orderManager.assertNotPilotPackageOrder(order, OrderExceptionType.BOOKVIHICE_ENDDEPT_CONNOTNULL);
	}
	
	@Test
	public void cityIsNot(){
		Order order = new Order();
		order.setContactCity("深圳");
		order.setTransportMode(Constant.ORDER_TRANSTYPE_AGENT_PACKAGE);
		orderManager.assertNotPilotPackageOrder(order, OrderExceptionType.BOOKVIHICE_ENDDEPT_CONNOTNULL);
	}
	
	@Test
	public void transportModeIsNot(){
		Order order = new Order();
		order.setContactCity("上海");
		order.setTransportMode(Constant.ORDER_TRANSTYPE_AGENT_VEHICLE);
		orderManager.assertNotPilotPackageOrder(order, OrderExceptionType.BOOKVIHICE_ENDDEPT_CONNOTNULL);
	}
	
	@Test(expected = GeneralException.class)
	public void isPilotPackageOrder(){
		Order order = new Order();
		order.setContactCity("上海");
		order.setTransportMode(Constant.ORDER_TRANSTYPE_AGENT_PACKAGE);
		orderManager.assertNotPilotPackageOrder(order, OrderExceptionType.BOOKVIHICE_ENDDEPT_CONNOTNULL);
	}
}
