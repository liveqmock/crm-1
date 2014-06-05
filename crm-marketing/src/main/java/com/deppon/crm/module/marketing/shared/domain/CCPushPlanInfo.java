/**   
 * <p>
 * 淇濆瓨CC钀ラ攢璁″垝鎺ㄩ�<br />
 * </p>
 * @title CCPushPlanInfo.java
 * @package com.deppon.crm.module.marketing.shared 
 * @author 043260
 * @version 0.1 2014骞�鏈�1鏃�
 */
package com.deppon.crm.module.marketing.shared.domain;

import java.io.Serializable;
import java.util.Date;

/**   
 * <p>
 * 淇濆瓨CC钀ラ攢璁″垝鎺ㄩ�<br />
 * </p>
 * @title CCPushPlanInfo.java
 * @package com.deppon.crm.module.marketing.shared 
 * @author 043260
 * @version 0.1 2014骞�鏈�1鏃�
 */

public class CCPushPlanInfo implements Serializable {
	/** 
	 * @fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = -4135556986116766248L;
	//瀹㈡埛缂栫爜
	private String custNumber;
	//璁″垝寮�鏃堕棿
	private Date planStartTime;
	//璁″垝缁撴潫鏃堕棿
	private Date planEndTime;
	//璁″垝涓婚
	private String planTheme;
	//鎵ц缃戠偣
	private String executeDept;
	//鍒涘缓浜�
	private String creatorCode;
	//澶囨敞
	private String remark;
	//璁″垝绫诲埆
	private String projectType;
	/**
	 * @return custNumber : return the property custNumber.  
	 */
	public String getCustNumber() {
		return custNumber;
	}
	/**
	 * @param custNumber : set the property custNumber. 
	 */
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}
	/**
	 * @return planStartTime : return the property planStartTime.  
	 */
	public Date getPlanStartTime() {
		return planStartTime;
	}
	/**
	 * @param planStartTime : set the property planStartTime. 
	 */
	public void setPlanStartTime(Date planStartTime) {
		this.planStartTime = planStartTime;
	}
	/**
	 * @return planEndTime : return the property planEndTime.  
	 */
	public Date getPlanEndTime() {
		return planEndTime;
	}
	/**
	 * @param planEndTime : set the property planEndTime. 
	 */
	public void setPlanEndTime(Date planEndTime) {
		this.planEndTime = planEndTime;
	}
	/**
	 * @return planTheme : return the property planTheme.  
	 */
	public String getPlanTheme() {
		return planTheme;
	}
	/**
	 * @param planTheme : set the property planTheme. 
	 */
	public void setPlanTheme(String planTheme) {
		this.planTheme = planTheme;
	}
	/**
	 * @return executeDept : return the property executeDept.  
	 */
	public String getExecuteDept() {
		return executeDept;
	}
	/**
	 * @param executeDept : set the property executeDept. 
	 */
	public void setExecuteDept(String executeDept) {
		this.executeDept = executeDept;
	}
	/**
	 * @return creatorCode : return the property creatorCode.  
	 */
	public String getCreatorCode() {
		return creatorCode;
	}
	/**
	 * @param creatorCode : set the property creatorCode. 
	 */
	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}
	/**
	 * @return remark : return the property remark.  
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark : set the property remark. 
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return projectType : return the property projectType.  
	 */
	public String getProjectType() {
		return projectType;
	}
	/**
	 * @param projectType : set the property projectType. 
	 */
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
}
