package com.deppon.crm.module.complaint.server.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.complaint.shared.domain.CellphoneMessageInfo;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.domain.ComplaintSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.TaskComplaintCount;
import com.deppon.crm.module.complaint.shared.domain.TaskResult;
import com.deppon.crm.module.complaint.shared.domain.WorkOrder;
import com.deppon.crm.module.duty.shared.domain.Duty;
import com.deppon.crm.module.duty.shared.domain.QueryDutyCondition;
public interface IComplaintDao  {
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
	 List searchComplaints(ComplaintSearchCondition condition);

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
	 Integer searchComplaintsCount(ComplaintSearchCondition condition);
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
	 List searchReportComplaints(ComplaintSearchCondition condition);
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
	 Integer searchReportComplaintsCount(ComplaintSearchCondition condition);
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
	 List<Complaint> searchWaitProccesComplaint(List<String> comStatus,List<String> specialStatus,List<String> feedbackStatus,String userCode,String userId,int start,int limit);
	/**
	 * 计算待办工单总数
	 * @param commonStatus
	 * @param specialStatus
	 * @param userCode
	 * @param userId
	 * @return
	 */
	 Integer searchWaitProccesComplaintCount(List<String> commonStatus, List<String> specialStatus,List<String> feedbackStatus,String userCode,String userId);		
	/**
	 * @Title:      getFreeExpiredComplaintForAccess
	 * Description: 根据上报类型查询已过期或者是未接入的单子（以优先级和上报时间为优先顺序）
	 * @param       parameterMap={reportType:'上报类型',sessionUser:'当前session用户',count:'显示的数据数'}  
	 * @return      List<Complaint>
	 * @throws
	 */
	 List<Complaint> getFreeExpiredComplaintForAccess(Map<String,Object> parameterMap);
	/**
	 * @Title:      getRecentlyLoComplaintsByReportType
	 * Description: 根据上报类型查询自己锁定的待处理工单
	 * @param       parameterMap={reportType:'上报类型',sessionUser:'当前session用户',count:'显示的数据数'}  
	 * @return      List<Complaint>
	 * @throws
	 */
	 List<Complaint> getLockingComplaintsByReportType(Map<String,Object> parameterMap);
	 /**
	 * @Title:      saveComplaint
	 * Description: 保存工单
	 * @param       parameterMap={complaint:'工单'}  
	 * @return      Integer
	 * @throws
	 */
	 Integer saveComplaint(Complaint complaint);
	
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
	 int updateComplaint(Complaint complaint);
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
	 int deleteComplaint(BigDecimal id);
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
	 Complaint getComplaintById(BigDecimal id);
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
	 List<Complaint> getComplaintResult(ComplaintSearchCondition condition);
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
	 Integer getComplaintResultCount(ComplaintSearchCondition condition);
	/**
	* @Title: 		searchTaskComplaints
	* @Description: TODO(根据条件查询工单回访数据)
	* @param 		@param condition
	* @param 		@return    设定文件
	* @return 		List    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	 List<TaskResult> searchTaskComplaints(ComplaintSearchCondition condition);
	 Integer searchTaskComplaintsCount(ComplaintSearchCondition condition);
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
	 List<Complaint> getReturnComplaintList(ComplaintSearchCondition condition);
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
	 Integer getReturnComplaintCount(ComplaintSearchCondition condition);
	/** 
	* @Title: 		searchComplaintCustomersByDate
	* @Description: TODO(根据月份，查询当月投诉客户)
	* @param 		@param date "yyyy-MM"
	* @param 		@return    设定文件
	* @return 		List<String>    返回类型  customerCodes
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	 List<String> searchCustComplaints(ComplaintSearchCondition condition);
	/**
	* @Title: 		getAllComplaintByCustCode
	* @Description: TODO(根据客户ID获得客户最近一次投诉记录)
	* @param 		@param custCode
	* @param 		@return    设定文件
	* @return 		List<Complaint>    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	 List<Complaint> getAllComplaintByCustCode(ComplaintSearchCondition condition);
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
	 String getDealBill();
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
	 List<Complaint> getVeryfiyComplaints(ComplaintSearchCondition condition);
	 Integer getVeryfiyComplaintsCount(ComplaintSearchCondition condition);
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
	 Integer getAccessComplaintsCount(ComplaintSearchCondition condition); 
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
	 List<Complaint> getAccessComplaints(ComplaintSearchCondition condition);
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
	 Integer getPendingComplaintsCount(ComplaintSearchCondition condition);
	/**
	 * 上报退回待办信息
	 * @author justin.xu
	 * @version 0.1 2012-6-6 
	 * @param condition
	 * @return
	 * List<Message>
	 */
	 List<Message> inquireAllUserComplaintResultCount(ComplaintSearchCondition condition);
	/**
	 * 工单回访待办信息
	 * @author justin.xu
	 * @version 0.1 2012-6-6 
	 * @param condition
	 * @return
	 * List<Message>
	 */
	 List<Message> inquireAllUserVeryfiyComplaintsCount(ComplaintSearchCondition condition);
	/**
	 * 部门工单待办信息
	 * @author justin.xu
	 * @version 0.1 2012-6-7
	 * @param condition
	 * @return
	 * List<Message>
	 */
	 List<TaskComplaintCount> inquireAllTaskComplaintsCount(ComplaintSearchCondition condition);
	/**
	 * 部门工单个人待办信息
	 * @author justin.xu
	 * @version 0.1 2012-6-7
	 * @param condition
	 * @return
	 * List<Message>
	 */
	 List<TaskComplaintCount> inquireAllUserTaskComplaintsCount(ComplaintSearchCondition condition);
	
	/**
	 * 待办工单待办信息
	 * @author justin.xu
	 * @version 0.1 2012-6-7 
	 * @param condition
	 * @return
	 * List<Message>
	 */
	 List<Message> inquireAllUserWaitProcessCount(List<String> commonStatus, List<String> specialStatus,List<String>feedbackStatus);
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
	 List<Complaint> getComplaintIncludeSatisfy(ComplaintSearchCondition condition);
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
	 Integer getComplaintIncludeSatisfyCount(ComplaintSearchCondition condition);
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
	 Integer inquireUserIdByEmployeeId(String employeeId);
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
	 List<Complaint> getUserLockedUnExpiredComplaints(Map<String,Object> parameterMap);
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
	 void addNewRelation(String compId, String serviceId,String loginName);
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
	 boolean insertCompAccessLog(Map<String,Object> map);
	List<Duty> queryDutyList(QueryDutyCondition queryDutyCondition);
	
	/**
	 * <p>
	 * 根据出发部门Id查询部门经理<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2013-07-12
	 * @param leaveDeptId
	 * List<CellphoneMessageInfo>
	 */
	List<CellphoneMessageInfo> findManagerByDeptId(String leaveDeptId);
	
	/**
	 * <p>
	 * 根据出发部门Id查询部门副经理<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2013-07-12
	 * @param leaveDeptId
	 * List<CellphoneMessageInfo>
	 */
	List<CellphoneMessageInfo> findAssistantManagerByDeptId(String leaveDeptId);
	
	/**
	 * 根据出发部门ID查询userID
	 * @param leaveDeptId 出发部门ID
	 * @return
	 */
	public List<String> findUserIdByDeptId(String leaveDeptId);
	
	/**
	 * 生成待办事宜
	 * @param message
	 */
	public void addTodoMessage(Message message);
	
	void complaintTimeoutScheduler();
}
