package com.deppon.crm.module.customer.server.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.testutils.DateCreateUtil;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;
import com.deppon.crm.module.customer.shared.domain.ShuttleAddress;
import com.deppon.crm.module.customer.shared.domain.UpGradeCustomerCondition;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.customer.shared.exception.MemberExceptionType;



public class CustomerValidatorTest extends BeanUtil{
	
	@Before
	public void setUp(){
	}
	
	
	@Test
	public void testValidateMemberForm(){
		//可以不测试
	}
	
	@Test
	public void testValidateMemberShuttleAddress(){
		Assert.assertTrue(customerValidator.validateMemberShuttleAddress(null));
		Assert.assertTrue(customerValidator.validateMemberShuttleAddress(new ArrayList<ShuttleAddress>()));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testValidateShuttleAddressFormList(){
		List list = null;
		Assert.assertTrue(customerValidator.validateShuttleAddressForm(list));
		Assert.assertTrue(customerValidator.validateShuttleAddressForm(new ArrayList<ShuttleAddress>()));
	}
	
	@Test
	public void testIsShuttleAddressRepeat(){
		List<ShuttleAddress> list = null;
		ShuttleAddress shuttleAddress = null;
		
		list = new ArrayList<ShuttleAddress>();
		shuttleAddress = DateCreateUtil.createShuttleAddress("123","123");
		list.add(shuttleAddress);
		shuttleAddress = DateCreateUtil.createShuttleAddress("111","123");
		list.add(shuttleAddress);
		Assert.assertFalse(customerValidator.isShuttleAddressRepeat(list));
		
		list = new ArrayList<ShuttleAddress>();
		shuttleAddress = DateCreateUtil.createShuttleAddress("123","123");
		list.add(shuttleAddress);
		shuttleAddress = DateCreateUtil.createShuttleAddress("123","123");
		list.add(shuttleAddress);
		Assert.assertTrue(customerValidator.isShuttleAddressRepeat(list));
		
		list = new ArrayList<ShuttleAddress>();
		shuttleAddress = DateCreateUtil.createShuttleAddress("123","123");
		list.add(shuttleAddress);
		shuttleAddress = DateCreateUtil.createShuttleAddress("123","111");
		list.add(shuttleAddress);
		shuttleAddress = DateCreateUtil.createShuttleAddress("111","123");
		list.add(shuttleAddress);
		Assert.assertFalse(customerValidator.isShuttleAddressRepeat(list));
		
		list = new ArrayList<ShuttleAddress>();
		shuttleAddress = DateCreateUtil.createShuttleAddress("123","123");
		list.add(shuttleAddress);
		shuttleAddress = DateCreateUtil.createShuttleAddress("111","111");
		list.add(shuttleAddress);
		shuttleAddress = DateCreateUtil.createShuttleAddress("111","111");
		list.add(shuttleAddress);
		Assert.assertTrue(customerValidator.isShuttleAddressRepeat(list));
	}
	
	@Test
	public void testIsShuttleAddressSame(){
		//简单不用测试
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testValidateMemberAccountFormList(){
		List list = null;
		Assert.assertTrue(customerValidator.validateMemberAccountForm(list));
		list = new ArrayList();
		Assert.assertTrue(customerValidator.validateMemberAccountForm(list));
		list.add(DateCreateUtil.createAccount());
		Assert.assertTrue(customerValidator.validateMemberAccountForm(list));
	}
	
	@Test
	public void testValidateMemberContactForm(){
		//TODO
		List<Contact> contacts = null;
		Contact contact = null;
		//联系人为空时
		try {
			contacts = new ArrayList<Contact>();
			customerValidator.validateMemberContactForm(contacts);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.ContactMustExistOne.getErrCode());
		}
		
		
		try {
			//有一个不通过表单验证的contact
			contacts = new ArrayList<Contact>();
			contact = DateCreateUtil.createContact();
			contact.setIsMainLinkMan(null);
			contacts.add(contact);
			customerValidator.validateMemberContactForm(contacts);
			Assert.fail();
		} catch (MemberException e) {
//			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.IsMainLinkManNull.getErrCode());
		}
		try {
			//有一个联系人通过表单验证，但是偏好地址不通过的
			contacts = new ArrayList<Contact>();
			contact = DateCreateUtil.createContact();
			List<PreferenceAddress> prList = new ArrayList<PreferenceAddress>();
			PreferenceAddress preAddress = DateCreateUtil.createPreferenceAddress("3213", "3213");
			preAddress.setAddress(null);
			prList.add(preAddress);
			contact.setPreferenceAddressList(prList);
			contacts.add(contact);
			
			customerValidator.validateMemberContactForm(contacts);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.AddressNull.getErrCode());
		}
		try {
			//有一个全部有数据没有偏好地址的联系人,不是主要联系人
			contacts = new ArrayList<Contact>();
			contact = DateCreateUtil.createContact();
			contact.setIsMainLinkMan(Constant.NormalLinkMan);
			contacts.add(contact);
			customerValidator.validateMemberContactForm(contacts);
			Assert.fail();
		} catch (MemberException e) {
//			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.MainContactCanExistOnlyOne.getErrCode());
		}
		

		//有一个全部有数据没有偏好地址的联系人,是主要联系人
		contacts = new ArrayList<Contact>();
		contact = DateCreateUtil.createContact();
		contact.setIsMainLinkMan(Constant.MainLinkMan);
		contacts.add(contact);
		try {
              customerValidator.validateMemberContactForm(contacts);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@Test
	public void testIsMainContactOnlyOne(){
		List<Contact> contacts = new ArrayList<Contact>();
		Contact contact = DateCreateUtil.createContact();
		contact.setIsMainLinkMan(Constant.MainLinkMan);
		contacts.add(contact);
		
		Assert.assertTrue(customerValidator.isMainContactOnlyOne(contacts));
		
		contacts = new ArrayList<Contact>();
		contact = DateCreateUtil.createContact();
		contact.setIsMainLinkMan(Constant.MainLinkMan);
		contacts.add(contact);
		
		contact = DateCreateUtil.createContact();
		contact.setIsMainLinkMan(Constant.MainLinkMan);
		contacts.add(contact);
		
		Assert.assertFalse(customerValidator.isMainContactOnlyOne(contacts));
		
		contacts = new ArrayList<Contact>();
		contact = DateCreateUtil.createContact();
		contact.setIsMainLinkMan(Constant.MainLinkMan);
		contacts.add(contact);
		
		contact = DateCreateUtil.createContact();
		contact.setIsMainLinkMan(Constant.NormalLinkMan);
		contacts.add(contact);
		Assert.assertTrue(customerValidator.isMainContactOnlyOne(contacts));
		
		contacts = new ArrayList<Contact>();
		contact = DateCreateUtil.createContact();
		contact.setIsMainLinkMan(Constant.MainLinkMan);
		contacts.add(contact);
		
		contact = DateCreateUtil.createContact();
		contact.setIsMainLinkMan(Constant.MainLinkMan);
		contacts.add(contact);
		
		contact = DateCreateUtil.createContact();
		contact.setIsMainLinkMan(Constant.NormalLinkMan);
		contacts.add(contact);
		Assert.assertFalse(customerValidator.isMainContactOnlyOne(contacts));
		
		contacts = new ArrayList<Contact>();
		contact = DateCreateUtil.createContact();
		contact.setIsMainLinkMan(Constant.MainLinkMan);
		contacts.add(contact);
		
		contact = DateCreateUtil.createContact();
		contact.setIsMainLinkMan(Constant.NormalLinkMan);
		contacts.add(contact);
		
		contact = DateCreateUtil.createContact();
		contact.setIsMainLinkMan(Constant.NormalLinkMan);
		contacts.add(contact);
		
		Assert.assertTrue(customerValidator.isMainContactOnlyOne(contacts));
		
		contacts = new ArrayList<Contact>();
		contact = DateCreateUtil.createContact();
		contact.setIsMainLinkMan(Constant.NormalLinkMan);
		contacts.add(contact);
		
		contact = DateCreateUtil.createContact();
		contact.setIsMainLinkMan(Constant.NormalLinkMan);
		contacts.add(contact);
		
		contact = DateCreateUtil.createContact();
		contact.setIsMainLinkMan(Constant.NormalLinkMan);
		contacts.add(contact);
		
		Assert.assertFalse(customerValidator.isMainContactOnlyOne(contacts));
		
		contacts = new ArrayList<Contact>();
		contact = DateCreateUtil.createContact();
		contact.setIsMainLinkMan(Constant.MainLinkMan);
		contacts.add(contact);
		
		contact = DateCreateUtil.createContact();
		contact.setIsMainLinkMan(Constant.MainLinkMan);
		contacts.add(contact);
		
		contact = DateCreateUtil.createContact();
		contact.setIsMainLinkMan(Constant.MainLinkMan);
		contacts.add(contact);
		
		Assert.assertFalse(customerValidator.isMainContactOnlyOne(contacts));
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testValidatePreferenceAddressFormList(){
		List list = null;
		Assert.assertTrue(customerValidator.validatePreferenceAddressForm(list));
		list = new ArrayList();
		Assert.assertTrue(customerValidator.validatePreferenceAddressForm(list));
	}
	
	@Test
	public void testIsMemberContactHasRepeat(){
		List<Contact> list = null;
		Contact contact = null;
		Contact other_contact = null;
		
		list = new ArrayList<Contact>();
		contact = DateCreateUtil.createContact();
		other_contact = DateCreateUtil.createContact();
		list.add(contact);
		Assert.assertFalse(customerValidator.isMemberContactHasRepeat(list));
		
		list = new ArrayList<Contact>();
		contact = DateCreateUtil.createContact();
		other_contact = DateCreateUtil.createContact();
		list.add(contact);
		list.add(other_contact);
		Assert.assertTrue(customerValidator.isMemberContactHasRepeat(list));
		
		list = new ArrayList<Contact>();
		contact = DateCreateUtil.createContact();
		other_contact = DateCreateUtil.createContact();
		contact.setMobilePhone("123");
		other_contact.setMobilePhone("232");
		
		list.add(contact);
		list.add(other_contact);
		Assert.assertTrue(customerValidator.isMemberContactHasRepeat(list));

		list = new ArrayList<Contact>();
		contact = DateCreateUtil.createContact();
		other_contact = DateCreateUtil.createContact();
		contact.setTelPhone("123");
		other_contact.setTelPhone("232");
		
		list.add(contact);
		list.add(other_contact);
		Assert.assertTrue(customerValidator.isMemberContactHasRepeat(list));

		list = new ArrayList<Contact>();
		contact = DateCreateUtil.createContact();
		other_contact = DateCreateUtil.createContact();
		contact.setIdCard("123");
		other_contact.setIdCard("232");
		
		list.add(contact);
		list.add(other_contact);
		Assert.assertTrue(customerValidator.isMemberContactHasRepeat(list));

		list = new ArrayList<Contact>();
		contact = DateCreateUtil.createContact();
		other_contact = DateCreateUtil.createContact();
		contact.setMobilePhone("123");
		other_contact.setMobilePhone("232");
		contact.setTelPhone("123");
		other_contact.setTelPhone("232");
		
		list.add(contact);
		list.add(other_contact);
		Assert.assertTrue(customerValidator.isMemberContactHasRepeat(list));

		list = new ArrayList<Contact>();
		contact = DateCreateUtil.createContact();
		other_contact = DateCreateUtil.createContact();
		contact.setMobilePhone("123");
		other_contact.setMobilePhone("232");
		contact.setIdCard("123");
		other_contact.setIdCard("232");
		
		list.add(contact);
		list.add(other_contact);
		Assert.assertTrue(customerValidator.isMemberContactHasRepeat(list));

		list = new ArrayList<Contact>();
		contact = DateCreateUtil.createContact();
		other_contact = DateCreateUtil.createContact();
		contact.setIdCard("123");
		other_contact.setIdCard("232");
		contact.setTelPhone("123");
		other_contact.setTelPhone("232");
		
		list.add(contact);
		list.add(other_contact);
		Assert.assertTrue(customerValidator.isMemberContactHasRepeat(list));

		list = new ArrayList<Contact>();
		contact = DateCreateUtil.createContact();
		other_contact = DateCreateUtil.createContact();
		contact.setMobilePhone("123");
		other_contact.setMobilePhone("232");
		contact.setIdCard("123");
		other_contact.setIdCard("232");
		contact.setTelPhone("123");
		other_contact.setTelPhone("232");
		
		list.add(contact);
		list.add(other_contact);
		Assert.assertFalse(customerValidator.isMemberContactHasRepeat(list));

		list = new ArrayList<Contact>();
		contact = DateCreateUtil.createContact();
		other_contact = DateCreateUtil.createContact();
		contact.setMobilePhone("123");
		other_contact.setMobilePhone("232");
		contact.setIdCard("123");
		other_contact.setIdCard("232");
		contact.setName("123");
		other_contact.setName("232");
		
		list.add(contact);
		list.add(other_contact);
		Assert.assertFalse(customerValidator.isMemberContactHasRepeat(list));

		list = new ArrayList<Contact>();
		contact = DateCreateUtil.createContact();
		other_contact = DateCreateUtil.createContact();
		contact.setMobilePhone("123");
		other_contact.setMobilePhone("232");
		contact.setIdCard("123");
		other_contact.setIdCard("232");
		contact.setName("123");
		other_contact.setName("232");
		contact.setTelPhone("123");
		//TODO
//		other_contact.setTelPhone("232");
		
		list.add(contact);
		list.add(other_contact);
		Assert.assertFalse(customerValidator.isMemberContactHasRepeat(list));
		
		
		
		
		
		
		
		
	}
	
	@Test
	public void testIsContactSame(){
		try {
			customerValidator.isContactSame(null,null);
			Assert.fail();
		}catch(IllegalArgumentException e){
			Assert.assertTrue(true);
		}
		try {
			customerValidator.isContactSame(new Contact(),null);
			Assert.fail();
		}catch(IllegalArgumentException e){
			Assert.assertTrue(true);
		}try {
			customerValidator.isContactSame(null,new Contact());
			Assert.fail();
		}catch(IllegalArgumentException e){
			Assert.assertTrue(true);
		}
		
		Contact contact = DateCreateUtil.createContact();
		Contact other_contact = DateCreateUtil.createContact();
		Assert.assertTrue(customerValidator.isContactSame(contact,other_contact));
		
		contact = DateCreateUtil.createContact();
		other_contact = DateCreateUtil.createContact();
		contact.setMobilePhone("123");
		other_contact.setMobilePhone("232");
		
		Assert.assertTrue(customerValidator.isContactSame(contact,other_contact));
		
		contact = DateCreateUtil.createContact();
		other_contact = DateCreateUtil.createContact();
		contact.setTelPhone("123");
		other_contact.setTelPhone("232");
		
		Assert.assertTrue(customerValidator.isContactSame(contact,other_contact));
		
		contact = DateCreateUtil.createContact();
		other_contact = DateCreateUtil.createContact();
		contact.setIdCard("123");
		other_contact.setIdCard("232");
		
		Assert.assertTrue(customerValidator.isContactSame(contact,other_contact));
		
		contact = DateCreateUtil.createContact();
		other_contact = DateCreateUtil.createContact();
		contact.setMobilePhone("123");
		other_contact.setMobilePhone("232");
		contact.setTelPhone("123");
		other_contact.setTelPhone("232");
		
		Assert.assertTrue(customerValidator.isContactSame(contact,other_contact));
		
		contact = DateCreateUtil.createContact();
		other_contact = DateCreateUtil.createContact();
		contact.setMobilePhone("123");
		other_contact.setMobilePhone("232");
		contact.setIdCard("123");
		other_contact.setIdCard("232");
		
		Assert.assertTrue(customerValidator.isContactSame(contact,other_contact));
		
		contact = DateCreateUtil.createContact();
		other_contact = DateCreateUtil.createContact();
		contact.setIdCard("123");
		other_contact.setIdCard("232");
		contact.setTelPhone("123");
		other_contact.setTelPhone("232");
		
		Assert.assertTrue(customerValidator.isContactSame(contact,other_contact));
		
		contact = DateCreateUtil.createContact();
		other_contact = DateCreateUtil.createContact();
		contact.setMobilePhone("123");
		other_contact.setMobilePhone("232");
		contact.setIdCard("123");
		other_contact.setIdCard("232");
		contact.setTelPhone("123");
		other_contact.setTelPhone("232");
	
		Assert.assertFalse(customerValidator.isContactSame(contact,other_contact));
		
		contact = DateCreateUtil.createContact();
		other_contact = DateCreateUtil.createContact();
		contact.setMobilePhone("123");
		other_contact.setMobilePhone("232");
		contact.setIdCard("123");
		other_contact.setIdCard("232");
		contact.setName("123");
		other_contact.setName("232");
	
		Assert.assertFalse(customerValidator.isContactSame(contact,other_contact));
		
		contact = DateCreateUtil.createContact();
		other_contact = DateCreateUtil.createContact();
		contact.setMobilePhone("123");
		other_contact.setMobilePhone("232");
		contact.setIdCard("123");
		other_contact.setIdCard("232");
		contact.setName("123");
		other_contact.setName("232");
		contact.setTelPhone("123");
		other_contact.setTelPhone("232");
		
		Assert.assertFalse(customerValidator.isContactSame(contact,other_contact));
		
		
	}
	
	@Test
	public void testValidateContactForm(){
		Contact contact = null;
		try {
			customerValidator.validateContactForm(contact);
			Assert.fail();
		}catch(IllegalArgumentException e){
			Assert.assertTrue(true);
		}
		
		contact = DateCreateUtil.createContact();
		try {
			customerValidator.validateContactForm(contact);
		} catch (MemberException e) {
			
		}
		try {
			contact = DateCreateUtil.createContact();
			contact.setNumber(null);
			customerValidator.validateContactForm(contact);
			Assert.fail();
		} catch (MemberException e) {
//			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.NumberNull.getErrCode());
		}
		
		try {
			contact = DateCreateUtil.createContact();
			contact.setLinkmanType(null);
			Assert.assertFalse(customerValidator.validateContactForm(contact));
			customerValidator.validateContactForm(contact);
			Assert.fail();
		} catch (MemberException e) {
//			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.LinkmanTypeNull.getErrCode());
		}
		
		try {
			contact = DateCreateUtil.createContact();
			contact.setIsMainLinkMan(null);
			Assert.assertFalse(customerValidator.validateContactForm(contact));
			customerValidator.validateContactForm(contact);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.IsMainLinkManNull.getErrCode());
		}
		
		try {
			contact = DateCreateUtil.createContact();
			contact.setSex(null);
			contact.setMobilePhone("13565544456");
			CustomerValidator.validateContactForm(contact);
//			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.SexNull.getErrCode());
		}
		
		try {
			contact = DateCreateUtil.createContact();
			contact.setNumber(null);
			Assert.assertFalse(customerValidator.validateContactForm(contact));
			customerValidator.validateContactForm(contact);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.NumberNull.getErrCode());
		}
		
		
		contact = DateCreateUtil.createContact();
		contact.setTelPhone("021-56556544");
		contact.setMobilePhone(null);
		Assert.assertTrue(customerValidator.validateContactForm(contact));
		
		contact = DateCreateUtil.createContact();
		contact.setTelPhone(null);
		contact.setMobilePhone("13524073834");
		Assert.assertTrue(customerValidator.validateContactForm(contact));
		
		try {
			contact = DateCreateUtil.createContact();
			contact.setTelPhone(null);
			contact.setMobilePhone(null);			
			customerValidator.validateContactForm(contact);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.ContactWayNull.getErrCode());
		}
		
	}
	
	@Test
	public void testValidateUpGradeCustomerCondition(){
		UpGradeCustomerCondition condition = null;
		try {
			customerValidator.validateUpGradeCustomerCondition(condition);
			Assert.fail();
		}catch(IllegalArgumentException e){
			Assert.assertTrue(true);
		}
		try {
			condition = new UpGradeCustomerCondition();
			customerValidator.validateUpGradeCustomerCondition(condition);
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.StatisticsTimeNull.getErrCode());
		}
		try {
			condition = new UpGradeCustomerCondition();
			condition.setStatisticsTime("2012-51");
			customerValidator.validateUpGradeCustomerCondition(condition);
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.StatisticsTimeFormError.getErrCode());
		}
		try {
			condition = new UpGradeCustomerCondition();
			condition.setStatisticsTime("2012-05");
			customerValidator.validateUpGradeCustomerCondition(condition);
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.TimeNotRight.getErrCode());
		}
		condition = new UpGradeCustomerCondition();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		condition.setStatisticsTime(sdf.format(date));
		condition.setDept("123");
		Assert.assertTrue(customerValidator.validateUpGradeCustomerCondition(condition));
		
	}
	
	@Test
	public void testValidateAccount(){
		Account account = null;
		try {
			customerValidator.validateAccount(account);
			Assert.fail();
		}catch(IllegalArgumentException e){
			Assert.assertTrue(true);
		}

		
		try {
			account =  DateCreateUtil.createAccount();
			account.setBank(null);
			customerValidator.validateAccount(account);
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.BankNull.getErrCode());
		}
		
		try {
			account =  DateCreateUtil.createAccount();
			account.setSubBankname(null);
			customerValidator.validateAccount(account);
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.SubBanknameNull.getErrCode());
		}
		
		try {
			account =  DateCreateUtil.createAccount();
			account.setIsdefaultaccount(null);
			customerValidator.validateAccount(account);
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.IsdefaultaccountNull.getErrCode());
		}
		
		try {
			account =  DateCreateUtil.createAccount();
			account.setBankAccount(null);
			customerValidator.validateAccount(account);
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.BankAccountNull.getErrCode());
		}
		
		try {
			account =  DateCreateUtil.createAccount();
			account.setCountName(null);
			customerValidator.validateAccount(account);
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.CountNameNull.getErrCode());
		}
		
		try {
			account =  DateCreateUtil.createAccount();
			account.setRelation(null);
			customerValidator.validateAccount(account);
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.RelationNull.getErrCode());
		}
		
		try {
			account =  DateCreateUtil.createAccount();
			account.setBankProvinceId(null);
			customerValidator.validateAccount(account);
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.BankProvinceIdNull.getErrCode());
		}
		
		try {
			account =  DateCreateUtil.createAccount();
			account.setBankCityId(null);
			customerValidator.validateAccount(account);
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.BankCityIdNull.getErrCode());
		}
		
		try {
			account =  DateCreateUtil.createAccount();
			account.setAccountNature(null);
			customerValidator.validateAccount(account);
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.AccountNatureNull.getErrCode());
		}
		
		try {
			account =  DateCreateUtil.createAccount();
			account.setAccountUse(null);
			customerValidator.validateAccount(account);
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.AccountUseNull.getErrCode());
		}
		
		try {
			account =  DateCreateUtil.createAccount();
			account.setFinanceLinkman(null);
			customerValidator.validateAccount(account);
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.FinanceLinkmanNull.getErrCode());
		}
		
		
		account =  DateCreateUtil.createAccount();
		account.setLinkManMobile(null);
		Assert.assertTrue(customerValidator.validateAccount(account));
		
		account =  DateCreateUtil.createAccount();
		account.setLinkManPhone(null);
		Assert.assertTrue(customerValidator.validateAccount(account));
		
		try {
			account =  DateCreateUtil.createAccount();
			account.setLinkManMobile(null);
			account.setLinkManPhone(null);
			customerValidator.validateAccount(account);
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.ContactWayNull.getErrCode());
		}
	}
	
	@Test
	public void testValidatePreferenceAddressForm(){
		PreferenceAddress preferenceAddress = null;
		try {
			customerValidator.validatePreferenceAddressForm(preferenceAddress);
			Assert.fail();
		}catch(IllegalArgumentException e){
			Assert.assertTrue(true);
		}
		
		try {
			preferenceAddress = DateCreateUtil.createPreferenceAddress(null, null);
			customerValidator.validatePreferenceAddressForm(preferenceAddress);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.AddressTypeNull.getErrCode());
		}
		
		try {
			preferenceAddress = DateCreateUtil.createPreferenceAddress("", "");
			customerValidator.validatePreferenceAddressForm(preferenceAddress);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.AddressTypeNull.getErrCode());
		}

		try {
			preferenceAddress = DateCreateUtil.createPreferenceAddress("1234", null);
			customerValidator.validatePreferenceAddressForm(preferenceAddress);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.AddressTypeNull.getErrCode());
		}
		
		try {
			preferenceAddress = DateCreateUtil.createPreferenceAddress(null, "123123");
			customerValidator.validatePreferenceAddressForm(preferenceAddress);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.AddressNull.getErrCode());
		}
		
		preferenceAddress = DateCreateUtil.createPreferenceAddress("312312", "123123");
		Assert.assertTrue(customerValidator.validatePreferenceAddressForm(preferenceAddress));

	}
	
	@Test
	public void testValidateMemberInfoForm(){
		Member member = null;
		try {
			customerValidator.validateMemberInfoForm(member);
			Assert.fail();
		}catch(IllegalArgumentException e){
			Assert.assertTrue(true);
		}
		
		member = DateCreateUtil.createMember();
		Assert.assertTrue(customerValidator.validateMemberInfoForm(member));
		
		try {
			member = DateCreateUtil.createMember();
			member.setDegree(null);
			customerValidator.validateMemberInfoForm(member);
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.DegreeNull.getErrCode());
		}
		
		try {
			member = DateCreateUtil.createMember();
			member.setCustName(null);
			customerValidator.validateMemberInfoForm(member);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.CustNameNull.getErrCode());
		}
		
		try {
			member = DateCreateUtil.createMember();
			member.setProvinceId(null);
			customerValidator.validateMemberInfoForm(member);
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.ProvinceNull.getErrCode());
		}
		
		try {
			member = DateCreateUtil.createMember();
			member.setCityId(null);
			customerValidator.validateMemberInfoForm(member);
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.CityNull.getErrCode());
		}
		
		
		try {
			member = DateCreateUtil.createMember();
			member.setCustType(null);
			customerValidator.validateMemberInfoForm(member);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.CustTypeNull.getErrCode());
		}	
		
		try {
			member = DateCreateUtil.createMember();
			member.setCustNature(null);
			customerValidator.validateMemberInfoForm(member);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.CustNatureNull.getErrCode());
		}
		
		try {
			member = DateCreateUtil.createMember();
			member.setTradeId(null);
			customerValidator.validateMemberInfoForm(member);
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.TradeIdNull.getErrCode());
		}
		
		try {
			member = DateCreateUtil.createMember();
			member.setIsSpecial(null);
			customerValidator.validateMemberInfoForm(member);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.IsSpecialNull.getErrCode());
		}
				
		try {
			member = DateCreateUtil.createMember();
			member.setIsImportCustor(null);
			customerValidator.validateMemberInfoForm(member);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.IsImportCustorNull.getErrCode());
		}
				
		try {
			member = DateCreateUtil.createMember();
			member.setIsRedeempoints(null);
			customerValidator.validateMemberInfoForm(member);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.IsRedeempointsNull.getErrCode());
		}
				
		try {
			member = DateCreateUtil.createMember();
			member.setIsParentCompany(null);
			customerValidator.validateMemberInfoForm(member);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.IsParentCompanyNull.getErrCode());
		}
				
		try {
			member = DateCreateUtil.createMember();
			member.setIsFocusPay(null);
			customerValidator.validateMemberInfoForm(member);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.IsFocusPayNull.getErrCode());
		}
				
		try {
			member = DateCreateUtil.createMember();
			member.setProcRedit(null);
			customerValidator.validateMemberInfoForm(member);
//			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.ProcReditNull.getErrCode());
		}
		
		member = DateCreateUtil.createMember();
		member.setCustType(Constant.Person_Member);
		member.setCompanyProperty(null);
		Assert.assertTrue(customerValidator.validateMemberInfoForm(member));
		
		try {
			member = DateCreateUtil.createMember();
			member.setDeptId("371218");
			member.setCustType(Constant.Enterprise_Member);
			member.setTaxregNumber("4305241986112666397A");
			member.setCompanyProperty(null);
//			customerValidator.validateMemberInfoForm(member);
//			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.CompanyPropertyNull.getErrCode());
		}	
		
		member = DateCreateUtil.createMember();
		member.setCustType(Constant.Person_Member);
		member.setTaxregNumber(null);
//		Assert.assertTrue(customerValidator.validateMemberInfoForm(member));

				
		try {
			member = DateCreateUtil.createMember();
			member.setCustType(Constant.Enterprise_Member);
			member.setTaxregNumber(null);
//			customerValidator.validateMemberInfoForm(member);
//			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.EnterpriseMember_TaxregNumberNull.getErrCode());
		}
		
		member = DateCreateUtil.createMember();
		member.setIsParentCompany(true);
		member.setParentCompanyId(null);
//		Assert.assertTrue(customerValidator.validateMemberInfoForm(member));
		
		try{
			member = DateCreateUtil.createMember();
			member.setIsParentCompany(false);
			member.setParentCompanyId(null);
//			Assert.assertTrue(customerValidator.validateMemberInfoForm(member));
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.ParentCompanyNull.getErrCode());
		}
		
		member = DateCreateUtil.createMember();
		member.setIsFocusPay(false);
		member.setParentCompanyId(null);
//		Assert.assertTrue(customerValidator.validateMemberInfoForm(member));
		
		try{
			member = DateCreateUtil.createMember();
			member.setIsFocusPay(true);
			member.setParentCompanyId(null);
//			Assert.assertTrue(customerValidator.validateMemberInfoForm(member));
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.EnterpriseMember_TaxregNumberNull.getErrCode());
		}
		
	}
	
	@Test
	public void testValidateShuttleAddressForm(){
		//参数  address addressType
		ShuttleAddress shuttleAddress = null;
		try {
			customerValidator.validateShuttleAddressForm(shuttleAddress);
			Assert.fail();
		}catch(IllegalArgumentException e){
			Assert.assertTrue(true);
		}
		
		shuttleAddress = DateCreateUtil.createShuttleAddress("123","233");
		Assert.assertTrue(customerValidator.validateShuttleAddressForm(shuttleAddress));
		
		try {
			shuttleAddress = DateCreateUtil.createShuttleAddress("","233");
			customerValidator.validateShuttleAddressForm(shuttleAddress);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.AddressNull.getErrCode());
		}
		
		try {
			shuttleAddress = DateCreateUtil.createShuttleAddress(null,"233");
			customerValidator.validateShuttleAddressForm(shuttleAddress);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.AddressNull.getErrCode());
		}
		
		try {
			shuttleAddress = DateCreateUtil.createShuttleAddress("123","");
			customerValidator.validateShuttleAddressForm(shuttleAddress);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.AddressTypeNull.getErrCode());
		}
		
		try {
			shuttleAddress = DateCreateUtil.createShuttleAddress("123",null);
			customerValidator.validateShuttleAddressForm(shuttleAddress);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.AddressTypeNull.getErrCode());
		}
		
		try {
			shuttleAddress = DateCreateUtil.createShuttleAddress("","233");
			customerValidator.validateShuttleAddressForm(shuttleAddress);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.AddressNull.getErrCode());
		}
		
		try {
			shuttleAddress = DateCreateUtil.createShuttleAddress("","");
			customerValidator.validateShuttleAddressForm(shuttleAddress);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.AddressNull.getErrCode());
		}
		
		try {
			shuttleAddress = DateCreateUtil.createShuttleAddress(null,null);
			customerValidator.validateShuttleAddressForm(shuttleAddress);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.AddressNull.getErrCode());
		}
		
		try {
			shuttleAddress = DateCreateUtil.createShuttleAddress("21312","231");
			shuttleAddress.setProvince(null);
			customerValidator.validateShuttleAddressForm(shuttleAddress);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.ProvinceNull.getErrCode());
		}
		
		try {
			shuttleAddress = DateCreateUtil.createShuttleAddress("21312","231");
			shuttleAddress.setProvince(null);
			customerValidator.validateShuttleAddressForm(shuttleAddress);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.ProvinceNull.getErrCode());
		}
		
		try {
			shuttleAddress = DateCreateUtil.createShuttleAddress("21312","231");
			shuttleAddress.setCity(null);
			customerValidator.validateShuttleAddressForm(shuttleAddress);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.CityNull.getErrCode());
		}
		
		try {
			shuttleAddress = DateCreateUtil.createShuttleAddress("21312","231");
			shuttleAddress.setArea(null);
			customerValidator.validateShuttleAddressForm(shuttleAddress);
			Assert.fail();
		} catch (MemberException e) {
			Assert.assertEquals(e.getErrorCode(),MemberExceptionType.AreaNull.getErrCode());
		}
	}
	
		
		
	
		
		
		
		
}
