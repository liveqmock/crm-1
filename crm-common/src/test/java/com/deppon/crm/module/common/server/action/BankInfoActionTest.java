package com.deppon.crm.module.common.server.action;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.shared.domain.BankView;

public class BankInfoActionTest extends TestCase{
	private static ApplicationContext ctx = null;
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	BankInfoAction action = null;
	@Before
	public void setUp() throws Exception {
		try {
			if (ctx == null) {
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			action = ctx.getBean(BankInfoAction.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSearchBankInfoByBankView() {
		BankView bankView=new BankView();
		bankView.setAccountBank("22");
		action.setBankView(bankView);
		action.searchBankInfoByBankView();
		System.out.println(action.getBankViewList().size()+" ==============");
		for(BankView bv:action.getBankViewList()){
			System.out.println(bv.getAccountBranch());
		}
		//assertEquals(action.searchBankInfoByBankView(), "success");
	}

}
