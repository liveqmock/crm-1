package com.deppon.crm.module.authorization.server.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.foss.framework.server.context.UserContext;

public class VirtualPositionUtils {
	public static String getCurrentUserId(){
		User user = (User) UserContext.getCurrentUser();
		return user.getId();
	}
	
	/**
	 * 
	 * <p>
	 * 找到需要新增的列表<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-27
	 * @param newList
	 * @param orgList
	 * @return
	 * List<String>
	 */
	public static List<String> toAddList(List<String> newList,List<String> orgList){
		List<String> result = new ArrayList<String>();
		for(String str : newList){
			if(!orgList.contains(str)){
				result.add(str);
			}
		}
		return result;
	}
	
	/**
	 * 
	 * <p>
	 * 找到需要删除的列表<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-11-27
	 * @param newList
	 * @param orgList
	 * @return
	 * List<String>
	 */
	public static List<String> toDeleteList(List<String> newList,List<String> orgList){
		List<String> result = new ArrayList<String>();
		for(String str : orgList){
			if(!newList.contains(str)){
				result.add(str);
			}
		}
		return result;
	}
}
