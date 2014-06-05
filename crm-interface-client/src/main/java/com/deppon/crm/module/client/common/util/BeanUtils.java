package com.deppon.crm.module.client.common.util;

import java.util.LinkedList;
import java.util.List;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;

public class BeanUtils {
	
	public static Object copyProperties(Object orig,Class<?> classes) throws CrmBusinessException{
		try {
			Object obj = classes.newInstance();
			org.apache.commons.beanutils.PropertyUtils.copyProperties(obj,orig);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException("1007",e.getMessage());
		}
	}
	
	public static List<?> copyProperties(List<?> orig,Class<?> classes) throws CrmBusinessException{
		try {
			
			List<Object> list = new LinkedList<Object>();
			Object obj;
			for (Object object : orig) {
				obj = copyProperties(object,classes);
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			throw new CrmBusinessException("1007",e.getMessage());
		}
	}

}
