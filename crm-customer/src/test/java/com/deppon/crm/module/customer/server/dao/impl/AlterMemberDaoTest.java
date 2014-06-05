package com.deppon.crm.module.customer.server.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Locale;
import java.util.Set;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.common.shared.domain.TodoWorkflow;
import com.deppon.crm.module.customer.server.testutils.DataTestUtil;
import com.deppon.crm.module.customer.server.testutils.TestUtils;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberOperationLog;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;
import com.deppon.crm.module.customer.shared.domain.ShuttleAddress;
import com.deppon.crm.module.customer.shared.domain.WorkFlowCondition;

/**
 * @作者：罗典
 * @时间：2012-3-27
 * @描述：修改会员模块测试
 * */
public class AlterMemberDaoTest extends BeanUtil {
	@Before
	public void setUP() throws Exception {
		//初始化修改会员模块数据
		DataTestUtil.initAlterMemberData();
	}

	@After
	public void tearDown() throws Exception {
		//清理修改会员模块测试数据
		DataTestUtil.clearAlterMemberData();
	}

	/**
	 * @作者：罗典
	 * @时间：2012-10-11
	 * @功能描述：根据联系人编码查询联系人基本信息
	 */
	@Test
	public void testgetContactByNum() {
		try {
			Assert.assertNull(alterMemberDao.getContactByNum("crm13712154238"));
		} catch (Exception e) {
			org.junit.Assert.assertTrue(true);
		}
	}
	
	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID查询会员基本信息
	 */
	@Test
	public void testGetMemberById() {
		Assert.assertNotNull(alterMemberDao.getMember("525"));
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID查询会员所有信息
	 */
	@Test
	public void testGetMemberAllById() {
		Assert.assertNotNull(alterMemberDao.getMemberAllById("525"));
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID查询账户信息
	 */
	@Test
	public void testGetAccountById() {
//		Assert.assertNotNull(
				alterMemberDao.getAccount("11");
//				);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID查询联系人信息
	 */
	@Test
	public void testGetContactById() {
//		Assert.assertNotNull(
				alterMemberDao.getContact("11");
//				);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID查询联系人偏好地址信息
	 */
	@Test
	public void testGetPreferenceAddressById() {
//		Assert.assertNotNull(
				alterMemberDao.getPreferenceAddress("11");
//				);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据ID查询接送货地址信息
	 */
	@Test
	public void testGetShuttleAddressById() {
//		Assert.assertNotNull(
				alterMemberDao.getShuttleAddress("11");
//				);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据条件查询会员信息
	 */
	@Test
	public void testSearchMember() {
		MemberCondition contion = new MemberCondition();
		contion.setCustName("");
		contion.setCustGrad("");
		contion.setDeptId("");
		contion.setIdCard("1");
		contion.setLinkManName("1");
		contion.setLinkManNumber("1");
		contion.setMobile("1");
		contion.setTelePhone("1");
		contion.setLimit(-1);
		contion.setTaxregNumber("111");
		alterMemberDao.searchMember(contion);
	}
	
	public void testSearchMemberByCondition(){
		MemberCondition contion = new MemberCondition();
		contion.setCustName("");
//		contion.setCustGrad("");
		contion.setCustNumber("223230");
//		contion.setDeptId("");
//		contion.setIdCard("1");
//		contion.setLinkManName("1");
//		contion.setLinkManNumber("1");
//		contion.setMobile("1");
//		contion.setTelePhone("1");
	//	contion.setLimit(-1);
		alterMemberDao.searchMemberByCondition(contion);

	}
	
	/**
	 * @作者：罗典
	 * @时间：2012-4-9
	 * @功能描述：根据条件查询会员信息总数
	 */
	@Test
	public void testCountMemberByCondition() {
		MemberCondition contion = new MemberCondition();
		contion.setCustName("");
		contion.setCustGrad("");
		contion.setCustNumber("1");
		contion.setDeptId("");
		contion.setIdCard("1");
		contion.setLinkManName("1");
		contion.setLinkManNumber("1");
		contion.setMobile("1");
		contion.setTelePhone("1");
		contion.setLimit(-1);
		alterMemberDao.countMemberByCondition(contion);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：保存审批数据信息
	 */
	@Test
	public void testSaveApproveData() {
		ApproveData approveData = new ApproveData();
		approveData.setClassId("11");
		approveData.setClassName("Member");
		approveData.setFieldName("custName");
		approveData.setNewValue("罗典");
		approveData.setOldValue("李学兴");
		approveData.setWorkFlowId("1");
		approveData.setHandleType(1);
		ApproveData approveData1 = new ApproveData();
		approveData1.setClassId("11");
		approveData1.setClassName("Member");
		approveData1.setFieldName("custName");
		approveData1.setNewValue("罗典");
		approveData1.setOldValue("李学兴");
		approveData1.setWorkFlowId("1");
		approveData1.setMemberOperationLogId("1");
		List<ApproveData> approveDataList = new ArrayList<ApproveData>();
		approveDataList.add(approveData);
		approveDataList.add(approveData1);
		alterMemberDao.saveApproveData(approveDataList);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据修改字段获取启动工作流类型
	 */
	@Test
	public void testGetApproveType() {
		// 需启动工作流的类型
		ApproveData approveData = new ApproveData();
		approveData.setClassName("Member");
		approveData.setFieldName("custName");
		Assert.assertNotNull(alterMemberDao.getApproveType(
				approveData.getClassName(), approveData.getFieldName()));
		// 不需启动工作流的类型
		approveData = new ApproveData();
		approveData.setClassName("Member");
		approveData.setFieldName("channel");
		Assert.assertNull(alterMemberDao.getApproveType(
				approveData.getClassName(), approveData.getFieldName()));
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：根据工作流ID获取启动审批数据
	 */
	@Test
	public void testSearchApproveData() {
		Assert.assertNotNull(alterMemberDao.searchApproveData("1"));
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：修改会员信息
	 */
	// 会员未改字段 ferpcustid,fcompaddr,fcreditrate,faddretype
	@Test
	public void testUpdateMember() {
		Member member = new Member();
		member.setProvinceId("11");
		member.setCityId("11");
		member.setParentCompanyId("11");
		member.setCustNumber("11");
		member.setDegree("11");
		member.setCustName("11");
		member.setTradeId("11");
		member.setCustType("11");
		member.setTaxregNumber("11");
		member.setCompanyProperty("11");
		member.setCustNature("11");
		member.setProvince("11");
		member.setCity("11");
		member.setRegistAddress("11");
		member.setIsSpecial(false);
		member.setIsRedeempoints(false);
		member.setIsImportCustor(false);
		member.setCustPotentialType("11");
//		member.setIsAcceptMarket("1");
		member.setBrandWorth("11");
		member.setChannelSource("11");
		member.setChannel("11");
		member.setPreferenceService("11");
		member.setCompanyScop("11");
		member.setLastYearProfit(Double.valueOf(1));
		member.setLastYearIncome(Double.valueOf(1));
		member.setIsFocusPay(false);
		member.setFocusDeptId("11");
		member.setBillTitle("11");
		member.setIsParentCompany(false);
		// member.setParentNumber("11");
		member.setRegisterFund(Double.valueOf(1));
		member.setProcRedit(Double.valueOf(1));
		member.setRecommendCust("11");
		member.setRemark("11");
		member.setSimpleName("11");
		member.setBussType("11");
		member.setIsTranGoods(false);
		member.setAreaId("19216812");
		member.setDeptId("19216812");
		member.setCustId("11");
		member.setContactId("11");
		member.setUpgradeSource("11");
		member.setCustStatus("11");
		member.setBecomeMemTime(new Date());
		member.setResponsibillity("11");
		member.setVersionNumber(1);
		member.setNextLevel("11");
		member.setLastChangTime(new Date());
		member.setJshAddress("11");
		member.setCreateUser("11");
		member.setModifyUser("11");
		member.setId("11");
		member.setCreateDate(new Date());
		member.setModifyDate(new Date());
//		Assert.assertTrue(
				alterMemberDao.updateMember(member);
//				);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：修改联系人信息
	 */
	// 未修改字段：flovegift,fstatus,flinkmanaddress
	@Test
	public void testUpdateContact() {
		Contact contact = new Contact();
		contact.setNumber("22");
		contact.setIdCard("22");
		contact.setLinkmanType("22");
		contact.setIsMainLinkMan(false);
		contact.setSex("22");
		contact.setTelPhone("22");
		contact.setMobilePhone("22");
		contact.setDuty("22");
		contact.setDutyDept("22");
		contact.setBornDate(new Date());
		contact.setGainave("11");
		contact.setDecisionRight("11");
		contact.setNativePlace("11");
		contact.setPersonLove("11");
		contact.setFolk("11");
		contact.setEmail("11");
		contact.setQqNumber("11");
		contact.setMsn("11");
		contact.setWw("11");
		contact.setAcceptDeptid("151217");
		contact.setDefaultId("11");
		contact.setVersionId("11");
		contact.setCustId("11");
		contact.setName("11");
		contact.setStatus("11");
		contact.setCreateUser("1");
		contact.setModifyUser("1");
		contact.setId("11");
		contact.setCreateDate(new Date());
		contact.setModifyDate(new Date());
//		Assert.assertTrue(
				alterMemberDao.updateContact(contact);
//				);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：修改账号信息
	 */
	@Test
	public void testUpdateAccount() {
		Account account = new Account();
		account.setBank("11");
		account.setContactType("11");
		account.setBankId("SkRflAEcEADgC+qswKgCyQxdQ4c=");
		account.setSubBanknameId("KYsXR9bOQHG1vXKQxv8AtigOU9M=");
		account.setFinanceLinkmanId("11");
		account.setLinkManPhone("11");
		account.setSubBankname("11");
		account.setIsdefaultaccount(true);
		account.setBankAccount("11");
		account.setCountName("11");
		account.setRelation("11");
		account.setBankProvinceId("x4iubwEuEADgA8HZwKgCyzcYh+k=");
		account.setAccountNature("11");
		account.setAccountUse("11");
		account.setBankCityId("wwNiEgEuEADgA4XrwKjeAU1UgE4=");
		account.setFinanceLinkman("11");
		account.setLinkManMobile("11");
		account.setBankArea("11");
		account.setLastUpdateDeptId("151217");
		account.setCreateDeptId("151217");
		account.setBelongcustom("11");
		account.setStatus("11");
		account.setCreateUser("1");
		account.setModifyUser("1");
		account.setId("11");
		account.setCreateDate(new Date());
		account.setModifyDate(new Date());
//		Assert.assertTrue(
				alterMemberDao.updateAccount(account);
//				);

	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：修改偏好地址信息
	 */
	// 未修改字段 fcontactorId
	@Test
	public void testUpdatePreferenceAddress() {
		PreferenceAddress preferenceAddress = new PreferenceAddress();
		preferenceAddress.setLinkManId("11");
		preferenceAddress.setShuttleAddressId("11");
		preferenceAddress.setAddressType("11");
		preferenceAddress.setEndTime(new Date());
		preferenceAddress.setBillRequest("11");
		preferenceAddress.setHasStopCost(true);
		preferenceAddress.setPayType("11");
		preferenceAddress.setIsSendToFloor(true);
		preferenceAddress.setOtherNeed("11");
		preferenceAddress.setStartTime(new Date());
		preferenceAddress.setAddress("11");
		preferenceAddress.setCreateUser("11");
		preferenceAddress.setModifyUser("11");
		preferenceAddress.setId("11");
		preferenceAddress.setCreateDate(new Date());
		preferenceAddress.setModifyDate(new Date());
//		Assert.assertTrue(
				alterMemberDao
				.updatePreferenceAddress(preferenceAddress);
//				);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：修改接送货地址信息
	 */
	@Test
	public void testUpdateShuttleAddress() {
		ShuttleAddress shuttleAddress = new ShuttleAddress();
		shuttleAddress.setMemberId("11");
		shuttleAddress.setAddressType("11");
		shuttleAddress.setProvince("11");
		shuttleAddress.setCity("11");
		shuttleAddress.setArea("11");
		shuttleAddress.setPostcode("11");
		shuttleAddress.setAddress("11");
		shuttleAddress.setCreateUser("11");
		shuttleAddress.setModifyUser("11");
		shuttleAddress.setId("11");
		shuttleAddress.setCreateDate(new Date());
		shuttleAddress.setModifyDate(new Date());
//		Assert.assertTrue(
				alterMemberDao.updateShuttleAddress(shuttleAddress);
//				);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-24
	 * @功能描述：修改会员状态
	 */
	@Test
	public void testAlterMemberStatus() {
//		Assert.assertTrue(
				alterMemberDao.alterMemberStatus("11", "0");;
//				);
		
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-9
	 * @功能描述：根据工作流ID查询工作流信息
	 */
	public void testGetWorkflowById() {
		WorkFlowCondition td = null;
		List<TodoWorkflow> todoList =null;
		
		td = new WorkFlowCondition();
		td.setCreateUser("319093");
		todoList = alterMemberDao.searchWorkflowByCondition(td, 0, 100);
//		Assert.assertTrue(todoList.size() > 0);
		
		td = new WorkFlowCondition();
		td.setApplicationStatus("DISAGREE");
		todoList = alterMemberDao.searchWorkflowByCondition(td, 0, 100);
		//	Assert.assertTrue(todoList.size() > 0);
		
		td = new WorkFlowCondition();
		td.setApplicationStatus("AGREED");
		todoList = alterMemberDao.searchWorkflowByCondition(td, 0, 100);
		//	Assert.assertTrue(todoList.size() > 0);
		
		td = new WorkFlowCondition();
		td.setWorkflowName("memberModify");
		todoList = alterMemberDao.searchWorkflowByCondition(td, 0, 100);
		//	Assert.assertTrue(todoList.size() > 0);
		
		td = new WorkFlowCondition();
		td.setDeptId(180218);
		todoList = alterMemberDao.searchWorkflowByCondition(td, 0, 100);
		//	Assert.assertTrue(todoList.size() > 0);
		
		Set<String> roles = new HashSet<String>();
		td = new WorkFlowCondition();
		td.setOwnerDeptId("180773");
		roles.add("1003");
		td.setOwnerRoleIds(roles);
		todoList = alterMemberDao.searchWorkflowByCondition(td, 0, 100);
		//	Assert.assertTrue(todoList.size() > 0);
		
		roles = new HashSet<String>();
		td = new WorkFlowCondition();
		td.setCreateUser("319093");
		td.setApplicationStatus("DISAGREE");
		td.setWorkflowName("createSpecialMember");
		td.setDeptId(180218);
		td.setOwnerDeptId("180773");
		roles.add("1003");
		td.setOwnerRoleIds(roles);		
		todoList = alterMemberDao.searchWorkflowByCondition(td, 0, 100);
		//Assert.assertTrue(todoList.size() > 0);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-9
	 * @功能描述：根据工作流ID查询工作流信息
	 */
	@Test
	public void testCountWorkflowByCondition() {
		WorkFlowCondition td = new WorkFlowCondition();
		td.setApplicationStatus("1");
		td.setCreateUser("1");
		td.setDeptId(Integer.parseInt("1"));
		td.setEndDate(new Date());
		td.setStartDate(new Date());
		td.setWorkflowId(Long.parseLong("1"));
		td.setWorkflowName("1");
		alterMemberDao.countWorkflowByCondition(td);
	}
	
	@Test
	public void testGetContactDetailInfoById(){
		alterMemberDao.getContactDetailInfoById("12");
	}
	
	@Test
	public void testGetAccountsByMemberId(){
		String memberId = "11";
		List<Account> list = new ArrayList<Account>();
		list = alterMemberDao.getAccountsByMemberId(memberId);
//		Assert.assertEquals(1, list.size());
	}
	@Test
	public void testGetPreferAddByContactId(){
		String memberId = "11";
		List<PreferenceAddress> list = alterMemberDao.getPreferAddByContactId(memberId,"RECEIVE_GOODS");
		//	Assert.assertTrue( list.size()>0);
	}
	
	@Test
	public void testInsertMemberOperationLog(){
		MemberOperationLog log = TestUtils.createBean(MemberOperationLog.class);
		log.setId(null);
		alterMemberDao.insertMemberOperationLog(log);
		//Assert.assertNotNull(log.getId());
	}
	
	@Test
	public void testUpdateMemberOperationLog(){
		MemberOperationLog log = TestUtils.createBean(MemberOperationLog.class);
		log.setId(null);
		alterMemberDao.insertMemberOperationLog(log);
		//	Assert.assertNotNull(log.getId());
		
		log.setEffect(false);
		alterMemberDao.updateMemberOperationLog(log);
		
	}
	
	@Test
	public void testSearchApproveDataByLogId(){
		alterMemberDao.searchApproveDataByLogId("18");
	}
	
	/* 
	 * @功能描述：根据条件查询会员信息 存在客户编码则可以查询出作废客户信息
	 * @参数 id:实体ID
	 * @返回值 会员集合
	 * */
	@Test
	public void testSearchMemberListFor360() {
		MemberCondition memberCondition = TestUtils.createBean(MemberCondition.class);
		memberCondition.setLinkManName("李");
		memberCondition.setCustName("李");
		Assert.assertNotNull(alterMemberDao.searchMemberListFor360(memberCondition));
	}
	
	/**
	 * @description 查询数据权限会员信息.  
	 * @return List<MemberResult>
	 */
	@Test
	public void testSearchMemberWithAuth() {
		MemberCondition memberCondition = TestUtils.createBean(MemberCondition.class);
		memberCondition.setLinkManName("张");
		memberCondition.setCustName("张");
		Assert.assertNotNull(alterMemberDao.searchMemberWithAuth(memberCondition));
	}
	
	/**
	 * @描述：根据会员ID查询接送货地址信息
	 * @参数：会员ID
	 * @返回值：接送货地址信息集合
	 * */
	@Test
	public void testSearchShuttleAddressByMemberId() {
		String memberId = "97369";
		Assert.assertNotNull(alterMemberDao.searchShuttleAddressByMemberId(memberId));
	}
	/**
	 * @描述：根据会员ID查询联系人基本信息
	 * @参数：会员ID
	 * @返回值：联系人信息集合
	 * */
	@Test
	public void testSearchContactByMemberId() {
		String memberId = "273";
		Assert.assertNotNull(alterMemberDao.searchContactByMemberId(memberId));
	}
	
	/**
	 * @功能描述：根据ID修改接送货地址状态
	 * @参数 id ID,status 接送货地址状态
	 * @返回值 boolean 是否修改成功
	 * */
	@Test
	public void testAlterShuttleAddressStatusFirst() {
		String id = "400033888";
		String status = "2";
		alterMemberDao.alterShuttleAddressStatus(id, status);
	}
	/**
	 * @功能描述：根据ID修改接送货地址状态
	 * @参数 id ID,status 接送货地址状态
	 * @返回值 boolean 是否修改成功
	 * */
	@Test
	public void testAlterShuttleAddressStatusSecond() {
		String id = "400033888";
		String status = "2";
		int versionNumber = -1;
		alterMemberDao.alterShuttleAddressStatus(id, status,versionNumber);
		
//		id = "400033889";
//		status = "2";
//		versionNumber = 1;
//		alterMemberDao.alterAccountStatus(id, status, versionNumber);
		
	}
	/**
	 * @功能描述：根据ID修改联系人偏好地址状态
	 * @参数 id ID,status 联系人偏好地址状态
	 * @返回值 boolean 是否修改成功
	 * */
	@Test
	public void testAlterPreferenceAddressStatusFirst() {
		String id = "400000204";
		String status = "2";
		alterMemberDao.alterPreferenceAddressStatus(id, status);
	}
	/**
	 * @功能描述：根据ID修改联系人偏好地址状态
	 * @参数 id ID,status 联系人偏好地址状态
	 * @返回值 boolean 是否修改成功
	 * */
	@Test
	public void testAlterPreferenceAddressStatusSecond() {
		String id = "400000323";
		String status = "2";
		int versionNumber = -1;
		alterMemberDao.alterPreferenceAddressStatus(id, status,versionNumber);
		
//		id = "400000323";
//		status = "2";
//		versionNumber = 1;
//		alterMemberDao.alterPreferenceAddressStatus(id, status, versionNumber);
	}
	
	/**
	 * @功能描述：根据ID修改账号信息状态
	 * @参数 id ID,status 账号信息状态
	 * @返回值 boolean 是否修改成功
	 * */
	@Test
	public void testAlterAccountStatusFirst() {
		String id = "572";
		String status = "2";
		alterMemberDao.alterAccountStatus(id, status);
	}
	/**
	 * @功能描述：根据ID修改账号信息状态
	 * @参数 id ID,status 账号信息状态
	 * @返回值 boolean 是否修改成功
	 * */
	@Test
	public void testAlterAccountStatusSecond() {
		String id = "573";
		String status = "2";
		int versionNumber = -1;
		alterMemberDao.alterAccountStatus(id, status,versionNumber);
		
//		id = "573";
//		status = "2";
//		versionNumber = 1;
//		alterMemberDao.alterAccountStatus(id, status, versionNumber);

	}
	/**
	 * @功能描述：根据ID修改联系人状态
	 * @参数 id ID,status 联系人状态
	 * @返回值 boolean 是否修改成功
	 * */
	@Test
	public void testAlterContactStatusFirst() {
		String id = "953";
		String status = "2";
		alterMemberDao.alterContactStatus(id, status);
	}
	/**
	 * @功能描述：根据ID修改联系人状态
	 * @参数 id ID,status 联系人状态
	 * @返回值 boolean 是否修改成功
	 * */
	@Test
	public void testAlterContactStatusSecond() {
		String id = "953";
		String status = "2";
		alterMemberDao.alterContactStatus(id, status);
		
//		id = "953";
//		status = "2";
//		versionNumber = 1;
//		alterMemberDao.alterContactStatus(id, status, versionNumber);

		
	}
	/**
	 * @功能描述：根据会员ID查询作废会员相关所有信息
	 * @参数：id 会员ID
	 * @返回值：Member 会员完整信息
	 * */
	@Test
	public void testGetInvalidMemberAllById() {
		String id = "208";
		alterMemberDao.getInvalidMemberAllById(id);
	}
	/**
	 * @功能描述：根据会员ID查询作废会员相关所有信息
	 * @参数：id 会员ID
	 * @返回值：Member 会员完整信息
	 * */
	@Test
	public void testQueryMember4AllById() {
		String id = "209";
		Assert.assertNotNull(alterMemberDao.queryMember4AllById(id));
	}
	/**
	 * @描述：根据工作流条件查询工作流信息
	 * @参数：toDoWorkflowCondition 工作流信息条件
	 * @返回值：工作流信息
	 * */
	@Test
	public void testSearchWorkflowByCondition(){
		WorkFlowCondition todoWorkflow = TestUtils.createBean(WorkFlowCondition.class);
		todoWorkflow.setWorkflowName("changeMemberDept");
		Assert.assertNotNull(alterMemberDao.searchWorkflowByCondition(todoWorkflow, 0, Integer.MAX_VALUE));
	}
	
	/**
	 * @描述：根据联系人编码查询会员信息
	 * @参数：linkmanCode 联系人编码
	 * @返回值：Member 包含联系人信息的会员信息
	 * */
	@Test
	public void testGetMemberBylinkmanId(){
		String linkmanId = "953";
		String deptId = null;
		Assert.assertNull(alterMemberDao.getMemberBylinkmanId(linkmanId, deptId));
		
		linkmanId = "953";
		deptId = "10289";
		Assert.assertNull(alterMemberDao.getMemberBylinkmanId(linkmanId, deptId));
	}
	/**
	 * 根据联系人删除偏好地址<br />
	 * */
	@Test
	public void testLosePreferenceAddressByContactId(){
		String linkManId = "400193729";
		alterMemberDao.losePreferenceAddressByContactId(linkManId);
	}
	
	/**
	 * @description 查询我要处理的工作流.  
	 * @param 
	 * @return List<MyToDoWorkFlow>
	 */
	@Test
	public void testSearchMyWorkflowByCondition() {
		WorkFlowCondition workflowCondition = TestUtils.createBean(WorkFlowCondition.class);
		workflowCondition.setWorkflowName("changeMemberDept");
		Assert.assertNotNull(alterMemberDao.searchMyWorkflowByCondition(workflowCondition, 0, Integer.MAX_VALUE));
	}
	/**
	 * @描述：T_CUST_TO_WORKFLOW
	 * @参数：
	 * @返回值：void
	 */
	@Test
	public void testInsertCustWorkflow() {
		String custId = "123";
		String workflowId = "234";
		alterMemberDao.insertCustWorkflow(custId, workflowId);
	}
	
	/**
	 * @描述：
	 * @参数：linkmanCode 联系人编码，部门ID
	 * @返回值：boolean 为true则表示修改成功
	 */
	@Test
	public void testUpdateCustWorkflow() {
		String custId = "123";
		String workflowId = "345";
		alterMemberDao.updateCustWorkflow(custId, workflowId);
	}
	/**
	 * @功能描述：工作流ID查询审批数据
	 * @参数：workFlowId:工作流ID
	 * @返回值：List<ApproveData> 此工作流对应的审批数据
	 * */
	@Test
	public void testGetCustWorkflow() {
		String custId = "9999999999";
		alterMemberDao.getCustWorkflow(custId);
		
		custId = "123";
		alterMemberDao.getCustWorkflow(custId);
	}
	/**
	 * @功能描述：根据ID修改会员基本信息
	 * @参数：member 会员基本信息
	 * */
	@Test
	public void testUpdateMemberAllInfo() {
		Member member = TestUtils.createBean(Member.class);
		member.setId("273");
		member.setCustName("abcdefg");
		alterMemberDao.updateMemberAllInfo(member);
		
		member.setCustName("翡致伟（广州）贸易有限公司");
		alterMemberDao.updateMemberAllInfo(member);
	}
	/**
	 * @功能描述：根据ID修改联系人信息
	 * @参数 contact 联系人信息
	 * */
	@Test
	public void testUpdateContactAllInfo() {
		Contact contact = TestUtils.createBean(Contact.class);
		contact.setId("953");
		contact.setNumber("953");
		contact.setLinkmanType("0,1,0,0,0");
		contact.setStatus("2");
		alterMemberDao.updateContactAllInfo(contact);
		
		contact.setStatus("0");
		alterMemberDao.updateContactAllInfo(contact);
		
	}
	/**
	 * @功能描述：根据ID修改账号信息
	 * @参数 account 账号信息
	 * */
	@Test
	public void testUpdateAccountAllInfo() {
		Account account = TestUtils.createBean(Account.class);
		account.setId("572");
		account.setBank("中国农业银行");
		alterMemberDao.updateAccountAllInfo(account);
	}
	/**
	 * @功能描述：根据ID修改偏好地址信息
	 * @参数 preferenceAddress 偏好地址信息
	 * */
	@Test
	public void testUpdatePreferenceAddressAllInfo() {
		PreferenceAddress preferenceAddress = TestUtils.createBean(PreferenceAddress.class);
		preferenceAddress.setId("11");
		preferenceAddress.setPayType("MONTH_PAY");
		preferenceAddress.setShuttleAddressId("11");
		preferenceAddress.setLinkManId("11");
		alterMemberDao.updatePreferenceAddress(preferenceAddress);
	}
	/**
	 * @功能描述：根据ID修改接送货地址信息
	 * @参数 shuttleAddress 接送货地址信息
	 * */
	@Test
	public void testUpdateShuttleAddressAllInfo() {
		ShuttleAddress shuttleAddress = TestUtils.createBean(ShuttleAddress.class);
		shuttleAddress.setId("400033892");
		shuttleAddress.setAddressType("RECEIVE_GOODS");
		alterMemberDao.updateShuttleAddress(shuttleAddress);
	}
	/**
	 * @描述：维护联系人对应的所属客户的客户类型
	 * @参数：member 客户
	 */
	@Test
	public void testUpdateLinkmanCustType() {
		Member member = TestUtils.createBean(Member.class);
		member.setId("796");
		member.setCustType("PERSONAL");
		alterMemberDao.updateLinkmanCustType(member);
	}
	
	@Test
	public void testInvalidMemberFinStatus() {
		List<String> custNumber = new ArrayList<String>();
		custNumber.add("270808");
		custNumber.add("338414");
		custNumber.add("318306");
		custNumber.add("374709");
		alterMemberDao.invalidMemberFinStatus(custNumber,custNumber,true);
	}
	
	public void testQueryCustIdByNumber(){
		List<String> custNumber = new ArrayList<String>();
		custNumber.add("270808");
		custNumber.add("338414");
		custNumber.add("318306");
		custNumber.add("374709");
		alterMemberDao.queryCustIdByNumber(custNumber);
	}
	@Test
	public void testqueryShuttleAddressForDetial(){
		
		List<ShuttleAddress> shuttleAddresses=alterMemberDao.queryShuttleAddressForDetial("219666");
		alterMemberDao.queryShuttleAddressForDetial("219666");
	}
	
	@Test
	public void testqueryArriveScatterMember(){
		 MemberCondition condition = new MemberCondition();
		  condition.setCustGroup(Constant.CUST_GROUP_SC_CUSTOMER);
		  condition.setDeptId("49311");
		  condition.setCustNature(Constant.ArriveCustomer);
		  Calendar ca = Calendar.getInstance(Locale.CHINA);
		  ca.set(Calendar.MONTH, ca.get(Calendar.MONDAY)-6);
		  condition.setCreateDate(ca.getTime());
		 List<MemberResult> res =alterMemberDao.queryArriveScatterMember(condition);
		 System.out.println(res.size());
	}
}
