package com.deppon.crm.module.customer.server.service.impl;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.ChangeMemberDept;

/**
 * @author Administrator
 *
 */
public class ChangeMemberDeptServiceTest extends BeanUtil{
	ChangeMemberDept changeMemberDept = new ChangeMemberDept();
	@Before
	public void setUp() throws Exception {
	}
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-26
	 @return void
	 */
	@Test
	public void testSaveChangeMemberDept(){
		changeMemberDept.setCreateDate(new Date());
		changeMemberDept.setCreateUser("小李子专用");
		changeMemberDept.setFromDeptId("321546");
		changeMemberDept.setFromDeptName("io撒旦离开");
		changeMemberDept.setId("123123");
		changeMemberDept.setMemberId("564545");
		changeMemberDept.setMemberNumber("123123123");
		changeMemberDept.setModifyDate(new Date());
		changeMemberDept.setModifyUser("TL");
		changeMemberDept.setReason("没道理");
		changeMemberDept.setToDeptId("10024");
		changeMemberDept.setToDeptName("成都营业部");
		changeMemberDept.setWorkFlowId(2154645);
		try {
			changeMemberDeptService.saveChangeMemberDept(changeMemberDept);
		} catch (Exception e) {}
	}
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-26
	 @return void
	 */
	@Test
	public void testGetChangeMemberDeptByWorkFlowId(){
		changeMemberDeptService.getChangeMemberDeptByWorkFlowId("123123");
	}
}
