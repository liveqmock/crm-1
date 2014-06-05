package com.deppon.crm.module.custview.shared.domain;

import java.util.Date;

/**
 * @description 理赔统计.
 * @author 安小虎
 * @version 0.1 2012-4-25
 * @date 2012-4-25
 */

public class RecStatistics {
	/*
	 * ID
	 */
	private String id;
	/*
	 * ID
	 */
	private String custId;
	/*
	 * 日期
	 */
	private Date date;
	/*
	 * 冒领
	 */
	private Integer impersonator;
	/*
	 * 破损
	 */
	private Integer breakageforpay;
	/*
	 * 潮湿
	 */
	private Integer dampforpay;
	/*
	 * 污染
	 */
	private Integer polluteforpay;
	/*
	 * 其他
	 */
	private Integer othersforpay;
	/*
	 * 整件丢失
	 */
	private Integer pieceslost;
	/*
	 * 整票丢失
	 */
	private Integer billlost;
	/*
	 * 内物短少
	 */
	private Integer goodslack;
	/*
	 * 合计
	 */
	private Integer total;
	/**
	 * @return the id
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setId(String id) {
		this.id = id;
	}
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
	 * @return the date
	 * @author 潘光均
	 * @version v0.1
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the impersonator
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getImpersonator() {
		return impersonator;
	}
	/**
	 * @param impersonator the impersonator to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setImpersonator(Integer impersonator) {
		this.impersonator = impersonator;
	}
	/**
	 * @return the breakageforpay
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getBreakageforpay() {
		return breakageforpay;
	}
	/**
	 * @param breakageforpay the breakageforpay to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setBreakageforpay(Integer breakageforpay) {
		this.breakageforpay = breakageforpay;
	}
	/**
	 * @return the dampforpay
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getDampforpay() {
		return dampforpay;
	}
	/**
	 * @param dampforpay the dampforpay to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setDampforpay(Integer dampforpay) {
		this.dampforpay = dampforpay;
	}
	/**
	 * @return the polluteforpay
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getPolluteforpay() {
		return polluteforpay;
	}
	/**
	 * @param polluteforpay the polluteforpay to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setPolluteforpay(Integer polluteforpay) {
		this.polluteforpay = polluteforpay;
	}
	/**
	 * @return the othersforpay
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getOthersforpay() {
		return othersforpay;
	}
	/**
	 * @param othersforpay the othersforpay to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setOthersforpay(Integer othersforpay) {
		this.othersforpay = othersforpay;
	}
	/**
	 * @return the pieceslost
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getPieceslost() {
		return pieceslost;
	}
	/**
	 * @param pieceslost the pieceslost to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setPieceslost(Integer pieceslost) {
		this.pieceslost = pieceslost;
	}
	/**
	 * @return the billlost
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getBilllost() {
		return billlost;
	}
	/**
	 * @param billlost the billlost to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setBilllost(Integer billlost) {
		this.billlost = billlost;
	}
	/**
	 * @return the goodslack
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getGoodslack() {
		return goodslack;
	}
	/**
	 * @param goodslack the goodslack to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setGoodslack(Integer goodslack) {
		this.goodslack = goodslack;
	}
	/**
	 * @return the total
	 * @author 潘光均
	 * @version v0.1
	 */
	public Integer getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
