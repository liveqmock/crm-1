package com.deppon.crm.module.duty.server.util;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Date;

public class DateCreateUtils {
	public static <T> T createBean(Class<T> objectClass){
		 try {
			T obj = objectClass.newInstance();
			Field[] fields = TypeUtil.getFieldsArray(objectClass);
			Field.setAccessible(fields, true);
			for (Field field : fields) {
				Class typeClass = field.getType();
				String  represent =field.toGenericString();
				String className = typeClass.getName();
				FieldType type = FieldType.getFieldType(className);
				switch (type) {
					case INT:field.set(obj, 1);break;
					case DOUBLE:field.set(obj,2d);break;
					case SHORT:field.set(obj,(short)2);break;
					case FLOAT:field.set(obj, 1f);break;
					case LONG:field.set(obj, 1L);break;
					case BYTE:field.set(obj,(byte)1);break;
					case CHAR:field.set(obj, 'a');break;
					case BOOLEAN:field.set(obj, true);break;
					case STRING:field.set(obj, "111");break;
					case DATE:field.set(obj, new Date());break;
					default:
						//field.set(obj,createBean(typeClass));
						break;
				}
				
			}
			return obj;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public static <T> T createBean(Class<T> objectClass,Object[] parameters){
		 try {
			T obj = objectClass.newInstance();
			Field[] fields = objectClass.getDeclaredFields();
			Field.setAccessible(fields, true);
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				Object parameter = parameters[i];
				Class typeClass = field.getType();
				String className = typeClass.getName();
				FieldType type = FieldType.getFieldType(className);
				field.set(obj, parameter);
			}
			return obj;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
