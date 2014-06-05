package com.deppon.crm.module.client.common.util;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.exception.ErrorMessageCode;

public class Check {

	public static void notNull(Object source,String errorMsg) throws CrmBusinessException{
		if (source==null) {
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_NULL, errorMsg);
		}
	}
	
	public static void notEmpty(String source,String errorMsg) throws CrmBusinessException{
		if ("".equals(source)) {
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_NULL, errorMsg);
		}
	}
	
	public static void notNullOrEmpty(String source,String errorMsg) throws CrmBusinessException{
		
		if (source==null || "".equals(source)) {
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_NULL, errorMsg);
		}
	}
	
	public static void notZero(int size,String errorMsg) throws CrmBusinessException{
		if (size==0) {
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_NULL, errorMsg);
		}
	}
}
