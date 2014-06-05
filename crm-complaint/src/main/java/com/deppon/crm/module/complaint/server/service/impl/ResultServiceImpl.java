package com.deppon.crm.module.complaint.server.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.complaint.server.dao.IResultDao;
import com.deppon.crm.module.complaint.server.service.IResultService;
import com.deppon.crm.module.complaint.shared.domain.Result;
import com.deppon.crm.module.complaint.shared.domain.ResultSearchCondition;
/**
 * 
 * <p>
 * Description:通报对象<br />
 * </p>
 * @title IComplaintService.java
 * @package com.deppon.crm.module.complaint.server.service 
 * @author ouy
 * @version 0.1 2012-4-12
 */
public class ResultServiceImpl implements IResultService{
	private IResultDao resultDao;
	/**
	 * 
	 * <p>
	 * Description:通过工单id查处理结果<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-17
	 * @return
	 * List<Complaint>
	 */
	@Override
	public List<Result> searchResultById(BigDecimal compId) {
		// TODO Auto-generated method stub
		return resultDao.searchResultById(compId);
	}
	public IResultDao getResultDao() {
		return resultDao;
	}
	public void setResultDao(IResultDao resultDao) {
		this.resultDao = resultDao;
	}
	/**
	 * 
	 * <p>
	 * Description:通过工单id查处理结果<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-17
	 * @return
	 * List<Complaint>
	 */
	@Override
	public Result getResultById(BigDecimal resultId) {
		// TODO Auto-generated method stub
		return resultDao.getResultById(resultId);
	}
	
	public void saveTaskDepartmentResult(List<Result> results) {
		resultDao.saveTaskDepartmentResult(results);
	}
	
	public void saveLeaveDepartmentResult(Result result) {
		resultDao.saveLeaveDepartmentResult(result);
	}
	
	/**
	* @Title: 		updateResult
	* @Description: TODO(根据主键ID更新处理结果)
	* @param 		@param result
	* @param 		@return    设定文件
	* @return 		int    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public int updateResult(Result result){
		return resultDao.updateResult(result);
	}
	@Override
	public List<Result> getResultByCondition(ResultSearchCondition condition) {
		// TODO Auto-generated method stub
		return resultDao.getResultByCondition(condition);
	}
	
	public int deleteResult(Result result) {
		return resultDao.deleteResult(result);
	}

	/**
	* @Title: 		getExpiredComplaint
	* @Description: TODO(获取所有已到期工单的任务结果记录)
	* @param 		@return    设定文件
	* @return 		List<Complaint>    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	@Override
	public List<Result> getExpiredResultById(BigDecimal complaintId) {
		// TODO Auto-generated method stub
		return resultDao.getExpiredResultById(complaintId);
	}
	/**
	* @Title: 		getExpiredFeedbackResultById
	* @Description: TODO(获取所有反馈超时的工单任务结果记录)
	* @param 		@param complaintId
	* @param 		@return    设定文件
	* @return 		List<Result>    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	@Override
	public List<Result> getExpiredFeedbackResultById(BigDecimal complaintId) {
		// TODO Auto-generated method stub
		return resultDao.getExpiredFeedbackResultById(complaintId);
	}
	/**
	* @Title: 		getExpiredFeedbackResultById
	* @Description: TODO(获取所有反馈超时的工单任务结果记录)
	* @param 		@param complaintId
	* @param 		@return    设定文件
	* @return 		List<Result>    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	@Override
	public List<Result> searchResultInfoByCompId(BigDecimal compId) {
		// TODO Auto-generated method stub
		return resultDao.getResultInfoByCompId(compId);
	}
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
	@Override
	public List<Result> searchReturnResultInfoByCompId(BigDecimal compId){
		return resultDao.getReturnResultInfoByCompId(compId);
	}
}
