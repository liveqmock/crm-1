package com.deppon.crm.module.complaint.server.manager;

import com.deppon.crm.module.complaint.server.util.Constants;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.exception.ComplaintException;
import com.deppon.crm.module.complaint.shared.exception.ComplaintExceptionType;

/**
 * <p>
 * Description:工单业务规则<br />
 * </p>
 * @title ComplaintRule.java
 * @package com.deppon.crm.module.complaint.server.manager.impl 
 * @author Weill
 * @version 0.1 2012-4-20
 */
public class ComplaintRule {
	
	/**
	 * <p>
	 * Description:获取完成处理后工单的下一个状态<br />
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-4-20
	 * @param complaint 从数据库中查询最新工单信息。
	 * @return
	 * String
	 */
	public static String getFinishProccessComplaintNextStatus(Complaint complaint){
		//不是最终反馈
		if(Constants.IF_FEED_BACK_NO.equals(complaint.getFeedback())){
			return Constants.COMPLAINT_STATUS_WAITE_RESPONSE;
			//是最终反馈
		}else if(Constants.IF_FEED_BACK_YES.equals(complaint.getFeedback())){
			return Constants.COMPLAINT_STATUS_BACKGROUND;
		}else{
			//反馈状态为空或值不正确
			throw new ComplaintException(ComplaintExceptionType.feedBackIsNull);
		}
	}
	
	/**
	 * <p>
	 * Description:获取退回提交人操作之后，工单状态，有两种：<br />
	 * 1)审批退回;2)升级退回
	 * </p>
	 * @author Weill
	 * @version 0.1 2012-4-22
	 * @param complaint
	 * @return
	 * String
	 */
	public static String getReturnSubmitorStatus(Complaint complaint){
		// 投诉待审批
		if(complaint.getProstatus().equals(Constants.COMPLAINT_STATUS_WAIT_APPROVAL)){
			// 投诉审批退回
			return Constants.COMPLAINT_STATUS_APPROVAL_RETURNED;
			// 投诉已升级
		}else if(Constants.COMPLAINT_STATUS_UPGRADED.equals(complaint.getProstatus())
				// 所有部门退回，从升级过来的
				|| Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE_ALL.equals(complaint.getProstatus())
				){
			// 投诉升级退回
			return Constants.COMPLAINT_STATUS_UPGRADED_RETURNED;
		}else{
			throw new ComplaintException(ComplaintExceptionType.connotReturnSubmitor);
		}
	}
}
