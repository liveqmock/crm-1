package com.deppon.crm.module.common.server.manager.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.common.server.manager.ISpecialDayManager;
import com.deppon.crm.module.common.server.service.ISpecialDayService;
import com.deppon.crm.module.common.shared.domain.SpecialDay;
import com.deppon.crm.module.common.shared.exception.SpecialDayException;
import com.deppon.crm.module.common.shared.exception.SpecialDayExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

public class SpecialDayManager implements ISpecialDayManager {

	private ISpecialDayService specialDayService;

	public ISpecialDayService getSpecialDayService() {
		return specialDayService;
	}

	public void setSpecialDayService(ISpecialDayService specialDayService) {
		this.specialDayService = specialDayService;
	}

	/**
	 * 
	 * @Description: 时间范围内周一到周五的天数
	 * @author huangzhanming
	 * @param startDate
	 * @param endDate
	 * @return int
	 * @date 2012-11-9 下午4:18:17
	 * @version V1.0
	 */
	public static int getNormalWorkdays(Date startDate, Date endDate) {
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		int days = 0;
		while (start.compareTo(end) <= 0) {
			int day = start.get(Calendar.DAY_OF_WEEK);
			start.set(Calendar.DATE, start.get(Calendar.DATE) + 1);
			if (day == Calendar.SUNDAY || day == Calendar.SATURDAY) {
				continue;
			} else {
				days++;
			}
		}
		return days;
	}

	/**
	 * 
	 * @Description: 时间范围内周一到周五的放假天数
	 * @author huangzhanming
	 * @param startDate
	 * @param endDate
	 * @return
	 * @date 2012-11-9 下午4:18:37
	 * @version V1.0
	 */
	public int getSpecialHolidays(Date startDate, Date endDate) {
		Calendar cal = Calendar.getInstance();
		// TODO 查询后台维护数据
		List<SpecialDay> list = specialDayService.getSpecialHolidayList(
				startDate, endDate);
		int days = 0;
		for (SpecialDay specialDay : list) {
			cal.setTime(specialDay.getSpecialDate());
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if (day == Calendar.SUNDAY || day == Calendar.SATURDAY) {
				continue;
			} else {
				days++;
			}
		}
		return days;
	}

	/**
	 * 
	 * @Description: 时间范围内周六和周日的工作日
	 * @author huangzhanming
	 * @param startDate
	 * @param endDate
	 * @return
	 * @date 2012-11-9 下午4:18:51
	 * @version V1.0
	 */
	public int getSpecialWorkdays(Date startDate, Date endDate) {
		Calendar cal = Calendar.getInstance();
		// TODO 查询后台维护数据
		List<SpecialDay> list = specialDayService.getSpecialWorkdayList(
				startDate, endDate);
		int days = 0;
		for (SpecialDay specialDay : list) {
			cal.setTime(specialDay.getSpecialDate());
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if (day == Calendar.SUNDAY || day == Calendar.SATURDAY) {
				days++;
			} else {
				continue;
			}
		}
		return days;
	}

	/**
	 * 
	 * @Description: 时间范围内实际的工作日
	 * @author huangzhanming
	 * @param startDate
	 * @param endDate
	 * @return
	 * @date 2012-11-9 下午4:19:06
	 * @version V1.0
	 */
	public int getActualWorkdays(Date startDate, Date endDate) {
		int normalWorkdays = getNormalWorkdays(startDate, endDate);
		int specialHolidays = getSpecialHolidays(startDate, endDate);
		int specialWorkdays = getSpecialWorkdays(startDate, endDate);
		int actualWorkdays = normalWorkdays + specialWorkdays - specialHolidays;
		return actualWorkdays;
	}

	@Override
	public void addSpecialDay(SpecialDay specialDay) {
		// TODO Auto-generated method stub
		// 判断当前工作是否存在特殊日，无则可以进行新增，否则抛出异常
		if (specialDay.getSpecialDate() != null) {
			if (specialDay.getDateType().equals("HOLIDAY")
					|| specialDay.getDateType().equals("WORKDAY")) {
				if (null == specialDayService.getSpecialDayByDate(specialDay
						.getSpecialDate())) {

					specialDayService.addSpecialDay(specialDay);
				} else {
					SpecialDayException specialDayException = new SpecialDayException(
							SpecialDayExceptionType.SPECIALDAY_IS_EXIST);
					throw new GeneralException(
							specialDayException.getErrorCode(),
							specialDayException.getMessage(),
							specialDayException, new Object[] {}) {
					};

				}
			}
			if (specialDay.getDateType().equals("LEGAL_HOLIDAYS")) {
				if (null == specialDayService.getLegalHolidaysByDate(specialDay
						.getSpecialDate())) {
					specialDayService.addSpecialDay(specialDay);

				} else {
					SpecialDayException specialDayException = new SpecialDayException(
							SpecialDayExceptionType.LEGAL_HOLIDAYS_IS_EXIST);
					throw new GeneralException(
							specialDayException.getErrorCode(),
							specialDayException.getMessage(),
							specialDayException, new Object[] {}) {
					};

				}

			}
		}

	}

	@Override
	public void updateSpecialDayById(SpecialDay specialDay) {
		
		if (specialDay.getSpecialDate() != null) {
			SpecialDay oldSpecialDay=getSpecialDayById(specialDay.getId());
			if(specialDay.getDateType().equals(oldSpecialDay.getDateType())){
				return;
			}
			if (specialDay.getDateType().equals("LEGAL_HOLIDAYS")) {
				if (null == specialDayService.getLegalHolidaysByDate(specialDay
						.getSpecialDate())) {
					specialDayService.updateSpecialDayById(specialDay);
					return;
				} else {
					SpecialDayException specialDayException = new SpecialDayException(
							SpecialDayExceptionType.LEGAL_HOLIDAYS_IS_EXIST);
					throw new GeneralException(
							specialDayException.getErrorCode(),
							specialDayException.getMessage(),
							specialDayException, new Object[] {}) {
					};

				}

			}else{
				SpecialDay unSpecialDay = specialDayService.getSpecialDayByDate(specialDay
						.getSpecialDate());
				if(unSpecialDay==null){
					specialDayService.updateSpecialDayById(specialDay);
					return;
				}else if(!unSpecialDay.getDateType().equals(specialDay.getDateType())
						&&!oldSpecialDay.getDateType().equals("LEGAL_HOLIDAYS")){
					specialDayService.updateSpecialDayById(specialDay);
					return;
				}
			}
			SpecialDayException specialDayException = new SpecialDayException(
					SpecialDayExceptionType.SPECIALDAY_IS_EXIST);
			throw new GeneralException(
					specialDayException.getErrorCode(),
					specialDayException.getMessage(),
					specialDayException, new Object[] {}) {
			};
		}
	}

	@Override
	public void deleteSpecialDayById(String id) {
		specialDayService.deleteSpecialDayById(id);
	}

	@Override
	public SpecialDay getSpecialDayById(String id) {
		return specialDayService.getSpecialDayById(id);
	}

	@Override
	public List<SpecialDay> getSpecialDayList(Date startDate, Date endDate,
			String type) {
		return specialDayService.getSpecialDayList(startDate, endDate, type);
	}

	@Override
	public void deleteSpecialDayList(String[] ids) {
		if (null != ids && ids.length != 0) {
			for (String id : ids) {
				specialDayService.deleteSpecialDayById(id);
			}

		}

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-8-23
	 * @param startDate
	 * @param endDate
	 * @param type
	 * @return int
	 */
	public int getValieWorkdays(Date startDate, Date endDate, String type) {
		int days = 0;
		List<SpecialDay> list = getSpecialDayList(startDate, endDate, type);

		days = (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 3600 * 24));
		if (list != null) {
			days -= list.size();
		}
		return days;
	}

}
