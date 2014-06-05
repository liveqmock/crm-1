package com.deppon.crm.test.client.map;

import static org.junit.Assert.*;

import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.test.client.common.OperaterTest;
import com.ibm.disthub2.impl.util.Assert;

public class MapOperateTest extends OperaterTest {

	 @Test
	 public void findDeptCodeByAddressTest(){
	 try {
	 String deptCode = mapOperate.findDeptCodeByAddress("中山北路125号");
	 System.out.println(deptCode);
//	 assertEquals(expected, actual)
	// Assert.assertEquals("DP00421", deptCode);
	 } catch (CrmBusinessException e) {
	 e.printStackTrace();
	 }
	 }

	@Test
	public void findDeptCodeByAddressTest1() throws Exception{
		// try {
		for (int i = 0; i < 1; i++) {
			Thread thread = new Thread(new MyThread(mapOperate));
			thread.start();
			// String deptCode = mapOperate.findDeptCodeByAddress("上海市青浦区徐泾镇");
			// deptCode = mapOperate.findDeptCodeByAddress("上海市青浦区徐泾镇");
			// deptCode = mapOperate.findDeptCodeByAddress("上海市青浦区徐泾镇");
			// deptCode = mapOperate.findDeptCodeByAddress("上海市青浦区徐泾镇");
			// deptCode = mapOperate.findDeptCodeByAddress("上海市青浦区徐泾镇");
			// deptCode = mapOperate.findDeptCodeByAddress("上海市青浦区徐泾镇");
			// System.out.println(deptCode);
			// Assert.assertEquals("DP01430", deptCode);
			// } catch (CrmBusinessException e) {
			// e.printStackTrace();
			// }
		}
		Thread.sleep(99999999);
		String deptCode = mapOperate.findDeptCodeByAddress("上海市青浦区徐泾镇");
		System.out.println(deptCode);
	}
}