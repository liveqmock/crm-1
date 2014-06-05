package com.deppon.crm.module.sysmail.server.utils;

import com.deppon.foss.framework.server.context.UserContext;

public class UserUtils {
	public static String getUserName(){
		return UserContext.getCurrentUser().getUserName();
	}
}
