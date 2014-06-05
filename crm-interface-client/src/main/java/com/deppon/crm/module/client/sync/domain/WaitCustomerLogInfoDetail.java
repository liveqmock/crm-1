package com.deppon.crm.module.client.sync.domain;


public class WaitCustomerLogInfoDetail {
	String FID;
	/** 事务序号*/
	String TRANSACTION_NO;
	/** 待同步的表的表名（物理模型中的表名） */
	String TABLE_NAME;
	/** 待同步数据的记录的关键字 */
	String TABLE_KEYWORD;
	/**
	 * 操作标识
	 * I：待插入
	 * U：待更新
	 * D：待删除
	 */
	String OPERATION_FLG;

	public String getFID() {
		return FID;
	}
	public void setFID(String fID) {
		FID = fID;
	}
	public String getTRANSACTION_NO() {
		return TRANSACTION_NO;
	}
	public void setTRANSACTION_NO(String tRANSACTION_NO) {
		TRANSACTION_NO = tRANSACTION_NO;
	}
	public String getTABLE_NAME() {
		return TABLE_NAME;
	}
	public void setTABLE_NAME(String tABLE_NAME) {
		TABLE_NAME = tABLE_NAME;
	}
	public String getTABLE_KEYWORD() {
		return TABLE_KEYWORD;
	}
	public void setTABLE_KEYWORD(String tABLE_KEYWORD) {
		TABLE_KEYWORD = tABLE_KEYWORD;
	}
	public String getOPERATION_FLG() {
		return OPERATION_FLG;
	}
	public void setOPERATION_FLG(String oPERATION_FLG) {
		OPERATION_FLG = oPERATION_FLG;
	}
}
