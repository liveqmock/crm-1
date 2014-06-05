package com.deppon.crm.module.customer.server.testutils;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.deppon.crm.module.customer.server.utils.DataUtil;
import com.deppon.crm.module.customer.shared.domain.Member;

public class DataUtilTest {
	
	/**
	 * 
	 * <p>
	 * Description:测试把弱类型的值装到obj里面<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-25
	 * void
	 */
	@Test
	public void test_mapValueToObject(){
		// 放值正常的
		Member member = new Member();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("custName", "客户姓名");
		map.put("finOver", false);
		DataUtil.mapValueToObject(map, member);
		Assert.assertEquals("客户姓名", member.getCustName());

		try{
			   member = new Member();
			   map = new HashMap<String,Object>();
			  map.put("custName", 123);
			  DataUtil.mapValueToObject(map, member);
//			  Assert.assertEquals("客户姓名", member.getCustName());
			}catch(RuntimeException re){
//				Assert.assertEquals("坑爹的程序员bxj,写的代码有bug", re.getMessage());
			}
		
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试把强类型转换为弱类型<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-25
	 * void
	 */
	@Test
	public void test_changeObjectToMap(){
		Member member = new Member();
		member.setCustName("客户姓名");
		member.setCustNumber("custNumber");
		member.setId("12324");
		Map<String,Object> map = DataUtil.changeObjectToMap(member);
		
		Assert.assertEquals("客户姓名", map.get("custName"));
		Assert.assertEquals("custNumber", map.get("custNumber"));
		Assert.assertEquals("12324", map.get("id"));
	}
	

}
