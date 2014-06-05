package com.deppon.crm.module.common.shared.exception;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title PotentialCustomerExceptionType.java
 * @package com.deppon.crm.module.customer.shared.exception
 * @author Administrator
 * @version 0.1 2012-3-2
 */

public enum DataDictionaryExceptionType {
	HasNoCode("i18n.dataDictionary.noCode"),
	HasNoValue("i18n.dataDictionary.noValue");
	
	private String errCode;

	private DataDictionaryExceptionType(String errCode) {
		this.errCode = errCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
}
