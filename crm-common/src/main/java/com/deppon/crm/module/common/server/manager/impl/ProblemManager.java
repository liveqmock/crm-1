/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ProblemManager.java
 * @package com.deppon.crm.module.common.server.manager.impl 
 * @author Administrator
 * @version 0.1 2012-8-1
 */
package com.deppon.crm.module.common.server.manager.impl;

import java.util.List;

import com.deppon.crm.module.common.server.manager.IProblemManager;
import com.deppon.crm.module.common.server.service.IProblemService;
import com.deppon.crm.module.common.shared.domain.Problem;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ProblemManager.java
 * @package com.deppon.crm.module.common.server.manager.impl 
 * @author 苏玉军
 * @version 0.1 2012-8-1
 */

public class ProblemManager implements IProblemManager {
	public IProblemService pService;
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.manager.IProblemManager#queryAllFeedBack()
	 */
	public IProblemService getpService() {
		return pService;
	}

	public void setpService(IProblemService pService) {
		this.pService = pService;
	}

	@Override
	public List<Problem> queryAllFeedBack() {
		return pService.queryAllFeedBack();
	}

}
