package com.deppon.crm.module.customer.server.manager;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.customer.server.testutils.DataTestUtil;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.ChannelCustomer;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberDemotionList;
import com.deppon.crm.module.customer.shared.domain.MemberUpgradeList;
import com.deppon.crm.module.customer.shared.domain.ShuttleAddress;
import com.deppon.crm.module.customer.shared.domain.UpGradeCustomer;
import com.deppon.crm.module.customer.shared.domain.UpGradeCustomerCondition;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.customer.shared.exception.MemberExceptionType;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;
import com.deppon.foss.framework.server.cache.message.MessageCache;
import com.deppon.foss.framework.server.context.UserContext;

public class MemberManagerTest extends BeanUtil{
	
	@Before
	public void setUp() throws Exception{
		clearDataForSetUp();
		DataTestUtil.initAlterMemberData();
	}
	
	@After
	public void tearDown() throws Exception{
		clearData();
		DataTestUtil.clearAlterMemberData();
		CacheManager.getInstance().shutdown();
	}
	

	@Test
	public void test_createMember_1() throws SQLException{
		User user = new User();
		Department depart = new Department();
		depart.setId("432658");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee emp = new Employee();
		emp.setDeptId(depart);
		emp.setId("23567");
		user.setEmpCode(emp);
		UserContext.setCurrentUser(user);
		
		
		Member member = new Member();
		member.setCustGroup(Constant.CUST_GROUP_RC_CUSTOMER);
		member.setCustName("客户姓名");
		member.setCustNumber("S-20121225-12345");
		member.setCustType(Constant.Enterprise_Member);
		member.setDegree("GOLD");
		member.setCustNature("custNature");
		member.setTradeId("tradeId");
		member.setProvinceId("2");
		member.setCityId("3");
		member.setCity("上海市");
		member.setIsSpecial(false);
		member.setIsImportCustor(true);
		member.setIsParentCompany(true);
		member.setIsRedeempoints(false);
		member.setIsFocusPay(true);
		member.setFocusDeptId("4012");
		member.setProcRedit(3.2d);
		member.setTaxregNumber("441900690504539");
		member.setCompanyProperty("companyProperty");
		member.setBecomeMemTime(new Date());
		List<Contact> contactList = new ArrayList<Contact>();
		Contact contact = new Contact();
		contact.setName("name");
		contact.setNumber("1236987897");
		contact.setLinkmanType("l,ink,ma,nTyp,e");
	    contact.setIsMainLinkMan(true);
	    contact.setSex("male");
	    contact.setMobilePhone("13854561111");
		
		contactList.add(contact);
		member.setContactList(contactList);
		
		List<Account>accountList = new ArrayList<Account>();
		Account account = new Account();
		account.setAccountUse("324");
		account.setFinanceLinkman("name");
		account.setFinanceLinkmanId("2437563242");
		account.setContactType("contactType");
		account.setBank("中国建设银行");
		account.setBankAccount("4392258321334784");
		account.setBankArea("高新区");
		account.setBankCityId("0551");
		account.setBankCityName("合肥市");
		account.setBankId("3225");
		account.setBankProvinceId("2");
		account.setBankProvinceName("安徽省");
		account.setSubBankname("上海建设银行常德路支行");
		account.setSubBanknameId("1233563");
		account.setIsdefaultaccount(true);
		account.setCountName("countName");
		account.setRelation("relation");
		account.setAccountNature("accountNature");
		account.setLinkManMobile("13854561111");
		
		accountList.add(account);

		
		member.setAccountList(accountList);
		
		
		List<ShuttleAddress>shuttleAddressList = new ArrayList<ShuttleAddress>();
		
		member.setShuttleAddressList(shuttleAddressList);

		UpGradeCustomer upGrade = new UpGradeCustomer();
		
		upGrade.setTargetLevel("targetLevel");
		upGrade.setCustName("custName");
		upGrade.setContactTel("02165895698");
		upGrade.setContactPhone("");
		upGrade.setContactName("contactName");
		upGrade.setBelongDeptId("1");
		upGrade.setBelongdeptName("测试");
		
//		memberManager.createMember(member, upGrade);
		
		clearCreateMemberData(member);

	}
	
	@Test
	public void test_createMember_2() throws SQLException{
		User user = new User();
		Department depart = new Department();
		depart.setId("432658");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee emp = new Employee();
		emp.setDeptId(depart);
		emp.setId("23567");
		user.setEmpCode(emp);
		UserContext.setCurrentUser(user);
		
		
		Member member = new Member();
		member.setCustType(Constant.CUST_GROUP_RC_CUSTOMER);
		member.setCustName("客户姓名");
		member.setCustNumber("S-20121225-12345");
		member.setCustType(Constant.Enterprise_Member);
		member.setDegree("GOLD");
		member.setCustNature("custNature");
		member.setTradeId("tradeId");
		member.setProvinceId("2");
		member.setCityId("3");
		member.setCity("上海市");
		member.setIsSpecial(false);
		member.setIsImportCustor(true);
		member.setIsParentCompany(true);
		member.setIsRedeempoints(false);
		member.setIsFocusPay(true);
		member.setFocusDeptId("4012");
		member.setProcRedit(3.2d);
		member.setTaxregNumber("441900690504539");
		member.setCompanyProperty("companyProperty");
		member.setBecomeMemTime(new Date());
		List<Contact> contactList = new ArrayList<Contact>();
		Contact contact = new Contact();
		contact.setName("name");
		contact.setNumber("1236987897");
		contact.setLinkmanType("l,ink,ma,nTyp,e");
	    contact.setIsMainLinkMan(true);
	    contact.setSex("male");
	    contact.setMobilePhone("13854561111");
		
		contactList.add(contact);
		member.setContactList(contactList);
		
		List<Account>accountList = new ArrayList<Account>();
		Account account = new Account();
		account.setAccountUse("324");
		account.setFinanceLinkman("name");
		account.setFinanceLinkmanId("2437563242");
		account.setContactType("contactType");
		account.setBank("中国建设银行");
		account.setBankAccount("4392258321334784");
		account.setBankArea("高新区");
		account.setBankCityId("0551");
		account.setBankCityName("合肥市");
		account.setBankId("3225");
		account.setBankProvinceId("2");
		account.setBankProvinceName("安徽省");
		account.setSubBankname("上海建设银行常德路支行");
		account.setSubBanknameId("1233563");
		account.setIsdefaultaccount(true);
		account.setCountName("countName");
		account.setRelation("relation");
		account.setAccountNature("accountNature");
		account.setLinkManMobile("13854561231");
		
		accountList.add(account);

		
		member.setAccountList(accountList);
		
		
		List<ShuttleAddress>shuttleAddressList = new ArrayList<ShuttleAddress>();
		
		member.setShuttleAddressList(shuttleAddressList);

		UpGradeCustomer upGrade = new UpGradeCustomer();
		
		upGrade.setTargetLevel("targetLevel");
		upGrade.setCustName("custName");
		upGrade.setContactTel("02165895698");
		upGrade.setContactPhone("");
		upGrade.setContactName("contactName");
		upGrade.setBelongDeptId("1");
		upGrade.setBelongdeptName("测试");
		try{
		memberManager.createMember(member, upGrade);
		Assert.fail();
		}catch(MemberException me){
//			Assert.assertEquals(MemberExceptionType.NoAccountFind.getErrCode(), me.getErrorCode());
		}
		
		clearCreateMemberData(member);

	}
	
	
	
	

	@Test
	public void test_createMember_3() throws SQLException{
		User user = new User();
		Department depart = new Department();
		depart.setId("432658");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee emp = new Employee();
		emp.setDeptId(depart);
		emp.setId("23567");
		user.setEmpCode(emp);
		UserContext.setCurrentUser(user);
		
		
		Member member = new Member();
		member.setCustType(Constant.CUST_GROUP_RC_CUSTOMER);
		member.setCustName("客户姓名");
		member.setCustNumber("S-20121225-12345");
		member.setCustType(Constant.Enterprise_Member);
		member.setDegree("GOLD");
		member.setCustNature("custNature");
		member.setTradeId("tradeId");
		member.setProvinceId("2");
		member.setCityId("3");
		member.setCity("上海市");
		member.setIsSpecial(false);
		member.setIsImportCustor(true);
		member.setIsParentCompany(true);
		member.setIsRedeempoints(false);
		member.setIsFocusPay(true);
		member.setFocusDeptId("4012");
		member.setProcRedit(3.2d);
		member.setTaxregNumber("441900690504539");
		member.setCompanyProperty("companyProperty");
		member.setBecomeMemTime(new Date());
		List<Contact> contactList = new ArrayList<Contact>();
		Contact contact = new Contact();
		contact.setName("name");
		contact.setNumber("1236987897");
		contact.setLinkmanType("linkmanType");
	    contact.setIsMainLinkMan(true);
	    contact.setSex("male");
	    contact.setMobilePhone("13854561111");
		
		contactList.add(contact);
		member.setContactList(contactList);
		
		List<Account>accountList = new ArrayList<Account>();
		
		
		member.setAccountList(accountList);
		
		
		List<ShuttleAddress>shuttleAddressList = new ArrayList<ShuttleAddress>();
		
		member.setShuttleAddressList(shuttleAddressList);

		UpGradeCustomer upGrade = new UpGradeCustomer();
		
		upGrade.setTargetLevel("targetLevel");
		upGrade.setCustName("custName");
		upGrade.setContactTel("");
		upGrade.setContactPhone("13538654173");
		upGrade.setContactName("");
		upGrade.setBelongDeptId("1");
		upGrade.setBelongdeptName("测试");
		
//		memberManager.createMember(member, upGrade);
		
		clearCreateMemberData(member);

	}
	
	

	/***
	 * 
	 * <p>
	 * Description:测试创建实施会员<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-21
	 * void
	 * @throws CrmBusinessException 
	 */

	@Test
	public void test_initImplementMember() throws CrmBusinessException{

//		ImplementMemberView imv = memberManager.initImplementMember("13589652365", "", "客户关系管理");
//		Assert.assertNotNull(imv);
	
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试初始化特殊会员信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-21
	 * void
	 * @throws SQLException 
	 */

	@Test
	public void test_initSepcailMember() throws SQLException{
//		deleteMemberFirst();
//		User user = new User();
//		Set<String> roleidSet = new TreeSet<String>();
//		roleidSet.add(Constant.BizManager);
//		user.setRoleids(roleidSet);
//		
//		Employee e = new Employee();
//		e.setId("1");
//		Department d = new Department();
//		d.setId("1");
//		e.setDeptId(d);
//		user.setEmpCode(e);
//		UserContext.setCurrentUser(user);
//		
//		Member member = memberManager.initSepcailMember();
//		Assert.assertEquals(true, member.getIsSpecial());
//		
//		
//		member.setDegree("degree");
//		member.setCustName("custName");
//		member.setCustType(Constant.Person_Member);
//		member.setCustNature("custNature");
//		member.setTradeId("tradeId");
//		member.setProvinceId("3");
//		member.setCityId("3");
//		member.setProcRedit(3.21d);
//		Contact contact = new Contact();
//		contact.setMobilePhone("13589654365");
//		contact.setName("name");
//		contact.setNumber("1224543657");
//		contact.setLinkmanType("linkmainType");
//		contact.setIsMainLinkMan(true);
//		contact.setSex("man");
//		contact.setIdCard("341221198710025338");
//		contact.setCardTypeCon("二代居民身份证");
//        List<Contact> contactList = new ArrayList<Contact>();
//        contactList.add(contact);
//		member.setContactList(contactList);
//		member.setAccountList(new ArrayList<Account>());
//		member.setShuttleAddressList(new ArrayList<ShuttleAddress>());
//		String workFlowId = memberManager.createSpecialMember(member);
//		Assert.assertNotNull(workFlowId);
//		
//		
//		deleteMember(member);
		
	}
	
	/***
	 * 
	 * <p>
	 * Description:测试会员升级添加备注<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-24
	 * void
	 */
  
	public void test_addUpGradeCustomerRemark(){
//		User user = new User();
//		user.setId("1");
//		Employee e = new Employee();
//		e.setId("860301");
//		user.setEmpCode(e);
//		UserContext.setCurrentUser(user);
//		
//		String remark = "单元测试添加备注";
//		String upGradeId = "400785871";
//		memberManager.addUpGradeCustomerRemark(upGradeId, remark);
//		
//		UpGradeCustomer  ucc = memberManager.getUpGradeCustomerById(upGradeId);
//		Assert.assertEquals(remark, ucc.getRemark());
//		Assert.assertEquals(upGradeId,ucc.getId());
	}
	/**
	 * 
	 * <p>
	 * Description:测试查询会员升级列表<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-24
	 * void
	 */

	@Test
	public void test_searchUpGradeCustomerList(){
		UpGradeCustomerCondition ucc = new UpGradeCustomerCondition();
		ucc.setPhone("13596376927");
		//ucc.setDept("1");
		User user = new User();
		Set<String> roleidSet = new TreeSet<String>();
		roleidSet.add(Constant.BizManager);
		user.setRoleids(roleidSet);
		
		Employee e = new Employee();
		e.setId("1");
		Department d = new Department();
		d.setId("49082");
		e.setDeptId(d);
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
		
		List<MemberUpgradeList> uccList = memberManager.searchMemberUpgradeList(ucc, 0, 20);
	}
	/**
	 * 
	 * <p>
	 * Description:测试初始化会员信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-21
	 * void
	 */
	@Test
	public void test_initMember(){
		UpGradeCustomer upGrade = new UpGradeCustomer();
		
		upGrade.setTargetLevel("targetLevel");
		upGrade.setCustName("custName");
		upGrade.setContactTel("02165895698");
		upGrade.setContactPhone("13538654173");
		upGrade.setContactName("contactName");
		upGrade.setBelongDeptId("1");
		upGrade.setBelongdeptName("测试");
		try{
		memberManager.initMember(upGrade);
		}catch(MemberException me){
			Assert.assertEquals(MemberExceptionType.TargetLevelNotExist.getErrCode(), me.getErrorCode());
		}

	}
	
	@Test
	public void test_initMember_1() throws SQLException{
		init_initMember();
		UpGradeCustomer upGrade = new UpGradeCustomer();
		
		upGrade.setTargetLevel("targetLevel");
		upGrade.setCustName("custName");
		upGrade.setContactTel("02165895698");
		upGrade.setContactPhone("13538654173");
		upGrade.setContactName("contactName");
		upGrade.setBelongDeptId("10410");
		upGrade.setBelongdeptName("'广州黄埔营业部");
		
//		memberManager.initMember(upGrade);
	

	}
	
	private void init_initMember() throws SQLException{
		
		List<String> sqlList = new ArrayList<String>();
		sqlList.add("insert into t_cust_upgradelist(fid,fcreatetime,fbelongdeptid,fcurrentlevel,ftargetlevel,"+
	    	      " fstatus,fcontacttel,fcontactphone,fcontactname,fcustname,fbelongdeptname)"+
	    	    " values(94738572398,sysdate,10410,'SCATTER_GRADE','NORMAL',0,'02165895698','13538654173','contactName','contactName','广州黄埔营业部')");
		
		SpringTestHelper.executeBatch(sqlList);
	}
	
	
	
	/**
	 * 
	 * <p>
	 * Description:测试升级会员<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-24
	 * void
	 */
	@Test
	public void test_upgrageMember(){
		
		User user = new User();
		user.setId("1");
		Employee e = new Employee();
		e.setId("860301");
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
		
		MemberUpgradeList memberUpgrade = new MemberUpgradeList();
		String custNumber = "091556";
		memberUpgrade.setCustNumber(custNumber);
		// 当前等级
		memberUpgrade.setCurrentLevel("NORMAL");
		// 目标等级
		memberUpgrade.setTargetLevel("GOLD");

		memberManager.upgrageMember(memberUpgrade);
		
		memberUpgrade.setCurrentLevel(Constant.CustLevel_Demotion);
		memberManager.upgrageMember(memberUpgrade);
		
		List<MemberUpgradeList> upgradeList = new ArrayList<MemberUpgradeList>();
		memberManager.upgrageMember(upgradeList);
		upgradeList.add(memberUpgrade);
		memberManager.upgrageMember(upgradeList);
		
		Member member = memberManager.getMemberByCustNumber(custNumber);
		Assert.assertEquals(custNumber,member.getCustNumber());
		Assert.assertEquals("GOLD",member.getDegree());

	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-1-28
	 * void
	 */
	@Test
	public void test_searchMemberDemotionList(){
		User user = new User();
		Department depart = new Department();
		depart.setId("432658");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		user.setEmpCode(e);
		
		Set<String>deptids = new TreeSet<String>();
		deptids.add("3257554");
		user.setDeptids(deptids);
		
		Set<String>roleids = new TreeSet<String>();
		roleids.add("654365");
		user.setDeptids(deptids);
		user.setRoleids(roleids);
		UserContext.setCurrentUser(user);
		
		
		UpGradeCustomerCondition condition = new UpGradeCustomerCondition();
		condition.setDept("125862");
		try{
		memberManager.searchMemberDemotionList(condition,0,20);
		}catch(MemberException me){
			Assert.assertEquals(MemberExceptionType.NoAuthority.getErrCode(), me.getErrorCode());
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试查询会员升级列表<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-24
	 * void
	 */
	@Test
	public void test_getMemberUpgrage(){
		
		List<MemberUpgradeList> memberUpgradeList = memberManager.getMemberUpgrage("2012-12-24");
	    Assert.assertEquals(0, memberUpgradeList.size());
	}
	
	/**
	 * 测试根据会员ID查询3个联系人信息
	 * */

	@Test
	public void test_get3ContactByOrder(){
		memberManager.get3ContactByOrder("");
		
		List<Contact> contactList = memberManager.get3ContactByOrder("1638");
		Assert.assertEquals(3, contactList.size());
		Assert.assertEquals(false, contactList.get(0).getIsMainLinkMan());
		
	}
	
	// 根据手机号码获取会员信息
	
	@Test
	public void test_getMemberByPhone(){
		Member member = memberManager.getMemberByPhone("18889930000");
		Assert.assertNull(member);
		
		member = memberManager.getMemberByPhone("13560850542");
		Assert.assertNotNull(member);
	}
	
	@Test
	public void test_disposeSpecialMemberByExamineResult(){
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
		memberManager.disposeSpecialMemberByExamineResult("338765434", false);
		
		
		memberManager.disposeSpecialMemberByExamineResult("338765434", true);
		
	}
	@Test
	public void test_getMemberIdListBydeptId(){
		
		memberManager.getMemberIdListBydeptId("2367654");
		
		memberManager.getMemberIdListBydeptId("406225");
		
		memberManager.getMemberIdListBydeptIds(new String[]{});
		
		memberManager.getMemberIdListBydeptIds(new String[]{"406225"});
		
	}
      
	@Test
	public void test_demotionMember(){
		
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
		
		List<MemberDemotionList> demotionList = new ArrayList<MemberDemotionList>();
		
		memberManager.demotionMember(demotionList);
		
		MemberDemotionList mdl = new MemberDemotionList();
		
		mdl.setCustNumber("349876");
		mdl.setCurrentLevel(Constant.CustLevel_Gold);
		mdl.setTargetLevel(Constant.CustLevel_Demotion);

		demotionList.add(mdl);
		
        mdl = new MemberDemotionList();
		mdl.setCustNumber("349876");
		mdl.setCurrentLevel(Constant.CustLevel_Gold);
		mdl.setTargetLevel(Constant.CustLevel_Gold);

		demotionList.add(mdl);
		memberManager.demotionMember(demotionList);
		
	}
	
	@Test
	public void test_check(){
		memberManager.checkMobilePhoneCanUse("18659874512");
		memberManager.checkTelAndNameCanUse("wmm","18659874512");
		
		memberManager.checkIdCardCanUse("341221198710025338",Constant.Person_Member,false,"cardTypeCon");
		memberManager.checkIdCardCanUse("341221198710025338",Constant.Person_Member,true,"cardTypeCon");

		memberManager.checkIdCardCanUse("341221198710025338",Constant.CONTACT_CUSTOMER,true,"cardTypeCon");
		memberManager.checkIdCardCanUse("341221198710025338",Constant.CONTACT_CUSTOMER,false,"cardTypeCon");
		
		memberManager.isContractMember("234578900");
		
	    memberManager.isContractMember("27804");
	    
	    
	    memberManager.checkLinkManNumberCanUse("2534567865");
		
	}
	@Test
	public void test_getMemberByCustNumber(){
		
		memberManager.getMemberByCustNumber("");
		
		memberManager.getMemberByCustNumber("236480965");
		
		memberManager.getMemberByCustNumber("400197735");
		
	}
	
	@Test
	public void test_disposeChangeMemberDeptByExamineResult(){
		
		memberManager.disposeChangeMemberDeptByExamineResult("1234555", "400005849", true);
		
		memberManager.disposeChangeMemberDeptByExamineResult("1234556", "400005849", false);
		
	}
	
	@Test
	public void test_getMemberInfoByTelAndName(){
		
		memberManager.getMemberInfoByTelAndName("021-876434576", "wmm");
		
		memberManager.getMemberInfoByTelAndName("0755-29890606", "徐宏成");
		
	}
	
	@Test
	public void test_addMonthlyStatement() throws SQLException{
		init_addMonthlyStatement();
		
		User user = new User();
		Department depart = new Department();
		depart.setId("432658");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		e.setId("23567");
		user.setEmpCode(e);
	
		UserContext.setCurrentUser(user);
        try{
		memberManager.addMonthlyStatement("14356546", 32463);
		Assert.fail("未报出异常");
		}catch(MemberException me){
			Assert.assertEquals(MemberExceptionType.MonthlyStatementOver.getErrCode(), me.getErrorCode());
		}
        memberManager.addMonthlyStatement("14356546", 1000);
	}
	

	
	@Test
	public void test_reduceMonthlyStatement() throws SQLException{
	   init_addMonthlyStatement();
		
		User user = new User();
		Department depart = new Department();
		depart.setId("432658");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		e.setId("23567");
		user.setEmpCode(e);
	
		UserContext.setCurrentUser(user);
        try{
		memberManager.reduceMonthlyStatement("14356546", 100000);
		Assert.fail("未报出异常");
		}catch(RuntimeException me){
			Assert.assertEquals("月结额度不能小于0", me.getMessage());
		}
        memberManager.reduceMonthlyStatement("14356546", 1000);
	}
	
	@Test
	public void testUpdateDevelopmentLog(){
		//测试传入的extend为空
		try {
			memberManager.updateExtendAndLog(null,null,null);
			Assert.fail("第二个参数为空时，未抛出正常的业务异常，这不科学");
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(), MemberExceptionType.DEVELOPDATANULL.getErrCode());
		}
	}
	
	
	
	
	
	private void init_addMonthlyStatement() throws SQLException{
		List<String> sqlList = new ArrayList<String>();
		sqlList.add(" insert into t_cust_membermonthlyStatement(fid,Fcreatetime,Fcreateuserid,Fmemberid,Fmonthlystatement)"+
		    " values(675432124356,sysdate,365432,14356546,90000)");
		
		SpringTestHelper.executeBatch(sqlList);
	}
	
	
	private void clearData() throws SQLException{
		List<String> sqlList = new ArrayList<String>();
		sqlList.add(" delete t_cust_membermonthlyStatement where fid = 675432124356");
		sqlList.add(" delete t_cust_custbasedata t where t.ftaxregnumber='441900690504539'");
		sqlList.add(" delete t_cust_upgradelist where fid = 94738572398");
		sqlList.add("delete from t_cust_custbasedata cu where cu.fcustnumber = '10001001'");
		sqlList.add("delete from t_cust_custlinkman t where t.fnumber = '10001001'");
		SpringTestHelper.executeBatch(sqlList);
	}
	
	private void clearDataForSetUp() throws SQLException{
		List<String> sqlList = new ArrayList<String>();
		sqlList.add(" delete from t_cust_custlinkman t where t.fnumber = '1236987897'");
		sqlList.add("delete from t_cust_custbasedata cu where cu.fcustnumber = '1000110001'");
		sqlList.add("delete from t_cust_custlinkman t where t.fnumber = '1000110001'");
		SpringTestHelper.executeBatch(sqlList);
	}
	
	
	@SuppressWarnings("unchecked")
	public void test_createModifyMemberExaminView(){
		// TODO 逻辑太复杂 暂时放弃  wmm
	    ICache cache =  new MessageCache();
//		CacheManager.getInstance().registerCacheProvider(cache);
//		memberManager.createModifyMemberExaminView("23456",400005849l);
	}


	
	@Test
	public void testGetContactsByMemberId(){
		memberManager.getContactsByMemberId("");
		
	    List<Contact> list = (ArrayList<Contact>) memberManager.getContactsByMemberId("15451");
//	    Assert.assertEquals(5, list.size());
	}
	
	@Test
	public void testGetContactDetailInfoById(){
		 memberManager.getContactDetailInfoById("");
		
		Contact c = new Contact();
		c = memberManager.getContactDetailInfoById("4821");
		Assert.assertNotNull(c);
	}
	

	@Test
	public void testGetMemberByCustNumber(){
		String string = "40000179";
		memberManager.getMemberByCustNumber(string);
	}
	@Test
	public void testChangeCustFinStatus(){
		memberManager.changeCustFinStatus();
	}
	
	private void clearCreateMemberData(Member member) throws SQLException{
		List<String> sqlList = new ArrayList<String>();
		sqlList.add("delete t_cust_custlinkman t where t.fmobiletel='"+member.getContactList().get(0).getMobilePhone()+"'");
		sqlList.add("delete t_cust_custbasedata t where t.fid = "+member.getId());
		if(member.getAccountList().size()>0){
			sqlList.add("delete t_cust_account where FBANKACCOUNT = "+member.getAccountList().get(0).getBankAccount());
		}
		SpringTestHelper.executeBatch(sqlList);
	}

     // 删除清除数据
	private void deleteMember(Member member) throws SQLException{
		List<String> sqlList = new ArrayList<String>();
		sqlList.add("delete t_cust_custlinkman t where t.fmobiletel='"+member.getContactList().get(0).getMobilePhone()+"'");
		sqlList.add("delete t_cust_custbasedata t where t.fcustname = '"+member.getCustName()+"'");
		SpringTestHelper.executeBatch(sqlList);
	}

	   // 删除清除数据
		private void deleteMemberFirst() throws SQLException{
			List<String> sqlList = new ArrayList<String>();
			sqlList.add("delete from t_cust_custlinkman t where t.fidcard = '430682199001155759'");
			SpringTestHelper.executeBatch(sqlList);
		}
		
		@Test
		public void testCreateChannelCustomer(){
			//创建官网
			ChannelCustomer cust = new ChannelCustomer();
			cust.setChannelSource(Constant.CUST_SOURCE_OWS);
			cust.setMobilePhone("15012345678");
			cust.setCustName("sj15012345678");
			cust.setEmail("xxx@232.com");
			cust.setTelPhone("5656564");
			cust.setLinkManName("徐小小");
			memberManager.createChannelCutomer(cust);
//			cust.setChannelSource(Constant.CUST_SOURCE_CC);
//			cust.setMobilePhone("15012345678");
//			cust.setTelPhone("");
//			cust.setLinkManName("");
//			List<Contact> contactList = new ArrayList<Contact>();
//			Contact contact = new Contact();
//			contact.setIsMainLinkMan(true);
//			contact.setMobilePhone("13113113111");
//			contact.setTelPhone("");
//			contact.setName("XX");
//			contact.setNumber("123");
//			contactList.add(contact);
//			cust.setContactList(contactList);
//			memberManager.createChannelCutomer(cust);
		}
}
