/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ProblemDao.java
 * @package com.deppon.crm.module.common.server.dao.impl 
 * @author Administrator
 * @version 0.1 2012-8-1
 */
package com.deppon.crm.module.common.server.dao.impl;

import java.util.List;

import com.deppon.crm.module.common.server.dao.IProblemDao;
import com.deppon.crm.module.common.shared.domain.Problem;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title ProblemDao.java
 * @package com.deppon.crm.module.common.server.dao.impl
 * @author 苏玉军
 * @version 0.1 2012-8-1
 */

public class ProblemDao extends iBatis3DaoImpl implements IProblemDao {
	private static final String NAMESACE_PRO = "com.deppon.crm.module.common.shared.domain.Problem.";
	private static final String QUERY_ALL_FEEDBACK = "queryAllFeedBack";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.common.server.dao.IProblemDao#queryAllFeedBack()
	 */
	@Override
	public List<Problem> queryAllFeedBack() {
		return this.getSqlSession().selectList(NAMESACE_PRO + QUERY_ALL_FEEDBACK);
	}

}
