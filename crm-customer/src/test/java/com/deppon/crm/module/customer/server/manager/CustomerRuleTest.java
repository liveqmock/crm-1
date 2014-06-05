package com.deppon.crm.module.customer.server.manager;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.deppon.crm.module.client.order.domain.WaybillInfo;
import com.deppon.crm.module.customer.server.testutils.DateCreateUtil;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;
import com.deppon.crm.module.customer.shared.domain.ScatterCustomer;
import com.deppon.crm.module.customer.shared.domain.ShuttleAddress;
import com.deppon.crm.module.customer.shared.domain.WayBillInfo;

public class CustomerRuleTest extends BeanUtil{
	
	@Test
	public void testGetNeedUpdateScatterCustomer(){
		
		try {
			CustomerRule.getNeedUpdateScatterCustomer(null, null);
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
		
		String type1 = "1";
		String type2 = "2";
		String type3 = "3";
		List<ScatterCustomer> list =new ArrayList<ScatterCustomer>();
		list.add(DateCreateUtil.createScatterCustomer(Constant.LEAVE_ARRIVE_CUSTOMER));
		list.add(DateCreateUtil.createScatterCustomer(type1));
		list.add(DateCreateUtil.createScatterCustomer(type2));
		list.add(DateCreateUtil.createScatterCustomer(type3));
		
		List<ScatterCustomer> result = CustomerRule.getNeedUpdateScatterCustomer(type1, list);
		Assert.assertTrue(result.size() == 2);
		
		list.add(DateCreateUtil.createScatterCustomer(Constant.LEAVE_ARRIVE_CUSTOMER));
		list.add(DateCreateUtil.createScatterCustomer(Constant.LEAVE_ARRIVE_CUSTOMER));

		result = CustomerRule.getNeedUpdateScatterCustomer(type1, list);
		Assert.assertTrue(result.size() == 2);
		
		list.add(DateCreateUtil.createScatterCustomer(type1));
		list.add(DateCreateUtil.createScatterCustomer(type1));
		list.add(DateCreateUtil.createScatterCustomer(type1));

		result = CustomerRule.getNeedUpdateScatterCustomer(type1, list);
		Assert.assertTrue(result.size() == 2);
		
		list.add(DateCreateUtil.createScatterCustomer(Constant.LEAVE_ARRIVE_CUSTOMER));
		list.add(DateCreateUtil.createScatterCustomer(Constant.LEAVE_ARRIVE_CUSTOMER));
		list.add(DateCreateUtil.createScatterCustomer(type1));
		list.add(DateCreateUtil.createScatterCustomer(type1));
		
		result = CustomerRule.getNeedUpdateScatterCustomer(type1, list);
		Assert.assertTrue(result.size() == 2);
		
		list.add(DateCreateUtil.createScatterCustomer(type2));
		list.add(DateCreateUtil.createScatterCustomer(type2));
		list.add(DateCreateUtil.createScatterCustomer(type2));
		
		result = CustomerRule.getNeedUpdateScatterCustomer(type1, list);
		Assert.assertTrue(result.size() == 5);
		
		list.add(DateCreateUtil.createScatterCustomer(type3));
		list.add(DateCreateUtil.createScatterCustomer(type3));
		
		result = CustomerRule.getNeedUpdateScatterCustomer(type1, list);
		Assert.assertTrue(result.size() == 7);

	}
	
	@Test
	public void testInitMemberByScatterCustomer(){
		//可以不测
	}
	
	@Test
	public void testChangeScatterCustomerToContact(){
		//可以不测
		
	}

	@Test
	public void testCreateCustNumberByMembrId(){
		Assert.assertNull(CustomerRule.createCustNumberByMembrId(null));
		Assert.assertEquals(CustomerRule.createCustNumberByMembrId("1"),"000001");
		Assert.assertEquals(CustomerRule.createCustNumberByMembrId("11"),"000011");
		Assert.assertEquals(CustomerRule.createCustNumberByMembrId("1234"),"001234");
		Assert.assertEquals(CustomerRule.createCustNumberByMembrId("1123123123231"),"1123123123231");
		Assert.assertEquals(CustomerRule.createCustNumberByMembrId("111111"),"111111");

	}

	@Test
	public void testGetContact(){
		//可以不测
		try {
			CustomerRule.getContact(null, null);
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
		try {
			CustomerRule.getContact(new ArrayList<Contact>(), null);
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
		
	}

	@Test
	public void testIsContactSameAccount(){
		Contact contact = DateCreateUtil.createContact("",""); 
		Account account = DateCreateUtil.createAccount("","");
		Assert.assertTrue(CustomerRule.isContactSameAccount(contact, account));
		
		contact = DateCreateUtil.createContact("",""); 
		account = DateCreateUtil.createAccount(null,"");
		Assert.assertTrue(CustomerRule.isContactSameAccount(contact, account));
		
		contact = DateCreateUtil.createContact("",""); 
		account = DateCreateUtil.createAccount("",null);
		Assert.assertTrue(CustomerRule.isContactSameAccount(contact, account));
		
		contact = DateCreateUtil.createContact(null,null); 
		account = DateCreateUtil.createAccount(null,null);
		Assert.assertTrue(CustomerRule.isContactSameAccount(contact, account));
		
		contact = DateCreateUtil.createContact("111","222"); 
		account = DateCreateUtil.createAccount("111","222");
		Assert.assertTrue(CustomerRule.isContactSameAccount(contact, account));
		
		contact = DateCreateUtil.createContact("111","222"); 
		account = DateCreateUtil.createAccount("222","333");
		Assert.assertFalse(CustomerRule.isContactSameAccount(contact, account));
	}

	//*** 如果存在两个一样的偏好地址，这种边界值未做考虑
	@Test
	public void testGetShuttelAddress(){
		ShuttleAddress sameShuttleAddress = DateCreateUtil.createShuttleAddress("111","000");
		ShuttleAddress sameShuttleAddress2 = DateCreateUtil.createShuttleAddress("111","000");
		ShuttleAddress shuttleAddress = DateCreateUtil.createShuttleAddress("222","333");
		ShuttleAddress shuttleAddress2 = DateCreateUtil.createShuttleAddress("111","222");
		ShuttleAddress shuttleAddress3 = DateCreateUtil.createShuttleAddress("222","000");
		ShuttleAddress shuttleAddress4 = DateCreateUtil.createShuttleAddress("","");
		ShuttleAddress shuttleAddress5 = DateCreateUtil.createShuttleAddress(null,null);
		
		PreferenceAddress preferenceAddress = DateCreateUtil.createPreferenceAddress("111", "000");
		
		List<ShuttleAddress> shuttleList = new ArrayList<ShuttleAddress>();
		shuttleList.add(sameShuttleAddress);
		Assert.assertSame(CustomerRule.getShuttelAddress(shuttleList, preferenceAddress),sameShuttleAddress);

		shuttleList = new ArrayList<ShuttleAddress>();
		shuttleList.add(sameShuttleAddress);
		shuttleList.add(shuttleAddress);
		Assert.assertSame(CustomerRule.getShuttelAddress(shuttleList, preferenceAddress),sameShuttleAddress);
		
		shuttleList = new ArrayList<ShuttleAddress>();
		shuttleList.add(shuttleAddress);
		shuttleList.add(shuttleAddress2);
		shuttleList.add(shuttleAddress3);
		shuttleList.add(shuttleAddress4);
		shuttleList.add(shuttleAddress5);
		Assert.assertNull(CustomerRule.getShuttelAddress(shuttleList, preferenceAddress));
		
		shuttleList = new ArrayList<ShuttleAddress>();
		shuttleList.add(shuttleAddress);
		shuttleList.add(shuttleAddress2);
		shuttleList.add(shuttleAddress3);
		shuttleList.add(shuttleAddress4);
		shuttleList.add(shuttleAddress5);
		Assert.assertNull(CustomerRule.getShuttelAddress(shuttleList, preferenceAddress));
		
		shuttleList = new ArrayList<ShuttleAddress>();
		Assert.assertNull(CustomerRule.getShuttelAddress(shuttleList, preferenceAddress));
		
		shuttleList = new ArrayList<ShuttleAddress>();
		shuttleList.add(sameShuttleAddress);
		shuttleList.add(sameShuttleAddress2);
		shuttleList.add(shuttleAddress);
		Assert.assertSame(CustomerRule.getShuttelAddress(shuttleList, preferenceAddress),sameShuttleAddress);
	}

	@Test
	public void testIsShuttleAddressSamePreferenceAddress(){
		ShuttleAddress shuttleAddress = DateCreateUtil.createShuttleAddress("","");
		PreferenceAddress preferenceAddress = DateCreateUtil.createPreferenceAddress("", "");
		Assert.assertTrue(CustomerRule.isShuttleAddressSamePreferenceAddress(shuttleAddress, preferenceAddress));
	
		shuttleAddress = DateCreateUtil.createShuttleAddress(null,null);
		preferenceAddress = DateCreateUtil.createPreferenceAddress(null, null);
		Assert.assertTrue(CustomerRule.isShuttleAddressSamePreferenceAddress(shuttleAddress, preferenceAddress));
		
		shuttleAddress = DateCreateUtil.createShuttleAddress("",null);
		preferenceAddress = DateCreateUtil.createPreferenceAddress(null, "");
		Assert.assertTrue(CustomerRule.isShuttleAddressSamePreferenceAddress(shuttleAddress, preferenceAddress));
		
		shuttleAddress = DateCreateUtil.createShuttleAddress("123","");
		preferenceAddress = DateCreateUtil.createPreferenceAddress("123", null);
		Assert.assertTrue(CustomerRule.isShuttleAddressSamePreferenceAddress(shuttleAddress, preferenceAddress));
		
		shuttleAddress = DateCreateUtil.createShuttleAddress("","123");
		preferenceAddress = DateCreateUtil.createPreferenceAddress(null, "123");
		Assert.assertTrue(CustomerRule.isShuttleAddressSamePreferenceAddress(shuttleAddress, preferenceAddress));
		
		
		shuttleAddress = DateCreateUtil.createShuttleAddress("111","111");
		preferenceAddress = DateCreateUtil.createPreferenceAddress("111", "111");
		Assert.assertTrue(CustomerRule.isShuttleAddressSamePreferenceAddress(shuttleAddress, preferenceAddress));
		
		shuttleAddress = DateCreateUtil.createShuttleAddress("222","111");
		preferenceAddress = DateCreateUtil.createPreferenceAddress("222", "111");
		Assert.assertTrue(CustomerRule.isShuttleAddressSamePreferenceAddress(shuttleAddress, preferenceAddress));
		
		
		shuttleAddress = DateCreateUtil.createShuttleAddress("123123",null);
		preferenceAddress = DateCreateUtil.createPreferenceAddress(null, "3123213");
		Assert.assertFalse(CustomerRule.isShuttleAddressSamePreferenceAddress(shuttleAddress, preferenceAddress));
		
		shuttleAddress = DateCreateUtil.createShuttleAddress("111","111");
		preferenceAddress = DateCreateUtil.createPreferenceAddress("111", "222");
		Assert.assertFalse(CustomerRule.isShuttleAddressSamePreferenceAddress(shuttleAddress, preferenceAddress));
		
		shuttleAddress = DateCreateUtil.createShuttleAddress("","222");
		preferenceAddress = DateCreateUtil.createPreferenceAddress("123", "222");
		Assert.assertFalse(CustomerRule.isShuttleAddressSamePreferenceAddress(shuttleAddress, preferenceAddress));
		
		shuttleAddress = DateCreateUtil.createShuttleAddress("123","");
		preferenceAddress = DateCreateUtil.createPreferenceAddress("123", "222");
		Assert.assertFalse(CustomerRule.isShuttleAddressSamePreferenceAddress(shuttleAddress, preferenceAddress));
		
		
	}
	
	@Test
	public void testCalculateCustLevel(){
		Assert.assertEquals(Constant.CustLevel_Fail, CustomerRule.calculateCustLevel(400));
		Assert.assertEquals(Constant.CustLevel_Fail, CustomerRule.calculateCustLevel(0));
		Assert.assertEquals(Constant.CustLevel_Fail, CustomerRule.calculateCustLevel(-200));
		Assert.assertEquals(Constant.CustLevel_Fail, CustomerRule.calculateCustLevel(599.99));
		Assert.assertEquals(Constant.CustLevel_Normol, CustomerRule.calculateCustLevel(600));
		Assert.assertEquals(Constant.CustLevel_Normol, CustomerRule.calculateCustLevel(600.5));
		Assert.assertEquals(Constant.CustLevel_Normol, CustomerRule.calculateCustLevel(2999.99));
		Assert.assertEquals(Constant.CustLevel_Gold, CustomerRule.calculateCustLevel(3000));
		Assert.assertEquals(Constant.CustLevel_Gold, CustomerRule.calculateCustLevel(3000.001));
		Assert.assertEquals(Constant.CustLevel_Gold, CustomerRule.calculateCustLevel(4555));
		Assert.assertEquals(Constant.CustLevel_Gold, CustomerRule.calculateCustLevel(9999.999));
		Assert.assertEquals(Constant.CustLevel_Platinum, CustomerRule.calculateCustLevel(10000));
		Assert.assertEquals(Constant.CustLevel_Platinum, CustomerRule.calculateCustLevel(10000.0001));
		Assert.assertEquals(Constant.CustLevel_Platinum, CustomerRule.calculateCustLevel(22222));
		Assert.assertEquals(Constant.CustLevel_Platinum, CustomerRule.calculateCustLevel(22222));
		Assert.assertEquals(Constant.CustLevel_Platinum, CustomerRule.calculateCustLevel(39999.999));
		Assert.assertEquals(Constant.CustLevel_Diamond, CustomerRule.calculateCustLevel(40000));
		Assert.assertEquals(Constant.CustLevel_Diamond, CustomerRule.calculateCustLevel(40000.000));
		Assert.assertEquals(Constant.CustLevel_Diamond, CustomerRule.calculateCustLevel(312312321));
		Assert.assertEquals(Constant.CustLevel_Diamond, CustomerRule.calculateCustLevel(321323));

	}
	
	public void testInitImplementMember(){
		//TODO 暂时不测试
	}
	
	public void testInitUpGradeCustomerMember(){
		//TODO 暂时不测试
	}
	
	public void testGetDefaultMember(){
		//TODO 暂时不测试
	}
	
	public void testInitContacts(){
		//TODO 暂时不测试
	}
	
	public void testInitSepcatilMember(){
		//TODO 暂时不测试
	}
	
	@Test
	public void testAdapterWayBillList(){
		Assert.assertNull(CustomerRule.adapterWayBillList(null));
		List<WaybillInfo> list = new ArrayList<WaybillInfo>();
		Assert.assertEquals(0, CustomerRule.adapterWayBillList(list).size());
		
		WaybillInfo waybillInfo = DateCreateUtil.createWaybillInfo(Constant.ArriveCustomer);
		list.add(waybillInfo);
		Assert.assertEquals(1, CustomerRule.adapterWayBillList(list).size());
		
		waybillInfo = DateCreateUtil.createWaybillInfo(Constant.ArriveCustomer);
		list.add(waybillInfo);
		Assert.assertEquals(2, CustomerRule.adapterWayBillList(list).size());

		waybillInfo = DateCreateUtil.createWaybillInfo(Constant.ArriveCustomer);
		list.add(waybillInfo);
		Assert.assertEquals(3, CustomerRule.adapterWayBillList(list).size());

	}
	
	@Test
	public void testAdapterWayBill(){
		//TODO 通知寸总完成数据字典key的改变
//		WaybillInfo waybillInfo = DateCreateUtil.createWaybillInfo(Constant.ArriveCustomer);
//		waybillInfo.setWaybillNumber("007");
//		waybillInfo.setCustomerType(WaybillInfo.RECEIVE_CUSTOMER);
//		waybillInfo.setPrepaid(200);
//		waybillInfo.setFreightCollect(300);
//		waybillInfo.setServicefee(30);
//		waybillInfo.setCollectionCharges(50);
//		
//		WayBillInfo waybill = CustomerRule.adapterWayBill(waybillInfo);
//		Assert.assertEquals("007",waybill.getWayBillNumber());
//		Assert.assertEquals(200.0+300-30-50,waybill.getMoney());
//		Assert.assertEquals(Constant.ArriveCustomerRatio,waybill.getRatio());

	}
	
	
	
	@Test
	public void testGetRatio(){
		Assert.assertEquals(Constant.LeaveCustomerRatio, CustomerRule.getRatio(Constant.LeaveCustomer));
		Assert.assertEquals(Constant.ArriveCustomerRatio, CustomerRule.getRatio(Constant.ArriveCustomer));
	}
	
	public void testGetCustLevel(){
		
	}
	
	@Test
	public void testGetMaxTargetLevel(){
		List<String> list = null;
		
		list = new ArrayList<String>();
		list.add(Constant.CustLevel_Diamond);
		list.add(Constant.CustLevel_Normol);
		Assert.assertEquals(CustomerRule.getMaxTargetLevel(list),Constant.CustLevel_Diamond);
		
		list = new ArrayList<String>();
		list.add(Constant.CustLevel_Normol);
		list.add(Constant.CustLevel_Diamond);
		Assert.assertEquals(CustomerRule.getMaxTargetLevel(list),Constant.CustLevel_Diamond);
		
		list = new ArrayList<String>();
		list.add(Constant.CustLevel_Gold);
		list.add(Constant.CustLevel_Platinum);
		list.add(Constant.CustLevel_Normol);
		Assert.assertEquals(CustomerRule.getMaxTargetLevel(list),Constant.CustLevel_Platinum);
		
	}
	
	@Test
	public void testSetMemberToSpecaialCreateStatus(){
		
	}
	
}
