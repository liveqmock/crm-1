package com.deppon.crm.module.customer.server.testutils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;

public class CreateSqlUtil {
	
//	public static String getInsertSql(Class clazz,String tableName){
//		return getInsertSql(clazz, tableName, null);
//	}
//	/**
//	 * 
//	 * <p>
//	 * 得到实体基本字段的insert sql,不包含 表与表之间的外键<br />
//	 * </p>
//	 * @author bxj
//	 * @version 0.2 2012-3-22
//	 * @param clazz
//	 * @param tableName
//	 * @return
//	 * String
//	 */
//	public static String getInsertSql(Class clazz,String tableName,String objName){
//		StringBuffer sb = new StringBuffer();
//		sb.append("insert into "+tableName+"(");
//		//String values = getValues(BaseEntity.class);
//		String values ="fid,";
//		sb.append(values);
//		
//		values = getValues(clazz,"f","");
//		sb.append(values);
//		String sql =  sb.substring(0, sb.length()-1);
//		sql += ") values( #{id},";
//		String pre = "#{";
//		if(objName !=null) pre+= objName+"."; 
//		values = getValues(clazz,pre,",jdbcType=VARCHAR}");
//		sql += values.substring(0, values.length()-1)+")";
//		return sql;
//	}
//	//#{id}
//	public static String getValues(Class clazz,String pre,String suffix){
//		StringBuffer sb = new StringBuffer();
//		Field[] fields = clazz.getDeclaredFields(); 
//		int i = 1;
//		for (Field field : fields) {
//			String fieldName = field.getName();
//			if(field.getType().getSimpleName().equals("List")){
//				continue;
//			}
//			if(!fieldName.equals("serialVersionUID")){
//				sb.append(pre+field.getName()+suffix+",");
//				//sb.append("1,");
//				i++;
//			}
//		}
//		System.out.println(i);
//		return sb.toString();
//	}
//	
	
	//jdbcType 是根据数据库表来生成的。 -----建立一个映射关系
	
	public static void main(String[] args) {
//		System.out.println(getInsertSql(Member.class,"t_cust_custbasedata"));
		List<String> field = new ArrayList<String>();
//		field.add("memberId");
//		System.out.println(getInsertSql(ShuttleAddress.class,"t_cust_shuttleaddress","shuttleAddress",field));

//		System.out.println(getInsertSql(Contact.class,"t_cust_custlinkman","contact",field));
		
//		field = getAllField(BaseEntity.class);
//		System.out.println(getInsertSql(Account.class,"t_cust_account","account",field));
		
		field.add("shuttelId");
		field.add("contactId");
		System.out.println(getInsertSql(PreferenceAddress.class,"t_cust_PreferenceAddress","preferenceAddress",field));
		
	}
	
	public static String getInsertSql(Class clazz,String tableName){
		return getInsertSql(clazz, tableName, null, null);
	}
	
	public static String getInsertSql(Class clazz,String tableName,String objName){
		return getInsertSql(clazz, tableName, objName, null);
	}
	
	public static String getInsertSql(Class clazz,String tableName,List<String> otherFields){
		return getInsertSql(clazz, tableName, null, otherFields);
	}
	
	public static String getInsertSql(Class clazz,String tableName,String objName,List<String> otherFields){
		List<String> fieldList = getAllField(clazz);
		if(otherFields !=null){
			fieldList.addAll(otherFields);
		}
		return createInsertSql(fieldList,tableName,objName);
	}
	
	public static String createInsertSql(List<String> fields,String tableName,String objName){
		StringBuffer sb = new StringBuffer();
		sb.append("insert into "+tableName+"(");
		String values = getValues(fields,"f","");
		sb.append(values);
		String sql =  sb.substring(0, sb.length()-1);
		sql += ")\n values(";
		String pre = "#{";
		if(objName !=null) pre+= objName+"."; 
		values = getValues(fields,pre,",jdbcType=VARCHAR}");
		System.out.println("values("+getValues(fields)+")");
		sql += values.substring(0, values.length()-1)+")";
		return sql;
	}
	
	public static String getValues(List<String> fields) {
		StringBuffer sb = new StringBuffer();
		for (String field : fields) {
			sb.append("1,");
		}
		return sb.substring(0,sb.length()-1);
	}

	public static String getValues(List<String> fields,String pre,String suffix){
		StringBuffer sb = new StringBuffer();
		for (String field : fields) {
			sb.append(pre+field+suffix+",");
		}
		return sb.toString();
	}
	
	
	public static List<String> getAllField(Class clazz){
		List<String> fieldList =new ArrayList<String>();
		Field[] fields = clazz.getDeclaredFields(); 
		int i = 1;
		for (Field field : fields) {
			String fieldName = field.getName();
			if(field.getType().getSimpleName().equals("List")){
				continue;
			}
			if(!fieldName.equals("serialVersionUID")){
				fieldList.add(fieldName);
			}
		}
		return fieldList;
	}
}
