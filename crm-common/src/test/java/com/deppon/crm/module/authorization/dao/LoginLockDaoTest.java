package com.deppon.crm.module.authorization.dao;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.server.dao.impl.LoginLockDao;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.authorization.testutil.TestUtil;

import junit.framework.TestCase;

/**
 * 
 * <p>
 * Description:LoginLockDao测试类<br />
 * </p>
 * @title LoginLockDaoTest.java
 * @package com.deppon.crm.module.authorization.dao 
 * @author 侯斌飞
 * @version 0.1 2012-12-26
 */
public class LoginLockDaoTest extends TestCase {
	static LoginLockDao loginDao = null;
	private User user = null;
	static {
		loginDao = TestUtil.loginDao;
	}
	
	/**
	 * 测试之前执行
	 */
	@Before
	protected void setUp() throws Exception {
		user = new User();
		user.setEmpCode(null);
		user.setLoginName("001377");
		user.setPassword("qqqqqq");
		user.setLastLogin(new Date());
		user.setStatus(new Byte("1"));
		user.setInvalidDate(new Date());
		user.setValidDate(new Date());
	}

	/**
	 * 测试之后执行
	 */
	@After
	protected void tearDown() throws Exception {
		
	}
	
	/**
	 * 
	 * <p>
	 * Description: 测试 -- 查询最后一次输入错误密码的时间 -- 测试<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testQueryUserlastErrTime(){
		loginDao.queryUserlastErrTime(user);
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试 -- 新增最后登陆限制时间 <br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testInsertUserlastErrTime(){
		//可以新增
		loginDao.insertUserlastErrTime(user);
		
		//不可以新增
		user.setLoginName("120750");
		loginDao.insertUserlastErrTime(user);
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:测试 -- 清除最后失败时间 <br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testCleanUserlastErrTime(){
		//可以清除
		loginDao.cleanUserlastErrTime(user);
		
		//不可以清除
		user.setLoginName("120750");
		loginDao.cleanUserlastErrTime(user);
	}
	
	
	
	/**
	 * 
	 * <p>
	 * Description:测试 --  清除错误密码次数<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testCleanErrorTimes(){
		loginDao.cleanErrorTimes(user);
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试 -- 查询错误密码次数<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testQueryErrorTimes(){
		loginDao.queryErrorTimes(user);
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试 -- 更新输入错误密码次数<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void testUpdateErrorTimes(){
		//可以更新
		loginDao.updateErrorTimes(user);
		
		//不可以更新
		user.setLoginName("120750");
		loginDao.updateErrorTimes(user);
	}
}
