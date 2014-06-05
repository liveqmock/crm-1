package com.deppon.crm.module.coupon.server.dao;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.coupon.shared.domain.AutoCouponCost;
import com.deppon.crm.module.coupon.shared.domain.ConditionType;
import com.deppon.crm.module.coupon.shared.domain.Coupon;
import com.deppon.crm.module.coupon.shared.domain.CouponRule;
import com.deppon.crm.module.coupon.shared.domain.GoodsLine;
import com.deppon.crm.module.coupon.shared.domain.HandMarketPlan;
import com.deppon.crm.module.coupon.shared.domain.Marketplan;
import com.deppon.crm.module.coupon.shared.domain.RuleCouponAuto;
import com.deppon.crm.module.coupon.shared.domain.RuleCouponHand;

/**   
 * <p>
 * Description:营销计划Dao<br />
 * </p>
 * @title IMarkerPlanDao.java
 * @package com.deppon.crm.module.coupon.server.dao 
 * @author 钟琼
 * @version 0.1 2012-11-16
 */
public interface IMarketPlanDao {
	/**   
	 * <p>
	 * Description:新增手动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-15
	 * @param ruleCouponHand 手动发券实体类
	 * @return int
	 */
	public int addMarketplanVOByRuleCouponHand(RuleCouponHand ruleCouponHand);
	/**   
	 * <p>
	 * Description:新增自动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-15
	 * @param ruleCouponAuto 自动发券实体类
	 * @return int
	 */
	public int addMarketplanVOByRuleCouponAuto(RuleCouponAuto ruleCouponAuto);
	
	/**   
	 * <p>
	 * Description:新增优惠券使用条件<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-15
	 * @param couponRule 优惠券使用条件实体类
	 * @return int
	 */
	public int addMarketplanVOByRuleCouponRule(CouponRule couponRule);
	/**   
	 * <p>
	 * Description:新增营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-15
	 * @param marketplan 营销计划实体类
	 * @return int
	 */
	public int addMarketplanVOByMarketplan(Marketplan marketplan);
	/**   
	 * <p>
	 * Description:新增约束条件<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-16
	 * @param conditionType 约束条件实体类
	 * @return int
	 */
	public int addMarketplanVOByConditionType(ConditionType conditionType);
	/**   
	 * <p>
	 * Description:新增自动返券金额表<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-16
	 * @param autoCouponCost 自动返券金额表实体类
	 * @return int
	 */
	public int addMarketplanVOByAutoCouponCost(AutoCouponCost autoCouponCost);
	/**   
	 * <p>
	 * Description:新增走货线路表<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-16
	 * @param createGoodsLine 走货线路表实体类
	 * @return int
	 */
	public int addMarketplanVOByGoodsLine(GoodsLine createGoodsLine);
	/**   
	 * <p>
	 * Description:查询营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-17
	 * @param marketPlaneVO 新增营销计划-发券规则VO
	 * @param start
	 * @param limit
	 * @return List<Marketplan>
	 */
	public List<Marketplan> searchMarketPlan(Marketplan marketplan,int start,int limit);
	/**   
	 * <p>
	 * Description:根据 营销计划ID 查询营销计划详情<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-17
	 * @param marketplanId 营销计划ID
	 * @return Marketplan
	 */
	public Marketplan searchMarketPlanDetail(String marketplanId);
	/**
	 * <p>
	 * Description: 根据营销计划查询规则<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-16
	 * @param marketPlanId
	 * @return CouponRule
	 */
	public CouponRule getCouponByMarketPlanId(String marketPlanId);
	/**   
	 * <p>
	 * Description:根据 营销计划ID 查询手动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-17
	 * @param marketplanId 营销计划ID
	 * @return RuleCouponHand
	 */
	public RuleCouponHand searchRuleCouponHand(String marketplanId);
	/**   
	 * <p>
	 * Description:根据 营销计划ID 查询自动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-17
	 * @param marketplanId 营销计划ID
	 * @return RuleCouponAuto
	 */
	public RuleCouponAuto searchRuleCouponAuto(String marketplanId);
	/**   
	 * <p>
	 * Description:根据 营销计划ID 查询自动发券规则中的 使用条件信息<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-17
	 * @param ruleCouponAutoId 自动发券ID，ruleCouponRuleType 规则id
	 * @return List<ConditionType>
	 */
	public List<ConditionType> searchRuleCPAByConditionTypes(String ruleCouponAutoId,String ruleCouponRuleType);
	/**   
	 * <p>
	 * Description:根据 营销计划ID 查询自动发券规则中的 返券金额信息<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-17
	 * @param ruleCouponAutoId 自动发券ID
	 * @return List<AutoCouponCost>
	 */
	public List<AutoCouponCost> searchRuleCPAByautoCouponCost(String ruleCouponAutoId);
	/**   
	 * <p>
	 * Description:根据 营销计划ID 查询自动发券规则中的 线路要求信息<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-17
	 * @param ruleCouponAutoId 自动发券ID，ruleCouponRuleType 规则id
	 * @return List<AutoCouponCost>
	 */
	public List<GoodsLine> searchRuleCPAByGoodsLine(String ruleCouponAutoId,String ruleCouponRuleType);

	/**
	 * <p>
	 * Description: 根据优惠券类型和优惠券类型ID查询手动发券营销计划<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-12-05
	 * @param couponType
	 * @param typeId
	 * @return HandMarketPlan 手动发券营销计划 
	 */
	public HandMarketPlan searchHandMarketPlanByCouponTypeAndTypeId(String couponType,String typeId);
	/**
	 * <p>
	 * Description:根据 营销计划ID 查询返券类型<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param marktPlanNumber 营销计划编码
	 * @return Marketplan
	 */
	public Marketplan getCouponTypeByMarketPlanId(String marktPlanNumber);
	/**   
	 * <p>
	 * Description:更新营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param marketplan 营销计划
	 * @return int
	 */
	public int updateMarketPlan(Marketplan marketplan);
	/**   
	 * <p>
	 * Description:更新手动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param ruleCouponHand 手动发券
	 * @return int
	 */
	public int updateCouponHand(RuleCouponHand ruleCouponHand);
	/**   
	 * <p>
	 * Description:更新自动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param ruleCouponAuto 自动发券
	 * @return int
	 */
	public int updateCouponAuto(RuleCouponAuto ruleCouponAuto);
	/**   
	 * <p>
	 * Description:更新自动发券的约束条件<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param conditionTypes 约束条件 ，id
	 * @return boolean
	 */
	public boolean updateAutoConditionTypes(List<ConditionType> conditionTypes,String id);
	/**   
	 * <p>
	 * Description:更新使用条件的约束条件<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param conditionTypes 约束条件
	 * @return boolean
	 */
	public boolean updateRuleConditionTypes(List<ConditionType> conditionTypes,String id);
	/**   
	 * <p>
	 * Description:更新返券金额<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param autoCouponCost 返券金额 ，id
	 * @return boolean
	 */
	public boolean updateAutoCouponCost(List<AutoCouponCost> autoCouponCost,String id);
	/**   
	 * <p>
	 * Description:更新自动发券的走货线路<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param createGoodsLine 走货线路 ，id
	 * @return boolean
	 */
	public boolean updateAutoCreateGoodsLine(List<GoodsLine> createGoodsLine,String id);
	/**   
	 * <p>
	 * Description:更新使用规则的走货线路<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param createGoodsLines 走货线路 ,id
	 * @return boolean
	 */
	public boolean updateRuleCreateGoodsLine(List<GoodsLine> createGoodsLines,String id);
	/**   
	 * <p>
	 * Description:更新优惠券使用规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param couponRule 使用规则划
	 * @return int
	 */
	public int updateCouponRule(CouponRule couponRule);
	/**   
	 * <p>
	 * Description:查询营销计划 条数<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-22
	 * @param marketPlane 新增营销计划-发券规则
	 * @return String
	 */
	public String searchMarketPlanCount(Marketplan marketplan);
	/**
	 * <p>
	 * Description:删除营销计划<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-23
	 * @param marketPlanId
	 * @return int
	 */
	public int deleteMarketPlan(String marketPlanId);
	/**
	 * <p>
	 * Description:删除手动发券规则<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param marketPlanId，id
	 * @return int
	 */
	public int deleteCouponHand(String marketPlanId);
	/**
	 * <p>
	 * Description:删除自动发券规则<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param marketPlanId，id
	 * @return int
	 */
	public int deleteCouponAuto(String marketPlanId);
	/**
	 * <p>
	 * Description:删除使用条件信息<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param conditiontypeCouponsend，id
	 * @return int
	 */
	public int deleteConditionTypes(String conditiontypeCouponsend, String id);
	/**
	 * <p>
	 * Description:删除返券金额<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param conditiontypeCouponsend，id
	 * @return int
	 */
	public int deleteCouponCost(String conditiontypeCouponsend, String id);
	/**
	 * <p>
	 * Description:删除走货线路<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param conditiontypeCouponsend，id
	 * @return int
	 */
	public int deleteCreateGoodsLine(String conditiontypeCouponsend, String id);
	/**
	 * <p>
	 * Description:删除发券规则<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param conditiontypeCouponsend，id
	 * @return int
	 */
	public int deleteCouponRule(String marketPlanId);
	/**   
	 * <p>
	 * Description:更新所有已启用的营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-27
	 * @param 
	 * @return boolean
	 */
	public int updateMarketPlanForSchedual();
	/**
	 * <p>
	 * Description: 每天检查更新所有状态（不包括已过期）优惠券 状态是否过期，<br/>
	 *  过期的把状态置为：已过期<br/>
	 * </p> 
	 * @author 钟琼
	 * @version 0.1 2012-11-29
	 * @return int
	 */
	public int updateCouponStatesByOverdueForSchedual();
	/**
	 * <p>
	 * Description:根据部门标杆编码和活动类型查询已启用的手动券营销计划<br/>
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-9-4
	 * @param CouponForInterface
	 * @return String
	 */
	public Coupon searchMarketPlanByDeptStandardCodeAndActivityType(Map<String,Object> map);
	/**
	 * <p>
	 * Description:根据部门标杆编码和活动类型查询手动券营销计划名称<br/>
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-9-4
	 * @param CouponForInterface
	 * @return String
	 */
	public String searchMPNameMarketPlanByDeptStandardCodeAndActivityType(Map<String,Object> map);
}
