package com.deppon.crm.module.scheduler.server.dao;

import com.deppon.crm.module.scheduler.shared.domain.ActionLog;
import java.util.List;
/**
 * 
 * <p>
 * Description:Action日志Dao接口<br />
 * </p>
 * @title ActionLogDao.java
 * @package com.deppon.crm.module.scheduler.server.dao.impl 
 * @author 华龙
 * @version 0.1 2012-11-19
 */
public abstract interface IActionLogDao {
	/**
	 * 
	 * <p>
	 * Description:查询Action日志<br />
	 * </p>
	 * @author 华龙
	 * @version 0.1 2012-11-19
	 * @return
	 * List<ActionLog>
	 */
	public abstract List<ActionLog> findActionLogList();
}