package com.deppon.crm.module.order.server.dao;

import java.util.List;

import com.deppon.crm.module.order.shared.domain.OrderAcceptConfig;
import com.deppon.crm.module.order.shared.domain.OrderAcceptInfo;
import com.deppon.crm.module.order.shared.domain.OrderReminder;

/**
 * 
 * <p>
 * Description:订单提醒接口dao<br />
 * 本次修改只是添加注释
 * </p>
 * @title IOrderReminderDao.java
 * @package com.deppon.crm.module.order.server.dao 
 * @author zouming
 * @version 0.1 2013-1-22上午11:09:31
 */
public interface IOrderReminderDao {
	/**
	 * 
	 * <p>
	 * Description:保存订单提醒信息<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-21下午4:51:02
	 * @param orderReminder
	 * @return
	 * OrderReminder
	 * @update 2013-1-21下午4:51:02
	 */
	OrderReminder saveOrderReminder(OrderReminder orderReminder);
	
	/**
	 * 
	 * <p>
	 * Description:获取所有的提醒信息<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-21下午4:52:07
	 * @return
	 * List<OrderReminder>
	 * @update 2013-1-21下午4:52:07
	 */
	List<OrderReminder> getOrderReminderAll();

	/**
	 * 
	 * @Title: getUnassignOrderReminder
	 * @Description: 获取未分配订单提醒信息
	 * @param @return 设定文件
	 * @return List<OrderReminder> 返回类型
	 * @throws
	 */
	List<OrderReminder> getUnassignOrderReminder(List<String> statuses);

	/**
	 * 
	 * @Title: getUnassignOrderReminder
	 * @Description: 获取未受理订单提醒信息
	 * @param @return 设定文件
	 * @return List<OrderReminder> 返回类型
	 * @throws
	 */
	List<OrderReminder> getUnacceptOrderReminder(List<String> statuses);

	/**
	 * 
	 * @Title: getUnassignOrderReminder
	 * @Description: 获取待受理订单提醒信息
	 * @param @return 设定文件
	 * @return List<OrderReminder> 返回类型
	 * @throws
	 */
	List<OrderReminder> getAcceptedOrderReminder(List<String> statuses);

	/**
	 * 
	 * @Title: clearOrderReminder
	 * @Description: 清楚提醒信息
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	void clearOrderReminder();

	/**
	 * 
	 * @Title: saveOrderReminder
	 * @Description: 保存提醒信息
	 * @param @param orderReminderList 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	void saveOrderReminderList(List<OrderReminder> orderReminderList);

	/**
	 * 
	 * @Title: getOrderAcceptConfig
	 * @Description: 查询待受理订单统计参数
	 */
	OrderAcceptConfig getOrderAcceptConfig();

	/**
	 * 
	 * @Title: addOrderAcceptDept
	 * @Description: 增加待受理订单部门
	 */
	void addOrderAcceptDept();

	/**
	 * 
	 * @Title: generateOrderAcceptInfo
	 * @Description: 统计待受理订单的部门和订单数量
	 */
	List<OrderAcceptInfo> generateOrderAcceptInfo(OrderAcceptConfig oac);

	/**
	 * 
	 * @Title: getOrderAcceptInfoByDept
	 * @Description: 查询制定部门的待受理订单数量
	 */
	OrderAcceptInfo getOrderAcceptInfoByDept(OrderAcceptConfig oac,
			String standardCode);

}
