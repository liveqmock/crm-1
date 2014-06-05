package com.deppon.crm.module.interfaces.workflow.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @作者：罗典
 * @时间：2012-9-13
 * @描述：理赔兑换审批数据
 * */
//@XStreamAlias("billHead")
public class BillHead {
	private String id;
	private boolean status;
	private String common;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getCommon() {
		return common;
	}

	public void setCommon(String common) {
		this.common = common;
	}
}
