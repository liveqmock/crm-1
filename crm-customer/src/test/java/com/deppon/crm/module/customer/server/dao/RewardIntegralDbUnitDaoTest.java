package com.deppon.crm.module.customer.server.dao;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Before;

import com.deppon.crm.module.customer.server.testutils.TestUtils;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.integral.RewardIntegRule;

/**
 * <p>
 * 积分奖励规则的测试类<br />
 * 此来作者写测试代码第一个引用dbUnit的类  -----先不引入此技术 测试线··
 * </p>
 * @title RewardIntegralDaoTest.java
 * @package com.deppon.crm.module.customer.server.dao 
 * @author bxj
 * @version 0.2 2012-4-20
 */
public class RewardIntegralDbUnitDaoTest extends BeanUtil{
	
	private IDatabaseConnection connection = null;
	
	@Before
	public void setUp() throws Exception{
		connection = new DatabaseDataSourceConnection(dataSource);
		File file = new File("src/test/resources/com/deppon/crm/module/customer/server/dao/RewardIntegral.xml");
		System.out.println(file.getAbsolutePath());
		IDataSet dataSet = new XmlDataSet(new FileInputStream(file));
		DatabaseOperation.REFRESH.execute(connection, dataSet);
		connection.close();
	}
	
	public void testSaveRewardIntegRule(){
		/*
		 * 我会写全自动的 等着···
		 */
		RewardIntegRule rewardIntegral = TestUtils.createBean(RewardIntegRule.class);
		rewardIntegRuleDao.saveRewardIntegRule(rewardIntegral);
	}
	
	public void testUpdateRewardIntegRule(){
		RewardIntegRule rewardIntegral = TestUtils.createBean(RewardIntegRule.class);
		rewardIntegRuleDao.saveRewardIntegRule(rewardIntegral);
		rewardIntegral.setFraction(3123123d);
		rewardIntegRuleDao.updateRewardIntegRule(rewardIntegral);
		
	}
	
	public void testGetRewardIntegRules(){
		List<RewardIntegRule> list = rewardIntegRuleDao.getRewardIntegRules(0, 100);
	}
}
