package com.deppon.crm.module.authorization.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.server.dao.impl.RoleDao;
import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.crm.module.authorization.testutil.TestUtil;

/**作者：zouming
 *创建时间：2012-12-26
 *最后修改时间：2012-12-26
 *描述：
 */
public class RoleDaoTest {
	private Role role = null;
	List<String> functionIds = null;
	private static RoleDao roleDao;
	
	static{
		roleDao = TestUtil.roleDao;
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
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.RoleDao#insert(com.deppon.crm.module.authorization.shared.domain.Role)}
	 *  @author zouming 
	 */
	
	@Test
	public void testInsert(){
		roleDao.insert(role); 
		roleDao.deleteById(role.getId());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.RoleDao#count(String,String)}
	 *  @author zouming 
	 */
	
	@Test
	public void testInsertString(){
		roleDao.insert(role); 
		roleDao.insert(role.getId(),functionIds.get(0));
		roleDao.deleteById(role.getId());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.RoleDao#update(com.deppon.crm.module.authorization.shared.domain.Role)}
	 *  @author zouming 
	 */
	
	@Test
	public void testUpdate(){
		roleDao.insert(role);
		roleDao.update(role);
		roleDao.deleteById(role.getId());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.RoleDao#getUseRole(String)}
	 *  @author zouming 
	 */
	
	@Test
	public void testGetUseRole(){
		roleDao.insert(role);
		roleDao.getUseRole(role.getId());
		roleDao.deleteById(role.getId());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.RoleDao#getAll()}
	 *  @author zouming 
	 */
	
	@Test
	public void testGetAll(){
		roleDao.getAll();
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.RoleDao#getAllRole()}
	 *  @author zouming 
	 */
	
	@Test
	public void testGetAllRole(){
		roleDao.getAllRole();
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.RoleDao#getAll(com.deppon.crm.module.authorization.shared.domain.Role)}
	 *  @author zouming 
	 */
	
	@Test
	public void testGetAllByRole(){
		Role r = null;
		roleDao.getAll(r);
		
		r = role;
		r.setRoleName(null);
		roleDao.getAll(r);
		
		r.setId("");
		r.setRoleName("");
		roleDao.getAll(r);
		
		roleDao.insert(r);
		r.setRoleName("老子天下第一");
		roleDao.getAll(r);
		roleDao.deleteById(role.getId());
		
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.RoleDao#getByIds(java.util.List<String>)}
	 *  @author zouming 
	 */
	//TODO
	@Test
	public void testGetByIds(){
//		roleDao.insert(role);
//		
//		List<String>roleIds = new ArrayList<String>();
//		roleIds.add(role.getId());
//		roleDao.getByIds(roleIds);
//		
//		roleDao.deleteById(role.getId());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.RoleDao#getById(String)}
	 *  @author zouming 
	 */
	
	@Test
	public void testGetById(){
		roleDao.insert(role);
		roleDao.getById(role.getId());
		roleDao.deleteById(role.getId());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.RoleDao#deleteById(String)}
	 *  @author zouming 
	 */
	
	@Test
	public void testDeleteById(){
		roleDao.insert(role);
		roleDao.deleteById(role.getId());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.RoleDao#deleteFunctionRoleById(String)}
	 *  @author zouming 
	 */
	
	@Test
	public void testDeleteFunctionRoleById(){
		roleDao.getAllRole();
		roleDao.deleteFunctionRoleById("");
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.RoleDao#deleteUserAuthRoleById(String)}
	 *  @author zouming 
	 */
	//TODO
	@Test
	public void testDeleteUserAuthRoleById(){
//		roleDao.insert
		roleDao.deleteUserAuthRoleById("1232");
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.RoleDao#getAll(com.deppon.crm.module.authorization.shared.domain.Role,Integer,Integer)}
	 *  @author zouming 
	 */
	
	@Test
	public void testGetAllByRoleStartLimit(){
		Role r = role;
		roleDao.getAll(null, 0, 10);
		
		roleDao.getAll(r, 0, 10);
		
		r.setId("");
		roleDao.getAll(r, 0, 10);
		
		roleDao.insert(r);
		roleDao.getAll(r,0,10);
		
		r.setRoleName("");
		roleDao.getAll(r,0,10);
		
		r.setRoleName(null);
		roleDao.getAll(r,0,10);
		
		roleDao.deleteById(r.getId());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.RoleDao#getLastModifyTime()}
	 *  @author zouming 
	 */
	
	@Test
	public void testGetLastModifyTime(){
		roleDao.getLastModifyTime();
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.RoleDao#getLeftRoles(String,String)}
	 *  @author zouming 
	 */
	
	@Test
	public void testGetLeftRoles(){
		roleDao.getLeftRoles(role.getCreateUser(), role.getModifyUser());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.RoleDao#getAuthedRoles(String,String)}
	 *  @author zouming 
	 */
	
	@Test
	public void testGetAuthedRoles(){
		roleDao.getAuthedRoles(role.getCreateUser(), role.getModifyUser());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.RoleDao#getAllAuthRoles(String)}
	 *  @author zouming 
	 */
	
	@Test
	public void testGetAllAuthRoles(){
		roleDao.getAllAuthRoles(role.getCreateUser());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.RoleDao#getIdByName(String)}
	 *  @author zouming 
	 */
	
	@Test
	public void testGetIdByName(){
		roleDao.insert(role);
		roleDao.getIdByName(role.getRoleName());
		roleDao.deleteById(role.getId());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.RoleDao#count(com.deppon.crm.module.authorization.shared.domain.Role)}
	 *  @author zouming 
	 */
	
	@Test
	public void testCount(){
		Role r = role;
		roleDao.count(r);
		
		r.setId("");
		roleDao.count(r);
		
		roleDao.count(null);
		
		r.setRoleName(null);
		roleDao.count(r);
		
		r.setRoleName("");
		roleDao.count(r);
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.RoleDao#getRoleIdsNoCurrentUserAuthRole(String,String)}
	 *  @author zouming 
	 */
	@Test
	public void testGetRoleIdsNoCurrentUserAuthRole(){
//		roleDao.insert(role);
		roleDao.getRoleIdsNoCurrentUserAuthRole(role.getCreateUser(), role.getModifyUser());
	}
}
