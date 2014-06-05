package com.deppon.crm.module.report.server.service;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

import com.deppon.crm.module.report.server.service.impl.KeycustReportService;
import com.deppon.crm.module.report.server.util.TestUtil;
import com.deppon.crm.module.report.server.utils.Constant;

public class KeycustReportServiceTest extends TestCase {
	private KeycustReportService keycustReportService;
	private String custNum;
	private Map<String, String> paramsMap;

	public void setUp() {
		keycustReportService = (KeycustReportService) TestUtil
				.getBean("keycustReportService");
		custNum = "40001904";
		paramsMap = new HashMap<String, String>();
		paramsMap.put("abSignNormalCount", Constant.ABSINGNORMALCOUNT);
		paramsMap.put("recompenseCount", Constant.RECOMPENSECOUNT);
		paramsMap.put("custNum", custNum);
	}

	@Test
	public void test_queryShipmentAmount() {
		keycustReportService.queryShipmentAmount(custNum);
	}

	@Test
	public void test_queryProductAmount() {
		keycustReportService.queryProductAmount(custNum);
	}

	@Test
	public void test_queryRoadAmount() {
		keycustReportService.queryRoadAmount(custNum);
	}

	@Test
	public void test_queryShipmentAging() {
		keycustReportService.queryShipmentAging(custNum);
	}

	@Test
	public void test_queryShipmentQuality() {
		keycustReportService.queryShipmentQuality(paramsMap);
	}
}
