package com.deppon.crm.module.common.server.manager;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.common.shared.domain.SpecialDay;

public interface ISpecialDayManager {
	/**
	 * 
	 * @Description:增加特殊日
	 * @author huangzhanming
	 * @param specialDay
	 * @return void
	 * @date 2012-11-9 下午4:22:48
	 * @version V1.0
	 */
	void addSpecialDay(SpecialDay specialDay);

	/**
	 * 
	 * @Description: 修改特殊日类型
	 * @author huangzhanming
	 * @param specialDay
	 * @return void
	 * @date 2012-11-9 下午4:22:59
	 * @version V1.0
	 */
	void updateSpecialDayById(SpecialDay specialDay);

	/**
	 * 
	 * @Description: 删除特殊日
	 * @author huangzhanming
	 * @param id
	 * @return void
	 * @date 2012-11-9 下午4:23:13
	 * @version V1.0
	 */
	void deleteSpecialDayById(String id);

	/**
	 * 
	 * @Description: 获取特殊日
	 * @author huangzhanming
	 * @param id
	 * @return SpecialDay
	 * @date 2012-11-9 下午4:23:21
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
	 * @date 2012-11-9 下午4:23:34
	 * @version V1.0
	 */
	List<SpecialDay> getSpecialDayList(Date startDate, Date endDate, String type);

	/**
	 * 
	 * @Description: 时间范围内周一到周五的放假天数
	 * @author huangzhanming
	 * @param startDate
	 * @param endDate
	 * @return int
	 * @date 2012-11-9 下午4:23:46
	 * @version V1.0
	 */
	int getSpecialHolidays(Date startDate, Date endDate);

	/**
	 * 
	 * @Description: 时间范围内周六和周日的工作日
	 * @author huangzhanming
	 * @param startDate
	 * @param endDate
	 * @return int
	 * @date 2012-11-9 下午4:24:00
	 * @version V1.0
	 */
	int getSpecialWorkdays(Date startDate, Date endDate);

	/**
	 * 
	 * @Description: 时间范围内实际的工作日
	 * @author huangzhanming
	 * @param startDate
	 * @param endDate
	 * @return
	 * @return int
	 * @date 2012-11-9 下午4:24:11
	 * @version V1.0
	 */
	int getActualWorkdays(Date startDate, Date endDate);

	/**
	 * 
	 * <p>
	 * Description:p量删除节假日<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-4-3
	 * @param id
	 *            void
	 */
	void deleteSpecialDayList(String[] ids);

	int getValieWorkdays(Date startDate, Date endDate, String type);

}
