package com.deppon.crm.module.servicerecovery.server.dao;

import java.sql.Connection;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.backfreight.server.dao.impl.BackFreightDao;
import com.deppon.crm.module.backfreight.shared.domain.BackFreight;
import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.servicerecovery.server.dao.IFinanceDeptDao;
import com.deppon.crm.module.servicerecovery.server.dao.impl.FinanceDeptDao;
import com.deppon.foss.framework.server.context.AppContext;

/**
 * Description:查询财务部测试用例 FinanceDeptDaoTest.java Create on 2012-11-13 下午4:15:09
 * 
 * @author 华龙
 * @version 1.0
 */
public class FinanceDeptDaoTest extends TestCase {
	static Connection conn = null;
	static FinanceDeptDao financeDeptDao = null;

	BackFreight backFreight = new BackFreight();
	static {
		
		financeDeptDao = TestUtil.financeDeptDao;
	}

	@Test
	public void testFinanceDept() {
		financeDeptDao.searchFinanceDeptList();

	}

}
