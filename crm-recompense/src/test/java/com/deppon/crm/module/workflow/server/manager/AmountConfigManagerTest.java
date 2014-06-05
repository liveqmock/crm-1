package com.deppon.crm.module.workflow.server.manager;

import java.math.BigDecimal;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.deppon.bpmsapi.module.client.domain.BpmsActivity;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.workflow.server.manager.impl.AmountConfigManagerImpl;
import com.deppon.crm.module.workflow.server.util.AmountConfigEntity;
import com.deppon.foss.framework.server.context.UserContext;

/**
 * 
 * <p>
 * Description:审批权限金额配置管理层测试<br />
 * </p>
 * @title SignetManagerManagerTest.java
 * @package com.deppon.crm.module.workflow.server.manager 
 * @author liuHuan
 * @version 0.1 2013-8-1
 */
public class AmountConfigManagerTest extends TestCase {
	
	private static AmountConfigManagerImpl amountConfigManager;
	
	static {
		amountConfigManager= TestUtil.amountConfigManager;
		amountConfigManager.setNormalClaimManager(TestUtil.normalClaimManager);
		User user = new User();
		user.setId("31967");
		Employee employee = new Employee();
		Department deparment = new Department();
		deparment.setId("316218");
		deparment.setDeptName("上海事业部营销管理组");
		employee.setId("31967");
		employee.setDeptId(deparment);
		employee.setEmpCode("002902");
		employee.setEmpName("包俊英");
		user.setEmpCode(employee);
		UserContext.setCurrentUser(user);
	}
	
	
	@Test
	public void  testQueryForBranch(){
		AmountConfigEntity ac = new AmountConfigEntity();
		ac.setId("CRM13805220776498B460EEBA2");
		amountConfigManager.queryForBranch(ac);
		if(ac!=null){
			System.out.println(ac.getMcName());
		}
	}
	
	@Test
	public void  testQueryAllBranch(){
		 AmountConfigEntity ac = new AmountConfigEntity();
		 List<AmountConfigEntity> list=amountConfigManager.queryAllBranch(ac, 0, 10);
		 for(AmountConfigEntity entity : list){
			 System.out.println(entity.getId());
		 }
	};

	@Test
	public void testGetBranchCount(){
		AmountConfigEntity ac = new AmountConfigEntity();
		System.out.println(amountConfigManager.getBranchCount(ac));
	}
	
	@Test
	public void saveOrUpdate(){
		AmountConfigEntity ac = new AmountConfigEntity();
//		ac.setId(Guid.newDCGuid());
		ac.setMcName("常规理赔申请");
		ac.setMcDefiniTionName("com.deppon.bpms.module.crm.bpsdesign.claims.generalclaims");
		ac.setCurrentApproStepName("测试(TEST)");
		ac.setCurrentApproStepNo("TEST");
		ac.setTargetApproStepName("测试2(TEST2)");
		ac.setTargetApproStepNo("TEST2");
		ac.setMinAmount(new BigDecimal(200000));
		ac.setMaxAmount(new BigDecimal(500000));
		amountConfigManager.saveOrUpdate(ac);
	}
	
	
	
		
	@Test
	public void testDeleteById(){
		amountConfigManager.deleteById("CRM13805220776498B460EEBA2");
		AmountConfigEntity ac = new AmountConfigEntity();
		ac.setId("CRM13805220776498B460EEBA2");
		System.out.println(amountConfigManager.queryForBranch(ac));
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
		System.out.println(amountConfigManager.isValid(ac));
	}
	
	@Test
	public void  testFindActivity(){
		 List<BpmsActivity> list=amountConfigManager.findActivity("com.deppon.bpms.module.crm.bpsdesign.claims.generalclaims");
		 for(BpmsActivity entity : list){
			 System.out.println(entity.getActivityName());
		 }
	}
	
}
