package com.deppon.crm.module.scheduler.server.service.impl;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.scheduler.server.dao.ISchedulingControlDao;
import com.deppon.crm.module.scheduler.server.service.ISchedulingControlService;
import com.deppon.crm.module.scheduler.shared.domain.SchedulingControl;

/**
 * @description 定时任务调度控制Service.
 * @author 安小虎
 * @version 0.1
 * @date 2012-5-31
 */

public class SchedulingControlService implements ISchedulingControlService {

	private ISchedulingControlDao schedulingControlDao;

	public ISchedulingControlDao getSchedulingControlDao() {
		return schedulingControlDao;
	}

	public void setSchedulingControlDao(
			ISchedulingControlDao schedulingControlDao) {
		this.schedulingControlDao = schedulingControlDao;
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.scheduler.server.service.ISchedulingControlService#updateByTableName(com.deppon.crm.module.scheduler.shared.domain.SchedulingControl)
	 */
	@Override
	public Boolean update(SchedulingControl sc) {
		return this.schedulingControlDao.update(sc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.scheduler.server.service.ISchedulingControlService
	 * #selectListByCondition()
	 */
	@Override
	public List<SchedulingControl> searchNeedExecuteList() {
		return this.schedulingControlDao.searchNeedExecuteList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.scheduler.server.service.ISchedulingControlService
	 * #searchListByCondition
	 * (com.deppon.crm.module.scheduler.shared.domain.SchedulingControl)
	 */
	@Override
	public List<SchedulingControl> searchListByCondition(SchedulingControl sc,
			Date reportTime1, Date buzDate1, int start, int limit) {
		return this.schedulingControlDao.searchListByCondition(sc, reportTime1,
				buzDate1, start, limit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.scheduler.server.service.ISchedulingControlService
	 * #searchValues()
	 */
	@Override
	public List searchValues() {
		return this.schedulingControlDao.searchValues();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.scheduler.server.service.ISchedulingControlService
	 * #searchValueByKey(java.lang.String)
	 */
	@Override
	public String searchValueByKey(String _key) {
		return this.schedulingControlDao.searchValueByKey(_key);
	}
	@Override
	public List<SchedulingControl> searchNeedExecuteListByTableName(
			String tableName) {
		
		return schedulingControlDao.searchNeedExecuteListByTableName(tableName);
	}
	
	/*
	 * 执行业务前修改调度表数据
	 */
	public boolean updateSchedulingControlBefore(List<SchedulingControl> scList) {
		if (scList != null && scList.size() > 0) {
			SchedulingControl _sc = null;
			for (SchedulingControl schedulingControl : scList) {
				_sc = new SchedulingControl();
				_sc.setId(schedulingControl.getId());
				_sc.setCrmTime(new Date());
				_sc.setState("2");// 异常
				this.schedulingControlDao.update(_sc);
			}
		}
		return true;
	}

	/*
	 * 执行业务后修改调度表数据
	 */
	public boolean updateSchedulingControlEnd(List<SchedulingControl> scList) {
		if (scList != null && scList.size() > 0) {
			SchedulingControl _sc = null;
			for (SchedulingControl schedulingControl : scList) {
				_sc = new SchedulingControl();
				_sc.setId(schedulingControl.getId());
				_sc.setCrmEndTime(new Date());
				_sc.setState("1");// 正常
				this.schedulingControlDao.update(_sc);
			}
		}
		return true;
	}
}
