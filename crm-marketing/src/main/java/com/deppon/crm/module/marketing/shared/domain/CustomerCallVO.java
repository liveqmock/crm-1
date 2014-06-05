package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description: 客户来电录入，根据手机号码，电话号码，部门id，联系人姓名查询是否存在此
 * 				固定客户或潜散客。因为返回对象不确定，所以统一建立一个实体，将固定客户
 * 				和潜散客转换为此统一实体，作为前台展示。
 * @author 钟琼
 * @Time 2012-11-06
 */
public class CustomerCallVO  extends BaseEntity{
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -8479609292465153788L;
	//联系人手机
	private String contactMobile;
	//联系人电话
	private String contactPhone;
	//联系人名字
	private String custLinkManName;
	//客户名称
	private String custName;
	//职位
	private String contactJob;
	//行业
	private String contactTrade;
	//二级行业
	private String secondTrade;
	//商机状态
	private String busStatus;
	//城市名称
	private String contactCity;
	//城市Id
	private String contactCityId;
	//地址
	private String contactAddress;
	// 客户类型
	private String contactType;
	//联系人Id
	private String custLinkManId;
	//跟踪日程时间
	private Date scheduleDate;
	//营销方式
	private String marketingMothod;
	//业务类别
	private String businessType;
	/**
	 * @return contactMobile : return the property contactMobile.
	 */
	public String getContactMobile() {
		return contactMobile;
	}
	/**
	 * @param contactMobile : set the property contactMobile.
	 */
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	/**
	 * @return contactPhone : return the property contactPhone.
	 */
	public String getContactPhone() {
		return contactPhone;
	}
	/**
	 * @param contactPhone : set the property contactPhone.
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
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
	 * @return contactJob : return the property contactJob.
	 */
	public String getContactJob() {
		return contactJob;
	}
	/**
	 * @param contactJob : set the property contactJob.
	 */
	public void setContactJob(String contactJob) {
		this.contactJob = contactJob;
	}
	/**
	 * @return contactTrade : return the property contactTrade.
	 */
	public String getContactTrade() {
		return contactTrade;
	}
	/**
	 * @param contactTrade : set the property contactTrade.
	 */
	public void setContactTrade(String contactTrade) {
		this.contactTrade = contactTrade;
	}
	/**
	 * @param secondTrade : get the property secondTrade.
	 */
	public String getSecondTrade() {
		return secondTrade;
	}
	/**
	 * @param secondTrade : set the property secondTrade.
	 */
	public void setSecondTrade(String secondTrade) {
		this.secondTrade = secondTrade;
	}
	/**
	 * @return busStatus : return the property busStatus.
	 */
	public String getBusStatus() {
		return busStatus;
	}
	/**
	 * @param busStatus : set the property busStatus.
	 */
	public void setBusStatus(String busStatus) {
		this.busStatus = busStatus;
	}
	/**
	 * @return contactCity : return the property contactCity.
	 */
	public String getContactCity() {
		return contactCity;
	}
	/**
	 * @param contactCity : set the property contactCity.
	 */
	public void setContactCity(String contactCity) {
		this.contactCity = contactCity;
	}
	/**
	 * @return contactCityId : return the property contactCityId.
	 */
	public String getContactCityId() {
		return contactCityId;
	}
	/**
	 * @param contactCityId : set the property contactCityId.
	 */
	public void setContactCityId(String contactCityId) {
		this.contactCityId = contactCityId;
	}
	/**
	 * @return contactAddress : return the property contactAddress.
	 */
	public String getContactAddress() {
		return contactAddress;
	}
	/**
	 * @param contactAddress : set the property contactAddress.
	 */
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	/**
	 * @return contactType : return the property contactType.
	 */
	public String getContactType() {
		return contactType;
	}
	/**
	 * @param contactType : set the property contactType.
	 */
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	/**
	 * @return custLinkManId : return the property custLinkManId.
	 */
	public String getCustLinkManId() {
		return custLinkManId;
	}
	/**
	 * @param custLinkManId : set the property custLinkManId.
	 */
	public void setCustLinkManId(String custLinkManId) {
		this.custLinkManId = custLinkManId;
	}
	/**
	 * @return scheduleDate : return the property scheduleDate.
	 */
	public Date getScheduleDate() {
		return scheduleDate;
	}
	/**
	 * @param scheduleDate : set the property scheduleDate.
	 */
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	/**
	 * @return marketingMothod : return the property marketingMothod.
	 */
	public String getMarketingMothod() {
		return marketingMothod;
	}
	/**
	 * @param marketingMothod : set the property marketingMothod.
	 */
	public void setMarketingMothod(String marketingMothod) {
		this.marketingMothod = marketingMothod;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-1-21
	 */
	public String getBusinessType() {
		return businessType;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-1-21
	 * @param businessType the businessType to set
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

}
