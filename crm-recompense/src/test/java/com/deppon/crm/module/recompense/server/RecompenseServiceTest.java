package com.deppon.crm.module.recompense.server;


import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.AccountBank;
import com.deppon.crm.module.common.shared.domain.AccountBranch;
import com.deppon.crm.module.common.shared.domain.BankCity;
import com.deppon.crm.module.common.shared.domain.BankProvince;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.recompense.server.manager.impl.ClaimManager;
import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.BankAccount;
import com.deppon.crm.module.recompense.shared.domain.Claim;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.Payment;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.Waybill;

public class RecompenseServiceTest extends TestCase{

	static ClassPathXmlApplicationContext factory;
	public static ClaimManager claimManager = null;
	public static User user = null;
	public static Claim claim = null;
	public static RecompenseService recompenseService = null;
	static {
		recompenseService = TestUtil.recompenseService;
	}
	
	@Test
	public void  testSubmitPaymentToFOSS(){
		RecompenseApplication recompense=new RecompenseApplication();
		Payment p=new Payment();
		p.setAccountProp(Constants.ACCOUNT_PROPERTIES_CASHIER_ACCOUNT);
		p.setAccount("111111111111111");
		BankCity bankCity=new BankCity();
		bankCity.setCode("511400");
		BankProvince bankProvince=new BankProvince();
		bankProvince.setCode("510000");
		AccountBank bank=new  AccountBank();
		bank.setCode("06");
		AccountBranch bra=new AccountBranch();
		bra.setCode("102665940312");
		p.setBankCity(bankCity);
		p.setBankProvice(bankProvince);
		p.setBranch(bra);
		p.setBank(bank);
		p.setMobile("13515895777");
		p.setOpenName("yff");
		p.setPaymentMode(Constants.CUST_DROWMONEY_TYPE_REMIT);
		recompense.setPayment(p);
		recompense.setRecompenseNum("819201125");
	
		recompense.setRecompenseAmount(1000.0);
		List<DeptCharge> deptCharges=new ArrayList<DeptCharge>();
		DeptCharge d=new DeptCharge();
		d.setAmount(1000.0);
		d.setDeptCode("DP01212");
		deptCharges.add(d);
		recompense.setRealAmount(1000.0);
		recompense.setDeptChargeList(deptCharges);
		recompense.setRecompenseType(Constants.ABNORMAL_SIGN);
		recompense.setRecompenseMethod(Constants.FAST_TYPE);
		recompense.setReportDept("DP01212");
		recompense.setClaimParty("1");
		recompense.setLeaveCustomerId("20130115-12745908");
		Waybill waybill=new Waybill();
		waybill.setLeaveCustomerId("40000533");
		waybill.setArriveCustomerId("aa");
		recompense.setWaybill(waybill);
		
		
		Employee e=new Employee();
		user=new User();
		
		e.setEmpCode("018670");
		user.setEmpCode(e);
		Boolean b=recompenseService.submitPaymentToFOSS(recompense, user);
		System.out.println(b);
		
		
	}

}
