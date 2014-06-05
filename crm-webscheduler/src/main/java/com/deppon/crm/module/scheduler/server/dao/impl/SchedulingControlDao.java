package com.deppon.crm.module.scheduler.server.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.scheduler.server.dao.ISchedulingControlDao;
import com.deppon.crm.module.scheduler.shared.domain.SchedulingControl;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * @description 定时控制.
 * @author 安小虎
 * @version 0.1
 * @date 2012-5-31
 */

public class SchedulingControlDao extends iBatis3DaoImpl implements
		ISchedulingControlDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.scheduler.server.dao.ISchedulingControlDao#
	 * updateByCondition
	 * (com.deppon.crm.module.scheduler.shared.domain.SchedulingControl)
	 */
	@Override
	public Boolean update(SchedulingControl sc) {
		return this.getSqlSession()
				.update(getClass().getName() + ".update", sc) > 0 ? true
				: false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.scheduler.server.dao.ISchedulingControlDao#
	 * selectListByCondition()
	 */
	@Override
	public List<SchedulingControl> searchNeedExecuteList() {
		return this.getSqlSession().selectList(
				getClass().getName() + ".searchNeedExecuteList");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.scheduler.server.dao.ISchedulingControlDao#
	 * searchListByCondition
	 * (com.deppon.crm.module.scheduler.shared.domain.SchedulingControl)
	 */
	@Override
	public List<SchedulingControl> searchListByCondition(SchedulingControl sc,
			Date reportTime1, Date buzDate1, int start, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sc", sc);
		map.put("reportTime1", reportTime1);
		map.put("buzDate1", buzDate1);
		RowBounds rowBounds = new RowBounds(start, limit);
		return this.getSqlSession()
				.selectList(getClass().getName() + ".searchListByCondition",
						map, rowBounds);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.scheduler.server.dao.ISchedulingControlDao#
	 * searchValueByKey(java.lang.String)
	 */
	@Override
	public String searchValueByKey(String _key) {
		return (String) this.getSqlSession().selectOne(
				getClass().getName() + ".searchValueByKey", _key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.scheduler.server.dao.ISchedulingControlDao#searchValues
	 * ()
	 */
	@Override
	public List searchValues() {
		return this.getSqlSession().selectList(
				getClass().getName() + ".searchValues");
	}
	@Override
	public List<SchedulingControl> searchNeedExecuteListByTableName(
			String tableName) {
		return this.getSqlSession().selectList(
				getClass().getName() + ".searchNeedExecuteListByTableName",tableName);
	}
}
