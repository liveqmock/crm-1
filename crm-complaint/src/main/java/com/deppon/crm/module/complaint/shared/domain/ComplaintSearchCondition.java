package com.deppon.crm.module.complaint.shared.domain;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;

/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ComplaintSearchCondition.java
 * @package com.deppon.crm.module.complaint.shared.domain 
 * @author ouy
 * @version 0.1 2012-4-10
 */
public class ComplaintSearchCondition {
	/**
	 * 处理编号
	 */
	private String dealbill;
	/**
	 * 上报类型
	 * r 投诉
	 * e 异常
	 */
	private String reportType;
	

	private Integer fid;
	//订单号
	private String  bill;
	//联系电话
	private String phone;
	private Integer start;
	private Integer limit;
	//当前登录用户可以查询的部门id
	private List<String> deptIds;
	//处理状态
	private String prostatus;
	//处理状态列表
	private List<String> prostatusList;

	//绑定工单码
	private String rebindno;
	//是否到期
	private String ifmaturity;
	//用户id
	private String userid;
	
	//开发维护模块参数，用于统计当月投诉客户 format:yyyy-MM
	private String maintainDateFrom;
	
	//开发维护模块参数，用于统计当月投诉客户 format:yyyy-MM-dd hh24:mi:ss
	private String maintainDateTo;
	
	//客户编码
	private String complainid;
	
	//登陆用户
	private User user;
	/**
	 * 重复工单码
	 */
	private String recomcode;
	/**
	 * 解决状态
	 */
	private String dealstatus;
	//上报日期 （小） 两数之间
	private Date reportTimeStart;
	//上报日期 （大）
	private Date reportTimeEnd;
	//上报类型已有
	//来电人
	private String compman;
	//处理结果id
	private String deptId;
	//处理状态 解决状态 已有
	
	//处理人
	private String dealman;
	//联系电话已有
	
	// 是否是接入 1:接入 0：不是接入
	private Integer ifInsert;
	//处理人id 后来加的字段（回访默认查询）
	private Integer dealmanid;
	//是否查询加锁工单(针对工单处理)
	private Integer searchLock;
	
	private Date lockingtime;
	
	//查询被绑定列表:1:绑定（去掉本身），0否
	private String toBeBanging;
	//回访人
	private String visitor;
	//客户满意度
	private String custsatisfy;
	//处理开始时间
	private Date processBeginTime;
	//处理结束时间
	private Date processEndTime;
	//回访开始时间
	private Date visitorTimeStart;
	//回访结束时间
	private Date visitorTimeEnd;
	//上报人
	private String reporter;
	// 排序类型（工单查询、工单接入）(废弃)
	private String sortType;
	//业务类型
	private String businessModel;
	/**
	 * set get方法
	 */
	//sortType get方法
	public String getSortType() {
		return sortType;
	}
	//sortType set方法
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	//user get方法
	public User getUser() {
		return user;
	}
	//user set方法
	public void setUser(User user) {
		this.user = user;
	}
	//prostatusList get方法
	public List<String> getProstatusList() {
		return prostatusList;
	}
	//prostatusList set方法
	public void setProstatusList(List<String> prostatusList) {
		this.prostatusList = prostatusList;
	}
	//complainid get方法
	public String getComplainid() {
		return complainid;
	}
	//complainid set方法
	public void setComplainid(String complainid) {
		this.complainid = complainid;
	}
	//maintainDateFrom get方法
	public String getMaintainDateFrom() {
		return maintainDateFrom;
	}
	//maintainDateFrom set方法
	public void setMaintainDateFrom(String maintainDateFrom) {
		this.maintainDateFrom = maintainDateFrom;
	}
	//maintainDateTo get方法
	public String getMaintainDateTo() {
		return maintainDateTo;
	}
	//maintainDateTo set方法
	public void setMaintainDateTo(String maintainDateTo) {
		this.maintainDateTo = maintainDateTo;
	}
	//deptIds get方法
	public List<String> getDeptIds() {
		return deptIds;
	}
	//deptIds set方法
	public void setDeptIds(List<String> deptIds) {
		this.deptIds = deptIds;
	}
	//dealbill get方法
	public String getDealbill() {
		return dealbill;
	}
	//dealbill set方法
	public void setDealbill(String dealbill) {
		this.dealbill = dealbill;
	}
	//start get方法
	public Integer getStart() {
		return start;
	}
	//start set方法
	public void setStart(Integer start) {
		this.start = start;
	}
	//limit get方法
	public Integer getLimit() {
		return limit;
	}
	//limit set方法
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	//fid get方法
	public Integer getFid() {
		return fid;
	}
	//fid set方法
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	//bill get方法
	public String getBill() {
		return bill;
	}
	//bill set方法
	public void setBill(String bill) {
		this.bill = bill;
	}
	//phone get方法
	public String getPhone() {
		return phone;
	}
	//phone set方法
	public void setPhone(String phone) {
		this.phone = phone;
	}
	//reportType get方法
	public String getReportType() {
		return reportType;
	}
	//reportType set方法
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	//prostatus get方法
	public String getProstatus() {
		return prostatus;
	}
	//prostatus set方法
	public void setProstatus(String prostatus) {
		this.prostatus = prostatus;
	}
	//rebindno get方法
	public String getRebindno() {
		return rebindno;
	}
	//rebindno set方法
	public void setRebindno(String rebindno) {
		this.rebindno = rebindno;
	}
	//ifmaturity get方法
	public String getIfmaturity() {
		return ifmaturity;
	}
	//ifmaturity set方法
	public void setIfmaturity(String ifmaturity) {
		this.ifmaturity = ifmaturity;
	}
	//userid get方法
	public String getUserid() {
		return userid;
	}
	//userid set方法
	public void setUserid(String userid) {
		this.userid = userid;
	}
	//recomcode get方法
	public String getRecomcode() {
		return recomcode;
	}
	//recomcode set方法
	public void setRecomcode(String recomcode) {
		this.recomcode = recomcode;
	}
	//deptId get方法
	public String getDeptId() {
		return deptId;
	}
	//deptId set方法
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	//dealman get方法
	public String getDealman() {
		return dealman;
	}
	//dealman set方法
	public void setDealman(String dealman) {
		this.dealman = dealman;
	}
	//reportTimeStart get方法
	public Date getReportTimeStart() {
		return reportTimeStart;
	}
	//dealstatus get方法
	public String getDealstatus() {
		return dealstatus;
	}
	//dealstatus set方法
	public void setDealstatus(String dealstatus) {
		this.dealstatus = dealstatus;
	}
	//reportTimeStart set方法
	public void setReportTimeStart(Date reportTimeStart) {
		this.reportTimeStart = reportTimeStart;
	}
	//compman get方法
	public String getCompman() {
		return compman;
	}
	//compman set方法
	public void setCompman(String compman) {
		this.compman = compman;
	}
	//reportTimeEnd get方法
	public Date getReportTimeEnd() {
		return reportTimeEnd;
	}
	//reportTimeEnd set方法
	public void setReportTimeEnd(Date reportTimeEnd) {
		this.reportTimeEnd = reportTimeEnd;
	}
	//ifInsert get方法
	public Integer getIfInsert() {
		return ifInsert;
	}
	//ifInsert set方法
	public void setIfInsert(Integer ifInsert) {
		this.ifInsert = ifInsert;
	}
	//dealmanid get方法
	public Integer getDealmanid() {
		return dealmanid;
	}
	//dealmanid set方法
	public void setDealmanid(Integer dealmanid) {
		this.dealmanid = dealmanid;
	}
	//searchLock get方法
	public Integer getSearchLock() {
		return searchLock;
	}
	//searchLock set方法
	public void setSearchLock(Integer searchLock) {
		this.searchLock = searchLock;
	}
	//lockingtime get方法
	public Date getLockingtime() {
		return lockingtime;
	}
	//lockingtime set方法
	public void setLockingtime(Date lockingtime) {
		this.lockingtime = lockingtime;
	}
	//toBeBanging get方法
	public String getToBeBanging() {
		return toBeBanging;
	}
	//toBeBanging set方法
	public void setToBeBanging(String toBeBanging) {
		this.toBeBanging = toBeBanging;
	}
	//visitor get方法
	public String getVisitor() {
		return visitor;
	}
	//visitor set方法
	public void setVisitor(String visitor) {
		this.visitor = visitor;
	}
	//custsatisfy get方法
	public String getCustsatisfy() {
		return custsatisfy;
	}
	//custsatisfy set方法
	public void setCustsatisfy(String custsatisfy) {
		this.custsatisfy = custsatisfy;
	}
	//processBeginTime get方法
	public Date getProcessBeginTime() {
		return processBeginTime;
	}
	//processBeginTime set方法
	public void setProcessBeginTime(Date processBeginTime) {
		this.processBeginTime = processBeginTime;
	}
	//processEndTime get方法
	public Date getProcessEndTime() {
		return processEndTime;
	}
	//processEndTime set方法
	public void setProcessEndTime(Date processEndTime) {
		this.processEndTime = processEndTime;
	}
	//visitorTimeStart get方法
	public Date getVisitorTimeStart() {
		return visitorTimeStart;
	}
	//visitorTimeStart set方法
	public void setVisitorTimeStart(Date visitorTimeStart) {
		this.visitorTimeStart = visitorTimeStart;
	}
	//visitorTimeEnd get方法
	public Date getVisitorTimeEnd() {
		return visitorTimeEnd;
	}
	//visitorTimeEnd set方法
	public void setVisitorTimeEnd(Date visitorTimeEnd) {
		this.visitorTimeEnd = visitorTimeEnd;
	}
	//reporter get方法
	public String getReporter() {
		return reporter;
	}
	//reporter set方法
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	
	public String getBusinessModel() {
		return businessModel;
	}
	public void setBusinessModel(String businessModel) {
		this.businessModel = businessModel;
	}
}
