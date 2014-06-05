package com.deppon.crm.module.client.workflow.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @作者：罗典
 * @时间：2012-9-13
 * @描述：积分兑换申请信息
 * */
@XStreamAlias("entry")
public class Entry {
	// 物品编码
	private String articleNum;
	// 数量
	private int quantity;
	// 备注
	private String reasonReq;

	public String getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(String articleNum) {
		this.articleNum = articleNum;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getReasonReq() {
		return reasonReq;
	}

	public void setReasonReq(String reasonReq) {
		this.reasonReq = reasonReq;
	}
}