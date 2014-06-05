//package com.deppon.crm.module.customer.server.testutils;
//
//import java.util.List;
//import java.util.Set;
//import java.util.TreeSet;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Test;
//
//import com.deppon.crm.module.authorization.shared.domain.User;
//import com.deppon.crm.module.authorization.shared.exception.UserException;
//import com.deppon.crm.module.customer.server.utils.ContextUtil;
//import com.deppon.crm.module.customer.shared.domain.Constant;
//import com.deppon.crm.module.organization.shared.domain.Department;
//import com.deppon.crm.module.organization.shared.domain.Employee;
//import com.deppon.foss.framework.server.context.UserContext;
//
//public class ContextUtilTest {
//	
//	@Test
//	public void test_getCurrentUserName(){
//		User user = new User();
//		Department depart = new Department();
//		depart.setId("432658");
//		depart.setDeptName("deptName");
//		depart.setStandardCode("DP00003");
//		Employee e = new Employee();
//		e.setDeptId(depart);
//		e.setEmpName("用户名");
//		user.setEmpCode(e);
//		UserContext.setCurrentUser(user);
//		
//		Assert.assertEquals(e.getEmpName(), ContextUtil.getCurrentUserName());
//		
//	}
//	
//	@Test
//	public void test_getDataAuthorityDepts(){
//		
//		ContextUtil.getDataAuthorityDepts("1234");
//		
//		User user = new User();
//		Department depart = new Department();
//		depart.setId("432658");
//		depart.setDeptName("deptName");
//		depart.setStandardCode("DP00003");
//		Employee e = new Employee();
//		e.setDeptId(depart);
//		user.setEmpCode(e);
//		
//		Set<String> deptids = new TreeSet<String>();
//		deptids.add("12345");
//		deptids.add("34576");
//		user.setDeptids(deptids);
//		UserContext.setCurrentUser(user);
//		
//		ContextUtil.getDataAuthorityDepts("");
//	}
//	
//	@Test
//	public void test_getCurrentUserDeptName(){
//		User user = new User();
//		Department depart = new Department();
//		depart.setId("432658");
//		depart.setDeptName("deptName");
//		depart.setStandardCode("DP00003");
//		Employee e = new Employee();
//		e.setDeptId(depart);
//		user.setEmpCode(e);
//		UserContext.setCurrentUser(user);
//		Assert.assertEquals(depart.getDeptName(), ContextUtil.getCurrentUserDeptName());
//		
//	}
//
//	
//	@Test
//	public void test_getCurrentUser(){
//		try{
//		   ContextUtil.getCurrentUser();
//		}catch(UserException ue){
//			Assert.assertNotNull(ue.getErrorCode());
//		}
//		
//	}
//	
//	
//	@Test
//	public void test_getCurrentUserRoleIds(){
//		User user = new User();
//		Set<String> roleIds = new TreeSet<String>();
//		roleIds.add("12345");
//		roleIds.add("34576");
//		user.setRoleids(roleIds);
//		UserContext.setCurrentUser(user);
//		Set<String> r_roleIds = ContextUtil.getCurrentUserRoleIds();
//		Assert.assertEquals(roleIds.size(), r_roleIds.size());
//		
//		Set<String> r_roleIs = ContextUtil.getRoles();
//		Assert.assertEquals(roleIds.size(), r_roleIs.size());
//	}
//	
//	@Test
//	public void test_getCurrentUserDataAuthorityDept(){
//		User user = new User();
//		Department depart = new Department();
//		depart.setId("432658");
//		depart.setDeptName("deptName");
//		depart.setStandardCode("DP00003");
//		Employee e = new Employee();
//		e.setDeptId(depart);
//		user.setEmpCode(e);
//		
//		Set<String> deptids = new TreeSet<String>();
//		deptids.add("12345");
//		deptids.add("34576");
//		user.setDeptids(deptids);
//		UserContext.setCurrentUser(user);
//		
//		List<String> deptIdList = ContextUtil.getCurrentUserDataAuthorityDept();
//		Assert.assertEquals(deptids.size(), deptIdList.size());
//		
//	}
//	
//	/**
//	 * 
//	 * <p>
//	 * Description:测试得到创建人或修改人userId<br />
//	 * </p>
//	 * @author 王明明
//	 * @version 0.1 2012-12-25
//	 * void
//	 */
//	@Test
//	public void test_getCreateOrModifyUserId(){
//		String deptId = ContextUtil.getCreateOrModifyUserId();
//		Assert.assertEquals(Constant.SuperManUserId,deptId);
//		
//		User user = new User();
//		Department depart = new Department();
//		depart.setId("432658");
//		depart.setDeptName("deptName");
//		depart.setStandardCode("DP00003");
//		Employee e = new Employee();
//		e.setDeptId(depart);
//		user.setEmpCode(e);
//		UserContext.setCurrentUser(user);
//		
//		String userId = ContextUtil.getCreateOrModifyUserId();
//		Assert.assertEquals(e.getId(), userId);
//	}
//
//	
//	/**
//	 * 
//	 * <p>
//	 * Description:测试获取<br />
//	 * </p>
//	 * @author 王明明
//	 * @version 0.1 2012-12-25
//	 * void
//	 */
//	@Test
//	public void test_getCurrentUserEmpId(){
//		User user = new User();
//		Department depart = new Department();
//		depart.setId("432658");
//		depart.setDeptName("deptName");
//		depart.setStandardCode("DP00003");
//		Employee e = new Employee();
//		e.setDeptId(depart);
//		e.setId("98564");
//		user.setEmpCode(e);
//		UserContext.setCurrentUser(user);
//		
//		String userId = ContextUtil.getCurrentUserEmpId();
//		Assert.assertEquals(e.getId(), userId);
//	}
//	
//	
//	
//	/**
//	 * 
//	 * <p>
//	 * Description:测试得到当前用户的部门Id<br />
//	 * </p>
//	 * @author 王明明
//	 * @version 0.1 2012-12-25
//	 * void
//	 */
//	@Test
//	public void test_getCurrentUserDeptId(){
//		
//		String deptId = ContextUtil.getCurrentUserDeptId();
//		Assert.assertEquals("104",deptId);
//		
//		User user = new User();
//		Department depart = new Department();
//		depart.setId("432658");
//		depart.setDeptName("deptName");
//		depart.setStandardCode("DP00003");
//		Employee e = new Employee();
//		e.setDeptId(depart);
//		user.setEmpCode(e);
//		UserContext.setCurrentUser(user);
//		
//		Assert.assertEquals(depart.getId(),ContextUtil.getCurrentUserDeptId());
//		
//	}
//	
//	@After
//	public void tearDown(){
//		UserContext.setCurrentUser(null);
//	}
//
//}
