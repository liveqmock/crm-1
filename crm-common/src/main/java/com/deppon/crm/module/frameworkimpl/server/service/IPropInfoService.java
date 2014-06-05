/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IActiveMQService.java
 * @package com.deppon.crm.module.frameworkimpl.server.service 
 * @author ZhuPJ
 * @version 0.1 2013-12-7
 */
package com.deppon.crm.module.frameworkimpl.server.service;

import java.util.Map;

/**   
 * <p>
 * Description: 从数据库获取配置信息<br />
 * </p>
 * @title IPropInfoService.java
 * @package com.deppon.crm.module.frameworkimpl.server.service 
 * @author ZhuPJ
 * @version 0.1 2013-12-7
 */

public interface IPropInfoService {
	// 获取Prop信息
	public Map<String, String> getPropInfo();
}
