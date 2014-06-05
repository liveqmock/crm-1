package com.deppon.crm.module.customer.server.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.testutils.DBUtils;
import com.deppon.crm.module.customer.server.testutils.DateCreateUtil;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
/**
 * 
 * @description 联系人积分测试类  
 * @author 潘光均
 * @version 0.1 2012-6-16
 *@date 2012-6-16
 */
public class ContactIntegralDaoTest extends BeanUtil{
	@Before
	public void setUp() throws Exception{
		DBUtils.clean();
		DBUtils.initContract();
	}

	/**
	 * @description function.  
	 * @author 潘光均
	 * @version 0.1 2012-6-16
	 * @param 
	 *@date 2012-6-16
	 * @return void
	 * @throws Exception 
	 * @update 2012-6-16 下午1:53:56
	 */
	@After
	public void tearDown() throws Exception {
		DBUtils.clean();
	}
	
	@Test
	public void testCreateContactIntegral(){
		ContactIntegral contactIntegral = DateCreateUtil.createContactIntegral();
		contactIntegral.getContact().setId("1000001");
		contactIntegralDao.createContactIntegral(contactIntegral);
	}
	@Test
	public void testUpdateContactIntegral(){
		ContactIntegral contactIntegral = DateCreateUtil.createContactIntegral();
		contactIntegral.getContact().setId("1000001");
		contactIntegralDao.createContactIntegral(contactIntegral);
		
		contactIntegral.setCurrentTotalScore(200);
		contactIntegralDao.updateContactIntegral(contactIntegral);
	}
	@Test
	public void testGetContactIntegral(){
		ContactIntegral contactIntegral = DateCreateUtil.createContactIntegral();
		contactIntegral.getContact().setId("1000001");
		contactIntegralDao.createContactIntegral(contactIntegral);
		
		Assert.assertNull(contactIntegralDao.getContactIntegral(contactIntegral.getId()));
	}
	
	@Test
	public void testSearchContactIntegrals(){
		ContactIntegral contactIntegral = DateCreateUtil.createContactIntegral();
		contactIntegral.getContact().setId("1000001");
		contactIntegralDao.createContactIntegral(contactIntegral);
		
		contactIntegral.setCreateUser(null);
		contactIntegral.setCreateDate(null);
		contactIntegral.setModifyDate(null);
		contactIntegral.setModifyUser(null);
		Assert.assertTrue(contactIntegralDao.searchContactIntegrals(contactIntegral).size() > 0);
	}
	
	@Test
	public void testSearchContactIntegralsBycontactIdList(){
		List<String> list = new ArrayList<String>();
		list.add("123123");
		list.add("231");
		list.add("3213123");
		contactIntegralDao.searchContactIntegralsBycontactIdList(list,0,100);
	}
	
	@Test
	public void testCountSearchContactIntegralsBycontactIdList(){
		List<String> list = new ArrayList<String>();
		list.add("123123");
		list.add("231");
		list.add("3213123");
		contactIntegralDao.countSearchContactIntegralsBycontactIdList(list);
	}
	
	@Test
	public void testSearchContactIntegralsByMemberId(){
		String string = "123";
		contactIntegralDao.searchContactIntegralsByMemberId(string);
	}
}

