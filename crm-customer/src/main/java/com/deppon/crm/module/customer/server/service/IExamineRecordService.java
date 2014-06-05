package com.deppon.crm.module.customer.server.service;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.customer.shared.domain.ExamineRecord;

public interface IExamineRecordService {
	/**
	 * <p>
	 * 保存审批记录<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-12
	 * @param record
	 * void
	 */
	void saveExamineRecord(ExamineRecord record);
	
	/**
	 * <p>
	 * 得到工作流对应的审批记录<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-12
	 * @param workflowId
	 * @return
	 * List<ExamineRecord>
	 */
	List<ExamineRecord> getExamineRecordByWorkflowId(long workflowId);
	
	/**
	 * <p>
	 * Description:通过工作流号 得到当前审批人的  名字和工号<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param workflowId
	 * @return
	 * String
	 */
	public String getCurrentPeople(long workflowId);
	

}
