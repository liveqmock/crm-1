package com.deppon.crm.module.customer.server.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.testutils.DateInitUtil;
import com.deppon.crm.module.customer.server.testutils.TestUtils;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.DevelopmentLog;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberDemotionList;
import com.deppon.crm.module.customer.shared.domain.MemberUpgradeList;
import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;
import com.deppon.crm.module.customer.shared.domain.ShuttleAddress;
import com.deppon.crm.module.customer.shared.domain.UpGradeCustomer;
import com.deppon.crm.module.customer.shared.domain.UpGradeCustomerCondition;

public class MemberDaoTest extends BeanUtil{
	/*
	static{
		try {
			DateInitUtil.executeCleanSql();
			Runtime.getRuntime().addShutdownHook(new Thread(){
				@Override
				public void run() {
					try {
						DateInitUtil.executeInitSql();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public void setUp() throws Exception {
		DateInitUtil.executeCleanSql();
		DateInitUtil.executeInitSql();
	}
	
	public void tearDown() throws Exception{
		DateInitUtil.executeCleanSql();
	}
	
	@Test
	//TODO 稍后完成
	public void testSearchMemberDemotionList(){
		List<String> depts = null;
		//此单元测试并没有保证查询出来的值都是一一映射正确
		UpGradeCustomerCondition condition = null;
		//边界值
		try{
			memberDao.searchMemberDemotionList(condition,0, 100).size();
			Assert.fail();
		}catch(IllegalArgumentException e){
			Assert.assertTrue(true);
		}
		//TODO 数据没有连起来，所以测试跑不过
//		
//		condition =	new UpGradeCustomerCondition();
//		AssertSearchMemberDemotionList(condition,2);
//		
//		//单一条件
//		condition =	new UpGradeCustomerCondition();
//		depts = new ArrayList<String>();
//		depts.add("1");
//		condition.setDepts(depts);
//		condition.setStatisticsTime("2012");
//		AssertSearchMemberDemotionList(condition,2);
//		
//		condition = new UpGradeCustomerCondition();
//		depts = new ArrayList<String>();
//		depts.add("1");
//		condition.setDepts(depts);
//		condition.setStatisticsTime("2012");
//		condition.setContactName("鲍相江");
//		AssertSearchMemberDemotionList(condition,1);
//
//		condition = new UpGradeCustomerCondition();
//		depts = new ArrayList<String>();
//		depts.add("1");
//		condition.setDepts(depts);
//		condition.setStatisticsTime("2012");
//		condition.setContactName("李学兴");
//		AssertSearchMemberDemotionList(condition,1);
//		
//		condition = new UpGradeCustomerCondition();
//		depts = new ArrayList<String>();
//		depts.add("1");
//		condition.setDepts(depts);
//		condition.setStatisticsTime("2012");
//		condition.setCustName("李大仙");
//		AssertSearchMemberDemotionList(condition,1);
//		
//		condition = new UpGradeCustomerCondition();
//		depts = new ArrayList<String>();
//		depts.add("1");
//		condition.setDepts(depts);		
//		condition.setStatisticsTime("2012");
//		condition.setTel("4332112");
//		AssertSearchMemberDemotionList(condition,1);
//		
//		condition = new UpGradeCustomerCondition();
//		depts = new ArrayList<String>();
//		depts.add("1");
//		condition.setDepts(depts);		
//		condition.setStatisticsTime("2012");
//		condition.setPhone("13524304157");
//		AssertSearchMemberDemotionList(condition,1);
//		
//		condition = new UpGradeCustomerCondition();
//		depts = new ArrayList<String>();
//		depts.add("1");
//		condition.setDepts(depts);		
//		condition.setStatisticsTime("2012");
//		condition.setNowLevel("PLATINUM");
//		AssertSearchMemberDemotionList(condition,1);
//		
//		condition = new UpGradeCustomerCondition();
//		depts = new ArrayList<String>();
//		depts.add("1");
//		condition.setDepts(depts);		
//		condition.setStatisticsTime("2012");
//		condition.setTargetLevel("NORMAL");
//		AssertSearchMemberDemotionList(condition,2);
//		
//		//组合条件
//		condition = new UpGradeCustomerCondition();
//		condition.setDept("1");
//		condition.setStatisticsTime("2012");
//		condition.setTargetLevel("NORMAL");
//		condition.setContactName("李学兴");
//		condition.setCustName("李大仙");
//		condition.setTel("4332112");
//		condition.setPhone("13524304158");
//		condition.setNowLevel("PLATINUM");
//		AssertSearchMemberDemotionList(condition,1);
//		
//		condition = new UpGradeCustomerCondition();
//		condition.setDept("1");
//		condition.setStatisticsTime("2012");
//		condition.setTargetLevel("NORMAL");
//		condition.setContactName("李学兴");
//		condition.setCustName("李大仙");
//		condition.setTel("4332112");
//		condition.setPhone("13524304158");
//		condition.setNowLevel("PLATINUM2");
//		AssertSearchMemberDemotionList(condition,0);
//		
//		condition = new UpGradeCustomerCondition();
//		depts = new ArrayList<String>();
//		depts.add("1");
//		condition.setDepts(depts);		
//		condition.setStatisticsTime("2012");
//		condition.setTargetLevel("NORMAL");
//		condition.setContactName("李学兴");
//		condition.setCustName("李大仙");
//		condition.setTel("4332112");
//		condition.setPhone("13524304150");
//		condition.setNowLevel("PLATINUM");
//		AssertSearchMemberDemotionList(condition,0);
//		
//		condition = new UpGradeCustomerCondition();
//		depts = new ArrayList<String>();
//		depts.add("1");
//		condition.setDepts(depts);		
//		condition.setStatisticsTime("2012");
//		condition.setTargetLevel("NORMAL");
//		condition.setContactName("李学兴");
//		condition.setCustName("李大仙");
//		condition.setTel("3213123");
//		condition.setPhone("13524304158");
//		condition.setNowLevel("PLATINUM");
//		AssertSearchMemberDemotionList(condition,0);
//		
//		condition = new UpGradeCustomerCondition();
//		depts = new ArrayList<String>();
//		depts.add("1");
//		condition.setDepts(depts);		
//		condition.setStatisticsTime("2012");
//		condition.setTargetLevel("NORMAL");
//		condition.setContactName("李学兴");
//		condition.setCustName("李大2仙");
//		condition.setTel("4332112");
//		condition.setPhone("13524304158");
//		condition.setNowLevel("PLATINUM");
//		AssertSearchMemberDemotionList(condition,0);
//		
//		condition = new UpGradeCustomerCondition();
//		depts = new ArrayList<String>();
//		depts.add("1");
//		condition.setDepts(depts);		
//		condition.setStatisticsTime("2012");
//		condition.setTargetLevel("NORMAL");
//		condition.setContactName("李学2兴");
//		condition.setCustName("李大仙");
//		condition.setTel("4332112");
//		condition.setPhone("13524304158");
//		condition.setNowLevel("PLATINUM");
//		AssertSearchMemberDemotionList(condition,0);
//		
//		condition = new UpGradeCustomerCondition();
//		depts = new ArrayList<String>();
//		depts.add("1");
//		condition.setDepts(depts);		
//		condition.setStatisticsTime("2012");
//		condition.setTargetLevel("NORMAL2");
//		condition.setContactName("李学兴");
//		condition.setCustName("李大仙");
//		condition.setTel("4332112");
//		condition.setPhone("13524304158");
//		condition.setNowLevel("PLATINUM");
//		AssertSearchMemberDemotionList(condition,0);
//		
//		condition = new UpGradeCustomerCondition();
//		depts = new ArrayList<String>();
//		depts.add("1");
//		condition.setDepts(depts);		
//		condition.setStatisticsTime("2021");
//		condition.setTargetLevel("NORMAL");
//		condition.setContactName("李学兴");
//		condition.setCustName("李大仙");
//		condition.setTel("4332112");
//		condition.setPhone("13524304158");
//		condition.setNowLevel("PLATINUM");
//		AssertSearchMemberDemotionList(condition,0);
//		
//		condition = new UpGradeCustomerCondition();
//		depts = new ArrayList<String>();
//		depts.add("2");
//		condition.setDepts(depts);		
//		condition.setStatisticsTime("2012");
//		condition.setTargetLevel("NORMAL");
//		condition.setContactName("李学兴");
//		condition.setCustName("李大仙");
//		condition.setTel("4332112");
//		condition.setPhone("13524304158");
//		condition.setNowLevel("PLATINUM");
//		AssertSearchMemberDemotionList(condition,0);
//		
	}
	
	private void AssertSearchMemberDemotionList(UpGradeCustomerCondition condition,int size){
		Assert.assertEquals(memberDao.searchMemberDemotionList(condition,0, 100).size(),size);
	}
	
	
	//TODO 不知道啥问题，待完善
	public void testInsertAccount(){
		//TODO 此单元测试没有保证这个值是否准确插进去
		Account account = TestUtils.createBean(Account.class);
		String memberId = "40000225";
		String contactId = "40000438";
		account.setCreateUser("267254");
		memberDao.insertAccount(account, memberId, contactId);
		Assert.assertNotNull(account.getId());
	}
	@Test
	public void testInsertContact(){
		List<Contact> contactList = new ArrayList<Contact>();
		Contact contact = TestUtils.createBean(Contact.class);
//		contact.setNumber("123");
//		contact.setBornDate(new Date());
//		contact.setDuty("123");
//		contact.setLinkmanType("123");
//		contact.setCreateUser("267254");
//		contactList.add(contact);
//		memberDao.insertContactList(contactList, "","267254");
//		Assert.assertNotNull(contact.getId());

	}
	@Test
	public void testInsertPreferenceAddress(){
		PreferenceAddress preferenceAddress = TestUtils.createBean(PreferenceAddress.class);
		preferenceAddress.setCreateUser("267254");
		memberDao.insertPreferenceAddress(preferenceAddress, "", "");
		Assert.assertNotNull(preferenceAddress.getId());

	}
	@Test
	public void testInsertMemberBaseInfo(){
//		Member member = TestUtils.createBean(Member.class);
//		String memberId = memberDao.getMemberIdUseSeq();
//		member.setId(memberId);
//		member.setCreateUser("267254");
//		member.setFinOver(true);
////		member.setSimpleName("1234");
////		member.setDegree("1234");
////		member.setCustType("1234");
////		member.setBussType("1234");
////		member.setIsSpecial(0);
////		member.setTaxregNumber("123");
////		member.setIsTranGoods(0);
////		member.setIsRedeempoints(0);
////		member.setIsImportCustor(1);
//		
//		memberDao.insertMemberBaseInfo(member);
//		Assert.assertNotNull(member.getId());

	}
	@Test
	public void testInsertShuttleAddress(){
		List<ShuttleAddress> shuttleAddressList = new ArrayList<ShuttleAddress>();
		ShuttleAddress shuttleAddress = TestUtils.createBean(ShuttleAddress.class);
		shuttleAddress.setCreateUser("267254");
		shuttleAddressList.add(shuttleAddress);
		memberDao.insertShuttleAddressList(shuttleAddressList,"","267254");
		Assert.assertNotNull(shuttleAddress.getId());

	}
	
	@Test
	public void testGetUpGradeCustomerById(){
		UpGradeCustomer upGrade = memberDao.getUpGradeCustomerById("1");
		Assert.assertNotNull(upGrade);
		Assert.assertEquals("1",upGrade.getBelongDeptId());
		Assert.assertEquals("1",upGrade.getBelongdeptName());
		Assert.assertEquals("lxx",upGrade.getCustName());
		Assert.assertEquals("lxx",upGrade.getContactName());
		Assert.assertEquals("13588888888",upGrade.getContactPhone());
		Assert.assertEquals("021-5555555",upGrade.getContactTel());
		Assert.assertEquals("NORMAL",upGrade.getTargetLevel());
		Assert.assertEquals("2012-03",upGrade.getStatisticsTime());
		Assert.assertEquals("SCATTER_GRADE",upGrade.getCurrentlevel());

	}
	
	@Test
	public void testUpdateUpGradeCustomerById(){
		memberDao.updateUpGradeCustomerById("1","你好吃了没？","1");
		UpGradeCustomer upGrade = memberDao.getUpGradeCustomerById("1");
		Assert.assertEquals(upGrade.getRemark(), "你好吃了没？");
	}
	
	@Test
	public void testSearchUpGradeCustomerList(){
		UpGradeCustomerCondition condition = new UpGradeCustomerCondition();
		condition.setStatisticsTime("2012-03");
		condition.setTel("232177709");
		List<UpGradeCustomer> list = memberDao.searchUpGradeCustomerList(condition,0,100);
		Assert.assertEquals(list.size(),1);
	}

	@Test
	public void testGetCountUpGradeCustomerByUpGradeCustomerCondition(){
		UpGradeCustomerCondition condition = new UpGradeCustomerCondition();
		condition.setStatisticsTime("2012-03");
		condition.setCustName("l");
		int count = memberDao.getCountUpGradeCustomerByUpGradeCustomerCondition(condition);
		System.out.println(count);
	}
	
	@Test
	public void testSearchMemberUpgradeList(){
		UpGradeCustomerCondition condition = new UpGradeCustomerCondition();
		condition.setStatisticsTime("2012-03");
		condition.setDept("1");
		List<MemberUpgradeList> list = memberDao.searchMemberUpgradeList(condition, 1, 1);
		
	}
	
	@Test
	public void testGetMemberUpgradeListByStatisticsTime(){
		List<MemberUpgradeList> list = memberDao.getMemberUpgraeByStatisticsTime("2012-03");
	}
	
	@Test
	public void testGetMemberDeListByStatisticsTime(){
		List<MemberDemotionList> list = memberDao.getMemberDemotionByStatisticsTime("2012-03");
	}
	
	@Test
	public void testGetMothlyStatementBymemberId(){
		Double money = memberDao.getMothlyStatementBymemberId("123");
	}
	
	@Test
	public void testUpdateMothlyStatementByMemberId(){
		memberDao.updateMothlyStatementByMemberId("123",453,"267254");
	}
	
	@Test
	public void testInsertMothlyStatement(){
		memberDao.insertMothlyStatement("1231",10000);
	}
	/**
	 * 
	 * @Title: testMemberExistOrNot
	 *  <p>
	 * @Description: 测试通过客户编码查询固定客户信息
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-6-2
	 * @return void
	 * @throws
	 */
	@Test
	public void testMemberExistOrNot(){
//		boolean flag = false; 
//		//校验存在有效固定客户的情况		
////		flag = memberDao.memberExistOrNot("40000225");
//		Assert.assertTrue(flag);
//		//校验不存在有效固定客户的情况
//		flag = memberDao.memberExistOrNot("327942232");
//		Assert.assertFalse(flag);
	}
	
	/**
	 * 
	 * @Title: testSearchMemberExtendByID
	 *  <p>
	 * @Description: 测试根据客户ID查询客户扩展信息
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2014-4-10
	 * @return void
	 * @throws
	 */
	@Test
	public void testSearchMemberExtendByID(){
		Assert.assertNotNull(memberDao.searchMemberExtendByID("40000225"));
	}
	@Test
	public void testdeleteScatterUpgradeById(){
		memberDao.deleteScatterUpgradeById("123");
	}
	@Test
	public void testgetMemberCountByTaxregNumber(){
		memberDao.getMemberCountByTaxregNumber("123");
	}
	@Test
	public void testdeleteUpGradeCustomerByNumber(){
		memberDao.deleteUpGradeCustomerByNumber("123");
	}
	@Test
	public void testdeleteAccountByMemberId(){
		memberDao.deleteAccountByMemberId("123");
	}
	@Test
	public void testdeleteMemberInfoById(){
		memberDao.deleteMemberInfoById("123");
	}
	@Test
	public void testdeleteShuttleAddressByMemberId(){
		memberDao.deleteShuttleAddressByMemberId("123");
	}
	@Test
	public void testdeletePreferenceAddressByMemberId(){
		memberDao.deletePreferenceAddressByMemberId("123");
	}
	@Test
	public void testgetCountMemberUpgradeListByCondition(){
		UpGradeCustomerCondition condition = new UpGradeCustomerCondition();
		condition.setDept("123");
		condition.setCustNumber("123");
		memberDao.getCountMemberUpgradeListByCondition(condition);
	}
	
	@Test
	public void testUpdateDevelopmentLog(){
		DevelopmentLog log = new DevelopmentLog();
		log.setCreateDate(new Date());
		log.setCreateUser("86301");
		log.setCurrentStage("N0.1");
		log.setNextStage("NO.2");
		log.setCustId("-1");
		memberDao.updateDevelopmentLog(log);
	}
	
	@Test
	public void testQueryDevelopmentLog(){
		memberDao.queryDevelopmentLogs("-2");
	}
}
