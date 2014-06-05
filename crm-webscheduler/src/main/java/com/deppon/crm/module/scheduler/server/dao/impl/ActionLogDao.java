package com.deppon.crm.module.scheduler.server.dao.impl;

import com.deppon.crm.module.scheduler.server.dao.IActionLogDao;
import com.deppon.crm.module.scheduler.shared.domain.ActionLog;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
import java.util.List;
/**
 * 
 * <p>
 * Description:Action日志Dao实现类<br />
 * </p>
 * @title ActionLogDao.java
 * @package com.deppon.crm.module.scheduler.server.dao.impl 
 * @author 华龙
 * @version 0.1 2012-11-19
 */
public class ActionLogDao extends iBatis3DaoImpl implements IActionLogDao {
	/**
	 * 
	 * <p>
	 * Description:查询Action日志<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-11-19
	 * @return List<ActionLog>
	 */
	public List<ActionLog> findActionLogList() {
		return getSqlSession().selectList("findActionLogList");
	}
}