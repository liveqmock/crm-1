/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title GoodsLine.java
 * @package com.deppon.crm.module.coupon.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-11-13
 */
package com.deppon.crm.module.coupon.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description: 走货线路表<br />
 * </p>
 * @title GoodsLine.java
 * @package com.deppon.crm.module.coupon.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-11-13
 */

public class GoodsLine extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 规则类型			1 发券规则  2 优惠劵使用规则
	private String ruleType;
	// 自动发券规则表ID自动券有，手动券无此数据
	private String couponAutoId;
	// 使用规则表ID
	private String couponRuleId;
	// 线路区域要求 	(0、1、2、3)空、走货线路、发货区域、到达区域
	private String regdemand;
	// 出发外场、发货区域 ID
	private String beginDeptOrCityId;
	// 到达外场、到货区域 ID
	private String endDeptOrCityId;
	// 出发外场、发货区域 名称
	private String beginDeptOrCityName;
	// 到达外场、到货区域 名称
	private String endDeptOrCityName;
	/**
	 * @return beginDeptOrCityName : return the property beginDeptOrCityName.
	 */
	public String getBeginDeptOrCityName() {
		// 出发外场、发货区域名称
		return beginDeptOrCityName;
	}
	/**
	 * @param beginDeptOrCityName : set the property beginDeptOrCityName.
	 */
	public void setBeginDeptOrCityName(String beginDeptOrCityName) {
		// 出发外场、发货区域名称
		this.beginDeptOrCityName = beginDeptOrCityName;
	}
	/**
	 * @return endDeptOrCityName : return the property endDeptOrCityName.
	 */
	public String getEndDeptOrCityName() {
		// 到达外场、到货区域 名称
		return endDeptOrCityName;
	}
	/**
	 * @param endDeptOrCityName : set the property endDeptOrCityName.
	 */
	public void setEndDeptOrCityName(String endDeptOrCityName) {
		// 到达外场、到货区域 名称
		this.endDeptOrCityName = endDeptOrCityName;
	}
	/**
	 * @return ruleType : return the property ruleType.
	 */
	public String getRuleType() {
		// 规则类型			1 发券规则  2 优惠劵使用规则
		return ruleType;
	}
	/**
	 * @param ruleType : set the property ruleType.
	 */
	public void setRuleType(String ruleType) {
		// 规则类型			1 发券规则  2 优惠劵使用规则
		this.ruleType = ruleType;
	}
	/**
	 * @return couponAutoId : return the property couponAutoId.
	 */
	public String getCouponAutoId() {
		// 自动发券规则表ID自动券有，手动券无此数据
		return couponAutoId;
	}
	/**
	 * @param couponAutoId : set the property couponAutoId.
	 */
	public void setCouponAutoId(String couponAutoId) {
		// 自动发券规则表ID自动券有，手动券无此数据
		this.couponAutoId = couponAutoId;
	}
	/**
	 * @return couponRuleId : return the property couponRuleId.
	 */
	public String getCouponRuleId() {
		// 使用规则表ID
		return couponRuleId;
	}
	/**
	 * @param couponRuleId : set the property couponRuleId.
	 */
	public void setCouponRuleId(String couponRuleId) {
		// 使用规则表ID
		this.couponRuleId = couponRuleId;
	}
	/**
	 * @return regdemand : return the property regdemand.
	 */
	public String getRegdemand() {
		// 线路区域要求 	(0、1、2、3)空、走货线路、发货区域、到达区域
		return regdemand;
	}
	/**
	 * @param regdemand : set the property regdemand.
	 */
	public void setRegdemand(String regdemand) {
		// 线路区域要求 	(0、1、2、3)空、走货线路、发货区域、到达区域
		this.regdemand = regdemand;
	}
	/**
	 * @return beginDeptOrCityId : return the property beginDeptOrCityId.
	 */
	public String getBeginDeptOrCityId() {
		// 出发外场、发货区域 ID
		return beginDeptOrCityId;
	}
	/**
	 * @param beginDeptOrCityId : set the property beginDeptOrCityId.
	 */
	public void setBeginDeptOrCityId(String beginDeptOrCityId) {
		// 出发外场、发货区域 ID
		this.beginDeptOrCityId = beginDeptOrCityId;
	}
	/**
	 * @return endDeptOrCityId : return the property endDeptOrCityId.
	 */
	public String getEndDeptOrCityId() {
		// 到达外场、发货区域 ID
		return endDeptOrCityId;
	}
	/**
	 * @param endDeptOrCityId : set the property endDeptOrCityId.
	 */
	public void setEndDeptOrCityId(String endDeptOrCityId) {
		// 到达外场、发货区域 ID
		this.endDeptOrCityId = endDeptOrCityId;
	}
	@Override
	public int hashCode() {
		
		return (regdemand+beginDeptOrCityId+endDeptOrCityId).hashCode();
	}
	@Override
	public boolean equals(Object arg0) {
		//如果该实体与arg0的地址相同则返回true
		if(this==arg0){
			return true;
		}
		//如果arg0为空则返回false
        if(arg0==null){
        	return false;
        }
        //如果该实体的class与arg0的class不同则返回false
        if( getClass()!= arg0.getClass()){ 
        	return false;
        }
        //将arg0强制类型转换为GOODSLINE
        GoodsLine g = (GoodsLine)arg0;
        //如果线路要求，出发部门，到达部门的字符串相等则返回true
        return (regdemand+beginDeptOrCityId+endDeptOrCityId)
        		.equals(g.regdemand+g.beginDeptOrCityId+g.endDeptOrCityId);       	
	}
	
}
