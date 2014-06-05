package com.deppon.crm.module.marketing.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.marketing.server.manager.ICustomerCallManager;
import com.deppon.crm.module.marketing.server.manager.impl.CustomerCallManager;
import com.deppon.crm.module.marketing.shared.domain.CCPushMarketingInfo;
import com.deppon.crm.module.marketing.shared.domain.CCPushPlanInfo;
import com.deppon.crm.module.marketing.shared.domain.CCQueryMarketingInfo;
import com.deppon.crm.module.marketing.shared.domain.CustomerCallVO;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinion;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotential;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;

/**
 * <p>
 * Description:客户来电录入<br />
 * </p>
 * 
 * @author 钟琼
 * @time 2012-11-06
 */
public class CustomerCallManagerTest{
	private ICustomerCallManager customerCallManager;
	private JdbcTemplate jdbc;
	public CustomerCallVO customerCallVO;
	private User user;

	/**
	 * <p>
	 * Description:根据客户手机查询是否存在固定客户潜散客<br />
	 * </p>
	 * 
	 * @author 钟琼
	 * @time 2012-11-06
	 */
	@Before
	public void setUp() throws Exception {
		
		jdbc = (JdbcTemplate) SpringTestHelper.get()
				.getBean(JdbcTemplate.class);
		customerCallManager = (ICustomerCallManager) SpringTestHelper.get()
				.getBean(CustomerCallManager.class);
		user = new User();
		Employee emp = new Employee();
		emp.setId("18706");
		Department dept = new Department();
		dept.setId("11469");
		emp.setDeptId(dept);
		user.setEmpCode(emp);
		UserContext.setCurrentUser(user);
	}

//	@Test
	public void testQueryCustomerByMobileAndDeptId() {
		//电话部门全为null
		customerCallVO = customerCallManager.queryCustomerByMobileAndDeptId(
				null, null);
		// 电话not null，部门 null
		customerCallVO = customerCallManager.queryCustomerByMobileAndDeptId(
				"13415907515", null);

		// 电话null，部门not null
		customerCallVO = customerCallManager.queryCustomerByMobileAndDeptId(
				null, "11333");
		
		// 此手机号码 存在会员 不存在潜散客
		String mobile = "13415907514";
		String deptId = "104";
		customerCallVO = customerCallManager.queryCustomerByMobileAndDeptId(
				mobile, deptId);
//		Assert.assertNotNull(customerCallVO);
		// 此手机号码 和部门 存在潜散客 不存在会员
		mobile = "13818292438";
		deptId = "11333";
		customerCallVO = customerCallManager.queryCustomerByMobileAndDeptId(
				mobile, deptId);
//		Assert.assertNotNull(customerCallVO);

		// 无电话、无名字、无部门
		customerCallVO = customerCallManager.queryCustomerByPhoneDeptIdName(
				null, null, null);
		customerCallVO = customerCallManager.queryCustomerByPhoneDeptIdName(
				"12345678", null, null);
		customerCallVO = customerCallManager.queryCustomerByPhoneDeptIdName(
				"12345678", "朱", null);
		customerCallVO = customerCallManager.queryCustomerByPhoneDeptIdName(
				"12345678", "朱", "11333");
		
		
		// 此固话号码 和部门id 存在潜散客 不存在 会员
		mobile = "84062738";
		deptId = "11161";
		String name = "黄小姐";
		customerCallVO = customerCallManager.queryCustomerByPhoneDeptIdName(
				mobile, name, deptId);
//		Assert.assertNotNull(customerCallVO);

		mobile = "0137-10522799";
		deptId = "11161";
		name = "陈伟明";
		customerCallVO = customerCallManager.queryCustomerByPhoneDeptIdName(
				mobile, name, deptId);
//		Assert.assertNotNull(customerCallVO);

	}

	@Test
	public void testQueryDepartmentWithCurrentUser() {
		BussinessDept dept = customerCallManager
				.queryDepartmentWithCurrentUser(user);
		Assert.assertNotNull(dept);
//		Assert.assertEquals("临沂兰山区临西九路营业部", dept.getDeptName());
//		Assert.assertEquals("147", dept.getCity().getId());
		user.getEmpCode().getDeptId().setId("00");
		dept = customerCallManager.queryDepartmentWithCurrentUser(user);
//		Assert.assertNull(dept);
	}

	@Test
	public void testProcessMarketData() {
		CustomerCallVO vo = new CustomerCallVO();
		vo.setCustName("苏玉军");
		Date now = new Date();
		long l = now.getTime();
		String mobile = String.valueOf(l).substring(0, 11);
		System.out.println(mobile);
		vo.setContactMobile(mobile);
		vo.setContactPhone(String.valueOf(l).substring(0, 11));
		vo.setCustLinkManName("苏玉军");
		vo.setBusinessType(Constant.CUST_BUSTYPE_EXANDLD);
		List<ReturnVisitOpinion> optionList = new ArrayList<ReturnVisitOpinion>();
		/**
		 * 客户意见
		 */
		ReturnVisitOpinion op1 = new ReturnVisitOpinion();
		ReturnVisitOpinion op2 = new ReturnVisitOpinion();
		op1.setOpinionType("EFFICIENCY");
		op1.setProblem("我的测试回访，客户意见");
		op1.setSolution("我的测试回访，客户意见解决办法");
		op2.setOpinionType("OTHER");
		op2.setProblem("我的测试回访，客户意见");
		op2.setSolution("我的测试回访，客户意见解决办法");
		optionList.add(op1);
		optionList.add(op2);
		/**
		 * 走货潜力
		 */
		List<ReturnVisitVolumePotential> volumePotentialList = new ArrayList<ReturnVisitVolumePotential>();
		ReturnVisitVolumePotential rv1 = new ReturnVisitVolumePotential();
		ReturnVisitVolumePotential rv2 = new ReturnVisitVolumePotential();
		rv1.setComeFromCity("兰州");
		rv1.setComeToCity("西安");
		rv1.setVolumePotential("600-3000");
		rv1.setCompanyId("SHUNFENG");
		rv1.setAlreadyHaveLine("YES");
		rv1.setRemark("我有意向");

		rv2.setComeFromCity("兰州");
		rv2.setComeToCity("北京");
		rv2.setVolumePotential("600-3000");
		rv2.setCompanyId("SHUNFENG");
		rv2.setAlreadyHaveLine("YES");
		rv2.setRemark("我有意向2");
		
		volumePotentialList.add(rv1);
		volumePotentialList.add(rv2);
		customerCallManager.processMarketData(vo, optionList, volumePotentialList,user);
		
		vo.setId("400053889"); // 客户已存在
		customerCallManager.processMarketData(vo, optionList, volumePotentialList,user);
	}
	
//	@Test
	public void testQueryMarketingHistory(){
		CustomerCallVO vo = new CustomerCallVO();
		try {
			// 客户ID null
			customerCallManager.queryMarketingHistory(vo, user);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		// 客户类型 null
		try {
			vo.setId("408779543");
			customerCallManager.queryMarketingHistory(vo, user);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			vo.setContactType("PC_CUSTOMER");
			customerCallManager.queryMarketingHistory(vo, user);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		
	}
	
	@Test
	public void testQueryRvOptionByMarketingIds(){
		CustomerCallVO vo = new CustomerCallVO();
		try {
			// 能找到marketingIds记录
			vo.setId("408779543");
			vo.setContactType("PC_CUSTOMER");
			customerCallManager.queryRvOptionByMarketingIds(vo, user, 0, 10);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			// 不能找到marketingIds记录
			vo.setId("11111");
			vo.setContactType("PC_CUSTOMER");
			customerCallManager.queryRvOptionByMarketingIds(vo, user, 0, 10);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		
	}
	
	@Test
	public void testQueryRvPotentialByMarketingIds(){
		CustomerCallVO vo = new CustomerCallVO();
		try {
			// 能找到marketingIds记录
			vo.setId("408779543");
			vo.setContactType("PC_CUSTOMER");
			customerCallManager.queryRvPotentialByMarketingIds(vo, user, 0, 10);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		try {
			// 不能找到marketingIds记录
			vo.setId("11111");
			vo.setContactType("PC_CUSTOMER");
			customerCallManager.queryRvPotentialByMarketingIds(vo, user, 0, 10);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		
	}
	
	@Test
	public void testAddCCMarketingInfo(){
		boolean result = false;
		CCPushMarketingInfo info = null;
		result = customerCallManager.addCCMarketingInfo(info);
		Assert.assertFalse(result);
		info = new CCPushMarketingInfo();
		info.setCustNumber("20100430-02847721");
		info.setCellphone("13917380852");
		info.setTelephone("0752-2786263");
		info.setMarketDept("DP01563");
		info.setMarketPerson("065372");
		info.setNeedType("SERVICE");
		info.setMarketTime(new Date());
		info.setNeedQuestion("我想要低价，测试CC营销信息推送到CRM");
		info.setSolution("部门给考虑打折");
		info.setCustName("长江灯具/王喜洋");
		result = customerCallManager.addCCMarketingInfo(info);
		Assert.assertTrue(result);
	}
	
	@Test
	public void testQueryMarketingInfoToCC(){
		String custNumber = "400462934";
		int start = 0;
		int limit = 10;
		CCQueryMarketingInfo  info = customerCallManager.queryMarketingInfoToCC(custNumber, start, limit);
		Assert.assertNotNull(info.getCount());
	}
	
	@Test
	public void testAddCCPlanInfo(){
		CCPushPlanInfo info = new CCPushPlanInfo();
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.setTime(new Date());
		start.add(Calendar.DATE, 3);
		end.setTime(new Date());
		end.add(Calendar.DATE, 30);
		info.setCreatorCode("065372");
		info.setCustNumber("134423");
		info.setExecuteDept("DP01563");
		info.setPlanEndTime(new Date(end.getTimeInMillis()));
		info.setPlanStartTime(new Date(start.getTimeInMillis()));
		info.setPlanTheme("呼叫中心计划推送" + new Random(10000000000L).toString());
		info.setRemark("上海市");
		String result = customerCallManager.addCCPlanInfo(info);
		System.out.println(result);
	}
}
