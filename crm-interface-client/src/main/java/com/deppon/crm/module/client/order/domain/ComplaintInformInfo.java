package com.deppon.crm.module.client.order.domain;

/**
 * 投诉通知消息封装类
 * @author davidcun @2012-4-6
 */
public class ComplaintInformInfo {

	//工号
	private String jobNumber;
	
	//投诉数量
	private int complaintCount;
	
	public ComplaintInformInfo() {
	}

	public ComplaintInformInfo(String jobNumber, int complaintCount) {
		super();
		this.jobNumber = jobNumber;
		this.complaintCount = complaintCount;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public int getComplaintCount() {
		return complaintCount;
	}

	public void setComplaintCount(int complaintCount) {
		this.complaintCount = complaintCount;
	}
}
