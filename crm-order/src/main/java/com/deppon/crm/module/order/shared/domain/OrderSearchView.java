package com.deppon.crm.module.order.shared.domain;

import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.BusDeptSearchCondition;
import com.deppon.crm.module.common.shared.domain.BussinessDept;

/**
 * 
 * @ClassName: OrderSearchView
 * @Description: action交互对象
 * @author
 * @date 2012-3-13 下午2:51:10
 * 
 */
public class OrderSearchView {
	//订单搜索条件实体
	private OrderSearchCondition orderSearchCondition;
	//车辆部门搜索条件实体
	private BusDeptSearchCondition deptSearchCondition;
	//订单列表
	private List<Order> orderList;
	//根据部门名称查询部门返回集合
	private List<BussinessDept> bussinessDepts;
	//查询数据的总条数
	private Long totalCount;
	//当前登录用户
	private User user;
	//部门名称
	private String deptName;
	//订单操作日志
	private List<OrderOperationLog> orderOperationLogList;
	/**
	 *@return  orderSearchCondition;
	 */
	public OrderSearchCondition getOrderSearchCondition() {
		return orderSearchCondition;
	}
	/**
	 * @param orderSearchCondition : set the property orderSearchCondition.
	 */
	public void setOrderSearchCondition(OrderSearchCondition orderSearchCondition) {
		this.orderSearchCondition = orderSearchCondition;
	}
	/**
	 *@return  deptSearchCondition;
	 */
	public BusDeptSearchCondition getDeptSearchCondition() {
		return deptSearchCondition;
	}
	/**
	 * @param deptSearchCondition : set the property deptSearchCondition.
	 */
	public void setDeptSearchCondition(BusDeptSearchCondition deptSearchCondition) {
		this.deptSearchCondition = deptSearchCondition;
	}
	/**
	 *@return  orderList;
	 */
	public List<Order> getOrderList() {
		return orderList;
	}
	/**
	 * @param orderList : set the property orderList.
	 */
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	/**
	 *@return  bussinessDepts;
	 */
	public List<BussinessDept> getBussinessDepts() {
		return bussinessDepts;
	}
	/**
	 * @param bussinessDepts : set the property bussinessDepts.
	 */
	public void setBussinessDepts(List<BussinessDept> bussinessDepts) {
		this.bussinessDepts = bussinessDepts;
	}
	/**
	 *@return  totalCount;
	 */
	public Long getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount : set the property totalCount.
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 *@return  user;
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user : set the property user.
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 *@return  deptName;
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName : set the property deptName.
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 *@return  orderOperationLogList;
	 */
	public List<OrderOperationLog> getOrderOperationLogList() {
		return orderOperationLogList;
	}
	/**
	 * @param orderOperationLogList : set the property orderOperationLogList.
	 */
	public void setOrderOperationLogList(
			List<OrderOperationLog> orderOperationLogList) {
		this.orderOperationLogList = orderOperationLogList;
	}
	
}
