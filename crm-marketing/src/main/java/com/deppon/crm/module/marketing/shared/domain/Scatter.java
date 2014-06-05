package com.deppon.crm.module.marketing.shared.domain;


import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;
/**
 * 
 * <p>
 * 散客信息
 * </p>
 * @title Scatter.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author 苏玉军
 * @version 0.1 2013-1-28
 */
public class Scatter extends BaseEntity{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7748976568264294943L;
	//客户名称
	private String custname;
	//会员编码
	private String memberno;
	//主要联系人
	private String mainlinkmanid;
	//行业
	private String trade;
	//身份证号
	private String idnumber;
	//公司规模
	private String companysize;
	//公司性质
	private String companynature;
	//税务登记号
	private String taxregistno;
	//职位
	private String office;
	//城市
	private String city;
	//客户性质
	private String custproperty;
	//出发部门
	private String leadeptid;
	//到达部门
	private String arrdeptid;
	//商机状态
	private String bussinessstate;
	//客户性质
	private String custnature;
	//备注
	private String remark;
	//最近合作物流公司
	private String recentcoop;
	//合作意向
	private String coopintention;
	//货量潜力
	private String volumepotential;
	//走货情况
	private String recentgoods;
	//客户需求
	private String custneed;
	//开发状态
	private String developstate;
	//最后回访时间
	private Date finalrevitime;
	//回访次数
	private String revitimes;
	//客户类型
	private String custtype;
	//客户来源
	private String custbase;
	//维护人
	private String prehuman;
	//联系人姓名
	private String linkmanname;
	//联系人手机号
	private String linkmanmobile;
	//联系人固话
	private String linkmanphone;
	//潜客信息状态
	private String infostate;
	//所属部门ID
	private String deptid;
	//创建人
	private String creator;
	//创建时间
	private Date creattime;
	//最后更新时间
	private Date lastupdatetime;
	//最后修改人
	private String lastupdator;
	//地址
	private String address;
	//是否失效
	private String iscancel;
	//散客ID
	private String scatterid;
	//潜客ID
	private String memberid;
	/**
	 * @return custname : return the property custname.
	 */
	public String getCustname() {
		return custname;
	}
	/**
	 * @param custname : set the property custname.
	 */
	public void setCustname(String custname) {
		this.custname = custname;
	}
	/**
	 * @return memberno : return the property memberno.
	 */
	public String getMemberno() {
		return memberno;
	}
	/**
	 * @param memberno : set the property memberno.
	 */
	public void setMemberno(String memberno) {
		this.memberno = memberno;
	}
	/**
	 * @return mainlinkmanid : return the property mainlinkmanid.
	 */
	public String getMainlinkmanid() {
		return mainlinkmanid;
	}
	/**
	 * @param mainlinkmanid : set the property mainlinkmanid.
	 */
	public void setMainlinkmanid(String mainlinkmanid) {
		this.mainlinkmanid = mainlinkmanid;
	}
	/**
	 * @return trade : return the property trade.
	 */
	public String getTrade() {
		return trade;
	}
	/**
	 * @param trade : set the property trade.
	 */
	public void setTrade(String trade) {
		this.trade = trade;
	}
	/**
	 * @return idnumber : return the property idnumber.
	 */
	public String getIdnumber() {
		return idnumber;
	}
	/**
	 * @param idnumber : set the property idnumber.
	 */
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	/**
	 * @return companysize : return the property companysize.
	 */
	public String getCompanysize() {
		return companysize;
	}
	/**
	 * @param companysize : set the property companysize.
	 */
	public void setCompanysize(String companysize) {
		this.companysize = companysize;
	}
	/**
	 * @return companynature : return the property companynature.
	 */
	public String getCompanynature() {
		return companynature;
	}
	/**
	 * @param companynature : set the property companynature.
	 */
	public void setCompanynature(String companynature) {
		this.companynature = companynature;
	}
	/**
	 * @return taxregistno : return the property taxregistno.
	 */
	public String getTaxregistno() {
		return taxregistno;
	}
	/**
	 * @param taxregistno : set the property taxregistno.
	 */
	public void setTaxregistno(String taxregistno) {
		this.taxregistno = taxregistno;
	}
	/**
	 * @return office : return the property office.
	 */
	public String getOffice() {
		return office;
	}
	/**
	 * @param office : set the property office.
	 */
	public void setOffice(String office) {
		this.office = office;
	}
	/**
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
	 * @return custproperty : return the property custproperty.
	 */
	public String getCustproperty() {
		return custproperty;
	}
	/**
	 * @param custproperty : set the property custproperty.
	 */
	public void setCustproperty(String custproperty) {
		this.custproperty = custproperty;
	}
	/**
	 * @return leadeptid : return the property leadeptid.
	 */
	public String getLeadeptid() {
		return leadeptid;
	}
	/**
	 * @param leadeptid : set the property leadeptid.
	 */
	public void setLeadeptid(String leadeptid) {
		this.leadeptid = leadeptid;
	}
	/**
	 * @return arrdeptid : return the property arrdeptid.
	 */
	public String getArrdeptid() {
		return arrdeptid;
	}
	/**
	 * @param arrdeptid : set the property arrdeptid.
	 */
	public void setArrdeptid(String arrdeptid) {
		this.arrdeptid = arrdeptid;
	}
	/**
	 * @return bussinessstate : return the property bussinessstate.
	 */
	public String getBussinessstate() {
		return bussinessstate;
	}
	/**
	 * @param bussinessstate : set the property bussinessstate.
	 */
	public void setBussinessstate(String bussinessstate) {
		this.bussinessstate = bussinessstate;
	}
	/**
	 * @return custnature : return the property custnature.
	 */
	public String getCustnature() {
		return custnature;
	}
	/**
	 * @param custnature : set the property custnature.
	 */
	public void setCustnature(String custnature) {
		this.custnature = custnature;
	}
	/**
	 * @return remark : return the property remark.
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark : set the property remark.
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return recentcoop : return the property recentcoop.
	 */
	public String getRecentcoop() {
		return recentcoop;
	}
	/**
	 * @param recentcoop : set the property recentcoop.
	 */
	public void setRecentcoop(String recentcoop) {
		this.recentcoop = recentcoop;
	}
	/**
	 * @return coopintention : return the property coopintention.
	 */
	public String getCoopintention() {
		return coopintention;
	}
	/**
	 * @param coopintention : set the property coopintention.
	 */
	public void setCoopintention(String coopintention) {
		this.coopintention = coopintention;
	}
	/**
	 * @return volumepotential : return the property volumepotential.
	 */
	public String getVolumepotential() {
		return volumepotential;
	}
	/**
	 * @param volumepotential : set the property volumepotential.
	 */
	public void setVolumepotential(String volumepotential) {
		this.volumepotential = volumepotential;
	}
	/**
	 * @return recentgoods : return the property recentgoods.
	 */
	public String getRecentgoods() {
		return recentgoods;
	}
	/**
	 * @param recentgoods : set the property recentgoods.
	 */
	public void setRecentgoods(String recentgoods) {
		this.recentgoods = recentgoods;
	}
	/**
	 * @return custneed : return the property custneed.
	 */
	public String getCustneed() {
		return custneed;
	}
	/**
	 * @param custneed : set the property custneed.
	 */
	public void setCustneed(String custneed) {
		this.custneed = custneed;
	}
	/**
	 * @return developstate : return the property developstate.
	 */
	public String getDevelopstate() {
		return developstate;
	}
	/**
	 * @param developstate : set the property developstate.
	 */
	public void setDevelopstate(String developstate) {
		this.developstate = developstate;
	}
	/**
	 * @return finalrevitime : return the property finalrevitime.
	 */
	public Date getFinalrevitime() {
		return finalrevitime;
	}
	/**
	 * @param finalrevitime : set the property finalrevitime.
	 */
	public void setFinalrevitime(Date finalrevitime) {
		this.finalrevitime = finalrevitime;
	}
	/**
	 * @return revitimes : return the property revitimes.
	 */
	public String getRevitimes() {
		return revitimes;
	}
	/**
	 * @param revitimes : set the property revitimes.
	 */
	public void setRevitimes(String revitimes) {
		this.revitimes = revitimes;
	}
	/**
	 * @return custtype : return the property custtype.
	 */
	public String getCusttype() {
		return custtype;
	}
	/**
	 * @param custtype : set the property custtype.
	 */
	public void setCusttype(String custtype) {
		this.custtype = custtype;
	}
	/**
	 * @return custbase : return the property custbase.
	 */
	public String getCustbase() {
		return custbase;
	}
	/**
	 * @param custbase : set the property custbase.
	 */
	public void setCustbase(String custbase) {
		this.custbase = custbase;
	}
	/**
	 * @return prehuman : return the property prehuman.
	 */
	public String getPrehuman() {
		return prehuman;
	}
	/**
	 * @param prehuman : set the property prehuman.
	 */
	public void setPrehuman(String prehuman) {
		this.prehuman = prehuman;
	}
	/**
	 * @return linkmanname : return the property linkmanname.
	 */
	public String getLinkmanname() {
		return linkmanname;
	}
	/**
	 * @param linkmanname : set the property linkmanname.
	 */
	public void setLinkmanname(String linkmanname) {
		this.linkmanname = linkmanname;
	}
	/**
	 * @return linkmanmobile : return the property linkmanmobile.
	 */
	public String getLinkmanmobile() {
		return linkmanmobile;
	}
	/**
	 * @param linkmanmobile : set the property linkmanmobile.
	 */
	public void setLinkmanmobile(String linkmanmobile) {
		this.linkmanmobile = linkmanmobile;
	}
	/**
	 * @return linkmanphone : return the property linkmanphone.
	 */
	public String getLinkmanphone() {
		return linkmanphone;
	}
	/**
	 * @param linkmanphone : set the property linkmanphone.
	 */
	public void setLinkmanphone(String linkmanphone) {
		this.linkmanphone = linkmanphone;
	}
	/**
	 * @return infostate : return the property infostate.
	 */
	public String getInfostate() {
		return infostate;
	}
	/**
	 * @param infostate : set the property infostate.
	 */
	public void setInfostate(String infostate) {
		this.infostate = infostate;
	}
	/**
	 * @return deptid : return the property deptid.
	 */
	public String getDeptid() {
		return deptid;
	}
	/**
	 * @param deptid : set the property deptid.
	 */
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	/**
	 * @return creator : return the property creator.
	 */
	public String getCreator() {
		return creator;
	}
	/**
	 * @param creator : set the property creator.
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * @return creattime : return the property creattime.
	 */
	public Date getCreattime() {
		return creattime;
	}
	/**
	 * @param creattime : set the property creattime.
	 */
	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}
	/**
	 * @return lastupdatetime : return the property lastupdatetime.
	 */
	public Date getLastupdatetime() {
		return lastupdatetime;
	}
	/**
	 * @param lastupdatetime : set the property lastupdatetime.
	 */
	public void setLastupdatetime(Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}
	/**
	 * @return lastupdator : return the property lastupdator.
	 */
	public String getLastupdator() {
		return lastupdator;
	}
	/**
	 * @param lastupdator : set the property lastupdator.
	 */
	public void setLastupdator(String lastupdator) {
		this.lastupdator = lastupdator;
	}
	/**
	 * @return address : return the property address.
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address : set the property address.
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return iscancel : return the property iscancel.
	 */
	public String getIscancel() {
		return iscancel;
	}
	/**
	 * @param iscancel : set the property iscancel.
	 */
	public void setIscancel(String iscancel) {
		this.iscancel = iscancel;
	}
	/**
	 * @return scatterid : return the property scatterid.
	 */
	public String getScatterid() {
		return scatterid;
	}
	/**
	 * @param scatterid : set the property scatterid.
	 */
	public void setScatterid(String scatterid) {
		this.scatterid = scatterid;
	}
	/**
	 * @return memberid : return the property memberid.
	 */
	public String getMemberid() {
		return memberid;
	}
	/**
	 * @param memberid : set the property memberid.
	 */
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	
}