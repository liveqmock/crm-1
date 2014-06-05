package com.deppon.crm.module.logmoniting.server.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ValidateUtil {
	public static boolean objectIsEmpty(Object obj){
		if(obj == null) {
			return true;
		}
		if(obj instanceof String){
			if(((String)obj).trim().equals("")){
				return true;
			}
		}else if(obj instanceof List){
			List list = (List)obj;
			return list.isEmpty();
		}else if(obj instanceof Set){
			Set set = (Set) obj;
			return set.isEmpty();
		}else if(obj instanceof Map){
			Map map = (Map)obj;
			return map.isEmpty();
		}
		return false;
	}
	
	public static boolean isSame(String target,String self){
		if(ValidateUtil.objectIsEmpty(target) && ValidateUtil.objectIsEmpty(self)){
			return true;
		}else if(!ValidateUtil.objectIsEmpty(target) && target.equals(self)){
			return true;
		}else if(!ValidateUtil.objectIsEmpty(self) && self.equals(target)){
			return true;
		}
		return false;
	}
}
