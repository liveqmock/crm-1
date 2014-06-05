/**   
 * <p>
 * CC回访记录查询<br />
 * </p>
 * @title CCQueryMarketingRecord.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author 043260
 * @version 0.1 2014年4月6日
 */
package com.deppon.crm.module.marketing.shared.domain;

import java.io.Serializable;
import java.util.Date;

/**   
 * <p>
 * CC回访记录查询<br />
 * </p>
 * @title CCQueryMarketingRecord.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author 043260
 * @version 0.1 2014年4月6日
 */

public class CCQueryMarketingRecord implements Serializable{
	/** 
	 * @fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = 3209829154173092934L;
	//营销主题
	private String planningTopic;
	//营销方式
	private String marketingMode;
	//营销类型
	private String marketingType;
	//回访ID
	private String recallId;
	//客户ID
	private String custId;
	//联系人名称
	private String linkMan;
	//营销人姓名
	private String marketingPerson;
	//营销时间
	private Date marketingTime;
	/**
	 * @return planningTopic : return the property planningTopic.  
	 */
	public String getPlanningTopic() {
		return planningTopic;
	}
	/**
	 * @param planningTopic : set the property planningTopic. 
	 */
	public void setPlanningTopic(String planningTopic) {
		this.planningTopic = planningTopic;
	}
	/**
	 * @return marketingMode : return the property marketingMode.  
	 */
	public String getMarketingMode() {
		return marketingMode;
	}
	/**
	 * @param marketingMode : set the property marketingMode. 
	 */
	public void setMarketingMode(String marketingMode) {
		this.marketingMode = marketingMode;
	}
	/**
	 * @return marketingType : return the property marketingType.  
	 */
	public String getMarketingType() {
		return marketingType;
	}
	/**
	 * @param marketingType : set the property marketingType. 
	 */
	public void setMarketingType(String marketingType) {
		this.marketingType = marketingType;
	}
	/**
	 * @return recallId : return the property recallId.  
	 */
	public String getRecallId() {
		return recallId;
	}
	/**
	 * @param recallId : set the property recallId. 
	 */
	public void setRecallId(String recallId) {
		this.recallId = recallId;
	}
	/**
	 * @return custId : return the property custId.  
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * @param custId : set the property custId. 
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 * @return linkMan : return the property linkMan.  
	 */
	public String getLinkMan() {
		return linkMan;
	}
	/**
	 * @param linkMan : set the property linkMan. 
	 */
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	/**
	 * @return marketingPerson : return the property marketingPerson.  
	 */
	public String getMarketingPerson() {
		return marketingPerson;
	}
	/**
	 * @param marketingPerson : set the property marketingPerson. 
	 */
	public void setMarketingPerson(String marketingPerson) {
		this.marketingPerson = marketingPerson;
	}
	/**
	 * @return marketingTime : return the property marketingTime.  
	 */
	public Date getMarketingTime() {
		return marketingTime;
	}
	/**
	 * @param marketingTime : set the property marketingTime. 
	 */
	public void setMarketingTime(Date marketingTime) {
		this.marketingTime = marketingTime;
	}
	
}
