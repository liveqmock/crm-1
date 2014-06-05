package com.service;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.core.Assert;
import org.junit.Test;

import com.deppon.crm.module.gis.server.service.IDeptQueryService;
import com.deppon.crm.module.gis.server.service.impl.DeptQueryService;
import com.util.SpringTestHelper;

public class DeptQueryTest {
	IDeptQueryService deptQueryService = null;
	
	@Test
	public void test(){
		
		deptQueryService= SpringTestHelper.get().getBean(DeptQueryService.class);
		List<String> list = new ArrayList();
		list.add("DP01111");
		Assert.isNotNull(deptQueryService.deptQueryByStanderCode(list).get(0).getAddress());
		
	}
	
}
