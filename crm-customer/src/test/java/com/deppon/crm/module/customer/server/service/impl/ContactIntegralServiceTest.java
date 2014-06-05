/**
 * 
 */
package com.deppon.crm.module.customer.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;

/**
 * @author Administrator
 *
 */
public class ContactIntegralServiceTest extends BeanUtil{
	ContactIntegral contactIntegral = new ContactIntegral();
	List<String> contactIdList = new ArrayList<String>();
	@Before
	public void setUp() throws Exception {
		
//		approvingWokflowDataDao = (IApprovingWokflowDataDao)new ApprovingWorkflowDataDao();
//		clearAlterMemberData();
		// 初始化修改会员模块数据
//		DataTestUtil.initAlterMemberData();
	}
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-26
	 @return void
	 */
	@Test
	public void testGetContactIntegralDao(){
//		contactIntegralService.getContactIntegralDao();
	}
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-26
	 @return void
	 */
//	@Test
//	public void testSetContactIntegralDao(){
//		contactIntegralService.setContactIntegralDao(contactIntegralDao);
//	}
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-26
	 @return void
	 */
//	@Test
//	public void testCreateContactIntegral(){
//		contactIntegral.setId("21213");
//		contactIntegral.setCreateUser("我弄的");
//		contactIntegral.setCurrentTotalScore(213);
//		contactIntegralService.createContactIntegral(contactIntegral);
//	}
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-26
	 @return void
	 */
	@Test
	public void testUpdateContactIntegral(){
		contactIntegral.setCreateDate(new Date());
		contactIntegral.setId("21213");
		contactIntegral.setCreateUser("我弄的");
		Assert.assertNotNull(contactIntegralService.updateContactIntegral(contactIntegral));
		
	}
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-26
	 @return void
	 */
	@Test
	public void testGetContactIntegral(){
	contactIntegralService.getContactIntegral("23123");
	}
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-26
	 @return void
	 */
	@Test
	public void testGetContactIntegralByContactId(){
		Assert.assertNotNull(contactIntegralService.getContactIntegralByContactId("12312"));
		Assert.assertNull(contactIntegralService.getContactIntegralByContactId(null));
	}
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-26
	 @return void
	 */
	@Test
	public void testSearchContactIntegralsBycontactIdList(){
		 int start = 1;
		 int limit = 100;
		 Assert.assertNotNull(contactIntegralService.searchContactIntegralsBycontactIdList(contactIdList, start, limit));
		 
	}
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-26
	 @return void
	 */
	@Test
	public void testCountSearchContactIntegralsBycontactIdList(){
		Assert.assertNotNull(contactIntegralService.countSearchContactIntegralsBycontactIdList(contactIdList));
	}
	@Test
	public void testSearchContactIntegralsByMemberId(){
		Assert.assertNotNull(contactIntegralService.searchContactIntegralsByMemberId("123123"));
	}
}
