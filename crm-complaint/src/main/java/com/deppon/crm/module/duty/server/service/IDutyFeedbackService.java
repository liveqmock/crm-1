package com.deppon.crm.module.duty.server.service;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.duty.shared.domain.DutyFeedback;

/**
 * <p>
 * Description:<br />
 * </p>
 * @title IDutyFeedbackService.java
 * @package com.deppon.crm.module.duty.server.dao 
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-3-13
 */
public interface IDutyFeedbackService {
	/**
	 * <p>
	 * Description:根据责任ID查询责任反馈记录列表<br />
	 * @author xiaohongye
	 * @param searchParam
	 * @return
	 * @version V0.1 
	 * @Date 2013-3-13
	 */
	public List<DutyFeedback>  searchDutyFeedbackByDutyId(Map<String,Object> searchParam);
	
	/**
	 * <p>
	 * Description:根据工单责任反馈ID查询查询工单责任状态<br />
	 * @author xiaohongye
	 * @param feedbackId
	 * @return
	 * @version V0.1 
	 * @Date 2013-3-20
	 */
	public DutyFeedback  searchDutyFeedbackByFeedbackId(String feedbackId);
	
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
	 * 更新反馈结果<br />
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
