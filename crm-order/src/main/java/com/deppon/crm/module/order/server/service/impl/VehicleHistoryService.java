/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title VehicleHistoryService.java
 * @package com.deppon.crm.module.order.server.service.impl 
 * @author Administrator
 * @version 0.1 2012-10-16
 */
package com.deppon.crm.module.order.server.service.impl;

import java.util.List;

import com.deppon.crm.module.order.server.dao.impl.VehicleHistoryDao;
import com.deppon.crm.module.order.server.service.IVehicleHistoryService;
import com.deppon.crm.module.order.shared.domain.VehicleHistory;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title VehicleHistoryService.java
 * @package com.deppon.crm.module.order.server.service.impl 
 * @author 苏玉军
 * @version 0.1 2012-10-16
 */

public class VehicleHistoryService implements IVehicleHistoryService {
	private VehicleHistoryDao vehicleHistoryDao;

	/**
	 *@return  vehicleHistoryDao;
	 */
	public VehicleHistoryDao getVehicleHistoryDao() {
		return vehicleHistoryDao;
	}

	/**
	 * @param vehicleHistoryDao : set the property vehicleHistoryDao.
	 */
	public void setVehicleHistoryDao(VehicleHistoryDao vehicleHistoryDao) {
		this.vehicleHistoryDao = vehicleHistoryDao;
	}

	/**
	 * 
	 * <p>
	 * Description:添加约车历史<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午10:58:30
	 * @param history
	 * @return
	 * @update 2013-1-28上午10:58:30
	 */
	@Override
	public boolean addVehicleHistory(VehicleHistory history) {
		return vehicleHistoryDao.saveVehicleHistory(history);
	}

	/**
	 * 
	 * <p>
	 * Description:更新约车历史<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午10:58:43
	 * @param history
	 * @return
	 * @update 2013-1-28上午10:58:43
	 */
	@Override
	public boolean updateVehicleHistory(VehicleHistory history) {
		return vehicleHistoryDao.updateVehicleHistory(history);
	}

	/**
	 * 
	 * <p>
	 * Description:获取约车历史<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午10:58:57
	 * @param history
	 * @return
	 * @update 2013-1-28上午10:58:57
	 */
	@Override
	public List<VehicleHistory> getVechicleHistory(VehicleHistory history) {
		return vehicleHistoryDao.getVehicleHistory(history);
	}

}
