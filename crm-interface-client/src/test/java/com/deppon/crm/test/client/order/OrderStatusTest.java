package com.deppon.crm.test.client.order;

import org.junit.Test;

import com.deppon.crm.module.client.order.dao.IOrderLogDao;
import com.deppon.crm.module.client.order.domain.OrderStatusLog;
import com.deppon.crm.test.client.common.OperaterTest;
import com.deppon.crm.test.client.util.SpringHelper;

public class OrderStatusTest extends OperaterTest {

	@Test
	public void test(){
		IOrderLogDao orderLogDao = (IOrderLogDao) SpringHelper.getApplicationContext().getBean("orderLogDao");
		try {
			OrderStatusLog log = new OrderStatusLog();
			log.setId(2222);
			orderLogDao.updateOrderStatusLogStatus(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
