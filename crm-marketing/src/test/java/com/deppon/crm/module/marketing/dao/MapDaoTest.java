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
import com.deppon.crm.module.marketing.server.dao.impl.MapDao;
import com.deppon.crm.module.marketing.shared.domain.CustomerLocation;
import com.deppon.crm.module.marketing.shared.domain.MapQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.MapView;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;

/**   
 * <p>
 * Description:五公里地图测试<br />
 * </p>
 * @title MapDaoTest.java
 * @package com.deppon.crm.module.marketing.dao 
 * @author zhujunyong
 * @version 0.1 Mar 29, 2012
 */

public class MapDaoTest {
	private IMapDao mapDao;
	private JdbcTemplate jdbc;
	
	@Before
	public void setUp(){	
		mapDao = (IMapDao) SpringTestHelper.get().getBean(MapDao.class);
	}
	
	@Test
	public void testInsertCustomerLocation() {
		CustomerLocation customerLocation = new CustomerLocation();
		customerLocation.setCreateUser("1");
		customerLocation.setCreateDate(new Date());
		customerLocation.setModifyUser("1");
		customerLocation.setModifyDate(new Date());
		customerLocation.setCustomerId("199");
		customerLocation.setCustomerType("MEMBER");
		customerLocation.setLat("lat");
		customerLocation.setLng("lng");
		Assert.assertTrue(mapDao.insertCustomerLocation(customerLocation));

		customerLocation.setLinkmanId("1");
		Assert.assertTrue(mapDao.insertCustomerLocation(customerLocation));
	}
	
	@Test
	public void testIsCustomerLocationExist() {
		CustomerLocation customerlocation = new CustomerLocation();	
		customerlocation.setCustomerId("1920000000000000");
		customerlocation.setCustomerType("POTENT");
		boolean bool = mapDao.isCustomerLocationExist(customerlocation);
		System.out.println(bool);
		
		customerlocation.setCustomerType("MEMBER");	
		customerlocation.setCustomerId("199");
		bool = mapDao.isCustomerLocationExist(customerlocation);
		System.out.println(bool);
	}
	
	
	
	@Test
	public void testUpdateCustomerLocation() {
		CustomerLocation customerLocation = new CustomerLocation();
		customerLocation.setCustomerId("192");
		customerLocation.setCustomerType("MEMBER");
		customerLocation.setLat("lat000");
		customerLocation.setLng("lng000");
		mapDao.updateCustomerLocation(customerLocation);
//		Assert.assertTrue(mapDao.updateCustomerLocation(customerLocation));
		customerLocation.setCustomerId("191");
		mapDao.updateCustomerLocation(customerLocation);
//		Assert.assertFalse(mapDao.updateCustomerLocation(customerLocation));
	}
	
	@Test
	public void testSearchUsers() {
		int start = 0;
		int limit = 20;
		MapQueryCondition condition = new MapQueryCondition();
		// test member
		condition.setCustomerName("%刘枚英%");
		condition.setCustomerType("MEMBER");
		condition.setDeptId("11469");
//TODO:	表或视图不存在
//		List<MapView> list = mapDao.searchUsers(condition, start, limit);
		condition.setCustomerName("%肖高飞%");
		condition.setCustomerType("PC_SC");
//TODO:		list = mapDao.searchUsers(condition, start, limit);
		condition.setLinkmanMobile("18037681526");
		condition.setCustomerName(null);
//		list = mapDao.searchUsers(condition, start, limit);
	}
}
