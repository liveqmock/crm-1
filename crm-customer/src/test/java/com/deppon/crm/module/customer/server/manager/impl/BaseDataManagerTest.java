package com.deppon.crm.module.customer.server.manager.impl;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.customer.shared.exception.MemberExceptionType;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;
import com.deppon.foss.framework.server.context.UserContext;
public class BaseDataManagerTest extends BeanUtil{
	@Before
	public void setUp(){
		User crDept= userDao.getUserRoleFunDeptById("019124");
		ContextUtil.getCurrentUserDept();
		UserContext.setCurrentUser(crDept);
	}
	/**
	 * 
	 * <p>
	 * Description:测试 获得营业部对应的事业部办公室下面的营销管理组<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-29
	 * void
	 */
	@Test
	public void test_getBusinessDivesionOfficeMarketingGroup(){
		
		Department childDept = new Department();
		childDept.setDeptSeq("77409");
		Department dep = baseDataManager.getBusinessDivesionOfficeMarketingGroup(childDept);
		Assert.assertNull(dep);
		
		childDept = new Department();
		childDept.setDeptSeq("110775..77409");
		dep = baseDataManager.getBusinessDivesionOfficeMarketingGroup(childDept);
		Assert.assertNotNull(dep);
		
	}
	
	
	
    /**
     * 
     * <p>
     * Description:测试<br />
     * </p>
     * @author 王明明
     * @version 0.1 2012-12-29
     * void
     */
	@Test
	public void test_getDataAuthorityDepts() {
		// 用户角色为 Constant.Cause
		User user = new User();
		Set<String> roleids = new TreeSet<String>();
		roleids.add(Constant.Cause);
		Employee e = new Employee();
		Department depart = new Department();
		depart.setId("10653");
		e.setDeptId(depart);
		user.setEmpCode(e);
		user.setRoleids(roleids);

		List<String> deptList = baseDataManager.getDataAuthorityDepts("");

		// 用户角色为 Constant.Neighborhood
		user = new User();
		roleids = new TreeSet<String>();
		roleids.add(Constant.Neighborhood);
		e = new Employee();
		depart = new Department();
		depart.setId("019124");
		e.setDeptId(depart);
		user.setEmpCode(e);
		user.setRoleids(roleids);
		deptList = baseDataManager.getDataAuthorityDepts("");
		Assert.assertTrue(deptList.size() >= 0);

		// 用户角色为Constant.Region
		user = new User();
		roleids = new TreeSet<String>();
		roleids.add(Constant.Region);
		e = new Employee();
		depart = new Department();
		depart.setId("019124");
		e.setDeptId(depart);
		user.setEmpCode(e);
		user.setRoleids(roleids);
		deptList = baseDataManager.getDataAuthorityDepts("");
	

		// 用户角色为Constant.Marketing
		user = new User();
		roleids = new TreeSet<String>();
		roleids.add(Constant.Marketing);
		e = new Employee();
		depart = new Department();
		depart.setId("019124");
		e.setDeptId(depart);
		user.setEmpCode(e);
		user.setRoleids(roleids);
		deptList = baseDataManager.getDataAuthorityDepts("");
		

		// 用户角色为Constant.BizManager
		user = new User();
		roleids = new TreeSet<String>();
		roleids.add(Constant.BizManager);
		e = new Employee();
		depart = new Department();
		depart.setId("019124");
		e.setDeptId(depart);
		user.setEmpCode(e);
		user.setRoleids(roleids);
		deptList = baseDataManager.getDataAuthorityDepts("");
	

		// 用户角色为Constant.BizManager
		user = new User();
		roleids = new TreeSet<String>();
		roleids.add("ere");
		e = new Employee();
		depart = new Department();
		depart.setId("019124");
		e.setDeptId(depart);
		user.setEmpCode(e);
		user.setRoleids(roleids);
		deptList = baseDataManager.getDataAuthorityDepts("");
	
		
		
		try{
			user = new User();
			roleids = new TreeSet<String>();
			roleids.add(Constant.BizManager);
			e = new Employee();
			depart = new Department();
			depart.setId("019124");
			e.setDeptId(depart);
			user.setEmpCode(e);
			user.setRoleids(roleids);
			deptList = baseDataManager.getDataAuthorityDepts("019124");
			Assert.assertTrue(deptList.size() > 0);
		}catch(MemberException me){
			Assert.assertEquals(MemberExceptionType.NoAuthority.getErrCode(), me.getErrorCode());
		}
		

		// 用户角色为Constant.BizManager
				user = new User();
				roleids = new TreeSet<String>();
				roleids.add(Constant.BizManager);
				e = new Employee();
				depart = new Department();
				depart.setId("10653");
				e.setDeptId(depart);
				user.setEmpCode(e);
				user.setRoleids(roleids);
				deptList = baseDataManager.getDataAuthorityDepts("");
		
		
		

	}
	
	
	/**
	 * 
	 * <p>
	 * Description:测试 客户模块特殊的---数据权限验证，权限采用DPAP可分配权限<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-29
	 * void
	 */
    @Test
	public void test_hasDataAuthorityDepts(){
		
		User user = new User();
		Set<String> deptids = new TreeSet<String>();
		deptids.add("233456");
		deptids.add("104");
		Department d = new Department();
		d.setId("104");
		Employee e = new Employee();
		e.setPosition("xxx");
		e.setDeptId(d);
		user.setEmpCode(e);
		user.setDeptids(deptids);
		UserContext.setCurrentUser(user);

		try{
	    	baseDataManager.hasDataAuthorityDepts("");
		}catch(MemberException me){
			Assert.assertEquals(MemberExceptionType.NoAuthority.getErrCode(), me.getErrorCode());
		}
		
		
		try{
	    	baseDataManager.hasDataAuthorityDepts("105");
		}catch(MemberException me){
			Assert.assertEquals(MemberExceptionType.NoAuthority.getErrCode(), me.getErrorCode());
		}
		
		List<String> deptIds = baseDataManager.hasDataAuthorityDepts("104");
	    Assert.assertTrue(deptIds.size()>0);
	    
	    UserContext.setCurrentUser(null);
	}
	

	@Test
	public void testGetBankCityByBankProvinceId(){
		String string = "wwNiEgEuEADgA4V6wKjeATcYh+k=";
		baseDataManager.getBankCityByBankProvinceId(string);
	}
	@Test
	public void testGetBankProvince(){
		baseDataManager.getBankProvince();
	}
	
	
	@Test
	public void testGetCustRelationsDepartment(){
		Department department =	baseDataManager.getCustRelationsDepartment();
		Assert.assertNotNull(department);
	}
	
	@Test
	public void testGetRegionDepartment(){
		String string = "10634";
		Department dep = baseDataManager.getRegionDepartment(string);
		Assert.assertEquals(dep.getDeptName(), "烟台大区");
	}
	@Test
	public void testGetNeighborhoodDepartment(){
		String string = "10634";
		Department dep = baseDataManager.getNeighborhoodDepartment(string);
		Assert.assertNotNull(dep.getDeptName());

	}
	@Test
	public void testGetCauseDepartment(){
		String string = "10634";
		Department dep = baseDataManager.getCauseDepartment(string);
		Assert.assertNotNull(dep.getDeptName());

	}
	@Test
	public void testGetDeptById(){
		String string = "10634";
		Department dep = baseDataManager.getDeptById(string);
		Assert.assertEquals(dep.getDeptName(), "威海经区鑫通货运市场营业部");
	}
	@Test
	public void testGetDeptByStandardCode(){
		String string = "DP02035";
		Department dep = baseDataManager.getDeptByStandardCode(string);
		Assert.assertEquals(dep.getDeptName(), "仙桃龙华山昌升路营业部");
	}
//	@Test
	public void testGetAccountBank(){
		baseDataManager.getAccountBank();
	}
	@Test
	public void testGetMyAuthDataDeptsByFunction(){
		String functionName = BaseDataManager.MemberFunction;
		String deptName = "营业部";
		int a = 0;
		int b = 100;
		baseDataManager.getMyAuthDataDeptsByFunction(functionName,deptName,a,b);
		
		functionName = "test";
		baseDataManager.getMyAuthDataDeptsByFunction(functionName,deptName,a,b);
	}
	@Test
	public void testGetMyAuthDataDeptsByFunction2(){
		String functionName = BaseDataManager.MemberFunction;
		int a = 0;
		int b = 100;
		baseDataManager.getMyAuthDataDeptsByFunction(functionName,a,b);
		
		functionName = "test";
		baseDataManager.getMyAuthDataDeptsByFunction(functionName,a,b);
	}
	@Test
	public void testCountMyAuthDataDeptsByFunction(){
		String functionName = BaseDataManager.MemberFunction;
		baseDataManager.countMyAuthDataDeptsByFunction(functionName);
		
		functionName = "test";
		baseDataManager.countMyAuthDataDeptsByFunction(functionName);

	}
	
	@Test
	public void testCountMyAuthDataDeptsByFunction2(){
		String functionName = BaseDataManager.MemberFunction;
		String deptName = "营业部";
		baseDataManager.countMyAuthDataDeptsByFunction(functionName,deptName);
		
		functionName = "test";
		baseDataManager.countMyAuthDataDeptsByFunction(functionName,deptName);
	}
	
	@Test
	public void testGetAllSignCompanys(){
		//1、查询条件为空的情况
		List<String> companysList =  baseDataManager.getAllSignCompanys(null, 0, 20);
		//2、查询条件不为空的情况
		companysList = baseDataManager.getAllSignCompanys("公司", 0, 20);
	}
	
	public void testCountAllSignCompanys(){
		//1、查询条件为空的情况
		Integer count = baseDataManager.countAllSignCompanys(null);
		//2、查询条件不为空的情况
		count = baseDataManager.countAllSignCompanys("公司");
	}
	@Test
	public void testCountAllDepartments(){
		baseDataManager.countAllDepartments("111111111111");
		
	}
	
	@Test
	public void testSearchAllDepartments(){
		baseDataManager.getAllDepartments("1111111", 1, 3);
	}
	@Test
	public void testGetDeptCityLocation(){
		baseDataManager.getDeptCityLocation("49311");
	}
	@Test
	public void testIsHongKongOrMainLand(){
		baseDataManager.isHongKongOrMainLand();
	}
	@Test
	public void testcountPotentialCustomerAuthData(){
		baseDataManager.countMyAuthDataDeptsByFunction("ScatterCustomerFunction");
		baseDataManager.countMyAuthDataDeptsByFunction("ContractFunction");
		baseDataManager.countMyAuthDataDeptsByFunction("FunctionRepeatCust");
		baseDataManager.countMyAuthDataDeptsByFunction("PotentialCustomerFunction");
	}
	@Test
	public void testgetMyAuthDataDeptsByFunction(){
		baseDataManager.getMyAuthDataDeptsByFunction("ScatterCustomerFunction",1,3);
		baseDataManager.getMyAuthDataDeptsByFunction("ContractFunction",1,3);
		baseDataManager.getMyAuthDataDeptsByFunction("FunctionRepeatCust",1,3);
		baseDataManager.getMyAuthDataDeptsByFunction("PotentialCustomerFunction",1,3);
	}
}

