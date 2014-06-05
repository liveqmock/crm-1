/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title TimerDeptHandleTest.java
 * @package com.deppon.crm.module.scheduler.manager 
 * @author ZhuPJ
 * @version 0.1 2012-6-2
 */
package com.deppon.crm.module.scheduler.manager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.scheduler.job.BeanContextHelper;
import com.deppon.crm.module.scheduler.server.dao.ILadingStationJobDao;
import com.deppon.crm.module.scheduler.server.manager.TimerDeptHandle;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title TimerDeptHandleTest.java
 * @package com.deppon.crm.module.scheduler.manager 
 * @author ZhuPJ
 * @version 0.1 2012-6-2
 */

public class TimerDeptHandleTest {
	private TimerDeptHandle tp;
	private ILadingStationJobDao ladingStationJobDao;

	@Before
	public void setUp() throws Exception {
		ladingStationJobDao = (ILadingStationJobDao) BeanContextHelper.getApplicationContext().getBean(ILadingStationJobDao.class);
		tp = new TimerDeptHandle();
		tp.setLadingStationJobDao(ladingStationJobDao);
	}

	@After
	public void setDown() throws Exception{
	}
	
	@Test
	public void testProcessData(){
		tp.processLadingStation();
	}
}
