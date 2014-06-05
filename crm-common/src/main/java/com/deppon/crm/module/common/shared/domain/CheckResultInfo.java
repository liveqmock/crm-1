package com.deppon.crm.module.common.shared.domain;

public class CheckResultInfo {

	private boolean result ;
	private String message;
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
