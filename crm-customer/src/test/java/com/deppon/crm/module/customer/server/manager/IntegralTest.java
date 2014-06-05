package com.deppon.crm.module.customer.server.manager;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.Gift;
import com.deppon.crm.module.customer.shared.domain.integral.HandRewardIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.Integral;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGift;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralOperation;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.RewardIntegRule;
import com.deppon.crm.module.customer.shared.exception.CustomerException;

public class IntegralTest extends BeanUtil{
	
	@Before
	public void setUp() {
	}

	@Test
	public void testAdjustIntegralRule() {
		ContactIntegral main = new ContactIntegral();// 主联系人
		ContactIntegral[] other = new ContactIntegral[] { new ContactIntegral() };// 其他联系人积分对象
		//MemberIntegral[] member = new MemberIntegral[] { new MemberIntegral() };

		Contact contact_from = new Contact();
		contact_from.setId("1");
		main.setContact(contact_from);
		main.setId("1");
		main.setCurrentUsableScore(2000);// 本期可用积分
		main.setCurrentTotalScore(2000);// 本期累计积分
		main.setTotalScore(2000);

		Contact contact_to = new Contact();
		contact_to.setId("2");

		other[0].setContact(contact_to);
		other[0].setId("2");
		other[0].setCurrentUsableScore(2000);
		other[0].setTotalToUsesScore(2000);
		other[0].setTotalScore(2000);

		// IIntegralRule iIntegralRule = new AdjustIntegralRule(from,to);
		IIntegralRule iIntegralRule = RuleFactory.getRule(
				RuleFactory.ADJUST_INTEGRAL, main, other, null, null, 0, null);

		IntegralOperation integralOperation = iIntegralRule
				.getBizIntegralOperation();
		List<ContactIntegral> list = integralOperation.getContactIntegral();

		ContactIntegral tf = list.get(0);
		Assert.assertEquals(tf.getId(), "1");
		Assert.assertEquals(tf.getCurrentUsableScore(), 4000);// 本期可用积分
		Assert.assertEquals(tf.getCurrentTotalScore(), 4000);// 本期累计积分
		Assert.assertEquals(tf.getTotalScore(), 4000);// 累计积分

		ContactIntegral rf = list.get(1);
		Assert.assertEquals(rf.getId(), "2");
		Assert.assertEquals(rf.getCurrentUsableScore(), 0);// 本期可用积分

		Assert.assertEquals(integralOperation.getIntegral().size(), 2);
		/**********************************************************************************/
		IIntegralRule iIntegralRule1 = RuleFactory.getRule(
				RuleFactory.ADJUST_INTEGRAL, main, null, null, null, 0, null);

		Assert.assertNull(iIntegralRule1);
		/******************************************************************************************/
		IIntegralRule iIntegralRule2 = RuleFactory.getRule(
				RuleFactory.ADJUST_INTEGRAL, null, other, null, null, 0, null);
		Assert.assertNull(iIntegralRule2);
		/********************************************************************************************/
		IIntegralRule iIntegralRule3 = RuleFactory.getRule(
				RuleFactory.ADJUST_INTEGRAL, main, other, null, null, 0, null);
		other[0].setCurrentUsableScore(0);
		IntegralOperation integralOperation3 = iIntegralRule3
				.getBizIntegralOperation();
		Assert.assertNotNull(iIntegralRule3);
		Assert.assertNull(integralOperation3);
	}

	public void testRewardIntegralRule_1() {
		RewardIntegRule rewardIntegRule = new RewardIntegRule();// 手动奖励规则
		int integralBasicNumber = 1000;// 手动奖励积分基数
		ContactIntegral[] contactIntegrals = new ContactIntegral[5];// 其他联系人积分对象
		MemberIntegral[] memberIntegrals = new MemberIntegral[3];// 其他联系人积分对象

		rewardIntegRule.setRuletype("比例");
		rewardIntegRule.setPointvalue(33333);
		rewardIntegRule.setPointbegintime(new Date(113,5,4));
		rewardIntegRule.setPointendtime(new Date(209,5,4));
		rewardIntegRule.setFraction(1.5);

		// 对联系人积分对象赋值
		Contact contact0 = new Contact();
		contact0.setId("1");
		contact0.setCustId("1");

		Contact contact1 = new Contact();
		contact1.setId("2");
		contact1.setCustId("1");

		Contact contact2 = new Contact();
		contact2.setId("3");
		contact2.setCustId("2");

		Contact contact3 = new Contact();
		contact3.setId("4");
		contact3.setCustId("2");

		Contact contact4 = new Contact();
		contact4.setId("5");
		contact4.setCustId("3");

		ContactIntegral contactIntegral0 = new ContactIntegral();
		contactIntegral0.setContact(contact0);
		contactIntegral0.setCurrentUsableScore(2000);// 本期可用积分
		contactIntegral0.setCurrentTotalScore(2000);// 本期累计积分
		contactIntegral0.setTotalScore(2000);// /累计积分--联系人获得的积分

		ContactIntegral contactIntegral1 = new ContactIntegral();
		contactIntegral1.setContact(contact1);
		contactIntegral1.setCurrentUsableScore(3000);// 本期可用积分
		contactIntegral1.setCurrentTotalScore(3000);// 本期累计积分
		contactIntegral1.setTotalScore(3000);// /累计积分--联系人获得的积分

		ContactIntegral contactIntegral2 = new ContactIntegral();
		contactIntegral2.setContact(contact2);
		contactIntegral2.setCurrentUsableScore(2000);// 本期可用积分
		contactIntegral2.setCurrentTotalScore(2000);// 本期累计积分
		contactIntegral2.setTotalScore(2000);// /累计积分--联系人获得的积分

		ContactIntegral contactIntegral3 = new ContactIntegral();
		contactIntegral3.setContact(contact3);
		contactIntegral3.setCurrentUsableScore(3000);// 本期可用积分
		contactIntegral3.setCurrentTotalScore(3000);// 本期累计积分
		contactIntegral3.setTotalScore(3000);// /累计积分--联系人获得的积分

		ContactIntegral contactIntegral4 = new ContactIntegral();
		contactIntegral4.setContact(contact4);
		contactIntegral4.setCurrentUsableScore(3000);// 本期可用积分
		contactIntegral4.setCurrentTotalScore(3000);// 本期累计积分
		contactIntegral4.setTotalScore(3000);// /累计积分--联系人获得的积分

		contactIntegrals[0] = contactIntegral0;
		contactIntegrals[1] = contactIntegral1;
		contactIntegrals[2] = contactIntegral2;
		contactIntegrals[3] = contactIntegral3;
		contactIntegrals[4] = contactIntegral4;

		Member member0 = new Member();
		member0.setCustId("1");
		member0.setId("1111");

		Member member1 = new Member();
		member1.setCustId("2");
		member1.setId("2222");

		Member member2 = new Member();
		member2.setCustId("3");
		member2.setId("3333");

		MemberIntegral memberIntegral0 = new MemberIntegral();
		memberIntegral0.setMember(member0);
		memberIntegral0.setCurrentUsableScore(1000);// 本期可用积分
		memberIntegral0.setCurrentTotalScore(1000);// 本期累计积分
		memberIntegral0.setTotalScore(1000);// /累计积分--联系人获得的积分

		MemberIntegral memberIntegral1 = new MemberIntegral();
		memberIntegral1.setMember(member1);
		memberIntegral1.setCurrentUsableScore(2000);// 本期可用积分
		memberIntegral1.setCurrentTotalScore(2000);// 本期累计积分
		memberIntegral1.setTotalScore(2000);// /累计积分--联系人获得的积分

		MemberIntegral memberIntegral2 = new MemberIntegral();
		memberIntegral2.setMember(member2);
		memberIntegral2.setCurrentUsableScore(3000);// 本期可用积分
		memberIntegral2.setCurrentTotalScore(3000);// 本期累计积分
		memberIntegral2.setTotalScore(3000);// /累计积分--联系人获得的积分

		memberIntegrals[0] = memberIntegral0;
		memberIntegrals[1] = memberIntegral1;
		memberIntegrals[2] = memberIntegral2;

		IIntegralRule iIntegralRule = RuleFactory.getRule(
				RuleFactory.REWARD_INTEGRAL, null, contactIntegrals,
				memberIntegrals, rewardIntegRule, integralBasicNumber, null);
		Assert.assertNotNull(iIntegralRule);

		IntegralOperation integralOperation = iIntegralRule
				.getBizIntegralOperation();
		List<ContactIntegral> contactIntegralList = integralOperation
				.getContactIntegral();
		List<MemberIntegral> memberIntegralList = integralOperation
				.getMemberIntegral();
		List<Integral> integralList = integralOperation.getIntegral();
		Assert.assertEquals(contactIntegralList.size(), 0);
		Assert.assertEquals(memberIntegralList.size(), 3);
		Assert.assertEquals(integralList.size(), 0);
		for (MemberIntegral memberIntegral : memberIntegralList) {
			if (memberIntegral.getMember().getCustId().equals("1")) {
				Assert.assertEquals(memberIntegral.getCurrentUsableScore(),
						1000);
			}
			if (memberIntegral.getMember().getCustId().equals("2")) {
				Assert.assertEquals(memberIntegral.getCurrentUsableScore(),
						2000);
			}
			if (memberIntegral.getMember().getCustId().equals("3")) {
				Assert.assertEquals(memberIntegral.getCurrentUsableScore(),
						3000);
			}
		}
		for (ContactIntegral contactIntegral : contactIntegralList) {
			if (contactIntegral.getContact().getId().equals("1")) {
				Assert.assertEquals(contactIntegral.getCurrentUsableScore(),
						3500);
			}
			if (contactIntegral.getContact().getId().equals("2")) {
				Assert.assertEquals(contactIntegral.getCurrentUsableScore(),
						4500);
			}
			if (contactIntegral.getContact().getId().equals("3")) {
				Assert.assertEquals(contactIntegral.getCurrentUsableScore(),
						3500);
			}
			if (contactIntegral.getContact().getId().equals("4")) {
				Assert.assertEquals(contactIntegral.getCurrentUsableScore(),
						4500);
			}
			if (contactIntegral.getContact().getId().equals("5")) {
				Assert.assertEquals(contactIntegral.getCurrentUsableScore(),
						4500);
			}
		}
		for (Integral integral : integralList) {
			if (integral instanceof HandRewardIntegral) {
				HandRewardIntegral h = (HandRewardIntegral) integral;
				Assert.assertEquals(h.getScore().intValue(), 1500);
			}
		}

	}

//	@Test
	public void testRewardIntegralRule_2() {
		RewardIntegRule rewardIntegRule = new RewardIntegRule();// 手动奖励规则
		int integralBasicNumber = 1000;// 手动奖励积分基数
		ContactIntegral[] contactIntegrals = new ContactIntegral[5];// 其他联系人积分对象
		MemberIntegral[] memberIntegrals = new MemberIntegral[3];// 会员积分对象

		rewardIntegRule.setRuletype("分值");
		rewardIntegRule.setPointvalue(33333);
		rewardIntegRule.setPointbegintime(new Date(113,5,4));
		rewardIntegRule.setPointendtime(new Date(209,5,4));
		rewardIntegRule.setFraction(1.5);

		// 对联系人积分对象赋值
		Contact contact0 = new Contact();
		contact0.setId("1");
		contact0.setCustId("1");

		Contact contact1 = new Contact();
		contact1.setId("2");
		contact1.setCustId("1");

		Contact contact2 = new Contact();
		contact2.setId("3");
		contact2.setCustId("2");

		Contact contact3 = new Contact();
		contact3.setId("4");
		contact3.setCustId("2");

		Contact contact4 = new Contact();
		contact4.setId("5");
		contact4.setCustId("3");

		ContactIntegral contactIntegral0 = new ContactIntegral();
		contactIntegral0.setContact(contact0);
		contactIntegral0.setCurrentUsableScore(2000);// 本期可用积分
		contactIntegral0.setCurrentTotalScore(2000);// 本期累计积分
		contactIntegral0.setTotalScore(2000);// /累计积分--联系人获得的积分

		ContactIntegral contactIntegral1 = new ContactIntegral();
		contactIntegral1.setContact(contact1);
		contactIntegral1.setCurrentUsableScore(3000);// 本期可用积分
		contactIntegral1.setCurrentTotalScore(3000);// 本期累计积分
		contactIntegral1.setTotalScore(3000);// /累计积分--联系人获得的积分

		ContactIntegral contactIntegral2 = new ContactIntegral();
		contactIntegral2.setContact(contact2);
		contactIntegral2.setCurrentUsableScore(2000);// 本期可用积分
		contactIntegral2.setCurrentTotalScore(2000);// 本期累计积分
		contactIntegral2.setTotalScore(2000);// /累计积分--联系人获得的积分

		ContactIntegral contactIntegral3 = new ContactIntegral();
		contactIntegral3.setContact(contact3);
		contactIntegral3.setCurrentUsableScore(3000);// 本期可用积分
		contactIntegral3.setCurrentTotalScore(3000);// 本期累计积分
		contactIntegral3.setTotalScore(3000);// /累计积分--联系人获得的积分

		ContactIntegral contactIntegral4 = new ContactIntegral();
		contactIntegral4.setContact(contact4);
		contactIntegral4.setCurrentUsableScore(3000);// 本期可用积分
		contactIntegral4.setCurrentTotalScore(3000);// 本期累计积分
		contactIntegral4.setTotalScore(3000);// /累计积分--联系人获得的积分

		contactIntegrals[0] = contactIntegral0;
		contactIntegrals[1] = contactIntegral1;
		contactIntegrals[2] = contactIntegral2;
		contactIntegrals[3] = contactIntegral3;
		contactIntegrals[4] = contactIntegral4;

		Member member0 = new Member();
		member0.setCustId("1");
		member0.setId("1111");

		Member member1 = new Member();
		member1.setCustId("2");
		member1.setId("2222");

		Member member2 = new Member();
		member2.setCustId("3");
		member2.setId("3333");
		
		MemberIntegral memberIntegral0 = new MemberIntegral();
		memberIntegral0.setMember(member0);
		memberIntegral0.setCurrentUsableScore(1000);// 本期可用积分
		memberIntegral0.setCurrentTotalScore(1000);// 本期累计积分
		memberIntegral0.setTotalScore(1000);// /累计积分--联系人获得的积分

		MemberIntegral memberIntegral1 = new MemberIntegral();
		memberIntegral1.setMember(member1);
		memberIntegral1.setCurrentUsableScore(2000);// 本期可用积分
		memberIntegral1.setCurrentTotalScore(2000);// 本期累计积分
		memberIntegral1.setTotalScore(2000);// /累计积分--联系人获得的积分

		MemberIntegral memberIntegral2 = new MemberIntegral();
		memberIntegral2.setMember(member2);
		memberIntegral2.setCurrentUsableScore(3000);// 本期可用积分
		memberIntegral2.setCurrentTotalScore(3000);// 本期累计积分
		memberIntegral2.setTotalScore(3000);// /累计积分--联系人获得的积分

		memberIntegrals[0] = memberIntegral0;
		memberIntegrals[1] = memberIntegral1;
		memberIntegrals[2] = memberIntegral2;

		IIntegralRule iIntegralRule = RuleFactory.getRule(
				RuleFactory.REWARD_INTEGRAL, null, contactIntegrals,
				memberIntegrals, rewardIntegRule, integralBasicNumber, null);
		Assert.assertNotNull(iIntegralRule);

		IntegralOperation integralOperation = iIntegralRule
				.getBizIntegralOperation();
		List<ContactIntegral> contactIntegralList = integralOperation
				.getContactIntegral();
		List<MemberIntegral> memberIntegralList = integralOperation
				.getMemberIntegral();
		List<Integral> integralList = integralOperation.getIntegral();
		Assert.assertEquals(contactIntegralList.size(), 0);
		Assert.assertEquals(memberIntegralList.size(), 3);
		Assert.assertEquals(integralList.size(), 0);
		for (MemberIntegral memberIntegral : memberIntegralList) {
			if (memberIntegral.getMember().getCustId().equals("1")) {
				Assert.assertEquals(memberIntegral.getCurrentUsableScore(),
						1000);
			}
			if (memberIntegral.getMember().getCustId().equals("2")) {
				Assert.assertEquals(memberIntegral.getCurrentUsableScore(),
						2000);
			}
			if (memberIntegral.getMember().getCustId().equals("3")) {
				Assert.assertEquals(memberIntegral.getCurrentUsableScore(),
						3000);
			}
		}
		for (ContactIntegral contactIntegral : contactIntegralList) {
			if (contactIntegral.getContact().getId().equals("1")) {
				Assert.assertEquals(contactIntegral.getCurrentUsableScore(),
						35333);
			}
			if (contactIntegral.getContact().getId().equals("2")) {
				Assert.assertEquals(contactIntegral.getCurrentUsableScore(),
						36333);
			}
			if (contactIntegral.getContact().getId().equals("3")) {
				Assert.assertEquals(contactIntegral.getCurrentUsableScore(),
						35333);
			}
			if (contactIntegral.getContact().getId().equals("4")) {
				Assert.assertEquals(contactIntegral.getCurrentUsableScore(),
						36333);
			}
			if (contactIntegral.getContact().getId().equals("5")) {
				Assert.assertEquals(contactIntegral.getCurrentUsableScore(),
						36333);
			}
		}
		for (Integral integral : integralList) {
			if (integral instanceof HandRewardIntegral) {
				HandRewardIntegral h = (HandRewardIntegral) integral;
				Assert.assertEquals(h.getScore().intValue(), 33333);
			}
		}
	}

	@Test
	public void testIntegralConvertRule() {
		ContactIntegral contactIntegral = new ContactIntegral();// 主联系人
		//ContactIntegral[] contactIntegrals = new ContactIntegral[1];// 其他联系人积分对象
		MemberIntegral[] memberIntegrals = new MemberIntegral[1];// 会员积分对象
		IntegralConvertGift[] integralConvertGifts = new IntegralConvertGift[4];// 礼品列表

		// 对联系人积分对象赋值
		Contact contact0 = new Contact();
		contact0.setId("1");
		contact0.setCustId("1");
		contactIntegral.setContact(contact0);
		contactIntegral.setCurrentUsableScore(2000);// 本期可用积分
		contactIntegral.setCurrentTotalScore(2000);// 本期累计积分
		contactIntegral.setTotalScore(2000);// /累计积分--联系人获得的积分

//		contactIntegrals[0] = contactIntegral0;

		Member member0 = new Member();
		member0.setCustId("1");
		member0.setIsRedeempoints(true);

		// 会员积分对象
		MemberIntegral memberIntegral0 = new MemberIntegral();
		memberIntegral0.setMember(member0);
		memberIntegral0.setCurrentUsableScore(1000);// 本期可用积分
		memberIntegral0.setCurrentTotalScore(1000);// 本期累计积分
		memberIntegral0.setTotalScore(1000);// /累计积分--联系人获得的积分

		memberIntegrals[0] = memberIntegral0;

		Gift gift0 = new Gift();
		gift0.setId("0");
		gift0.setNeedPoints(100);

		Gift gift1 = new Gift();
		gift1.setId("1");
		gift1.setNeedPoints(50);

		Gift gift2 = new Gift();
		gift2.setId("2");
		gift2.setNeedPoints(60);

		Gift gift3 = new Gift();
		gift3.setId("3");
		gift3.setNeedPoints(80);

		IntegralConvertGift integralConvertGift0 = new IntegralConvertGift();
		integralConvertGift0.setGift(gift0);
		integralConvertGift0.setContact(contact0);
		integralConvertGift0.setConvertNumber(1);

		IntegralConvertGift integralConvertGift1 = new IntegralConvertGift();
		integralConvertGift1.setGift(gift1);
		integralConvertGift1.setContact(contact0);
		integralConvertGift1.setConvertNumber(2);

		IntegralConvertGift integralConvertGift2 = new IntegralConvertGift();
		integralConvertGift2.setGift(gift2);
		integralConvertGift2.setContact(contact0);
		integralConvertGift2.setConvertNumber(3);

		IntegralConvertGift integralConvertGift3 = new IntegralConvertGift();
		integralConvertGift3.setGift(gift3);
		integralConvertGift3.setContact(contact0);
		integralConvertGift3.setConvertNumber(4);

		integralConvertGifts[0] = integralConvertGift0;
		integralConvertGifts[1] = integralConvertGift1;
		integralConvertGifts[2] = integralConvertGift2;
		integralConvertGifts[3] = integralConvertGift3;

		IIntegralRule iIntegralRule = RuleFactory.getRule(
				RuleFactory.CONVERTGIFT_INTEGRAL, contactIntegral, null,
				memberIntegrals, null, 0, integralConvertGifts);

		IntegralOperation integralOperation = iIntegralRule
				.getBizIntegralOperation();
		List<ContactIntegral> contactIntegralList = integralOperation
				.getContactIntegral();
		List<MemberIntegral> memberIntegralList = integralOperation
				.getMemberIntegral();
		List<Integral> integralList = integralOperation.getIntegral();
		Assert.assertEquals(contactIntegralList.size(), 1);
		Assert.assertEquals(memberIntegralList.size(), 1);
		Assert.assertEquals(integralList.size(), 4);
		for (MemberIntegral memberIntegral : memberIntegralList) {
			if (memberIntegral.getMember().getCustId().equals("1")) {
				Assert.assertEquals(memberIntegral.getCurrentUsableScore(), 300);
			}
		}
		for (ContactIntegral contactIntegral1 : contactIntegralList) {
			if (contactIntegral1.getContact().getId().equals("1")) {
				Assert.assertEquals(contactIntegral1.getCurrentUsableScore(),
						1300);
			}
		}
		for (Integral integral : integralList) {
			if (integral instanceof IntegralConvertGift) {
				IntegralConvertGift h = (IntegralConvertGift) integral;
				if (h.getGift().getId().equals("0")) {
					Assert.assertEquals(h.getScore().intValue(), 100);
				}
				if (h.getGift().getId().equals("1")) {
					Assert.assertEquals(h.getScore().intValue(), 100);
				}
				if (h.getGift().getId().equals("2")) {
					Assert.assertEquals(h.getScore().intValue(), 180);
				}
				if (h.getGift().getId().equals("3")) {
					Assert.assertEquals(h.getScore().intValue(), 320);
				}
			}
		}
	}

	@Test
	public void testIntegralConvertRule_1() {
		ContactIntegral mainContact = new ContactIntegral();// 主联系人
		ContactIntegral[] contactIntegrals = new ContactIntegral[5];// 其他联系人积分对象
		MemberIntegral[] memberIntegrals = new MemberIntegral[1];// 会员积分对象
		IntegralConvertGift[] integralConvertGifts = new IntegralConvertGift[4];// 礼品列表

		// 主联系人
		Contact mainCon = new Contact();
		mainCon.setId("0");
		mainCon.setCustId("1");
		mainContact.setContact(mainCon);
		mainContact.setCurrentUsableScore(7000);// 本期可用积分
		mainContact.setCurrentTotalScore(7000);// 本期累计积分
		mainContact.setTotalScore(7000);// /累计积分--联系人获得的积分

		// 对联系人积分对象赋值
		Contact contact1 = new Contact();
		contact1.setId("1");
		contact1.setCustId("1");

		Contact contact2 = new Contact();
		contact2.setId("2");
		contact2.setCustId("1");

		Contact contact3 = new Contact();
		contact3.setId("3");
		contact3.setCustId("1");

		Contact contact4 = new Contact();
		contact4.setId("4");
		contact4.setCustId("1");

		Contact contact5 = new Contact();
		contact5.setId("5");
		contact5.setCustId("1");

		ContactIntegral contactIntegral0 = new ContactIntegral();
		contactIntegral0.setContact(contact1);
		contactIntegral0.setCurrentUsableScore(2000);// 本期可用积分
		contactIntegral0.setCurrentTotalScore(2000);// 本期累计积分
		contactIntegral0.setTotalScore(2000);// /累计积分--联系人获得的积分

		ContactIntegral contactIntegral1 = new ContactIntegral();
		contactIntegral1.setContact(contact2);
		contactIntegral1.setCurrentUsableScore(3000);// 本期可用积分
		contactIntegral1.setCurrentTotalScore(3000);// 本期累计积分
		contactIntegral1.setTotalScore(3000);// /累计积分--联系人获得的积分

		ContactIntegral contactIntegral2 = new ContactIntegral();
		contactIntegral2.setContact(contact3);
		contactIntegral2.setCurrentUsableScore(2000);// 本期可用积分
		contactIntegral2.setCurrentTotalScore(2000);// 本期累计积分
		contactIntegral2.setTotalScore(2000);// /累计积分--联系人获得的积分

		ContactIntegral contactIntegral3 = new ContactIntegral();
		contactIntegral3.setContact(contact4);
		contactIntegral3.setCurrentUsableScore(3000);// 本期可用积分
		contactIntegral3.setCurrentTotalScore(3000);// 本期累计积分
		contactIntegral3.setTotalScore(3000);// /累计积分--联系人获得的积分

		ContactIntegral contactIntegral4 = new ContactIntegral();
		contactIntegral4.setContact(contact5);
		contactIntegral4.setCurrentUsableScore(3000);// 本期可用积分
		contactIntegral4.setCurrentTotalScore(3000);// 本期累计积分
		contactIntegral4.setTotalScore(3000);// /累计积分--联系人获得的积分

		contactIntegrals[0] = contactIntegral0;
		contactIntegrals[1] = contactIntegral1;
		contactIntegrals[2] = contactIntegral2;
		contactIntegrals[3] = contactIntegral3;
		contactIntegrals[4] = contactIntegral4;

		Member member0 = new Member();
		member0.setCustId("1");
		member0.setIsRedeempoints(false);
		// 会员积分对象
		MemberIntegral memberIntegral0 = new MemberIntegral();
		memberIntegral0.setMember(member0);
		memberIntegral0.setCurrentUsableScore(20000);// 本期可用积分
		memberIntegral0.setCurrentTotalScore(20000);// 本期累计积分
		memberIntegral0.setTotalScore(20000);// /累计积分--联系人获得的积分

		memberIntegrals[0] = memberIntegral0;
		/**
		 * 初始化礼品
		 */
		Gift gift0 = new Gift();
		gift0.setId("0");
		gift0.setNeedPoints(100);

		Gift gift1 = new Gift();
		gift1.setId("1");
		gift1.setNeedPoints(50);

		Gift gift2 = new Gift();
		gift2.setId("2");
		gift2.setNeedPoints(60);

		Gift gift3 = new Gift();
		gift3.setId("3");
		gift3.setNeedPoints(80);
		
		/**
		 * 初始化兑换礼品
		 */
		IntegralConvertGift integralConvertGift0 = new IntegralConvertGift();
		integralConvertGift0.setGift(gift0);
		integralConvertGift0.setContact(mainCon);
		integralConvertGift0.setConvertNumber(1);

		IntegralConvertGift integralConvertGift1 = new IntegralConvertGift();
		integralConvertGift1.setGift(gift1);
		integralConvertGift1.setContact(mainCon);
		integralConvertGift1.setConvertNumber(2);

		IntegralConvertGift integralConvertGift2 = new IntegralConvertGift();
		integralConvertGift2.setGift(gift2);
		integralConvertGift2.setContact(mainCon);
		integralConvertGift2.setConvertNumber(3);

		IntegralConvertGift integralConvertGift3 = new IntegralConvertGift();
		integralConvertGift3.setGift(gift3);
		integralConvertGift3.setContact(mainCon);
		integralConvertGift3.setConvertNumber(4);
		//全是主联系人兑换的奖品哦。
		integralConvertGifts[0] = integralConvertGift0;
		integralConvertGifts[1] = integralConvertGift1;
		integralConvertGifts[2] = integralConvertGift2;
		integralConvertGifts[3] = integralConvertGift3;

		IIntegralRule iIntegralRule = RuleFactory.getRule(
				RuleFactory.CONVERTGIFT_INTEGRAL, mainContact,
				contactIntegrals, memberIntegrals, null, 0,
				integralConvertGifts);

		IntegralOperation integralOperation = iIntegralRule
				.getBizIntegralOperation();
		List<ContactIntegral> contactIntegralList = integralOperation
				.getContactIntegral();
		List<MemberIntegral> memberIntegralList = integralOperation
				.getMemberIntegral();
		List<Integral> integralList = integralOperation.getIntegral();
		Assert.assertEquals(contactIntegralList.size(),1);
		Assert.assertEquals(memberIntegralList.size(), 1);
		Assert.assertEquals(integralList.size(), 4);
		Assert.assertEquals(memberIntegralList.get(0).getCurrentUsableScore(),
						19300);
				Assert.assertEquals(contactIntegralList.get(0).getCurrentUsableScore(),
						6300);
//				Assert.assertEquals(contactIntegralList.get(1).getCurrentUsableScore(),
//						2895);
//				Assert.assertEquals(contactIntegralList.get(2).getCurrentUsableScore(),
//						1930);
//				Assert.assertEquals(contactIntegralList.get(3).getCurrentUsableScore(),
//						2895);
//				Assert.assertEquals(contactIntegralList.get(4).getCurrentUsableScore(),
//						2895);
//				Assert.assertEquals(contactIntegralList.get(5).getCurrentUsableScore(),
//						6755);
//				
//				Assert.assertEquals(integralList.get(0).getScore().intValue(),
//						-70);
//				Assert.assertEquals(integralList.get(1).getScore().intValue(),
//						70);
//				Assert.assertEquals(integralList.get(2).getScore().intValue(),
//						-105);
//				Assert.assertEquals(integralList.get(3).getScore().intValue(),
//						105);
//				Assert.assertEquals(integralList.get(4).getScore().intValue(),
//						-70);
//				Assert.assertEquals(integralList.get(5).getScore().intValue(),
//						70);
//				Assert.assertEquals(integralList.get(6).getScore().intValue(),
//						-105);
//				Assert.assertEquals(integralList.get(7).getScore().intValue(),
//						105);
//				Assert.assertEquals(integralList.get(8).getScore().intValue(),
//						-105);
//				Assert.assertEquals(integralList.get(9).getScore().intValue(),
//						105);
//				Assert.assertEquals(integralList.get(10).getScore().intValue(),
//						100);
//				Assert.assertEquals(integralList.get(11).getScore().intValue(),
//						100);
//				Assert.assertEquals(integralList.get(12).getScore().intValue(),
//						180);
//				Assert.assertEquals(integralList.get(13).getScore().intValue(),
//						320);
	}
	
	@Test
	public void testGet3ContactIntegral(){
		String  memberId = "525";
		List<ContactIntegral> list = inIIntegralManager.get3ContactIntegral(memberId);
		//测试主联系人
		Assert.assertEquals(1, list.size());
	}
	
//	@Test
	public void testGetMemberIntegralByMemberNum(){
		String memberNo = "999999999999999";
		try {
			MemberIntegral mem = inIIntegralManager.getMemberIntegralByMemberNum(memberNo);
		} catch (CustomerException e) {
			assertEquals("i18n.Integral.custCodeNotFound", e.getErrorCode());
		}
		
	}
}
