package com.deppon.crm.module.marketing.manager;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.manager.IRegionPartitionManager;
import com.deppon.crm.module.marketing.server.manager.impl.RegionPartitionManager;
import com.deppon.crm.module.marketing.shared.domain.RegionPartitionConstance;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;

/**
 * <p>
 * Description:区域划分<br />
 * </p>
 * 
 * @author ZhouYuan
 * @time 2012-11-06
 */
public class RegionPartitionManagerTest {
	private IRegionPartitionManager regionPartitionManager;
	private User user;

	@Before
	public void setUp() throws Exception { 
		regionPartitionManager = (IRegionPartitionManager) SpringTestHelper.get()
				.getBean(RegionPartitionManager.class);
		user = new User();
		Employee emp = new Employee();
		emp.setId("18706");
		Department dept = new Department();
		dept.setId("11469");
		dept.setPrincipal("005072");
		emp.setDeptId(dept);
		user.setEmpCode(emp);
		Set<String> roleIds = new HashSet<String>();
		roleIds.add(RegionPartitionConstance.AUTH_HEADQUARTERS);
		user.setRoleids(roleIds);
	}

	@Test
	public void testiInitRegionPartitionPage(){
		//具有划分经营本部权限用户
		try{
			regionPartitionManager.initRegionPartitionPage(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//具有事业部权限
		Set<String> roleIds = new HashSet<String>();
		roleIds.add(RegionPartitionConstance.AUTH_DIVISION);
		user.setRoleids(roleIds);
		try{
			regionPartitionManager.initRegionPartitionPage(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		//具有大区权限
		roleIds = new HashSet<String>();
		roleIds.add(RegionPartitionConstance.AUTH_BIG_REGION);
		user.setRoleids(roleIds);
		try{
			regionPartitionManager.initRegionPartitionPage(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		//具有小区权限
		roleIds = new HashSet<String>();
		roleIds.add(RegionPartitionConstance.AUTH_BIG_REGION);
		user.setRoleids(roleIds);
		try{
			regionPartitionManager.initRegionPartitionPage(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		//具有营业部权限
		roleIds = new HashSet<String>();
		roleIds.add(RegionPartitionConstance.AUTH_SMALL_REGION);
		user.setRoleids(roleIds);
		try{
			regionPartitionManager.initRegionPartitionPage(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		//经营本部负责人
		user.setId("27396");
		user.getEmpCode().setEmpCode("002397");
		user.getEmpCode().getDeptId().setId("464252");
		user.getEmpCode().getDeptId().setPrincipal("002397");
		roleIds = new HashSet<String>();
		user.setRoleids(roleIds);
		try{
			regionPartitionManager.initRegionPartitionPage(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		//事业部负责人
		user.setId("31753");
		user.getEmpCode().setEmpCode("001513");
		user.getEmpCode().getDeptId().setId("110775");
		user.getEmpCode().getDeptId().setPrincipal("001513");
		roleIds = new HashSet<String>();
		user.setRoleids(roleIds);
		try{
			regionPartitionManager.initRegionPartitionPage(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		//大区负责人
		user.setId("17597");
		user.getEmpCode().setEmpCode("008607");
		user.getEmpCode().getDeptId().setId("291219");
		user.getEmpCode().getDeptId().setPrincipal("008607");
		roleIds = new HashSet<String>();
		user.setRoleids(roleIds);
		try{
			regionPartitionManager.initRegionPartitionPage(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		//小区负责人
		user.setId("18296");
		user.getEmpCode().setEmpCode("005220");
		user.getEmpCode().getDeptId().setId("22244");
		user.getEmpCode().getDeptId().setPrincipal("005220");
		roleIds = new HashSet<String>();
		user.setRoleids(roleIds);
		try{
			regionPartitionManager.initRegionPartitionPage(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		//没有权限用户
		user.setId("1");
		user.getEmpCode().setEmpCode("1");
		user.getEmpCode().getDeptId().setId("1");
		user.getEmpCode().getDeptId().setPrincipal("1");
		roleIds = new HashSet<String>();
		user.setRoleids(roleIds);
		try{
			regionPartitionManager.initRegionPartitionPage(user);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testSearchRegionDept(){
		//具有划分经营本部权限用户
		Employee emp = new Employee();
		emp.setId("18706");
		Department dept = new Department();
		dept.setId("11469");
		dept.setPrincipal("005072");
		emp.setDeptId(dept);
		user.setEmpCode(emp);
		Set<String> roleIds = new HashSet<String>();
		roleIds.add(RegionPartitionConstance.AUTH_HEADQUARTERS);
		user.setRoleids(roleIds);
		try{
			regionPartitionManager.searchRegionDept(user);
			}catch(Exception e){
				e.printStackTrace();
			}
		//具有事业部权限
		roleIds = new HashSet<String>();
		roleIds.add(RegionPartitionConstance.AUTH_DIVISION);
		user.setRoleids(roleIds);
		try{
			regionPartitionManager.searchRegionDept(user);
			}catch(Exception e){
				e.printStackTrace();
			}
		//具有大区权限
		roleIds = new HashSet<String>();
		roleIds.add(RegionPartitionConstance.AUTH_BIG_REGION);
		user.setRoleids(roleIds);
		try{
			regionPartitionManager.searchRegionDept(user);
			}catch(Exception e){
				e.printStackTrace();
			}
		//具有小区权限
		roleIds = new HashSet<String>();
		roleIds.add(RegionPartitionConstance.AUTH_BIG_REGION);
		user.setRoleids(roleIds);
		try{
			regionPartitionManager.searchRegionDept(user);
			}catch(Exception e){
				e.printStackTrace();
			}
		//具有营业部权限
		roleIds = new HashSet<String>();
		roleIds.add(RegionPartitionConstance.AUTH_SMALL_REGION);
		user.setRoleids(roleIds);
		try{
			regionPartitionManager.searchRegionDept(user);
			}catch(Exception e){
				e.printStackTrace();
			}
		//经营本部负责人
		user.setId("27396");
		user.getEmpCode().setEmpCode("002397");
		user.getEmpCode().getDeptId().setId("464252");
		user.getEmpCode().getDeptId().setPrincipal("002397");
		roleIds = new HashSet<String>();
		user.setRoleids(roleIds);
		try{
			regionPartitionManager.searchRegionDept(user);
			}catch(Exception e){
				e.printStackTrace();
			}
		//事业部负责人
		user.setId("31753");
		user.getEmpCode().setEmpCode("001513");
		user.getEmpCode().getDeptId().setId("110775");
		user.getEmpCode().getDeptId().setPrincipal("001513");
		roleIds = new HashSet<String>();
		user.setRoleids(roleIds);
		try{
			regionPartitionManager.searchRegionDept(user);
			}catch(Exception e){
				e.printStackTrace();
			}
		//大区负责人
		user.setId("17597");
		user.getEmpCode().setEmpCode("008607");
		user.getEmpCode().getDeptId().setId("291219");
		user.getEmpCode().getDeptId().setPrincipal("008607");
		roleIds = new HashSet<String>();
		user.setRoleids(roleIds);
		try{
			regionPartitionManager.searchRegionDept(user);
			}catch(Exception e){
				e.printStackTrace();
			}
		//小区负责人
		user.setId("18296");
		user.getEmpCode().setEmpCode("005220");
		user.getEmpCode().getDeptId().setId("22244");
		user.getEmpCode().getDeptId().setPrincipal("005220");
		roleIds = new HashSet<String>();
		user.setRoleids(roleIds);
		try{
		regionPartitionManager.searchRegionDept(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		//没有权限用户
		user.setId("1");
		user.getEmpCode().setEmpCode("1");
		user.getEmpCode().getDeptId().setId("1");
		user.getEmpCode().getDeptId().setPrincipal("1");
		roleIds = new HashSet<String>();
		user.setRoleids(roleIds);
		try{
			regionPartitionManager.searchRegionDept(user);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testSaveRegionPartition(){
		try{
			regionPartitionManager.saveRegionPartition("1", "1", user);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testDeleteRegionPartition(){
		try{
			regionPartitionManager.deleteRegionPartition("1", user);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testSearchRegionByDeptId(){
		regionPartitionManager.searchRegionByDeptId("1");
	}
}
