//package com.deppon.crm.module.customer.server.service.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import junit.framework.Assert;
//
//import org.junit.Test;
//
//import com.deppon.crm.module.customer.server.testutils.DataTestUtil;
//import com.deppon.crm.module.customer.server.util.BeanUtil;
//import com.deppon.crm.module.customer.server.util.SpringTestHelper;
//import com.deppon.crm.module.customer.shared.domain.Account;
//import com.deppon.crm.module.customer.shared.domain.ApproveData;
//import com.deppon.crm.module.customer.shared.domain.Contact;
//import com.deppon.crm.module.customer.shared.domain.Member;
//import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;
//import com.deppon.crm.module.customer.shared.domain.ShuttleAddress;
//
//
///**
// * 
// * <p>
// * Description:修改会员模块service层测试<br />
// * </p>
// * @title AlterMemberServiceTest.java
// * @package com.deppon.crm.module.customer.server.service.impl 
// * @author 王明明
// * @version 0.1 2012-12-14
// */
//public class AlterMemberServiceTest extends BeanUtil{
//
//	@Override
//	public void setUp() throws Exception {
//		// 初始化修改会员模块数据
//		clearAlterMemberData();
//	}
//	
//	@Override
//	public void tearDown() throws Exception {
//		// 清理修改会员模块测试数据
//		clearAlterMemberData();
//	}
//	
//	
//	
//	/***
//	 * 
//	 * <p>
//	 * Description:测试更新用户基本信息<br />
//	 * </p>
//	 * @author 王明明
//	 * @version 0.1 2012-12-17
//	 * void
//	 * @throws Exception 
//	 */
//	@Test
//	public void test_updateMember() throws Exception{
//		initAlterMemberData();
//        String  memberId = "11111";
//		Member member = new Member();
//		member.setId(memberId);
//		member.setCustName("客户姓名修改");
//		member.setProvince("上海市");
//		member.setProvinceId("021");
//		member.setCity("上海");
//		member.setCityId("021");
//		member.setRegistAddress("上海市青浦区明珠路1080号");
//		member.setIsSpecial(true);
//		member.setRecommendCust("推荐人");
//		boolean result = alterMemberService.updateMember(member);
//		
//		Assert.assertEquals("更新会员基本信息结果错误",true, result);
//		
//		Member r_member = alterMemberService.getMemberById(memberId);
//		
//		Assert.assertEquals("客户姓名修改", r_member.getCustName());
//		Assert.assertEquals("上海市", r_member.getProvince());
//		Assert.assertEquals("21", r_member.getProvinceId());
//		Assert.assertEquals("上海", r_member.getCity());
//		
//		Assert.assertEquals("21", r_member.getCityId());
//		Assert.assertEquals("上海市青浦区明珠路1080号", r_member.getRegistAddress());
//		Assert.assertEquals(new Boolean(true), r_member.getIsSpecial());
//		Assert.assertEquals("推荐人", r_member.getRecommendCust());
//	}
//	
//	/**
//	 * 
//	 * <p>
//	 * Description:测试删除会员信息<br />
//	 * </p>
//	 * @author 王明明
//	 * @version 0.1 2012-12-15
//	 * void
//	 * @throws Exception 
//	 */
//	@Test
//	public void test_deleteMember() throws Exception{
//		initAlterMemberData();
//		
//		String  memberId = "11111";
//		
//		Member member = new Member();
//		member.setId(memberId);
//		
//		Contact contact = new Contact();
//		contact.setId("11");
//		contact.setNumber("1");
//		contact.setCustId(memberId);
//		
//		Contact contact1 = new Contact();
//		contact1.setId("12");
//		contact1.setNumber("1");
//		contact1.setCustId(memberId);
//		
//		PreferenceAddress pfa = new PreferenceAddress();
//		pfa.setId("12");
//		pfa.setLinkManId("11");
//		List<PreferenceAddress> preferenceAddressList = new ArrayList<PreferenceAddress>();
//		preferenceAddressList.add(pfa);
//		contact1.setPreferenceAddressList(preferenceAddressList);
//		
//        List<Contact> contactList = new ArrayList<Contact>();
//        contactList.add(contact);
//        contactList.add(contact1);
//		member.setContactList(contactList);
//		
//		ShuttleAddress shuttleAddress = new ShuttleAddress();
//		shuttleAddress.setId("11");
//		shuttleAddress.setStatus("0");
//		shuttleAddress.setMemberId(memberId);
//		List<ShuttleAddress> shuttleAddressList = new ArrayList<ShuttleAddress>();
//		shuttleAddressList.add(shuttleAddress);
//		member.setShuttleAddressList(shuttleAddressList);
//		
//		 // 删除会员
//		 alterMemberService.deleteMember(member);
//		 
//		
//		 Member r_member = alterMemberService.getInvalidMemberAllById(memberId);
//		 //验证会员状态
//		 Assert.assertNotNull(r_member);
//		 Assert.assertEquals("会员状态错误","2", r_member.getCustStatus());
//		 //验证会员联系人
//		 List<Contact> r_contactList = r_member.getContactList();
//		 Assert.assertEquals(2, r_contactList.size());
//		 for (Contact r_contact : r_contactList) {
//			Assert.assertEquals("联系人状态错误","2", r_contact.getStatus());
//			if("12".equals(r_contact.getId())){
//		    	// 验证联系人偏好地址
//				List<PreferenceAddress> r_preferenceAddressList = r_contact.getPreferenceAddressList();
//		    	for (PreferenceAddress preferenceAddress : r_preferenceAddressList) {
//					Assert.assertEquals("联系人偏好地址错误","2", preferenceAddress.getStatus());
//				}
//		    }
//		 }
//		 // 验证接收货地址
//		 List<ShuttleAddress> r_shuttleAddressList = r_member.getShuttleAddressList();
//		 Assert.assertEquals(1, r_shuttleAddressList.size());
//		 for (ShuttleAddress r_shuttleAddress : r_shuttleAddressList) {
//			 // 状态未查询
//			//Assert.assertEquals("会员接收地址错误","2", r_shuttleAddress.getStatus());
//			Assert.assertEquals("会员接收ID","11", r_shuttleAddress.getId());
//			
//		}
//		 // 验证银行账户信息
//		 List<Account> accountList = r_member.getAccountList();
//		 Assert.assertEquals(1, accountList.size());
//		 Assert.assertEquals("0", accountList.get(0).getStatus());
//		
//	}
//	
//	
//	
//
//	
//	/**
//	 * 
//	 * <p>
//	 * Description:测试保存审批数据信息<br />
//	 * </p>
//	 * @author 王明明
//	 * @version 0.1 2012-12-15
//	 * void
//	 */
//	@Test
//	public void test_saveApproveData(){
//		
//		ApproveData appData = new ApproveData();
//		appData.setClassName(Member.class.getSimpleName());
//		appData.setClassId("12");
//		appData.setFieldName("custName");
//		appData.setNewValue("wmm");
//		appData.setOldValue("罗典");
//		appData.setWorkFlowId("321456");
//		appData.setHandleType(1);
//		List<ApproveData> approveDataList = new ArrayList<ApproveData>();
//		approveDataList.add(appData);
//		alterMemberService.saveApproveData(approveDataList, "321456987");
//		
//		
//		ApproveData seacApproveData = alterMemberService.searchApproveData("321456").get(0);
//		Assert.assertNotNull("查询对象不能为空", seacApproveData);
//		Assert.assertEquals(appData.getClassId(), seacApproveData.getClassId());
//		Assert.assertEquals(appData.getClassName(), seacApproveData.getClassName());
//		Assert.assertEquals(appData.getFieldName(), seacApproveData.getFieldName());
//		Assert.assertEquals(appData.getNewValue(), seacApproveData.getNewValue());
//		Assert.assertEquals(appData.getOldValue(), seacApproveData.getOldValue());
//		Assert.assertEquals(appData.getWorkFlowId(), seacApproveData.getWorkFlowId());
//		Assert.assertEquals(appData.getMemberOperationLogId(), seacApproveData.getMemberOperationLogId());
//			
//	}
//	
//	
//	
//	
//	
//	/**
//	 * 
//	 * <p>
//	 * Description:测试根据联系人ID查询会员信息,及是否为合同客户(存入是否允许联系人兑换积分字段中返回)<br />
//	 * </p>
//	 * @author Administrator
//	 * @version 0.1 2012-12-15
//	 * void
//	 * @throws Exception 
//	 */
//	@Test
//	public void test_getMemberBylinkmanId() throws Exception{
//		initAlterMemberData();
//		Member member = alterMemberService.getMemberBylinkmanId("11", "123");
//		Assert.assertNotNull(member);
//		Assert.assertEquals("客户编码错误","1", member.getCustNumber());
//		Assert.assertEquals("客户等级错误","1", member.getDegree());
//		Assert.assertEquals("客户名称错误","客户名称", member.getCustName());
//		Assert.assertEquals("客户类型错误","1", member.getCustType());
//		Assert.assertNull("税务登记号错误", member.getTaxregNumber());
//	}
//
//	/**
//	 * 
//	 * <p>
//	 * Description:测试 根据联系人编码查询联系人信息
//	 *   查询不到数据<br />
//	 * </p>
//	 * @author 王明明
//	 * @version 0.1 2012-12-14
//	 * void
//	 * @throws Exception 
//	 */
//	@Test
//	public void test_getContactByNum_forNull() throws Exception{
//		initAlterMemberData();
//		Contact contact = alterMemberService.getContactByNum("111223");
//		Assert.assertNull("查询数据错误", contact);
//	}
//	
//	/**
//	 * 
//	 * <p>
//	 * Description:测试 根据联系人编码查询联系人信息
//	 *   能够查询到数据<br />
//	 * </p>
//	 * @author 王明明
//	 * @version 0.1 2012-12-14
//	 * void
//	 * @throws Exception 
//	 */
//	@Test
//	public void test_getContactByNum() throws Exception{
//		initAlterMemberData();
//		Contact contact = alterMemberService.getContactByNum("1");
//		Assert.assertEquals("联系人编码信息错误","1", contact.getNumber());
//		Assert.assertEquals("联系人信息错误","联系人姓名2", contact.getName());
//		Assert.assertEquals("联系人电话错误","13925698548", contact.getMobilePhone());
//		Assert.assertEquals("联系人办公电话错误","021-12365479", contact.getTelPhone());
//		System.out.println(contact.getLinkmanType().getClass());
//		Assert.assertEquals("联系人类型","1", contact.getLinkmanType());
//		Assert.assertEquals("联系人职务","1", contact.getDuty());
//		Assert.assertEquals("联系人状态","0", contact.getStatus());
//		Assert.assertEquals("联系人客户ID","11111", contact.getCustId());
//		Assert.assertNull("联系人性别",contact.getSex());
//		Assert.assertNull("联系人类型", contact.getEmail());
//		
//	}
//	
//	/**
//	 * 
//	 * <p>
//	 * Description:修改会员模块功能数据<br />
//	 * </p>
//	 * @author 王明明
//	 * @version 0.1 2012-12-15
//	 * @throws Exception
//	 * void
//	 */
//	private void initAlterMemberData() throws Exception{
//		List<String> sqlList = new ArrayList<String>();
//		// 会员信息
//		sqlList.add("delete T_CUST_CUSTLINKMAN where fid in (11,12)");
//		sqlList.add("delete T_CUST_APPROVEDATA where fid in (11,12)");
//		sqlList.add("delete T_CUST_PREFERENCEADDRESS where fid =11");
//		sqlList.add("delete T_CRM_TODOWORKFLOW where fid =11");
//		sqlList.add("insert into t_cust_custbasedata(fid,fcustname,fdegree,"
//						+ "fcusttype,fisspecial,fbusstype,fistrangoods,fisredeempoints,fcuststatus,FCUSTNUMBER) "
//						+ "values('11111','客户名称','1','1','1','1','1','1',0,1)");
//				
//				// 账户信息
//		sqlList.add("insert into T_CUST_ACCOUNT(fid,fstatus,fbelongcustom) values('11',0,'11111')");
//				
//				// 接送货地址信息
//		sqlList.add( "insert into T_CUST_SHUTTLEADDRESS(Fid,Faddress,Fprovince,Fcity,Fmemberid,fstatus) values('11','收货地址','安徽省','合肥','11111',0)");
//				// 联系人信息
//		sqlList.add( "insert into T_CUST_CUSTLINKMAN(Fid,fnumber,fborndate,fduty,flinkmantype,fstatus,fcustid,fname,fmobiletel,foffertel,facceptdeptid)"
//						+ "values('11','1',sysdate,'1','1',0,11111,'联系人姓名2','13925698548','021-12365479',14592)");
//		sqlList.add("insert into T_CUST_CUSTLINKMAN(Fid,fnumber,fborndate,fduty,flinkmantype,fstatus,fcustid,fname,fmobiletel,foffertel)"
//						+ "values('12','2',sysdate,'1','1',0,11111,'联系人姓名','13825698318','021-12365478')");
//				// 偏好地址信息
//		sqlList.add("insert into T_CUST_PREFERENCEADDRESS(Fid,fstatus) values('11',0)");
//		sqlList.add( "insert into T_CUST_PREFERENCEADDRESS(Fid,fstatus,faddresstype,flinkmanid) values('12',0,'RECEIVE_GOODS','11')");
//				// 审批数据
//		sqlList.add("insert into T_CUST_APPROVEDATA (FID, FCLASSID, FCLASSNAME, "
//						+ "FFIELDNAME, FNEWVALUE, FOLDVALUE, FWORKFLOWID,FHANDLETYPE)values "
//						+ "(11, 1, 'Member', 'custName', '罗典', '李学兴', 1,2)");
//				// 工作流信息
//		sqlList.add("insert into T_CRM_TODOWORKFLOW (FID, FCREATEUSERID, FLASTUPDATETIME, "
//						+ "FLASTMODIFYUSERID, FCREATETIME, FWORKFLOWID, FWORKFLOWNAME, FAPPLICATIONID,"
//						+ " FAPPLICATIONSTATUS, FROLEID, FDEPTID, FTODOFLAG)"
//						+ "values (11, null, '', null, sysdate,"
//						+ " 11, '12123', 1, '12123', 1, 1, '0')") ;
//		SpringTestHelper.executeBatch(sqlList);
//	}
//	
//	
//	/**
//	 * 
//	 * <p>
//	 * Description:清理修改会员模块功能数据<br />
//	 * </p>
//	 * @author 王明明
//	 * @version 0.1 2012-12-15
//	 * @throws Exception
//	 * void
//	 */
//	private void clearAlterMemberData () throws Exception{
//		List<String> sqlList = new ArrayList<String>();
//		// 偏好地址信息
//		sqlList.add("delete T_CUST_PREFERENCEADDRESS where fid = '11'");
//		sqlList.add("delete T_CUST_PREFERENCEADDRESS where fid = '12'");
//		sqlList.add("delete T_CUST_PREFERENCEADDRESS where flinkmanid = '11'");
//		// 账户信息
//		sqlList.add("delete T_CUST_ACCOUNT  where fid = '11' ");
//		// 接送货地址信息
//		sqlList.add("delete T_CUST_SHUTTLEADDRESS where fid = '11'");
//		// 会员信息
//		// 审批数据
//		sqlList.add("delete T_CUST_APPROVEDATA where fworkflowid ='1'");
//		sqlList.add("delete T_CUST_APPROVEDATA where fworkflowid ='321456'");
//		sqlList.add("delete T_CUST_APPROVEDATA where fid='11'");
//		sqlList.add("delete T_CRM_TODOWORKFLOW where fid ='11'");
//		// 联系人信息
//		sqlList.add("delete T_CUST_CUSTLINKMAN where fmobiletel in( '13825698548','13825698318')");
//		sqlList.add("delete T_CUST_CUSTLINKMAN where fid = '12'");
//		sqlList.add("delete T_CUST_CUSTLINKMAN where fid = '11'");
//		sqlList.add("delete T_CUST_CUSTLINKMAN where fnumber='1'");
//		sqlList.add("delete t_cust_custbasedata where fid = '11111' ");
//		SpringTestHelper.executeBatch(sqlList);
//
//	}
//	/**
//	 * @throws Exception 
//	 * 
//	 * @Title: modifyPrefrenceAddress
//	 *  <p>
//	 * @Description: 修改联系人接送货主偏好地址测试方法
//	 * </p>
//	 * @author 唐亮
//	 * @version 0.1 2013-4-10
//	 * @return void
//	 * @throws
//	 */
//	@Test
//	public void testModifyPreferenceAddress() throws Exception{
//		//初始化数据
//		DataTestUtil.initAlterMemberData();
//		PreferenceAddress preferenceAddress = new PreferenceAddress();
//		preferenceAddress.setAddress("一直被模仿，从未被超越");
//		preferenceAddress.setId("12");
//		//修改联系人偏好地址
//		alterMemberService.modifyPreferenceAddress(preferenceAddress);
//		//校验联系人偏好地址是否被修改
//		Assert.assertEquals("一直被模仿，从未被超越",alterMemberService.queryPreferenceAddrByContactId("11")
//				.get(0).getAddress());
//		//清理测试数据
//		DataTestUtil.clearAlterMemberData();
//	}
//	/**
//	 * @throws Exception 
//	 * 
//	 * @Title: queryPreferenceAddrByContactId
//	 *  <p>
//	 * @Description: 通过联系人Id查询联系人偏好地址测试方法
//	 * </p>
//	 * @author 唐亮
//	 * @version 0.1 2013-4-10
//	 * @return void
//	 * @throws
//	 */
//	@Test
//	public void testQueryPreferenceAddrByContactId() throws Exception{
//		//初始化测试信息
//		DataTestUtil.initAlterMemberData();
//		//测试能否查询到初始化的联系人偏好地址信息
//		Assert.assertNotNull(alterMemberService.queryPreferenceAddrByContactId("11"));
//		//清除测试信息
//		DataTestUtil.clearAlterMemberData();
//	}
//	/**
//	 * @throws Exception 
//	 * 
//	 * @Title: modifyMemberShuttleAddress
//	 *  <p>
//	 * @Description: 修改固定客户接送货地址
//	 * </p>
//	 * @author 唐亮
//	 * @version 0.1 2013-4-10
//	 * @return void
//	 * @throws
//	 */
//	@Test
//	public void testModifyMemberShuttleAddress() throws Exception{
//		//初始化测试信息
//		DataTestUtil.initAlterMemberData();
//		ShuttleAddress address = new ShuttleAddress();
//		address.setId("11");
//		address.setAddress("测试数据，请勿模仿");
//		//执行修改操作
//		alterMemberService.modifyMemberShuttleAddress(address);
//		//校验是否修改成功
//		List<ShuttleAddress> addresseList = alterMemberDao.searchShuttleAddressByMemberId("11");
//		for(ShuttleAddress shuttleAddres:addresseList){
//			//拿到修改的那条接送货信息，做比较
//			if (shuttleAddres.getId().equals("11")) {
//				Assert.assertEquals("测试数据，请勿模仿",shuttleAddres.getAddress());
//			}
//		}
//		//清理测试数据
//		DataTestUtil.clearAlterMemberData();
//	}
//	
//	
//	
//}
//
//
//
//
//
//
////
////
////	@Test
////	public void testService() throws Exception{
////		Map<String,List> map = new HashMap<String,List>();
////		Contact contact =new Contact();
////		contact.setAlid("11");
////		contact.setNumber("11");
////		contact.setBornDate(new Date());
////		contact.setCreateDate(new Date());
////		contact.setDuty("11");
////		contact.setLinkmanType("11");
////		PreferenceAddress pa = new PreferenceAddress();
////		pa.setAddress("asdfasdf");
////		List<PreferenceAddress> paList = new ArrayList<PreferenceAddress>();
////		paList.add(pa);
//////		contact.setPreferenceAddressList(paList);
////		List contactList = new ArrayList();
////		contactList.add(contact);
////		map.put("Contact", contactList);
////		List<ApproveData> adList = new ArrayList<ApproveData>();
////		alterMemberService.savePojoInfo(map, adList, "1");
////	}
////
////	/**
////	 * @作者：罗典
////	 * @时间：2012-3-24
////	 * @功能描述：保存审批数据信息
////	 */
////	@Test
////	public void testSaveApproveData() {
////		ApproveData approveData = new ApproveData();
////		approveData.setClassId("11");
////		approveData.setClassName("Member");
////		approveData.setFieldName("custName");
////		approveData.setNewValue("罗典");
////		approveData.setOldValue("李学兴");
////		approveData.setWorkFlowId("1");
////		ApproveData approveData1 = new ApproveData();
////		approveData1.setClassId("11");
////		approveData1.setClassName("Member");
////		approveData1.setFieldName("custName");
////		approveData1.setNewValue("罗典");
////		approveData1.setOldValue("李学兴");
////		approveData1.setWorkFlowId("1");
////		List<ApproveData> approveDataList = new ArrayList<ApproveData>();
////		approveDataList.add(approveData);
////		approveDataList.add(approveData1);
////		alterMemberService.saveApproveData(approveDataList);
////	}
////
////	/**
////	 * @作者：罗典
////	 * @时间：2012-3-24
////	 * @功能描述：根据类名和字段名查询审批类型
////	 */
////	@Test
////	public void testGetApproveType() {
////		// 需启动工作流的类型
////		ApproveData approveData = new ApproveData();
////		approveData.setClassName("Member");
////		approveData.setFieldName("tradeId");
////		Assert.assertNotNull(alterMemberService.getApproveType(
////				approveData.getClassName(), approveData.getFieldName()));
////		// 不需启动工作流的类型
////		approveData = new ApproveData();
////		approveData.setClassName("Member");
////		approveData.setFieldName("custType");
////		Assert.assertNull(alterMemberService.getApproveType(
////				approveData.getClassName(), approveData.getFieldName()));
////	}
////
////	/**
////	 * @作者：罗典
////	 * @时间：2012-3-24
////	 * @功能描述：根据工作流ID获取启动审批数据
////	 */
////	@Test
////	public void testSearchApproveData() {
////		Assert.assertNotNull(alterMemberService.searchApproveData("1"));
////	}
////
////	/**
////	 * @作者：罗典
////	 * @时间：2012-3-24
////	 * @功能描述：修改会员状态
////	 */
////	@Test
////	public void testAlterMemberStatus() {
////		Assert.assertTrue(alterMemberService.alterMemberStatus("11", "0"));
////	}
////
////	/**
////	 * @throws Exception
////	 * @作者：罗典
////	 * @时间：2012-3-24
////	 * @功能描述：根据审批数据修改会员相关信息
////	 */
////	@Test
////	public void testUpdateMemberInfo() throws Exception {
////		List<ApproveData> approveDataList = new ArrayList<ApproveData>();
////		ApproveData approveDatar = new ApproveData();
////		approveDatar.setClassName("PreferenceAddress");
////		approveDatar.setFieldName("addressType");
////		approveDatar.setClassId("121");
////		approveDatar.setNewValue("44");
////		approveDataList.add(approveDatar);
////		ApproveData approveData1 = new ApproveData();
////		approveData1.setClassName("PreferenceAddress");
////		approveData1.setFieldName("addressType");
////		approveData1.setClassId("11");
////		approveData1.setNewValue("44");
////		approveDataList.add(approveData1);
////		ApproveData approveDatax = new ApproveData();
////		approveDatax.setClassName("PreferenceAddress");
////		approveDatax.setFieldName("addressType");
////		approveDatax.setClassId("121");
////		approveDatax.setNewValue("44");
////		approveDataList.add(approveDatax);
////		ApproveData approveData2 = new ApproveData();
////		approveData2.setClassName("PreferenceAddress");
////		approveData2.setFieldName("address");
////		approveData2.setClassId("11");
////		approveData2.setNewValue("44");
////		approveDataList.add(approveData2);
////		ApproveData approveData3 = new ApproveData();
////		approveData3.setClassName("PreferenceAddress");
////		approveData3.setFieldName("billRequest");
////		approveData3.setClassId("11");
////		approveData3.setNewValue("44");
////		approveDataList.add(approveData3);
////		ApproveData approveData4 = new ApproveData();
////		approveData4.setClassName("PreferenceAddress");
////		approveData4.setFieldName("hasStopCost");
////		approveData4.setClassId("11");
////		approveData4.setNewValue("44");
////		approveDataList.add(approveData4);
////		ApproveData approveData5 = new ApproveData();
////		approveData5.setClassName("PreferenceAddress");
////		approveData5.setFieldName("payType");
////		approveData5.setClassId("11");
////		approveData5.setNewValue("44");
////		approveDataList.add(approveData5);
////		ApproveData approveData6 = new ApproveData();
////		approveData6.setClassName("PreferenceAddress");
////		approveData6.setFieldName("isSendToFloor");
////		approveData6.setClassId("11");
////		approveData6.setNewValue("1");
////		approveDataList.add(approveData6);
////		ApproveData approveData7 = new ApproveData();
////		approveData7.setClassName("PreferenceAddress");
////		approveData7.setFieldName("otherNeed");
////		approveData7.setClassId("11");
////		approveData7.setNewValue("44");
////		approveDataList.add(approveData7);
////		ApproveData approveData8 = new ApproveData();
////		approveData8.setClassName("PreferenceAddress");
////		approveData8.setFieldName("shuttleAddressId");
////		approveData8.setClassId("11");
////		approveData8.setNewValue("44");
////		approveDataList.add(approveData8);
////		ApproveData approveData9 = new ApproveData();
////		approveData9.setClassName("PreferenceAddress");
////		approveData9.setFieldName("linkManId");
////		approveData9.setClassId("11");
////		approveData9.setNewValue("44");
////		approveDataList.add(approveData9);
////		ApproveData approveData10 = new ApproveData();
////		approveData10.setClassName("Account");
////		approveData10.setFieldName("bank");
////		approveData10.setClassId("11");
////		approveData10.setNewValue("44");
////		approveDataList.add(approveData10);
////		ApproveData approveData11 = new ApproveData();
////		approveData11.setClassName("Account");
////		approveData11.setFieldName("bankId");
////		approveData11.setClassId("11");
////		approveData11.setNewValue("44");
////		approveDataList.add(approveData11);
////		ApproveData approveData12 = new ApproveData();
////		approveData12.setClassName("Account");
////		approveData12.setFieldName("subBankname");
////		approveData12.setClassId("11");
////		approveData12.setNewValue("44");
////		approveDataList.add(approveData12);
////		ApproveData approveData13 = new ApproveData();
////		approveData13.setClassName("Account");
////		approveData13.setFieldName("subBanknameId");
////		approveData13.setClassId("11");
////		approveData13.setNewValue("44");
////		approveDataList.add(approveData13);
////		ApproveData approveData14 = new ApproveData();
////		approveData14.setClassName("Account");
////		approveData14.setFieldName("isdefaultaccount");
////		approveData14.setClassId("11");
////		approveData14.setNewValue("1");
////		approveDataList.add(approveData14);
////		ApproveData approveData15 = new ApproveData();
////		approveData15.setClassName("Account");
////		approveData15.setFieldName("bankAccount");
////		approveData15.setClassId("11");
////		approveData15.setNewValue("44");
////		approveDataList.add(approveData15);
////		ApproveData approveData16 = new ApproveData();
////		approveData16.setClassName("Account");
////		approveData16.setFieldName("countName");
////		approveData16.setClassId("11");
////		approveData16.setNewValue("44");
////		approveDataList.add(approveData16);
////		ApproveData approveData17 = new ApproveData();
////		approveData17.setClassName("Account");
////		approveData17.setFieldName("relation");
////		approveData17.setClassId("11");
////		approveData17.setNewValue("44");
////		approveDataList.add(approveData17);
////		ApproveData approveData18 = new ApproveData();
////		approveData18.setClassName("Account");
////		approveData18.setFieldName("bankProvinceId");
////		approveData18.setClassId("11");
////		approveData18.setNewValue("44");
////		approveDataList.add(approveData18);
////		ApproveData approveData19 = new ApproveData();
////		approveData19.setClassName("Account");
////		approveData19.setFieldName("accountNature");
////		approveData19.setClassId("11");
////		approveData19.setNewValue("44");
////		approveDataList.add(approveData19);
////		ApproveData approveData20 = new ApproveData();
////		approveData20.setClassName("Account");
////		approveData20.setFieldName("accountUse");
////		approveData20.setClassId("11");
////		approveData20.setNewValue("44");
////		approveDataList.add(approveData20);
////		ApproveData approveData21 = new ApproveData();
////		approveData21.setClassName("Account");
////		approveData21.setFieldName("bankCityId");
////		approveData21.setClassId("11");
////		approveData21.setNewValue("44");
////		approveDataList.add(approveData21);
////		ApproveData approveData22 = new ApproveData();
////		approveData22.setClassName("Account");
////		approveData22.setFieldName("financeLinkman");
////		approveData22.setClassId("11");
////		approveData22.setNewValue("44");
////		approveDataList.add(approveData22);
////		ApproveData approveData23 = new ApproveData();
////		approveData23.setClassName("Account");
////		approveData23.setFieldName("financeLinkmanId");
////		approveData23.setClassId("11");
////		approveData23.setNewValue("44");
////		approveDataList.add(approveData23);
////		ApproveData approveData24 = new ApproveData();
////		approveData24.setClassName("Account");
////		approveData24.setFieldName("linkManMobile");
////		approveData24.setClassId("11");
////		approveData24.setNewValue("44");
////		approveDataList.add(approveData24);
////		ApproveData approveData25 = new ApproveData();
////		approveData25.setClassName("Account");
////		approveData25.setFieldName("linkManPhone");
////		approveData25.setClassId("11");
////		approveData25.setNewValue("44");
////		approveDataList.add(approveData25);
////		ApproveData approveData26 = new ApproveData();
////		approveData26.setClassName("Account");
////		approveData26.setFieldName("contactType");
////		approveData26.setClassId("11");
////		approveData26.setNewValue("44");
////		approveDataList.add(approveData26);
////		ApproveData approveData27 = new ApproveData();
////		approveData27.setClassName("Account");
////		approveData27.setFieldName("bankArea");
////		approveData27.setClassId("11");
////		approveData27.setNewValue("44");
////		approveDataList.add(approveData27);
////		ApproveData approveData28 = new ApproveData();
////		approveData28.setClassName("Account");
////		approveData28.setFieldName("lastUpdateDeptId");
////		approveData28.setClassId("11");
////		approveData28.setNewValue("44");
////		approveDataList.add(approveData28);
////		ApproveData approveData29 = new ApproveData();
////		approveData29.setClassName("Account");
////		approveData29.setFieldName("createDeptId");
////		approveData29.setClassId("11");
////		approveData29.setNewValue("44");
////		approveDataList.add(approveData29);
////		ApproveData approveData30 = new ApproveData();
////		approveData30.setClassName("Account");
////		approveData30.setFieldName("belongcustom");
////		approveData30.setClassId("11");
////		approveData30.setNewValue("44");
////		approveDataList.add(approveData30);
////		ApproveData approveData31 = new ApproveData();
////		approveData31.setClassName("Account");
////		approveData31.setFieldName("status");
////		approveData31.setClassId("11");
////		approveData31.setNewValue("44");
////		approveDataList.add(approveData31);
////		ApproveData approveData32 = new ApproveData();
////		approveData32.setClassName("Member");
////		approveData32.setFieldName("custNumber");
////		approveData32.setClassId("11");
////		approveData32.setNewValue("44");
////		approveDataList.add(approveData32);
////		ApproveData approveData33 = new ApproveData();
////		approveData33.setClassName("Member");
////		approveData33.setFieldName("degree");
////		approveData33.setClassId("11");
////		approveData33.setNewValue("44");
////		approveDataList.add(approveData33);
////		ApproveData approveData34 = new ApproveData();
////		approveData34.setClassName("Member");
////		approveData34.setFieldName("custName");
////		approveData34.setClassId("11");
////		approveData34.setNewValue("44");
////		approveDataList.add(approveData34);
////		ApproveData approveData35 = new ApproveData();
////		approveData35.setClassName("Member");
////		approveData35.setFieldName("tradeId");
////		approveData35.setClassId("11");
////		approveData35.setNewValue("44");
////		approveDataList.add(approveData35);
////		ApproveData approveData36 = new ApproveData();
////		approveData36.setClassName("Member");
////		approveData36.setFieldName("custType");
////		approveData36.setClassId("11");
////		approveData36.setNewValue("44");
////		approveDataList.add(approveData36);
////		ApproveData approveData37 = new ApproveData();
////		approveData37.setClassName("Member");
////		approveData37.setFieldName("taxregNumber");
////		approveData37.setClassId("11");
////		approveData37.setNewValue("44");
////		approveDataList.add(approveData37);
////		ApproveData approveData38 = new ApproveData();
////		approveData38.setClassName("Member");
////		approveData38.setFieldName("companyProperty");
////		approveData38.setClassId("11");
////		approveData38.setNewValue("44");
////		approveDataList.add(approveData38);
////		ApproveData approveData39 = new ApproveData();
////		approveData39.setClassName("Member");
////		approveData39.setFieldName("custNature");
////		approveData39.setClassId("11");
////		approveData39.setNewValue("44");
////		approveDataList.add(approveData39);
////		ApproveData approveData40 = new ApproveData();
////		approveData40.setClassName("Member");
////		approveData40.setFieldName("province");
////		approveData40.setClassId("11");
////		approveData40.setNewValue("44");
////		approveDataList.add(approveData40);
////		ApproveData approveData41 = new ApproveData();
////		approveData41.setClassName("Member");
////		approveData41.setFieldName("provinceId");
////		approveData41.setClassId("11");
////		approveData41.setNewValue("44");
////		approveDataList.add(approveData41);
////		ApproveData approveData42 = new ApproveData();
////		approveData42.setClassName("Member");
////		approveData42.setFieldName("city");
////		approveData42.setClassId("11");
////		approveData42.setNewValue("44");
////		approveDataList.add(approveData42);
////		ApproveData approveData43 = new ApproveData();
////		approveData43.setClassName("Member");
////		approveData43.setFieldName("cityId");
////		approveData43.setClassId("11");
////		approveData43.setNewValue("44");
////		approveDataList.add(approveData43);
////		ApproveData approveData44 = new ApproveData();
////		approveData44.setClassName("Member");
////		approveData44.setFieldName("registAddress");
////		approveData44.setClassId("11");
////		approveData44.setNewValue("44");
////		approveDataList.add(approveData44);
////		ApproveData approveData45 = new ApproveData();
////		approveData45.setClassName("Member");
////		approveData45.setFieldName("isSpecial");
////		approveData45.setClassId("11");
////		approveData45.setNewValue("1");
////		approveDataList.add(approveData45);
////		ApproveData approveData46 = new ApproveData();
////		approveData46.setClassName("Member");
////		approveData46.setFieldName("isRedeempoints");
////		approveData46.setClassId("11");
////		approveData46.setNewValue("1");
////		approveDataList.add(approveData46);
////		ApproveData approveData47 = new ApproveData();
////		approveData47.setClassName("Member");
////		approveData47.setFieldName("isImportCustor");
////		approveData47.setClassId("11");
////		approveData47.setNewValue("1");
////		approveDataList.add(approveData47);
////		ApproveData approveData48 = new ApproveData();
////		approveData48.setClassName("Member");
////		approveData48.setFieldName("custPotentialType");
////		approveData48.setClassId("11");
////		approveData48.setNewValue("44");
////		approveDataList.add(approveData48);
////		ApproveData approveData49 = new ApproveData();
////		approveData49.setClassName("Member");
////		approveData49.setFieldName("isAcceptMarket");
////		approveData49.setClassId("11");
////		approveData49.setNewValue("1");
////		approveDataList.add(approveData49);
////		ApproveData approveData50 = new ApproveData();
////		approveData50.setClassName("Member");
////		approveData50.setFieldName("brandWorth");
////		approveData50.setClassId("11");
////		approveData50.setNewValue("44");
////		approveDataList.add(approveData50);
////		ApproveData approveData51 = new ApproveData();
////		approveData51.setClassName("Member");
////		approveData51.setFieldName("channelSource");
////		approveData51.setClassId("11");
////		approveData51.setNewValue("44");
////		approveDataList.add(approveData51);
////		ApproveData approveData52 = new ApproveData();
////		approveData52.setClassName("Member");
////		approveData52.setFieldName("channel");
////		approveData52.setClassId("11");
////		approveData52.setNewValue("44");
////		approveDataList.add(approveData52);
////		ApproveData approveData53 = new ApproveData();
////		approveData53.setClassName("Member");
////		approveData53.setFieldName("preferenceService");
////		approveData53.setClassId("11");
////		approveData53.setNewValue("44");
////		approveDataList.add(approveData53);
////		ApproveData approveData54 = new ApproveData();
////		approveData54.setClassName("Member");
////		approveData54.setFieldName("companyScop");
////		approveData54.setClassId("11");
////		approveData54.setNewValue("44");
////		approveDataList.add(approveData54);
////		ApproveData approveData55 = new ApproveData();
////		approveData55.setClassName("Member");
////		approveData55.setFieldName("lastYearProfit");
////		approveData55.setClassId("11");
////		approveData55.setNewValue("44");
////		approveDataList.add(approveData55);
////		ApproveData approveData56 = new ApproveData();
////		approveData56.setClassName("Member");
////		approveData56.setFieldName("lastYearIncome");
////		approveData56.setClassId("11");
////		approveData56.setNewValue("44");
////		approveDataList.add(approveData56);
////		ApproveData approveData57 = new ApproveData();
////		approveData57.setClassName("Member");
////		approveData57.setFieldName("isFocusPay");
////		approveData57.setClassId("11");
////		approveData57.setNewValue("1");
////		approveDataList.add(approveData57);
////		ApproveData approveData58 = new ApproveData();
////		approveData58.setClassName("Member");
////		approveData58.setFieldName("focusDeptId");
////		approveData58.setClassId("11");
////		approveData58.setNewValue("44");
////		approveDataList.add(approveData58);
////		ApproveData approveData59 = new ApproveData();
////		approveData59.setClassName("Member");
////		approveData59.setFieldName("billTitle");
////		approveData59.setClassId("11");
////		approveData59.setNewValue("44");
////		approveDataList.add(approveData59);
////		ApproveData approveData60 = new ApproveData();
////		approveData60.setClassName("Member");
////		approveData60.setFieldName("isParentCompany");
////		approveData60.setClassId("11");
////		approveData60.setNewValue("1");
////		approveDataList.add(approveData60);
////		// ApproveData approveData61 = new ApproveData();
////		// approveData61.setClassName("Member");
////		// approveData61.setFieldName("parentNumber");
////		// approveData61.setClassId("11");
////		// approveData61.setNewValue("44");
////		// approveDataList.add(approveData61);
////		ApproveData approveData62 = new ApproveData();
////		approveData62.setClassName("Member");
////		approveData62.setFieldName("parentCompanyId");
////		approveData62.setClassId("11");
////		approveData62.setNewValue("44");
////		approveDataList.add(approveData62);
////		ApproveData approveData63 = new ApproveData();
////		approveData63.setClassName("Member");
////		approveData63.setFieldName("registerFund");
////		approveData63.setClassId("11");
////		approveData63.setNewValue("44");
////		approveDataList.add(approveData63);
////		ApproveData approveData64 = new ApproveData();
////		approveData64.setClassName("Member");
////		approveData64.setFieldName("procRedit");
////		approveData64.setClassId("11");
////		approveData64.setNewValue("44");
////		approveDataList.add(approveData64);
////		ApproveData approveData65 = new ApproveData();
////		approveData65.setClassName("Member");
////		approveData65.setFieldName("recommendCust");
////		approveData65.setClassId("11");
////		approveData65.setNewValue("44");
////		approveDataList.add(approveData65);
////		ApproveData approveData66 = new ApproveData();
////		approveData66.setClassName("Member");
////		approveData66.setFieldName("remark");
////		approveData66.setClassId("11");
////		approveData66.setNewValue("44");
////		approveDataList.add(approveData66);
////		ApproveData approveData70 = new ApproveData();
////		approveData70.setClassName("Member");
////		approveData70.setFieldName("simpleName");
////		approveData70.setClassId("11");
////		approveData70.setNewValue("44");
////		approveDataList.add(approveData70);
////		ApproveData approveData71 = new ApproveData();
////		approveData71.setClassName("Member");
////		approveData71.setFieldName("bussType");
////		approveData71.setClassId("11");
////		approveData71.setNewValue("44");
////		approveDataList.add(approveData71);
////		ApproveData approveData72 = new ApproveData();
////		approveData72.setClassName("Member");
////		approveData72.setFieldName("isTranGoods");
////		approveData72.setClassId("11");
////		approveData72.setNewValue("1");
////		approveDataList.add(approveData72);
////		ApproveData approveData73 = new ApproveData();
////		approveData73.setClassName("Member");
////		approveData73.setFieldName("areaId");
////		approveData73.setClassId("11");
////		approveData73.setNewValue("44");
////		approveDataList.add(approveData73);
////		ApproveData approveData74 = new ApproveData();
////		approveData74.setClassName("Member");
////		approveData74.setFieldName("deptId");
////		approveData74.setClassId("11");
////		approveData74.setNewValue("44");
////		approveDataList.add(approveData74);
////		ApproveData approveData75 = new ApproveData();
////		approveData75.setClassName("Member");
////		approveData75.setFieldName("custId");
////		approveData75.setClassId("11");
////		approveData75.setNewValue("44");
////		approveDataList.add(approveData75);
////		ApproveData approveData76 = new ApproveData();
////		approveData76.setClassName("Member");
////		approveData76.setFieldName("contactId");
////		approveData76.setClassId("11");
////		approveData76.setNewValue("44");
////		approveDataList.add(approveData76);
////		ApproveData approveData77 = new ApproveData();
////		approveData77.setClassName("Member");
////		approveData77.setFieldName("upgradeSource");
////		approveData77.setClassId("11");
////		approveData77.setNewValue("44");
////		approveDataList.add(approveData77);
////		ApproveData approveData78 = new ApproveData();
////		approveData78.setClassName("Member");
////		approveData78.setFieldName("custStatus");
////		approveData78.setClassId("11");
////		approveData78.setNewValue("44");
////		approveDataList.add(approveData78);
////		ApproveData approveData79 = new ApproveData();
////		approveData79.setClassName("Member");
////		approveData79.setFieldName("responsibillity");
////		approveData79.setClassId("11");
////		approveData79.setNewValue("44");
////		approveDataList.add(approveData79);
////		ApproveData approveData80 = new ApproveData();
////		approveData80.setClassName("Member");
////		approveData80.setFieldName("versionNumber");
////		approveData80.setClassId("11");
////		approveData80.setNewValue("44");
////		approveDataList.add(approveData80);
////		ApproveData approveData81 = new ApproveData();
////		approveData81.setClassName("Member");
////		approveData81.setFieldName("nextLevel");
////		approveData81.setClassId("11");
////		approveData81.setNewValue("44");
////		approveDataList.add(approveData81);
////		ApproveData approveData82 = new ApproveData();
////		approveData82.setClassName("Member");
////		approveData82.setFieldName("jshAddress");
////		approveData82.setClassId("11");
////		approveData82.setNewValue("44");
////		approveDataList.add(approveData82);
////		ApproveData approveData83 = new ApproveData();
////		approveData83.setClassName("ShuttleAddress");
////		approveData83.setFieldName("addressType");
////		approveData83.setClassId("11");
////		approveData83.setNewValue("44");
////		approveDataList.add(approveData83);
////		ApproveData approveData84 = new ApproveData();
////		approveData84.setClassName("ShuttleAddress");
////		approveData84.setFieldName("address");
////		approveData84.setClassId("11");
////		approveData84.setNewValue("44");
////		approveDataList.add(approveData84);
////		ApproveData approveData85 = new ApproveData();
////		approveData85.setClassName("ShuttleAddress");
////		approveData85.setFieldName("province");
////		approveData85.setClassId("11");
////		approveData85.setNewValue("44");
////		approveDataList.add(approveData85);
////		ApproveData approveData86 = new ApproveData();
////		approveData86.setClassName("ShuttleAddress");
////		approveData86.setFieldName("city");
////		approveData86.setClassId("11");
////		approveData86.setNewValue("44");
////		approveDataList.add(approveData86);
////		ApproveData approveData87 = new ApproveData();
////		approveData87.setClassName("ShuttleAddress");
////		approveData87.setFieldName("area");
////		approveData87.setClassId("11");
////		approveData87.setNewValue("44");
////		approveDataList.add(approveData87);
////		ApproveData approveData88 = new ApproveData();
////		approveData88.setClassName("ShuttleAddress");
////		approveData88.setFieldName("postcode");
////		approveData88.setClassId("11");
////		approveData88.setNewValue("44");
////		approveDataList.add(approveData88);
////		ApproveData approveData89 = new ApproveData();
////		approveData89.setClassName("ShuttleAddress");
////		approveData89.setFieldName("memberId");
////		approveData89.setClassId("11");
////		approveData89.setNewValue("44");
////		approveDataList.add(approveData89);
////		ApproveData approveData90 = new ApproveData();
////		approveData90.setClassName("Contact");
////		approveData90.setFieldName("number");
////		approveData90.setClassId("11");
////		approveData90.setNewValue("44");
////		approveDataList.add(approveData90);
////		ApproveData approveData91 = new ApproveData();
////		approveData91.setClassName("Contact");
////		approveData91.setFieldName("name");
////		approveData91.setClassId("11");
////		approveData91.setNewValue("44");
////		approveDataList.add(approveData91);
////		ApproveData approveData92 = new ApproveData();
////		approveData92.setClassName("Contact");
////		approveData92.setFieldName("idCard");
////		approveData92.setClassId("11");
////		approveData92.setNewValue("44");
////		approveDataList.add(approveData92);
////		ApproveData approveData93 = new ApproveData();
////		approveData93.setClassName("Contact");
////		approveData93.setFieldName("linkmanType");
////		approveData93.setClassId("11");
////		approveData93.setNewValue("44");
////		approveDataList.add(approveData93);
////		ApproveData approveData94 = new ApproveData();
////		approveData94.setClassName("Contact");
////		approveData94.setFieldName("isMainLinkMan");
////		approveData94.setClassId("11");
////		approveData94.setNewValue("1");
////		approveDataList.add(approveData94);
////		ApproveData approveData95 = new ApproveData();
////		approveData95.setClassName("Contact");
////		approveData95.setFieldName("sex");
////		approveData95.setClassId("11");
////		approveData95.setNewValue("44");
////		approveDataList.add(approveData95);
////		ApproveData approveData96 = new ApproveData();
////		approveData96.setClassName("Contact");
////		approveData96.setFieldName("telPhone");
////		approveData96.setClassId("11");
////		approveData96.setNewValue("44");
////		approveDataList.add(approveData96);
////		ApproveData approveData97 = new ApproveData();
////		approveData97.setClassName("Contact");
////		approveData97.setFieldName("mobilePhone");
////		approveData97.setClassId("11");
////		approveData97.setNewValue("44");
////		approveDataList.add(approveData97);
////		ApproveData approveData98 = new ApproveData();
////		approveData98.setClassName("Contact");
////		approveData98.setFieldName("duty");
////		approveData98.setClassId("11");
////		approveData98.setNewValue("44");
////		approveDataList.add(approveData98);
////		ApproveData approveData99 = new ApproveData();
////		approveData99.setClassName("Contact");
////		approveData99.setFieldName("dutyDept");
////		approveData99.setClassId("11");
////		approveData99.setNewValue("44");
////		approveDataList.add(approveData99);
////		ApproveData approveData100 = new ApproveData();
////		approveData100.setClassName("Contact");
////		approveData100.setFieldName("gainave");
////		approveData100.setClassId("11");
////		approveData100.setNewValue("44");
////		approveDataList.add(approveData100);
////		ApproveData approveData101 = new ApproveData();
////		approveData101.setClassName("Contact");
////		approveData101.setFieldName("decisionRight");
////		approveData101.setClassId("11");
////		approveData101.setNewValue("44");
////		approveDataList.add(approveData101);
////		ApproveData approveData102 = new ApproveData();
////		approveData102.setClassName("Contact");
////		approveData102.setFieldName("nativePlace");
////		approveData102.setClassId("11");
////		approveData102.setNewValue("44");
////		approveDataList.add(approveData102);
////		ApproveData approveData103 = new ApproveData();
////		approveData103.setClassName("Contact");
////		approveData103.setFieldName("personLove");
////		approveData103.setClassId("11");
////		approveData103.setNewValue("44");
////		approveDataList.add(approveData103);
////		ApproveData approveData104 = new ApproveData();
////		approveData104.setClassName("Contact");
////		approveData104.setFieldName("folk");
////		approveData104.setClassId("11");
////		approveData104.setNewValue("44");
////		approveDataList.add(approveData104);
////		ApproveData approveData105 = new ApproveData();
////		approveData105.setClassName("Contact");
////		approveData105.setFieldName("email");
////		approveData105.setClassId("11");
////		approveData105.setNewValue("44");
////		approveDataList.add(approveData105);
////		ApproveData approveData106 = new ApproveData();
////		approveData106.setClassName("Contact");
////		approveData106.setFieldName("qqNumber");
////		approveData106.setClassId("11");
////		approveData106.setNewValue("44");
////		approveDataList.add(approveData106);
////		ApproveData approveData107 = new ApproveData();
////		approveData107.setClassName("Contact");
////		approveData107.setFieldName("msn");
////		approveData107.setClassId("11");
////		approveData107.setNewValue("44");
////		approveDataList.add(approveData107);
////		ApproveData approveData108 = new ApproveData();
////		approveData108.setClassName("Contact");
////		approveData108.setFieldName("ww");
////		approveData108.setClassId("11");
////		approveData108.setNewValue("44");
////		approveDataList.add(approveData108);
////		ApproveData approveData109 = new ApproveData();
////		approveData109.setClassName("Contact");
////		approveData109.setFieldName("alid");
////		approveData109.setClassId("11");
////		approveData109.setNewValue("44");
////		approveDataList.add(approveData109);
////		ApproveData approveData110 = new ApproveData();
////		approveData110.setClassName("Contact");
////		approveData110.setFieldName("onlineBusinessId");
////		approveData110.setClassId("11");
////		approveData110.setNewValue("44");
////		approveDataList.add(approveData110);
////		ApproveData approveData111 = new ApproveData();
////		approveData111.setClassName("Contact");
////		approveData111.setFieldName("taobId");
////		approveData111.setClassId("11");
////		approveData111.setNewValue("44");
////		approveDataList.add(approveData111);
////		ApproveData approveData112 = new ApproveData();
////		approveData112.setClassName("Contact");
////		approveData112.setFieldName("youshangId");
////		approveData112.setClassId("11");
////		approveData112.setNewValue("44");
////		approveDataList.add(approveData112);
////		ApproveData approveData113 = new ApproveData();
////		approveData113.setClassName("Contact");
////		approveData113.setFieldName("accountId");
////		approveData113.setClassId("11");
////		approveData113.setNewValue("44");
////		approveDataList.add(approveData113);
////		ApproveData approveData114 = new ApproveData();
////		approveData114.setClassName("Contact");
////		approveData114.setFieldName("acceptDeptid");
////		approveData114.setClassId("11");
////		approveData114.setNewValue("44");
////		approveDataList.add(approveData114);
////		ApproveData approveData115 = new ApproveData();
////		approveData115.setClassName("Contact");
////		approveData115.setFieldName("status");
////		approveData115.setClassId("11");
////		approveData115.setNewValue("44");
////		approveDataList.add(approveData115);
////		ApproveData approveData116 = new ApproveData();
////		approveData116.setClassName("Contact");
////		approveData116.setFieldName("defaultId");
////		approveData116.setClassId("11");
////		approveData116.setNewValue("44");
////		approveDataList.add(approveData116);
////		ApproveData approveData117 = new ApproveData();
////		approveData117.setClassName("Contact");
////		approveData117.setFieldName("versionId");
////		approveData117.setClassId("11");
////		approveData117.setNewValue("44");
////		approveDataList.add(approveData117);
////		ApproveData approveData118 = new ApproveData();
////		approveData118.setClassName("Contact");
////		approveData118.setFieldName("custId");
////		approveData118.setClassId("11");
////		approveData118.setNewValue("44");
////		approveDataList.add(approveData118);
//////		Assert.assertTrue(alterMemberService.updateMemberInfo(approveDataList));
////	}
////
////}
