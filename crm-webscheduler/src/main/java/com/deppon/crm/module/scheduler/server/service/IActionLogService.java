package com.deppon.crm.module.scheduler.server.service;

import com.deppon.crm.module.scheduler.shared.domain.ActionLog;
import java.util.List;
/**
 * 
 * <p>
 * Description:ActionLog服务接口<br />
 * </p>
 * @title ActionLogService.java
 * @package com.deppon.crm.module.scheduler.server.service.impl 
 * @author 华龙
 * @version 0.1 2012-11-19
 */
public abstract interface IActionLogService
{
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