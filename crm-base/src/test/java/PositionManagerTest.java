import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext; 
import com.deppon.crm.module.uums.server.dao.ICompanyDao;
import com.deppon.crm.module.uums.server.dao.Imp.CompanyDaoImp;
import com.deppon.crm.module.uums.server.manager.ISyncUumsDataManager;
import com.deppon.crm.module.uums.server.manager.impl.SyncUumsCompanyInfoManager;
import com.deppon.crm.module.uums.server.manager.impl.SyncUumsPositionInfoManager;
import com.deppon.crm.module.uums.server.manager.impl.syncFacedManager;
import com.deppon.crm.module.uums.shared.domain.CompanyInfo;
import com.deppon.crm.module.uums.shared.domain.PostionInfo;

/**作者：zouming
 *创建时间：2012-12-26
 *最后修改时间：2012-12-26
 *描述：
 */
public class PositionManagerTest {
	private static ApplicationContext factory;
	private ISyncUumsDataManager syncManager;
	private PostionInfo position;
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
		position=new PostionInfo();
		position.setChangeDate(new Date());
		position.setLastModifyTime(new Date());
		position.setTheMainCode("45");
		position.setTheMainId("1erer");
		position.setPositionName("IT");
		position.setChangeType(SyncUumsPositionInfoManager.EXIST);
		syncManager.check(position);
	}
	@Test
	public void update()throws Exception{
		position=new PostionInfo();
		position.setChangeDate(new Date());
		position.setLastModifyTime(new Date());
		position.setTheMainCode("67");
		position.setTheMainId("1");
		position.setPositionName("bi");
		position.setChangeType(SyncUumsPositionInfoManager.EXIST);
		syncManager.check(position);
	}
	@Test
	public void delete()throws Exception{
		position=new PostionInfo();
		position.setChangeDate(new Date());
		position.setLastModifyTime(new Date());
		position.setTheMainCode("67");
		position.setTheMainId("1");
		position.setPositionName("bi");
		position.setChangeType(SyncUumsPositionInfoManager.DELETED);
		syncManager.check(position);
	}
	@Test
	public void select(){
		//companyInfo=companyDao.searchByCode("4444");
		//System.out.println(companyInfo.getCompanyName());
		//companyDao.deleteById("111");
	}
}
