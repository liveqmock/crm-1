package com.deppon.crm.module.customer.server.testutils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.customer.shared.domain.Account;

public class TypeUtil {
	
	
	public static void main(String[] args) throws IOException {
		createFileByClass(Account.class);
	}
	
	public static void createFileByClass(Class clazz) throws IOException{
		List<SqlEntity> list = analysisSqlEntity(clazz);
		FileHelper.write(clazz,list);
	}
	
	public static List<SqlEntity> analysisSqlEntity(Class clazz){
		List<SqlEntity> list = new ArrayList<SqlEntity>();
		
		List<Field> fields = getFields(clazz);
		for (Field field : fields) {
			SqlEntity sqlEntity = new SqlEntity();
			String fieldName = field.getName();
			String tableNFiledName = "f"+fieldName;
			String jdbcType =  getJdbcType(field.getType());
			
			sqlEntity.setClassField(field.getName());
			sqlEntity.setTableField(tableNFiledName);
			sqlEntity.setJdbcType(jdbcType);
			list.add(sqlEntity);
		}
		return list;
	}
	
	private static String getJdbcType(Class clazz) {
		String className = clazz.getName();
		FieldType type = FieldType.getFieldType(className);
		switch (type) {
			case INT:;
			case DOUBLE:;
			case SHORT:;
			case FLOAT:;
			case BOOLEAN:;
			case LONG:return "NUMERIC";
//			case BYTE:;
//			case CHAR:;
			case STRING:return "VARCHAR";
			case DATE:return "DATE";
			case OTHER:return "VARCHAR";
			default:
				//field.set(obj,createBean(typeClass));
				break;
		}
		return "";
	}
	//TODO 把过滤字段 更改为 元数据
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
				if(!field.getName().equals("serialVersionUID") && !field.getType().getSimpleName().equals("List") 
					&& !field.toGenericString().contains("final") 	
					&& !field.toGenericString().contains("static")	
						){
					fieldsList.add(field);
				}
			}
		}
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if(!field.getName().equals("serialVersionUID") && !field.getType().getSimpleName().equals("List") 
					&& !field.toGenericString().contains("final") 	
					&& !field.toGenericString().contains("static")	
					){
				fieldsList.add(field);
			}
		}
		return fieldsList;
	}
	
	public static Field[] getFieldsArray(Class clazz) {
		List<Field> fieldList = getFields(clazz);
		Field[] fields = new Field[fieldList.size()];
		fieldList.toArray(fields);
		return fields;
	}
	
}
