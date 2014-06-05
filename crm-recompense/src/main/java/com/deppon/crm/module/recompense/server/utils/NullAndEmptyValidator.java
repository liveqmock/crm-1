/**   
 * @title NullAndEmptyValidator.java
 * @package com.deppon.crm.utils
 * @description what to do
 * @author 潘光均
 * @update 2012-2-23 上午8:54:24
 * @version V1.0   
 */
package com.deppon.crm.module.recompense.server.utils;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.recompense.shared.exception.RecompenseDaoException;

/**
 * @description 实现xxx  
 * @author 潘光均
 * @version 0.1 2012-2-23
 *@date 2012-2-23
 */

public class NullAndEmptyValidator {
	/**
	 * @description id为空抛出异常
	 * @author 潘光均
	 * @version 0.1 2012-1-31
	 * @param id
	 *            :检验字符串
	 * @date 2012-1-31
	 * @return void
	 * @update 2012-2-21 上午9:19
	 */
	public static  void stringNullPreform(String id) {
		//校验是否为空
		if (StringUtils.isNotEmpty(id)) {
			throw new RecompenseDaoException("the object is already exists!");
		}
	}

	/**
	 * @description 实体为空抛出异常.
	 * @author 潘光均
	 * @version 0.1 2012-2-21
	 * @param obj
	 *            :检测实体
	 * @return 
	 * @date 2012-2-21
	 * @return void
	 * @update 2012-2-21 上午10:44:56
	 */
	public static  void objectNullPreform(Object obj) {
		//是否为空
		if (null == obj) {
			throw new RecompenseDaoException("cann't insert null object!");
		}
	}

	/**
	 * @description 检查一个字符串是否为空或者为空串.
	 * @author 潘光均
	 * @version 0.1 2012-2-21
	 * @param str
	 * @date 2012-2-21
	 * @return boolean
	 * @update 2012-2-21 上午10:55:57
	 */
	public static  boolean checkStringNullAndEmpty(String str) {
		//不为空 返回true
		if (StringUtils.isBlank(str)) {
			return true;
		}
		return false;
	}
}
