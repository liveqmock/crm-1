package com.deppon.crm.module.customer.server.data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Random;

import com.deppon.crm.module.customer.server.testutils.FieldType;
import com.deppon.crm.module.customer.server.testutils.TypeUtil;
import com.deppon.crm.module.customer.shared.domain.PotentialCustomer;



public abstract class BaseData {
	
	protected static <T>T createdBean(Class<T> classes){
		 try {
			T obj = classes.newInstance();
			Field[] fields = TypeUtil.getFieldsArray(classes);
			Field.setAccessible(fields, true);

			Random rd = new Random();
			for (Field field : fields) {
				Class<?> typeClass = field.getType();
				String  fieldName =field.getName();
				String className = typeClass.getName();
				FieldType type = FieldType.getFieldType(className);
				
				switch (type) {
					case INT:field.set(obj, rd.nextInt());break;
					case DOUBLE:field.set(obj,rd.nextDouble());break;
					case SHORT:field.set(obj,rd.nextInt(Short.MAX_VALUE));break;
					case FLOAT:field.set(obj, rd.nextFloat());break;
					case LONG:field.set(obj, rd.nextLong());break;
					case BYTE:field.set(obj,rd.nextInt(Byte.MAX_VALUE));break;
					case CHAR:field.set(obj, 'a');break;
					case BOOLEAN:field.set(obj, rd.nextBoolean());break;
					case STRING:
						if(fieldName.toLowerCase().indexOf("id")!=-1){
							field.set(obj, "1");
						}else{
							field.set(obj, fieldName);
						}
						
					break;
					case DATE:field.set(obj, new Date());break;
					default:
						//field.set(obj,createBean(typeClass));
						break;
				}
				
			}
			
			try {
				Method md = classes.getMethod("setCreateUser",new Class[]{String.class});
				md.invoke(obj, "86301");
				md = classes.getMethod("setModifyUser",new Class[]{String.class});
				md.invoke(obj, "86301");

			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			return obj;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	


}
