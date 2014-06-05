package com.deppon.crm.module.complaint.util;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.complaint.server.util.MemberVirtualDataProducer;

public class MemberVirtualDataProducerTest {
	private MemberVirtualDataProducer memberVirtualDataProducer;
	@Before
	public void initUtil(){
		memberVirtualDataProducer = new MemberVirtualDataProducer();
	}
	
	@Test
	public void testGetMemberByCustNumber(){
		memberVirtualDataProducer.getMemberByCustNumber("001");
	}
	
	@Test
	public void testSearchMemberByTel(){
		memberVirtualDataProducer.searchMemberByTel("12345678910");
	}
}
