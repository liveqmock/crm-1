package com.deppon.crm.module.scheduler.server.service.impl;

import java.util.List;

import com.deppon.crm.module.scheduler.server.dao.IScheduleOperateRecordDao;
import com.deppon.crm.module.scheduler.server.service.IScheduleOperateRecordService;
import com.deppon.crm.module.scheduler.shared.domain.ScheduleOperateEnum;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

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
public class ScheduleOperateRecordServiceImpl extends iBatis3DaoImpl implements IScheduleOperateRecordService{
	private IScheduleOperateRecordDao scheduleOperateRecordDao;
	@Override
	public void insertBatchOperateList(List operateList,ScheduleOperateEnum so) {
		scheduleOperateRecordDao.insertBatchOperateList(operateList,so);
	}

	@Override
	public void deleteBatchOperateList(List operateList,
			ScheduleOperateEnum so) {
		scheduleOperateRecordDao.deleteBatchOperateList(operateList,so);
	}

	public IScheduleOperateRecordDao getScheduleOperateRecordDao() {
		return scheduleOperateRecordDao;
	}

	public void setScheduleOperateRecordDao(
			IScheduleOperateRecordDao scheduleOperateRecordDao) {
		this.scheduleOperateRecordDao = scheduleOperateRecordDao;
	}
}
