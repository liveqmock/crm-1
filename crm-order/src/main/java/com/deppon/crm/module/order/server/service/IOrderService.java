package com.deppon.crm.module.order.server.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.client.order.domain.AppointmentCarInfo;
import com.deppon.crm.module.client.order.domain.ChannelOrderStatusInfo;
import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.client.order.domain.WaybillInfo;
import com.deppon.crm.module.client.sms.domain.SmsInformation;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.order.shared.domain.OrderAcceptConfig;
import com.deppon.crm.module.order.shared.domain.OrderAcceptInfo;
import com.deppon.crm.module.order.shared.domain.OrderOperationLog;
import com.deppon.crm.module.order.shared.domain.OrderReminder;
import com.deppon.crm.module.order.shared.domain.OrderSearchCondition;
import com.deppon.foss.crm.ResultDetal;
/**
 * 
 * <p>
 * Description:订单service<br />
 * </p>
 * @title IOrderService.java
 * @package com.deppon.crm.module.order.server.service 
 * @author zouming
 * @version 0.1 2013-1-22上午11:12:05
 */
public interface IOrderService {
	/**
	 * @作者：罗典
	 * @描述：根据订单号查询订单详情(接口用)
	 * @时间：2012-11-8
	 * @参数：orderNumer 渠道单号或订单号
	 * @返回值: Order 订单信息
	 * */
	public Order queryOrderByOrderNumber(String OrderNumber);
	/**
	 * 
	 * @description 根据渠道客户id查询其注册信息.
	 * @author 安小虎
	 * @version 0.1
	 * @param 渠道客户id
	 * @date 2012-7-14
	 * @return 注册对象
	 * @update 2012-7-14 上午9:05:57
	 */
	RegisterInfo getRegisterInfo(String channelCustId);

	/**
	 * @作者：罗典
	 * @时间：2012-6-28
	 * @描述：绑定淘宝商城ID后修改此客户订单的发货客户信息
	 * @参数：order 需修改的订单的发货客户信息
	 * @返回值：修改的订单条数
	 * */
	int updateOrderByOrderPerson(Order order);
	
	/**
	 * 
	 * @description 根据订单号查询出订单及官网用户注册信息.
	 * @author 安小虎
	 * @version 0.1
	 * @param 订单号
	 * @date 2012-6-25
	 * @return 订单和用户注册信息
	 * @update 2012-6-25 下午3:22:55
	 */
	 Map<String, Object> getOrderAndRegInfoByOrderNum(String orderNum);

	/**
	 * 
	 * <p>
	 * Description:根据ERPID查询订单<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22上午11:12:36
	 * @param erpId
	 * @return
	 * List<Order>
	 * @update 2013-1-22上午11:12:36
	 */
	List<Order> getOrderByErpId(String erpId);

	/**
	 * @作者：罗典
	 * @描述：根据渠道订单号查询订单信息
	 * @时间：2012-4-17
	 * @参数：channelNumber 渠道单号
	 * @返回值: Order 订单信息
	 * */
	Order queryOrderByChannelNumber(String channelNumber);
	
	/**
	 * @作者：罗典
	 * @时间：2012-4-13
	 * @描述：根据条件查询订单信息
	 * @参数：map 查询条件信息
	 * @返回值：List<Order> 订单信息集合
	 * */
	List<Order> queryOrders(Map<String,Object> map);
	
	/**
	 * @作者：罗典
	 * @时间：2012-4-13
	 * @描述：根据条件查询订单信息总数
	 * @参数：map 查询条件信息
	 * @返回值：int 总数
	 * */
	int countOrders(Map<String,Object> map);
	/**
	 * 
	 * @description 获取数据库id.  
	 * @author 潘光均
	 * @version 0.1 2012-3-28
	 * @param b true or false   
	 *@date 2012-3-28
	 * @return none
	 * @update 2012-3-28 下午9:17:39
	 */
	String getSequnce();
	/**
	 * 
	 * @description 保存订单接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-8
	 * @param 订单对象
	 * @date 2012-3-8
	 * @return 订单对象
	 * @update 2012-3-8 下午4:10:15
	 */
	Order saveOrder(Order order);

	/**
	 * 
	 * @description 修改订单.
	 * @author 安小虎
	 * @version 0.1 2012-3-12
	 * @param 订单对象
	 * @date 2012-3-12
	 * @return int：1修改成功
	 * @update 2012-3-12 上午11:31:12
	 */
	Order updateOrder(Order order);

	 /**
	 *
	 * @description 更新外部系统和本系统中订单属性.
	 * @author 潘光均
	 * @version 0.1 2012-3-13
	 * @param String
	 * @param Order
	 * @date 2012-3-13
	 * @return none
	 * @update 2012-3-13 下午3:07:52
	 */
	 Order updateOrderWithChannel(ChannelOrderStatusInfo channelOrder,Order order);

	/**
	 * 
	 * @description 通过订单号查询订单.
	 * @author 潘光均
	 * @version 0.1 2012-3-12
	 * @param String
	 * @date 2012-3-12
	 * @return Order
	 * @update 2012-3-12 下午5:41:23
	 */
	Order getOrderById(String id);

	/**
	 * 
	 * @Title: searchOrders
	 * @Description: 按查询条件查询订单
	 * @param @param osc
	 * @param @return 设定文件
	 * @return List<Order> 返回类型
	 * @throws
	 */
	List<Order> searchOrders(OrderSearchCondition osc);

	/**
	 * 
	 * @Title: getOrders
	 * @Description: 查询分配和拒绝订单
	 * @param @param osc
	 * @param @return 设定文件
	 * @return List<Order> 返回类型
	 * @throws
	 */
	List<Order> searchAssignAndRefuseOrders(OrderSearchCondition osc);

	/**
	 * 
	 * @Title: getOrderOperationLogList
	 * @Description: 按订单号查询操作
	 * @param @param orderId
	 * @param @return 设定文件
	 * @return List<OrderOperationLog> 返回类型
	 * @throws
	 */
	List<OrderOperationLog> getOrderOperationLogList(String orderId);


	/**
	 * 
	 * @Title: getOrdersByIds
	 * @Description: 获取订单IDS的订单
	 * @param @param ids
	 * @param @return 设定文件
	 * @return List<Order> 返回类型
	 * @throws
	 */
	List<Order> getOrdersByIds(List<String> ids);

	/**
	 * 
	 * @Title: updateOrderBatch
	 * @Description: 更新批量分配的状态
	 * @param @param orderIds
	 * @param @param acceptDept
	 * @param @param startStation
	 * @param @param orderStatus
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	boolean updateOrderAssignBatch(List<String> orderIds,
			String acceptDept, String acceptDeptName,String startStation, 
			String orderStatus, List<OrderOperationLog> orderOperationLogList);

	/**
	 * 
	 * @Title: saveOrderOperationLog
	 * @Description: 保持操作记录
	 * @param @param orderOperationLog
	 * @param @return 设定文件
	 * @return OrderOperationLog 返回类型
	 * @throws
	 */
	OrderOperationLog saveOrderOperationLog(
			OrderOperationLog orderOperationLog);

	/**
	 * 
	 * @Title: saveOrderOperationLogList
	 * @Description: 保存批量操作记录
	 * @param @param orderOperationLogList
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	boolean saveOrderOperationLogList(
			List<OrderOperationLog> orderOperationLogList);

	/**
	 * @description 查询订单受理默认加载订单数据.
	 * @author 潘光均
	 * @version 0.1 2012-3-15
	 * @param String
	 * @param String
	 * @date 2012-3-15
	 * @return List<Order>
	 * @update 2012-3-15 下午2:22:48
	 */
	abstract List<Order> searchOrdersByDeptIdsAndStatus(
			List<String> deptIds, String status);

	/**
	 * 
	 * @description 查询订单操作记录集合按操作时间倒序.
	 * @author 安小虎
	 * @version 0.1 2012-3-15
	 * @param 订单操作记录对象
	 * @date 2012-3-15
	 * @return 订单操作记录集合
	 * @update 2012-3-15 下午3:26:48
	 */
	List<OrderOperationLog> getListByCondtion(
			OrderOperationLog orderOperationLog);

	/**
	 * @description 将约车信息导入ERP.
	 * @author 潘光均
	 * @version 0.1 2012-3-16
	 * @param ERPOrder
	 * @date 2012-3-16
	 * @return Order
	 * @throws CrmBusinessException 
	 * @update 2012-3-16 下午5:12:43
	 */
	boolean updateOrderToErp(AppointmentCarInfo erpOrder, Order order) throws CrmBusinessException;

	/**
	 * @description 通过订单查询订单信息.
	 * @author 潘光均
	 * @version 0.1 2012-3-19
	 * @param String
	 * @date 2012-3-19
	 * @return Order
	 * @update 2012-3-19 上午11:44:15
	 */
	Order getOrderByOrderNumber(String orderNumber);

	/**
	 * @description 通过运单号查询运单信息.
	 * @author 潘光均
	 * @version 0.1 2012-3-19
	 * @param String
	 * @date 2012-3-19
	 * @return Waybill
	 * @throws CrmBusinessException 
	 * @update 2012-3-19 上午11:48:30
	 */
	WaybillInfo getWaybillByNumber(String waybillNumber) throws CrmBusinessException;

	/**
	 * @description 通过运单号查询订单信息.
	 * @author 潘光均
	 * @version 0.1 2012-3-19
	 * @param String
	 * @date 2012-3-19
	 * @return Order
	 * @update 2012-3-19 下午4:53:19
	 */
	Order getOrderByWaybillNumber(String waybillNumber);

	/**
	 * @description 定运单关联.
	 * @author 潘光均
	 * @version 0.1 2012-3-19
	 * @param Order
	 * @date 2012-3-19
	 * @return Order
	 * @throws CrmBusinessException 
	 * @update 2012-3-19 下午4:55:49
	 */
	boolean associateOrderWaybill(Order order,
			String newWaybillNum) throws CrmBusinessException;

	/**
	 * @description 删除订单.
	 * @author 潘光均
	 * @version 0.1 2012-3-20
	 * @param String
	 * @date 2012-3-20
	 * @return none
	 * @update 2012-3-20 上午9:59:06
	 */
	void deleteOrder(String id);

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
	void saveOrderReminder(List<OrderReminder> orderReminderList);

	/**
	 * @description 查询受理订单总条数.  
	 * @author 潘光均
	 * @version 0.1 2012-3-22
	 * @param OrderSearchCondition   
	 *@date 2012-3-22
	 * @return Long
	 * @update 2012-3-22 上午10:48:50
	 */
	Long getTotalCount(OrderSearchCondition orderSearchCondition);
	/**
	 * 
	 * @description 短信发送接口.  
	 * @author 潘光均
	 * @version 0.1 2012-3-29
	 * @param String   
	 * @param String
	 *@date 2012-3-29
	 * @return boolean
	 * @update 2012-3-29 上午11:36:54
	 */
	public boolean sendMessage(SmsInformation info); 
	/**
	 * 
	 * <p>
	 * Description:根据下单客户ID与发货地址查询历史订单，确认始发网点<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-3-29
	 * @param custId 下单客户ID
	 * @param contactProvinceString 发货省份
	 * @param contactCity 发货城市
	 * @param contactArea 发货区域
	 * @param contactAddress 发货地址
	 * @return
	 * String
	 */
	String getStartStationByHistory(String custId,String contactProvince ,String contactCity,String contactArea,String contactAddress);
	/**
	 * @description 根据客户最近一次发货信息查询始发网点Id.  
	 * @author 潘光均
	 * @version 0.1 2012-3-31
	 * @param String   
	 *@date 2012-3-31
	 * @return String
	 * @update 2012-3-31 上午10:53:29
	 */
	String getStartStationByHistory(String shipperId);
	/**
	 * 
	 * @description 更新订单状态.  
	 * @author 潘光均
	 * @version 0.1 2012-4-9
	 * @param String  
	 * @param String  
	 *@date 2012-4-9
	 * @return none
	 * @update 2012-4-9 上午8:30:13
	 */
	boolean updateOrderStatus(String id,String status);

	/**
	 * 
	 * @description 根据客户ID、起始时间获取订单信息列表(只获取订单有绑定联系人的记录列表).
	 * @author 安小虎
	 * @version 0.1 2012-4-24
	 * @param 客户ID
	 * @param 创建开始时间
	 * @param 创建结束时间
	 * @param start
	 * @param limit
	 * @date 2012-4-24
	 * @return 订单集合
	 * @update 2012-4-24 下午5:10:35
	 */
	List<Order> getOrderListByCustId(String custId, Date startDate,
			Date endDate,
			int start, int limit);

	/**
	 * 
	 * @description 根据客户ID、起始时间获取订单信息记录数(只获取订单有绑定联系人的记录列表).
	 * @author 安小虎
	 * @version 0.1 2012-4-24
	 * @param 客户ID
	 * @param 创建开始时间
	 * @param 创建结束时间
	 * @date 2012-4-24
	 * @return 记录数
	 * @update 2012-4-24 下午5:10:35
	 */
	Long getOrderCountByCustId(String custId, Date startDate, Date endDate);
	
	/**
	 * 
	 * <p>
	 * Description:查询起始站电话<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-21下午6:23:16
	 * @param phone
	 * @return
	 * String
	 * @update 2013-1-21下午6:23:16
	 */
	String searchStartStationByPhone(String phone);

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

	/**
	 * 
	 * @Title: sendFossOrderAcceptInfo
	 * @Description: 发送待受理订单的部门和订单数量给FOSS
	 */
	List<ResultDetal> sendFossOrderAcceptInfo(List<OrderAcceptInfo> oaiList);
	/**
	 * 
	 * @Title: updateDelayToUnaccept
	 * @Description: 延迟状态变为待受理状态
	 * @return 
	 */
	public void updateDelayToUnaccept(String statusDelay,String statusUnaccept);

	/**
	 * @description 同步订单状态.
	 * @author 黄展明
	 * @version 0.1 2013-9-3
	 * @param order
	 * @return void
	 */
	void syncOrderStatus(Order order);

	/**
	 * @description 订单和运单关联.
	 * @author 黄展明
	 * @version 0.1 2013-11-28
	 * @return void
	 * @throws CrmBusinessException
	 */
	public boolean linkOrderWaybill(String orderNumber, String newWaybillNum,
			String oldWaybillNum) throws CrmBusinessException;

	/**
	 * @description 查询运单信息.
	 * @author 黄展明
	 * @version 0.1 2013-11-28
	 * @return void
	 * @throws CrmBusinessException
	 */
	public FossWaybillInfo queryFossWaybillInfo(String waybillNum)
			throws CrmBusinessException;

}
