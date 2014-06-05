package com.deppon.crm.module.customer.server.testutils;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.client.order.domain.WaybillInfo;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.PotentialCustomer;
import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;
import com.deppon.crm.module.customer.shared.domain.ScatterCustomer;
import com.deppon.crm.module.customer.shared.domain.ShuttleAddress;
import com.deppon.crm.module.customer.shared.domain.integral.AdjustIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.Gift;
import com.deppon.crm.module.customer.shared.domain.integral.HandRewardIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGift;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.RewardIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.WaybillIntegral;
import com.deppon.crm.module.organization.shared.domain.Department;

public class DateCreateUtil {
	
	public static PotentialCustomer createPotentialCustomer(String phone,String tel,String name,String deptId){
		PotentialCustomer pc = new PotentialCustomer();
		pc.setLinkManPhone(tel);
		pc.setLinkManMobile(phone);
		pc.setDeptId(deptId);
		pc.setLinkManName(name);
		return pc;
	}
	
	public static PotentialCustomer createPotentialCustomer(String id,String phone,String tel,String name,String deptId){
		PotentialCustomer pc = createPotentialCustomer(phone, tel, name, deptId);
		pc.setId(id);
		return pc;
	}
	
	public static List<PotentialCustomer> createPotentialCustomerList(){
		List<PotentialCustomer> list = new ArrayList<PotentialCustomer>();
		for (int i = 0; i < 10; i++) {
			list.add(createPotentialCustomer("02177777","13855555","鲍相江","001"));
		}
		return list;
	}

	public static ScatterCustomer createScatterCustomer(String phone,String tel,String name,String deptId) {
		ScatterCustomer sc = new ScatterCustomer();
		sc.setLinkManPhone(tel);
		sc.setLinkManMobile(phone);
		sc.setDeptId(deptId);
		sc.setLinkManName(name);
		return sc;
	}
	
	public static ScatterCustomer createScatterCustomer(String phone,String tel,String name,String deptId,String custNature) {
		ScatterCustomer sc = createScatterCustomer(phone, tel, name, deptId);
		sc.setCustNature(custNature);
		return sc;
	}
	
	public static ScatterCustomer createScatterCustomer(String custNature) {
		ScatterCustomer sc = createScatterCustomer("123", "321", "saa", "001");
		sc.setCustNature(custNature);
		return sc;
	}
	
	public static Member createMemberWithNoId(){
		return createMemberWithNoId("1","1","1","1","1");
	}

	public static Member createMemberWithNoId(String taxregNumber,String phone,String idCard,String tel,String linkManName){
		Member member = TestUtils.createBean(Member.class);
		member.setId(null);
		member.setTaxregNumber(taxregNumber);
		
		List<Contact> contactList = new ArrayList<Contact>();
		Contact contact = TestUtils.createBean(Contact.class);
		contact.setCardTypeCon("SECOND_GENERATION_IDCARD");
		contact.setMobilePhone(phone);
		contact.setTelPhone(tel);
		contact.setIdCard(idCard);
		contact.setIsMainLinkMan(true);
		contact.setName(linkManName);
		contact.setId(null);

		List<PreferenceAddress> preferenceAddressList = new ArrayList<PreferenceAddress>();
		PreferenceAddress pa = TestUtils.createBean(PreferenceAddress.class);
		pa.setId(null);

		preferenceAddressList.add(pa);
		contact.setPreferenceAddressList(preferenceAddressList);
		
		contactList.add(contact);
		
		List<ShuttleAddress> shuttleAddressList = new ArrayList<ShuttleAddress>();
		ShuttleAddress sa =TestUtils.createBean(ShuttleAddress.class);
		sa.setId(null);

		shuttleAddressList.add(sa);
		
		List<Account> accountList = new ArrayList<Account>();
		Account account = TestUtils.createBean(Account.class);
		account.setId(null);

		accountList.add(account);
		
		
		member.setContactList(contactList);
		member.setShuttleAddressList(shuttleAddressList);
		member.setAccountList(accountList);
		return member;
	}

	public static ShuttleAddress createShuttleAddress(String address,String addressType) {
		ShuttleAddress sa = TestUtils.createBean(ShuttleAddress.class);
		sa.setAddress(address);
		sa.setAddressType(addressType);
		return sa;
	}

	public static PreferenceAddress createPreferenceAddress(String address,String addressType){
		PreferenceAddress pa = TestUtils.createBean(PreferenceAddress.class);
		pa.setAddress(address);
		pa.setAddressType(addressType);
		return pa;
	}

	public static Contact createContact(String phone, String name) {
		Contact contact = TestUtils.createBean(Contact.class);
		contact.setMobilePhone(phone);
		contact.setName(name);
		return contact;
	}

	public static Account createAccount(String phone, String name) {
		Account account = TestUtils.createBean(Account.class);
		account.setLinkManMobile(phone);
		account.setFinanceLinkman(name);
		return account;
	}

	public static Member createMember() {
		Member member = TestUtils.createBean(Member.class);
		return member;
	}

	public static Account createAccount() {
		return TestUtils.createBean(Account.class);
	}

	public static Contact createContact() {
		return TestUtils.createBean(Contact.class);
	}

	public static WaybillInfo createWaybillInfo(String custType) {
		WaybillInfo waybill = TestUtils.createBean(WaybillInfo.class);
		waybill.setCustomerType(custType);
		return waybill;
	}

	public static Gift createGift() {
		Gift gift = TestUtils.createBean(Gift.class);
		Department department = new Department();
		department.setId("1");
		gift.setDepartment(department);
		return gift;
	}

	public static MemberIntegral createMemberIntegral() {
		MemberIntegral memberIn = TestUtils.createBean(MemberIntegral.class);
		Member member = TestUtils.createBean(Member.class);
		memberIn.setMember(member);
		return memberIn;
	}

	public static AdjustIntegral createAdjustIntegral() {
		AdjustIntegral integral = TestUtils.createBean(AdjustIntegral.class); 
		Contact contactFrom = TestUtils.createBean(Contact.class);
		Contact contactTo = TestUtils.createBean(Contact.class);
		Contact contact = TestUtils.createBean(Contact.class);
		
		contact.setId("1000001");
		contactTo.setId("1000001");
		contactFrom.setId("1000001");
		
		integral.setContactFrom(contactFrom);
		integral.setContact(contact);
		integral.setContactTo(contactTo);
		return integral;
	}

	public static HandRewardIntegral createHandRewardIntegral() {
		HandRewardIntegral integral = TestUtils.createBean(HandRewardIntegral.class);
		RewardIntegRule reward = TestUtils.createBean(RewardIntegRule.class);
		Contact contact = TestUtils.createBean(Contact.class);
		contact.setId("1000001");
		integral.setContact(contact);
		reward.setId("1000001");
		integral.setRewardIntegral(reward);
		return integral;
	}

	public static IntegralConvertGift createIntegralConvertGift() {
		IntegralConvertGift integral = TestUtils.createBean(IntegralConvertGift.class);
		Gift gift = TestUtils.createBean(Gift.class);
		gift.setId("1000001");
		Contact contact = TestUtils.createBean(Contact.class);
		contact.setId("1000001");
		integral.setContact(contact);
		integral.setGift(gift);
		return integral;
	}

	public static WaybillIntegral createWaybillIntegral() {
		WaybillIntegral integral = TestUtils.createBean(WaybillIntegral.class);
		Contact contact = TestUtils.createBean(Contact.class);

		integral.setContact(contact);		
		return integral;
	}

	public static ContactIntegral createContactIntegral() {
		ContactIntegral contactIntegral = TestUtils.createBean(ContactIntegral.class);
		Contact contact = TestUtils.createBean(Contact.class);
		contactIntegral.setContact(contact);
		return contactIntegral;
	}
}
