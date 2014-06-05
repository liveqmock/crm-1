/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisitOpinionVo.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author Administrator
 * @version 0.1 2012-11-12
 */
package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;

/**   
 * <p>
 * 客户意见VO<br />
 * </p>
 * @title ReturnVisitOpinionVo.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author 苏玉军
 * @version 0.1 2012-11-12
 */

public class ReturnVisitOpinionVo extends ReturnVisitOpinion {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6753691193354039198L;
	//联系人姓名
	private String custLinkManName;
	//计划主题
	private String topic;
	//营销方式
	private String marketingMethod;
	//营销人
	private String marketingPerson;
	//营销时间
	private Date marketingDate;
	//盛诗庆 计划类别
	private String projectType;
	/**
	 * @return custLinkManName : return the property custLinkManName.
	 */
	public String getCustLinkManName() {
		return custLinkManName;
	}
	/**
	 * @param custLinkManName : set the property custLinkManName.
	 */
	public void setCustLinkManName(String custLinkManName) {
		this.custLinkManName = custLinkManName;
	}
	/**
	 * @return topic : return the property topic.
	 */
	public String getTopic() {
		return topic;
	}
	/**
	 * @param topic : set the property topic.
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}
	/**
	 * @return marketingMethod : return the property marketingMethod.
	 */
	public String getMarketingMethod() {
		return marketingMethod;
	}
	/**
	 * @param marketingMethod : set the property marketingMethod.
	 */
	public void setMarketingMethod(String marketingMethod) {
		this.marketingMethod = marketingMethod;
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
	 * @return marketingDate : return the property marketingDate.
	 */
	public Date getMarketingDate() {
		return marketingDate;
	}
	/**
	 * @param marketingDate : set the property marketingDate.
	 */
	public void setMarketingDate(Date marketingDate) {
		this.marketingDate = marketingDate;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
}
