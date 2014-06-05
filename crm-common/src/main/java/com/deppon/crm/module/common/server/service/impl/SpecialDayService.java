package com.deppon.crm.module.common.server.service.impl;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.common.server.dao.impl.SpecialDayDao;
import com.deppon.crm.module.common.server.service.ISpecialDayService;
import com.deppon.crm.module.common.shared.domain.SpecialDay;

public class SpecialDayService implements ISpecialDayService {
	private SpecialDayDao specialDayDao;

	@Override
	public void addSpecialDay(SpecialDay specialDay) {
		specialDayDao.addSpecialDay(specialDay);
	}

	@Override
	public void updateSpecialDayById(SpecialDay specialDay) {
		specialDayDao.updateSpecialDayById(specialDay);
	}

	@Override
	public void deleteSpecialDayById(String id) {
		specialDayDao.deleteSpecialDayById(id);
	}

	@Override
	public SpecialDay getSpecialDayByDate(Date specialDate) {
		return specialDayDao.getSpecialDayByDate(specialDate);
	}

	@Override
	public SpecialDay getSpecialDayById(String id) {
		return specialDayDao.getSpecialDayById(id);
	}

	@Override
	public List<SpecialDay> getSpecialDayList(Date startDate, Date endDate,
			String type) {
		return specialDayDao.getSpecialDayList(startDate, endDate, type);
	}

	@Override
	public List<SpecialDay> getSpecialHolidayList(Date startDate, Date endDate) {
		return specialDayDao.getSpecialHolidayList(startDate, endDate);
	}

	@Override
	public List<SpecialDay> getSpecialWorkdayList(Date startDate, Date endDate) {
		return specialDayDao.getSpecialWorkdayList(startDate, endDate);
	}

	public SpecialDayDao getSpecialDayDao() {
		return specialDayDao;
	}

	public void setSpecialDayDao(SpecialDayDao specialDayDao) {
		this.specialDayDao = specialDayDao;
	}

	@Override
	public SpecialDay getLegalHolidaysByDate(Date specialDate) {
		return specialDayDao.getLegalHolidaysByDate(specialDate);
	}

}
