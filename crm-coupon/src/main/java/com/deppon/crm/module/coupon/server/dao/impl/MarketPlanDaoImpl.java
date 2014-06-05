package com.deppon.crm.module.coupon.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.coupon.server.dao.IMarketPlanDao;
import com.deppon.crm.module.coupon.shared.domain.AutoCouponCost;
import com.deppon.crm.module.coupon.shared.domain.ConditionType;
import com.deppon.crm.module.coupon.shared.domain.Coupon;
import com.deppon.crm.module.coupon.shared.domain.CouponRule;
import com.deppon.crm.module.coupon.shared.domain.GoodsLine;
import com.deppon.crm.module.coupon.shared.domain.HandMarketPlan;
import com.deppon.crm.module.coupon.shared.domain.Marketplan;
import com.deppon.crm.module.coupon.shared.domain.RuleCouponAuto;
import com.deppon.crm.module.coupon.shared.domain.RuleCouponHand;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**   
 * <p>
 * Description:营销计划Dao<br/>
 * </p>
 * @title MarketPlanDaoImpl.java
 * @package com.deppon.crm.module.coupon.server.dao.impl 
 * @author 钟琼
 * @version 0.1 2012-11-16
 */
public class MarketPlanDaoImpl extends iBatis3DaoImpl implements IMarketPlanDao{
	private final String NAMESPACE_MARKETPLAN="com.deppon.crm.module.coupon.shared.domain.Marketplan.";
	//新增  营销计划
	private final String INSERT_MARKETPLAN="insertMarketPlan";
	//新增  手动发券规则
	private final String INSERT_RULECOUPONHAND="insertRuleCouponHand";
	//新增  自动发券规则
	private final String INSERT_RULECOUPONAUTO="insertRuleCouponAuto";
	//新增  约束条件信息
	private final String INSERT_CONDITIONTYPE="insertConditionType";
	//新增  约束条件信息
	private final String INSERT_AUTOCOUPONCOST="insertAutoCouponCost";
	//新增 走货线路信息
	private final String INSERT_GOODSLINE="insertGoodsLine";
	//新增 优惠券使用条件 
	private final String INSERT_COUPONRULE="insertCouponRule";
	//查询 营销计划 
	private final String SELECT_MARKETPLAN="selectMarketPlan";
	//查询 营销计划详情 
	private final String SELECT_MARKETPLANDETAIL="selectMarketPlanDetail";
	// 根据营销计划查询优惠券使用规则
	private final String SELECT_COUPONBYMARKETPLANID = "searchCouponRuleByMarketPlanId";
	//查询 手动发券规则
	private final String SELECT_RULECOUPONHAND="selectRuleCouponHand";
	//查询 自动发券规则
	private final String SELECT_RULECOUPONAUTO="selectAuleCouponAuto";
	//查询 自动发券规则中的 使用条件信息
	private final String SELECT_RULECONDITIONTYPE="selectRuleConditionType";
	//查询 自动发券规则中的 返券金额信息
	private final String SELECT_AUTOCOUPONCOST="selectAutoCouponCost";
	//查询 自动发券规则中的 线路要求信息
	private final String SELECT_GOODSLINE="selectGoodsLine";
	// 根据营销计划编码 查询优惠券发券规则
	private final String SELECT_COUPONTYPEBYMARKETPLANID = "selectCouponTypeByMarketPlanId";
	// 查询营销计划 条数
	private final String SELECT_MARKETPLANCOUNT = "selectMarketPlanCount";
	//根据优惠券类型和优惠券类型ID查询手动发券营销计划
	private final String SEARCH_HANDMARKETPLANBYCOUPONTYPEANDTYPEID = "searchHandMarketPlanByCouponTypeAndTypeId";
	//根据部门标杆编码和活动类型查询已启用的手动券营销计划
	private final String SEARCH_MARKETPLANBYDEPTSTANDARDCODEANDACTIVITYTYPE= "searchMarketPlanByDeptStandardCodeAndActivityType";
	//根据部门标杆编码和活动类型查询待创建营销计划名称
	private final String SEARCH_MPNAMEMARKETPLANBYDEPTSTANDARDCODEANDACTIVITYTYPE= "searchMPNameMarketPlanByDeptStandardCodeAndActivityType";
	// 更新所有已启用的营销计划
	private final String  UPDATE_MARKETPLANFORSCHEDUAL = "updateMarketPlanForSchedual";
	// 更新营销计划
	private final String UPDATE_UPDATEMARKETPLAN = "updateUpdateMarketPlan";
	// 更新手动发券规则
	private final String UPDATE_UPDATECOUPONHAND = "updateUpdateCouponHand";
	// 更新自动发券规则
	private final String UPDATE_UPDATECOUPONAUTO = "updateUpdateCouponAuto";
	// 更新约束条件
	private final String UPDATE_UPDATECONDITIONTYPES = "updateUpdateConditionTypes";
	// 更新返券金额
	private final String UPDATE_UPDATEAUTOCOUPONCOST = "updateUpdateAutoCouponCost";
	// 更新走货线路
	private final String UPDATE_UPDATECREATEGOODSLINE = "updateUpdateCreateGoodsLine";
	// 更新使用规则
	private final String UPDATE_UPDATECOUPONRULE = "updateUpdateCouponRule";
	// 更新优惠券为过期状态
	private final String UPDATE_COUPONSTATESBYOVERDUEFORSCHEDUAL = "updateCouponStatesByOverdueForSchedual";
	// 删除 营销计划
	private final String DELETE_MARKETPLAN = "deleteMarketPlan";
	// 删除 手动发券规则
	private final String DELETE_COUPONHAND = "deleteCouponHand";
	// 删除 自动发券规则
	private final String DELETE_COUPONAUTO = "deleteCouponAuto";
	// 删除 使用条件
	private final String DELETE_CONDITIONTYPES = "deleteConditionType";
	// 删除 返券金额
	private final String DELETE_COUPONCOST = "deleteCouponCost";
	// 删除走货线路
	private final String DELETE_GOODSLINE = "deleteGoodLine";
	// 删除发券规则
	private final String DELETE_COUPOONRULE = "deleteCouponRule";
	
	/**   
	 * <p>
	 * Description:新增手动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-15
	 * @param ruleCouponHand 手动发券实体类
	 * @return int
	 */
	@Override
	public int addMarketplanVOByRuleCouponHand(RuleCouponHand ruleCouponHand) {
		return this.getSqlSession().insert(
				NAMESPACE_MARKETPLAN+INSERT_RULECOUPONHAND,ruleCouponHand);
	}
	/**   
	 * <p>
	 * Description:新增自动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-15
	 * @param ruleCouponAuto 自动发券实体类
	 * @return int
	 */
	@Override
	public int addMarketplanVOByRuleCouponAuto(RuleCouponAuto ruleCouponAuto) {
		return this.getSqlSession().insert(
				NAMESPACE_MARKETPLAN+INSERT_RULECOUPONAUTO,ruleCouponAuto);
	}
	/**   
	 * <p>
	 * Description:新增营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-15
	 * @param marketplan 营销计划实体类
	 * @return int
	 */
	@Override
	public int addMarketplanVOByMarketplan(Marketplan marketplan) {
		return this.getSqlSession().insert(
				NAMESPACE_MARKETPLAN+INSERT_MARKETPLAN,marketplan);
	}
	/**   
	 * <p>
	 * Description:新增优惠券使用条件<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-15
	 * @param couponRule 优惠券使用条件实体类
	 * @return int
	 */
	@Override
	public int addMarketplanVOByRuleCouponRule(CouponRule couponRule) {
		return this.getSqlSession().insert(
				NAMESPACE_MARKETPLAN+INSERT_COUPONRULE,couponRule);
	}
	/**   
	 * <p>
	 * Description:新增约束条件<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-16
	 * @param conditionType 约束条件实体类
	 * @return int
	 */
	@Override
	public int addMarketplanVOByConditionType(ConditionType conditionType) {
		return this.getSqlSession().insert(
				NAMESPACE_MARKETPLAN+INSERT_CONDITIONTYPE,conditionType);
	}
	/**   
	 * <p>
	 * Description:新增自动返券金额表<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-16
	 * @param autoCouponCost 自动返券金额表实体类
	 * @return int
	 */
	@Override
	public int addMarketplanVOByAutoCouponCost(AutoCouponCost autoCouponCost) {
		return this.getSqlSession().insert(
				NAMESPACE_MARKETPLAN+INSERT_AUTOCOUPONCOST,autoCouponCost);
	}
	/**   
	 * <p>
	 * Description:新增走货线路表<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-16
	 * @param createGoodsLine 走货线路表实体类
	 * @return int
	 */
	@Override
	public int addMarketplanVOByGoodsLine(GoodsLine createGoodsLine) {
		return this.getSqlSession().insert(
				NAMESPACE_MARKETPLAN+INSERT_GOODSLINE,createGoodsLine);
	}
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
	@SuppressWarnings("unchecked")
	@Override
	public List<Marketplan> searchMarketPlan(Marketplan marketplan,int start,int limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("marketplan", marketplan);
		map.put("start", start);
		map.put("limit",limit);
		return this.getSqlSession().selectList(
				NAMESPACE_MARKETPLAN+SELECT_MARKETPLAN,map);
	}
	/**   
	 * <p>
	 * Description:根据 营销计划ID 查询营销计划详情<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-17
	 * @param marketplanId 营销计划ID
	 * @return Marketplan
	 */
	@Override
	public Marketplan searchMarketPlanDetail(String marketplanId) {
		return (Marketplan) this.getSqlSession().selectOne(
				NAMESPACE_MARKETPLAN+SELECT_MARKETPLANDETAIL,marketplanId);
	}
	/**
	 * <p>
	 * Description: 根据营销计划查询规则<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-16
	 * @param marketPlanId
	 * @return CouponRule
	 */
	@Override
	public CouponRule getCouponByMarketPlanId(String marketPlanId) {
		return (CouponRule) this.getSqlSession().selectOne(NAMESPACE_MARKETPLAN+
				SELECT_COUPONBYMARKETPLANID, marketPlanId);
		
	}
	/**   
	 * <p>
	 * Description:根据 营销计划ID 查询手动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-17
	 * @param marketplanId 营销计划ID
	 * @return RuleCouponHand
	 */
	@Override
	public RuleCouponHand searchRuleCouponHand(String marketplanId) {
		return (RuleCouponHand) this.getSqlSession().selectOne(
				NAMESPACE_MARKETPLAN+SELECT_RULECOUPONHAND,marketplanId);
	}
	/**   
	 * <p>
	 * Description:根据 营销计划ID 查询自动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-17
	 * @param marketplanId 营销计划ID
	 * @return RuleCouponAuto
	 */
	@Override
	public RuleCouponAuto searchRuleCouponAuto(String marketplanId){
		return (RuleCouponAuto) this.getSqlSession().selectOne(
				NAMESPACE_MARKETPLAN+SELECT_RULECOUPONAUTO,marketplanId);
	}
	/**   
	 * <p>
	 * Description:根据 营销计划ID 查询自动发券规则中的 使用条件信息<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-17
	 * @param ruleCouponAutoId 自动发券ID，ruleCouponRuleType 规则id
	 * @return List<ConditionType>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ConditionType> searchRuleCPAByConditionTypes(String ruleCouponAutoId,String ruleCouponRuleType) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("ruleCouponAutoId", ruleCouponAutoId);
		map.put("ruleCouponRuleType", ruleCouponRuleType);
		return this.getSqlSession().selectList(
				NAMESPACE_MARKETPLAN+SELECT_RULECONDITIONTYPE,map);
	}
	/**   
	 * <p>
	 * Description:根据 营销计划ID 查询自动发券规则中的 返券金额信息<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-17
	 * @param ruleCouponAutoId 自动发券ID
	 * @return List<AutoCouponCost>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AutoCouponCost> searchRuleCPAByautoCouponCost(
			String ruleCouponAutoId) {
		return this.getSqlSession().selectList(
				NAMESPACE_MARKETPLAN+SELECT_AUTOCOUPONCOST,ruleCouponAutoId);
	}
	/**   
	 * <p>
	 * Description:根据 营销计划ID 查询自动发券规则中的 线路要求信息<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-17
	 * @param ruleCouponAutoId 自动发券ID，ruleCouponRuleType 规则id
	 * @return List<AutoCouponCost>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GoodsLine> searchRuleCPAByGoodsLine(String ruleCouponAutoId,
			String ruleCouponRuleType) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("ruleCouponAutoId", ruleCouponAutoId);
		map.put("ruleCouponRuleType", ruleCouponRuleType);
		return this.getSqlSession().selectList(
				NAMESPACE_MARKETPLAN+SELECT_GOODSLINE,map);
	}
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
	public HandMarketPlan searchHandMarketPlanByCouponTypeAndTypeId(String couponType,String typeId){
		Map<String,String> condition = new HashMap<String,String>();
		condition.put("couponType", couponType);
		condition.put("typeId", typeId);
		return (HandMarketPlan)this.getSqlSession().selectOne(NAMESPACE_MARKETPLAN+
				SEARCH_HANDMARKETPLANBYCOUPONTYPEANDTYPEID, condition );		
	}
	/**
	 * <p>
	 * Description:根据 营销计划ID 查询返券类型<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param marktPlanNumber 营销计划编码
	 * @return Marketplan
	 */
	@Override
	public Marketplan getCouponTypeByMarketPlanId(String marktPlanNumber) {
		return (Marketplan) this.getSqlSession().selectOne(
				NAMESPACE_MARKETPLAN+SELECT_COUPONTYPEBYMARKETPLANID,marktPlanNumber);
	}
	/**
	 * <p>
	 * Description:根据部门标杆编码和活动类型查询已启用的手动券营销计划<br/>
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-9-4
	 * @param CouponForInterface
	 * @return String
	 */
	@Override
	public Coupon searchMarketPlanByDeptStandardCodeAndActivityType(Map<String,Object> map){
		return (Coupon)this.getSqlSession().
				selectOne(NAMESPACE_MARKETPLAN+SEARCH_MARKETPLANBYDEPTSTANDARDCODEANDACTIVITYTYPE,map);
	}
	/**
	 * <p>
	 * Description:根据部门标杆编码和活动类型查询手动券营销计划名称<br/>
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-9-4
	 * @param CouponForInterface
	 * @return String
	 */
	public String searchMPNameMarketPlanByDeptStandardCodeAndActivityType(Map<String,Object> map){
		return (String)this.getSqlSession().selectOne(NAMESPACE_MARKETPLAN+
					SEARCH_MPNAMEMARKETPLANBYDEPTSTANDARDCODEANDACTIVITYTYPE,map);
	}
	/**   
	 * <p>
	 * Description:更新营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param marketplan 营销计划
	 * @return int
	 */
	@Override
	public int updateMarketPlan(Marketplan marketplan) {
		return this.getSqlSession().update(
				NAMESPACE_MARKETPLAN+UPDATE_UPDATEMARKETPLAN, marketplan);
	}
	/**   
	 * <p>
	 * Description:更新手动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param ruleCouponHand 手动发券
	 * @return int
	 */
	@Override
	public int updateCouponHand(RuleCouponHand ruleCouponHand) {
		return this.getSqlSession().update(
				NAMESPACE_MARKETPLAN+UPDATE_UPDATECOUPONHAND, ruleCouponHand);
	}
	/**   
	 * <p>
	 * Description:更新自动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param ruleCouponAuto 自动发券
	 * @return int
	 */
	@Override
	public int updateCouponAuto(RuleCouponAuto ruleCouponAuto) {
		return this.getSqlSession().update(
				NAMESPACE_MARKETPLAN+UPDATE_UPDATECOUPONAUTO, ruleCouponAuto);
	}
	/**   
	 * <p>
	 * Description:更新自动发券的约束条件<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param conditionTypes 约束条件
	 * @return boolean
	 */
	@Override
	public boolean updateAutoConditionTypes(List<ConditionType> conditionTypes,String id) {
		for(ConditionType conditionType : conditionTypes){
			conditionType.setCouponAutoId(id);
			this.getSqlSession().update(
					NAMESPACE_MARKETPLAN+UPDATE_UPDATECONDITIONTYPES, conditionType);
		}
		return true;
	}
	/**   
	 * <p>
	 * Description:更新使用条件的约束条件<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param conditionTypes 约束条件
	 * @return boolean
	 */
	@Override
	public boolean updateRuleConditionTypes(List<ConditionType> conditionTypes,String id) {
		for(ConditionType conditionType : conditionTypes){
			conditionType.setCouponRuleId(id);
			this.getSqlSession().update(
					NAMESPACE_MARKETPLAN+UPDATE_UPDATECONDITIONTYPES, conditionType);
		}
		return true;
	}
	/**   
	 * <p>
	 * Description:更新返券金额<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param autoCouponCosts 返券金额
	 * @return boolean
	 */
	@Override
	public boolean updateAutoCouponCost(List<AutoCouponCost> autoCouponCosts,String id) {
		for(AutoCouponCost autoCouponCost : autoCouponCosts){
			autoCouponCost.setCouponAutoId(id);
			this.getSqlSession().update(
					NAMESPACE_MARKETPLAN+UPDATE_UPDATEAUTOCOUPONCOST, autoCouponCost);
		}
		return true;
	}
	/**   
	 * <p>
	 * Description:更新自动发券的走货线路<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param createGoodsLines 走货线路 ,id
	 * @return boolean
	 */
	@Override
	public boolean updateAutoCreateGoodsLine(List<GoodsLine> createGoodsLines,String id) {
		for(GoodsLine createGoodsLine : createGoodsLines){
			createGoodsLine.setCouponAutoId(id);
			this.getSqlSession().update(
					NAMESPACE_MARKETPLAN+UPDATE_UPDATECREATEGOODSLINE, createGoodsLine);
		}
		return true;
	};
	/**   
	 * <p>
	 * Description:更新使用规则的走货线路<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param createGoodsLines 走货线路 ,id
	 * @return boolean
	 */
	@Override
	public boolean updateRuleCreateGoodsLine(List<GoodsLine> createGoodsLines,String id) {
		for(GoodsLine createGoodsLine : createGoodsLines){
			createGoodsLine.setCouponAutoId(id);
			this.getSqlSession().update(
					NAMESPACE_MARKETPLAN+UPDATE_UPDATECREATEGOODSLINE, createGoodsLine);
		}
		return true;
	};
	/**   
	 * <p>
	 * Description:更新优惠券使用规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param couponRule 使用规则划
	 * @return int
	 */
	public int updateCouponRule(CouponRule couponRule){
		return getSqlSession().update(
				NAMESPACE_MARKETPLAN+UPDATE_UPDATECOUPONRULE, couponRule);
	}
	/**   
	 * <p>
	 * Description:查询营销计划 条数<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-22
	 * @param marketPlane 新增营销计划-发券规则
	 * @return String
	 */
	@Override
	public String searchMarketPlanCount(Marketplan marketplan) {
		return (String) this.getSqlSession().selectOne(
				NAMESPACE_MARKETPLAN+SELECT_MARKETPLANCOUNT,marketplan);
	}
	/**
	 * <p>
	 * Description:删除营销计划<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-23
	 * @param marketPlanId
	 * @return int
	 */
	@Override
	public int deleteMarketPlan(String marketPlanId) {
		return this.getSqlSession().delete(
				NAMESPACE_MARKETPLAN+DELETE_MARKETPLAN,marketPlanId);
	}
	/**
	 * <p>
	 * Description:删除手动发券规则<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param marketPlanId
	 * @return int
	 */
	@Override
	public int deleteCouponHand(String marketPlanId) {
		return this.getSqlSession().delete(
				NAMESPACE_MARKETPLAN+DELETE_COUPONHAND,marketPlanId);
	}
	/**
	 * <p>
	 * Description:删除自动发券规则<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param marketPlanId，id
	 * @return int
	 */
	@Override
	public int deleteCouponAuto(String marketPlanId) {
		return this.getSqlSession().delete(
				NAMESPACE_MARKETPLAN+DELETE_COUPONAUTO,marketPlanId);
	}
	/**
	 * <p>
	 * Description:删除使用条件信息<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param conditiontypeCouponsend，id
	 * @return int
	 */
	@Override
	public int deleteConditionTypes(String conditiontypeCouponsend, String id) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("states", conditiontypeCouponsend);
		map.put("id", id);
		return this.getSqlSession().delete(
				NAMESPACE_MARKETPLAN+DELETE_CONDITIONTYPES,map);
	}
	/**
	 * <p>
	 * Description:删除返券金额<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param conditiontypeCouponsend，id
	 * @return int
	 */
	@Override
	public int deleteCouponCost(String conditiontypeCouponsend, String id) {
		return this.getSqlSession().delete(
				NAMESPACE_MARKETPLAN+DELETE_COUPONCOST,id);
	}
	/**
	 * <p>
	 * Description:删除走货线路<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param conditiontypeCouponsend，id
	 * @return int
	 */
	@Override
	public int deleteCreateGoodsLine(String conditiontypeCouponsend,
			String id) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("states", conditiontypeCouponsend);
		map.put("id", id);
		return this.getSqlSession().delete(
				NAMESPACE_MARKETPLAN+DELETE_GOODSLINE,map);
	}
	/**
	 * <p>
	 * Description:删除发券规则<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param conditiontypeCouponsend，id
	 * @return int
	 */
	@Override
	public int deleteCouponRule(String marketPlanId) {
		return this.getSqlSession().delete(
				NAMESPACE_MARKETPLAN+DELETE_COUPOONRULE,marketPlanId);
	}
	/**   
	 * <p>
	 * Description:更新所有已启用的营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-27
	 * @param 
	 * @return int
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int updateMarketPlanForSchedual() {
		return this.getSqlSession().update(
				NAMESPACE_MARKETPLAN+UPDATE_MARKETPLANFORSCHEDUAL);
	}
	/**
	 * <p>
	 * Description: 每天检查更新所有状态（不包括已过期）优惠券 状态是否过期，<br/>
	 *  过期的把状态置为：已过期<br/>
	 * </p> 
	 * @author 钟琼
	 * @version 0.1 2012-11-29
	 */
	@Override
	public int updateCouponStatesByOverdueForSchedual() {
		 return this.getSqlSession().update(
				NAMESPACE_MARKETPLAN+UPDATE_COUPONSTATESBYOVERDUEFORSCHEDUAL);
	}
}
