/**   
 * @title OrderDao.java
 * @package com.deppon.crm.module.order.server.dao.impl
 * @description what to do
 * @author 安小虎
 * @update 2012-3-9 上午11:35:19
 * @version V1.0   
 */
package com.deppon.crm.module.order.server.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.order.server.dao.IOrderDao;
import com.deppon.crm.module.order.server.dao.ISequenceTool;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.order.shared.domain.OrderSearchCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * @description订单DAO实现
 * @author 安小虎
 * @version 0.1 2012-3-9
 * @date 2012-3-9
 */
public class OrderDao extends iBatis3DaoImpl implements IOrderDao {
	//orderDao配置的命名空间
	private static final String NAMESPACE_ORDER = "com.deppon.crm.module.order.shared.domain.Order.";
	//根据ID更新订单状态
	private static final String UPDATE_ORDER_STATUS_BY_ID = "updateOrderStatusByID";
	//订单ID 序列
	private static final String SEQ_ID_ORDER = "seq_id_order";
	//插入订单
	private static final String INSERT_ORDER = "insertOrder";
	//更新订单
	private static final String UPDATE_ORDER = "updateOrder";
	//根据ID删除订单
	private static final String DELETE_ORDERBYID = "deleteOrderByID";
	//根据ID查询订单
	private static final String QUERY_ORDERBYID = "queryOrderByID";
	//根据编码查询订单
	private static final String QUERY_ORDERBYNUM = "queryOrderByNum";
	//根据运单号查询订单
	private static final String QUERY_ORDERBYWAYBILLNUMBER = "queryOrderByWaybillNumber";
	//根据输入条件查询订单的SQL ID
	private static final String SEARCH_ORDER_BY_CONDITION = "searchOrderByCondition";
	//根据ID列表获取订单
	private static final String GET_ORDERS_BY_IDS = "getOrdersByIds";
	//批量更新订单分配	
	private static final String UPDATE_ORDER_ASSIGN_BATCH = "updateOrderAssignBatch";
	//通过部门ID和状态获取订单信息的SQL ID
	private static final String GET_ORDER_BY_DEPTIDANDSTATUS = "getOrderByDeptIdAndStatus";
	//通过输入条件查询订单数量
	private static final String SEARCH_ORDERCOUNT_BYCONDITION = "searchOrderCountByCondition";
	//通过历史查询起始站数据
	private static final String QUERY_START_STATION_BY_HISTORY = "queryStartStationByHistory";
	//通过发货人ID查询起始站信息的SQL ID
	private static final String QUERY_START_STATION_BY_SHIPPERID = "queryStartStationByShipperId";
	//通过合同电话查询起始站信息 的SQL ID
	private static final String QUERY_START_STATION_BY_FCONTACTMOBILE = "queryStartStationByContactmobile";
	//通过客户ID获取订单列表的 SQL id
	private static final String GET_ORDERLIST_BYCUSTID = "getOrderListByCustId";
	//通过客户ID获取订单总数
	private static final String GET_ORDERCOUNT_BYCUSTID = "getOrderCountByCustId";
	// 根据渠道单号查询订单信息
	private static final String QUERY_ORDER_CHANNELNUMBER = "queryOrderByChannelNumber";
	// 根据条件查询订单信息
	private static final String QUERY_ORDER_CONDITION = "queryOrderByCondition";
	// 根据条件查询满足条件的订单总数
	private static final String QUERY_ORDER_COUNT_CONDITION = "countOrderByCondition";
	//通过ERP ID获取订单信息
	private static final String GET_ORDER_BYERPID = "getOrderByErpId";
	// 根据淘宝商城ID修改订单发货人信息
	private static final String UPDATE_ORDER_ORDERPERSION = "updateOrderByOrderPerson";
	// 根据单号查询订单详细信息(接口用)
	private final String QUERY_ORDER_NUM = "queryOrderByNumber";
	//更新订单已延迟状态为待受理
	private static final String UPDATE_DELAYTOUNACCEPT = "updateDelayToUnaccept";
	// 通用序列工具
	private ISequenceTool sequenceTool;
	
	//unchecked
	private static final String UNCHECKED = "unchecked";
	//rawTypes
	private static final String RAWTYPES = "rawtypes";
	
	/**
	 *@return  sequenceTool;
	 */
	public ISequenceTool getSequenceTool() {
		return sequenceTool;
	}
	/**
	 * @param sequenceTool : set the property sequenceTool.
	 */
	public void setSequenceTool(ISequenceTool sequenceTool) {
		this.sequenceTool = sequenceTool;
	}
	
	/**
	 * @作者：罗典
	 * @描述：根据订单号查询订单详情(接口用)
	 * @时间：2012-11-8
	 * @参数：orderNumer 渠道单号或订单号
	 * @返回值: Order 订单信息
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public Order queryOrderByOrderNumber(String OrderNumber) {
		List<Order> orderList = (List<Order>)this.getSqlSession().
				selectList(NAMESPACE_ORDER+QUERY_ORDER_NUM,OrderNumber);
		if(orderList.size() > 0){
			return orderList.get(0);
		}
		return null;
	}
	/**
	 * @作者：罗典
	 * @时间：2012-6-28
	 * @描述：绑定淘宝商城ID后修改此客户订单的发货客户信息
	 * @参数：order 需修改的订单的发货客户信息
	 * @返回值：修改的订单条数
	 * */
	public int updateOrderByOrderPerson(Order order) {
		return this.getSqlSession().update(
				NAMESPACE_ORDER + UPDATE_ORDER_ORDERPERSION, order);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-13
	 * @描述：根据条件查询订单信息
	 * @参数：map 查询条件信息
	 * @返回值：List<Order> 订单信息集合
	 * */
	@SuppressWarnings(UNCHECKED)
	@Override
	public List<Order> queryOrders(Map<String, Object> map) {
		return (List<Order>) this.getSqlSession().selectList(
				NAMESPACE_ORDER + QUERY_ORDER_CONDITION, map);
	}

	/**
	 * @作者：罗典
	 * @描述：根据渠道订单号查询订单信息
	 * @时间：2012-4-17
	 * @参数：channelNumber 渠道单号
	 * @返回值: Order 订单信息
	 * */
	public Order queryOrderByChannelNumber(String channelNumber) {
		return (Order) this.getSqlSession().selectOne(
				NAMESPACE_ORDER + QUERY_ORDER_CHANNELNUMBER, channelNumber);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-13
	 * @描述：根据条件查询订单信息总数
	 * @参数：map 查询条件信息
	 * @返回值：int 总数
	 * */
	public int countOrders(Map<String, Object> map) {
		return Integer.parseInt(this.getSqlSession().selectOne(
				NAMESPACE_ORDER + QUERY_ORDER_COUNT_CONDITION, map)
				+ "");
	}

	/**
	 * 
	 * @description 保存订单DAO接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-9
	 * @param 订单对象
	 * @date 2012-3-9
	 * @return 订单对象
	 * @update 2012-3-9 上午11:42:20
	 * 
	 * @see com.deppon.crm.module.order.server.dao.IOrderDao#insertOrder(com.deppon
	 *      .crm.module.order.shared.domain.Order)
	 */
	@Override
	public Order saveOrder(Order order) {
		this.getSqlSession().insert(NAMESPACE_ORDER + INSERT_ORDER, order);
		return order;
	}

	/**
	 * 
	 * @description 修改订单DAO接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-10
	 * @param 订单对象
	 * @date 2012-3-10
	 * @return 订单对象
	 * @update 2012-3-10 下午2:14:31
	 * 
	 * @see com.deppon.crm.module.order.server.dao.IOrderDao#updateOrder(com.deppon
	 *      .crm.module.order.shared.domain.Order)
	 */
	@Override
	public Order updateOrder(Order order) {
		this.getSqlSession().update(NAMESPACE_ORDER + UPDATE_ORDER, order);
		return order;
	}

	/**
	 * 
	 * @description 根据订单ID删除订单DAO接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-10
	 * @param 订单ID
	 * @date 2012-3-10
	 * @return 成功与否
	 * @update 2012-3-10 下午3:47:31
	 * 
	 * @see com.deppon.crm.module.order.server.dao.IOrderDao#deleteOrderById(java
	 *      .lang.String)
	 */
	@Override
	public boolean deleteOrderById(String id) {
		int result = this.getSqlSession().delete(
				NAMESPACE_ORDER + DELETE_ORDERBYID, id);
		return result > 0 ? true : false;
	}

	/**
	 * 
	 * @description 根据订单ID查找订单DAO接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-10
	 * @param 订单ID
	 * @date 2012-3-9
	 * @return 订单对象
	 * @update 2012-3-9 下午5:14:57
	 * 
	 * @see com.deppon.crm.module.order.server.dao.IOrderDao#selectOrderByID(java
	 *      .lang.String)
	 */
	@Override
	public Order getOrderById(String id) {
		return (Order) this.getSqlSession().selectOne(
				NAMESPACE_ORDER + QUERY_ORDERBYID, id);
	}

	/**
	 * 
	 * @description 根据订单号查找订单DAO接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-19
	 * @param 订单号
	 * @date 2012-3-19
	 * @return 订单对象
	 * @update 2012-3-19 下午2:20:08
	 * 
	 * @see com.deppon.crm.module.order.server.dao.IOrderDao#getOrderByNum(java.lang
	 *      .String)
	 */
	@Override
	public Order getOrderByNum(String orderNumber) {
		return (Order) this.getSqlSession().selectOne(
				NAMESPACE_ORDER + QUERY_ORDERBYNUM, orderNumber);
	}

	/**
	 * 
	 * @description 根据运单号查找订单DAO接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-19
	 * @param 运单号
	 * @date 2012-3-19
	 * @return 订单对象
	 * @update 2012-3-19 下午4:20:08
	 * 
	 * @see com.deppon.crm.module.order.server.dao.IOrderDao#getOrderByWaybillNumber
	 *      (java.lang.String)
	 */
	@Override
	public Order getOrderByWaybillNumber(String waybillNumber) {
		return (Order) this.getSqlSession().selectOne(
				NAMESPACE_ORDER + QUERY_ORDERBYWAYBILLNUMBER, waybillNumber);
	}

	/**
	 * 
	 * @description 根据订单状态集合查询订单集合DAO接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-13
	 * @param 订单状态集合
	 * @date 2012-3-13
	 * @return 订单集合
	 * @update 2012-3-13 下午2:17:55
	 * 
	 * @see com.deppon.crm.module.order.server.dao.IOrderDao#queryOrderListByOrderStatus
	 *      (java.util.List)
	 */
	/*
	 * @描述：删除注释了的代码 
	 * @修改人：邹明
	 * @时间 2013-01-22
	 */

	/**
	 * 
	 * @description 查询订单条数根据条件.
	 * @author 安小虎
	 * @version 0.1 2012-3-22
	 * @param 订单查询视图
	 * @date 2012-3-22
	 * @return 订单条数
	 * @update 2012-3-22 上午11:36:39
	 * 
	 * @see com.deppon.crm.module.order.server.dao.IOrderDao#searchOrderCountByCondition
	 *      (com.deppon.crm.module.order.shared.domain.OrderSearchCondition)
	 */
	@Override
	public Long searchOrderCountByCondition(OrderSearchCondition condition) {
		return (Long) this.getSqlSession().selectOne(
				NAMESPACE_ORDER + SEARCH_ORDERCOUNT_BYCONDITION, condition);
	}

	/**
	 * 
	 * <p>
	 * Description:根据输入条件查询订单<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-23上午9:35:08
	 * @param osc
	 * @return
	 * @update 2013-1-23上午9:35:08
	 */
	@SuppressWarnings(UNCHECKED)
	@Override
	public List<Order> searchOrders(OrderSearchCondition osc) {
		if (osc == null || osc.getStart() == null || osc.getLimit() == null) {
			return (List<Order>) getSqlSession().selectList(
					NAMESPACE_ORDER + SEARCH_ORDER_BY_CONDITION, osc);
		} else {
			RowBounds bounds = new RowBounds(osc.getStart(), osc.getLimit());
			return (List<Order>) getSqlSession().selectList(
					NAMESPACE_ORDER + SEARCH_ORDER_BY_CONDITION, osc, bounds);
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:搜索未分配和拒绝的订单<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-23上午9:37:44
	 * @param osc
	 * @return
	 * @update 2013-1-23上午9:37:44
	 */
	@SuppressWarnings(UNCHECKED)
	@Override
	public List<Order> searchAssignAndRefuseOrders(OrderSearchCondition osc) {
		RowBounds bounds = new RowBounds(osc.getStart(), osc.getLimit());
		return (List<Order>) getSqlSession().selectList(
				NAMESPACE_ORDER + SEARCH_ORDER_BY_CONDITION, osc, bounds);
	}

	/**
	 * 
	 * <p>
	 * Description:根据订单ID列表获取订单<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-23上午9:38:13
	 * @param orderIds
	 * @return
	 * @update 2013-1-23上午9:38:13
	 */
	@SuppressWarnings(UNCHECKED)
	@Override
	public List<Order> getOrdersByIds(List<String> orderIds) {
		return (List<Order>) getSqlSession().selectList(
				NAMESPACE_ORDER + GET_ORDERS_BY_IDS, orderIds);
	}
	
	/**
	 * 
	 * <p>
	 * Description:更新订单分配批处理<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22下午2:07:51
	 * @param orderIds
	 * @param acceptDeptId
	 * @param acceptDeptName
	 * @param startStation
	 * @param orderStatus
	 * @return
	 * @update 2013-1-22下午2:07:51
	 */
	@SuppressWarnings({ UNCHECKED, RAWTYPES })
	@Override
	public boolean updateOrderAssignBatch(List<String> orderIds,
			String acceptDeptId, String acceptDeptName, String startStation,
			String orderStatus) {
		Map map = new HashMap();
		map.put("orderIds", orderIds);
		map.put("acceptDept", acceptDeptId);
		map.put("acceptDeptName", acceptDeptName);
		map.put("startStation", startStation);
		map.put("orderStatus", orderStatus);
		int updated = getSqlSession().update(
				NAMESPACE_ORDER + UPDATE_ORDER_ASSIGN_BATCH, map);
		return updated == orderIds.size();
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午11:26:58
	 * @param deptIds
	 * @param status
	 * @return
	 * @update 2013-1-28上午11:26:58
	 */
	@SuppressWarnings({ UNCHECKED, RAWTYPES })
	@Override
	public List<Order> getOrderListByDeptIdsAndStatus(List<String> deptIds,
			String status) {
		Map map = new HashMap();
		map.put("deptIds", deptIds);
		map.put("status", status);
		return getSqlSession().selectList(
				NAMESPACE_ORDER + GET_ORDER_BY_DEPTIDANDSTATUS, map);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午11:27:15
	 * @return
	 * @update 2013-1-28上午11:27:15
	 */
	@Override
	public String getSequence() {
		return sequenceTool.getSequence(SEQ_ID_ORDER);
	}
	
	/**
	 * 
	 * <p>
	 * Description:从历史里面查询起始站<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22下午2:08:50
	 * @param custId
	 * @param contactProvince
	 * @param contactCity
	 * @param contactArea
	 * @param contactAddress
	 * @return
	 * @update 2013-1-22下午2:08:50
	 */
	@SuppressWarnings({ UNCHECKED, RAWTYPES })
	@Override
	public String queryStartStationByHistory(String custId,
			String contactProvince, String contactCity, String contactArea,
			String contactAddress) {

		Map map = new HashMap();
		map.put("custId", custId);
		map.put("contactProvince", contactProvince);
		map.put("contactCity", contactCity);
		map.put("contactArea", contactArea);
		map.put("contactAddress", contactAddress);

		return (String) getSqlSession().selectOne(
				NAMESPACE_ORDER + QUERY_START_STATION_BY_HISTORY, map);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午11:27:23
	 * @param shipperId
	 * @return
	 * @update 2013-1-28上午11:27:23
	 */
	@Override
	public String queryStartStationByHistory(String shipperId) {
		return (String) getSqlSession().selectOne(
				NAMESPACE_ORDER + QUERY_START_STATION_BY_SHIPPERID, shipperId);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午11:27:31
	 * @param id
	 * @param status
	 * @return
	 * @update 2013-1-28上午11:27:31
	 */
	@SuppressWarnings({ RAWTYPES, UNCHECKED })
	@Override
	public boolean updateOrderStatus(String id, String status) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("orderStatus", status);
		return getSqlSession().update(
				NAMESPACE_ORDER + UPDATE_ORDER_STATUS_BY_ID, map) > 0;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午11:27:36
	 * @param custId
	 * @param startDate
	 * @param endDate
	 * @param start
	 * @param limit
	 * @return
	 * @update 2013-1-28上午11:27:36
	 */
	@SuppressWarnings({ RAWTYPES, UNCHECKED })
	@Override
	public List<Order> getOrderListByCustId(String custId, Date startDate,
			Date endDate, int start, int limit) {
		Map map = new HashMap();
		map.put("custId", custId);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		RowBounds rowBounds = new RowBounds(start, limit);
		return this.getSqlSession().selectList(
				NAMESPACE_ORDER + GET_ORDERLIST_BYCUSTID, map, rowBounds);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午11:27:41
	 * @param custId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @update 2013-1-28上午11:27:41
	 */
	@SuppressWarnings({ RAWTYPES, UNCHECKED })
	@Override
	public Long getOrderCountByCustId(String custId, Date startDate,
			Date endDate) {
		Map map = new HashMap();
		map.put("custId", custId);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return (Long) this.getSqlSession().selectOne(
				NAMESPACE_ORDER + GET_ORDERCOUNT_BYCUSTID, map);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午11:27:46
	 * @param waybillNumber
	 * @update 2013-1-28上午11:27:46
	 */
	@Override
	public void updateOrderByWaybillNumber(String waybillNumber) {
		getSqlSession().update(NAMESPACE_ORDER + "updateOrderByWaybillNumber",
				waybillNumber);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午11:27:51
	 * @param erpId
	 * @return
	 * @update 2013-1-28上午11:27:51
	 */
	@SuppressWarnings(UNCHECKED)
	@Override
	public List<Order> getOrderByErpId(String erpId) {
		return this.getSqlSession().selectList(
				NAMESPACE_ORDER + GET_ORDER_BYERPID, erpId);
	}
	/**
	 * @description 输入手机号，光标离开输入栏后即根据手机号查找客户信息，<br/>
	 * 若已存在此固定客户，将客户信息（客户编码、联系人、客户名称、电话、地址、<br/>
	 * 始发网点（最近一次成功发货网点，使用部门为营业部时，始发网点默认为本部门）自动带出；<br/>
	 * @author 钟琼
	 * @version 0.1 2012-10-11
	 */
	@Override
	public String getStartStationId(String phone) {
		return (String) getSqlSession().selectOne(
				NAMESPACE_ORDER + QUERY_START_STATION_BY_FCONTACTMOBILE, phone);
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
		Map map = new HashMap();
		map.put("statusDelay", statusDelay);
		map.put("statusUnaccept", statusUnaccept);
		getSqlSession().update(NAMESPACE_ORDER + UPDATE_DELAYTOUNACCEPT,map);
	}

}
