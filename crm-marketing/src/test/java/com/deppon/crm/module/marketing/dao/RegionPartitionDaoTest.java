/**   
 * <p>
 * Description:客户分组<br />
 * </p>
 * @title CustomerGroupDaoTest.java
 * @package com.deppon.crm.module.marketing.server.dao.impl 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */
package com.deppon.crm.module.marketing.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.marketing.server.dao.IRegionPartitionDao;
import com.deppon.crm.module.marketing.server.dao.impl.RegionPartitionDao;
import com.deppon.crm.module.marketing.shared.domain.RegionPartition;
import com.deppon.crm.module.marketing.shared.domain.RegionPartitionConstance;
import com.deppon.crm.module.marketing.shared.domain.SearchDeptCondition;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;


/**   
 * <p>
 * Description:客户分组<br />
 * </p>
 * @title CustomerGroupDaoTest.java
 * @package com.deppon.crm.module.marketing.server.dao.impl 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */

public class RegionPartitionDaoTest {
 
	private IRegionPartitionDao regionPartitionDao;
	@Before
	public void setUp(){	
		regionPartitionDao = (RegionPartitionDao) SpringTestHelper.get().getBean(RegionPartitionDao.class);
	}
	@Test
	public void testSearchRegionDept(){
		SearchDeptCondition sdc = new SearchDeptCondition();
		sdc.setAuthCharacter(RegionPartitionConstance.HEADQUARTERS);
		sdc.setOwner(RegionPartitionConstance.OWNER);
		regionPartitionDao.searchRegionDept(sdc);
		sdc.setAuthCharacter(RegionPartitionConstance.DIVISION);
		sdc.setDeptId("1");
		regionPartitionDao.searchRegionDept(sdc);
	}
	@Test
	public void testSearchRegionByDeptId(){
		regionPartitionDao.searchRegionByDeptId("1234");
	}
	@Test
	public void testSearchRegionByParentId(){
		regionPartitionDao.searchRegionByParentId("1234");
	}
	@Test
	public void testInsertRegionPartition(){
		RegionPartition region = new RegionPartition();
		region.setDeptId("1");
		region.setRegionId("1234");
		region.setCreateDate(new Date());
		region.setCreateUser("1");
		regionPartitionDao.insertRegionPartition(region);
	}
	@Test
	public void testDeleteRegionPartitionByDeptId(){
		regionPartitionDao.deleteRegionPartition("1");
	}
	@Test
	public void testSearchDpetCharacterByDeptId(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("owner", RegionPartitionConstance.OWNER);
		map.put("deptId", "265");
		map.put("empCode", "1");
		regionPartitionDao.searchDpetCharacterByDeptId(map);
	}
}
