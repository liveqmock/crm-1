package com.deppon.crm.module.customer.server.dao;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.customer.server.testutils.DBUtils;
import com.deppon.crm.module.customer.server.testutils.DataTestUtil;
import com.deppon.crm.module.customer.server.testutils.TestUtils;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContactCondition;
import com.deppon.crm.module.customer.shared.domain.ContactView;
import com.deppon.crm.module.customer.shared.domain.Order;
import com.deppon.crm.module.customer.shared.domain.RandomNumber;

public class ContactDaoTest extends BeanUtil {
	
	private Contact contact = null;
	@Before
	public void setUp(){
		
		Assert.assertNotNull(contactDao);
	}
	@After
	public void tearDown() throws Exception {
		DataTestUtil.cleanContactDaoData();
	}
	@Test
	public void testGetContactCountByIdCard(){
		int total = contactDao.getContactCountByIdCard("11111111");
		if (total != 0) {
			System.out.println(total);
		}
	}
	
	@Test
	public void updateContactDao(){
		//Contact contact = TestUtils.createBean(Contact.class);
		Contact contact = new Contact();
		contact.setId("227757");
		contactDao.updateContact(contact);
	}
	
	@Test
	public void testGetContactCountByPhone(){
		int total = contactDao.getContactCountByPhone("111");
		if (total != 0) {
			System.out.println(total);
		}
	}
	
	@Test
	public void testGetContactCountByTelAndName(){
		int total = contactDao.getContactCountByTelAndName("111", "111");
		if (total != 0) {
			System.out.println(total);
		}
	}
	
	@Test
	public void testSearchContacts(){
		Contact contact = TestUtils.createBean(Contact.class);
		contactDao.searchContacts(contact);
	}
	
	@Test
	public void testSearchContactByCondition(){
		ContactCondition contactCondition = TestUtils.createBean(ContactCondition.class);
		contactDao.searchContactByCondition(contactCondition);
	}
	@Test
	public void testGetContactByNumber(){
		ContactView view = contactDao.getContactByNumber("227757");
		Assert.assertNotNull(view);
	}
	/**
	 * <p>
	 * Description:通过手机得到联系方式<br />
	 * </p>
	 * @version 0.1 2012-12-17
	 * void
	 */
	@Test
	public void testGetContactByPhone() {
		contactDao.getContactByPhone(null);
	}
	/**
	 * <p>
	 * Description:通过电话和联系人姓名得到联系方式<br />
	 * </p>
	 * @version 0.1 2012-12-17
	 * void
	 */
	@Test
	public void testGetContactByTelAndName() {
		contactDao.getContactByTelAndName(null, null);
	}
	/**
	 * <p>
	 * Description:通过会员ID删除联系方式<br />
	 * </p>
	 * @version 0.1 2012-12-17
	 * void
	 */
	@Test
	public void testDeleteContactByMemberId() {
		String memberId = "814";
		try {
			contactDao.deleteContactByMemberId(memberId);
		} catch (Exception e) {
			org.junit.Assert.assertTrue(true);
		}
	}
	/**
	 * <p>
	 * Description:保存随机编码<br />
	 * </p>
	 * @param phone 手机号
	 * @param number 随机码
	 * @version 0.1 2012-12-17
	 * void
	 */
	@Test
	public void testSaveRandomPhone() {
		String phone = "123";
		String number = "123";
		contactDao.saveRandomPhone(phone, number);
		
	}
	/**
	 * 
	 * @根据手机号得到验证码
	 * @version 0.1 2012-12-17
	 * @return
	 */
	@Test
	public void testGetRandomNumberByPhone(){
		String phone = "123";
		List<RandomNumber> list = contactDao.getRandomNumberByPhone(phone);
		org.junit.Assert.assertNotNull(list);
		
	}
	/**
	 * 
	 * @绑定联系人
	 * @version 0.1 2012-12-17
	 * @return
	 */
	@Test
	public void testBoundContact() {
		contact = new Contact();
		contact.setId("2503");
		contact.setStatus("0");
		contact.setCreateDate(new Date());
		try {
			org.junit.Assert.assertTrue(contactDao.boundContact(contact));
		} catch (Exception e) {
			org.junit.Assert.assertTrue(true);
		}
	}
	/**
	 * 
	 * @根据渠道订单号查询订单信息
	 * @author 李国文
	 * @version 0.1 2012-12-17
	 * @return
	 */
	@Test
	public void testQueryOrderByChannelNumber() {
		Order order = contactDao.queryOrderByChannelNumber("Y121214532301");
	}
	/**
	 * 
	 * @根据渠道订单号查询订单信息
	 * @author 李国文
	 * @version 0.1 2012-12-17
	 * @return
	 */
	@Test
	public void testGetOrderByNumber(){
		Order order = contactDao.getOrderByNumber("Y121214532301");
		
	}
	/**
	 * @description 根据手机号删除以前的验证码.  
	 * @version 0.1 2012-12-17
	 */
	@Test
	public void testDeleteRandomCodeByMobile(){
		String mobile = "13858024675";
		try {
			contactDao.deleteRandomCodeByMobile(mobile);
		} catch (Exception e) {
			org.junit.Assert.assertTrue(true);
		}
	}
	
	/**
	 * @description 新增官网联系人绑定关系测试 /联系人对应的官网id
	 * @version 0.1 2012-12-17
	 * @throws Exception 
	 */
	@Test
	public void testInsertContactOnlineNum() throws Exception{
		DBUtils.deleteContactOnlineNum();
		RegisterInfo info = new RegisterInfo();
		info.setUserName("patern");
		info.setCusCode("12344321");
		info.setCreateUser("12344321");
		info.setAddress("上海青浦徐进明珠路1018号");
		info.setFixedPhone("1358525847");
		info.setTelephone("021-89848785");
		info.setRealName("张三");
		try {
			contactDao.insertContactOnlineNum(info);
			fail();
		} catch (Exception e) {
			e.printStackTrace();
		}
		info.setCustsource(Constant.ORDER_SOURCE_ONLINE);
		contactDao.insertContactOnlineNum(info);
		
		String userName = contactDao.getOnlineLinkmanId("patern");
		assertEquals("12344321",userName);
		DBUtils.deleteContactOnlineNum();
	}
	
	/**
	 * @description 修改官网联系人绑定关系测试 /联系人对应的官网id
	 * @version 0.1 2012-12-17
	 * @throws Exception 
	 */
	@Test
	public void testUpdateContactOnlineNum() throws Exception{
		DBUtils.deleteContactOnlineNum();

		RegisterInfo info = new RegisterInfo();
		info.setUserName("patern");
		info.setCusCode("12344321");
		info.setCreateUser("12344321");
		info.setAddress("上海青浦徐进明珠路1018号");
		info.setFixedPhone("1358525847");
		info.setTelephone("021-89848785");
		info.setRealName("张三");
		info.setCustsource(Constant.ORDER_SOURCE_ONLINE);
		contactDao.insertContactOnlineNum(info);
		String linkmanId = contactDao.getOnlineLinkmanId("patern");
		assertEquals("12344321",linkmanId);
		
		RegisterInfo modifyInfo = new RegisterInfo();
		
		modifyInfo.setCusCode("122222");
		modifyInfo.setModifyUser("12344321");
		modifyInfo.setUserName("patern");
		modifyInfo.setCustsource(Constant.ORDER_SOURCE_ONLINE);
		
		contactDao.updateContactOnlineNum(modifyInfo);
		linkmanId = contactDao.getOnlineLinkmanId("patern");
		assertEquals("122222",linkmanId);
		DBUtils.deleteContactOnlineNum();

	}
	
	/**
	 * @description 修改官网联系人绑定关系测试 /联系人对应的官网id
	 * @version 0.1 2012-12-17
	 * @throws Exception 
	 */
	@Test
	public void testUnboundContactOnlineNum() throws Exception{
		DBUtils.deleteContactOnlineNum();

		RegisterInfo info = new RegisterInfo();
		info.setUserName("patern");
		info.setCusCode("12344321");
		info.setCreateUser("12344321");
		info.setAddress("上海青浦徐进明珠路1018号");
		info.setFixedPhone("1358525847");
		info.setTelephone("021-89848785");
		info.setRealName("张三");
		info.setCustsource(Constant.ORDER_SOURCE_ONLINE);
		contactDao.insertContactOnlineNum(info);
		String userName = contactDao.getOnlineLinkmanId("patern");
		assertEquals("12344321",userName);
		contactDao.unboundContactOnlineNum("12344321");
		userName = contactDao.getOnlineLinkmanId("patern");
		assertNull(userName);
		DBUtils.deleteContactOnlineNum();

	}
	
	
	@Test
	public void testQueryRegisterInfo() throws Exception{
		DBUtils.deleteContactOnlineNum();

		RegisterInfo info = new RegisterInfo();
		info.setUserName("patern");
		info.setCusCode("12344321");
		info.setCreateUser("12344321");
		info.setAddress("上海青浦徐进明珠路1018号");
		info.setFixedPhone("1358525847");
		info.setTelephone("021-89848785");
		info.setRealName("张三");
		info.setCustsource(Constant.ORDER_SOURCE_ONLINE);
		contactDao.insertContactOnlineNum(info);
		RegisterInfo queryInfo = new RegisterInfo();
		queryInfo.setUserName("patern");
		List<RegisterInfo> list = contactDao.getRegisterInfo(queryInfo);
		assertEquals("12344321", list.get(0).getCusCode());
		assertEquals("张三", list.get(0).getRealName());
		assertEquals("1358525847", list.get(0).getFixedPhone());
		assertEquals("021-89848785", list.get(0).getTelephone());
		assertEquals("上海青浦徐进明珠路1018号", list.get(0).getAddress());
		DBUtils.deleteContactOnlineNum();

	}
	@Test
	public void testChangeContactOnlineNum() throws Exception{
		DBUtils.deleteContactOnlineNum();

		RegisterInfo info = new RegisterInfo();
		info.setUserName("patern");
		info.setCusCode("12344321");
		info.setCreateUser("12344321");
		info.setAddress("上海青浦徐进明珠路1018号");
		info.setFixedPhone("1358525847");
		info.setTelephone("021-89848785");
		info.setRealName("张三");
		info.setCustsource(Constant.ORDER_SOURCE_ONLINE);
		contactDao.insertContactOnlineNum(info);
		RegisterInfo queryInfo = new RegisterInfo();
		queryInfo.setCusCode("12344321");
		queryInfo.setCustsource(Constant.ORDER_SOURCE_ONLINE);
		contactDao.changeContactOnlineNum(queryInfo);
		
		
		queryInfo = new RegisterInfo();
		queryInfo.setCusCode("12344321");
		queryInfo.setCustsource(Constant.ORDER_SOURCE_ALIBABA);
		queryInfo.setUserName("patern");
		queryInfo.setModifyDate(new Date());
		queryInfo.setModifyUser("86301");
		contactDao.updateContactOnlineNum(queryInfo);
		DBUtils.deleteContactOnlineNum();

		
	}

}
