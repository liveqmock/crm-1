package com.deppon.crm.module.scheduler.server.service.impl;

import com.deppon.crm.module.scheduler.server.dao.IActionLogDao;
import com.deppon.crm.module.scheduler.server.service.IActionLogService;
import com.deppon.crm.module.scheduler.shared.domain.ActionLog;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
import java.util.List;

/**
 * 
 * <p>
 * Description:ActionLog服务实现类<br />
 * </p>
 * 
 * @title ActionLogService.java
 * @package com.deppon.crm.module.scheduler.server.service.impl
 * @author 华龙
 * @version 0.1 2012-11-19
 */
public class ActionLogService extends iBatis3DaoImpl implements
		IActionLogService {
	// 注入
	private IActionLogDao actionLogDao;

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
		return getActionLogDao().findActionLogList();
	}

	public IActionLogDao getActionLogDao() {
		return this.actionLogDao;
	}

	public void setActionLogDao(IActionLogDao actionLogDao) {
		this.actionLogDao = actionLogDao;
	}
}