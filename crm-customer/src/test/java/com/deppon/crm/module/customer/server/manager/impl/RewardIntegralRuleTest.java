/**   
 * @title RewardIntegralRuleTest.java
 * @package com.deppon.crm.module.customer.server.manager.impl
 * @description what to do
 * @author 潘光均
 * @update 2012-7-2 上午9:34:50
 * @version V1.0   
 */
package com.deppon.crm.module.customer.server.manager.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralOperation;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.RewardIntegRule;
import com.deppon.crm.module.customer.shared.exception.IntegException;
import com.deppon.crm.module.customer.shared.exception.IntegExceptionType;

/**
 * @description fuction  
 * @author 潘光均
 * @version 0.1 2012-7-2
 *@date 2012-7-2
 */

public class RewardIntegralRuleTest extends BeanUtil {


	/**
	 * @description function.  
	 * @author 潘光均
	 * @version 0.1 2012-7-2
	 * @param 
	 *@date 2012-7-2
	 * @return void
	 * @update 2012-7-2 上午9:34:50
	 */
	@Before
	public void setUp() throws Exception {
		
	}

	/**
	 * @description function.  
	 * @author 潘光均
	 * @version 0.1 2012-7-2
	 * @param 
	 *@date 2012-7-2
	 * @return void
	 * @update 2012-7-2 上午9:34:51
	 */
	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void test_getBizIntegralOperation() throws ParseException{
		
	    RewardIntegRule rewardIntegRule = new RewardIntegRule();
		rewardIntegRule.setFraction(5.6d);
		rewardIntegRule.setPointvalue(3);
		
		rewardIntegRule.setPointbegintime(getDate(-1));
		rewardIntegRule.setPointendtime(getDate(-1));
		MemberIntegral[] memberIntegrals = new MemberIntegral[1];
		int integralBasicNumber = 5;
		ContactIntegral[] contactIntegrals = new ContactIntegral[1];
		RewardIntegralRule rir = new RewardIntegralRule(rewardIntegRule, integralBasicNumber,
				contactIntegrals, memberIntegrals);

		
		try{
			
		 rir.getBizIntegralOperation();
		 Assert.fail("抛出异常");
		}catch(IntegException ire){
			Assert.assertEquals(IntegExceptionType.IntegRuleDateError.getErrCode(), ire.getErrorCode());
		}
		
		
		
		rewardIntegRule.setPointbegintime(getDate(-1));
		rewardIntegRule.setPointendtime(getDate(1));
		try{
			integralBasicNumber = 0;
			
			 rir = new RewardIntegralRule(rewardIntegRule, integralBasicNumber,
						contactIntegrals, memberIntegrals);
			 rir.getBizIntegralOperation();
		
		}catch(IntegException ire){
			Assert.assertEquals(IntegExceptionType.IntegIszero.getErrCode(), ire.getErrorCode());
		}
			
		
		
		
		rewardIntegRule.setPointbegintime(getDate(-1));
		rewardIntegRule.setPointendtime(getDate(1));
		rewardIntegRule.setIntegType(Constant.RULETYPE_SCORE);
		try{
			integralBasicNumber = 0;
			
			 rir = new RewardIntegralRule(rewardIntegRule, integralBasicNumber,
						contactIntegrals, memberIntegrals);
			 rir.getBizIntegralOperation();
		
		}catch(IntegException ire){
			Assert.assertEquals(IntegExceptionType.IntegIszero.getErrCode(), ire.getErrorCode());
		}
		
		
		
		rewardIntegRule.setPointbegintime(getDate(-1));
		rewardIntegRule.setPointendtime(getDate(1));
		rewardIntegRule.setIntegType(Constant.RULETYPE_SCORE);

			String custId = "123456";
			integralBasicNumber = 5;
			MemberIntegral memberIntegral = new MemberIntegral();
			Member member = new Member();
			member.setId(custId);
			memberIntegral.setMember(member);
			memberIntegrals[0] = memberIntegral;
			
			ContactIntegral contactIntegral = new ContactIntegral();
			Contact contact = new Contact();
			contact.setCustId(custId);
			contactIntegral.setContact(contact);
			contactIntegrals[0] = contactIntegral;
			 rir = new RewardIntegralRule(rewardIntegRule, integralBasicNumber,
						contactIntegrals, memberIntegrals);
			 IntegralOperation io = rir.getBizIntegralOperation();
		Assert.assertNotNull(io);
		
		
		
		

		
		rewardIntegRule.setPointbegintime(getDate(-1));
		rewardIntegRule.setPointendtime(getDate(1));
		rewardIntegRule.setIntegType("Type");
			integralBasicNumber = 5;
			 memberIntegral = new MemberIntegral();
			 member = new Member();
			member.setId(custId);
			memberIntegral.setMember(member);
			memberIntegrals[0] = memberIntegral;
			
			 contactIntegral = new ContactIntegral();
			 contact = new Contact();
			contact.setCustId(custId);
			contactIntegral.setContact(contact);
			contactIntegrals[0] = contactIntegral;
			
			 contactIntegral = new ContactIntegral();
			 contact = new Contact();
			contact.setCustId("12346e4577687");
			contactIntegral.setContact(contact);
			contactIntegrals[0] = contactIntegral;
			 rir = new RewardIntegralRule(rewardIntegRule, integralBasicNumber,
						contactIntegrals, memberIntegrals);
			  io = rir.getBizIntegralOperation();
		Assert.assertNotNull(io);
		
	}
	
	
	
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-31
	 * void
	 */
	@Test
	public void test_isValidate() {

//		// rewardIntegRule 为空 返回false

		RewardIntegralRule rir = new RewardIntegralRule(null,
				0, null, null);
		boolean r = rir.isValidate();
		Assert.assertFalse(r);

		RewardIntegRule rewardIntegRule = new RewardIntegRule();
	
		
//		// rewardIntegRule 为空 返回false
		rewardIntegRule.setPointvalue(0);
		rewardIntegRule.setFraction(0d);
		 rir = new RewardIntegralRule(rewardIntegRule,
				0, null, null);
		 r = rir.isValidate();
		Assert.assertFalse(r);
		
		int integralBasicNumber = 5;
		// integralBasicNumber
		integralBasicNumber = 0;
		rewardIntegRule.setFraction(3d);
		rir = new RewardIntegralRule(rewardIntegRule, integralBasicNumber,
				null, null);
		r = rir.isValidate();
		Assert.assertFalse(r);
		
		ContactIntegral[] contactIntegrals = new ContactIntegral[2];
		// contactIntegrals
		integralBasicNumber = 5;
		rewardIntegRule.setFraction(3d);
		rir = new RewardIntegralRule(rewardIntegRule, integralBasicNumber,
				contactIntegrals, null);
		r = rir.isValidate();
		Assert.assertFalse(r);

		// memberIntegrals
		rewardIntegRule = new RewardIntegRule();
		rewardIntegRule.setPointvalue(3);
		rewardIntegRule.setFraction(1.1d);
		MemberIntegral[] memberIntegrals = new MemberIntegral[]{};
		integralBasicNumber = 5;
		contactIntegrals[0] = new ContactIntegral();
		rir = new RewardIntegralRule(rewardIntegRule, integralBasicNumber,
				contactIntegrals, memberIntegrals);
		 r = rir.isValidate();
		Assert.assertFalse(r);
//
//		//
		memberIntegrals = new MemberIntegral[1];
		integralBasicNumber = 5;
		rewardIntegRule.setPointvalue(3);
		contactIntegrals[0] = new ContactIntegral();
		memberIntegrals[0] = new MemberIntegral();
		rir = new RewardIntegralRule(rewardIntegRule, integralBasicNumber,
				contactIntegrals, memberIntegrals);
		r = rir.isValidate();
		Assert.assertTrue(r);

	}



	private Date getDate(int day){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+day);
	 return cal.getTime();
	}
}
