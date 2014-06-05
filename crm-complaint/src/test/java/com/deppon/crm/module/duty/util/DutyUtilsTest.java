package com.deppon.crm.module.duty.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;

import com.deppon.crm.module.duty.server.util.DutyConstants;
import com.deppon.crm.module.duty.server.util.DutyUtils;

public class DutyUtilsTest extends TestCase{

	//工单责任辅助类-两个时间相隔天数
	@Test
	public void testDiffer() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 =  sdf.parse("2013-03-03 18:18:19");
		Date date2 =  sdf.parse("2013-03-03 18:18:18");
		Long excp = -1000L;
		Long actu = DutyUtils.differ(date1, date2);
		assertEquals(excp, actu);
	}
	//工单责任辅助类-判断时间是否为空
	@Test
	public void testDateIsNull() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 =  sdf.parse("2013-03-03 18:18:19");
		Date date2 =  sdf.parse("2013-03-03 18:18:18");
		boolean actu = false;
		boolean excp = DutyUtils.dateIsNull(date1);
		assertEquals(excp, actu);
		int excp1 = DutyUtils.dateIsNull(null, null);
		int actu1 = 1;
		assertEquals(excp1, actu1);
		int excp2 = DutyUtils.dateIsNull(null, date2);
		int actu2 = 2;
		assertEquals(excp2, actu2);
		int excp3 = DutyUtils.dateIsNull(date1, date2);
		int actu3 = 3;
		assertEquals(excp3, actu3);
		
	}
	//工单责任辅助类-判断时间是否为空
	@Test
	public void testCheckArrayCondition(){
		Date[] date = {new Date(),null};
		Date[] date1 = {null,null};
		boolean actu = true;
		boolean excp = DutyUtils.checkArrayCondition("", "", "", "", "", "", date);
		assertEquals(excp, actu);
		boolean actu1 = false;
		boolean excp1 = DutyUtils.checkArrayCondition("11", "22", "33", "44", "55", "66", date1);
		assertEquals(excp1, actu1);
	}
	//工单责任辅助类-检查数组是否全为空
	@Test
	public void testObjHasNull(){
		boolean actu = true;
		boolean excp = DutyUtils.objHasNull("");
		assertEquals(excp, actu);
		
	}
	//工单责任辅助类-检查数组是否存在空
	@Test
	public void testObjHasNotNull(){
		Date[] date = {new Date(),null};
		boolean actu = true;
		boolean excp = DutyUtils.objHasNotNull(date);
		assertEquals(excp, actu);
		
	}
	@Test
	public void testGetSearchReportType(){
		Set<String> roleIds = new HashSet<String>();
		roleIds.add(DutyConstants.RECEIVE_AUTH_COMPLAINTEMP);
		DutyUtils.getSearchReportType(roleIds);
		roleIds = new HashSet<String>();
		roleIds.add(DutyConstants.RECEIVE_AUTH_COMPLAINTMAN);
		DutyUtils.getSearchReportType(roleIds);
		roleIds = new HashSet<String>();
		roleIds.add(DutyConstants.RECEIVE_AUTH_UNUSUALEMP);
		DutyUtils.getSearchReportType(roleIds);
		roleIds = new HashSet<String>();
		roleIds.add(DutyConstants.RECEIVE_AUTH_UNUSUALMAN);
		DutyUtils.getSearchReportType(roleIds);
		roleIds = new HashSet<String>();
		roleIds.add("ccc");
		try {
			DutyUtils.getSearchReportType(roleIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
