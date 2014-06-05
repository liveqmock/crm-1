package com.deppon.crm.module.client.esb.domain;

/**
 * @作者：罗典
 * @描述：ESB对接状态信息
 * @时间：2012-11-22
 * */
public class StatusInfo {

	// 状态KEY
	private String stateKey;
	// 状态发生时间
	private Long stateTime;

	public String getStateKey() {
		return stateKey;
	}

	public void setStateKey(String stateKey) {
		this.stateKey = stateKey;
	}

	public Long getStateTime() {
		return stateTime;
	}

	public void setStateTime(Long stateTime) {
		this.stateTime = stateTime;
	}
}
