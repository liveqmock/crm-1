package com.deppon.crm.module.order.shared.domain;

import java.util.Date;
import java.util.List;
/**
 * 
* @ClassName: 	OrderSearchCondition
* @Description: 查询条件
* @author huangzhanming
* @date 		2012-3-13 下午2:50:26
*
 */
public class OrderSearchCondition {
	//创建起始日期
	private Date startDate;
	//创建终止日期
	private Date endDate;
	//修改起始日期
	private Date startModifyDate;
	//修改终止日期
	private Date endModifyDate;
	// 订单来源
	private String resource;
	// 订单状态
	private String orderStatus;
	// 运单号
	private String waybillNum;
	// 订单号
	private String orderNum;
	// 联系人手机
	private String contactMobile;
	// 联系人固话
	private String contactPhone;
	// 始发网点id
	private String startStationId;
	// 制单人名称（员工姓名）
	private String createEmpName;
	//开始条数
	private Integer start;
	//每页条数
	private Integer limit;
	//当前登录用户可以查询的部门id
	private List<String> deptIds;
    /**			
	    * 修改人：张斌
		*修改时间：2013-7-27 11:10
		*原有内容：无（新增）
		*修改原因：增加运输方式查询条件(后台查询实体)以及get/set方法
	 */
	//begin
	// 运输方式
	private String transportMode;
	
	/**
	 * @return the transportMode
	 */
	public String getTransportMode() {
		return transportMode;
	}
	/**
	 * @param transportMode the transportMode to set
	 */
	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}
	//end
	/**
	 *@return  startDate;
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate : set the property startDate.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 *@return  endDate;
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate : set the property endDate.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 *@return  startModifyDate;
	 */
	public Date getStartModifyDate() {
		return startModifyDate;
	}
	/**
	 * @param startModifyDate : set the property startModifyDate.
	 */
	public void setStartModifyDate(Date startModifyDate) {
		this.startModifyDate = startModifyDate;
	}
	/**
	 *@return  endModifyDate;
	 */
	public Date getEndModifyDate() {
		return endModifyDate;
	}
	/**
	 * @param endModifyDate : set the property endModifyDate.
	 */
	public void setEndModifyDate(Date endModifyDate) {
		this.endModifyDate = endModifyDate;
	}
	/**
	 *@return  resource;
	 */
	public String getResource() {
		return resource;
	}
	/**
	 * @param resource : set the property resource.
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}
	/**
	 *@return  orderStatus;
	 */
	public String getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus : set the property orderStatus.
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 *@return  waybillNum;
	 */
	public String getWaybillNum() {
		return waybillNum;
	}
	/**
	 * @param waybillNum : set the property waybillNum.
	 */
	public void setWaybillNum(String waybillNum) {
		this.waybillNum = waybillNum;
	}
	/**
	 *@return  orderNum;
	 */
	public String getOrderNum() {
		return orderNum;
	}
	/**
	 * @param orderNum : set the property orderNum.
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 *@return  contactMobile;
	 */
	public String getContactMobile() {
		return contactMobile;
	}
	/**
	 * @param contactMobile : set the property contactMobile.
	 */
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	/**
	 *@return  contactPhone;
	 */
	public String getContactPhone() {
		return contactPhone;
	}
	/**
	 * @param contactPhone : set the property contactPhone.
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	/**
	 *@return  startStationId;
	 */
	public String getStartStationId() {
		return startStationId;
	}
	/**
	 * @param startStationId : set the property startStationId.
	 */
	public void setStartStationId(String startStationId) {
		this.startStationId = startStationId;
	}
	/**
	 *@return  createEmpName;
	 */
	public String getCreateEmpName() {
		return createEmpName;
	}
	/**
	 * @param createEmpName : set the property createEmpName.
	 */
	public void setCreateEmpName(String createEmpName) {
		this.createEmpName = createEmpName;
	}
	/**
	 *@return  start;
	 */
	public Integer getStart() {
		return start;
	}
	/**
	 * @param start : set the property start.
	 */
	public void setStart(Integer start) {
		this.start = start;
	}
	/**
	 *@return  limit;
	 */
	public Integer getLimit() {
		return limit;
	}
	/**
	 * @param limit : set the property limit.
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	/**
	 *@return  deptIds;
	 */
	public List<String> getDeptIds() {
		return deptIds;
	}
	/**
	 * @param deptIds : set the property deptIds.
	 */
	public void setDeptIds(List<String> deptIds) {
		this.deptIds = deptIds;
	}
	
}
