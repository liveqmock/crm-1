package com.deppon.crm.module.customer.shared.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @description : 联系人
 * @author : 罗典
 * @createTime : 2012-2-27 14:29
 * */
public class Contact extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 联系人编码
	private String number;
	// 联系人姓名
	private String name;
	// 证件类型
	private String cardTypeCon;
	// 身份证号
	private String idCard;
	// 联系人类型
	private String linkmanType;
	// 是否主联系人1为是，0为否
	private Boolean isMainLinkMan;
	// 性别
	private String sex;
	// 固定电话
	private String telPhone;
	// 手机号码
	private String mobilePhone;
	// 职务
	private String duty;
	// 任职部门
	private String dutyDept;
	// 出生日期
	private Date bornDate;
	// 获知公司途径
	private String gainave;
	// 物流决定权
	private String decisionRight;
	// 籍贯
	private String nativePlace;
	// 个人爱好
	private String personLove;
	// 民族
	private String folk;
	// Email地址
	private String email;
	// QQ号码
	private String qqNumber;
	// MSN
	private String msn;
	// 旺旺号
	private String ww;
	// 受理部门ID
	private String acceptDeptid;
	// 状态    正常：0；  审批中：1  ；失效 2；
	private String status;
	// 默认账户ID
	private String defaultId;
	// 版本号ID
	private String versionId;
	// 客户ID
	private String custId;
	// 会员信息
	private Member member;
	// 偏好地址
	private List<PreferenceAddress> preferenceAddressList = new ArrayList<PreferenceAddress>();
	//所属客户的客户类型
	private String belongCustType;

	//X轴
	private String lat;
	//Y轴
	private String lng;
	
	//传真号
	private String ffax;
	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午3:25:23 
	 *@return the ffax
	 */
	public String getFfax() {
		return ffax;
	}
	/**
	 *@author chenaichun
	 * @date 2014-3-7 下午3:25:23 
	 * @param ffax the ffax to set
	 */
	public void setFfax(String ffax) {
		this.ffax = ffax;
	}
	/**
	 * Description:lat<br />
	 * @author CoCo
	 * @version 0.1 2013-5-27
	 */
	public String getLat() {
		return lat;
	}
	/**
	 * Description:lat<br />
	 * @author CoCo
	 * @version 0.1 2013-5-27
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	/**
	 * Description:lng<br />
	 * @author CoCo
	 * @version 0.1 2013-5-27
	 */
	public String getLng() {
		return lng;
	}
	/**
	 * Description:lng<br />
	 * @author CoCo
	 * @version 0.1 2013-5-27
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}
	/**
	 * <p>
	 * Description:number<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * <p>
	 * Description:number<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setNumber(String number) {
		this.number = number;
	}


	/**
	 * <p>
	 * Description:name<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getName() {
		return name;
	}


	/**
	 * <p>
	 * Description:name<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * <p>
	 * Description:cardTypeCon<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCardTypeCon() {
		return cardTypeCon;
	}


	/**
	 * <p>
	 * Description:cardTypeCon<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCardTypeCon(String cardTypeCon) {
		this.cardTypeCon = cardTypeCon;
	}


	/**
	 * <p>
	 * Description:idCard<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getIdCard() {
		return idCard;
	}


	/**
	 * <p>
	 * Description:idCard<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/**
	 * <p>
	 * Description:linkmanType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLinkmanType() {
		return linkmanType;
	}

	/**
	 * <p>
	 * Description:linkmanType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLinkmanType(String linkmanType) {
		this.linkmanType = linkmanType;
	}


	/**
	 * <p>
	 * Description:isMainLinkMan<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Boolean getIsMainLinkMan() {
		return isMainLinkMan;
	}


	/**
	 * <p>
	 * Description:isMainLinkMan<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIsMainLinkMan(Boolean isMainLinkMan) {
		this.isMainLinkMan = isMainLinkMan;
	}


	/**
	 * <p>
	 * Description:sex<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getSex() {
		return sex;
	}


	/**
	 * <p>
	 * Description:sex<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * <p>
	 * Description:telPhone<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getTelPhone() {
		return telPhone;
	}

	/**
	 * <p>
	 * Description:telPhone<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	/**
	 * <p>
	 * Description:mobilePhone<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * <p>
	 * Description:mobilePhone<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 * <p>
	 * Description:duty<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDuty() {
		return duty;
	}

	/**
	 * <p>
	 * Description:duty<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDuty(String duty) {
		this.duty = duty;
	}

	/**
	 * <p>
	 * Description:dutyDept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDutyDept() {
		return dutyDept;
	}

	/**
	 * <p>
	 * Description:dutyDept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDutyDept(String dutyDept) {
		this.dutyDept = dutyDept;
	}


	/**
	 * <p>
	 * Description:bornDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getBornDate() {
		return bornDate;
	}


	/**
	 * <p>
	 * Description:bornDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}


	/**
	 * <p>
	 * Description:gainave<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getGainave() {
		return gainave;
	}

	/**
	 * <p>
	 * Description:gainave<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setGainave(String gainave) {
		this.gainave = gainave;
	}

	/**
	 * <p>
	 * Description:decisionRight<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDecisionRight() {
		return decisionRight;
	}

	/**
	 * <p>
	 * Description:decisionRight<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDecisionRight(String decisionRight) {
		this.decisionRight = decisionRight;
	}

	/**
	 * <p>
	 * Description:nativePlace<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getNativePlace() {
		return nativePlace;
	}

	/**
	 * <p>
	 * Description:nativePlace<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	/**
	 * <p>
	 * Description:personLove<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getPersonLove() {
		return personLove;
	}

	/**
	 * <p>
	 * Description:personLove<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setPersonLove(String personLove) {
		this.personLove = personLove;
	}

	/**
	 * <p>
	 * Description:folk<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getFolk() {
		return folk;
	}

	/**
	 * <p>
	 * Description:folk<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setFolk(String folk) {
		this.folk = folk;
	}

	/**
	 * <p>
	 * Description:email<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * <p>
	 * Description:email<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * <p>
	 * Description:qqNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getQqNumber() {
		return qqNumber;
	}


	/**
	 * <p>
	 * Description:qqNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	/**
	 * <p>
	 * Description:msn<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getMsn() {
		return msn;
	}

	/**
	 * <p>
	 * Description:msn<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMsn(String msn) {
		this.msn = msn;
	}

	/**
	 * <p>
	 * Description:ww<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getWw() {
		return ww;
	}

	/**
	 * <p>
	 * Description:ww<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setWw(String ww) {
		this.ww = ww;
	}


	/**
	 * <p>
	 * Description:acceptDeptid<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getAcceptDeptid() {
		return acceptDeptid;
	}


	/**
	 * <p>
	 * Description:acceptDeptid<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setAcceptDeptid(String acceptDeptid) {
		this.acceptDeptid = acceptDeptid;
	}


	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * <p>
	 * Description:defaultId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDefaultId() {
		return defaultId;
	}

	/**
	 * <p>
	 * Description:defaultId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDefaultId(String defaultId) {
		this.defaultId = defaultId;
	}


	/**
	 * <p>
	 * Description:versionId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getVersionId() {
		return versionId;
	}

	/**
	 * <p>
	 * Description:versionId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}


	/**
	 * <p>
	 * Description:custId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustId() {
		return custId;
	}


	/**
	 * <p>
	 * Description:custId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}


	/**
	 * <p>
	 * Description:member<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Member getMember() {
		return member;
	}


	/**
	 * <p>
	 * Description:member<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMember(Member member) {
		this.member = member;
	}


	/**
	 * <p>
	 * Description:preferenceAddressList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public List<PreferenceAddress> getPreferenceAddressList() {
		return preferenceAddressList;
	}


	/**
	 * <p>
	 * Description:preferenceAddressList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setPreferenceAddressList(
			List<PreferenceAddress> preferenceAddressList) {
		this.preferenceAddressList = preferenceAddressList;
	}


	/**
	 * <p>
	 * Description:belongCustType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getBelongCustType() {
		return belongCustType;
	}


	/**
	 * <p>
	 * Description:belongCustType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBelongCustType(String belongCustType) {
		this.belongCustType = belongCustType;
	}


	/**
	 * <p>
	 * Description:serialversionuid<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((acceptDeptid == null) ? 0 : acceptDeptid.hashCode());
		result = prime * result + ((belongCustType == null) ? 0 : belongCustType.hashCode());
		result = prime * result + ((bornDate == null) ? 0 : bornDate.hashCode());
		result = prime * result + ((cardTypeCon == null) ? 0 : cardTypeCon.hashCode());
		result = prime * result + ((custId == null) ? 0 : custId.hashCode());
		result = prime * result + ((decisionRight == null) ? 0 : decisionRight.hashCode());
		result = prime * result + ((defaultId == null) ? 0 : defaultId.hashCode());
		result = prime * result + ((duty == null) ? 0 : duty.hashCode());
		result = prime * result + ((dutyDept == null) ? 0 : dutyDept.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((ffax == null) ? 0 : ffax.hashCode());
		result = prime * result + ((folk == null) ? 0 : folk.hashCode());
		result = prime * result + ((gainave == null) ? 0 : gainave.hashCode());
		result = prime * result + ((idCard == null) ? 0 : idCard.hashCode());
		result = prime * result + ((isMainLinkMan == null) ? 0 : isMainLinkMan.hashCode());
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((linkmanType == null) ? 0 : linkmanType.hashCode());
		result = prime * result + ((lng == null) ? 0 : lng.hashCode());
		result = prime * result + ((member == null) ? 0 : member.hashCode());
		result = prime * result + ((mobilePhone == null) ? 0 : mobilePhone.hashCode());
		result = prime * result + ((msn == null) ? 0 : msn.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nativePlace == null) ? 0 : nativePlace.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((personLove == null) ? 0 : personLove.hashCode());
		result = prime * result + ((preferenceAddressList == null) ? 0 : preferenceAddressList.hashCode());
		result = prime * result + ((qqNumber == null) ? 0 : qqNumber.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((telPhone == null) ? 0 : telPhone.hashCode());
		result = prime * result + ((versionId == null) ? 0 : versionId.hashCode());
		result = prime * result + ((ww == null) ? 0 : ww.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (acceptDeptid == null) {
			if (other.acceptDeptid != null)
				return false;
		} else if (!acceptDeptid.equals(other.acceptDeptid))
			return false;
		if (belongCustType == null) {
			if (other.belongCustType != null)
				return false;
		} else if (!belongCustType.equals(other.belongCustType))
			return false;
		if (bornDate == null) {
			if (other.bornDate != null)
				return false;
		} else if (!bornDate.equals(other.bornDate))
			return false;
		if (cardTypeCon == null) {
			if (other.cardTypeCon != null)
				return false;
		} else if (!cardTypeCon.equals(other.cardTypeCon))
			return false;
		if (custId == null) {
			if (other.custId != null)
				return false;
		} else if (!custId.equals(other.custId))
			return false;
		if (decisionRight == null) {
			if (other.decisionRight != null)
				return false;
		} else if (!decisionRight.equals(other.decisionRight))
			return false;
		if (defaultId == null) {
			if (other.defaultId != null)
				return false;
		} else if (!defaultId.equals(other.defaultId))
			return false;
		if (duty == null) {
			if (other.duty != null)
				return false;
		} else if (!duty.equals(other.duty))
			return false;
		if (dutyDept == null) {
			if (other.dutyDept != null)
				return false;
		} else if (!dutyDept.equals(other.dutyDept))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (ffax == null) {
			if (other.ffax != null)
				return false;
		} else if (!ffax.equals(other.ffax))
			return false;
		if (folk == null) {
			if (other.folk != null)
				return false;
		} else if (!folk.equals(other.folk))
			return false;
		if (gainave == null) {
			if (other.gainave != null)
				return false;
		} else if (!gainave.equals(other.gainave))
			return false;
		if (idCard == null) {
			if (other.idCard != null)
				return false;
		} else if (!idCard.equals(other.idCard))
			return false;
		if (isMainLinkMan == null) {
			if (other.isMainLinkMan != null)
				return false;
		} else if (!isMainLinkMan.equals(other.isMainLinkMan))
			return false;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (linkmanType == null) {
			if (other.linkmanType != null)
				return false;
		} else if (!linkmanType.equals(other.linkmanType))
			return false;
		if (lng == null) {
			if (other.lng != null)
				return false;
		} else if (!lng.equals(other.lng))
			return false;
		if (member == null) {
			if (other.member != null)
				return false;
		} else if (!member.equals(other.member))
			return false;
		if (mobilePhone == null) {
			if (other.mobilePhone != null)
				return false;
		} else if (!mobilePhone.equals(other.mobilePhone))
			return false;
		if (msn == null) {
			if (other.msn != null)
				return false;
		} else if (!msn.equals(other.msn))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nativePlace == null) {
			if (other.nativePlace != null)
				return false;
		} else if (!nativePlace.equals(other.nativePlace))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (personLove == null) {
			if (other.personLove != null)
				return false;
		} else if (!personLove.equals(other.personLove))
			return false;
		if (preferenceAddressList == null) {
			if (other.preferenceAddressList != null)
				return false;
		} else if (!preferenceAddressList.equals(other.preferenceAddressList))
			return false;
		if (qqNumber == null) {
			if (other.qqNumber != null)
				return false;
		} else if (!qqNumber.equals(other.qqNumber))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (telPhone == null) {
			if (other.telPhone != null)
				return false;
		} else if (!telPhone.equals(other.telPhone))
			return false;
		if (versionId == null) {
			if (other.versionId != null)
				return false;
		} else if (!versionId.equals(other.versionId))
			return false;
		if (ww == null) {
			if (other.ww != null)
				return false;
		} else if (!ww.equals(other.ww))
			return false;
		return true;
	}
	
}
