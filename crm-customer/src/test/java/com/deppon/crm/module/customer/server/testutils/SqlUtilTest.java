/**
 * 
 */
package com.deppon.crm.module.customer.server.testutils;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.customer.server.utils.SqlUtil;

import junit.framework.TestCase;

/**
 * @author Administrator
 *
 */
public class SqlUtilTest extends TestCase{
	SqlUtil util = new SqlUtil();
	@SuppressWarnings("static-access")
	public void testGetInSql(){
		List<String> list = new ArrayList<String>();
		list.add("select 周恒春");
		list.add("select 王伟");
		list.add("select 李盛");
		list.add("select 李学兴");
		list.add("select 潘光均");
		list.add("select 李国文");
		list.add("select 王明明");
		util.getInSql(list);
	}
	@SuppressWarnings("static-access")
	public void testGetLikeField(){
		String field = "换个发型换个心情";
		util.getLikeField(field);
		field = null;
		util.getLikeField(field);
	}
}
