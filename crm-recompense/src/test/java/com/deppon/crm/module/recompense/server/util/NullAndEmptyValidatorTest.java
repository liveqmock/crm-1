package com.deppon.crm.module.recompense.server.util;

import junit.framework.TestCase;

import org.junit.Test;

import com.deppon.crm.module.recompense.server.utils.NullAndEmptyValidator;

/**
 * 
 * <p>
 * Description:NullAndEmptyValidatorTest<br />
 * </p>
 * @title NullAndEmptyValidatorTest.java
 * @package com.deppon.crm.module.recompense.server.util 
 * @author 华龙
 * @version 0.1 2012-12-21
 */
public class NullAndEmptyValidatorTest extends TestCase {
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 华龙
	 * @version 0.1 2012-12-21
	 * void
	 */
	@Test
	public void testStringNullPreform(){
		NullAndEmptyValidator.checkStringNullAndEmpty("111");
		
	}
	/**
	 * 
	 * <p>
	 * Description:testCheckStringNullAndEmpty<br />
	 * </p>
	 * @author 华龙
	 * @version 0.1 2012-12-21
	 * void
	 */
	@Test
	public void testCheckStringNullAndEmpty(){
		NullAndEmptyValidator.checkStringNullAndEmpty("1");
	}
	/**
	 * 
	 * <p>
	 * Description:testObjectNullPreform<br />
	 * </p>
	 * @author 华龙
	 * @version 0.1 2012-12-21
	 * void
	 */
	@Test
	public void testObjectNullPreform(){
		NullAndEmptyValidator.objectNullPreform(new Object());
		
		
	}
	

}
