package com.deppon.crm.module.order.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * @author Administrator
 * 
 */

public class OrderAcceptInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7171430343423169377L;
	//
	private String standardCode;
	//
	private int warnNum;
	//
	private int lockNum;

	public String getStandardCode() {
		return standardCode;
	}

	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}

	public int getWarnNum() {
		return warnNum;
	}

	public void setWarnNum(int warnNum) {
		this.warnNum = warnNum;
	}

	public int getLockNum() {
		return lockNum;
	}

	public void setLockNum(int lockNum) {
		this.lockNum = lockNum;
	}

}
