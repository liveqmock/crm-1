package com.deppon.crm.module.customer.shared.domain;

import java.util.Date;
import java.util.List;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @description : 潜在客户
 * @author : 罗典
 * @createTime : 2012-2-27 14:29
 * */
public class PotentialCustomer extends BaseEntity {

	private static final long serialVersionUID = 2473112006171327221L;
	// 客户姓名
	private String custName;
	// 行业
	private String trade;
	// 二级行业
	private String secondTrade;
	// 客户来源
	private String custbase;
	// 会员编码
	private String memberNo;
	// 联系人姓名
	private String linkManName;
	// 联系人手机
	private String linkManMobile;
	// 联系人固话
	private String linkManPhone;
	// 职位
	private String post;
	// 商机状态
	private String bussinesState;
	//省份Id
	private String provinceId;
	//省份名称
	private String province;
	// 城市Id
	private String cityId;
	// 城市名称
	private String city;
	//区县Id
	private String areaId;
	//区县名称
	private String area;
	// 地址
	private String address;
	// 最近合作物流公司
	private String recentcoop;
	// 合作意向
	private String coopIntention;
	// 货量潜力
	private String volumePotential;
	// 走货情况(最近合作物流公司)
	private String recentGoods;
	// 客户需求
	private String custNeed;
	// 回访次数
	private Integer reviTimes;
	// 最后回访时间
	private Date finalreviTime;
	// 开发状态
	private String developState;
	// 是否失效 0为启用，1为失效，默认为0
	private int isCancel = 0;
	// 日程时间
	private Date programmeTime;
	// 潜客信息状态
	private String infoState;
	// 所属部门
	private String deptId;
	// 所属部门
	private String deptName;
	// 会员ID
	private String memberId;
	// 散客ID
	private String scatterId;
	// 客户类型 潜客：PC_CUSTOMER，散客：SC_CUSTOMER，潜散客：PC_SC
	private String custType;

	//增加版本控制
	private String version;
	
	//是否锁定,如果散客或者潜在客户有存在审批中的工作流，则isLock为1，否则为0
	private boolean isLock;
	//客户标签
	private List<CustLabel> custLabels;
	
	//X轴
	private String lat;
	//Y轴
	private String lng;
	//性别
	private String sex;
	// 客户属性
	private String custNature;
	//客户类别
	private String custCategory;
	//客户类型
	private String customerType;
	/**
	 *@user pgj
	 *2014-3-20下午2:14:22
	 * @return sex : return the property sex.
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex : set the property sex.
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 *@user pgj
	 *2014-3-20下午2:14:22
	 * @return custNature : return the property custNature.
	 */
	public String getCustNature() {
		return custNature;
	}
	/**
	 * @param custNature : set the property custNature.
	 */
	public void setCustNature(String custNature) {
		this.custNature = custNature;
	}
	/**
	 *@user pgj
	 *2014-3-20下午2:14:22
	 * @return custCategory : return the property custCategory.
	 */
	public String getCustCategory() {
		return custCategory;
	}
	/**
	 * @param custCategory : set the property custCategory.
	 */
	public void setCustCategory(String custCategory) {
		this.custCategory = custCategory;
	}
	/**
	 *@user pgj
	 *2014-3-20下午2:14:22
	 * @return customerType : return the property customerType.
	 */
	public String getCustomerType() {
		return customerType;
	}
	/**
	 * @param customerType : set the property customerType.
	 */
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
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
	 * Description:custName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * <p>
	 * Description:custName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * <p>
	 * Description:trade<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getTrade() {
		return trade;
	}
	/**
	 * <p>
	 * Description:trade<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTrade(String trade) {
		this.trade = trade;
	}
	/**
	 * <p>
	 * Description:custbase<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustbase() {
		return custbase;
	}
	/**
	 * <p>
	 * Description:custbase<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustbase(String custbase) {
		this.custbase = custbase;
	}
	/**
	 * <p>
	 * Description:memberNo<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getMemberNo() {
		return memberNo;
	}
	/**
	 * <p>
	 * Description:memberNo<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	/**
	 * <p>
	 * Description:linkManName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLinkManName() {
		return linkManName;
	}
	/**
	 * <p>
	 * Description:linkManName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}
	/**
	 * <p>
	 * Description:linkManMobile<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLinkManMobile() {
		return linkManMobile;
	}
	/**
	 * <p>
	 * Description:linkManMobile<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLinkManMobile(String linkManMobile) {
		this.linkManMobile = linkManMobile;
	}
	/**
	 * <p>
	 * Description:linkManPhone<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public String getLinkManPhone() {
		return linkManPhone;
	}



	/**
	 * <p>
	 * Description:linkManPhone<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public void setLinkManPhone(String linkManPhone) {
		this.linkManPhone = linkManPhone;
	}

	/**
	 * <p>
	 * Description:post<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public String getPost() {
		return post;
	}
	/**
	 * <p>
	 * Description:post<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public void setPost(String post) {
		this.post = post;
	}
	
	/**
	 * <p>
	 * Description:bussinesState<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public String getBussinesState() {
		return bussinesState;
	}

	/**
	 * <p>
	 * Description:bussinesState<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public void setBussinesState(String bussinesState) {
		this.bussinesState = bussinesState;
	}


	/**
	 * <p>
	 * Description:address<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public String getAddress() {
		return address;
	}

	/**
	 * <p>
	 * Description:address<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * <p>
	 * Description:recentcoop<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public String getRecentcoop() {
		return recentcoop;
	}

	/**
	 * <p>
	 * Description:recentcoop<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public void setRecentcoop(String recentcoop) {
		this.recentcoop = recentcoop;
	}

	/**
	 * <p>
	 * Description:coopIntention<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public String getCoopIntention() {
		return coopIntention;
	}

	/**
	 * <p>
	 * Description:coopIntention<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public void setCoopIntention(String coopIntention) {
		this.coopIntention = coopIntention;
	}

	/**
	 * <p>
	 * Description:volumePotential<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public String getVolumePotential() {
		return volumePotential;
	}


	/**
	 * <p>
	 * Description:volumePotential<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public void setVolumePotential(String volumePotential) {
		this.volumePotential = volumePotential;
	}

	/**
	 * <p>
	 * Description:recentGoods<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getRecentGoods() {
		return recentGoods;
	}

	/**
	 * <p>
	 * Description:recentGoods<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public void setRecentGoods(String recentGoods) {
		this.recentGoods = recentGoods;
	}

	/**
	 * <p>
	 * Description:custNeed<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustNeed() {
		return custNeed;
	}

	/**
	 * <p>
	 * Description:custNeed<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public void setCustNeed(String custNeed) {
		this.custNeed = custNeed;
	}

	/**
	 * <p>
	 * Description:reviTimes<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Integer getReviTimes() {
		return reviTimes;
	}

	/**
	 * <p>
	 * Description:reviTimes<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setReviTimes(Integer reviTimes) {
		this.reviTimes = reviTimes;
	}

	/**
	 * <p>
	 * Description:finalreviTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public Date getFinalreviTime() {
		return finalreviTime;
	}


	/**
	 * <p>
	 * Description:finalreviTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public void setFinalreviTime(Date finalreviTime) {
		this.finalreviTime = finalreviTime;
	}

	/**
	 * <p>
	 * Description:developState<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public String getDevelopState() {
		return developState;
	}


	/**
	 * <p>
	 * Description:developState<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public void setDevelopState(String developState) {
		this.developState = developState;
	}

	/**
	 * <p>
	 * Description:isCancel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public int getIsCancel() {
		return isCancel;
	}


	/**
	 * <p>
	 * Description:isCancel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public void setIsCancel(int isCancel) {
		this.isCancel = isCancel;
	}

	/**
	 * <p>
	 * Description:programmeTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public Date getProgrammeTime() {
		return programmeTime;
	}

	/**
	 * <p>
	 * Description:programmeTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public void setProgrammeTime(Date programmeTime) {
		this.programmeTime = programmeTime;
	}

	/**
	 * <p>
	 * Description:infoState<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public String getInfoState() {
		return infoState;
	}

	/**
	 * <p>
	 * Description:infoState<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public void setInfoState(String infoState) {
		this.infoState = infoState;
	}

	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public String getDeptId() {
		return deptId;
	}

	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * <p>
	 * Description:deptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * <p>
	 * Description:deptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 *@user pgj
	 *2014-3-12下午2:19:35
	 * @return provinceId : return the property provinceId.
	 */
	public String getProvinceId() {
		return provinceId;
	}
	/**
	 * @param provinceId : set the property provinceId.
	 */
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 *@user pgj
	 *2014-3-12下午2:19:35
	 * @return province : return the property province.
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province : set the property province.
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 *@user pgj
	 *2014-3-12下午2:19:35
	 * @return cityId : return the property cityId.
	 */
	public String getCityId() {
		return cityId;
	}
	/**
	 * @param cityId : set the property cityId.
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	/**
	 *@user pgj
	 *2014-3-12下午2:19:35
	 * @return city : return the property city.
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city : set the property city.
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 *@user pgj
	 *2014-3-12下午2:19:35
	 * @return areaId : return the property areaId.
	 */
	public String getAreaId() {
		return areaId;
	}
	/**
	 * @param areaId : set the property areaId.
	 */
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	/**
	 *@user pgj
	 *2014-3-12下午2:19:35
	 * @return area : return the property area.
	 */
	public String getArea() {
		return area;
	}
	/**
	 * @param area : set the property area.
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * <p>
	 * Description:memberId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */

	public String getMemberId() {
		return memberId;
	}


	/**
	 * <p>
	 * Description:memberId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	/**
	 * <p>
	 * Description:scatterId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getScatterId() {
		return scatterId;
	}
	/**
	 * <p>
	 * Description:scatterId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setScatterId(String scatterId) {
		this.scatterId = scatterId;
	}
	/**
	 * <p>
	 * Description:custType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustType() {
		return custType;
	}
	/**
	 * <p>
	 * Description:custType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}
	/**
	 * <p>
	 * Description:version<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <p>
	 * Description:version<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * <p>
	 * Description:isLock<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public boolean isLock() {
		return isLock;
	}
	/**
	 * <p>
	 * Description:isLock<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLock(boolean isLock) {
		this.isLock = isLock;
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
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((areaId == null) ? 0 : areaId.hashCode());
		result = prime * result + ((bussinesState == null) ? 0 : bussinesState.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((cityId == null) ? 0 : cityId.hashCode());
		result = prime * result + ((coopIntention == null) ? 0 : coopIntention.hashCode());
		result = prime * result + ((custCategory == null) ? 0 : custCategory.hashCode());
		result = prime * result + ((custLabels == null) ? 0 : custLabels.hashCode());
		result = prime * result + ((custName == null) ? 0 : custName.hashCode());
		result = prime * result + ((custNature == null) ? 0 : custNature.hashCode());
		result = prime * result + ((custNeed == null) ? 0 : custNeed.hashCode());
		result = prime * result + ((custType == null) ? 0 : custType.hashCode());
		result = prime * result + ((custbase == null) ? 0 : custbase.hashCode());
		result = prime * result + ((customerType == null) ? 0 : customerType.hashCode());
		result = prime * result + ((deptId == null) ? 0 : deptId.hashCode());
		result = prime * result + ((deptName == null) ? 0 : deptName.hashCode());
		result = prime * result + ((developState == null) ? 0 : developState.hashCode());
		result = prime * result + ((finalreviTime == null) ? 0 : finalreviTime.hashCode());
		result = prime * result + ((infoState == null) ? 0 : infoState.hashCode());
		result = prime * result + isCancel;
		result = prime * result + (isLock ? 1231 : 1237);
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((linkManMobile == null) ? 0 : linkManMobile.hashCode());
		result = prime * result + ((linkManName == null) ? 0 : linkManName.hashCode());
		result = prime * result + ((linkManPhone == null) ? 0 : linkManPhone.hashCode());
		result = prime * result + ((lng == null) ? 0 : lng.hashCode());
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
		result = prime * result + ((memberNo == null) ? 0 : memberNo.hashCode());
		result = prime * result + ((post == null) ? 0 : post.hashCode());
		result = prime * result + ((programmeTime == null) ? 0 : programmeTime.hashCode());
		result = prime * result + ((province == null) ? 0 : province.hashCode());
		result = prime * result + ((provinceId == null) ? 0 : provinceId.hashCode());
		result = prime * result + ((recentGoods == null) ? 0 : recentGoods.hashCode());
		result = prime * result + ((recentcoop == null) ? 0 : recentcoop.hashCode());
		result = prime * result + ((reviTimes == null) ? 0 : reviTimes.hashCode());
		result = prime * result + ((scatterId == null) ? 0 : scatterId.hashCode());
		result = prime * result + ((secondTrade == null) ? 0 : secondTrade.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((trade == null) ? 0 : trade.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		result = prime * result + ((volumePotential == null) ? 0 : volumePotential.hashCode());
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
		PotentialCustomer other = (PotentialCustomer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (areaId == null) {
			if (other.areaId != null)
				return false;
		} else if (!areaId.equals(other.areaId))
			return false;
		if (bussinesState == null) {
			if (other.bussinesState != null)
				return false;
		} else if (!bussinesState.equals(other.bussinesState))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (cityId == null) {
			if (other.cityId != null)
				return false;
		} else if (!cityId.equals(other.cityId))
			return false;
		if (coopIntention == null) {
			if (other.coopIntention != null)
				return false;
		} else if (!coopIntention.equals(other.coopIntention))
			return false;
		if (custCategory == null) {
			if (other.custCategory != null)
				return false;
		} else if (!custCategory.equals(other.custCategory))
			return false;
		if (custLabels == null) {
			if (other.custLabels != null)
				return false;
		} else if (!custLabels.equals(other.custLabels))
			return false;
		if (custName == null) {
			if (other.custName != null)
				return false;
		} else if (!custName.equals(other.custName))
			return false;
		if (custNature == null) {
			if (other.custNature != null)
				return false;
		} else if (!custNature.equals(other.custNature))
			return false;
		if (custNeed == null) {
			if (other.custNeed != null)
				return false;
		} else if (!custNeed.equals(other.custNeed))
			return false;
		if (custType == null) {
			if (other.custType != null)
				return false;
		} else if (!custType.equals(other.custType))
			return false;
		if (custbase == null) {
			if (other.custbase != null)
				return false;
		} else if (!custbase.equals(other.custbase))
			return false;
		if (customerType == null) {
			if (other.customerType != null)
				return false;
		} else if (!customerType.equals(other.customerType))
			return false;
		if (deptId == null) {
			if (other.deptId != null)
				return false;
		} else if (!deptId.equals(other.deptId))
			return false;
		if (deptName == null) {
			if (other.deptName != null)
				return false;
		} else if (!deptName.equals(other.deptName))
			return false;
		if (developState == null) {
			if (other.developState != null)
				return false;
		} else if (!developState.equals(other.developState))
			return false;
		if (finalreviTime == null) {
			if (other.finalreviTime != null)
				return false;
		} else if (!finalreviTime.equals(other.finalreviTime))
			return false;
		if (infoState == null) {
			if (other.infoState != null)
				return false;
		} else if (!infoState.equals(other.infoState))
			return false;
		if (isCancel != other.isCancel)
			return false;
		if (isLock != other.isLock)
			return false;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (linkManMobile == null) {
			if (other.linkManMobile != null)
				return false;
		} else if (!linkManMobile.equals(other.linkManMobile))
			return false;
		if (linkManName == null) {
			if (other.linkManName != null)
				return false;
		} else if (!linkManName.equals(other.linkManName))
			return false;
		if (linkManPhone == null) {
			if (other.linkManPhone != null)
				return false;
		} else if (!linkManPhone.equals(other.linkManPhone))
			return false;
		if (lng == null) {
			if (other.lng != null)
				return false;
		} else if (!lng.equals(other.lng))
			return false;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		if (memberNo == null) {
			if (other.memberNo != null)
				return false;
		} else if (!memberNo.equals(other.memberNo))
			return false;
		if (post == null) {
			if (other.post != null)
				return false;
		} else if (!post.equals(other.post))
			return false;
		if (programmeTime == null) {
			if (other.programmeTime != null)
				return false;
		} else if (!programmeTime.equals(other.programmeTime))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (provinceId == null) {
			if (other.provinceId != null)
				return false;
		} else if (!provinceId.equals(other.provinceId))
			return false;
		if (recentGoods == null) {
			if (other.recentGoods != null)
				return false;
		} else if (!recentGoods.equals(other.recentGoods))
			return false;
		if (recentcoop == null) {
			if (other.recentcoop != null)
				return false;
		} else if (!recentcoop.equals(other.recentcoop))
			return false;
		if (reviTimes == null) {
			if (other.reviTimes != null)
				return false;
		} else if (!reviTimes.equals(other.reviTimes))
			return false;
		if (scatterId == null) {
			if (other.scatterId != null)
				return false;
		} else if (!scatterId.equals(other.scatterId))
			return false;
		if (secondTrade == null) {
			if (other.secondTrade != null)
				return false;
		} else if (!secondTrade.equals(other.secondTrade))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (trade == null) {
			if (other.trade != null)
				return false;
		} else if (!trade.equals(other.trade))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		if (volumePotential == null) {
			if (other.volumePotential != null)
				return false;
		} else if (!volumePotential.equals(other.volumePotential))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PotentialCustomer [custName=" + custName + ", trade=" + trade
				+ ", custbase=" + custbase + ", memberNo=" + memberNo
				+ ", linkManName=" + linkManName + ", linkManMobile="
				+ linkManMobile + ", linkManPhone=" + linkManPhone + ", post="
				+ post + ", bussinesState=" + bussinesState + ", city=" + cityId
				+ ", cityName=" + city + ", address=" + address
				+ ", recentcoop=" + recentcoop + ", coopIntention="
				+ coopIntention + ", volumePotential=" + volumePotential
				+ ", recentGoods=" + recentGoods + ", custNeed=" + custNeed
				+ ", reviTimes=" + reviTimes + ", finalreviTime="
				+ finalreviTime + ", developState=" + developState
				+ ", isCancel=" + isCancel + ", programmeTime=" + programmeTime
				+ ", infoState=" + infoState + ", deptId=" + deptId
				+ ", memberId=" + memberId + ", scatterId=" + scatterId
				+ ", custType=" + custType + "]";
	}
	
	/**
	 *@user pgj
	 *2013-7-18下午2:14:57
	 * @return secondTrade : return the property secondTrade.
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

	public List<CustLabel> getCustLabels() {
		return custLabels;
	}
	public void setCustLabels(List<CustLabel> custLabels) {
		this.custLabels = custLabels;
	}

}
