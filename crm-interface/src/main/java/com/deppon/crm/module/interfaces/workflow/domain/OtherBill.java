package com.deppon.crm.module.interfaces.workflow.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @作者：罗典
 * @时间：2012-9-13
 * @描述：理赔兑换审批数据
 * */
//@XStreamAlias("otherBill")
public class OtherBill {
	private BillHead billHead;

	public BillHead getBillHead() {
		return billHead;
	}

	public void setBillHead(BillHead billHead) {
		this.billHead = billHead;
	}
}
