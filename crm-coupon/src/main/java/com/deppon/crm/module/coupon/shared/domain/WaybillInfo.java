/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title WaybillInfo.java
 * @package com.deppon.crm.module.coupon.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */
package com.deppon.crm.module.coupon.shared.domain;

import java.math.BigDecimal;
import java.util.List;

/**   
 * <p>
 * Description: 运单信息实体，用于优惠券校验<br />
 * </p>
 * @title WaybillInfo.java
 * @package com.deppon.crm.module.coupon.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */

public class WaybillInfo {
	// 运单号
	protected String waybillNumber;
	// 订单号
	protected String orderNumber;
	// 订单来源
	protected String orderSource;
	// 产品类型
	protected String produceType;
	// 运单总额
	protected BigDecimal totalAmount;
	// 发货人手机
	protected String leaveMobile;
	// 发货人固话
	protected String leavePhone;
	// 客户编码
	protected String custNumber;
	// 出发部门
	protected String leaveDept;
	// 到达部门
	protected String arrivedDept;
	// 出发外场
	protected String leaveOutDept;
	// 到达外场
	protected String arrivedOutDept;
	// 运单金额明细
	protected List<AmountInfo> amountList;
	/**
	 * @return waybillNumber : return the property waybillNumber.
	 */
	public String getWaybillNumber() {
		// 运单号
		return waybillNumber;
	}
	/**
	 * @param waybillNumber : set the property waybillNumber.
	 */
	public void setWaybillNumber(String waybillNumber) {
		// 运单号
		this.waybillNumber = waybillNumber;
	}
	/**
	 * @return orderNumber : return the property orderNumber.
	 */
	public String getOrderNumber() {
		// 订单号
		return orderNumber;
	}
	/**
	 * @param orderNumber : set the property orderNumber.
	 */
	public void setOrderNumber(String orderNumber) {
		// 订单号
		this.orderNumber = orderNumber;
	}
	/**
	 * @return orderSource : return the property orderSource.
	 */
	public String getOrderSource() {
		// 订单来源
		return orderSource;
	}
	/**
	 * @param orderSource : set the property orderSource.
	 */
	public void setOrderSource(String orderSource) {
		// 订单来源
		this.orderSource = orderSource;
	}
	/**
	 * @return produceType : return the property produceType.
	 */
	public String getProduceType() {
		// 产品类型
		return produceType;
	}
	/**
	 * @param produceType : set the property produceType.
	 */
	public void setProduceType(String produceType) {
		// 产品类型
		this.produceType = produceType;
	}
	/**
	 * @return totalAmount : return the property totalAmount.
	 */
	public BigDecimal getTotalAmount() {
		// 运单总额
		return totalAmount;
	}
	/**
	 * @param totalAmount : set the property totalAmount.
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		// 运单总额
		this.totalAmount = totalAmount;
	}
	/**
	 * @return leaveMobile : return the property leaveMobile.
	 */
	public String getLeaveMobile() {
		// 发货人手机
		return leaveMobile;
	}
	/**
	 * @param leaveMobile : set the property leaveMobile.
	 */
	public void setLeaveMobile(String leaveMobile) {
		// 发货人手机
		this.leaveMobile = leaveMobile;
	}
	/**
	 * @return leavePhone : return the property leavePhone.
	 */
	public String getLeavePhone() {
		// 发货人固话
		return leavePhone;
	}
	/**
	 * @param leavePhone : set the property leavePhone.
	 */
	public void setLeavePhone(String leavePhone) {
		// 发货人固话
		this.leavePhone = leavePhone;
	}
	/**
	 * @return custNumber : return the property custNumber.
	 */
	public String getCustNumber() {
		// 客户编码
		return custNumber;
	}
	/**
	 * @param custNumber : set the property custNumber.
	 */
	public void setCustNumber(String custNumber) {
		// 客户编码
		this.custNumber = custNumber;
	}
	/**
	 * @return leaveDept : return the property leaveDept.
	 */
	public String getLeaveDept() {
		// 出发部门
		return leaveDept;
	}
	/**
	 * @param leaveDept : set the property leaveDept.
	 */
	public void setLeaveDept(String leaveDept) {
		// 出发部门
		this.leaveDept = leaveDept;
	}
	/**
	 * @return arrivedDept : return the property arrivedDept.
	 */
	public String getArrivedDept() {
		// 到达部门
		return arrivedDept;
	}
	/**
	 * @param arrivedDept : set the property arrivedDept.
	 */
	public void setArrivedDept(String arrivedDept) {
		// 到达部门
		this.arrivedDept = arrivedDept;
	}
	/**
	 * @return leaveOutDept : return the property leaveOutDept.
	 */
	public String getLeaveOutDept() {
		// 出发外场
		return leaveOutDept;
	}
	/**
	 * @param leaveOutDept : set the property leaveOutDept.
	 */
	public void setLeaveOutDept(String leaveOutDept) {
		// 出发外场
		this.leaveOutDept = leaveOutDept;
	}
	/**
	 * @return arrivedOutDept : return the property arrivedOutDept.
	 */
	public String getArrivedOutDept() {
		// 到达外场
		return arrivedOutDept;
	}
	/**
	 * @param arrivedOutDept : set the property arrivedOutDept.
	 */
	public void setArrivedOutDept(String arrivedOutDept) {
		// 到达外场
		this.arrivedOutDept = arrivedOutDept;
	}
	/**
	 * @return amountList : return the property amountList.
	 */
	public List<AmountInfo> getAmountList() {
		// 运单金额明细
		return amountList;
	}
	/**
	 * @param amountList : set the property amountList.
	 */
	public void setAmountList(List<AmountInfo> amountList) {
		// 运单金额明细
		this.amountList = amountList;
	}
}
