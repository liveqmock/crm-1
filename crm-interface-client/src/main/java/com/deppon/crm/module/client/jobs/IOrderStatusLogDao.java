package com.deppon.crm.module.client.jobs;

import java.util.List;

import com.deppon.crm.module.client.order.domain.OrderStatusLog;

public interface IOrderStatusLogDao {

	
	//@Transactional
	public void update(List<OrderStatusLog> logs);
	
	
	//@Transactional
	public void update(OrderStatusLog log);
}
