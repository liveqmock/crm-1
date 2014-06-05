package com.deppon.crm.module.customer.server.testutils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.Member;

public class LuoDian {
	
	
	public static <T> T luo(Class<T> clazz,List<ApproveData> datas) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, NoSuchMethodException{
		T obj = clazz.newInstance();
		
		for (ApproveData approveData : datas) {
			String fieldName = approveData.getFieldName();
			String value = approveData.getOldValue();
			
			String methodName = getMethodName(fieldName);
			
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				if(method.getName().equals(methodName)){
					method.invoke(obj,value);
				}
			}
			
		}
		return obj;
	}
	
	public static String getMethodName(String fieldName){
		StringBuffer sb = new StringBuffer();
		sb.append("set");
		String rex = fieldName.substring(0,1);
		String other = fieldName.substring(1,fieldName.length());
		sb.append(StringUtils.upperCase(rex));
		sb.append(other);
		return sb.toString();
	}
	
	public static void main(String[] args) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
		ApproveData date =new ApproveData();
		date.setFieldName("id");
		date.setOldValue("123");
		date.setNewValue("456");
		
		List<ApproveData> list = new ArrayList<ApproveData>();
		
		list.add(date);
		
		Member member = LuoDian.luo(Member.class,list);
		System.out.println(member.getId());
	}
}
