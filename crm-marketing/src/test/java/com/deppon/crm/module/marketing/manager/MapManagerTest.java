/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MapManagerTest.java
 * @package com.deppon.crm.module.marketing.manager 
 * @author zhujunyong
 * @version 0.1 Mar 29, 2012
 */
package com.deppon.crm.module.marketing.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.common.shared.domain.Head;
import com.deppon.crm.module.marketing.server.manager.IMapManager;
import com.deppon.crm.module.marketing.server.manager.impl.MapManager;
import com.deppon.crm.module.marketing.shared.domain.CustomerLocation;
import com.deppon.crm.module.marketing.shared.domain.MapQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.MapView;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.crm.module.marketing.utils.TradeCache;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.server.context.UserContext;

/**   
 * <p>
 * Description:五公里地图测试类<br />
 * </p>
 * @title MapManagerTest.java
 * @package com.deppon.crm.module.marketing.manager 
 * @author zhujunyong
 * @version 0.1 Mar 29, 2012
 */

public class MapManagerTest {
	private IMapManager mapManager;
	private JdbcTemplate jdbc;
	private User user = new User();;
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		mapManager = (IMapManager) SpringTestHelper.get().getBean(MapManager.class);
		jdbc = (JdbcTemplate) SpringTestHelper.get().getBean(JdbcTemplate.class);
		user.setId("1");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("238217", null);
		map.put("1", null);
		map.put("250218", null);
		map.put("22241", null);
		map.put("26238", null);
		Set<String> ids = map.keySet();
		user.setDeptids(ids);
		Employee emp = new Employee();
		emp.setId("1");
		Department dept=new Department();
		dept.setId("1");
		emp.setDeptId(dept);
		user.setEmpCode(emp);
		UserContext.setCurrentUser(user);
		
		// set cache
		List<Map<String, Object>> list = jdbc.queryForList("select fcode as code, fcodedesc as codedesc from t_code_detail where fcodetype = ?", new Object[]{"TRADE"});
		List<Detail> detailList = new ArrayList<Detail>();
		for (Map<String, Object> map1 : list) {
			Detail detail = new Detail();
			detail.setCode((String)map1.get("code"));
			detail.setCodeDesc((String)map1.get("codedesc"));
			detailList.add(detail);
		}
		Head keyHead = new Head();
		keyHead.setDetailList(detailList);
		TradeCache cache = new TradeCache();
		cache.put("TRADE", keyHead);
		CacheManager.getInstance().shutdown();
		CacheManager.getInstance().registerCacheProvider(cache);

//		for (Detail detail : detailList) {
//			System.out.println(detail.getCode()+"==="+detail.getCodeDesc());
//		}
		
//		jdbc.execute("delete from t_crm_custlocation where fid = 192168");
//		jdbc.execute("insert into t_crm_custlocation(fid, fcustid, fcusttype, flat, flng) values(192168, 192, 'MEMBER', '123', '789')");
//
//		
//		// 会员表
//		jdbc.execute("delete from t_cust_custbasedata where fid = 192");
//		jdbc.execute("insert into t_cust_custbasedata (fid, fcustname, fdegree, fcusttype, fisspecial, fbusstype, fistrangoods, fisredeempoints, fdeptid, ftradeid) values(192, 'Five Kilometre Test Customer', 'Golden', 'MEMBER', 1, 'bussType','Y', 'Y', 1234, 'CLOTH_SPIN')");
//		
//		// 联系人表
//		jdbc.execute("delete from t_cust_custlinkman where fid = 168");
//		jdbc.execute("delete from t_cust_custlinkman where fid = 169");
//		jdbc.execute("insert into t_cust_custlinkman (fid, fnumber, fborndate, fduty, flinkmantype, fcustid, fname, fmobiletel, foffertel) values(168, '223233', sysdate, 'Specialist', 'major', 192, '宋江', '13301012233', '021-69193388')");
//		jdbc.execute("insert into t_cust_custlinkman (fid, fnumber, fborndate, fduty, flinkmantype, fcustid, fname, fmobiletel, foffertel) values(169, '223234', sysdate, 'Manager', 'second', 192, '晁盖', '13301012234', '021-69193389')");
//
//		// 地址表
//		jdbc.execute("delete from t_cust_shuttleaddress where fid = 1024");
//		jdbc.execute("delete from t_cust_shuttleaddress where fid = 1025");
//		jdbc.execute("delete from t_cust_shuttleaddress where fid = 1026");
//		jdbc.execute("insert into t_cust_shuttleaddress (fid, faddress) values(1024, '明珠路徐翔路')");
//		jdbc.execute("insert into t_cust_shuttleaddress (fid, faddress) values(1025, '外高桥')");
//		jdbc.execute("insert into t_cust_shuttleaddress (fid, faddress) values(1026, '中山公园')");
//		
//		// 联系人地址关联表
//		jdbc.execute("delete from t_cust_preferenceaddress where fid = 1024");
//		jdbc.execute("delete from t_cust_preferenceaddress where fid = 1025");
//		jdbc.execute("delete from t_cust_preferenceaddress where fid = 1026");
//		jdbc.execute("insert into t_cust_preferenceaddress (fid, fshuttleaddressid, flinkmanid) values(1024, 1024, 168)");
//		jdbc.execute("insert into t_cust_preferenceaddress (fid, fshuttleaddressid, flinkmanid) values(1025, 1025, 168)");
//		jdbc.execute("insert into t_cust_preferenceaddress (fid, fshuttleaddressid, flinkmanid) values(1026, 1026, 169)");
//		
//		// 潜散客表
//		jdbc.execute("delete from T_CUST_POTENTIALSCATTER where fid = 192");
//		jdbc.execute("delete from T_CUST_POTENTIALSCATTER where fid = 193");
//		jdbc.execute("insert into T_CUST_POTENTIALSCATTER (fid, fcustname, flinkManname, flinkmanmobile, flinkmanphone, fdeptid, fcusttype, fcity, ftrade) values(192, '佳佳快运','一丈青','13916003333','021-69198090', 1234, 'SC_CUSTOMER', 2611, 'ITRONIC_FURNITURE')");
//		jdbc.execute("insert into T_CUST_POTENTIALSCATTER (fid, fcustname, flinkManname, flinkmanmobile, flinkmanphone, fdeptid, fcusttype, fcity, ftrade) values(193, '圆圆快运','一丈红','13916003334','021-69198091', 1234, 'PC_CUSTOMER', 2611, 'LIFE_ELECTRIC')");
//
//		// 地图标记表
//		jdbc.execute("delete from t_crm_custlocation where fid = 192");
//		jdbc.execute("delete from t_crm_custlocation where fid = 193");
//		jdbc.execute("delete from t_crm_custlocation where fid = 194");
//		jdbc.execute("delete from t_crm_custlocation where fid = 195");
//		jdbc.execute("delete from t_crm_custlocation where fid = 196");
//		jdbc.execute("insert into t_crm_custlocation (fid, fcustid, fcusttype, fcontactid, flat, flng) values(192, 192, 'MEMBER', 168, 'lat0002', 'lng0003')");
//		jdbc.execute("insert into t_crm_custlocation (fid, fcustid, fcusttype, fcontactid, flat, flng) values(193, 192, 'MEMBER', 169, 'lat0012', 'lng0013')");
//		jdbc.execute("insert into t_crm_custlocation (fid, fcustid, fcusttype, fcontactid, flat, flng) values(194, 192, 'SC_CUSTOMER', null, 'lats012', 'lngs013')");
//		jdbc.execute("insert into t_crm_custlocation (fid, fcustid, fcusttype, fcontactid, flat, flng) values(195, 193, 'PC_CUSTOMER', null, 'lats022', 'lngs033')");
//		jdbc.execute("insert into t_crm_custlocation (fid, fcustid, fcusttype, fcontactid, flat, flng) values(196, 192, 'SC_CUSTOMER', 169, 'lats0121', 'lngs0131')");
//		
//		jdbc.execute("delete from t_crm_custlocation where fcustid = 192 and fcusttype = 'MEMBER'");
		
	}

	@Test
	public void testMarkCustomerLocation() {
		List<CustomerLocation> request = new ArrayList<CustomerLocation>();
		CustomerLocation c1 = new CustomerLocation();
		c1.setCustomerId("192");
		c1.setCustomerType("SC_CUSTOMER");
		c1.setLat("lat1");
		c1.setLng("lng2");
		
		CustomerLocation c2 = new CustomerLocation();
		c2.setCustomerId("999");
		c2.setCustomerType("MEMBER");
		c2.setLinkmanId("123");
		c2.setLat("lat222");
		c2.setLng("lng211");

		request.add(c1);
		request.add(c2);
		
		List<Boolean> response = mapManager.markCustomerLocation(request,user);
		Assert.assertNotNull(response);
		Assert.assertEquals(2, response.size());
		for (Boolean bool : response) {
			Assert.assertTrue(bool);
		}
		
		// check exception
		request.clear();
		request.add(new CustomerLocation());
		try {
			response = mapManager.markCustomerLocation(request,user);
			Assert.fail("传入的条件全为空，应该抛出Exception");
		} catch (Throwable t) {
			Assert.assertTrue(true);
//			Assert.assertEquals("抛出的Exception类型不正确", MapException.class.getName(), t.getClass().getName());
		}
	}
	
//	@SuppressWarnings("unchecked")
//	@Test
//	public void testGetCustomerList() {
//		User user = (User) UserContext.getCurrentUser();
//		int start = 0;
//		int limit = 20;
//		MapQueryCondition condition = new MapQueryCondition();
//
//		condition.setCustomerType("PC_SC");
//		condition.setCustomerName("快运");
//		condition.setLinkmanName("一丈");
//		condition.setLinkmanPhone("021-69198090");
//		condition.setLinkmanMobile("13916003333");
//		
////		Map<String, Object> result = mapManager.getCustomerList(condition, start, limit, user);
////		List<MapView> list = (List<MapView>) result.get("data");
////		int count = (Integer) result.get("count");
////		Assert.assertNotNull(list);
////		Assert.assertTrue(list.size() > 0);
////		Assert.assertTrue(count >= list.size());
//		
////		condition = new MapQueryCondition();
////		condition.setCustomerType("MEMBER");
////		condition.setCustomerName("Five");
////		
////		result = mapManager.getCustomerList(condition, start, limit, user);
////		list = (List<MapView>) result.get("data");
////		count = (Integer) result.get("count");
////		Assert.assertNotNull(list);
//////		Assert.assertTrue(list.size() > 0);
////		Assert.assertTrue(count >= list.size());
//
//		condition.setDeptId("11469");
//		Map<String, Object> result2 = mapManager.getCustomerList(condition, start, limit, user);
//		condition.setCustomerName(null);
//		Map<String, Object> result3 = mapManager.getCustomerList(condition, start, limit, user);
//		
//	}
	
	
}
