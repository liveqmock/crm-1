package com.deppon.crm.module.customer.server.manager.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.TodoWorkflow;
import com.deppon.crm.module.customer.server.testutils.DataTestUtil;
import com.deppon.crm.module.customer.server.testutils.DateInitUtil;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.ApprovingWorkflowData;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.CustAddress;
import com.deppon.crm.module.customer.shared.domain.CustomerLocation;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberOperationLog;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.customer.shared.domain.MyToDoWorkFlow;
import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;
import com.deppon.crm.module.customer.shared.domain.ShuttleAddress;
import com.deppon.crm.module.customer.shared.domain.WorkFlowCondition;
import com.deppon.crm.module.customer.shared.exception.CustomerException;
import com.deppon.crm.module.customer.shared.exception.CustomerExceptionType;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.customer.shared.exception.MemberExceptionType;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;

public class AlterMemberManagerTest extends BeanUtil {

	// 目的就是为了测试私有方法
	public static AlterMemberManager newAlterMemberManager = new AlterMemberManager();

	@Before
	public void setUp() throws Exception {
		DateInitUtil.executeCleanSql();
		DataTestUtil.initAlterMemberData();
	}

	@After
	public void tearDown() throws Exception {
		DataTestUtil.clearAlterMemberData();
		DateInitUtil.executeCleanSql();
	}

	/**
	 * 
	 * <p>
	 * Description:修改固定客户信息<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2012-12-28 void
	 * @throws SQLException
	 */
	@Test
	@SuppressWarnings("rawtypes")
	public void test_alterMember() throws SQLException {

		User user = new User();
		Department depart = new Department();
		depart.setId("86301");
		depart.setDeptName("总裁");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setId("267254");
		e.setDeptId(depart);
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);

		MemberCondition searchCustCondition = new MemberCondition();
		searchCustCondition.setMemberId("400002267");
		Member member = alterMemberManager
				.getMemberAllById(searchCustCondition);

		// 修改的
		List<ApproveData> updateDataAllList = new ArrayList<ApproveData>();
		ApproveData approveData = new ApproveData();// 修改联系人 编号
		approveData.setClassId("400210063");
		approveData.setClassName(Contact.class.getSimpleName());
		approveData.setFieldName("number");
		approveData.setOldValue("xx4000693661");
		approveData.setNewValue("xx4000693661345");
		updateDataAllList.add(approveData);

		approveData = new ApproveData();// // 修改联系人手机
		approveData.setClassId("400194677");
		approveData.setClassName(Contact.class.getSimpleName());
		approveData.setFieldName("mobilePhone");
		approveData.setNewValue("13945862354");
		updateDataAllList.add(approveData);

		approveData = new ApproveData();// // 修改联系人姓名
		approveData.setClassId("400194147");
		approveData.setClassName(Contact.class.getSimpleName());
		approveData.setFieldName("name");
		approveData.setOldValue("苏宁易购");
		approveData.setNewValue("e-bay");
		updateDataAllList.add(approveData);

		approveData = new ApproveData();// 修改联系人 编号 固话
		approveData.setClassId("400210059");
		approveData.setClassName(Contact.class.getSimpleName());
		approveData.setFieldName("telPhone");
		approveData.setOldValue("4000693661");
		approveData.setNewValue("4000811111");
		updateDataAllList.add(approveData);

		approveData = new ApproveData();// 修改联系人 身份证号
		approveData.setClassId("400193899");
		approveData.setClassName(Contact.class.getSimpleName());
		approveData.setFieldName("idCard");
		approveData.setOldValue("4000693661");
		approveData.setNewValue("341221198710025338");
		updateDataAllList.add(approveData);

		approveData = new ApproveData();// 修改客户税务登记号
		approveData.setClassId("400002267");
		approveData.setClassName(Member.class.getSimpleName());
		approveData.setFieldName("taxregNumber");
		approveData.setNewValue("44190066501121576");
		updateDataAllList.add(approveData);

		// 删除
		List<ApproveData> deleteDataList = new ArrayList<ApproveData>();
		approveData = new ApproveData();
		approveData.setClassId("400210062");// 删除联系人
		approveData.setClassName(Contact.class.getSimpleName());
		deleteDataList.add(approveData);
		approveData = new ApproveData();
		approveData.setClassId("400000485");// 删除偏好地址
		approveData.setClassName(PreferenceAddress.class.getSimpleName());
		deleteDataList.add(approveData);

		// 新增的
		Map<String, List> addPojoMap = new HashMap<String, List>();
		List<Contact> addContactList = new ArrayList<Contact>();
		Contact contact = new Contact();
		contact.setName("CRM系统开发组");
		contact.setTelPhone("02155373456");
		contact.setNumber("365498731654673");
		contact.setLinkmanType("0,0,1,0,0");
		contact.setIsMainLinkMan(false);
		addContactList.add(contact);

		List<PreferenceAddress> preferenceAddressList = new ArrayList<PreferenceAddress>();
		PreferenceAddress pa = new PreferenceAddress();
		pa.setAddress("上海-上海市-宝山区-刘场路438号");
		pa.setAddressType("RECEIVE_GOODS");
		preferenceAddressList.add(pa);

		List<ShuttleAddress> shuttleAddressList = new ArrayList<ShuttleAddress>();
		ShuttleAddress sa = new ShuttleAddress();
		sa.setAddress("上海市青浦区明珠路1018号");
		sa.setAddressType("RECEIVE_GOODS");
		shuttleAddressList.add(sa);

		addPojoMap.put("Contact", addContactList);
		addPojoMap.put("PreferenceAddress", preferenceAddressList);
		addPojoMap.put("Account", new ArrayList<Account>());
		addPojoMap.put("ShuttleAddress", shuttleAddressList);

		try {
			alterMemberManager.alterMember(updateDataAllList, addPojoMap,
					deleteDataList, member.getId(), member);
		} catch (Exception exp) {
		}

		clearAlterMember(addPojoMap);

	}


	@Test
	public void test_searchPreferenceAddressByContactId() {
		List<PreferenceAddress> paList = alterMemberManager
				.searchPreferenceAddressByContactId("24980",
						"RECEIVE_SEND_GOODS");
		Assert.assertEquals(1, paList.size());
	}

	@Test
	public void test_cleanContactPreferenceAddress() {
		alterMemberManager.cleanContactPreferenceAddress("4434");
	}

	@Test
	public void test_addContactType() {
		
		alterMemberManager.addContactType("1",Constant.ContactType_Treaty);
		try {
			alterMemberManager.addContactType("0", 1);
		} catch (Exception e) {
		}
		

	}

	/**
	 * 
	 * <p>
	 * Description:测试查询<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2012-12-26 void
	 */
	@Test
	public void test_getMemberIdNum() {
		String number = "001497";
		String numberId = alterMemberManager.getMemberIdByMemberNum(number);
		Assert.assertEquals("1497", numberId);

		numberId = alterMemberManager.getMemberIdByMemberNumFor360(number);
		Assert.assertEquals("1497", numberId);

		number = "0014972345";
		numberId = alterMemberManager.getMemberIdByMemberNum(number);
		Assert.assertNull(numberId);

		numberId = alterMemberManager.getMemberIdByMemberNumFor360(number);
		Assert.assertNull(numberId);

		number = "13527611392";
		numberId = alterMemberManager.getMemberIdByContactMobile(number);
		Assert.assertEquals("1497", numberId);

		number = "1352761192";
		numberId = alterMemberManager.getMemberIdByContactMobile(number);
		Assert.assertNull(numberId);

		number = "lww13527611392";
		numberId = alterMemberManager.getMemberIdByContactNum(number);
		Assert.assertEquals("1497", numberId);
		number = "lww13527611392235";
		numberId = alterMemberManager.getMemberIdByContactNum(number);
		Assert.assertNull(numberId);

	}

	/**
	 * 
	 * <p>
	 * Description:test根据会员Id查想会员相关银行账号信息<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2012-12-26 void
	 */
	@Test
	public void test_getAccountsByMemberId() {
		List<Account> accountList = alterMemberManager
				.getAccountsByMemberId("1497");
		Assert.assertEquals(1, accountList.size());

		Account account = alterMemberManager.getAccountById("729");
		Assert.assertEquals("729", account.getId());

	}

	/**
	 * 
	 * <p>
	 * Description:test根据工作流条件查询工作流信息<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2012-12-26 void
	 */
	@Test
	public void test_searchWorkflowByCondition() {
		WorkFlowCondition toDoWorkflowCondition = new WorkFlowCondition();
		toDoWorkflowCondition.setWorkflowId(800001220l);

		User user = new User();
		Department dept = new Department();
		dept.setId("180218");
		dept.setDeptName("上海青浦营业部");
		dept.setPhone("02131350606");
		Employee e = new Employee();
		e.setDeptId(dept);
		user.setEmpCode(e);
		Set<String> roleIds = new TreeSet<String>();
		roleIds.add("1004");
		roleIds.add("34576");
		user.setRoleids(roleIds);

		UserContext.setCurrentUser(user);

		int count = alterMemberManager
				.countWorkflowByCondition(toDoWorkflowCondition);
		Assert.assertNotNull(count);
		List<TodoWorkflow> workFlowList = alterMemberManager
				.searchWorkflowByCondition(toDoWorkflowCondition, 0, 20);
		Assert.assertTrue(workFlowList.size() >= 0);

		List<MyToDoWorkFlow> myWorkFlowList = alterMemberManager
				.searchMyWorkflowByCondition(toDoWorkflowCondition, 0, 20);

		Assert.assertTrue(myWorkFlowList.size() >= 0);

	}

	/**
	 * 
	 * <p>
	 * Description:test完成工作流审批<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2012-12-26 void
	 */
	@Test
	public void test_disposeWorkflow() {
		alterMemberManager.disposeWorkflow("400003509", true);
	}

	/**
	 * 
	 * <p>
	 * Description:test 根据工作流ID查询相关审批数据<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2012-12-26 void
	 */
	@Test
	public void test_searchApproveData() {
		List<ApproveData> appList = alterMemberManager
				.searchApproveData("400003509");
		Assert.assertEquals(33, appList.size());

		appList = alterMemberManager.searchApproveDataByLogId("1658");
		Assert.assertEquals(42, appList.size());
	}

	/**
	 * 
	 * <p>
	 * Description:test根据会员查询条件查询会员信息<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2012-12-26 void
	 */
	@Test
	public void test_searchMemberWithAuth() {
		MemberCondition memberCondition = new MemberCondition();
		memberCondition.setLinkManName("陈伟明");

		List<MemberResult> memberList = alterMemberManager
				.searchMemberWithAuth(memberCondition);
		Assert.assertTrue(memberList.size() > 0);

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2012-12-26 void
	 */
	@Test
	public void test_searchMemberListFor360() {
		MemberCondition memberCondition = new MemberCondition();
		memberCondition.setLinkManName("陈伟明");
		memberCondition.setMemberId("198645");
		memberCondition.setDeptId("251218");
		memberCondition.setCustName("深圳市晟泰通信有限公司");
		memberCondition.setLinkManNumber("cwm13590424918");
		memberCondition.setCustGrad("NORMAL");
		memberCondition.setMobile("13590424918");
		memberCondition.setTelePhone("0755-27656696");
		memberCondition.setIdCard("440102197107181415");
		memberCondition.setStart(1);
		List<String> deptIdList = new ArrayList<String>();
		deptIdList.add("251218");
		memberCondition.setDeptIds(deptIdList);

		memberCondition.setTaxregNumber("440306106365513");
		memberCondition.setVersionNumber(0);

		List<MemberResult> memberList = alterMemberManager
				.searchMemberListFor360(memberCondition);
		Assert.assertEquals(1, memberList.size());

	}

	/**
	 * 
	 * <p>
	 * Description:test根据会员查询条件查询会员信息<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2012-12-26 void
	 */
	@Test
	public void test_searchMember() {
		MemberCondition memberCondition = new MemberCondition();
		memberCondition.setLinkManName("陈伟明");

		List<MemberResult> memberList = alterMemberManager
				.searchMember(memberCondition);
		Assert.assertTrue(memberList.size() > 0);

		memberCondition.setMemberId("1497");
		Member member = alterMemberManager.getMemberAllById(memberCondition);
		Assert.assertEquals(memberCondition.getMemberId(), member.getId());

		memberCondition.setMemberId("1497");
		member = alterMemberManager.getInvalidMemberAllById(memberCondition);
		Assert.assertEquals(memberCondition.getMemberId(), member.getId());

		member = alterMemberManager.getMemberById("1497");
		Assert.assertEquals("1497", member.getId());

	}

	/**
	 * 
	 * <p>
	 * Description:test根据联系人编码查询联系人信息<br />
	 * </p>
	 * 
	 * @author 王明明
	 * @version 0.1 2012-12-26 void
	 */
	@Test
	public void test_getContact() {

		Contact contact = alterMemberManager.getContact("4434");
		Assert.assertEquals("4434", contact.getId());
		contact = alterMemberManager.getContactByNum("2");
		Assert.assertNotNull(contact);
		Assert.assertEquals("2", contact.getNumber());
	}

	@Test
	public void test_queryAlterMemberById() {
		Member member;
		try {
			member = alterMemberManager.queryAlterMemberById("400236541");
		} catch (GeneralException be) {
			Assert.assertEquals(
					CustomerExceptionType.MemberIsExamin.getErrCode(),
					be.getErrorCode());
		}
		try {
			member = alterMemberManager.queryAlterMemberById("400205337");
		} catch (GeneralException be) {
			Assert.assertEquals(CustomerExceptionType.DataError.getErrCode(),
					be.getErrorCode());
		}

		member = alterMemberManager.queryAlterMemberById("868");
		Assert.assertNotNull(member);
	}

	@Test
	public void test_updateMemberLastWorkFlowId() {
		alterMemberManager.updateMemberLastWorkFlowId("280901", "400013329");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void clearAlterMember(Map<String, List> addPojoMap)
			throws SQLException {
		List<String> sqls = new ArrayList<String>();
		List<PreferenceAddress> paList = addPojoMap.get("PreferenceAddress");

		for (PreferenceAddress contact : paList) {
			sqls.add("delete t_cust_preferenceaddress  where fid = "
					+ contact.getId());

		}
		List<ShuttleAddress> saList = addPojoMap.get("ShuttleAddress");

		for (ShuttleAddress sa : saList) {
			sqls.add("delete t_cust_shuttleaddress  where fid = " + sa.getId());

		}
		sqls.add("update t_cust_custlinkman t set t.fnumber='xx4000693661' where t.fid=400210063");
		sqls.add("update t_cust_custlinkman t set t.fstatus = 0 where t.fid=400210062");
		sqls.add("update t_cust_preferenceaddress t set t.fstatus = 0 where t.fid=400000485");

		sqls.add("update t_cust_custbasedata t set t.ftaxregNumber ='' where  t.Fid=400002267");
		sqls.add("update t_cust_custlinkman set fname='苏宁易购' where fid = 400194147");
		sqls.add("update t_cust_custlinkman set fmobiletel='' where fid = 400194677");
		sqls.add("update t_cust_custlinkman set foffertel='4000693661' where fid = 400210059");
		sqls.add("update t_cust_custlinkman set fidcard='13916078170' where fid = 400193899");

		List<Contact> contactList = addPojoMap.get("Contact");

		for (Contact contact : contactList) {
			sqls.add("delete t_cust_custlinkman  where fid = "
					+ contact.getId());

		}
		

		SpringTestHelper.executeBatch(sqls);
	}

	private void clearDeleteData(Member member) throws Exception {
		String[] sqls = new String[1];
		sqls[0] = "update  t_cust_custbasedata t set t.fversionnumber=1 where fid="
				+ member.getId();
		SpringTestHelper.executeBatch(sqls);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-10-11
	 * @描述：根据联系人编码查询联系人信息
	 * @参数：number 联系人编码
	 * @返回值：Contact 联系人信息
	 * */
	@Test
	public void testGetContactByNum() {
		Assert.assertNull(alterMemberManager.getContactByNum(""));
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-19
	 * @描述：根据联系人ID查询会员信息,及是否为合同客户(存入是否允许联系人兑换积分字段中返回)
	 * @参数：linkmanId 联系人Id,部门ID
	 * @返回值：Member 包含联系人信息的会员信息
	 * */
	@Test
	public void testGetMemberBylinkmanId() {
		Assert.assertNull(alterMemberManager.getMemberBylinkmanId("", ""));
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @描述：修改会员及相关信息
	 * @参数：approveDataList 需修改的审批数据集合，addPojoMap 需新增的数据实体集合， deleteDataList
	 *                     需删除的审批数据集合，memberId,会员ID
	 * @返回值：String (启动了工作流返回工作流id , 未启动工作流返回 "")
	 * */
	@Test
	public void testAlterMember() {
		// TODO
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-9
	 * @描述：根据条件查询会员信息总数
	 * @参数: 会员查询条件
	 * @返回值：满足条件的总数
	 * */
	@Test
	public void testCountMemberByCondition() {
		MemberCondition condition = new MemberCondition();
		condition.setDeptId("48917");
		int i = alterMemberManager.countMemberByCondition(condition);
		if (0 == i) {
			Assert.assertNull(i);
		} else {
			Assert.assertNotNull(i);
		}
	}

	/**
	 * 
	 * <p>
	 * 得到会员最后一次工作流id<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-8-6
	 * @param memberId
	 * @return String
	 */
	@Test
	public void testGetMemberLastWorkFlowId() {
		Assert.assertNull(alterMemberManager.getMemberLastWorkFlowId(""));
	}

	/**
	 * Description:创建会员的操作日志<br />
	 * 
	 * @version 0.1 2013-3-8
	 */
	@Test
	public void testCreateLog() {
		// 设置当前登录人
		User user = new User();
		Department depart = new Department();
		depart.setId("86301");
		depart.setDeptName("总裁");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);

		// 参数会员ID
		String memberId = "";
		MemberOperationLog memberOperationLog = null;
		try {
			Method method = newAlterMemberManager.getClass().getDeclaredMethod(
					"createLog", String.class);
			memberId = "10086";
			method.setAccessible(true);
			memberOperationLog = (MemberOperationLog) method.invoke(
					alterMemberManager, memberId);
			Assert.assertNotNull(memberOperationLog);
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e2) {
			e2.printStackTrace();
		} catch (IllegalAccessException e3) {
			e3.printStackTrace();
		} catch (InvocationTargetException e4) {
			e4.printStackTrace();
		}
	}

	/**
	 * 锁定审批数据<br />
	 * 
	 * @param updateDataAllList
	 * @param memberId
	 * @param workFlowId
	 * @return Set<ApprovingWorkflowData>
	 */
	@Test
	public void testLockApproveData() {
		// TODO
	}

	/**
	 * @update 2012-7-26 下午9:11:56
	 */
	@Test
	public void testGetContactCardType() {
		// 造数据
		Contact contact = new Contact();
		contact.setId("1");
		contact.setCardTypeCon("ID_CARD");
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(contact);

		Member member = new Member();
		member.setContactList(contacts);
		// 造参数
		ApproveData approveData = new ApproveData();
		approveData.setClassId("1");
		// 结果
		String cardTypeCon = null;
		try {
			// 正常结果
			Method method = newAlterMemberManager.getClass().getDeclaredMethod(
					"getContactCardType", Member.class, ApproveData.class);
			method.setAccessible(true);
			cardTypeCon = (String) method.invoke(newAlterMemberManager, member,
					approveData);
			Assert.assertNotNull(cardTypeCon);

			// classId 不相等
			approveData.setClassId("2");
			method.setAccessible(true);
			cardTypeCon = (String) method.invoke(newAlterMemberManager, member,
					approveData);
			Assert.assertNull(cardTypeCon);

			// ContactList = null
			member.setContactList(null);
			method.setAccessible(true);
			cardTypeCon = (String) method.invoke(newAlterMemberManager, member,
					approveData);
			Assert.assertNull(cardTypeCon);

		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e2) {
			e2.printStackTrace();
		} catch (IllegalAccessException e3) {
			e3.printStackTrace();
		} catch (InvocationTargetException e4) {
			e4.printStackTrace();
		}
	}

	@Test
	public void testGetMainContractId() {
		// 参数对象
		Member member = null;
		// 结果值
		String mainContactId = null;
		// 反射得到的方法名
		Method method = null;

		try {
			method = newAlterMemberManager.getClass().getDeclaredMethod(
					"getMainContractId", Member.class);
			method.setAccessible(true);
			// 传入的参数为空
			mainContactId = (String) (method.invoke(newAlterMemberManager,
					member));
			Assert.assertNull(mainContactId);

			// 客户的联系人为空
			member = new Member();
			member.setContactList(null);
			method.setAccessible(true);
			mainContactId = (String) (method.invoke(newAlterMemberManager,
					member));
			Assert.assertNull(mainContactId);

			// 主联系人为空
			Contact contact = new Contact();
			contact.setId("111");
			List<Contact> contacts = null;
			contacts = new ArrayList<Contact>();
			member.setContactList(contacts);
			method.setAccessible(true);
			mainContactId = (String) (method.invoke(newAlterMemberManager,
					member));
			Assert.assertNull(mainContactId);

			// 主联系人不为空,但不是默认的主联系人
			contact.setIsMainLinkMan(false);
			contacts.clear();
			contacts.add(contact);
			method.setAccessible(true);
			mainContactId = (String) (method.invoke(newAlterMemberManager,
					member));
			Assert.assertNull(mainContactId);

			// 所有条件满足，返回结果不为空
			contact.setIsMainLinkMan(true);
			contacts.clear();
			contacts.add(contact);
			method.setAccessible(true);
			mainContactId = (String) (method.invoke(newAlterMemberManager,
					member));
			Assert.assertNotNull(mainContactId);
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e2) {
			e2.printStackTrace();
		} catch (IllegalAccessException e3) {
			e3.printStackTrace();
		} catch (InvocationTargetException e4) {
			e4.printStackTrace();
		}
	}

	/**
	 * @description 校验一个对象是否在一个set中.
	 */
	@Test
	public void testIsApproveDataExist() {
		// 参数
		ApprovingWorkflowData workflowData = null;
		Set<ApprovingWorkflowData> contactWorkflowDatas = new HashSet<ApprovingWorkflowData>();
		// 结构
		boolean flag;
		Method method;
		try {
			method = newAlterMemberManager.getClass().getDeclaredMethod(
					"isApproveDataExist", Set.class,
					ApprovingWorkflowData.class);
			method.setAccessible(true);
			// 传入的参数为空
			flag = (Boolean) (method.invoke(newAlterMemberManager,
					contactWorkflowDatas, workflowData));
			Assert.assertFalse(flag);

			// 都是new出来的对象,内容为空
			workflowData = new ApprovingWorkflowData();
			method.setAccessible(true);
			flag = (Boolean) (method.invoke(newAlterMemberManager,
					contactWorkflowDatas, workflowData));
			Assert.assertFalse(flag);

			// 正常
			workflowData.setId("1");
			contactWorkflowDatas.add(workflowData);
			method.setAccessible(true);
			flag = (Boolean) (method.invoke(newAlterMemberManager,
					contactWorkflowDatas, workflowData));
			Assert.assertTrue(flag);

		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e2) {
			e2.printStackTrace();
		} catch (IllegalAccessException e3) {
			e3.printStackTrace();
		} catch (InvocationTargetException e4) {
			e4.printStackTrace();
		}
	}

	/**
	 * @描述：根据会员查询条件查询会员信息
	 * @参数：condition 会员查询条件
	 * @返回值：只包含联系人集合及会员基本信息的会员信息
	 */
	@Test
	public void testSearchMember() {
		MemberCondition condition = null;
		try {
			alterMemberManager.searchMember(condition);
		} catch (Exception e) {
		}
		condition = new MemberCondition();
		condition.setCustName("江南style00000000");
		Assert.assertNotNull(alterMemberManager.searchMember(condition));
	}

	@Test
	public void testCreateUpdateLog() {
		// 参数
		Boolean isSuccess = false;
		List<ApproveData> adList = null;
		MemberOperationLog operationLog = null;
		Method method = null;
		try {
			Method[] methods = newAlterMemberManager.getClass()
					.getDeclaredMethods();
			for (Method method2 : methods) {
				if (method2.getName().equals("createUpdateLog")) {
					method = method2;
				}
			}
			// 参数不为空,false
			adList = new ArrayList<ApproveData>();
			ApproveData approveData = new ApproveData();
			approveData.setMemberOperationLogId("11");
			adList.add(approveData);

			method.setAccessible(true);
			operationLog = (MemberOperationLog) (method.invoke(
					newAlterMemberManager, isSuccess, adList));
			Assert.assertNull(operationLog);

			// true
			isSuccess = true;
			method.setAccessible(true);
			operationLog = (MemberOperationLog) (method.invoke(
					newAlterMemberManager, isSuccess, adList));
			Assert.assertNotNull(operationLog);

		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e2) {
			e2.printStackTrace();
		} catch (IllegalAccessException e3) {
			e3.printStackTrace();
		} catch (InvocationTargetException e4) {
			e4.printStackTrace();
		} catch (Exception e) {
		}
	}

	/**
	 * Description:360查询<br />
	 */
	@Test
	public void testSearchMemberListFor360() {
		// 参数
		MemberCondition memberCondition = null;
		try {
			alterMemberManager.searchMemberListFor360(memberCondition);
		} catch (CustomerException e) {
			e.getErrorCode().equals(
					CustomerExceptionType.ConditionCanNotNull.getErrCode());
		}

		memberCondition = new MemberCondition();
		memberCondition.setLinkManName("江南style00000000");
		Assert.assertNotNull(alterMemberManager
				.searchMemberListFor360(memberCondition));
	}

	@Test
	public void testGetAccountsByMemberId() {
		String memberId = null;
		Assert.assertNull(alterMemberManager.getAccountsByMemberId(memberId));

		memberId = "132465465464654654646";
		Assert.assertNotNull(alterMemberManager.getAccountsByMemberId(memberId));
	}

	@Test
	public void testGetAccountById() {
		String id = null;
		Assert.assertNull(alterMemberManager.getAccountById(id));

		id = "132465465464654654646";
		Assert.assertNull(alterMemberManager.getAccountById(id));
	}

	@Test
	public void testGetMemberIdByMemberNum() {
		String memberNum = "11";
		Assert.assertNull(alterMemberManager.getMemberIdByMemberNum(memberNum));

		memberNum = "000655";
		Assert.assertNotNull(alterMemberManager
				.getMemberIdByMemberNum(memberNum));
	}

	@Test
	public void testGetMemberIdByMemberNumFor360() {
		String memberNum = "11";
		Assert.assertNull(alterMemberManager
				.getMemberIdByMemberNumFor360(memberNum));

		memberNum = "000655";
		Assert.assertNotNull(alterMemberManager
				.getMemberIdByMemberNumFor360(memberNum));
	}

	@Test
	public void testGetMemberIdByContactNum() {
		String contactNum = "11";
		Assert.assertNull(alterMemberManager
				.getMemberIdByContactNum(contactNum));

		contactNum = "hs076982165300";
		Assert.assertNotNull(alterMemberManager
				.getMemberIdByContactNum(contactNum));
	}

	@Test
	public void testAddContactType() {
		String contactId = null;
		int addContactType = 1;

		contactId = "";
		alterMemberManager.addContactType(contactId, addContactType);

		contactId = "2";
		alterMemberManager.addContactType(contactId, addContactType);
	}

	@Test
	public void testGetMemberIdByContactMobile() {
		String mobile = "1321321321313213";
		Assert.assertNull(alterMemberManager.getMemberIdByContactMobile(mobile));

		mobile = "13509201297";
		Assert.assertNotNull(alterMemberManager
				.getMemberIdByContactMobile(mobile));
	}

	@Test
	public void testQueryAlterMemberById() {
		String memberId = "";
		Assert.assertNull(alterMemberManager.queryAlterMemberById(memberId));
		memberId = "5556";
		try {
			alterMemberManager.queryAlterMemberById(memberId);
//			Assert.fail("为空，应该跑出异常才对");
		} catch (GeneralException e) {
			Assert.assertEquals(e.getErrorCode(),CustomerExceptionType.DataError.getErrCode());
		}
		memberId = "400236541";
		alterMemberManager.queryAlterMemberById(memberId);
	}

	@Test
	public void testQueryBasicMemberByCustNum() {
		MemberResult bMemberResult=alterMemberManager.queryBasicMemberByCustNum("008808");
		System.out.println(bMemberResult.getCustGrade());
	}
	/**
	 * 
	 * <p>
	 * Description:修改客户地址<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-10-25 void
	 */
	@Test
	public void testAlterCustAddress() {
		try {
			alterMemberManager.alterCustAddress(null);
		} catch (MemberException ge) {
			assertEquals(MemberExceptionType.Cust_Data_Error.getErrCode(),
					ge.getErrorCode());
		}
		CustAddress ca = new CustAddress();
		try {
			alterMemberManager.alterCustAddress(ca);
		} catch (MemberException ge) {
			assertEquals(MemberExceptionType.Cust_Data_Error.getErrCode(),
					ge.getErrorCode());
		}
		ca.setCustId("11");
		try {
			alterMemberManager.alterCustAddress(ca);
		} catch (MemberException ge) {
			assertEquals(MemberExceptionType.Cust_Data_Error.getErrCode(),
					ge.getErrorCode());
		}
		ca.setAddress("11");
		try {
			alterMemberManager.alterCustAddress(ca);
		} catch (MemberException ge) {
			assertEquals(MemberExceptionType.Cust_Data_Error.getErrCode(),
					ge.getErrorCode());
		}
		ca.setCustType(Constant.CRM_POTENTIAL);
		try {
			alterMemberManager.alterCustAddress(ca);
		} catch (MemberException ge) {
			assertEquals(MemberExceptionType.Cust_Data_Error.getErrCode(),
					ge.getErrorCode());
		}
		ca.setCustId("11");
		try {
			alterMemberManager.alterCustAddress(ca);
		} catch (MemberException ge) {
			assertEquals(MemberExceptionType.Cust_Data_Error.getErrCode(),
					ge.getErrorCode());
		}
		ca.setCityId("111");
		try {
			alterMemberManager.alterCustAddress(ca);
		} catch (MemberException ge) {
			assertEquals(MemberExceptionType.Cust_Data_Error.getErrCode(),
					ge.getErrorCode());
		}
		try {
			
		
		ca.setCustType(Constant.CRM_SCATTER);
		alterMemberManager.alterCustAddress(ca);
		ca.setCustType(Constant.CRM_POTENTIAL);
		ca.setCustId("12");
		alterMemberManager.alterCustAddress(ca);
		ca.setCustType("1111");
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			alterMemberManager.alterCustAddress(ca);
		} catch (MemberException ge) {
			assertEquals(MemberExceptionType.Cust_Data_Error.getErrCode(),
					ge.getErrorCode());
		}
		ca.setAreaId("1");
		try {
			alterMemberManager.alterCustAddress(ca);
		} catch (MemberException ge) {
			assertEquals(MemberExceptionType.Cust_Data_Error.getErrCode(),
					ge.getErrorCode());
		}
		ca.setProvinceId("1");
		try {
			alterMemberManager.alterCustAddress(ca);
		} catch (MemberException ge) {
			assertEquals(MemberExceptionType.Cust_Data_Error.getErrCode(),
					ge.getErrorCode());
		}
		ca.setContactId("11");
		try {
			
			alterMemberManager.alterCustAddress(ca);
		} catch (Exception e) {
		}
				
	

	}

	/**
	 * 
	 * <p>
	 * Description:修改固定客户标注test<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-10-25 void
	 */
	@Test
	public void testUpdateCustCoordinates() {
		/**
		 * 坐标修改对象为空时
		 * 
		 */
		CustomerLocation cl = null;
		try {
			alterMemberManager.updateCustCoordinates(cl);

		} catch (CustomerException ge) {
			assertEquals(
					CustomerExceptionType.CustomerLocation_Is_Null.getErrCode(),
					ge.getErrorCode());
		}

		cl = new CustomerLocation();
		try {
			alterMemberManager.updateCustCoordinates(cl);

		} catch (CustomerException ge) {
			assertEquals(
					CustomerExceptionType.CustomerLocation_Is_Null.getErrCode(),
					ge.getErrorCode());
		}
		cl.setCustomerId("11");
		cl = new CustomerLocation();
		try {
			alterMemberManager.updateCustCoordinates(cl);

		} catch (CustomerException ge) {
			assertEquals(
					CustomerExceptionType.CustomerLocation_Is_Null.getErrCode(),
					ge.getErrorCode());
		}
		cl.setCustomerType("111");
		try {
			alterMemberManager.updateCustCoordinates(cl);

		} catch (CustomerException ge) {
			assertEquals(
					CustomerExceptionType.CustomerLocation_Is_Null.getErrCode(),
					ge.getErrorCode());
		}
		cl.setCustomerType(Constant.MEMBERTYPE_IN_CUSTLABEL);
		try {
			alterMemberManager.updateCustCoordinates(cl);

		} catch (CustomerException ge) {
			assertEquals(
					CustomerExceptionType.CustomerLocation_Is_Null.getErrCode(),
					ge.getErrorCode());
		}
		cl.setLat("25161");
		try {
			alterMemberManager.updateCustCoordinates(cl);

		} catch (CustomerException ge) {
			assertEquals(
					CustomerExceptionType.CustomerLocation_Is_Null.getErrCode(),
					ge.getErrorCode());
		}
		cl.setLng("256.232");
		try {
			alterMemberManager.updateCustCoordinates(cl);

		} catch (CustomerException ge) {
			assertEquals(
					CustomerExceptionType.CustomerLocation_Is_Null.getErrCode(),
					ge.getErrorCode());
		}
		cl.setLinkmanId(null);
		try {
			alterMemberManager.updateCustCoordinates(cl);

		} catch (CustomerException ge) {
			assertEquals(
					CustomerExceptionType.CustomerLocation_Is_Null.getErrCode(),
					ge.getErrorCode());
		}
		cl.setCustomerId("11");
		try {
			alterMemberManager.updateCustCoordinates(cl);

		} catch (CustomerException ge) {
			assertEquals(
					CustomerExceptionType.CustomerLocation_Is_Null.getErrCode(),
					ge.getErrorCode());
		}
		cl.setLinkmanId("11");

		/**
		 * 固定客户正向测试
		 */
		alterMemberManager.updateCustCoordinates(cl);
		cl.setCustomerId("12");
		cl.setCustomerType(Constant.CRM_POTENTIAL);
		alterMemberManager.updateCustCoordinates(cl);
		cl.setCustomerId("11");
		cl.setCustomerType(Constant.CRM_SCATTER);
		alterMemberManager.updateCustCoordinates(cl);

	}	
}
