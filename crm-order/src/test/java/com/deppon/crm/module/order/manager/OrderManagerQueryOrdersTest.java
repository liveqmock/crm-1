package com.deppon.crm.module.order.manager;

import static org.easymock.EasyMock.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.deppon.crm.module.order.server.manager.impl.OrderManager;
import com.deppon.crm.module.order.server.service.impl.OrderService;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.foss.framework.exception.GeneralException;

import static org.junit.Assert.*;

public class OrderManagerQueryOrdersTest {

	@Test
	public void correct() {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("startDate", getDate("2013-06-30"));
		condition.put("endDate", getDate("2013-09-30"));

		List<Order> orders = new ArrayList<Order>();

		OrderService service = createMock(OrderService.class);
		expect(service.queryOrders(condition)).andReturn(orders);

		replay(service);

		OrderManager manager = new OrderManager();
		manager.setOrderService(service);
		List<Order> queryOrders = manager.queryOrders(condition);
		assertEquals(queryOrders, orders);

		verify(service);
	}

	@Test(expected = GeneralException.class)
	public void dateMoreThanThreeMonths1() {
		OrderManager manager = new OrderManager();

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("startDate", getDate("2013-06-29"));
		condition.put("endDate", getDate("2013-09-30"));
		manager.queryOrders(condition);
	}
	
	@Test(expected = GeneralException.class)
	public void dateMoreThanThreeMonths2() {
		OrderManager manager = new OrderManager();

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("startDate", getDate("2013-06-30"));
		condition.put("endDate", getDate("2013-10-1"));
		manager.queryOrders(condition);
	}
	
	@Test(expected = GeneralException.class)
	public void dateMoreThanThreeMonths3() {
		OrderManager manager = new OrderManager();

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("startDate", getDate("2012-06-30"));
		condition.put("endDate", getDate("2013-09-30"));
		manager.queryOrders(condition);
	}

	@Test(expected = GeneralException.class)
	public void startDateIsNull() {
		OrderManager manager = new OrderManager();

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("endDate", getDate("2013-09-30"));
		manager.queryOrders(condition);
	}

	@Test(expected = GeneralException.class)
	public void endDateIsNull() {
		OrderManager manager = new OrderManager();

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("startDate", getDate("2013-09-30"));
		manager.queryOrders(condition);
	}

	@Test
	public void bothNull() {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("startDate", null);
		condition.put("endDate", null);
		List<Order> orders = new ArrayList<Order>();

		OrderService service = createMock(OrderService.class);
		expect(service.queryOrders(condition)).andReturn(orders);

		replay(service);

		OrderManager manager = new OrderManager();
		manager.setOrderService(service);
		List<Order> queryOrders = manager.queryOrders(condition);
		assertEquals(queryOrders, orders);

		verify(service);
	}

	private Date getDate(String date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
