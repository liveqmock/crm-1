package com.deppon.crm.module.customer.server.manager.impl;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.Province;
import com.deppon.crm.module.customer.server.manager.IContactManager;
import com.deppon.crm.module.customer.server.service.IAlterMemberService;
import com.deppon.crm.module.customer.server.service.IApprovingWokflowDataService;
import com.deppon.crm.module.customer.server.service.IMemberService;
import com.deppon.crm.module.customer.shared.domain.ApprovingWorkflowData;
import com.deppon.crm.module.customer.shared.domain.ChannelCustomer;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberExtend;
import com.deppon.crm.module.customer.shared.domain.MemberOperationLog;
import com.deppon.crm.module.customer.shared.exception.CustomerException;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.customer.shared.exception.MemberExceptionType;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;

public class MemberManagerMockTest extends TestCase {
	public static MemberManager memberManager = new MemberManager();
	public Member member = new Member();
	@Before
	protected void setUp() throws Exception {
		alterMemberServiceJMock();
		contactManagerJMock();
		memberServiceJMock();
		departmentServiceJmock();
		approvingWokflowDataServiceJMock();
		departmentManagerJmock();
		contextInit();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * 
	* @Title: contextInit
	* @Description: 
	* @author chenaichun 
	* @param     设定文件
	* @date 2014-3-22 上午11:44:05
	* @return void    返回类型
	* @throws
	* @update 2014-3-22 上午11:44:05
	 */
	public void contextInit() {
		User user = new User();
		Department depart = new Department();
		depart.setId("92974");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);

	}
	
	public void departmentManagerJmock(){
		Mockery departmentManagerJmock = new Mockery();
		final ILadingstationDepartmentManager departmentManager = departmentManagerJmock.mock(ILadingstationDepartmentManager.class);
		departmentManagerJmock.checking(new Expectations(){
			{
				BussinessDept busDept = new BussinessDept();
				Province province = new Province();
				province.setId("13");
				province.setName("安徽省");
				City city = new City();
				city.setId("99");
				city.setName("合肥市");
				busDept.setCity(city);
				busDept.setProvince(province);
				allowing(departmentManager).getBusDeptById(with(any(String.class)));
				will(returnValue(busDept));
			}
		});
		memberManager.setDepartmentManager(departmentManager);
	}
	
    public void departmentServiceJmock(){
    	Mockery departmentServiceMockery = new Mockery();
    	final IDepartmentService departmentService = departmentServiceMockery.mock(IDepartmentService.class);
    	departmentServiceMockery.checking(new Expectations(){
    		{
			Department dept = new Department();
			dept.setId("408218");
			dept.setDeptName("电销管理小组");
    		allowing(departmentService).getDeptByStandardCode(Constant.CALL_SALE_STANDCODE);
    		will(returnValue(dept));
    		}
    	});
    	memberManager.setDepartmentService(departmentService);
    }
	public void alterMemberServiceJMock() {
		Mockery alterMemberServiceMockery = new Mockery();
		final IAlterMemberService alterMemberService = alterMemberServiceMockery
				.mock(IAlterMemberService.class);
		final List<Member> memberList = new ArrayList<Member>();
		Member member = new Member();
		member.setId("111");
		memberList.add(member);
		final MemberCondition memberCondition_1 = new MemberCondition();
		memberCondition_1.setMobile("1301213");
		final MemberCondition memberCondition_2 = new MemberCondition();
		memberCondition_2.setTelePhone("027");
		memberCondition_2.setLinkManName("cc");
		alterMemberServiceMockery.checking(new Expectations() {
			{
				allowing(alterMemberService).searchMemberByCondition(memberCondition_1);
				will(returnValue(new ArrayList<Member>()));
				allowing(alterMemberService).searchMemberByCondition(memberCondition_2);
				will(returnValue(memberList));
				allowing(alterMemberService).saveMemberOperationLog(new MemberOperationLog());
			}
		});
		memberManager.setAlterMemberService(alterMemberService);
	}
	
	public void memberServiceJMock(){
		Mockery memberServiceMockery = new Mockery();
		final IMemberService memberService = memberServiceMockery
				.mock(IMemberService.class);
		memberServiceMockery.checking(new Expectations(){
			{
				//验证checkTaxregNumber()
				allowing(memberService).getMemberListByTaxregNumber("tx123");
				will(returnValue(null));
				allowing(memberService).getMemberListByTaxregNumber("01234567890");
				Map<String, String> sMap = new HashMap<String, String>();
				sMap.put("CUST", "kkk");
				List<Map> mapList = new ArrayList<Map>();
				mapList.add(sMap);
				will(returnValue(mapList));
				allowing(memberService).getMemberListByTaxregNumber("0123456789");
				will(returnValue(mapList));
				allowing(memberService).getMemberListByTaxregNumber("123456789");
				will(returnValue(null));
				allowing(memberService).getMemberListByTaxregNumber("12345678910");
				will(returnValue(null));
				//验证checkPhone()
				allowing(memberService).getMemberListByCellphone("13011");
				will(returnValue(mapList));
				allowing(memberService).getMemberListByCellphone("1301213");
				will(returnValue(null));
				//验证checkTelandName（）
				allowing(memberService).getMemberListByCustnameAndContact("aa","021");
				will(returnValue(null));
				allowing(memberService).getMemberListByCustnameAndContact("cc","333");
				will(returnValue(mapList));
				//验证isExistLinkManMuber()
				allowing(memberService).getMemberListByLinkManNumber("aa021");
				will(returnValue(null));
				allowing(memberService).getMemberListByLinkManNumber("bb222");
				will(returnValue(mapList));
				allowing(memberService).getMemberListByLinkManNumber("cc333");
				will(returnValue(null));
				allowing(memberService).getMemberListByLinkManNumber(with(any(String.class)));
				will(returnValue(null));
				//验证idCardCanUse()
				allowing(memberService).getMemberListByPersonalcardNumber("OTHER", "55555", Constant.Person_Member, true);
				will(returnValue(null));
				allowing(memberService).getMemberListByPersonalcardNumber("SECOND_GENERATION_IDCARD", "421181195606255453", Constant.Person_Member, true);
				will(returnValue(mapList));
				allowing(memberService).getMemberListByPersonalcardNumber("OTHER", "33333", Constant.Person_Member, true);
				will(returnValue(null));
				//验证createMember()  memberService.getMemberIdUseSeq();
				allowing(memberService).getMemberIdUseSeq();
				will(returnValue("110110"));
				allowing(memberService).createMember(with(any(Member.class)));
				allowing(memberService).getMemberListByCellphone(with(any(String.class)));
				will(returnValue(null));
				allowing(memberService).getMemberListByLinkManNumber("nmlxr15012345678");
				will(returnValue(null));
				allowing(memberService).createMemberExtendInfo(with(any(MemberExtend.class)));
				allowing(memberService).queryBusinessOpportunityById("110110");
				will(returnValue(null));
			}
		});
		memberManager.setMemberService(memberService);
	}
	
	public void approvingWokflowDataServiceJMock() {
		Mockery appWflowDataServiceMockery = new Mockery();
		final IApprovingWokflowDataService approvingWokflowDataService = appWflowDataServiceMockery
				.mock(IApprovingWokflowDataService.class);
		appWflowDataServiceMockery.checking(new Expectations(){
			{
				//验证checkTaxregNumber()
				ApprovingWorkflowData workflowData_1 = new ApprovingWorkflowData();
				workflowData_1.setTaxregNumber("123456789");
				List<ApprovingWorkflowData> appList = new ArrayList<ApprovingWorkflowData>();
				appList.add(workflowData_1);
				allowing(approvingWokflowDataService).queryConWorkflowData(workflowData_1);
				will(returnValue(appList));
				ApprovingWorkflowData workflowData_2 = new ApprovingWorkflowData();
				workflowData_2.setTaxregNumber("12345678910");
				allowing(approvingWokflowDataService).queryConWorkflowData(workflowData_2);
				will(returnValue(appList));
				ApprovingWorkflowData workflowData_3 = new ApprovingWorkflowData();
				workflowData_3.setTaxregNumber("tx123");
				allowing(approvingWokflowDataService).queryConWorkflowData(workflowData_3);
				will(returnValue(null));
				//验证checkPhone()
				ApprovingWorkflowData workflowData_4 = new ApprovingWorkflowData();
				workflowData_4.setContactMobile("13012345678");
				allowing(approvingWokflowDataService).queryConWorkflowData(workflowData_4);
				will(returnValue(appList));
				ApprovingWorkflowData workflowData_5 = new ApprovingWorkflowData();
				workflowData_5.setContactMobile("13011");
				allowing(approvingWokflowDataService).queryConWorkflowData(workflowData_5);
				will(returnValue(null));
				ApprovingWorkflowData workflowData_6 = new ApprovingWorkflowData();
				workflowData_6.setContactMobile("1301213");
				allowing(approvingWokflowDataService).queryConWorkflowData(workflowData_6);
				will(returnValue(null));
				//验证checkNameAndTel()
				ApprovingWorkflowData workflowData_7 = new ApprovingWorkflowData();
				workflowData_7.setContactTel("021");
				workflowData_7.setContactName("aa");
				allowing(approvingWokflowDataService).queryConWorkflowData(workflowData_7);
				will(returnValue(null));
				ApprovingWorkflowData workflowData_8 = new ApprovingWorkflowData();
				workflowData_8.setContactTel("222");
				workflowData_8.setContactName("bb");
				allowing(approvingWokflowDataService).queryConWorkflowData(workflowData_8);
				will(returnValue(appList));
				ApprovingWorkflowData workflowData_9 = new ApprovingWorkflowData();
				workflowData_9.setContactTel("333");
				workflowData_9.setContactName("cc");
				allowing(approvingWokflowDataService).queryConWorkflowData(workflowData_9);
				will(returnValue(null));
				//验证isExistLinkManMuber()
				ApprovingWorkflowData workflowData_10 = new ApprovingWorkflowData();
				workflowData_10.setContactNum("aa021");
				allowing(approvingWokflowDataService).queryConWorkflowData(workflowData_10);
				will(returnValue(null));
				ApprovingWorkflowData workflowData_11 = new ApprovingWorkflowData();
				workflowData_11.setContactNum("cc333");
				allowing(approvingWokflowDataService).queryConWorkflowData(workflowData_11);
				will(returnValue(appList));
				//验证idCardCanUse()
				ApprovingWorkflowData workflowData_12 = new ApprovingWorkflowData();
				workflowData_12.setCredentialsType("OTHER");
				workflowData_12.setIdCardNo("55555");
				allowing(approvingWokflowDataService).queryConWorkflowData(workflowData_12);
				will(returnValue(null));
				ApprovingWorkflowData workflowData_13 = new ApprovingWorkflowData();
				workflowData_13.setCredentialsType("OTHER");
				workflowData_13.setIdCardNo("33333");
				allowing(approvingWokflowDataService).queryConWorkflowData(workflowData_13);
				will(returnValue(appList));
				//isMobileInApprove
				List<ApprovingWorkflowData> appWorkFlowList = new ArrayList<ApprovingWorkflowData>();
				ApprovingWorkflowData workflowData_14 = new ApprovingWorkflowData();
				workflowData_14.setContactMobile("15012345678");
				allowing(approvingWokflowDataService).queryConWorkflowData(workflowData_14);
				will(returnValue(appWorkFlowList));
				ApprovingWorkflowData workflowData_15 = new ApprovingWorkflowData();
				workflowData_15.setContactNum("nmlxr15012345678");
				allowing(approvingWokflowDataService).queryConWorkflowData(workflowData_15);
				will(returnValue(appWorkFlowList));
				allowing(approvingWokflowDataService).queryConWorkflowData(with(any(ApprovingWorkflowData.class)));
				will(returnValue(appWorkFlowList));
			}
		});
		memberManager.setApprovingWokflowDataService(approvingWokflowDataService);
	}
	/**
	 * 
	* @Title: test_searchMemberByContactWay
	* @Description: 测试searchMemberByContactWay（）方法
	* @author chenaichun 
	* @param     设定文件
	* @date 2014-3-24 下午7:10:02
	* @return void    返回类型
	* @throws
	* @update 2014-3-24 下午7:10:02
	 */
	@Test
	public void test_searchMemberByContactWay(){
		Method method;
		List<Member> memberList= new ArrayList<Member>();
		try {
			method = memberManager.getClass().getDeclaredMethod("searchMemberByContactWay",
					new Class[] {String.class,String.class,String.class});
			method.setAccessible(true);
			try {
				method.invoke(memberManager,null,null,null);
			} catch (IllegalArgumentException e) {
				assertEquals("参数不合法", e.getMessage());
			}
			try {
				memberList = (List<Member>)method.invoke(memberManager,"1301213",null,null);	
				Assert.assertTrue(memberList.size()==0);
				memberList = (List<Member>)method.invoke(memberManager,null,"027","cc");	
				Assert.assertTrue(memberList.get(0).getId().equals("111"));
			} catch (Exception e) {
				e.getMessage();
			}
			
		} catch (Exception e) {
			
		}
	}
	@Test
	public void test_checkTaxregNumber(){
		Method method;
		Boolean result;
		try {
			method = memberManager.getClass().getDeclaredMethod("checkTaxregNumber", new Class[] {String.class});
			method.setAccessible(true);
			try {
				result = (Boolean)method.invoke(memberManager, "tx123");
				assertTrue(result);
			} catch (Exception e) {
				e.getMessage();
			}
			try {
				result = (Boolean)method.invoke(memberManager, "0123456789");
			} catch (MemberException e) {
				assertEquals(MemberExceptionType.TaxregnumerMemberExist,e.getErrorCode());
			}
			try {
				method.invoke(memberManager, "01234567890");
			} catch (MemberException e) {
				assertEquals(MemberExceptionType.RegistrationMemberExist, e.getErrorCode());
			}
			try {
				method.invoke(memberManager, "123456789");
			} catch (MemberException e) {
				assertEquals(MemberExceptionType.TaxregnumerMemberExamin, e.getErrorCode());
			}
			try {
				method.invoke(memberManager, "12345678910");
			} catch (MemberException e) {
				assertEquals(MemberExceptionType.RegistrationNumerMemberExamin, e.getErrorCode());
			}
			
		} catch (Exception e) {
			
		}
	}
	@Test
	public void test_checkPhone(){
		Method method;
		try {
			method = memberManager.getClass().getDeclaredMethod("checkPhone", String.class);
			method.setAccessible(true);
			Boolean result = (Boolean)method.invoke(memberManager, "1301213");
			assertTrue(result);
			try {
				method.invoke(memberManager, "13012345678");
			} catch (MemberException e) {
				assertEquals(MemberExceptionType.PhoneMemberExamin, e.getErrorCode());
			}
			try {
				method.invoke(memberManager, "13011");
			} catch (MemberException e) {
				assertEquals(MemberExceptionType.PhoneMemberExamin, e.getErrorCode());
			}
			
		}catch (Exception e) {
		}
	}
	@Test
	public void test_checkTelAndName(){
		Method method;
		try {
			method = memberManager.getClass().getDeclaredMethod("checkTelAndName", new Class[]{String.class,String.class});
			method.setAccessible(true);
			Boolean result = (Boolean)method.invoke(memberManager, "021","aa");
			assertTrue(result);
			try {
				method.invoke(memberManager, "222","bb");
			} catch (MemberException e) {
				assertEquals(MemberExceptionType.TelAndNameMemberExamin, e.getErrorCode());
			}
			try {
				method.invoke(memberManager, "333","cc");
			} catch (MemberException e) {
				assertEquals(MemberExceptionType.TelAndNameMemberExist, e.getErrorCode());
			}
		}catch (Exception e) {
		}
	}
	@Test
	public void test_isExistLinkManMuber(){
		Method method;
		Boolean flag;
		try {
			method = memberManager.getClass().getDeclaredMethod("isExistLinkManMuber", String.class);
			method.setAccessible(true);
			flag = (Boolean) method.invoke(memberManager, "aa021");
			assertFalse(flag);
			try {
				method.invoke(memberManager, "bb222");
			} catch (MemberException e) {
				assertEquals(MemberExceptionType.LinkManMuberMemberExist, e.getErrorCode());
			}
			try {
				method.invoke(memberManager, "cc333");
			} catch (MemberException e) {
				assertEquals(MemberExceptionType.LinkManMuberMemberExamin, e.getErrorCode());
			}
		
		}catch(Exception e){
			
		}
	}
	@Test
	public void test_isContactExist(){
		Contact con = new Contact();
		Method method;
		Boolean flag;
		try {
			method = memberManager.getClass().getDeclaredMethod("isContactExist", Contact.class);
			method.setAccessible(true);
			try {
				method.invoke(memberManager, con);
			} catch (IllegalArgumentException e) {
				assertEquals("手机，姓名，电话，联系人编码,值不全,参数调用不合法", e.getMessage());
			}
			//不存在
			con.setMobilePhone("1301213");
			con.setTelPhone("021");
			con.setName("aa");
			con.setNumber("aa021");
			flag = (Boolean)method.invoke(memberManager, con);
			assertFalse(flag);
			//存在
			con.setNumber("cc333");
			flag = (Boolean)method.invoke(memberManager, con);
			assertTrue(flag);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void test_isContactsAllNotExist(){
		Method method;
		Boolean flag;
		try {
			method = memberManager.getClass().getDeclaredMethod("isContactsAllNotExist", List.class);
			method.setAccessible(true);
			List conList = new ArrayList<Contact>();
			Contact con = new Contact();
			con.setMobilePhone("1301213");
			con.setTelPhone("021");
			con.setName("aa");
			con.setNumber("aa021");
			conList.add(con);
			flag = (Boolean) method.invoke(memberManager, conList);
			assertTrue(flag);
			Contact con_2 = new Contact();
			con_2.setNumber("cc333");
			conList.add(con_2);
			flag = (Boolean) method.invoke(memberManager, conList);
			assertFalse(flag);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void test_idCardCanUse(){
		Method method;
		Boolean flag;
		try {
			method = memberManager.getClass().getDeclaredMethod("idCardCanUse", new Class[]{});
			method.setAccessible(true);
			flag = (Boolean) method.invoke(memberManager, "55555","OTHER");
			assertTrue(flag);
			try {
				method.invoke(memberManager, "421181195606255453","SECOND_GENERATION_IDCARD");
			} catch (MemberException e) {
				assertEquals(MemberExceptionType.CardMemberExist, e.getErrorCode());
			}
			try {
				method.invoke(memberManager, "33333","OTHER");
			} catch (MemberException e) {
				assertEquals(MemberExceptionType.CardMemberExamin, e.getErrorCode());
			}
		}catch(Exception e){
			
		}
	}
	@Test
	public void test_validateCreateMember(){
		Member member = new Member();
		member.setCustType(Constant.Enterprise_Member);
		member.setCustGroup(Constant.CUST_GROUP_RC_CUSTOMER);
		member.setTaxregNumber("tx123");
		List conList = new ArrayList<Contact>();
		Contact con = new Contact();
		con.setMobilePhone("1301213");
		con.setTelPhone("021");
		con.setName("aa");
		con.setNumber("aa021");
		conList.add(con);
		member.setContactList(conList);
		memberManager.validateCreateMember(member);
		con.setIsMainLinkMan(true);
		con.setIdCard("55555");
		con.setCardTypeCon("OTHER");
		member.setCustType(Constant.Person_Member);
		memberManager.validateCreateMember(member);
		con.setIdCard("");
		con.setCardTypeCon("");
		
		try {
			memberManager.validateCreateMember(member);
		} catch (MemberException e) {
			assertEquals("i18n.member.IdCardNull",e.getErrorCode());
		}
		
	}
	
	@Test
	public void test_createPotentialCustomer() {
		
		Contact con = new Contact();
		con.setName("你妹");
		con.setMobilePhone("13012131415");
		List<Contact> contactList = new ArrayList<Contact>();
		
		member.setCustGroup(Constant.CUST_GROUP_PC_CUSTOMER);
		member.setCustName("你妹");
		member.setCustType(Constant.Person_Member);
		member.setCustNature(Constant.LEAVE_ARRIVE_CUSTOMER);
		member.setDeptId("92974");
		member.setProvinceId("123");
		member.setCityId("123");
		member.setCustCategory(Constant.CUST_BUSTYPE_LINGDAN);
		member.setIsKeyCustomer(false);
		member.setIsImportCustor(false);
		member.setIsSpecial(false);
		member.setIsFocusPay(false);
		member.setIsRedeempoints(false);
		member.setIsParentCompany(true);
		member.setRegistAddress("上海-上海");
		member.setChannelSource(Constant.CUST_SOURCE_STRANGER);
		con.setNumber("aa021");
		con.setLinkmanType("0,1,0,0,0");
		con.setCardTypeCon("OTHER");
		con.setIdCard("55555");
		con.setIsMainLinkMan(true);
		con.setSex("female");
		contactList.add(con);
		try{
			memberManager.createPotentialCustomer(member);
		}catch(CustomerException ce){
			assertEquals("i18n.customer.contactExitsCustomer",ce.getErrorCode());
		}
		member.setContactList(contactList);
		memberManager.createPotentialCustomer(member);
		
	}
	
	public void contactManagerJMock() {
		Mockery contactManagerMockery = new Mockery();
		final IContactManager contactManager = contactManagerMockery
				.mock(IContactManager.class);
		contactManagerMockery.checking(new Expectations() {
			{
				List<Contact> contactLists = new ArrayList<Contact>();
				//查不出来为空的情况
				allowing(contactManager).searchContactList("15012345678", "", "");
				will(returnValue(null));
				allowing(contactManager).boundContactForOnline(with(any(RegisterInfo.class)), with(any(String.class)));
			}
		});
		memberManager.setContactManager(contactManager);

	}
	@Test
	public void testGet(){
		memberManager.getAlterMemberService();
		memberManager.getApprovingWokflowDataService();
		memberManager.getBaseDataManager();
		memberManager.getChangeMemberDeptManager();
		memberManager.getContactManager();
		memberManager.getContactService();
		memberManager.getContractManager();
		memberManager.getWaybillOperate();
		memberManager.getMessageBundle();
		memberManager.getMemberWorkFlowManager();
		memberManager.getCustLabelManager();
		memberManager.getChangeMemberDeptManager();
		memberManager.getCustLabelManager();
		memberManager.getCustomerOperate();
	}
	
	@Test
	public void test_createChannelCutomer(){
		ChannelCustomer cust = new ChannelCustomer();
		try {
			memberManager.createChannelCutomer(cust);
		} catch (CustomerException e) {
			assertEquals("i18n.customer.ChanenCustomerDataNull",e.getErrorCode());
		}
		cust.setChannelSource("notCCorOWS");
		try {
			memberManager.createChannelCutomer(cust);
		} catch (CustomerException e) {
			assertEquals("i18n.customer.ChanenCustomerDataNull",e.getErrorCode());
		}
		//创建官网
		cust.setChannelSource(Constant.CUST_SOURCE_OWS);
		cust.setMobilePhone("15012345678");
		cust.setTelPhone("");
		cust.setLinkManName("");
		memberManager.createChannelCutomer(cust);
		cust.setChannelSource(Constant.CUST_SOURCE_CC);
		cust.setMobilePhone("15012345678");
		cust.setTelPhone("");
		cust.setLinkManName("");
		List<Contact> contactList = new ArrayList<Contact>();
		Contact contact = new Contact();
		contact.setIsMainLinkMan(true);
		contact.setMobilePhone("13113113111");
		contact.setTelPhone("");
		contact.setName("XX");
		contact.setNumber("123");
		contactList.add(contact);
		cust.setContactList(contactList);
		memberManager.createChannelCutomer(cust);
	}
}
