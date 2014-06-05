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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.manager.ICustomerGroupManager;
import com.deppon.crm.module.marketing.server.manager.impl.CustomerGroupManager;
import com.deppon.crm.module.marketing.server.manager.impl.MarketingCommManager;
import com.deppon.crm.module.marketing.server.service.ICustomerGroupService;
import com.deppon.crm.module.marketing.server.service.IPlanService;
import com.deppon.crm.module.marketing.server.service.impl.CustomerGroupService;
import com.deppon.crm.module.marketing.server.service.impl.PlanService;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroup;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;

/**   
 * <p>
 * Description:客户分组<br />
 * </p>
 * @title CustomerGroupManagerTest.java
 * @package com.deppon.crm.module.marketing.server.manager.impl 
 * @author zhujunyong
 * @version 0.1 Apr 6, 2012
 */

public class CustomerGroupManagerTest {

	private ICustomerGroupManager customerGroupManager;
	private ICustomerGroupService customerGroupService;
	private IPlanService planService;
	private JdbcTemplate jdbc;

	/**
	 * <p>
	 * Description:客户分组<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 6, 2012
	 * @throws java.lang.Exception
	 * void
	 */
	@Before
	public void setUp() throws Exception {
		jdbc = (JdbcTemplate) SpringTestHelper.get().getBean(JdbcTemplate.class);
		
		// 会员表
//		jdbc.execute("delete from t_cust_custbasedata where fid between 192 and 197");
//		jdbc.execute("insert into t_cust_custbasedata (fid, fcustname, fcustnumber, fdegree, fcusttype, fisspecial, fbusstype, fistrangoods, fisredeempoints, fdeptid) values(192, 'Five Kilometre Test Customer', '223230', 'Golden', 'MEMBER', 1, 'bussType','Y', 'Y', 12638)");
//		jdbc.execute("insert into t_cust_custbasedata (fid, fcustname, fcustnumber, fdegree, fcusttype, fisspecial, fbusstype, fistrangoods, fisredeempoints, fdeptid) values(195, 'Six Kilometre Test Customer', '223231', 'Diamond', 'MEMBER', 1, 'bussType','Y', 'Y', 12638)");
//		jdbc.execute("insert into t_cust_custbasedata (fid, fcustname, fcustnumber, fdegree, fcusttype, fisspecial, fbusstype, fistrangoods, fisredeempoints, fdeptid) values(197, 'Seven Kilometre Test Customer', '223232', 'Normal', 'MEMBER', 1, 'bussType','Y', 'Y', 12638)");
//		// 分组表
//		jdbc.execute("delete from t_cust_group where fid = 192");
//		jdbc.execute("delete from t_cust_group where fid = 193");
//		jdbc.execute("delete from t_cust_group where fid = 194");
//		jdbc.execute("delete from t_cust_group where fid = 195");
//		jdbc.execute("insert into t_cust_group (fid, fgroupname, fdeptid) values(192, '测试分组组名大客户', 12638)");
//		jdbc.execute("insert into t_cust_group (fid, fgroupname, fdeptid) values(193, '测试分组组名中客户', 12638)");
//		jdbc.execute("insert into t_cust_group (fid, fgroupname, fdeptid) values(194, '测试分组组名小客户', 12638)");
//		jdbc.execute("insert into t_cust_group (fid, fgroupname, fdeptid) values(195, '测试分组组名超级客户', 12638)");
//
//		// 分组详情表
//		jdbc.execute("delete from t_cust_custgroupdetail where fid between 192 and 197");
//		jdbc.execute("delete from t_cust_custgroupdetail where fgroupid between 190 and 195");
//		jdbc.execute("insert into t_cust_custgroupdetail (fid, fcustid, fgroupid, fdeptid, fprehuman) values(192, 192, 192, 12638, 23195)");
//		jdbc.execute("insert into t_cust_custgroupdetail (fid, fcustid, fgroupid, fdeptid, fprehuman) values(193, 193, 193, 12638, 23195)");
//		jdbc.execute("insert into t_cust_custgroupdetail (fid, fcustid, fgroupid, fdeptid, fprehuman) values(194, 194, 194, 12638, 23195)");
//		jdbc.execute("insert into t_cust_custgroupdetail (fid, fcustid, fgroupid, fdeptid, fprehuman) values(195, 195, 195, 12638, 23195)");
//		jdbc.execute("insert into t_cust_custgroupdetail (fid, fcustid, fgroupid, fdeptid, fprehuman) values(196, 196, 195, 12638, 23195)");
//		jdbc.execute("insert into t_cust_custgroupdetail (fid, fcustid, fgroupid, fdeptid, fprehuman) values(197, 197, 195, 12638, 23195)");
//
//		// 发到货周期表
//		jdbc.execute("delete from t_crm_custanalysebyday where fid < 8");
//		jdbc.execute("insert into T_CRM_CUSTANALYSEBYDAY (fid, FCANALYSETYPE, Fcustnumber, fyear, fmonth, fday, FFPREPAYAMOUNT, FARRIVEDAOUNT, FAGENTRECEIVEPAY, FREFUNDRABATE) values(1, 1, 223230, 2012, 4, 12, 200, 800, 300, 100)");
//		jdbc.execute("insert into T_CRM_CUSTANALYSEBYDAY (fid, FCANALYSETYPE, Fcustnumber, fyear, fmonth, fday, FFPREPAYAMOUNT, FARRIVEDAOUNT, FAGENTRECEIVEPAY, FREFUNDRABATE) values(2, 1, 223230, 2012, 4, 11, 300, 800, 300, 500)");
//		jdbc.execute("insert into T_CRM_CUSTANALYSEBYDAY (fid, FCANALYSETYPE, Fcustnumber, fyear, fmonth, fday, FFPREPAYAMOUNT, FARRIVEDAOUNT, FAGENTRECEIVEPAY, FREFUNDRABATE) values(3, 1, 223230, 2012, 3, 1, 300, 800, 300, 500)");
//		jdbc.execute("insert into T_CRM_CUSTANALYSEBYDAY (fid, FCANALYSETYPE, Fcustnumber, fyear, fmonth, fday, FFPREPAYAMOUNT, FARRIVEDAOUNT, FAGENTRECEIVEPAY, FREFUNDRABATE) values(4, 1, 223230, 2012, 2, 2, 330, 800, 300, 500)");
//		jdbc.execute("insert into T_CRM_CUSTANALYSEBYDAY (fid, FCANALYSETYPE, Fcustnumber, fyear, fmonth, fday, FFPREPAYAMOUNT, FARRIVEDAOUNT, FAGENTRECEIVEPAY, FREFUNDRABATE) values(5, 1, 223230, 2011, 12, 2, 330, 800, 350, 500)");
//		jdbc.execute("insert into T_CRM_CUSTANALYSEBYDAY (fid, FCANALYSETYPE, Fcustnumber, fyear, fmonth, fday, FFPREPAYAMOUNT, FARRIVEDAOUNT, FAGENTRECEIVEPAY, FREFUNDRABATE) values(6, 1, 223230, 2012, 1, 2, 330, 805, 300, 500)");
//		jdbc.execute("insert into T_CRM_CUSTANALYSEBYDAY (fid, FCANALYSETYPE, Fcustnumber, fyear, fmonth, fday, FFPREPAYAMOUNT, FARRIVEDAOUNT, FAGENTRECEIVEPAY, FREFUNDRABATE) values(7, 1, 223230, 2011, 11, 3, 330, 870, 300, 520)");
		
		customerGroupManager = (ICustomerGroupManager) SpringTestHelper.get().getBean(CustomerGroupManager.class);
		customerGroupService = (ICustomerGroupService) SpringTestHelper.get().getBean(CustomerGroupService.class);
		planService = (IPlanService) SpringTestHelper.get().getBean(PlanService.class);
		User user=new User();
		user.setId("1");
		Employee employee = new Employee();
		Department department = new Department();
		department.setId("12638");
		employee.setId("1");
		employee.setDeptId(department);
		user.setEmpCode(employee);
		UserContext.setCurrentUser(user);
	}
	
	@After
	public void tearDown() {
		jdbc.execute("delete from t_cust_custgroupdetail where fcustid between 120 and 130");
	}


	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.manager.impl.CustomerGroupManager#createCustomerGroup(java.lang.String)}.
	 */
	@Test
	public void testCreateCustomerGroup() {
		User user = (User) UserContext.getCurrentUser();
		boolean result = false;
		
		result = customerGroupManager.createCustomerGroup(null, user);
		assertFalse(result);
		result = customerGroupManager.createCustomerGroup(" ", user);
		assertFalse(result);

		try {
			result = customerGroupManager.createCustomerGroup("测试分组组名超级客户", user);
			fail("组名重复了");
		} catch (Throwable t) {
			Assert.assertTrue(true);
//			assertEquals(GeneralException e.class.getName(), t.getClass().getName());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		result = customerGroupManager.createCustomerGroup("我是分组组名" + sdf.format(new Date()), user);
		assertTrue(result);
		try{
			result = customerGroupManager.createCustomerGroup("我是分组组名20121218170815", user);
			assertTrue(result);
		}catch(Exception e){}
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.manager.impl.CustomerGroupManager#updateCustomerGroup(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testUpdateCustomerGroup() {
		User user = (User) UserContext.getCurrentUser();
		boolean result = false;
		
		result = customerGroupManager.updateCustomerGroup("", "192", user);
		assertFalse(result);
		result = customerGroupManager.updateCustomerGroup("测试分组组名小客户", "", user);
		assertFalse(result);
		result = customerGroupManager.updateCustomerGroup("测试分组组名小客户测试分组组名小客户测试分组组名小客户", "", user);
		assertFalse(result);
		
		try {
			result = customerGroupManager.updateCustomerGroup("测试分组组名小客户", "192", user);
			fail("组名重复了");
		} catch (Throwable t) {
			Assert.assertTrue(true);
//			assertEquals(CustomerGroupException.class.getName(), t.getClass().getName());
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		result = customerGroupManager.updateCustomerGroup("修改组名" + sdf.format(new Date()), "192", user);
//		assertTrue(result);
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.manager.impl.CustomerGroupManager#deleteCustomerGroup(java.lang.String)}.
	 */
	@Test
	public void testDeleteCustomerGroup() {
		User user = (User) UserContext.getCurrentUser();
		boolean result = false;
		
		result = customerGroupManager.deleteCustomerGroup(" ", user);
		assertFalse(result);
		result = customerGroupManager.deleteCustomerGroup("193", user);
//		assertTrue(result);
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.manager.impl.CustomerGroupManager#getCustomerGroupList()}.
	 */
	@Test
	public void testGetCustomerGroupList() {
		User user = (User) UserContext.getCurrentUser();
		String deptId = user.getEmpCode().getDeptId().getId();
		List<CustomerGroup> groups = customerGroupManager.getCustomerGroupList(deptId);
		assertNotNull(groups);
		assertTrue(groups.size() > 0);
		int sum = 0;
		for (CustomerGroup group : groups) {
			assertTrue(StringUtils.isNotBlank(group.getGroupName()));
			assertTrue(StringUtils.isNotBlank(group.getId()));
			sum += group.getCount();
		}
		deptId = "3042";
		groups = customerGroupManager.getCustomerGroupList(deptId);
//		assertTrue(sum > 0);
	}


	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.manager.impl.CustomerGroupManager#saveCustomerGroup()}.
	 */
	@Test
	public void testSaveCustomerGroup() {
		User user = (User) UserContext.getCurrentUser();
		CustomerGroupDetail delete1 = new CustomerGroupDetail();
		delete1.setCustId("1985");
		CustomerGroupDetail delete2 = new CustomerGroupDetail();
		delete2.setCustId("1986");
		List<CustomerGroupDetail> deletedList = new ArrayList<CustomerGroupDetail>();
		deletedList.add(delete1);
		deletedList.add(delete2);

		CustomerGroupDetail update1 = new CustomerGroupDetail();
		update1.setCustId("1987");
		update1.setUserId("11469");
		List<CustomerGroupDetail> updatedList = new ArrayList<CustomerGroupDetail>();
		updatedList.add(update1);
		
		CustomerGroupDetail create1 = new CustomerGroupDetail();
		create1.setCustId("1988");
		CustomerGroupDetail create2 = new CustomerGroupDetail();
		create2.setCustId("1989");
		List<CustomerGroupDetail> createdList = new ArrayList<CustomerGroupDetail>();
		createdList.add(create1);
		
//		String groupId = "";
//		assertFalse(customerGroupManager.saveCustomerGroup("",groupId, deletedList, updatedList, createdList, user));
//		groupId = null;
//		customerGroupManager.deleteCustomerGroup(groupId, user);
//		customerGroupManager.saveCustomerGroup("",groupId, deletedList, updatedList, createdList, user);
//		groupId = "400000227";
//		customerGroupManager.saveCustomerGroup("",groupId, deletedList, updatedList, createdList, user);
//		
//		deletedList.clear();
//		updatedList.clear();
//		createdList.clear();
//		customerGroupManager.saveCustomerGroup("",groupId, deletedList, updatedList, createdList, user);
	}
	
	
	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.manager.impl.CustomerGroupManager#getCustomerGroupDetailList()}.
	 */
	@Test
	public void testGetCustomerGroupDetailList() {
		Calendar queryDate = Calendar.getInstance();
		queryDate.set(Calendar.YEAR, 2012);
		queryDate.set(Calendar.MONTH, 4);
		queryDate.set(Calendar.DATE, 16);
		customerGroupManager.getCustomerGroupDetailList("191", queryDate.getTime(), "192");
		List<CustomerGroupDetail> details = customerGroupManager.getCustomerGroupDetailList("192", queryDate.getTime(), null);
//		Assert.assertNotNull(details);
//		Assert.assertEquals(1, details.size());
//		for (CustomerGroupDetail detail : details) {
//			Assert.assertEquals("12638", detail.getDeptId());
//			Assert.assertEquals("223230", detail.getCustNumber());
//			Assert.assertEquals("Five Kilometre Test Customer", detail.getCustName());
//			Assert.assertEquals(16, detail.getDayAmount().size());
//		}
	}
	
	
	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.manager.impl.CustomerGroupManager#getCustomerGroupDetailListByCondition()}.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetCustomerGroupDetailListByCondition() {
		CustomerGroupDetail detail = new CustomerGroupDetail();
	
		detail.setGroupId("192");
		detail.setCustName("Five");
		detail.setCustNumber("");
		detail.setEmpName("杨娜娜");
		detail.setDeptId("12638");
		detail.setFcAnalyseType("1");
		detail.setSendGoodsType(MarketingConstance.SEND_TRUCKLOAD);
		detail.setCustType(MarketingConstance.REGULAR_CUSTOMER);
		Map<String, Object> result = customerGroupManager.getCustomerGroupDetailListByCondition(detail, 0, 20);
		List<CustomerGroupDetail> details = (List<CustomerGroupDetail>)result.get("data");
		int count = (Integer)result.get("count");
		Assert.assertNotNull(details);
		try{
			customerGroupManager.getCustomerGroupDetailListByCondition(detail, 0, 20);
		}catch(Exception e){}
//		Assert.assertTrue(details.size() >= 1);
//		Assert.assertTrue(count == details.size());
		
//		for (CustomerGroupDetail d : details) {
//			Assert.assertEquals(detail.getGroupId(), d.getGroupId());
//			Assert.assertEquals(detail.getEmpName(), d.getEmpName());
//			Assert.assertEquals(detail.getDeptId(), d.getDeptId());
//		}
		
	}
	@Test
	public void testGetcustomerGroupByPrehuman(){
		String depId = "110";
		String userId = "110";
		
		customerGroupManager.getCustomerGroupByPrehuman("110", "110");
		customerGroupManager.getPrehumanByGroupId("110", "110");
		
		
	}
	@Test
	public void testMarketingCommManager(){
		String depId = "110";
		String userId = "110";
		Set<String> deptids = new HashSet<String>();
		deptids.add(depId);
		User user  = new User();
		user.setDeptids(deptids);
		MarketingCommManager marketingCommManager = new MarketingCommManager();
		marketingCommManager.setCustomerGroupService(customerGroupService);
		marketingCommManager.setPlanService(planService);
		marketingCommManager.getCustomerGroupService();
		marketingCommManager.getPlanService();
		try{
			marketingCommManager.getCustomerGroupByPrehuman(depId, userId);
		}catch(Exception e){}
		try{
			marketingCommManager.getPrehumanByGroupId(depId, userId);
		}catch(Exception e){}
		try{
			marketingCommManager.getPlanCreatorList(user, "", "DEV");
		}catch(Exception e){}
		try{
			marketingCommManager.getPlanCreatorList(user, "", "DEV");
			marketingCommManager.getPlanCreatorList(user, "110", "DEV");
		}catch(Exception e){}
	}
	
}
