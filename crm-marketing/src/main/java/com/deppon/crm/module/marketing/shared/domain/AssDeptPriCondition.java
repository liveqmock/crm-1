package com.deppon.crm.module.marketing.shared.domain;

public class AssDeptPriCondition {
	//经营本部
	private static final String serCenter =
			MarketAssessConstance.AUTHOR_SERCENTER_STR;
	//事业部
	private static final String buDept = 
			MarketAssessConstance.AUTHOR_BUDEPT_STR;
	//大区
	private static final String region = 
			MarketAssessConstance.AUTHOR_REGION_STR;
	//小区
	private static final String buQuarter = 
			MarketAssessConstance.AUTHOR_BUQUARTER_STR;
	//营业部
	private static final String salDept = 
			MarketAssessConstance.AUTHOR_SALDEPT_STR;
	//用户empCode
	private String empCode;
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public static String getSercenter() {
		return serCenter;
	}
	public static String getBudept() {
		return buDept;
	}
	public static String getRegion() {
		return region;
	}
	public static String getBuquarter() {
		return buQuarter;
	}
	public static String getSaldept() {
		return salDept;
	}
	
}
