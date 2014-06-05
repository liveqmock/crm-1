package com.deppon.crm.module.customer.server.testutils;


import org.junit.Test;
import junit.framework.Assert;
import com.deppon.crm.module.customer.server.utils.ApproveDataUtil;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.Member;

public class ApproveDataUtilTest {
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-25
	 * void
	 */
	@Test
	public void test_setOldValueToPojo(){
		
		Member member = new Member();
		
		ApproveData appData = new ApproveData();
		appData.setFieldName("custName");
		appData.setOldValue("客户姓名1");
		ApproveDataUtil.setOldValueToPojo(member, appData);
		
		Assert.assertEquals("客户姓名1", member.getCustName());
		
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-25
	 * void
	 */
	@Test
	public void test_setNewValueToPojo(){
		
		Member member = new Member();
		
		ApproveData appData = new ApproveData();
		appData.setFieldName("custName");
		appData.setNewValue("客户姓名1");
		ApproveDataUtil.setNewValueToPojo(member, appData);
		
		Assert.assertEquals("客户姓名1", member.getCustName());
		
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试通过反射将值通过字段名写入实体中<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-25
	 * void
	 */
	@Test
	public void test_setValueToPojo(){
		
		Member member = new Member();
		
		ApproveDataUtil.setValueToPojo(member, "custName", "客户姓名");
		Assert.assertEquals("客户姓名", member.getCustName());
		
		ApproveDataUtil.setValueToPojo(member, "versionNumber", "1234");
		Assert.assertEquals(new Integer(1234), member.getVersionNumber());
		
//		ApproveDataUtil.setValueToPojo(member, "modifyDate", "Tue Dec 25 2012");
//		Assert.assertNotNull(member.getModifyDate());
		
		ApproveDataUtil.setValueToPojo(member, "isTranGoods", "true");
		Assert.assertTrue(member.getIsTranGoods());
		
		ApproveDataUtil.setValueToPojo(member, "procRedit", "365.2");
		Assert.assertEquals(new Double(365.2),member.getProcRedit());
		
	}

}
