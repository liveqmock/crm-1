package com.deppon.crm.module.servicerecovery.server.manager;

import java.sql.Connection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.servicerecovery.server.manager.IServiceRecoveryManager;
import com.deppon.crm.module.servicerecovery.server.util.ServiceRecoveryConstant;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.foss.framework.server.context.AppContext;

/**
 * Description:服务补救DAO测试 Create on 2012-11-1 下午3:04:04
 * 
 * @author 华龙
 * @version 1.0
 */
public class FinanceDeptManagerTest {
	static IFinanceDeptManager financeDeptManager;
	static Connection conn = null;
	static ServiceRecovery serviceRecovery = new ServiceRecovery();
	static User user=new User();
	static {
		financeDeptManager = TestUtil.financeDeptManager;
		
	}
	@Test
	public void testfind(){
		financeDeptManager.searchFinanceDeptList();
		
		
	}

}
