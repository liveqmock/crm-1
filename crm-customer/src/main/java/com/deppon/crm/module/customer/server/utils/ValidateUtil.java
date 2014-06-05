package com.deppon.crm.module.customer.server.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * <p>
 * Description:校验工具类<br />
 * </p>
 * 
 * @title ValidateUtil.java
 * @package com.deppon.crm.module.customer.server.utils
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class ValidateUtil {
	/**
	 * 
	 * <p>
	 * Description:校验对象是否为空<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param obj
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	public static boolean objectIsEmpty(Object obj) {
		// 如果为空直接返回false
		if (obj == null) {
			return true;
		}
		// 如果类型是String
		if (obj instanceof String) {
			// 如果为""
			if (((String) obj).trim().equals("")) {
				return true;
			}
			// 如果为list
		} else if (obj instanceof List) {
			// 进行类型转换
			List list = (List) obj;
			// 返回是否为空
			return list.isEmpty();
			// 如果为set
		} else if (obj instanceof Set) {
			// 进行类型转换
			Set set = (Set) obj;
			// 是否为空
			return set.isEmpty();
			// 如果为map
		} else if (obj instanceof Map) {
			// 进行类型转换
			Map map = (Map) obj;
			// 判断
			return map.isEmpty();
		}
		// 其他类型直接返回false
		return false;
	}

	/**
	 * 
	 * <p>
	 * Description:校验对象是否相等<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param target
	 * @param self
	 * @return boolean
	 */
	public static boolean isSame(String target, String self) {
		// 如果都为空
		if (ValidateUtil.objectIsEmpty(target) && ValidateUtil.objectIsEmpty(self)) {
			// 返回true
			return true;
			// 一个为空
		} else if (!ValidateUtil.objectIsEmpty(target) && target.equals(self)) {
			// 返回true
			return true;
		} else if (!ValidateUtil.objectIsEmpty(self) && self.equals(target)) {
			// 返回true
			return true;
		}
		// 一个为空
		return false;
	}
}
