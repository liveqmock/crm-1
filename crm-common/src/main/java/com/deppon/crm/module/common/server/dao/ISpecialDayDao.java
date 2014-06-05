package com.deppon.crm.module.common.server.dao;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.common.shared.domain.SpecialDay;



/**
 
 * @description：工作日计算DAO接口
 * @author 华龙
 * @version 1.0
 * @date 2012-10-29上午11:13:34
 */
public interface ISpecialDayDao {
	/**
	 * 增加特殊日
	 * 
	 * @param specialDay
	 */
	public void addSpecialDay(SpecialDay specialDay);

	/**
	 * 修改特殊日类型
	 * 
	 * @param specialDay
	 */
	public void updateSpecialDayById(SpecialDay specialDay);

	/**
	 * 删除特殊日
	 * 
	 * @param id
	 */
	public void deleteSpecialDayById(String id);

	/**
	 * 查询是否存在当前特殊日
	 * 
	 * @param specialDate
	 * @return
	 */
	public SpecialDay getSpecialDayByDate(Date specialDate);

	/**
	 * 获取特殊日
	 * 
	 * @param id
	 * @return
	 */
	public SpecialDay getSpecialDayById(String id);

	/**
	 * 查询范围内特殊日
	 * 
	 * @param startDate
	 * @param endDate
	 * @param type
	 * @return
	 */
	public List<SpecialDay> getSpecialDayList(Date startDate, Date endDate,
			String type);

	/**
	 * 时间范围内周一到周五的放假天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<SpecialDay> getSpecialHolidayList(Date startDate, Date endDate);

	/**
	 * 时间范围内周六和周日的工作日
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<SpecialDay> getSpecialWorkdayList(Date startDate, Date endDate);


	}


