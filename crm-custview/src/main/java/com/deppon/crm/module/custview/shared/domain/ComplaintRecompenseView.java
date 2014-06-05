package com.deppon.crm.module.custview.shared.domain;

import java.util.List;

import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;

/**
 * @description 投诉理赔
 * @author 安小虎
 * @version 0.1 2012-4-23
 * @date 2012-4-23
 */

public class ComplaintRecompenseView {
	// 投诉集合
	private List<Complaint> complaintList;
	// 理赔集合
	private List<RecompenseApplication> recompenseList;
	/**
	 * @return the complaintList
	 * @author 潘光均
	 * @version v0.1
	 */
	public List<Complaint> getComplaintList() {
		return complaintList;
	}
	/**
	 * @param complaintList the complaintList to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setComplaintList(List<Complaint> complaintList) {
		this.complaintList = complaintList;
	}
	/**
	 * @return the recompenseList
	 * @author 潘光均
	 * @version v0.1
	 */
	public List<RecompenseApplication> getRecompenseList() {
		return recompenseList;
	}
	/**
	 * @param recompenseList the recompenseList to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setRecompenseList(List<RecompenseApplication> recompenseList) {
		this.recompenseList = recompenseList;
	}
	
}
