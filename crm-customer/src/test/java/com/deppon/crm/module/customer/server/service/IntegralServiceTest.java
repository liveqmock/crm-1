package com.deppon.crm.module.customer.server.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.integral.AdjustIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.GiftConvertHoldRecode;
import com.deppon.crm.module.customer.shared.domain.integral.HandRewardIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.Integral;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGift;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralOperation;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;

public class IntegralServiceTest extends BeanUtil {
	/**
	 * 
	 * <p>
	 * Description:test保存暂存礼品记录<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-26
	 * @throws Exception
	 * void
	 */
	@Test
	public void test_saveTemporyGiftList() throws Exception{
		
		List<GiftConvertHoldRecode> giftConvertHoldRecodes = new ArrayList<GiftConvertHoldRecode>();
		
		GiftConvertHoldRecode giftConvertHoldRecode = new GiftConvertHoldRecode();
		giftConvertHoldRecode.setConvertNumber(50);
		giftConvertHoldRecodes.add(giftConvertHoldRecode);
		integralService.saveTemporyGiftList(giftConvertHoldRecodes);
		
		clearGiftConvertHoldData(giftConvertHoldRecode);
		
	}
	
	/**
	 * 
	 * <p>
	 * Description:test保存积分操作对象<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-26
	 * void
	 * @throws Exception 
	 */
	@Test
	public void test_saveIntegralOperation_scane(){
		
       IntegralOperation operation = new IntegralOperation();
		
		ContactIntegral contactIntegral = new ContactIntegral();
		contactIntegral.setCurrentUsableScore(1000);
		operation.getContactIntegral().add(contactIntegral);
		
		MemberIntegral memberIntegral = new MemberIntegral();
		memberIntegral.setCurrentToUsesScore(1002);
		operation.getMemberIntegral().add(memberIntegral);
		
		Integral integral = new Integral();
		integral.setScore(100);
		operation.getIntegral().add(integral);
		
		
		integral = new AdjustIntegral();
		integral.setScore(100);
		operation.getIntegral().add(integral);
		
		integral = new HandRewardIntegral();
		integral.setScore(100);
		operation.getIntegral().add(integral);
		
		integral = new IntegralConvertGift();
		integral.setScore(100);
		operation.getIntegral().add(integral);
		
		User user = new User();
		Department depart = new Department();
		depart.setId("432658");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		e.setId("98564");
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
		try{
		integralService.saveIntegralOperation(operation);
		}catch(RuntimeException re){
			
			Assert.assertEquals("扩展了对象，这里也要添加代码的，亲", re.getMessage());
			
		}
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:test保存积分操作对象<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-26
	 * void
	 * @throws Exception 
	 */
	@Test
	public void test_saveIntegralOperation() throws Exception{
		
		
		IntegralOperation operation = new IntegralOperation();
		
		ContactIntegral contactIntegral = new ContactIntegral();
		contactIntegral.setCurrentUsableScore(1000);
		operation.getContactIntegral().add(contactIntegral);
		
		MemberIntegral memberIntegral = new MemberIntegral();
		memberIntegral.setCurrentToUsesScore(1002);
		operation.getMemberIntegral().add(memberIntegral);
		
		Integral integral = new AdjustIntegral();
		integral.setScore(100);
		operation.getIntegral().add(integral);
		
		integral = new HandRewardIntegral();
		integral.setScore(100);
		operation.getIntegral().add(integral);
		
		integral = new IntegralConvertGift();
		integral.setScore(100);
		operation.getIntegral().add(integral);
		
		User user = new User();
		Department depart = new Department();
		depart.setId("432658");
		depart.setDeptName("deptName");
		depart.setStandardCode("DP00003");
		Employee e = new Employee();
		e.setDeptId(depart);
		e.setId("98564");
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
		integralService.saveIntegralOperation(operation);
		
		
		clearIntegraOperation(operation);
		
		
	}
	
	/**
	 * 
	 * <p>
	 * Description:test通过联系人ID获得联系人积分对象<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void test_getContactIntegralByContact(){
		
		ContactIntegral contactIntegral = integralService.getContactIntegralByContact("400131224");
		Assert.assertEquals("400131224", contactIntegral.getContact().getId());
	
		 contactIntegral = integralService.getContactIntegralByContact("12345");
		Assert.assertNull( contactIntegral);
	
		
		
		
	}
	
	/**
	 * 
	 * <p>
	 * Description:test更新积分联系人<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-26
	 * void
	 * @throws Exception 
	 */
	@Test
	public void test_updateMemberIntegral() throws Exception{
		
		MemberIntegral memberIntegral = new MemberIntegral ();
		
		memberIntegral.setCurrentUsableScore(1000);
		integralService.updateMemberIntegral(memberIntegral);
		memberIntegral.setCurrentToUsesScore(500);
		integralService.updateMemberIntegral(memberIntegral);
		clearMemberIntegralData(memberIntegral);

	}
	
	
	
	/**
	 * 
	 * <p>
	 * Description:test更新联系人的积分<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-26
	 * void
	 * @throws Exception 
	 */
	@Test
	public void test_updateContactIntegral() throws Exception{
		
		ContactIntegral contactIntegral = new ContactIntegral();
		contactIntegral.setCurrentUsableScore(1000);
		integralService.updateContactIntegral(contactIntegral);
		
		integralService.updateContactIntegral(contactIntegral);
		clearContactIntegralData(contactIntegral);
	}
	
	private void clearGiftConvertHoldData(GiftConvertHoldRecode giftConvertHoldRecode) throws Exception{
		String[] sqls = new String[1];
		sqls[0] = "delete T_CUST_GIFTCONVERTHOLDRECODE where fid= "+giftConvertHoldRecode.getId();
		SpringTestHelper.executeBatch(sqls);
		
	}
	
	private void clearIntegraOperation(IntegralOperation operation) throws Exception{
		String[] sqls = new String[3];
		sqls[0] = "delete t_cust_memberpoint where fid= "+operation.getMemberIntegral().get(0).getId();
		sqls[1] =  "delete t_cust_linkmanpoint where fid= "+operation.getContactIntegral().get(0).getId();
		sqls[2] =  "delete t_cust_integ where fid in ("+operation.getIntegral().get(0).getId()+","+operation.getIntegral().get(1).getId()+","+operation.getIntegral().get(2).getId()+")";
		
		SpringTestHelper.executeBatch(sqls);
		
	}
	
	
	
	private void clearMemberIntegralData(MemberIntegral memberIntegral) throws Exception{
		String[] sqls = new String[1];
		sqls[0] = "delete t_cust_memberpoint where fid= "+memberIntegral.getId();
		SpringTestHelper.executeBatch(sqls);
		
	}
	
	
	private void clearContactIntegralData(ContactIntegral contactIntegral) throws Exception{
		String[] sqls = new String[1];
		sqls[0] = "delete t_cust_linkmanpoint where fid= "+contactIntegral.getId();
		SpringTestHelper.executeBatch(sqls);
	}
}
