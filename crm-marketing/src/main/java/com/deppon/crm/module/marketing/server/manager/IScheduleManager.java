
package com.deppon.crm.module.marketing.server.manager;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.shared.domain.PotentialCustomer;
import com.deppon.crm.module.customer.shared.domain.ScatterCustomer;
import com.deppon.crm.module.marketing.server.action.CustomerVo;
import com.deppon.crm.module.marketing.server.action.DevelopScheduleVO;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.Schedule;
import com.deppon.crm.module.marketing.shared.domain.ScheduleQueryResultObject;
import com.deppon.crm.module.marketing.shared.domain.SearchCustomerCondition;
/**
 * 
 * <p>
 * 日程封装操作类
 * </p>
 * @title IScheduleManager.java
 * @package com.deppon.crm.module.marketing.server.manager 
 * @author 苏玉军
 * @version 0.1 2013-1-28
 */
public interface IScheduleManager {
	/**
	 * 
	 * <p>
	 * 计划id+联系人id 删除日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-10
	 * @param planId
	 * @param contactId
	 * @return
	 * boolean
	 */
	 boolean deleteScheduleByContact(String planId,String contactId);
	/**
	 * 
	 * <p>
	 * 删除日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-26
	 * @param planId
	 * @param custid
	 * @return
	 * boolean
	 */
	 boolean deleteSchedule(String planId,String custid);
	
	/**
	 * 
	 * <p>
	 * 创建日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-26
	 * @param ids
	 * @return
	 * boolean
	 */
	 boolean createSchedule(Schedule sche,User user);

	/**
	 * <p>
	 * 根据计划获得所有对应的日程对象<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-27
	 * @param plan
	 * @return
	 * List<Schedule>
	 */
	 List<Schedule> getAllSchedule(Plan plan);
	
	/**
	 * 
	 * <p>
	 * 根据客户Id，客户类型查询客户详情<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-28
	 * @param custId 客户Id
	 * @param custType 客户类型
	 * @return
	 * PotentialCustomer 客户对象
	 */
//	 PotentialCustomer getCustomerDetail(String custId,String custType);
	
	/**
	 * 
	 * <p>
	 * 更新日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-28
	 * @param newSchedule
	 * @return
	 * boolean
	 */
	 boolean updateSchedule(Schedule newSchedule,User user);
	
	
	/**
	 * 
	 * <p>
	 * 仅更新日程的状态<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-28
	 * @param schedule
	 * @return
	 * boolean
	 */
	 boolean updateScheduleStatus(Schedule schedule);

	/**
	 * <p>
	 * 根据计划的Id查询对应的客户id<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-31
	 * @param planId
	 * @return
	 * List<String>
	 */
	 List<String> getCustomerIdByPlanId(String planId);
	
	/**
	 * 
	 * <p>
	 * 查询日程详细信息<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-1
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 * List<?>
	 */
	 List<ScheduleQueryResultObject> searchCustomerList(CustomerVo condition , int start ,int limit,User user);
	
	/**
	 * 
	 * <p>
	 * 根据日程PO删除日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-1
	 * @param schedule
	 * @return
	 * boolean
	 */
	 boolean deleteSchedule(String[] scheduleIds,User user);
	
	/**
	 * 
	 * <p>
	 * 根据日程id删除日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-6
	 * @param scheduleId
	 * @return
	 * boolean
	 */
	 boolean deleteScheduleById(String planId);
	
	
	/**
	 * 
	 * <p>
	 * 日程查询<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-7
	 * @param vo
	 * @return
	 * List<DevelopScheduleVO>
	 */
	 List<DevelopScheduleVO> searchSchedule(DevelopScheduleVO vo,int start,int limit,User user);
	/**
	 * 
	 * <p>
	 * 日程查询统计总条数<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-7
	 * @param vo
	 * @return
	 * int
	 */
	 int countForSearchSchedule(DevelopScheduleVO vo);
	
	/**
	 * 
	 * <p>
	 * 更新、创建日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-7
	 * @param schedule
	 * @return
	 * boolean
	 */
	 boolean saveSchedule(Schedule schedule,User user);
	
	/**
	 * 
	 * <p>
	 * 日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-17
	 * @return
	 * int
	 */
	 int countSearchCustomerList(CustomerVo condition);
	
	/**
	 * 
	 * <p>
	 * 通过更新计划更新日程执行人，在更新日程之前执行<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-19
	 * @param planId
	 * @param exeManId
	 * @return
	 * boolean
	 */
	 boolean updOrginalSchdule(String planId,String exeManId);
	/**
	 * 
	 * <p>
	 * 创建有计划日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-19
	 * @param sche
	 * @return
	 * boolean
	 */
	 boolean createScheduleByPlan(Schedule sche);
	/**
	 * 
	 * <p>
	 * 根据查询条件分页查询客户<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-25
	 * @param condition
	 * @param start
	 * @param limit
	 * @param user
	 * @return
	 * Map<String,Object>
	 */
	
	 Map<String, Object> searchCustomerList4Plan(SearchCustomerCondition condition, int start,int limit, User user);
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-5-19
	 * @param scheduleId
	 * @return
	 * Schedule
	 */
	 Schedule getScheduleById(String scheduleId);
	
	/**
	 * <p>
	 * Description: 查询自己的日程<br />
	 * </p>
	 * 
	 * @author ZhuPJ
	 * @version 0.1 2012-5-23
	 * @param condition
	 * @param start
	 * @param limit
	 * @param user
	 * @return List<ScheduleQueryResultObject>
	 */
	 Map<String, Object> searchScheduleSelf(String execuserid,
			String scheduleType, int start, int limit);
	
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
	 * Description: 更新日程状态<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-5-25
	 * @param scList
	 * @param status
	 * @return
	 * boolean
	 */
	 boolean processScheduleStatus( List<Schedule> scList, String status);
	
	/**
	 * <p>
	 * Description: 统计每天待办日程（已指派、执行中、已过期）<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-5-28
	 * @return
	 * boolean
	 */
	 boolean processScheduleTODO();
	
	/**
	 * <p>
	 * Description: 根据ID获取潜散客详情<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-5-29
	 * @param id
	 * @return
	 * ScatterCustomer
	 */
//	 ScatterCustomer getPcScDetail(String id);
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
	  * @author 苏玉军
	  * @version 0.1 2014/03/20
	  * @param vo
	  * @return
	  * List<DevelopScheduleVO>
	  */
	 List<DevelopScheduleVO> searchBusinessSchedule(DevelopScheduleVO vo,int start,int limit,User user);
	 
		/**
		 * 
		 * <p>
		 * 查询客户详细信息（开发阶段管理）<br />
		 * </p>
		 * 
		 * @author 张斌
		 * @version 0.1 2014-4-15
		 * @param condition
		 * @param start
		 * @param limit
		 * @return List<ScheduleQueryResultObject>
		 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#searchCustomerList(com.deppon.crm.module.marketing.shared.domain.PlanScheduleQueryCondition,
		 *      int, int)
		 * @description 查询客户详细信息（开发阶段管理）
		 */
	List<ScheduleQueryResultObject> searchCustomerListForMemberExtend(
			CustomerVo condition, int start, int limit, User user);
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
		 * @param scheduleFormulate
		 * @return
		 * int
		 */
		int countForsearchScheduleByStatus(String status);
		
		/**
		 * 
		 * <p>
		 * 传入客户ID，对该客户下的所有联系人的未结束的日程更新为已完成<br />
		 * </p>
		 * @author 043260
		 * @version 0.1 2014年5月22日
		 * @param custId
		 * void
		 */
		void changeStatusToCompleteForBO(String custId);
}
