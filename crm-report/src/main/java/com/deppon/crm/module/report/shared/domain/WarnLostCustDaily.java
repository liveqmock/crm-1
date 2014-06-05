package com.deppon.crm.module.report.shared.domain;
/**
 * 	流失预警报表公共信息类
 * **/

public class WarnLostCustDaily //extends WarnLostCustPub
{
	/**
	 * 预警后流失数据统计
	 * */
	public static  String[] FILEDDES=new String[]{
		"所属部门","小区","大区","事业部","经营本部"
		,"预警客户总数","零担预警客户总数","快递预警客户总数",
		
		"预警客户流失总数","零担类预警客户流失总数","快递类预警客户流失总数",
		"预警客户流失率","快递类预警客户流失率","零担类预警客户流失率",
		"预警客户回访总数","预警客户回访率",
		"零担类预警客户回访总数","快递类预警客户回访总数",
		"快递类预警客户回访率","零担类预警客户回访率"
		};
	public static  String[] FILEDSNAME=new String[]{
		"deptName","areaName",
		"bigAreaName","careerDeptName",
		"cadreName",
		
		"warnLostCount",
		"lTTWarnLostCount",
		"expressWarnLostCount",
		
		"lostAfterWarnCustCount",
		"lTTlostAfterWarnCustCount",
		"expresslostAfterWarnCustCount",
		"lostAfterWarnPre",
		"expresslostAfterWarnCustPercentage",
		"lTTlostAfterWarnCustPercentage",
		
		"returnVisitWarnLostCount",
		"warnLostRTPercentage",
		"lTTReturnVisitWarnLostCount",
		"erpressReturnVisitWarnLostCount",
		"lTTWarnLostRTPercentage",
		"expressWarnLostRTPercentage"
		};
	/**
	 * 预警后流失数据统计
	 * */
	//预警后流失的客户
	private int lostAfterWarnCustCount;
	//零担客户预警后流失数量
	private int lTTlostAfterWarnCustCount;
	//快递客户预警后流失数量
	private int expresslostAfterWarnCustCount;
	//预警客户流失率
	private float lostAfterWarnPre;
	//零担客户预警后流失率
	private int lTTlostAfterWarnCustPercentage;
	//快递客户预警后流失率
	private int expresslostAfterWarnCustPercentage;
	/**
	 * 回访数据统计
	 * **/        
	//回访流失客户总数
	private int returnVisitWarnLostCount;
	//流失预静名单中的回访率
	private float warnLostRTPercentage;
	//名单中零担客户的回访数量
	private int lTTReturnVisitWarnLostCount;
	//名单中快递客户的回访数量
	private int erpressReturnVisitWarnLostCount;

	//预警中零担客户的回访率
	private float lTTWarnLostRTPercentage;
	//预警中快递客户回访率
	private float expressWarnLostRTPercentage;
	
	
	
	
	/**预警名单统计
	 * **/
	//流失预警名单中客户总数
	private int warnLostCount;
	//流失预警名单中零担客户数量
	private int lTTWarnLostCount;
	//流失预警名单中快递客户数量
	private int expressWarnLostCount;
	/**
	 * 部门信息
	 * **/
	// 部门ID
	private int deptId;
	//部门名称
	private String deptName;
	//小区标杆编码
	private String areaName;
	//大区标杆编码
	private String bigAreaName;
	//事业部标杆编码
	private String careerDeptName;
	//本部标杆编码
	private String cadreName;
	public int getWarnLostCount() {
		return warnLostCount;
	}
	public void setWarnLostCount(int warnLostCount) {
		this.warnLostCount = warnLostCount;
	}
	public int getLTTWarnLostCount() {
		return lTTWarnLostCount;
	}
	public void setLTTWarnLostCount(int lTTWarnLostCount) {
		this.lTTWarnLostCount = lTTWarnLostCount;
	}
	public int getExpressWarnLostCount() {
		return expressWarnLostCount;
	}
	public void setExpressWarnLostCount(int expressWarnLostCount) {
		this.expressWarnLostCount = expressWarnLostCount;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getBigAreaName() {
		return bigAreaName;
	}
	public void setBigAreaName(String bigAreaName) {
		this.bigAreaName = bigAreaName;
	}
	public String getCareerDeptName() {
		return careerDeptName;
	}
	public void setCareerDeptName(String careerDeptName) {
		this.careerDeptName = careerDeptName;
	}
	public String getCadreName() {
		return cadreName;
	}
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	
	
	
	public int getLostAfterWarnCustCount() {
		return lostAfterWarnCustCount;
	}
	public void setLostAfterWarnCustCount(int lostAfterWarnCustCount) {
		this.lostAfterWarnCustCount = lostAfterWarnCustCount;
	}
	public int getLTTlostAfterWarnCustCount() {
		return lTTlostAfterWarnCustCount;
	}
	public void setLTTlostAfterWarnCustCount(int lTTlostAfterWarnCustCount) {
		this.lTTlostAfterWarnCustCount = lTTlostAfterWarnCustCount;
	}
	public int getExpresslostAfterWarnCustCount() {
		return expresslostAfterWarnCustCount;
	}
	public void setExpresslostAfterWarnCustCount(int expresslostAfterWarnCustCount) {
		this.expresslostAfterWarnCustCount = expresslostAfterWarnCustCount;
	}
	public float getLostAfterWarnPre() {
		return lostAfterWarnPre;
	}
	public void setLostAfterWarnPre(float lostAfterWarnPre) {
		this.lostAfterWarnPre = lostAfterWarnPre;
	}
	public int getLTTlostAfterWarnCustPercentage() {
		return lTTlostAfterWarnCustPercentage;
	}
	public void setLTTlostAfterWarnCustPercentage(int lTTlostAfterWarnCustPercentage) {
		this.lTTlostAfterWarnCustPercentage = lTTlostAfterWarnCustPercentage;
	}
	public int getExpresslostAfterWarnCustPercentage() {
		return expresslostAfterWarnCustPercentage;
	}
	public void setExpresslostAfterWarnCustPercentage(
			int expresslostAfterWarnCustPercentage) {
		this.expresslostAfterWarnCustPercentage = expresslostAfterWarnCustPercentage;
	}
	public int getReturnVisitWarnLostCount() {
		return returnVisitWarnLostCount;
	}
	public void setReturnVisitWarnLostCount(int returnVisitWarnLostCount) {
		this.returnVisitWarnLostCount = returnVisitWarnLostCount;
	}
	public float getWarnLostRTPercentage() {
		return warnLostRTPercentage;
	}
	public void setWarnLostRTPercentage(float warnLostRTPercentage) {
		this.warnLostRTPercentage = warnLostRTPercentage;
	}
	public int getLTTReturnVisitWarnLostCount() {
		return lTTReturnVisitWarnLostCount;
	}
	public void setLTTReturnVisitWarnLostCount(int lTTReturnVisitWarnLostCount) {
		this.lTTReturnVisitWarnLostCount = lTTReturnVisitWarnLostCount;
	}
	public int getErpressReturnVisitWarnLostCount() {
		return erpressReturnVisitWarnLostCount;
	}
	public void setErpressReturnVisitWarnLostCount(
			int erpressReturnVisitWarnLostCount) {
		this.erpressReturnVisitWarnLostCount = erpressReturnVisitWarnLostCount;
	}
	public float getLTTWarnLostRTPercentage() {
		return lTTWarnLostRTPercentage;
	}
	public void setLTTWarnLostRTPercentage(float lTTWarnLostRTPercentage) {
		this.lTTWarnLostRTPercentage = lTTWarnLostRTPercentage;
	}
	public float getExpressWarnLostRTPercentage() {
		return expressWarnLostRTPercentage;
	}
	public void setExpressWarnLostRTPercentage(float expressWarnLostRTPercentage) {
		this.expressWarnLostRTPercentage = expressWarnLostRTPercentage;
	}
	
}
