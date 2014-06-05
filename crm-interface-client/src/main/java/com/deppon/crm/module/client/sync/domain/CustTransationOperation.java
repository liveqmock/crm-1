package com.deppon.crm.module.client.sync.domain;

public class CustTransationOperation {
	/**
	 * 带同步的客户主数据相关表的表名
	 * 
	 * @author li
	 */
	public enum CustomerTableName {
		/** 客户基础信息 */
		T_CUST_CUSTBASEDATA,
		/** 联系人 */
		T_CUST_CUSTLINKMAN,
		/** 接送货地址 */
		T_CUST_SHUTTLEADDRESS,
		/** 账户信息 */
		T_CUST_ACCOUNT,
		/** 合同信息 */
		T_CUST_CONTRACT,
		/** 联系人-接送地址-关系 */
		T_CUST_PREFERENCEADDRESS,
		/** 合同-部门关系对应表 */
		T_CUST_CONTRACTDEPT,
		/** 优惠信息表 */
		T_CUST_PREFERENTIAL,
		/**合同发票标记子公司*/
		T_CUST_CONTRACTTAX
	}

	/**
	 * 操作标识
	 * 
	 * @author li
	 * 
	 */
	public enum OperationFlg {
		/** 更新 */
		U,
		/** 插入 */
		I,
		/** 删除 */
		D
	} 
	
	private CustomerTableName tableName;
	private OperationFlg optFlg;
	private String keyword;
	public CustomerTableName getTableName() {
		return tableName;
	}
	public void setTableName(CustomerTableName tableName) {
		this.tableName = tableName;
	}
	public OperationFlg getOptFlg() {
		return optFlg;
	}
	public void setOptFlg(OperationFlg optFlg) {
		this.optFlg = optFlg;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
