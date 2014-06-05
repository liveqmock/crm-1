/**   
 * @title IOrderDao.java
 * @package com.deppon.crm.module.order.server.dao
 * @description what to do
 * @author 安小虎
 * @update 2012-3-9 上午11:33:53
 * @version V1.0   
 */
package com.deppon.crm.module.order.server.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.order.shared.domain.OrderSearchCondition;

/**
 * @description
 * @author 安小虎
 * @version 0.1 2012-3-9
 * @date 2012-3-9
 */

public interface IOrderDao {

	/**
	 * @作者：罗典
	 * @描述：根据订单号查询订单详情(接口用)
	 * @时间：2012-11-8
	 * @参数：orderNumer 渠道单号或订单号
	 * @返回值: Order 订单信息
	 * */
	public Order queryOrderByOrderNumber(String OrderNumber);
	
	/**
	 * @作者：罗典
	 * @时间：2012-6-28
	 * @描述：绑定淘宝商城ID后修改此客户订单的发货客户信息(接口用)
	 * @参数：order 需修改的订单的发货客户信息
	 * @返回值：修改的订单条数
	 * */
	int updateOrderByOrderPerson(Order order);
	
	/**
	 * 
	 * <p>
	 * Description:根据ERPID查询订单<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22上午11:32:48
	 * @param erpId
	 * @return
	 * List<Order>
	 * @update 2013-1-22上午11:32:48
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
	 * @description 查询数据库的sequence.  
	 * @author 潘光均
	 * @version 0.1 2012-3-28
	 * @param b true or false   
	 *@date 2012-3-28
	 * @return none
	 * @update 2012-3-28 下午9:13:39
	 */
	String getSequence();
	
	/**
	 * 
	 * @description 保存订单DAO接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-9
	 * @param 订单对象
	 * @date 2012-3-9
	 * @return 订单对象
	 * @update 2012-3-9 上午11:42:20
	 */
	Order saveOrder(Order order);

	/**
	 * 
	 * @description 修改订单DAO接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-10
	 * @param 订单对象
	 * @date 2012-3-10
	 * @return 订单对象
	 * @update 2012-3-10 下午2:14:31
	 */
	Order updateOrder(Order order);

	/**
	 * 
	 * @description 根据订单ID删除订单DAO接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-10
	 * @param 订单ID
	 * @date 2012-3-10
	 * @return 成功与否
	 * @update 2012-3-10 下午3:47:31
	 */
	boolean deleteOrderById(String id);

	/**
	 * 
	 * @description 根据订单ID查找订单DAO接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-10
	 * @param 订单ID
	 * @date 2012-3-9
	 * @return 订单对象
	 * @update 2012-3-9 下午5:14:57
	 */
	Order getOrderById(String id);

	/**
	 * 
	 * @description 根据订单号查找订单DAO接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-19
	 * @param 订单号
	 * @date 2012-3-19
	 * @return 订单对象
	 * @update 2012-3-19 下午2:20:08
	 */
	Order getOrderByNum(String orderNum);

	/**
	 * 
	 * @description 根据运单号查找订单DAO接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-19
	 * @param 运单号
	 * @date 2012-3-19
	 * @return 订单对象
	 * @update 2012-3-19 下午4:20:08
	 */
	Order getOrderByWaybillNumber(String waybillNumber);

	/**
	 * 
	 * @description 查询订单条数根据条件.DAO接口
	 * @author 安小虎
	 * @version 0.1 2012-3-22
	 * @param 订单查询视图
	 * @date 2012-3-22
	 * @return 订单条数
	 * @update 2012-3-22 上午11:36:39
	 */
	Long searchOrderCountByCondition(OrderSearchCondition condition);

	/**
	 * 
	 * @Title: getOrders
	 * @Description: 查询订单
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
	 * @Title: getOrdersByIds
	 * @Description: 按订单IDS查询订单
	 * @param @param orderIds
	 * @param @return 设定文件
	 * @return List<Order> 返回类型
	 * @throws
	 */
	List<Order> getOrdersByIds(List<String> orderIds);

	/**
	 * 
	 * @Title: updateOrderAssignBatch
	 * @Description: 批量更新分配订单
	 * @param @param orderIds
	 * @param @param acceptDept
	 * @param @param startStation
	 * @param @param orderStatus
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	boolean updateOrderAssignBatch(List<String> orderIds,
			String acceptDept,String acceptDeptName, String startStation, String orderStatus);

	/**
	 * @description 通过部门id.
	 * @author 潘光均
	 * @version 0.1 2012-3-15
	 * @param String
	 * @param String
	 * @date 2012-3-15
	 * @return List<Order>
	 * @update 2012-3-15 下午2:38:41
	 */
	List<Order> getOrderListByDeptIdsAndStatus(List<String> deptIds,
			String status);
	
	/**
	 * 
	 * <p>
	 * Description:查询历史受理部门<br />
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
	String queryStartStationByHistory(String custId,String contactProvince ,String contactCity,String contactArea,String contactAddress);

	/**
	 * @description 根据客户最近一次发货信息查询始发网点Id.  
	 * @author 潘光均
	 * @version 0.1 2012-3-31
	 * @param String   
	 *@date 2012-3-31
	 * @return String
	 * @update 2012-3-31 上午10:53:29
	 */
	String queryStartStationByHistory(String shipperId);
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
	 * @description 覆盖以前定运单关联的订单中的订单号.  
	 * @author 潘光均
	 * @version 0.1 2012-6-4
	 * @param 
	 *@date 2012-6-4
	 * @return void
	 * @update 2012-6-4 上午10:43:30
	 */
	void updateOrderByWaybillNumber(String waybillNumber);
	
	/**
	 * 
	 * <p>
	 * Description:获取起始站信息<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-21下午4:56:08
	 * @param phone
	 * @return
	 * String
	 * @update 2013-1-21下午4:56:08
	 */
	String getStartStationId(String phone);
	/**
	 * 
	 * @Title: updateDelayToUnaccept
	 * @Description: 延迟状态变为待受理状态
	 * @return 
	 */
	public void updateDelayToUnaccept(String statusDelay,String statusUnaccept);

}
