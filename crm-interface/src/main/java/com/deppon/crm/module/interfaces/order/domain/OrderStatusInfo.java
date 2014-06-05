package com.deppon.crm.module.interfaces.order.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * ERP状态返回信息封装类
 * 
 * @author davidcun @2012-3-22
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderStatusInfo {

	// 状态表示
	private int crmOrderState;
	// 操作人(工号)
	private String processPerson;
	// 操作部门，名称(标杆编码)
	private String processDept;
	// 操作时间
	private Date processDate;
	// 订单号
	private String orderNumber;
	// 运单号，返回已开单、运输中、正常签收、异常签收状态的时候需要设置
	// 特别注意的是当ERP作废运单时候，返回给CRM的是已受理状态，也需要带上运单号
	private String waybillNumber;
	// 状态为已退回或异常签收时需要设置原因
	private String failReason;
	// 司机姓名
	private String driverName;
	// 司机手机
	private String driverMobile;

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverMobile() {
		return driverMobile;
	}

	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}

	public int getCrmOrderState() {
		return crmOrderState;
	}

	public void setCrmOrderState(int crmOrderState) {
		this.crmOrderState = crmOrderState;
	}

	public String getProcessPerson() {
		return processPerson;
	}

	public void setProcessPerson(String processPerson) {
		this.processPerson = processPerson;
	}

	public String getProcessDept() {
		return processDept;
	}

	public void setProcessDept(String processDept) {
		this.processDept = processDept;
	}

	public Date getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public String getWaybillNumber() {
		return waybillNumber;
	}

	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}
}
