package com.deppon.crm.module.customer.server.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.testutils.DateCreateUtil;
import com.deppon.crm.module.customer.server.testutils.TestUtils;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralSearchCondition;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;
import com.opensymphony.xwork2.interceptor.annotations.After;

public class MemberIntegralDaoTest extends BeanUtil{
	
	@Before
	public void setUp(){
	}
	@After
	public void shutDown() throws Exception{
//		DateInitUtil.executeCleanSql();
	}
	@Test
	public void testInsertMemberIntegral(){
		MemberIntegral memberIntegral = DateCreateUtil.createMemberIntegral();
		memberIntegral.setId(null);
		memberIntegralDao.insertMemberIntegral(memberIntegral);
		Assert.assertNotNull(memberIntegral.getId());
	}
	
	@Test
	public void testUpdateMemberIntegral(){
		MemberIntegral memberIntegral = DateCreateUtil.createMemberIntegral();
		memberIntegralDao.insertMemberIntegral(memberIntegral);
		
		memberIntegral.setTotalScore(7);
		
		memberIntegralDao.updateMemberIntegral(memberIntegral);
		MemberIntegral memberIn2 = memberIntegralDao.getMemberIntegralById(memberIntegral.getId());
		Assert.assertEquals(memberIntegral.getTotalScore(),7);
	}

	@Test
	public void testGetMemberIntegralById(){
		MemberIntegral memberIntegral = DateCreateUtil.createMemberIntegral();
		memberIntegral.setId(null);
		memberIntegralDao.insertMemberIntegral(memberIntegral);
		
		MemberIntegral memberIn = memberIntegralDao.getMemberIntegralById(memberIntegral.getId());
	}
	@Test
	public void testGetMemberIntegrals(){
		MemberIntegral memberIntegral = DateCreateUtil.createMemberIntegral();
		memberIntegralDao.insertMemberIntegral(memberIntegral);
		memberIntegral.setCreateDate(null);
		memberIntegral.setCreateUser(null);
		memberIntegral.setModifyDate(null);
		memberIntegral.setModifyUser(null);
		List<MemberIntegral> list = memberIntegralDao.searchMemberIntegrals(memberIntegral,0,100);
		Assert.assertTrue(list.size() >0);
	}
	
	@Test
	public void testCountSearchMemberIntegrals(){
		MemberIntegral memberIntegral = DateCreateUtil.createMemberIntegral();
		memberIntegralDao.insertMemberIntegral(memberIntegral);
		memberIntegral.setCreateDate(null);
		memberIntegral.setCreateUser(null);
		memberIntegral.setModifyDate(null);
		memberIntegral.setModifyUser(null);
		long count = memberIntegralDao.countSearchMemberIntegrals(memberIntegral);
		Assert.assertTrue(count >0);
	}
	
	@Test
	public void testDeleteMemberIntegral(){
		MemberIntegral memberIntegral = DateCreateUtil.createMemberIntegral();
		memberIntegralDao.insertMemberIntegral(memberIntegral);
		memberIntegralDao.deleteMemberIntegral(memberIntegral.getId());
		
		MemberIntegral memberIn2 = memberIntegralDao.getMemberIntegralById(memberIntegral.getId());
		Assert.assertNull(memberIn2);
		
	}
	@Test
	public void testSearchMemberIntegralsForCondition(){
		IntegralSearchCondition integralSearchCondition = new IntegralSearchCondition();
		integralSearchCondition.setMemberNum("000525");
		integralSearchCondition.setMemberName("大大");
		memberIntegralDao.searchMemberIntegralsForCondition(integralSearchCondition,0,1000);
	}
	@Test
	public void testCountSearchMemberIntegralsForCondition(){
		IntegralSearchCondition integralSearchCondition = TestUtils.createBean(IntegralSearchCondition.class);
		memberIntegralDao.countSearchMemberIntegralsForCondition(integralSearchCondition);
	}
	@Test
	public void testSearchMemberIntegrals() {
		MemberIntegral memberIntegral = new MemberIntegral();
		memberIntegral.setTotalScore(100);
		memberIntegralDao.searchMemberIntegrals(memberIntegral, 0, 10);
	}
	@Test
	public void testDeleteMemberIntegralByMemberId() {
		String id = "";
		memberIntegralDao.deleteMemberIntegral(id);
	}
}
