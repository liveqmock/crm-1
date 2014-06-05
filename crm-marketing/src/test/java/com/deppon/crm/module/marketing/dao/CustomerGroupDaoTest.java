/**   
 * <p>
 * Description:客户分组<br />
 * </p>
 * @title CustomerGroupDaoTest.java
 * @package com.deppon.crm.module.marketing.server.dao.impl 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */
package com.deppon.crm.module.marketing.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.marketing.server.dao.ICustomerGroupDao;
import com.deppon.crm.module.marketing.server.dao.impl.CustomerGroupDao;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroup;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroupDay;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroupMonth;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.crm.module.organization.shared.domain.Employee;

/**   
 * <p>
 * Description:客户分组<br />
 * </p>
 * @title CustomerGroupDaoTest.java
 * @package com.deppon.crm.module.marketing.server.dao.impl 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */

public class CustomerGroupDaoTest {

	private static ICustomerGroupDao customerGroupDao;
	
	static{
		customerGroupDao = (ICustomerGroupDao) SpringTestHelper.get().getBean(CustomerGroupDao.class);
	}
	/**
	 * <p>
	 * Description:客户分组<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 5, 2012
	 * @throws java.lang.Exception
	 * void
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * <p>
	 * Description:客户分组<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 5, 2012
	 * @throws java.lang.Exception
	 * void 
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.dao.impl.CustomerGroupDao#getCustomerGroupNameList(java.lang.String)}.
	 */
	@Test
	public void testGetCustomerGroupNameList() {
		String deptId = "12638";
		List<CustomerGroup> list = customerGroupDao.getCustomerGroupNameList(deptId);
//		Assert.assertNotNull(list);
//		Assert.assertTrue(list.size() >= 4);
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.dao.impl.CustomerGroupDao#getCustomerGroupCountList(java.lang.String)}.
	 */
	@Test
	public void testGetCustomerGroupCountList() {
		String deptId = "12638";
		List<CustomerGroup> list = customerGroupDao.getCustomerGroupCountList(deptId);
		Assert.assertNotNull(list);
//		Assert.assertTrue(list.size() >= 1);
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.dao.impl.CustomerGroupDao#createCustomerNameGroup(com.deppon.crm.module.marketing.shared.domain.CustomerGroup)}.
	 */
	@Test
	public void testCreateCustomerNameGroup() {
		Date now = new Date();
		
		CustomerGroup c = new CustomerGroup();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		c.setGroupName(sdf.format(now));
		c.setDeptId("12638");
		c.setCreateDate(now);
		c.setCreateUser("23195");
		c.setModifyDate(now);
		c.setModifyUser("23195");
		Assert.assertTrue(customerGroupDao.createCustomerNameGroup(c));
		
		customerGroupDao.deleteCustomerGroupDetail(c.getId(), c.getDeptId());
		
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.dao.impl.CustomerGroupDao#updateCustomerNameGroup(com.deppon.crm.module.marketing.shared.domain.CustomerGroup)}.
	 */
	@Test
	public void testUpdateCustomerNameGroup() {
		CustomerGroup c = new CustomerGroup();
		c.setId("192");
		c.setGroupName("TestUpdateGroupName");
		c.setModifyDate(new Date());
		c.setModifyUser("23195");
		c.setDeptId("12638");
		customerGroupDao.updateCustomerNameGroup(c);
		c.setId("191");
		customerGroupDao.updateCustomerNameGroup(c);
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.dao.impl.CustomerGroupDao#deleteCustomerGroup(java.lang.String)}.
	 */
	@Test
	public void testDeleteCustomerGroup() {
		String id = "195";
		String deptId = "12638";
		customerGroupDao.deleteCustomerGroup(id, deptId);
//		Assert.assertTrue(customerGroupDao.deleteCustomerGroup(id, deptId));
		id = "191";
		customerGroupDao.deleteCustomerGroup(id, deptId);
//		Assert.assertFalse(customerGroupDao.deleteCustomerGroup(id, deptId));
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.dao.impl.CustomerGroupDao#deleteCustomerGroupDetail(java.lang.String)}.
	 */
	@Test
	public void testDeleteCustomerGroupDetail() {
		String groupId = "195";
		String deptId = "12638";
//		Assert.assertTrue(customerGroupDao.deleteCustomerGroupDetail(groupId, deptId));
		groupId = "191";
//		Assert.assertFalse(customerGroupDao.deleteCustomerGroupDetail(groupId, deptId));
	}

	
	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.dao.impl.CustomerGroupDao#getCustomerGroupDetailList(java.lang.String)}.
	 */
	@Test
	public void testGetCustomerGroupDetailList() {
		String customerGroupId = "192";
		List<CustomerGroupDetail> details = customerGroupDao.getCustomerGroupDetailList(customerGroupId,new Date(), "1");

		Assert.assertNotNull(details);
//		Assert.assertTrue(details.size() > 0);
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.dao.impl.CustomerGroupDao#deleteCustomerGroupCustomer(java.lang.String)}.
	 */
	@Test
	public void testDeleteCustomerGroupCustomer() {
		CustomerGroupDetail detail = new CustomerGroupDetail();
		detail.setGroupId("192");
		detail.setCustId("192");
		Assert.assertTrue(customerGroupDao.deleteCustomerGroupCustomer(detail));
		detail.setGroupId("191");
		Assert.assertFalse(customerGroupDao.deleteCustomerGroupCustomer(detail));
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.dao.impl.CustomerGroupDao#createCustomerGroupCustomer(java.lang.String)}.
	 */
	@Test
	public void testCreateCustomerGroupCustomer() {
		CustomerGroupDetail detail = new CustomerGroupDetail();
		Date now = new Date();
		detail.setGroupId("192");
		detail.setCustId("192");
		detail.setCreateDate(now);
		detail.setCreateUser("192");
		detail.setModifyDate(now);
		detail.setModifyUser("192");
		detail.setDeptId("12638");
		customerGroupDao.deleteCustomerGroupCustomer(detail);
		Assert.assertTrue(customerGroupDao.createCustomerGroupCustomer(detail));
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.dao.impl.CustomerGroupDao#updateCustomerGroupCustomer(java.lang.String)}.
	 */
	@Test
	public void testUpdateCustomerGroupCustomer() {
		CustomerGroupDetail detail = new CustomerGroupDetail();
		detail.setGroupId("192");
		detail.setCustId("192");
		detail.setUserId("23199");
		customerGroupDao.updateCustomerGroupCustomer(detail);
		detail.setGroupId("191");
		customerGroupDao.updateCustomerGroupCustomer(detail);
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.dao.impl.CustomerGroupDao#getCustomerGroupDetailMonthAmount(java.lang.String)}.
	 */
	@Test
	public void testGetCustomerGroupDetailMonthAmount() {
		List<String> customerNumbers = new ArrayList<String>();
		customerNumbers.add("223230");
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("year", 2012);
		condition.put("year1", 2012);
		condition.put("year2", 2012);
		condition.put("month", 4);
		condition.put("month1", 3);
		condition.put("month2", 2);
		condition.put("day", 12);
		condition.put("customerNumbers", customerNumbers);
		List<CustomerGroupMonth> amounts = customerGroupDao.getCustomerGroupDetailMonthAmount(condition);
		Assert.assertNotNull(amounts);
//		Assert.assertEquals(3, amounts.size());
		
//		for (CustomerGroupMonth c : amounts) {
//			Assert.assertNotNull(c.getCustomerNumber());
//			Assert.assertNotNull(c.getYear());
//			Assert.assertNotNull(c.getMonth());
//			Assert.assertNotNull(c.getAmount());
//		}
	}
	
	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.dao.impl.CustomerGroupDao#getCustomerGroupDetailDayAmount(java.lang.String)}.
	 */
	@Test
	public void testGetCustomerGroupDetailDayAmount() {
		List<String> customerNumbers = new ArrayList<String>();
		customerNumbers.add("223230");
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("year", 2012);
		condition.put("month", 4);
		condition.put("day", 12);
		condition.put("customerNumbers", customerNumbers);
		List<CustomerGroupDay> amounts = customerGroupDao.getCustomerGroupDetailDayAmount(condition);
		Assert.assertNotNull(amounts);
//		Assert.assertTrue(amounts.size() >= 2);
		
//		for (CustomerGroupDay c : amounts) {
//			Assert.assertNotNull(c.getCustomerNumber());
//			Assert.assertNotNull(c.getDay());
//			Assert.assertNotNull(c.getAmount());
//		}
	}
	
	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.dao.impl.CustomerGroupDao#getCustomerGroupDetailListByCondition(java.lang.String)}.
	 */
	@Test
	public void testGetCustomerGroupDetailListByCondition() {
		CustomerGroupDetail detail = new CustomerGroupDetail();
//		detail.setGroupId("192");
//		detail.setCustName("%Five%");
//		detail.setCustNumber("");
//		detail.setEmpName("杨娜娜");
//		detail.setDeptId("12638");
//		detail.setFcAnalyseType("1");
		detail.setDeptId("11469");
//		detail.setCyclePage(true);
		detail.setQueryDate(new Date());
		detail.setFcAnalyseType("1");
		List<CustomerGroupDetail> details = customerGroupDao.getCustomerGroupDetailListByCondition(detail, 0, 20);
		
		Assert.assertNotNull(details);
		Assert.assertTrue(details.size() > 0);
//		for (CustomerGroupDetail d : details) {
//			Assert.assertEquals(detail.getGroupId(), d.getGroupId());
//			Assert.assertEquals(detail.getEmpName(), d.getEmpName());
//			Assert.assertEquals(detail.getDeptId(), d.getDeptId());
//		}
		
		
		
		int count = customerGroupDao.getCustomerGroupDetailListByConditionCount(detail);
//		Assert.assertTrue(count >= details.size());
	}

	@Test
	public void testGetPrehumanByDeptId(){
		List<Employee> el = customerGroupDao.getPrehumanByDeptId("11469");	
		for (Employee e: el){
			System.out.println(e.getId()+" " + e.getEmpName());
		}
	}
	
	@Test
	public void testGetCustomerGroupByPrehuman(){
		CustomerGroup cg = new CustomerGroup();
		cg.setDeptId("11469");
		cg.setEmpId("25972");
		List<String> sl = customerGroupDao.getCustomerGroupByPrehuman(cg);
		for (String s: sl){
			System.out.println(s);
		}
	}

	@Test
	public void testGetPrehumanByGroupId(){
		CustomerGroup cg = new CustomerGroup();
		cg.setDeptId("11469");
		cg.setId("1123");
		List<String> sl = customerGroupDao.getPrehumanByGroupId(cg);
		for (String s: sl){
			System.out.println(s);
		}
	}
	
}
