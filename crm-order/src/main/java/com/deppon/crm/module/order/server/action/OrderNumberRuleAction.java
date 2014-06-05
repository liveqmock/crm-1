package com.deppon.crm.module.order.server.action;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.order.server.manager.IOrderNumberRuleManager;
import com.deppon.crm.module.order.shared.domain.OrderNumberRule;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

public class OrderNumberRuleAction extends AbstractAction {
	private static final long serialVersionUID = 3190717407233304926L;
	private IOrderNumberRuleManager orderNumberRuleManager;

	private List<OrderNumberRule> rules;
	private OrderNumberRule rule;
	private List<OrderNumberRule> changeStatusRules = new ArrayList<OrderNumberRule>();
	private List<OrderNumberRule> resources;

	@JSON
	public String allRules() {
		rules = orderNumberRuleManager.getAll();
		return SUCCESS;
	}

	@JSON
	public String updateRule() {
		orderNumberRuleManager.update(rule, (User) UserContext.getCurrentUser());
		return SUCCESS;
	}

	@JSON
	public String changeStatus() {
		orderNumberRuleManager.changeStatus(changeStatusRules,
				(User) UserContext.getCurrentUser());
		return SUCCESS;
	}

	@JSON
	public String createRule() {
		List<OrderNumberRule> rules = new ArrayList<OrderNumberRule>();
		String[] transportModes = rule.getTransportMode().split(",");
		for (String transportMode : transportModes) {
			OrderNumberRule orderNumberRule = new OrderNumberRule();
			orderNumberRule.setName(rule.getName());
			orderNumberRule.setRemark(rule.getRemark());
			orderNumberRule.setResource(rule.getResource());
			orderNumberRule.setSign(rule.getSign());
			orderNumberRule.setStatus(rule.getStatus());
			orderNumberRule.setTransportMode(transportMode.trim());
			rules.add(orderNumberRule);
		}
		orderNumberRuleManager.insert(rules,
				(User) UserContext.getCurrentUser());
		return SUCCESS;
	}

	@JSON
	public String allResources() {
		resources = orderNumberRuleManager.allResources();
		return SUCCESS;
	}

	public void setOrderNumberRuleManager(
			IOrderNumberRuleManager orderNumberRuleManager) {
		this.orderNumberRuleManager = orderNumberRuleManager;
	}

	public List<OrderNumberRule> getRules() {
		return rules;
	}

	public void setRule(OrderNumberRule rule) {
		this.rule = rule;
	}

	public OrderNumberRule getRule() {
		return rule;
	}

	public void setChangeStatusRules(List<OrderNumberRule> changeStatusRules) {
		this.changeStatusRules = changeStatusRules;
	}

	public List<OrderNumberRule> getChangeStatusRules() {
		return changeStatusRules;
	}

	public List<OrderNumberRule> getResources() {
		return resources;
	}
}