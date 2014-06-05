package com.deppon.crm.module.customer.workflow.function;

import java.util.HashSet;
import java.util.Set;

import com.deppon.crm.module.customer.shared.domain.Constant;

public class RoleTest {
	
	public static final boolean isContract =false;
	
	public static String  getDepartment(String caller){
		if(caller.equals(Constant.MarketingManager)){
			return Constant.MarketingId;
		}
		return "test";
	}
	public static String getRole(String role){
		return role;
	}
	
	public static Set<String> getRules(String role){
		Set<String> set = new HashSet<String>();
		set.add(role);
		return set;
	}
	
	public static boolean isContract(){
		return isContract;
	}
}
