package com.deppon.crm.module.order.server.service.impl;

import java.util.List;

import com.deppon.crm.module.order.server.dao.IOrderNumberRuleDao;
import com.deppon.crm.module.order.server.service.IOrderNumberRuleService;
import com.deppon.crm.module.order.shared.domain.OrderNumberRule;

public class OrderNumberRuleService implements IOrderNumberRuleService {
	private IOrderNumberRuleDao orderNumberRuleDao;

	@Override
	public List<OrderNumberRule> getAll() {
		return orderNumberRuleDao.getAll();
	}

	@Override
	public void update(OrderNumberRule rule) {
		orderNumberRuleDao.update(rule);
	}

	public void setOrderNumberRuleDao(IOrderNumberRuleDao orderNumberRuleDao) {
		this.orderNumberRuleDao = orderNumberRuleDao;
	}

	@Override
	public List<OrderNumberRule> find(OrderNumberRule rule) {
		return orderNumberRuleDao.find(rule);
	}

	@Override
	public void insert(OrderNumberRule rule) {
		orderNumberRuleDao.insert(rule);
	}

	@Override
	public List<OrderNumberRule> allResources() {
		return orderNumberRuleDao.allResources();
	}

	@Override
	public int checkSign(OrderNumberRule rule) {
		return orderNumberRuleDao.checkSign(rule);
	}
}
