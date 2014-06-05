/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title WarnLostInfoFor360.java
 * @package com.deppon.crm.module.marketing.shared 
 * @author 043260
 * @version 0.1 2014年5月29日
 */
package com.deppon.crm.module.marketing.shared;

import java.util.Date;

/**   
 * <p>
 * 客户360视图提供实体<br />
 * </p>
 * @title WarnLostInfoFor360.java
 * @package com.deppon.crm.module.marketing.shared 
 * @author 043260
 * @version 0.1 2014年5月29日
 */

public class WarnLostInfoFor360 {
	//预警状态
	private String warnStatus;
	//预发货开始时间
	private Date preDeliverytendTime;
	//预发货结束时间
	private Date preDeliverytBiegnTime;
	//是否为季节性
	private String isSeason;
	//流失原因
	private String lostCause;
	//预警时间
	private Date createTime;
	//执行人
	private String executor;
	//变更时间
	private Date warnStatusChangeTime;
	//备注
	private String lostCustRemark;
	/**
	 * @return warnStatus : return the property warnStatus.  
	 */
	public String getWarnStatus() {
		return warnStatus;
	}
	/**
	 * @param warnStatus : set the property warnStatus. 
	 */
	public void setWarnStatus(String warnStatus) {
		this.warnStatus = warnStatus;
	}
	/**
	 * @return preDeliverytendTime : return the property preDeliverytendTime.  
	 */
	public Date getPreDeliverytendTime() {
		return preDeliverytendTime;
	}
	/**
	 * @param preDeliverytendTime : set the property preDeliverytendTime. 
	 */
	public void setPreDeliverytendTime(Date preDeliverytendTime) {
		this.preDeliverytendTime = preDeliverytendTime;
	}
	/**
	 * @return preDeliverytBiegnTime : return the property preDeliverytBiegnTime.  
	 */
	public Date getPreDeliverytBiegnTime() {
		return preDeliverytBiegnTime;
	}
	/**
	 * @param preDeliverytBiegnTime : set the property preDeliverytBiegnTime. 
	 */
	public void setPreDeliverytBiegnTime(Date preDeliverytBiegnTime) {
		this.preDeliverytBiegnTime = preDeliverytBiegnTime;
	}
	/**
	 * @return isSeason : return the property isSeason.  
	 */
	public String getIsSeason() {
		return isSeason;
	}
	/**
	 * @param isSeason : set the property isSeason. 
	 */
	public void setIsSeason(String isSeason) {
		this.isSeason = isSeason;
	}
	/**
	 * @return lostCause : return the property lostCause.  
	 */
	public String getLostCause() {
		return lostCause;
	}
	/**
	 * @param lostCause : set the property lostCause. 
	 */
	public void setLostCause(String lostCause) {
		this.lostCause = lostCause;
	}
	/**
	 * @return createTime : return the property createTime.  
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime : set the property createTime. 
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return executor : return the property executor.  
	 */
	public String getExecutor() {
		return executor;
	}
	/**
	 * @param executor : set the property executor. 
	 */
	public void setExecutor(String executor) {
		this.executor = executor;
	}
	/**
	 * @return warnStatusChangeTime : return the property warnStatusChangeTime.  
	 */
	public Date getWarnStatusChangeTime() {
		return warnStatusChangeTime;
	}
	/**
	 * @param warnStatusChangeTime : set the property warnStatusChangeTime. 
	 */
	public void setWarnStatusChangeTime(Date warnStatusChangeTime) {
		this.warnStatusChangeTime = warnStatusChangeTime;
	}
	/**
	 * @return lostCustRemark : return the property lostCustRemark.  
	 */
	public String getLostCustRemark() {
		return lostCustRemark;
	}
	/**
	 * @param lostCustRemark : set the property lostCustRemark. 
	 */
	public void setLostCustRemark(String lostCustRemark) {
		this.lostCustRemark = lostCustRemark;
	}
	
}
