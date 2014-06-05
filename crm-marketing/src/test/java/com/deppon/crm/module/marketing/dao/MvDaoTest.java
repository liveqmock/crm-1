/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MapDaoTest.java
 * @package com.deppon.crm.module.marketing.dao 
 * @author zhujunyong
 * @version 0.1 Mar 29, 2012
 */
package com.deppon.crm.module.marketing.dao;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.deppon.crm.module.marketing.server.dao.IMapDao;
import com.deppon.crm.module.marketing.server.dao.IMaterializedView;
import com.deppon.crm.module.marketing.server.dao.IMaterializedViewPS;
import com.deppon.crm.module.marketing.server.dao.impl.MapDao;
import com.deppon.crm.module.marketing.server.dao.impl.MaterializedViewImpl;
import com.deppon.crm.module.marketing.server.dao.impl.MaterializedViewPSImpl;
import com.deppon.crm.module.marketing.shared.domain.CustomerLocation;
import com.deppon.crm.module.marketing.shared.domain.MapQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.MapView;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;

/**   
 * <p>
 * Description:物化视图刷新DAO<br />
 * </p>
 * @title MvDaoTest.java
 * @package com.deppon.crm.module.marketing.dao 
 * @author zhangzw
 * @version 2013-5-28
 */

public class MvDaoTest {
	private IMaterializedView mvDao;
	private IMaterializedViewPS mvDaoPs;
	private JdbcTemplate jdbc;
	
	@Before
	public void setUp(){	
		mvDao = SpringTestHelper.get().getBean(MaterializedViewImpl.class);
		mvDaoPs=SpringTestHelper.get().getBean(MaterializedViewPSImpl.class);
	}
//	@Test
//	public void testRefreshCust(){
//		System.out.println("Refresh the materialized view");
//		mvDao.reFreshMVCuststomerMonthly();
//		mvDao.reFreshMVCuststomerDaily();
//
//	}
//	@Test
//	public void testUpdatePs(){
//		mvDaoPs.reFreshMVPSCustomerDaily();
//	}
}
