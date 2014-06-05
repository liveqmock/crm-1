/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title VehicleHistoryManager.java
 * @package com.deppon.crm.module.order.server.manager.impl 
 * @author Administrator
 * @version 0.1 2012-10-16
 */
package com.deppon.crm.module.order.server.manager.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.order.server.manager.IVehicleHistoryManager;
import com.deppon.crm.module.order.server.service.IVehicleHistoryService;
import com.deppon.crm.module.order.server.util.OrderUtil;
import com.deppon.crm.module.order.shared.domain.BookVehicleInfo;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.order.shared.domain.VehicleHistory;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title VehicleHistoryManager.java
 * @package com.deppon.crm.module.order.server.manager.impl 
 * @author 苏玉军
 * @version 0.1 2012-10-16
 */

public class VehicleHistoryManager implements IVehicleHistoryManager {
	//约车历史service
	private IVehicleHistoryService vehicleHistoryService;

	/**
	 *@return  vehicleHistoryService;
	 */
	public IVehicleHistoryService getVehicleHistoryService() {
		return vehicleHistoryService;
	}
	
	/**
	 * @param vehicleHistoryService : set the property vehicleHistoryService.
	 */
	public void setVehicleHistoryService(
			IVehicleHistoryService vehicleHistoryService) {
		this.vehicleHistoryService = vehicleHistoryService;
	}

	/**
	 * 生成最近一次约车记录
	 * 
	 * @param order
	 * @param info
	 * @param user
	 * @param vehicleTeamName
	 */
	@Transactional
	@Override
	public void generateVehicleHistory(Order order, BookVehicleInfo info,
			User user, String vehicleTeamName) {
		// 8、生成最近一次约车记录
		VehicleHistory history = OrderUtil.produceVehicleHistory(order, info,
				user, vehicleTeamName);
		adjustVehicleHistory(history);
	}

	/**
	 * 
	 * <p>
	 * Description:约车历史增加或者更新<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午11:17:04
	 * @param history
	 * @update 2013-1-28上午11:17:04
	 */
	@Override
	public void adjustVehicleHistory(VehicleHistory history) {
		//约车历史列表
		List<VehicleHistory> list = vehicleHistoryService.getVechicleHistory(history);
		//验证约车历史列表
		if(list!=null && list.size()>0){
			history.setId(list.get(0).getId());
			//约车历史不为空，则更新历史
			vehicleHistoryService.updateVehicleHistory(history);
		}else{
			//约车历史为空，则新增约车历史
			vehicleHistoryService.addVehicleHistory(history);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:获取约车历史<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午11:17:52
	 * @param user
	 * @return
	 * @update 2013-1-28上午11:17:52
	 */
	@Override
	public VehicleHistory getVehicleHistory(User user) {
		//约车历史
		VehicleHistory his = new VehicleHistory();
		//登录用户的部门名字
		String deptName = user.getEmpCode().getDeptId().getDeptName();
		//设置部门名字
		his.setUseVehicleDeptName(deptName);
		//约车历史列表
		List<VehicleHistory> list = vehicleHistoryService.getVechicleHistory(his);
		//验证约车历史列表是否为空
		if(list == null || list.size()<1){
//			如果为空，则返回空
			return null;
		}
		//返回约车历史列表第一条数据
		return list.get(0);
	}
}
