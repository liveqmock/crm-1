package com.deppon.crm.module.order.server.manager;

import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.order.shared.domain.OrderNumberRule;

public interface IOrderNumberRuleManager {

	List<OrderNumberRule> getAll();
	List<OrderNumberRule> find(OrderNumberRule rule);
	void changeStatus(List<OrderNumberRule> rules, User user);
	void insert(List<OrderNumberRule> rules, User user);
	String produceOrderNumber(String idSeq, String resource,
			String transportMode);
	List<OrderNumberRule> allResources();
	void update(OrderNumberRule rule,User user);
}