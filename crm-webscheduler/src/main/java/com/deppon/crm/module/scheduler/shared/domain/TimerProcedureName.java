package com.deppon.crm.module.scheduler.shared.domain;

public enum TimerProcedureName {
	MEMBERUPGRADE("memberupgrade"),
	MEMBERFALL("memberfall");
	
	private TimerProcedureName(String str){
		this.str = str;
	}
	
	/*
	 * 属性
	 */
	private String str;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
	
	/*
	 * 获得值
	 */
	public static String getValue(TimerProcedureName type) {
		switch (type) {
			case MEMBERUPGRADE:
				return MEMBERUPGRADE.getStr();
			case MEMBERFALL:
				return MEMBERFALL.getStr();
			default:
				return "";
		}
	}
}
