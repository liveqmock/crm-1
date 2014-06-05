package com.deppon.crm.module.scheduler.shared.domain;

import java.util.Date;

public class CompareErrorInfo {
	private String compareTime;
	private String keyInfo;
	private String mismatchingInfo;


	public String getCompareTime() {
		return compareTime;
	}

	public void setCompareTime(String compareTime) {
		this.compareTime = compareTime;
	}

	public String getKeyInfo() {
		return keyInfo;
	}

	public void setKeyInfo(String keyInfo) {
		this.keyInfo = keyInfo;
	}

	public String getMismatchingInfo() {
		return mismatchingInfo;
	}

	public void setMismatchingInfo(String mismatchingInfo) {
		this.mismatchingInfo = mismatchingInfo;
	}

}
