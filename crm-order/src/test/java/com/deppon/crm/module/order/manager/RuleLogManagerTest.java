package com.deppon.crm.module.order.manager;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.crm.module.order.server.manager.IRuleLogManager;
import com.deppon.crm.module.order.shared.domain.RuleLog;
import com.deppon.foss.framework.server.context.AppContext;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = "classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml")
public class RuleLogManagerTest extends AbstractTransactionalJUnit4SpringContextTests {

	static {
		AppContext.initAppContext("application", "", "");
	}

	@Autowired
	private IRuleLogManager manager;
	
	@Test
	public void insert(){
		RuleLog ruleLog = new RuleLog();
		ruleLog.setContent("状态从启用改为禁用");
		ruleLog.setEvent("禁用");
		ruleLog.setOperateTime(new Date());
		ruleLog.setOperatorDeptName("上海派送中心");
		ruleLog.setOperatorEmpName("林冲");
		ruleLog.setOperatorUserId("15453");
		ruleLog.setOrderNumberRuleId("snrety-zserbse-kytu-wfwef");
		manager.insert(ruleLog);
	}
}
