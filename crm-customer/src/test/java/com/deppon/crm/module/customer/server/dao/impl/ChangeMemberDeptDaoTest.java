package com.deppon.crm.module.customer.server.dao.impl;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.testutils.TestUtils;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.ChangeMemberDept;
public class ChangeMemberDeptDaoTest extends BeanUtil{
	@Before
	public void setUp(){
	}

	@Test
	public void testGetChangeMemberDeptByWorkFlowId(){
		String string = "111";
		changeMemberDeptDao.getChangeMemberDeptByWorkFlowId(string);
	}
	@Test
	public void testInsertChangeMemberDept(){
		ChangeMemberDept changeMemberDept = TestUtils.createBean(ChangeMemberDept.class);
		changeMemberDeptDao.insertChangeMemberDept(changeMemberDept);
	}
}

