package com.deppon.crm.module.custview.shared.domain;

import java.util.Date;

/**
 * @description 客户最近交易记录
 * @author 安小虎
 * @version 0.1 2012-4-26
 * @date 2012-4-26
 */

public class LatelyTrade {
	// 客户ID
	private String custId;
	// 最近交易日期
	private Date latelyTradeDate;
	// 距离上次交易时长
	private Long duration;
	// 最近交易日期
	private Date latelyTradeDateExpress;
	// 距离上次交易时长
	private Long durationExpress;
	/**
	 * @return the custId
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 * @return the latelyTradeDate
	 * @author 潘光均
	 * @version v0.1
	 */
	public Date getLatelyTradeDate() {
		return latelyTradeDate;
	}
	/**
	 * @param latelyTradeDate the latelyTradeDate to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setLatelyTradeDate(Date latelyTradeDate) {
		this.latelyTradeDate = latelyTradeDate;
	}
	/**
	 * @return the duration
	 * @author 潘光均
	 * @version v0.1
	 */
	public Long getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	/**
	 *@return  latelyTradeDateExpress;
	 */
	public Date getLatelyTradeDateExpress() {
		return latelyTradeDateExpress;
	}
	/**
	 * @param latelyTradeDateExpress : set the property latelyTradeDateExpress.
	 */
	public void setLatelyTradeDateExpress(Date latelyTradeDateExpress) {
		this.latelyTradeDateExpress = latelyTradeDateExpress;
	}
	/**
	 *@return  durationExpress;
	 */
	public Long getDurationExpress() {
		return durationExpress;
	}
	/**
	 * @param durationExpress : set the property durationExpress.
	 */
	public void setDurationExpress(Long durationExpress) {
		this.durationExpress = durationExpress;
	}
	
}
