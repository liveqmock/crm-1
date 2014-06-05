package com.deppon.crm.module.workflow.server.dao;

import java.math.BigDecimal;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.workflow.server.dao.impl.AmountConfigDaoImpl;
import com.deppon.crm.module.workflow.server.util.AmountConfigEntity;
import com.deppon.crm.module.workflow.server.util.Guid;

/**
 * 
 * <p>
 * Description:审批权限金额配置Dao测试<br />
 * </p>
 * @title SignetManagerDaoTest.java
 * @package com.deppon.crm.module.workflow.server.dao 
 * @author liuHuan
 * @version 0.1 2013-8-1
 */
public class AmountConfigDaoTest extends TestCase {
	
	private static AmountConfigDaoImpl amountConfigDao  ;
	static {
		amountConfigDao= TestUtil.amountConfigDao;
	}

	@Test
	public void  testQueryForBranch(){
		AmountConfigEntity ac = new AmountConfigEntity();
		ac.setId("CRM13805220776498B460EEBA2");
		amountConfigDao.queryForBranch(ac);
		if(ac!=null){
			System.out.println(ac.getMcName());
		}
	}
	
	@Test
	public void  testQueryAllBranch(){
		 AmountConfigEntity ac = new AmountConfigEntity();
		 List<AmountConfigEntity> list=amountConfigDao.queryAllBranch(ac, 0, 10);
		 for(AmountConfigEntity entity : list){
			 System.out.println(entity.getId());
		 }
	}
	
	@Test
	public void testGetBranchCount(){
		AmountConfigEntity ac = new AmountConfigEntity();
		System.out.println(amountConfigDao.getBranchCount(ac));
	}
	
	@Test
	public void testInsertBranch(){
		AmountConfigEntity ac = new AmountConfigEntity();
		ac.setId(Guid.newDCGuid());
		ac.setMcName("常规理赔申请");
		ac.setMcDefiniTionName("com.deppon.bpms.module.crm.bpsdesign.claims.generalclaims");
		ac.setCurrentApproStepName("测试(TEST)");
		ac.setCurrentApproStepNo("TEST");
		ac.setTargetApproStepName("测试2(TEST2)");
		ac.setTargetApproStepNo("TEST2");
		ac.setMinAmount(new BigDecimal(200000));
		ac.setMaxAmount(new BigDecimal(500000));
		amountConfigDao.insertBranch(ac);
	}
	
	@Test
	public void testUpdateBranch(){
		AmountConfigEntity ac = new AmountConfigEntity();
		ac.setId("CRM13805220776498B460EEBA2");
		ac.setMcName("常规理赔申请");
		ac.setMcDefiniTionName("com.deppon.bpms.module.crm.bpsdesign.claims.generalclaims");
		ac.setCurrentApproStepName("测试(TEST)");
		ac.setCurrentApproStepNo("TEST");
		ac.setTargetApproStepName("测试2(TEST2)");
		ac.setTargetApproStepNo("TEST2");
		ac.setMinAmount(new BigDecimal(200000));
		ac.setMaxAmount(new BigDecimal(500000));
		amountConfigDao.updateBranch(ac);
	}
	
		
	@Test
	public void testDeleteById(){
		amountConfigDao.deleteById("CRM13805220776498B460EEBA2");
		AmountConfigEntity ac = new AmountConfigEntity();
		ac.setId("CRM13805220776498B460EEBA2");
		System.out.println(amountConfigDao.queryForBranch(ac));
	}
	
	@Test
	public void testIsValid(){
		AmountConfigEntity ac = new AmountConfigEntity();
		ac.setMcDefiniTionName("com.deppon.bpms.module.crm.bpsdesign.claims.generalclaims");
		ac.setCurrentApproStepName("测试(TEST)");
		ac.setCurrentApproStepNo("TEST");
		ac.setTargetApproStepName("测试2(TEST2)");
		ac.setTargetApproStepNo("TEST2");
		ac.setMinAmount(new BigDecimal(200000));
		ac.setMaxAmount(new BigDecimal(500000));
		System.out.println(amountConfigDao.isValid(ac));
	}

		
	
}
