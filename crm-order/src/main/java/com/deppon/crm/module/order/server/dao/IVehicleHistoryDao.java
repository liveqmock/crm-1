package com.deppon.crm.module.order.server.dao;

import java.util.List;

import com.deppon.crm.module.order.shared.domain.VehicleHistory;
/**
 * 
 * <p>
 * Description:约车历史dao<br />
 * </p>
 * @title IVehicleHistoryDao.java
 * @package com.deppon.crm.module.order.server.dao 
 * @author zouming
 * @version 0.1 2013-1-22上午11:27:42
 */
public interface IVehicleHistoryDao {
	/**
	 * 
	 * <p>
	 * Description:增加约车历史<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22上午11:26:52
	 * @param history
	 * @return
	 * boolean
	 * @update 2013-1-22上午11:26:52
	 */
	boolean saveVehicleHistory(VehicleHistory history);
	/**
	 * 
	 * <p>
	 * Description:更新约车历史<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22上午11:27:06
	 * @param history
	 * @return
	 * boolean
	 * @update 2013-1-22上午11:27:06
	 */
	boolean updateVehicleHistory(VehicleHistory history);
	/**
	 * 
	 * <p>
	 * Description:获取约车历史<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22上午11:27:21
	 * @param history
	 * @return
	 * List<VehicleHistory>
	 * @update 2013-1-22上午11:27:21
	 */
	List<VehicleHistory> getVehicleHistory(VehicleHistory history);
}
