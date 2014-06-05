package com.deppon.crm.module.order.server.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.order.domain.WaybillInfo;
import com.deppon.crm.module.common.server.manager.impl.LadingstationDepartmentManager;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.PotentialCustomer;
import com.deppon.crm.module.order.server.manager.IOrderManager;
import com.deppon.crm.module.order.server.manager.IVehicleHistoryManager;
import com.deppon.crm.module.order.server.util.Constant;
import com.deppon.crm.module.order.shared.domain.BookVehicleInfo;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.order.shared.domain.OrderOperationLog;
import com.deppon.crm.module.order.shared.domain.OrderView;
import com.deppon.crm.module.order.shared.domain.VehicleHistory;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.message.IMessageBundle;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

public class OrderAction extends AbstractAction {

	// -----------------只需要get的数据---------------------------------------------

	// 提示信息
	private String message;

	public String getMessage() {
		return message;
	}
	
	//是否为合同客户
	private Boolean isContractMember;
	
	public Boolean getIsContractMember() {
		return isContractMember;
	}
	public void setIsContractMember(Boolean isContractMember) {
		this.isContractMember = isContractMember;
	}
	
	//客户ID
	private String memberId;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	//客户编码
	private String custNumber;
	
	public String getCustNumber() {
		return custNumber;
	}
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}

	// 打印信息
	private Order order;

	public Order getOrder() {
		return order;
	}

	// 订单备注
	private OrderOperationLog orderOperationLog;

	public OrderOperationLog getOrderOperationLog() {
		return orderOperationLog;
	}

	public void setOrderOperationLog(OrderOperationLog orderOperationLog) {
		this.orderOperationLog = orderOperationLog;
	}

	// -----------------只需要set的数据---------------------------------------------
	private IOrderManager orderManager;

	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}

	// 订单约车车队历史
	private IVehicleHistoryManager vehicleHistoryManager;

	public void setVehicleHistoryManager(
			IVehicleHistoryManager vehicleHistoryManager) {
		this.vehicleHistoryManager = vehicleHistoryManager;
	}

	// BEAN获得国际化对象
	private IMessageBundle messageBundle;

	public void setMessageBundle(IMessageBundle messageBundle) {
		this.messageBundle = messageBundle;
	}

	private String orderId;

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	private String shipperId;

	public void setShipperId(String shipperId) {
		this.shipperId = shipperId;
	}
	
	private LadingstationDepartmentManager ladingstationDepartmentManager;

	public void setLadingstationDepartmentManager(
			LadingstationDepartmentManager ladingstationDepartmentManager) {
		this.ladingstationDepartmentManager = ladingstationDepartmentManager;
	}

	// -----------------需要get和set的数据---------------------------------------------
	// 封装订单view对象
	private OrderView orderView;

	public OrderView getOrderView() {
		return orderView;
	}

	public void setOrderView(OrderView orderView) {
		this.orderView = orderView;
	}
	//是否约车标识
	private String isBookVehicle;

	public void setIsBookVehicle(String isBookVehicle) {
		this.isBookVehicle = isBookVehicle;
	}
	
	// 订单约车车队历史
	private VehicleHistory vehicleHistory;
	public VehicleHistory getVehicleHistory() {
		return vehicleHistory;
	}

	public void setVehicleHistory(VehicleHistory vehicleHistory) {
		this.vehicleHistory = vehicleHistory;
	}

	/**
	 * .
	 * <p>
	 * 提交订单信息（包括约车）<br/>
	 * 方法名：createOrder
	 * </p>
	 * 
	 * @author 张斌 update @author 张登
	 * @时间 2012-3-9
	 * @since JDK1.6
	 */
	@JSON
	public String createOrder() {
		Order order = null;
		if (StringUtils.isNotBlank(isBookVehicle) && isBookVehicle.equals("1")) {// 1表示约车
			User user = (User) UserContext.getCurrentUser();
			BookVehicleInfo bookVehicleInfo = new BookVehicleInfo();
			bookVehicleInfo.setVehicleTeam(orderView.getVehicleTeam());
			bookVehicleInfo.setUseVehicleDept(orderView.getUseVehicleDept());
			bookVehicleInfo.setIsTailBoard(orderView.getIsTailBoard());
			// 是否接货设置true
			orderView.getOrder().setIsReceiveGoods(true);
			String contactId = orderView.getOrder().getContactManId();
			if(contactId!=null && !"".equals(contactId) && contactId.equals("0")){
				orderView.getOrder().setContactManId("");
			}
			order = orderManager.bookVehicleAndCreateOrder(
					orderView.getOrder(), bookVehicleInfo, user,
					vehicleHistory.getVehicleTeamName());
			vehicleHistoryManager.generateVehicleHistory(order,
					bookVehicleInfo, user, vehicleHistory.getVehicleTeamName());
		} else {
			String contactId = orderView.getOrder().getContactManId();
			if(contactId!=null && !"".equals(contactId) && contactId.equals("0")){
				orderView.getOrder().setContactManId("");
			}
			// 是否接货设置为否
			orderView.getOrder().setIsReceiveGoods(false);
			// 清空接货起始结束时间
			orderView.getOrder().setBeginAcceptTime(null);
			orderView.getOrder().setEndAcceptTime(null);
			order = orderManager.createAllOrder(orderView.getOrder());
		}
		message = messageBundle.getMessage(getLocale(),
				"i18n.order.commitSuccess")
				+ messageBundle.getMessage(getLocale(),
						"i18n.order.orderNumForShow") + order.getOrderNumber();
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 网点所在城市是否在大小城市基础资料表中<br/>
	 * 方法名：isBacikCity
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-3-15
	 * @since JDK1.6
	 */
	@JSON
	public String initPoint() {
		User user = (User) UserContext.getCurrentUser();
		String id = user.getEmpCode().getDeptId().getId();
		BussinessDept dept = orderManager.createOrderInit(id);
		orderView = new OrderView();
		orderView.setBeginDept(dept);
		if (StringUtils.isNotBlank(isBookVehicle) && isBookVehicle.equals("1")) {// 1表示约车
			vehicleHistory=vehicleHistoryManager.getVehicleHistory(user);
		}
		return SUCCESS;
	}

	@JSON
	public String changePointId() {
		if (null == orderView || null == orderView.getBeginDept()) {
			return SUCCESS;
		}
		BussinessDept dept = orderManager.searchBusDeptByErpId(orderView
				.getBeginDept().getId());
		orderView = new OrderView();
		orderView.setBeginDept(dept);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据标杆编码查询营业部门<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-1-8
	 * @return
	 * String
	 */
	@JSON
	public String changePointStandardcode() {
		if (null == orderView || null == orderView.getStandardCode() || "".equals(orderView.getStandardCode())) {
			return SUCCESS;
		}
		BussinessDept dept = orderManager.searchBusDeptByStandardCode(orderView.getStandardCode());
		orderView = new OrderView();
		orderView.setBeginDept(dept);
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 验证订单可否修改<br/>
	 * 方法名：updateOrderable
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-3-16
	 * @since JDK1.6
	 */
	@JSON
	public String updateOrderable() {
		String id = orderView.getOrderId();
		User user = (User) UserContext.getCurrentUser();
		Map map = orderManager.getOrderWithValidation(id,user);
		Order order = (Order) map.get("order");
		WaybillInfo waybill = (WaybillInfo) map.get("waybill");
		BussinessDept beginDept = (BussinessDept) map.get("beginDept");
		BussinessDept endDept = (BussinessDept) map.get("endDept");
		orderView.setOrder(order);
		orderView.setWaybill(waybill);
		orderView.setBeginDept(beginDept);
		orderView.setEndDept(endDept);
		isContractMember=orderManager.isContractMember(memberId,custNumber);
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 订单的修改<br/>
	 * 方法名：updateOrder
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-3-16
	 * @since JDK1.6
	 */
	@JSON
	public String updateOrder() {
		User user = (User) UserContext.getCurrentUser();
		Order order = orderManager.updateOrder(orderView.getOrder(), user);
		orderView.setOrder(order);
		message = messageBundle.getMessage(getLocale(),
				"i18n.order.updateOrderSuccess");
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 受理订单<br/>
	 * 方法名：processOrder
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-3-16
	 * @since JDK1.6
	 */
	@JSON
	public String processOrder() {
		String id = orderView.getOrderId();
		User user = (User) UserContext.getCurrentUser();
		String feedbackInfo = orderView.getFeedbackInfo();
		Order order = orderManager.processOrder(id, user, feedbackInfo);
		orderView.setOrder(order);
		message = messageBundle.getMessage(getLocale(),
				"i18n.order.processSuccess");
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 打回订单<br/>
	 * 方法名：returnOrder
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-3-16
	 * @since JDK1.6
	 */
	@JSON
	public String returnOrder() {
		String id = orderView.getOrderId();
		String feedbackInfo = orderView.getFeedbackInfo();
		User user = (User) UserContext.getCurrentUser();
		Order order = orderManager.returnOrder(id, feedbackInfo, user);
		orderView.setOrder(order);
		message = messageBundle.getMessage(getLocale(),
				"i18n.order.returnSuccess");
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 揽货失败<br/>
	 * 方法名：failOrder
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-3-16
	 * @since JDK1.6
	 */
	@JSON
	public String failOrder() {

		String id = orderView.getOrderId();
		String feedbackInfo = orderView.getFeedbackInfo();
		User user = (User) UserContext.getCurrentUser();
		// 揽货失败
		Map map = orderManager.accpetFail(id, feedbackInfo, user);
		Order order = (Order) map.get("order");
		Member cust = (Member) map.get("cust");
		orderView.setOrder(order);
		try {
			String transportMode = order.getTransportMode();
			String businessType = "";
			if (transportMode != null
					&& Constant.ORDER_TRANSTYPE_AGENT_PACKAGE
							.equals(transportMode)) {
				businessType = "EXPRESS";
			} else {
				businessType = "LESS_THAN_TRUCKLOAD";
			}
//			cust.setBusinessType(businessType);
			// 创建潜客信息--为了分开揽货失败和创建潜客的事务
			orderManager.createPotentialCustomer(cust);
		} catch (Exception e) {
			e.printStackTrace();
		}
		message = messageBundle.getMessage(getLocale(),
				"i18n.order.failOrderSuccess");
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 催单<br/>
	 * 方法名：pressOrder
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-3-21
	 * @since JDK1.6
	 */
	@JSON
	public String pressOrder() {
		String id = orderView.getOrderId();
		/* orderView = orderManager.urgeOrder(id); */
		User user = (User) UserContext.getCurrentUser();
		Order order = orderManager.urgeOrder(id, user);
		orderView.setOrder(order);
		message = messageBundle.getMessage(getLocale(),
				"i18n.order.pressOrderSuccess");
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 检验该订单是否可约车<br/>
	 * 方法名：bookVehicleable
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-3-16
	 * @since JDK1.6
	 */
	@JSON
	public String bookVehicleable() {
		String id = orderView.getOrderId();

		// orderView = orderManager.getOrderIfCanBookVehicle(id);
		Map map = orderManager.getOrderIfCanBookVehicle(id);
		Order order = (Order) map.get("order");
		BussinessDept beginDept = (BussinessDept) map.get("beginDept");
		BussinessDept endDept = (BussinessDept) map.get("endDept");
		orderView.setOrder(order);
		orderView.setBeginDept(beginDept);
		orderView.setEndDept(endDept);
		isContractMember=orderManager.isContractMember(memberId,custNumber);
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 约车提交<br/>
	 * 方法名：bookVehicle
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-3-16
	 * @since JDK1.6
	 */
	@JSON
	public String bookVehicle() {
		Order vorder = orderView.getOrder();
		BookVehicleInfo bkinfo = orderView.getBookVehicleInfo();
		User user = (User) UserContext.getCurrentUser();
		// 获取派车车队
		BussinessDept dept = ladingstationDepartmentManager.getBusDeptByCode(bkinfo
				.getVehicleTeam());
		// 生成派车车队历史记录
		vehicleHistoryManager.generateVehicleHistory(order, bkinfo, user,
				dept.getDeptName());
		orderManager.bookVehicle(vorder, bkinfo, user);
		message = messageBundle.getMessage(getLocale(),
				"i18n.order.callCarSuccess");
		return SUCCESS;
	}
	
	@JSON
	public String vehicleHistory(){
		User user = (User) UserContext.getCurrentUser();
		vehicleHistory = vehicleHistoryManager.getVehicleHistory(user);
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * print获取数据<br/>
	 * 方法名：printOrder
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-3-16
	 * @since JDK1.6
	 */
	@JSON
	public String printOrder() {
		order = orderManager.getOrderById(orderId);
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * print获取数据<br/>
	 * 方法名：printOrder
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-3-16
	 * @since JDK1.6
	 */
	@JSON
	public String init400Point() {
		BussinessDept dept = orderManager.callCenterOrderInit(shipperId);
		orderView.setBeginDept(dept);
		return SUCCESS;
	}

	/**
	 ***************************************************************************************************
	 **/

	/**
	 * . 订单分配
	 * 
	 * @author 张登
	 * @时间 2012-3-14 assignOrder
	 * @since JDK1.6
	 */
	@JSON
	public String assignOrder() {
		User user = (User) UserContext.getCurrentUser();
		orderManager.assignOrder(orderView.getOrderIds(), orderView.getOrder(),
				user);
		message = messageBundle.getMessage(getLocale(),
				"i18n.order.assignOrderSuccess");
		return SUCCESS;
	}

	/**
	 * . 订单拒绝
	 * 
	 * @author 张登
	 * @时间 2012-3-14 refuseOrder
	 * @since JDK1.6
	 */
	@JSON
	public String refuseOrder() {
		User user = (User) UserContext.getCurrentUser();
		String feedbackInfo = orderView.getFeedbackInfo();
		Order order = orderManager.refuseOrder(orderView.getOrderId(), user,
				feedbackInfo);
		orderView.setOrder(order);
		message = messageBundle.getMessage(getLocale(),
				"i18n.order.refuseOrderSuccess");
		return SUCCESS;
	}

	/**
	 * . 订单撤销
	 * 
	 * @author 张登
	 * @时间 2012-3-14 cancelOrder
	 * @since JDK1.6
	 */
	@JSON
	public String cancelOrder() {
		User user = (User) UserContext.getCurrentUser();
		Order order = orderManager.cancelAllOrder(orderView.getOrderId(),
				orderView.getFeedbackInfo(), user);
		orderView.setOrder(order);
		message = messageBundle.getMessage(getLocale(),
				"i18n.order.cancelOrderSuccess");
		return SUCCESS;
	}

	/**
	 * . 根据订单号查询订单
	 * 
	 * @author 张登
	 * @时间 2012-3-14 orderByOrderNumber
	 * @since JDK1.6
	 */
	@JSON
	public String orderByOrderNumber() {
		Order order = orderManager.getOrderWaybill(orderView.getOrder()
				.getOrderNumber());
		orderView.setOrder(order);
		return SUCCESS;
	}

	/**
	 * . 根据运单查询运单信息
	 * 
	 * @author 张登
	 * @时间 2012-3-14 waybillNum
	 * @since JDK1.6
	 */
	@JSON
	public String waybillNum() {
		orderView.setWaybill(orderManager.getWaybillByNumber(orderView
				.getWaybill().getWaybillNumber()));
		return SUCCESS;
	}

	/**
	 * . 根据运单号查询订单
	 * 
	 * @author 张登
	 * @时间 2012-3-14 getOrderWaybillNum
	 * @since JDK1.6
	 */
	@JSON
	public String orderWaybillNum() {
		Order order = orderManager.getOrderWaybillNum(orderView.getWaybill()
				.getWaybillNumber());
		orderView.setOrder(order);
		return SUCCESS;
	}

	/**
	 * . 定运单关联
	 * 
	 * @author 张登
	 * @时间 2012-3-14 refuseOrder
	 * @since JDK1.6
	 */
	@JSON
	public String associateOrderAndWaybill() {
		/* orderManager.associateOrderWaybill(orderView); */
		String orderId = orderView.getOrder().getOrderNumber();
		WaybillInfo waybill = orderView.getWaybill();
		orderManager.associateOrderWaybill(waybill, orderId);
		message = messageBundle.getMessage(getLocale(),
				"i18n.order.orderWayllSuccess");
		return SUCCESS;
	}

	/**
	 * 保存订单备注
	 * 
	 * @author xiaohongye
	 * @时间 2012-3-14 refuseOrder
	 * @since JDK1.6
	 */
	@JSON
	public String saveOrderRemark() {
		User user = (User) UserContext.getCurrentUser();
		orderManager.saveOrderRemarkInfo(orderOperationLog, user);
		// System.out.println(orderOperationLog.getOrderId()+"|"+orderOperationLog.getOperatorContent()+"|"+orderOperationLog.getContactName());
		return SUCCESS;
	}
	/**
	 * 根据客户Id或者客户编码来查询客户是否为合同客户
	 * 
	 * @author 杨伟
	 * @时间 2012-4-24
	 * @since JDK1.6
	 */
	@JSON
	public String isContractMember(){
		isContractMember = orderManager.isContractMember(memberId, custNumber);
		return SUCCESS;
	}
	/**
	 * .
	 * <p>
	 * 延迟订单<br/>
	 * 方法名：delayOrder
	 * </p>
	 * 
	 * @author kuang
	 * @时间 2013-9-3
	 * @since JDK1.6
	 */
	@JSON
	public String delayOrder() {
		String id = orderView.getOrderId();
		String feedbackInfo = orderView.getFeedbackInfo();
		User user = (User) UserContext.getCurrentUser();
		Date delayTime = orderView.getDelayTime();
		Order order = orderManager.delayOrder(id, user, feedbackInfo, delayTime);
		orderView.setOrder(order);
		message = messageBundle.getMessage(getLocale(),
				"i18n.order.delaySuccess");
		return SUCCESS;
	}
}
