package com.deppon.crm.module.marketing.server.action;

import java.util.Date;

public class CustomerVo {
	/**
	 * 维护计划新增查询新增字段
	 */

	// 联系人属性
	private String linkManProperty;
	// 联系人编码
	private String linkManCode;
	// 投诉数量
	private int complaintsNum;
	// 会员等级
	private String memberLevel;
	// 已制定计划数量
	private int planNum;
	// 已制定计划名称
	private String ftopic;
	// 联系人Id
	private String contactId;
	private String planId;
	// 客户等级
	private String degree;
	// 未完成计划数量
	private int unfinishedPlanNum;
	// 回访次数
	private int visitNum;
	private Date lastVistiTime;
	// 执行人
	private String executeUserName;
	// 客户编码
	private String memberNo;
	// id
	private String id;
	// 客户姓名
	private String custName;
	// 行业
	private String trade;
	// 客户来源
	private String custbase;

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
	// 城市Id
	private String city;
	// 城市名称
	private String cityName;
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
	private int reviTimes = 0;
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
	// 会员ID
	private String memberId;
	// 散客ID
	private String scatterId;
	// 客户类型
	private String custType;
	// 日程类型
	private String scheduleType;
	// 身份证号
	private String idNumber;
	// 公司规模
	private String companySize;
	// 公司性质
	private String companyNature;
	// 税务登记号
	private String taxregistNo;
	// 客户属性
	private String custProperty;
	// 客户性质
	private String custNature;
	// 备注
	private String remark;
	// 出发部门
	private String leaDeptId;
	// 出发部门名称
	private String leaDeptName;
	// 到达部门名称
	private String arrDeptName;
	// 到达部门
	private String arrDeptId;
	// 维护人
	private String prehuMan;
	// 创建开始时间
	private Date beginTime;
	//创建结束时间
	private Date overTime;
	//查询开始时间
	private Date queryBeginTime;
	//查询结束时间
	private Date queryOverTime;
	// 发货量
	private double beginShipWeight;
	//结束发货量
	private double overShipWeight;
	// 发货票数
	private Integer beginShipVotes;
	//结束发货票数
	private Integer overShipVotes;
	// 发货金额
	private double beginShipAmount;
	//结束发货金额
	private double overShipAmount;
	// 到达货量
	private double beginArrivalWeight;
	//结束到达货量
	private double overArrivalWeight;
	// 到达票数
	private Integer beginArrivalVotes;
	//结束到达票数
	private Integer overArrivalVotes;
	// 到达金额
	private double beginArrivalAmount;
	//结束到达金额
	private double overArrivalAmount;

	// 客户编码数组
	private String[] custNumbers;
	// 联系人数组
	private String[] contactIds;
	//联系人姓名
	private String contact;
	//未完成计划名称
	private String unfinishedPlanName;
	//营销备注
	private String marketRemark ;
	//客户二级行业
	private String secondTrade;
	// 业务类别 标识客户是零担，快递还是快递和零担
	private String businessType;
	//客户开发阶段 需求确认、意向洽谈、持续培养、开始发货
	private String developmentPhase;
	//是否继续营销 0 否 1是 
	private String continueMark;
	//合作意向 潜散客属性 
	private String cooperationIntention;
	//是否疑似重复客户 0：否，1：是
	private String repeatCust;
	//疑似重复客户所在部门
	private String repeatCustDeptName;
	// 商机状态
	private String businessOpportunityStatus;
	//客户id数组
	private String [] custIds;
	/**
	 * @return linkManProperty : return the property linkManProperty.  
	 */
	public String getLinkManProperty() {
		return linkManProperty;
	}
	/**
	 * @param linkManProperty : set the property linkManProperty. 
	 */
	public void setLinkManProperty(String linkManProperty) {
		this.linkManProperty = linkManProperty;
	}
	/**
	 * @return linkManCode : return the property linkManCode.  
	 */
	public String getLinkManCode() {
		return linkManCode;
	}
	/**
	 * @param linkManCode : set the property linkManCode. 
	 */
	public void setLinkManCode(String linkManCode) {
		this.linkManCode = linkManCode;
	}
	/**
	 * @return complaintsNum : return the property complaintsNum.  
	 */
	public int getComplaintsNum() {
		return complaintsNum;
	}
	/**
	 * @param complaintsNum : set the property complaintsNum. 
	 */
	public void setComplaintsNum(int complaintsNum) {
		this.complaintsNum = complaintsNum;
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
	 * @return planNum : return the property planNum.  
	 */
	public int getPlanNum() {
		return planNum;
	}
	/**
	 * @param planNum : set the property planNum. 
	 */
	public void setPlanNum(int planNum) {
		this.planNum = planNum;
	}
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
	 * @return planId : return the property planId.  
	 */
	public String getPlanId() {
		return planId;
	}
	/**
	 * @param planId : set the property planId. 
	 */
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	/**
	 * @return degree : return the property degree.  
	 */
	public String getDegree() {
		return degree;
	}
	/**
	 * @param degree : set the property degree. 
	 */
	public void setDegree(String degree) {
		this.degree = degree;
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
	/**
	 * @return executeUserName : return the property executeUserName.  
	 */
	public String getExecuteUserName() {
		return executeUserName;
	}
	/**
	 * @param executeUserName : set the property executeUserName. 
	 */
	public void setExecuteUserName(String executeUserName) {
		this.executeUserName = executeUserName;
	}
	/**
	 * @return memberNo : return the property memberNo.  
	 */
	public String getMemberNo() {
		return memberNo;
	}
	/**
	 * @param memberNo : set the property memberNo. 
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	/**
	 * @return id : return the property id.  
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id : set the property id. 
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return cityName : return the property cityName.  
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * @param cityName : set the property cityName. 
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
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
	 * @return recentGoods : return the property recentGoods.  
	 */
	public String getRecentGoods() {
		return recentGoods;
	}
	/**
	 * @param recentGoods : set the property recentGoods. 
	 */
	public void setRecentGoods(String recentGoods) {
		this.recentGoods = recentGoods;
	}
	/**
	 * @return custNeed : return the property custNeed.  
	 */
	public String getCustNeed() {
		return custNeed;
	}
	/**
	 * @param custNeed : set the property custNeed. 
	 */
	public void setCustNeed(String custNeed) {
		this.custNeed = custNeed;
	}
	/**
	 * @return reviTimes : return the property reviTimes.  
	 */
	public int getReviTimes() {
		return reviTimes;
	}
	/**
	 * @param reviTimes : set the property reviTimes. 
	 */
	public void setReviTimes(int reviTimes) {
		this.reviTimes = reviTimes;
	}
	/**
	 * @return finalreviTime : return the property finalreviTime.  
	 */
	public Date getFinalreviTime() {
		return finalreviTime;
	}
	/**
	 * @param finalreviTime : set the property finalreviTime. 
	 */
	public void setFinalreviTime(Date finalreviTime) {
		this.finalreviTime = finalreviTime;
	}
	/**
	 * @return developState : return the property developState.  
	 */
	public String getDevelopState() {
		return developState;
	}
	/**
	 * @param developState : set the property developState. 
	 */
	public void setDevelopState(String developState) {
		this.developState = developState;
	}
	/**
	 * @return isCancel : return the property isCancel.  
	 */
	public int getIsCancel() {
		return isCancel;
	}
	/**
	 * @param isCancel : set the property isCancel. 
	 */
	public void setIsCancel(int isCancel) {
		this.isCancel = isCancel;
	}
	/**
	 * @return programmeTime : return the property programmeTime.  
	 */
	public Date getProgrammeTime() {
		return programmeTime;
	}
	/**
	 * @param programmeTime : set the property programmeTime. 
	 */
	public void setProgrammeTime(Date programmeTime) {
		this.programmeTime = programmeTime;
	}
	/**
	 * @return infoState : return the property infoState.  
	 */
	public String getInfoState() {
		return infoState;
	}
	/**
	 * @param infoState : set the property infoState. 
	 */
	public void setInfoState(String infoState) {
		this.infoState = infoState;
	}
	/**
	 * @return deptId : return the property deptId.  
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId : set the property deptId. 
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
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
	 * @return custType : return the property custType.  
	 */
	public String getCustType() {
		return custType;
	}
	/**
	 * @param custType : set the property custType. 
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}
	/**
	 * @return scheduleType : return the property scheduleType.  
	 */
	public String getScheduleType() {
		return scheduleType;
	}
	/**
	 * @param scheduleType : set the property scheduleType. 
	 */
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}
	/**
	 * @return idNumber : return the property idNumber.  
	 */
	public String getIdNumber() {
		return idNumber;
	}
	/**
	 * @param idNumber : set the property idNumber. 
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	/**
	 * @return companySize : return the property companySize.  
	 */
	public String getCompanySize() {
		return companySize;
	}
	/**
	 * @param companySize : set the property companySize. 
	 */
	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}
	/**
	 * @return companyNature : return the property companyNature.  
	 */
	public String getCompanyNature() {
		return companyNature;
	}
	/**
	 * @param companyNature : set the property companyNature. 
	 */
	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature;
	}
	/**
	 * @return taxregistNo : return the property taxregistNo.  
	 */
	public String getTaxregistNo() {
		return taxregistNo;
	}
	/**
	 * @param taxregistNo : set the property taxregistNo. 
	 */
	public void setTaxregistNo(String taxregistNo) {
		this.taxregistNo = taxregistNo;
	}
	/**
	 * @return custProperty : return the property custProperty.  
	 */
	public String getCustProperty() {
		return custProperty;
	}
	/**
	 * @param custProperty : set the property custProperty. 
	 */
	public void setCustProperty(String custProperty) {
		this.custProperty = custProperty;
	}
	/**
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
	 * @return leaDeptId : return the property leaDeptId.  
	 */
	public String getLeaDeptId() {
		return leaDeptId;
	}
	/**
	 * @param leaDeptId : set the property leaDeptId. 
	 */
	public void setLeaDeptId(String leaDeptId) {
		this.leaDeptId = leaDeptId;
	}
	/**
	 * @return leaDeptName : return the property leaDeptName.  
	 */
	public String getLeaDeptName() {
		return leaDeptName;
	}
	/**
	 * @param leaDeptName : set the property leaDeptName. 
	 */
	public void setLeaDeptName(String leaDeptName) {
		this.leaDeptName = leaDeptName;
	}
	/**
	 * @return arrDeptName : return the property arrDeptName.  
	 */
	public String getArrDeptName() {
		return arrDeptName;
	}
	/**
	 * @param arrDeptName : set the property arrDeptName. 
	 */
	public void setArrDeptName(String arrDeptName) {
		this.arrDeptName = arrDeptName;
	}
	/**
	 * @return arrDeptId : return the property arrDeptId.  
	 */
	public String getArrDeptId() {
		return arrDeptId;
	}
	/**
	 * @param arrDeptId : set the property arrDeptId. 
	 */
	public void setArrDeptId(String arrDeptId) {
		this.arrDeptId = arrDeptId;
	}
	/**
	 * @return prehuMan : return the property prehuMan.  
	 */
	public String getPrehuMan() {
		return prehuMan;
	}
	/**
	 * @param prehuMan : set the property prehuMan. 
	 */
	public void setPrehuMan(String prehuMan) {
		this.prehuMan = prehuMan;
	}
	/**
	 * @return beginTime : return the property beginTime.  
	 */
	public Date getBeginTime() {
		return beginTime;
	}
	/**
	 * @param beginTime : set the property beginTime. 
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * @return overTime : return the property overTime.  
	 */
	public Date getOverTime() {
		return overTime;
	}
	/**
	 * @param overTime : set the property overTime. 
	 */
	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}
	/**
	 * @return queryBeginTime : return the property queryBeginTime.  
	 */
	public Date getQueryBeginTime() {
		return queryBeginTime;
	}
	/**
	 * @param queryBeginTime : set the property queryBeginTime. 
	 */
	public void setQueryBeginTime(Date queryBeginTime) {
		this.queryBeginTime = queryBeginTime;
	}
	/**
	 * @return queryOverTime : return the property queryOverTime.  
	 */
	public Date getQueryOverTime() {
		return queryOverTime;
	}
	/**
	 * @param queryOverTime : set the property queryOverTime. 
	 */
	public void setQueryOverTime(Date queryOverTime) {
		this.queryOverTime = queryOverTime;
	}
	/**
	 * @return beginShipWeight : return the property beginShipWeight.  
	 */
	public double getBeginShipWeight() {
		return beginShipWeight;
	}
	/**
	 * @param beginShipWeight : set the property beginShipWeight. 
	 */
	public void setBeginShipWeight(double beginShipWeight) {
		this.beginShipWeight = beginShipWeight;
	}
	/**
	 * @return overShipWeight : return the property overShipWeight.  
	 */
	public double getOverShipWeight() {
		return overShipWeight;
	}
	/**
	 * @param overShipWeight : set the property overShipWeight. 
	 */
	public void setOverShipWeight(double overShipWeight) {
		this.overShipWeight = overShipWeight;
	}
	/**
	 * @return beginShipVotes : return the property beginShipVotes.  
	 */
	public Integer getBeginShipVotes() {
		return beginShipVotes;
	}
	/**
	 * @param beginShipVotes : set the property beginShipVotes. 
	 */
	public void setBeginShipVotes(Integer beginShipVotes) {
		this.beginShipVotes = beginShipVotes;
	}
	/**
	 * @return overShipVotes : return the property overShipVotes.  
	 */
	public Integer getOverShipVotes() {
		return overShipVotes;
	}
	/**
	 * @param overShipVotes : set the property overShipVotes. 
	 */
	public void setOverShipVotes(Integer overShipVotes) {
		this.overShipVotes = overShipVotes;
	}
	/**
	 * @return beginShipAmount : return the property beginShipAmount.  
	 */
	public double getBeginShipAmount() {
		return beginShipAmount;
	}
	/**
	 * @param beginShipAmount : set the property beginShipAmount. 
	 */
	public void setBeginShipAmount(double beginShipAmount) {
		this.beginShipAmount = beginShipAmount;
	}
	/**
	 * @return overShipAmount : return the property overShipAmount.  
	 */
	public double getOverShipAmount() {
		return overShipAmount;
	}
	/**
	 * @param overShipAmount : set the property overShipAmount. 
	 */
	public void setOverShipAmount(double overShipAmount) {
		this.overShipAmount = overShipAmount;
	}
	/**
	 * @return beginArrivalWeight : return the property beginArrivalWeight.  
	 */
	public double getBeginArrivalWeight() {
		return beginArrivalWeight;
	}
	/**
	 * @param beginArrivalWeight : set the property beginArrivalWeight. 
	 */
	public void setBeginArrivalWeight(double beginArrivalWeight) {
		this.beginArrivalWeight = beginArrivalWeight;
	}
	/**
	 * @return overArrivalWeight : return the property overArrivalWeight.  
	 */
	public double getOverArrivalWeight() {
		return overArrivalWeight;
	}
	/**
	 * @param overArrivalWeight : set the property overArrivalWeight. 
	 */
	public void setOverArrivalWeight(double overArrivalWeight) {
		this.overArrivalWeight = overArrivalWeight;
	}
	/**
	 * @return beginArrivalVotes : return the property beginArrivalVotes.  
	 */
	public Integer getBeginArrivalVotes() {
		return beginArrivalVotes;
	}
	/**
	 * @param beginArrivalVotes : set the property beginArrivalVotes. 
	 */
	public void setBeginArrivalVotes(Integer beginArrivalVotes) {
		this.beginArrivalVotes = beginArrivalVotes;
	}
	/**
	 * @return overArrivalVotes : return the property overArrivalVotes.  
	 */
	public Integer getOverArrivalVotes() {
		return overArrivalVotes;
	}
	/**
	 * @param overArrivalVotes : set the property overArrivalVotes. 
	 */
	public void setOverArrivalVotes(Integer overArrivalVotes) {
		this.overArrivalVotes = overArrivalVotes;
	}
	/**
	 * @return beginArrivalAmount : return the property beginArrivalAmount.  
	 */
	public double getBeginArrivalAmount() {
		return beginArrivalAmount;
	}
	/**
	 * @param beginArrivalAmount : set the property beginArrivalAmount. 
	 */
	public void setBeginArrivalAmount(double beginArrivalAmount) {
		this.beginArrivalAmount = beginArrivalAmount;
	}
	/**
	 * @return overArrivalAmount : return the property overArrivalAmount.  
	 */
	public double getOverArrivalAmount() {
		return overArrivalAmount;
	}
	/**
	 * @param overArrivalAmount : set the property overArrivalAmount. 
	 */
	public void setOverArrivalAmount(double overArrivalAmount) {
		this.overArrivalAmount = overArrivalAmount;
	}
	/**
	 * @return custNumbers : return the property custNumbers.  
	 */
	public String[] getCustNumbers() {
		return custNumbers;
	}
	/**
	 * @param custNumbers : set the property custNumbers. 
	 */
	public void setCustNumbers(String[] custNumbers) {
		this.custNumbers = custNumbers;
	}
	/**
	 * @return contactIds : return the property contactIds.  
	 */
	public String[] getContactIds() {
		return contactIds;
	}
	/**
	 * @param contactIds : set the property contactIds. 
	 */
	public void setContactIds(String[] contactIds) {
		this.contactIds = contactIds;
	}
	/**
	 * @return contact : return the property contact.  
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * @param contact : set the property contact. 
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * @return unfinishedPlanName : return the property unfinishedPlanName.  
	 */
	public String getUnfinishedPlanName() {
		return unfinishedPlanName;
	}
	/**
	 * @param unfinishedPlanName : set the property unfinishedPlanName. 
	 */
	public void setUnfinishedPlanName(String unfinishedPlanName) {
		this.unfinishedPlanName = unfinishedPlanName;
	}
	/**
	 * @return marketRemark : return the property marketRemark.  
	 */
	public String getMarketRemark() {
		return marketRemark;
	}
	/**
	 * @param marketRemark : set the property marketRemark. 
	 */
	public void setMarketRemark(String marketRemark) {
		this.marketRemark = marketRemark;
	}
	/**
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
	/**
	 * @return businessType : return the property businessType.  
	 */
	public String getBusinessType() {
		return businessType;
	}
	/**
	 * @param businessType : set the property businessType. 
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	/**
	 * @return developmentPhase : return the property developmentPhase.  
	 */
	public String getDevelopmentPhase() {
		return developmentPhase;
	}
	/**
	 * @param developmentPhase : set the property developmentPhase. 
	 */
	public void setDevelopmentPhase(String developmentPhase) {
		this.developmentPhase = developmentPhase;
	}
	/**
	 * @return continueMark : return the property continueMark.  
	 */
	public String getContinueMark() {
		return continueMark;
	}
	/**
	 * @param continueMark : set the property continueMark. 
	 */
	public void setContinueMark(String continueMark) {
		this.continueMark = continueMark;
	}
	/**
	 * @return cooperationIntention : return the property cooperationIntention.  
	 */
	public String getCooperationIntention() {
		return cooperationIntention;
	}
	/**
	 * @param cooperationIntention : set the property cooperationIntention. 
	 */
	public void setCooperationIntention(String cooperationIntention) {
		this.cooperationIntention = cooperationIntention;
	}
	/**
	 * @return repeatCust : return the property repeatCust.  
	 */
	public String getRepeatCust() {
		return repeatCust;
	}
	/**
	 * @param repeatCust : set the property repeatCust. 
	 */
	public void setRepeatCust(String repeatCust) {
		this.repeatCust = repeatCust;
	}
	/**
	 * @return repeatCustDeptName : return the property repeatCustDeptName.  
	 */
	public String getRepeatCustDeptName() {
		return repeatCustDeptName;
	}
	/**
	 * @param repeatCustDeptName : set the property repeatCustDeptName. 
	 */
	public void setRepeatCustDeptName(String repeatCustDeptName) {
		this.repeatCustDeptName = repeatCustDeptName;
	}
	/**
	 * @return businessOpportunityStatus : return the property businessOpportunityStatus.  
	 */
	public String getBusinessOpportunityStatus() {
		return businessOpportunityStatus;
	}
	/**
	 * @param businessOpportunityStatus : set the property businessOpportunityStatus. 
	 */
	public void setBusinessOpportunityStatus(String businessOpportunityStatus) {
		this.businessOpportunityStatus = businessOpportunityStatus;
	}
	/**
	 * @return custIds : return the property custIds.  
	 */
	public String[] getCustIds() {
		return custIds;
	}
	/**
	 * @param custIds : set the property custIds. 
	 */
	public void setCustIds(String[] custIds) {
		this.custIds = custIds;
	}
	
}
