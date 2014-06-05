package com.deppon.crm.module.scheduler.manager;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.scheduler.server.manager.TimeCompareMember;
import com.deppon.crm.module.scheduler.service.testUtil.SpringTestHelper;

public class TimeCompareMemberTest {
	TimeCompareMember timeCompareMember;

	@Before
	public void init() {
		timeCompareMember = SpringTestHelper.get().getBean(TimeCompareMember.class);
	}
	
	@Test
	public void testMember(){
		timeCompareMember.execute();
	}
}
