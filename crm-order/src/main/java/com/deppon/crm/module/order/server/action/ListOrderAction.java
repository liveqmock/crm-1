package com.deppon.crm.module.order.server.action;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.order.server.manager.IOrderManager;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.order.shared.domain.OrderOperationLog;
import com.deppon.crm.module.order.shared.domain.OrderSearchCondition;
import com.deppon.crm.module.order.shared.domain.OrderSearchView;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;
/**
 * 
 * <p>
 * Description:订单列表Action<br />
 * </p>
 * @title ListOrderAction.java
 * @package com.deppon.crm.module.order.server.action 
 * @author zouming
 * @version 0.1 2013-1-23上午9:29:15
 */
public class ListOrderAction extends AbstractAction {

	// -----------------只需要get的数据（JSON
	// 通过get方法将ACTION中的数据转换为JSON传给前台）---------------------------------------------
	// 客户实对象 列表
	private List<Member> members;
	// 客户名称
	private String customerName;	
	// 客户手机号
	private String cellNum;
	// 客户电话号码
	private String phone;
	// BEAN获得MANAGER对象"orderManager"
	private IOrderManager orderManager;
	// 订单id
	private String orderId;
	// 分页查询，每页个数限制
	private int limit;	
	// 分页查询的起始页
	private int start;
	// 订单查询
	private OrderSearchView orderSearchView;
	// 客户实对象
	private Member member;
	// 始发部门
	private BussinessDept bussinessDept;
	// 查询结果
	private List<Order> orderList;
	// 总共有多少条数据
	private Long totalCount;
	// 订单操作记录
	private List<OrderOperationLog> orderOperationList;	
	// 网点 对象列表
	private List<BussinessDept> bussinessDeptList;
	/**
	 *@return  members;
	 */
	public List<Member> getMembers() {
		return members;
	}
	/**
	 *@return  member;
	 */
	public Member getMember() {
		return member;
	}
	/**
	 *@return  bussinessDept;
	 */
	public BussinessDept getBussinessDept() {
		return bussinessDept;
	}
	/**
	 *@return  orderList;
	 */
	public List<Order> getOrderList() {
		return orderList;
	}
	/**
	 *@return  totalCount;
	 */
	public Long getTotalCount() {
		return totalCount;
	}
	/**
	 *@return  orderOperationList;
	 */
	public List<OrderOperationLog> getOrderOperationList() {
		return orderOperationList;
	}
	/**
	 *@return  bussinessDeptList;
	 */
	public List<BussinessDept> getBussinessDeptList() {
		return bussinessDeptList;
	}

	// -----------------只需要set的数据(JSON
	// 通过set方法将前台传来的值设置到ACTION中)---------------------------------------------
	/**
	 * @param customerName : set the property customerName.
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @param cellNum : set the property cellNum.
	 */
	public void setCellNum(String cellNum) {
		this.cellNum = cellNum;
	}
	/**
	 * @param phone : set the property phone.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @param orderManager : set the property orderManager.
	 */
	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}
	/**
	 * @param orderId : set the property orderId.
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * @param limit : set the property limit.
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	/**
	 * @param start : set the property start.
	 */
	public void setStart(int start) {
		this.start = start;
	}

	// -----------------需要get和set的数据---------------------------------------------
	/**
	 * @param orderSearchView : set the property orderSearchView.
	 */
	public void setOrderSearchView(OrderSearchView orderSearchView) {
		this.orderSearchView = orderSearchView;
	}
	/**
	 *@return  orderSearchView;
	 */
	public OrderSearchView getOrderSearchView() {
		return orderSearchView;
	}

	// ------------------------------ACTION方法-------------------------------------------------

	/**
	 * .
	 * <p>
	 * 根据客户名称查询客户信息列表<br/>
	 * 方法名：searchCustomerByName
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-3-9
	 * @since JDK1.6
	 */
	@JSON
	public String searchCustomerByName() {
		//客户信息列表
		members = orderManager.searchMemberByName(customerName);
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 根据客户手机号查询客户信息列表<br/>
	 * 方法名：searchCustomerByCellNum
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-3-9
	 * @since JDK1.6
	 */
	@JSON
	public String searchCustomerByCellNum() {
		//客户信息列表
		member = orderManager.getMenberByMobile(cellNum);
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 根据客户电话查询客户信息列表<br/>
	 * 方法名：searchCustomerByPhone
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-3-9
	 * @since JDK1.6
	 */
	@JSON
	public String searchCustomerByPhone() {
		//客户信息列表
		members = orderManager.searchMemberByPhone(phone);
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 受理与打回，按照条件查询相应订单<br/>
	 * 方法名：searchOrderList
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-3-15
	 * @since JDK1.6
	 */
	@JSON
	public String searchOrderList() {
		
		//给订单查询前台传到后台的条件对象设置查询限制
		orderSearchView.getOrderSearchCondition().setLimit(limit);
		orderSearchView.getOrderSearchCondition().setStart(start);
		
		//查询条件对象
		OrderSearchCondition vosc = orderSearchView.getOrderSearchCondition();
		User user = (User) UserContext.getCurrentUser();
		
		//受理和打回订单集合
		Map map = orderManager.searchProcessAndReturnOrders(vosc, user);
		
		//查询出的订单列表
		orderList = (List) map.get("orderList");
		//查询总条数
		totalCount = Long.valueOf(map.get("count").toString());
		//查询条件
		vosc = (OrderSearchCondition) map.get("vosc");
		orderSearchView.setOrderSearchCondition(vosc);
		
		//如果总条数为空，返回1
		if (totalCount == null) {
			totalCount = (long) 1;
		}
		//如果总条数为0，返回1
		if (totalCount == 0) {
			totalCount = (long) 1;
		}
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 根据查询部门<br/>
	 * 方法名：searchBussinessDepts
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-3-26
	 * @since JDK1.6
	 */
	@JSON
	public String searchBussinessDepts() {
		//总数
		totalCount = orderManager.getDeptNumberByName(orderSearchView
				.getDeptSearchCondition());
		
		//增加每次查询的数量限制
		orderSearchView.getDeptSearchCondition().setLimit(limit);
		orderSearchView.getDeptSearchCondition().setStart(start);
		
		orderSearchView.setBussinessDepts(orderManager
				.getDeptByName(orderSearchView.getDeptSearchCondition()));
		return SUCCESS;
	}

	/**
	  ***************************************************************************************************
	  **/

	/**
	 * . 订单分配查询页面
	 * 
	 * @author 张登
	 * @时间 2012-3-14 searchAssignAndRefuseOrders
	 * @since JDK1.6
	 */
	@JSON
	public String searchAssignAndRefuseOrders() {
		//增加每次查询的数量限制
		orderSearchView.getOrderSearchCondition().setLimit(limit);
		orderSearchView.getOrderSearchCondition().setStart(start);
		
		//查询分配和拒绝的订单集合
		Map map = orderManager.searchAssignAndRefuseOrders(orderSearchView
				.getOrderSearchCondition());
		//获取订单列表
		orderList = (List<Order>) map.get("orderList");
		
		//查询总条数
		totalCount = Long.valueOf(map.get("count").toString());
		//如果总数为空，返回1
		if (totalCount == null) {
			totalCount = (long) 1;
		}
		//如果总数为0，返回1
		if (totalCount == 0) {
			totalCount = (long) 1;
		}
		return SUCCESS;
	}

	/**
	 * .根据手机号查询会员信息
	 * @author 张登
	 * @时间 2012-3-14 getOrderOperationLogList
	 * @since JDK1.6
	 */
	@SuppressWarnings("rawtypes")
	@JSON
	public String searchMemberInfoByPhone() {
		User user = (User) UserContext.getCurrentUser();
		Map map = orderManager.searchMemberInfoByPhone(phone,user);
		member = (Member) map.get("member");
		bussinessDept = (BussinessDept)map.get("busDept");
		return SUCCESS;
	}

	/**
	 * . 订单页面用户操作记录查询
	 * 
	 * @author 张登
	 * @时间 2012-3-14 getOrderOperationLogList
	 * @since JDK1.6
	 */
	@JSON
	public String getOrderOperationLogList() {
		orderOperationList = orderManager.getOrderOperationLogList(orderId);
		return SUCCESS;
	}

}
