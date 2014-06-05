package com.deppon.crm.module.duty.server.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.duty.server.dao.IDutyFeedbackDao;
import com.deppon.crm.module.duty.server.service.IDutyFeedbackService;
import com.deppon.crm.module.duty.shared.domain.DutyFeedback;
/**
 * <p>
 * Description:工单责任反馈记录<br />
 * </p>
 * @title DutyFeedbackServiceImpl.java
 * @package com.deppon.crm.module.duty.server.service.impl
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-3-13
 */
@Transactional
public class DutyFeedbackServiceImpl implements IDutyFeedbackService{
	private IDutyFeedbackDao dutyFeedbackDao;	
	/**
	 * @param dutyFeedbackDao the dutyFeedbackDao to set
	 */
	public void setDutyFeedbackDao(IDutyFeedbackDao dutyFeedbackDao) {
		this.dutyFeedbackDao = dutyFeedbackDao;
	}


	/**
	 * <p>
	 * Description:根据责任id查询反馈记录列表<br />
	 * @author xiaohongye
	 * @param searchParam
	 * @return
	 * @version V0.1 
	 * @Date 2013-3-13
	 */
	@Override
	public List<DutyFeedback> searchDutyFeedbackByDutyId(Map<String,Object> searchParam) {
		List<DutyFeedback> dutyFeedbackList = dutyFeedbackDao.searchDutyFeedbackByDutyId(searchParam);
		for(DutyFeedback feedBack : dutyFeedbackList){
			feedBack.setDutyId((String)searchParam.get("dutyId"));
		}
		return dutyFeedbackList;
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
		dutyFeedbackDao.dutyApproval(dutyFeedback);
		
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
		return dutyFeedbackDao.searchDutyFeedbackByFeedbackId(feedbackId);
	}


	/**
	 * <p>
	 * 更新反馈结果<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-20
	 * @param feedback
	 * void
	 * @see com.deppon.crm.module.duty.server.service.IDutyFeedbackService#updateDutyFeedback(com.deppon.crm.module.duty.shared.domain.DutyFeedback)
	 */
	@Override
	public void updateDutyFeedback(DutyFeedback feedback) {
		dutyFeedbackDao.updateDutyFeedback(feedback);
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
		dutyFeedbackDao.updateDutyFeedbackAll(feedback);
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
		dutyFeedbackDao.insertDutyFeedback(feedback);
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
		return dutyFeedbackDao.searchDutyFeedbackByDutyResultId(detailId);
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
		dutyFeedbackDao.deleteFeedbackByDetailId(detailId);
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
	@Override
	public String searchStatDeptName(String feedbackId) {
		return dutyFeedbackDao.searchStatDeptName(feedbackId);
	}

}
