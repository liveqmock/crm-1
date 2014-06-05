package com.deppon.crm.module.report.server.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import com.deppon.crm.module.marketingReport.server.dao.impl.WarnLostCustReportDaoImp;
import com.deppon.crm.module.report.server.util.TestUtil;
import com.deppon.crm.module.report.shared.domain.WarnLostCustDaily;

public class WarnLostDaoTest extends TestCase {
	private WarnLostCustReportDaoImp warnLostCustReportDao;
	private String deptid;
	Date BeginTime;
	Date EndTime;
	private Map<String,String> paramsMap;
	public void setUp() {
		warnLostCustReportDao = (WarnLostCustReportDaoImp) TestUtil.getBean("WarnLostCustReportDao");
		deptid = "11500";
		paramsMap = new HashMap<String, String>();
		/*paramsMap.put("abSignNormalCount", Constant.ABSINGNORMALCOUNT);
		paramsMap.put("recompenseCount", Constant.RECOMPENSECOUNT);
		paramsMap.put("custNum",custNum);*/
	}
	public void test_queryShipmentQuality() {
		Calendar cl= Calendar.getInstance();
		cl.add(Calendar.DATE, -9);
		BeginTime=cl.getTime();
		EndTime=new Date();
		System.out.println(BeginTime);
		System.out.println(EndTime);
		int start=5;
		int end=6;
		List l;
		try{
			l=warnLostCustReportDao.getWarnCustReportDailyBatch(deptid, BeginTime, EndTime,start,end,"dep");
			System.out.println(l.size());
		}catch (Exception e) {
			e.printStackTrace();
		}
		}
	public void test_queryShipmentQualityTotal() {
		Calendar cl= Calendar.getInstance();
		cl.add(Calendar.DATE, -9);
		BeginTime=cl.getTime();
		EndTime=new Date();
		System.out.println(BeginTime);
		System.out.println(EndTime);
		try{
			WarnLostCustDaily total=warnLostCustReportDao.getWarnLostReportTotal(deptid, BeginTime, EndTime,"dep");
			System.out.println(total);
		}catch (Exception e) {
			e.printStackTrace();
		}
		}
	
	public void test_getWarnCustReportCount() {
		Calendar cl= Calendar.getInstance();
		cl.add(Calendar.DATE, -9);
		BeginTime=cl.getTime();
		EndTime=new Date();
		System.out.println(BeginTime);
		System.out.println(EndTime);
		int l;
		try{
			l=warnLostCustReportDao.getWarnCustReportCount(deptid, BeginTime, EndTime,"dep");
			System.out.println(l);
		}catch (Exception e) {
			e.printStackTrace();
		}
		}
}
