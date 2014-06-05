package com.deppon.crm.module.common.server.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.server.dao.ISpecialDayDao;
import com.deppon.crm.module.common.shared.domain.SpecialDay;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 
 * @description：服务补救Dao实现类
 * @author 华龙
 * @version 1.0
 * @date 2012-10-29下午2:14:17
 */
public class SpecialDayDao extends iBatis3DaoImpl implements ISpecialDayDao {
	private static final String NAMESPACE = "com.deppon.crm.module.common.shared.domain.SpecialDay.";
	private static final String ADDSPECIALDAY = "addSpecialDay";
	private static final String UPDATE_SPECIALDAY_BY_ID = "updateSpecialDayById";
	private static final String DELETE_SPECIALDAY_BY_ID = "deleteSpecialDayById";
	private static final String GET_SPECIALDAY_BY_DATE = "getSpecialDayByDate";
	private static final String GET_SPECIALDAY_BY_ID = "getSpecialDayById";
	private static final String GET_SPECIALDAYLIST = "getSpecialDayList";
	private static final String GET_SPECIALHOLIDAYDAYLIST = "getSpecialHolidayList";
	private static final String GET_SPECIALWORKDAYLIST = "getSpecialWorkdayList";
	private static final String GET_LEGALHOLIDAYS_BY_DATE = "getlegalHoliDayasByDate";

	/**
	 * 增加特殊日
	 * 
	 * @author 华龙
	 * @param specialDay
	 */
	public void addSpecialDay(SpecialDay specialDay) {
		if (null != specialDay && null == specialDay.getId()) {
			getSqlSession().insert(NAMESPACE + ADDSPECIALDAY, specialDay);

		}

	}

	/**
	 * 修改特殊日类型
	 * 
	 * @author 华龙
	 * @param specialDay
	 */
	public void updateSpecialDayById(SpecialDay specialDay) {
		if (null != specialDay && null != specialDay.getId()) {
			getSqlSession().update(NAMESPACE + UPDATE_SPECIALDAY_BY_ID,
					specialDay);

		}

	}

	/**
	 * 删除特殊日
	 * 
	 * @author 华龙
	 * @param id
	 */
	public void deleteSpecialDayById(String id) {
		if (null != id) {
			getSqlSession().delete(NAMESPACE + DELETE_SPECIALDAY_BY_ID, id);

		}
	}

	/**
	 * 查询是否存在当前特殊日
	 * 
	 * @param specialDate
	 * @return
	 */
	public SpecialDay getSpecialDayByDate(Date specialDate) {
		if (null != specialDate) {
			return (SpecialDay) getSqlSession().selectOne(
					NAMESPACE + GET_SPECIALDAY_BY_DATE, specialDate);

		}
		return null;
	}

	/**
	 * 获取特殊日
	 * 
	 * @param id
	 * @return
	 */
	public SpecialDay getSpecialDayById(String id) {
		if (null != id) {
			return (SpecialDay) getSqlSession().selectOne(
					NAMESPACE + GET_SPECIALDAY_BY_ID, id);
		}
		return null;
	}

	/**
	 * 查询范围内特殊日
	 * 
	 * @param startDate
	 * @param endDate
	 * @param type
	 * @return
	 */
	public List<SpecialDay> getSpecialDayList(Date startDate, Date endDate,
			String type) {
		Map map = new HashMap();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("type", type);
		return getSqlSession().selectList(NAMESPACE + GET_SPECIALDAYLIST, map);
	}

	/**
	 * 时间范围内周一到周五的放假天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<SpecialDay> getSpecialHolidayList(Date startDate, Date endDate) {
		Map map = new HashMap();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return getSqlSession().selectList(
				NAMESPACE + GET_SPECIALHOLIDAYDAYLIST, map);
	}

	/**
	 * 时间范围内周六和周日的工作日
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<SpecialDay> getSpecialWorkdayList(Date startDate, Date endDate) {
		Map map = new HashMap();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return getSqlSession().selectList(NAMESPACE + GET_SPECIALWORKDAYLIST,
				map);
	}

	public SpecialDay getLegalHolidaysByDate(Date specialDate) {
		if (null != specialDate) {
			return (SpecialDay) getSqlSession().selectOne(
					NAMESPACE + GET_LEGALHOLIDAYS_BY_DATE, specialDate);

		}
		return null;

	}

}
