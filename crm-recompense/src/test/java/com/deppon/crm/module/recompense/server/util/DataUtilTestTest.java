package com.deppon.crm.module.recompense.server.util;

import java.util.Date;

import junit.framework.TestCase;

import org.junit.Test;

import com.deppon.crm.module.recompense.server.utils.DateUtil;
import com.deppon.crm.module.recompense.server.utils.NullAndEmptyValidator;

/**
 * 
 * <p>
 * Description:NullAndEmptyValidatorTest<br />
 * </p>
 * 
 * @title NullAndEmptyValidatorTest.java
 * @package com.deppon.crm.module.recompense.server.util
 * @author 华龙
 * @version 0.1 2012-12-21
 */
public class DataUtilTestTest extends TestCase {
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-21 void
	 */
	@Test
	public void testGetDateMidnight() {
		try {
			DateUtil.getDateMidnight(new Date("1111223232"), 2323);
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

}