/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title TimerSyncEmployeeTest.java
 * @package com.deppon.crm.module.scheduler.manager 
 * @author Administrator
 * @version 0.1 2012-7-5
 */
package com.deppon.crm.module.scheduler.manager;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.scheduler.server.manager.TimerSyncEmployee;
import com.deppon.crm.module.scheduler.service.testUtil.SpringTestHelper;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title TimerSyncEmployeeTest.java
 * @package com.deppon.crm.module.scheduler.manager 
 * @author 苏玉军
 * @version 0.1 2012-7-5
 */

public class TimerSyncEmployeeTest {
	private TimerSyncEmployee employeeTimer;
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-5
	 * @throws java.lang.Exception
	 * void
	 */
	@Before
	public void setUp() throws Exception {
		employeeTimer = SpringTestHelper.get().getBean(TimerSyncEmployee.class);
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-5
	 * @throws java.lang.Exception
	 * void
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSync() {
		employeeTimer.syncEmployee();
	}

}
