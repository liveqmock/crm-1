package com.deppon.crm.module.order.server.manager;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.order.shared.domain.OrderNumberRule;
import com.deppon.crm.module.order.shared.domain.RuleLog;

public interface IRuleLogManager {
	void insert(RuleLog ruleLog);

	void generateRuleLog(String event, OrderNumberRule oldRule,
			OrderNumberRule newRule, User user);
}
