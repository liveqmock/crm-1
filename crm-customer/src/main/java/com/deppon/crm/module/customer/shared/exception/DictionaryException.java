package com.deppon.crm.module.customer.shared.exception;

import com.deppon.foss.framework.exception.GeneralException;
/***
 * 
 * <p>
 * Description:数据字典异常<br />
 * </p>
 * @title DictionaryException.java
 * @package com.deppon.crm.module.customer.shared.exception 
 * @author 王明明
 * @version 0.1 2013-7-20
 */
public class DictionaryException extends GeneralException {

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 1L;
	/**
	 * constructer
	 */
	public DictionaryException(DictionnaryExcetionType errorType) {
		super();
		this.errCode = errorType.getErrCode();
	}
	
	public DictionaryException(DictionnaryExcetionType errorType,Object... arguments){
		this(errorType);
		super.setErrorArguments(arguments);
	}
}
