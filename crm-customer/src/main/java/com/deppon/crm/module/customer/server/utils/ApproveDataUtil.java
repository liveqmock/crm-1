/**   
 * @title ApproveDataUtil.java
 * @package com.deppon.crm.module.customer.server.utils
 * @description what to do
 * @author 潘光均
 * @update 2012-11-20 上午10:07:53
 * @version V1.0   
 */
package com.deppon.crm.module.customer.server.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.customer.shared.exception.MemberExceptionType;

/**
 * @description approveData工具类  
 * @author 潘光均
 * @version 0.1 2012-11-20
 *@date 2012-11-20
 */

public class ApproveDataUtil {
	/**
	 * @作者：罗典
	 * @时间：2012-3-28
	 * @描述：通过反射将值通过字段名写入实体中
	 * @参数：obj 实体 fieldName 字段名 value 值
	 * */
	public static void setValueToPojo(Object obj, String fieldName, String value)
			throws MemberException {
		Method[] methods = obj.getClass().getMethods();
		// 得到实体字段的set方法
		String funName = "set" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1, fieldName.length());
		for (Method method : methods) {
			if (method.getName().equals(funName)) {
				String valueType = method.getParameterTypes()[0]
						.getSimpleName();
				Object valueObj = null;
				if (valueType.equals("Double")||valueType.equals("double")) {
					valueObj = Double.valueOf(value);
				} else if (valueType.equals("Integer")
						|| valueType.equals("int")) {
					valueObj = Integer.valueOf(value);
				} else if (valueType.equals("Date")) {
					SimpleDateFormat sdf = new SimpleDateFormat(
							"EEE MMM dd yyyy hh:mm:ss", Locale.US);
					try {
						if(ValidateUtil.objectIsEmpty(value)){
							valueObj = null;
						}else{
							valueObj = sdf.parse(value);
						}
					} catch (ParseException e) {
						throw new MemberException(e,
								MemberExceptionType.DateFormartError);
					}
				} else if (valueType.equals("String")) {
					valueObj = value;
				} else if (valueType.equals("Boolean")) {
					valueObj = Boolean.valueOf(value);
				} else {
					throw new MemberException(
							MemberExceptionType.NotSupportDataTypeError,
							new Object[] { valueType, fieldName });
				}
				try {
					method.invoke(obj, valueObj);
				} catch (IllegalArgumentException e) {
					throw new MemberException(e,
							MemberExceptionType.JavaReflexError);
				} catch (IllegalAccessException e) {
					throw new MemberException(e,
							MemberExceptionType.JavaReflexError);
				} catch (InvocationTargetException e) {
					throw new MemberException(e,
							MemberExceptionType.JavaReflexError);
				}
			}
		}
	}
	public static void setNewValueToPojo(Object obj, ApproveData appData){
		setValueToPojo(obj, appData.getFieldName(), appData.getNewValue());
	}
	public static void setOldValueToPojo(Object obj, ApproveData appData){
		setValueToPojo(obj, appData.getFieldName(), appData.getOldValue());
	}
}
