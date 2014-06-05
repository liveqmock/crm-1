package com.deppon.crm.module.client.common.domain;

import java.math.BigDecimal;
import java.util.Date;

public class WaitCustomerLogInfo {
	String FID;
	/** 事务序号 */
	String TRANSACTION_NO;
	/** 客户编号 */
	String CUSTOMER_NO;
	/** 数据进表时间 */
	Date CRATE_TIME;
	/** 同步完成时间 */
	Date FINISH_TIME;
	/** 错误处理方式 */
	String HANDLE_TYPE;
	/** 记录状态  U 待发送 ，D 已发送*/  
	String STATUS;
	/** 目标系统 */
	String TARGET;
	/** 最后一次的出错代码 */
	String ERROR_CODE;
	/** 最后一次发送的出错描述 */
	String ERR_DESC;
	/** 出错次数 */
	BigDecimal ERR_NUMBER;
	/** 最后一次的出错时间 */
	Date ERR_TIME;
	/** 场景 */
	String PATTERN;
	/** JSON数据 */
	String DATA;
	/** 数据推送失败的系统  1:FOSS 2:CC 4:WDGH 如CC和WDGH失败，则该值为 6 */
	Integer ERR_SYS;

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

	public String getCUSTOMER_NO() {
		return CUSTOMER_NO;
	}

	public void setCUSTOMER_NO(String cUSTOMER_NO) {
		CUSTOMER_NO = cUSTOMER_NO;
	}

	public Date getCRATE_TIME() {
		return CRATE_TIME;
	}

	public void setCRATE_TIME(Date cRATE_TIME) {
		CRATE_TIME = cRATE_TIME;
	}

	public Date getFINISH_TIME() {
		return FINISH_TIME;
	}

	public void setFINISH_TIME(Date fINISH_TIME) {
		FINISH_TIME = fINISH_TIME;
	}

	public String getHANDLE_TYPE() {
		return HANDLE_TYPE;
	}

	public void setHANDLE_TYPE(String hANDLE_TYPE) {
		HANDLE_TYPE = hANDLE_TYPE;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getTARGET() {
		return TARGET;
	}

	public void setTARGET(String tARGET) {
		TARGET = tARGET;
	}

	public String getERROR_CODE() {
		return ERROR_CODE;
	}

	public void setERROR_CODE(String eRROR_CODE) {
		ERROR_CODE = eRROR_CODE;
	}

	public String getERR_DESC() {
		return ERR_DESC;
	}

	public void setERR_DESC(String eRR_DESC) {
		ERR_DESC = eRR_DESC;
	}

	public BigDecimal getERR_NUMBER() {
		return ERR_NUMBER;
	}

	public void setERR_NUMBER(BigDecimal eRR_NUMBER) {
		ERR_NUMBER = eRR_NUMBER;
	}

	public Date getERR_TIME() {
		return ERR_TIME;
	}

	public void setERR_TIME(Date eRR_TIME) {
		ERR_TIME = eRR_TIME;
	}

	public String getPATTERN() {
		return PATTERN;
	}

	public void setPATTERN(String pATTERN) {
		PATTERN = pATTERN;
	}

	public String getDATA() {
		return DATA;
	}

	public void setDATA(String dATA) {
		DATA = dATA;
	}

	public Integer getERR_SYS() {
		return ERR_SYS;
	}

	public void setERR_SYS(Integer eRR_SYS) {
		ERR_SYS = eRR_SYS;
	}
	
}
