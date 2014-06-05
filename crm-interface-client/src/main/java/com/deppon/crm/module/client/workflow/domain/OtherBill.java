package com.deppon.crm.module.client.workflow.domain;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @作者：罗典
 * @时间：2012-9-13
 * @描述：积分兑换申请信息
 * */
@XStreamAlias("otherBill")
public class OtherBill {
	private BillHead billHead;
	private List<Entry> billEntries = new ArrayList<Entry>();

	public BillHead getBillHead() {
		return billHead;
	}

	public void setBillHead(BillHead billHead) {
		this.billHead = billHead;
	}

	public List<Entry> getBillEntries() {
		return billEntries;
	}

	public void setBillEntries(List<Entry> billEntries) {
		this.billEntries = billEntries;
	}

}
