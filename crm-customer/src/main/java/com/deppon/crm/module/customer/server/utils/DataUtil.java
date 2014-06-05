package com.deppon.crm.module.customer.server.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.deppon.crm.module.customer.server.manager.impl.ContractWorkflowManager;

/**
 * 
 * <p>
 * 数据转换工具<br />
 * </p>
 * @title DataUtil.java
 * @package com.deppon.crm.module.customer.server.utils 
 * @author bxj
 * @version 0.2 2012-4-19
 */
public class DataUtil {
	private final static Logger logger = Logger.getLogger(DataUtil.class);

	/**
	 * 
	 * <p>
	 * 把弱类型的值装到obj里面<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-20
	 * @param map
	 * @param obj
	 * void
	 */
	public static void mapValueToObject(Map<String,Object> map,Object obj){
		Field[] array = getFieldsArray(obj.getClass());
		Field.setAccessible(array, true);
		for (Field field : array) {
			String fieldName = field.getName();
			try {
				field.set(obj,map.get(fieldName));
			} catch (IllegalArgumentException e) {
				logger.info("坑爹的程序员鲍相江,写的代码有bug", e);
			} catch (IllegalAccessException e) {
				logger.info("坑爹的程序员鲍相江,写的代码有bug", e);
			}
		}
	}
	
	/**
	 * 
	 * <p>
	 * 把强类型转换为弱类型<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-19
	 * @param obj
	 * @return
	 * Map
	 */
	public static Map<String,Object> changeObjectToMap(Object obj){
		Map<String,Object> map = new HashMap<String,Object>();
		Field[] array = getFieldsArray(obj.getClass());
		Field.setAccessible(array, true);
		for (Field field : array) {
			String fieldName = field.getName();
			try {
				map.put(fieldName, field.get(obj));
			} catch (IllegalArgumentException e) {
				logger.info("坑爹的程序员鲍相江,写的代码有bug", e);
			} catch (IllegalAccessException e) {
				logger.info("坑爹的程序员鲍相江,写的代码有bug", e);
			}
		}
		return map;
	}
	
	private static Field[] getFieldsArray(Class clazz) {
		List<Field> list = getFields(clazz);
		Field[] arry = new Field[list.size()];
		int i = 0;
		for (Field field : list) {
			arry[i++] = field;
		}
		return arry;
	}

	private static List<Field> getFields(Class clazz) {
		List<Field> fieldsList = new ArrayList<Field>();
		Class parentClazz = clazz;
		while(true){
			parentClazz = parentClazz.getSuperclass();
			if(parentClazz.getSimpleName().equals("Object")){
				break;
			}
			Field[] superFields = parentClazz.getDeclaredFields();
			for (Field field : superFields) {
				if(!field.toGenericString().contains("final") 	
					&& !field.toGenericString().contains("static")	
						){
					fieldsList.add(field);
				}
			}
		}
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if(!field.toGenericString().contains("final") 	
					&& !field.toGenericString().contains("static")	
					){
				fieldsList.add(field);
			}
		}
		return fieldsList;
	}
}
