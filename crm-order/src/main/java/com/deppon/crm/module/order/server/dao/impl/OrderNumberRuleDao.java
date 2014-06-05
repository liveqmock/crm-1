package com.deppon.crm.module.order.server.dao.impl;

import java.util.List;

import com.deppon.crm.module.order.server.dao.IOrderNumberRuleDao;
import com.deppon.crm.module.order.shared.domain.OrderNumberRule;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class OrderNumberRuleDao extends iBatis3DaoImpl implements IOrderNumberRuleDao {
	private static final String NAMESPACE = "com.deppon.crm.module.order.shared.domain.OrderNumberRule.";

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderNumberRule> getAll() {
		return getSqlSession().selectList(NAMESPACE + "selectAll");
	}

	@Override
	public void update(OrderNumberRule rule) {
		getSqlSession().update(NAMESPACE + "update", rule);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderNumberRule> find(OrderNumberRule rule) {
		return getSqlSession().selectList(NAMESPACE + "find", rule);
	}

	@Override
	public void insert(OrderNumberRule rule) {
		getSqlSession().insert(NAMESPACE + "insert", rule);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderNumberRule> allResources() {
		return getSqlSession().selectList(NAMESPACE + "allResources");
	}

	@Override
	public int checkSign(OrderNumberRule rule) {
		return (Integer) getSqlSession().selectOne(NAMESPACE + "checkSign", rule);
	}

}
