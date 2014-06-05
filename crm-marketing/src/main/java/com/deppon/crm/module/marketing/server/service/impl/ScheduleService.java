
package com.deppon.crm.module.marketing.server.service.impl;

import java.util.List;

import com.deppon.crm.module.marketing.server.action.CustomerVo;
import com.deppon.crm.module.marketing.server.action.DevelopScheduleVO;
import com.deppon.crm.module.marketing.server.action.ToDoPojo;
import com.deppon.crm.module.marketing.server.dao.IScheduleDao;
import com.deppon.crm.module.marketing.server.service.IScheduleService;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.Schedule;
import com.deppon.crm.module.marketing.shared.domain.ScheduleQueryResultObject;

/**
 * 
 * <p>
 * 日程操作service实现类<br />
 * </p>
 * @title ScheduleService.java
 * @package com.deppon.crm.module.marketing.server.service.impl 
 * @author 苏玉军
 * @version 0.1 2013-1-29
 */
public class ScheduleService implements IScheduleService {
	//日程操作dao封装
	private IScheduleDao scheduleDao;
	
	/**
	 * @return scheduleDao : return the property scheduleDao.
	 */
	public IScheduleDao getScheduleDao() {
		return scheduleDao;
	}

	/**
	 * @param scheduleDao : set the property scheduleDao.
	 */
	public void setScheduleDao(IScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#deleteDevelopSchedule(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteDevelopSchedule(String planId, String custId) {
		return scheduleDao.deleteDevelopSchedule(planId,custId);
	}

	/**
	 * <p>
	 * 新加日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-27
	 * @param sche
	 * @return
	 * boolean
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#createDevelopSchedules(com.deppon.crm.module.marketing.shared.domain.Schedule)
	 */
	@Override
	public boolean createDevelopSchedules(Schedule sche) {
		return scheduleDao.createSchedules(sche);
	}
	
	
	/**
	 * 
	 * <p>
	 * 根据Id查询日程信息<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-28
	 * @param id
	 * @return
	 * Schedule
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#getScheduleById(java.lang.String)
	 */
	@Override
	public Schedule getScheduleById(String id) {
		return scheduleDao.searchScheduleById(id);
	}
	
	/**
	 * 
	 * <p>
	 * 更新日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-28
	 * @param sche
	 * @return
	 * boolean
	 */
	public boolean updateSchedule(Schedule sche){
		return scheduleDao.updateSchedule(sche);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#getAllSchedule(com.deppon.crm.module.marketing.shared.domain.Plan)
	 */
	@Override
	public List<Schedule> getAllSchedule(Plan plan) {
		return scheduleDao.getAllSchedule(plan);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#searchCustomerList(com.deppon.crm.module.marketing.shared.domain.PlanScheduleQueryCondition, int, int)
	 */
	@Override
	public List<ScheduleQueryResultObject> searchCustomerList(
			CustomerVo condition, int start, int limit) {
		return scheduleDao.searchCustomerList(condition,start,limit);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#delete(com.deppon.crm.module.marketing.shared.domain.Schedule)
	 */
	@Override
	public boolean delete(Schedule schedule) {
		return scheduleDao.delete(schedule);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#deleteScheduleById(java.lang.String)
	 */
	@Override
	public boolean deleteScheduleById(String planId) {
		return scheduleDao.deleteScheduleById(planId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#searchSchedule(com.deppon.crm.module.marketing.server.action.DevelopScheduleVO)
	 */
	@Override
	public List<DevelopScheduleVO> searchSchedule(DevelopScheduleVO vo,int start,int limit) {
		return scheduleDao.searchSchedule(vo,start,limit);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#countForSearchSchedule(com.deppon.crm.module.marketing.server.action.DevelopScheduleVO)
	 */
	@Override
	public int countForSearchSchedule(DevelopScheduleVO vo) {
		return scheduleDao.countForSearchSchedule(vo);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#deleteScheduleByContact(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteScheduleByContact(String planId, String contactId) {
		return scheduleDao.deleteScheduleByContact(planId,contactId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#updateScheduleStatusForTimer(com.deppon.crm.module.marketing.shared.domain.Schedule)
	 */
	@Override
	public boolean updateScheduleStatus(Schedule schedule) {
		return scheduleDao.updateScheduleStatusForTimer(schedule);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#searchMaintainSchedule(com.deppon.crm.module.marketing.server.action.DevelopScheduleVO, int, int)
	 */
	@Override
	public List<DevelopScheduleVO> searchMaintainSchedule(DevelopScheduleVO vo,
			int start, int limit) {
		return scheduleDao.searchMaintainSchedule(vo,start,limit);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#countSearchCustomerList(com.deppon.crm.module.marketing.server.action.CustomerVo)
	 */
	@Override
	public int countSearchCustomerList(CustomerVo condition) {
		return scheduleDao.countSearchCustomerList(condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#searchMaintainCustList(com.deppon.crm.module.marketing.server.action.CustomerVo, int, int)
	 */
	@Override
	public List<ScheduleQueryResultObject> searchMaintainCustList(
			CustomerVo condition, int start, int limit) {
		return scheduleDao.searchMaintainCustList(condition,start,limit);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#countSearchMaintainCustList(com.deppon.crm.module.marketing.server.action.CustomerVo)
	 */
	@Override
	public int countSearchMaintainCustList(CustomerVo condition) {
		return scheduleDao.countSearchMaintainCustList(condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#countForSearchMaintainSchedule(com.deppon.crm.module.marketing.server.action.DevelopScheduleVO)
	 */
	@Override
	public int countForSearchMaintainSchedule(DevelopScheduleVO vo) {
		return scheduleDao.countForSearchMaintainSchedule(vo);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#updOrginalSchdule(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updOrginalSchdule(String planId, String exeManId) {
		return scheduleDao.updOrginalSchdule(planId,exeManId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#searchDEVScheduleSelf(java.lang.String, int, int)
	 */
	@Override
	public List<DevelopScheduleVO> searchDEVScheduleSelf(String execuserid,
			int start, int limit) {
		return scheduleDao.searchDEVScheduleSelf(execuserid, start, limit);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#getDEVScheduleSelfCount(java.lang.String)
	 */
	@Override
	public int getDEVScheduleSelfCount(String execuserid) {
		return scheduleDao.getDEVScheduleSelfCount(execuserid);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#searchMATScheduleSelf(java.lang.String, int, int)
	 */
	@Override
	public List<DevelopScheduleVO> searchMATScheduleSelf(String execuserid,
			int start, int limit) {
		return scheduleDao.searchMATScheduleSelf(execuserid, start, limit);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#getMATScheduleSelfCount(java.lang.String)
	 */
	@Override
	public int getMATScheduleSelfCount(String execuserid) {
		return scheduleDao.getMATScheduleSelfCount(execuserid);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#searchScheduleByStatusList(java.lang.String)
	 */
	@Override
	public List<Schedule> searchScheduleByStatus(String status,int start,int limit) {
		return scheduleDao.searchScheduleByStatus(status,start,limit);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#searchSchedule4Job(com.deppon.crm.module.marketing.shared.domain.Schedule)
	 */
	@Override
	public List<ToDoPojo> searchSchedule4Job(Schedule sche) {
		return scheduleDao.searchSchedule4Job(sche);
	}
	
	/**
	 * <p>
	 * Description: 查出已经回访过的 日程 集合<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-10-17
	 */
	@Override
	public List<Schedule> searchScheduleByReturnVisit(int start, int limit) {
		return scheduleDao.searchScheduleByReturnVisit(start,limit);
	}
	 /**
	  * 
	  * <p>
	  * 商机日程查询<br />
	  * </p>
	  * @author 043260
	  * @version 0.1 2014-3-20
	  * @param vo
	  * @param start
	  * @param limit
	  * @return
	  * List<DevelopScheduleVO>
	  */
	@Override
	public List<DevelopScheduleVO> searchBusinessSchedule(DevelopScheduleVO vo,
			int start, int limit) {
		return scheduleDao.searchBusinessSchedule(vo,start,limit);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#countForsearchScheduleByReturnVisit()
	 */
	@Override
	public int countForsearchScheduleByReturnVisit() {
		return scheduleDao.countForsearchScheduleByReturnVisit();
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#countForsearchScheduleByStatus(java.lang.String)
	 */
	@Override
	public int countForsearchScheduleByStatus(String status) {
		return scheduleDao.countForsearchScheduleByStatus(status);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#updAllScheduleToCompleteByCustId(java.lang.String)
	 */
	@Override
	public void updAllScheduleToCompleteByCustId(String custId) {
		scheduleDao.updAllScheduleToCompleteByCustId(custId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IScheduleService#searchTodoInfo(java.lang.String)
	 */
	@Override
	public List<ToDoPojo> searchTodoInfo(String custId) {
		return scheduleDao.searchTodoInfo(custId);
	}

}
