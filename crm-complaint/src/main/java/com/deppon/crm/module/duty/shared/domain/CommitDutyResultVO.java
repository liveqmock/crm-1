package com.deppon.crm.module.duty.shared.domain;

import java.util.List;

import com.deppon.foss.framework.entity.BaseEntity;

public class CommitDutyResultVO extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<DutyResult> addDutyResultRecords;//新增责任划分结果
	private List<DutyResult> updateDutyResultRecords;//修改责任划分结果
	private List<DutyResult> deleteDutyResultRecords;//删除责任划分结果
	private List<InformUser> addInformUserRecords;//新增通知对象
	private List<InformUser> updateInformUserRecords;//修改通知对象
	private List<InformUser> deleteInformUserRecords;//删除通知对象
	private DutyDealProcess dutyDealProcess;//处理经过
	private Duty dutyDetail;//工单详情
	//处理编号
	private String dealNumber;
	
	
	// 工单划分-处理结果
	private List<DutyResult> dutyResultList;
	//责任通知对象集合
	private List<InformUser> dutyInformUserList;
	//反馈信息
	private DutyFeedback dutyFeedback;
	/**
	 * @return the addDutyResultRecords
	 */
	public List<DutyResult> getAddDutyResultRecords() {
		return addDutyResultRecords;
	}
	/**
	 * @param addDutyResultRecords the addDutyResultRecords to set
	 */
	public void setAddDutyResultRecords(List<DutyResult> addDutyResultRecords) {
		this.addDutyResultRecords = addDutyResultRecords;
	}
	/**
	 * @return the updateDutyResultRecords
	 */
	public List<DutyResult> getUpdateDutyResultRecords() {
		return updateDutyResultRecords;
	}
	/**
	 * @param updateDutyResultRecords the updateDutyResultRecords to set
	 */
	public void setUpdateDutyResultRecords(List<DutyResult> updateDutyResultRecords) {
		this.updateDutyResultRecords = updateDutyResultRecords;
	}
	/**
	 * @return the deleteDutyResultRecords
	 */
	public List<DutyResult> getDeleteDutyResultRecords() {
		return deleteDutyResultRecords;
	}
	/**
	 * @param deleteDutyResultRecords the deleteDutyResultRecords to set
	 */
	public void setDeleteDutyResultRecords(List<DutyResult> deleteDutyResultRecords) {
		this.deleteDutyResultRecords = deleteDutyResultRecords;
	}
	/**
	 * @return the addInformUserRecords
	 */
	public List<InformUser> getAddInformUserRecords() {
		return addInformUserRecords;
	}
	/**
	 * @param addInformUserRecords the addInformUserRecords to set
	 */
	public void setAddInformUserRecords(List<InformUser> addInformUserRecords) {
		this.addInformUserRecords = addInformUserRecords;
	}
	/**
	 * @return the updateInformUserRecords
	 */
	public List<InformUser> getUpdateInformUserRecords() {
		return updateInformUserRecords;
	}
	/**
	 * @param updateInformUserRecords the updateInformUserRecords to set
	 */
	public void setUpdateInformUserRecords(List<InformUser> updateInformUserRecords) {
		this.updateInformUserRecords = updateInformUserRecords;
	}
	/**
	 * @return the deleteInformUserRecords
	 */
	public List<InformUser> getDeleteInformUserRecords() {
		return deleteInformUserRecords;
	}
	/**
	 * @param deleteInformUserRecords the deleteInformUserRecords to set
	 */
	public void setDeleteInformUserRecords(List<InformUser> deleteInformUserRecords) {
		this.deleteInformUserRecords = deleteInformUserRecords;
	}
	/**
	 * @return the dutyDealProcess
	 */
	public DutyDealProcess getDutyDealProcess() {
		return dutyDealProcess;
	}
	/**
	 * @param dutyDealProcess the dutyDealProcess to set
	 */
	public void setDutyDealProcess(DutyDealProcess dutyDealProcess) {
		this.dutyDealProcess = dutyDealProcess;
	}
	/**
	 * @return the dutyDetail
	 */
	public Duty getDutyDetail() {
		return dutyDetail;
	}
	/**
	 * @param dutyDetail the dutyDetail to set
	 */
	public void setDutyDetail(Duty dutyDetail) {
		this.dutyDetail = dutyDetail;
	}
	/**
	 * @return the dutyResultList
	 */
	public List<DutyResult> getDutyResultList() {
		return dutyResultList;
	}
	/**
	 * @param dutyResultList the dutyResultList to set
	 */
	public void setDutyResultList(List<DutyResult> dutyResultList) {
		this.dutyResultList = dutyResultList;
	}
	/**
	 * @return the dutyInformUserList
	 */
	public List<InformUser> getDutyInformUserList() {
		return dutyInformUserList;
	}
	/**
	 * @param dutyInformUserList the dutyInformUserList to set
	 */
	public void setDutyInformUserList(List<InformUser> dutyInformUserList) {
		this.dutyInformUserList = dutyInformUserList;
	}
	/**
	 * @return the dutyFeedback
	 */
	public DutyFeedback getDutyFeedback() {
		return dutyFeedback;
	}
	/**
	 * @param dutyFeedback the dutyFeedback to set
	 */
	public void setDutyFeedback(DutyFeedback dutyFeedback) {
		this.dutyFeedback = dutyFeedback;
	}
	/**
	 * @return the dealNumber
	 */
	public String getDealNumber() {
		return dealNumber;
	}
	/**
	 * @param dealNumber the dealNumber to set
	 */
	public void setDealNumber(String dealNumber) {
		this.dealNumber = dealNumber;
	}
	
	
}
