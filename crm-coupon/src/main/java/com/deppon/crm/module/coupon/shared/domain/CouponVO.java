/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CouponVO.java
 * @package com.deppon.crm.module.coupon.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-11-21
 */
package com.deppon.crm.module.coupon.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description: 优惠券前台VO<br />
 * </p>
 * @title CouponVO.java
 * @package com.deppon.crm.module.coupon.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-11-21
 */

public class CouponVO  extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 优惠券查询对象
	CouponSearchCondition condition;
	// 优惠券结果对象
	CouponResultVO result;
	
	/**
	 * @return condition : return the property condition.
	 */
	public CouponSearchCondition getCondition() {
		// 优惠券查询对象
		return condition;
	}
	/**
	 * @param condition : set the property condition.
	 */
	public void setCondition(CouponSearchCondition condition) {
		// 优惠券查询对象
		this.condition = condition;
	}
	/**
	 * @return result : return the property result.
	 */
	public CouponResultVO getResult() {
		// 优惠券结果对象
		return result;
	}
	/**
	 * @param result : set the property result.
	 */
	public void setResult(CouponResultVO result) {
		// 优惠券结果对象
		this.result = result;
	}
}
