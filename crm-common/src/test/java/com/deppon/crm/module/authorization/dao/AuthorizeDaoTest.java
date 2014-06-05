package com.deppon.crm.module.authorization.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.server.dao.IAuthorizeDao;
import com.deppon.crm.module.authorization.server.dao.impl.AuthorizeDao;
import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.authorization.testutil.TestUtil;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.entity.IRole;

/**作者：zouming
 *创建时间：2012-12-26
 *最后修改时间：2012-12-26
 *描述：
 */
public class AuthorizeDaoTest {
	private User user;
	private Department department;
	private Department parentDepartment;
	private static IAuthorizeDao authorizeDao;
	private Employee empCode;
	private Set<String>deptIds;
	private List<String>deptIdList;
	private Set<String>roleids;
	private List<IRole>roles = null;
	
	static{
		authorizeDao = TestUtil.authorizeDao;
	}
	
	@Before
	public void setUp(){
		deptIds = new HashSet<String>();
		deptIds.add("1");
		deptIds.add("2");
		
		deptIdList = new ArrayList<String>();
		deptIdList.add("1");
		deptIdList.add("2");
		
		roleids = new HashSet<String>();
		roleids.add("1");
		roleids.add("2");
		
		empCode = new Employee();
		empCode.setId("1");
		
		IRole role1 = new Role();
		role1.setId("1");
		IRole role2 = new Role();
		role2.setId("2");
		roles = new ArrayList<IRole>();
		roles.add(role1);
		roles.add(role2);
		
		user = new User();
		user.setId("1");
		user.setCreateDate(new Date());
		user.setCreateUser("白骨精");
		user.setDeptids(deptIds);
		user.setEmpCode(empCode);
		user.setInvalidDate(new Date());
		user.setLastLogin(new Date());
		user.setLoginName("唐僧");
		user.setModifyDate(new Date());
		user.setModifyUser("孙悟空");
		user.setPassword("password");
		user.setRoleids(roleids);
		user.setRoles(roles);
		user.setStatus(new Byte("1"));
		user.setValidDate(new Date());
		
		parentDepartment = new Department();
		parentDepartment.setId("008");
		parentDepartment.setAddress("花果山水帘洞");
		parentDepartment.setCompanyName("花果山公司");
		parentDepartment.setCreateDate(new Date());
		parentDepartment.setCreateUser("孙悟空");
		parentDepartment.setDeptCode("008001");
		parentDepartment.setDeptDesc("本部门主管杀猪");
		parentDepartment.setDeptLevel(new Byte("1"));
		parentDepartment.setDeptName("杀猪部");
		parentDepartment.setDeptSeq("008001001");
		parentDepartment.setDisplayOrder(1);
		parentDepartment.setFax("今天杀了几头？");
		parentDepartment.setInvalidDate(new Date());
		parentDepartment.setLeaf(true);
		parentDepartment.setModifyDate(new Date());
		parentDepartment.setModifyUser("猪八戒");
		parentDepartment.setParentCode(new Department());
		parentDepartment.setPhone("13843819438");
		parentDepartment.setPrincipal("杀了八头");
		parentDepartment.setStandardCode("00800101");
		parentDepartment.setStatus(true);
		parentDepartment.setValidDate(new Date());
		parentDepartment.setZipCode("够吃吗？");
		
		department = new Department();
		department.setId("008001");
		department.setAddress("花果山水帘洞");
		department.setCompanyName("花果山公司");
		department.setCreateDate(new Date());
		department.setCreateUser("孙悟空");
		department.setDeptCode("008001");
		department.setDeptDesc("本部门主管杀猪");
		department.setDeptLevel(new Byte("1"));
		department.setDeptName("杀猪部");
		department.setDeptSeq("008001001");
		department.setDisplayOrder(1);
		department.setFax("今天杀了几头？");
		department.setInvalidDate(new Date());
		department.setLeaf(true);
		department.setModifyDate(new Date());
		department.setModifyUser("猪八戒");
		department.setParentCode(parentDepartment);
		department.setPhone("13843819438");
		department.setPrincipal("杀了八头");
		department.setStandardCode("00800101");
		department.setStatus(true);
		department.setValidDate(new Date());
		department.setZipCode("够吃吗？");
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.AuthorizeDao#insertUserDeptAuth(String, String)}
	 *  @author zouming 
	 */
	@Test
	public void testInsertUserDeptAuth(){
		authorizeDao.insertUserDeptAuth(user.getId(), department.getId());
		authorizeDao.deleteUserDeptAuthByUserId(user.getId());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.AuthorizeDao#insertUserRoleAuth(String, String)}
	 *  @author zouming 
	 */
	@Test
	public void testinsertUserRoleAuth(){
		Iterator it = roleids.iterator();
		String roleIdArray[] = new String[5];
		int  i = 0;
		while(it.hasNext()){
			roleIdArray[i] = (String) it.next();
			i++;
		}
		authorizeDao.insertUserRoleAuth(user.getId(),roleIdArray[0]);
		authorizeDao.deleteUserRoleAuthByUserId(user.getId());
		
	}
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.AuthorizeDao#countAllDepartmentByUserId(String)}
	 *  @author zouming 
	 */
	@Test
	public void testCountAllDepartmentByUserId(){
		authorizeDao.countAllDepartmentByUserId(user.getId());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.AuthorizeDao#countAllDepartmentByUserIdAndLikeDeptName(String, String)}
	 *  @author zouming 
	 */
	@Test
	public void testCountAllDepartmentByUserIdAndLikeDeptName(){
		authorizeDao.countAllDepartmentByUserIdAndLikeDeptName(user.getId(), department.getDeptName());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.AuthorizeDao#countAllleafByParentId(String)}
	 *  @author zouming 
	 */
	@Test
	public void testCountAllleafByParentId(){
		List<String> deptIds=new ArrayList<String>();
		deptIds.add(department.getParentCode().getId());
		authorizeDao.countAllleafByParentId(deptIds);
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.AuthorizeDao#countAllleafByParentIdAndLikeDeptName(String, String)}
	 *  @author zouming 
	 */
	@Test
	public void testCountAllleafByParentIdAndLikeDeptName(){
		List<String> deptIds=new ArrayList<String>();
		deptIds.add(department.getParentCode().getId());
		authorizeDao.countAllleafByParentIdAndLikeDeptName(
				deptIds, department.getDeptName());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.AuthorizeDao#deleteUserDeptAuthByUserId(String)}
	 *  @author zouming 
	 */
	@Test
	public void testDeleteUserDeptAuthByUserId(){
//		authorizeDao.deleteuSER	
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.AuthorizeDao#deleteUserRoleAuthByUserId(String)}
	 *  @author zouming 
	 */
	@Test
	public void testDeleteUserRoleAuthByUserId(){
//		
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.AuthorizeDao#getAllAuthorizedRoleByUserId(String, String)}
	 *  @author zouming 
	 */
	@Test
	public void testGetAllAuthorizedRoleByUserId(){
		authorizeDao.getAllAuthorizedRoleByUserId(user.getId(), user.getId());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.AuthorizeDao#getAllChooesRoleByUserId(String, String)}
	 *  @author zouming 
	 */
	@Test
	public void testGetAllChooesRoleByUserId(){
		authorizeDao.getAllChooesRoleByUserId(user.getId(), user.getId());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.AuthorizeDao#getAllDepartmentByUserId(String)}
	 *  @author zouming 
	 */
	@Test
	public void testGetAllDepartmentByUserId(){
		authorizeDao.getAllDepartmentByUserId(user.getId());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.AuthorizeDao#getAllDepartmentByUserId(String, Integer, Integer)}
	 *  @author zouming 
	 */
	@Test
	public void testGetAllDepartmentByUserIdLimitStart(){
		authorizeDao.getAllDepartmentByUserId(user.getId(), 0, 2);
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.AuthorizeDao#getAllDepartmentByUserIdAndLikeDeptName(String, String, int, int)}
	 *  @author zouming 
	 */
	@Test
	public void testGetAllDepartmentByUserIdAndLikeDeptName(){
		authorizeDao.getAllDepartmentByUserIdAndLikeDeptName(
				user.getId(), department.getDeptName(), 0, 10);
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.AuthorizeDao#getAllDepartmentIdByUserId(String, String)}
	 *  @author zouming 
	 */
	@Test
	public void testGetAllDepartmentIdByUserId(){
		authorizeDao.getAllDepartmentIdByUserId(user.getId(), user.getId());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.AuthorizeDao#getAllDepartmentsByIds(List<String>)}
	 *  @author zouming 
	 */
	@Test
	public void testGetAllDepartmentsByIds(){
		authorizeDao.getAllDepartmentsByIds(null);
		
		authorizeDao.getAllDepartmentsByIds(deptIdList);
		
		authorizeDao.getAllDepartmentsByIds(new ArrayList<String>());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.AuthorizeDao#getAllleafByParentId(String, int, int)}
	 *  @author zouming 
	 */
	@Test
	public void testGetAllleafByParentId(){
		List<String> deptIds=new ArrayList<String>();
		deptIds.add(department.getParentCode().getId());
		authorizeDao.getAllleafByParentId(deptIds, 0, 2);
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.AuthorizeDao#getAllleafByParentIdAndLikeDeptName(String, String, int, int)}
	 *  @author zouming 
	 */
	@Test
	public void testGetAllleafByParentIdAndLikeDeptName(){
		List<String> deptIds=new ArrayList<String>();
		deptIds.add(department.getParentCode().getId());
		authorizeDao.getAllleafByParentIdAndLikeDeptName(
					deptIds, department.getDeptName(), 0, 1);
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.dao.impl.AuthorizeDao#getChildDepartmentIdByUserId(String, String, String)}
	 *  @author zouming 
	 */
	@Test
	public void testGetChildDepartmentIdByUserId(){
		authorizeDao.getChildDepartmentIdByUserId(
				user.getId(), user.getId(), department.getParentCode().getId());
	}
	/**
	 * 
	 * @Title: testGetAllSignCompanys
	 *  <p>
	 * </p>
	 * @author tl
	 * @version 0.1 2013-11-16
	 * @return void
	 * @throws
	 */
	@Test
	public void testGetAllSignCompanys(){
	//1、测试合同签署公司名字为空的时候
	List<String> companys = authorizeDao.getAllSignCompanys(null, 0, 20);
	//2、测试合同签署公司名字不为空的时候
	companys = authorizeDao.getAllSignCompanys("公司", 0, 20);
	System.out.println(companys);
	}
	/**
	 * 
	 * @Title: testCountAllSignCompanys
	 *  <p>
	 * </p>
	 * @author TL
	 * @version 0.1 2013-11-16
	 * @return void
	 * @throws
	 */
	@Test
	public void testCountAllSignCompanys(){
		//1、测试查询条件为空的时候
		Integer countInteger = authorizeDao.countAllSignCompanys(null);
		//2、测试查询条件不为空的时候
		countInteger = authorizeDao.countAllSignCompanys("公司");
	}
	
	@Test
	public void testGetAllDeptMappedExpress(){
//		String userId,
//		String deptName, String position, int start, int limit
		String userId = "123456";
		String deptCode = "DP11921";
		String deptname = "北京";
		int start = 0;
		int limit = 20;
		authorizeDao.getAllDeptMappedExpress(userId, deptname, deptCode, start, limit);
	}
}
