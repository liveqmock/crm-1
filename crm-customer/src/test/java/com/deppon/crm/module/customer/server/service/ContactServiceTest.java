package com.deppon.crm.module.customer.server.service;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.deppon.crm.module.customer.server.service.impl.ContractService;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;

public class ContactServiceTest extends BeanUtil{
	
	/**
	 * 
	 * <p>
	 * Description:test 根据idCard查询联系人<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-26
	 * void
	 */
	@Test
	public void test_getContactCountByIdCard(){
		try{
		    contactService.getContactCountByIdCard("");
		}catch(RuntimeException re){
			Assert.assertEquals("身份证号码不能为空,参数调用不合法", re.getMessage());
		}
		
		int count = contactService.getContactCountByIdCard("341221198710025338");
		Assert.assertEquals(0, count);
		
	}
	
	@Test
	public void test_getcontract(){
		 ContractService cs = SpringTestHelper.getBean(ContractService.class);
		 List<Double> moneyList = cs.getLatelyDeliverMoney("24216",3);
		 System.out.println(moneyList);
	     for (Double double1 : moneyList) {
			System.out.println(double1.intValue());
		}
	}
	
	

}
