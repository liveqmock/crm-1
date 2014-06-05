package com.deppon.crm.module.interfaces.order.impl;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam;
import javax.xml.ws.Holder;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.Check;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.crm.module.complaint.server.manager.IComplaintManager;
import com.deppon.crm.module.client.customer.ICustomerOperate;
import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.client.order.IOrderOperate;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.IContactManager;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.interfaces.common.util.IntefacesTool;
import com.deppon.crm.module.interfaces.order.IBHOOrderService;
import com.deppon.crm.module.interfaces.order.IEBMOrderService;
import com.deppon.crm.module.interfaces.order.IERPOrderService;
import com.deppon.crm.module.interfaces.order.IESBOrderService;
import com.deppon.crm.module.interfaces.order.domain.ComplaintOrderBindRequest;
import com.deppon.crm.module.interfaces.order.domain.ComplaintOrderBindResponse;
import com.deppon.crm.module.interfaces.order.domain.OrderQueryCondition;
import com.deppon.crm.module.interfaces.order.domain.OrderStatusInfo;
import com.deppon.crm.module.order.server.manager.IOrderManager;
import com.deppon.crm.module.order.server.service.IOrderService;
import com.deppon.crm.module.interfaces.order.domain.Order;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.crm.CancelOrderResponse;
import com.deppon.foss.framework.exception.GeneralException;

public class OrderServiceImpl implements IBHOOrderService, IEBMOrderService,
		IERPOrderService, IESBOrderService {
	// 订单mamager
	IOrderManager orderManager;
	// 部门服务
	IDepartmentService departmentService;
	// 会员manager
	IAlterMemberManager alterMemberManager;
	// 订单service
	IOrderService orderService;
	// 员工模块
	IUserService userService;
	//用户联系人绑定解绑模块
	IContactManager contactManager;
	//根据用户id获取逛网注册用户信息
	ICustomerOperate customerOperate;
	//工单投诉
	private IComplaintManager complaintManager;
	//订单接口
	private IOrderOperate orderOperate;
	

	/**
	 * @作者：罗典
	 * @时间：2012-6-29
	 * @描述：官网绑定淘宝商城ID修改此客户相关的订单信息
	 * @参数：订单信息(只包含部分发货人信息)
	 * @返回值：影响行数
	 * */
	public int bindShangchengId(Order interfaceOrder) throws CrmBusinessException {
		IntefacesTool.validateNull(interfaceOrder, "0010",interfaceOrder); // 订单信息为空
		IntefacesTool.validateStringNull(interfaceOrder.getChannelCustId(), "0019",interfaceOrder);// 用户名为空
		IntefacesTool.validateStringNull(interfaceOrder.getOrderPerson(), "0032",interfaceOrder);// 商城客户ID为空
		if (interfaceOrder.getStartStation() != null) {
			// 将传输过来的始发部门编码转换为始发部门ID存入订单信息中
			Department dept = departmentService.getDeptByStandardCode(interfaceOrder
					.getStartStation());
			IntefacesTool.validateNull(dept,"0025", interfaceOrder);//部门为空
			interfaceOrder.setStartStation(dept.getId());
		}
		return orderService.updateOrderByOrderPerson(IntefacesTool.interfaceOrderToOrder(interfaceOrder));
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-13
	 * @描述：更新订单状态
	 * @参数：orderStateInfo 参数信息
	 * @返回值：是否成功
	 * */

	public boolean updateOrderStatus(OrderStatusInfo orderStateInfo)
			throws CrmBusinessException {
		/*// 订单编号(或ERP订单ID)
		String orderNumber = orderStateInfo.getOrderNumber();
		// 验证订单为空
		IntefacesTool.validateStringNull(orderNumber, "0016",orderNumber);
		// 验证操作部门为空
		IntefacesTool.validateStringNull(orderStateInfo.getProcessDept(), "0023");
		// 验证操作人为空
		IntefacesTool.validateStringNull(orderStateInfo.getProcessPerson(), "0019");
		Order dpOrder = null;
		// 传入的订单长度为24位时，则为ERP订单中的ID
		//加入try，如果調用orderManager业务逻辑出错ERP就不需要再次重发
		try {
			if (orderNumber.length() == 28 || orderNumber.length() == 32) {
				List<Order> orderList = orderManager
						.getOrderByErpId(orderNumber);
				if (orderList != null && orderList.size() > 0) {
					dpOrder = orderList.get(0);
				}
			} else {
				dpOrder = orderManager.getOrderByNumber(orderNumber);
			}
		} catch (Exception e) {
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_DYNAMIC,
					e);
		}
		IntefacesTool.validateStringNull(dpOrder, "0012",orderNumber);
		// 需修改的状态
		String newStatus = IntefacesTool.getOrderStatus(orderStateInfo
				.getCrmOrderState());
		// 订单当前状态
		String oldStatus = dpOrder.getOrderStatus();
		// 作为参数传入订单模块订单
		Order paramOrder = new Order();
		paramOrder.setId(dpOrder.getId());
		paramOrder.setContactName(dpOrder.getContactName());
		paramOrder.setContactMobile(dpOrder.getContactMobile());
		// 获取用户信息
		User user = new User();
		user.setLoginName(orderStateInfo.getProcessPerson());
		List<User> userList = userService.queryAll(user);
		Employee employee = new Employee();
		if (userList.size() > 0) {
			user = userList.get(0);
			employee = user.getEmpCode();
		} else {
			employee.setEmpCode(orderStateInfo.getProcessPerson());
			employee.setEmpName(orderStateInfo.getProcessPerson());
		}
		Department dept = departmentService
				.getDeptByStandardCode(orderStateInfo.getProcessDept());
		if (dept == null) {
			dept = new Department();
			dept.setDeptName(orderStateInfo.getProcessDept());
		}
		employee.setDeptId(dept);
		user.setEmpCode(employee);
		String errorDesc = "["+orderNumber+IntefacesTool.getOrderStatusDesc(oldStatus)+"]";
		// 操作内容日志记录
		String operContent = "订单状态由"
				+ IntefacesTool.getOrderStatusDesc(oldStatus) + "更改为"
				+ IntefacesTool.getOrderStatusDesc(newStatus);
		if(orderStateInfo.getWaybillNumber() != null && 
				!orderStateInfo.getWaybillNumber().equals("")){
			operContent += orderStateInfo.getWaybillNumber();
		}
		// 操作类型
		String operType = "";
		// 新订单状态
		paramOrder.setOrderStatus(newStatus);
		// 只有已约车，接货中订单才能修改为接货中!
		if (newStatus.equals(IntefacesTool.ORDER_SATUTS_RECEIPTING)) {
			if (!oldStatus.equals(IntefacesTool.ORDER_SATUTS_RECEIPTING)
					&& !oldStatus.equals(IntefacesTool.ORDER_SATUTS_SHOUTCAR)) {
				throw new CrmBusinessException("0002",errorDesc);
			}
			operType = IntefacesTool.ORDER_OPERATION_PICK_GOODS;
			operContent += "(司机姓名:"+orderStateInfo.getDriverName()
					+",司机手机:"+orderStateInfo.getDriverMobile()+")";
		}
		// 只有已受理和已退回才能修改为已约车
		else if (newStatus.equals(IntefacesTool.ORDER_SATUTS_SHOUTCAR)) {
			if (!oldStatus.equals(IntefacesTool.ORDER_STATUS_ACCEPT)
					&& !oldStatus.equals(IntefacesTool.ORDER_SATUTS_GOBACK)) {
				throw new CrmBusinessException("0029",errorDesc);
			}
			operType = IntefacesTool.ORDER_OPERATION_BOOK_VEHICLE;
		}
		// 只有已约车，接货中订单才能修改为已退回!
		else if (newStatus.equals(IntefacesTool.ORDER_SATUTS_GOBACK)) {
			if (!oldStatus.equals(IntefacesTool.ORDER_SATUTS_RECEIPTING)
					&& !oldStatus.equals(IntefacesTool.ORDER_SATUTS_SHOUTCAR)) {
				throw new CrmBusinessException("0003",errorDesc);
			}
			operType = IntefacesTool.ORDER_OPERATION_GOBACK;
			// 退回需记录反馈信息
			operContent += orderStateInfo.getFailReason() + "/";
		}
		// 只有接货中，已退回，已受理订单才能修改为已开单!
		else if (newStatus.equals(IntefacesTool.ORDER_STATUS_GOT)) {
			if (!oldStatus.equals(IntefacesTool.ORDER_SATUTS_RECEIPTING)
					&& !oldStatus.equals(IntefacesTool.ORDER_SATUTS_GOBACK)
					&& !oldStatus.equals(IntefacesTool.ORDER_STATUS_ACCEPT)) {
				throw new CrmBusinessException("0004",errorDesc);
			}
			if (orderStateInfo.getWaybillNumber() == null
					|| orderStateInfo.getWaybillNumber().equals("")) {
				throw new CrmBusinessException("0017");
			}
			paramOrder.setWaybillNumber(orderStateInfo.getWaybillNumber());
			operType = IntefacesTool.ORDER_OPERATION_GOT;
		}
		// 只有待受理，已约车，已开单,接货中,正常签收，异常签收，已退回才能修改为已受理!
		else if (newStatus.equals(IntefacesTool.ORDER_STATUS_ACCEPT)) {
			if (!oldStatus.equals(IntefacesTool.ORDER_STATUS_WAIT_ACCEPT)
					&& !oldStatus.equals(IntefacesTool.ORDER_SATUTS_SHOUTCAR)
					&& !oldStatus.equals(IntefacesTool.ORDER_STATUS_GOT)
					&& !oldStatus.equals(IntefacesTool.ORDER_STATUS_IN_TRANSIT)
					&& !oldStatus.equals(IntefacesTool.ORDER_STATUS_SINGSUCCESS)
					&& !oldStatus.equals(IntefacesTool.ORDER_STATUS_SIGNFAIL)
					&& !oldStatus.equals(IntefacesTool.ORDER_SATUTS_GOBACK)
					) {
				throw new CrmBusinessException("0005",errorDesc);
			}
			operType = IntefacesTool.ORDER_OPERATION_WAYBILL_CANCEL;
			// 运单作废需记录反馈信息
			operContent += orderStateInfo.getFailReason() + "/";
			// 清空运单信息
			paramOrder.setWaybillNumber("");
		}
		// 只有已开单，运输中才能修改为运输中!
		else if (newStatus.equals(IntefacesTool.ORDER_STATUS_IN_TRANSIT)) {
			if (!oldStatus.equals(IntefacesTool.ORDER_STATUS_GOT)
					&& !oldStatus.equals(IntefacesTool.ORDER_STATUS_IN_TRANSIT)) {
				throw new CrmBusinessException("0006",errorDesc);
			}
			operType = IntefacesTool.ORDER_OPERATION_TRANSIT;
		}
		// 签收成功的可修改状态为：已开单，运输中，正常签收，异常签收
		else if (newStatus.equals(IntefacesTool.ORDER_STATUS_SINGSUCCESS)) {
			if (!oldStatus.equals(IntefacesTool.ORDER_STATUS_SINGSUCCESS)
					&& !oldStatus.equals(IntefacesTool.ORDER_STATUS_GOT)
					&& !oldStatus.equals(IntefacesTool.ORDER_STATUS_IN_TRANSIT)
					&& !oldStatus.equals(IntefacesTool.ORDER_STATUS_SIGNFAIL)) {
				throw new CrmBusinessException("0007",errorDesc);
			}
			operType = IntefacesTool.ORDER_OPERATION_SING;
		}
		// 签收失败的可修改状态为：已开单，运输中，正常签收，异常签收
		else if (newStatus.equals(IntefacesTool.ORDER_STATUS_SIGNFAIL)) {
			if (!oldStatus.equals(IntefacesTool.ORDER_STATUS_SINGSUCCESS)
					&& !oldStatus.equals(IntefacesTool.ORDER_STATUS_GOT)
					&& !oldStatus.equals(IntefacesTool.ORDER_STATUS_IN_TRANSIT)
					&& !oldStatus.equals(IntefacesTool.ORDER_STATUS_SIGNFAIL)) {
				throw new CrmBusinessException("0008",errorDesc);
			}
			operType = IntefacesTool.ORDER_OPERATION_SING;
			// 异常签收需记录反馈信息
			paramOrder.setFeedbackInfo((dpOrder.getFeedbackInfo()==null?"":dpOrder.getFeedbackInfo())
					+ orderStateInfo.getFailReason());
		}
		// 不允许其他状态的更改
		else {
			throw new CrmBusinessException("0009",orderStateInfo.getOrderNumber());
		}
		return orderManager.updateChannelOrder(user, paramOrder, operContent,
				operType);*/
		return false;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-13
	 * @描述：创建订单
	 * @参数：order 订单信息
	 * @返回值：是否成功
	 * */
	
	public boolean createOrder(Order interfaceOrder) throws CrmBusinessException {
		// 传入订单信息不能为空
		if (interfaceOrder == null) {
			throw new CrmBusinessException("0010");
		}
		if (interfaceOrder.getStartStation() != null&&!"".equals(interfaceOrder.getStartStation())) {
			// 将传输过来的始发部门编码转换为始发部门ID存入订单信息中
			Department startDept = departmentService.getDeptByStandardCode(interfaceOrder
					.getStartStation());
			IntefacesTool.validateNull(startDept, "0025", interfaceOrder); // 查询不到此部门信息
			interfaceOrder.setStartStation(startDept.getId());
		}
		if(interfaceOrder.getReceivingToPoint()!=null&&!"".equals(interfaceOrder.getReceivingToPoint())){
			Department ToDept = departmentService.getDeptByStandardCode(interfaceOrder
								.getReceivingToPoint());
			IntefacesTool.validateNull(ToDept, "0025", interfaceOrder); // 查询不到此部门信息
			interfaceOrder.setReceivingToPoint(ToDept.getId());
		}
		// 传入下单时间为空时，取当前时间
		if (interfaceOrder.getOrderTime() == null) {
			interfaceOrder.setOrderTime(new Date());
		}
		if (null != interfaceOrder.getChannelCustId()&&!"".equals(interfaceOrder.getChannelCustId())) {
			RegisterInfo registerInfo = new RegisterInfo();
			registerInfo.setUserName(interfaceOrder.getChannelCustId());
			registerInfo.setCustsource(interfaceOrder.getResource());
			List<RegisterInfo> infos = contactManager
					.queryRegisterInfo(registerInfo);
			String contactId = "";
			if (null != infos && infos.size() > 0) {
				contactId = infos.get(0).getCusCode();
			}
			// 把官网用户注册信息存放到CRM中
			else if ("ONLINE".equals(interfaceOrder.getResource())
					|| "0".equals(interfaceOrder.getResource())) {
				RegisterInfo bhoInfo = customerOperate.queryRegisterUser(interfaceOrder
						.getChannelCustId());
				StringBuffer contactAddress;
				if (bhoInfo != null) {
					contactAddress= new StringBuffer("");
					if(!StringUtils.isEmpty(bhoInfo.getProvince())){
						contactAddress.append(bhoInfo.getProvince());
					}
					if(!StringUtils.isEmpty(bhoInfo.getCity())){
						contactAddress.append(bhoInfo.getCity());
					}
					if(!StringUtils.isEmpty(bhoInfo.getArea())){
						contactAddress.append(bhoInfo.getArea());
					}
					if(!StringUtils.isEmpty(bhoInfo.getAddress())){
						contactAddress.append(bhoInfo.getAddress());
					}
					bhoInfo.setAddress(contactAddress.toString());
				}
				
				if(null!=bhoInfo){
					bhoInfo.setCustsource("ONLINE");
					bhoInfo.setCreateDate(new Date());
					bhoInfo.setCreateUser("86301");
					contactManager.boundContactForOnline(bhoInfo, "1");
				}
			} else {
				// 如果是第一次下单，把发货人的信息存入数据库中
				RegisterInfo deliveryCustInfo = new RegisterInfo();
				StringBuffer contactAddress = new StringBuffer("");
				if (!StringUtils.isEmpty(interfaceOrder.getContactProvince())) {
					contactAddress.append(interfaceOrder.getContactProvince());
				}
				if (!StringUtils.isEmpty(interfaceOrder.getContactCity())) {
					contactAddress.append(interfaceOrder.getContactCity());
				}
				if (!StringUtils.isEmpty(interfaceOrder.getContactArea())) {
					contactAddress.append(interfaceOrder.getContactArea());
				}
				if (!StringUtils.isEmpty(interfaceOrder.getContactAddress())) {
					contactAddress.append(interfaceOrder.getContactAddress());
				}
				deliveryCustInfo.setUserName(interfaceOrder.getChannelCustId());
				deliveryCustInfo.setCustsource(IntefacesTool
						.changeOrderResourceCodeToString(interfaceOrder.getResource()));
				deliveryCustInfo.setCusCode(interfaceOrder.getContactManId());
				deliveryCustInfo.setTelephone(interfaceOrder.getContactMobile());
				deliveryCustInfo.setFixedPhone(interfaceOrder.getContactPhone());
				deliveryCustInfo.setAddress(contactAddress.toString());
				deliveryCustInfo.setRealName(interfaceOrder.getContactName());
				deliveryCustInfo.setCreateDate(new Date());
				deliveryCustInfo.setCreateUser("86301");
				contactManager.boundContactForOnline(deliveryCustInfo, "1");
			}
			// 发货客户编码存在，ID不存在，则发货客户编码为渠道客户绑定的联系人ID
			if (interfaceOrder.getShipperNumber() != null
					&& !interfaceOrder.getShipperNumber().equals("")
					&& (interfaceOrder.getShipperId() == null || interfaceOrder.getShipperId()
							.equals(""))) {
				// 通过联系ID找到会员客户编码
				Member member = alterMemberManager.getMemberBylinkmanId(
						interfaceOrder.getShipperNumber(), null);
				if (member != null) {
					interfaceOrder.setContactManId(interfaceOrder.getShipperNumber());
					interfaceOrder.setShipperId(member.getId());
					interfaceOrder.setShipperNumber(member.getCustNumber());
					interfaceOrder.setShipperName(member.getCustName());
				} else {
					interfaceOrder.setShipperNumber(null);
				}
			} else if (null != contactId && !contactId.equals("")) {
				Member member = alterMemberManager.getMemberBylinkmanId(
						contactId, null);
				if (member != null) {
					interfaceOrder.setContactManId(contactId);
					interfaceOrder.setShipperId(member.getId());
					interfaceOrder.setShipperNumber(member.getCustNumber());
					interfaceOrder.setShipperName(member.getCustName());
				}
			}
		}
		try {
			// 创建订单
			orderManager.createAllOrderInterface(IntefacesTool
					.interfaceOrderToOrder(interfaceOrder));
			return true;
		} catch (GeneralException e) {
			String errorMsg = IntefacesTool.getMessage("order",
					e.getErrorCode());
			throw new CrmBusinessException("1005", errorMsg + e);
		}
		
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-13
	 * @描述：修改订单信息
	 * @参数：order 订单信息
	 * @返回值：boolean 是否成功
	 * */
	public boolean updateOrder(Order interfaceOrder) throws CrmBusinessException {
		// 渠道单号不能为空
		if (interfaceOrder.getChannelNumber() == null) {
			throw new CrmBusinessException("0011");
		}
		// 如果修改了始发网点信息，则需转换网点编码未网点ID
		if (interfaceOrder.getStartStation() != null) {
			// 将传输过来的始发部门编码转换为始发部门ID存入订单信息中
			Department dept = departmentService
					.getDeptByStandardCode(interfaceOrder.getStartStation());
			IntefacesTool.validateNull(dept, "0025",
					interfaceOrder.getStartStation());
			interfaceOrder.setStartStation(dept.getId());
		}
		if (interfaceOrder.getReceivingToPoint() != null) {
			// 将传输过来的始发部门编码转换为始发部门ID存入订单信息中
			Department dept = departmentService
					.getDeptByStandardCode(interfaceOrder.getReceivingToPoint());
			IntefacesTool.validateNull(dept, "0025",
					interfaceOrder.getReceivingToPoint());
			interfaceOrder.setReceivingToPoint(dept.getId());
		}
		// 初始化用户信息，所有接口
		User user = new User();
		user.setId("DP0000");
		user.setLoginName("DP000");
		Department dept = new Department();
		dept.setDeptCode("渠道客户");
		Employee e = new Employee();
		e.setDeptId(dept);
		e.setEmpCode("DP0000");
		e.setEmpName(IntefacesTool.changeOrderSourceValueToDesc(interfaceOrder
				.getResource()));
		user.setEmpCode(e);
		com.deppon.crm.module.order.shared.domain.Order dpOrder = orderManager
				.queryOrderByChannelNumber(interfaceOrder.getChannelNumber());
		if (dpOrder == null || dpOrder.getId() == null) {
			throw new CrmBusinessException("0012",
					JsonMapperUtil.writeValue(interfaceOrder));
		}
		com.deppon.crm.module.order.shared.domain.Order generateOrder = IntefacesTool
				.interfaceOrderToOrder(interfaceOrder);
		generateOrder.setId(dpOrder.getId());
		try {
			// 修改订单信息
			orderManager.updateOrder(generateOrder, user);
		} catch (GeneralException ex) {
			String errorMsg = IntefacesTool.getMessage("order",
					ex.getErrorCode());
			throw new CrmBusinessException("1005", errorMsg
					+ generateOrder.getChannelNumber() + e);
		}
		return true;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-18
	 * @描述：撤销订单信息，,添加了FOSS订单撤销 BY吴根斌
	 * */
	public boolean cancelOrder(String channelOrderNumber, String channelSource,
			String remark) throws CrmBusinessException {
		// 渠道单号，反馈信息，渠道来源均不能为空
		if (channelOrderNumber == null || channelOrderNumber.equals("")) {
			throw new CrmBusinessException("0011");
		}
		if (remark == null || remark.equals("")) {
			throw new CrmBusinessException("0013");
		}
		if (channelSource == null || channelSource.equals("")) {
			throw new CrmBusinessException("0014");
		}
		// 初始化用户信息，所有接口
		User user = new User();
		user.setId("DP0000");
		user.setLoginName("DP000");
		Department dept = new Department();
		dept.setDeptCode("渠道客户");
		Employee e = new Employee();
		e.setDeptId(dept);
		e.setEmpCode("DP0000");
		e.setEmpName(IntefacesTool.changeOrderSourceValueToDesc(channelSource));
		user.setEmpCode(e);
		com.deppon.crm.module.order.shared.domain.Order dpOrder = orderManager
				.queryOrderByChannelNumber(channelOrderNumber);
		if (dpOrder == null) {
			throw new CrmBusinessException("0012",channelOrderNumber);
		}
		try {
				com.deppon.crm.module.order.shared.domain.Order order = orderManager.cancelAllOrder(dpOrder.getId(),
						remark, user);
				if(null!=order){
					return true;
				}else{
					return false;
				}
		} catch (GeneralException ex) {
			String errorMsg = IntefacesTool.getMessage("order", ex.getErrorCode());
			throw new CrmBusinessException("1005",errorMsg+channelOrderNumber+e);
		}
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-13
	 * @描述：根据条件查询订单信息
	 * */
	public List<Order> queryOrders(Holder<OrderQueryCondition> holder)
			throws CrmBusinessException {
		OrderQueryCondition orderQueryCondition = holder.value;

		if (orderQueryCondition.getOrderNumber() != null
				|| orderQueryCondition.getWaybillNumber() != null) {
			orderQueryCondition.setStartLine(0);
			orderQueryCondition.setEndLine(1);
		} else if (orderQueryCondition.getCustomerCode() == null
				|| orderQueryCondition.getCustomerCode().equals("")
				|| orderQueryCondition.getEndLine() < 1
				|| orderQueryCondition.getEndLine() < orderQueryCondition
						.getStartLine()) {
			throw new CrmBusinessException("0015",JsonMapperUtil.writeValue(holder));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", orderQueryCondition.getStartDate());
		map.put("endDate", orderQueryCondition.getEndDate());
		map.put("currentPage", orderQueryCondition.getCurrentPage());
		map.put("pageSize", orderQueryCondition.getPageSize());
		map.put("customerCode", orderQueryCondition.getCustomerCode());
		map.put("paymentType", orderQueryCondition.getPaymentType());
		map.put("orderStatus", orderQueryCondition.getOrderStatus());
		map.put("orderNumber", orderQueryCondition.getOrderNumber());
		map.put("waybillNumber", orderQueryCondition.getWaybillNumber());
		map.put("receiveContact", orderQueryCondition.getReceiveContact());
		map.put("goodsName", orderQueryCondition.getGoodsName());
		map.put("startLine", orderQueryCondition.getStartLine());
		map.put("endLine", orderQueryCondition.getEndLine());
		map.put("onlineName", orderQueryCondition.getOnlineName());
		// 存入总数
		orderQueryCondition.setCount(orderManager.countOrders(map));
		List<com.deppon.crm.module.order.shared.domain.Order> orders = orderManager.queryOrders(map);
//		for (Order order : orders) {
//			if (order.getStartStation() != null && !order.getStartStation().equals("")) {
//				// 将始发网点ID转换为始发网点编码
//				Department dept = departmentService.queryById(order
//						.getStartStation());
//				if (dept != null) {
//					order.setStartStation(dept.getStandardCode());
//				}
//			}
//		}
		return IntefacesTool.OrderListToInterfaceOrderList(orders);
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-13
	 * @描述：根据订单号查询订单信息，
	 * */
	public Order searchOrder(String orderNumber) throws CrmBusinessException {

		Check.notNullOrEmpty(orderNumber,
				"searchOrder's arguments can not be null or empty");
		com.deppon.crm.module.order.shared.domain.Order order = orderManager.getOrderWithLinkManCode(orderNumber);
		// 将始发网点ID转换为始发网点编码
		if (order != null && order.getStartStation() != null
				&& !order.getStartStation().equals("")) {
			Department dept = departmentService.queryById(order
					.getStartStation());
			if (dept != null) {
				order.setStartStation(dept.getStandardCode());
			}
			// 将保险价值金额保留两位小数
			if(order.getInsuredAmount() != null && order.getInsuredAmount() > 0){
				DecimalFormat format = new DecimalFormat("#.00");
				order.setInsuredAmount(Double.valueOf(format.format(order.getInsuredAmount())));
			}
		}
		if(order != null && order.getReceivingToPoint() != null
				&& !order.getReceivingToPoint().equals("")){
			Department dept = departmentService.queryById(order
					.getReceivingToPoint());
			if (dept != null) {
				order.setReceivingToPoint(dept.getStandardCode());
			}
		}
		// 转换联系人ID为联系人编码
		if(order != null && order.getContactManId() != null && !order.getContactManId().equals("")){
			if(order.getContactManId().matches("[0-9]+")){
				Contact contact = alterMemberManager.getContact(order.getContactManId());
				if(contact != null){
					order.setContactManId(contact.getNumber());
				}
			}
		}
		return IntefacesTool.orderTOinterfaceOrder(order);
	}

	@Override
	public Order queryOrderByChannelNumber(String channelOrderNumber)
			throws CrmBusinessException {
		Check.notNullOrEmpty(channelOrderNumber,
				"queryOrderByChannelNumber's arguments can not be null or empty");
		com.deppon.crm.module.order.shared.domain.Order order = null;
		order = orderManager.queryOrderByChannelNumber(channelOrderNumber);
		if (order == null) {
			throw new CrmBusinessException("0012",channelOrderNumber);
		}
		if(order.getStartStation()!=null && !order.getStartStation().equals("")){
			Department dept = departmentService.queryById(order.getStartStation());
			if (dept != null) {
				order.setStartStation(dept.getStandardCode());
			}
		}
		return IntefacesTool.orderTOinterfaceOrder(order);
	}

	public IOrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}

	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public IAlterMemberManager getAlterMemberManager() {
		return alterMemberManager;
	}

	public void setAlterMemberManager(IAlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}

	public IOrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}

	public void setComplaintManager(IComplaintManager complaintManager) {
		this.complaintManager = complaintManager;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Override
	public ComplaintOrderBindResponse bhoCreateOrder(@WebParam(name = "ComplaintOrderBindRequest", targetNamespace = "http://www.deppon.com/bho/remote/crm/domain/entity", partName = "ComplaintOrderBindRequest") ComplaintOrderBindRequest complaintOrder) throws CrmBusinessException
		{
		
		ComplaintOrderBindResponse response = null;

		IntefacesTool.validateStringNull(complaintOrder, "0010","complaintOrder=null");// 订单信息为空
		IntefacesTool.validateStringNull(complaintOrder.getBillnumber(), "0010","billnumber="+complaintOrder.getBillnumber());// 订单信息为空
		IntefacesTool.validateStringNull(complaintOrder.getClientRequest(), "0010","clientRequest="+complaintOrder.getClientRequest());// 订单信息为空
		IntefacesTool.validateStringNull(complaintOrder.getContent(), "0010","content="+complaintOrder.getContent());// 订单信息为空
		IntefacesTool.validateStringNull(complaintOrder.getName(), "0010","name="+complaintOrder.getName());// 订单信息为空
		IntefacesTool.validateStringNull(complaintOrder.getPhone(), "0010","phone="+complaintOrder.getPhone());// 订单信息为空
		IntefacesTool.validateStringNull(complaintOrder.getSenderOrConsignee(), "0010","senderOrConsignee="+complaintOrder.getSenderOrConsignee());// 订单信息为空

		
		Complaint complaint = IntefacesTool.converToComplaint(complaintOrder);
		
		
		complaintManager.submitComplaintForOnline(complaint);
		
		response = new ComplaintOrderBindResponse(true,"保存成功");
		return response;
	}
   	public IContactManager getContactManager() {
		return contactManager;
	}

	public void setContactManager(IContactManager contactManager) {
		this.contactManager = contactManager;
	}
	public ICustomerOperate getCustomerOperate() {
		return customerOperate;
	}

	public void setCustomerOperate(ICustomerOperate customerOperate) {
		this.customerOperate = customerOperate;
	}

	public IOrderOperate getOrderOperate() {
		return orderOperate;
	}

	public void setOrderOperate(IOrderOperate orderOperate) {
		this.orderOperate = orderOperate;
	}

}
