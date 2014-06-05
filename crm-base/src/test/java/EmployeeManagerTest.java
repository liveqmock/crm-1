import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext; 
import com.deppon.crm.module.uums.server.dao.ICompanyDao;
import com.deppon.crm.module.uums.server.dao.Imp.CompanyDaoImp;
import com.deppon.crm.module.uums.server.manager.ISyncUumsDataManager;
import com.deppon.crm.module.uums.server.manager.impl.SyncUumsCompanyInfoManager;
import com.deppon.crm.module.uums.server.manager.impl.SyncUumsEmployeeInfoManager;
import com.deppon.crm.module.uums.server.manager.impl.syncFacedManager;
import com.deppon.crm.module.uums.shared.domain.CompanyInfo;
import com.deppon.crm.module.uums.shared.domain.EmployeeInfo;

/**作者：zouming
 *创建时间：2012-12-26
 *最后修改时间：2012-12-26
 *描述：
 */
public class EmployeeManagerTest {
	private static ApplicationContext factory;
	private ISyncUumsDataManager syncManager;
	private EmployeeInfo employeeInfo;
	@Before
	public void setUp(){
		String[] resource = new String[] { "spring.xml" };
		//String[] resource = new String[] { "crm-baseNew/src/main/resources/com/deppon/crm/module/uums/server/META-INF/spring.xml" };
		if (factory == null) {
			factory = new ClassPathXmlApplicationContext(resource);
		}
		syncManager = factory.getBean(syncFacedManager.class);
	}
	@Test
	public void insert() throws Exception{
		employeeInfo=new EmployeeInfo();
		employeeInfo.setBirthday(new Date());
		employeeInfo.setDeptName("www");
		employeeInfo.setDeptCode("wP01172");
		employeeInfo.setEmployeeName("ssss");
		employeeInfo.setDeptCode("23");
		employeeInfo.setPosition("客服组经理");
		employeeInfo.setGender("1");
		employeeInfo.setTheMainCode("34");
		employeeInfo.setStatus("1");
		employeeInfo.setDeptBenchmarkCode("DP01172");
		employeeInfo.setChangeType(SyncUumsEmployeeInfoManager.INSERT);
		syncManager.check(employeeInfo);
	}
	@Test
	public void update()throws Exception{
		employeeInfo=new EmployeeInfo();
		employeeInfo.setBirthday(new Date());
		employeeInfo.setDeptCode("wP01172");
		employeeInfo.setDeptCode("23");
		employeeInfo.setStatus("1");
		employeeInfo.setDeptBenchmarkCode("DP01172");
		employeeInfo.setBirthday(new Date());
		employeeInfo.setDeptName("555");
		employeeInfo.setDeptCode("W0113050605");
		employeeInfo.setEmployeeName("6666");
		employeeInfo.setPosition("大区总经理");
		employeeInfo.setTheMainCode("34");
		employeeInfo.setChangeType(SyncUumsEmployeeInfoManager.UPDATE);
		syncManager.check(employeeInfo);
	}
	@Test
	public void delete()throws Exception{
		employeeInfo=new EmployeeInfo();
		employeeInfo.setBirthday(new Date());
		employeeInfo.setDeptCode("wP01172");
		employeeInfo.setDeptCode("23");
		employeeInfo.setStatus("1");
		employeeInfo.setDeptBenchmarkCode("DP01172");
		employeeInfo.setBirthday(new Date());
		employeeInfo.setDeptName("555");
		employeeInfo.setDeptCode("W0113050605");
		employeeInfo.setEmployeeName("6666");
		employeeInfo.setPosition("大区总经理");
		employeeInfo.setTheMainCode("34");
		employeeInfo.setChangeType(SyncUumsEmployeeInfoManager.DELETE);
		syncManager.check(employeeInfo);
	}
	@Test
	public void reuse()throws Exception{
		employeeInfo=new EmployeeInfo();
		employeeInfo.setBirthday(new Date());
		employeeInfo.setDeptCode("wP01172");
		employeeInfo.setDeptCode("23");
		employeeInfo.setStatus("1");
		employeeInfo.setDeptBenchmarkCode("DP01172");
		employeeInfo.setBirthday(new Date());
		employeeInfo.setDeptName("555");
		employeeInfo.setDeptCode("W0113050605");
		employeeInfo.setEmployeeName("6666");
		employeeInfo.setPosition("大区总经理");
		employeeInfo.setTheMainCode("34");
		employeeInfo.setChangeType(SyncUumsEmployeeInfoManager.REUSED);
		syncManager.check(employeeInfo);
	}
}
