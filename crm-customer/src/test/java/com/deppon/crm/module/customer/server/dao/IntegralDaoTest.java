package com.deppon.crm.module.customer.server.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.testutils.DBUtils;
import com.deppon.crm.module.customer.server.testutils.DateCreateUtil;
import com.deppon.crm.module.customer.server.testutils.TestUtils;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.UserUtil;
import com.deppon.crm.module.customer.shared.domain.integral.AdjustIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.HandRewardIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGift;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGiftCondition;
import com.opensymphony.xwork2.interceptor.annotations.After;

public class IntegralDaoTest extends BeanUtil{

	@Before
	public void setUp() throws Exception {
		DBUtils.initContract();
	}
	@After
	public void tearDown() throws Exception{
//		DateInitUtil.executeCleanSql();
		DBUtils.clean();
	}
	@Test
	public void testInsertAdjustIntegral() {
		AdjustIntegral integral = DateCreateUtil.createAdjustIntegral(); 
		integral.setId(null);
		integralDao.insertAdjustIntegral(integral);
		Assert.assertNotNull(integral);
	}
	
	@Test
	public void testUpdateAdjustIntegral() {
		AdjustIntegral integral = DateCreateUtil.createAdjustIntegral(); 
		integral.setId(null);
		integralDao.insertAdjustIntegral(integral);
		Assert.assertNotNull(integral);
		
		integral.setAdjustType("123123");
		integralDao.updateAdjustIntegral(integral);
		
		AdjustIntegral integral2 = integralDao.getAdjustIntegralById(integral.getId());
		Assert.assertEquals(integral2.getAdjustType(), "123123");
		
	}
	
	@Test
	public void testGetAdjustIntegrals() {
		AdjustIntegral integral = DateCreateUtil.createAdjustIntegral(); 
		integral.setId(null);
		integralDao.insertAdjustIntegral(integral);

		integral.setCreateDate(null);
		integral.setCreateUser(null);
		integral.setModifyDate(null);
		integral.setModifyUser(null);
		List list =  integralDao.searchAdjustIntegrals(integral, 0, 100);
		Assert.assertTrue(list.size() > 0);
	}
	
	@Test
	public void testCountSearchAdjustIntegrals() {
		AdjustIntegral integral = DateCreateUtil.createAdjustIntegral(); 
		integral.setId(null);
		integralDao.insertAdjustIntegral(integral);

		integral.setCreateDate(null);
		integral.setCreateUser(null);
		integral.setModifyDate(null);
		integral.setModifyUser(null);
		long count =  integralDao.countSearchAdjustIntegrals(integral);
		Assert.assertTrue(count > 0);
	}
	
	@Test
	public void testInsertHandRewardIntegral() {
//		HandRewardIntegral integral = DateCreateUtil.createHandRewardIntegral(); 
//		integral.setId(null);
//		integralDao.insertHandRewardIntegral(integral);
//		Assert.assertNotNull(integral);
	}
	
	@Test
	public void testUpdateHandRewardIntegral() {
//		HandRewardIntegral integral = DateCreateUtil.createHandRewardIntegral(); 
//		integral.setId(null);
//		integralDao.insertHandRewardIntegral(integral);
//		Assert.assertNotNull(integral);
//		
//		integral.setIntegralBasicNumber(123);
//		integralDao.updateHandRewardIntegral(integral);
//		
//		HandRewardIntegral integral2 = integralDao.getHandRewardIntegralById(integral.getId());
//		Assert.assertEquals(integral2.getIntegralBasicNumber(),(Integer)123);
//		
	}
	
	@Test
	public void testGetHandRewardIntegralById(){
		integralDao.getHandRewardIntegralById("40");
	}

	
	@Test
	public void testGetHandRewardIntegrals() {
//		HandRewardIntegral integral = DateCreateUtil.createHandRewardIntegral(); 
//		integral.setId(null);
//		integralDao.insertHandRewardIntegral(integral);
//		Assert.assertNotNull(integral);
//		
//		integral.setCreateDate(null);
//		integral.setCreateUser(null);
//		integral.setModifyDate(null);
//		integral.setModifyUser(null);
//		List list =  integralDao.searchHandRewardIntegrals(integral, 0, 100);
//		Assert.assertTrue(list.size() > 0);
	}
	
	@Test
	public void testCountSearchHandRewardIntegrals() {
//		HandRewardIntegral integral = DateCreateUtil.createHandRewardIntegral(); 
//		integral.setId(null);
//		integralDao.insertHandRewardIntegral(integral);
//		Assert.assertNotNull(integral);
//		
//		integral.setCreateDate(null);
//		integral.setCreateUser(null);
//		integral.setModifyDate(null);
//		integral.setModifyUser(null);
//		long count =  integralDao.countSearchHandRewardIntegrals(integral);
//		Assert.assertTrue(count > 0);
	}
	
	@Test
	public void testInsertIntegralConvertGift() {
		IntegralConvertGift integral = DateCreateUtil.createIntegralConvertGift(); 
		integral.setId(null);
		UserUtil.setUserToAdmin();
		integralDao.insertIntegralConvertGift(integral);
		
		Assert.assertNotNull(integral);
	}

	@Test
	public void testUpdateIntegralConvertGift() {
		IntegralConvertGift integral = DateCreateUtil.createIntegralConvertGift(); 
		integral.setId(null);
		UserUtil.setUserToAdmin();
		integralDao.insertIntegralConvertGift(integral);
		
		integral.setConvertIdCard("123123123");
		integralDao.updateIntegralConvertGift(integral);
		
		IntegralConvertGift integral2 = integralDao.getIntegralConvertGiftById(integral.getId());
		Assert.assertEquals(integral2.getConvertIdCard(),"123123123");
		
	}

	public void testGetIntegralConvertGifts() {
		IntegralConvertGift integral = DateCreateUtil.createIntegralConvertGift(); 
		integral.setId(null);
		integralDao.insertIntegralConvertGift(integral);
		
		integral.setCreateDate(null);
		integral.setCreateUser(null);
		integral.setModifyDate(null);
		integral.setModifyUser(null);
		List list =  integralDao.searchIntegralConvertGifts(integral, 0, 100);
		//Assert.assertTrue(list.size() > 0);
	}

	public void testCountSearchIntegralConvertGifts() {
		IntegralConvertGift integral = DateCreateUtil.createIntegralConvertGift(); 
		integral.setId(null);
		integralDao.insertIntegralConvertGift(integral);
		
		integral.setCreateDate(null);
		integral.setCreateUser(null);
		integral.setModifyDate(null);
		integral.setModifyUser(null);
		long count =  integralDao.countSearchIntegralConvertGifts(integral);
		//Assert.assertTrue(count > 0);
	}
	@Test
	public void testSearchIntegralConvertGiftForContactId(){
		List list = new ArrayList<String>();
		list.add("123123");
		list.add("231");
		list.add("3213123");
		integralDao.searchIntegralConvertGiftForContactId(list,0,100);
	}
	@Test
	public void testCountSearchIntegralConvertGiftForContactId(){
		List list = new ArrayList<String>();
		list.add("123123");
		list.add("231");
		list.add("3213123");
		integralDao.countSearchIntegralConvertGiftForContactId(list);
	}
	@Test
	public void testSearchHandRewardIntegralForContactId(){
		List list = new ArrayList<String>();
		list.add("123123");
		list.add("231");
		list.add("3213123");
		integralDao.searchHandRewardIntegralForContactId(list,0,100);
	}
	@Test
	public void testCountSearchHandRewardIntegralForContactId(){
		List list = new ArrayList<String>();
		list.add("123123");
		list.add("231");
		list.add("3213123");
		integralDao.countSearchHandRewardIntegralForContactId(list);
	}
	@Test
	public void testSearchAdjustIntegralForContactId(){
		List list = new ArrayList<String>();
		list.add("123123");
		list.add("231");
		list.add("3213123");
		integralDao.searchAdjustIntegralForContactId(list,0,100);
	}
	@Test
	public void testCountSearchAdjustIntegralForContactId(){
		List list = new ArrayList<String>();
		list.add("123123");
		list.add("231");
		list.add("3213123");
		integralDao.countSearchAdjustIntegralForContactId(list);
	}
	@Test
	public void testSearchIntegralConvertGift(){
		IntegralConvertGiftCondition integralConvertGiftCondition = TestUtils.createBean(IntegralConvertGiftCondition.class);
		integralDao.searchIntegralConvertGift(integralConvertGiftCondition,0,100);
	}
	@Test
	public void testCountSearchIntegralConvertGift(){
		IntegralConvertGiftCondition integralConvertGiftCondition = TestUtils.createBean(IntegralConvertGiftCondition.class);
		integralDao.countSearchIntegralConvertGift(integralConvertGiftCondition);
	}
}
