package com.deppon.crm.module.scheduler.server.dao;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.scheduler.shared.domain.SchedulingControl;

/**
 * @description 定时任务调度控制DAO.
 * @author 安小虎
 * @version 0.1
 * @date 2012-5-31
 */

public interface ISchedulingControlDao {

	Boolean update(SchedulingControl sc);

	List<SchedulingControl> searchNeedExecuteList();

	List<SchedulingControl> searchListByCondition(SchedulingControl sc,
			Date reportTime1, Date buzDate1, int start, int limit);

	List searchValues();

	String searchValueByKey(String _key);
	List<SchedulingControl> searchNeedExecuteListByTableName(String tableName);
}
