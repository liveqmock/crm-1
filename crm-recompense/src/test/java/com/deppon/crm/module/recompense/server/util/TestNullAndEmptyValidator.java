package com.deppon.crm.module.recompense.server.util;

import org.junit.Test;

import com.deppon.crm.module.recompense.server.utils.NullAndEmptyValidator;

import junit.framework.TestCase;

public class TestNullAndEmptyValidator extends TestCase {
	/**作者：zouming
	 *创建时间：2012-12-21
	 *最后修改时间：2012-12-21
	 *描述：
	 */
	
	@Test
	public void test(){
		NullAndEmptyValidator nullAndEmptyValidator = new NullAndEmptyValidator();
		NullAndEmptyValidator.checkStringNullAndEmpty("1212");
	}
}
