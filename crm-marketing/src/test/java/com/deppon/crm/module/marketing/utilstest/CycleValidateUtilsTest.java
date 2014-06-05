/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CycleValidateUtilsTest.java
 * @package com.deppon.crm.module.marketing.manager 
 * @author Administrator
 * @version 0.1 2012-4-16
 */
package com.deppon.crm.module.marketing.utilstest;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.marketing.server.utils.CycleValidateUtils;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CycleValidateUtilsTest.java
 * @package com.deppon.crm.module.marketing.manager 
 * @author 苏玉军
 * @version 0.1 2012-4-16
 */

public class CycleValidateUtilsTest {

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-16
	 * @throws java.lang.Exception
	 * void
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-16
	 * @throws java.lang.Exception
	 * void
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.utils.CycleValidateUtils#isConditonNull(com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail)}.
	 */
	@Test
	public void testIsConditonNull() {
		/*
		 * 
		 * detail == null
				|| StringUtils.isEmpty(detail.getDeptId()) ||(
						detail.getQueryDate() == null
						&& StringUtils.isEmpty(detail.getCustNumber())
						&& StringUtils.isEmpty(detail.getGroupId()) 
						&& StringUtils.isEmpty(detail.getEmpName()));
		
		*/
		
		CustomerGroupDetail detail =null;
		boolean b = CycleValidateUtils.isConditonNull(detail);
		Assert.assertTrue(b);
		
		detail = new CustomerGroupDetail();
		b = CycleValidateUtils.isConditonNull(detail);
		Assert.assertTrue(b);
		
		detail.setDeptId("12334");
		detail.setQueryDate(new Date());
		detail.setCustNumber("1234455");
		detail.setGroupId("1234455");
		detail.setEmpName("1234455");
		b = CycleValidateUtils.isConditonNull(detail);

		detail.setDeptId("12334");
		detail.setQueryDate(null);
		detail.setCustNumber("1234455");
		detail.setGroupId("1234455");
		detail.setEmpName("1234455");
		b = CycleValidateUtils.isConditonNull(detail);

		detail.setDeptId("12334");
		detail.setQueryDate(new Date());
		detail.setCustNumber(null);
		detail.setGroupId(null);
		detail.setEmpName(null);
		b = CycleValidateUtils.isConditonNull(detail);

		detail.setDeptId("12334");
		detail.setQueryDate(new Date());
		detail.setCustNumber("1234455");
		detail.setGroupId(null);
		detail.setEmpName(null);
		b = CycleValidateUtils.isConditonNull(detail);

		detail.setDeptId("12334");
		detail.setQueryDate(new Date());
		detail.setCustNumber("1234455");
		detail.setGroupId("1234455");
		detail.setEmpName(null);
		b = CycleValidateUtils.isConditonNull(detail);

	}

}
