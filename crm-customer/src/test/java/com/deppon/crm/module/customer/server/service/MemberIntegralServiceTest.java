package com.deppon.crm.module.customer.server.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.Assert;

import org.junit.Test;

import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;

public class MemberIntegralServiceTest extends BeanUtil{
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void test_getMemberIntegralByMemberId(){
		
		MemberIntegral memberIntegral =	memberIntegralService.getMemberIntegralByMemberId("12234");
		Assert.assertEquals("12234", memberIntegral.getMember().getId());
		
		memberIntegral = memberIntegralService.getMemberIntegralByMemberId("");
		Assert.assertNull(memberIntegral);
		
		memberIntegral = memberIntegralService.getMemberIntegralByMemberId("400279523");
		Assert.assertEquals("400279523", memberIntegral.getMember().getId());

		memberIntegralService.deleteMemberIntegral(memberIntegral.getId());
		
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void test_getMemberIntegralByMemberIds(){
		Set<String> memberIds = new TreeSet<String>();
		memberIds.add("12234");
		List<MemberIntegral> miList = memberIntegralService.getMemberIntegralByMemberIds(memberIds);
		Assert.assertEquals(1, miList.size());
	}
}
