/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IScheduleDao.java
 * @package com.deppon.crm.module.marketing.server.dao 
 * @author Administrator
 * @version 0.1 2012-3-27
 */
package com.deppon.crm.module.marketing.server.dao;

import java.util.List;

import com.deppon.crm.module.marketing.server.action.CustomerVo;
import com.deppon.crm.module.marketing.server.action.DevelopScheduleVO;
import com.deppon.crm.module.marketing.server.action.ToDoPojo;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.Schedule;
import com.deppon.crm.module.marketing.shared.domain.ScheduleQueryResultObject;

/**   
 * <p>
 * 日程操作接口<br />
 * </p>
 * @title IScheduleDao.java
 * @package com.deppon.crm.module.marketing.server.dao 
 * @author 苏玉军
 * @version 0.1 2012-3-27
 */

public interface IScheduleDao {

	/**
	 * <p>
	 * 删除日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-27
	 * @param planId
	 * @param custId
	 * void
	 */
	 boolean  deleteDevelopSchedule(String planId, String custId);

	/**
	 * <p>
	 * 创建日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-27
	 * @param sche
	 * @return
	 * boolean
	 */
	 boolean createSchedules(Schedule sche);
	
	/**
	 * 
	 * <p>
	 * 查询日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-27
	 * @param id
	 * @return
	 * Schedule
	 */
	 Schedule searchScheduleById(String id);
	
	/**
	 * 
	 * <p>
	 * 更新日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-28
	 * @param schedule
	 * @return
	 * boolean
	 */
	 boolean updateSchedule(Schedule schedule);

	/**
	 * <p>
	 * 查询该开发计划下的日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-29
	 * @param plan
	 * @return
	 * List<Schedule>
	 */
	 List<Schedule> getAllSchedule(Plan plan);

	/**
	 * <p>
	 * 查询日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-1
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 * List<ScheduleQueryResultObject>
	 */
	 List<ScheduleQueryResultObject> searchCustomerList(
			CustomerVo condition, int start, int limit);

	/**
	 * <p>
	 * 逻辑删除<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-5
	 * @param schedule
	 * @return
	 * Object
	 */
	 boolean delete(Schedule schedule);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-6
	 * @param scheduleId
	 * @return
	 * boolean
	 */
	 boolean deleteScheduleById(String scheduleId);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-7
	 * @param vo
	 * @return
	 * List<DevelopScheduleVO>
	 */
	 List<DevelopScheduleVO> searchSchedule(DevelopScheduleVO vo,int start,int limit);

	/**
	 * <p>
	 * 统计条数<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-7
	 * @param vo
	 * @return
	 * int
	 */
	 int countForSearchSchedule(DevelopScheduleVO vo);

	/**
	 * @version 0.1 2012-4-10
	 * @param planId
	 * @param contactId
	 * @return
	 * boolean
	 */
	 boolean deleteScheduleByContact(String planId, String contactId);

	/**
	 * 
	 * <p>
	 * Description:开发状态定时器，更新日程状态<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 10, 2012
	 * @param schedule
	 * @return
	 * boolean
	 */
	 boolean updateScheduleStatusForTimer(Schedule schedule);

	/**
	 * <p>
	 * 查询维护日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-11
	 * @param vo
	 * @param start
	 * @param limit
	 * @return
	 * List<DevelopScheduleVO>
	 */
	 List<DevelopScheduleVO> searchMaintainSchedule(DevelopScheduleVO vo,
			int start, int limit);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-17
	 * @param condition
	 * @return
	 * int
	 */
	 int countSearchCustomerList(CustomerVo condition);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-17
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 * List<ScheduleQueryResultObject>
	 */
	 List<ScheduleQueryResultObject> searchMaintainCustList(
			CustomerVo condition, int start, int limit);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-18
	 * @param condition
	 * @return
	 * int
	 */
	 int countSearchMaintainCustList(CustomerVo condition);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-18
	 * @param vo
	 * @return
	 * int
	 */
	 int countForSearchMaintainSchedule(DevelopScheduleVO vo);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-19
	 * @param planId
	 * @param exeManId
	 * @return
	 * boolean
	 */
	 boolean updOrginalSchdule(String planId, String exeManId);
	
	/**
	 * <p>
	 * Description: 根据执行人ID统计当天日程信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-5-23
	 * @param execuserid
	 * @return
	 * DevelopScheduleVO
	 */
	 List<DevelopScheduleVO>  searchDEVScheduleSelf(String execuserid,int start,int limit);
	/**
	 * 
	 * <p>
	 * 查询日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-23
	 * @param execuserid
	 * @return
	 * int
	 */
	 int getDEVScheduleSelfCount(String execuserid);
	/**
	 * 
	 * <p>
	 * 查询维护日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-23
	 * @param execuserid
	 * @param start
	 * @param limit
	 * @return
	 * List<DevelopScheduleVO>
	 */
	 List<DevelopScheduleVO>  searchMATScheduleSelf(String execuserid,int start,int limit);
	/**
	 * 
	 * <p>
	 * 查询维护日程总数<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-23
	 * @param execuserid
	 * @return
	 * int
	 */
	 int getMATScheduleSelfCount(String execuserid);

	/**
	 * <p>
	 * Description: 日程状态定时器，查找已制定、进行中的日程<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-5-25
	 * @param limit 
	 * @param start 
	 * @return
	 * List<Schedule>
	 */
	 List<Schedule> searchScheduleByStatus(String status, int start, int limit);

	/**
	 * <p>
	 * Description: 根据日期及日程状态统计数据（供定时任务使用）<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-5-29
	 * @param sche
	 * void
	 */
	 List<ToDoPojo> searchSchedule4Job(Schedule sche);
	/**
	 * <p>
	 * Description: 查出已经回访过的 日程 集合<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-10-17
	 * @param limit 
	 * @param start 
	 */
	 List<Schedule> searchScheduleByReturnVisit(int start, int limit);
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
	 List<DevelopScheduleVO> searchBusinessSchedule(DevelopScheduleVO vo,
			int start, int limit);

	/**
	 * <p>
	 * 查询已经回访的日程总数<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014年5月20日
	 * @return
	 * int
	 */
	int countForsearchScheduleByReturnVisit();

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014年5月20日
	 * @param status
	 * @return
	 * int
	 */
	int countForsearchScheduleByStatus(String status);

	/**
	 * <p>
	 * 更新客户下联系人的日程状态为已完成<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014年5月22日
	 * @param custId
	 * void
	 */
	void updAllScheduleToCompleteByCustId(String custId);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014年5月22日
	 * @param custId
	 * @return
	 * List<ToDoPojo>
	 */
	List<ToDoPojo> searchTodoInfo(String custId);
}
