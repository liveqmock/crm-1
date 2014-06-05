package com.deppon.crm.module.client.order.domain;

import com.deppon.crm.module.client.common.domain.QueryCondition;

/**
 * 运单查询条件封装类，一般情况是用来查询客户相关的运单信息
 * <pre>
 *  1、时时创建会员的时候会根据发货客户或者收货客户手机去查询发货客户金额及收货客户金额
 * </pre>
 * @author davidcun @2012-3-21
 */
public class WaybillQueryCondition extends QueryCondition {
	
	//----------积分，待积分运单
	//客户编码
	private String customerNumber;
	//联系人编码
	private String contactNumber;

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	
}
