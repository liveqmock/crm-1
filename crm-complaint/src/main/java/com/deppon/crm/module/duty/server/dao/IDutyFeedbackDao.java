package com.deppon.crm.module.duty.server.dao;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.duty.shared.domain.DutyFeedback;


/**
 * 工单责任反馈记录操作
 * @author xiaohongye
 *
 */
public interface IDutyFeedbackDao {
	/**
	 * Description:根据责任划分ID查询工单责任反馈记录
	 * @author xiaohongye
	 * @version 0.1 2013-3-12
	 */
	public List<DutyFeedback> searchDutyFeedbackByDutyId(Map<String,Object> searchParam);
	
	/**
	 * <p>
	 * Description:根据工单责任反馈ID查询查询工单责任状态<br />
	 * @author xiaohongye
	 * @param feedbackId
	 * @return
	 * @version V0.1 
	 * @Date 2013-3-20
	 */
	public DutyFeedback searchDutyFeedbackByFeedbackId(String feedbackId);
	
	/**
	 * <p>
	 * Description:工单责任审批<br />
	 * @author xiaohongye
	 * @param dutyFeedback
	 * @version V0.1 
	 * @Date 2013-3-18
	 */
	public void dutyApproval(DutyFeedback dutyFeedback);

	/**
	 * <p>
	 * 工单反馈信息<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-20
	 * @param feedback
	 * void
	 */
	public void updateDutyFeedback(DutyFeedback feedback);
	
	/**
	 * <p>
	 * 修改工单反馈信息，全部字段<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-22
	 * @param feedback
	 * void
	 */
	public void updateDutyFeedbackAll(DutyFeedback feedback);
	
	/**
	 * <p>
	 * 新增工单反馈信息<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-22
	 * @param feedback
	 * void
	 */
	public void insertDutyFeedback(DutyFeedback feedback);
	/**
	 * <p>
	 * Description:根据工单责任反馈ID查询查询工单责任反馈信息<br />
	 * @author zhangbin
	 * @param dutyResultId
	 * @return
	 * @version V0.1 
	 * @Date 2013-3-20
	 */
	DutyFeedback searchDutyFeedbackByDutyResultId(String detailId);

	/**
	 * <p>
	 * Description:根据责任划分结果删除反馈信息<br />
	 * @author zhangbin
	 * @param detailId
	 * @return
	 * @version V0.1 
	 * @Date 2013-4-15
	 */
	void deleteFeedbackByDetailId(String detailId);
	/**
	 * <p>
	 * Description:查询统计员所在部门<br />
	 * @author 张斌
	 * @param feedbackId
	 * @return
	 * @version V0.1 
	 * @Date 2013-6-8
	 */
	String searchStatDeptName(String feedbackId);
	
	
	
}
