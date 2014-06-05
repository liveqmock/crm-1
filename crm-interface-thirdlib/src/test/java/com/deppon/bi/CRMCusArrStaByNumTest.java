package com.deppon.bi;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import com.deppon.bi.arrived.CRMCusArrStaByNum;
import com.deppon.bi.arrived.CRMCusArrStaByNumService;
import com.deppon.bi.leave.CRMCusStaByNum;
import com.deppon.bi.leave.CRMCusStaByNumService;

public class CRMCusArrStaByNumTest {

	CRMCusArrStaByNum crmCusArrStaByNum;
	CRMCusStaByNum crmCusStaByNum;
	
	@Before
	public void init(){
		crmCusArrStaByNum = new CRMCusArrStaByNumService().getCRMCusArrStaByNumPort();
		
		crmCusStaByNum = new CRMCusStaByNumService().getCRMCusStaByNumPort();
	}
	
	@Test
	public void test1() throws ParseException{
		String result = crmCusArrStaByNum.newOperation("20101218-05599686", new SimpleDateFormat("yyyy-MM-dd").parse("2012-05-03"));
		System.out.println(result);
	}
	@Test
	public void test2() throws ParseException{
		String result = crmCusStaByNum.newOperation("20101218-05599686", new SimpleDateFormat("yyyy-MM-dd").parse("2012-05-03"));
		
		String[] cs = result.split(";");
		System.out.println(cs.length);
		for (String string : cs) {
			System.out.println("--------------------");
			System.out.println(string);
			String[] des = string.split(",");
			for (String string2 : des) {
				
				System.out.println("     "+string2);
			}
		}
//		System.out.println(result);
	}
}
