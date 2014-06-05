package com.deppon.crm.module.authorization.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.server.dao.IAuthorizeDao;
import com.deppon.crm.module.authorization.server.service.IAuthorizeService;
import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.authorization.testutil.TestUtil;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.entity.IRole;

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
public class AuthorizeServiceTest extends TestCase {
	private static IAuthorizeService authorizeService;
	private User user;
	private Department department;
	private Department parentDepartment;
	private static IAuthorizeDao authorizeDao;
	private Employee empCode;
	private Set<String>deptIds;
	private List<String>deptIdList;
	private Set<String>roleids;
	private List<String>roleidList;
	private List<IRole>roles = null;
	
	static{
		authorizeDao = TestUtil.authorizeDao;
		authorizeService = TestUtil.authorizeService;
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
		
		roleidList = new ArrayList<String>();
		roleidList.add("1");
		roleidList.add("2");
		
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
	 * {@link com.deppon.crm.module.authorization.server.service.impl.AuthorizeService#countAllDepartmentByUserId(String)}
	 *  @author zouming 
	 */
	@Test
	public void testCountAllDepartmentByUserId(){
		authorizeService.countAllDepartmentByUserId("1");
		
		try {
			authorizeService.countAllDepartmentByUserId("");
		} catch (Exception e) {
		}
		
		try {
			authorizeService.countAllDepartmentByUserId(null);
		} catch (Exception e) {
		}
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.service.impl.AuthorizeService#countAllDepartmentByUserIdAndLikeDeptName(String, String)}
	 *  @author zouming 
	 */
	@Test
	public void testCountAllDepartmentByUserIdAndLikeDeptName(){
		authorizeService.countAllDepartmentByUserIdAndLikeDeptName("1", department.getDeptName());
		
		try {
			authorizeService.countAllDepartmentByUserIdAndLikeDeptName("", department.getDeptName());
		} catch (Exception e) {
		}
		
		try {
			authorizeService.countAllDepartmentByUserIdAndLikeDeptName(null, department.getDeptName());
		} catch (Exception e) {
		}
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.service.impl.AuthorizeService#countAllleafByParentId(String)}
	 *  @author zouming 
	 */
	@Test
	public void testCountAllleafByParentId(){
		authorizeService.countAllleafByParentId(department.getParentCode().getId());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.service.impl.AuthorizeService#countAllleafByParentIdAndLikeDeptName(String, String)}
	 *  @author zouming 
	 */
	@Test
	public void testCountAllleafByParentIdAndLikeDeptName(){
		authorizeService.countAllleafByParentIdAndLikeDeptName(
						department.getId(), department.getDeptName());
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.service.impl.AuthorizeService#getAllDepartmentByUserId(String, int, int)}
	 *  @author zouming 
	 */
	@Test
	public void testGetAllDepartmentByUserId(){
		authorizeService.getAllDepartmentByUserId(user.getId(), 0, 5);
		
		try {
			authorizeService.getAllDepartmentByUserId("", 0, 5);
		} catch (Exception e) {
		}
		
		try {
			authorizeService.getAllDepartmentByUserId(null, 0, 5);
		} catch (Exception e) {
		}
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.service.impl.AuthorizeService#getAllDepartmentByUserIdAndLikeDeptName(String, String, int, int)}
	 *  @author zouming 
	 */
	@Test
	public void testGetAllDepartmentByUserIdAndLikeDeptName(){
//		authorizeDao.
		authorizeDao.insertUserDeptAuth(user.getId(), department.getId());
		authorizeService.getAllDepartmentByUserIdAndLikeDeptName(
								user.getId(), department.getDeptName(), 0, 2);
		
		try {
			authorizeService.getAllDepartmentByUserIdAndLikeDeptName(
					"", department.getDeptName(), 0, 2);
		} catch (Exception e) {
		}
		
		try {
			authorizeService.getAllDepartmentByUserIdAndLikeDeptName(
					null, department.getDeptName(), 0, 2);
		} catch (Exception e) {
		}
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.service.impl.AuthorizeService#getAllDepartmentsByIds(List<String>)}
	 *  @author zouming 
	 */
	@Test
	public void testGetAllDepartmentsByIds(){
		authorizeService.getAllDepartmentsByIds(deptIdList);
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.service.impl.AuthorizeService#getAllleafByParentId(String, int, int)}
	 *  @author zouming 
	 */
	@Test
	public void testGetAllleafByParentId(){
		authorizeService.getAllleafByParentId(
				department.getParentCode().getId(), 0, 10);
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.service.impl.AuthorizeService#getAllleafByParentIdAndLikeDeptName(String, String, int, int)}
	 *  @author zouming 
	 */
	@Test
	public void testGetAllleafByParentIdAndLikeDeptName(){
		authorizeService.getAllleafByParentIdAndLikeDeptName(department.getParentCode().getId(), department.getDeptName(), 0, 10);
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.service.impl.AuthorizeService#getMyAuthDepts()}
	 *  @author zouming 
	 */
	@Test
	public void testGetMyAuthDepts(){
		try {
			authorizeService.getMyAuthDepts();
		} catch (Exception e) {
		}
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.service.impl.AuthorizeService#queryAllAuthorizeRole(String, String)}
	 *  @author zouming 
	 */
	@Test
	public void testQueryAllAuthorizeRole(){
		authorizeService.queryAllAuthorizeRole(user.getId(), user.getId());
		
		try {
			authorizeService.queryAllAuthorizeRole(user.getId(), null);
		} catch (Exception e) {
		}
		
		try {
			authorizeService.queryAllAuthorizeRole(user.getId(), "");
		} catch (Exception e) {
		}
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.service.impl.AuthorizeService#queryAllChooesRole(String, String)}
	 *  @author zouming 
	 */
	@Test
	public void testQueryAllChooesRole(){
		authorizeService.queryAllChooesRole(user.getId(), user.getId());
		
		try {
			authorizeService.queryAllChooesRole(user.getId(), null);
		} catch (Exception e) {
		}
		
		try {
			authorizeService.queryAllChooesRole(user.getId(), "");
		} catch (Exception e) {
		}
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.service.impl.AuthorizeService#queryAllDepartmentIdByUserId(String)}
	 *  @author zouming 
	 */
	@Test
	public void testQueryAllDepartmentIdByUserId(){
		authorizeService.queryAllDepartmentIdByUserId(user.getId());
		
		try {
			authorizeService.queryAllDepartmentIdByUserId(null);
		} catch (Exception e) {
		}
		
		try {
			authorizeService.queryAllDepartmentIdByUserId("");
		} catch (Exception e) {
		}
	}
	
	/**
	 * Test method for
	 * {@link com.deppon.crm.module.authorization.server.service.impl.AuthorizeService#queryChildDepartmentIdByUserId(String, String, String)}
	 *  @author zouming 
	 */
	@Test
	public void testQueryChildDepartmentIdByUserId(){
		authorizeService.queryChildDepartmentIdByUserId(
					department.getId(), user.getId(), "1212");
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
		//1、模拟查询条件为空的情况
		List<String> companysList = authorizeService.getAllSignCompanys(null, 0, 20);
		//2、模拟查询条件不为空的情况
		companysList = authorizeService.getAllSignCompanys("公司", 0, 20);
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
		//1、模拟查询条件为空的情况
		Integer count = authorizeService.countAllSignCompanys(null);
		//2、模拟查询条件不为空的情况
		count = authorizeService.countAllSignCompanys("公司");
	}
}
