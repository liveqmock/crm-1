/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ProblemService.java
 * @package com.deppon.crm.module.common.server.service.impl 
 * @author Administrator
 * @version 0.1 2012-8-1
 */
package com.deppon.crm.module.common.server.service.impl;

import java.util.List;

import com.deppon.crm.module.common.server.dao.IProblemDao;
import com.deppon.crm.module.common.server.service.IProblemService;
import com.deppon.crm.module.common.shared.domain.Problem;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ProblemService.java
 * @package com.deppon.crm.module.common.server.service.impl 
 * @author 苏玉军
 * @version 0.1 2012-8-1
 */

public class ProblemService implements IProblemService {
	public IProblemDao pDao;
	
	public IProblemDao getpDao() {
		return pDao;
	}

	public void setpDao(IProblemDao pDao) {
		this.pDao = pDao;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.common.server.service.IProblemService#queryAllFeedBack()
	 */
	@Override
	public List<Problem> queryAllFeedBack() {
		return pDao.queryAllFeedBack();
	}

}
