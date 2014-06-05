package com.deppon.crm.test.client.workflow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.workflow.domain.PaymentInfo;
import com.deppon.crm.test.client.common.OperaterTest;
import com.deppon.erp.payment.DepClaimsBill;
import com.deppon.foss.crm.BankPayInfo;
import com.deppon.foss.crm.ClaimsPayBillGenerateRequest;
import com.deppon.foss.crm.ResponsibilityInfo;

public class PaymentApplyOperateTest extends OperaterTest {
	@Test
	public void claimsApbillTest(){
		BankPayInfo bankPayInfo = new BankPayInfo();
		bankPayInfo.setPayWay("");
		bankPayInfo.setAccountName("");
		bankPayInfo.setAccountProperty("");
		bankPayInfo.setAccountNumber("");
		bankPayInfo.setBankCode("");
		bankPayInfo.setBankName("");
		bankPayInfo.setSubbranchCode("");
		bankPayInfo.setSubbranchName("");
		bankPayInfo.setBankProvinceCode("");
		bankPayInfo.setBankProvinceName("");
		bankPayInfo.setBankCityCode("");
		bankPayInfo.setBankCityName("");


		ResponsibilityInfo responsibilityInfo = new ResponsibilityInfo();
		responsibilityInfo.setResponsibilityDeptCode("");
		responsibilityInfo.setResponsibilityMoney(new BigDecimal(1));

		ClaimsPayBillGenerateRequest claimsPayBillGenerateRequest = new ClaimsPayBillGenerateRequest();
		claimsPayBillGenerateRequest.setClaimType("1");
		claimsPayBillGenerateRequest.setClaimWay("1");
		claimsPayBillGenerateRequest.setBusinessType("1");
		claimsPayBillGenerateRequest.setDeptNo("DP01121");
		claimsPayBillGenerateRequest.setCustNo("116939");
		claimsPayBillGenerateRequest.setClaimMoney(new BigDecimal(1000));
		claimsPayBillGenerateRequest.setBillNo("123456789");
		claimsPayBillGenerateRequest.setCreatorNo("42366");
//		claimsPayBillGenerateRequest.getResponsibilityInfos().add(responsibilityInfo);
		claimsPayBillGenerateRequest.setBankPayInfo(bankPayInfo);
		claimsPayBillGenerateRequest.setPayBillLastTime(new Date());
		claimsPayBillGenerateRequest.setPaymentCategory("TT");
		try {
			boolean result=paymentApplyOperate.claimsApbill(claimsPayBillGenerateRequest);
			System.out.println(result);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	} 
	@Test
	public void submitPaymentInfoTest(){
		String claims[] = {"20","DP00344","20100819-AR-W06070212","100","18888888","042792"};
		String claimsDept[] = {"18888888","DP00344","700","18888888","DP00344","300"};
		List<String> lists = new ArrayList<String>();
		lists.add("20");
		lists.add("DP00344");
		lists.add("20100819-AR-W06070212");
		lists.add("100");
		lists.add("18888888");
		lists.add("042792");
		List<String> lists2 = new ArrayList<String>();
		lists2.add("18888888");
		lists2.add("DP00344");
		lists2.add("700");
		lists2.add("18888888");
		lists2.add("DP00344");
		lists2.add("300");
		try {
			paymentApplyOperate.submitPaymentInfo(lists, lists2);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void queryDepClaimsByApproveDateTest(){
		try{
			 Calendar c =Calendar.getInstance();
			  c.setTime(new Date());
			  c.get(Calendar.DAY_OF_MONTH);
			  c.set(2011, 5, 10,0,0,0);
			  Date myDate = c.getTime();
			  List<DepClaimsBill> list=  paymentApplyOperate.queryDepClaimsByApproveDate(myDate, new Date());
			  System.out.println(list.size());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryDepClaimsByNumList(){
		List<String> list = new ArrayList<String>();
		list.add("08094001");
		list.add("08081009");
//		list.add("40020363");
//		list.add("40sddd3");
		try {
			List<DepClaimsBill> billList = paymentApplyOperate.queryDepClaimsByNumList(list);
			System.out.println(billList.size());
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void paymentApplyTest(){
		try {
			
			String result = paymentApplyOperate.paymentApply(getPaymentInfo());
			System.out.println(result);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void paymentOnlineApplyTest(){
		try {
			
			String result = paymentApplyOperate.paymentOnlineApply(getPaymentInfo());
			System.out.println(result);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
		
	}
	
	public PaymentInfo getPaymentInfo(){
		PaymentInfo paymentInfo = new PaymentInfo();
		paymentInfo.setApplyPersonNumber("053951");
		paymentInfo.setPayee("1");
		paymentInfo.setPayeeMobilePhone("13917182631");
		paymentInfo.setProvince("上海");
		paymentInfo.setCity("上海");
		paymentInfo.setBank("1");
		paymentInfo.setSubbranch("1");
		paymentInfo.setAccountNumber("1");
		paymentInfo.setAmountMoney(Double.valueOf("1"));
		paymentInfo.setPayWay("1");
		paymentInfo.setWaybillNumber("1");
		paymentInfo.setErrorNumber("1");
		paymentInfo.setPartA("1");
		paymentInfo.setPartB("1");
		paymentInfo.setShipmentsDate(new Date());
		paymentInfo.setStartStation("1");
		paymentInfo.setDestination("1");
		paymentInfo.setRecompenseMoney(Double.valueOf("1"));
		paymentInfo.setRecompenseMoneyText("1");
		paymentInfo.setAccountName("1");
		paymentInfo.setPartAsign("1");
		paymentInfo.setPartAsignDate(new Date());
		paymentInfo.setPartBAsign("1");
		paymentInfo.setPartBAsignDate(new Date());
		paymentInfo.setIdentityCard("1");
		return paymentInfo;
	}
	
}
