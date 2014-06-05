package com.deppon.crm.module.order.server.dao.impl;

import java.util.List;

import com.deppon.crm.module.order.server.dao.IVehicleHistoryDao;
import com.deppon.crm.module.order.shared.domain.VehicleHistory;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
/**
 * 
 * <p>
 * Description:约车历史dao<br />
 * </p>
 * @title VehicleHistoryDao.java
 * @package com.deppon.crm.module.order.server.dao.impl 
 * @author zouming
 * @version 0.1 2013-1-22上午11:11:31
 */
public class VehicleHistoryDao extends iBatis3DaoImpl implements
		IVehicleHistoryDao {
	/**   
	 *约车历史 命名空间
	 */
	private static final String NAMESPACE="com.deppon.crm.module.order.shared.domain.VehicleHistory.";
	/**   
	 *保存 约车历史
	 */
	private static final String SAVE_VEHICLEHISTORY = "saveVehicleHistory";
	/**   
	 *查询 约车历史
	 */
	private static final String QUERY_VEHICLEHISTORY = "queryVehicleHistory";
	//更新约车历史
	private static final String UPDATE_VEHICLEHISTORY = "updateVehicleHistory";
	/**   
	 * <p>
	 * 增加约车历史<br />
	 * </p>
	 * @package com.deppon.crm.module.order.server.dao.impl 
	 * @author 钟琼
	 * @version 0.1 2012-10-16
	 */
	@Override
	public boolean saveVehicleHistory(VehicleHistory history) {
		int result = this.getSqlSession().insert(
				NAMESPACE + SAVE_VEHICLEHISTORY, history);
		return result > 0 ? true : false;
	}
	/**
	 * 
	 * <p>
	 * Description:更新约车历史<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22上午11:11:12
	 * @param history
	 * @return
	 * @update 2013-1-22上午11:11:12
	 */
	@Override
	public boolean updateVehicleHistory(VehicleHistory history) {
		return this.getSqlSession().update(NAMESPACE + UPDATE_VEHICLEHISTORY, history) > 0 ? true : false;
	}

	/**   
	 * <p>
	 * 查询约车历史<br />
	 * </p>
	 * @package com.deppon.crm.module.order.server.dao.impl 
	 * @author 钟琼
	 * @version 0.1 2012-10-16
	 */
	@Override
	public List<VehicleHistory> getVehicleHistory(VehicleHistory history) {
		@SuppressWarnings("unchecked")
		List<VehicleHistory> vehiclehistory=(List<VehicleHistory>) this.getSqlSession().selectList(
				NAMESPACE + QUERY_VEHICLEHISTORY, history);
		return vehiclehistory;
	}

}
