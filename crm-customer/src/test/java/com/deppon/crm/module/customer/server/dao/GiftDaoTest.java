package com.deppon.crm.module.customer.server.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.testutils.DateCreateUtil;
import com.deppon.crm.module.customer.server.testutils.DateInitUtil;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.integral.Gift;
import com.opensymphony.xwork2.interceptor.annotations.After;

public class GiftDaoTest extends BeanUtil{
	
	@Before
	public void setUp(){
	}
	
	@After
	public void shutDown() throws Exception{
		DateInitUtil.executeCleanSql();
	}
	
	@Test
	public void testInsertGift(){
		Gift gift = DateCreateUtil.createGift(); 
		gift.setId(null);
		giftDao.insertGift(gift);
		Assert.assertNotNull(gift.getId());
	}
	
	@Test
	public void testUpdateGift(){
		Gift gift = DateCreateUtil.createGift(); 
		gift.setId(null);
		giftDao.insertGift(gift);
		gift.setGiftName("bxj");
		gift.setIsStart(false);
		giftDao.updateGift(gift);
		
		Gift h = giftDao.getGiftById(gift.getId());
		Assert.assertEquals(gift.getGiftName(),h.getGiftName());
	}
	
	@Test
	public void testGetGiftById(){
		Gift gift = DateCreateUtil.createGift(); 
		gift.setId(null);
		giftDao.insertGift(gift);
		
		Gift h = giftDao.getGiftById(gift.getId());
		Assert.assertEquals(gift.getGiftName(),h.getGiftName());
	}
	
	@Test
	public void testGetGifts(){
		Gift gift = DateCreateUtil.createGift(); 
		gift.setId(null);
		giftDao.insertGift(gift);
		
		gift.setCreateDate(null);
		gift.setCreateUser(null);
		gift.setModifyDate(null);
		gift.setModifyUser(null);
		
		List<Gift> list = giftDao.getGifts(gift,0,100);
		Assert.assertTrue(list.size() > 0);
	}
	
	@Test
	public void testCountGetGifts(){
		Gift gift = DateCreateUtil.createGift(); 
		gift.setId(null);
		giftDao.insertGift(gift);
		
		gift.setCreateDate(null);
		gift.setCreateUser(null);
		gift.setModifyDate(null);
		gift.setModifyUser(null);
		
		long count = giftDao.countGetGifts(gift);
		Assert.assertTrue(count>0);
	}
	
	
	@Test
	public void testDeleteGift(){
		Gift gift = DateCreateUtil.createGift(); 
		gift.setId(null);
		giftDao.insertGift(gift);
		giftDao.deleteGift(gift.getId());
		
		Gift h = giftDao.getGiftById(gift.getId());
		Assert.assertNull(h);
	}
}
