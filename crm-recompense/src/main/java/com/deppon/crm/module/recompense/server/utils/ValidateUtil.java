package com.deppon.crm.module.recompense.server.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @description 验证的工具类
 * @author 鲍相江
 * @version 0.1 2012-1-11
 * @date 2012-1-11
 */
public class ValidateUtil {

	public final static String MESSAGE = "message";
	public final static String VALUE = "value";

	/**
	 * 
	 * @description function.
	 * @author 鲍相江
	 * @version 0.1 2012-1-7
	 * @param
	 * @date 2012-1-7
	 * @return boolean
	 * @update 2012-1-7 下午3:34:40
	 */
	public static boolean objectIsEmpty(Object obj) {
		try {
			Class clazz = obj.getClass();
			Field[] fields = clazz.getDeclaredFields();
			Field.setAccessible(fields, true);
			for (int i = 0; i < fields.length; i++) {
				if (!filedIsEmpty(fields[i].get(obj))) {
					;
					return false;
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 
	 * @description function.
	 * @author 鲍相江
	 * @version 0.1 2012-1-7
	 * @param
	 * @date 2012-1-7
	 * @return boolean
	 * @update 2012-1-7 下午2:51:18
	 */
	public static boolean filedIsEmpty(Object obj) {
		if (obj == null)
			return true;
		if (obj instanceof String) {
			System.out.println(obj);
			System.out.println(((String) obj).trim().equals(""));
			if (((String) obj).trim().equals("")) {
				return true;
			}
		} else if (obj instanceof List) {
			List list = (List) obj;
			return list.isEmpty();
		} else if (obj instanceof Set) {
			Set set = (Set) obj;
			return set.isEmpty();
		} else if (obj instanceof Map) {
			Map map = (Map) obj;
			return map.isEmpty();
		}
		return false;
	}

	/**
	 * 
	 * @description 根据List定制检查是否为空.
	 * @author 鲍相江
	 * @version 0.1 2012-1-13
	 * @param data
	 *            其中Map里面 value:检查的值。 message：错误返回信息
	 * @date 2012-1-13
	 * @return String
	 * @update 2012-1-13 上午11:27:43
	 */
	public static List<String> validateEmpty(List<Map<String, Object>> data) {
		List<String> messages = new ArrayList<String>();
		for (Map<String, Object> map : data) {
			Object value = map.get(VALUE);
			if (filedIsEmpty(value)) {
				Object obj = map.get(MESSAGE);
				if (obj instanceof String) {
					String message = (String) obj;
					messages.add(message);
				}
			}
		}
		return messages;
	}

	public static Map<String, Object> createValiMap(Object value, String message) {
		Map<String, Object> valiMap = new HashMap<String, Object>();
		valiMap.put(VALUE, value);
		valiMap.put(MESSAGE, message);
		return valiMap;
	}
}
