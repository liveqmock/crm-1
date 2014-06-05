/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ScheduleDao.java
 * @package com.deppon.crm.module.marketing.server.dao.impl 
 * @author Administrator
 * @version 0.1 2012-3-27
 */
package com.deppon.crm.module.marketing.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.marketing.server.action.CustomerVo;
import com.deppon.crm.module.marketing.server.action.DevelopScheduleVO;
import com.deppon.crm.module.marketing.server.action.ToDoPojo;
import com.deppon.crm.module.marketing.server.dao.IScheduleDao;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.Schedule;
import com.deppon.crm.module.marketing.shared.domain.ScheduleQueryResultObject;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ScheduleDao.java
 * @package com.deppon.crm.module.marketing.server.dao.impl 
 * @author 苏玉军
 * @version 0.1 2012-3-27
 */

public class ScheduleDao extends iBatis3DaoImpl implements IScheduleDao {
	//namespace
	private static final String NAMESPACE_SCHEDULE="com.deppon.crm.module.marketing.shared.domain.Schedule.";
	//新增日程
	private static final String SCHEDULE_INSERT="insertSchedule";
	//删除日程
	private static final String SCHEDULE_DELETE="deleteSchedule";
	//根据Id查询
	private static final String SCHEDULE_QUERYBYID="queryScheduleById";
	//更新日程
	private static final String SCHEDULE_UPDATE="updateSchedule";
	//查询计划下的所有日程
	private static final String SCHEDULE_ALL="queryAllScheduleOfPlan";
	//查询日程详情
	private static final String SCHEDULE_SEARCHCUSTOMERLIST="searchCustomerList";
	//逻辑删除 更新日程
	private static final String SCHEDULE_DELETESCHEDULE="delete";
	//根据Id删除日程
	private static final String SCHEDULE_DELETEBYID="deleteScheduleById";
	//查询日程
	private static final String SCHEDULE_SESRCHSCHEDULE="searchSchedule";
	//统计日程数量
	private static final String SCHEDULE_COUNTFORSEARCHSCHEDULE ="countForSearchSchedule";
	//planId+联系人id删除日程
	private static final String SCHEDULE_DELETEBYCONTACTID="deleteScheduleByContact";
	
	//开发状态定时器，更新日程状态
	private static final String UPDATESCHEDULESTATUSFORTIMER = "updateScheduleStatusForTimer";
	//查询维护日程
	private static final String SCHEDULE_SEARCHMAINTAINSCHEDULE="searchMaintainSchedule";
	//日程新增总记录数
	private static final String SCHEDULE_COUNTSEARCHCUSTOMERLIST = "countSearchCustomerList";
	//维护日程新增查询
	private static final String SCHEDULE_SEARCHMAINTAINCUSTLIST="searchMaintainCustList";
	//维护日程新增查询分页
	private static final String SCHEDULE_COUNTSEARCHMAINCUSTLIST = "countSearchMaintainCustList";
	//维护日程查询 分页
	private static final String SCHEDULE_COUNTFORSEARCHMAINTAINSCHEDULE ="countForSearchMaintainSchedule";
	//更新原始日程
	private static final String SCHEDULE_UPDORIGSCHEDULE="updOrginalSchdule";
	//查询当前登录用户开发日程
	private static final String SEARCH_DEV_SCHEDULESELF = "searchDEVScheduleSelf";
	//查询当前登录用户开发日程总数
	private static final String SEARCH_DEV_SCHEDULESELF_COUNT = "searchDEVScheduleSelf_count";
	//查询当前登录用户维护日程
	private static final String SEARCH_MAT_SCHEDULESELF = "searchMATScheduleSelf";
	//查询当前登录用户维护日程总数
	private static final String SEARCH_MAT_SCHEDULESELF_COUNT = "searchMATScheduleSelf_count";
	//通过状态查询日程
	private static final String SEARCH_SCHEDULE_BY_STATYS = "searchScheduleByStatus";
	//供定时器调用
	private static final String SEARCH_SCHEDULE_BY_STATYS_4_JOB = "searchScheduleByStatus4Job";
	//查询 已经 做过 回访的日程 集合 
	private static final String SEARCH_SCHEDULE_BY_RETURNVISIT="searchScheduleByReturnVisit";
	//商机日程查询
	private static final String SEARCHBUSINESSSCHEDULE = "searchBusinessSchedule";
	
	/* 根据计划Id，客户id删除日程
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#deleteDevelopSchedule(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteDevelopSchedule(String planId, String custId) {
		//参数封装
		Map<String,String> map=new HashMap<String, String>();
		//计划ID
		map.put("planId", planId);
		//客户ID
		map.put("custId", custId);
		//删除操作
		this.getSqlSession().delete(NAMESPACE_SCHEDULE+SCHEDULE_DELETE, map);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#createSchedules(com.deppon.crm.module.marketing.shared.domain.Schedule)
	 */
	@Override
	public boolean createSchedules(Schedule sche) {
		//新增日程
		this.getSqlSession().insert(NAMESPACE_SCHEDULE+SCHEDULE_INSERT,sche);
		//success
		return true;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#searchScheduleById(java.lang.String)
	 */
	@Override
	public Schedule searchScheduleById(String id) {
		//根据日程ID查询日程
		return (Schedule) this.getSqlSession().selectOne(NAMESPACE_SCHEDULE+SCHEDULE_QUERYBYID, id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#updateSchedule(com.deppon.crm.module.marketing.shared.domain.Schedule)
	 */
	@Override
	public boolean updateSchedule(Schedule schedule) {
		//更新日程
		this.getSqlSession().update(NAMESPACE_SCHEDULE+SCHEDULE_UPDATE, schedule);
		//success
		return true;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#getAllSchedule(com.deppon.crm.module.marketing.shared.domain.Plan)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Schedule> getAllSchedule(Plan plan) {
		//查询计划下的所有日程
		return this.getSqlSession().selectList(NAMESPACE_SCHEDULE+SCHEDULE_ALL, plan);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#searchCustomerList(com.deppon.crm.module.marketing.shared.domain.PlanScheduleQueryCondition, int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ScheduleQueryResultObject> searchCustomerList(
			CustomerVo condition, int start, int limit) {
		//分页参数
		RowBounds rb = new RowBounds(start, limit);
		//客户名称不为空，模糊查询
		if(!StringUtils.isEmpty(condition.getCustName())){
			//拼装模糊查询格式
			condition.setCustName("%"+condition.getCustName()+"%");
		}
		//联系人名称不为空
		if(!StringUtils.isEmpty(condition.getLinkManName())){
			//拼装模糊查询格式
			condition.setLinkManName("%"+condition.getLinkManName()+"%");
		}
		//分页查询
		return this.getSqlSession().selectList(NAMESPACE_SCHEDULE+SCHEDULE_SEARCHCUSTOMERLIST, condition , rb);
	}
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#countSearchCustomerList(com.deppon.crm.module.marketing.server.action.CustomerVo)
	 */
	@Override
	public int countSearchCustomerList(CustomerVo condition) {
		//查询总数
		return (Integer) this.getSqlSession().selectOne(NAMESPACE_SCHEDULE+SCHEDULE_COUNTSEARCHCUSTOMERLIST, condition);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#deleteByUpdate(com.deppon.crm.module.marketing.shared.domain.Schedule)
	 */
	@Override
	public boolean delete(Schedule schedule) {
		//删除日程
		this.getSqlSession().delete(NAMESPACE_SCHEDULE+SCHEDULE_DELETESCHEDULE, schedule);
		//success
		return true;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#deleteScheduleById(java.lang.String)
	 */
	@Override
	public boolean deleteScheduleById(String planId) {
		//根据计划ID删除日程
		this.getSqlSession().delete(NAMESPACE_SCHEDULE+SCHEDULE_DELETEBYID, planId);
		//succcess
		return true;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#searchSchedule(com.deppon.crm.module.marketing.server.action.DevelopScheduleVO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DevelopScheduleVO> searchSchedule(DevelopScheduleVO vo,int start,int limit) {
		//分页参数
		RowBounds rb=new RowBounds(start, limit);
		//计划名称不为空
		if(!StringUtils.isEmpty(vo.getPlaneName())){
			//设置模糊查询
			vo.setPlaneName("%"+vo.getPlaneName()+"%");
		}
		//返回查询结果
		return (List<DevelopScheduleVO>)this.getSqlSession().selectList(NAMESPACE_SCHEDULE+SCHEDULE_SESRCHSCHEDULE, vo,rb);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#countForSearchSchedule(com.deppon.crm.module.marketing.server.action.DevelopScheduleVO)
	 */
	@Override
	public int countForSearchSchedule(DevelopScheduleVO vo) {
		//查询总数
		return (Integer) this.getSqlSession().selectOne(NAMESPACE_SCHEDULE+SCHEDULE_COUNTFORSEARCHSCHEDULE, vo);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#deleteScheduleByContact(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteScheduleByContact(String planId, String contactId) {
		//参数封装
		Map<String,Object> map=new HashMap<String,Object>();
		//计划ID
		map.put("planId", planId);
		//联系人ＩＤ
		map.put("contactId", contactId);
		//删除日程
		this.getSqlSession().delete(NAMESPACE_SCHEDULE+SCHEDULE_DELETEBYCONTACTID, map);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#updateScheduleStatusForTimer(com.deppon.crm.module.marketing.shared.domain.Schedule)
	 */
	@Override
	public boolean updateScheduleStatusForTimer(Schedule schedule) {
		//更新日程状态
		getSqlSession().update(NAMESPACE_SCHEDULE + UPDATESCHEDULESTATUSFORTIMER, schedule);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#searchMaintainSchedule(com.deppon.crm.module.marketing.server.action.DevelopScheduleVO, int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DevelopScheduleVO> searchMaintainSchedule(DevelopScheduleVO vo,
			int start, int limit) {
		//计划名称不为空
		if(StringUtils.isNotEmpty(vo.getPlaneName())){
			//拼装模糊查询格式
			vo.setPlaneName("%"+vo.getPlaneName()+"%");
		}
		//分页参数
		RowBounds rb=new RowBounds(start, limit);
		//查询
		return this.getSqlSession().selectList(NAMESPACE_SCHEDULE+SCHEDULE_SEARCHMAINTAINSCHEDULE, vo,rb);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#searchMaintainCustList(com.deppon.crm.module.marketing.server.action.CustomerVo, int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ScheduleQueryResultObject> searchMaintainCustList(
			CustomerVo condition, int start, int limit) {
		//客户名称不为空
		if(StringUtils.isNotEmpty(condition.getCustName())){
			//模糊查询
			condition.setCustName("%"+condition.getCustName()+"%");
		}
		//联系人名称不为空，模糊查询
		if(StringUtils.isNotEmpty(condition.getLinkManName())){
			condition.setLinkManName("%"+condition.getLinkManName()+"%");
		}
		//分页参数
		RowBounds rb = new RowBounds(start,limit);
		//返回结果
		return this.getSqlSession().selectList(NAMESPACE_SCHEDULE+SCHEDULE_SEARCHMAINTAINCUSTLIST, condition,rb);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#countSearchMaintainCustList(com.deppon.crm.module.marketing.server.action.CustomerVo)
	 */
	@Override
	public int countSearchMaintainCustList(CustomerVo condition) {
		//返回查询结果
		return (Integer) this.getSqlSession().selectOne(NAMESPACE_SCHEDULE+SCHEDULE_COUNTSEARCHMAINCUSTLIST, condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#countForSearchMaintainSchedule(com.deppon.crm.module.marketing.server.action.DevelopScheduleVO)
	 */
	@Override
	public int countForSearchMaintainSchedule(DevelopScheduleVO vo) {
		//返回查询结果总数
		return (Integer) this.getSqlSession().selectOne(NAMESPACE_SCHEDULE+SCHEDULE_COUNTFORSEARCHMAINTAINSCHEDULE, vo);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#updOrginalSchdule(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updOrginalSchdule(String planId, String exeManId) {
		//参数封装
		Map<String , String > map = new HashMap<String, String>();
		//计划ID
		map.put("planId", planId);
		//执行人ID
		map.put("exeManId", exeManId);
		//更新日程
		this.getSqlSession().update(NAMESPACE_SCHEDULE+SCHEDULE_UPDORIGSCHEDULE, map);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#searchDEVScheduleSelf(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DevelopScheduleVO>  searchDEVScheduleSelf(String execuserid,int start,int limit) {
		//分页参数
		RowBounds rb = new RowBounds(start,limit);
		//分页查询
		return this.getSqlSession().selectList(NAMESPACE_SCHEDULE+SEARCH_DEV_SCHEDULESELF, execuserid,rb);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#getDEVScheduleSelfCount(java.lang.String)
	 */
	@Override
	public int getDEVScheduleSelfCount(String execuserid) {
		//查询当前用户维护的日程
		return (Integer) this.getSqlSession().selectOne(NAMESPACE_SCHEDULE+SEARCH_DEV_SCHEDULESELF_COUNT, execuserid);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#searchMATScheduleSelf(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DevelopScheduleVO>  searchMATScheduleSelf(String execuserid,int start,int limit) {
		//分页参数
		RowBounds rb = new RowBounds(start,limit);
		//分页查询用户维护的日程
		return this.getSqlSession().selectList(NAMESPACE_SCHEDULE+SEARCH_MAT_SCHEDULESELF, execuserid,rb);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#getMATScheduleSelfCount(java.lang.String)
	 */
	@Override
	public int getMATScheduleSelfCount(String execuserid) {
		//根据执行人查询日程数量
		return (Integer) this.getSqlSession().selectOne(NAMESPACE_SCHEDULE+SEARCH_MAT_SCHEDULESELF_COUNT, execuserid);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#searchScheduleByStatusList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Schedule> searchScheduleByStatus(String status,int start,int limit) {
		//参数Map
		Map<String, String> map = new HashMap<String, String>();
		//日程状态
		map.put("status", status);
		RowBounds rb = new RowBounds(start,limit);
		//查询日程
		return this.getSqlSession().selectList(NAMESPACE_SCHEDULE+SEARCH_SCHEDULE_BY_STATYS, map,rb);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#searchSchedule4Job(com.deppon.crm.module.marketing.shared.domain.Schedule)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ToDoPojo> searchSchedule4Job(Schedule sche) {
		//查询日程
		return this.getSqlSession().selectList(NAMESPACE_SCHEDULE+SEARCH_SCHEDULE_BY_STATYS_4_JOB, sche);
	}

	/**
	 * <p>
	 * Description: 查出已经回访过的 日程 集合<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-10-17
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Schedule> searchScheduleByReturnVisit(int start,int limit) {
		RowBounds rb = new RowBounds(start, limit);
		//根据回访查询日程
		return this.getSqlSession().selectList(NAMESPACE_SCHEDULE+SEARCH_SCHEDULE_BY_RETURNVISIT,null,rb);
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
	@SuppressWarnings("unchecked")
	@Override
	public List<DevelopScheduleVO> searchBusinessSchedule(DevelopScheduleVO vo,
			int start, int limit) {
		RowBounds rb = new RowBounds(start,limit);
		return this.getSqlSession().selectList(NAMESPACE_SCHEDULE + SEARCHBUSINESSSCHEDULE, vo, rb);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#countForsearchScheduleByReturnVisit()
	 */
	@Override
	public int countForsearchScheduleByReturnVisit() {
		return (Integer) this.getSqlSession().selectOne(NAMESPACE_SCHEDULE + "countForsearchScheduleByReturnVisit");
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#countForsearchScheduleByStatus(java.lang.String)
	 */
	@Override
	public int countForsearchScheduleByStatus(String status) {
		//参数Map
		Map<String, String> map = new HashMap<String, String>();
		//日程状态
		map.put("status", status);
		return (Integer) this.getSqlSession().selectOne(NAMESPACE_SCHEDULE + "countForsearchScheduleByStatus",map) ;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#updAllScheduleToCompleteByCustId(java.lang.String)
	 */
	@Override
	public void updAllScheduleToCompleteByCustId(String custId) {
		this.getSqlSession().update(NAMESPACE_SCHEDULE + "updAllScheduleToCompleteByCustId", custId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IScheduleDao#searchTodoInfo(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ToDoPojo> searchTodoInfo(String custId) {
		return this.getSqlSession().selectList(NAMESPACE_SCHEDULE + "searchTodoInfo",custId);
	}
	
}
