package com.deppon.crm.module.complaint.server.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.order.IWaybillOperate;
import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.complaint.server.dao.IComplaintDao;
import com.deppon.crm.module.complaint.server.service.IComplaintService;
import com.deppon.crm.module.complaint.server.util.ComplaintConstants;
import com.deppon.crm.module.complaint.shared.domain.CellphoneMessageInfo;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.domain.ComplaintSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.Result;
import com.deppon.crm.module.complaint.shared.domain.TaskComplaintCount;
import com.deppon.crm.module.complaint.shared.domain.TaskResult;
import com.deppon.crm.module.complaint.shared.exception.ComplaintException;
import com.deppon.crm.module.complaint.shared.exception.ComplaintExceptionType;
import com.deppon.foss.framework.exception.BusinessException;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.shared.util.string.StringUtil;

/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ComplaintService.java
 * @package com.deppon.crm.module.complaint.server.service.impl 
 * @author ouy
 * @version 0.1 2012-4-11
 */
public class ComplaintServiceImpl implements IComplaintService{
	private IComplaintDao  complaintDao;
	// 运单相关接口
		private IWaybillOperate waybillOperate;
	/*@Override
	public Complaint getComplaintById(String compId) {
		return complaintDao.getComplaintById(compId);
	}*/

	@Override
	public List<Complaint> searchComplaints(ComplaintSearchCondition condition) {
		// TODO Auto-generated method stub
 		return complaintDao.searchComplaints(condition);
	}
	
	@Override
	public Integer searchComplaintsCount(ComplaintSearchCondition condition){
 		return complaintDao.searchComplaintsCount(condition);
	}
	public IComplaintDao getComplaintDao() {
		return complaintDao;
	}

	public void setComplaintDao(IComplaintDao complaintDao) {
		this.complaintDao = complaintDao;
	}
	/**
	 * @Title:      getFreeExpiredComplaintForAccess
	 * Description: 普通处理员接入工单
	 * @param       parameterMap={reportType:'上报类型',sessionUser:'当前session用户'}  
	 * @return      List<Complaint>
	 */	
	public List<Complaint> getAccessComplaints(Map<String,Object> parameterMap) {
		Integer totalCount=(Integer)parameterMap.get("limitCount");
		
		//优先接入已被自己锁定且未过期的单子
		List<Complaint> lockedComplaints=complaintDao.getUserLockedUnExpiredComplaints(parameterMap);
		Integer lockedCount=lockedComplaints.size();
		Integer freeCount=totalCount-lockedCount;
		//第一次加载，不执行接入操作
		if(parameterMap.get("firstLoad")!=null&&(Boolean)parameterMap.get("firstLoad")){
			return lockedComplaints;
		}
		String businessModel=(String)parameterMap.get("businessModel");
		if(lockedComplaints.size()>0){
			for(Complaint lockedComplaint:lockedComplaints){
				if(!StringUtil.isEmpty(lockedComplaint.getBusinessModel())){
					if(!businessModel.equals(lockedComplaint.getBusinessModel())){
						BusinessException error = new ComplaintException(
								ComplaintExceptionType.COMPLAINT_INSERT_EXPRESS_OR_NOEXPRESS);
						throw new GeneralException(error.getErrorCode(), error.getMessage(),
								error, new Object()) {
						};
					}
				}
			}
		}
		//若未超过10条，则加入已超时或者未被接入的单子
		if(freeCount>0){
			parameterMap.put("limitCount",freeCount);
			List<Complaint> freeComplaints=complaintDao.getFreeExpiredComplaintForAccess(parameterMap);
			lockedComplaints.addAll(freeComplaints);
		}
		return lockedComplaints;
	}

	/**
	 * @Title:      getRecentlyLoComplaintsByReportType
	 * Description: 根据上报类型查询自己锁定的待处理工单
	 * @param       parameterMap={reportType:'上报类型',sessionUser:'当前session用户',count:'显示的数据数'}  
	 * @return      List<Complaint>
	 * @throws
	 */
	public List<Complaint> getLockingComplaintsByReportType(Map<String,Object> parameterMap){
		return complaintDao.getLockingComplaintsByReportType(parameterMap);
	}


	@Override
	public List<Complaint> searchReportComplaints(ComplaintSearchCondition csc) {
		// TODO Auto-generated method stub
		return complaintDao.searchReportComplaints(csc);
	}

	@Override
	public Integer searchReportComplaintsCount(ComplaintSearchCondition csc) {
		// TODO Auto-generated method stub
		return complaintDao.searchReportComplaintsCount(csc);
	}
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
	@Override
	public List<Complaint> searchWaitProccesComplaint(
			List<String> commonStatus, List<String> specialStatus,List<String>feedbackStatus,
			String userCode, String userId,int start, int limit) {
		return this.complaintDao.searchWaitProccesComplaint(commonStatus, specialStatus,feedbackStatus,userCode, userId, start, limit);
	}
	/**
	 * 查询当前待办工单的总数
	 * @param commonStatus
	 * @param specialStatus
	 * @param userCode
	 * @param userId
	 * @return
	 */
	@Override
	public Integer searchWaitProccesComplaintCount(List<String> commonStatus, List<String> specialStatus,List<String>feedbackStatus,String userCode,String userId){
		return this.complaintDao.searchWaitProccesComplaintCount(commonStatus, specialStatus,feedbackStatus, userCode, userId);
	}
	
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
	@Override
	public int finishedComplaintProccess(Complaint complaint,
			List<Result> result) {
		//先更新工单信息
		
		//插入工单处理结果，可新增，修改，删除
		return 0;
	}
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
	@Override
	public Integer saveComplaint(Complaint complaint) {
		return complaintDao.saveComplaint(complaint);
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
	 * int  影响记录行数
	 */
	@Override
	public int updateComplaint(Complaint complaint) {
		
		return this.complaintDao.updateComplaint(complaint);
	}
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
	@Override
	public int deleteComplaint(BigDecimal id) {
		return this.complaintDao.deleteComplaint(id);
	}

	@Override
	public Complaint getComplaintById(String id) {
		return this.complaintDao.getComplaintById(new BigDecimal(id));
	}

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
	@Override
	public List<Complaint> getComplaintResultList(
			ComplaintSearchCondition condition) {
		return this.getComplaintDao().getComplaintResult(condition);
	}

	public Integer getComplaintResultCount(ComplaintSearchCondition condition){
		return complaintDao.getComplaintResultCount(condition);
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
		// TODO Auto-generated method stub
		return complaintDao.searchTaskComplaints(condition);
	}
	/**
	* @Title: 		searchTaskComplaintsCount
	* @Description: TODO(根据条件查询工单回访数据总数)
	* @param 		@param condition
	* @param 		@return    设定文件
	* @return 		Integer    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	@Override
	public Integer searchTaskComplaintsCount(ComplaintSearchCondition condition) {
		// TODO Auto-generated method stub
		return complaintDao.searchTaskComplaintsCount(condition);
	}

	@Override
	public List<String> searchCustComplaints(ComplaintSearchCondition condition) {
		// TODO Auto-generated method stub
 		return complaintDao.searchCustComplaints(condition);
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
	public List<Complaint> getAllComplaintByCustCode(
			ComplaintSearchCondition condition) {
		// TODO Auto-generated method stub
 		return complaintDao.getAllComplaintByCustCode(condition);
	}
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
	@Override
	public String getDealbill() {
		return complaintDao.getDealBill();
	}
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
	@Override
	public List<Complaint> getVeryfiyComplaints(
			ComplaintSearchCondition condition) {
		// TODO Auto-generated method stub
		return complaintDao.getVeryfiyComplaints(condition);
	}
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
	@Override
	public Integer getVeryfiyComplaintsCount(
			ComplaintSearchCondition condition) {
		// TODO Auto-generated method stub
		return complaintDao.getVeryfiyComplaintsCount(condition);
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
	public Integer getAccessComplaintsCount(
			ComplaintSearchCondition condition) {
		// TODO Auto-generated method stub
		return complaintDao.getAccessComplaintsCount(condition);
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
		return complaintDao.getAccessComplaints(condition);
	}

	@Override
	public Integer getPendingComplaintsCount(ComplaintSearchCondition condition) {
		// TODO Auto-generated method stub
		return complaintDao.getPendingComplaintsCount(condition);
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
		return complaintDao.inquireAllUserComplaintResultCount(condition);
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
		return complaintDao.inquireAllUserVeryfiyComplaintsCount(condition);
	}
	/**
	 * 部门工单待办信息
	 * @author justin.xu
	 * @version 0.1 2012-6-6 
	 * @param condition
	 * @return
	 * List<Message>
	 */
	@Override
	public List<TaskComplaintCount> inquireAllTaskComplaintsCount(
			ComplaintSearchCondition condition) {
		return complaintDao.inquireAllTaskComplaintsCount(condition);
	}
	/**
	 * 部门工单个人待办信息
	 * @author justin.xu
	 * @version 0.1 2012-6-6 
	 * @param condition
	 * @return
	 * List<Message>
	 */
	@Override
	public List<TaskComplaintCount> inquireAllUserTaskComplaintsCount(
			ComplaintSearchCondition condition) {
		return complaintDao.inquireAllUserTaskComplaintsCount(condition);
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
		return complaintDao.inquireAllUserWaitProcessCount(commonStatus,specialStatus,feedbackStatus);
	}
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
	@Override
	public List<Complaint> getComplaintIncludeSatisfy(
			ComplaintSearchCondition condition) {
		// TODO Auto-generated method stub
		return complaintDao.getComplaintIncludeSatisfy(condition);
	}
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
	@Override
	public Integer getComplaintIncludeSatisfyCount(
			ComplaintSearchCondition condition) {
		// TODO Auto-generated method stub
		return complaintDao.getComplaintIncludeSatisfyCount(condition);
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
		return complaintDao.inquireUserIdByEmployeeId(employeeId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.complaint.server.service.IComplaintService#addNewRelation(java.lang.Integer, java.lang.String)
	 */
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
	@Override
	public void addNewRelation(String compId, String serviceId,String loginName) {
		complaintDao.addNewRelation(compId,serviceId,loginName);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.complaint.server.service.IComplaintService#insertCompAccessLog(java.util.Map)
	 */

	/**
	 * <p>
	 * 工单接入日志<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-8-22
	 * @param map
	 * void
	 */
	@Override
	public boolean insertCompAccessLog(Map<String, Object> map) {
		return complaintDao.insertCompAccessLog(map);
	}
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
	 public FossWaybillInfo findWaybillByNO(String waybillNumber){
		 
		if(!StringUtil.isEmpty(waybillNumber)){
			
			try {
				return waybillOperate.queryWaybillInfo(waybillNumber);
			} catch (CrmBusinessException e) {
				e.printStackTrace();
			}
			
		}else {
			
			return null;
			
		}
		return null;
		  
	 }

	/**
	 * <p>
	 * Description:waybillOperate<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-7-10
	 */
	public IWaybillOperate getWaybillOperate() {
		return waybillOperate;
	}

	/**
	 * <p>
	 * Description:waybillOperate<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-7-10
	 */
	public void setWaybillOperate(IWaybillOperate waybillOperate) {
		this.waybillOperate = waybillOperate;
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
		return complaintDao.findManagerByDeptId(leaveDeptId);
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
		return complaintDao.findAssistantManagerByDeptId(leaveDeptId);
	}
	
	/**
	 * 根据出发部门ID查询userID
	 * @param leaveDeptId 出发部门ID
	 * @return
	 */
	@Override
	public List<String> findUserIdByDeptId(String leaveDeptId) {
		return complaintDao.findUserIdByDeptId(leaveDeptId);
	}
	
	/**
	 * 生成待办事宜
	 * @param message
	 */
	@Override
	public void addTodoMessage(Message message) {
		complaintDao.addTodoMessage(message);
	}

	/**
	 * 修改官网的工单业务类型
	 * @param message
	 */
	public Boolean changeBusinessModel(Complaint complaint) {
		Complaint changeComplaint =new Complaint();
		changeComplaint.setFid(complaint.getFid());
		changeComplaint.setBusinessModel(complaint.getBusinessModel());
		if(complaintDao.updateComplaint(changeComplaint)==1){
			return true;
		}else{
			return false;
		}
		
	}
	
	public void complaintTimeoutScheduler() {
		complaintDao.complaintTimeoutScheduler();
	}
}
