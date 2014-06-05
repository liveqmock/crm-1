package com.deppon.crm.module.authorization.testutil;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.server.dao.IAuthorizeDao;
import com.deppon.crm.module.authorization.server.dao.impl.AuthorizeDao;
import com.deppon.crm.module.authorization.server.dao.impl.FunctionDao;
import com.deppon.crm.module.authorization.server.dao.impl.LoginLockDao;
import com.deppon.crm.module.authorization.server.dao.impl.RoleDao;
import com.deppon.crm.module.authorization.server.dao.impl.TreeDao;
import com.deppon.crm.module.authorization.server.dao.impl.UserDao;
import com.deppon.crm.module.authorization.server.dao.impl.UserDeptAndRoleDao;
import com.deppon.crm.module.authorization.server.manager.ITreeManager;
import com.deppon.crm.module.authorization.server.service.IAuthorizeService;
import com.deppon.crm.module.authorization.server.service.IFunctionService;
import com.deppon.crm.module.authorization.server.service.IRoleService;
import com.deppon.crm.module.authorization.server.service.ITreeService;
import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.organization.server.dao.impl.DepartmentDao;
import com.deppon.crm.module.organization.server.dao.impl.EmployeeDao;
import com.deppon.crm.module.sync.server.manager.ISyncManager;

/**
 * 
 * <p>
 * Description:Auth测试工具类<br />
 * </p>
 * 
 * @title TestUtil.java
 * @package com.deppon.crm.module.authorization.testutil
 * @author 华龙
 * @version 0.1 2012-12-26
 */
public class TestUtil {

	public static ApplicationContext factory;
	static String[] resource = new String[] { "authorization/AuthorizationBeanTest.xml" };
	public static RoleDao roleDao = null;
	public static UserDao userDao = null;
	public static FunctionDao functionDao = null;
	public static IAuthorizeDao authorizeDao = null;
	public static EmployeeDao empDao=null;
	public static DepartmentDao orgDao=null;
	public static UserDeptAndRoleDao userDeptAndRoleDao = null;
	public static TreeDao treeDao = null;
	public static ISyncManager userSync=null;
	public static ISyncManager empSync=null;
	public static ISyncManager depSync=null;
	public static LoginLockDao loginDao = null;
	public static IAuthorizeService authorizeService = null;
	public static IFunctionService functionService = null;
	public static IRoleService roleService = null;
	public static ITreeService treeService = null;
	public static IUserService userService = null;
	public static ITreeManager treeManager = null;
	static {
		try {
			if (factory == null) {
				factory = new ClassPathXmlApplicationContext(resource);
			}
			roleDao = (RoleDao) factory.getBean("roleDao");
			userDao = (UserDao) factory.getBean("userDao");
			functionDao = (FunctionDao) factory.getBean("functionDao");
			authorizeDao = (AuthorizeDao) factory.getBean("authorizeDao");
			userDeptAndRoleDao = (UserDeptAndRoleDao) factory
					.getBean("userDeptAndRoleDao");
			treeDao = (TreeDao) factory.getBean("treeDao");
			loginDao = (LoginLockDao) factory.getBean("lockDao");
			empDao=(EmployeeDao)factory.getBean("empDao");
			orgDao=(DepartmentDao)factory.getBean("orgDao");
			Object oo=factory.getBean("authorizeService");
			authorizeService = (IAuthorizeService) oo;
			functionService = (IFunctionService) factory.getBean("functionService");
			roleService = (IRoleService) factory.getBean("roleService");
			treeService = (ITreeService) factory.getBean("treeService");
			userService = (IUserService) factory.getBean("userService");
			treeManager = (ITreeManager) factory.getBean("treeManager");
			userSync=(ISyncManager)factory.getBean("syncUserManager");
			empSync=(ISyncManager)factory.getBean("syncEmpManager");
			depSync=(ISyncManager)factory.getBean("syncDepManager");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
