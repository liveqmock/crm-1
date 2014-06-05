package com.deppon.crm.module.report.shared.domain;
/**
 * 	流失预警报表公共信息类
 * **/
public class WarnLostCustPub {
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
	
}
