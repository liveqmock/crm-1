/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title TokenDaoTest.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author suyujun
 * @version 0.1 2013-2-25
 */
package com.deppon.crm.module.common.server.dao;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.server.dao.impl.TodoWorkflowDao;
import com.deppon.crm.module.frameworkimpl.server.dao.ITokenDao;
import com.deppon.crm.module.frameworkimpl.server.dao.impl.TokenDao;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title TokenDaoTest.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author 苏玉军
 * @version 0.1 2013-2-25
 */

public class TokenDaoTest {
	private ITokenDao tokenDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[] { "DaoBeanTest.xml" };
	String key = "crm_xxx_yyy_zzz";
	String value = "token_value";
	Timestamp ts = new Timestamp(System.currentTimeMillis());
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-2-25
	 * @throws java.lang.Exception
	 * void
	 */
	@Before
	public void setUp() throws Exception {
		try {
			if (ctx == null) {
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			tokenDao = ctx.getBean(TokenDao.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-2-25
	 * @throws java.lang.Exception
	 * void
	 */
	@After
	public void tearDown() throws Exception {
		tokenDao.deleteById(key);
	}

	@Test
	public void testInsert() {
		tokenDao.insert(key, value, ts);
		String token = tokenDao.getById(key);
		Assert.assertEquals(value, token);
	}
	
	@Test
	public void testRemoveAll(){
		try {
			tokenDao.insert(key, value, ts);
			//十秒
			Thread.sleep(10000);
			tokenDao.removeAll(new Timestamp(System.currentTimeMillis()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
