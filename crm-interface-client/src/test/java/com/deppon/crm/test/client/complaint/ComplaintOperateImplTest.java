package com.deppon.crm.test.client.complaint;

import org.junit.Assert;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.test.client.common.OperaterTest;

public class ComplaintOperateImplTest extends OperaterTest {

	@Test
	public void bindComplaintId2ServiceNumberTest(){
		try {
			boolean result = complaintOperate.bindComplaintId2ServiceNumber("asdf", "asdf");
			Assert.assertTrue(result);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description:合肥呼叫中心,处理工单上报<br />
	 * @author CoCo
	 * @version 0.1 2013-11-11
	 * void
	 */
	@Test
	public void bindHfComplaintId2ServiceNumber(){
		try {
			boolean result = complaintOperate.bindHfComplaintId2ServiceNumber("asdf", "asdf");
			Assert.assertTrue(result);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		} 
	}
}
