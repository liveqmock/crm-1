package com.deppon.crm.test.client.fin;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.ClientTool;
import com.deppon.crm.module.client.fin.domain.BillInfo;
import com.deppon.crm.module.client.fin.domain.CashierAccountInfo;
import com.deppon.crm.module.client.fin.domain.CostDetail;
import com.deppon.crm.test.client.common.OperaterTest;
import com.deppon.fin.selfservice.CostDetailsType;
import com.deppon.fin.selfservice.GenerateClaimsapbillRequest;
import com.deppon.fin.selfservice.GenerateClaimsapbillResponse;
import com.deppon.fin.selfservice.QueryCashieraccountRequest;
import com.deppon.fin.selfservice.QueryCashieraccountResponse;

public class FINSelfserviceOperateImplTest extends OperaterTest {

	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testGenerateClaimsapbill() throws CrmBusinessException {
		BillInfo request = new BillInfo();
		request.setAccountNature("200");
		request.setApplyUser("111276");
		request.setBankAccount("1111111111111");
		request.setBankBranchCode("123456789");
		request.setBankCityCode("362426");
		request.setBankCode("ddd");
		request.setBankProvinceCode("ds");
		request.setMobilePhone("deez");
		request.setUserName("44444");
		request.setRecompenseAmount(new BigDecimal(5));
		request.setBillNum("222");
		request.setDrawMoneyType("20");
		CostDetail cost=new CostDetail();
		cost.setVoucherNumber("5");
		cost.setCostDate(new Date());
		cost.setRecompenseType("kd");
		cost.setReimbursementMoneyDetail(new BigDecimal(5));
		cost.setResponsibilityDeptInfo("kldkl");
		request.getCostDetails().add(cost);
		GenerateClaimsapbillResponse response=null;
		for(int i=0;i<6;i++)
		try {
			response = finSelfOperate
					.generateClaimsapbill(request);
		} catch (CrmBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.notNull(response);
	}


	@Test
	public void testQueryCashieraccount() throws CrmBusinessException {
		QueryCashieraccountRequest request = new QueryCashieraccountRequest();
		request.setCashierNumber("pm");
		List<CashierAccountInfo> response=null;
		for(int i=0;i<10;i++){
		try {
			response = finSelfOperate
					.queryCashieraccount(request);
		} catch (CrmBusinessException e) {
			
			e.printStackTrace();
		}
		}
		Assert.notNull(response);
	}
}
