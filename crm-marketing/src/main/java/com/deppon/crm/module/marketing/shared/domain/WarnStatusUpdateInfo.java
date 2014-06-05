package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;

/**
 * <p>
 * Description:预警状态变更Bean<br />
 * </p>
 * 
 * @title WarnLostCust.java
 * @package com.deppon.crm.module.marketing.shared.domain
 * @author zzw
 * @version 2014-3-11
 */
public class WarnStatusUpdateInfo {
	private int modifyEmpId;// ,--修改人
	private String warnStatus; // ,--预警状态
	private Date preSendBeginTime;// ,--预发货开始时间
	private Date preSendEndTime; // ,--预发货结束时间
	private String lostCause; // ,--流失原因
	private String lostCauseRemark; // ,--流失原因标注
	private int rVid;// --回访ID
	private String custNumber; // ,//--客户编码
	private int isseason; //是否季节性客户
	private Date lastSendTime;//最近一次发货时间
	private Date warnTime;//预警时间
   //无参构造函数
	public WarnStatusUpdateInfo() {
	}
	//构造函数
	public WarnStatusUpdateInfo(int modifyEmpId, String warnStatus,
			Date preSendBeginTime, Date preSendEndTime, String LostCause,
			String LostCauseRemark, int RVid, String custNumber, int isseason) {
		this.custNumber = custNumber;
		this.isseason = isseason;
		this.lostCauseRemark = LostCauseRemark;
		this.lostCause = LostCause;
		this.modifyEmpId = modifyEmpId;
		this.preSendBeginTime = preSendBeginTime;
		this.preSendEndTime = preSendEndTime;
		this.rVid = RVid;
		this.warnStatus = warnStatus;
	}

	public int getModifyEmpId() {
		return modifyEmpId;
	}

	public void setModifyEmpId(int modifyEmpId) {
		this.modifyEmpId = modifyEmpId;
	}

	public String getWarnStatus() {
		return warnStatus;
	}

	public void setWarnStatus(String warnStatus) {
		this.warnStatus = warnStatus;
	}

	public Date getPreSendBeginTime() {
		return preSendBeginTime;
	}

	public void setPreSendBeginTime(Date preSendBeginTime) {
		this.preSendBeginTime = preSendBeginTime;
	}

	public Date getPreSendEndTime() {
		return preSendEndTime;
	}

	public void setPreSendEndTime(Date preSendEndTime) {
		this.preSendEndTime = preSendEndTime;
	}

	
	public String getLostCause() {
		return lostCause;
	}
	public void setLostCause(String lostCause) {
		this.lostCause = lostCause;
	}
	public String getLostCauseRemark() {
		return lostCauseRemark;
	}
	public void setLostCauseRemark(String lostCauseRemark) {
		this.lostCauseRemark = lostCauseRemark;
	}
	public int getrVid() {
		return rVid;
	}

	public void setrVid(int rVid) {
		this.rVid = rVid;
	}

	public String getCustNumber() {
		return custNumber;
	}

	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}

	public int getIsseason() {
		return isseason;
	}

	public void setIsseason(int isseason) {
		this.isseason = isseason;
	}
	public Date getLastSendTime() {
		return lastSendTime;
	}
	public void setLastSendTime(Date lastSendTime) {
		this.lastSendTime = lastSendTime;
	}
	public Date getWarnTime() {
		return warnTime;
	}
	public void setWarnTime(Date warnTime) {
		this.warnTime = warnTime;
	}
	
}
