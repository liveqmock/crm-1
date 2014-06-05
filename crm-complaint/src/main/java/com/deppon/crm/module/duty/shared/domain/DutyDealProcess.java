package com.deppon.crm.module.duty.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;
/**
 * 
 * <p>
 * Description:工单责任 -处理经过<br />
 * </p>
 * @author 钟琼
 * @version 0.1 2013-3-11
 */
@SuppressWarnings("serial")
public class DutyDealProcess extends BaseEntity{
	// 责任ID
	private String dutyId;
	// 处理经过状态
	private String treatStates;
	// 处理经过
	private String treatProcess;
	// 操作员ID
	private String operaprId;
	// 操作员姓名
	private String opratorName;
	// 操作时间
	private Date operatorTime;
	/**
	 * @return the dutyId
	 */
	public String getDutyId() {
		return dutyId;
	}
	/**
	 * @param dutyId the dutyId to set
	 */
	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}
	/**
	 * @return the treatStates
	 */
	public String getTreatStates() {
		return treatStates;
	}
	/**
	 * @param treatStates the treatStates to set
	 */
	public void setTreatStates(String treatStates) {
		this.treatStates = treatStates;
	}
	/**
	 * @return the treatProcess
	 */
	public String getTreatProcess() {
		return treatProcess;
	}
	/**
	 * @param treatProcess the treatProcess to set
	 */
	public void setTreatProcess(String treatProcess) {
		this.treatProcess = treatProcess;
	}
	/**
	 * @return the operaprId
	 */
	public String getOperaprId() {
		return operaprId;
	}
	/**
	 * @param operaprId the operaprId to set
	 */
	public void setOperaprId(String operaprId) {
		this.operaprId = operaprId;
	}
	/**
	 * @return the opratorName
	 */
	public String getOpratorName() {
		return opratorName;
	}
	/**
	 * @param opratorName the opratorName to set
	 */
	public void setOpratorName(String opratorName) {
		this.opratorName = opratorName;
	}
	/**
	 * @return the operatorTime
	 */
	public Date getOperatorTime() {
		return operatorTime;
	}
	/**
	 * @param operatorTime the operatorTime to set
	 */
	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}
	
	
}
