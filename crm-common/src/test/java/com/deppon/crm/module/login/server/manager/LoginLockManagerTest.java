/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title LoginLockManagerTest.java
 * @package com.deppon.crm.module.login.server.manager 
 * @author ZhuPJ
 * @version 0.1 2012-12-24
 */
package com.deppon.crm.module.login.server.manager;

import java.util.HashMap;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.login.server.manager.impl.LoginLockManager;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title LoginLockManagerTest.java
 * @package com.deppon.crm.module.login.server.manager 
 * @author ZhuPJ
 * @version 0.1 2012-12-24
 */

public class LoginLockManagerTest  extends TestCase{

	private static  ILoginLockManager loginManager;
	private static ApplicationContext ctx = null;
	static String[] xmls = new String[]{"LoginBeanTest.xml"};

	static User user;
	
	static{
		try{
			if (ctx == null) {
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			loginManager = (ILoginLockManager) ctx.getBean("lockManager");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		user = new User();
		user.setId("1");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("238217", null);
		map.put("1", null);
		map.put("250218", null);
		map.put("22241", null);
		map.put("49708", null);
		Set<String> ids = map.keySet();
		user.setDeptids(ids);
		Employee emp = new Employee();
		emp.setId("1");
		Department dept=new Department();
		dept.setId("1");
		emp.setDeptId(dept);
		user.setEmpCode(emp);
		user.setLoginName("089499");
		UserContext.setCurrentUser(user);
		
	}
	
	@Override
	protected void setUp() throws Exception {
		
	}

	@Test
	public void testValidateTimes (){
		// 账号锁定 出错5次后锁定
		loginManager.validateTimes(user);
		loginManager.validateTimes(user);
		loginManager.validateTimes(user);
		loginManager.validateTimes(user);
		loginManager.validateTimes(user);
		loginManager.validateTimes(user);
	}

	@Test
	public void testIsToLockUser(){
		loginManager.isToLockUser(user);
	}
	
	@Test
	public void testIsThirthMins(){
		// 检查上次出错时间
		user.setLoginName("090008");
		loginManager.isThirthMins(user);
		user.setLoginName("089499");
		loginManager.isThirthMins(user);
	}
	
	@Test
	public void testCleanLastErrorTime(){
		user.setLoginName("089499");
		loginManager.cleanLastErrorTime(user);
	}

	@Test
	public void testQueryLastLoginErrTime(){
		user.setLoginName("089499");
		loginManager.queryLastLoginErrTime(user);
	}

	@Test
	public void testQueryErrorTimes(){
		user.setLoginName("089499");
		loginManager.queryErrorTimes(user);
	}

	@Test
	public void testCleanErrorTimes(){
		user.setLoginName("089499");
		loginManager.cleanErrorTimes(user);
	}
	
	@Test
	public void testGetFunction(){
		LoginLockManager llm = new LoginLockManager();
		llm.getLockService();
	}
}
