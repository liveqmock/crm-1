package com.deppon.crm.test.client.fin;

import java.util.List;

import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.fin.domain.CashierAccountInfo;
import com.deppon.crm.module.client.fin.domain.NoBillingRecompenseInfo;
import com.deppon.crm.module.client.fin.domain.ResponsibilityDeptInfo;
import com.deppon.crm.test.client.common.OperaterTest;

public class FINRecompenserOperateImplTest extends OperaterTest {

	@Test
	public void searchCashierAccountTest() {for(int i =0;i<200;i++){
		try {
			
			List<CashierAccountInfo> nn = fINBankNumberOperate.searchCashierAccount("117277");
			System.out.println(nn.size());
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}}
	}

	@Test
	public void createNoBillingRecompenseTest() {
		try {
			ResponsibilityDeptInfo responsibilityDeptInfo = new ResponsibilityDeptInfo();
			responsibilityDeptInfo.setResponsibilityDeptCode("DP00465");
			responsibilityDeptInfo.setResponsibilityMoney("100.0");

			NoBillingRecompenseInfo noBillingRecompenseInfo = new NoBillingRecompenseInfo();
			//2013051875266
			noBillingRecompenseInfo.setBillNum("2013051879001");
			noBillingRecompenseInfo.setRecompenseAmount("100.0");
			noBillingRecompenseInfo.setBankCode("103");
			noBillingRecompenseInfo.setReceiverName("1");
			noBillingRecompenseInfo.setBankAccount("1");
			noBillingRecompenseInfo.setBankBranchCode("103136152919");
			noBillingRecompenseInfo.setBankProvinceCode("130000");
			noBillingRecompenseInfo.setBankCityCode("130600");
			noBillingRecompenseInfo.setMobilePhone("11111111");
			noBillingRecompenseInfo.setDrawMoneyType("20");
			noBillingRecompenseInfo.setRecompenseType("unbilled");
			noBillingRecompenseInfo.setApplyUser("084212");
			noBillingRecompenseInfo.setAccountNature("6");
			noBillingRecompenseInfo.getResponsibilityDeptInfos().add(responsibilityDeptInfo);
			String nn = fINBankNumberOperate.createNoBillingRecompense(noBillingRecompenseInfo);
			System.out.println(nn);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
}
