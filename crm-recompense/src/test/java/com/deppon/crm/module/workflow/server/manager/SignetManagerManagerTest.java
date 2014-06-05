package com.deppon.crm.module.workflow.server.manager;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import junit.framework.TestCase;

import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.workflow.server.manager.impl.SignetManagerManagerImpl;
import com.deppon.crm.module.workflow.server.service.ISignetManagerService;
import com.deppon.crm.module.workflow.server.service.impl.SignetManagerServiceImpl;
import com.deppon.crm.module.workflow.shared.domain.SignetManager;

/**
 * 
 * <p>
 * Description:理赔工作流管理层测试<br />
 * </p>
 * @title SignetManagerManagerTest.java
 * @package com.deppon.crm.module.workflow.server.manager 
 * @author liuHuan
 * @version 0.1 2013-8-1
 */
public class SignetManagerManagerTest extends TestCase {
	
	private static SignetManagerManagerImpl signetManagerManager;
	
	static {
		signetManagerManager= TestUtil.signetManagerManager;
	}
	
	
	
	@Test
	public void testAddSignetManager() {
		SignetManager sm = new SignetManager();
		sm.setDeptId(111);
		sm.setEmpId(13);
//		sm.setEmpId(26404);
		int code=signetManagerManager.addSignetManager(sm);
		System.out.println(code);
	}
	
	@Test
	public void testSignetManagerByDeptId() {
		SignetManager s = signetManagerManager.getSignetManagerByDeptId("11129");
		System.out.println(s);
	}
}
