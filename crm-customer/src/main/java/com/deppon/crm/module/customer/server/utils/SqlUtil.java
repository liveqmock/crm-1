package com.deppon.crm.module.customer.server.utils;

import java.util.List;

import org.springframework.util.CollectionUtils;
/**
 * 
 * <p>
 * Description:sql工具类<br />
 * </p>
 * @title SqlUtil.java
 * @package com.deppon.crm.module.customer.server.utils 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class SqlUtil {
	/**
	 * 
	 * <p>
	 * Description:List<String>为string,用于in查询<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param list
	 * @return
	 * String
	 */
	public static String getInSql(List<String> list){
		//新建stringbuffer
		StringBuffer sb = new StringBuffer();
		//如果集合不为空
		if (!CollectionUtils.isEmpty(list)) {
			//循环
			for (int i=0;i<list.size();i++) {
				//如果不是最后一个
				if(i <list.size() -1 ){
					sb.append("'"+list.get(i)+"',");
				}else{
					sb.append("'"+list.get(i)+"'");
				}
			}
		}
		return sb.toString();
	}
	/**
	 * 
	 * <p>
	 * Description:模糊查询条件封装<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param field
	 * @return
	 * String
	 */
	public static String getLikeField(String field){
		//校验是否为空
		if(ValidateUtil.objectIsEmpty(field)) {
			return field;
		}
		//%%"+field+"%";
		 return "%"+field+"%";
	}
}
