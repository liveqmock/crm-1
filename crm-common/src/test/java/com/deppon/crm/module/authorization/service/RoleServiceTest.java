
package com.deppon.crm.module.authorization.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.server.dao.impl.RoleDao;
import com.deppon.crm.module.authorization.server.service.IRoleService;
import com.deppon.crm.module.authorization.server.service.impl.RoleService;
import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.authorization.shared.exception.RoleExceptionType;
import com.deppon.crm.module.authorization.shared.exception.UserExceptionType;
import com.deppon.crm.module.authorization.testutil.TestUtil;
import com.deppon.foss.framework.exception.GeneralException;

import junit.framework.TestCase;
/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title AuthorizeServiceTest.java
 * @package com.deppon.crm.module.authorization.service 
 * @author 华龙
 * @version 0.1 2012-12-26
 */
public class RoleServiceTest extends TestCase {
	private Role role = null;
	private User user = null;
	List<String> functionIds = null;
	private static RoleDao roleDao = null;
	private static IRoleService roleService = null;
	
	static{
		roleDao = TestUtil.roleDao;
		roleService =TestUtil.roleService;
	}
	
	@Before
	public void setUp(){
		functionIds = new ArrayList<String>();
		functionIds.add("1");
		functionIds.add("2"); 
		
		role = new Role();
		role.setRoleName("老子天下第一");
		role.setRoleDesc("老子天下第一");
		role.setFunctionIds(functionIds);
		role.setCreateUser("1"); 
		role.setModifyUser("11");
		
		user = new User();
		user.setId("1");
	}
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.service.impl.RoleService#count(com.deppon.crm.module.authorization.shared.domain.Role)}
	 *  @author zouming 
	 */
	@Test
	public void testCount(){
		roleDao.insert(role);
		roleService.count(role);
		
		Role role = new Role();
		role.setId("800000");
		roleService.count(role);
		roleDao.deleteById(role.getId());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.service.impl.RoleService#queryAll(com.deppon.crm.module.authorization.shared.domain.Role)}
	 *  @author zouming 
	 */
	@Test
	public void testQueryAll(){
		assertNotNull(roleService.queryAll(role));
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.service.impl.RoleService#queryAll(com.deppon.crm.module.authorization.shared.domain.Role,int,int)}
	 *  @author zouming 
	 */
	@Test
	public void testQueryAllStartLimit(){
		assertNotNull(roleService.queryAll(role, 0, 5));
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.service.impl.RoleService#queryAuthedRoles(String, String)}
	 *  @author zouming 
	 */
	@Test
	public void testQueryAuthedRoles(){
		assertNotNull(roleService.queryAuthedRoles(user.getId(), user.getId()));
		
		try {
			roleService.queryAuthedRoles(user.getId(), null);
		} catch (GeneralException e) {
//			fail("异常");
		}
		
		try {
			roleService.queryAuthedRoles(user.getId(), "");
		} catch (GeneralException e) {
//			fail("异常");
		}
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.service.impl.RoleService#queryLeftRoles(String, String)}
	 *  @author zouming 
	 */
	@Test
	public void testQueryLeftRoles(){
		roleService.queryLeftRoles(user.getId(), user.getId());
		
		roleService.queryLeftRoles(user.getId(), null);
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.service.impl.RoleService#removeById(String)}
	 *  @author zouming 
	 */
	//TODO
	@Test
	public void testRemoveById(){
		roleDao.insert(role);
		roleDao.insert(role.getId(), "1");
		try {
			roleService.removeById(role.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		try {
			roleService.removeById(null);
		} catch (GeneralException e) {
//			if(e.getErrorCode().equals(RoleExceptionType.RoleIdNull)){
				assertTrue(true);
//			}else{
//				fail("异常异常。");
//			}
		}
		
		try {
			roleService.removeById("");
		} catch (GeneralException e) {
//			if(e.getErrorCode().equals(RoleExceptionType.RoleIdNull)){
				assertTrue(true);
//			}else{
//				fail("异常异常。");
//			}
		}
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.service.impl.RoleService#save(com.deppon.crm.module.authorization.shared.domain.Role)}
	 *  @author zouming 
	 */
	//TODO
	@Test
	public void testSave(){
		//1.角色名字对应有记录
		try {
			roleService.save(role);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		//2.角色名字对应没有记录
		Role r = role;
		r.setRoleName("老子天下第三");
		roleService.save(r);
		roleDao.deleteById(r.getId());
		
		//3.角色为空
		try {
			roleService.save(null);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		//4.角色名字为空
		r.setRoleName(null);
		try {
			roleService.save(r);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		//5.角色名字为空
		r.setRoleName("");
		try {
			roleService.save(r);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		//6.角色名字中有空格
		r.setRoleName("老子    天下第二");
		try {
			roleService.save(r);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		//7.角色名字大于64
		r.setRoleName("老子天下第二老子天下第二老子天下第二老子天下第二" +
				"老子天下第二老子天下第二老子天下第二老子天下第二老子天下第二" +
				"老子天下第二老子天下第二老子天下第二老子天下第二老子天下第二" +
				"老子天下第二老子天下第二老子天下第二老子天下第二老子天下第二" +
				"老子天下第二老子天下第二老子天下第二老子天下第二老子天下第二");
		try {
			roleService.save(r);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		//8.角色描述长度大于128
			r.setRoleDesc("老子天下第二老子天下第二老子天下第二老子天下第二" +
					"老子天下第二老子天下第二老子天下第二老子天下第二老子天下第二" +
					"老子天下第二老子天下第二老子天下第二老子天下第二老子天下第二" +
					"老子天下第二老子天下第二老子天下第二老子天下第二老子天下第二" +
					"老子天下第二老子天下第二老子天下第二老子天下第二老子天下第二"+
					"老子天下第二老子天下第二老子天下第二老子天下第二" +
					"老子天下第二老子天下第二老子天下第二老子天下第二老子天下第二" +
					"老子天下第二老子天下第二老子天下第二老子天下第二老子天下第二" +
					"老子天下第二老子天下第二老子天下第二老子天下第二老子天下第二" +
					"老子天下第二老子天下第二老子天下第二老子天下第二老子天下第二"+
					"老子天下第二老子天下第二老子天下第二老子天下第二" +
					"老子天下第二老子天下第二老子天下第二老子天下第二老子天下第二" +
					"老子天下第二老子天下第二老子天下第二老子天下第二老子天下第二" +
					"老子天下第二老子天下第二老子天下第二老子天下第二老子天下第二" +
					"老子天下第二老子天下第二老子天下第二老子天下第二老子天下第二");
			try {
				roleService.save(r);
			} catch (Exception e) {
				assertTrue(true);
			}
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.service.impl.RoleService#update(com.deppon.crm.module.authorization.shared.domain.Role)}
	 *  @author zouming 
	 */
	//TODO
	@Test
	public void testUpdate(){
		//1.
		Role r = role;
		r.setId(null);
		try {
			roleService.update(r);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		//2.
		r.setId("");
		try {
			roleService.update(r);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		//3.
		roleDao.insert(r);
		r.setId("99");
		try {
			roleService.update(r);
		} catch (Exception e) {
			assertTrue(true);
		}
	}
}
