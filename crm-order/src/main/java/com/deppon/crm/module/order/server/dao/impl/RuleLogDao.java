package com.deppon.crm.module.order.server.dao.impl;

import com.deppon.crm.module.order.server.dao.IRuleLogDao;
import com.deppon.crm.module.order.shared.domain.RuleLog;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class RuleLogDao extends iBatis3DaoImpl implements IRuleLogDao {
	private static final String NAMESPACE = "com.deppon.crm.module.order.shared.domain.RuleLog.";

	@Override
	public void insert(RuleLog ruleLog) {
		getSqlSession().insert(NAMESPACE + "insert", ruleLog);
	}

}
