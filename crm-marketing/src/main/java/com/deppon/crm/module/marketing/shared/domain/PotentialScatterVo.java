package com.deppon.crm.module.marketing.shared.domain;

import java.sql.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * 潜客、散客VO，用于开发计划制定查询结果<br />
 * </p>
 * 
 * @title PotentialScatterVo.java
 * @package com.deppon.crm.module.marketing.shared.domain
 * @author 苏玉军
 * @version 0.1 2013-3-14
 */
public class PotentialScatterVo extends BaseEntity {
	private static final long serialVersionUID = -5152017565718042947L;
	// 客户姓名
	private String custName;
	// 联系人姓名
	private String linkManName;
	// 联系人手机
	private String linkManMobile;
	// 联系人固话
	private String linkManPhone;
	// 职位
	private String post;
	// 合作意向
	private String coopIntention;
	// 货量潜力
	private String volumePotential;
	// 商机状态
	private String bussinesState;
	// 最后回访时间
	private Date lastVistiTime;
	
	//回访次数
	private int visitNum;
	
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
	 * @return post : return the property post.
	 */
	public String getPost() {
		return post;
	}
	/**
	 * @param post : set the property post.
	 */
	public void setPost(String post) {
		this.post = post;
	}
	/**
	 * @return coopIntention : return the property coopIntention.
	 */
	public String getCoopIntention() {
		return coopIntention;
	}
	/**
	 * @param coopIntention : set the property coopIntention.
	 */
	public void setCoopIntention(String coopIntention) {
		this.coopIntention = coopIntention;
	}
	/**
	 * @return volumePotential : return the property volumePotential.
	 */
	public String getVolumePotential() {
		return volumePotential;
	}
	/**
	 * @param volumePotential : set the property volumePotential.
	 */
	public void setVolumePotential(String volumePotential) {
		this.volumePotential = volumePotential;
	}
	/**
	 * @return bussinesState : return the property bussinesState.
	 */
	public String getBussinesState() {
		return bussinesState;
	}
	/**
	 * @param bussinesState : set the property bussinesState.
	 */
	public void setBussinesState(String bussinesState) {
		this.bussinesState = bussinesState;
	}
	/**
	 * @param bussinesState : set the property bussinesState.
	 */
	public Date getLastVistiTime() {
		return lastVistiTime;
	}
	public void setLastVistiTime(Date lastVistiTime) {
		this.lastVistiTime = lastVistiTime;
	}
}
