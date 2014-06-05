package com.deppon.crm.module.duty.shared.domain;

import java.util.Date;
import java.util.List;

import com.deppon.foss.framework.entity.BaseEntity;

public class SearchDutyCondition extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//ID
	private String id;
	//编号类型
	private String codeType;
	//查询编号
	private String code;
	//接入人ID
	private String receiveUserId;
	//接入人
	private String receiveUser;
	//接入开始时间
	private Date receiveBeginTime;
	//接入结束时间
	private Date receiveEndTime;
	//暂存标识
	private String proStatus;
	//工单责任划分状态
	private List<String> status ;
	//工单接入条数
	private String rowNum;
	//上报类型
	private String reportType; 
	//责任反馈
	private String dutyFeedback;
	//部门ID
	private String deptId;
	//是否第一次进入页面
	private int firstLoad;
	//值
	private String value;
	
	
	// 业务模式 快递 EXPRESS 零担UNEXPRESS
	private String businessModel;
	
	//责任划分结果ID
	private String dutyResultId;
	/**
	 * @return id : return the property id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id : set the property id.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return codeType : return the property codeType.
	 */
	public String getCodeType() {
		return codeType;
	}
	/**
	 * @param codeType : set the property codeType.
	 */
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	/**
	 * @return deptId : return the property deptId.
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId : set the property deptId.
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * @return code : return the property code.
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code : set the property code.
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return receiveUserId : return the property receiveUserId.
	 */
	public String getReceiveUserId() {
		return receiveUserId;
	}
	/**
	 * @param receiveUserId : set the property receiveUserId.
	 */
	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}
	/**
	 * @return receiveUser : return the property receiveUser.
	 */
	public String getReceiveUser() {
		return receiveUser;
	}
	/**
	 * @param receiveUser : set the property receiveUser.
	 */
	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser;
	}
	/**
	 * @return receiveBeginTime : return the property receiveBeginTime.
	 */
	public Date getReceiveBeginTime() {
		return receiveBeginTime;
	}
	/**
	 * @param receiveBeginTime : set the property receiveBeginTime.
	 */
	public void setReceiveBeginTime(Date receiveBeginTime) {
		this.receiveBeginTime = receiveBeginTime;
	}
	/**
	 * @return receiveEndTime : return the property receiveEndTime.
	 */
	public Date getReceiveEndTime() {
		return receiveEndTime;
	}
	/**
	 * @param receiveEndTime : set the property receiveEndTime.
	 */
	public void setReceiveEndTime(Date receiveEndTime) {
		this.receiveEndTime = receiveEndTime;
	}
	/**
	 * @return proStatus : return the property proStatus.
	 */
	public String getProStatus() {
		return proStatus;
	}
	/**
	 * @param proStatus : set the property proStatus.
	 */
	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}
	/**
	 * @return status : return the property status.
	 */
	public List<String> getStatus() {
		return status;
	}
	/**
	 * @param status : set the property status.
	 */
	public void setStatus(List<String> status) {
		this.status = status;
	}
	/**
	 * @return rowNum : return the property rowNum.
	 */
	public String getRowNum() {
		return rowNum;
	}
	/**
	 * @param rowNum : set the property rowNum.
	 */
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	/**
	 * @return reportType : return the property reportType.
	 */
	public String getReportType() {
		return reportType;
	}
	/**
	 * @param reportType : set the property reportType.
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	/**
	 * @return dutyFeedback : return the property dutyFeedback.
	 */
	public String getDutyFeedback() {
		return dutyFeedback;
	}
	/**
	 * @param dutyFeedback : set the property dutyFeedback.
	 */
	public void setDutyFeedback(String dutyFeedback) {
		this.dutyFeedback = dutyFeedback;
	}
	/**
	 * @param firstLoad : get the property dutyFeedback.
	 */
	public int getFirstLoad() {
		return firstLoad;
	}
	/**
	 * @param firstLoad : set the property dutyFeedback.
	 */
	public void setFirstLoad(int firstLoad) {
		this.firstLoad = firstLoad;
	}
	/**
	 * @return value : return the property value.
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value : set the property value.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	public String getBusinessModel() {
		return businessModel;
	}
	public void setBusinessModel(String businessModel) {
		this.businessModel = businessModel;
	}
	/**
	 * @return the dutyResultId
	 */
	public String getDutyResultId() {
		return dutyResultId;
	}
	/**
	 * @param dutyResultId the dutyResultId to set
	 */
	public void setDutyResultId(String dutyResultId) {
		this.dutyResultId = dutyResultId;
	}
	
}
