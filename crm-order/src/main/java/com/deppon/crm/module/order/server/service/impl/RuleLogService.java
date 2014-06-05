package com.deppon.crm.module.order.server.service.impl;

import com.deppon.crm.module.order.server.dao.IRuleLogDao;
import com.deppon.crm.module.order.server.service.IRuleLogService;
import com.deppon.crm.module.order.shared.domain.RuleLog;

public class RuleLogService implements IRuleLogService {
	private IRuleLogDao ruleLogDao;

	@Override
	public void insert(RuleLog ruleLog) {
		ruleLogDao.insert(ruleLog);
	}

	public void setRuleLogDao(IRuleLogDao ruleLogDao) {
		this.ruleLogDao = ruleLogDao;
	}
}
