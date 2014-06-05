package com.deppon.crm.module.complaint.server.dao.impl;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.complaint.server.dao.IComplaintDao;
import com.deppon.crm.module.complaint.server.util.Constants;
import com.deppon.crm.module.complaint.shared.domain.CellphoneMessageInfo;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.domain.ComplaintSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.TaskComplaintCount;
import com.deppon.crm.module.complaint.shared.domain.TaskResult;
import com.deppon.crm.module.complaint.shared.domain.WorkOrder;
import com.deppon.crm.module.duty.shared.domain.Duty;
import com.deppon.crm.module.duty.shared.domain.QueryDutyCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class ComplaintDaoImpl extends iBatis3DaoImpl implements IComplaintDao {
	// 命名空间
	private static final String NAMESPACE_COMPLAINT = "com.deppon.crm.module.complaint.shared.domain.Complaint.";
	// 查询待投诉列表
	private static final String SEARCH_WAIT_PROCCESS_COMPLAINT = "searchWaitProccesComplaint";
	// 查询待投诉列表 条数
	private static final String SEARCH_WAIT_PROCCESS_COMPLAINT_COUNT = "searchWaitProccesComplaintCount";	
	//根据上报类型查询已过期或者是未接入的单子（以优先级和上报时间为优先顺序）
	private static final String GET_FREE_EXPIRED_COMPLAINT_FOR_ACCESS = "getFreeExpiredComplaintForAccess";
	//根据上报类型查询自己锁定的待处理工单
	private static final String GET_RENCENTLY_THREE_COMPLAINT_LOCKING = "getLockingComplaint";
	// 查询上报工单
	private static final String SEARCH_REPORT_COMPLAINT = "selectReportComplaints";
	// 查询上报工单-条数
	private static final String SEARCH_REPORT_COMPLAINT_COUNT = "selectReportComplaintsCount";
	// 工单-插入
	private static final String COMPLAINT_INSERT = "complaintsInsert";
	/*private static final String GET_WOKEORDER_BY_COMPLAINTID = "getWorkOrderRecordByComplaintId";*/
	// 更新工单
	private static final String UPDATE_COMPLAINT="updateComplaint";
	// 删除工单
	private static final String DELETE_COMPLAINT="deleteComplaint";
	// 通过id查询工单
	private static final String GET_COMPLAINT_BY_ID="getComplaintById";
	// 查询工单结果列表
	private static final String GET_COMPLAINT_RESULT_LIST="getComplaintResult";
	// 查询工单结果列表-条数
	private static final String GET_COMPLAINT_RESULT_LIST_COUNT="getComplaintResultCount";
	// 根据条件查询工单回访数据
	private static final String SELECT_TASK_COMPLAINTS="selectTaskComplaints";
	// 查询工单返回条数
	private static final String GET_RETURN_COMPLAINTS="getReturnComplaint";
	// 查询返回工单
	private static final String GET_RETURN_COMPLAINTS_COUNT="getReturnComplaintCount";
	// 查询客户工单
	private static final String SELECT_CUST_COMPLAINTS="selectCustComplaints";
	// 根据客户编码查询工单
	private static final String GET_ALL_COMPLAINT_BY_CUSTCODE="getAllComplaintByCustCode";
	// 工单回访查询
	private static final String GET_VERYFIY_COMPLAINT="getveryfiyComplaint";
	// 工单回访查询-条数
	private static final String GET_VERYFIY_COMPLAINT_COUNT="getveryfiyComplaintCount";
	// 查询工单
	private static final String SELECT_COMPLAINTS="selectComplaints";
	// 工单条数
	private static final String SELECT_COMPLAINTS_COUNT="selectComplaintsCount";
	// 查询最大的处理编号
	private static final String GET_MAX_DEALBILL="getMaxDealBill";
	// 查询接入工单
	private static final String GET_ACCESS_COMPLAINTS="getAccessComplaints";
	// 查询接入工单条数
	private static final String GET_ACCESS_COMPLAINTS_COUNT="getAccessComplaintsCount";
	// 查询延时工单条数
	private static final String GET_PENDING_COMPLAINTS_COUNT="getPendingComplaintsCount";
	private static final String GET_COMPLAINTS_INCLUDE_SATISFY="getComplaintIncludeSatisfy";
	private static final String GET_COMPLAINTS_INCLUDE_SATISFY_COUNT="getComplaintIncludeSatisfyCount";
	// 得到未超时的已被指定用户锁定的单子
	private static final String GET_USER_LOCKED_UNEXPIRED_COMPLAINTS="getUserLockedUnExpiredComplaints";
	// 新增关系
	private static final String ADD_NEW_RELATION = "addNewRelation";
	//工单接入日志
	private static final String INSERT_COMPLAINT_ACCESS_LOG="insertCompAccessLog";
	// 查询部门经理
	private static final String GET_COMPLAINT_MANAGER = "getComplaintManager";
	// 查询部门副经理
	private static final String GET_COMPLAINT_ASSISTANT_MANAGER = "getComplaintAssistantManager";
	// 根据出发部门查询UserId
	private static final String FIND_USERID_BY_DEPTID = "findUserIdByDeptId";
	//根据ID修改工单的业务类型
	private static final String UPDATE_COMLAINT_BUSINESSMODEL="updateBusinessModel";
	//工单超时定时器
	private static final String COMPLAINT_TIMEOUT_SCHEDULER = "complaintTimeoutScheduler";
	
	/**
	 * <p>
	 * Description:工单接入查询<br />
	 * </p>
	 * @author 
	 * @version 0.1 
	 * @param condition
	 * @return
	 * List<Complaint>
	 */
	@Override
	public List searchComplaints(ComplaintSearchCondition condition) {
		// TODO Auto-generated method stub
		List<Complaint> list = null;
		// 无分页限制
		if (null ==condition.getLimit() || 1> condition.getLimit()) {
			// 工单接入查询，按工单接入时间（升序），处理编号排序（升序）
			condition.setSortType("SORT_REPORTTIME");
			// 查询工单
			list = this.getSqlSession()
					.selectList(NAMESPACE_COMPLAINT+SELECT_COMPLAINTS,
							condition);
		} else {
			// 分页查询
			RowBounds bounds = new RowBounds(condition.getStart(),
					condition.getLimit());
			// 查询工单
			list = this.getSqlSession()
					.selectList(NAMESPACE_COMPLAINT+SELECT_COMPLAINTS,
							condition, bounds);
		}
		return list;
	}

	/**
	 * <p>
	 * Description:工单接入查询-条数<br />
	 * </p>
	 * @author 
	 * @version 0.1 
	 * @param condition
	 * @return
	 * List<Complaint>
	 */
	@Override
	public Integer searchComplaintsCount(ComplaintSearchCondition condition) {
		// TODO Auto-generated method stub
		Integer count = (Integer) this
				.getSqlSession()
				.selectOne(NAMESPACE_COMPLAINT+SELECT_COMPLAINTS_COUNT,
						condition);
		return count;
	}
	 /**
		 * <p>
		 * Description:工单上报查询-条数<br />
		 * </p>
		 * @author 
		 * @version 0.1 
		 * @param condition
		 * @return
		 * List<Complaint>
		 */
	@Override
	public List searchReportComplaints(ComplaintSearchCondition condition) {
		// TODO Auto-generated method stub
		List<Complaint> list = null;
		//无分页限制
		if (null ==condition.getLimit() || 1> condition.getLimit()) {
			// 查询工单
			list = this.getSqlSession()
					.selectList(NAMESPACE_COMPLAINT
							+ SEARCH_REPORT_COMPLAINT,
							condition);
		} else {
			// 分页限制
			RowBounds bounds = new RowBounds(condition.getStart(),
					condition.getLimit());
			// 查询工单
			list = this.getSqlSession()
					.selectList(NAMESPACE_COMPLAINT
							+ SEARCH_REPORT_COMPLAINT,
							condition, bounds);
		}
		return list;
	}
	 /**
		 * <p>
		 * Description:工单上报查询-条数<br />
		 * </p>
		 * @author 
		 * @version 0.1 
		 * @param condition
		 * @return
		 * List<Complaint>
		 */
	@Override
	public Integer searchReportComplaintsCount(
			ComplaintSearchCondition condition) {
		// TODO Auto-generated method stub
		Integer count = (Integer) this
				.getSqlSession()
				.selectOne(NAMESPACE_COMPLAINT
						+ SEARCH_REPORT_COMPLAINT_COUNT,
						condition);
		return count;
	}

	/**
	 * @Title:      getFreeExpiredComplaintForAccess
	 * Description: 根据上报类型查询已过期或者是未接入的单子（以优先级和上报时间为优先顺序）
	 * @param       parameterMap={reportType:'上报类型',sessionUser:'当前session用户',count:'显示的数据数'}  
	 * @return      List<Complaint>
	 * @throws
	 */
	@Override
	public List<Complaint> getFreeExpiredComplaintForAccess(Map<String,Object> parameterMap) {
		parameterMap.put("prostatus", Constants.COMPLAINT_STATUS_PENDING);
		// 分页限制
		Integer limit=(Integer)parameterMap.get("limitCount");
		// 如果为空
		if(limit==null){
			// 默认查10条
			limit=10;
		}
		// 分页限制
		RowBounds rowBounds = new RowBounds(0, limit);
		// 查询工单		
		return this.getSqlSession().selectList(NAMESPACE_COMPLAINT + GET_FREE_EXPIRED_COMPLAINT_FOR_ACCESS, parameterMap,rowBounds);
	}
	/**
	 * @Title:      getRecentlyLoComplaintsByReportType
	 * Description: 根据上报类型查询自己锁定的待处理工单
	 * @param       parameterMap={reportType:'上报类型',sessionUser:'当前session用户',count:'显示的数据数'}  
	 * @return      List<Complaint>
	 * @throws
	 */
	public List<Complaint> getLockingComplaintsByReportType(Map<String,Object> parameterMap){
		parameterMap.put("prostatus", Constants.COMPLAINT_STATUS_PENDING);
		return this.getSqlSession().selectList(NAMESPACE_COMPLAINT + GET_RENCENTLY_THREE_COMPLAINT_LOCKING, parameterMap);
	}
	/**
	 * @Title:      saveComplaint
	 * Description: 保存工单
	 * @param       parameterMap={complaint:'工单'}  
	 * @return      Integer
	 * @throws
	 */
	@Override
	public Integer saveComplaint(Complaint complaint) {
		 this.getSqlSession().insert(
				NAMESPACE_COMPLAINT + COMPLAINT_INSERT,
				complaint);
		return Integer.parseInt(complaint.getFid()+"");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	/**
	 * 
	 * <p>
	 * Description:查询待投诉列表<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-4-12
	 * @param status
	 * @param start
	 * @param limit
	 * @return
	 * List<Complaint>
	 */
	public List<Complaint> searchWaitProccesComplaint(
			List<String> commonStatus, List<String> specialStatus,List<String> feedbackStatus,String userCode,
			String userId, int start, int limit) {
		Map para = new HashMap();
		// 一般状态
		para.put("commonStatus", commonStatus);
		// 特殊状态
		para.put("specialStatus", specialStatus);
		// 用户id
		para.put("userId", userId);
		// 用户工号
		para.put("userCode", userCode);
		// 退回状态
		para.put("feedbackStatus", feedbackStatus);
		// 分页限制
		RowBounds rowBounds = new RowBounds(start, limit);
		return this.getSqlSession()
				.selectList(NAMESPACE_COMPLAINT
						+ SEARCH_WAIT_PROCCESS_COMPLAINT,
						para,rowBounds);
	}
	/**
	 * 计算待办工单总数
	 * @param commonStatus
	 * @param specialStatus
	 * @param userCode
	 * @param userId
	 * @return
	 */
	public Integer searchWaitProccesComplaintCount(
			List<String> commonStatus, List<String> specialStatus,List<String> feedbackStatus,String userCode,
			String userId) {
		Map para = new HashMap();
		// 一般状态
		para.put("commonStatus", commonStatus);
		// 特殊状态
		para.put("specialStatus", specialStatus);
		// 退回状态
		para.put("feedbackStatus", feedbackStatus);
		// 用户id
		para.put("userId", userId);
		// 用户工号
		para.put("userCode", userCode);
		Object obj=getSqlSession().selectOne(NAMESPACE_COMPLAINT+SEARCH_WAIT_PROCCESS_COMPLAINT_COUNT, para);
		Integer count=0;
		if(obj!=null){
			count=(Integer)obj;
		}
		return count;
	}	
	
	
	/**
	 * 
	 * <p>
	 * Description:更新工单信息<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-4-20
	 * @param complaint 工单信息
	 * @return
	 * int 影响行数
	 */
	@Override
	public int updateComplaint(Complaint complaint) {
		return this.getSqlSession().update(NAMESPACE_COMPLAINT+UPDATE_COMPLAINT, complaint);
	}
	/**
	 * <p>
	 * Description:删除工单信息<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-4-20
	 * @param id
	 * @return
	 * int
	 */
	@Override
	public int deleteComplaint(BigDecimal id) {
		return this.getSqlSession().delete(NAMESPACE_COMPLAINT + DELETE_COMPLAINT,id);
	}
	/**
	 * <p>
	 * Description:根据ID查询工单信息<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-4-20
	 * @param id
	 * @return
	 * Complaint
	 */
	@Override
	public Complaint getComplaintById(BigDecimal id) {
		return (Complaint) this.getSqlSession().selectOne(NAMESPACE_COMPLAINT+GET_COMPLAINT_BY_ID,id);
	}
	/**
	 * <p>
	 * Description:查询回访页面list<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-23
	 * @param condition
	 * @return
	 * List<Complaint>
	 */
	@Override
	public List<Complaint> getComplaintResult(ComplaintSearchCondition condition) {
		// 返回结果集
		List<Complaint> compList=null;
		// 如果没有分页限制
		if (null !=condition.getLimit() && 0< condition.getLimit()){
			// 新建一个分页
			RowBounds rowBounds = new RowBounds(condition.getStart(), condition.getLimit());
			// 查询工单
			compList=this.getSqlSession().selectList(NAMESPACE_COMPLAINT+GET_COMPLAINT_RESULT_LIST,condition,rowBounds);
		}else{
			// 查询工单
			compList=this.getSqlSession().selectList(NAMESPACE_COMPLAINT+GET_COMPLAINT_RESULT_LIST,condition);
		}
		return compList;
	}
	 /**
	 * <p>
	 * Description:查询工单结果-条数<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-23
	 * @param condition
	 * @return
	 * List<Complaint>
	 */
	public Integer getComplaintResultCount(ComplaintSearchCondition condition){
		return (Integer)this.getSqlSession().selectOne(NAMESPACE_COMPLAINT+GET_COMPLAINT_RESULT_LIST_COUNT,condition);
	}
	/**
	* @Title: 		searchTaskComplaints
	* @Description: TODO(根据条件查询工单回访数据)
	* @param 		@param condition
	* @param 		@return    设定文件
	* @return 		List    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	@Override
	public List<TaskResult> searchTaskComplaints(ComplaintSearchCondition condition) {
		// 返回结果集
		List<TaskResult> list = null;
		// 如果没有分页限制
		if (null ==condition.getLimit() || 1> condition.getLimit()) {
			// 查询
			list = this.getSqlSession()
					.selectList(NAMESPACE_COMPLAINT
							+ SELECT_TASK_COMPLAINTS,
							condition);
		} else {
			// 分页限制
			RowBounds bounds = new RowBounds(condition.getStart(),
					condition.getLimit());
			// 查询
			list = this.getSqlSession()
					.selectList(NAMESPACE_COMPLAINT
							+ SELECT_TASK_COMPLAINTS,
							condition, bounds);
		}
		return list;
	}
	
	public Integer searchTaskComplaintsCount(ComplaintSearchCondition condition) {
		// TODO Auto-generated method stub
		Integer count = (Integer) this
				.getSqlSession()
				.selectOne("com.deppon.crm.module.complaint.shared.domain.Complaint.selectTaskComplaintsCount",
						condition);
		return count;
	}
	/**
	 * 
	 * <p>
	 * Description:工单退回页面查询<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-23
	 * @param condition
	 * @return
	 * List<Complaint>
	 */
	@Override
	public List<Complaint> getReturnComplaintList(
			ComplaintSearchCondition condition) {
		// 返回结果集
		List<Complaint> list = null;
		// 如果没有分页限制
		if (null ==condition.getLimit() || 1> condition.getLimit()) {
			// 查询
			list = this.getSqlSession().selectList(NAMESPACE_COMPLAINT+ GET_RETURN_COMPLAINTS,condition);
		} else {
			// 分页限制
			RowBounds bounds = new RowBounds(condition.getStart(),
					condition.getLimit());
			// 查询
			list = this.getSqlSession().selectList(NAMESPACE_COMPLAINT+ GET_RETURN_COMPLAINTS,condition, bounds);
		}
		return list;
	}
	/**
	 * 
	 * <p>
	 * Description:工单退回-条数<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-23
	 * @param condition
	 * @return
	 * Integer
	 */
	@Override
	public Integer getReturnComplaintCount(
			ComplaintSearchCondition condition) {
		// 查询 条数
		Integer count = (Integer) this
				.getSqlSession()
				.selectOne(NAMESPACE_COMPLAINT+GET_RETURN_COMPLAINTS_COUNT,
						condition);
		// 返回条数
		return count;
	}
	/** 
	* @Title: 		searchComplaintCustomersByDate
	* @Description: TODO(根据月份，查询当月投诉客户)
	* @param 		@param date "yyyy-MM"
	* @param 		@return    设定文件
	* @return 		List<String>    返回类型  customerCodes
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	@Override
	public List<String> searchCustComplaints(ComplaintSearchCondition condition) {
		return this.getSqlSession().selectList(NAMESPACE_COMPLAINT+ SELECT_CUST_COMPLAINTS,condition);
	}
	/**
	* @Title: 		getAllComplaintByCustCode
	* @Description: TODO(根据客户ID获得客户最近一次投诉记录)
	* @param 		@param custCode
	* @param 		@return    设定文件
	* @return 		List<Complaint>    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	@Override
	public List<Complaint> getAllComplaintByCustCode(ComplaintSearchCondition condition) {
		return this.getSqlSession().selectList(NAMESPACE_COMPLAINT+ GET_ALL_COMPLAINT_BY_CUSTCODE,condition);
	}
	/**
	 * <p>
	 * Description:得到处理编号<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-27
	 * @param 
	 * @return
	 * String
	 */
	@Override
	public String getDealBill() {
		return (String)this.getSqlSession().selectOne(NAMESPACE_COMPLAINT+GET_MAX_DEALBILL);
	}
	/**
	 * 
	 * <p>
	 * Description:工单回访查询<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-27
	 * @param condition
	 * @return
	 * String
	 */
	@Override
	public List<Complaint> getVeryfiyComplaints(ComplaintSearchCondition condition) {
		// 返回结果集
		List<Complaint> list = null;
		// 分页限制
		RowBounds bounds = new RowBounds(condition.getStart(),
				condition.getLimit());
		// 如果没有分页限制
		if (null ==condition.getLimit() || 1> condition.getLimit()) {
			// 查询
			list = this.getSqlSession()
					.selectList(NAMESPACE_COMPLAINT
							+ GET_VERYFIY_COMPLAINT,
							condition);
		} else {
			//进行分页查询
			list = this.getSqlSession()
					.selectList(NAMESPACE_COMPLAINT
							+ GET_VERYFIY_COMPLAINT,
							condition, bounds);
		}
		// 返回结果集
		return list;
	}

	@Override
	public Integer getVeryfiyComplaintsCount(ComplaintSearchCondition condition) {
		// TODO Auto-generated method stub
		return (Integer)this.getSqlSession().selectOne(NAMESPACE_COMPLAINT+ GET_VERYFIY_COMPLAINT_COUNT,condition);
	}
	/**
	 * 
	 * <p>
	 * Description:经理查询接入count<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-5-30
	 * @param condition
	 * @return
	 * Integer
	 */
	@Override
	public Integer getAccessComplaintsCount(ComplaintSearchCondition condition){
		return (Integer)this.getSqlSession().selectOne(NAMESPACE_COMPLAINT+ GET_ACCESS_COMPLAINTS_COUNT,condition);
	}
	/**
	 * 
	 * <p>
	 * Description:经理查询接入<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-5-30
	 * @param condition
	 * @return
	 * List<Complaint>
	 */
	@Override
	public List<Complaint> getAccessComplaints(ComplaintSearchCondition condition){
		// 返回结果集
		List<Complaint> list = null;
		// 如果没有分页限制
		if (null == condition.getLimit() || 1 > condition.getLimit()) {
			// 查询
			list = this.getSqlSession().selectList(
					NAMESPACE_COMPLAINT + GET_ACCESS_COMPLAINTS, condition);
		} else {
			// 分页限制
			RowBounds bounds = new RowBounds(condition.getStart(),
					condition.getLimit());
			// 查询
			list = this.getSqlSession().selectList(
					NAMESPACE_COMPLAINT + GET_ACCESS_COMPLAINTS, condition,
					bounds);
		}
		// 返回结果
		return list;
	}
	/**
	 * 
	 * <p>
	 * Description:查询待办工单数<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-6-5
	 * @param condition
	 * @return
	 * List<Complaint>
	 */
	@Override
	public Integer getPendingComplaintsCount(
			ComplaintSearchCondition condition) {
		//查询待办工单数
		return (Integer)this.getSqlSession().selectOne(NAMESPACE_COMPLAINT+ GET_PENDING_COMPLAINTS_COUNT,condition);
	}
	/**
	 * 上报退回待办信息
	 * @author justin.xu
	 * @version 0.1 2012-6-6 
	 * @param condition
	 * @return
	 * List<Message>
	 */
	@Override
	public List<Message> inquireAllUserComplaintResultCount(
			ComplaintSearchCondition condition) {
		//上报退回待办信息
		return this.getSqlSession().selectList(NAMESPACE_COMPLAINT+"inquireAllUserComplaintResultCount",condition);
	}
	/**
	 * 工单回访待办信息
	 * @author justin.xu
	 * @version 0.1 2012-6-6 
	 * @param condition
	 * @return
	 * List<Message>
	 */
	@Override
	public List<Message> inquireAllUserVeryfiyComplaintsCount(
			ComplaintSearchCondition condition) {
		//工单回访待办信息
		return this.getSqlSession().selectList(NAMESPACE_COMPLAINT+"inquireAllUserVeryfiyComplaintsCount",condition);
	}
	/**
	 * 部门工单待办信息
	 * @author justin.xu
	 * @version 0.1 2012-6-7
	 * @param condition
	 * @return
	 * List<Message>
	 */
	@Override
	public List<TaskComplaintCount> inquireAllTaskComplaintsCount(
			ComplaintSearchCondition condition) {
		//部门工单待办信息
		return this.getSqlSession().selectList(NAMESPACE_COMPLAINT+"inquireAllTaskComplaintsCount",condition);
	}
	/**
	 * 部门工单个人待办信息
	 * @author justin.xu
	 * @version 0.1 2012-6-7
	 * @param condition
	 * @return
	 * List<Message>
	 */
	@Override
	public List<TaskComplaintCount> inquireAllUserTaskComplaintsCount(
			ComplaintSearchCondition condition) {
		//部门工单个人待办信息
		return this.getSqlSession().selectList(NAMESPACE_COMPLAINT+"inquireAllUserTaskComplaintsCount",condition);
	}
	/**
	 * 待办工单待办信息
	 * @author justin.xu
	 * @version 0.1 2012-6-7 
	 * @param condition
	 * @return
	 * List<Message>
	 */
	@Override
	public List<Message> inquireAllUserWaitProcessCount(
			List<String> commonStatus, List<String> specialStatus,
			List<String> feedbackStatus) {
		Map para = new HashMap();
		// 一般状态
		para.put("commonStatus", commonStatus);
		// 特殊状态
		para.put("specialStatus", specialStatus);
		// 退回状态
		para.put("feedbackStatus", feedbackStatus);
		// 返回查询结果
		return this.getSqlSession().selectList(NAMESPACE_COMPLAINT+ "inquireAllUserWaitProcessCount",para);
	}
	/**
	 * 
	 * <p>
	 * Description:给360提供接口<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-6-12
	 * @param condition
	 * @return
	 * List<Complaint>
	 */
	@Override
	public List<Complaint> getComplaintIncludeSatisfy(
			ComplaintSearchCondition condition) {
		//返回结果
		List<Complaint> list = null;
		// 如果没有分页限制
		if (null == condition.getLimit() || 1 > condition.getLimit()) {
			// 查询
			list = this.getSqlSession().selectList(
					NAMESPACE_COMPLAINT + GET_COMPLAINTS_INCLUDE_SATISFY, condition);
		} else {
			// 分页限制
			RowBounds bounds = new RowBounds(condition.getStart(),
					condition.getLimit());
			// 查询
			list = this.getSqlSession().selectList(
					NAMESPACE_COMPLAINT + GET_COMPLAINTS_INCLUDE_SATISFY, condition,
					bounds);
		}
		return list;
	}
	/**
	 * 
	 * <p>
	 * Description:给360提供接口<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-6-12
	 * @param condition
	 * @return
	 * List<Complaint>
	 */
	@Override
	public Integer getComplaintIncludeSatisfyCount(
			ComplaintSearchCondition condition) {
		return (Integer)this.getSqlSession().selectOne(NAMESPACE_COMPLAINT+ GET_COMPLAINTS_INCLUDE_SATISFY_COUNT,condition);
	}
	/**
	 * <p>
	 * Description:根据employeeId得到userid<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2012-6-13
	 * @param condition
	 * @return
	 * List<Complaint>
	 */
	@Override
	public Integer inquireUserIdByEmployeeId(String employeeId) {
		//根据employeeId得到userid
		return (Integer)this.getSqlSession().selectOne(NAMESPACE_COMPLAINT+ "inquireUserIdByEmployeeId",employeeId);
	}

	/**
	 * <p>
	 * Description:得到未超时的已被指定用户锁定的单子<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2012-6-13
	 * @param condition
	 * @return
	 * List<Complaint>
	 */	
	@Override
	public List<Complaint> getUserLockedUnExpiredComplaints(Map<String,Object> parameterMap){
		// 分页限制
		Integer limit=(Integer)parameterMap.get("limitCount");
		// 分页为空
		if(limit==null){
			// 默认为10
			limit=10;
		}
		// 分页限制
		RowBounds rowBounds = new RowBounds(0, limit);
		// 查询
		return this.getSqlSession().selectList(NAMESPACE_COMPLAINT + GET_USER_LOCKED_UNEXPIRED_COMPLAINTS, parameterMap,rowBounds);		
		
	}
	/**
	 * <p>
	 * 新增关系<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-8-7
	 * @param compId
	 * @param serviceId
	 * void
	 */
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.complaint.server.dao.IComplaintDao#addNewRelation(java.lang.Integer, java.lang.String)
	 */
	@Override
	public void addNewRelation(String compId, String serviceId,String loginName) {
		// 查询条件
		Map<String,String> map = new HashMap<String, String>();
		// 工单id
		map.put("compId", compId);
		// 服务id
		map.put("serviceId", serviceId);
		// 登陆名
		map.put("loginName", loginName);
		// 新增关系
		this.getSqlSession().insert(NAMESPACE_COMPLAINT+ADD_NEW_RELATION, map);
	}
	/**
	 * 
	 * <p>
	 * 工单接入日志<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-8-22
	 * @param map
	 * @return
	 * boolean
	 */
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
	public List<Duty> queryDutyList(QueryDutyCondition queryDutyCondition) {
		
		return this.getSqlSession().selectList(NAMESPACE_COMPLAINT+"dutyListSearch", queryDutyCondition);
	}

	@Override
	public boolean insertCompAccessLog(Map<String, Object> map) {
		// 新增工单接入日志
		return this.getSqlSession().insert(NAMESPACE_COMPLAINT+INSERT_COMPLAINT_ACCESS_LOG, map) > 0 ? true : false;
	}
	
	/**
	 * <p>
	 * 根据出发部门Id查询部门经理<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2013-07-12
	 * @param leaveDeptId
	 * List<CellphoneMessageInfo>
	 */
	@Override
	public List<CellphoneMessageInfo> findManagerByDeptId(String leaveDeptId) {
		return this.getSqlSession().selectList(NAMESPACE_COMPLAINT + GET_COMPLAINT_MANAGER, leaveDeptId);
	}
	
	/**
	 * <p>
	 * 根据出发部门Id查询部门副经理<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2013-07-12
	 * @param leaveDeptId
	 * List<CellphoneMessageInfo>
	 */
	@Override
	public List<CellphoneMessageInfo> findAssistantManagerByDeptId(String leaveDeptId) {
		return this.getSqlSession().selectList(NAMESPACE_COMPLAINT + GET_COMPLAINT_ASSISTANT_MANAGER, leaveDeptId);
	}
	
	/**
	 * 根据出发部门ID查询userID
	 * @param leaveDeptId 出发部门ID
	 * @return
	 */
	@Override
	public List<String> findUserIdByDeptId(String leaveDeptId) {
		return this.getSqlSession().selectList(NAMESPACE_COMPLAINT + FIND_USERID_BY_DEPTID, leaveDeptId);
	}
	
	/**
	 * 生成待办事宜
	 * @param message
	 */
	@Override
	public void addTodoMessage(Message message) {
		if (message != null) {
			this.getSqlSession()
					.insert("com.deppon.crm.module.common.shared.domain.Message.addMessage",
							message);
		}
	}
	
	public void complaintTimeoutScheduler() {
		this.getSqlSession().selectOne(NAMESPACE_COMPLAINT+COMPLAINT_TIMEOUT_SCHEDULER);
	}
}