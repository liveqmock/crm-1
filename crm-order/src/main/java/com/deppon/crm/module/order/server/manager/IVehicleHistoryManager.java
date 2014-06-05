package com.deppon.crm.module.order.server.manager;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.order.shared.domain.BookVehicleInfo;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.order.shared.domain.VehicleHistory;

/**
 * 
 * <p>
 * Description:约车历史manager接口<br />
 * </p>
 * 
 * @title IVehicleHistoryManager.java
 * @package com.deppon.crm.module.order.server.manager
 * @author zouming
 * @version 0.1 2013-1-22上午11:20:20
 */
public interface IVehicleHistoryManager {
	/**
	 * 生成最近一次约车记录
	 * 
	 * @param order
	 * @param info
	 * @param user
	 * @param vehicleTeamName
	 */
	public void generateVehicleHistory(Order order, BookVehicleInfo info,
			User user, String vehicleTeamName);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-10-16
	 * @param history
	 *            void
	 */
	void adjustVehicleHistory(VehicleHistory history);

	/**
	 * 
	 * <p>
	 * Description:根据用户获取约车历史<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22上午11:20:02
	 * @param user
	 * @return VehicleHistory
	 * @update 2013-1-22上午11:20:02
	 */
	VehicleHistory getVehicleHistory(User user);;
}
