package com.deppon.crm.module.order.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.customer.ICustomerOperate;
import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.client.order.IOrderOperate;
import com.deppon.crm.module.client.order.IWaybillOperate;
import com.deppon.crm.module.client.order.domain.AppointmentCarInfo;
import com.deppon.crm.module.client.order.domain.ChannelOrderStatusInfo;
import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.client.order.domain.WaybillInfo;
import com.deppon.crm.module.client.sms.ISmsInfoSender;
import com.deppon.crm.module.client.sms.domain.SmsInformation;
import com.deppon.crm.module.customer.server.manager.impl.ContactManager;
import com.deppon.crm.module.order.server.dao.IOrderDao;
import com.deppon.crm.module.order.server.dao.IOrderOperationLogDao;
import com.deppon.crm.module.order.server.dao.IOrderReminderDao;
import com.deppon.crm.module.order.server.service.IOrderService;
import com.deppon.crm.module.order.server.util.Constant;
import com.deppon.crm.module.order.server.util.OrderUtil;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.order.shared.domain.OrderAcceptConfig;
import com.deppon.crm.module.order.shared.domain.OrderAcceptInfo;
import com.deppon.crm.module.order.shared.domain.OrderOperationLog;
import com.deppon.crm.module.order.shared.domain.OrderReminder;
import com.deppon.crm.module.order.shared.domain.OrderSearchCondition;
import com.deppon.crm.module.order.shared.exception.OrderException;
import com.deppon.crm.module.order.shared.exception.OrderExceptionType;
import com.deppon.foss.crm.ResultDetal;
import com.deppon.foss.crm.SyncOrderLockInfo;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.ows.OrderCouponBackRequest;

/**
 * 
 * @description 订单service
 * @author 潘光均
 * @version 0.1 2012-3-13
 * @date 2012-3-13
 */
public class OrderService implements IOrderService {
	// 订单dao
	private IOrderDao orderDao;
	// 订单操作记录dao
	private IOrderOperationLogDao orderOperationLogDao;
	// 订单client
	private IOrderOperate orderOperate;
	// 订单调用外部接口
	private IWaybillOperate waybillOperate;
	// 短信接口
	private ISmsInfoSender smsSender;
	// 绑定联系人用
	private ICustomerOperate customerOperate;
	//订单提醒Dao
	private IOrderReminderDao orderReminderDao;
	//
	private ContactManager contactManager;

	/**
	 * @作者：罗典
	 * @描述：根据订单号查询订单详情(接口用)
	 * @时间：2012-11-8
	 * @参数：orderNumer 渠道单号或订单号
	 * @返回值: Order 订单信息
	 * */
	@Override
	public Order queryOrderByOrderNumber(String OrderNumber) {
		return this.orderDao.queryOrderByOrderNumber(OrderNumber);
	}
	
	/**
	 * @作者：罗典
	 * @时间：2012-6-28
	 * @描述：绑定淘宝商城ID后修改此客户订单的发货客户信息
	 * @参数：order 需修改的订单的发货客户信息
	 * @返回值：修改的订单条数
	 * */
	public int updateOrderByOrderPerson(Order order) {
		return orderDao.updateOrderByOrderPerson(order);
	}

	/**
	 * @作者：罗典
	 * @描述：根据渠道订单号查询订单信息
	 * @时间：2012-4-17
	 * @参数：channelNumber 渠道单号
	 * @返回值: Order 订单信息
	 * */
	public Order queryOrderByChannelNumber(String channelNumber) {
		return orderDao.queryOrderByChannelNumber(channelNumber);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-13
	 * @描述：根据条件查询订单信息
	 * @参数：map 查询条件信息
	 * @返回值：List<Order> 订单信息集合
	 * */
	public List<Order> queryOrders(Map<String, Object> map) {
		return orderDao.queryOrders(map);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-13
	 * @描述：根据条件查询订单信息总数
	 * @参数：map 查询条件信息
	 * @返回值：int 总数
	 * */
	@Override
	public int countOrders(Map<String, Object> map) {
		return orderDao.countOrders(map);
	}

	/**
	 *@return  orderDao;
	 */
	public IOrderDao getOrderDao() {
		return orderDao;
	}

	/**
	 * @param orderDao : set the property orderDao.
	 */
	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}

	/**
	 *@return  orderOperationLogDao;
	 */
	public IOrderOperationLogDao getOrderOperationLogDao() {
		return orderOperationLogDao;
	}

	/**
	 * @param orderOperationLogDao : set the property orderOperationLogDao.
	 */
	public void setOrderOperationLogDao(IOrderOperationLogDao orderOperationLogDao) {
		this.orderOperationLogDao = orderOperationLogDao;
	}

	/**
	 *@return  waybillOperate;
	 */
	public IWaybillOperate getWaybillOperate() {
		return waybillOperate;
	}

	/**
	 * @param waybillOperate : set the property waybillOperate.
	 */
	public void setWaybillOperate(IWaybillOperate waybillOperate) {
		this.waybillOperate = waybillOperate;
	}

	/**
	 *@return  smsSender;
	 */
	public ISmsInfoSender getSmsSender() {
		return smsSender;
	}

	/**
	 * @param smsSender : set the property smsSender.
	 */
	public void setSmsSender(ISmsInfoSender smsSender) {
		this.smsSender = smsSender;
	}

	/**
	 *@return  customerOperate;
	 */
	public ICustomerOperate getCustomerOperate() {
		return customerOperate;
	}

	/**
	 * @param customerOperate : set the property customerOperate.
	 */
	public void setCustomerOperate(ICustomerOperate customerOperate) {
		this.customerOperate = customerOperate;
	}

	/**
	 *@return  orderReminderDao;
	 */
	public IOrderReminderDao getOrderReminderDao() {
		return orderReminderDao;
	}

	/**
	 * @param orderReminderDao : set the property orderReminderDao.
	 */
	public void setOrderReminderDao(IOrderReminderDao orderReminderDao) {
		this.orderReminderDao = orderReminderDao;
	}
	
	/**
	 *@return  orderOperate;
	 */
	public IOrderOperate getOrderOperate() {
		return orderOperate;
	}

	/**
	 * @param orderOperate : set the property orderOperate.
	 */
	public void setOrderOperate(IOrderOperate orderOperate) {
		this.orderOperate = orderOperate;
	}

	/**
	 * 
	 * <p>
	 * Description:保存订单<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:02:27
	 * @param order
	 * @return
	 * @update 2013-1-28上午9:02:27
	 */
	public Order saveOrder(Order order) {
		orderDao.saveOrder(order);
		return order;
	}

	/**
	 * 
	 * <p>
	 * Description:更新订单<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:02:42
	 * @param order
	 * @return
	 * @update 2013-1-28上午9:02:42
	 */
	@Override
	public Order updateOrder(Order order) {
		//订单操作日志列表
		Set<OrderOperationLog> logs = order.getOperationLogs();
		//如果存在列表，保存列表
		if (logs != null && logs.size() > 0) {
			saveOrderOperationLog(order.getOperationLogs().iterator().next());
		}
		this.orderDao.updateOrder(order);
		return this.orderDao.getOrderById(order.getId());
	}

	/**
	 * 
	 * <p>
	 * Description:根据ID获取订单<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:03:15
	 * @param id
	 * @return
	 * @update 2013-1-28上午9:03:15
	 */
	@Override
	public Order getOrderById(String id) {
		return orderDao.getOrderById(id);
	}
	
	/**
	 * 
	 * <p>
	 * Description:通过输入条件查询订单<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22下午5:11:07
	 * @param osc
	 * @return
	 * @update 2013-1-22下午5:11:07
	 */
	@Override
	public List<Order> searchOrders(OrderSearchCondition osc) {
		return orderDao.searchOrders(osc);
	}
	
	/**
	 * 
	 * <p>
	 * Description:搜索分配和拒绝的订单<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22下午5:11:44
	 * @param osc
	 * @return
	 * @update 2013-1-22下午5:11:44
	 */
	@Override
	public List<Order> searchAssignAndRefuseOrders(OrderSearchCondition osc) {
		return orderDao.searchAssignAndRefuseOrders(osc);
	}
	
	/**
	 * 
	 * <p>
	 * Description:获取订单操作日志列表<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22下午5:13:09
	 * @param orderId
	 * @return
	 * @update 2013-1-22下午5:13:09
	 */
	@Override
	public List<OrderOperationLog> getOrderOperationLogList(String orderId) {
		return orderOperationLogDao.getListByOrderId(orderId);
	}
	
	/**
	 * 
	 * <p>
	 * Description:通过ID列表获取订单列表<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22下午5:14:05
	 * @param ids
	 * @return
	 * @update 2013-1-22下午5:14:05
	 */
	@Override
	public List<Order> getOrdersByIds(List<String> ids) {
		return orderDao.getOrdersByIds(ids);
	}
	
	/**
	 * 
	 * <p>
	 * Description:批量更新订单分配信息<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22下午5:15:46
	 * @param orderIds
	 * @param acceptDeptId
	 * @param acceptDeptName
	 * @param startStation
	 * @param orderStatus
	 * @param orderOperationLogList
	 * @return
	 * @update 2013-1-22下午5:15:46
	 */
	@Override
	public boolean updateOrderAssignBatch(List<String> orderIds,
			String acceptDeptId, String acceptDeptName, String startStation,
			String orderStatus, List<OrderOperationLog> orderOperationLogList) {
		for (int i = 0; i < orderIds.size(); i++) {
			/*
			 * 修改时间：2013-10-16
			 * 修改人：kuang
			 * 修改原因：代码有问题，由get(0)改为get(i)
			 */
			orderOperationLogDao.saveOrderOperationLog(orderOperationLogList
					.get(i));
		}
		return orderDao.updateOrderAssignBatch(orderIds, acceptDeptId,
				acceptDeptName, startStation, orderStatus);
	}

	/**
	 * @description 订单受理时双击订单可以备注信息功能新增
	 * @author 钟琼
	 * @version 0.1 2012-10-12
	 */
	@Override
	public OrderOperationLog saveOrderOperationLog(
			OrderOperationLog orderOperationLog) {
		return orderOperationLogDao.saveOrderOperationLog(orderOperationLog);

	}
	
	/**
	 * 
	 * <p>
	 * Description:保存订单操作日志列表<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22下午5:17:15
	 * @param orderOperationLogList
	 * @return
	 * @update 2013-1-22下午5:17:15
	 */
	@Override
	public boolean saveOrderOperationLogList(
			List<OrderOperationLog> orderOperationLogList) {
		return orderOperationLogDao
				.batchSaveOrderOperationLog(orderOperationLogList);
	}

	/**
	 * 
	 * <p>
	 * Description:根据输入条件获取操作日志列表<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:08:30
	 * @param orderOperationLog
	 * @return
	 * @update 2013-1-28上午9:08:30
	 */
	@Override
	public List<OrderOperationLog> getListByCondtion(
			OrderOperationLog orderOperationLog) {
		return orderOperationLogDao.getListByCondion(orderOperationLog);
	}

	/**
	 * 
	 * <p>
	 * Description:根据部门ID和状态获取订单<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:09:06
	 * @param deptIds
	 * @param status
	 * @return
	 * @update 2013-1-28上午9:09:06
	 */
	@Override
	public List<Order> searchOrdersByDeptIdsAndStatus(List<String> deptIds,
			String status) {
		return orderDao.getOrderListByDeptIdsAndStatus(deptIds, status);
	}

	/**
	 * 
	 * <p>
	 * Description:更新订单<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:09:57
	 * @param erpOrder
	 * @param order
	 * @return
	 * @throws CrmBusinessException
	 * @update 2013-1-28上午9:09:57
	 */
	@Override
	public boolean updateOrderToErp(AppointmentCarInfo erpOrder, Order order)
			throws CrmBusinessException {
		//订单(根据订单号获取)
		Order order1 = orderDao.getOrderByNum(order.getOrderNumber());
		//验证订单状态
		if (Constant.ORDER_SATUTS_SHOUTCAR.equalsIgnoreCase(order1
				.getOrderStatus())) {
			//定义异常  “重复约车”
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_HADBOOKVIHECLE);
			//抛出异常
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
				private static final long serialVersionUID = -9118260715261431380L;
			};
		}
		/**
		 * 只更新 营业部和呼叫中心订单
		 * 
		 * @author suyujun line 288-298
		 */
		// 增加呼叫中心合肥 2013-11-12
		if (Constant.ORDER_SOURCE_BUSINESS_DEPT.equalsIgnoreCase(order1
				.getResource())
				|| OrderUtil.isCallCenter(order1.getResource())) {
			// 更新订单
			orderDao.updateOrder(order);
		} else {
			// 更新订单状态
			order1.setOrderStatus(Constant.ORDER_SATUTS_SHOUTCAR);
			orderDao.updateOrder(order1);
		}
		// 保存操作日志
		saveOrderOperationLog(order.getOperationLogs().iterator().next());
		erpOrder.setMemberType(order.getMemberType());
		erpOrder.setCouponNumber(order.getCouponNumber());
		erpOrder.setWaybillNumber(order.getWaybillNumber());
		boolean result = orderOperate.appointmentCar(erpOrder);
		return result;
	}

	/**
	 * 
	 * <p>
	 * Description:通过订单号获取订单<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:10:37
	 * @param orderNumber
	 * @return
	 * @update 2013-1-28上午9:10:37
	 */
	@Override
	public Order getOrderByOrderNumber(String orderNumber) {
		return orderDao.getOrderByNum(orderNumber);
	}

	/**
	 * 
	 * <p>
	 * Description:通过运单号获取运单信息<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:11:07
	 * @param waybillNumber
	 * @return
	 * @throws CrmBusinessException
	 * @update 2013-1-28上午9:11:07
	 */
	@Override
	public WaybillInfo getWaybillByNumber(String waybillNumber)
			throws CrmBusinessException {
		return waybillOperate.queryNotClosedWaybill(waybillNumber);
	}

	/**
	 * 
	 * <p>
	 * Description:通过运单号获取订单信息<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:12:41
	 * @param waybillNumber
	 * @return
	 * @update 2013-1-28上午9:12:41
	 */
	@Override
	public Order getOrderByWaybillNumber(String waybillNumber) {
		return orderDao.getOrderByWaybillNumber(waybillNumber);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:14:10
	 * @param order
	 * @param oldWaybillNum
	 * @return
	 * @throws CrmBusinessException
	 * @update 2013-1-28上午9:14:10
	 */
	@Override
	public boolean associateOrderWaybill(Order order, String oldWaybillNum)
			throws CrmBusinessException {

		orderDao.updateOrderByWaybillNumber(order.getWaybillNumber());

		orderDao.updateOrder(order);
		return waybillOperate.linkWaybill(order.getOrderNumber(),
				order.getWaybillNumber(), oldWaybillNum);
	}

	/**
	 * 
	 * <p>
	 * Description:删除订单<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:15:48
	 * @param id
	 * @update 2013-1-28上午9:15:48
	 */
	@Override
	public void deleteOrder(String id) {
		orderDao.deleteOrderById(id);
	}
	
	/**
	 * 
	 * <p>
	 * Description:获取未分配订单提醒<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22下午5:17:55
	 * @param statuses
	 * @return
	 * @update 2013-1-22下午5:17:55
	 */
	@Override
	public List<OrderReminder> getUnassignOrderReminder(List<String> statuses) {
		return orderReminderDao.getUnassignOrderReminder(statuses);
	}
	
	/**
	 * 
	 * <p>
	 * Description:获取未受理订单提醒<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22下午5:18:25
	 * @param statuses
	 * @return
	 * @update 2013-1-22下午5:18:25
	 */
	@Override
	public List<OrderReminder> getUnacceptOrderReminder(List<String> statuses) {
		return orderReminderDao.getUnacceptOrderReminder(statuses);
	}
	
	/**
	 * 
	 * <p>
	 * Description:获取已受理订单提醒<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22下午5:19:49
	 * @param statuses
	 * @return
	 * @update 2013-1-22下午5:19:49
	 */
	@Override
	public List<OrderReminder> getAcceptedOrderReminder(List<String> statuses) {
		return orderReminderDao.getAcceptedOrderReminder(statuses);
	}
	
	/**
	 * 
	 * <p>
	 * Description:清除订单提醒<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22下午5:20:23
	 * @update 2013-1-22下午5:20:23
	 */
	@Override
	public void clearOrderReminder() {
		orderReminderDao.clearOrderReminder();
	}

	/**
	 * 
	 * <p>
	 * Description:保存订单提醒<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22下午5:20:41
	 * @param orderReminderList
	 * @update 2013-1-22下午5:20:41
	 */
	@Override
	public void saveOrderReminder(List<OrderReminder> orderReminderList) {
		if (orderReminderList != null && orderReminderList.size() > 0){
			orderReminderDao.saveOrderReminderList(orderReminderList);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:根据输入条件获取订单总数<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:16:26
	 * @param orderSearchCondition
	 * @return
	 * @update 2013-1-28上午9:16:26
	 */
	@Override
	public Long getTotalCount(OrderSearchCondition orderSearchCondition) {
		return orderDao.searchOrderCountByCondition(orderSearchCondition);
	}

	/**
	 * 
	 * <p>
	 * Description:获取序列<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:18:08
	 * @return
	 * @update 2013-1-28上午9:18:08
	 */
	@Override
	public String getSequnce() {
		return orderDao.getSequence();
	}

	/**
	 * 
	 * <p>
	 * Description:发送消息<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:18:21
	 * @param info
	 * @return
	 * @update 2013-1-28上午9:18:21
	 */
	@Override
	public boolean sendMessage(SmsInformation info) {
		try {
			return smsSender.sendSms(info);
		} catch (CrmBusinessException e) {
			GeneralException e1 = new GeneralException(e.getErrorCode(),
					e.getMessage(), e, new Object[] {}) {
			};
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * Description:根据下单客户ID与发货地址查询历史订单，确认始发网点<br />
	 * </p>
	 * 
	 * @author Weill
	 * @version 0.1 2012-3-29
	 * @param custId
	 *            下单客户ID
	 * @param contactProvinceString
	 *            发货省份
	 * @param contactCity
	 *            发货城市
	 * @param contactArea
	 *            发货区域
	 * @param contactAddress
	 *            发货地址
	 * @return String
	 */
	public String getStartStationByHistory(String custId,
			String contactProvince, String contactCity, String contactArea,
			String contactAddress) {
		return orderDao.queryStartStationByHistory(custId, contactProvince,
				contactCity, contactArea, contactAddress);
	}

	/**
	 * 
	 * <p>
	 * Description:根据渠道跟新订单<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:18:44
	 * @param channelOrder
	 * @param order
	 * @return
	 * @update 2013-1-28上午9:18:44
	 */
	@Override
	public Order updateOrderWithChannel(ChannelOrderStatusInfo channelOrder,
			Order order) {
		// 更新订单
		Order ord = orderDao.updateOrder(order);
		// 保存操作日志
		saveOrderOperationLog(order.getOperationLogs().iterator().next());

		return ord;
	}

	/**
	 * 
	 * <p>
	 * Description:通过历史获取订单始发网点<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:19:14
	 * @param shipperId
	 * @return
	 * @update 2013-1-28上午9:19:14
	 */
	@Override
	public String getStartStationByHistory(String shipperId) {
		return orderDao.queryStartStationByHistory(shipperId);
	}

	/**
	 * 
	 * <p>
	 * Description:跟新订单状态<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:20:07
	 * @param id
	 * @param status
	 * @return
	 * @update 2013-1-28上午9:20:07
	 */
	@Override
	public boolean updateOrderStatus(String id, String status) {
		return orderDao.updateOrderStatus(id, status);
	}

	/**
	 * 
	 * <p>
	 * Description:通过客户ID获取订单列表<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:20:29
	 * @param custId
	 * @param startDate
	 * @param endDate
	 * @param start
	 * @param limit
	 * @return
	 * @update 2013-1-28上午9:20:29
	 */
	@Override
	public List<Order> getOrderListByCustId(String custId, Date startDate,
			Date endDate, int start, int limit) {
		return this.orderDao.getOrderListByCustId(custId, startDate, endDate,
				start, limit);
	}

	/**
	 * 
	 * <p>
	 * Description:获取按客户ID查询的订单总数<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:20:58
	 * @param custId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @update 2013-1-28上午9:20:58
	 */
	@Override
	public Long getOrderCountByCustId(String custId, Date startDate,
			Date endDate) {
		return this.orderDao.getOrderCountByCustId(custId, startDate, endDate);
	}

	/**
	 * 
	 * <p>
	 * Description:获取订单通过ERP ID<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:22:37
	 * @param erpId
	 * @return
	 * @update 2013-1-28上午9:22:37
	 */
	@Override
	public List<Order> getOrderByErpId(String erpId) {
		return this.orderDao.getOrderByErpId(erpId);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:23:10
	 * @param orderNum
	 * @return
	 * @update 2013-1-28上午9:23:10
	 */
	@Override
	public Map<String, Object> getOrderAndRegInfoByOrderNum(String orderNum) {
		Map<String, Object> m = new HashMap<String, Object>();
		// 订单（根据订单号获取）
		Order order = this.getOrderByOrderNumber(orderNum.trim());
		if (order != null) {
			// 不能绑定订单来源集合
			List<String> list = new ArrayList<String>();
			list.add(Constant.ORDER_SOURCE_BUSINESS_DEPT);
			list.add(Constant.ORDER_SOURCE_CALLCENTER);
			// 增加呼叫中心合肥 2013-11-12
			list.add(Constant.ORDER_SOURCE_CALLCENTER_HF);
			//订单来源
			String source = order.getResource();
			//验证订单来源
			if (source == null || "".equals(source) || list.contains(source)) {
				OrderException e1 = new OrderException(
						OrderExceptionType.BOUNDCONTACT_ORDERSOURCEERROR);
				throw new GeneralException(e1.getErrorCode(), e1.getMessage(),
						e1, new Object[] {}) {                  
				};
			}
			//客户ID
			String custId = order.getChannelCustId();
			//验证客户ID
			if (custId != null && !"".equals(custId)) {
				/**
				 * @description :CRM维护客户信息，将管网查询客户信息改为在CRM自己查询客户信息
				 * @author  kuang
				 * @date2013-7-9
				 */
				RegisterInfo registerInfo = new RegisterInfo();
				registerInfo.setUserName(custId);
				registerInfo.setCustsource(source);
				List<RegisterInfo> registerInfolist = contactManager
						.queryRegisterInfo(registerInfo);
				if (registerInfolist.isEmpty()) {
					OrderException e = new OrderException(
							OrderExceptionType.REGISTERUSERNOTEXIST);
					throw new GeneralException(e.getErrorCode(),
							e.getMessage(), e, new Object[] {}) {
					};
				} else {
					registerInfo = registerInfolist.get(0);
				}
				//验证该用户是否已绑定
				if (registerInfolist.get(0).getCusCode() != null
						&& !"".equals(registerInfolist.get(0).getCusCode())) {
					m.put("isBound", "YES");
				}
				
				m.put("registerInfo", registerInfo);
				m.put("order", order);
			}
		} else {
			OrderException e = new OrderException(OrderExceptionType.ORDER_NULL);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		return m;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午9:25:03
	 * @param channelCustId
	 * @return
	 * @update 2013-1-28上午9:25:03
	 */
	@Override
	public RegisterInfo getRegisterInfo(String channelCustId) {
		try {
			return this.customerOperate.queryRegisterUser(channelCustId);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
			OrderException e1 = new OrderException(
					OrderExceptionType.BOUNDCONTACT_CALLINTEFACEERROR);
			throw new GeneralException(e1.getErrorCode(), e1.getMessage(), e1,
					new Object[] {}) {
			};
		}
	}

	/**
	 * @description 输入手机号，光标离开输入栏后即根据手机号查找客户信息，<br/>
	 *              若已存在此固定客户，将客户信息（客户编码、联系人、客户名称、电话、地址、<br/>
	 *              始发网点（最近一次成功发货网点，使用部门为营业部时，始发网点默认为本部门）自动带出；<br/>
	 * @author 钟琼
	 * @version 0.1 2012-10-11
	 */
	@Override
	public String searchStartStationByPhone(String phone) {
		return this.orderDao.getStartStationId(phone);
	}

	public ContactManager getContactManager() {
		return contactManager;
	}

	public void setContactManager(ContactManager contactManager) {
		this.contactManager = contactManager;
	}

	/**
	 * 
	 * @Title: getOrderAcceptConfig
	 * @Description: 查询待受理订单统计参数
	 */
	@Override
	public OrderAcceptConfig getOrderAcceptConfig() {
		return orderReminderDao.getOrderAcceptConfig();
	}

	/**
	 * 
	 * @Title: addOrderAcceptDept
	 * @Description: 增加待受理订单部门
	 */
	@Override
	public void addOrderAcceptDept() {
		orderReminderDao.addOrderAcceptDept();
	}

	/**
	 * 
	 * @Title: generateOrderAcceptInfo
	 * @Description: 统计待受理订单的部门和订单数量
	 */
	@Override
	public List<OrderAcceptInfo> generateOrderAcceptInfo(OrderAcceptConfig oac) {
		return orderReminderDao.generateOrderAcceptInfo(oac);
	}

	/**
	 * 
	 * @Title: getOrderAcceptInfoByDept
	 * @Description: 查询制定部门的待受理订单数量
	 */
	@Override
	public OrderAcceptInfo getOrderAcceptInfoByDept(OrderAcceptConfig oac,
			String standardCode) {
		return orderReminderDao.getOrderAcceptInfoByDept(oac, standardCode);
	}

	/**
	 * 
	 * @Title: sendFossOrderAcceptInfo
	 * @Description: 发送待受理订单的部门和订单数量给FOSS
	 */
	@Override
	public List<ResultDetal> sendFossOrderAcceptInfo(List<OrderAcceptInfo> oaiList) {
		List<SyncOrderLockInfo> lockList = new ArrayList<SyncOrderLockInfo>();
		for (OrderAcceptInfo oai : oaiList) {
			SyncOrderLockInfo lockInfo = new SyncOrderLockInfo();
			lockInfo.setDeptCode(oai.getStandardCode());
			lockInfo.setPromptCount(oai.getWarnNum());
			lockInfo.setLockCount(oai.getLockNum());
			lockList.add(lockInfo);
		}
		try {
			return orderOperate.syncOrderLockInfo(lockList);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @description 订单已延迟更新为待受理状态.
	 * @author kuang
	 * @version 0.1 2013-9-6
	 * @param 
	 * @return 
	 */
	@Override
	public void updateDelayToUnaccept(String statusDelay,String statusUnaccept){
		orderDao.updateDelayToUnaccept(statusDelay,statusUnaccept);
	}

	/**
	 * @description 同步订单状态.
	 * @author 黄展明
	 * @version 0.1 2013-9-3
	 * @param order
	 * @return void
	 */
	@Override
	public void syncOrderStatus(Order order) {
		OrderCouponBackRequest orderInfo = new OrderCouponBackRequest();
		orderInfo.setCouponNumber(order.getCouponNumber());
		orderInfo.setOrderNumber(order.getOrderNumber());
		orderInfo.setStatus(order.getOrderStatus());
		orderOperate.orderCouponBack(orderInfo);
	}

	/**
	 * @description 订单和运单关联.
	 * @author 黄展明
	 * @version 0.1 2013-11-28
	 * @return void
	 * @throws CrmBusinessException
	 */
	@Override
	public boolean linkOrderWaybill(String orderNumber, String newWaybillNum,
			String oldWaybillNum) throws CrmBusinessException {
		return waybillOperate.linkWaybill(orderNumber, newWaybillNum,
				oldWaybillNum);
	}

	/**
	 * @description 查询运单信息.
	 * @author 黄展明
	 * @version 0.1 2013-11-28
	 * @return void
	 * @throws CrmBusinessException
	 */
	@Override
	public FossWaybillInfo queryFossWaybillInfo(String waybillNum)
			throws CrmBusinessException {
		return waybillOperate.queryWaybillInfo(waybillNum);
	}

}
