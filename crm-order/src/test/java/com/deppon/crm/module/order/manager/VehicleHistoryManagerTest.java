package com.deppon.crm.module.order.manager;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager;
import com.deppon.crm.module.order.server.manager.IVehicleHistoryManager;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.order.shared.domain.OrderOperationLog;
import com.deppon.crm.module.order.shared.domain.VehicleHistory;
import com.deppon.crm.module.order.util.TestUtil;
import com.deppon.foss.framework.server.context.AppContext;
/**
 * 
 * <p>
 * Description:约车历史manager测试<br />
 * </p>
 * @title VehicleHistoryManagerTest.java
 * @package com.deppon.crm.module.order.manager 
 * @author 华龙
 * @version 0.1 2012-12-21
 */
public class VehicleHistoryManagerTest extends TestCase {
	static IVehicleHistoryManager vehicleHistoryManager;
	static ILadingstationDepartmentManager ladingstationDepartmentManager;
	OrderOperationLog log;
	Order order = null;
	List<String> ids = new ArrayList<String>();
	User user = null;
	static VehicleHistory vehicleHistory = null;
	static {
	
		vehicleHistoryManager = TestUtil.vehicleHistoryManager;
		ladingstationDepartmentManager =TestUtil.ladingstationDepartmentManager;

		vehicleHistory = new VehicleHistory();
		vehicleHistory.setUserVehicleDeptCode("110");
		vehicleHistory.setUseVehicleDeptName("上海营业部");
		vehicleHistory.setVehicleTeamCode("112");
		vehicleHistory.setVehicleTeamName("青浦车队");
	}
	/**
	 * 
	 * <p>
	 * Description:AdjustVehicleHistory<br />
	 * </p>
	 * @author 华龙
	 * @version 0.1 2012-12-21
	 * void
	 */
	@Test
	public void testAdjustVehicleHistory() {
		vehicleHistory.setUserVehicleDeptCode("8888888888");
		vehicleHistoryManager.adjustVehicleHistory(vehicleHistory);
		
		vehicleHistory.setUseVehicleDeptName("三趟街");
		vehicleHistoryManager.adjustVehicleHistory(vehicleHistory);
		vehicleHistory.setUserVehicleDeptCode("11111");
		vehicleHistory.setUseVehicleDeptName("上海营222业部");
		vehicleHistory.setVehicleTeamCode("1133212");
		vehicleHistory.setVehicleTeamName("青浦1111车队");
		vehicleHistoryManager.adjustVehicleHistory(vehicleHistory);
		
	}
}
