package com.deppon.crm.module.order.server.manager.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.order.server.manager.IOrderNumberRuleManager;
import com.deppon.crm.module.order.server.manager.IRuleLogManager;
import com.deppon.crm.module.order.server.service.IOrderNumberRuleService;
import com.deppon.crm.module.order.server.util.OrderUtil;
import com.deppon.crm.module.order.shared.domain.OrderNumberRule;
import com.deppon.crm.module.order.shared.exception.OrderExceptionType;

public class OrderNumberRuleManager implements IOrderNumberRuleManager {
	private IOrderNumberRuleService orderNumberRuleService;
	private IRuleLogManager ruleLogManager;

	/**
	 * @description 产生订单号.
	 * @author 潘光均,胡傲果
	 * @version 0.2 2013-9-7
	 * @date 2013-9-7
	 * @return String
	 * @update 2013-9-7 上午9:26
	 */
	@Transactional(readOnly = true)
	@Override
	public String produceOrderNumber(String id, String resource,
			String transportMode) {
		StringBuilder orderNumber = new StringBuilder();

		String sign = getSign(resource, transportMode);
		orderNumber.append(sign);

		SimpleDateFormat sf = new SimpleDateFormat("yyMMdd");
		String date = sf.format(new Date());
		orderNumber.append(date);

		if (id.length() > 6) {
			orderNumber.append(id.substring(id.length() - 6, id.length()));
		} else {
			orderNumber.append(StringUtils.leftPad(id, 6, '0'));
		}
		return orderNumber.toString();
	}

	private String getSign(String resource, String transportMode) {
		OrderNumberRule condition = new OrderNumberRule();
		condition.setResource(resource.toUpperCase());
		condition.setTransportMode(transportMode);
		condition.setStatus(1);
		List<OrderNumberRule> result = find(condition);
		if (result.isEmpty()) {
			throw OrderUtil
					.buildException(OrderExceptionType.ORDER_NUMBER_RULE_NOTFOUND);
		}
		if (result.size() > 1) {
			throw OrderUtil
					.buildException(OrderExceptionType.ORDER_NUMBER_RULE_FOUNDTOOMUCH);
		}
		String sign = result.get(0).getSign();
		if (sign == null) {
			throw OrderUtil
					.buildException(OrderExceptionType.ORDER_NUMBER_RULE_SIGNISNULL);
		}
		return sign;
	}

	@Transactional(readOnly = true)
	@Override
	public List<OrderNumberRule> getAll() {
		return orderNumberRuleService.getAll();
	}

	@Transactional
	@Override
	public void update(OrderNumberRule rule, User user) {
		checkRules(rule);
		OrderNumberRule oldRule = get(rule.getId());
		orderNumberRuleService.update(rule);
		ruleLogManager.generateRuleLog("修改", oldRule, rule, user);
	}

	private void checkRules(OrderNumberRule rule) {
		// 检查规则是否已经存在
		OrderNumberRule condition = new OrderNumberRule();
		condition.setResource(rule.getResource());
		condition.setTransportMode(rule.getTransportMode());
		List<OrderNumberRule> result = find(condition);
		for (Iterator<OrderNumberRule> iterator = result.iterator(); iterator
				.hasNext();) {
			OrderNumberRule orderNumberRule = (OrderNumberRule) iterator.next();
			if (orderNumberRule.getId().equals(rule.getId())) {
				iterator.remove();
			}
		}
		if (!result.isEmpty()) {
			throw OrderUtil.buildException(OrderExceptionType.ORDER_NUMBER_RULE_CONFLICT);
		}
		
		// 检查SIGN是否已经被使用
		int checkSign = orderNumberRuleService.checkSign(rule);
		if(checkSign > 0){
			throw OrderUtil.buildException(OrderExceptionType.ORDER_NUMBER_RULE_SIGNUSED);
		}
	}

	@Transactional(readOnly = true)
	public OrderNumberRule get(String id) {
		OrderNumberRule rule = new OrderNumberRule();
		rule.setId(id);
		List<OrderNumberRule> list = find(rule);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Transactional(readOnly = true)
	@Override
	public List<OrderNumberRule> find(OrderNumberRule rule) {
		return orderNumberRuleService.find(rule);
	}

	@Transactional
	@Override
	public void changeStatus(List<OrderNumberRule> rules, User user) {
		for (OrderNumberRule rule : rules) {
			OrderNumberRule oldRule = get(rule.getId());
			orderNumberRuleService.update(rule);

			String event = rule.getStatus() == 1 ? "启用" : "禁用";
			ruleLogManager.generateRuleLog(event, oldRule, rule, user);
		}
	}

	@Transactional
	@Override
	public void insert(List<OrderNumberRule> rules, User user) {
		for (OrderNumberRule rule : rules) {
			checkRules(rule);
		}
		for (OrderNumberRule rule : rules) {
			if (rule.getId() == null) {
				rule.setId(UUID.randomUUID().toString());
			}
			rule.setCreateUserId(user.getId());
			rule.setCreateUserEmpName(user.getEmpCode().getEmpName());
			rule.setCreateTime(new Date());
			orderNumberRuleService.insert(rule);

			ruleLogManager.generateRuleLog("新增", new OrderNumberRule(), rule,
					user);
		}
	}

	@Override
	public List<OrderNumberRule> allResources() {
		return orderNumberRuleService.allResources();
	}

	public void setOrderNumberRuleService(
			IOrderNumberRuleService orderNumberRuleService) {
		this.orderNumberRuleService = orderNumberRuleService;
	}

	public void setRuleLogManager(IRuleLogManager ruleLogManager) {
		this.ruleLogManager = ruleLogManager;
	}

}