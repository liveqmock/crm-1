/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ConditionType.java
 * @package com.deppon.crm.module.coupon.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-11-13
 */
package com.deppon.crm.module.coupon.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ConditionType.java
 * @package com.deppon.crm.module.coupon.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-11-13
 */

public class ConditionType extends BaseEntity{
	
	private static final long serialVersionUID = 611938467356592487L;
	// 规则类型
	private String ruleType;
	// 自动发券规则ID
	private String couponAutoId;
	// 优惠券使用规则ID
	private String couponRuleId;
	// 约束条件类型		（1、2、3、4）产品类型、客户等级、订单来源、客户行业
	private String type;
	// 约束条件值
	private String value;
	
	/**
	 * @return ruleType : return the property ruleType.
	 */
	public String getRuleType() {
		return ruleType;
	}
	/**
	 * @param ruleType : set the property ruleType.
	 */
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	/**
	 * @return couponAutoId : return the property couponAutoId.
	 */
	public String getCouponAutoId() {
		return couponAutoId;
	}
	/**
	 * @param couponAutoId : set the property couponAutoId.
	 */
	public void setCouponAutoId(String couponAutoId) {
		this.couponAutoId = couponAutoId;
	}
	/**
	 * @return couponRuleId : return the property couponRuleId.
	 */
	public String getCouponRuleId() {
		return couponRuleId;
	}
	/**
	 * @param couponRuleId : set the property couponRuleId.
	 */
	public void setCouponRuleId(String couponRuleId) {
		this.couponRuleId = couponRuleId;
	}
	/**
	 * @return type : return the property type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type : set the property type.
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return value : return the property value.
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value : set the property value.
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
