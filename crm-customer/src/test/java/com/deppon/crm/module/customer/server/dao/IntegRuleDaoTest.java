package com.deppon.crm.module.customer.server.dao;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.testutils.DateInitUtil;
import com.deppon.crm.module.customer.server.testutils.TestUtils;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.integral.ChannelIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.ProductIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.WiringIntegRule;
import com.opensymphony.xwork2.interceptor.annotations.After;

public class IntegRuleDaoTest extends BeanUtil{
	
	@Before
	public void setUp(){
	}
	@After
	public void shutDown() throws Exception{
		DateInitUtil.executeCleanSql();
	}
	@Test
	public void testsaveInteg(){
		/*
		 * 不是我不写 高自动的测试代码， 时间有限，半自动代码也不错的蛮···   保存了 去 数据库里面查一查 就不ok拉
		 */
		ChannelIntegRule integRule = TestUtils.createBean(ChannelIntegRule.class);
		integRuleDao.saveChannelIntegRule(integRule);
		
		ProductIntegRule productRule = TestUtils.createBean(ProductIntegRule.class);
		integRuleDao.saveProductIntegRule(productRule);
		
		WiringIntegRule wiringIntegRule = TestUtils.createBean(WiringIntegRule.class);
		integRuleDao.saveWiringIntegRule(wiringIntegRule);
		
	}
	
	@Test
	public void testupdateInteg(){
		
		ChannelIntegRule integRule = TestUtils.createBean(ChannelIntegRule.class);
		integRuleDao.updateChannelIntegRule(integRule);
		
		ProductIntegRule productRule = TestUtils.createBean(ProductIntegRule.class);
		integRuleDao.updateProductIntegRule(productRule);
		
		WiringIntegRule wiringIntegRule = TestUtils.createBean(WiringIntegRule.class);
		integRuleDao.updateWiringIntegRule(wiringIntegRule);
		
		
	}
	
	@Test
	public void testgetInteg(){
		ChannelIntegRule integRule = TestUtils.createBean(ChannelIntegRule.class);
		integRuleDao.saveChannelIntegRule(integRule);
		
		ProductIntegRule productRule = TestUtils.createBean(ProductIntegRule.class);
		integRuleDao.saveProductIntegRule(productRule);
		
		WiringIntegRule wiringIntegRule = TestUtils.createBean(WiringIntegRule.class);
		integRuleDao.saveWiringIntegRule(wiringIntegRule);
		
		integRule = integRuleDao.getChannelIntegRuleById(integRule.getId());
		wiringIntegRule = integRuleDao.getWiringIntegRuleById(wiringIntegRule.getId());
		productRule = integRuleDao.getProductIntegRuleById(productRule.getId());
		
		Assert.assertNotNull(integRule);
		Assert.assertNotNull(wiringIntegRule);
		Assert.assertNotNull(productRule);
		
	}
	
	@Test
	public void testdeleteInteg(){
		ChannelIntegRule integRule = TestUtils.createBean(ChannelIntegRule.class);
		integRuleDao.saveChannelIntegRule(integRule);
		integRuleDao.deleteChannelIntegRule(integRule.getId());
		
		ProductIntegRule productRule = TestUtils.createBean(ProductIntegRule.class);
		integRuleDao.saveProductIntegRule(productRule);
		integRuleDao.deleteProductIntegRule(productRule.getId());

		WiringIntegRule wiringIntegRule = TestUtils.createBean(WiringIntegRule.class);
		integRuleDao.saveWiringIntegRule(wiringIntegRule);
		integRuleDao.deleteWiringIntegRule(wiringIntegRule.getId());
		
		integRule = integRuleDao.getChannelIntegRuleById(integRule.getId());
		wiringIntegRule = integRuleDao.getWiringIntegRuleById(wiringIntegRule.getId());
		productRule = integRuleDao.getProductIntegRuleById(productRule.getId());
		
		Assert.assertNull(integRule);
		Assert.assertNull(wiringIntegRule);
		Assert.assertNull(productRule);

	}
	
	@Test
	public void testselectList(){
		ChannelIntegRule integRule = TestUtils.createBean(ChannelIntegRule.class);
		integRuleDao.saveChannelIntegRule(integRule);
		
		ProductIntegRule productRule = TestUtils.createBean(ProductIntegRule.class);
		integRuleDao.saveProductIntegRule(productRule);

		WiringIntegRule wiringIntegRule = TestUtils.createBean(WiringIntegRule.class);
		integRuleDao.saveWiringIntegRule(wiringIntegRule);
		
		Assert.assertTrue(integRuleDao.getChannelIntegRules(0,100).size()>0);
		integRuleDao.countGetChannelIntegRules();
		Assert.assertTrue(integRuleDao.getWiringIntegRules(0,100).size()>0);
		integRuleDao.countGetWiringIntegRules();
		Assert.assertTrue(integRuleDao.getProductIntegRules(0,100).size()>0);
		integRuleDao.countGetProductIntegRules();
		integRuleDao.deleteWiringIntegRule(wiringIntegRule.getId());
		integRuleDao.deleteProductIntegRule(productRule.getId());
		integRuleDao.searchIntegRule(integRule.getId());
		integRuleDao.deleteChannelIntegRule(integRule.getId());
	}
	
	@Test
	public void testQueryRuleByCondition(){
		ProductIntegRule productRule = TestUtils.createBean(ProductIntegRule.class);
		productRule.setPointbegintime(new Date(2012, 7, 1));
		productRule.setPointendtime(new Date(2012, 7, 31));
		productRule.setProduct("JZKH");
		integRuleDao.queryRuleByCondition(productRule, "产品规则");
	}
}
