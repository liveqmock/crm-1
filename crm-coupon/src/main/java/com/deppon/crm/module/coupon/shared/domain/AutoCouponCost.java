package com.deppon.crm.module.coupon.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;
/**   
* <p>
* Description:新增营销计划-发券规则-自动返券金额 实体类<br />
* </p>
* @author 钟琼
* @version 0.1 2012-11-14
*/
public class AutoCouponCost extends BaseEntity{
	
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -270015939923901397L;
	// 自动返券ID
	private String couponAutoId;
	// 费用类型	
	private String costType;
	// 金额下限
	private String costDown;
	// 返券金额
	private String couponCost;
	
	/************set  get 方法*************************************/
	
	/**
	 * 自动返券ID的get方法
	 * @return
	 */
	public String getCouponAutoId() {
		// 自动返券ID
		return couponAutoId;
	}
	/**
	 * 自动返券ID的set方法
	 * @return
	 */
	public void setCouponAutoId(String couponAutoId) {
		// 自动返券ID
		this.couponAutoId = couponAutoId;
	}
	/**
	 * 费用类型的个体get方法
	 * @return
	 */
	public String getCostType() {
		// 费用类型	
		return costType;
	}
	/**
	 * 费用类型的set方法
	 * @return
	 */
	public void setCostType(String costType) {
		// 费用类型	
		this.costType = costType;
	}
	/**
	 * 金额下限的个体get方法
	 * @return
	 */
	public String getCostDown() {
		// 金额下限
		return costDown;
	}
	/**
	 * 金额下限的set方法
	 * @return
	 */
	public void setCostDown(String costDown) {
		// 金额下限
		this.costDown = costDown;
	}
	/**
	 * 返券金额的个体get方法
	 * @return
	 */
	public String getCoupoCost() {
		// 返券金额
		return couponCost;
	}
	/**
	 * 返券金额的set方法
	 * @return
	 */
	public void setCoupoCost(String couponCost) {
		// 返券金额
		this.couponCost = couponCost;
	}
}
