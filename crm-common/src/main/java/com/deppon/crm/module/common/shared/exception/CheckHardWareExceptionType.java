package com.deppon.crm.module.common.shared.exception;

public enum CheckHardWareExceptionType {
	
	NoAuditCode("i18n.checkHard.noAudit"),
	NoExistsAuditCode("i18n.checkHard.noExistsCehckRecode"),
	NoExistsTokenCode("i18n.checkHard.noTokenRecode"),
	IsExpireTokenCode("i18n.checkHard.isExpireToken"),
	TokenIsUsedCode("i18n.checkHard.tokenIsUsed");

	private String errCode;
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	private CheckHardWareExceptionType(String errCode){
		this.errCode = errCode;
	}
}
