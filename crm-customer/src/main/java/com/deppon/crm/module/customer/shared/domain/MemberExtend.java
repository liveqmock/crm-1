/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MemberExtend.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author pgj
 * @version 0.1 2014-3-13
 */
package com.deppon.crm.module.customer.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * <p>
 * Description:客户扩展属性<br />
 * </p>
 * 
 * @title MemberExtend.java
 * @package com.deppon.crm.module.customer.shared.domain
 * @author pgj
 * @version 0.1 2014-3-13
 */

public class MemberExtend extends BaseEntity {
	//客户id
	private String custId;
	//是否季节性客户
	private String isSeason;
	//客户预警状态
	private String warnStatus ;
	//货量潜力
	private String volumePotential;
	//信用预警次数
	private String creditTimes;
	//是否信用较差客户 1 是 0 否
	private String isPoorCredit;
	//最后一次信用预警时间
	private Date lastWarnTime;
	//预发货时间开始时间
	private Date preDeliverytBeginTime;
	//预发货时间结束时间
	private Date preDeliverytEndTime;
	//流失原因
	private String lostCause;
	//其他流失原因
	private String otherLostCause;
	//标记季节性客户次数
	private int seasonTims;
	//是否继续营销 0 否 1是 
	private String isContinueMark;
	//客户开发阶段 需求确认、意向洽谈、持续培养、开始发货
	private String developmentPhase;
	//最近合作物流公司
	private String recentCoop;
	//合作意向
	private String coopIntention;
	//走货情况
	private String recentGoods;
	//客户需求
	private String custNeed;
	//适合我司承运 1是 0 否
	private String isAccord;
	//合我司承运备注
	private String accordMark;
	//其他合作物流公司
	private String otherRecentCoop;
	/**
	 *@author chenaichun
	 * @date 2014年3月29日 下午5:34:35 
	 *@return the otherRecentCoop
	 */
	public String getOtherRecentCoop() {
		return otherRecentCoop;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月29日 下午5:34:35 
	 * @param otherRecentCoop the otherRecentCoop to set
	 */
	public void setOtherRecentCoop(String otherRecentCoop) {
		this.otherRecentCoop = otherRecentCoop;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 *@return the custId
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 * @param custId the custId to set
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 *@return the isSeason
	 */
	public String getIsSeason() {
		return isSeason;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 * @param isSeason the isSeason to set
	 */
	public void setIsSeason(String isSeason) {
		this.isSeason = isSeason;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 *@return the warnStatus
	 */
	public String getWarnStatus() {
		return warnStatus;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 * @param warnStatus the warnStatus to set
	 */
	public void setWarnStatus(String warnStatus) {
		this.warnStatus = warnStatus;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 *@return the volumePotential
	 */
	public String getVolumePotential() {
		return volumePotential;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 * @param volumePotential the volumePotential to set
	 */
	public void setVolumePotential(String volumePotential) {
		this.volumePotential = volumePotential;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 *@return the creditTimes
	 */
	public String getCreditTimes() {
		return creditTimes;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 * @param creditTimes the creditTimes to set
	 */
	public void setCreditTimes(String creditTimes) {
		this.creditTimes = creditTimes;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 *@return the isPoorCredit
	 */
	public String getIsPoorCredit() {
		return isPoorCredit;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 * @param isPoorCredit the isPoorCredit to set
	 */
	public void setIsPoorCredit(String isPoorCredit) {
		this.isPoorCredit = isPoorCredit;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 *@return the lastWarnTime
	 */
	public Date getLastWarnTime() {
		return lastWarnTime;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 * @param lastWarnTime the lastWarnTime to set
	 */
	public void setLastWarnTime(Date lastWarnTime) {
		this.lastWarnTime = lastWarnTime;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 *@return the preDeliverytBeginTime
	 */
	public Date getPreDeliverytBeginTime() {
		return preDeliverytBeginTime;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 * @param preDeliverytBeginTime the preDeliverytBeginTime to set
	 */
	public void setPreDeliverytBeginTime(Date preDeliverytBeginTime) {
		this.preDeliverytBeginTime = preDeliverytBeginTime;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 *@return the preDeliverytEndTime
	 */
	public Date getPreDeliverytEndTime() {
		return preDeliverytEndTime;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 * @param preDeliverytEndTime the preDeliverytEndTime to set
	 */
	public void setPreDeliverytEndTime(Date preDeliverytEndTime) {
		this.preDeliverytEndTime = preDeliverytEndTime;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 *@return the lostCause
	 */
	public String getLostCause() {
		return lostCause;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 * @param lostCause the lostCause to set
	 */
	public void setLostCause(String lostCause) {
		this.lostCause = lostCause;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 *@return the otherLostCause
	 */
	public String getOtherLostCause() {
		return otherLostCause;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 * @param otherLostCause the otherLostCause to set
	 */
	public void setOtherLostCause(String otherLostCause) {
		this.otherLostCause = otherLostCause;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 *@return the seasonTims
	 */
	public int getSeasonTims() {
		return seasonTims;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 * @param seasonTims the seasonTims to set
	 */
	public void setSeasonTims(int seasonTims) {
		this.seasonTims = seasonTims;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 *@return the isContinueMark
	 */
	public String getIsContinueMark() {
		return isContinueMark;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 * @param isContinueMark the isContinueMark to set
	 */
	public void setIsContinueMark(String isContinueMark) {
		this.isContinueMark = isContinueMark;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 *@return the developmentPhase
	 */
	public String getDevelopmentPhase() {
		return developmentPhase;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 * @param developmentPhase the developmentPhase to set
	 */
	public void setDevelopmentPhase(String developmentPhase) {
		this.developmentPhase = developmentPhase;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 *@return the recentCoop
	 */
	public String getRecentCoop() {
		return recentCoop;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 * @param recentCoop the recentCoop to set
	 */
	public void setRecentCoop(String recentCoop) {
		this.recentCoop = recentCoop;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 *@return the coopIntention
	 */
	public String getCoopIntention() {
		return coopIntention;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 * @param coopIntention the coopIntention to set
	 */
	public void setCoopIntention(String coopIntention) {
		this.coopIntention = coopIntention;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 *@return the recentGoods
	 */
	public String getRecentGoods() {
		return recentGoods;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 * @param recentGoods the recentGoods to set
	 */
	public void setRecentGoods(String recentGoods) {
		this.recentGoods = recentGoods;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 *@return the custNeed
	 */
	public String getCustNeed() {
		return custNeed;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 * @param custNeed the custNeed to set
	 */
	public void setCustNeed(String custNeed) {
		this.custNeed = custNeed;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 *@return the accordMark
	 */
	public String getAccordMark() {
		return accordMark;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月27日 下午4:41:48 
	 * @param accordMark the accordMark to set
	 */
	public void setAccordMark(String accordMark) {
		this.accordMark = accordMark;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月28日 上午8:45:03 
	 *@return the isAccord
	 */
	public String getIsAccord() {
		return isAccord;
	}
	/**
	 *@author chenaichun
	 * @date 2014年3月28日 上午8:45:03 
	 * @param isAccord the isAccord to set
	 */
	public void setIsAccord(String isAccord) {
		this.isAccord = isAccord;
	}
	

	
}
