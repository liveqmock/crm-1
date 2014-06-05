/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PropInfoUtil.java
 * @package com.deppon.crm.module.frameworkimpl.server.util 
 * @author ZhuPJ
 * @version 0.1 2013-12-7
 */
package com.deppon.crm.module.frameworkimpl.server.util;

import java.util.Map;

import com.deppon.crm.module.frameworkimpl.server.service.IPropInfoService;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PropInfoUtil.java
 * @package com.deppon.crm.module.frameworkimpl.server.util 
 * @author ZhuPJ
 * @version 0.1 2013-12-7
 */

public class PropInfoUtil {
	private static Map<String, String> map = null;
	
	public static String getValue(IPropInfoService propInfoService, String key){
		if (map == null){
			map = propInfoService.getPropInfo();
		}
		return map.get(key);
	}
}
