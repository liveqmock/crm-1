package com.deppon.crm.module.duty.dao;

import java.math.BigDecimal;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.duty.server.dao.IBasicLevelDao;
import com.deppon.crm.module.duty.server.dao.IProcResultDao;
import com.deppon.crm.module.duty.util.TestUtil;

public class ProcResultDaoTest extends TestCase {
	IProcResultDao resultDao = TestUtil.procResultDao;
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testProcResultByLevelId() {
		resultDao.getProcResultByLevelId(new BigDecimal("800001965"));
	}
}
