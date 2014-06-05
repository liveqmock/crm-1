package com.deppon.crm.module.client.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.thoughtworks.xstream.XStream;

/**
 * @作者：罗典
 * @时间：2012-9-13
 * @描述：xml格式与java对象的转换
 * */
public class XmlMapperUtil {
	private static XStream xs = new XStream();
	private static String xmlHead = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
	private static List<Class> classList = new ArrayList<Class>();

	public static String writeValue(Object obj) throws CrmBusinessException {
		Check.notNull(obj, "object to xml wrong because obj is null");
		if (!classList.contains(obj.getClass())) {
			Class clazz = obj.getClass();
			xs.alias(clazz.getSimpleName(), obj.getClass());
			String className = changeUpperOrLower(clazz.getSimpleName(), 1);
			xs.alias(className, clazz);
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				Class typeClazz = field.getType();
				if (typeClazz.isAssignableFrom(List.class)) {
					Type type = field.getGenericType();
					if (type instanceof ParameterizedType) {
						ParameterizedType pt = (ParameterizedType) type;
						Class genericClazz = (Class) pt
								.getActualTypeArguments()[0];
						className = changeUpperOrLower(
								genericClazz.getSimpleName(), 1);
						xs.alias(className, genericClazz);
					}
				}
			}
			classList.add(obj.getClass());
		}
		String xmlString = xmlHead + xs.toXML(obj);
		return xmlString;
	}

	public static Object readValue(String xmlString, Class clazz)
			throws CrmBusinessException {
		Check.notNullOrEmpty(xmlString,
				"xmlString to Object wrong because xmlString is null");
		xs.alias(clazz.getSimpleName(), clazz);
		String className = changeUpperOrLower(clazz.getSimpleName(), 1);
		xs.alias(className, clazz);
		Object obj = xs.fromXML(xmlString);
		return obj;
	}

	// 首字母大写或小写 1小写，2大写
	private static String changeUpperOrLower(String fieldName, int type) {
		if (type == 1) {
			fieldName = fieldName.substring(0, 1).toLowerCase()
					+ fieldName.substring(1, fieldName.length());
		}
		if (type == 2) {
			fieldName = fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1, fieldName.length());
		}
		return fieldName;
	}
}
