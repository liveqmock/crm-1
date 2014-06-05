/**   
 * @title OrderDaoTest.java
 * @package com.deppon.crm.module.order.dao
 * @description what to do
 * @author 安小虎
 * @update 2012-3-9 上午11:45:43
 * @version V1.0   
 */
package com.deppon.crm.module.order.dao;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.order.server.dao.IVehicleHistoryDao;
import com.deppon.crm.module.order.shared.domain.VehicleHistory;
import com.deppon.crm.module.order.util.TestUtil;

public class VehicleHistoryDaoTest extends TestCase {

	static IVehicleHistoryDao vehicleHistoryDao;
	VehicleHistory history = new VehicleHistory();
	static {
		vehicleHistoryDao = TestUtil.vehicleHistoryDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		history.setId("1");
		history.setModifyUser("123213");
		history.setUseVehicleDeptName("兰州城关区营业部");
		history.setVehicleTeamName("兰州外场车队");
	}
	
	@Test
	public void testUpdateHistory(){
		 boolean b = vehicleHistoryDao.updateVehicleHistory(history);
		 Assert.assertTrue(b);
		 
		 //更新不到数据
		 history.setId("123321123");
		 vehicleHistoryDao.updateVehicleHistory(history);
	}
	
	@Test
	public void testSaveVehicleHistory() {
		boolean isSave = vehicleHistoryDao.saveVehicleHistory(history);
		Assert.assertTrue(isSave);
	}
	@Test
	public void testVetVehicleHistory() {
		List<VehicleHistory> isQuery=vehicleHistoryDao.getVehicleHistory(history);
		Assert.assertNotNull(isQuery);
		System.out.println(isQuery.get(0).getUseVehicleDeptName());
	}
}
