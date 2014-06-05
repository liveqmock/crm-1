/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title TimerSyncDepartmentTest.java
 * @package com.deppon.crm.module.scheduler.manager 
 * @author Administrator
 * @version 0.1 2012-7-3
 */
package com.deppon.crm.module.scheduler.manager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.scheduler.server.manager.TimerSyncDepartment;
import com.deppon.crm.module.scheduler.service.testUtil.SpringTestHelper;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title TimerSyncDepartmentTest.java
 * @package com.deppon.crm.module.scheduler.manager 
 * @author 苏玉军
 * @version 0.1 2012-7-3
 */

public class TimerSyncDepartmentTest {
	private TimerSyncDepartment timer;
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-3
	 * @throws java.lang.Exception
	 * void
	 */
	@Before
	public void setUp() throws Exception {
		timer =  SpringTestHelper.get().getBean(TimerSyncDepartment.class);
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-3
	 * @throws java.lang.Exception
	 * void
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		timer.syncDepartment();
	}

}
