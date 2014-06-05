/**   
 * @title OrderView.java
 * @package com.deppon.crm.module.order.shared.domain
 * @description 订单View对象
 * @author 潘光均
 * @update 2012-3-8 下午4:54:37
 * @version V1.0   
 */
package com.deppon.crm.module.order.shared.domain;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.client.order.domain.WaybillInfo;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.common.shared.domain.LadingstationDepartment;

/**
 * @description 封装订单view对象
 * @author 潘光均
 * @version 0.1 2012-3-8
 * @date 2012-3-8
 */

public class OrderView {
	private Order order;
	//订单id集合
	private List<String> orderIds;
	//订单id
	private String orderId;
	//反馈信息
	private String feedbackInfo;
	//始发网点
	private String startStation;
	//运单信息
	private WaybillInfo waybill;
	//约车信息实体
	private BookVehicleInfo bookVehicleInfo;
	//始发网点与受理部门关系实体
	private LadingstationDepartment ladDept;
	//始发网点
	private BussinessDept beginDept;
	//到达网点
	private BussinessDept endDept;
	//用车部门
	private String useVehicleDept;
	//派车车队
	private String vehicleTeam;
	//尾板车
	private Boolean isTailBoard;
	
	//标杆编码
	private String standardCode;
	/*		修改人：kuang
	修改时间：2013-9-3
	修改原因：增加延迟发货，订单表中增加延迟时间
	修改内容：增加delayTime字段，以及它的get，set方法
	*/
	//延迟时间
	private Date delayTime;
	
	/**
	 *@return  delayTime;
	 */
	public Date getDelayTime() {
		return delayTime;
	}
	/**
	 * @param order : set the property delayTime.
	 */
	public void setDelayTime(Date delayTime) {
		this.delayTime = delayTime;
	}
	/**
	 *@return  order;
	 */
	public Order getOrder() {
		return order;
	}
	/**
	 * @param order : set the property order.
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
	/**
	 *@return  orderIds;
	 */
	public List<String> getOrderIds() {
		return orderIds;
	}
	/**
	 * @param orderIds : set the property orderIds.
	 */
	public void setOrderIds(List<String> orderIds) {
		this.orderIds = orderIds;
	}
	/**
	 *@return  orderId;
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId : set the property orderId.
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 *@return  feedbackInfo;
	 */
	public String getFeedbackInfo() {
		return feedbackInfo;
	}
	/**
	 * @param feedbackInfo : set the property feedbackInfo.
	 */
	public void setFeedbackInfo(String feedbackInfo) {
		this.feedbackInfo = feedbackInfo;
	}
	/**
	 *@return  startStation;
	 */
	public String getStartStation() {
		return startStation;
	}
	/**
	 * @param startStation : set the property startStation.
	 */
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	/**
	 *@return  waybill;
	 */
	public WaybillInfo getWaybill() {
		return waybill;
	}
	/**
	 * @param waybill : set the property waybill.
	 */
	public void setWaybill(WaybillInfo waybill) {
		this.waybill = waybill;
	}
	/**
	 *@return  bookVehicleInfo;
	 */
	public BookVehicleInfo getBookVehicleInfo() {
		return bookVehicleInfo;
	}
	/**
	 * @param bookVehicleInfo : set the property bookVehicleInfo.
	 */
	public void setBookVehicleInfo(BookVehicleInfo bookVehicleInfo) {
		this.bookVehicleInfo = bookVehicleInfo;
	}
	/**
	 *@return  ladDept;
	 */
	public LadingstationDepartment getLadDept() {
		return ladDept;
	}
	/**
	 * @param ladDept : set the property ladDept.
	 */
	public void setLadDept(LadingstationDepartment ladDept) {
		this.ladDept = ladDept;
	}
	/**
	 *@return  beginDept;
	 */
	public BussinessDept getBeginDept() {
		return beginDept;
	}
	/**
	 * @param beginDept : set the property beginDept.
	 */
	public void setBeginDept(BussinessDept beginDept) {
		this.beginDept = beginDept;
	}
	/**
	 *@return  endDept;
	 */
	public BussinessDept getEndDept() {
		return endDept;
	}
	/**
	 * @param endDept : set the property endDept.
	 */
	public void setEndDept(BussinessDept endDept) {
		this.endDept = endDept;
	}
	/**
	 *@return  useVehicleDept;
	 */
	public String getUseVehicleDept() {
		return useVehicleDept;
	}
	/**
	 * @param useVehicleDept : set the property useVehicleDept.
	 */
	public void setUseVehicleDept(String useVehicleDept) {
		this.useVehicleDept = useVehicleDept;
	}
	/**
	 *@return  vehicleTeam;
	 */
	public String getVehicleTeam() {
		return vehicleTeam;
	}
	/**
	 * @param vehicleTeam : set the property vehicleTeam.
	 */
	public void setVehicleTeam(String vehicleTeam) {
		this.vehicleTeam = vehicleTeam;
	}
	/**
	 *@return  isTailBoard;
	 */
	public Boolean getIsTailBoard() {
		return isTailBoard;
	}
	/**
	 * @param isTailBoard : set the property isTailBoard.
	 */
	public void setIsTailBoard(Boolean isTailBoard) {
		this.isTailBoard = isTailBoard;
	}
	

	public String getStandardCode() {
		return standardCode;
	}

	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}
	
}
