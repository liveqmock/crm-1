package com.deppon.crm.module.authorization.server.util;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**   
 * <p>
 * Description:权限工具类<br />
 * </p>
 * @title AuthorizeUtil.java
 * @package com.deppon.crm.module.authorization.server.util 
 * @author 李国文
 * @version 0.1 2012-12-27
 */
public class AuthorizeUtil {
	/**
	 * <p>
	 * Description:供角色分配部门ID调用<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2012-12-24
	 * @param deptId
	 * @return
	 * List<String>
	 */
	public static List<String> parseStringToList(String deptId) {
		List<String> list = null;
		if (!StringUtils.isEmpty(deptId)) {
			String[] deptIds = deptId.split(",");
			list = Arrays.asList(deptIds);
		}
		return list;
	}
}
