package com.deppon.crm.module.marketing.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.deppon.crm.module.marketing.server.utils.MarketActivityUtils;
import com.deppon.crm.module.marketing.shared.domain.activity.LineDept;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivityConstance;

public class MarketActivityUtilsTest {
	@Test
	public void testAddValueToObject(){
		List<LineDept> list = new ArrayList<LineDept>();
		LineDept[] a = new LineDept[10];
		for(int i=0 ;i<10;i++){
			list.add(new LineDept());
			a[i]=new LineDept();
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", "1");
		map.put("conditionType", "2");
		map.put("leavedDeptName", "上海");
		MarketActivityUtils.addValueToObject(list, map);
		MarketActivityUtils.addValueToObject(a, map);
		for( LineDept l : list){
			System.out.println(l.getId()+" "+l.getConditionType()+" "+l.getLeavedDeptName());
		}
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		for( LineDept l : a){
			System.out.println(l.getId()+" "+l.getConditionType()+" "+l.getLeavedDeptName());
		}		
	}
	@Test
	public void testAAA(){
//		String a ="1234556-12321123-123";
//		System.out.println(a.matches("^[0-9-]{1,20}$"));
//		String b = "12312312332";
//		System.out.println("b: "+b+"   "+b.matches(MarketActivityConstance.NUMBER_REGEX));
//		String c = "123.123";
//		System.out.println("c: "+c+"   "+c.matches(MarketActivityConstance.NUMBER_REGEX));
//		String d = "12345678.123";
//		System.out.println("d: "+d+"   "+d.matches(MarketActivityConstance.NUMBER_REGEX));
//		String e = "0.12";
//		System.out.println("e: "+e+"   "+e.matches(MarketActivityConstance.NUMBER_REGEX));
//		String f = "12345678.1234";
//		System.out.println("f: "+f+"   "+f.matches(MarketActivityConstance.NUMBER_REGEX));
		String f = "12345678.1234";
		System.out.println("f: "+f+"   "+f.matches(MarketActivityConstance.DISCOUNT_REGEX));
		f = "12.12";
		System.out.println("f: "+f+"   "+f.matches(MarketActivityConstance.DISCOUNT_REGEX));
		f = "12.123";
		System.out.println("f: "+f+"   "+f.matches(MarketActivityConstance.DISCOUNT_REGEX));
		f = "100.123";
		System.out.println("f: "+f+"   "+f.matches(MarketActivityConstance.DISCOUNT_REGEX));
		f = "100";
		System.out.println("f: "+f+"   "+f.matches(MarketActivityConstance.DISCOUNT_REGEX));
		f = "9.33";
		System.out.println("f: "+f+"   "+f.matches(MarketActivityConstance.DISCOUNT_REGEX));
		f = "0.33";
		System.out.println("f: "+f+"   "+f.matches(MarketActivityConstance.DISCOUNT_REGEX));
		f = "0.3";
		System.out.println("f: "+f+"   "+f.matches(MarketActivityConstance.DISCOUNT_REGEX));
		f = "1000";
		System.out.println("f: "+f+"   "+f.matches(MarketActivityConstance.DISCOUNT_REGEX));
	}
	@Test
	public void testACV(){
		String aa = "99.83";
		String bb = "100";
		System.out.println((new BigDecimal(aa).divide(new BigDecimal(bb))).toString());
	}

}
