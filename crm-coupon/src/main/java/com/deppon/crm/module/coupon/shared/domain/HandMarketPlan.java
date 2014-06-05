package com.deppon.crm.module.coupon.shared.domain;
/**   
 * <p>
 * Description:当手动券的营销计划信息<br />
 * </p>
 * @author ZhouYuan
 * @version 0.1 2012-12-05
 */
public class HandMarketPlan extends Marketplan{
	
	private static final long serialVersionUID = 1L;
	//优惠券创建规则 
	private int createRuleId;
	//发券类型是实时生成还是后台生成
	private String typeId;
	//优惠券面值
	private String value;
	//使用规则ID
	private String useRuleId;
	//发券数量
	private int quantity;
	//已生成优惠券数量
	private int couponCreateNum;
	/**
	 * <p>
	 * Description: 获得优惠券创建规则<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 优惠券创建规则
	 */
	public int getCreateRuleId() {
		//优惠券创建规则 
		return createRuleId;
	}
	/**
	 * <p>
	 * Description: 设置优惠券创建规则<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setCreateRuleId(int createRuleId) {
		//优惠券创建规则 
		this.createRuleId = createRuleId;
	}
	/**
	 * <p>
	 * Description: 获得发券类型是实时生成还是后台生成<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 发券类型是实时生成还是后台生成
	 */
	public String getTypeId() {
		//发券类型是实时生成还是后台生成
		return typeId;
	}
	/**
	 * <p>
	 * Description: 设置发券类型是实时生成还是后台生成<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setTypeId(String typeId) {
		//发券类型是实时生成还是后台生成
		this.typeId = typeId;
	}
	/**
	 * <p>
	 * Description: 获得优惠券面值<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 优惠券面值
	 */
	public String getValue() {
		//优惠券面值
		return value;
	}
	/**
	 * <p>
	 * Description: 设置优惠券面值<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setValue(String value) {
		//优惠券面值
		this.value = value;
	}
	/**
	 * <p>
	 * Description: 获得使用规则ID<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 使用规则ID
	 */
	public String getUseRuleId() {
		//使用规则ID
		return useRuleId;
	}
	/**
	 * <p>
	 * Description: 设置使用规则ID<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setUseRuleId(String useRuleId) {
		//使用规则ID
		this.useRuleId = useRuleId;
	}
	/**
	 * <p>
	 * Description: 获得发券数量<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 发券数量
	 */
	public int getQuantity() {
		//发券数量
		return quantity;
	}
	/**
	 * <p>
	 * Description: 设置发券数量<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setQuantity(int quantity) {
		//发券数量
		this.quantity = quantity;
	}
	/**
	 * <p>
	 * Description: 获得已生成优惠券数量<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 已生成优惠券数量
	 */
	public int getCouponCreateNum() {
		//已生成优惠券数量
		return couponCreateNum;
	}
	/**
	 * <p>
	 * Description: 设置已生成优惠券数量<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setCouponCreateNum(int couponCreateNum) {
		//已生成优惠券数量
		this.couponCreateNum = couponCreateNum;
	} 
	
}
