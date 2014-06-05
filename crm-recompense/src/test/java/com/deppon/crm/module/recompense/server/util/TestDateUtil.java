package com.deppon.crm.module.recompense.server.util;

import java.util.Date;

import org.junit.Test;

import com.deppon.crm.module.recompense.server.utils.DateUtil;

import junit.framework.TestCase;

public class TestDateUtil extends TestCase {
	/**作者：zouming
	 *创建时间：2012-12-21
	 *最后修改时间：2012-12-21
	 *描述：
	 */
	@Test
	public void test(){
		DateUtil dateUtil = new DateUtil();
		
		try {
			DateUtil.getDateMidnight(new Date(0), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
