package com.deppon.crm.module.interfaces.customer.domain;

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
	// 阿里ID
	private String alid;
	// 网营ID
	private String onlineBusinessId;
	// 淘宝ID
	private String taobId;
	// 金蝶友商ID
	private String youshangId;

	// 账号信息ID
	private String accountId;
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
	//private Member member;
	// 偏好地址
	//private List<PreferenceAddress> preferenceAddressList = new ArrayList<PreferenceAddress>();
	//所属客户的客户类型
	private String belongCustType;

	//X轴
	private String lat;
	//Y轴
	private String lng;
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
	 * Description:alid<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getAlid() {
		return alid;
	}

	/**
	 * <p>
	 * Description:alid<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setAlid(String alid) {
		this.alid = alid;
	}

	/**
	 * <p>
	 * Description:onlineBusinessId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getOnlineBusinessId() {
		return onlineBusinessId;
	}

	/**
	 * <p>
	 * Description:onlineBusinessId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setOnlineBusinessId(String onlineBusinessId) {
		this.onlineBusinessId = onlineBusinessId;
	}

	/**
	 * <p>
	 * Description:taobId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getTaobId() {
		return taobId;
	}

	/**
	 * <p>
	 * Description:taobId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTaobId(String taobId) {
		this.taobId = taobId;
	}

	/**
	 * <p>
	 * Description:youshangId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getYoushangId() {
		return youshangId;
	}

	/**
	 * <p>
	 * Description:youshangId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setYoushangId(String youshangId) {
		this.youshangId = youshangId;
	}

	/**
	 * <p>
	 * Description:accountId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * <p>
	 * Description:accountId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
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
		result = prime * result
				+ ((acceptDeptid == null) ? 0 : acceptDeptid.hashCode());
		result = prime * result
				+ ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((alid == null) ? 0 : alid.hashCode());
		result = prime * result
				+ ((bornDate == null) ? 0 : bornDate.hashCode());
		result = prime * result + ((custId == null) ? 0 : custId.hashCode());
		result = prime * result
				+ ((decisionRight == null) ? 0 : decisionRight.hashCode());
		result = prime * result
				+ ((defaultId == null) ? 0 : defaultId.hashCode());
		result = prime * result + ((duty == null) ? 0 : duty.hashCode());
		result = prime * result
				+ ((dutyDept == null) ? 0 : dutyDept.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((folk == null) ? 0 : folk.hashCode());
		result = prime * result + ((gainave == null) ? 0 : gainave.hashCode());
		result = prime * result + ((idCard == null) ? 0 : idCard.hashCode());
		result = prime * result
				+ ((isMainLinkMan == null) ? 0 : isMainLinkMan.hashCode());
		result = prime * result
				+ ((linkmanType == null) ? 0 : linkmanType.hashCode());
		result = prime * result
				+ ((mobilePhone == null) ? 0 : mobilePhone.hashCode());
		result = prime * result + ((msn == null) ? 0 : msn.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((nativePlace == null) ? 0 : nativePlace.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime
				* result
				+ ((onlineBusinessId == null) ? 0 : onlineBusinessId.hashCode());
		result = prime * result
				+ ((personLove == null) ? 0 : personLove.hashCode());
		result = prime * result
				+ ((qqNumber == null) ? 0 : qqNumber.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((taobId == null) ? 0 : taobId.hashCode());
		result = prime * result
				+ ((telPhone == null) ? 0 : telPhone.hashCode());
		result = prime * result
				+ ((versionId == null) ? 0 : versionId.hashCode());
		result = prime * result + ((ww == null) ? 0 : ww.hashCode());
		result = prime * result
				+ ((youshangId == null) ? 0 : youshangId.hashCode());
		return result;
	}
}
