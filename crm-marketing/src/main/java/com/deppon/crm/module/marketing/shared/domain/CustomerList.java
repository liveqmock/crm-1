/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerList.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-4-13
 */
package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerList.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-4-13
 */

public class CustomerList {
	//计划名称
	private String ftopic;
	// 会员ID
	private String memberId;
	// 散客ID
	private String scatterId;
	// 客户姓名
	private String custName;
	//会员等级
	private String memberLevel;
	//联系人Id
	private String contactId;
	// 联系人姓名
	private String linkManName;
	// 联系人手机
	private String linkManMobile;
	// 联系人固话
	private String linkManPhone;
	//未完成计划数量
	private int unfinishedPlanNum;
	//回访次数
	private int visitNum;
	private Date lastVistiTime;
	/**
	 * @return ftopic : return the property ftopic.
	 */
	public String getFtopic() {
		return ftopic;
	}
	/**
	 * @param ftopic : set the property ftopic.
	 */
	public void setFtopic(String ftopic) {
		this.ftopic = ftopic;
	}
	/**
	 * @return memberId : return the property memberId.
	 */
	public String getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId : set the property memberId.
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	/**
	 * @return scatterId : return the property scatterId.
	 */
	public String getScatterId() {
		return scatterId;
	}
	/**
	 * @param scatterId : set the property scatterId.
	 */
	public void setScatterId(String scatterId) {
		this.scatterId = scatterId;
	}
	/**
	 * @return custName : return the property custName.
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * @param custName : set the property custName.
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * @return memberLevel : return the property memberLevel.
	 */
	public String getMemberLevel() {
		return memberLevel;
	}
	/**
	 * @param memberLevel : set the property memberLevel.
	 */
	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}
	/**
	 * @return contactId : return the property contactId.
	 */
	public String getContactId() {
		return contactId;
	}
	/**
	 * @param contactId : set the property contactId.
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	/**
	 * @return linkManName : return the property linkManName.
	 */
	public String getLinkManName() {
		return linkManName;
	}
	/**
	 * @param linkManName : set the property linkManName.
	 */
	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}
	/**
	 * @return linkManMobile : return the property linkManMobile.
	 */
	public String getLinkManMobile() {
		return linkManMobile;
	}
	/**
	 * @param linkManMobile : set the property linkManMobile.
	 */
	public void setLinkManMobile(String linkManMobile) {
		this.linkManMobile = linkManMobile;
	}
	/**
	 * @return linkManPhone : return the property linkManPhone.
	 */
	public String getLinkManPhone() {
		return linkManPhone;
	}
	/**
	 * @param linkManPhone : set the property linkManPhone.
	 */
	public void setLinkManPhone(String linkManPhone) {
		this.linkManPhone = linkManPhone;
	}
	/**
	 * @return unfinishedPlanNum : return the property unfinishedPlanNum.
	 */
	public int getUnfinishedPlanNum() {
		return unfinishedPlanNum;
	}
	/**
	 * @param unfinishedPlanNum : set the property unfinishedPlanNum.
	 */
	public void setUnfinishedPlanNum(int unfinishedPlanNum) {
		this.unfinishedPlanNum = unfinishedPlanNum;
	}
	/**
	 * @return visitNum : return the property visitNum.
	 */
	public int getVisitNum() {
		return visitNum;
	}
	/**
	 * @param visitNum : set the property visitNum.
	 */
	public void setVisitNum(int visitNum) {
		this.visitNum = visitNum;
	}
	/**
	 * @return lastVistiTime : return the property lastVistiTime.
	 */
	public Date getLastVistiTime() {
		return lastVistiTime;
	}
	/**
	 * @param lastVistiTime : set the property lastVistiTime.
	 */
	public void setLastVistiTime(Date lastVistiTime) {
		this.lastVistiTime = lastVistiTime;
	}
	
	
}
