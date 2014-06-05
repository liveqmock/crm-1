package com.deppon.crm.module.common.server.service;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.common.shared.domain.SpecialDay;


public interface ISpecialDayService {
	/**
	 * 
	 * @Description: 增加特殊日
	 * @author huangzhanming
	 * @param specialDay
	 * @return void
	 * @date 2012-11-9 下午4:20:16
	 * @version V1.0
	 */
	 void addSpecialDay(SpecialDay specialDay);

	/**
	 * 
	 * @Description: 修改特殊日类型
	 * @author huangzhanming
	 * @param specialDay
	 * @return void
	 * @date 2012-11-9 下午4:20:26
	 * @version V1.0
	 */
	 void updateSpecialDayById(SpecialDay specialDay);

	/**
	 * 
	 * @Description:删除特殊日
	 * @author huangzhanming
	 * @param id
	 * @return void
	 * @date 2012-11-9 下午4:20:34
	 * @version V1.0
	 */
	 void deleteSpecialDayById(String id);

	/**
	 * 
	 * @Description: 查询是否存在当前特殊日
	 * @author huangzhanming
	 * @param specialDate
	 * @return
	 * @return SpecialDay
	 * @date 2012-11-9 下午4:20:44
	 * @version V1.0
	 * @param dateType 
	 */
	 SpecialDay getSpecialDayByDate(Date specialDate);

	/**
	 * 
	 * @Description: 获取特殊日
	 * @author huangzhanming
	 * @param id
	 * @return SpecialDay
	 * @date 2012-11-9 下午4:20:52
	 * @version V1.0
	 */
	 SpecialDay getSpecialDayById(String id);

	/**
	 * 
	 * @Description: 查询范围内特殊日
	 * @author huangzhanming
	 * @param startDate
	 * @param endDate
	 * @param type
	 * @return List<SpecialDay>
	 * @date 2012-11-9 下午4:21:06
	 * @version V1.0
	 */
	public List<SpecialDay> getSpecialDayList(Date startDate, Date endDate,
			String type);

	/**
	 * 
	 * @Description: 时间范围内周一到周五的放假天数
	 * @author huangzhanming
	 * @param startDate
	 * @param endDate
	 * @return
	 * @return List<SpecialDay>
	 * @date 2012-11-9 下午4:21:24
	 * @version V1.0
	 */
	public List<SpecialDay> getSpecialHolidayList(Date startDate, Date endDate);

	/**
	 * 
	 * @Description:时间范围内周六和周日的工作日
	 * @author huangzhanming
	 * @param startDate
	 * @param endDate
	 * @return
	 * @return List<SpecialDay>
	 * @date 2012-11-9 下午4:21:33
	 * @version V1.0
	 */
	public List<SpecialDay> getSpecialWorkdayList(Date startDate, Date endDate);

	SpecialDay getLegalHolidaysByDate(Date specialDate);

}
