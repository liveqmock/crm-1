package com.deppon.crm.module.uums.syncManager;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.server.dao.IUserDao;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.authorization.testutil.TestUtil;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.sync.server.manager.ISyncManager;
import com.deppon.crm.module.sync.server.manager.Imp.EmployeeSyncManagerImp;


/**作者：zhangzhenwei
 *创建时间：2014-1-3
 *最后修改时间：2014-1-3
 *描述：
 */
public class EmployeeManagerTest {
	private static ApplicationContext factory;
	private static ISyncManager syncManager;
	private Map<String,String> employeeInfo;
	String CHANGETYPE="changeType";
	String MAINCODE="mainCode";
	String ID="mainId";
	String POSITION="position";
	String PARENTID="parentOrgId";
	String ORGID="orgId";
	static {
		syncManager = TestUtil.empSync;
	}
	@Test
	public void insert(){
		employeeInfo=new HashMap<String,String>();
		employeeInfo.put(CHANGETYPE, "1");
		employeeInfo.put(MAINCODE, "010999");
		employeeInfo.put(ORGID, null);
		employeeInfo.put(POSITION, null);
		syncManager.check(employeeInfo);
	}
	@Test
	public void update(){
		
	}
	@Test
	public void delete(){
		
	}
	@Test
	public void reuse(){
		
	}
}
