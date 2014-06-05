package com.deppon.crm.module.client.common.util;

import java.util.List;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;

/**
 * @描述：接口参数校验
 * @时间：2012-12-14
 * @作者：罗典
 * */
public class NullOrEmptyValidator {
	public static void checkNull(Object obj, String objName)
			throws CrmBusinessException {
		if (obj == null) {
			throw new CrmBusinessException("1007", objName);
		}
	}
	
	public static void checkNull(Object obj, String objName,String errorCode)
			throws CrmBusinessException {
		if (obj == null) {
			throw new CrmBusinessException(errorCode, objName);
		}
	}
	
	public static void checkEmpty(Object obj, String objName)
			throws CrmBusinessException {
		if (obj == null) {
			throw new CrmBusinessException("1007", objName);
		}
		if(obj instanceof String && obj.toString().equals("")){
			throw new CrmBusinessException("1007", objName);
		}
		if(obj instanceof List && ((List) obj).size()==0){
			throw new CrmBusinessException("1007", objName);
		}
	}
}
