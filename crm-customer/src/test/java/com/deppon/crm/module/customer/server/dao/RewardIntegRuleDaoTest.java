package com.deppon.crm.module.customer.server.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.testutils.DateInitUtil;
import com.deppon.crm.module.customer.server.testutils.TestUtils;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.integral.RewardIntegRule;
import com.opensymphony.xwork2.interceptor.annotations.After;

/**
 * 
 * <p>
 * 积分奖励规则的测试类<br />
 * 此来作者写测试代码第一个引用dbUnit的类  -----先不引入此技术 测试线··
 * </p>
 * @title RewardIntegralDaoTest.java
 * @package com.deppon.crm.module.customer.server.dao 
 * @author bxj
 * @version 0.2 2012-4-20
 */
public class RewardIntegRuleDaoTest extends BeanUtil{
	
	@Before
	public void setUp(){
		
	}
	@After
	public void shutDown() throws Exception{
		DateInitUtil.executeCleanSql();
	}
	@Test
	public void testSaveRewardIntegRule(){
		/*
		 * 我会写全自动的 等着···
		 */
		RewardIntegRule rewardIntegral = TestUtils.createBean(RewardIntegRule.class);
		rewardIntegRuleDao.saveRewardIntegRule(rewardIntegral);
	}
	
	@Test
	public void testUpdateRewardIntegRule(){
		RewardIntegRule rewardIntegral = TestUtils.createBean(RewardIntegRule.class);
		rewardIntegRuleDao.saveRewardIntegRule(rewardIntegral);
		rewardIntegral.setFraction(3123123d);
		rewardIntegRuleDao.updateRewardIntegRule(rewardIntegral);
	}
	
	@Test
	public void testGetRewardIntegRules(){
		RewardIntegRule rewardIntegral = TestUtils.createBean(RewardIntegRule.class);
		rewardIntegRuleDao.saveRewardIntegRule(rewardIntegral);
		List<RewardIntegRule> list = rewardIntegRuleDao.getRewardIntegRules(0, 100);
		Assert.assertTrue(list.size()>0);
	}
	
	@Test
	public void testCountRewardIntegRules(){
		RewardIntegRule rewardIntegral = TestUtils.createBean(RewardIntegRule.class);
		rewardIntegRuleDao.saveRewardIntegRule(rewardIntegral);
		long count = rewardIntegRuleDao.countRewardIntegRules();
		Assert.assertTrue(count>0);
	}
}
