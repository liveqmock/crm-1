/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerGroupManagerTest.java
 * @package com.deppon.crm.module.marketing.server.manager.impl 
 * @author zhujunyong
 * @version 0.1 Apr 6, 2012
 */
package com.deppon.crm.module.marketing.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.manager.IUserManager;
import com.deppon.crm.module.marketing.server.manager.impl.UserManager;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;

/**   
 * <p>
 * Description: 自定义用户Manager<br />
 * </p>
 * @title CustomerGroupManagerTest.java
 * @package com.deppon.crm.module.marketing.server.manager.impl 
 * @author ZhuPj
 * @version 0.1 2012-6-28
 */

public class UserManagerTest {

	private IUserManager userManager;
	private User user;
	@Before
	public void setUp() throws Exception {
		userManager = (IUserManager) SpringTestHelper.get().getBean(UserManager.class);
		
		user = new User();
		user.setId("1");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("238217", null);
		map.put("1", null);
		map.put("250218", null);
		map.put("22241", null);
		map.put("26238", null);
		Set<String> ids = map.keySet();
		user.setDeptids(ids);
		Employee emp = new Employee();
		emp.setId("1");
		Department dept=new Department();
		dept.setId("1");
		emp.setDeptId(dept);
		user.setEmpCode(emp);
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testGetUserDeptIds(){
		this.userManager.getUserDeptIds(user.getEmpCode().getId());
	}

	@Test
	public void testQueryManagerUserIdByDeptId(){
		this.userManager.queryManagerUserIdByDeptId(user.getEmpCode().getDeptId().getId());
	}
	@Test
	public void testQueryUsersByDeptIds(){
		List<String> deps = new ArrayList<String>();
		Set<String> sets = user.getDeptids();
		for (String id:sets){
			deps.add(id);
		}
		this.userManager.queryUsersByDeptIds(deps);
	}
	@Test
	public void testGetAllAuthedDepts(){
		this.userManager.getAllAuthedDepts(null);
		this.userManager.getAllAuthedDepts(user);
	}

	@Test
	public void testGetDefaultDep(){
		this.userManager.getDefaultDep(user);
	}
	
	@Test 
	public void testGetAllEmployeesByDeptId(){
		userManager.getAllEmployeesByDeptId(new String[]{"11469", "11333"});
	}
	
	@Test 
	public void testGetEmployeesByDeptId(){
		userManager.getEmployeesByDeptId("11469");
	}
	
	@Test
	public void testQueryDeptListByDeptName(){
		userManager.queryDeptListByDeptName(user, "营业部", 0, 10);
	}

	@Test
	public void testGetFunction(){
		UserManager um = new UserManager();
		um.getAuthorizeService();
	}

}
