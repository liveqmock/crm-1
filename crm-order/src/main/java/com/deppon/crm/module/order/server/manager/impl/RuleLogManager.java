package com.deppon.crm.module.order.server.manager.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.order.server.manager.IRuleLogManager;
import com.deppon.crm.module.order.server.service.IRuleLogService;
import com.deppon.crm.module.order.shared.domain.OrderNumberRule;
import com.deppon.crm.module.order.shared.domain.RuleLog;

public class RuleLogManager implements IRuleLogManager {
	private IRuleLogService ruleLogService;

	@Override
	public void insert(RuleLog ruleLog) {
		if (ruleLog.getId() == null) {
			ruleLog.setId(UUID.randomUUID().toString());
		}
		ruleLogService.insert(ruleLog);
	}

	@Override
	public void generateRuleLog(String event, OrderNumberRule oldRule,
			OrderNumberRule newRule, User user) {
		// 如果没有改动就直接返回
		String content = generateContent(oldRule, newRule);
		if (content.isEmpty()) {
			return;
		}
		RuleLog ruleLog = new RuleLog();
		ruleLog.setContent(content);
		ruleLog.setEvent(event);
		ruleLog.setOperateTime(new Date());
		ruleLog.setOperatorDeptName(user.getEmpCode().getDeptId().getDeptName());
		ruleLog.setOperatorEmpName(user.getEmpCode().getEmpName());
		ruleLog.setOperatorUserId(user.getId());
		ruleLog.setOrderNumberRuleId(newRule.getId());
		insert(ruleLog);
	}

	private String generateContent(OrderNumberRule oldRule,
			OrderNumberRule newRule) {
		StringBuilder sb = new StringBuilder();
		if (isModified(oldRule.getName(), newRule.getName())) {
			sb.append("渠道名由").append(oldRule.getName()).append("改为")
					.append(newRule.getName()).append(";");
		}
		if (isModified(oldRule.getResource(), newRule.getResource())) {
			sb.append("渠道代号由").append(oldRule.getResource()).append("改为")
					.append(newRule.getResource()).append(";");
		}
		if (isModified(oldRule.getTransportMode(), newRule.getTransportMode())) {
			sb.append("运输方式由").append(oldRule.getTransportMode()).append("改为")
					.append(newRule.getTransportMode()).append(";");
		}
		if (isModified(oldRule.getSign(), newRule.getSign())) {
			sb.append("前缀由").append(oldRule.getSign()).append("改为")
					.append(newRule.getSign()).append(";");
		}
		if (isModified(oldRule.getStatus(), newRule.getStatus())) {
			sb.append("状态由").append(oldRule.getStatus()).append("改为")
					.append(newRule.getStatus()).append(";");
		}
		if (isModified(oldRule.getRemark(), newRule.getRemark())) {
			sb.append("备注由").append(oldRule.getRemark()).append("改为")
					.append(newRule.getRemark()).append(";");
		}
		return sb.toString();
	}

	private boolean isModified(Object oldOne, Object newOne) {
		// 如果新值是null，SQL不会更新该字段，所以不会修改
		if (newOne == null) {
			return false;
		}
		// 如果新值是空字符串而原值是null，则实际上也不会发生改动
		if (isBlankString(newOne) && isBlankString(oldOne)) {
			return false;
		}
		// 其他情况，只要不相同就是发生改动
		return !newOne.equals(oldOne);
	}

	private boolean isBlankString(Object str) {
		return str == null
				|| (str instanceof String && StringUtils.isBlank((String) str));
	}

	public void setRuleLogService(IRuleLogService ruleLogService) {
		this.ruleLogService = ruleLogService;
	}
}
