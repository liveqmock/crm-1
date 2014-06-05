/*package com.deppon.erp;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.deppon.erp.payment.DepClaimsBillService;
import com.deppon.erp.payment.DepClaimsBillServiceImplService;

public class DepClaimsBillServiceTest {
	DepClaimsBillService depClaimsBillService = null;

	@Before
	public void init() {
		DepClaimsBillServiceImplService service = new DepClaimsBillServiceImplService();
		depClaimsBillService = service.getDepClaimsBillServiceImplPort();
	}

	@Test
	public void test() {
		// String claims[] =
		// {"20","59ANYgEbEADgALhrwKgC/MznrtQ=","NyWr+AEyEADgAADFwKgLdkeF39U=","1000","45698719","042792"};
		// 理赔类型，部门id，客户id，金额，单号，创建人工号
		// 邓大伟 14:19:17
		// String claimsDept[] =
		// {"45698719","001","700","45698719","002","300"}; 单号，部门编号，承担金额
		//
		List<String> k1 = new ArrayList<String>();
		k1.add("20");
		k1.add("59ANYgEbEADgALhrwKgC/MznrtQ=");
		k1.add("NyWr+AEyEADgAADFwKgLdkeF39U=");
		k1.add("1000");
		k1.add("45698719");
		k1.add("042792");
		List<String> k2 = new ArrayList<String>();
		k2.add("45698719");
		k2.add("001");
		k2.add("700");
		k2.add("45698719");
		k2.add("002");
		k2.add("300");
		String nnn = depClaimsBillService.insertDepClaimsBill(k1, k2);
		System.out.println(nnn);
	}
}
*/