package com.deppon.crm.module.duty.dao;

import java.math.BigDecimal;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.duty.server.dao.IBasicLevelDao;
import com.deppon.crm.module.duty.util.TestUtil;

public class BasicLevelDaoTest extends TestCase {
	IBasicLevelDao basicLevelDao = TestUtil.basicLevelDao;
	@Before
	public void setUp() throws Exception {
		
		
	}

	@Test
	public void testSelectBusScope() {
		basicLevelDao.selectBusScope(new BigDecimal("800001665"));
	}
	
}
