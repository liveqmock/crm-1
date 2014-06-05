package com.deppon.crm.module.duty.server.dao.impl;

import java.util.List;
import java.util.Map;


import com.deppon.crm.module.duty.server.dao.IDutyFeedbackDao;
import com.deppon.crm.module.duty.shared.domain.DutyFeedback;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class DutyFeedbackDaoImpl  extends iBatis3DaoImpl implements IDutyFeedbackDao {
	private static final String NAMESPACE_DUTYFEEDBACK = "com.deppon.crm.module.duty.shared.domain.DutyFeedback.";
	//根据条件查询工单
	private static final String SEARCHDUTYFEEDBACKBYDUTYID = "searchDutyFeedbackByDutyId";
	//根据工单责任反馈ID查询查询工单责任状态
	private static final String SEARCHDUTYFEEDBACKBYFEEDBACKID = "searchDutyFeedbackByFeedbackId";
	//工单责任审批：根据工单责任反馈ID更新工单责任反馈记录
	private static final String UPDATEDUTYFEEDBACK = "updateDutyFeedback";
	//工单责任审批：根据工单划分ID更新工单责任划分结果
	private static final String UPDATEDUTYRESULT = "updateDutyResult";
	//更新反馈信息全部字段
	private static final String UPDATEDUTYFEEDBACKALL = "updateDutyFeedbackAll";
	//更新反馈信息全部字段
	private static final String INSERTDUTYFEEDBACKALL = "insertDutyFeedback";
	//根据工单责任划分ID查询工单责任反馈信息
	private static final String SEARCHDUTYFEEDBACKBYDUTYRESULTID = "searchDutyFeedbackByDutyResultId";
	
	//根据划分结果ID删除反馈信息
	private static final String DELETE_FEEDBACK_DETAILID = "deleteFeedbackByDetailId";
	//查询统计员所在部门
	private static final String SEARCH_STATDEPTNAME = "searchStatDeptName";
	
	/**
	 * <p>
	 * Description:根据责任划分ID查询责任反馈记录<br />
	 * @author xiaohongye
	 * @param searchParam
	 * @return
	 * @version V0.1 
	 * @Date 2013-3-18
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DutyFeedback> searchDutyFeedbackByDutyId(Map<String,Object> searchParam) {
		return this.getSqlSession().selectList(NAMESPACE_DUTYFEEDBACK+SEARCHDUTYFEEDBACKBYDUTYID,searchParam);
	}
	
	/**
	 * <p>
	 * Description:查询统计员所在部门<br />
	 * @author 张斌
	 * @param feedbackId
	 * @return
	 * @version V0.1 
	 * @Date 2013-6-8
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String searchStatDeptName(String feedbackId) {
		return (String) this.getSqlSession().selectOne(NAMESPACE_DUTYFEEDBACK+SEARCH_STATDEPTNAME,feedbackId);
	}
	/**
	 * <p>
	 * Description:工单责任审批<br />
	 * @author xiaohongye
	 * @param dutyFeedback
	 * @version V0.1 
	 * @Date 2013-3-18
	 */
	@Override
	public void dutyApproval(DutyFeedback dutyFeedback) {
		//工单责任审批：根据工单责任反馈ID更新工单责任反馈记录
		this.getSqlSession().update(NAMESPACE_DUTYFEEDBACK+UPDATEDUTYFEEDBACK, dutyFeedback);
		//工单责任审批：根据工单划分ID更新工单责任划分结果
		this.getSqlSession().update(NAMESPACE_DUTYFEEDBACK+UPDATEDUTYRESULT, dutyFeedback);
	}
	/**
	 * <p>
	 * Description:根据工单责任反馈ID查询查询工单责任状态<br />
	 * @author xiaohongye
	 * @param feedbackId
	 * @return
	 * @version V0.1 
	 * @Date 2013-3-20
	 */
	@Override
	public DutyFeedback searchDutyFeedbackByFeedbackId(String feedbackId) {
		return (DutyFeedback) this.getSqlSession().selectOne(SEARCHDUTYFEEDBACKBYFEEDBACKID, feedbackId);
	}
	
	/**
	 * <p>
	 * Description:根据工单责任反馈ID查询查询工单责任反馈信息<br />
	 * @author zhangbin
	 * @param dutyResultId
	 * @return
	 * @version V0.1 
	 * @Date 2013-3-20
	 */
	@Override
	public DutyFeedback searchDutyFeedbackByDutyResultId(String detailId) {
		return (DutyFeedback) this.getSqlSession().selectOne(SEARCHDUTYFEEDBACKBYDUTYRESULTID, detailId);
	}
	/**
	 * <p>
	 * 工单反馈信息<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-20
	 * @param feedback
	 * void
	 * @see com.deppon.crm.module.duty.server.dao.IDutyFeedbackDao#updateDutyFeedback(com.deppon.crm.module.duty.shared.domain.DutyFeedback)
	 */
	@Override
	public void updateDutyFeedback(DutyFeedback feedback) {
		//更新反馈信息
		this.getSqlSession().update(NAMESPACE_DUTYFEEDBACK+UPDATEDUTYFEEDBACK, feedback);
	}
	/**
	 * <p>
	 * 修改工单反馈信息，全部字段<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-22
	 * @param feedback
	 * void
	 */
	@Override
	public void updateDutyFeedbackAll(DutyFeedback feedback) {
		//更新反馈信息
		this.getSqlSession().update(NAMESPACE_DUTYFEEDBACK+UPDATEDUTYFEEDBACKALL, feedback);
	}
	/**
	 * <p>
	 * 新增工单反馈信息<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-22
	 * @param feedback
	 * void
	 */
	@Override
	public void insertDutyFeedback(DutyFeedback feedback) {
		//更新反馈信息
		this.getSqlSession().insert(NAMESPACE_DUTYFEEDBACK+INSERTDUTYFEEDBACKALL, feedback);
	}
	/**
	 * <p>
	 * Description:根据责任划分结果删除反馈信息<br />
	 * @author zhangbin
	 * @param detailId
	 * @return
	 * @version V0.1 
	 * @Date 2013-4-15
	 */
	@Override
	public void deleteFeedbackByDetailId(String detailId) {
		//删除反馈信息
		this.getSqlSession().delete(NAMESPACE_DUTYFEEDBACK+DELETE_FEEDBACK_DETAILID, detailId);
	}
	
}
