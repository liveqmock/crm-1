package com.deppon.crm.module.customer.server.manager;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.UserUtil;
import com.deppon.crm.module.organization.shared.domain.Department;

public class BaseDataManagerTest extends BeanUtil{
	
	@Before
	public void setUp(){
		UserUtil.setUserToAdmin();
	}
	@Test
	public void testGetBusinessDivesionOfficeMarketingGroup(){
		Department chil = new Department();
		chil.setId("11495");
		chil.setDeptSeq(".103.104.265.475.50502.11494.11495.");
		Department department =	baseDataManager.getBusinessDivesionOfficeMarketingGroup(chil);
		Assert.assertEquals(department.getId(),"315233");
	}
}
