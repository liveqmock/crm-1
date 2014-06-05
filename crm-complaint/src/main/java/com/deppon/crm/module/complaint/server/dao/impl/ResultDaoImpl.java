package com.deppon.crm.module.complaint.server.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.complaint.server.dao.IResultDao;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.domain.Result;
import com.deppon.crm.module.complaint.shared.domain.ResultSearchCondition;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class ResultDaoImpl extends iBatis3DaoImpl implements IResultDao{
	
	private static final String NAMESPACE_RESULT = "com.deppon.crm.module.complaint.shared.domain.Result.";
	private static final String SEARCH_RESULT_BY_COMPID  =  "selectResultByCompId";
	private static final String QUERY_RESULTBYID = "queryResultByID";
	private static final String SAVE_RESULT = "insertResult";
	private static final String SAVE_LEAVERESULT = "insertLEAVEResult";
	private static final String UPDATE_RESULT="updateResult";
	private static final String DELETE_RESULT = "deleteResult";
	private static final String SEARCH_RESULT_BY_CONDITION="searchResultByCondition";
	private static final String GET_EXPIRE_RESULT="getExpireResultByComplaintId";
	private static final String GET_EXPIRE_FEEDBACK_Result="getExpireFeedBackResultByComplaintId";
	private static final String GET__RESULT_INFO_BY_COMPID="getResultInfoByCompId";
	private static final String GET_RETURN_RESULT_INFO_BY_COMPID="getReturnResultInfoByCompId";
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
		Map<String, BigDecimal> map=new HashMap<String, BigDecimal>();
		map.put("compId", compId);
		List<Result> resultList=this.getSqlSession()
				.selectList(NAMESPACE_RESULT+SEARCH_RESULT_BY_COMPID, map);
		return resultList;
	}
	/**
	* @Title: 		getResultById
	* @Description: TODO(根据主键Id查询处理结果)
	* @param 		@param resultId
	* @param 		@return    设定文件
	* @return 		Result    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	@Override
	public Result getResultById(BigDecimal resultId) {
		// TODO Auto-generated method stub
		return (Result) this.getSqlSession().selectOne(
				NAMESPACE_RESULT + QUERY_RESULTBYID, resultId);
	}
	
	// 获取seqId
//	private String getSequence() {
//		SqlSession session = this.getSqlSession(ExecutorType.REUSE);
//		return session.selectOne(NAMESPACE_RESULT + "getSeqId").toString();
//	}
	
//	private SqlSession getSqlSession(ExecutorType executorType) {
//		SqlSessionFactory factory = ((SqlSessionTemplate) getSqlSession()).getSqlSessionFactory();
//		SqlSession template = new SqlSessionTemplate(factory, executorType);
//		return template;
//	}
	
	/**
	 * 
	 * <p>
	 * Description: 保存任务部门处理数据<br />
	 * </p>
	 * @author admin
	 * @version 0.1 2012-4-19
	 * @param results
	 * void
	 */
	public int saveTaskDepartmentResult(List<Result> results) {
//		SqlSession session = this.getSqlSession(ExecutorType.BATCH);
		int successInt = 0;
		for (Result result: results) {
//			result.setFid(BigDecimal.valueOf(Long.parseLong(getSequence())));
			this.getSqlSession().insert(NAMESPACE_RESULT + SAVE_RESULT, result);
			successInt++;
		}
		return successInt;
		
	}
	
	/**
	 * <p>
	 * Description: 保存出发部门处理数据<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2013-7-22
	 * @param result
	 * void
	 */
	 public void saveLeaveDepartmentResult(Result result) {
		 this.getSqlSession().insert(NAMESPACE_RESULT + SAVE_LEAVERESULT, result);
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
	@Override
	public int updateResult(Result result) {		
		return this.getSqlSession().update(NAMESPACE_RESULT+UPDATE_RESULT, result);
	}

	/**
	* @Title: 		deleteResult
	* @Description: TODO(根据主键ID删除处理结果)
	* @param 		@param result
	* @param 		@return    设定文件
	* @return 		int    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	@Override
	public int deleteResult(Result result) {
		return this.getSqlSession().delete(NAMESPACE_RESULT+DELETE_RESULT, result);
	}

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
	@Override
	public List<Result> getResultByCondition(ResultSearchCondition condition) {
		return this.getSqlSession().selectList(NAMESPACE_RESULT+SEARCH_RESULT_BY_CONDITION, condition);
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
		return this.getSqlSession().selectList(NAMESPACE_RESULT+GET_EXPIRE_RESULT,complaintId);
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
		return this.getSqlSession().selectList(NAMESPACE_RESULT+GET_EXPIRE_FEEDBACK_Result,complaintId);
	}
	/**
	 * 
	 * <p>
	 * Description:查询反馈<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-5-24
	 * @param complaintId
	 * @return
	 * List<Result>
	 */
	@Override
	public List<Result> getResultInfoByCompId(BigDecimal complaintId) {
		// TODO Auto-generated method stub
		Map<String, BigDecimal> map=new HashMap<String, BigDecimal>();
		map.put("compId", complaintId);
		return this.getSqlSession().selectList(NAMESPACE_RESULT+GET__RESULT_INFO_BY_COMPID,map);
	}
	/**
	 * 
	 * <p>
	 * Description:查询已退回result<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-5-24
	 * @param complaintId
	 * @return
	 * List<Result>
	 */
	@Override
	public List<Result> getReturnResultInfoByCompId(BigDecimal complaintId) {
		// TODO Auto-generated method stub
		Map<String, BigDecimal> map=new HashMap<String, BigDecimal>();
		map.put("compId", complaintId);
		return this.getSqlSession().selectList(NAMESPACE_RESULT+GET_RETURN_RESULT_INFO_BY_COMPID,map);
	}
	
}
