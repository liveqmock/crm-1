package com.deppon.crm.module.scheduler.service.testUtil;

import java.lang.reflect.Field;

import com.deppon.crm.module.customer.server.utils.ValidateUtil;

/**
 * 
 * <p>
 * Spring配置文件自动生成工具<br />
 * 编写原因: 不知哪位领导要求，不能用注解，写配置。导致代码量贼大，为了减轻自己的负担，半自动化
 * 半自动的哦！！！
 * </p>
 * @title SpringIocUtil.java
 * @package com.deppon.crm.module.customer.server.testutils 
 * @author bxj
 * @version 0.2 2012-4-19
 */
public class SpringIocUtil {
	
	/*
	 * <bean id="workFLowService" class="com.deppon.crm.module.customer.server.service.impl.WorkFLowService">
	 *	<property name="workFlowDao" ref="workFlowDao"></property>
	 * </bean>
	 * 只生成上面这种格式的配置哦，亲 
	 * id为开头小写的类名
	 */
	public static void createSpringIocUtil(Class clazz){
		StringBuffer sb = new StringBuffer();
		String id = beginLowser(clazz.getSimpleName());
		String classStr = clazz.getName();
		sb.append("<bean id =\""+id+"\" class=\""+classStr+"\">\n");
		if(id.endsWith("Dao")){
			sb.append("\t<property name=\"sqlSessionFactory\" ref=\"sqlSessionFactory\" />\n");
		}else{
			Field[] list = TypeUtil.getFieldsArray(clazz);
			for (Field field : list) {
				sb.append(getProperty(field)+"\n");
			}
		}
		
		sb.append("</bean>\n");
		System.out.println(sb.toString());
	}
	
	private static String getProperty(Field field){
		StringBuffer property = new StringBuffer();
		String workFlow = field.getName();
		property.append("<property name=\""+workFlow+"\" ref=\""+workFlow+"\"></property>");
		return property.toString();
	}
	
	
	private static String beginLowser(String str){
		if(ValidateUtil.objectIsEmpty(str)){
			return str;
		}
		String startName = str.substring(0,1);
		String otherName = str.substring(1);
		return  startName.toLowerCase() + otherName;
	}
	
	
}
