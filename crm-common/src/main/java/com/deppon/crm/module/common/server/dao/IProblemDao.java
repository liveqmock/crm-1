/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IProblemDao.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author Administrator
 * @version 0.1 2012-8-1
 */
package com.deppon.crm.module.common.server.dao;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.Problem;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IProblemDao.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author 苏玉军
 * @version 0.1 2012-8-1
 */

public interface IProblemDao {
	public List<Problem> queryAllFeedBack();
}
