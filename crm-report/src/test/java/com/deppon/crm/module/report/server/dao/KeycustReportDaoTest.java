package com.deppon.crm.module.report.server.dao;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.deppon.crm.module.report.server.dao.impl.KeycustReportDao;
import com.deppon.crm.module.report.server.util.TestUtil;
import com.deppon.crm.module.report.server.utils.Constant;

import junit.framework.TestCase;

public class KeycustReportDaoTest extends TestCase {
	private KeycustReportDao keycustReportDao;
	private String custNum;
	private Map<String,String> paramsMap;
	public void setUp() {
		keycustReportDao = (KeycustReportDao) TestUtil
				.getBean("keycustReportDao");
		custNum = "40001904";
		paramsMap = new HashMap<String, String>();
		paramsMap.put("abSignNormalCount", Constant.ABSINGNORMALCOUNT);
		paramsMap.put("recompenseCount", Constant.RECOMPENSECOUNT);
		paramsMap.put("custNum",custNum);
	}

	@Test
	public void test_queryShipmentAmount() {
		keycustReportDao.queryShipmentAmount(custNum);
	}
	@Test
	public void test_queryProductAmount() {
		keycustReportDao.queryProductAmount(custNum);
	}
	@Test
	public void test_queryRoadAmount() {
		keycustReportDao.queryRoadAmount(custNum);
	}
	@Test
	public void test_queryShipmentAging() {
		keycustReportDao.queryShipmentAging(custNum);
	}
	@Test
	public void test_queryShipmentQuality() {
		keycustReportDao.queryShipmentQuality(paramsMap);
	}
}
