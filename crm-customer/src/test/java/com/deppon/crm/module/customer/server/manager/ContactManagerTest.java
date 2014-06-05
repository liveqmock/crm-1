/**
 * @description
 * @author 赵斌
 * @2012-4-25
 * @return
 */
package com.deppon.crm.module.customer.server.manager;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.customer.server.manager.impl.ContactManager;
import com.deppon.crm.module.customer.server.testutils.DBUtils;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContactCondition;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.exception.ContractException;
import com.deppon.crm.module.customer.shared.exception.ContractExceptionType;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;

/**
 * @author 赵斌
 * 
 */
public class ContactManagerTest extends BeanUtil{
	private ContactManager contactManager1=new ContactManager();
	@Before
	public void setUp() throws Exception {
		contactManager = (IContactManager) SpringTestHelper.getBean(
				ContactManager.class);
		// 初始化DAO层测试数据
		 DBUtils.clean();
		 DBUtils.initContract();
		User user  = new User();
		Department dept  =  new Department();
		dept.setDeptName("上海青浦营业部");
		dept.setPhone("02131350606");
		Employee e = new Employee();
		e.setId("1233");
		e.setDeptId(dept);
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
	}

	@After
	public void destroy() throws Exception {
		// 清理DAO层测试数据
		 DBUtils.clean();
		UserContext.setCurrentUser(null);
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据联系人ID得到对应的会员id<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-29
	 * void
	 */
	@Test
	public void test_getContactMemberId(){
		
		String memberId = contactManager.getContactMemberId("123");
		Assert.assertNull(memberId);
		
		memberId = contactManager.getContactMemberId("34341");
		Assert.assertNotNull(memberId);
		contactManager1.getiSmssender();
		contactManager1.getContactService();
		
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:测试联系人编码绑定<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-29
	 * void
	 */
	@Test
	public void test_boundContactForWeb(){
		String mobile = "";
		String contactNumber = "ytc13913220571";
		String orderSource = "";
		// 参数验证错误
		try{
		contactManager.boundContactForWeb(orderSource, "", contactNumber, mobile);
		}catch(ContractException ce){
			Assert.assertEquals(ContractExceptionType.MOBILEPHONENOTSAME.getErrCode(), ce.getErrorCode());
		}
		// 手机号码不一致
		try{
		contactNumber = "ytc13913220571";
		mobile = "137365248987";
		contactManager.boundContactForWeb(orderSource, "", contactNumber, mobile);
		}catch(ContractException ce){
			Assert.assertEquals(ContractExceptionType.MOBILEPHONENOTSAME.getErrCode(), ce.getErrorCode());
		}
		// 参数错误
//		try{
//			contactNumber = "ytc13913220571";
//			mobile = "13913220571";
//			orderSource = "";
//			contactManager.boundContactForWeb(orderSource, "", contactNumber, mobile);
//			}catch(ContractException ce){
//				Assert.assertEquals(ContractExceptionType.ORDERSOURCEERROR.getErrCode(), ce.getErrorCode());
//			}
		
	        // 成功
			contactNumber = "ytc13913220571";
			mobile = "13913220571";
			orderSource = "1";
			contactManager.boundContactForWeb(orderSource, "11581", contactNumber, mobile);

	}
	
	

	/**
	 * 
	 * <p>
	 * Description:测试发送短信验证码<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-29
	 * void
	 */
	@Test
	public void test_sendValidatorMsg() {

		boolean result = contactManager.sendValidatorMsg("1232", "");
		Assert.assertFalse(result);
	}

	
	
	/**
	 * 
	 * <p>
	 * Description:根据会员ID 得到联系人的ID集合<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-29
	 * void
	 */
	public void test_getContactIdListByMemberId(){
		List<String> contactIdList = contactManager.getContactIdListByMemberId("11581");
	     Assert.assertTrue(contactIdList.size()>0);
	}   
	
	/**
	 * 
	 * <p>
	 * Description:根据会员ID 得到联系人集合<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-29
	 * void
	 */
	public void test_getContactIdByMemberId(){
		List<Contact> contactIdList = contactManager.getContactByMemberId("11581");
	     Assert.assertTrue(contactIdList.size()>0);
	}        
	
	/**
	 * 
	 * <p>
	 * Description:测试 根据联系编码查询联系ID<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-29
	 * void
	 */
	@Test
	public void test_getContactIdByContactNumber(){
		
		String contactId = contactManager.getContactIdByContactNumber("1224");
		Assert.assertNull(contactId);
		
		 contactId = contactManager.getContactIdByContactNumber("ytc13913220571");
		Assert.assertNotNull(contactId);
		
	}
	
	/**
	 * 
	 * @根据联系人编码获得联系人信息
	 * @author 赵斌
	 * @2012-4-24
	 * @return
	 */
	@Test
	public void testGetContactByNumber() {
		String contactnum = "sspps13524052214";
	
				contactManager.getContactByNumber(contactnum);
	}
	
	@Test
	public void testBoundContactForWeb(){
		String orderSource = Constant.ORDER_SOURCE_ALIBABA;
		String channelCustId = "xhb_paytest";
		String contactNumber = "227757";
		String mobile = "13286859421";
		try {
			contactManager.boundContactForWeb(orderSource, channelCustId, contactNumber,mobile);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBoundContact(){
		try {
			User user  = new User();
			Department dept  =  new Department();
			dept.setDeptName("上海青浦营业部");
			dept.setPhone("02131350606");
			Employee e = new Employee();
			e.setId("12343");
			e.setDeptId(dept);
			user.setId("12342");
			user.setEmpCode(e);
			UserContext.setCurrentUser(user);
			RegisterInfo info = new RegisterInfo();
			info.setUserName("patern");
			info.setCusCode("12344321");
			info.setCreateUser("12344321");
			info.setAddress("上海青浦徐进明珠路1018号");
			info.setFixedPhone("1358525847");
			info.setTelephone("021-89848785");
			info.setRealName("张三");
			info.setCustsource("1");
			contactManager.boundContactForOnline(info,"1");
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testVaryContact(){
		Contact contact = new Contact();
		Member member = new Member();
		contact.setId("0");
		member.setId("0");
		contactManager.varyContact(contact, member);
	}
	
	@Test
	public void testSearchContactByCondition(){
		ContactCondition condition = new ContactCondition();
		condition.setMemberName("这方法真戳");
		condition.setMemberNum("400074110");
		condition.setContactName("竟然没有任何校验");
		condition.setContactNum("qnmlgb74110");
		condition.setMobile("13245678954");
		condition.setPhone("021-32154671");
		contactManager.searchContactByCondition(condition);
	}
	
	@Test
	public void testSendSmsToContact() throws CrmBusinessException{
		Contact contact = new Contact();
		User user = new User();
		String registerId = null;
		contact.setMobilePhone(null);
		Employee empCode = new Employee();
		Department deptId = new Department();
		deptId.setDeptName("I简直服了U这奇葩参数名字");
		deptId.setPhone("13546987542");
		empCode.setDeptId(deptId);
		user.setEmpCode(empCode);
		try {
			contactManager.sendSmsToContact(contact, user, registerId);
		} catch (ContractException e) {
			Assert.assertEquals(e.getErrorCode(), ContractExceptionType.MOBILECONNOTNULL.getErrCode());
		}
		contact.setMobilePhone("13546879654");
		deptId.setStandardCode("DW71321");
		contactManager.sendSmsToContact(contact, user, registerId);
	}
	
	@Test
	public void testUnboundContact(){
		RegisterInfo registerInfo = new RegisterInfo();
		registerInfo.setUserName("测试");
		registerInfo.setCustsource("测试");
		contactManager.unboundContact(registerInfo);
	}
	
	@Test
	public void testQueryRegisterInfo(){
		RegisterInfo registerInfo = new RegisterInfo();
		registerInfo.setTelephone("13546981235");
		registerInfo.setId("123");
		contactManager.queryRegisterInfo(registerInfo);
	}
	
	@Test
	public void testSearchContactList(){
		//phone为null
		try {
			contactManager.searchContactList(null, null, null);
		} catch (IllegalArgumentException e) {
			Assert.assertEquals(e.getMessage(), "参数不合法");
		}
		//name为null
		try {
			contactManager.searchContactList(null, "021-12354541", null);
		} catch (IllegalArgumentException e) {
			Assert.assertEquals(e.getMessage(), "参数不合法");
		}
		//tel为null
		try {
			contactManager.searchContactList(null,null,"测试");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals(e.getMessage(), "参数不合法");
		}
		
		//参数合法，结果为空
		Assert.assertTrue(contactManager.searchContactList("-88888888", null, null).size()==0);
		Assert.assertTrue(contactManager.searchContactList(null, "-12354521", "Yes,My Lady").size()==0);
		
		//参数合法，结果不为空
		Assert.assertTrue(contactManager.searchContactList("13545621245", null, null).size()>0);
		Assert.assertTrue(contactManager.searchContactList(null, "021-12354521", "测试联系人").size()>0);
		
	}
	
	@Test
	public void testSearchContactByPhone(){
		Assert.assertNotNull(contactManager.searchContactByPhone("13545621245"));
	}
	
	@Test
	public void testSearchContactByTelName(){
		Assert.assertNotNull(contactManager.searchContactByTelName("021-12354521", "测试联系人"));
	}
}
