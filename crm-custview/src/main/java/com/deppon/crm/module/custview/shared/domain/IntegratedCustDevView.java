package com.deppon.crm.module.custview.shared.domain;

import java.util.List;

import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;

/**
 * @description 维护信息
 * @author 安小虎
 * @version 0.1 2012-4-23
 * @date 2012-4-23
 */

public class IntegratedCustDevView {
	// 计划/日程
	private List<ReturnVisit> planScheduleList;
	// 维护记录
	private List<ReturnVisit> visitRecordList;
	/**
	 * @return the planScheduleList
	 * @author 潘光均
	 * @version v0.1
	 */
	public List<ReturnVisit> getPlanScheduleList() {
		return planScheduleList;
	}
	/**
	 * @param planScheduleList the planScheduleList to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setPlanScheduleList(List<ReturnVisit> planScheduleList) {
		this.planScheduleList = planScheduleList;
	}
	/**
	 * @return the visitRecordList
	 * @author 潘光均
	 * @version v0.1
	 */
	public List<ReturnVisit> getVisitRecordList() {
		return visitRecordList;
	}
	/**
	 * @param visitRecordList the visitRecordList to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setVisitRecordList(List<ReturnVisit> visitRecordList) {
		this.visitRecordList = visitRecordList;
	}
}
