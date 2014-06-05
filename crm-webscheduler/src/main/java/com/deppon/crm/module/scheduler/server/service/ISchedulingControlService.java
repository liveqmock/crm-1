package com.deppon.crm.module.scheduler.server.service;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.scheduler.shared.domain.SchedulingControl;

/**
 * @description 定时任务调度控制Service.
 * @author 安小虎
 * @version 0.1
 * @date 2012-5-31
 */

public interface ISchedulingControlService {

	List<SchedulingControl> searchListByCondition(SchedulingControl sc,
			Date reportTime1, Date buzDate1, int start, int limit);

	List<SchedulingControl> searchNeedExecuteList();

	Boolean update(SchedulingControl sc);

	List searchValues();

	String searchValueByKey(String _key);
	/**
	 * 
	 * <p>
	 * Description:通过表名查询待执行的schedule<br />
	 * </p>
	 * @author 李盛
	 * @version 0.1 2012-10-12
	 * @param tableName 表名
	 * @return
	 * List<SchedulingControl>
	 */
	public List<SchedulingControl> searchNeedExecuteListByTableName(String tableName);

	/**
	 * 
	 * <p>
	 * Description:更新执行的schedule中间表<br />
	 * </p>
	 * @author 李盛
	 * @version 0.1 2012-10-12
	 * @param sc 待更新的schedule执行记录
	 * @return
	 * Boolean
	 */
	public boolean updateSchedulingControlBefore(List<SchedulingControl> scList);
	
	public boolean updateSchedulingControlEnd(List<SchedulingControl> scList);
	
}
