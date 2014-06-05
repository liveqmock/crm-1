package com.deppon.crm.module.customer.server.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.Assert;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.workflow.IGiftApplyOperate;
import com.deppon.crm.module.client.workflow.domain.GiftApplyInfo;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.file.manager.IFileManager;
import com.deppon.crm.module.customer.server.manager.impl.IntegralManager;
import com.deppon.crm.module.customer.server.service.IAlterMemberService;
import com.deppon.crm.module.customer.server.service.IContactIntegralService;
import com.deppon.crm.module.customer.server.service.IContactVaryService;
import com.deppon.crm.module.customer.server.service.IExamineRecordService;
import com.deppon.crm.module.customer.server.service.IIntegralService;
import com.deppon.crm.module.customer.server.service.IMemberIntegralService;
import com.deppon.crm.module.customer.server.service.IMemberService;
import com.deppon.crm.module.customer.server.service.IWaybillIntegralService;
import com.deppon.crm.module.customer.server.testutils.DBUtils;
import com.deppon.crm.module.customer.server.testutils.TestUtils;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.server.util.UserUtil;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContactView;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.integral.AdjustIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.ContactVary;
import com.deppon.crm.module.customer.shared.domain.integral.Gift;
import com.deppon.crm.module.customer.shared.domain.integral.HandRewardIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGift;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGiftCondition;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralSearchCondition;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.RewardIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.WaybillIntegral;
import com.deppon.crm.module.customer.shared.exception.IntegException;
import com.deppon.crm.module.customer.shared.exception.IntegExceptionType;
import com.deppon.crm.module.custrepeat.server.service.IRepeatedCustService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;


public class IntegralManagerTest extends BeanUtil{
	
	private IntegralSearchCondition memberIntegCondition;
	private IntegralManager integralManagerForMock = new IntegralManager();
	
	
	public void baseDataManagerJMock(){
		Mockery baseDataManagerJMock = new Mockery();
		final IBaseDataManager baseDataManager = baseDataManagerJMock.mock(IBaseDataManager.class);
		baseDataManagerJMock.checking(new Expectations() {
			{
				allowing(baseDataManager).getCauseDepartment(with(any(String.class)));
				will(returnValue(with(any(String.class))));
			}
		});
		integralManagerForMock.setBaseDataManager(baseDataManager);
	}
	
	public void giftApplyOperateJMock() throws CrmBusinessException{
		Mockery giftApplyOperateJMock = new Mockery();
		final IGiftApplyOperate giftApplyOperate = giftApplyOperateJMock.mock(IGiftApplyOperate.class);
		giftApplyOperateJMock.checking(new Expectations() {
			{
				allowing(giftApplyOperate).giftApply(with(any(GiftApplyInfo.class)));
			}
		});
		integralManagerForMock.setGiftApplyOperate(giftApplyOperate);
	}
	
	public void integRuleManagerJMock(){
		Mockery integRuleManagerJMock = new Mockery();
		final IIntegRuleManager integRuleManager = integRuleManagerJMock.mock(IIntegRuleManager.class);
		integRuleManagerJMock.checking(new Expectations() {
			{
				allowing(integRuleManager).getGiftById(with(any(String.class)));
				will(returnValue(with(any(Gift.class))));
				allowing(integRuleManager).updateGiftApproval(with(any(Gift.class)));
				allowing(integRuleManager).updateGift(with(any(Gift.class)));
			}
		});
		integralManagerForMock.setBaseDataManager(baseDataManager);
	}
	
	public void contactManagerJMock(){
		Mockery contactManagerJMock = new Mockery();
		final IContactManager contactManager = contactManagerJMock.mock(IContactManager.class);
		contactManagerJMock.checking(new Expectations() {
			{
				List<Contact> contactList = new ArrayList<Contact>();
				ContactView contactView = new ContactView();
				Contact contact = new Contact();
				contact.setId("999");
				contact.setIsMainLinkMan(false);
				contactView.setContact(contact);
				contactList.add(contact);
				allowing(contactManager).getContactByMemberId(with(any(String.class)));
				will(returnValue(contactList));
				allowing(contactManager).getContactMemberId(with(any(String.class)));
				will(returnValue(with(any(String.class))));
				allowing(contactManager).getContactByNumber(with(any(String.class)));
				will(returnValue(contactView));
				allowing(contactManager).varyContact(with(any(Contact.class)), with(any(Member.class)));
			}
		});
		integralManagerForMock.setContactManager(contactManager);
	}
	
	public void integralServiceJMock(){
		Mockery integralServiceJMock = new Mockery();
		final IIntegralService integralService = integralServiceJMock.mock(IIntegralService.class);
		integralServiceJMock.checking(new Expectations() {
			{
				ContactIntegral contactInteg = new ContactIntegral();
				Contact contact = new Contact();
				contact.setIsMainLinkMan(false);
				contactInteg.setContact(contact);
				allowing(integralService).getContactIntegralByContact(with(any(String.class)));
				will(returnValue(contactInteg));
				allowing(integralService).searchIntegralConvertGiftForContactId(with(any(List.class)), with(any(Integer.class)), with(any(Integer.class)));
				will(returnValue(with(any(List.class))));
				allowing(integralService).searchHandRewardIntegralForContactId(with(any(List.class)), with(any(Integer.class)), with(any(Integer.class)));
				will(returnValue(with(any(List.class))));
				allowing(integralService).searchAdjustIntegralForContactId(with(any(List.class)), with(any(Integer.class)), with(any(Integer.class)));
				will(returnValue(with(any(List.class))));
				allowing(integralService).countSearchIntegralConvertGiftForContactId(with(any(List.class)));
				will(returnValue(with(any(Long.class))));
				allowing(integralService).countSearchHandRewardIntegralForContactId(with(any(List.class)));
				will(returnValue(with(any(Long.class))));
				allowing(integralService).countSearchAdjustIntegralForContactId(with(any(List.class)));
				will(returnValue(with(any(Long.class))));
			}
		});
		integralManagerForMock.setIntegralService(integralService);
	}
	
	
	public void contactIntegralServiceJMock(){
		Mockery contactIntegralServiceJMock = new Mockery();
		final IContactIntegralService contactIntegralService = contactIntegralServiceJMock.mock(IContactIntegralService.class);
		contactIntegralServiceJMock.checking(new Expectations() {
			{
				ContactIntegral toContactInteg = new ContactIntegral();
				allowing(contactIntegralService).searchContactIntegralsBycontactIdList(with(any(List.class)), with(any(Integer.class)), with(any(Integer.class)));
				will(returnValue(with(any(List.class))));
				allowing(contactIntegralService).getContactIntegralByContactId(with(any(String.class)));
				will(returnValue(toContactInteg));
			}
		});
		integralManagerForMock.setContactIntegralService(contactIntegralService);
	}
	
//	public void repeatedCustServiceJMock(){
//		Mockery repeatedCustServiceJMock = new Mockery();
//		final IRepeatedCustService repeatedCustService = repeatedCustServiceJMock.mock(IRepeatedCustService.class);
//		repeatedCustServiceJMock.checking(new Expectations() {
//			{
//				allowing(repeatedCustService).isCustExistsRepeat(with(any(String.class)));
//				will(returnValue(false));
//			}
//		});
//		integralManagerForMock.setRepeatedCustService(repeatedCustService);
//	}
	
	public void fileManagerJMock(){
		Mockery fileManagerJMock = new Mockery();
		final IFileManager fileManager = fileManagerJMock.mock(IFileManager.class);
		fileManagerJMock.checking(new Expectations() {
			{
				allowing(fileManager).saveFileInfo(with(any(List.class)));
				allowing(fileManager).searchFileInfoByCondition(with(any(FileInfo.class)),with(any(Integer.class)),with(any(Integer.class)));
				will(returnValue(with(any(List.class))));
			}
		});
		integralManagerForMock.setFileManager(fileManager);
	}
	
	public void contactVaryServiceJMock(){
		Mockery contactVaryServiceJMock = new Mockery();
		final IContactVaryService contactVaryService = contactVaryServiceJMock.mock(IContactVaryService.class);
		contactVaryServiceJMock.checking(new Expectations() {
			{
				ContactVary contactVary = new ContactVary();
				MemberIntegral moveMembeIntegral = new MemberIntegral();
				Member member = new Member();
				Contact toContact = new Contact();
				toContact.setId("1111");
				member.setMainContact(toContact);
				moveMembeIntegral.setMember(member);
				contactVary.setFromMemberIntegral(moveMembeIntegral);
				contactVary.setToMemberIntegral(moveMembeIntegral);
				allowing(contactVaryService).getContactVaryId();
				will(returnValue(with(any(String.class))));
				allowing(contactVaryService).insertContactVary(with(any(ContactVary.class)));
				allowing(contactVaryService).getContactVaryByWorkFlowId(with(any(Long.class)));
				will(returnValue(with(any(ContactVary.class))));
				allowing(contactVaryService).getContactVaryById(with(any(String.class)));
				will(returnValue(contactVary));
				
				
			}
		});
		integralManagerForMock.setContactVaryService(contactVaryService);
	}
	
	public void alterMemberManagerJMock(){
		Mockery alterMemberManagerJMock = new Mockery();
		final IAlterMemberManager alterMemberManager = alterMemberManagerJMock.mock(IAlterMemberManager.class);
		alterMemberManagerJMock.checking(new Expectations() {
			{
				allowing(alterMemberManager).cleanContactPreferenceAddress(with(any(String.class)));
				allowing(alterMemberManager).getMemberIdByMemberNum(with(any(String.class)));
				will(returnValue(with(any(String.class))));
				allowing(alterMemberManager).getMemberIdByContactNum(with(any(String.class)));
				will(returnValue(with(any(String.class))));
				allowing(alterMemberManager).getMemberIdByContactMobile(with(any(String.class)));
				will(returnValue(with(any(String.class))));
				allowing(alterMemberManager).getContact(with(any(String.class)));
				will(returnValue(with(any(Contact.class))));
				
				
			}
		});
		integralManagerForMock.setAlterMemberManager(alterMemberManager);
	}
	
	public void alterMemberServiceJMock(){
		Mockery alterMemberServiceJMock = new Mockery();
		final IAlterMemberService alterMemberService = alterMemberServiceJMock.mock(IAlterMemberService.class);
		alterMemberServiceJMock.checking(new Expectations() {
			{
				Member  member = new Member();
				member.setCustGroup("11");
				allowing(alterMemberService).getCustWorkflow(with(any(String.class)));
				will(returnValue(""));
				allowing(alterMemberService).getMemberById(with(any(String.class)));
				will(returnValue(member));
			}
		});
		integralManagerForMock.setAlterMemberService(alterMemberService);
	}
	
	public void waybillIntegralServiceJMock(){
		Mockery waybillIntegralServiceJMock = new Mockery();
		final IWaybillIntegralService waybillIntegralService = waybillIntegralServiceJMock.mock(IWaybillIntegralService.class);
		waybillIntegralServiceJMock.checking(new Expectations() {
			{
				List<WaybillIntegral> waybillIntegralList = new ArrayList<WaybillIntegral>();
				WaybillIntegral waybillIntegral = new WaybillIntegral();
				waybillIntegral.setPayMent("10");
				waybillIntegralList.add(waybillIntegral);
				allowing(waybillIntegralService).searchWaybillIntegralForContactId(with(any(List.class)), with(any(Integer.class)), with(any(Integer.class)));
				will(returnValue(waybillIntegralList));
				allowing(waybillIntegralService).countSearchWaybillIntegralForContactId(with(any(List.class)));
				will(returnValue(with(any(Long.class))));
			}
		});
		integralManagerForMock.setWaybillIntegralService(waybillIntegralService);;
	}
	
	public void memberIntegralServiceJMock(){
		Mockery memberIntegralServiceJMock = new Mockery();
		final IMemberIntegralService memberIntegralService = memberIntegralServiceJMock.mock(IMemberIntegralService.class);
		memberIntegralServiceJMock.checking(new Expectations() {
			{
				MemberIntegral memberIntegral  = new  MemberIntegral();
				allowing(memberIntegralService).getMemberIntegralByMemberId(with(any(String.class)));
				will(returnValue(memberIntegral));
				allowing(memberIntegralService).searchMemberIntegralsForCondition(with(any(IntegralSearchCondition.class)), with(any(Integer.class)), with(any(Integer.class)));
				will(returnValue(with(any(List.class))));
				allowing(memberIntegralService).countSearchMemberIntegralsForCondition(with(any(IntegralSearchCondition.class)));
				will(returnValue(with(any(Long.class))));
			}
		});
		integralManagerForMock.setMemberIntegralService(memberIntegralService);
	}
	
	public void memberServiceJMock(){
		Mockery memberServiceJMock = new Mockery();
		final IMemberService memberService = memberServiceJMock.mock(IMemberService.class);
		memberServiceJMock.checking(new Expectations() {
			{
				allowing(memberService).updateMemberStauts(with(any(String.class)), with(any(String.class)), with(any(String.class)));
			}
		});
		integralManagerForMock.setMemberService(memberService);
	}
	
	public void memberWorkFlowManagerJMock(){
		Mockery memberWorkFlowManagerJMock = new Mockery();
		final IMemberWorkFlowManager memberWorkFlowManager = memberWorkFlowManagerJMock.mock(IMemberWorkFlowManager.class);
		memberWorkFlowManagerJMock.checking(new Expectations() {
			{
				allowing(memberWorkFlowManager).createContactVaryWorkFlow(with(any(String.class)), with(any(String.class)), with(any(String.class)), with(any(String.class)));
			}
		});
		integralManagerForMock.setMemberWorkFlowManager(memberWorkFlowManager);
	}
	
	public void examineRecordServiceJMock(){
		Mockery examineRecordServiceJMock = new Mockery();
		final IExamineRecordService examineRecordService = examineRecordServiceJMock.mock(IExamineRecordService.class);
		examineRecordServiceJMock.checking(new Expectations() {
			{
				allowing(examineRecordService).getExamineRecordByWorkflowId( with(any(Long.class)));
				will(returnValue(with(any(List.class))));
				allowing(examineRecordService).getCurrentPeople( with(any(Long.class)));
				will(returnValue(with(any(String.class))));
			}
		});
		integralManagerForMock.setExamineRecordService(examineRecordService);
	}
	
	List<String> contactIdList=  new ArrayList<String>();
	private int start = 0;
	private int limit=5;
	@Override
	public void setUp() throws Exception {
//		DBUtils.clean();
		contactIdList = new ArrayList<String>();
		contactIdList.add("2");
		memberIntegCondition = new IntegralSearchCondition();
		memberIntegCondition.setContactName("wbd");
		memberIntegCondition.setDeptId("332");
//		DBUtils.initContract();
		baseDataManagerJMock();
		giftApplyOperateJMock();
		integRuleManagerJMock();
		contactManagerJMock();
		integralServiceJMock();
		contactIntegralServiceJMock();
//		repeatedCustServiceJMock();
		fileManagerJMock();
		contactVaryServiceJMock();
		alterMemberManagerJMock();
		alterMemberServiceJMock();
		waybillIntegralServiceJMock();
		memberIntegralServiceJMock();
		memberServiceJMock();
		memberWorkFlowManagerJMock();
		examineRecordServiceJMock();
		
	}
	@Override
	public void tearDown() throws Exception{
		DBUtils.clean();
		clearData();
	}
	
	@Test
	public void testGiftIntegralCreateApprove(){
		integralManagerForMock.giftIntegralCreateApprove("11", true, "11", new Date());
	}
	
	@Test
	public void testCreateContactVaryExaminView(){
		integralManagerForMock.createContactVaryExaminView("11",123456L);
	}
	
	@Test
	public void testGetMemberIntegralByMemberNumFor360(){
		integralManagerForMock.getMemberIntegralByMemberNumFor360("11");
	}
	
	
	@Test
	public void testCountSearchIntegralConvertGiftForContactId(){
		List<String> contactIdList = new ArrayList<String>();
		integralManagerForMock.countSearchIntegralConvertGiftForContactId(contactIdList);
	}
	
	@Test
	public void testCountSearchWaybillIntegralForContactId(){
		List<String> contactIdList = new ArrayList<String>();
		integralManagerForMock.countSearchWaybillIntegralForContactId(contactIdList);
	}
	
	@Test
	public void testCountSearchHandRewardIntegralForContactId(){
		List<String> contactIdList = new ArrayList<String>();
		integralManagerForMock.countSearchHandRewardIntegralForContactId(contactIdList);
	}
	
	@Test
	public void testCountSearchAdjustIntegralForContactId(){
		List<String> contactIdList = new ArrayList<String>();
		integralManagerForMock.countSearchAdjustIntegralForContactId(contactIdList);
	}
	
	@Test
	public void testGetContactIntegral(){
		integralManagerForMock.getContactIntegral("1111");
	}
	@Test
	public void testGetNotMainContact(){
		integralManagerForMock.getNotMainContact("1111");
	}
	
	@Test
	public void testContactVaryTest(){
		User user = new User();
		Employee e = new Employee();
		e.setEmpCode("150960");
		e.setId("150960");
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
		ContactVary contactVary = new ContactVary();
		List<FileInfo> fileList = new ArrayList<FileInfo>();
		MemberIntegral fromMemberIntegral = new MemberIntegral();
		MemberIntegral toMemberIntegral = new MemberIntegral();
		Member member = new Member();
		fromMemberIntegral.setId("1111");
		toMemberIntegral.setId("2222");
		member.setCustStatus("1");
		member.setId("111");
		fromMemberIntegral.setMember(member);
		toMemberIntegral.setMember(member);
		contactVary.setFromMemberIntegral(fromMemberIntegral);
		contactVary.setToMemberIntegral(toMemberIntegral);
		try {
			//TODO
			integralManagerForMock.contactVary(contactVary, fileList);
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void testContactVary(){
		ContactVary contactVary = new ContactVary();
		MemberIntegral fromMemberIntegral = new MemberIntegral();
		Member member = new Member();
		member.setCustStatus("1");
		member.setId("444");
		fromMemberIntegral.setMember(member);
		fromMemberIntegral.setId("555");
		contactVary.setFromMemberIntegral(fromMemberIntegral);
		contactVary.setToMemberIntegral(fromMemberIntegral);
		contactVary.setContactNumber("11111");
		integralManagerForMock.giftIntegralCreateApprove("11", true, "11", new Date());
	}
	
	@Test
	public void testSearchWaybillIntegralForContactId(){
		List<String> list = new ArrayList<String>();
		integralManagerForMock.searchWaybillIntegralForContactId(list, 0, 20);
	}
	
	@Test
	public void testget3ContactIntegral(){
		List<ContactIntegral> list = integralManager.get3ContactIntegral("002244");
		Assert.assertNotNull("查询结果不为空", list);
	}
	@Test
	public void testgetContactIntegralListByMemId(){
		List<ContactIntegral> list = integralManager.getContactIntegralListByMemId("002244");
		Assert.assertNotNull(list);
	}
	@Test
	public void testgetMemberIntegralByContactId(){
		MemberIntegral m= integralManager.getMemberIntegralByContactId("40000180");
		Assert.assertNull(m);
	}
	@Test
	public void testgetMemberIntegralListByContact(){
		List<String> slist = new ArrayList<String>();
		//追加联系人编码
		slist.add("122234");
		
		integralManager.getMemberIntegralListByContact(slist);
	}
	@Test
	public void testgetMemberIntegralListByMemberId(){
		integralManager.getMemberIntegralListByMemberId("002244");
	}
	@Test
	public void testsearchMemberIntegralsForCondition(){
		memberIntegCondition.setContactName(null);
		memberIntegCondition.setDeptId(null);
		User user = new User();
		Department depart = new Department();
		depart.setId("432658");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		user.setEmpCode(e);
		Set<String> deptids= new TreeSet<String>();
		deptids.add("1");
		user.setDeptids(deptids);
		UserContext.setCurrentUser(user);
		integralManager.searchMemberIntegralsForCondition(memberIntegCondition, start, limit);
	
	}
	@Test
	public void testcountSearchMemberIntegralsForCondition(){
		memberIntegCondition.setContactName(null);
		memberIntegCondition.setDeptId(null);
		User user = new User();
		Department depart = new Department();
		depart.setId("432658");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		user.setEmpCode(e);
		Set<String> deptids= new TreeSet<String>();
		deptids.add("1");
		user.setDeptids(deptids);
		UserContext.setCurrentUser(user);
		memberIntegCondition.setContactName(null);
		memberIntegCondition.setDeptId(null);
		integralManager.countSearchMemberIntegralsForCondition(memberIntegCondition);
	}
	@Test
	public void testsearchContactIntegralsForContactId(){
		integralManager.searchContactIntegralsForContactId(contactIdList, start, limit);
	}
	@Test
	public void testsearchIntegralConvertGiftForContactId(){
		integralManager.searchIntegralConvertGiftForContactId(contactIdList, start, limit);
	}
	@Test
	public void testsearchHandRewardIntegralForContactId(){
		integralManager.searchHandRewardIntegralForContactId(contactIdList, start, limit);
	}
	@Test
	public void testsearchAdjustIntegralForContactId(){
		integralManager.searchAdjustIntegralForContactId(contactIdList, start, limit);
	}
	@Test
	public void testcontactVary(){
		UserUtil.setUserToAdmin();
		ContactVary contactVary = new ContactVary();
		contactVary.setContactNumber("sspps13524052214");
		List<FileInfo> fileList = new ArrayList<FileInfo>();
		FileInfo info = TestUtils.createBean(FileInfo.class);
		fileList.add(info);
		//目标会员积分和被异动会员积分都为空
		long result = integralManager.contactVary(contactVary, fileList);
		Assert.assertEquals(0, result);
		
//		contactVary.setToMemberIntegral(toMemberIntegral)
	}
	
	@Test
	public void testdisposeContactVaryByExamineResult(){
		User user = new User();
		Department depart = new Department();
		depart.setId("86301");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		e.setId("98564");
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
		
		integralManager.disposeContactVaryByExamineResult("21", true);
	
		integralManager.disposeContactVaryByExamineResult("400000281", false);
		
		integralManager.disposeContactVaryByExamineResult("1000004", true);
		
		integralManagerForMock.disposeContactVaryByExamineResult("111", true);
	}
	@Test
	public void testgetContactVaryByWorkFlowId(){
		integralManager.getContactVaryByWorkFlowId(400003462);
	}

	@Test
	public void testcreateHandRewardIntegrals(){
		User user = new User();
		Department depart = new Department();
		depart.setId("86301");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		e.setId("98564");
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
		
		RewardIntegRule rewardRule = new RewardIntegRule();
		rewardRule.setPointbegintime(getDate(-1));
		rewardRule.setPointendtime(getDate(1));
		rewardRule.setPointvalue(12);
		rewardRule.setFraction(23.3);
		List<String> contactIds = new ArrayList<String>();
		contactIds.add("10333");
		contactIds.add("10334");
		try{
		integralManager.createHandRewardIntegrals(1223, rewardRule, contactIds);
		}catch(Exception ex){}
	
	}
	/**
	 * <p>
	 * 根据会员编码查询会员积分对象<br />
	 * </p>
	 */
	@Test
	public void testgetMemberIntegralByMemberNum(){
		String memberNum = "290758";
		Assert.assertNotNull(integralManager.getMemberIntegralByMemberNum(memberNum));
	}
	/**
	 * <p>
	 * 根据联系人编码查询会员积分对象<br />
	 * </p>
	 */
	@Test
	public void testgetMemberIntegralByContactNum(){
		String contactNum = "osp15914146016";
		Assert.assertNotNull(integralManager.getMemberIntegralByContactNum(contactNum));
	}
	/**
	 * <p>
	 * 根据手机号码查询会员积分对象<br />
	 * </p>
	 */
	@Test
	public void testgetMemberIntegralByContactMobile(){
		String mobilePhone = "15914146016";
		Assert.assertNotNull(integralManager.getMemberIntegralByContactMobile(mobilePhone));
	}
	@Test
	public void testsearchAdjustIntegrals(){
		AdjustIntegral adjust = new AdjustIntegral();
		Contact contact = new Contact();
		contact.setMobilePhone("15914146016");
		adjust.setContact(contact);
		Assert.assertNotNull(integralManager.searchAdjustIntegrals(adjust, start, limit));
	}
	@Test
	public void testcountSearchAdjustIntegrals(){
		AdjustIntegral adjust = new AdjustIntegral();
		Contact contact = new Contact();
		contact.setMobilePhone("15914146016");
		adjust.setContact(contact);
		Assert.assertNotNull(integralManager.countSearchAdjustIntegrals(adjust));
	}
	@Test
	public void testsearchHandRewardIntegrals(){
		int start = 0;
		int limit = Integer.MAX_VALUE;
		HandRewardIntegral handRewardIntegral = new HandRewardIntegral();
		Assert.assertNotNull(integralManager.searchHandRewardIntegrals(handRewardIntegral, start, limit));
	}
	@Test
	public void testcountSearchHandRewardIntegrals(){
		HandRewardIntegral handRewardIntegral = new HandRewardIntegral();
		Assert.assertNotNull(integralManager.countSearchHandRewardIntegrals(handRewardIntegral));
	}
	/**
	 * 
	 * <p>
	 * 更新积分兑换礼品<br />
	 * </p>
	 */
	@Test
	public void testupdateIntegralConvertGift(){
		IntegralConvertGift integralConvertGift = new IntegralConvertGift();
		integralConvertGift.setId("400642462");
		integralConvertGift.setModifyDate(new Date());
		integralManager.updateIntegralConvertGift(integralConvertGift);
	}
	/**
	 * <p>
	 * 组合查询积分兑换<br />
	 * </p>
	 */
	@Test
	public void testsearchIntegralConvertGift(){
		int start = 0 ;
		int limit = Integer.MAX_VALUE;
		IntegralConvertGiftCondition condition = new IntegralConvertGiftCondition();
		condition.setMemberNum("290758");
		Assert.assertNotNull(integralManager.searchIntegralConvertGift(condition, start, limit));
	}
	/**
	 * <p>
	 * 统计组合查询积分兑换数量<br />
	 * </p>
	 */
	@Test
	public void testcountSearchIntegralConvertGift(){
		IntegralConvertGiftCondition condition = new IntegralConvertGiftCondition();
		condition.setMemberNum("290758");
		Assert.assertNotNull(integralManager.countSearchIntegralConvertGift(condition));
	}

	
	public void testsaveIntegralConvertGift(){
//		integralManager.saveIntegralConvertGift(integralList);
	}
	@Test
	public void testgetMyIntegralConvertGift(){
		integralManager.getMyIntegralConvertGift();
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试检查兑换中使用的礼品，是否满足要求，返回不满足要求的联系人编码<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-29
	 * void
	 */
	@Test
	public void test_insertIntegralConvertGift(){
		User user = new User();
		Department depart = new Department();
		depart.setId("265222");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		e.setId("98564");
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
		
		List<IntegralConvertGift> integrals = new ArrayList<IntegralConvertGift>();
		IntegralConvertGift integralConvertGift = new IntegralConvertGift();

		integrals.add(integralConvertGift);

		integralConvertGift = new IntegralConvertGift();
		Contact contact = new Contact();
		contact.setId("1346");
		integralConvertGift.setContact(contact);
		
		Gift gift = new Gift();
		gift.setId("400000109");
		integralConvertGift.setGift(gift);
		
		integralConvertGift = new IntegralConvertGift();
		 contact = new Contact();
		contact.setId("1346");
		integralConvertGift.setContact(contact);
		
		 gift = new Gift();
		gift.setId("800000084");
		integralConvertGift.setGift(gift);
		
		integrals.add(integralConvertGift);
//		integralManager.insertIntegralConvertGift(integrals);
		
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试根据条件得到会员积分对象列表<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-1-26
	 * void
	 */
	@Test
	public void test_searchMemberIntegralsForCondition(){
		User user = new User();
		Department depart = new Department();
		depart.setId("432658");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		e.setId("98564");
		user.setEmpCode(e);
		Set<String> deptids = new TreeSet<String>();
		deptids.add("432658");
		user.setDeptids(deptids);
		UserContext.setCurrentUser(user);
		
		IntegralSearchCondition memberIntegCondition = new IntegralSearchCondition ();
		integralManager.searchMemberIntegralsForCondition(memberIntegCondition, start, limit);
		integralManager.countSearchMemberIntegralsForCondition(memberIntegCondition);
		
		memberIntegCondition.setMemberNum("memberNum");
		integralManager.searchMemberIntegralsForCondition(memberIntegCondition, start, limit);
		integralManager.countSearchMemberIntegralsForCondition(memberIntegCondition);
		 memberIntegCondition = new IntegralSearchCondition ();
		memberIntegCondition.setMemberName("memberName");
		integralManager.searchMemberIntegralsForCondition(memberIntegCondition, start, limit);
		integralManager.countSearchMemberIntegralsForCondition(memberIntegCondition);
		
		memberIntegCondition = new IntegralSearchCondition ();
		memberIntegCondition.setContactName("contactName");
		integralManager.searchMemberIntegralsForCondition(memberIntegCondition, start, limit);
		integralManager.countSearchMemberIntegralsForCondition(memberIntegCondition);
		
		memberIntegCondition = new IntegralSearchCondition ();
		memberIntegCondition.setContactId("3456789");
		integralManager.searchMemberIntegralsForCondition(memberIntegCondition, start, limit);
		integralManager.countSearchMemberIntegralsForCondition(memberIntegCondition);	
		
	
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-29
	 * void
	 */
	@Test
	public void test_contactVary(){
		MemberIntegral fromMemberIntegral = new MemberIntegral();
		MemberIntegral toMemberIntegral = new MemberIntegral();
		// 参数错误
	    ContactVary contactVary = new ContactVary ();
	    List<FileInfo> fileList = new ArrayList<FileInfo>();
		integralManager.contactVary(null,fileList);
		integralManager.contactVary(contactVary,fileList);
		
		contactVary.setFromMemberIntegral(fromMemberIntegral);
		integralManager.contactVary(contactVary,fileList);
		
		
		toMemberIntegral.setMember(null);
		contactVary.setToMemberIntegral(toMemberIntegral);
		integralManager.contactVary(contactVary,fileList);

		fromMemberIntegral.setMember(null);
		contactVary.setToMemberIntegral(toMemberIntegral);
		integralManager.contactVary(contactVary,fileList);
		try{
			
			toMemberIntegral.setMember(new Member());
			toMemberIntegral.setId("1");
			fromMemberIntegral.setMember(new Member());
			fromMemberIntegral.setId("1");
		contactVary.setToMemberIntegral(toMemberIntegral);
		integralManager.contactVary(contactVary,fileList);
		Assert.fail();
		}catch(IntegException ie){
			Assert.assertEquals(IntegExceptionType.SameMemberCanNotContact.getErrCode(), ie.getErrorCode());
		}
		
		contactVary = new ContactVary();
		long result = integralManager.contactVary(contactVary,fileList);
		Assert.assertEquals(0, result);
		// 封装参数错误
	
		contactVary.setFromMemberIntegral(fromMemberIntegral);
		fromMemberIntegral.setMember(null);
	
		contactVary.setToMemberIntegral(toMemberIntegral);

		result = integralManager.contactVary(contactVary,fileList);
		Assert.assertEquals(0, result);
		
		// 同一个会员改变
		try{
		fromMemberIntegral = new MemberIntegral();
		fromMemberIntegral.setId("1");
		fromMemberIntegral.setMember(new Member());
		contactVary.setFromMemberIntegral(fromMemberIntegral);
		 toMemberIntegral = new MemberIntegral();
		 toMemberIntegral.setId("1");
		 toMemberIntegral.setMember(new Member());
		contactVary.setToMemberIntegral(toMemberIntegral);
		result = integralManager.contactVary(contactVary,fileList);

		}catch(IntegException ie){
			Assert.assertEquals(IntegExceptionType.SameMemberCanNotContact.getErrCode(), ie.getErrorCode());
		}
		
		try{
		fromMemberIntegral = new MemberIntegral();
		fromMemberIntegral.setId("1");
		fromMemberIntegral.setMember(new Member());
		contactVary.setFromMemberIntegral(fromMemberIntegral);
		 toMemberIntegral = new MemberIntegral();
		 toMemberIntegral.setId("2");
		 toMemberIntegral.setMember(new Member());
		contactVary.setToMemberIntegral(toMemberIntegral);
		contactVary.setContactNumber("cwm013710522799");
//		result = integralManager.contactVary(contactVary,fileList);
		}catch(IntegException ie){
			Assert.assertEquals(IntegExceptionType.MainLinkManNotChangeMember.getErrCode(), ie.getErrorCode());
		}
		
		//成功
		User user = new User();
		Department depart = new Department();
		depart.setId("86301");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		e.setId("98564");
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);

		contactVary.setId("1232");
		fromMemberIntegral = new MemberIntegral();
		fromMemberIntegral.setId("1000001");
		fromMemberIntegral.getMember().setId("400532478");
		fromMemberIntegral.getMember().setDeptId("13332");
		contactVary.setFromMemberIntegral(fromMemberIntegral);
		
		 toMemberIntegral = new MemberIntegral();
		 toMemberIntegral.setId("1000002");
		 toMemberIntegral.getMember().setId("1000002");
		 toMemberIntegral.getMember().setDeptId("13330");
		 contactVary.setContactNumber("sspps13524052214");
		contactVary.setToMemberIntegral(toMemberIntegral);
//		result = integralManager.contactVary(contactVary,fileList);
//		Assert.assertTrue(result>0);
		
	}
	
	@Test
	public void test_approvalIntegralConvertGift() throws SQLException{
		init_approvalIntegralConvertGift();
		integralManager.approvalIntegralConvertGift(true, "23545097645");
		
		
		integralManager.approvalIntegralConvertGift(false, "23545097645");
	
		
	}
	@Test
	public void test_updateIntegralConvertGiftSend(){
		List<String> idList = new ArrayList<String>();
		integralManager.updateIntegralConvertGiftSend(idList);
		integralManager.updateIntegralConvertGiftRecover(idList);
		
		idList.add("3459876543");
		integralManager.updateIntegralConvertGiftSend(idList);
		integralManager.updateIntegralConvertGiftRecover(idList);
		
		
	}
	
	@Test
	public void test_saveIntegralConvertGift(){
		User user = new User();
		Department depart = new Department();
		depart.setId("432658");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		e.setId("98564");
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
		
		List<IntegralConvertGift> integralList = new ArrayList<IntegralConvertGift> ();
		integralManager.saveIntegralConvertGift(integralList);
		
		IntegralConvertGift icf = new IntegralConvertGift();
		integralList.add(icf);
		integralManager.saveIntegralConvertGift(integralList);
		
		icf.setId("4000017289895");
		
		integralManager.saveIntegralConvertGift(integralList);
	}

	@Test
	public void test_getContactIntegralByContactNumber(){
		
		integralManager.getContactIntegralByContactNumber("239745332234");
		
		integralManager.getContactIntegralByContactNumber("ljh13527962031");
		
	}
	
	@Test
	public void test_searchTemporaryGiftBill(){
		User user = new User();
		Department depart = new Department();
		depart.setId("432658546");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		e.setId("98564");
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
		
		integralManager.searchTemporaryGiftBill();
	}
	
	
	
	private void init_approvalIntegralConvertGift() throws SQLException{
		
		List<String> sqlList = new ArrayList<String>();
		sqlList.add("insert into t_cust_gift (FID, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID,"+
                   "FGIFTNAME, FNEEDPOINTS, FINVENTNUMBER, FGIFTVALUE, FGIFTDESC,"+
                   "FGIFTNUMBER, FREALVALUE, FBEGINTIME, FENDTIME, FDEPARTMENTID, FISSTART)"+
             "values (800056081, sysdate, 1, '', 1,"+
                   "'bxj', 1, 1, 2, '1',"+
                   "'1', 2, sysdate-2, sysdate+2, 1, '0')");
		
		sqlList.add("insert into T_CUST_INTEG(FID,FCREATETIME,FCREATEUSERID,"+
         "FLASTUPDATETIME,FLASTMODIFYUSERID,FSCORE, FTYPE,FCONTACTID,FGIFTID,"+
        "FCONVERTNUMBER,FCONVERTIDCARD,FWORKFLOWID,FCONVERTTIME,FSENDTIME,FISSEND,FREWARDINTEGRALID,"+
        "FINTEGRALBASICNUMBER,FADJUSTTYPE,FCONTACTFROMID,FCONTACTTOID,FREWARDDATE,"+
        "fsendStatus,FdeptId) "+
         " values (4000017289895, sysdate, 98564, "+
        		 "null, null, 100, 'IntegralConvertGift', 573716, 800056081," +
        		 " '09239834',341221198710025338, 23545097645, sysdate, null, null, null, " +
        		 "null, null, null, null, null," +
        		 "null, '432658')");

		SpringTestHelper.executeBatch(sqlList);
	}
	
	
	private void clearData() throws SQLException{
		List<String> sqlList = new ArrayList<String>();
		sqlList.add("delete T_CUST_INTEG where FID = 4000017289895");
		sqlList.add("delete t_cust_gift where FID = 800056081");
		SpringTestHelper.executeBatch(sqlList);
	}
	
	
	
	
	private Date getDate(int day){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+day);
	 return cal.getTime();
	}
}
