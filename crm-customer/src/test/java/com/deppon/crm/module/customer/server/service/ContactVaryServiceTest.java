package com.deppon.crm.module.customer.server.service;

import org.junit.Test;

import junit.framework.Assert;

import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.server.service.impl.ContactVaryService;
import com.deppon.crm.module.customer.shared.domain.integral.ContactVary;
public class ContactVaryServiceTest extends BeanUtil{
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void test_getContactVaryByWorkFlowId(){
		
		ContactVary contactVary = contactVaryService.getContactVaryByWorkFlowId(23346);
		Assert.assertNull(contactVary);
		contactVary = contactVaryService.getContactVaryByWorkFlowId(400007617);
	  Assert.assertEquals(400007617, contactVary.getWorkflowId());
	}
}
