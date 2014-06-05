/**
 * Filename:	Constants.java
 * Description: 投诉通知出发部门
 * Copyright:	Copyright (c)2013
 * Company:		CRM
 * @author:		
 * @version:	
 * create at:	2013-7-12 下午3:12:44
 *
 * Modification History:
 * Date			Author			Version			Description
 * ------------------------------------------------------------------------
 * 2013-7-12   
 */

package com.deppon.crm.module.complaint.server.util;

import java.util.Map;

public interface ComplaintConstants {
	//客户维护通知
	public static final String MESSAGE_CUSTOMER_MAINTENANCE_NOTICE_CONTENT = "【客户维护通知】您好，{0}，贵部有出发货引起客户投诉，请贵部对发货人做好维护工作，单号{1}，来电人{2}，已通知任务部门处理。为避免客户流失，请及时跟踪反馈并做好客户维护工作！";
	
	//待办
	public static final String TODO_MESSAGE="您有一条凭证号为{0}的工单信息待处理，请及时核实处理！";
	
	// 待办事宜的类型
	public static final String TODO_TASK_TYPE = "COMPLAINT_TASK";
	//工单接入模式错误
	public static String COMPLAINT_BUSINESSMODEL_EXCEPTION ="请接入与列表中业务模式相同的工单";
}

