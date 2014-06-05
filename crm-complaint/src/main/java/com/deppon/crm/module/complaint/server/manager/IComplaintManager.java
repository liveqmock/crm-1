package com.deppon.crm.module.complaint.server.manager;
  
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.complaint.shared.domain.BasciLevel;
import com.deppon.crm.module.complaint.shared.domain.BasciLevelSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.BasciLevelView;
import com.deppon.crm.module.complaint.shared.domain.BasicBusScopeVO;
import com.deppon.crm.module.complaint.shared.domain.BasicBusType;
import com.deppon.crm.module.complaint.shared.domain.BasicInfo;
import com.deppon.crm.module.complaint.shared.domain.BasicSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.Bulletin;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.domain.ComplaintSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.FeedBackReasion;
import com.deppon.crm.module.complaint.shared.domain.ProcResult;
import com.deppon.crm.module.complaint.shared.domain.Result;
import com.deppon.crm.module.complaint.shared.domain.ResultSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.WorkOrder;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;

/**
* @ClassName: 	IComplaintManager
* @Description: TODO(这里用一句话描述这个类的作用)
* @author 
* @date 		2012-4-16 上午11:30:39
* @see <a href="package.html#section">查看流程图</a>
*
*/
public interface IComplaintManager {
	
	/**
	 * 查询工单
	 * <p>
	 * Description:根据查上报历史工单 <br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-16
	 * @param condition
	 * @return
	 * Map
	 */
	 Map searchReportedComplaints(ComplaintSearchCondition condition);
	 
	 
	 /**
	  * Description:根据查询工单<br />
	  * @author 侯斌飞
	  * @param condition
	  * @return
	  */
	 Map searchComplaints(ComplaintSearchCondition condition);
	 
	 /**
	  * Description:根据重复工单编码工单<br />
	  * @author 侯斌飞
	  * @param condition
	  * @return
	  */
	 Map searchRepeatComplaints(ComplaintSearchCondition condition);
	 
	 
	 
	/**
	 * 
	 * <p>
	 * Description:查询接入的工单<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-5-18
	 * @param condition
	 * @return
	 * Map
	 */
	 Map searchAccessComplaints(ComplaintSearchCondition condition,User user);
	/**
	 * 
	 * <p>
	 * Description:通过id查询工单<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-16
	 * @param id
	 * @return
	 * Complaint
	 */
	 Complaint getComplaintById(String id);
	
	/**	 * 
	 * <p>
	 * Description:查询当前用户待办工单<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-4-12
	 * @param currentUser
	 * @return
	 * List<Complaint>
	 */
	 Map<String,Object> searchWaitProccesComplaint(User currentUser,int start,int limit);
	/**
	 * 输入订单号或运单号带出客户信息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-11
	 * @param num
	 * @return
	 * Map<String,List>
	 */
	 Map<String,Object> searchMembersByOWNum(String num ,ComplaintSearchCondition condition);
	/**
	 * 
	 * <p>
	 * Description:通过电话得到联系人<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-16
	 * @param phone
	 * @param condition
	 * @return 
	 * Map<String,List>
	 */
	 Map<String,Object> getContractByPhone(String phone,ComplaintSearchCondition condition);
	/**
	 * 
	 * <p>
	 * Description:得到重复工单码<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-16
	 * @param owNum
	 * @param complaintId
	 * @param complaintType
	 * @return
	 * Map<String,String>
	 */
	 Map<String,String> getRepeatedCode(String owNum,String complaintId,String complaintType);

	
	/**
	* @Title: 		searchComplaintCustomersByDate
	* @Description: TODO(根据月份，查询当月投诉客户)
	* @param 		@param date "yyyy-MM"
	* @param 		@return    设定文件
	* @return 		List<String>    返回类型  customerCodes
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	 List<String> searchComplaintCustomersByDate(String date);
	

//	public Map<String,Object> getOtherCompInfo(Complaint complaint);

	/**
	 * @Title:      getRecently3ComplaintsByReportType
	 * Description: 根据上报类型查询最近优先顺序最高的三条工单
	 * @param       parameterMap={reportType:'上报类型',sessionUser:'当前session用户'}  
	 * @return      List<Complaint>
	 * @throws
	 * @see <a href="package.html#section">查看流程图</a>
	 */
	 List<Complaint> getRecently3ComplaintsByReportType(Map<String,Object> parameterMap);

	/**
	* @Title: 		searchTaskComplaints
	* @Description: TODO(根据条件查询任务部门的投诉)
	* @param 		@param condition
	* @param 		@return    设定文件
	* @return 		Map    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	 Map searchTaskComplaints(ComplaintSearchCondition condition);
	
	/**
	 * @Title:      returnReportor
	 * <p>
	 * Description: 退回工单上报人
	 * </p>
	 * void
	 */
	 void returnReportor(Map map);
	
	/**
	 * Description: 提交审批
	 * @version 0.1 2012-4-18
	 * @param map
	 * void
	 */
	 void submitForApproval(Map map);
	
	/**
	 * @Title:      upgradeComplaint
	 * <p>
	 * Description: 投诉升级<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-4-18
	 * @param map
	 * void
	 */
	 void upgradeComplaint(Map map);
	
	/**
	 * 
	 * <p>
	 * Description:工单处理完成处理信息保存<br />
	 * </p>
	 * @author 
	 * @version 0.1 2012-4-23
	 * @param complaint 工单信息
	 * @param result 处理结果
	 * @return
	 * boolean
	 */
	 boolean finishedComplaintProccess(Map map);

	/**
	 * 
	 * <p>
	 * Description:完成处理信息保存<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-4-18
	 * @param complaint 工单信息
	 * @param result 处理结果
	 * @return
	 * boolean
	 */
	 boolean finishedComplaintProccess(Complaint complaint,List<Result> addResult,List<Result> delResult, List<Bulletin> bulletinList, User user);
	
	/**
	* @Title: 		returnToProcess
	* @Description: TODO(任务部门退回处理人)
	* @param 		@param complaint    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	 void returnToProcess(String complaintId,String resultId,String feedbackContent,User user);
	
	/**
	* @Title: 		passFeedback
	* @Description: TODO(任务部门反馈登记)
	* @param 		@param complaint    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	 void passFeedback(String complaintId,String resultId,String feedbackContent,String resolveCode,User user);
	
	/**
	* @Title: 		delayApplication
	* @Description: TODO(任务部门延时申请)
	* @param 		@param complaintId
	* @param 		@param resultId
	* @param 		@param feedbackContent
	* @param 		@param applyCode    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	 void delayApplication(String complaintId,String resultId,String feedbackContent,String applyCode,User user);

	/**
	 * @Title:      getWorkOrderRecordByComplaintId
	 * <p>
	 * Description: 根据工单ID获取工单操作记录<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-4-18
	 * @param complaintId
	 * @return
	 * WorkOrder
	 */
	//public WorkOrder getWorkOrderRecordByComplaintId(String complaintId);
	/**
	 * 
	 * <p>
	 * Description:工单上报提交<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-6-15
	 * @param complaint 要上报的工单
	 * @param user 
	 * @return
	 * boolean
	 */
	 boolean submitComplaint(Complaint complaint,User user) ;
	/**
	 * <p>
	 * Description:查询上报退回工单页面<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-20
	 * @param condition
	 * @return
	 * List<Complaint>
	 */
	 Map<String,Object> searchReturnedComplaints(ComplaintSearchCondition condition,User user);
	/**
	 * <p>
	 * Description:工单上报退回<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-20
	 * @param complaint 退回的工单
	 * @param complaintId 被绑定工单的id
	 * @param repeatedCode 重复工单码
	 * @return
	 * boolean
	 */
	 boolean submitComplaint(Complaint complaint,String complaintId,String repeatedCode,User user);
	
	/**
	 * <p>
	 * Description:申请延时审批，同意或者不同意都调用此方法，在状态中设置对应状态<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-4-22
	 * @param complaint
	 * @return
	 * boolean
	 */
	 boolean applyMoreTimeApproval(Complaint complaint,List<Result> results, String agreeFlag, User user, String returnReason);
	
	
	/**
	 * <p>
	 * Description:工单处理-经理锁定工单<br />
	 * </p>
	 * @author hpf
	 * @version 0.1 2012-5-30
	 * @param complaint
	 * @param user 
	 * boolean
	 */
	 void managerLockComplaint(Complaint complaint,User user);
	/**
	 * <p>
	 * Description:待办工单界面退回提交人<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-4-22
	 * @param complaintId
	 * @param order
	 * @return
	 * boolean
	 */
	 boolean returnSubmitor(Complaint complaint, User user, String feedbackReason);
	/**
	 * 
	 * <p>
	 * Description:工单回访处理：点击回访登记<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-6-15
	 * @param complaint 回访的工单
	 * @param feedBackReasion 退回原因记录
	 * @param user 当前用户
	 * @return
	 * boolean
	 */
	 boolean submitVerfiyComplaint(Complaint complaint,FeedBackReasion feedBackReasion,User user);
	/**
	 * <p>
	 * Description:工单回访查询<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-23
	 * @return
	 * Complaint
	 */
	 Map<String,Object> searchVerfiyComplaint(ComplaintSearchCondition condition,User user,Boolean ifDeafult);
	/**
	 * 
	 * <p>
	 * Description:查询业务范围<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-23
	 * @return
	 * List<BasciLevel>
	 */
	 List<BasciLevel> getFBasciLevlel(BasciLevelSearchCondition condition);
	/**
	 * <p>
	 * Description:业务类型查询<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-23
	 * @return
	 * List<BasciLevel>
	 */
	 List<BasciLevel> getChildBasciLevel(BasciLevelSearchCondition condition);
	/**
	 * 
	 * <p>
	 * Description:查询投诉 业务范围
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2012-7-10
	 * @return
	 */
	 List<BasciLevelView> searchComplaintLevelDataList(BasciLevelSearchCondition condition);
	/**
	 * 
	 * <p>
	 * Description:查询异常 业务范围
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2012-7-10
	 * @return
	 */
	 List<BasciLevelView> searchExceptionLevelDataList(BasciLevelSearchCondition condition);	
	
	/**
	* @Title: 		getMostRecentlyComplaintByCustCode
	* @Description: TODO(根据客户编码获得客户最近一次投诉记录)
	* @param 		@param custCode
	* @param 		@return    设定文件
	* @return 		Complaint    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	 Complaint getMostRecentlyComplaintByCustCode(String custCode);
	
	/**
	* @Title: 		getAllComplaintByCustCode
	* @Description: TODO(根据客户编码获取该客户所有投诉信息列表--按投诉时间倒序排列)
	* @param 		@param custCode
	* @param 		@return    设定文件
	* @return 		List<Complaint>    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	 List<Complaint> getAllComplaintByCustCode(String custCode);
	/**
	 * 
	 * <p>
	 * Description: 通过compid查退回原因记录列表<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-4-24
	 * @param compId
	 * @return
	 * List<FeedBackReasion>
	 */
	 List<FeedBackReasion> searchFeedBackReasionByCompId(BigDecimal compId);
	/**
	 * 
	 * <p>
	 * Description:点击新增，打开页面，点击保存操作<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-24
	 * @param procResult 基础资料处理结果
	 * @param basciLevel 业务类型对象
	 * @param fBasciLevelId 业务范围id
	 * @return
	 * Integer
	 */
	//public Integer saveFoundationData(List<ProcResult> procResult,BasciLevel basciLevel,BigDecimal fBasciLevelId,User user);
	/**
	 * 
	 * <p>
	 * Description:点击业务范围设置保存<br />
	 * 传进来的时候要给basciLevel 设置上报类型 方便我后台保存
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-26
	 * @param reportbasciLevel
	 * @param exceptionBasciLevel
	 * @param user
	 * void
	 */
	 void setUpParentBasciLevel(List<BasciLevel> reportbasciLevel,List<BasciLevel> exceptionBasciLevel,User user);
	
	/**
	 * <p>
	 * Description: 删除业务范围数据<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-7-6
	 * @param basciLevelList
	 * void
	 */
	 void deleteBasciLevel(BasciLevel basciLevel);
	
	/**
	 * <p>
	 * Description: 删除业务类型数据<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2012-7-10
	 * @param basciLevelList
	 * void
	 */
	 void deleteBasciLevelType(List<BasciLevelView> basciLevelList);
	
	/**
	 * <p>
	 * Description:点击查询或者修改页面<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-25
	 * @param childId	业务类型id
	 * @param reportType 上报类型
	 * @return
	 * Map<String,Object>
	 */
	 Map<String,Object> searchFoundationData(BigDecimal childId,String reportType);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-25
	 * @param procResultList  基础资料处理结果
	 * @param childBasciLevel 业务类型
	 * @param reportType
	 * void
	 */
	//public void updateFoundationData(List<ProcResult> procResultList,BasciLevel childBasciLevel,String reportType,User user);
	/**
	 * <p>
	 * Description:保存修改<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-26
	 * @param procResultList
	 * @param childBasciLevel
	 * @param fBasciLevelId  业务范围id
	 * @param reportType
	 * @param user
	 * void
	 */
	 void saveOrUpdateFoundationData(List<ProcResult> procResultList,BasciLevel childBasciLevel,BigDecimal fBasciLevelId,String reportType,User user);
	/**
	 * <p>
	 * Description: 根据条件查询部门处理结果列表<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-4-24
	 * @param condition
	 * @return
	 * List<Result>
	 */
	 List<Result> getResultByCondition(ResultSearchCondition condition);
	
	/**
	 * <p>
	 * Description: 获取所有部门列表<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-4-25
	 * @return
	 * List<Department>
	 */
	 List<Department> getAllDepartment(String deptName);
	
	/**
	 * <p>
	 * Description: 根据部门ID获取部门下所有员工列表<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-4-25
	 * @param deptId
	 * @return
	 * List<Employee>
	 */
	 List<Employee> getAllEmployeesByDepartmentId(String deptId);
	
	/**
	 * <p>
	 * Description: 根据工单ID获取该工单相关的通知对象列表<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-4-25
	 * @param compId
	 * @return
	 * List<Bulletin>
	 */
	 List<Bulletin> searchBulletinByCompId(BigDecimal compId);

	/**
	 * <p>
	 * Description: 根据工单ID获取该工单相关的通知对象列表<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-4-25
	 * @param compId
	 * @return
	 * List<Bulletin>
	 */
	 List<Bulletin> searchBulletinToProcByCompId(BigDecimal compId);
	/**
	 * <p>
	 * Description: 获取工单处理列表总数<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-4-28
	 * @param condition
	 * @return
	 * int
	 */
	 int getPendingComplaintCount(ComplaintSearchCondition condition);
	
	/**
	 * <p>
	 * Description: 查询处理结果集合 通过工单编号<br />
	 * compId : 工单编号
	 * </p>
	 * @author hpf
	 * @version 0.1 2012-4-28
	 * @param condition
	 * @return List<Result>
	 */
	 List<Result> searchProcessResultListByCompId(BigDecimal compId);
	
	/**
	 * <p>
	 * Description: 查询退回记录集合 通过工单编号<br />
	 * compId : 工单编号
	 * </p>
	 * @author hpf
	 * @version 0.1 2012-4-28
	 * @param condition
	 * @return List<Result>
	 */
	 List<FeedBackReasion> searchReturnedRecordListByCompId(BigDecimal compId);
	
	/**
	* @Title: 		expireComplaint
	* @Description: TODO(扫描工单，将超过处理时限的工单设置为已到期)
	* @param 		    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	 void expireComplaint();
	
	/**
	 * <p>
	 * Description: 查询基础资料处理结果根据层级编号<br />
	 * basciLevelId : 层级编号
	 * </p>
	 * @author hpf
	 * @version 0.1 2012-5-8
	 * @param condition
	 * @return List<ProcResult>
	 */
	 List<ProcResult> searchProcresultsByLevelId(BigDecimal basciLevelId);
	
	/**
	* @Title: 		getResultById
	* @Description: TODO(根据Id查询任务部门处理结果)
	* @param 		@param resultId
	* @param 		@return    设定文件
	* @return 		Result    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	 Result getResultById(BigDecimal resultId);
	
	/**
	 * 
	 * <p>
	 * Description:待办工单 回访结果查询<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-5-24
	 * @param compId
	 * @return
	 * List<Result>
	 */
	 List<Result> searchReturnResultInfoByCompId(BigDecimal compId);
	/**
	 * <p>
	 * Description:处理工单模块的待办事项信息<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2012-6-6
	 */	
	 void processComplaintMessage();
	/**
	 * 
	 * <p>
	 * Description:提供给外部（360）的接口<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-6-12
	 * @param condition
	 * @return
	 * Map<String,Object>
	 */
	 Map<String,Object> searchComplaintIncludeSatisfy(ComplaintSearchCondition condition);
	/**
	 * 
	 * <p>
	 * Description:发送投诉通知信息<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2012-7-2
	 * @param condition
	 * @return
	 * Map<String,Object>
	 */
	 void processSendComplaintInfo();
	
	/**
	 * <p>
	 * Description:根据单据ID查询所有的调查建议<br />
	 * </p>
	 * @author 邢悦
	 * @version 0.1 2012-7-18
	 * @param condition
	 * @return
	 * List<WorkOrder>
	 */
	 List<WorkOrder> getInvestigateSuggestionByCompId(BigDecimal complaintId);
	
	/**
	 * <p>
	 * Description:校验工单是否超时（用于工单接入做处理的时候）<br/>
	 * </p>
	 * @author 邢悦
	 * @version 0.1 2012-8-1
	 * @param 
	 * List<WorkOrder>
	 */
	 void isComplaintAccessExpired(String complaintId,User user);
	
	/**
	 * <p>
	 * Description:校验反馈登记是否过期（true：未过期，false：过期）<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-10-12
	 * @param complaintId
	 * @param user  
	 * void
	 */
	 void isComplaintFeedbackOverTime(String resultId);
	
	
	/**
	 * 
	 * <p>
	 * Description:处理短信的方法（供定时器调用）<br/>
	 * </p>
	 * @author 邢悦
	 * @version 0.1 2012-8-3
	 * @param 
	 */	
	 void processcellphoneMsg();
	/**
	 * <p>
	 * Description: 新基础资料--查询基础资料数据<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-22
	 * @param bsc
	 * @param Map
	 */
	public Map<String,Object> searchBasicInfo(BasicSearchCondition bsc,int start,int limit);
	/**
	 * <p>
	 * Description: 新基础资料--查询出业务范围及对应的业务类型用于修改基础资料页面<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param bbs
	 */
	public List<BasicBusScopeVO> searchBasicBusScopeVO(BasicBusScopeVO bbs);
	/**
	 * <p>
	 * Description: 新基础资料--新增或更新业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicItems
	 * @param user
	 */
	public boolean operateBusItem(List<BasicInfo> basicItems,User user);
	/**
	 * <p>
	 * Description: 新基础资料--业务项修改,业务范围新增与修改,业务类型新增修改删除<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicBusScope
	 * @param user
	 */
	public boolean operateBasicBusInfo(BasicBusScopeVO basicBusScope,User user);
	/**
	 * <p>
	 * Description: 新基础资料--删除业务类型及业务范围<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param id
	 */
	public boolean deleteBusTypeByIds(List<BasicInfo> basicInfos);
	/**
	 * <p>
	 * Description: 新基础资料--删除业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param id
	 */
	public boolean deleteBusItemByIds(List<BasicInfo> basicInfos);
	/**
	 * <p>
	 * Description: 新基础资料--删除业务类型在操作基础资料页面中使用<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param id
	 */
	public boolean deleteBusTypeByIdsInBusScopeVO(List<BasicBusType> busTypes);
	/**
	 * <p>
	 * Description: 新基础资料--根据上报类型查询业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param BasicBusScopeVO
	 * @param BasicBusScopeVO
	 */
	public List<BasicInfo> searchBusItemByType(String reportType);
	/**
	 * <p>
	 * Description: 新基础资料--删除添加业务项页面用于显示已存在的业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param BasicBusScopeVO
	 * @param BasicBusScopeVO
	 */
	public Map<String,List<BasicInfo>> showBusItemByType();
	/**
	 * <p>
	 * Description: 新基础资料--根据业务项ID查询业务范围<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param busItemId
	 */
	public List<BasicInfo> searchBusScopeByBusItemId(String busItemId);
	/**
	 * <p>
	 * Description: 新基础资料根据业务范围ID查询业务类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-25
	 * @param busScopeId
	 * @return List<BasicBusType> 
	 */
	public List<BasicInfo> searchBusTypeByBusScope(String busScopeId);
	/**
	 * <p>
	 * Description: 新基础资料--根据ID修改业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-25
	 * @param basicInfo
	 * @return List<BasicBusType> 
	 */
	public boolean updateBusItemById(BasicInfo basicInfo,User user);
	/**
	 * 
	 * <p>
	 * Description:为官网提供投诉流入CRM---保存官网投诉工单br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-7-5
	 * @return
	 * boolean
	 */
	public  boolean  submitComplaintForOnline (Complaint complaint);
	
	/**
	 * 工单处理，当出发部门不为任务部门时，生成短信通知出发部门经理和副经理维护客户以及生成一条待办事宜到出发部门
	 * @param resultList 处理结果
	 * @param complaint 工单信息
	 */
	void dealComplaint(List<Result> resultList, Complaint complaint);
	/**
	 * 工单处理，修改来自官网的工单的业务类型
	 * @param complaint 工单信息
	 * @author 杨伟
	 * @version 0.1 2013-9-5
	 */
	public Boolean changeBusinessModel(Complaint complaint);
}
