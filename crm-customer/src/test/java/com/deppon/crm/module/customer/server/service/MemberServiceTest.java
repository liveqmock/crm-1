package com.deppon.crm.module.customer.server.service;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.server.manager.CustomerRule;
import com.deppon.crm.module.customer.server.service.impl.MemberService;
import com.deppon.crm.module.customer.server.testutils.DateCreateUtil;
import com.deppon.crm.module.customer.server.testutils.DateInitUtil;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.server.util.UserUtil;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.UpGradeCustomer;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.customer.shared.exception.MemberExceptionType;

public class MemberServiceTest extends BeanUtil{
	@Before
	public void setUp() throws Exception{
		DateInitUtil.executeInitSql();
		UserUtil.setUserToAdmin();
	}
	
	/**
	 * 
	 * <p>
	 * Description:test得到会员实际等级<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
   public void test_getMemberTargetLevel(){
	   UpGradeCustomer upGrade = new UpGradeCustomer();
	   try{
	   upGrade.setContactTel("021-235445");
	   memberService.getMemberTargetLevel(upGrade);
	   }catch(RuntimeException re){
		   Assert.assertEquals("调用参数不合法", re.getMessage());
	   }
	   
	   upGrade.setContactPhone("13538654173");
	   
	   List<String> trageList = memberService.getMemberTargetLevel(upGrade);
	   
	   upGrade.setContactName("谢艳红");
	   upGrade.setContactTel("0755-86109773");
	   upGrade.setContactPhone("");
	   trageList = memberService.getMemberTargetLevel(upGrade);
	   
	   
   }
	/**
	 * 
	 * @Title: testQueryMemberByCustNumber
	 *  <p>
	 * @Description: 测试通过客户编码查询固定客户信息
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-6-2
	 * @return void
	 * @throws
	 */
	@Test
	public void testQueryMemberByCustNumber(){
//		boolean flag = false; 
//		//校验存在有效固定客户的情况		
//		flag = memberService.queryMemberByCustNumber("40000225");
//		Assert.assertTrue(flag);
//		//校验不存在有效固定客户的情况
//		flag = memberService.queryMemberByCustNumber("327942232");
//		Assert.assertFalse(flag);
	}
}
