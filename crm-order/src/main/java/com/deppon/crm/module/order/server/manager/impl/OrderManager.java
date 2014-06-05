package com.deppon.crm.module.order.server.manager.impl;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.order.IOrderOperate;
import com.deppon.crm.module.client.order.domain.AppointmentCarInfo;
import com.deppon.crm.module.client.order.domain.ChannelOrderStatusInfo;
import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.client.order.domain.WaybillInfo;
import com.deppon.crm.module.client.sms.domain.SmsInformation;
import com.deppon.crm.module.common.server.manager.IMessageManager;
import com.deppon.crm.module.common.server.manager.IPilotCityManager;
import com.deppon.crm.module.common.server.manager.impl.AreaAddressManager;
import com.deppon.crm.module.common.server.manager.impl.LadingstationDepartmentManager;
import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.AreaSearchCondition;
import com.deppon.crm.module.common.shared.domain.BusDeptSearchCondition;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.LadingstationDepartment;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.common.shared.domain.Province;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.ShuttleAddress;
import com.deppon.crm.module.order.server.manager.IOrderManager;
import com.deppon.crm.module.order.server.manager.IOrderNumberRuleManager;
import com.deppon.crm.module.order.server.manager.IVehicleHistoryManager;
import com.deppon.crm.module.order.server.manager.OrderValidator;
import com.deppon.crm.module.order.server.service.IOrderService;
import com.deppon.crm.module.order.server.util.Constant;
import com.deppon.crm.module.order.server.util.OrderUtil;
import com.deppon.crm.module.order.server.util.PropertiesUtil;
import com.deppon.crm.module.order.shared.domain.BookVehicleInfo;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.order.shared.domain.OrderAcceptConfig;
import com.deppon.crm.module.order.shared.domain.OrderAcceptInfo;
import com.deppon.crm.module.order.shared.domain.OrderOperationLog;
import com.deppon.crm.module.order.shared.domain.OrderReminder;
import com.deppon.crm.module.order.shared.domain.OrderSearchCondition;
import com.deppon.crm.module.order.shared.exception.OrderException;
import com.deppon.crm.module.order.shared.exception.OrderExceptionType;
import com.deppon.crm.module.organization.server.service.IEmployeeService;
import com.deppon.crm.module.organization.server.service.impl.DepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.crm.CancelOrderResponse;
import com.deppon.foss.crm.ResultDetal;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.shared.util.string.StringUtil;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * 
 * <p>
 * 根据sonar规则修改代码
 * </p>
 * 
 * @title OrderManager.java
 * @package com.deppon.crm.module.order.server.manager.impl
 * @updateUser zouming
 * @updateDate 2012-01-22
 */
@Transactional
public class OrderManager implements IOrderManager {
	// logger
	Logger logger = LoggerFactory.getLogger(OrderManager.class);

	// 订单验证类
	private OrderValidator orderValidator;
	// 订单service
	private IOrderService orderService;
	// 客户manager
	private IMemberManager customerManager;
	// 部门manager
	public LadingstationDepartmentManager ladDeptManager;
	// 消息manager
	private IMessageManager messageManager;
	// departmentService
	private DepartmentService departmentService;
	// 职员Service
	private IEmployeeService employeeService;
	// 约车历史manager
	private IVehicleHistoryManager vehicleHistoryManager;
	// 省市区
	public AreaAddressManager areaAddressManager;

	private IMemberManager memberManager;
	
	private IOrderNumberRuleManager orderNumberRuleManager;
/*	修改人：张斌
	修改时间2013-7-30
	修改内容：无（新增）
	修改原因：注入接口spring*/
	//begin
	// 订单client
	private IOrderOperate orderOperate;
	
	/**
	 * @param orderOperate the orderOperate to set
	 */
	public void setOrderOperate(IOrderOperate orderOperate) {
		this.orderOperate = orderOperate;
	}
    //end

	/**
	 * <p>
	 * Description:试点城市<br />
	 * </p>
	 * 
	 * @author kuang
	 * @version 0.1 2013-7-29
	 */
	private IPilotCityManager pilotCityManager;

	/**
	 * @return pilotCityManager;
	 */
	public IPilotCityManager getPilotCityManager() {
		return pilotCityManager;
	}
	/**
	 * @param pilotCityManager
	 *            : set the property pilotCityManager.
	 */
	public void setPilotCityManager(IPilotCityManager pilotCityManager) {
		this.pilotCityManager = pilotCityManager;
	}

	/**
	 * @return vehicleHistoryManager;
	 */
	public IVehicleHistoryManager getVehicleHistoryManager() {
		return vehicleHistoryManager;
	}

	/**
	 * @param vehicleHistoryManager
	 *            : set the property vehicleHistoryManager.
	 */
	public void setVehicleHistoryManager(
			IVehicleHistoryManager vehicleHistoryManager) {
		this.vehicleHistoryManager = vehicleHistoryManager;
	}

	/**
	 * @return employeeService;
	 */
	public IEmployeeService getEmployeeService() {
		return employeeService;
	}

	/**
	 * @param employeeService
	 *            : set the property employeeService.
	 */
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * @return departmentService;
	 */
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	/**
	 * @param departmentService
	 *            : set the property departmentService.
	 */
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/**
	 * @return orderValidator;
	 */
	public OrderValidator getOrderValidator() {
		return orderValidator;
	}

	/**
	 * @param orderValidator
	 *            : set the property orderValidator.
	 */
	public void setOrderValidator(OrderValidator orderValidator) {
		this.orderValidator = orderValidator;
	}

	/**
	 * @return orderService;
	 */
	public IOrderService getOrderService() {
		return orderService;
	}

	/**
	 * @param orderService
	 *            : set the property orderService.
	 */
	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * @return customerManager;
	 */
	public IMemberManager getCustomerManager() {
		return customerManager;
	}

	/**
	 * @param customerManager
	 *            : set the property customerManager.
	 */
	public void setCustomerManager(IMemberManager customerManager) {
		this.customerManager = customerManager;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public LadingstationDepartmentManager getLadDeptManager() {
		return ladDeptManager;
	}

	public void setLadDeptManager(LadingstationDepartmentManager ladDeptManager) {
		this.ladDeptManager = ladDeptManager;
	}

	public AreaAddressManager getAreaAddressManager() {
		return areaAddressManager;
	}

	public void setAreaAddressManager(AreaAddressManager areaAddressManager) {
		this.areaAddressManager = areaAddressManager;
	}

	/**
	 * @return messageManager;
	 */
	public IMessageManager getMessageManager() {
		return messageManager;
	}

	/**
	 * @param messageManager
	 *            : set the property messageManager.
	 */
	public void setMessageManager(IMessageManager messageManager) {
		this.messageManager = messageManager;
	}

	
	public void setOrderNumberRuleManager(
			IOrderNumberRuleManager orderNumberRuleManager) {
		this.orderNumberRuleManager = orderNumberRuleManager;
	}

	/**
	 * 确定这个订单不是 <b>试点城市</b> 的 <b>快递</b> 订单，如果是则抛出异常
	 * 
	 * @param order
	 *            订单
	 * @param orderExceptionType
	 *            异常提示信息
	 * @throws OrderException
	 *             当order是试点城市快递订单时抛出此异常
	 */
	@SuppressWarnings("serial")
	@Transactional
	public void assertNotPilotPackageOrder(Order order,
			OrderExceptionType orderExceptionType) {
		if (pilotCityManager.checkIsPilotCityOrder(order.getContactCity(),
				order.getTransportMode())) {
			OrderException e = new OrderException(orderExceptionType);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}

	/**
	 * @作者：罗典
	 * @描述：根据订单号查询订单详情(接口用)
	 * @时间：2012-11-8
	 * @参数：orderNumer 渠道单号或订单号
	 * @返回值: Order 订单信息
	 * */
	@Override
	@Transactional
	public Order queryOrderByOrderNumber(String OrderNumber) {
		return orderService.queryOrderByOrderNumber(OrderNumber);
	}

	/**
	 * @作者：罗典
	 * @描述：根据渠道订单号查询订单信息
	 * @时间：2012-4-17
	 * @参数：channelNumber 渠道单号
	 * @返回值: Order 订单信息
	 * */
	@Transactional(readOnly = true)
	public Order queryOrderByChannelNumber(String channelNumber) {
		return orderService.queryOrderByChannelNumber(channelNumber);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-13
	 * @描述：根据条件查询订单信息
	 * @参数：map 查询条件信息
	 * @返回值：List<Order> 订单信息集合
	 * */
	@Transactional(readOnly = true)
	public List<Order> queryOrders(Map<String, Object> map) {
		validateQueryOrdersCondition(map);
		return orderService.queryOrders(map);
	}
	
	private void validateQueryOrdersCondition(Map<String, Object> map) {
		Date startDate = (Date) map.get("startDate");
		Date endDate = (Date) map.get("endDate");
		if (startDate == null && endDate == null) {
			return;
		}
		if ((startDate == null && endDate != null)
				|| (endDate == null && startDate != null)) {
			throw OrderUtil
					.buildException(OrderExceptionType.QUERY_ORDERS_DATE_INVALID);
		}
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.YEAR, 1);
		c.add(Calendar.DATE, 1);
		if (c.getTime().before(endDate)) {
			throw OrderUtil
					.buildException(OrderExceptionType.QUERY_ORDERS_DATE_INVALID);
		}
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-13
	 * @描述：根据条件查询订单信息总数
	 * @参数：map 查询条件信息
	 * @返回值：int 总数
	 * */
	@Transactional(readOnly = true)
	public int countOrders(Map<String, Object> map) {
		return orderService.countOrders(map);
	}

	/**
	 * 
	 * <p>
	 * Description:呼叫中心订单初始化<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:20:42
	 * @param shipperId
	 * @return
	 * @update 2013-1-22下午4:20:42
	 */
	@Transactional(readOnly = true)
	public BussinessDept callCenterOrderInit(String shipperId) {
		// 部门
		BussinessDept dept = null;
		// 出发网点
		String startSationId = orderService.getStartStationByHistory(shipperId);
		// 如果出发网点不为空，则根据出发网点查询相应部门
		if (startSationId != null && !"".equals(startSationId)) {
			// 查询部门
			dept = ladDeptManager.getBusDeptByDeptId(startSationId);
		}
		return dept;
	}

	/**
	 * 
	 * <p>
	 * Description:创建订单初始化<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:21:03
	 * @param deptId
	 * @return
	 * @update 2013-1-22下午4:21:03
	 */
	@Transactional(readOnly = true)
	public BussinessDept createOrderInit(String deptId) {
		// 根据部门ID查询部门
		BussinessDept dept = ladDeptManager.getBusDeptByDeptId(deptId);
		// 如果部门不为空且为出发部门，返回查询出的部门
		if (dept != null && dept.getIfLeave()) {
			return dept;
		}
		// 如果部门为空或者不为出发部门，返回空
		return null;
	}

	/**
	 * 
	 * <p>
	 * Description:通过ERP ID搜索车队部门<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:21:26
	 * @param deptId
	 * @return
	 * @update 2013-1-22下午4:21:26
	 */
	@Transactional(readOnly = true)
	public BussinessDept searchBusDeptByErpId(String deptId) {
		return ladDeptManager.getBusDeptByErpId(deptId);
	}

	@Transactional(readOnly = true)
	public BussinessDept searchBusDeptByStandardCode(String standardCode) {
		return ladDeptManager.getBusDeptByStandardCode(standardCode);
	}

	/**
	 * 
	 * <p>
	 * Description:创建订单<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:21:56
	 * @param order
	 * @return
	 * @update 2013-1-22下午4:21:56
	 */
	@Transactional
	@Override
	public Order createOrder(Order order) {
		//去除订单中可能存在的特殊字符
		order = replaceSpecialChar(order);
		// 0、渠道订单重复提交
		String channelNumber = order.getChannelNumber();
		//当运输方式选择为“经济快递”，保价金额大于20000时，点击提交，提示“快递保价金额不能超过20000”
		orderValidator.sureAcountPackage(order.getTransportMode(),order.getInsuredAmount(),order.getReviceMoneyAmount());
		// 如果渠道号不为空，则获取渠道订单
		if (channelNumber != null && !"".equals(channelNumber)) {
			Order o = orderService.queryOrderByChannelNumber(channelNumber);
			// 如果渠道订单不为空，则抛出异常
			if (o != null) {
				// 创建异常
				OrderException e = new OrderException(
						OrderExceptionType.CREATEORDER_DOUBLECHANNELNUMBER);
				// 抛出异常
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {

							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;
				};
			}
		}

		// 1、校验
		if (this.orderValidator.isNeedCheck(order.getResource())) {
			// 校验订单信息是否完整
			orderValidator.validateOrderInfoComplete(order,pilotCityManager.checkIsPilotCityOrder(
					order.getContactCity(), order.getTransportMode()));
		}

		// 2、订单号处理
		String idSeq = orderService.getSequnce();
		order.setId(idSeq);
		// 订单号
		String orderNumber = orderNumberRuleManager.produceOrderNumber(idSeq,
				order.getResource(), order.getTransportMode());
		// 设置订单号
		order.setOrderNumber(orderNumber);
		// 如果订单来自官网或者呼叫中心，则订单号跟渠道单号一样
		// 增加呼叫中心合肥 2013-11-12
		if (Constant.ORDER_SOURCE_ONLINE.equals(order.getResource())
				|| OrderUtil.isCallCenter(order.getResource())) {
			// 验证渠道单号不能为空
			if (orderValidator.isChannelNumberNull(order.getChannelNumber())) {
				order.setOrderNumber(order.getChannelNumber());
			}
			// 呼叫中心来源的订单，需要重新转换他的制单员（FCREATEUSERID）
			// 增加呼叫中心合肥 2013-11-12
			if (OrderUtil.isCallCenter(order.getResource())) {
				String empCode = order.getCreateUser();
				// 职员
				Employee emp = new Employee();
				// 职员编码设置
				emp.setEmpCode(empCode);
				List<Employee> eList = employeeService.queryAll(emp);
				// 如果职员列表至少存在一个，设置第一个为订单创建人
				if (null != eList && eList.size() > 0) {
					// 设置第一个为订单创建人
					order.setCreateUser(eList.get(0).getId());
				}
			}
		} else if (!orderValidator.isWebChannelOrder(order) 
				|| Constant.ORDER_SOURCE_WEIXIN.equalsIgnoreCase(order.getResource())) {
			// 非网络渠道，渠道订单号与订单一致
			order.setChannelNumber(orderNumber);
		}
		//删掉渠道已经做了的重复逻辑
		//修改人张斌
		// 其他 淘宝商城   友商网 默认为送货上楼
        if (Constant.ORDER_SOURCE_YOUSHANG.equals(order.getResource())
				|| Constant.ORDER_SOURCE_SHANGCHENG.equals(order.getResource())) {
			order.setDeliveryMode(Constant.ORDER_DELIVERMODE_PICKUPSTAIRS);
		} 

		// 3、说明：通过电子地图查询的网点返回的id是一个uuid， 需要判断前台传到后台的值是不是一个非数字的id，
		// 如果不是就要通过这个uuid查询到网点的id
		// 若订单中始发网点的id不为数字，则为erpid，需要转换成id
		String reg = "[0-9]+";

		// 验证始发网点
		if (orderValidator.isObjectNotNull(order.getStartStation())
				&& !order.getStartStation().matches(reg)) {
			// 营业部
			BussinessDept bd = ladDeptManager.getBusDeptByErpId(order
					.getStartStation());
			// 设置始发网点
			order.setStartStation(bd.getId());
		}
		// 若订单中到达网点的id不为数字，则为erpid，需要转换成id
		if (orderValidator.isObjectNotNull(order.getReceivingToPoint())
				&& !order.getReceivingToPoint().matches(reg)) {
			// 营业部
			BussinessDept bd = ladDeptManager.getBusDeptByErpId(order
					.getReceivingToPoint());
			// 设置到达网点
			if (null != bd) {
				order.setReceivingToPoint(bd.getId());
			}else{
				order.setReceivingToPoint(null);
			}
		}

		// 4、渠道订单需要分配始发网点和受理部门，系统取最近一次交易记录中始发网点
		if (orderValidator.isWebChannelOrder(order)
				&& (null == order.getStartStation() || "".equals(order
						.getStartStation()))) {
			// 始发网点
			String startStation = orderService.getStartStationByHistory(
					order.getShipperId(), order.getContactProvince(),
					order.getContactCity(), order.getContactArea(),
					order.getContactAddress());
			// 验证始发网点
			if (null != startStation && !"".equals(startStation)) {
				order.setStartStation(startStation);
			}
		}

		// 5、维护受理部门和状态
		/*		修改人：kuang
		修改时间：2013-7-30
		修改内容：无
		修改原因：如果是试点城市的快递，则验证受理部门和维护
		状态*/
		if(!pilotCityManager.checkIsPilotCityOrder(
				order.getContactCity(), order.getTransportMode())){	
		if (null != order.getStartStation()
				// 5、维护受理部门和状态
				/*		修改人：kuang
				修改时间：2013-7-30
				修改内容：&& !"".equals(order.getStartStation())) {
				修改原因：如果是非试点城市快递，则状态直接是待分配
				状态*/
				//begin
				&& !"".equals(order.getStartStation())&&!Constant.ORDER_TRANSTYPE_AGENT_PACKAGE.equals(order.getTransportMode())) {
			    //end
			// 受理部门
			Department acceptDept = this.getAcceptDept(order);
			// 设置受理部门ID
			order.setAcceptDept(acceptDept.getId());
			// 设置受理部门名字
			order.setAcceptDeptName(acceptDept.getDeptName());
			// 如果是营业部的单状态为已受理
			if (Constant.ORDER_SOURCE_BUSINESS_DEPT.equals(order.getResource())) {
				// 始发网点不为空则设置为受理
				order.setOrderStatus(Constant.ORDER_STATUS_ACCEPT);
			} else {
				// 设置为待受理
				order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ACCEPT);
			}
		} else {
			// 否则状态设置为待分配
			order.setOrderStatus(Constant.ORDER_STATUS_WAIT_ALLOT);
		}
	}

		// 6、 是呼叫中心订单创建时短信提示
		if (orderValidator.isCallCenterOrder(order) && order.getIsSendmessage()) {
			// 消息内容
			String smsContent = MessageFormat
					.format(Constant.ORDER_MESSAGE_CREATE,
							new Object[] { order.getContactName(),
									order.getReceiverCustCity(),
									order.getOrderNumber() });
			// 验证合同电话
			// 增加呼叫中心合肥 2013-11-12
			if (null != order.getContactMobile()
					&& !"".equals(order.getContactMobile())
					&& !OrderUtil.isCallCenter(order.getResource())) {
				SmsInformation info = new SmsInformation();
				User user = (User) UserContext.getCurrentUser();
				String deptStandardCode = user.getEmpCode().getDeptId()
						.getStandardCode();
				String empCode = user.getEmpCode().getEmpCode();
				// 设置电话
				info.setMobile(order.getContactMobile());
				// 设置消息i内容
				info.setMsgContent(smsContent);
				// 调用发送信息函数
				info.setSendDept(deptStandardCode);
				info.setSender(empCode);
				info.setMsgType(com.deppon.crm.module.client.common.util.Constant.SMS_ORDER_CODE);
				orderService.sendMessage(info);
			}
		}
		// 营业部订单维护下单人
		if (Constant.ORDER_SOURCE_BUSINESS_DEPT.equalsIgnoreCase(order
				.getResource())) {
			// 设置创建人
			order.setCreateUser(OrderUtil.getLoginEmpId());
			// 设置订单人
			order.setOrderPerson(OrderUtil.getLoginEmpNoName());
		}
		// 创建订单时的转到待受理的时间默认为下单时间
		order.setToWaitAcceptTime(order.getOrderTime());
		// 订单来源外部渠道更新运单为空
		String bindWaybillNum = null;
		if (OrderValidator.isWebChannelOrder(order)
				&& null != order.getWaybillNumber()
				&& !"".equals(order.getWaybillNumber())) {
			bindWaybillNum = order.getWaybillNumber();
			order.setWaybillNumber(null);
		}
		order = orderService.saveOrder(order);
		// 订单和运单重新绑定
		if (null != bindWaybillNum && !"".equals(bindWaybillNum)) {
			try {
				updateWaybillNumByChannel(order.getChannelNumber(),
						bindWaybillNum);
			} catch (CrmBusinessException e) {
			}
		}

		return order;
	}

	/**
	 * @description 下单约车操作只是针对业务部门订单， 不针对渠道订单
	 * @see com.deppon.crm.module.order.server.manager.IOrderManager#bookVehicleAndCreateOrder(com.deppon.crm.module.order.shared.domain.Order,
	 *      com.deppon.crm.module.order.shared.domain.BookVehicleInfo)
	 */
	@Transactional
	@Override
	public Order bookVehicleAndCreateOrder(Order order, BookVehicleInfo info,
			User user, String vehicleTeamName) {
		//去除订单中可能存在的特殊字符
		order = replaceSpecialChar(order);
		// 1、校验订单数据完整性
		orderValidator.validateOrderInfoComplete(order,false);//能够提交并约车的不会是快递订单
		// 2、订单号处理
		String idSeq = orderService.getSequnce();
		order.setId(idSeq);
		// 订单号
		String orderNumber = orderNumberRuleManager.produceOrderNumber(idSeq,
				order.getResource(),order.getTransportMode());
		// 设置订单号
		order.setOrderNumber(orderNumber);
		// 3、说明：通过电子地图查询的网点返回的id是一个uuid， 需要判断前台传到后台的值是不是一个非数字的id，
		// 如果不是就要通过这个uuid查询到网点的id
		// 若订单中始发网点的id不为数字，则为erpid，需要转换成id
		if (orderValidator.isObjectNotNull(order.getStartStation())
				&& !order.getStartStation().matches("[0-9]+")) {
			// 营业部
			BussinessDept bd = ladDeptManager.getBusDeptByErpId(order
					.getStartStation());
			// 设置始发网点
			order.setStartStation(bd.getId());
		}
		// 4、若订单中到达网点的id不为数字，则为erpid，需要转换成id
		if (orderValidator.isObjectNotNull(order.getReceivingToPoint())
				&& !order.getReceivingToPoint().matches("[0-9]+")) {
			// 营业部
			BussinessDept bd = ladDeptManager.getBusDeptByErpId(order
					.getReceivingToPoint());
			// 设置到达网点
			order.setReceivingToPoint(bd.getId());
		}
		// 5、维护受理部门和状态
		if (null != order.getStartStation()
				&& !"".equals(order.getStartStation())) {
			Department acceptDept = this.getAcceptDept(order);
			// 设置受理部门
			order.setAcceptDept(acceptDept.getId());
			// 设置受理部门名字
			order.setAcceptDeptName(acceptDept.getDeptName());
			if (Constant.ORDER_SOURCE_BUSINESS_DEPT.equals(order.getResource())) {
				// 设置订单状态 已受理
				order.setOrderStatus(Constant.ORDER_STATUS_ACCEPT);
			}
		}
		// 6、营业部订单维护下单人
		if (Constant.ORDER_SOURCE_BUSINESS_DEPT.equalsIgnoreCase(order
				.getResource())) {
			// 设置创建人
			order.setCreateUser(OrderUtil.getLoginEmpId());
			// 设置下单人
			order.setOrderPerson(OrderUtil.getLoginEmpNoName());
		}
		// 设置下单时间
		order.setCreateDate(new Date());
		// 7、创建订单
		Order ord = orderService.saveOrder(order);
		// 8、生成最近一次约车记录
		// VehicleHistory history = OrderUtil.produceVehicleHistory(order, info,
		// user, vehicleTeamName);
		// vehicleHistoryManager.adjustVehicleHistory(history);
		// 8、约车
		Map map = bookVehicle(order, info, user);
		return ord;
	}

	/**
	 * 
	 * <p>
	 * Description:根据订单的始发网点得到受理部门，如未取到值则受理部门与始发网点一致<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2012-3-29
	 * @param order
	 * @return BussinessDept
	 */
	@Transactional(readOnly = true)
	private Department getAcceptDept(Order order) {
		// 订单来源
		String orderSource = orderValidator.isCallCenterOrder(order) ? Constant.ORDER_SOURCE_CALLCENTER
				: Constant.ORDER_SOURCE_ONLINE;
		// 是否送货
		Boolean isReceiveGoods = order.getIsReceiveGoods() == null ? false
				: order.getIsReceiveGoods();
		// 到达部门列表
		List<LadingstationDepartment> acceptDeptList = ladDeptManager
				.getAcceptDeptByConfigurator(order.getStartStation(),
						isReceiveGoods, orderSource);
		// 到达部门
		Department acceptDept = null;

		// 到达部门列表不为空，且列表里面第一个acceptDeptN有值，给到达部门赋值，否则新建一个到达部门对象
		if (null != acceptDeptList && acceptDeptList.size() > 0
				&& null != acceptDeptList.get(0).getAcceptDeptN()) {
			// 受理部门
			acceptDept = acceptDeptList.get(0).getAcceptDeptN();
		} else {
			acceptDept = new Department();
			// 给受理部门设置Id
			acceptDept.setId(order.getStartStation());
			// 给受理部门设置名字
			acceptDept.setDeptName(order.getStartStationName());
		}
		// 返回受理部门
		return acceptDept;
	}

	/**
	 * 
	 * <p>
	 * Description:通过名字获取客户信息<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:22:39
	 * @param memberName
	 * @return
	 * @update 2013-1-22下午4:22:39
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Member> searchMemberByName(String memberName) {
		return customerManager.searchMemberByName(memberName);
	}

	/**
	 * 
	 * <p>
	 * Description:通过电话获取客户信息<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:23:04
	 * @param phone
	 * @return
	 * @update 2013-1-22下午4:23:04
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Member> searchMemberByPhone(String phone) {
		return customerManager.searchMemberByTel(phone);
	}

	/**
	 * 
	 * <p>
	 * Description:通过移动电话获取客户信息<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:23:27
	 * @param mobile
	 * @return
	 * @update 2013-1-22下午4:23:27
	 */
	@Transactional(readOnly = true)
	@Override
	public Member getMenberByMobile(String mobile) {
		return customerManager.getMemberByPhone(mobile);

	}

	/**
	 * 
	 * <p>
	 * Description:更新订单信息<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:23:57
	 * @param vorder
	 * @param user
	 * @return
	 * @update 2013-1-22下午4:23:57
	 */
	@Transactional
	@Override
	public Order updateOrder(Order vorder, User user) {
		//去除订单中可能存在的特殊字符
		vorder = replaceSpecialChar(vorder);
		Order order = orderService.getOrderById(vorder.getId());
		//当运输方式选择为“经济快递”，保价金额大于20000时，点击提交，提示“快递保价金额不能超过20000”
		orderValidator.sureAcountPackage(vorder.getTransportMode(),vorder.getInsuredAmount(),vorder.getReviceMoneyAmount());
		/*
		 * 场景：零担改快递报错
		 * 		快递改零担报错
		 * 		非试点快递改为试点快递，同步到FOSS并返回
		 * 		试点快递改为试点快递，同步到FOSS并返回
		 * 		零担改为非试点快递，报错提示
		 * 		试点快递改为非试点快递，报错提示
		 */
		//begin
		/*
		 * 微信订单可以实现零担快递互转
		 */
		if (!OrderUtil.isModifyChannel(order.getResource())) {
			if (isPackageOrder(vorder) && !isPackageOrder(order)) {
				throw OrderUtil
						.buildException(OrderExceptionType.PILOTCITY_PACKAGE_CHANGE_NOT);
			}
			if (!isPackageOrder(vorder) && isPackageOrder(order)) {
				throw OrderUtil
						.buildException(OrderExceptionType.PILOTCITY_PACKAGE_CHANGE_NOT2);
			}
		}
	
		//end
		// 订单更改信息
		String content = OrderUtil.produceModifyLog(order, vorder,
				ladDeptManager);
		// 产生操作日志
		OrderOperationLog log = OrderUtil.generateOrderOperationLog(order,
				Constant.ORDER_OPERATION_UPDATE, content, user);
		// 日志集合
		Set<OrderOperationLog> logs = new HashSet<OrderOperationLog>();
		logs.add(log);
		// 订单增加操作记录
		vorder.setOperationLogs(logs);
		
		if(isPackageOrder(vorder)){
			if(isPilotCity(vorder)){
				//验证是否需要检查
				if (this.orderValidator.isNeedCheck(order.getResource())) {
					// 校验数据完整性
					orderValidator.validateOrderInfoComplete(vorder,true);
				}
				changeToPilotPackageOrder(vorder);
				return vorder;
			}
		/*	else{
				if(isPilotCity(order)){
					throw OrderUtil.buildException(OrderExceptionType.PILOTCITY_CHANGE_NOT);
				}
			}*/
		}
		
		// 验证订单是否可以修改
		if (orderValidator.isOrderUpdateable(order,user)) {
			// 验证是否需要检查
			if (this.orderValidator.isNeedCheck(order.getResource())) {
				// 校验数据完整性
				orderValidator.validateOrderInfoComplete(vorder,false);
			}
			// 验证订单渠道
			if (orderValidator.isOrderChannelModify(user, order)) {
				// 判断订单接货信息是否修改
				if (orderValidator.isReciveGoodsInfoChange(vorder, order)  || (isPackageRetrunOther(vorder, order))) {

					// QQ速递修改
					/**
					 * if (Constant.ORDER_SOURCE_ONLINE
					 * .equals(order.getResource())) {
					 * orderValidator.checkInsuredAmountUpdateable(
					 * vorder.getInsuredAmount(), order.getInsuredAmount()); }
					 */

					// 判断订单是否有始发站
					if (orderValidator.isOrderHaveNoStateStation(vorder)) {
						// 设置状态待分配
						vorder.setOrderStatus(Constant.ORDER_STATUS_WAIT_ALLOT);
					} else {
						// 设置状态为待受理
						vorder.setOrderStatus(Constant.ORDER_STATUS_WAIT_ACCEPT);
						// 修改订单时的转到待受理的时间为当前时间
						vorder.setToWaitAcceptTime(new Date());
					}
				}
				// 更新订单
				vorder = orderService.updateOrder(vorder);
				// 更新完后根据状态同步接口系统数据
				syncOrderStatus(vorder.getId(), vorder.getOrderStatus());
			}
		}
		return vorder;
	}
	@Transactional
	private boolean isPilotCity(Order order) {
		String contactCity = order.getContactCity();
		return contactCity != null && pilotCityManager.checkPilotCityByName(contactCity);
	}
	@Transactional
	private boolean isPackageOrder(Order order) {
		String transportMode = order.getTransportMode();
		return transportMode != null && Constant.ORDER_TRANSTYPE_AGENT_PACKAGE.equals(transportMode);
	}
	
	@Transactional
	private boolean isPackageRetrunOther(Order order,Order vorder){
		return (isPackageOrder(order) && !isPackageOrder(vorder) ) || (!isPackageOrder(order) && isPackageOrder(vorder) ) ;
	}

	/**
	 * 
	 * @description 订单打回.
	 * @author 安小虎
	 * @version 0.1 2012-3-12
	 * @param 订单ID
	 * @param 反馈信息
	 * @date 2012-3-12
	 * @return 订单视图
	 * @update 2012-3-12 上午11:31:12
	 * @see com.deppon.crm.module.order.server.manager.IOrderManager#returnOrder(com.deppon.crm.module.order.shared.domain.Order,
	 *      java.lang.String)
	 */
	@SuppressWarnings("serial")
	@Transactional
	@Override
	public Order returnOrder(String id, String feedbackInfo, User user) {
		// 订单
		Order order = orderService.getOrderById(id);
		// 订单来源
		String source = order.getResource();
		// 如果订单来源不为空且来源与营业部，抛出异常
		if (source != null
				&& !"".equals(source.trim())
				&& Constant.ORDER_SOURCE_BUSINESS_DEPT.equalsIgnoreCase(source
						.trim())) {
			// 营业部不能打回订单异常
			OrderException e = new OrderException(
					OrderExceptionType.ORDER_BUSINESS_DEPT_NOT_RETURNORDER);
			// 抛出异常
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		// 1、验证订单状态是否为待受理
		if (orderValidator.isReturnOrderStatus(order)) {
			// 订单ID
			String orderId = id;
			// 打回前始发网点
			String startStationOld = order.getStartStation();// 打回前始发网点

			// 出发网点部门
			BussinessDept startStationDept = ladDeptManager
					.getBusDeptById(startStationOld);
			// 出发网点旧名字
			String startStationOldName = Constant.ORDER_MESSAGE_NULL;
			// 如果出发网点部门不为空
			if (null != startStationDept) {
				// 获取出发网点旧名字
				startStationOldName = startStationDept.getDeptName();
			}
			// 订单打回前状态
			String orderStatusOld = order.getOrderStatus();
			// 打回后状态为未分配
			String orderStatusNew = Constant.ORDER_STATUS_WAIT_ALLOT;
			// 2、验证打回原因是否填写
			if (orderValidator.isFeedbackInfoNotNull(feedbackInfo)) {
				// 3、打回订单成功，清空订单的始发网点，订单状态更新为未分配
				Order o = new Order();
				// 订单ID
				o.setId(orderId);
				// 打回后订单状态
				o.setOrderStatus(orderStatusNew);
				o.setFeedbackInfo(feedbackInfo);

				// 4、记录打回的操作内容：打回原因、打回人、原始发网点、打回时间
				Set<OrderOperationLog> logs = new HashSet<OrderOperationLog>();

				// 打回的主体内容
				String content = MessageFormat
						.format(Constant.ORDER_OPERATE_LOG,
								new Object[] {
										Constant.ORDER_STATUS,
										OrderUtil
												.getCHNOrderStatusByENStatus(orderStatusOld),
										OrderUtil
												.getCHNOrderStatusByENStatus(orderStatusNew) });
				content = content
						+ MessageFormat.format(Constant.ORDER_OPERATE_LOG,
								new Object[] { Constant.ORDER_STARTSTATION,
										startStationOldName,
										Constant.ORDER_MESSAGE_NULL });
				content = content + Constant.ORDER_MESSAGE_REASON
						+ feedbackInfo;
				// 订单操作日志
				OrderOperationLog ool = OrderUtil.generateOrderOperationLog(
						order, Constant.ORDER_OPERATION_RETURNBACK, content,
						user);
				logs.add(ool);
				// 设置操作日志集合
				o.setOperationLogs(logs);
				// 更新订单
				o = this.orderService.updateOrder(o);
				// 返回订单
				return o;
			}
		}
		return null;
	}

	/**
	 * 
	 * @description 订单撤消.
	 * @author 安小虎
	 * @version 0.1 2012-3-14
	 * @param 订单ID
	 * @param 反馈信息
	 * @date 2012-3-14
	 * @return 订单视图
	 * @update 2012-3-14 上午11:17:06
	 * @see com.deppon.crm.module.order.server.manager.IOrderManager#cancelOrder(com.deppon.crm.module.order.shared.domain.Order,
	 *      java.lang.String)
	 */
	@Transactional
	@Override
	public Order cancelOrder(Order order, String feedbackInfo, User user) {
		// 订单ID
		String orderId = order.getId();
		// 订单来源
		String orderSource = order.getResource();// 订单来源
		// 订单撤销前状态
		String orderStatus_old = order.getOrderStatus();// 订单撤消前状态
		// 订单撤销后状态
		String orderStatus_new = Constant.ORDER_STATUS_CANCEL;// 已撤消

		// 1、验证订单状态是否为未分配、待受理、已受理、已退回
		// 2、反馈信息是否填写
		// 如果是渠道订单，需要判断传过来的User是否符合约定的渠道单用户的类型
		// 若果不是渠道订单，则需要验证订单的来源
		// 4、撤销订单成功，向订单受理部门反馈撤销信息，订单状态改为已撤销；
		if (orderValidator.isCancelOrderStatus(order,user)
				&& orderValidator.isFeedbackInfoNotNull(feedbackInfo)
				&& (user.getId().equals("DP0000") || orderValidator
						.isOrderFromCrm(orderSource))) {
			// 创建订单对象
			Order o = new Order();
			// 设置订单ID
			o.setId(orderId);
			// 设置订单状态
			o.setOrderStatus(orderStatus_new);// 已撤消
			o.setFeedbackInfo(feedbackInfo);

			// 5、记录操作记录
			Set<OrderOperationLog> logs = new HashSet<OrderOperationLog>();
			// 日志主体内容
			String content = MessageFormat
					.format(Constant.ORDER_OPERATE_LOG,
							new Object[] {
									Constant.ORDER_STATUS,
									OrderUtil
											.getCHNOrderStatusByENStatus(orderStatus_old),
									OrderUtil
											.getCHNOrderStatusByENStatus(orderStatus_new) });
			content = content + feedbackInfo;

			// 造作日志
			OrderOperationLog ool = OrderUtil.generateOrderOperationLog(order,
					Constant.ORDER_OPERATION_CANCEL, content, user);
			logs.add(ool);
			// 设置操作日志
			o.setOperationLogs(logs);
			// 更新订单
			o = this.orderService.updateOrder(o);
			// 更新完后根据状态同步接口系统数据
			syncOrderStatus(orderId, orderStatus_new);
			// 返回订单
			return o;
		}
		return null;
	}

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
	@Transactional
	@Override
	public Order cancelPolitOrder(Order order, String feedbackInfo, User user) {

		// 订单ID
		String orderId = order.getId();
		// 订单来源
		String orderSource = order.getResource();// 订单来源
		// 订单撤销前状态
		String orderStatus_old = order.getOrderStatus();// 订单撤消前状态
		// 订单撤销后状态
		String orderStatus_new = Constant.ORDER_STATUS_CANCEL;// 已撤消
		// 1、验证订单状态是否为已约车
		// 2、反馈信息是否填写
		// 如果是渠道订单，需要判断传过来的User是否符合约定的渠道单用户的类型
		// 若果不是渠道订单，则需要验证订单的来源
		// 4、撤销订单成功，向订单受理部门反馈撤销信息，订单状态改为已撤销；
		if (orderValidator.isCancelPolitOrderStatus(order,user)
				&& orderValidator.isFeedbackInfoNotNull(feedbackInfo)
				&& (user.getId().equals("DP0000") || orderValidator
						.isOrderFromCrm(orderSource))) {
			CancelOrderResponse response = null;
			try {
				response = orderOperate.cancelOrder(order.getOrderNumber());
			} catch (CrmBusinessException e) {
				// 抛出异常
				throw new GeneralException(e.getMessage(),
						e.getErrorCode(), e, new Object[] {}) {

							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;
				};
			}
			if(response.getResult()==1){
				
			}else{
				OrderException e = new OrderException(
						OrderExceptionType.PILOTCITY_FOSS_NOT);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {

							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;
				};
			}
			// 创建订单对象
			Order o = new Order();
			// 设置订单ID
			o.setId(orderId);
			// 设置订单状态
			o.setOrderStatus(orderStatus_new);// 已撤消
			o.setFeedbackInfo(feedbackInfo);

			// 5、记录操作记录
			Set<OrderOperationLog> logs = new HashSet<OrderOperationLog>();
			// 日志主体内容
			String content = MessageFormat
					.format(Constant.ORDER_OPERATE_LOG,
							new Object[] {
									Constant.ORDER_STATUS,
									OrderUtil
											.getCHNOrderStatusByENStatus(orderStatus_old),
									OrderUtil
											.getCHNOrderStatusByENStatus(orderStatus_new) });
			content = content + feedbackInfo;

			// 造作日志
			OrderOperationLog ool = OrderUtil.generateOrderOperationLog(order,
					Constant.ORDER_OPERATION_CANCEL, content, user);
			logs.add(ool);
			// 设置操作日志
			o.setOperationLogs(logs);
			// 更新订单
			o = this.orderService.updateOrder(o);
			// 返回订单
			return o;
		}
		return null;
	}
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
	@Transactional
	@Override
	public Order cancelAllOrder(String id, String feedbackInfo, User user) {
		// 订单对象
		Order order = orderService.getOrderById(id);
		if (pilotCityManager.checkIsPilotCityOrder(
				order.getContactCity(), order.getTransportMode())){
			return cancelPolitOrder(order,feedbackInfo,user);
		}else{
			return cancelOrder(order,feedbackInfo,user);
		}
	}
	
	/**
	 * 
	 * @description 订单催办.
	 * @author 安小虎
	 * @version 0.1 2012-3-15
	 * @param 订单ID
	 * @date 2012-3-15
	 * @return 订单视图
	 * @update 2012-3-15 上午10:33:54
	 * @see com.deppon.crm.module.order.server.manager.IOrderManager#urgeOrder(com
	 *      .deppon.crm.module.order.shared.domain.Order)
	 */
	@Transactional
	@Override
	public Order urgeOrder(String id, User user) {
		// 订单ID
		String orderId = id;
		// 根据订单ID获取订单信息
		Order order = orderService.getOrderById(id);
		// 1、催单有时间和频率限制，下单时间内30分钟内，禁止催单；针对同一订单，每15分钟内仅允许催单操作一次；每催一次单，催单次数+1；
		// 按催单时间倒序查询该部门该订单的催单记录集合
		OrderOperationLog oolog = OrderUtil.generateOrderOperationLog(order,
				Constant.ORDER_OPERATION_URGE, Constant.ORDER_MESSAGE_URGE,
				user);
		// 获取订单操作日志列表
		List<OrderOperationLog> list = orderService.getListByCondtion(oolog);
		// 验证订单
		if (orderValidator.urgeTimeAndRateLimit(order, list)) {
			// 2、订单状态为为分配、已撤销、已拒绝、揽货失败、正常签收、异常签收、已开单时不允许催单；
			if (orderValidator.isUrgeOrderStatus(order)) {
				// 3、记录催单操作记录，含：订单号、催单人、催单时间、当前催单次数、催单部门；
				Order o = new Order();
				o.setId(orderId);

				// 操作日志集合
				Set<OrderOperationLog> logs = new HashSet<OrderOperationLog>();
				// 操作日志
				OrderOperationLog ool = OrderUtil.generateOrderOperationLog(
						order, Constant.ORDER_OPERATION_URGE,
						Constant.ORDER_MESSAGE_URGE, user);
				logs.add(ool);
				// 设置订单操作日志
				o.setOperationLogs(logs);

				// 4、催单次数+1
				o.setHastenCount(order.getHastenCount() == null ? 1 : order
						.getHastenCount() + 1);
				// 设置最后催单时间
				o.setLastHastenTime(new Date());
				// 更新订单
				o = this.orderService.updateOrder(o);

				// 5、 生成待办
				Message msg = new Message();
				// 消息
				msg.setTaskcount(1);
				// 部门ID
				Integer deptId = null;
				// 接货部门
				String accptDept = o.getAcceptDept();
				// 如果接货部门不为空
				if (accptDept != null && !"".equals(accptDept)) {
					// 获取其部门ID
					deptId = Integer.parseInt(accptDept);
				}

				// 消息设置部门ID
				msg.setDeptId(deptId);
				// 消息设置任务类别
				msg.setTasktype(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_ORDER_MESSAGE);
				msg.setIshaveinfo(Constant.ORDER_NUMBER + o.getOrderNumber()
						+ Constant.ORDER_URGE_COUNT + o.getHastenCount());
				// 添加消息
				messageManager.addMessage(msg);
				// 返回订单
				return o;
			}
		}
		return null;
	}

	/**
	 * 
	 * @description 揽货失败订单.
	 * @author 安小虎
	 * @version 0.1 2012-3-21
	 * @param 订单ID
	 * @date 2012-3-21
	 * @return 订单视图
	 * @update 2012-3-21 下午3:42:29
	 * 
	 * @see com.deppon.crm.module.order.server.manager.IOrderManager#accpetFail(java
	 *      .lang.String)
	 */
	@Transactional
	@Override
	public Map<String, Object> accpetFail(String id, String feedbackInfo,
			User user) {
		// 订单
		Order order = orderService.getOrderById(id);
		// 订单ID
		String orderId = id;
		// 订单揽货前状态
		String orderStatus_old = order.getOrderStatus();// 订单揽货前状态
		// 揽货失败状态
		String orderStatus_new = Constant.ORDER_SATUTS_FAILGOT;// 揽货失败
		// 1、验证订单状态是否为：已受理、已退回、接货中

		if (orderValidator.isAccpetFailOrderStatus(order)) {
			// 2、反馈信息是否填写
			if (orderValidator.isFeedbackInfoNotNull(feedbackInfo)) {

				// 4、揽货失败成功，修改订单状态
				Order o = new Order();
				o.setId(orderId);
				o.setOrderStatus(orderStatus_new);
				o.setFeedbackInfo(feedbackInfo);
				// o.setOrderAcceptTime(new Date());
				// 5、记录操作记录
				Set<OrderOperationLog> logs = new HashSet<OrderOperationLog>();
				String content = MessageFormat
						.format(Constant.ORDER_OPERATE_LOG,
								new Object[] {
										Constant.ORDER_STATUS,
										OrderUtil
												.getCHNOrderStatusByENStatus(orderStatus_old),
										OrderUtil
												.getCHNOrderStatusByENStatus(orderStatus_new) });
				content = content + feedbackInfo;
				OrderOperationLog ool = OrderUtil.generateOrderOperationLog(
						order, Constant.ORDER_OPERATION_RECIVEGOODSFAIL,
						content, user);
				logs.add(ool);
				o.setOperationLogs(logs);
				// 生成潜客信息
				Member cust = new Member();
				// 得到当前登录用户的部门id
				String bussDeptId = user.getEmpCode().getDeptId().getId();
				// 根据部门id查询部门的信息
				BussinessDept currentDept = ladDeptManager
						.getBusDeptByDeptId(bussDeptId);
				// 营业部所在城市id
				String cityId = null;
				String cityName = null;
				String provinceId = null;
				String provinceName = null;
				// 如果当前部门不为空,切当前部门坐在城市不为空，取它所在城市id设置为潜客所在城市
				if (currentDept != null) {
					cityId = currentDept.getCity() != null ? currentDept
							.getCity().getId() : null;
					cityName = currentDept.getCityName() != null ? currentDept.getCityName() : null;
					provinceId = currentDept.getProvince() != null ? currentDept
							.getProvince().getId() : null;
					provinceName = currentDept.getProvinceName() != null ? currentDept
							.getProvinceName() : null;
				}
				// 设置潜客的部门id，潜客挂在营业下
				cust.setDeptId(user.getEmpCode().getDeptId().getId());
				//联系人信息
				Contact con  = new Contact();
				// 设置联系人名称
				con.setName(StringUtils.replaceChars(
						order.getContactName(), " ", ""));
				//设置客户名称，如果客户名称为空，设置为联系人名称
				// 替换掉客户名称中所有的空格
				String shipperName = StringUtils.replaceChars(
						order.getShipperName(), " ", "");
				if(StringUtil.isEmpty(shipperName)){
					cust.setCustName(con.getName());
				}else{
					// 设置客户名称
					cust.setCustName(shipperName);
				}
				// 联系人手机
				con.setMobilePhone(order.getContactMobile());
				con.setLinkmanType("0,1,0,0,0");//业务联系人（联系人类型必填）
				if(order.getTransNote()!=Constant.ORDER_TRANSTYPE_AGENT_PACKAGE){//不是快递,设置客户类别"零担"
					cust.setCustCategory("LTT");//零担
				}else{
					cust.setCustCategory("EXPRESS");//快递
				}
				// 联系人电话
				con.setTelPhone(order.getContactPhone());
				List<Contact> contactList = new ArrayList<Contact>();
				contactList.add(con);
				cust.setContactList(contactList);

				// 设置潜客地址
				cust.setRegistAddress(concatAddress(order));
				//省
				if(!StringUtil.isEmpty(order.getContactProvince())){
					AreaSearchCondition condition = new AreaSearchCondition();
					cust.setProvince(order.getContactProvince());
					condition.setName(order.getContactProvince());
					Province province = areaAddressManager.searchProvinceByName(condition);
					if(province!=null){
						cust.setProvinceId(province.getId());
					}else{
						cust.setProvince(provinceName);
						cust.setProvinceId(provinceId);
					}
				}
				//市
				if(!StringUtil.isEmpty(order.getContactCity())){
					AreaSearchCondition condition = new AreaSearchCondition();
					cust.setCity((order.getContactCity()));
					condition.setName(order.getContactCity());
					City city = areaAddressManager.searchCityByName(condition);
					if(city!=null){
						cust.setCityId(city.getId());
					}else{
						cust.setCity(cityName);
						cust.setCityId(cityId);
					}
				}
				//区县
				if(!StringUtil.isEmpty(order.getContactArea())){
					AreaSearchCondition condition = new AreaSearchCondition();
					condition.setName(order.getContactArea());
					Area area = areaAddressManager.searchAreaByName(condition);
					if(area!=null){
						cust.setAreaId(area.getId());
					}
				}
				// 客户编号
				cust.setCustNumber(order.getShipperNumber());
				// 潜客来源——订单潜客
				cust.setChannelSource(Constant.ORDER_CUSTOMER);
				// 更新订单状态，设置订单状态为揽货失败
				o = this.orderService.updateOrder(o);
				// 更新完后根据状态同步接口系统数据
				syncOrderStatus(orderId, orderStatus_new);
				// 返回潜客信息和订单信息
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("order", o);
				map.put("cust", cust);
				return map;
			}
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Description:地址的组合， 以前的代码为一行组合完毕，不易阅读， 现在把以前的代码拆开来组合<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2012-12-29下午3:35:56
	 * @param order
	 * @return String
	 * @update 2012-12-29下午3:35:56
	 */
	@Transactional
	private String concatAddress(Order order) {
		// 组合地址
		StringBuffer combinationAddress = new StringBuffer();
		// 省
		combinationAddress.append(order.getContactProvince() == null ? ""
				: order.getContactProvince()+"-");
		// 市
		combinationAddress.append(order.getContactCity() == null ? "" : order
				.getContactCity()+"-");
		// 区
		combinationAddress.append(order.getContactArea() == null ? "" : order
				.getContactArea()+"-");
		// 详细地址
		combinationAddress.append(order.getContactAddress() == null ? ""
				: order.getContactAddress());
		// 返回组合地址
		return new String(combinationAddress);
	}

	/**
	 * 
	 * <p>
	 * Description:获取校验订单<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:24:26
	 * @param id
	 * @return
	 * @update 2013-1-22下午4:24:26
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	@Override
	public Map getOrderWithValidation(String id,User user) {
		Order order = orderService.getOrderById(id);
		// 校验订单是否可以更改
		if (orderValidator.isOrderUpdateable(order,user)) {
			// 始发网点
			String sartStation = order.getStartStation();
			// 出发部门
			BussinessDept beginDept = null;
			// 到达部门
			BussinessDept endDept = null;
			// 验证始发网点
			if (sartStation != null && !"".equals(sartStation.trim())) {
				// 出发部门
				beginDept = ladDeptManager.getBusDeptById(order
						.getStartStation());
				// 到达部门
				endDept = order.getReceivingToPoint() != null ? ladDeptManager
						.getBusDeptById(order.getReceivingToPoint()) : null;
			}
			// 运单信息
			WaybillInfo waybill = null;
			// 如果订单的运单号不为空，则根据运单号获取运单，否则抛出异常
			if (orderValidator.isObjectNotNull(order.getWaybillNumber())) {
				try {
					// 根据运单号获取运单信息
					waybill = orderService.getWaybillByNumber(order
							.getWaybillNumber());
				} catch (CrmBusinessException e) {
					// 运单不存在异常
					OrderException e1 = new OrderException(
							OrderExceptionType.WAYBILL_NOT_EXIST);
					// 抛出异常
					throw new GeneralException(e1.getErrorCode(),
							e1.getMessage(), e1, new Object[] {}) {
					};
				}
			}

			Map map = new HashMap();
			map.put("order", order);
			map.put("waybill", waybill);
			map.put("beginDept", beginDept);
			map.put("endDept", endDept);
			return map;
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Description:查询分配和拒绝的订单<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:26:43
	 * @param vosc
	 * @return
	 * @update 2013-1-22下午4:26:43
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map searchAssignAndRefuseOrders(OrderSearchCondition vosc) {
		// 订单列表
		List<Order> orderList = null;
		// 输入条件为空或者结束时间为空，则将当前时间赋值给结束时间
		if (null == vosc.getEndDate()) {
			// 设置结束日期
			vosc.setEndDate(new Date());
		}
		// 输入条件为空或者开始时间为空的处理
		if (null == vosc.getStartDate()) {
			Calendar c = Calendar.getInstance();
			c.setTime(vosc.getEndDate());
			c.add(Calendar.DATE, -30);
			vosc.setStartDate(c.getTime());
		}
		/*
		 * 先判断vosc对象是否为空，避免出现空指针
		 * 
		 * @author zouming
		 */
		// 验证输入条件
		if (vosc != null) {
			// 如果来源为全部，条件来源设置为空
			if (Constant.ORDER_STATUS_ALL.equals(vosc.getResource())) {
				vosc.setResource(null);
			}
			// 如果订单状态为全部，条件状态设置为空
			if (Constant.ORDER_STATUS_ALL.equals(vosc.getOrderStatus())) {
				vosc.setOrderStatus(null);
			}
			/**
			 * 修改人：张斌 修改时间：2013-7-27 11:40 原有内容：无（新增校验）
			 * 修改原因：校验运输方式，选择“全部”，设置该值为null就能查询出全部
			 */
			// 验证运输方式
			if (Constant.ORDER_STATUS_ALL.equals(vosc.getTransportMode())) {
				// 设置运输方式
				vosc.setTransportMode(null);
			}
		}
		// 搜索条件
		OrderSearchCondition osc = vosc;
		// 如果前台传的分页参数有问题，则需要处理
		if (null != osc) {
			// 如果start为空，设置为0
			if (null == osc.getStart()) {
				osc.setStart(0);
			}
			// 如果limit为空，设置为10
			if (null == osc.getLimit()) {
				osc.setLimit(10);
			}
		}

		// 搜索条件
		OrderSearchCondition condition = new OrderSearchCondition();
		// 设置start
		condition.setStart(osc.getStart());
		// 设置limit
		condition.setLimit(osc.getLimit());

		Long count = 0L;

		// 如果订单号不为空，就按订单号查
		if (StringUtil.isNotEmpty(osc.getOrderNum())) {
			// 设置订单号
			condition.setOrderNum(osc.getOrderNum());
			// 获取订单列表
			orderList = orderService.searchAssignAndRefuseOrders(condition);
			// 根据输入条件查询订单总数
			count = orderService.getTotalCount(condition);
			// 如果运单号不为空就按运单号查
		} else if (StringUtil.isNotEmpty(osc.getWaybillNum())) {
			// 设置运单号
			condition.setWaybillNum(osc.getWaybillNum());
			// 根据输入条件查询订单列表
			orderList = orderService.searchAssignAndRefuseOrders(condition);
			// 根据输入条件获取订单总数
			count = orderService.getTotalCount(condition);
			// 如果客户手机不为空就按手机查
		} else if (StringUtil.isNotEmpty(osc.getContactMobile())) {
			// 设置合同手机
			condition.setContactMobile(osc.getContactMobile());
			// 根据输入条件获取订单列表
			orderList = orderService.searchAssignAndRefuseOrders(condition);
			// 根据输入条件获取订单总数
			count = orderService.getTotalCount(condition);
			// 如果固话不为空按固话条件
		} else if (StringUtil.isNotEmpty(osc.getContactPhone())) {
			// 设置合同电话
			/**
			 * 修改人：吕崇新 修改时间：2013-12-27  原有内容：无（新增校验）
			 * 修改原因：校验查询电话位数不得少于4
			 */
			if(osc.getContactPhone().length()>=4){
				condition.setContactPhone(osc.getContactPhone());
				// 根据输入条件查询订单列表
				orderList = orderService.searchAssignAndRefuseOrders(condition);
				// 根据输入条件获取订单总数
				count = orderService.getTotalCount(condition);
			}else{
				//查询电话位数少于4，返回为空
				orderList=null;
				count=0L;
			}
			
			// 如果时间不为空加按组合条件
		} else if (osc.getStartDate() != null && osc.getEndDate() != null) {
			if (orderValidator.isInDayRange(osc.getStartDate(),
					osc.getEndDate(), Constant.SEARCH_MAX_DAY_RANGE)) {
				// 根据输入条件获取订单列表
				orderList = orderService.searchAssignAndRefuseOrders(osc);
				// 根据输入条件获取订单总数
				count = orderService.getTotalCount(osc);
			}
		} else {
			// 根据输入条件获取分配和拒绝订单列表
			orderList = orderService.searchAssignAndRefuseOrders(osc);
			// 根据输入条件获取订单总数
			count = orderService.getTotalCount(osc);
		}

		Map map = new HashMap();
		map.put("count", count);
		map.put("orderList", orderList);
		return map;
	}

	/**
	 * 
	 * <p>
	 * Description:根据订单ID获取订单信息<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-28上午11:14:51
	 * @param orderId
	 * @return
	 * @update 2013-1-28上午11:14:51
	 */
	@Transactional(readOnly = true)
	@Override
	public Order getOrderById(String orderId) {
		return orderService.getOrderById(orderId);
	}

	/**
	 * 
	 * <p>
	 * Description:分配订单<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:27:22
	 * @param orderIds
	 * @param vorder
	 * @param user
	 * @update 2013-1-22下午4:27:22
	 */
	@Transactional
	@Override
	public void assignOrder(List<String> orderIds, Order vorder, User user) {
		List<Order> orderList = orderService.getOrdersByIds(orderIds);
		// 校验订单是否可以分配，校验批量分配的订单的信息是否一样，
		if (orderValidator.isAssignableOrder(orderList)
				&& orderValidator.isSameOrderInfo(orderList)) {
			// 订单操作日志列表
			List<OrderOperationLog> orderOperationLogList = new ArrayList<OrderOperationLog>();
			// 订单状态
			String orderStatus = Constant.ORDER_STATUS_WAIT_ACCEPT;

			// 订单来源
			vorder.setResource(orderList.get(0).getResource());
			// 如果始发网点id不是数字，则转换为数字
			if (orderValidator.isObjectNotNull(vorder.getStartStation())
					&& !vorder.getStartStation().matches("[0-9]+")) {
				// 营业部
				BussinessDept bd = ladDeptManager.getBusDeptByErpId(vorder
						.getStartStation());
				// 设置始发网点
				vorder.setStartStation(bd.getId());
			}

			// 始发网点
			String startStation = vorder.getStartStation();
			// 始发网点名字
			String startStationName = vorder.getStartStationName();
			// 根据分配的始发网点查询受理部门
			Department acceptDept = getAcceptDept(vorder);
			// 先把始发网点作为其受理部门
			String acceptDeptId = vorder.getStartStation();
			// 受理部门名字
			String acceptDeptName = vorder.getStartStationName();
			// 如果根据始发网点查询出的受理部门不为空，则改变受理部门的值
			if (null != acceptDeptId) {
				// 受理部门ID
				acceptDeptId = acceptDept.getId();
				// 受理部门名字
				acceptDeptName = acceptDept.getDeptName();
			}

			// 内容
			String content = MessageFormat.format(
					Constant.ORDER_OPERATE_ASSIGN_LOG,
					new Object[] {
							OrderUtil.getCHNOrderStatusByENStatus(orderStatus),
							startStationName, acceptDeptName });
			for (Order order : orderList) {
				// 生成订单操作日志
				OrderOperationLog orderOperationLog = OrderUtil
						.generateOrderOperationLog(order,
								Constant.ORDER_OPERATION_ASSIGN, content, user);
				// 把生成的操作日志加入操作日志列表
				orderOperationLogList.add(orderOperationLog);
			}
			// 更新订单分配信息
			orderService.updateOrderAssignBatch(orderIds, acceptDeptId,
					acceptDeptName, startStation, orderStatus,
					orderOperationLogList);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:拒绝订单<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:27:42
	 * @param orderId
	 * @param user
	 * @param refuseReason
	 * @return
	 * @update 2013-1-22下午4:27:42
	 */
	@Transactional
	@Override
	public Order refuseOrder(String orderId, User user, String refuseReason) {
		Order order = orderService.getOrderById(orderId);
		Order ord = null;
		// 检验订单是否符合拒绝条件
		// 校验打回原因是否为空
		if (orderValidator.isRefuseableOrder(order)
				&& orderValidator.isFeedbackInfoNotNull(refuseReason)) {
			// 设置订单状态
			order.setOrderStatus(Constant.ORDER_STATUS_REJECT);
			// 内容
			String content = MessageFormat.format(
					Constant.ORDER_OPERATE_REFUSE_LOG,
					new Object[] { Constant.ORDER_STATUS_REJECT_CHN });
			// 生成订单操作日志
			OrderOperationLog orderOperationLog = OrderUtil
					.generateOrderOperationLog(order,
							Constant.ORDER_OPERATION_REFUSE, content, user);
			// 操作日志集合
			Set<OrderOperationLog> logs = new HashSet<OrderOperationLog>();
			// 将生成的操作日志添加到操作日志集合里面
			logs.add(orderOperationLog);
			// 给订单设置操作日志集合
			order.setOperationLogs(logs);

			// 根据订单ID获取订单信息
			ord = orderService.getOrderById(orderId);
			if (order.getChannelType() != null
					&& !order.getChannelType().equals(
							Constant.ORDER_CHANNEL_TYPE_SELF)) {
				// 构造同步渠道的订单信息
				ChannelOrderStatusInfo orderInfo = new ChannelOrderStatusInfo();
				// 设置渠道单号
				orderInfo.setChannelOrderNumber(order.getChannelNumber());
				// 设置退回原因
				orderInfo.setComment(refuseReason);
				// 设置订单状态
				orderInfo.setOrderStatus(order.getOrderStatus());
				// 设置订单状态
				orderInfo.setOrderNumber(order.getOrderNumber());

				// 跟新订单信息
				orderService.updateOrderWithChannel(orderInfo, order);
			} else {
				// 跟新订单信息
				orderService.updateOrder(order);
			}
			// 更新完后根据状态同步接口系统数据
			syncOrderStatus(orderId, Constant.ORDER_STATUS_REJECT);
		}
		// 返回订单
		return ord;
	}

	/**
	 * 
	 * <p>
	 * Description:获取订单操作日志列表<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:28:39
	 * @param orderId
	 * @return
	 * @update 2013-1-22下午4:28:39
	 */
	@Transactional(readOnly = true)
	@Override
	public List<OrderOperationLog> getOrderOperationLogList(String orderId) {
		// 获取操作日志列表
		List<OrderOperationLog> orderOperationLogList = orderService
				.getOrderOperationLogList(orderId);
		// 返回操作日志列表
		return orderOperationLogList;
	}

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
	@Transactional(readOnly = true)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map searchProcessAndReturnOrders(OrderSearchCondition vosc, User user) {
		// 输入条件为空或者结束时间为空，则将当前时间赋值给结束时间
		if (null == vosc.getEndDate()) {
			// 设置结束日期
			vosc.setEndDate(new Date());
		}
		// 输入条件为空或者开始时间为空的处理
		if (null == vosc.getStartDate()) {
			Calendar c = Calendar.getInstance();
			c.setTime(vosc.getEndDate());
			c.add(Calendar.DATE, -30);
			vosc.setStartDate(c.getTime());
		}
		// 部门ID
		String deptId = null;
		// 部门ID列表
		List<String> deptIds = new ArrayList<String>();

		/**
		 * 查询规则调整 2012/09/07 jira CRM-2578 by zpj
		 * 根据制单员时，需要结合始发网点部门;未选中部门时，查询所有部门。 不根据制单员时，沿用旧规则，即：无部门时采用默认登陆用户所在部门
		 */
		/* CRM-3181 先判断vosc，再使用 */
		if (vosc != null
				&& StringUtils.isEmpty(vosc.getCreateEmpName())
				&& (vosc.getStartStationId() == null || "".equals(vosc
						.getStartStationId().trim()))) {
			// 只能查询当前登录用户所有在部门的订单
			if (orderValidator.isUserLogin(user)) {
				deptId = user.getEmpCode().getDeptId().getId();
			}
			deptIds.add(deptId);
			vosc.setDeptIds(deptIds);
		}

		/*
		 * 先对vosc进行空校验，避免出现空指针
		 * 
		 * @author zouming
		 * 
		 * @date 2012-01-21
		 */
		if (vosc != null) {
			// 验证订单来源
			if (Constant.ORDER_STATUS_ALL.equals(vosc.getResource())) {
				// 设置来源
				vosc.setResource(null);
			}
			// 验证订单状态
			if (Constant.ORDER_STATUS_ALL.equals(vosc.getOrderStatus())) {
				// 设置订单状态
				vosc.setOrderStatus(null);
			}
			/**
			 * 修改人：张斌 修改时间：2013-7-27 11:40 原有内容：无（新增校验）
			 * 修改原因：校验运输方式，选择“全部”，设置该值为null就能查询出全部
			 */
			// 验证运输方式
			if (Constant.ORDER_STATUS_ALL.equals(vosc.getTransportMode())) {
				// 设置运输方式
				vosc.setTransportMode(null);
			}
		}
		// 设置部门ID列表
		vosc.setDeptIds(deptIds);
		// 订单查询条件
		OrderSearchCondition osc = vosc;
		// 如果前台传的分页参数有问题，则需要处理
		if (null == osc || null == osc.getStart()) {
			// 设置查询start
			osc.setStart(0);
		}
		// 验证订单与订单limit
		if (null == osc || null == osc.getLimit()) {
			// 设置查询limit
			osc.setLimit(10);
		}

		// 订单查询条件
		OrderSearchCondition condition = new OrderSearchCondition();
		// 设置start
		condition.setStart(osc.getStart());
		// 设置limit
		condition.setLimit(osc.getLimit());

		// 订单总数
		Long count = 0L;
		// 订单列表
		List<Order> orderList = null;

		// 如果订单号不为空，就按订单号查
		if (orderValidator.isObjectNotNull(vosc.getOrderNum())) {
			// 设置订单号
			condition.setOrderNum(vosc.getOrderNum());
			// 获取订单列表
			orderList = orderService.searchOrders(condition);
			// 获取订单总数
			count = orderService.getTotalCount(condition);
			// 如果运单号不为空就按运单号查
		} else if (orderValidator.isObjectNotNull(vosc.getWaybillNum())) {
			// 设置运单号
			condition.setWaybillNum(vosc.getWaybillNum());
			// 获取订单列表
			orderList = orderService.searchOrders(condition);
			// 获取订单总数
			count = orderService.getTotalCount(condition);
			// 如果客户手机不为空就按手机查
		} else if (orderValidator.isObjectNotNull(vosc.getContactMobile())) {
			// 设置合同手机
			condition.setContactMobile(vosc.getContactMobile());
			// 获取订单列表
			orderList = orderService.searchOrders(condition);
			// 获取订单总数
			count = orderService.getTotalCount(condition);
			// 如果固话不为空按固话条件
		} else if (StringUtil.isNotEmpty(osc.getContactPhone())) {
			/**
			 * 修改人：吕崇新 修改时间：2013-12-27  原有内容：无（新增校验）
			 * 修改原因：校验查询电话位数不得少于4
			 */
			if(osc.getContactPhone().length()>=4){
				// 设置合同电话
				condition.setContactPhone(osc.getContactPhone());
				// 获取订单列表
				orderList = orderService.searchOrders(condition);
				// 获取订单总数
				count = orderService.getTotalCount(condition);
			}else{
				//查询电话位数少于4，返回为空
				orderList=null;
				count=0L;
			}
			// 如果时间不为空加按组合条件
		} else {
			// 获取订单列表
			orderList = orderService.searchOrders(vosc);
			// 获取订单总数
			count = orderService.getTotalCount(vosc);
		}

		Map map = new HashMap();
		map.put("count", count);
		map.put("orderList", orderList);
		map.put("vosc", vosc);

		return map;
	}

	/**
	 * 
	 * @description 受理订单.
	 * @author 潘光均
	 * @version 0.1 2012-3-16
	 * @param OrderView
	 * @date 2012-3-16
	 * @return OrderView
	 * @update 2012-3-16 上午11:23:40
	 */
	@Transactional
	@Override
	public Order processOrder(String id, User user, String feedbackInfo) {
		Order order = orderService.getOrderById(id);
		Order ord = null;
		// 400二次反馈
		// 增加呼叫中心合肥 2013-11-12
		if (OrderUtil.isCallCenter(order.getResource())
				&& Constant.ORDER_STATUS_ACCEPT.equalsIgnoreCase(order
						.getOrderStatus())) {
			// 新建订单对象
			Order order1 = new Order();
			// 设置ID
			order1.setId(order.getId());
			// 设置反馈信息
			order1.setFeedbackInfo(feedbackInfo);

			// 1、构造操作记录
			String content = MessageFormat
					.format(Constant.ORDER_OPERATE_FEEDBACKINFO_LOG,
							new Object[] { StringUtils.isNotEmpty(feedbackInfo) ? feedbackInfo
									: "无" });
			// 生成操作日志
			OrderOperationLog log = OrderUtil.generateOrderOperationLog(order,
					Constant.ORDER_OPERATION_PROCESS, content, user);
			// 订单操作日志集合
			Set<OrderOperationLog> logs = new HashSet<OrderOperationLog>();
			// 将生成 的订单操作日志加入到订单操作日志集合里面
			logs.add(log);
			// 给订单对象设置操作日志集合
			order1.setOperationLogs(logs);

			// 2、修改订单
			ord = this.orderService.updateOrder(order1);
		} else {
			// 日志内容
			String content = MessageFormat.format(
					Constant.ORDER_OPERATE_REFUSE_LOG,
					new Object[] { Constant.ORDER_STATUS_ACCEPT_CHN });

			// 反馈信息
			// 增加呼叫中心合肥 2013-11-12
			if (OrderUtil.isCallCenter(order.getResource())) {
				content = content
						+ MessageFormat
								.format(Constant.ORDER_OPERATE_FEEDBACKINFO_LOG,
										new Object[] { StringUtils
												.isNotEmpty(feedbackInfo) ? feedbackInfo
												: "无" });
				// 反馈信息
				order.setFeedbackInfo(feedbackInfo);
			}

			// 生成操作日志
			OrderOperationLog log = OrderUtil.generateOrderOperationLog(order,
					Constant.ORDER_OPERATION_PROCESS, content, user);
			// 操作日志集合
			Set<OrderOperationLog> logs = new HashSet<OrderOperationLog>();
			// 将生成的操作日志添加到操作日志集合里面
			logs.add(log);

			// 订单增加操作记录
			order.setOperationLogs(logs);
			if (orderValidator.validateOrderProcessable(order)) {
				order.setOrderStatus(Constant.ORDER_STATUS_ACCEPT);
				// 对于未分配直接受理的订单或不是本部门的订单，设置它的始发网点和受理部门都为当前登录用户所在部门
				String userDeptId = user.getEmpCode().getDeptId().getId();
				// 用户部门名字
				String userDeptName = user.getEmpCode().getDeptId()
						.getDeptName();
				// 受理部门ID
				String acceptDeptId = order.getAcceptDept();
				// 验证始发网点，受理部门ID，用户部门ID
				if (!orderValidator.isObjectNotNull(order.getStartStation())
						|| !orderValidator.isObjectNotNull(acceptDeptId)
						|| !acceptDeptId.equals(userDeptId)) {
					// 设置始发网点
					order.setStartStation(userDeptId);
					// 设置始发网点名字
					order.setStartStationName(userDeptName);

					// 设置受理部门
					order.setAcceptDept(userDeptId);
					// 设置受理部门名字
					order.setAcceptDeptName(userDeptName);
				}

				if (orderValidator.isOrderFromOnlineOrCallcenter(order
						.getResource())) {
					// 不需要更新外部系统
					order.setOrderAcceptTime(new Date());
					ord = orderService.updateOrder(order);
				} else {
					// 更新外部系统和crm系统
					// 设置订单受理时间
					order.setOrderAcceptTime(new Date());
					// 渠道订单状态信息
					ChannelOrderStatusInfo orderInfo = new ChannelOrderStatusInfo();
					// 设置渠道单号
					orderInfo.setChannelOrderNumber(order.getChannelNumber());
					// 失败原因
					orderInfo.setComment("");
					// 订单状态
					orderInfo.setOrderStatus(order.getOrderStatus());
					// 订单号
					orderInfo.setOrderNumber(order.getOrderNumber());
					// 更新订单信息
					ord = orderService.updateOrderWithChannel(orderInfo, order);
				}
			}

			/**
			 * CRM-3177 Sonar Review #10232 - Correctness - Possible null
			 * pointer dereference 增添 null != ord 条件判断，验证 ord 非空使用
			 */
			// 短信提醒
			if (null != ord
					// 增加呼叫中心合肥 2013-11-12
					&& !OrderUtil.isCallCenter(order.getResource())) {
				if (ord.getIsSendmessage() != null && ord.getIsSendmessage()
						&& ord.getContactMobile() != null
						&& !"".equals(ord.getContactMobile())) {
					String message = "";
					if (Constant.ORDER_SOURCE_BUSINESS_DEPT.equals(ord
							.getResource())
							|| Constant.ORDER_SOURCE_ONLINE.equals(ord
									.getResource())) {
						message = MessageFormat
								.format(Constant.ORDER_MESSAGE_ACCEPT,
										new Object[] {
												order.getReceiverCustProvince()
														+ order.getReceiverCustCity()
														+ order.getReceiverCustArea()
														+ order.getReceiverCustAddress(),
												order.getReceiverCustName(),
												order.getOrderNumber() });
					} else {
						String phone = "";
						if (StringUtils.isNotEmpty(order.getStartStation())) {
							Department department = departmentService
									.getDepartmentById(order.getStartStation());
							phone = department.getPhone();
						}
						String receivingToPointName = order
								.getReceivingToPointName();
						if (StringUtils.isNotEmpty(receivingToPointName)) {
							receivingToPointName = "(" + receivingToPointName
									+ ")";
						} else {
							receivingToPointName = "";
						}
						message = MessageFormat
								.format(Constant.ORDER_MESSAGE_ACCEPT_CHANNEL,
										new Object[] {
												order.getReceiverCustProvince()
														+ order.getReceiverCustCity()
														+ order.getReceiverCustArea()
														+ order.getReceiverCustAddress(),
												receivingToPointName,
												order.getReceiverCustName(),
												order.getChannelNumber(),
												order.getStartStationName(),
												phone });
					}
					/**
					 * 短信内容组装
					 */
					SmsInformation info = new SmsInformation();
					String deptStandardCode = user.getEmpCode().getDeptId()
							.getStandardCode();
					String empCode = user.getEmpCode().getEmpCode();
					info.setSendDept(deptStandardCode);
					info.setSender(empCode);
					info.setMobile(ord.getContactMobile());
					info.setMsgContent(message);
					info.setMsgType(com.deppon.crm.module.client.common.util.Constant.SMS_ORDER_CODE);
					orderService.sendMessage(info);
				}
			}
		}
		// 返回订单
		return ord;
	}

	/**
	 * 
	 * <p>
	 * Description:获取能约车的订单<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:29:26
	 * @param orderId
	 * @return
	 * @update 2013-1-22下午4:29:26
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map getOrderIfCanBookVehicle(String orderId) {
		Map map = new HashMap();
		// 根据订单ID获取订单
		Order ord = orderService.getOrderById(orderId);
		/*		修改人：张斌
		修改时间：2017-7-30
		修改内容：无（新增）
		修改原因：加上“快递订单校验(不允许约车)”*/
		//begin
		if(Constant.ORDER_TRANSTYPE_AGENT_PACKAGE.equals(ord.getTransportMode())){
			OrderException e = new OrderException(
					OrderExceptionType.PILOTCITY_CALLCAR_NOT);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;
			};
		}
		//end
		//this.assertPilotPackageOrder(order,OrderExceptionType.PILOTCITY_ACCEPT_NOT);
		if (ord != null) {
			// 取得始发网点
			BussinessDept beginDept = ord.getStartStation() != null ? ladDeptManager
					.getBusDeptById(ord.getStartStation()) : null;
			// 取得到达网点
			BussinessDept endDept = ord.getReceivingToPoint() != null ? ladDeptManager
					.getBusDeptById(ord.getReceivingToPoint()) : null;

			// 验证订单状态始发可以约车
			if (orderValidator.isOrderCanBookVehice(ord)) {
				map.put("order", ord);
				map.put("beginDept", beginDept);
				map.put("endDept", endDept);
			}
		}

		return map;
	}

	/**
	 * 
	 * <p>
	 * Description:约车<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:30:21
	 * @param vorder
	 * @param bkinfo
	 * @param user
	 * @return
	 * @update 2013-1-22下午4:30:21
	 */
	@Transactional
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	@Override
	public Map bookVehicle(Order vorder, BookVehicleInfo bkinfo, User user) {
		/*
		 * 场景：零担改快递报错
		 * 		快递改零担报错
		 * 		改为试点快递约车，直接同步到FOSS并返回
		 * 		改为非试点快递约车，报错提示
		 */
		//begin
		Order order = orderService.getOrderById(vorder.getId());
		if (isPackageOrder(vorder) && !isPackageOrder(order)) {
			throw OrderUtil
					.buildException(OrderExceptionType.PILOTCITY_PACKAGE_CHANGE_NOT);
		}
		if (!isPackageOrder(vorder) && isPackageOrder(order)) {
			throw OrderUtil
					.buildException(OrderExceptionType.PILOTCITY_PACKAGE_CHANGE_NOT2);
		}
        //快递是不允许约车的，所以约车操作是不会有试点城市快递订单出现的（零担不能改快递，快递不能改零担）
		//end
		Map map = new HashMap();
		// 验证订单是否完成，验证订单约车，验证约车是否完成
		if (orderValidator.validateOrderInfoComplete(vorder,false)
				&& orderValidator.bookVehicleValidator(vorder)
				&& orderValidator.isBookVehicleInfoComplete(bkinfo)) {
			vorder.setMemberType(order.getMemberType());
			// 验证是否可以约车
			if (orderValidator.isOrderCanBookVehice(order)) {
				// 设置订单状态
				vorder.setOrderStatus(Constant.ORDER_SATUTS_SHOUTCAR);
				// 设置约车优惠劵
				vorder.setCouponNumber(order.getCouponNumber());
				// 设置约车运单号
				vorder.setWaybillNumber(order.getWaybillNumber());
				// 消息内容
				String content = MessageFormat.format(
						Constant.ORDER_OPERATE_REFUSE_LOG,
						new Object[] { Constant.ORDER_STATUS_BOOKVECHCEL_CHN,
								order.getOrderStatus() });
				// 生成操作日志
				OrderOperationLog log = OrderUtil.generateOrderOperationLog(
						order,
						Constant.ORDER_OPERATION_BOOKVEHICLE,
						content
								+ OrderUtil.produceModifyLog(order, vorder,
										ladDeptManager), user);
				// 操作日志集合
				Set<OrderOperationLog> logs = new HashSet<OrderOperationLog>();
				// 将生成的操作日志添加到操作日志集合里面
				logs.add(log);
				boolean result = false;
				// 营业部
				BussinessDept bd = null;
				try {
					// begin 调用约车接口时用
					if (orderValidator.isObjectNotNull(vorder
							.getReceivingToPoint())) {
						// 根据部门ID获取营业部
						bd = this.ladDeptManager.getBusDeptById(vorder
								.getReceivingToPoint());
						/*
						 * 修改人：胡傲果
						 * 修改时间：2013-07-31
						 * 修改内容：简化代码
						 * 修改原因：原代码太冗长，看起来不方便
						 */
						// 如果营业部不为空
						if (bd != null) {
							// 设置EPP ID
							String erpId = bd.getErpId();
							// 验证ERP ID是否为空
							if (erpId != null && !"".equals(erpId)) {
								// 设置ERP ID
								bkinfo.setErpId(erpId);
							} else {
								// 定义异常 “约车时，到达网点对应的erpId不能为空”
								OrderException e = new OrderException(
										OrderExceptionType.BOOKVIHICE_ERPID_CANNOTNULL);
								// 抛出异常
								throw new GeneralException(e.getErrorCode(),
										e.getMessage(), e, new Object[] {}) {
								};
							}
						} else {
							// 定义异常 “约车时，到达网点对应的erpId不能为空”
							OrderException e = new OrderException(
									OrderExceptionType.BOOKVIHICE_ERPID_CANNOTNULL);
							// 抛出异常
							throw new GeneralException(e.getErrorCode(),
									e.getMessage(), e, new Object[] {}) {
							};
						}
					}
					// 产生erp约车信息
					AppointmentCarInfo info = OrderUtil.produceERPOrder(vorder,
							bkinfo);
					// 设回到达网点的id
					if (bd != null) {
						// 设置到达网点
						vorder.setReceivingToPoint(bd.getId());
					}
					/*		修改人：kuang
					修改时间：2013-8-5
					修改内容：增加代码String code = null;
					if(!"".equals(vorder.getReceivingToPoint()) || vorder.getReceivingToPoint() != null){
						code =
						(ladDeptManager.getBusDeptById(vorder.getReceivingToPoint())).getDeptCode();
					}
					info.setDeptCode(code);
					修改原因：将DeptCode变为到达网点的标杆编码*/
					String code = vorder.getReceivingToPoint();
					if(!"".equals(code) && code != null){
						code =
						(ladDeptManager.getBusDeptById(vorder.getReceivingToPoint())).getDeptCode();
					}
					info.setDeptCode(code);
					// 设置操作日志
					vorder.setOperationLogs(logs);

					// 约车
					result = orderService.updateOrderToErp(info, vorder);
				} catch (CrmBusinessException e) {
					// 抛出异常
					throw new GeneralException(e.getMessage(),
							e.getErrorCode(), e, new Object[] {}) {
					};
				}
				// 验证订单是否发送成功
				if (orderValidator.isSendOrderSuccess(result)) {
					map.put("vorder", vorder);
					map.put("bkinfo", bkinfo);
					return map;
				}
			}
		}
		return null;
	}
	
	/**
	 * 将订单改为已约车并同步到FOSS
	 * @param vorder 订单信息
	 * @author 胡傲果
	 * @date 2013-07-31
	 */
	@Transactional
	private void changeToPilotPackageOrder(Order vorder) {
		vorder.setOrderStatus(Constant.ORDER_SATUTS_SHOUTCAR);
		orderService.updateOrder(vorder);
		AppointmentCarInfo appointmentCarInfo = OrderUtil.produceERPOrder(vorder, null);
		try {
			orderOperate.appointmentCar(appointmentCarInfo);
		} catch (CrmBusinessException e) {
			throw OrderUtil.buildException(e);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:根据订单号获取订单<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:30:39
	 * @param orderNumber
	 * @return
	 * @update 2013-1-22下午4:30:39
	 */
	@Transactional(readOnly = true)
	@Override
	public Order getOrderWaybill(String orderNumber) {
		return orderService.getOrderByOrderNumber(orderNumber);
	}

	/**
	 * 
	 * <p>
	 * Description:查询运单信息<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:31:32
	 * @param number
	 * @return
	 * @update 2013-1-22下午4:31:32
	 */
	@SuppressWarnings("serial")
	@Transactional(readOnly = true)
	@Override
	public WaybillInfo getWaybillByNumber(String number) {
		WaybillInfo waybill = null;
		try {
			// 根据运单号查询运单信息
			waybill = orderService.getWaybillByNumber(number);
		} catch (CrmBusinessException e) {
			// 定义异常“运单号不存在”
			OrderException e1 = new OrderException(
					OrderExceptionType.WAYBILL_NOT_EXIST);
			// 抛出异常
			throw new GeneralException(e1.getErrorCode(), e1.getMessage(), e1,
					new Object[] {}) {
			};
		}
		// 返回运单号
		return waybill;
	}

	/**
	 * 
	 * <p>
	 * Description:订运单的联系<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:32:00
	 * @param vwaybill
	 * @param orderNumber
	 * @return
	 * @update 2013-1-22下午4:32:00
	 */
	@Transactional
	@SuppressWarnings("serial")
	@Override
	public boolean associateOrderWaybill(WaybillInfo vwaybill,
			String orderNumber) {
		// 根据订单号查询订单信息
		Order order = orderService.getOrderByOrderNumber(orderNumber);
		// 运单信息
		WaybillInfo waybill;
		try {
			// 根据运单号查询运单信息
			waybill = orderService.getWaybillByNumber(vwaybill
					.getWaybillNumber());
		} catch (CrmBusinessException e) {
			// 定义异常 “运单不存在”
			OrderException e1 = new OrderException(
					OrderExceptionType.WAYBILL_NOT_EXIST);
			// 抛出异常
			throw new GeneralException(e1.getErrorCode(), e1.getMessage(), e1,
					new Object[] {}) {
			};
		}
		// 验证运单签收信息
		if (!orderValidator.isWaybillSigned(waybill)) {
			// order.setWaybillNumber(waybill.getWaybillNumber());
			Order ord = new Order();
			// 设置ID
			ord.setId(order.getId());
			// 设置运单号
			ord.setWaybillNumber(vwaybill.getWaybillNumber());
			// 设置订单号
			ord.setOrderNumber(order.getOrderNumber());
			boolean result;
			try {
				// 订运单关联结果
				result = orderService.associateOrderWaybill(
						ord,
						order.getWaybillNumber() == null ? "" : order
								.getWaybillNumber());
			} catch (CrmBusinessException e) {
				e.printStackTrace();
				// 定义异常 “定运单关联失败”
				OrderException e1 = new OrderException(
						OrderExceptionType.ORDER_ASSOCIATE_FAIL);
				// 抛出异常
				throw new GeneralException(e1.getErrorCode(), e1.getMessage(),
						e1, new Object[] {}) {
				};
			}
			// 验证订单是否发送成功
			if (orderValidator.isSendOrderSuccess(result)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * Description:删除订单<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:32:53
	 * @param id
	 * @update 2013-1-22下午4:32:53
	 */
	@Transactional
	@Override
	public void deleteOrder(String id) {
		orderService.deleteOrder(id);
	}

	/**
	 * 
	 * <p>
	 * Description:提醒管理者<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:33:13
	 * @update 2013-1-22下午4:33:13
	 */
	@Override
	@Transactional
	public void remindOrderMonitor() {
		// 删除未分配消息
		messageManager
				.deleteMessageByType(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_ORDER_UNASSIGN);
		// 未分配订单列表
		List<Message> unassignOrder = getUnassignOrderMessage();
		// 如果存在未分配订单，则加入消息列表
		if (unassignOrder != null && unassignOrder.size() > 0) {
			// 增加消息列表
			messageManager.addMessageList(unassignOrder);
		}

		// 删除未受理消息
		messageManager
				.deleteMessageByType(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_ORDER_UNACCEPT);
		// 未受理订单列表
		List<Message> unacceptOrder = getUnacceptOrderMessage();
		// 如果存在未受理订单，则将其加入消息列表
		if (unacceptOrder != null && unacceptOrder.size() > 0) {
			// 增加未受理订单
			messageManager.addMessageList(unacceptOrder);
		}

		// 删除已受理消息
		messageManager
				.deleteMessageByType(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_ORDER_ACCEPTED);
		// 已受理订单消息列表
		List<Message> acceptedOrder = getAcceptedOrderMessage();
		// 如果存在未受理,则加入消息列表
		if (acceptedOrder != null && acceptedOrder.size() > 0) {
			// 增加已受理订单
			messageManager.addMessageList(acceptedOrder);
		}

		// 删除退回订单消息
		messageManager
				.deleteMessageByType(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_ORDER_GOBACK);
		// 退回订单列表
		List<Message> gobackOrder = getGobackOrderMessage();
		// 如果存在退回订单，则加入退回订单消息
		if (gobackOrder != null && gobackOrder.size() > 0) {
			// 加入返回订单列表
			messageManager.addMessageList(gobackOrder);
		}
	}

	/**
	 * @description 生成订单未分配待办事宜.
	 * @author 黄展明
	 * @version 0.1 2013-10-18
	 * @return void
	 */
	@Override
	@Transactional
	public void generateOrderUnassign() {
		// 删除未分配消息
		messageManager
				.deleteMessageByType(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_ORDER_UNASSIGN);
		// 未分配订单列表
		List<Message> unassignOrder = getUnassignOrderMessage();
		// 如果存在未分配订单，则加入消息列表
		if (unassignOrder != null && unassignOrder.size() > 0) {
			// 增加消息列表
			messageManager.addMessageList(unassignOrder);
		}
	}

	/**
	 * @description 生成订单未受理待办事宜.
	 * @author 黄展明
	 * @version 0.1 2013-10-18
	 * @return void
	 */
	@Override
	@Transactional
	public void generateOrderUnaccept() {
		// 删除未受理消息
		messageManager
				.deleteMessageByType(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_ORDER_UNACCEPT);
		// 未受理订单列表
		List<Message> unacceptOrder = getUnacceptOrderMessage();
		// 如果存在未受理订单，则将其加入消息列表
		if (unacceptOrder != null && unacceptOrder.size() > 0) {
			// 增加未受理订单
			messageManager.addMessageList(unacceptOrder);
		}
	}

	/**
	 * @description 生成订单已受理待办事宜.
	 * @author 黄展明
	 * @version 0.1 2013-10-18
	 * @return void
	 */
	@Override
	@Transactional
	public void generateOrderAccepted() {
		// 删除已受理消息
		messageManager
				.deleteMessageByType(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_ORDER_ACCEPTED);
		// 已受理订单消息列表
		List<Message> acceptedOrder = getAcceptedOrderMessage();
		// 如果存在未受理,则加入消息列表
		if (acceptedOrder != null && acceptedOrder.size() > 0) {
			// 增加已受理订单
			messageManager.addMessageList(acceptedOrder);
		}
	}

	/**
	 * @description 生成订单已退回待办事宜.
	 * @author 黄展明
	 * @version 0.1 2013-10-18
	 * @return void
	 */
	@Override
	@Transactional
	public void generateOrderGoback() {
		// 删除退回订单消息
		messageManager
				.deleteMessageByType(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_ORDER_GOBACK);
		// 退回订单列表
		List<Message> gobackOrder = getGobackOrderMessage();
		// 如果存在退回订单，则加入退回订单消息
		if (gobackOrder != null && gobackOrder.size() > 0) {
			// 加入返回订单列表
			messageManager.addMessageList(gobackOrder);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:获得未分配订单消息<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:33:37
	 * @return List<Message>
	 * @update 2013-1-22下午4:33:37
	 */
	@Transactional
	private List<Message> getUnassignOrderMessage() {
		// 状态列表
		List<String> statuses = new ArrayList<String>();
		// 加入待分配状态
		statuses.add(Constant.ORDER_STATUS_WAIT_ALLOT);
		// 未分配列表
		List<OrderReminder> unassign = orderService
				.getUnassignOrderReminder(statuses);
		// 消息列表
		List<Message> messageList = new ArrayList<Message>();
		if (unassign.size() == 1) {
			// 订单提醒
			OrderReminder orderReminder = unassign.get(0);
			// 电子商务部—网络客服组DP08162、电话下单受理小组 DP00059
			Message message = null;
			// 部门
			Department dept = null;
			//
			String[] deptStandartdCodes = PropertiesUtil.getInstance()
					.getProperty("ElectronicBusinessDeptStandartdCodes")
					.split(",");
			for (String str : deptStandartdCodes) {
				message = new Message();
				// 设置任务数
				message.setTaskcount(orderReminder.getOrderQty());
				// 设置条件为待分配
				message.setConditions(Constant.ORDER_STATUS_WAIT_ALLOT);
				// 设置任务类型
				message.setTasktype(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_ORDER_UNASSIGN);
				// 部门
				dept = departmentService.getDeptByStandardCode(str);
				if (dept != null) {
					// 给消息设置部门ID
					message.setDeptId(Integer.parseInt(dept.getId()));
				}
				// 将消息加入消息列表
				messageList.add(message);
			}
		}
		// 返回消息列表
		return messageList;
	}

	/**
	 * 
	 * <p>
	 * Description:获得未受理订单消息<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:34:02
	 * @return List<Message>
	 * @update 2013-1-22下午4:34:02
	 */
	@Transactional
	private List<Message> getUnacceptOrderMessage() {
		// 状态列表
		List<String> statuses = new ArrayList<String>();
		// 状态列表加入未受理状态
		statuses.add(Constant.ORDER_STATUS_WAIT_ACCEPT);
		// 未受理 订单列表
		List<OrderReminder> unaccept = orderService
				.getUnacceptOrderReminder(statuses);
		// 消息列表
		List<Message> messageList = new ArrayList<Message>();
		for (int i = 0; i < unaccept.size(); i++) {
			// 订单提醒
			OrderReminder orderReminder = unaccept.get(i);
			// 消息
			Message message = new Message();
			// 设置任务类型
			message.setTasktype(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_ORDER_UNACCEPT);
			// 设置任务数
			message.setTaskcount(orderReminder.getOrderQty());
			// 设置条件为待受理
			message.setConditions(Constant.ORDER_STATUS_WAIT_ACCEPT);
			Integer deptId = null;
			// 验证订单通知的部门ID是否为空
			if (orderReminder.getDeptId() != null) {
				deptId = Integer.parseInt(orderReminder.getDeptId());
			}
			// 设置部门ID
			message.setDeptId(deptId);
			// 将消息加入消息列表
			messageList.add(message);
		}
		// 返回订单消息
		return messageList;
	}

	/**
	 * 
	 * <p>
	 * Description:获得已受理订单信息<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:35:04
	 * @return List<Message>
	 * @update 2013-1-22下午4:35:04
	 */
	@Transactional
	private List<Message> getAcceptedOrderMessage() {
		// 状态列表
		List<String> statuses = new ArrayList<String>();
		// 将已受理状态加入状态列表
		statuses.add(Constant.ORDER_STATUS_ACCEPT);
		// 已受理订单列表
		List<OrderReminder> accepted = orderService
				.getAcceptedOrderReminder(statuses);
		// 消息列表
		List<Message> messageList = new ArrayList<Message>();
		for (int i = 0; i < accepted.size(); i++) {
			// 订单提醒
			OrderReminder orderReminder = accepted.get(i);
			// 消息
			Message message = new Message();
			// 设置任务类型
			message.setTasktype(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_ORDER_ACCEPTED);
			// 设置任务数
			message.setTaskcount(orderReminder.getOrderQty());
			// 设置条件为订单已受理状态
			message.setConditions(Constant.ORDER_STATUS_ACCEPT);
			// 部门ID
			Integer deptId = null;
			// 验证订单提醒的部门ID是否为空
			if (orderReminder.getDeptId() != null) {
				deptId = Integer.parseInt(orderReminder.getDeptId());
			}
			// 设置部门ID
			message.setDeptId(deptId);
			// 将消息加入消息列表
			messageList.add(message);
		}
		// 返回消息列表
		return messageList;
	}

	/**
	 * 
	 * <p>
	 * Description:获得退回订单消息<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:35:32
	 * @return List<Message>
	 * @update 2013-1-22下午4:35:32
	 */
	@Transactional
	private List<Message> getGobackOrderMessage() {
		// 状态列表
		List<String> statuses = new ArrayList<String>();
		// 状态列表加入已退回状态
		statuses.add(Constant.ORDER_SATUTS_GOBACK);
		// 订单提醒列表
		List<OrderReminder> accepted = orderService
				.getAcceptedOrderReminder(statuses);
		// 消息列表
		List<Message> messageList = new ArrayList<Message>();
		for (int i = 0; i < accepted.size(); i++) {
			// 订单提醒
			OrderReminder orderReminder = accepted.get(i);
			// 消息
			Message message = new Message();
			// 设置任务类型为已退回
			message.setTasktype("ORDER_GOBACK");
			// 设置任务数
			message.setTaskcount(orderReminder.getOrderQty());
			// 设置条件为已退回状态
			message.setConditions(Constant.ORDER_SATUTS_GOBACK);
			// 部门ID
			Integer deptId = null;
			// 验证订单提醒的部门ID是否为空
			if (orderReminder.getDeptId() != null) {
				deptId = Integer.parseInt(orderReminder.getDeptId());
			}
			// 设置部门ID
			message.setDeptId(deptId);
			// 将消息加入消息列表
			messageList.add(message);
		}
		// 返回消息列表
		return messageList;
	}

	/**
	 * 
	 * <p>
	 * Description:通过运单号获得订单信息<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:35:51
	 * @param waybillNumber
	 * @return
	 * @update 2013-1-22下午4:35:51
	 */
	@Transactional(readOnly = true)
	@Override
	public Order getOrderWaybillNum(String waybillNumber) {
		// 根据运单号获取订单
		Order order = orderService.getOrderByWaybillNumber(waybillNumber);
		/*
		 * OrderView view = new OrderView(); view.setOrder(order);
		 */
		// 返回订单
		return order;
	}

	/**
	 * 
	 * <p>
	 * Description:通过名字获取车队部门信息<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:38:35
	 * @param bsc
	 * @return
	 * @update 2013-1-22下午4:38:35
	 */
	@Transactional(readOnly = true)
	@Override
	public List<BussinessDept> getDeptByName(BusDeptSearchCondition bsc) {
		// 根据条件查询出发部门
		List<BussinessDept> dpets = ladDeptManager.getLeaveBusDeptByName(bsc);
		// 返回出发部门
		return dpets;
	}

	/**
	 * 
	 * <p>
	 * Description:通过输入条件查询部门编码<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:43:23
	 * @param bsc
	 * @return
	 * @update 2013-1-22下午4:43:23
	 */
	@Transactional(readOnly = true)
	@Override
	public long getDeptNumberByName(BusDeptSearchCondition bsc) {
		return ladDeptManager.getBussinessNumber(bsc);
	}

	/**
	 * 
	 * <p>
	 * Description:更新渠道订单<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:44:33
	 * @param user
	 * @param order
	 * @param operContent
	 * @param operType
	 * @return
	 * @update 2013-1-22下午4:44:33
	 */
	@Transactional
	@Override
	public boolean updateChannelOrder(User user, Order order,
			String operContent, String operType,String standardCode) {
		// 生成操作日志
		OrderOperationLog log = OrderUtil.generateOrderOperationLog(order,
				operType, operContent, user);
		// 操作日志集合
		Set<OrderOperationLog> logs = new HashSet<OrderOperationLog>();
		// 将生成的操作日志加入到操作日志集合里面
		logs.add(log);
		// 设置订单操作日志集合
		order.setOperationLogs(logs);
		/**
		 * @description 接口调用，收入部门标杆编码，将订单的受理部门设置上
		 * @author 张斌
		 * @version 0.1 2013-8-9
		 * @param String String standardCode 部门标杆编码
		 */
		//begin
		Department dept = null;
		if(!StringUtils.isEmpty(standardCode)){//要去判断边干编码是否为空
			 dept = departmentService.getDeptByStandardCode(standardCode);// 根据标杆编码查询出部门
		}
		if(dept!=null){
			order.setAcceptDept(dept.getId());
			order.setAcceptDeptName(dept.getDeptName());
		}
		//end
		// 返回更新订单的结果
		return orderService.updateOrder(order) != null;
	}

	/***
	 * 
	 * <p>
	 * Description:通过订单号查询订单信息<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:48:41
	 * @param number
	 * @return
	 * @update 2013-1-22下午4:48:41
	 */
	@Transactional(readOnly = true)
	@Override
	public Order getOrderByNumber(String number) {
		return orderService.getOrderByOrderNumber(number);
	}

	/**
	 * 
	 * <p>
	 * Description:通过订单号判断是否渠道订单<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:49:07
	 * @param orderNumber
	 * @return
	 * @update 2013-1-22下午4:49:07
	 */
	@Transactional(readOnly = true)
	@Override
	public boolean isChannelOrderNumber(String orderNumber) {
		return orderValidator.isChannelNumber(orderNumber);
	}

	/**
	 * 
	 * <p>
	 * Description:获取订单列表通过客户ID<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:49:49
	 * @param custId
	 * @param startDate
	 * @param endDate
	 * @param start
	 * @param limit
	 * @return
	 * @update 2013-1-22下午4:49:49
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Order> getOrderListByCustId(String custId, Date startDate,
			Date endDate, int start, int limit) {
		return this.orderService.getOrderListByCustId(custId, startDate,
				endDate, start, limit);
	}

	/**
	 * 
	 * <p>
	 * Description:获取订单总数通过客户ID<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:52:25
	 * @param custId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @update 2013-1-22下午4:52:25
	 */
	@Transactional(readOnly = true)
	@Override
	public Long getOrderCountByCustId(String custId, Date startDate,
			Date endDate) {
		return this.orderService.getOrderCountByCustId(custId, startDate,
				endDate);
	}

	/**
	 * 
	 * <p>
	 * Description:创建潜在客户<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:53:13
	 * @param cust
	 * @update 2013-1-22下午4:53:13
	 */
	@Transactional
	@Override
	public void createPotentialCustomer(Member cust) {
		try {
		    memberManager.createPotentialMember(cust);
			// 抛出来的RuntimeException必须以Throwable catch
			// 用exception是catch不住滴。
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>
	 * Description:通过ERP ID获取订单信息<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:53:50
	 * @param erpId
	 * @return
	 * @update 2013-1-22下午4:53:50
	 */
	@Transactional
	@Override
	public List<Order> getOrderByErpId(String erpId) {
		return this.orderService.getOrderByErpId(erpId);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:56:10
	 * @param orderNum
	 * @return
	 * @update 2013-1-22下午4:56:10
	 */
	@Transactional
	@Override
	public Map<String, Object> getOrderAndRegInfoByOrderNum(String orderNum) {
		return this.orderService.getOrderAndRegInfoByOrderNum(orderNum);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-28上午10:49:21
	 * @param order
	 * @return
	 * @update 2013-1-28上午10:49:21
	 */
	@Transactional
	@Override
	public Boolean updateOrderNoValidate(Order order) {
		return this.orderService.updateOrder(order) != null ? true : false;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-28上午10:50:08
	 * @param orderNumber
	 * @return
	 * @update 2013-1-28上午10:50:08
	 */
	@Transactional
	@Override
	public Order getOrderWithLinkManCode(String orderNumber) {
		Order order = null;
		// 传入的订单长度为24位时，则为ERP订单中的ID
		if (orderNumber.length() == 28 || orderNumber.length() == 32) {
			// 订单列表
			List<Order> orderList = this.getOrderByErpId(orderNumber);
			// 验证订单列表是否存在数据
			if (orderList != null && orderList.size() > 0) {
				order = orderList.get(0);
			}
		} else {
			// 如果订单号的长度不是28或者32时，按照订单号获取订单
			order = this.getOrderByNumber(orderNumber);
		}
		// 验证订单是否为空
		if (order == null) {
			// 如果为空，按照渠道号查询订单信息
			order = this.queryOrderByChannelNumber(orderNumber);
		}
		// 返回订单
		return order;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-28上午10:50:15
	 * @param createStartDate
	 * @param createEndDate
	 * @return
	 * @update 2013-1-28上午10:50:15
	 */
	@Transactional
	@Override
	public Map<String, Object> orderStatusMonitoring(Date createStartDate,
			Date createEndDate) {
		// 0
		logger.debug("orderStatusMonitoring 0 args：" + createStartDate + "、"
				+ createEndDate);

		Map<String, Object> map = new HashMap<String, Object>();

		// 1公用条件
		OrderSearchCondition osc = new OrderSearchCondition();
		@SuppressWarnings("deprecation")
		Date d = new Date(112, 6, 28);
		if (createStartDate != null
				&& createStartDate.getTime() - d.getTime() > 0) {
			osc.setStartDate(createStartDate);
		} else {
			osc.setStartDate(d);
		}
		logger.debug("orderStatusMonitoring 1 args：" + createStartDate + "、"
				+ createEndDate);

		/*
		 * 2.1阿里
		 */
		osc.setResource(Constant.ORDER_SOURCE_ALIBABA);
		// 调用接口
		// 3.1
		map.putAll(this.withChannelOrderCompare(osc, null));

		/*
		 * 2.2 400
		 */
		osc.setResource(Constant.ORDER_SOURCE_CALLCENTER);
		// 调用接口
		// 3.2
		map.putAll(this.withChannelOrderCompare(osc, null));

		/*
		 * 2.3商城
		 */
		osc.setResource(Constant.ORDER_SOURCE_SHANGCHENG);
		// 调用接口
		// 3.3
		map.putAll(this.withChannelOrderCompare(osc, null));

		/*
		 * 2.4淘宝
		 */
		osc.setResource(Constant.ORDER_SOURCE_TAOBAO);
		// 调用接口
		// 3.4
		map.putAll(this.withChannelOrderCompare(osc, null));

		/*
		 * 2.5友商
		 */
		osc.setResource(Constant.ORDER_SOURCE_YOUSHANG);
		// 调用接口
		// 3.5
		map.putAll(this.withChannelOrderCompare(osc, null));

		// QQ速递
		osc.setResource(Constant.ORDER_SOURCE_QQSUDI); // 调用接口
		map.putAll(this.withChannelOrderCompare(osc, null));

		return map;
	}

	/**
	 * 
	 * <p>
	 * Description:渠道订单比较<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-1-22下午4:56:38
	 * @param osc
	 * @param orderList
	 * @return Map<String,Object>
	 * @update 2013-1-22下午4:56:38
	 */
	@Transactional
	private Map<String, Object> withChannelOrderCompare(
			OrderSearchCondition osc, List<Order> orderList) {
		Map<String, Object> map = new HashMap<String, Object>();

		// 订单列表
		List<Order> list = orderService.searchOrders(osc);
		// 订单来源
		String resource = osc.getResource();
		// 验证是否存在订单(按照输入条件获取)
		if (list != null && list.size() > 0) {
			map.put(resource + "OrderListSize0", list.size());
			map.put(resource + "OrderList0", list);
			logger.debug("orderStatusMonitoring 3 args：" + resource
					+ "OrderListSize0、" + list.size() + "、" + list.toString());

			// 验证是否存在订单(参数传的值)
			if (orderList != null && orderList.size() > 0) {
				map.put(resource + "OrderListSize1", orderList.size());
				map.put(resource + "OrderList1", orderList);
				logger.debug("orderStatusMonitoring 3 args：" + resource
						+ "OrderListSize1、" + orderList.size() + "、"
						+ orderList.toString());

				// 4
				List<Order> difOrderList = new ArrayList<Order>();
				for (Order order0 : list) {
					for (Order order1 : orderList) {
						/*
						 * CRM-3171 删除无效判断 author By 许华龙
						 */
						difOrderList.add(order0);
						difOrderList.add(order1);
						break;

					}
				}// for end
				map.put(resource + "OrderListSize", difOrderList.size());
				map.put(resource + "OrderList", difOrderList);
				logger.debug("orderStatusMonitoring 4 args：" + resource
						+ "OrderListSize、" + difOrderList.size() + "、"
						+ difOrderList.toString());
			}
		}
		return map;
	}

	/**
	 * @description 使用部门为营业部时，省份、城市、区县默认为登录者部门所在地<br/>
	 * @author 钟琼
	 * @version 0.1 2012-10-10
	 */
	@Transactional
	@Override
	public Map<String, String> searchInitReceiveGoodsAddress(BussinessDept dept) {
		Map<String, String> map = new HashMap<String, String>();

		if (dept != null && dept.getDeptName() != null
				&& !"".equals(dept.getDeptName())) {// 部门不能为空且部门名字不能为空
			String provinceName = dept.getProvince().getName(); // 营业部所在省份名
			String cityName = dept.getCity().getName(); // 营业部所在城市名
			String regionName = dept.getRegion().getName(); // 营业部所在区域名
			String deptAddress = dept.getDeptAddress(); // 营业部所在详细地址

			map.put("provinceName", provinceName);
			map.put("cityName", cityName);
			map.put("regionName", regionName);
			map.put("deptAddress", deptAddress);
		}
		return map;
	}

	/**
	 * @description 输入手机号，光标离开输入栏后即根据手机号查找客户信息，<br/>
	 *              若已存在此固定客户，将客户信息（客户编码、联系人、客户名称、电话、地址、<br/>
	 *              始发网点（最近一次成功发货网点，使用部门为营业部时，始发网点默认为本部门）自动带出；<br/>
	 * @author 钟琼
	 * @version 0.1 2012-10-11
	 */
	@Transactional
	@Override
	public Map<String, Object> searchMemberInfoByPhone(String phone, User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		Member member = null;
		BussinessDept busDept = null;
		if (!StringUtils.isEmpty(phone)) {// 参数 phone 不为空的话
			member = customerManager.getMemberByPhone(phone); // 根据参数 phone
																// 查出联系人详细信息
		}
		if (!ValidateUtil.objectIsEmpty(member)) { // 联系人不为空的话
			// 根据登陆用户的部门id 得到该部门的详细信息
			String id = user.getEmpCode().getDeptId().getId();
			BussinessDept dept = createOrderInit(id);
			if (dept != null) { // 部门不为空话 就是营业部
				busDept = dept; // 把营业部作为 始发网点赋值给 busDept
			} else {// 部门为空的话 不是营业部
				String deptId = orderService.searchStartStationByPhone(phone);// 根据参数phone查出该联系人最近一次成功发货网点id
				if (!StringUtils.isEmpty(deptId)) {
					busDept = ladDeptManager.getBusDeptByDeptId(deptId);// 根据id查出部门详细信息
				}
			}
			map.put("member", member);
			map.put("busDept", busDept);
		}
		return map;
	}

	/**
	 * @description 订单受理时双击订单可以备注信息功能新增
	 * @author 钟琼
	 * @version 0.1 2012-10-12
	 */
	@Transactional
	@Override
	public void saveOrderRemarkInfo(OrderOperationLog log, User user) {
		// 从uer得到 操作人 格式：登陆员工用户名(登陆员工工号)
		String operatorId = user.getEmpCode().getEmpName() + "("
				+ user.getEmpCode().getEmpCode() + ")";
		log.setOperatorId(operatorId);
		// 从user得到 登陆员工 部门
		log.setOperatorOrgId(user.getEmpCode().getDeptId().getDeptName());
		// 设置oprationType
		log.setOperatorType(Constant.ORDER_OPERATION_REMARK);// 操作类型为"REMARK"
		// 设置操作时间
		log.setOperatorTime(new Date());
		// 校验，不通过，就抛出异常
		orderValidator.checkOpeLogIsNull(log);
		orderService.saveOrderOperationLog(log);
	}
	@Transactional
	@Override
	public boolean isContractMember(String memberId, String custNumber) {
		return memberManager.isContractMemberByIdOrCustNumber(memberId,
				custNumber);
	}


	/**
	 * <p>
	 * Description:memberManager<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-4-20
	 */
	public IMemberManager getMemberManager() {
		return memberManager;
	}

	/**
	 * <p>
	 * Description:memberManager<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-4-20
	 */
	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}

	/**
	 * 
	 * @Title: sendFossOrderRemaidInfo
	 * @Description: 发给FOSS订单待受理订单信息
	 * @return void 返回类型
	 */
	@Override
	public List<ResultDetal> sendFossOrderAcceptInfo() {
		// 查询待受理订单统计参数
		OrderAcceptConfig oac = orderService.getOrderAcceptConfig();
		// 增加待受理订单部门
		orderService.addOrderAcceptDept();
		// 统计待受理订单的部门和订单数量
		List<OrderAcceptInfo> oaiList = orderService
				.generateOrderAcceptInfo(oac);
		// 发送待受理订单的部门和订单数量给FOSS
		return orderService.sendFossOrderAcceptInfo(oaiList);
	}

	/**
	 * 
	 * @Title: getOrderAcceptInfoByDept
	 * @Description: 查询部门的订单待受理订单信息
	 * @return OrderAcceptInfo 订单待受理订单信息
	 */
	@Override
	public OrderAcceptInfo getOrderAcceptInfoByDept(String standardCode) {
		// 查询待受理订单统计参数
		OrderAcceptConfig oac = orderService.getOrderAcceptConfig();
		// 增加待受理订单部门
		orderService.addOrderAcceptDept();
		// 查询制定部门的待受理订单数量
		OrderAcceptInfo aoi = orderService.getOrderAcceptInfoByDept(oac,
				standardCode);
		if (aoi == null) {
			aoi = new OrderAcceptInfo();
			aoi.setStandardCode(standardCode);
			aoi.setWarnNum(0);
			aoi.setLockNum(0);
		}
		// 返回
		return aoi;
	}
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
	@Transactional
	@Override
	public AppointmentCarInfo createPilotOrder(Order order) {
		AppointmentCarInfo appointmentCarInfo = new AppointmentCarInfo();
/*		修改人：张斌
		修改时间：2013-7-30
		修改内容：无
		修改原因：设置订单的状态为已约车，防止接口没有设置*/
		order.setOrderStatus(Constant.ORDER_SATUTS_SHOUTCAR);
		Order o = createOrder(order);
		/*		修改人：张斌
		修改时间：2013-7-30
		修改内容：// appointmentCarInfo.setCreatorNum();//当前操作人工号
		appointmentCarInfo.setCubage(new BigDecimal(o.getTotalVolume()));
		appointmentCarInfo.setCustAddress(o.getContactAddress());
		appointmentCarInfo.setCustArea(o.getContactArea());
		appointmentCarInfo.setCustCity(o.getContactCity());
		appointmentCarInfo.setCustMobile(o.getContactMobile());
		appointmentCarInfo.setCustName(o.getContactName());
		appointmentCarInfo.setCustProvince(o.getContactProvince());
		appointmentCarInfo.setCustTel(o.getContactPhone());
		appointmentCarInfo.setGoodsName(o.getGoodsName());
		appointmentCarInfo.setGoodsType(o.getGoodsType());
		appointmentCarInfo.setOrderedTime(o.getOrderTime());
		appointmentCarInfo.setOrderNumber(o.getOrderNumber());
		// appointmentCarInfo.setOrderType();
		appointmentCarInfo.setPacking(o.getPacking());
		// appointmentCarInfo.setRemark(remark);
		appointmentCarInfo.setPieces(o.getGoodsNumber());
		// appointmentCarInfo.setSize(size);
		appointmentCarInfo.setTransProperty(o.getTransportMode());
		appointmentCarInfo.setWeight(new BigDecimal(o.getTotalWeight()));
		appointmentCarInfo.setDeliverMode(o.getDeliveryMode());
		修改原因：用原有的转换方式转换*/
		appointmentCarInfo = OrderUtil.produceERPOrder(order,null);
		return appointmentCarInfo;
	}
	
	/**
	 * <p>
	 * Description:创建订单<br />
	 * </p>
	 * 
	 * @author 张斌
	 * @param order
	 * @return Order
	 * @version 0.1 2013-7-30
	 */
	@Transactional
	@Override
	public Order createAllOrder(Order order) {
		if (pilotCityManager.checkIsPilotCityOrder(order.getContactCity(),
				order.getTransportMode())) {
			String bindWaybillNum = order.getWaybillNumber();
			AppointmentCarInfo appointmentCarInfo = createPilotOrder(order);
			try {
				if (StringUtils.isNotEmpty(bindWaybillNum)) {
					return order;
				} else {
					orderOperate.appointmentCar(appointmentCarInfo);
				}
			} catch (CrmBusinessException e) {
				// 抛出异常
				throw new GeneralException(e.getMessage(), e.getErrorCode(), e,
						new Object[] {}) {
					private static final long serialVersionUID = 1L;
				};
			}
			return order;
		}
		/*		修改人：张斌
		修改时间：2013-7-31
		修改内容：无
		修改原因：如果创建的是非试点城市快递订单，抛异常，不被允许创建
		状态*/
		else if(Constant.ORDER_TRANSTYPE_AGENT_PACKAGE.equals(order.getTransportMode())){
			// 定义异常 “非试点城市快递订单不允许创建”
			OrderException e1 = new OrderException(
					OrderExceptionType.NOTPILOTCITY_CREATE_NOT);
			// 抛出异常
			throw new GeneralException(e1.getErrorCode(), e1.getMessage(),
					e1, new Object[] {}) {

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;
			};
		}
		// 5、维护受理部门和状态
		/*		修改人：kuang
		修改时间：2013-7-30
		修改内容：无
		修改原因：如果是试点城市的快递，则验证受理部门和维护
		状态*/
		else{
			return createOrder(order);
		}
	}
	
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
	@Override
	public Order createAllOrderInterface(Order order) {
		if (pilotCityManager.checkIsPilotCityOrder(order.getContactCity(),
				order.getTransportMode())) {
			String bindWaybillNum = order.getWaybillNumber();
			AppointmentCarInfo appointmentCarInfo = createPilotOrder(order);
			try {
				if (StringUtils.isNotEmpty(bindWaybillNum)) {
					return order;
				} else {
					orderOperate.appointmentCar(appointmentCarInfo);
				}
			} catch (CrmBusinessException e) {
				// 抛出异常
				throw new GeneralException(e.getMessage(), e.getErrorCode(), e,
						new Object[] {}) {
					private static final long serialVersionUID = 1L;
				};
			}

			return order;
		} else {
			return createOrder(order);
		}
	}
	/**
	 * 
	 * @description 延迟订单.
	 * @author 匡永琴
	 * @version 0.1 2013-9-3
	 * @param OrderView
	 * @return Order
	 */
	@Transactional
	@Override
	public Order delayOrder(String id, User user, String feedbackInfo,Date delayOrderTime) {

		// 订单对象
		Order order = orderService.getOrderById(id);
		// 订单来源
		String orderSource = order.getResource();// 订单来源
		// 订单延迟前状态
		String orderStatus_old = order.getOrderStatus();
		// 订单延迟后状态
		String orderStatus_new = Constant.ORDER_STATUS_DELAY;// 已延迟

		// 1、验证订单状态是否为待受理、已受理
		// 2、反馈信息是否填写
		// 验证是否为呼叫中心订单
		// 若果不是渠道订单，则需要验证订单的来源
		// 4、延迟订单成功，向订单受部门反馈延迟信息，订单状态改为已延迟；
		if (orderValidator.validateOrderDelayable(order,user)
				&& orderValidator.isFeedbackInfoNotNull(feedbackInfo)) {
			order.setOrderStatus(orderStatus_new);// 已撤消
			order.setFeedbackInfo(feedbackInfo);
			order.setDelayTime(delayOrderTime);
			// 5、记录操作记录
			Set<OrderOperationLog> logs = new HashSet<OrderOperationLog>();
			// 日志主体内容
			String content = MessageFormat
					.format(Constant.ORDER_OPERATE_LOG,
							new Object[] {
									Constant.ORDER_STATUS,
									OrderUtil
											.getCHNOrderStatusByENStatus(orderStatus_old),
									OrderUtil
											.getCHNOrderStatusByENStatus(orderStatus_new) });
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//延迟的时间
			String timeString= Constant.ORDER_DELAYTIME+formatter.format(delayOrderTime);
			content = content + feedbackInfo+timeString;

			// 操作日志
			OrderOperationLog ool = OrderUtil.generateOrderOperationLog(order,
					Constant.ORDER_OPERATION_DELAY, content, user);
			logs.add(ool);
			// 设置操作日志
			order.setOperationLogs(logs);
			// 更新订单
			order = this.orderService.updateOrder(order);
			// 返回订单
			return order;
		}
		return null;	
	}

	@Transactional
	@Override
	public void syncOrderStatus(String orderId, String orderStatus) {
		Order order = orderService.getOrderById(orderId);
		order.setOrderStatus(orderStatus);
		// 是否续签同步状态到其它系统
		if (orderValidator.validateOrderNeedSync(orderStatus,
				order.getResource())) {
			orderService.syncOrderStatus(order);
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:订单延迟状态自动转化成“待受理”状态<br />
	 * </p>
	 * 
	 * @author kuang
	 * @version 0.1 2013-9-6 
	 * @return
	 */
	@Override
	@Transactional
	public void updateDelayToUnaccept() {
			orderService.updateDelayToUnaccept(Constant.ORDER_STATUS_DELAY,Constant.ORDER_STATUS_WAIT_ACCEPT);	
	}

	/**
	 * 
	 * <p>
	 * Description:去掉订单中可能存在的特殊字符<br />
	 * </p>
	 * 
	 * @author zhangbin
	 * @version 0.1 2013-9-6 
	 * @return Order
	 */
	private Order replaceSpecialChar(Order order){
		//货物名称
		if(!StringUtil.isEmpty(order.getGoodsName())){
			order.setGoodsName(order.getGoodsName().replaceAll(Constant.SPECIAL_2029,""));
			order.setGoodsName(order.getGoodsName().replaceAll(Constant.SPECIAL_2028,""));
		}
		//储运事项
		if(!StringUtil.isEmpty(order.getTransNote())){
			order.setTransNote(order.getTransNote().replaceAll(Constant.SPECIAL_2029,""));
			order.setTransNote(order.getTransNote().replaceAll(Constant.SPECIAL_2028,""));
		}
		//包装材料
		if(!StringUtil.isEmpty(order.getPacking())){
			order.setPacking(order.getPacking().replaceAll(Constant.SPECIAL_2029,""));
			order.setPacking(order.getPacking().replaceAll(Constant.SPECIAL_2028,""));
		}
		//发货联系人姓名
		if(!StringUtil.isEmpty(order.getContactName())){
			order.setContactName(order.getContactName().replaceAll(Constant.SPECIAL_2029,""));
			order.setContactName(order.getContactName().replaceAll(Constant.SPECIAL_2028,""));
		}
		//发货客户名称
		if(!StringUtil.isEmpty(order.getShipperName())){
			order.setShipperName(order.getShipperName().replaceAll(Constant.SPECIAL_2029,""));
			order.setShipperName(order.getShipperName().replaceAll(Constant.SPECIAL_2028,""));
		}
		//发货地址
		if(!StringUtil.isEmpty(order.getContactAddress())){
			order.setContactAddress(order.getContactAddress().replaceAll(Constant.SPECIAL_2029,""));
			order.setContactAddress(order.getContactAddress().replaceAll(Constant.SPECIAL_2028,""));
		}
		//发货联系人姓名
		if(!StringUtil.isEmpty(order.getReceiverCustName())){
			order.setReceiverCustName(order.getReceiverCustName().replaceAll(Constant.SPECIAL_2029,""));
			order.setReceiverCustName(order.getReceiverCustName().replaceAll(Constant.SPECIAL_2028,""));
		}
		//收货客户名称
		if(!StringUtil.isEmpty(order.getReceiverCustcompany())){
			order.setReceiverCustcompany(order.getReceiverCustcompany().replaceAll(Constant.SPECIAL_2029,""));
			order.setReceiverCustcompany(order.getReceiverCustcompany().replaceAll(Constant.SPECIAL_2028,""));
		}
		//收货地址
		if(!StringUtil.isEmpty(order.getReceiverCustAddress())){
			order.setReceiverCustAddress(order.getReceiverCustAddress().replaceAll(Constant.SPECIAL_2029,""));
			order.setReceiverCustAddress(order.getReceiverCustAddress().replaceAll(Constant.SPECIAL_2028,""));
		}
		return order;
	}
	
	/**
	 * @description 通过渠道单号修改运单号.
	 * @author 黄展明
	 * @version 0.1 2013-11-27
	 * @param channelNum
	 * @param waybillNum
	 * @throws CrmBusinessException
	 */
	@Override
	@Transactional
	public boolean updateWaybillNumByChannel(String channelNum,
			String waybillNum) throws CrmBusinessException {
		// 判断输入参数是否有效
		if (null == channelNum || null == waybillNum || "".equals(channelNum)
				|| "".equals(waybillNum)) {
			return false;
		}
		// 运单信息
		FossWaybillInfo waybill = null;
		// 待更新状态
		// String orderStatus = Constant.ORDER_SATUTS_FAILGOT;
		// 反馈信息
		String message = "";
		// 根据渠道订单查询订单
		Order channelNumOrder = orderService
				.queryOrderByChannelNumber(channelNum);
		if (null != channelNumOrder) {
			if (null != channelNumOrder.getWaybillNumber()
					&& waybillNum.equals(channelNumOrder.getWaybillNumber())) {
				return true;
			}
		} else {
			return false;
		}
		// 根据运单号查询订单
		Order waybillNumOrder = orderService
				.getOrderByWaybillNumber(waybillNum);
		if (null != waybillNumOrder) {
			// 反馈信息
			message = "该运单号已被其他订单号关联";
			channelNumOrder.setFeedbackInfo(message);
			// 更新对应渠道订单状态为揽货失败，并清空运单号
			updateOrderAndLog(channelNumOrder, "",
					Constant.ORDER_SATUTS_FAILGOT);
//			orderService.linkOrderWaybill(channelNumOrder.getOrderNumber(),
//					null, channelNumOrder.getWaybillNumber());
			return false;
		}
		try {
			// 根据运单号查询运单信息
			waybill = orderService.queryFossWaybillInfo(waybillNum);
		} catch (CrmBusinessException e) {
			// 如有异常，运单为null
			waybill = null;
		}
		if (null == waybill) {
			// 反馈信息
			message = "无效运单号，关联失败";
			channelNumOrder.setFeedbackInfo(message);
			// 更新对应渠道订单状态为揽货失败，并清空运单号
			updateOrderAndLog(channelNumOrder, "",
					Constant.ORDER_SATUTS_FAILGOT);
//			orderService.linkOrderWaybill(channelNumOrder.getOrderNumber(),
//					null, channelNumOrder.getWaybillNumber());
			return false;
		} else {
			// 更新对应渠道订单状态为对应订单，并赋予对应的运单号
			String orderStatus = transWaybillStatusToOrderStatus(waybill
					.getOrderState());
			// 比较收货人联系人信息
			if (!orderStatus.equals(Constant.ORDER_SATUTS_FAILGOT)
					&& checkPhoneAndMobile(
							channelNumOrder.getReceiverCustName(),
							channelNumOrder.getReceiverCustPhone(),
							channelNumOrder.getReceiverCustMobile(),
							channelNumOrder.getTransportMode(),
							waybill.getConsignee(),
							waybill.getConsigneePhone(),
							waybill.getConsigneeMobile(), waybill.getTranType())) {
				// 反馈信息
				message = "线下订单";
				channelNumOrder.setFeedbackInfo(message);
				// 出发部门
				Department dept = departmentService
						.getDeptByStandardCode(waybill.getDepartureDeptNumber());
				channelNumOrder.setAcceptDept(dept.getId());
				updateOrderAndLog(channelNumOrder, waybillNum, orderStatus);
				orderService.linkOrderWaybill(channelNumOrder.getOrderNumber(),
						waybillNum, channelNumOrder.getWaybillNumber());
				return true;
			} else {
				// 反馈信息
				if (orderStatus.equals(Constant.ORDER_SATUTS_FAILGOT)) {
					message = "无效运单号，关联失败";
				} else {
					message = "订运单信息不一致，关联失败";
				}
				channelNumOrder.setFeedbackInfo(message);
				updateOrderAndLog(channelNumOrder, "",
						Constant.ORDER_SATUTS_FAILGOT);
//				orderService.linkOrderWaybill(channelNumOrder.getOrderNumber(),
//						null, channelNumOrder.getWaybillNumber());
				return false;
			}
		}
	}

	/**
	 * @description 订单和运单的联系人信息和运输方式比较
	 * @version 0.1 2013-11-27
	 * @param orderConsignee
	 * @param orderPhone
	 * @param orderMobile
	 * @param orderTrans
	 * @param waybillConsignee
	 * @param waybillPhone
	 * @param waybillMobile
	 * @param waybillTrans
	 * @return
	 */
	public boolean checkPhoneAndMobile(String orderConsignee,
			String orderPhone, String orderMobile, String orderTrans,
			String waybillConsignee, String waybillPhone, String waybillMobile,
			String waybillTrans) {
		orderConsignee = null == orderConsignee ? "" : orderConsignee.trim();
		orderPhone = null == orderPhone ? "" : orderPhone.trim();
		orderMobile = null == orderMobile ? "" : orderMobile.trim();
		waybillConsignee = null == waybillConsignee ? "" : waybillConsignee
				.trim();
		waybillPhone = null == waybillPhone ? "" : waybillPhone.trim();
		waybillMobile = null == waybillMobile ? "" : waybillMobile.trim();
		orderTrans = null == orderTrans ? "" : orderTrans.trim();
		waybillTrans = null == waybillTrans ? "" : waybillTrans.trim();
		// 电话比较
		boolean isSamePhone = false;
		if (StringUtils.isNotEmpty(orderPhone)) {
			if (orderPhone.equals(waybillPhone)
					|| orderPhone.equals(waybillMobile)) {
				isSamePhone = true;
			}
		} 
		if (StringUtils.isNotEmpty(orderMobile)) {
			if (orderMobile.equals(waybillPhone)
					|| orderMobile.equals(waybillMobile)) {
				isSamePhone = true;
			}
		}
		// 运输方式比较
		boolean isSameTrans = false;
		if (orderTrans.equals(Constant.ORDER_TRANSTYPE_AGENT_PACKAGE)
				&& waybillTrans.equals(Constant.WAYBILL_TRANS_EXPRESS)) {
			isSameTrans = true;
		}
		if (!orderTrans.equals(Constant.ORDER_TRANSTYPE_AGENT_PACKAGE)
				&& !waybillTrans.equals(Constant.WAYBILL_TRANS_EXPRESS)) {
			isSameTrans = true;
		}
		// 联系人姓名比较
		if (isSamePhone && isSameTrans
				&& StringUtils.isNotEmpty(orderConsignee)
				&& orderConsignee.equals(waybillConsignee)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @description 修改运单、状态和生成操作日志
	 * @version 0.1 2013-11-27
	 * @param channelNumOrder
	 * @param waybillNum
	 * @param orderStatus
	 */
	public void updateOrderAndLog(Order channelNumOrder, String waybillNum,
			String orderStatus) {
		// 生成调用User对象
		User user = generateOperationUser(channelNumOrder.getResource());
		// 修改订单状态
		channelNumOrder.setOrderStatus(orderStatus);
		// 修改订单的运单号
		channelNumOrder.setWaybillNumber(waybillNum);
		if (null == waybillNum) {
			waybillNum = "";
		}
		// 订单更改信息
		String content = "运单号变为：" + waybillNum + "状态变为："
				+ OrderUtil.getCHNOrderStatusByENStatus(orderStatus);
		// 产生操作日志
		OrderOperationLog log = OrderUtil
				.generateOrderOperationLog(channelNumOrder,
						Constant.ORDER_OPERATION_UPDATE, content, user);
		Set<OrderOperationLog> logs = new HashSet<OrderOperationLog>();
		logs.add(log);
		channelNumOrder.setOperationLogs(logs);

		orderService.updateOrder(channelNumOrder);
	}

	/**
	 * @description 生成后台操作用户
	 * @version 0.1 2013-11-27
	 * @param resource
	 * @return
	 */
	public User generateOperationUser(String resource) {
		// 初始化用户信息，所有接口
		User user = new User();
		user.setId("DP0000");
		user.setLoginName("DP000");
		Department dept = new Department();
		dept.setDeptCode("渠道客户");
		Employee e = new Employee();
		e.setDeptId(dept);
		e.setEmpCode("DP0000");
		e.setEmpName(resource);
		user.setEmpCode(e);
		return user;
	}

	/**
	 * @description 运单状态转化为订单状态
	 * @version 0.1 2013-11-27
	 * @param waybillStatus
	 * @return
	 */
	public String transWaybillStatusToOrderStatus(String waybillStatus) {
		// 运单状态和订单状态转换
		String orderStauts = "";
		if ("EFFECTIVE".equals(waybillStatus)) {// 已开单收货
			orderStauts = Constant.ORDER_STATUS_GOT;
		} else if ("STATION_OUT".equals(waybillStatus)) {// 运输途中
			orderStauts = Constant.ORDER_STATUS_IN_TRANSIT;
		} else if ("TFR_IN".equals(waybillStatus)) {// 运输途中
			orderStauts = Constant.ORDER_STATUS_IN_TRANSIT;
		} else if ("TFR_STOCK".equals(waybillStatus)) {// 运输途中
			orderStauts = Constant.ORDER_STATUS_IN_TRANSIT;
		} else if ("TFR_OUT".equals(waybillStatus)) {// 运输途中
			orderStauts = Constant.ORDER_STATUS_IN_TRANSIT;
		} else if ("STATION_IN".equals(waybillStatus)) {// 已到达
			orderStauts = Constant.ORDER_STATUS_IN_TRANSIT;
		} else if ("STATION_STOCK".equals(waybillStatus)) {// 运输途中
			orderStauts = Constant.ORDER_STATUS_IN_TRANSIT;
		} else if ("DELIVERING".equals(waybillStatus)) {// 派送中
			orderStauts = Constant.ORDER_STATUS_IN_TRANSIT;
		} else if ("NORMAL_SIGN".equals(waybillStatus)) {// 正常签收
			orderStauts = Constant.ORDER_STATUS_SINGSUCCESS;
		} else if ("UNNORMAL_SIGN".equals(waybillStatus)) {// 异常签收
			orderStauts = Constant.ORDER_STATUS_SIGNFAIL;
		} else if ("ABORTED".equals(waybillStatus)) {// ABORT
			orderStauts = Constant.ORDER_SATUTS_FAILGOT;
		} else if ("OBSOLETE".equals(waybillStatus)) {// CANCEL
			orderStauts = Constant.ORDER_SATUTS_FAILGOT;
		} else {
			orderStauts = Constant.ORDER_SATUTS_FAILGOT;
		}
		return orderStauts;
	}
}

