/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IPropDao.java
 * @package com.deppon.crm.module.frameworkimpl.server.dao 
 * @author ZhuPJ
 * @version 0.1 2013-12-7
 */
package com.deppon.crm.module.frameworkimpl.server.dao;

import java.util.List;

import com.deppon.crm.module.frameworkimpl.shared.domain.PropInfo;

/**   
 * <p>
 * Description: 从数据库获取配置信息<br />
 * </p>
 * @title IPropInfoDao.java
 * @package com.deppon.crm.module.frameworkimpl.server.dao 
 * @author ZhuPJ
 * @version 0.1 2013-12-7
 */

public interface IPropInfoDao {
	// 获取Prop信息
	public List<PropInfo> getPropInfo();
}
