/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title LoginLockDaoTest.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author Administrator
 * @version 0.1 2012-7-9
 */
package com.deppon.crm.module.common.server.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.server.dao.ILoginLockDao;
import com.deppon.crm.module.authorization.server.dao.impl.LoginLockDao;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.server.dao.impl.SearchDeptAndEmployeeDaoImpl;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title LoginLockDaoTest.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author 苏玉军
 * @version 0.1 2012-7-9
 */

public class LoginLockDaoTest {
	private User user;
	private ILoginLockDao dao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[] { "DaoBeanTest.xml" };
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-9
	 * @throws java.lang.Exception
	 * void
	 */
	@Before
	public void setUp() throws Exception {
		try {
			if (ctx == null) {
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			dao = ctx.getBean(LoginLockDao.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		user = new User();
		user.setLoginName("070211");
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-9
	 * @throws java.lang.Exception
	 * void
	 */
	@After
	public void tearDown() throws Exception {
		boolean b = dao.cleanUserlastErrTime(user);
		Assert.assertTrue(b);
	}

	/**
	 * Test method for {@link com.deppon.crm.module.authorization.server.dao.impl.LoginLockDao#queryUserlastErrTime(com.deppon.crm.module.authorization.shared.domain.User)}.
	 */
	@Test
	public void testQueryUserlastErrTime() {
		Date date = dao.queryUserlastErrTime(user);
		Assert.assertNull(date);
	}
	
	@Test
	public void testInsertErrTime(){
		boolean b = dao.insertUserlastErrTime(user);
		Assert.assertTrue(b);
	}
}
