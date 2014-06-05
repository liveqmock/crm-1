package com.deppon.crm.module.order.server.service;

import java.util.List;

import com.deppon.crm.module.order.shared.domain.VehicleHistory;

/**   
 * <p>
 * 部门约车 车队操作<br />
 * </p>
 * @title IVehicleHistoryService.java
 * @package com.deppon.crm.module.order.server.service.impl 
 * @author 苏玉军
 * @version 0.1 2012-10-16
 */

public interface IVehicleHistoryService {
	/**
	 * 
	 * <p>
	 * Description:增加约车历史<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22上午11:25:46
	 * @param history
	 * @return
	 * boolean
	 * @update 2013-1-22上午11:25:46
	 */
	boolean addVehicleHistory(VehicleHistory history);
	/**
	 * 
	 * <p>
	 * Description:更新约车历史<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22上午11:26:03
	 * @param history
	 * @return
	 * boolean
	 * @update 2013-1-22上午11:26:03
	 */
	boolean updateVehicleHistory(VehicleHistory history);
	/**
	 * 
	 * <p>
	 * Description:获取约车历史<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22上午11:26:21
	 * @param history
	 * @return
	 * List<VehicleHistory>
	 * @update 2013-1-22上午11:26:21
	 */
	List<VehicleHistory> getVechicleHistory(VehicleHistory history);
}
