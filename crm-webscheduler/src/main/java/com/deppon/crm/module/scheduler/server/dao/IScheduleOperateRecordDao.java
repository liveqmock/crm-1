package com.deppon.crm.module.scheduler.server.dao;

import java.util.List;

import com.deppon.crm.module.scheduler.shared.domain.ScheduleOperateEnum;

/**
 * 
 * <p>
 * Description:定时任务操作记录表操作<br />
 * </p>
 * @title ScheduleOperateRecord.java
 * @package com.deppon.crm.module.scheduler.server.dao 
 * @author 李学兴
 * @version 0.1 2012-7-14
 */
@SuppressWarnings("rawtypes")
public interface IScheduleOperateRecordDao {
	/**
	 * <p>
	 * 批量新增日志表<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.1 2012-7-14
	 * @param operateList
	 * @return
	 * boolean
	 */
	public void insertBatchOperateList(List operateList,ScheduleOperateEnum so);
	/**
	 * <p>
	 * 批量删除,作废，降级日志表<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.1 2012-7-14
	 * @param operateList
	 * @return
	 * boolean
	 */
	public void deleteBatchOperateList(List operateList,ScheduleOperateEnum so);
	
}
