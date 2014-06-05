package com.deppon.crm.module.scheduler.server.manager;

import java.util.Date;

import com.deppon.crm.module.complaint.server.manager.IComplaintManager;

public class TimerComplaint {
	private IComplaintManager complaintManager;

	public IComplaintManager getComplaintManager() {
		return complaintManager;
	}

	public void setComplaintManager(IComplaintManager complaintManager) {
		this.complaintManager = complaintManager;
	}
	
	
	public void callComplaintTimer() {
		System.out.println("TimerComplaint Call.........." + new Date());
		complaintManager.expireComplaint();
	}
	
	/**
	 * 工单模块之 OA 提醒
	 */
	public void complaint_OA_msg_timer() {
		System.out.println("complaint_OA_msg_timer .........." + new Date());
		complaintManager.processSendComplaintInfo();
	}

	// 工单模块待办事宜
	public void callComplaintToDoTimer(){
		System.out.println("callComplaintToDoTimer Call.........." + new Date());
		complaintManager.processComplaintMessage();
	}

	// 工单模块待办事宜
	public void smsMessageJob(){
		System.out.println("processcellphoneMsg Call.........." + new Date());
		complaintManager.processcellphoneMsg();
	}
}
