/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IProblemManager.java
 * @package com.deppon.crm.module.common.server.manager 
 * @author Administrator
 * @version 0.1 2012-8-1
 */
package com.deppon.crm.module.common.server.manager;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.Problem;

/**   
 * <p>
 * 问题反馈功能<br />
 * </p>
 * @title IProblemManager.java
 * @package com.deppon.crm.module.common.server.manager 
 * @author 苏玉军
 * @version 0.1 2012-8-1
 */

public interface IProblemManager {

	static final String SHOW_PRO="SHOW";
	static final String HIDE_PRO="HIDE";
	public List<Problem> queryAllFeedBack();
}
