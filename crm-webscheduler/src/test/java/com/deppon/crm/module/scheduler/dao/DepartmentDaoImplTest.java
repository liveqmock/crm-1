/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title DepartmentDaoImplTest.java
 * @package com.deppon.crm.module.scheduler.server.dao.impl 
 * @author zhujunyong
 * @version 0.1 May 4, 2012
 */
package com.deppon.crm.module.scheduler.dao;

import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.scheduler.job.BeanContextHelper;
import com.deppon.crm.module.scheduler.server.dao.IDepartmentDao;
import com.deppon.crm.module.scheduler.server.dao.impl.DepartmentDaoImpl;
import com.deppon.crm.module.scheduler.shared.domain.DepartmentExt;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title DepartmentDaoImplTest.java
 * @package com.deppon.crm.module.scheduler.server.dao.impl 
 * @author zhujunyong
 * @version 0.1 May 4, 2012
 */

public class DepartmentDaoImplTest {

	private IDepartmentDao departmentDao;
	
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 May 4, 2012
	 * @throws java.lang.Exception
	 * void
	 */
	@Before
	public void setUp() throws Exception {
		departmentDao = (IDepartmentDao) BeanContextHelper.getApplicationContext().getBean(DepartmentDaoImpl.class);
	}

	@Test
	public void testQueryOADepartments() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -10);
		List<DepartmentExt> list = departmentDao.queryOADepartments(cal.getTime());
		Assert.assertTrue(list.size() > 1000);
	}

}
