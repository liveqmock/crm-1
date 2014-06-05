package com.deppon.crm.module.complaint.server.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.complaint.shared.domain.CellphoneMessageInfo;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.domain.ComplaintSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.Result;
import com.deppon.crm.module.complaint.shared.domain.TaskComplaintCount;
import com.deppon.crm.module.complaint.shared.domain.TaskResult;
import com.deppon.crm.module.complaint.shared.domain.WorkOrder;
/**
 * 
 * <p>
 * Description:工单实现类<br />
 * </p>
 * @title IComplaintService.java
 * @package com.deppon.crm.module.complaint.server.service 
 * @author ouy
 * @version 0.1 2012-4-12
 */
public interface IComplaintService {
	 List<Complaint> searchComplaints(ComplaintSearchCondition csc);

	/**
	 * 
	 * <p>
	 * Description:查询当前用户待办工单<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-4-17
	 * @param comStatus 工单状态
	 * @param proStatus 部门处理状态
	 * @param userCode 员工ID
	 * @param start
	 * @param limit
	 * @return
	 * List<Complaint>
	 */
	 List<Complaint> searchWaitProccesComplaint(List<String> comStatus,List<String> specialStatus,List<String>feedbackStatus,String userCode,String userId, int start,int limit);
	
	/**
	 * 查询当前待办工单的总数
	 * @param commonStatus
	 * @param specialStatus
	 * @param userCode
	 * @param userId
	 * @return
	 */
	 Integer searchWaitProccesComplaintCount(List<String> commonStatus, List<String> specialStatus,List<String>feedbackStatus,String userCode,String userId);		
	
	 Integer searchComplaintsCount(ComplaintSearchCondition csc);
	/**
	 * @Title:      getFreeExpiredComplaintForAccess
	 * Description: 普通处理员接入工单
	 * @param       parameterMap={reportType:'上报类型',sessionUser:'当前session用户'}  
	 * @return      List<Complaint>
	 */	
	 List<Complaint> getAccessComplaints(Map<String,Object> parameterMap);
	
	/**
	 * @Title:      getRecentlyLoComplaintsByReportType
	 * Description: 根据上报类型查询自己锁定的待处理工单
	 * @param       parameterMap={reportType:'上报类型',sessionUser:'当前session用户',count:'显示的数据数'}  
	 * @return      List<Complaint>
	 * @throws
	 */
	 List<Complaint> getLockingComplaintsByReportType(Map<String,Object> parameterMap);
	 List<Complaint> searchReportComplaints(ComplaintSearchCondition csc);
	 Integer searchReportComplaintsCount(ComplaintSearchCondition csc);
	/**
	 * <p>
	 * Description:完成工单处理Service<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-4-18
	 * @param complaint
	 * @param result
	 * @return
	 * int
	 */
	 int finishedComplaintProccess(Complaint complaint,List<Result> result);
	/**
	 * 
	 * <p>
	 * Description:保存工单<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-19
	 * @param complaint
	 * @return
	 * Integer
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
	 * int  影响记录行数
	 */
	 int updateComplaint(Complaint complaint);
	/**
	 * <p>
	 * Description:删除工单信息<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-4-20
	 * @param id 工单ID
	 * @return
	 * int 删除行数
	 */
	 int deleteComplaint(BigDecimal id);
	
	/**
	 * <p>
	 * Description:根据ID查询工单信息<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-4-20
	 * @param id 工单ID
	 * @return
	 * Complaint 工单信息
	 */
	 Complaint getComplaintById(String id);
	/**
	 * 
	 * <p>
	 * Description:工单回访查询页面<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-23
	 * @param condition
	 * @return
	 * List<Complaint>
	 */
	 List<Complaint> getComplaintResultList(ComplaintSearchCondition condition);
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

	/**
	* @Title: 		searchTaskComplaintsCount
	* @Description: TODO(根据条件查询工单回访数据总数)
	* @param 		@param condition
	* @param 		@return    设定文件
	* @return 		Integer    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	 Integer searchTaskComplaintsCount(ComplaintSearchCondition condition);
	
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
	 * 
	 * <p>
	 * Description:生成处理编号<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-27
	 * @return
	 * String
	 */
	 String getDealbill();
	/**
	 * 
	 * <p>
	 * Description:回访<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-27
	 * @param condition
	 * @return
	 * List<Complaint>
	 */
	 List<Complaint> getVeryfiyComplaints(ComplaintSearchCondition condition);
	/**
	 * 
	 * <p>
	 * Description:回访<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-27
	 * @param condition
	 * @return
	 * List<Complaint>
	 */
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
	public List<Message> inquireAllUserVeryfiyComplaintsCount(ComplaintSearchCondition condition);
	
	/**
	 * 部门工单待办信息
	 * @author justin.xu
	 * @version 0.1 2012-6-6 
	 * @param condition
	 * @return
	 * List<Message>
	 */
	public List<TaskComplaintCount> inquireAllTaskComplaintsCount(ComplaintSearchCondition condition);
	/**
	 * 部门工单个人待办信息
	 * @author justin.xu
	 * @version 0.1 2012-6-6 
	 * @param condition
	 * @return
	 * List<Message>
	 */
	public List<TaskComplaintCount> inquireAllUserTaskComplaintsCount(ComplaintSearchCondition condition);	
	/**
	 * 待办工单待办信息
	 * @author justin.xu
	 * @version 0.1 2012-6-7 
	 * @param condition
	 * @return
	 * List<Message>
	 */
	public List<Message> inquireAllUserWaitProcessCount(List<String> commonStatus, List<String> specialStatus,List<String>feedbackStatus);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-6-12
	 * @param condition
	 * @return
	 * List<Complaint>
	 */
	 List<Complaint> getComplaintIncludeSatisfy(ComplaintSearchCondition condition);
	/**
	 * <p>
	 * Description:这里写描述<br />
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
	 * 新增工单Id与呼叫中心ServiceId关系<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-8-7
	 * @param compId
	 * @param serviceId
	 * void
	 */
	 void addNewRelation(String compId, String serviceId,String loginName);

	/**
	 * <p>
	 * 工单接入日志<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-8-22
	 * @param map
	 * void
	 */
	 boolean insertCompAccessLog(Map<String, Object> map);
	 
	 /**
	  * 
	  * <p>
	  * Description:根据运单号查询运单信息<br />
	  * </p>
	  * @author roy
	  * @version 0.1 2013-7-10
	  * @param waybillNumber
	  * @return
	  * FossWaybillInfo
	  */
	 FossWaybillInfo findWaybillByNO(String waybillNumber);
	 
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
	 * 根据出发部门查询userID
	 * @param leaveDeptId 出发部门ID
	 * @return
	 */
	List<String> findUserIdByDeptId(String leaveDeptId);
	
	/**
	 * 生成待办事宜
	 * @param message
	 */
	void addTodoMessage(Message message);
	/**
	 * 修改官网工单的业务类型
	 * @param complaint
	 */
	Boolean changeBusinessModel(Complaint complaint);
	
	void complaintTimeoutScheduler();
}
