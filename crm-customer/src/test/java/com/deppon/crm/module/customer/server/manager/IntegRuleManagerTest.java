package com.deppon.crm.module.customer.server.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.server.manager.impl.IntegRuleManager;
import com.deppon.crm.module.customer.server.service.IIntegRuleService;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.integral.ChannelIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.Gift;
import com.deppon.crm.module.customer.shared.domain.integral.IntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.ProductIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.RewardIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.WiringIntegRule;
import com.deppon.crm.module.customer.shared.exception.IntegException;
import com.deppon.crm.module.customer.shared.exception.IntegExceptionType;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;


public class IntegRuleManagerTest extends BeanUtil{
	
	private IntegRuleManager integRuleManagerForMock = new IntegRuleManager();
	@Before
	public void setUp() throws SQLException{
		clearData();
		User user = new User();
		Department depart = new Department();
		depart.setId("432658");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		e.setId("98564");
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
		IIntegRuleServiceJMock();
		baseDataManagerJMock();
	}
	
	public void IIntegRuleServiceJMock(){
		Mockery IIntegRuleServiceJMock = new Mockery();
		final IIntegRuleService integRuleService = IIntegRuleServiceJMock.mock(IIntegRuleService.class);
		IIntegRuleServiceJMock.checking(new Expectations() {
			{
				allowing(integRuleService).updateChannelIntegRule(with(any(ChannelIntegRule.class)));
				allowing(integRuleService).updateProductIntegRule(with(any(ProductIntegRule.class)));
				allowing(integRuleService).updateWiringIntegRule(with(any(WiringIntegRule.class)));
				allowing(integRuleService).getChannelIntegRules(with(any(Integer.class)),with(any(Integer.class)));
				allowing(integRuleService).getProductIntegRules(with(any(Integer.class)),with(any(Integer.class)));
				allowing(integRuleService).getWiringIntegRules(with(any(Integer.class)),with(any(Integer.class)));
				allowing(integRuleService).searchIntegRule(with(any(String.class)));
				allowing(integRuleService).countGetProductIntegRules();
				allowing(integRuleService).countGetChannelIntegRules();
				allowing(integRuleService).deleteChannelIntegRule(with(any(String.class)));
				allowing(integRuleService).deleteProductIntegRule(with(any(String.class)));
				allowing(integRuleService).deleteWiringIntegRule(with(any(String.class)));
				allowing(integRuleService).saveRewardIntegRule(with(any(RewardIntegRule.class)));
				allowing(integRuleService).saveRewardIntegRule(with(any(RewardIntegRule.class)));
				allowing(integRuleService).getRewardIntegRules(with(any(Integer.class)),with(any(Integer.class)));
				allowing(integRuleService).countRewardIntegRules();
				allowing(integRuleService).insertGift(with(any(Gift.class)));
				allowing(integRuleService).updateGift(with(any(Gift.class)));
				allowing(integRuleService).getGiftById(with(any(String.class)));
				will(returnValue(with(any(Gift.class))));
				allowing(integRuleService).getGifts(with(any(Gift.class)),with(any(Integer.class)),with(any(Integer.class)));
				will(returnValue(with(any(List.class))));
				allowing(integRuleService).countGetGifts(with(any(Gift.class)));
				will(returnValue(with(any(Long.class))));
				allowing(integRuleService).deleteGift(with(any(String.class)));
				allowing(integRuleService).updateRewardIntegRule(with(any(RewardIntegRule.class)));
				allowing(integRuleService).countGetWiringIntegRules();
			}
		});
		integRuleManagerForMock.setIntegRuleService(integRuleService);
	}
	
	public void baseDataManagerJMock(){
		Mockery baseDataManagerJMock = new Mockery();
		final IBaseDataManager baseDataManager = baseDataManagerJMock.mock(IBaseDataManager.class);
		baseDataManagerJMock.checking(new Expectations() {
			{
				allowing(baseDataManager).getCauseDepartment(with(any(String.class)));
			}
		});
		integRuleManagerForMock.setBaseDataManager(baseDataManager);
	}
	
	@Test
	public void testCountGetWiringIntegRules(){
		integRuleManagerForMock.countGetWiringIntegRules();
	}
	
	@Test
	public void testGetGiftById(){
		integRuleManagerForMock.getGiftById("1111");
	}
	
	@Test
	public void testUpdateChannelIntegRule(){
		ChannelIntegRule integRule = new ChannelIntegRule();
		integRuleManagerForMock.updateChannelIntegRule(integRule);
	}
	
	@Test
	public void testUpdateProductIntegRule(){
		ProductIntegRule integRule = new ProductIntegRule();
		integRuleManagerForMock.updateProductIntegRule(integRule);
	}
	
	@Test
	public void testUpdateWiringIntegRule(){
		WiringIntegRule integRule = new WiringIntegRule();
		integRuleManagerForMock.updateWiringIntegRule(integRule);
	}
	
	@Test
	public void testGetChannelIntegRules(){
		integRuleManagerForMock.getChannelIntegRules(1 , 20);
	}
	
	@Test
	public void testGetProductIntegRules(){
		integRuleManagerForMock.getProductIntegRules(1 , 20);
	}
	
	@Test
	public void testGetWiringIntegRules(){
		integRuleManagerForMock.getWiringIntegRules(1 , 20);
	}
	
	@Test
	public void testSearchIntegRule(){
		integRuleManagerForMock.searchIntegRule("1111");
	}
	
	@Test
	public void testCountGetProductIntegRules(){
		integRuleManagerForMock.countGetProductIntegRules();
	}
	
	@Test
	public void testCountGetChannelIntegRules(){
		integRuleManagerForMock.countGetChannelIntegRules();
	}
	
	@Test
	public void testDeleteChannelIntegRule(){
		integRuleManagerForMock.deleteChannelIntegRule("1111");
	}
	
	@Test
	public void testDeleteProductIntegRule(){
		integRuleManagerForMock.deleteProductIntegRule("1111");
	}
	
	@Test
	public void testSaveRewardIntegRule(){
		RewardIntegRule rewardIntegral = new RewardIntegRule();
		integRuleManagerForMock.saveRewardIntegRule(rewardIntegral);
	}
	@Test
	public void testUpdateRewardIntegRule(){
		RewardIntegRule rewardIntegral = new RewardIntegRule();
		integRuleManagerForMock.updateRewardIntegRule(rewardIntegral);
	}
	
	@Test
	public void testInsertGift(){
		Gift gift = new Gift();
		integRuleManagerForMock.insertGift(gift);
	}
	
	@Test
	public void testUpdateGift(){
		Gift gift = new Gift();
		integRuleManagerForMock.updateGift(gift);
	}
	
	@Test
	public void testCountRewardIntegRules(){
		integRuleManagerForMock.countRewardIntegRules();
	}
	
	@Test
	public void testGetRewardIntegRules(){
		integRuleManagerForMock.getRewardIntegRules(1 , 20);
	}
	
	@Test
	public void testDeleteWiringIntegRule(){
		integRuleManagerForMock.deleteWiringIntegRule("1111");
	}
	
	@Test
	public void testUpdateGiftApproval(){
		Gift gift = new Gift();
		integRuleManagerForMock.updateGiftApproval(gift);
	}
	
	@Test
	public void testGetGifts(){
		Gift gift = new Gift();
		integRuleManagerForMock.getGifts(gift,1 , 20);
	}
	
	@Test
	public void testCountGetGifts(){
		Gift gift = new Gift();
		integRuleManagerForMock.countGetGifts(gift);
	}
	
	@Test
	public void testDeleteGift(){
		integRuleManagerForMock.deleteGift("1111");
	}
	@Test
	public void testGetCanUseGiftsByGiftName(){
		integRuleManagerForMock.getCanUseGiftsByGiftName("1111",1 , 20);
	}
	
	@Test
	public void testCountCanUseGistsByGiftName(){
		integRuleManagerForMock.countCanUseGistsByGiftName("1111");
	}
	
	@Test
	public void testGetCanUserGistsCondition(){
		integRuleManagerForMock.countCanUseGistsByGiftName("1111");
	}
	
	@After
	public void tearDown() throws SQLException{
		UserContext.setCurrentUser(null);
		clearData();
	}
	
	@Test
	public void test_saveWiringIntegRule(){
		WiringIntegRule integRule = new WiringIntegRule();
		integRule.setFraction(23.4);
		integRule.setPointdesc("pointdesc");
		integRule.setNumber("39836598");
		integRule.setPointbegintime(new Date());
		integRule.setPointendtime(new Date());
		
		integRuleManager.saveWiringIntegRule(integRule);
		
		try{
			integRuleManager.saveWiringIntegRule(integRule);
		}catch(IntegException ie){
			Assert.assertEquals(IntegExceptionType.IntegRuleExist.getErrCode(), ie.getErrorCode());
		}
		
	}
	
	@Test
	public void test_saveProductIntegRule(){
		ProductIntegRule integRule = new ProductIntegRule();
		integRule.setFraction(23.4);
		integRule.setPointdesc("pointdesc");
		integRule.setNumber("39836598");
		integRule.setPointbegintime(new Date());
		integRule.setPointendtime(new Date());
		
		integRuleManager.saveProductIntegRule(integRule);
		
		try{
			integRuleManager.saveProductIntegRule(integRule);
		}catch(IntegException ie){
			Assert.assertEquals(IntegExceptionType.IntegRuleExist.getErrCode(), ie.getErrorCode());
		}
		
	}
	
	
	
	
	
	@Test
	public void test_saveChannelIntegRule(){
		
		ChannelIntegRule integRule = new ChannelIntegRule();
		integRule.setFraction(23.4);
		integRule.setPointdesc("pointdesc");
		integRule.setNumber("39836598");
		integRule.setPointbegintime(new Date());
		integRule.setPointendtime(new Date());
		
		integRuleManager.saveChannelIntegRule(integRule);
		try{
			integRuleManager.saveChannelIntegRule(integRule);	
		}catch(IntegException ie){
			Assert.assertEquals(IntegExceptionType.IntegRuleExist.getErrCode(), ie.getErrorCode());
		}
		
	}
	
	
	
	/**
	 * 
	 * <p>
	 * Description:test 保存 积分规则<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-1-25
	 * void
	 */
	@Test
	public void test_createIntegRule(){
		
		IntegRule integRule = new ProductIntegRule();
		integRule.setFraction(23.4);
		integRule.setPointdesc("pointdesc");
		integRule.setNumber("39836598");
		integRule.setPointbegintime(new Date());
		integRule.setPointendtime(new Date());
		
		integRuleManager.createIntegRule(integRule, IntegRuleManager.INTEGRAL_PRODUCT);
		integRuleManager.createIntegRule(integRule, IntegRuleManager.INTEGRAL_CHANNEL);
		integRuleManager.createIntegRule(integRule, IntegRuleManager.INTEGRAL_WIRING);
		
		
		integRule = new WiringIntegRule();
		integRule.setFraction(23.4);
		integRule.setPointdesc("pointdesc");
		integRule.setNumber("39836598");
		integRule.setPointbegintime(new Date());
		integRule.setPointendtime(new Date());
	   
		integRuleManager.createIntegRule(integRule, IntegRuleManager.INTEGRAL_PRODUCT);
		integRuleManager.createIntegRule(integRule, IntegRuleManager.INTEGRAL_CHANNEL);
		integRuleManager.createIntegRule(integRule, IntegRuleManager.INTEGRAL_WIRING);
		
		integRule = new ChannelIntegRule();
		integRule.setFraction(23.4);
		integRule.setPointdesc("pointdesc");
		integRule.setNumber("39836598");
		integRule.setPointbegintime(new Date());
		integRule.setPointendtime(new Date());
	   
		integRuleManager.createIntegRule(integRule, IntegRuleManager.INTEGRAL_PRODUCT);
		integRuleManager.createIntegRule(integRule, IntegRuleManager.INTEGRAL_CHANNEL);
		integRuleManager.createIntegRule(integRule, IntegRuleManager.INTEGRAL_WIRING);
		integRule = new IntegRule();
		
		integRuleManager.createIntegRule(integRule, IntegRuleManager.INTEGRAL_PRODUCT);
		integRuleManager.createIntegRule(integRule, IntegRuleManager.INTEGRAL_CHANNEL);
		integRuleManager.createIntegRule(integRule, IntegRuleManager.INTEGRAL_WIRING);
	}
	
	
	
	private void clearData() throws SQLException{
		
		List<String> sqlList = new ArrayList<String>();
		
		sqlList.add("delete t_cust_integrule ci where ci.fcreateuserid = 98564");
		sqlList.add("delete t_cust_integrule ci where ci.fcreateuserid = 1 and ci.fpointbegintime = trunc(sysdate)");
		SpringTestHelper.executeBatch(sqlList);
		
	}
}
