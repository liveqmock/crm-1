package com.deppon.crm.module.customer.shared.exception;

/**
 * 
 * <p>
 * Description:数据字典异常类型<br />
 * </p>
 * @title DictionnaryExcetionType.java
 * @package com.deppon.crm.module.customer.shared.exception 
 * @author 王明明
 * @version 0.1 2013-7-20
 */
public enum DictionnaryExcetionType {
	
	CODE_DESC_EXITS("i18n.dictionnary.code_desc_exists"),
	CODE_DESC_IS_NULL("i18n.dictionnary.code_desc_isNull");

	private String errCode;

	private DictionnaryExcetionType(String errorCode){
		this.errCode = errorCode;
	}

	public String getErrCode() {
		return errCode;
	}
	
}
