package com.deppon.crm.module.complaint.server.dao;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.domain.Result;
import com.deppon.crm.module.complaint.shared.domain.ResultSearchCondition;
/**
 * 
 * <p>
 * Description:通报对象<br />
 * </p>
 * @title IBulletinDao.java
 * @package com.deppon.crm.module.complaint.server.dao 
 * @author ouy
 * @version 0.1 2012-4-17
 */
public interface IResultDao  {
	/**
	 * <p>
	 * Description:通过工单id查处理结果<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-17
	 * @return
	 * List<Complaint>
	 */
	 List<Result> searchResultById(BigDecimal compId);
	/**
	* @Title: 		getResultById
	* @Description: TODO(根据主键Id查询处理结果)
	* @param 		@param resultId
	* @param 		@return    设定文件
	* @return 		Result    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	 Result getResultById(BigDecimal resultId);
	/**
	 * <p>
	 * Description: 保存任务部门处理数据<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-4-19
	 * @param results
	 * void
	 */
	 int saveTaskDepartmentResult(List<Result> results);
	 
	 /**
	 * <p>
	 * Description: 保存出发部门处理数据<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2013-7-22
	 * @param result
	 * void
	 */
	 void saveLeaveDepartmentResult(Result result);
	 
	/**
	* @Title: 		updateResult
	* @Description: TODO(根据主键ID更新处理结果)
	* @param 		@param result
	* @param 		@return    设定文件
	* @return 		int    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	 int updateResult(Result result);
	/**
	* @Title: 		deleteResult
	* @Description: TODO(根据主键ID删除处理结果)
	* @param 		@param result
	* @param 		@return    设定文件
	* @return 		int    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	 int deleteResult(Result result);
	/**
	 * <p>
	 * Description:查询部门处理记录<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-21
	 * @param condition
	 * @return
	 * List<Result>
	 */
	 List<Result> getResultByCondition(ResultSearchCondition condition);
	/**
	* @Title: 		getExpiredComplaint
	* @Description: TODO(获取所有已到期工单的任务结果记录)
	* @param 		@return    设定文件
	* @return 		List<Complaint>    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	 List<Result> getExpiredResultById(BigDecimal complaintId);
	/**
	* @Title: 		getExpiredFeedbackResultById
	* @Description: TODO(获取所有反馈超时的工单任务结果记录)
	* @param 		@param complaintId
	* @param 		@return    设定文件
	* @return 		List<Result>    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	 List<Result> getExpiredFeedbackResultById(BigDecimal complaintId);
	/**
	 * <p>
	 * Description:查询反馈<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-5-24
	 * @param complaintId
	 * @return
	 * List<Result>
	 */
	 List<Result> getResultInfoByCompId(BigDecimal complaintId);
	/**
	 * <p>
	 * Description:查询已退回result<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-5-24
	 * @param complaintId
	 * @return
	 * List<Result>
	 */
	 List<Result> getReturnResultInfoByCompId(BigDecimal complaintId);
}
