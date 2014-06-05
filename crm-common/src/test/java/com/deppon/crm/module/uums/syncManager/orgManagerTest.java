package com.deppon.crm.module.uums.syncManager;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.deppon.crm.module.sync.server.manager.Imp.EmployeeSyncManagerImp;


/**作者：zhangzhenwei
 *创建时间：2014-1-3
 *最后修改时间：2014-1-3
 *描述：
 */
public class orgManagerTest {
	private static ApplicationContext factory;
	private EmployeeSyncManagerImp syncManager;
	private Map employeeInfo;
	String CHANGETYPE="changeType";
	String MAINCODE="mainCode";
	String ID="mainId";
	String POSITION="position";
	String PARENTID="parentOrgId";
	String ORGID="orgId";
	@Before
	public void setUp(){
		employeeInfo=new HashMap<String,String>();
		String[] resource = new String[] { "syncBeanTest.xml" };
		if (factory == null) {
			factory = new ClassPathXmlApplicationContext(resource);
		}
		syncManager = factory.getBean(EmployeeSyncManagerImp.class);
	}
	@Test
	public void insert(){
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
