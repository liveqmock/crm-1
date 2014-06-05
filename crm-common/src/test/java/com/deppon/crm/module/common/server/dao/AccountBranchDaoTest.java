package com.deppon.crm.module.common.server.dao;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.server.dao.impl.AccountBranchDao;
import com.deppon.crm.module.common.shared.domain.AccountBranch;
import com.deppon.crm.module.common.shared.domain.BankView;

public class AccountBranchDaoTest extends TestCase{
	private AccountBranchDao accountBranchDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	@Before
	public void setUp() throws Exception {
		try{
			if(ctx ==null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			accountBranchDao = ctx.getBean(AccountBranchDao.class);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAccountBranchs() {
		BankView bv=new BankView();
//		bv.setCityId("2247");
//		bv.setAccountBranch("工行分行");
//		bv.setCity("");
	//	bv.setCity("2247");
		bv.setAccountBankId("22");
		bv.setAccountBranchId("25");
		List<BankView> bvList=accountBranchDao.getAccountBranchs(bv);
		assertNotNull(bvList);
	}
	
	@Test
	public void testGetAccountBranchById() {
//		BankView getAccountBranchById=new BankView();
//		List<AccountBranch> abList=accountBranchDao.getAccountBranchById(25);
//		for (AccountBranch accountBranch : abList) {
//			System.out.println(accountBranch.getName());
//			System.out.println(accountBranch.getfId());
//		}
	}
}
