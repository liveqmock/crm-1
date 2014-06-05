/**
 * 
 */
package com.deppon.crm.module.customer.server.manager.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.customer.server.testutils.DBUtils;
import com.deppon.crm.module.customer.server.testutils.TestUtils;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.UserUtil;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.ContractDept;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGift;

/**
 * @author Administrator
 *
 */
public class MainContactInvokeIntegralRuleTest extends BeanUtil{
	String contractId = null;
	String sourceId = null;
	String deptId = null;
	Contract contract = null;


	@Before
	public void setUp() throws Exception {
		UserUtil.setUserToAdmin();
		contract = TestUtils.createBean(Contract.class);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(contract.getContractendDate());
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		
		contract.setContractendDate(calendar.getTime());
		Member member = new Member();
		member.setId("1000001");
		contract.setMember(member);
		contract.setPayWay(Constant.CONTRACT_PAYWAY_NOT_MONTH_END);
		contract.setPreferentialType(Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND);
		ContractDept deptpart = TestUtils.createBean(ContractDept.class);
		List<ContractDept> deptList = new ArrayList<ContractDept>();
		deptList.add(deptpart);
		contract.setContractDepartList(deptList);
		ContractDept dept = TestUtils.createBean(ContractDept.class);
		List<ContractDept> depts = new ArrayList<ContractDept>();
		depts.add(dept);
		contract.setContractDepartList(depts);
		contract.setDept(ContextUtil.getCurrentUserDept());
		FileInfo info = TestUtils.createBean(FileInfo.class);
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		fileInfos.add(info);
		contract.setFileInfoList(fileInfos);
		DBUtils.initContract();
	}

	@After
	public void tearDown() throws Exception {
		 DBUtils.clean();
	}
	
	// 会员本期可用积分
	protected int currentUsableMemberScore = 0;
	// 主联系人积分
	protected ContactIntegral mainContactIntegral = null;
	protected ContactIntegral mainContactIntegral1 = null;
	protected ContactIntegral mainContactIntegral2 = null;
	// 其他联系人积分
	protected ContactIntegral[] contactIntegrals = null;
	// 礼品兑换所需总积分数
	protected int giftTotScore = 0;
	// 礼品组
	protected IntegralConvertGift[] integralConvertGifts = null;
	MainContactInvokeIntegralRule contactInvokeIntegralRule = new MainContactInvokeIntegralRule(mainContactIntegral, contactIntegrals, integralConvertGifts, currentUsableMemberScore);
	/**
	 * 
	 @Discript 
	 @author  唐亮
	 @date 2012-12-29
	 @return void
	 */
	@Test
	public void testGetBizIntegralOperation(){
		contactInvokeIntegralRule.getBizIntegralOperation();
	}
	/**
	 * 
	 @Discript  * @param mainContactIntegral
	 *            主联系人积分
	 * @param contactIntegrals
	 *            其它联系人积分
	 * @param integralConvertGifts
	 *            礼品数组
	 * @param currentUsableMemberScore
	 *            会员可用积分
	 @author  唐亮
	 @date 2012-12-29
	 @return void
	 */
	@Test
	public void testIsValidate(){
		//1.输入：条件参数为空 ，输出：false
		Assert.assertFalse(contactInvokeIntegralRule.isValidate());
	
//		IntegralConvertGift integralConvertGift = new IntegralConvertGift();
//		integralConvertGift.setDeptId("3306");
//		integralConvertGift.setGift(new Gift());
//		integralConvertGift.setWorkFlowId("1008612");
//		integralConvertGifts = new IntegralConvertGift[]{integralConvertGift};
//		currentUsableMemberScore = 100;
//		mainContactIntegral = new ContactIntegral();
//		mainContactIntegral1 = new ContactIntegral();
//		mainContactIntegral2 = new ContactIntegral();
//		mainContactIntegral.setCurrentUsableScore(98);
//		mainContactIntegral1.setCurrentUsableScore(99);
//		mainContactIntegral2.setCurrentUsableScore(97);
//		contactIntegrals =new ContactIntegral[]{mainContactIntegral,mainContactIntegral1,mainContactIntegral2};
		//Assert.assertTrue(contactInvokeIntegralRule.isValidate());
		contactInvokeIntegralRule.isValidate();
		mainContactIntegral =null;
		//1.输入：条件参数不为空 ，输出：
		mainContactIntegral = new ContactIntegral();
		contactInvokeIntegralRule = new MainContactInvokeIntegralRule(
				mainContactIntegral, contactIntegrals, integralConvertGifts, currentUsableMemberScore);
		contactInvokeIntegralRule.isValidate();
		mainContactIntegral =null;
		
		mainContactIntegral = new ContactIntegral();
		contactIntegrals =new ContactIntegral[]{};
		contactInvokeIntegralRule = new MainContactInvokeIntegralRule(
				mainContactIntegral, contactIntegrals, integralConvertGifts, currentUsableMemberScore);
		contactInvokeIntegralRule.isValidate();
		mainContactIntegral =null;
		
		mainContactIntegral = new ContactIntegral();
		contactIntegrals =new ContactIntegral[]{mainContactIntegral};
		contactInvokeIntegralRule = new MainContactInvokeIntegralRule(
				mainContactIntegral, contactIntegrals, integralConvertGifts, currentUsableMemberScore);
		contactInvokeIntegralRule.isValidate();
		contactInvokeIntegralRule =null;
		
		ContactIntegral mainContactIntegral = new ContactIntegral();
		ContactIntegral[] contactIntegrals =new ContactIntegral[]{mainContactIntegral};
		mainContactIntegral.setCurrentUsableScore(2);
		int currentUsableMemberScore = 3;
		contactInvokeIntegralRule = new MainContactInvokeIntegralRule(
				mainContactIntegral, contactIntegrals, integralConvertGifts, currentUsableMemberScore);
		contactInvokeIntegralRule.isValidate();
		contactInvokeIntegralRule =null;
		
		ContactIntegral mainContactIntegral1 = new ContactIntegral();
		ContactIntegral[] contactIntegrals1 =new ContactIntegral[]{mainContactIntegral};
		mainContactIntegral.setCurrentUsableScore(4);
		int currentUsableMemberScore1 = 3;
		contactInvokeIntegralRule = new MainContactInvokeIntegralRule(
				mainContactIntegral1, contactIntegrals1, integralConvertGifts, currentUsableMemberScore1);
		contactInvokeIntegralRule.isValidate();
		contactInvokeIntegralRule =null;
		
	}

}
