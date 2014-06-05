package com.deppon.crm.module.customer.server.manager.impl;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.shared.domain.ChannelCustomer;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.exception.CustomerException;
import com.deppon.crm.module.customer.shared.exception.CustomerExceptionType;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;
import com.opensymphony.xwork2.inject.Context;

public class MemberManagerDataTest extends BeanUtil {
	//创建时返回的memberId
	String custNumber = "";
	@Before
	public void setUp() throws Exception {
		clearDataForSetUp();
		initCreateData();
		User user = new User();
		Department depart = new Department();
		depart.setId("86301");
		depart.setDeptName("总裁");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setId("267254");
		e.setDeptId(depart);
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
	}

	private void initCreateData() throws SQLException {
		List<String> sqlList = new ArrayList<String>();
		String custSql = "INSERT INTO T_CUST_CUSTBASEDATA"+
				"(FID, FCUSTNUMBER,FDEGREE,FCUSTNAME,FCUSTTYPE,FTAXREGNUMBER, FCUSTNATURE,FPROVINCE,FPROVINCEID,FCITY,FCITYID,FISSPECIAL,FISREDEEMPOINTS,FISIMPORTCUSTOR,FISACCEPTMARKET,FCHANNELSOURCE,FISFOCUSPAY,FISPARENTCOMPANY,FPROCREDIT,FDEPTID,FCONTACTID, FCUSTSTATUS,FCOMPADDR, FCUSTCATEGORY,FCUSTGROUP)"+ 
				"values(40003530,'40003530','','官网已存在客户验证', 'PERSONAL','', 'LEAVE_CUSTOMER', '','3','','3','0','0','0','0','ALIBABA',0,1,0.0,'49311', null,'0','上海-上海市-','LTT','PC_CUSTOMER')";
		String linkManSql = "insert into t_cust_custlinkman"+
				"(FID,FNUMBER,FNAME, FIDCARD,FLINKMANTYPE,FISMAINLINKMAN,FSEX, FOFFERTEL,FMOBILETEL,FSTATUS,FCUSTID,FBELONGCUSTTYPE)"+
				"values(40003121,'yczkh19196969696','已存在客户','','0,0,1,0,0', 1,'WOMAN', '021-455878','19196969696','0','40003530','PERSONAL')";		
		String extendSql = "INSERT INTO T_CUST_CUSTBASEDATA_EXTEND"+ 
									"(FCUSTID, FDEVELOPMENTPHASE)"+
								"VALUES('40003530','IDENTIFY_NEEDS')";  
		//插入客户基本信息
		sqlList.add(custSql);
		//插入联系人信息
		sqlList.add(linkManSql);
		//插入扩展信息
		sqlList.add(extendSql);
		//创建一个散客 
		String scatter_custSql = "INSERT INTO T_CUST_CUSTBASEDATA"+
				"(FID, FCUSTNUMBER,FDEGREE,FCUSTNAME,FCUSTTYPE,FTAXREGNUMBER, FCUSTNATURE,FPROVINCE,FPROVINCEID,FCITY,FCITYID,FISSPECIAL,FISREDEEMPOINTS,FISIMPORTCUSTOR,FISACCEPTMARKET,FCHANNELSOURCE,FISFOCUSPAY,FISPARENTCOMPANY,FPROCREDIT,FDEPTID,FCONTACTID, FCUSTSTATUS,FCOMPADDR, FCUSTCATEGORY,FCUSTGROUP)"+ 
				"values(100035900,'100035900','','已存在散客验证', 'PERSONAL','', 'LEAVE_CUSTOMER', '','3','','3','0','0','0','0','ALIBABA',0,1,0.0,'49311', null,'0','上海-上海市-','LTT','SC_CUSTOMER')";
		String scatter_linkManSql = "insert into t_cust_custlinkman"+
				"(FID,FNUMBER,FNAME, FIDCARD,FLINKMANTYPE,FISMAINLINKMAN,FSEX, FOFFERTEL,FMOBILETEL,FSTATUS,FCUSTID,FBELONGCUSTTYPE)"+
				"values(100031210,'yczsk19296969696','已存在散客','','0,0,1,0,0', 1,'WOMAN', '021-455878','19296969696','0','100035900','PERSONAL')";		
		String scatter_extendSql = "INSERT INTO T_CUST_CUSTBASEDATA_EXTEND"+ 
									"(FCUSTID, FDEVELOPMENTPHASE)"+
								"VALUES('100035900','BEGAN_SHIPPING')"; 
		String scatter_contactBindSql = "INSERT INTO t_user_contactbind"+
								"(FID,fcreatetime,FCREATEUSERID,FUSERNAME,FCONTACTID,FOPERATETYPE,FORDERSOURCE,FLINKMANNAME,FMOBILEPHONE,FTELEPHONE)"
								+ "VALUES(11790460,SYSDATE,'86301','ycz19296969696','100031210','1','ONLINE','已存在散客','19296969696','')";
		//插入客户基本信息
		sqlList.add(scatter_custSql);
		//插入联系人信息
		sqlList.add(scatter_linkManSql);
		//插入扩展信息
		sqlList.add(scatter_extendSql);
		//插入绑定表信息
		sqlList.add(scatter_contactBindSql);
		SpringTestHelper.executeBatch(sqlList);
	}

	private void clearDataForSetUp() throws SQLException {
		List<String> sqlList = new ArrayList<String>();
		String custSql = "DELETE FROM t_cust_custbasedata cu WHERE cu.fcustnumber = '"+custNumber+"'";
		String extendSql = "DELETE FROM t_cust_custbasedata_extend ce "+
							"WHERE ce.fcustid = (SELECT cu.fid FROM t_cust_custbasedata cu WHERE cu.fcustnumber = '"+custNumber+"')";
		//删除官网创建时联系人test_OWS_createChannelCustomer()
		sqlList.add(" DELETE FROM t_cust_custlinkman cl WHERE cl.fmobiletel = '17565656565'");
		
		//删除CC创建时联系人 test_CC_createChannelCustomer_1
		sqlList.add(" DELETE FROM t_cust_custlinkman cl WHERE cl.fmobiletel = '12193939393'");
		sqlList.add(" DELETE FROM t_cust_custlinkman cl WHERE cl.fmobiletel = '16387878787'");
		//删除扩展信息
		sqlList.add(extendSql);
		//删除客户基本信息
		sqlList.add(custSql);
		
		//删除绑定表信息
		sqlList.add("DELETE FROM t_user_contactbind cb WHERE cb.fusername = 'ows17565656565' AND cb.fmobilephone = '17565656565'");
		
		//初始化数据先清理下test_OWS_createChannelCustomer_2()
		//删除联系人
		sqlList.add(" DELETE FROM t_cust_custlinkman cl WHERE cl.fmobiletel = '19196969696'");
		//删除扩展信息
		sqlList.add("delete from t_cust_custbasedata_extend ce where ce.fcustid = '40003530'");
		//删除客户基本信息
		sqlList.add("delete from t_cust_custbasedata cu where cu.fid = '40003530'");
		
		//删除暂存表信息
		sqlList.add("DELETE FROM t_cust_channelcustomer cu WHERE cu.fcustname = '暂存用户01'");
		
		
//		//删除联系人test_OWS_updateChannelCustomer_2()
		sqlList.add(" DELETE FROM t_cust_custlinkman cl WHERE cl.fmobiletel = '19296969696'");
		//删除扩展信息
		sqlList.add("delete from t_cust_custbasedata_extend ce where ce.fcustid = '100035900'");
		//删除客户基本信息
		sqlList.add("delete from t_cust_custbasedata cu where cu.fid = '100035900'");
		//删除绑定表信息
		sqlList.add("DELETE FROM t_user_contactbind cb WHERE cb.fid = 11790460");
	    //	删除暂存表
		sqlList.add("DELETE FROM t_cust_channelcustomer ch WHERE ch.fchannelsource = 'ONLINEHALL' AND ch.fcustname = 'ycz19296969696'");
		//删除锁定信息
		sqlList.add("DELETE FROM t_cust_approvingworkflowdata aw WHERE aw.fcontactmobile = '19896969696' AND aw.fstatus = '1'");
	
		SpringTestHelper.executeBatch(sqlList);
	}

	@After
	public void tearDown() throws Exception {
		clearData();
	}

	private void clearData() throws SQLException {
		List<String> sqlList = new ArrayList<String>();
		String custSql = "DELETE FROM t_cust_custbasedata cu WHERE cu.fcustnumber = '"+custNumber+"'";
		String extendSql = "DELETE FROM t_cust_custbasedata_extend ce "+
							"WHERE ce.fcustid = (SELECT cu.fid FROM t_cust_custbasedata cu WHERE cu.fcustnumber = '"+custNumber+"')";
		//删除联系人test_OWS_createChannelCustomer()
		sqlList.add(" DELETE FROM t_cust_custlinkman cl WHERE cl.fmobiletel = '17565656565'");
		
		//删除CC创建时联系人 test_CC_createChannelCustomer_1
		sqlList.add(" DELETE FROM t_cust_custlinkman cl WHERE cl.fmobiletel = '12193939393'");
		sqlList.add(" DELETE FROM t_cust_custlinkman cl WHERE cl.fmobiletel = '16387878787'");
		//删除扩展信息
		sqlList.add(extendSql);
		//删除客户基本信息
		sqlList.add(custSql);
		//删除绑定表信息
		sqlList.add("DELETE FROM t_user_contactbind cb WHERE cb.fusername = 'ows17565656565' AND cb.fmobilephone = '17565656565'");
		
		
//		删除联系人test_OWS_createChannelCustomer_2()
		sqlList.add(" DELETE FROM t_cust_custlinkman cl WHERE cl.fmobiletel = '19196969696'");
		//删除扩展信息
		sqlList.add("delete from t_cust_custbasedata_extend ce where ce.fcustid = '40003530'");
		//删除客户基本信息
		sqlList.add("delete from t_cust_custbasedata cu where cu.fid = '40003530'");
		//删除暂存表信息
		sqlList.add("DELETE FROM t_cust_channelcustomer cu WHERE cu.fcustname = '暂存用户01'");
		
//		//删除联系人test_OWS_updateChannelCustomer_2()
		sqlList.add(" DELETE FROM t_cust_custlinkman cl WHERE cl.fmobiletel = '19296969696'");
		//删除扩展信息
		sqlList.add("delete from t_cust_custbasedata_extend ce where ce.fcustid = '100035900'");
		//删除客户基本信息
		sqlList.add("delete from t_cust_custbasedata cu where cu.fid = '100035900'");
		//删除绑定表信息
		sqlList.add("DELETE FROM t_user_contactbind cb WHERE cb.fid = 11790460");
	    //	删除暂存表
		sqlList.add("DELETE FROM t_cust_channelcustomer ch WHERE ch.fchannelsource = 'ONLINEHALL' AND ch.fcustname = 'ycz19296969696'");
		//删除锁定信息
		sqlList.add("DELETE FROM t_cust_approvingworkflowdata aw WHERE aw.fcontactmobile = '19896969696' AND aw.fstatus = '1'");
		SpringTestHelper.executeBatch(sqlList);
	}
	/**
	 * 
	* @Title: test_OWS_createChannelCustomer
	* @Description: 官网创建 单元测试//场景一  该用户第一次注册 系统原来从不存在该用户和联系方式
	* @author chenaichun 
	* @param     设定文件
	* @date 2014年5月20日 上午9:57:38
	* @return void    返回类型
	* @throws
	* @update 2014年5月20日 上午9:57:38
	 */
	@Test
	public void test_OWS_createChannelCustomer() {
		//场景一  该用户第一次注册 系统原来从不存在该用户和联系方式
		ChannelCustomer cust = new ChannelCustomer();
		cust.setCustName("ows17565656565");
		try {
			memberManager.createChannelCutomer(cust);
		} catch (CustomerException e) {
			assertEquals("i18n.customer.ChanenCustomerDataNull", e.getErrorCode());
		}
		cust.setChannelSource(Constant.CUST_SOURCE_OWS);
		try {
			memberManager.createChannelCutomer(cust);
		} catch (IllegalArgumentException e) {
			assertEquals("手机，姓名，电话，值不全,参数调用不合法", e.getMessage());
		}
		cust.setMobilePhone("17565656565");
		cust.setLinkManName("联系人姓名官网");
		cust.setEmail("ows@deppon.com");
		cust.setErpId("121212");
		Map<String,String> owsMap= memberManager.createChannelCutomer(cust);
		custNumber = owsMap.get("custNumber");
		assertEquals("ows17565656565", owsMap.get("userName"));
		
		//场景二  该用户的联系方式 已经在cRm存在有效的客户，未绑定，那么这个时候只需要做绑定操作（审批中的不自动绑，只能手动绑了）
	}
	@Test
	public void test_OWS_createChannelCustomer_2() {
		//场景二  该用户的联系方式 已经在cRm存在有效的客户，未绑定，那么这个时候只需要做绑定操作（审批中的不自动绑，只能手动绑了）
		ChannelCustomer cust = new ChannelCustomer();
		cust.setChannelSource(Constant.CUST_SOURCE_OWS);
		cust.setCustName("ows19196969696");
		cust.setMobilePhone("19196969696");
		cust.setLinkManName("官网已存在客户验证");
		cust.setEmail("ows@deppon.com");
		Map<String,String> owsMap= memberManager.createChannelCutomer(cust);
		custNumber = owsMap.get("custNumber");
		assertEquals("ows19196969696", owsMap.get("userName"));
	}
    
	@Test
	public void test_CC_createChannelCustomer_1() {
		//场景1  该用户不存在于CRM系统中
		ChannelCustomer cust = new ChannelCustomer();
		cust.setChannelSource(Constant.CUST_SOURCE_CC);
		cust.setCustName("cc客户名称");
		//CC会传客户类型
		cust.setCustType(Constant.Person_Member);
		//CC 会传客户类别（零担快递）
		cust.setCustCategory(Constant.CUST_BUSTYPE_LINGDAN);
		cust.setErpId("93789897");
		List<Contact> contactList = new ArrayList<Contact>();
		Contact con_1 = new Contact();
		con_1.setMobilePhone("12193939393");
		con_1.setTelPhone("027-8585858");
		con_1.setName("主联系人姓名");
		con_1.setFfax("121");
		con_1.setEmail("cc93@deppon.com");
		contactList.add(con_1);
		Contact con_2 = new Contact();
		con_2.setMobilePhone("16387878787");
		con_2.setName("第二联系人姓名");
		con_2.setEmail("cc87@deppon.com");
		con_2.setFfax("222");
		contactList.add(con_2);
		cust.setContactList(contactList);
		Map<String,String> owsMap= memberManager.createChannelCutomer(cust);
		custNumber = owsMap.get("custNumber");
		assertEquals("success", owsMap.get("message"));
	}
	
	@Test
	public void test_CC_createChannelCustomer_2() {
		//场景二  该用户的联系方式 已经在cRm存在有效的客户， 创建失败
		ChannelCustomer cust = new ChannelCustomer();
		cust.setChannelSource(Constant.CUST_SOURCE_CC);
		cust.setCustName("cc客户名称");
		//CC会传客户类型
		cust.setCustType(Constant.Person_Member);
		//CC 会传客户类别（零担快递）
		cust.setCustCategory(Constant.CUST_BUSTYPE_LINGDAN);
		cust.setErpId("93789897");
		List<Contact> contactList = new ArrayList<Contact>();
		Contact con_1 = new Contact();
		con_1.setMobilePhone("19196969696");
		con_1.setTelPhone("027-8585858");
		con_1.setName("主联系人姓名");
		con_1.setFfax("121");
		con_1.setEmail("cc93@deppon.com");
		contactList.add(con_1);
		Map<String,String> owsMap= memberManager.createChannelCutomer(cust);
		assertEquals("fail", owsMap.get("message"));
	}
	
	@Test
	public void test_OWS_updateChannelCustomer_1() {
		//暂存表中只有1条对应的客户信息时，更新这个表
		ChannelCustomer cust_1 = new ChannelCustomer();
		cust_1.setChannelSource(Constant.CUST_SOURCE_OWS);
		cust_1.setCustName("暂存用户01");
		memberService.saveChannelCustomer(cust_1);
		//官网修改 ，暂存表中只有1条对应的客户信息时，更新这个表，不做修改，等定时器扫
		ChannelCustomer cust = new ChannelCustomer();
		cust.setChannelSource(Constant.CUST_SOURCE_OWS);
		String returnValue= memberManager.updateChannelCustomer(cust);
		assertEquals("官网用户名为空", returnValue);
		cust.setCustName("暂存用户01");
		cust.setEmail("test@deppon.com");
		returnValue= memberManager.updateChannelCustomer(cust);
		assertNull(returnValue);
		//暂存表中有两条用户信息
		ChannelCustomer cust_2 = new ChannelCustomer();
		cust_2.setChannelSource(Constant.CUST_SOURCE_OWS);
		cust_2.setCustName("暂存用户01");
		memberService.saveChannelCustomer(cust_2);
		returnValue= memberManager.updateChannelCustomer(cust);
		assertEquals("Channel customer more than 2!", returnValue);
		
		//不存在暂存表表中，绑定表中没有 异常
		cust.setCustName("官网修改不在绑定表");
		returnValue= memberManager.updateChannelCustomer(cust);
		assertEquals("Member manager----con't find registerInfo!", returnValue);
		
		//不存在暂存表，绑定表中只有一条，CRM存在有效客户
		//先创建一个官网用户
		ChannelCustomer newCust = new ChannelCustomer();
		newCust.setChannelSource(Constant.CUST_SOURCE_OWS);
		newCust.setCustName("ows17565656565");
		newCust.setMobilePhone("17565656565");
		newCust.setLinkManName("联系人姓名官网");
		newCust.setEmail("ows@deppon.com");
		newCust.setErpId("121212");
		Map<String,String> owsMap= memberManager.createChannelCutomer(newCust);
		custNumber = owsMap.get("custNumber");
		cust.setCustName("ows17565656565");
		//不修改关键信息  ，修改email，地址,旧信息会传回来到cust里面去
		cust.setMobilePhone("17565656565");
		cust.setLinkManName("联系人姓名官网");
		cust.setErpId("121212");
		cust.setEmail("modifyEmail@deppon.com");
		cust.setCity("3");
		cust.setProvince("3");
		cust.setAddress("修改后地址--");
		returnValue = memberManager.updateChannelCustomer(cust);
		assertNull(returnValue);
		
		//修改的手机号已经在CRM中存在
		cust.setMobilePhone("19196969696");
		returnValue= memberManager.updateChannelCustomer(cust);
		assertEquals("联系方式相同，不允许修改", returnValue);
		
	}
	@Test
	public void test_OWS_updateChannelCustomer_2() {
		ChannelCustomer cust = new ChannelCustomer();
		cust.setChannelSource(Constant.CUST_SOURCE_OWS);
		cust.setCustName("ycz19296969696");
		cust.setMobilePhone("19896969696");
		cust.setLinkManName("已存在散客");
		cust.setErpId("121212");
		cust.setEmail("modifyEmail@deppon.com");
		cust.setCity("3");
		cust.setProvince("3");
		cust.setAddress("修改后地址--");
		String returnValue = memberManager.updateChannelCustomer(cust);
		assertNull(returnValue);
	}
	
}
