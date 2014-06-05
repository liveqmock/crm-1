package com.deppon.crm.module.order.server.dao;

import java.util.List;

import com.deppon.crm.module.order.shared.domain.OrderNumberRule;

public interface IOrderNumberRuleDao {
	List<OrderNumberRule> getAll();

	void update(OrderNumberRule rule);

	List<OrderNumberRule> find(OrderNumberRule rule);

	void insert(OrderNumberRule rule);

	List<OrderNumberRule> allResources();

	int checkSign(OrderNumberRule rule);
}
