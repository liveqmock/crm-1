/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CouponRule.java
 * @package com.deppon.crm.module.coupon.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-11-13
 */
package com.deppon.crm.module.coupon.shared.domain;

import java.util.Date;
import java.util.List;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description: 优惠券使用规则实体<br />
 * </p>
 * @title CouponRule.java
 * @package com.deppon.crm.module.coupon.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-11-13
 */

public class CouponRule extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 营销计划ID	外键
	private String marketPlanId;
	// 金额要求模式 	默认为“1” 分级抵扣模式，“2”严格抵扣模式
	private String costMode;
	// 金额类型 		默认为“1” 运费，“2”开单金额
	private String costType;
	// 使用金额
	private String value;
	// 抵扣金额		选择分级抵扣模式 时 使用
	private String discount;
	// 增值费要求	默认为“0”没选中，“1”选中
	private String costAddedMode;
	// 增值费类型	包装、保价、代收、送货、接货费
	private String costAddedType;
	// 增值费金额
	private String costAdded;
	// 使用期限开始时间
	private Date begintime;
	// 使用期限结束时间
	private Date endtime;
	// 短信内容
	private String smsContent;
	// 优惠券描述
	private String describe;
	// 走货线路信息
	private List<GoodsLine> goodsLines;
	// 使用条件信息
	private List<ConditionType> conditionTypes;
	// 规则类型-用于mybatis集合查询，非固话列
	private String usrType ;
	
	/**
	 * @return ruleType : return the property usrType.
	 */
	public String getUsrType() {
		// 规则类型-用于mybatis集合查询，非固话列
		return usrType;
	}
	/**
	 * @param ruleType : set the property usrType.
	 */
	public void setUsrType(String usrType) {
		// 规则类型-用于mybatis集合查询，非固话列
		this.usrType = usrType;
	}
	/**
	 * @return marketPlanId : return the property marketPlanId.
	 */
	public String getMarketPlanId() {
		// 营销计划ID	外键
		return marketPlanId;
	}
	/**
	 * @param marketPlanId : set the property marketPlanId.
	 */
	public void setMarketPlanId(String marketPlanId) {
		// 营销计划ID	外键
		this.marketPlanId = marketPlanId;
	}
	/**
	 * @return costMode : return the property costMode.
	 */
	public String getCostMode() {
		// 金额要求模式 	默认为“1” 分级抵扣模式，“2”严格抵扣模式
		return costMode;
	}
	/**
	 * @param costMode : set the property costMode.
	 */
	public void setCostMode(String costMode) {
		// 金额要求模式 	默认为“1” 分级抵扣模式，“2”严格抵扣模式
		this.costMode = costMode;
	}
	/**
	 * @return costType : return the property costType.
	 */
	public String getCostType() {
		// 金额类型 		默认为“1” 运费，“2”开单金额
		return costType;
	}
	/**
	 * @param costType : set the property costType.
	 */
	public void setCostType(String costType) {
		// 金额类型 		默认为“1” 运费，“2”开单金额
		this.costType = costType;
	}
	/**
	 * @return value : return the property value.
	 */
	public String getValue() {
		// 使用金额
		return value;
	}
	/**
	 * @param value : set the property value.
	 */
	public void setValue(String value) {
		// 使用金额
		this.value = value;
	}
	/**
	 * @return discount : return the property discount.
	 */
	public String getDiscount() {
		// 抵扣金额		选择分级抵扣模式 时 使用
		return discount;
	}
	/**
	 * @param discount : set the property discount.
	 */
	public void setDiscount(String discount) {
		// 抵扣金额		选择分级抵扣模式 时 使用
		this.discount = discount;
	}
	/**
	 * @return costAddedMode : return the property costAddedMode.
	 */
	public String getCostAddedMode() {
		// 增值费金额
		return costAddedMode;
	}
	/**
	 * @param costAddedMode : set the property costAddedMode.
	 */
	public void setCostAddedMode(String costAddedMode) {
		// 增值费金额
		this.costAddedMode = costAddedMode;
	}
	/**
	 * @return costAddedType : return the property costAddedType.
	 */
	public String getCostAddedType() {
		// 增值费类型	包装、保价、代收、送货、接货费
		return costAddedType;
	}
	/**
	 * @param costAddedType : set the property costAddedType.
	 */
	public void setCostAddedType(String costAddedType) {
		// 增值费类型	包装、保价、代收、送货、接货费
		this.costAddedType = costAddedType;
	}
	/**
	 * @return costAdded : return the property costAdded.
	 */
	public String getCostAdded() {
		// 增值费金额
		return costAdded;
	}
	/**
	 * @param costAdded : set the property costAdded.
	 */
	public void setCostAdded(String costAdded) {
		// 增值费金额
		this.costAdded = costAdded;
	}
	/**
	 * @return begintime : return the property begintime.
	 */
	public Date getBegintime() {
		// 使用期限开始时间
		return begintime;
	}
	/**
	 * @param begintime : set the property begintime.
	 */
	public void setBegintime(Date begintime) {
		// 使用期限开始时间
		this.begintime = begintime;
	}
	/**
	 * @return endtime : return the property endtime.
	 */
	public Date getEndtime() {
		// 使用期限结束时间
		return endtime;
	}
	/**
	 * @param endtime : set the property endtime.
	 */
	public void setEndtime(Date endtime) {
		// 使用期限结束时间
		this.endtime = endtime;
	}
	/**
	 * @return smsContent : return the property smsContent.
	 */
	public String getSmsContent() {
		// 短信内容
		return smsContent;
	}
	/**
	 * @param smsContent : set the property smsContent.
	 */
	public void setSmsContent(String smsContent) {
		// 短信内容
		this.smsContent = smsContent;
	}
	/**
	 * @return describe : return the property describe.
	 */
	public String getDescribe() {
		// 优惠券描述
		return describe;
	}
	/**
	 * @param describe : set the property describe.
	 */
	public void setDescribe(String describe) {
		// 优惠券描述
		this.describe = describe;
	}
	/**
	 * @return goodsLines : return the property goodsLines.
	 */
	public List<GoodsLine> getGoodsLines() {
		// 走货线路信息
		return goodsLines;
	}
	/**
	 * @param goodsLines : set the property goodsLines.
	 */
	public void setGoodsLines(List<GoodsLine> goodsLines) {
		// 走货线路信息
		this.goodsLines = goodsLines;
	}
	/**
	 * @return conditionTypes : return the property conditionTypes.
	 */
	public List<ConditionType> getConditionTypes() {
		// 使用条件信息
		return conditionTypes;
	}
	/**
	 * @param conditionTypes : set the property conditionTypes.
	 */
	public void setConditionTypes(List<ConditionType> conditionTypes) {
		// 使用条件信息
		this.conditionTypes = conditionTypes;
	}
}
