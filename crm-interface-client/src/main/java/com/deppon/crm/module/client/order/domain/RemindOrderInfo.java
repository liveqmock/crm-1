package com.deppon.crm.module.client.order.domain;

/**
 * CRM需要调用ERP催单功能传输的参数的封装类
 * @author davidcun @2012-3-23
 */
public class RemindOrderInfo {

	//订单编号
	private String orderCode;
	//催单时间
	private int remindTimes;
	//催单部门(名字)
	private String remindDept;
	//催单人，姓名
	private String remindPerson;
	
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public int getRemindTimes() {
		return remindTimes;
	}

	public void setRemindTimes(int remindTimes) {
		this.remindTimes = remindTimes;
	}

	public String getRemindDept() {
		return remindDept;
	}

	public void setRemindDept(String remindDept) {
		this.remindDept = remindDept;
	}

	public String getRemindPerson() {
		return remindPerson;
	}

	public void setRemindPerson(String remindPerson) {
		this.remindPerson = remindPerson;
	}
}
