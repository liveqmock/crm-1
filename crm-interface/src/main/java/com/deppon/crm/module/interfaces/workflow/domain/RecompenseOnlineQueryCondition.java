package com.deppon.crm.module.interfaces.workflow.domain;

import com.deppon.crm.module.client.common.domain.QueryCondition;

public class RecompenseOnlineQueryCondition extends QueryCondition {

	//BHO-11: 开始时间、结束时间、用户名 、当前页数、每页条数
	//BHO-10：注册用户名、运单号
	//网厅注册用户
	private String username;
	//运单号
	private String waybillNumber;
	
	//总条数
	private int count;
	
	public String getUsername() {
		return username;
	}
	
	public RecompenseOnlineQueryCondition setUsername(String username) {
		this.username = username;
		return this;
	}
	
	public RecompenseOnlineQueryCondition setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
		return this;
	}
	
	public String getWaybillNumber() {
		return waybillNumber;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
