/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ChanenCustomer.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author pgj
 * @version 0.1 2014-3-4
 */
package com.deppon.crm.module.customer.shared.domain;

import java.util.List;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:渠道客户<br />
 * </p>
 * @title ChanenCustomer.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author pgj
 * @version 0.1 2014-3-4
 */

public class ChannelCustomer extends BaseEntity  {
	//fossID
	private String erpId;
	//官网注册用户名（放在custName里面）
// 	private String userName;
	//渠道来源（官网和CC）
	private String channelSource;
	//手机号（官网和foss）
    private String mobilePhone;
    //固定电话号
    private String telPhone;
    //联系人名称(官网和CC共用)
    private String linkManName;
	//手机号
    private String beforeMobilePhone;
    //固定电话号
    private String beforeTelPhone;
    //联系人名称(官网和foss共用)
    private String beforeLinkManName;
    //CC对应客户名称   官网对应用户名
    private String custName;
 	// 详细地址
 	private String address;
 	// 省份ID
 	private String province;
 	//省份名称
 	private String provinceName;
 	// 城市ID
 	private String city;
 	//城市名称
 	private String cityName;
 	// 区县ID
 	private String area;
 	//区县名称
 	private String areaName;
 	//邮箱
 	private String email;
 	//传真
 	private String ffax;
 	//客户行业CC
 	private String tradeId;
 	//二级行业CC
 	private String secondTrade;
 	//偏好渠道CC
 	private String channel;
 	//偏好服务CC
 	private String preferenceService;
 	//客户类型（个人、企业）
 	private String custType;
	// 客户编码
	private String custNumber;
	//CC传过来的联系人列表
	private List<Contact> contactList;
	//foss传过来的客户属性(出发、到达)
	private String custNature;
	//foss传过来的部门ID
	private String deptStandCode;
	//客户类别（零担、快递、零担快递）
	private String custCategory;
	
	/**
	 *@author chenaichun
	 * @date 2014年4月8日 下午4:53:51 
	 *@return the custCategory
	 */
	public String getCustCategory() {
		return custCategory;
	}

	/**
	 *@author chenaichun
	 * @date 2014年4月8日 下午4:53:51 
	 * @param custCategory the custCategory to set
	 */
	public void setCustCategory(String custCategory) {
		this.custCategory = custCategory;
	}

	/**
	 *@author chenaichun
	 * @date 2014年4月8日 下午3:11:59 
	 *@return the deptStandCode
	 */
	public String getDeptStandCode() {
		return deptStandCode;
	}

	/**
	 *@author chenaichun
	 * @date 2014年4月8日 下午3:11:59 
	 * @param deptStandCode the deptStandCode to set
	 */
	public void setDeptStandCode(String deptStandCode) {
		this.deptStandCode = deptStandCode;
	}

	/**
	 *@author chenaichun
	 * @date 2014年4月8日 下午2:35:38 
	 *@return the custNature
	 */
	public String getCustNature() {
		return custNature;
	}

	/**
	 *@author chenaichun
	 * @date 2014年4月8日 下午2:35:38 
	 * @param custNature the custNature to set
	 */
	public void setCustNature(String custNature) {
		this.custNature = custNature;
	}

	/**
	 *@author chenaichun
	 * @date 2014年4月3日 下午2:10:15 
	 *@return the contactList
	 */
	public List<Contact> getContactList() {
		return contactList;
	}

	/**
	 *@author chenaichun
	 * @date 2014年4月3日 下午2:10:15 
	 * @param contactList the contactList to set
	 */
	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}

	/**
	 *@user pgj
	 *2014-3-8下午2:49:01
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

 	
//	/**
//	 *@author chenaichun
//	 * @date 2014-3-8 上午10:02:48 
//	 *@return the userName
//	 */
//	public String getUserName() {
//		return userName;
//	}
//
//	/**
//	 *@author chenaichun
//	 * @date 2014-3-8 上午10:02:48 
//	 * @param userName the userName to set
//	 */
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 *@return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 *@return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 *@return the provinceName
	 */
	public String getProvinceName() {
		return provinceName;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 * @param provinceName the provinceName to set
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 *@return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 *@return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 *@return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 *@return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 * @param areaName the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 *@return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 *@return the ffax
	 */
	public String getFfax() {
		return ffax;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 * @param ffax the ffax to set
	 */
	public void setFfax(String ffax) {
		this.ffax = ffax;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 *@return the tradeId
	 */
	public String getTradeId() {
		return tradeId;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 * @param tradeId the tradeId to set
	 */
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 *@return the secondTrade
	 */
	public String getSecondTrade() {
		return secondTrade;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 * @param secondTrade the secondTrade to set
	 */
	public void setSecondTrade(String secondTrade) {
		this.secondTrade = secondTrade;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 *@return the channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 *@return the preferenceService
	 */
	public String getPreferenceService() {
		return preferenceService;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 * @param preferenceService the preferenceService to set
	 */
	public void setPreferenceService(String preferenceService) {
		this.preferenceService = preferenceService;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 *@return the custType
	 */
	public String getCustType() {
		return custType;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午4:37:59 
	 * @param custType the custType to set
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-5 上午10:13:31 
	 *@return the custName
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-5 上午10:13:31 
	 * @param custName the custName to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-5 上午9:26:20 
	 *@return the linkManName
	 */
	public String getLinkManName() {
		return linkManName;
	}

	/**
	 *@user pgj
	 *2014-3-10上午8:50:07
	 * @return beforeMobilePhone : return the property beforeMobilePhone.
	 */
	public String getBeforeMobilePhone() {
		return beforeMobilePhone;
	}

	/**
	 * @param beforeMobilePhone : set the property beforeMobilePhone.
	 */
	public void setBeforeMobilePhone(String beforeMobilePhone) {
		this.beforeMobilePhone = beforeMobilePhone;
	}

	/**
	 *@user pgj
	 *2014-3-10上午8:50:07
	 * @return beforeTelPhone : return the property beforeTelPhone.
	 */
	public String getBeforeTelPhone() {
		return beforeTelPhone;
	}

	/**
	 * @param beforeTelPhone : set the property beforeTelPhone.
	 */
	public void setBeforeTelPhone(String beforeTelPhone) {
		this.beforeTelPhone = beforeTelPhone;
	}

	/**
	 *@user pgj
	 *2014-3-10上午8:50:07
	 * @return beforeLinkManName : return the property beforeLinkManName.
	 */
	public String getBeforeLinkManName() {
		return beforeLinkManName;
	}

	/**
	 * @param beforeLinkManName : set the property beforeLinkManName.
	 */
	public void setBeforeLinkManName(String beforeLinkManName) {
		this.beforeLinkManName = beforeLinkManName;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-5 上午9:26:20 
	 * @param linkManName the linkManName to set
	 */
	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-4 上午11:07:35 
	 *@return the mobilePhone
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-4 上午11:07:35 
	 * @param mobilePhone the mobilePhone to set
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-4 上午11:07:35 
	 *@return the telPhone
	 */
	public String getTelPhone() {
		return telPhone;
	}

	/**
	 *@author chenaichun
	 * @date 2014-3-4 上午11:07:35 
	 * @param telPhone the telPhone to set
	 */
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	/**
	 *@user pgj
	 *2014-3-4上午9:43:18
	 * @return channelSource : return the property channelSource.
	 */
	public String getChannelSource() {
		return channelSource;
	}

	/**
	 * @param channelSource : set the property channelSource.
	 */
	public void setChannelSource(String channelSource) {
		this.channelSource = channelSource;
	}

	/**
	 *@author chenaichun
	 * @date 2014年4月14日 下午5:13:07 
	 *@return the erpId
	 */
	public String getErpId() {
		return erpId;
	}

	/**
	 *@author chenaichun
	 * @date 2014年4月14日 下午5:13:07 
	 * @param erpId the erpId to set
	 */
	public void setErpId(String erpId) {
		this.erpId = erpId;
	}
	
	
}
