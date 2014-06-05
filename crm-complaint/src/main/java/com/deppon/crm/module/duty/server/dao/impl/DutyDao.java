package com.deppon.crm.module.duty.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;

import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.duty.server.dao.IDutyDao;
import com.deppon.crm.module.duty.shared.domain.Duty;
import com.deppon.crm.module.duty.shared.domain.DutyDealProcess;
import com.deppon.crm.module.duty.shared.domain.DutyQueryResult;
import com.deppon.crm.module.duty.shared.domain.DutyResult;
import com.deppon.crm.module.duty.shared.domain.QueryDutyCondition;
import com.deppon.crm.module.duty.shared.domain.SearchDutyCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 
 * <p>
 * Description:工单责任-基础类型  Dao 实现<br />
 * </p>
 * @title DutyDao.java
 * @package com.deppon.crm.module.duty.server.dao.impl 
 * @author 侯斌飞
 * @version 0.1 2013-2-26
 */
public class DutyDao extends iBatis3DaoImpl implements IDutyDao {
	private Logger log = Logger.getLogger(DutyDao.class);

	private static final String NAMESPACE_DUTY = "com.deppon.crm.module.duty.shared.domain.Duty.";
	//根据条件查询工单
	private static final String SEARCHDUTYBYRECEIVE = "searchDutyByCondition";
	//根据条件计算工单责任数
	private static final String COUNTDUTYBYRECEIVE = "countDutyByCondition";
	//工单责任查询
	private static final String DUTYLISTSEARCH = "dutyListSearch"; 
	//工单责任查询条数
	private static final String DUTYLISTSEARCHCOUNT = "dutyListSearchCount"; 
	//更新工单责任数据
	private static final String UPDATEDUTYBYID = "updateDutyById";
	//工单责任审批查询列表
	private static final String QUERY_DUTY_APPROVAL_LIST = "queryDutyApprovalList";
	//工单责任审批查询总数
	private static final String COUNT_QUERY_DUTY_APPROVAL_LIST = "countQueryDutyApprovalList";
	//工单查询-责任详情
	private static final String GETDUTYDETAIL = "getDutyDetail";
	//工单查询-处理经过
	private static final String GETDUTYDEALPROCESS = "getDutyDealProcess";
	//工单查询-划分结果经过
	private static final String GETDUTYRESULT = "getdutyResult";
	//工单查询-通知对象
	private static final String GETUSERNAMELIST = "getUserNameList";
	// 工单查询- 列表展开
	private static final String QUERYDUTYDETAILLIST = "queryDutyDetailList";
	//插入工单
	private static final String COMPLAINTSINSERT = "complaintsInsert";
	//修改工单
	private static final String COMPLAINTSUPDATE = "updateComplaint";
	//修改处理经过
	private static final String UPDATEPROCESSPASS = "updateProcessPass";
	//修改状态
	private static final String UPDATESTATUS = "updateStatus";
	//修改调查状态
	private static final String UPDATESURVEYRESULT = "updateSurveyResult";
	
	private static final String GETALLPARENTDEPTBYDETAILID = "getAllParentDeptByDetailId";
	
	/**
	 * <p>
	 * Description:工单责任-查询<br />
	 * </p>
	 * @title queryDutyList.java
	 * @author 钟琼
	 * @version 0.1 2013-2-26
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DutyQueryResult> queryDutyList(QueryDutyCondition queryDutyCondition,int start,int limit) {
		RowBounds bounds = new RowBounds(start,limit);
		return this.getSqlSession().selectList(NAMESPACE_DUTY+DUTYLISTSEARCH, queryDutyCondition,bounds);
	}
	/**
	 * 
	 * <p>
	 * Description:工单责任-查询条数<br />
	 * </p>
	 * @title queryDutyList.java
	 * @author 钟琼
	 * @version 0.1 2013-2-26
	 */
	@Override
	public int queryDutyListCount(QueryDutyCondition queryDutyCondition) {
		return (Integer) this.getSqlSession().selectOne(NAMESPACE_DUTY+DUTYLISTSEARCHCOUNT, queryDutyCondition);
	}
	
	/**
	 * <p>
	 * Description:根据条件查询查询工单责任---无分页<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-7
	 * @param SearchDutyCondition
	 * @return List<Duty>
	 */
	public List<Duty> searchDutyByCondition(SearchDutyCondition sdc){
		return this.getSqlSession().selectList(NAMESPACE_DUTY+SEARCHDUTYBYRECEIVE,sdc);
	}
	/**
	 * <p>
	 * Description:根据条件查询查询工单责任---有分页<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-7
	 * @param SearchDutyCondition
	 * @param rowBounds
	 * @return List<Duty>
	 */
	public List<Duty> searchDutyByCondition(SearchDutyCondition sdc,RowBounds rowBounds){
		return this.getSqlSession().selectList(NAMESPACE_DUTY+SEARCHDUTYBYRECEIVE,sdc,rowBounds);
		
	}
	/**
	 * <p>
	 * Description:根据条件计算查询工单责任数<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-7
	 * @param SearchDutyCondition
	 * @return List<Duty>
	 */
	public int countDutyByCondition(SearchDutyCondition sdc){
		return (Integer)this.getSqlSession().selectOne(NAMESPACE_DUTY+COUNTDUTYBYRECEIVE,sdc);
	}
	/**
	 * <p>
	 * Description:根据ID更新工单责任信息<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-7
	 * @param Duty
	 * @return int
	 */
	public int updateDutyById(Duty duty){
		return (Integer)this.getSqlSession().update(NAMESPACE_DUTY+UPDATEDUTYBYID,duty);
	}
   /**
	 * <p>
	 * 统计工单审批总数<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-12
	 * @param queryDutyCondition
	 * @return
	 * int
	 * @see com.deppon.crm.module.duty.server.dao.IDutyDao#countQueryDutyApprovalList(com.deppon.crm.module.duty.shared.domain.QueryDutyCondition)
	 */
	@Override
	public int countQueryDutyApprovalList(QueryDutyCondition queryDutyCondition) {
		return (Integer)this.getSqlSession().selectOne(NAMESPACE_DUTY + COUNT_QUERY_DUTY_APPROVAL_LIST, queryDutyCondition);
	}
	/** <p>
	 * 查询工单审批列表<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-12
	 * @param queryDutyCondition
	 * @param start
	 * @param limit
	 * @return
	 * List<DutyQueryResult>
	 * @see com.deppon.crm.module.duty.server.dao.IDutyDao#queryDutyApprovalList(com.deppon.crm.module.duty.shared.domain.QueryDutyCondition, int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DutyQueryResult> queryDutyApprovalList(
			QueryDutyCondition queryDutyCondition, int start, int limit) {
		RowBounds rb = new RowBounds(start, limit);
		return this.getSqlSession().selectList(NAMESPACE_DUTY + QUERY_DUTY_APPROVAL_LIST, queryDutyCondition, rb);
	}
	/**
	 * <p>
	 * Description:工单责任 -查询-工单责任详情<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-11
	 */
	@Override
	public Duty getDutyDetail(String dutyId) {
		return (Duty) this.getSqlSession().selectOne(NAMESPACE_DUTY+GETDUTYDETAIL, dutyId);
	}
	/**
	 * <p>
	 * Description:工单责任 -查询-处理经过详情<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-11
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DutyDealProcess> getDutyDealProcess(String dutyId) {
		return (List<DutyDealProcess>) this.getSqlSession().selectList(NAMESPACE_DUTY+GETDUTYDEALPROCESS, dutyId);
	}
	/**
	 * <p>
	 * Description:工单责任 -查询-划分结果<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-11
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DutyResult> getDutyResult(String dutyId) {
		return (List<DutyResult>) this.getSqlSession().selectList(NAMESPACE_DUTY+GETDUTYRESULT, dutyId);
	}
	/**
	 * <p>
	 * Description:工单责任 -查询-通知对象<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-11
	 */
	@Override
	public List<String> getuserNameList(String dutyId) {
		return (List<String>) this.getSqlSession().selectList(NAMESPACE_DUTY+GETUSERNAMELIST, dutyId);
	}
	/**
	 * <p>
	 * Description: 工单查询-展开表格<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-14
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DutyQueryResult> queryDutyDetailList(String dutyResultId) {
		return (List<DutyQueryResult>) this.getSqlSession().selectList(NAMESPACE_DUTY+QUERYDUTYDETAILLIST, dutyResultId);
	}
	/**
	 * <p>
	 * Description: 工单责任插入（在工单插入时，执行责任插入）<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-20
	 */
	@Override
	public void complaintsInsert(Complaint complaint) {
		this.getSqlSession().insert(NAMESPACE_DUTY+COMPLAINTSINSERT, complaint);
		
	}
	/**
	 * <p>
	 * Description:  工单责任插入（在工单修改时，执行责任修改）<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-20
	 */
	@Override
	public void updateComplaint(Complaint complaint) {
		this.getSqlSession().update(NAMESPACE_DUTY+COMPLAINTSUPDATE, complaint);
		
	}
	/**
	 * <p>
	 * Description: 修改暂存的处理经过<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-20
	 */
	@Override
	public void updateProcessPass(String processPass, String modifyUser,String dutyId) {
		Map map = new HashMap();
		map.put("modifyUser", modifyUser);
		map.put("processPass", processPass);
		map.put("dutyId", dutyId);
		this.getSqlSession().update(NAMESPACE_DUTY+UPDATEPROCESSPASS, map);
		
		
	}
	/**
	 * <p>
	 * Description: 修改状态<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-20
	 */
	@Override
	public void updateStatus(String status, String modifyUser, String dutyId) {
		Map map = new HashMap();
		map.put("modifyUser", modifyUser);
		map.put("status", status);
		map.put("dutyId", dutyId);
		this.getSqlSession().update(NAMESPACE_DUTY+UPDATESTATUS, map);
	}
	/**
	 * <p>
	 * Description: 修改调查状态<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-20
	 */
	@Override
	public void updateSurveyResult(String surveyResult, String modifyUser, String dutyId) {
		Map map = new HashMap();
		map.put("modifyUser", modifyUser);
		map.put("surveyResult", surveyResult);
		map.put("dutyId", dutyId);
		this.getSqlSession().update(NAMESPACE_DUTY+UPDATESURVEYRESULT, map);
	}
	
	@Override
	public List<String> getAllParentDeptId(String detailId) {
		List<String> ids = this.getSqlSession().selectList(NAMESPACE_DUTY+GETALLPARENTDEPTBYDETAILID, detailId);
		return ids;
	}
	
}