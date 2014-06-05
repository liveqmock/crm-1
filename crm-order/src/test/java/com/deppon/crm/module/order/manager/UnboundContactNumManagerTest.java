/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title UnboundContactNumManagerTest.java
 * @package com.deppon.crm.module.order.manager 
 * @author Administrator
 * @version 0.1 2012-9-10
 */
package com.deppon.crm.module.order.manager;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.server.dao.impl.UserDao;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContactView;
import com.deppon.crm.module.order.server.manager.impl.UnboundContactNumManager;
import com.deppon.crm.module.order.util.TestUtil;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.AppContext;
import com.deppon.foss.framework.server.context.UserContext;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title UnboundContactNumManagerTest.java
 * @package com.deppon.crm.module.order.manager
 * @author 苏玉军
 * @version 0.1 2012-9-10
 */

public class UnboundContactNumManagerTest {

	private static UnboundContactNumManager manager;
	public static UserDao userDao;
	static User user = null;
	static {
		user = new User();
		user.setId("23195");
		Department dept = new Department();
		dept.setId("223");
		dept.setDeptCode("dp129182");
		dept.setDeptName("少林寺");
		dept.setStandardCode("110");
		Employee emp = new Employee();
		emp.setDeptId(dept);
		emp.setEmpCode("08888");
		emp.setEmpName("张三丰");
		user.setEmpCode(emp);
		UserContext.setCurrentUser(user);

		manager = TestUtil.manager;
		userDao = TestUtil.userDao;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-9-10
	 * @throws java.lang.Exception
	 *             void
	 */
	@Before
	public void setUp() throws Exception {
		User user = new User();
		Department dept = new Department();
		dept.setDeptName("上海青浦营业部");
		dept.setPhone("02131350606");
		Employee e = new Employee();
		e.setDeptId(dept);
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-9-10
	 * @throws java.lang.Exception
	 *             void
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.order.server.manager.impl.UnboundContactNumManager#queryContactAndBoundInfo(java.lang.String)}
	 * .
	 */
	@Test
	public void testQueryContactAndBoundInfo() {
		String contactNumber = "100011";
		Map<String, Object> map = manager
				.queryContactAndBoundInfo(contactNumber);
		Assert.assertNotNull(map);
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.order.server.manager.impl.UnboundContactNumManager#unboundContactNumber(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
/*	@Test
	public void testUnboundContactNumber() {
		String source = Constant.ORDER_SOURCE_ALIBABA;
		String registerId = "xhb_paytest";
		String contactNumber = "2504";
		Map<String, Object> map = manager
				.queryContactAndBoundInfo(contactNumber);
		ContactView view = (ContactView) map.get("contactView");
		Contact contact = view.getContact();try {
			boolean b = manager.unboundContactNumber(contactNumber, registerId,
					"ONLINE");
			Assert.assertTrue(b);
			
		} catch (Exception e) {
		}
	}*/

	@Test
	public void testBoundInfos() {
		String contactNum = "100011";
		List<RegisterInfo> regList = manager.boundInfos(contactNum);
		Assert.assertNotNull(regList);

		try {
			manager.boundInfos(null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testQueryContactViewByNum() {
		String contactNum = "100011";
		ContactView cv = manager.queryContactViewByNum(contactNum);
		String contactId = cv.getContact().getId();
		List<RegisterInfo> infos = manager.boundInfos(contactId);
		Assert.assertNotNull(cv);
		Assert.assertNotNull(infos);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-20
	 * @throws CrmBusinessException
	 *             void
	 */
	@Test
	public void testsendSmsToContact() throws CrmBusinessException {

		Contact contact = new Contact();
		contact.setNumber("1234");
		contact.setMobilePhone("123432423");
		contact.setDutyDept("1111");
		String source = "发送";
		// User user = userDao.getById("26272");
		String registerId = "11";
		try {
			manager.sendSmsToContact(contact, user, registerId);
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
