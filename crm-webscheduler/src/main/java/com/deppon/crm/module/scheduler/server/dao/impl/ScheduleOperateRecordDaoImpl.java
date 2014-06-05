package com.deppon.crm.module.scheduler.server.dao.impl;

import java.util.List;

import com.deppon.crm.module.scheduler.server.dao.IScheduleOperateRecordDao;
import com.deppon.crm.module.scheduler.shared.domain.OaEmployeeEntity;
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
public class ScheduleOperateRecordDaoImpl extends iBatis3DaoImpl implements IScheduleOperateRecordDao{

	@Override
	public void insertBatchOperateList(List operateList,ScheduleOperateEnum so) {
		switch (so) {
		//潜客新增
		case POTENTIALCUSTADD:
			this.getSqlSession().insert(""+"", operateList);
			break;
		default:
			break;
		}
	}

	@Override
	public void deleteBatchOperateList(List operateList,
			ScheduleOperateEnum so) {
		switch (so) {
		//潜客新增
		case POTENTIALCUSTADD:
			this.getSqlSession().insert(""+"", operateList);
			break;
		default:
			break;
		}
	}
}
