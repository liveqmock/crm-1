package com.deppon.crm.module.order.server.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.order.domain.AppointmentCarInfo;
import com.deppon.crm.module.client.order.domain.WaybillInfo;
import com.deppon.crm.module.common.shared.domain.BusDeptSearchCondition;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.PotentialCustomer;
import com.deppon.crm.module.order.shared.domain.BookVehicleInfo;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.order.shared.domain.OrderAcceptInfo;
import com.deppon.crm.module.order.shared.domain.OrderOperationLog;
import com.deppon.crm.module.order.shared.domain.OrderSearchCondition;
import com.deppon.foss.crm.ResultDetal;
/**
 * 
 * <p>
 * Description:订单Manager<br />
 * 本次修改只是添加注释
 * </p>
 * @title IOrderManager.java
 * @package com.deppon.crm.module.order.server.manager 
 * @author zouming
 * @version 0.1 2013-1-22上午11:07:57
 */
public interface IOrderManager {

	/**
	 * 
	 * @description 订单状态监控.
	 * @author 安小虎
	 * @version 0.1
	 * @date 2012-8-10
	 * @return none
	 * @update 2012-8-10 下午2:23:16
	 */
	Map<String, Object> orderStatusMonitoring(Date createStartDate,
			Date createEndDate);

	/**
	 * 
	 * @description 根据订单号查询订单包含此单渠道id对应的联系人编码.
	 * @author 安小虎
	 * @version 0.1
	 * @param 订单号
	 * @date 2012-7-14
	 * @return 订单对象
	 * @update 2012-7-14 上午8:47:23
	 */
	Order getOrderWithLinkManCode(String orderNumber);

	/**
	 * 
	 * @description 绑定联系人后需维护订单客户信息.
	 * @author 安小虎
	 * @version 0.1
	 * @param 订单对象
	 * @date 2012-7-9
	 * @return true|false
	 * @update 2012-7-9 上午10:09:22
	 */
	Boolean updateOrderNoValidate(Order order);

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
	 * @description 根据ERPID查询订单.
	 * @author 安小虎
	 * @version 0.1
	 * @param 订单号
	 * @date 2012-6-20
	 * @return 订单和用户注册信息
	 * @update 2012-6-20 下午3:22:55
	 */
	List<Order> getOrderByErpId(String erpId);
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
	List<Order> queryOrders(Map<String, Object> map);

	/**
	 * @作者：罗典
	 * @时间：2012-4-13
	 * @描述：根据条件查询订单信息总数
	 * @参数：map 查询条件信息
	 * @返回值：int 总数
	 * */
	int countOrders(Map<String, Object> map);

	/**
	 * 
	 * @description 400界面初始化始发网点.
	 * @author 潘光均
	 * @version 0.1 2012-3-31
	 * @param String
	 * @date 2012-3-31
	 * @return BussinessDept
	 * @update 2012-3-31 上午10:29:04
	 */
	BussinessDept callCenterOrderInit(String shipperId);

	/**
	 * 
	 * @description 新增订单初始化用户所在部门.
	 * @author 潘光均
	 * @version 0.1 2012-3-8
	 * @param 订单视图对象
	 * @date 2012-3-8
	 * @return 订单对象
	 * @update 2012-3-8 下午4:12:00
	 */
	BussinessDept createOrderInit(String deptName);

	/**
	 * 
	 * @description 新增订单接口.
	 * @author 安小虎
	 * @version 0.1 2012-3-8
	 * @param 订单对象
	 * @date 2012-3-8
	 * @return 订单对象
	 * @update 2012-3-8 下午4:12:00
	 */
	Order createOrder(Order vorder);

	/**
	 * 
	 * @description 订单打回.
	 * @author 安小虎
	 * @version 0.1 2012-3-12
	 * @param 订单ID
	 * @param 反馈信息
	 * @date 2012-3-12
	 * @return 订单对象
	 * @update 2012-3-12 上午11:31:12
	 */
	Order returnOrder(String id, String feedbackInfo, User user);

	/**
	 * 
	 * @description 订单撤消.
	 * @author 安小虎
	 * @version 0.1 2012-3-14
	 * @param 订单ID
	 * @param 反馈信息
	 * @date 2012-3-14
	 * @return 订单对象
	 * @update 2012-3-14 上午11:17:06
	 */
	Order cancelOrder(Order order, String feedbackInfo, User user);

	/**
	 * 
	 * @description 订单催办.
	 * @author 安小虎
	 * @version 0.1 2012-3-15
	 * @param 订单ID
	 * @date 2012-3-15
	 * @return 订单对象
	 * @update 2012-3-15 上午10:33:54
	 */
	Order urgeOrder(String id, User user);

	/**
	 * 
	 * @description 揽货失败.
	 * @author 安小虎
	 * @version 0.1 2012-3-21
	 * @param 订单ID
	 * @date 2012-3-21
	 * @return 订单对象
	 * @update 2012-3-21 下午3:42:29
	 */
	Map<String, Object> accpetFail(String id, String feedbackInfo,
			User user);

	/**
	 * 
	 * @description 修改订单
	 * @author 潘光均
	 * @version 0.1 2012-3-10
	 * @param Order
	 * @date 2012-3-10
	 * @return boolean
	 * @update 2012-3-10 上午9:08:36
	 */
	Order updateOrder(Order vorder, User user);

	/**
	 * 
	 * @description 根据客户名称查询客户信息.
	 * @author 潘光均
	 * @version 0.1 2012-3-9
	 * @param String
	 * @date 2012-3-9
	 * @return List<Member>
	 * @update 2012-3-9 下午3:30:09
	 */
	List<Member> searchMemberByName(String memberName);

	/**
	 * 
	 * @description 根据客户电话查询客户信息.
	 * @author 潘光均
	 * @version 0.1 2012-3-9
	 * @param String
	 * @date 2012-3-9
	 * @return List<Member>
	 * @update 2012-3-9 下午3:30:09
	 */
	List<Member> searchMemberByPhone(String phone);

	/**
	 * 
	 * @description 根据客户手机查询客户信息.
	 * @author 潘光均
	 * @version 0.1 2012-3-9
	 * @param String
	 * @date 2012-3-9
	 * @return List<Member>
	 * @update 2012-3-9 下午3:30:09
	 */
	Member getMenberByMobile(String mobile);

	/**
	 * 
	 * @description 查询订单是否可以修改.
	 * @author 潘光均
	 * @version 0.1 2012-3-12
	 * @param String
	 * @date 2012-3-12
	 * @return boolean
	 * @update 2012-3-12 下午5:26:52
	 */
	Map getOrderWithValidation(String id,User user);

	/**
	 * 
	 * @Title: searchOrders
	 * @Description: 分配和拒绝查询
	 * @param @param osv
	 * @param @return 设定文件
	 * @return OrderSearchView 返回类型
	 * @throws
	 */
	Map searchAssignAndRefuseOrders(OrderSearchCondition vosc);

	/**
	 * 
	 * @Title: getOrderById
	 * @Description: 按Id查询订单
	 * @param @param orderId
	 * @param @return 设定文件
	 * @return Order 返回类型
	 * @throws
	 */
	Order getOrderById(String orderId);

	/**
	 * 
	 * @Title: assignOrder
	 * @Description: 分配订单
	 * @param @param orderView 设定文件
	 * @param Order
	 *            :存放订单的受理网点信息
	 * @return void 返回类型
	 * @throws
	 */
	void assignOrder(List<String> orderIds, Order vorder, User user);

	/**
	 * 
	 * @Title: refuseOrder
	 * @Description: 拒绝订单
	 * @param @param orderView 设定文件
	 * @return void 返回类型
	 * @throws
	 * @see <a href="package.html#section">查看流程图</a>
	 */
	Order refuseOrder(String orderId, User user, String refuseReason);

	/**
	 * 
	 * @Title: getOrderOperationLogList
	 * @Description: 查询订单的操作
	 * @param @param orderId
	 * @param @return 设定文件
	 * @return OrderSearchView 返回类型
	 * @throws
	 */
	List<OrderOperationLog> getOrderOperationLogList(String orderId);

	/**
	 * 
	 * @description 查询处理和打回订单信息.
	 * @author 潘光均
	 * @version 0.1 2012-3-16
	 * @param OrderSearchView
	 * @date 2012-3-16
	 * @return OrderSearchView
	 * @update 2012-3-16 上午11:12:43
	 */
	Map searchProcessAndReturnOrders(OrderSearchCondition vosc, User user);

	/**
	 * 
	 * @description 当用户查看了订单列表后,给相应订单约车时进行校验是否可以约车.
	 * @author 潘光均
	 * @version 0.1 2012-3-16
	 * @param String
	 * @date 2012-3-16
	 * @return OrderView
	 * @update 2012-3-16 下午2:08:26
	 */
	Map getOrderIfCanBookVehicle(String orderId);

	/**
	 * 
	 * @description 订单约车.
	 * @author 潘光均
	 * @version 0.1 2012-3-16
	 * @param OrderView
	 * @date 2012-3-16
	 * @return OrderView
	 * @update 2012-3-16 下午2:58:25
	 */
	Map bookVehicle(Order vorder, BookVehicleInfo bkinfo, User user);

	/**
	 * 
	 * @description 通过订单号查询订单信息.
	 * @author 潘光均
	 * @version 0.1 2012-3-19
	 * @param b
	 *            true or false
	 * @date 2012-3-19
	 * @return none
	 * @update 2012-3-19 上午11:41:31
	 */
	Order getOrderWaybill(String orderNumber);

	/**
	 * 
	 * @description 通过运单号查询订单信息.
	 * @author 潘光均
	 * @version 0.1 2012-3-19
	 * @param b
	 *            true or false
	 * @date 2012-3-19
	 * @return none
	 * @update 2012-3-19 上午11:41:31
	 */
	Order getOrderWaybillNum(String waybillNumber);

	/**
	 * 
	 * @description 通过运单号查询运单信息.
	 * @author 潘光均
	 * @version 0.1 2012-3-19
	 * @param String
	 * @date 2012-3-19
	 * @return Waybill
	 * @update 2012-3-19 下午1:56:39
	 */
	WaybillInfo getWaybillByNumber(String number);

	/**
	 * 
	 * @description 通过订单号查询定单信息.
	 * @author 潘光均
	 * @version 0.1 2012-3-19
	 * @param String
	 * @date 2012-3-19
	 * @return Order
	 * @update 2012-3-19 下午1:56:39
	 */
	Order getOrderByNumber(String number);

	/**
	 * 
	 * @description 定运单关联
	 * @author 潘光均
	 * @version 0.1 2012-3-19
	 * @param b
	 *            true or false
	 * @date 2012-3-19
	 * @return boolean
	 * @update 2012-3-19 下午4:28:33
	 */
	boolean associateOrderWaybill(WaybillInfo vwaybill,
			String orderNumber);

	/**
	 * 
	 * @description 订单受理.
	 * @author 潘光均
	 * @version 0.1 2012-3-20
	 * @param OrderView
	 * @date 2012-3-20
	 * @return OrderView
	 * @update 2012-3-20 上午8:51:54
	 */
	Order processOrder(String id, User user, String feedbackInfo);

	/**
	 * @description 删除订单.
	 * @author 潘光均
	 * @version 0.1 2012-3-20
	 * @param String
	 * @date 2012-3-20
	 * @return none
	 * @update 2012-3-20 上午9:58:06
	 */
	void deleteOrder(String id);

	/**
	 * 
	 * @Title: remindOrderMonitor
	 * @Description: 订单提醒任务
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	void remindOrderMonitor();

	/**
	 * 
	 * @description 通过始发部门名称查询始发部门信息.
	 * @author 潘光均
	 * @version 0.1 2012-3-24
	 * @param OrderSearchView
	 * @date 2012-3-24
	 * @return OrderSearchView
	 * @update 2012-3-24 下午2:31:46
	 */
	List<BussinessDept> getDeptByName(BusDeptSearchCondition bsc);

	/**
	 * 
	 * @description 更新订单状态.
	 * @author 潘光均
	 * @version 0.1 2012-4-9
	 * @param String
	 * @param String
	 * @date 2012-4-9
	 * @return none
	 * @update 2012-4-9 上午8:30:13
	 */
	boolean updateChannelOrder(User user, Order order,
			String operContent, String operType,String standardCode);

	/**
	 * 
	 * @description 判断单号是否是一个渠道单号
	 * @author 潘光均
	 * @version 0.1 2012-3-30
	 * @param String
	 * @date 2012-3-30
	 * @return boolean
	 * @update 2012-3-30 下午2:54:26
	 */
	boolean isChannelOrderNumber(String orderNumber);

	/**
	 * 
	 * @description 创建潜客信息
	 * @author 潘光均
	 * @version 0.1 2012-5-24
	 * @param PotentialCustomer
	 * @date 2012-5-24
	 * @return
	 * @update 2012-3-30 下午2:54:26
	 */
	void createPotentialCustomer(Member cust);

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
			Date endDate, int start, int limit);

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
	 * @description 根据部门名称查询部门的数目.
	 * @author 潘光均
	 * @version 0.1 2012-5-30
	 * @param
	 * @date 2012-5-30
	 * @return long
	 * @update 2012-5-30 下午3:01:44
	 */
	long getDeptNumberByName(BusDeptSearchCondition bsc);

	/**
	 * @description function.
	 * @author 潘光均
	 * @version 0.1 2012-6-20
	 * @param
	 * @date 2012-6-20
	 * @return BussinessDept
	 * @update 2012-6-20 下午3:42:03
	 */
	BussinessDept searchBusDeptByErpId(String id);
	
	
	/**
	 * 
	 * <p>
	 * Description:通过标杆编码查询营业部门<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-1-8
	 * @param standardCode 标杆编码
	 * @return
	 * BussinessDept
	 */
	public BussinessDept searchBusDeptByStandardCode(String standardCode);
	
	/**
	 * @description 使用部门为营业部时，省份、城市、区县默认为登录者部门所在地<br/>
	 * @author 钟琼
	 * @version 0.1 2012-10-10
	 * @param dept
	 * @return Map
	 */
	Map<String,String> searchInitReceiveGoodsAddress(BussinessDept dept); 
	/**
	 * 
	 * <p>
	 * 创建订单并且约车
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-10-16
	 * @param order
	 * @param info
	 * @param user
	 * @param vehicleTeamName
	 * @return
	 * Order
	 */
	Order bookVehicleAndCreateOrder(Order order,BookVehicleInfo info,User user,String vehicleTeamName);
	
	/**
	 * @description 输入手机号，光标离开输入栏后即根据手机号查找客户信息，<br/>
	 *              若已存在此固定客户，将客户信息（客户编码、联系人、客户名称、电话、地址、<br/>
	 *              始发网点（最近一次成功发货网点，使用部门为营业部时，始发网点默认为本部门）自动带出；<br/>
	 * @author 钟琼
	 * @version 0.1 2012-10-11
	 */
	Map<String,Object> searchMemberInfoByPhone(String phone,User user);
	/**
	 * @description 订单受理时双击订单可以备注信息功能新增
	 * @author 钟琼
	 * @version 0.1 2012-10-12
	 */
	void saveOrderRemarkInfo(OrderOperationLog log,User user);
	/**
	 * 
	 * <p>
	 * Description:是否是合同客户<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-4-20
	 * @param memberId
	 * @return
	 * boolean
	 */
	public boolean isContractMember(String memberId,String custNumber);

	/**
	 * 
	 * @Title: sendFossOrderRemaidInfo
	 * @Description: 发给FOSS订单待受理订单信息
	 * @return void 返回类型
	 */
	public List<ResultDetal> sendFossOrderAcceptInfo();

	/**
	 * 
	 * @Title: getOrderAcceptInfoByDept
	 * @Description: 查询部门的订单待受理订单信息
	 * @return OrderAcceptInfo 订单待受理订单信息
	 */
	OrderAcceptInfo getOrderAcceptInfoByDept(String standardCode);
	/**
	 * <p>
	 * Description:创建试点城市的快递订单<br />
	 * </p>
	 * 
	 * @author kuang
	 * @param order
	 * @return AppointmentCarInfo
	 * @version 0.1 2013-7-27
	 */
	AppointmentCarInfo createPilotOrder(Order order);

	/**
	 * <p>
	 * Description:CRM创建订单调用方法<br />
	 * </p>
	 * 
	 * @author zhangbin
	 * @param order
	 * @return Order
	 * @version 0.1 2013-7-30
	 */
	Order createAllOrder(Order order);
	/**
	 * <p>
	 * Description:创建订单(接口)<br />
	 * </p>
	 * 
	 * @author 张斌
	 * @param order
	 * @return Order
	 * @version 0.1 2013-7-31
	 */
	Order createAllOrderInterface(Order order);
	/**
	 * <p>
	 * Description:撤销试点城市快递订单<br />
	 * </p>
	 * 
	 * @author 张斌
	 * @param order
	 * @return Order
	 * @version 0.1 2013-8-13
	 */
	Order cancelPolitOrder(Order order, String feedbackInfo, User user);
	/**
	 * <p>
	 * Description:撤销所有订单<br />
	 * </p>
	 * 
	 * @author 张斌
	 * @param order
	 * @return Order
	 * @version 0.1 2013-8-13
	 */
	Order cancelAllOrder(String id, String feedbackInfo, User user);
	/**
	 * 
	 * @description 延迟订单.
	 * @author 匡永琴
	 * @version 0.1 2013-9-3
	 * @param OrderView
	 * @return Order
	 */
	Order delayOrder(String id, User user, String feedbackInfo, Date delayOrderTime);

	/**
	 * @description 同步订单状态.
	 * @author 黄展明
	 * @version 0.1 2013-9-3
	 * @param order
	 * @return void
	 */
	void syncOrderStatus(String orderId, String orderStatus);

	/**
	 * 
	 * @Title: updateDelayToUnaccept
	 * @Description: 延迟状态变为待受理状态
	 * @return 
	 */
	public void updateDelayToUnaccept();

	
	/**
	 * @description 生成订单未分配待办事宜.
	 * @author 黄展明
	 * @version 0.1 2013-10-18
	 * @return void
	 */
	public void generateOrderUnassign();

	/**
	 * @description 生成订单未受理待办事宜.
	 * @author 黄展明
	 * @version 0.1 2013-10-18
	 * @return void
	 */
	public void generateOrderUnaccept();

	/**
	 * @description 生成订单已受理待办事宜.
	 * @author 黄展明
	 * @version 0.1 2013-10-18
	 * @return void
	 */
	public void generateOrderAccepted();

	/**
	 * @description 生成订单已退回待办事宜.
	 * @author 黄展明
	 * @version 0.1 2013-10-18
	 * @return void
	 */
	public void generateOrderGoback();

	/**
	 * @description 通过渠道单号修改运单号.
	 * @author 黄展明
	 * @version 0.1 2013-11-27
	 * @param channelNum
	 * @param waybillNum
	 * @throws CrmBusinessException
	 */
	public boolean updateWaybillNumByChannel(String channelNum,
			String waybillNum) throws CrmBusinessException;
}
