package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;
/**   
 * <p>
 * Description:流失预警客户Bean<br />
 * </p>
 * @title WarnLostCust.java
 * @package com.deppon.crm.module.marketing.shared.domain
 * @author zzw
 * @version 2014-3-11
 */
public class WarnLostCust {
	//excel 表头信息
	public static final String[] HEAD=
			new String[]{
		"经营本部","事业部","大区","营业区","所属部门",
		"客户编码","客户等级","客户名称","客户类别",
		"预警状态","预发货开始时限","预发货结束时限",
		"是否为季节性","标记为季节性客户的次数",
		"预警时间","平均发货时间间隔（天）",
		"最近一次发货时间","最近一次发货金额（元）"
		,"流失原因","流失原因备注",
		"预警信息变更时间","预警信息变更人","近90天总发货金额（元）"};
	////excel 表头信息对应的字段名
	public static final String[] KEYS=new String[]{
		"cadreStandardName","careerDeptStandardName","bigAreaStandardName","areaStandardName","deptStandardName",
		"custNumber","custLevelDesc","custName","custCategoryDesc",
		"warnStatusDesc","preDeliverytBiegnTime","preDeliverytendTime",
		"isSeasonDesc","seasonTime","createTime","intervalNendTime",
		"lastsendTime","lastsendMoney",
		"lostCause","lostCustRemark","warnStatusChangeTime","warnStatusChangeUser","suminRecent90day"};
	//部门级别
	//部门
	public final static String DETP="DEPT";
	//小区
	public final static String AREA="AREA";
	//大区
	public final static String BIGAREA="BIGAREA";
	//事业部
	public final static String CAREEA="CAREEA";
	//本部
	public final static String CADRES="CADRES";
	//快递大区 shit！！！！
	public final static String EXPRESSBIGAREA="EXPRESSBIGAREA";
	//客户等级 黄金
	public final static String GOLD="GOLD";
	//钻石
	public final static String DIAMOND="DIAMOND";
	//铂金
	public final static String PLATINUM="PLATINUM";
	// 普通客户
	public final static String NORMAL = "NORMAL";
	private int id;
	//标记为季节的次数
	private int seasonTime;
	//客户编码
	private String custNumber;
	//平均发货时间间隔
	private double intervalNendTime;
	//最后一次发货时间（某天）
	private Date lastsendTime;
	//最后一次发货金额（
	private double lastsendMoney;
	//90天发货总金额
	private double suminRecent90day;
	//预警信息创建时间
	private Date createTime;
	//创建人ID
	private  int createUser;
	//创建人名称
	private String createUserName;
	//部门标杆编码
	private String deptStandardCode;
	//小区标杆编码
	private String areaDtandardCode;
	//大区标杆编码
	private String bigAreaStandardCode;
	//事业部标杆编码
	private String careerDeptStandardCode;
	//本部标杆编码
	private String cadreStandardCode;
	//部门名称
	private String deptStandardName;
	//小区标杆编码
	private String areaStandardName;
	//大区标杆编码
	private String bigAreaStandardName;
	//事业部标杆编码
	private String careerDeptStandardName;
	//本部标杆编码
	private String cadreStandardName;
	//客户等级
	private String custLevel;
	//客户等级描述
	private String custLevelDesc;
	//客户等级
	private String custName;
	//是否季节
	private int isSeason;
	//客户季节性描述
	private String isSeasonDesc;
	//预警状态
	private String warnStatus;
	//客户预警状态描述
	private String warnStatusDesc;
	//预警状态变更人 姓名
	private String warnStatusChangeUser;
	//预警状态变更时间
	private Date warnStatusChangeTime;
	//客户类别：EL LTT EXPRESS
	private String custCategory;
	//客户类别：零担 快递 零担和快递描述
	private String custCategoryDesc;
	//预发货开始时间
	private Date preDeliverytendTime;
	//预发货结束时间
	private Date preDeliverytBiegnTime;
	//流失原因
	private String lostCause;
	//流失备注
	private String lostCustRemark;
	//回访次数
	private int returnTimes;
	//计划执行人
	private String executor;
	public WarnLostCust(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSeasonTime() {
		return seasonTime;
	}
	public void setSeasonTime(int seasonTime) {
		this.seasonTime = seasonTime;
	}
	public String getCustNumber() {
		return custNumber;
	}
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}
	public double getIntervalNendTime() {
		return intervalNendTime;
	}
	public void setIntervalNendTime(double intervalNendTime) {
		this.intervalNendTime = intervalNendTime;
	}
	public Date getLastsendTime() {
		return lastsendTime;
	}
	public void setLastsendTime(Date lastsendTime) {
		this.lastsendTime = lastsendTime;
	}
	public double getLastsendMoney() {
		return lastsendMoney;
	}
	public void setLastsendMoney(double lastsendMoney) {
		this.lastsendMoney = lastsendMoney;
	}
	public double getSuminRecent90day() {
		return suminRecent90day;
	}
	public void setSuminRecent90day(double suminRecent90day) {
		this.suminRecent90day = suminRecent90day;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getCreateUser() {
		return createUser;
	}
	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getDeptStandardCode() {
		return deptStandardCode;
	}
	public void setDeptStandardCode(String deptStandardCode) {
		this.deptStandardCode = deptStandardCode;
	}
	public String getAreaDtandardCode() {
		return areaDtandardCode;
	}
	public void setAreaDtandardCode(String areaDtandardCode) {
		this.areaDtandardCode = areaDtandardCode;
	}
	public String getBigAreaStandardCode() {
		return bigAreaStandardCode;
	}
	public void setBigAreaStandardCode(String bigAreaStandardCode) {
		this.bigAreaStandardCode = bigAreaStandardCode;
	}
	public String getCareerDeptStandardCode() {
		return careerDeptStandardCode;
	}
	public void setCareerDeptStandardCode(String careerDeptStandardCode) {
		this.careerDeptStandardCode = careerDeptStandardCode;
	}
	public String getCadreStandardCode() {
		return cadreStandardCode;
	}
	public void setCadreStandardCode(String cadreStandardCode) {
		this.cadreStandardCode = cadreStandardCode;
	}
	public String getDeptStandardName() {
		return deptStandardName;
	}
	public void setDeptStandardName(String deptStandardName) {
		this.deptStandardName = deptStandardName;
	}
	public String getAreaStandardName() {
		return areaStandardName;
	}
	public void setAreaStandardName(String areaStandardName) {
		this.areaStandardName = areaStandardName;
	}
	public String getBigAreaStandardName() {
		return bigAreaStandardName;
	}
	public void setBigAreaStandardName(String bigAreaStandardName) {
		this.bigAreaStandardName = bigAreaStandardName;
	}
	public String getCareerDeptStandardName() {
		return careerDeptStandardName;
	}
	public void setCareerDeptStandardName(String careerDeptStandardName) {
		this.careerDeptStandardName = careerDeptStandardName;
	}
	public String getCadreStandardName() {
		return cadreStandardName;
	}
	public void setCadreStandardName(String cadreStandardName) {
		this.cadreStandardName = cadreStandardName;
	}
	public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public int getIsSeason() {
		return isSeason;
	}
	public void setIsSeason(int isSeason) {
		this.isSeason = isSeason;
	}
	
	public String getWarnStatus() {
		return warnStatus;
	}
	public void setWarnStatus(String warnStatus) {
		this.warnStatus = warnStatus;
	}
	
	public Date getPreDeliverytendTime() {
		return preDeliverytendTime;
	}
	public void setPreDeliverytendTime(Date preDeliverytendTime) {
		this.preDeliverytendTime = preDeliverytendTime;
	}
	public Date getPreDeliverytBiegnTime() {
		return preDeliverytBiegnTime;
	}
	public void setPreDeliverytBiegnTime(Date preDeliverytBiegnTime) {
		this.preDeliverytBiegnTime = preDeliverytBiegnTime;
	}
	public String getWarnStatusChangeUser() {
		return warnStatusChangeUser;
	}
	public void setWarnStatusChangeUser(String warnStatusChangeUser) {
		this.warnStatusChangeUser = warnStatusChangeUser;
	}
	public Date getWarnStatusChangeTime() {
		return warnStatusChangeTime;
	}
	public void setWarnStatusChangeTime(Date warnStatusChangeTime) {
		this.warnStatusChangeTime = warnStatusChangeTime;
	}
	public String getLostCause() {
		return lostCause;
	}
	public void setLostCause(String lostCause) {
		this.lostCause = lostCause;
	}
	public String getLostCustRemark() {
		return lostCustRemark;
	}
	public void setLostCustRemark(String lostCustRemark) {
		this.lostCustRemark = lostCustRemark;
	}
	public int getReturnTimes() {
		return returnTimes;
	}
	public void setReturnTimes(int returnTimes) {
		this.returnTimes = returnTimes;
	}
	public String getCustLevelDesc() {
		return custLevelDesc;
	}
	public void setCustLevelDesc(String custLevelDesc) {
		this.custLevelDesc = custLevelDesc;
	}
	public String getIsSeasonDesc() {
		return isSeasonDesc;
	}
	public void setIsSeasonDesc(String isSeasonDesc) {
		this.isSeasonDesc = isSeasonDesc;
	}
	public String getWarnStatusDesc() {
		return warnStatusDesc;
	}
	public void setWarnStatusDesc(String warnStatusDesc) {
		this.warnStatusDesc = warnStatusDesc;
	}
	public String getCustCategory() {
		return custCategory;
	}
	public void setCustCategory(String custCategory) {
		this.custCategory = custCategory;
	}
	public String getCustCategoryDesc() {
		return custCategoryDesc;
	}
	public void setCustCategoryDesc(String custCategoryDesc) {
		this.custCategoryDesc = custCategoryDesc;
	}
	public String getExecutor() {
		return executor;
	}
	public void setExecutor(String executor) {
		this.executor = executor;
	}

}
