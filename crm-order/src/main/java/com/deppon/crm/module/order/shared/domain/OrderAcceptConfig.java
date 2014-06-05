package com.deppon.crm.module.order.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * @author Administrator
 * 
 */

public class OrderAcceptConfig extends BaseEntity {

	private static final long serialVersionUID = -6764057340024966331L;
	//
	private String standardCode;
	//
	private int warnTime;
	//
	private int lockTime;

	public String getStandardCode() {
		return standardCode;
	}

	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}

	public int getWarnTime() {
		return warnTime;
	}

	public void setWarnTime(int warnTime) {
		this.warnTime = warnTime;
	}

	public int getLockTime() {
		return lockTime;
	}

	public void setLockTime(int lockTime) {
		this.lockTime = lockTime;
	}

}
