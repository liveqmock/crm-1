/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CouponServiceImpl.java
 * @package com.deppon.crm.module.coupon.server.service.impl 
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */
package com.deppon.crm.module.coupon.server.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.coupon.server.dao.ICouponDao;
import com.deppon.crm.module.coupon.server.dao.IMarketPlanDao;
import com.deppon.crm.module.coupon.server.service.ICouponService;
import com.deppon.crm.module.coupon.server.utils.CouponValidator;
import com.deppon.crm.module.coupon.shared.domain.AutoCouponCost;
import com.deppon.crm.module.coupon.shared.domain.ConditionType;
import com.deppon.crm.module.coupon.shared.domain.Coupon;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphoneMsgInfo;
import com.deppon.crm.module.coupon.shared.domain.CouponForInterface;
import com.deppon.crm.module.coupon.shared.domain.CouponResultVO;
import com.deppon.crm.module.coupon.shared.domain.CouponRule;
import com.deppon.crm.module.coupon.shared.domain.CouponSearchCondition;
import com.deppon.crm.module.coupon.shared.domain.GoodsLine;
import com.deppon.crm.module.coupon.shared.domain.HandMarketPlan;
import com.deppon.crm.module.coupon.shared.domain.MarketingCouponConstance;
import com.deppon.crm.module.coupon.shared.domain.Marketplan;
import com.deppon.crm.module.coupon.shared.domain.RuleCouponAuto;
import com.deppon.crm.module.coupon.shared.domain.RuleCouponHand;
import com.deppon.crm.module.coupon.shared.domain.SendCouponVO;
import com.deppon.crm.module.coupon.shared.domain.WaybillCoupon;
import com.deppon.crm.module.organization.shared.domain.Department;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CouponServiceImpl.java
 * @package com.deppon.crm.module.coupon.server.service.impl 
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */

public class CouponServiceImpl implements ICouponService {
	private ICouponDao couponDaoImpl;
	private IMarketPlanDao marketPlanDaoImpl;
	
	/**   
	 * <p>
	 * Description:改变优惠券状态<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponCode 优惠券状态编码
	 * @param couponNums 优惠券编码
	 * @return boolean
	 */
	@Override
	public boolean updateCouponStatus(String couponCode, List<String> couponNums) {
		return couponDaoImpl.updateCouponStatus(couponCode, couponNums)> 0;
	}

	/**   
	 * <p>
	 * Description:根据 优惠券编码查询优惠券<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponNums 优惠券编码
	 * @return List<Coupon>
	 */
	@Override
	public List<Coupon> searchCouponsByNums(List<String> couponNums) {
		return couponDaoImpl.searchCouponsByNums(couponNums);
		
	}
	
	/**   
	 * <p>
	 * Description:根据 优惠券编码查询优惠券<br />
	 * </p>
	 * @author 朱培军
	 * @version 0.1 2012-11-13
	 * @param couponNums 优惠券编码
	 * @return Coupon
	 */
	@Override
	public Coupon getCouponByNum(String couponNum) {
		return couponDaoImpl.getCouponByNum(couponNum);
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-13
	 * @param couponNums
	 * @return CouponRule
	 */
	@Override
	public CouponRule getCouponRuleByNumber(String couponNums) {
		return null;
	}

	/**
	 * <p>
	 * Description: 根据营销计划查询规则<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-13
	 * @param couponNums
	 * @return CouponRule
	 */
	@Override
	public CouponRule getCouponRuleByMarketPlanId(String marketPlanId) {
		return this.marketPlanDaoImpl.getCouponByMarketPlanId(marketPlanId);
	}
	/**   
	 * <p>
	 * Description:根据条件，改变单条优惠券状态<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponCode 优惠券状态编码
	 * @param couponNums 优惠券编码
	 * @return boolean
	 */
	@Override
	public boolean updateOneCouponStatus(String couponCode, String couponNum) {
		return couponDaoImpl.updateOneCouponStatus(couponCode,couponNum) > 0;
	}
	/**   
	 * <p>
	 * Description:新增手动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-15
	 * @param ruleCouponHand 手动发券实体类
	 * @return boolean
	 */
	@Override
	public boolean addMarketplanVOByRuleCouponHand(RuleCouponHand ruleCouponHand) {
		return marketPlanDaoImpl.addMarketplanVOByRuleCouponHand(ruleCouponHand) > 0;
	}
	/**   
	 * <p>
	 * Description:新增自动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-15
	 * @param ruleCouponAuto 自动发券实体类
	 * @return boolean
	 */
	@Override
	public boolean addMarketplanVOByRuleCouponAuto(RuleCouponAuto ruleCouponAuto) {
		boolean isInsert = marketPlanDaoImpl.addMarketplanVOByRuleCouponAuto(ruleCouponAuto)> 0;
		// 约束条件表
		List<ConditionType> conditionTypes = ruleCouponAuto.getConditionTypes();
		String ruleCouponAutoId = ruleCouponAuto.getId();
		if(null != conditionTypes && 0 != conditionTypes.size()){
			for(ConditionType conditionType : conditionTypes){
				conditionType.setRuleType(MarketingCouponConstance.CONDITIONTYPE_COUPONSEND);
				conditionType.setCouponAutoId(ruleCouponAutoId);
				marketPlanDaoImpl.addMarketplanVOByConditionType(conditionType);
			}
		}
		// 自动返券金额表
		List<AutoCouponCost> autoCouponCosts = ruleCouponAuto.getAutoCouponCost();
		if(null != autoCouponCosts && 0 != autoCouponCosts.size()){
			for(AutoCouponCost autoCouponCost : autoCouponCosts){
				autoCouponCost.setCouponAutoId(ruleCouponAutoId);
				marketPlanDaoImpl.addMarketplanVOByAutoCouponCost(autoCouponCost);
			}
		}
		// 走货线路表
		List<GoodsLine> createGoodsLines = ruleCouponAuto.getCreateGoodsLine();
		if(null != createGoodsLines && 0 != createGoodsLines.size()){
			for(GoodsLine createGoodsLine : createGoodsLines){
				createGoodsLine.setRuleType(MarketingCouponConstance.CONDITIONTYPE_COUPONSEND);
				createGoodsLine.setCouponAutoId(ruleCouponAutoId);
				marketPlanDaoImpl.addMarketplanVOByGoodsLine(createGoodsLine);
			}
		}
		return isInsert;
	}
	/**   
	 * <p>
	 * Description:新增营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-15
	 * @param marketplan 营销计划实体类
	 * @return boolean
	 */
	@Override
	public boolean addMarketplanVOByMarketplan(Marketplan marketplan) {
		return marketPlanDaoImpl.addMarketplanVOByMarketplan(marketplan)> 0;
	}
	/**   
	 * <p>
	 * Description:新增优惠券使用条件<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-15
	 * @param couponRule 优惠券使用条件实体类
	 * @return boolean
	 */
	@Override
	public boolean addMarketplanVOByRuleCouponRule(CouponRule couponRule) {
		boolean isInsert = marketPlanDaoImpl.addMarketplanVOByRuleCouponRule(couponRule)> 0;
		String  couponRuleId = couponRule.getId();
		// 走货线路表
		List<GoodsLine> goodsLines = couponRule.getGoodsLines();
		if(null != goodsLines && 0 != goodsLines.size()){
			for(GoodsLine goodsLine : goodsLines){
				goodsLine.setRuleType(MarketingCouponConstance.CONDITIONTYPE_COUPONUSE);
				goodsLine.setCouponRuleId(couponRuleId);
				marketPlanDaoImpl.addMarketplanVOByGoodsLine(goodsLine);
			}
		}
		// 使用条件信息
		List<ConditionType> conditionTypes = couponRule.getConditionTypes();
		if(null != conditionTypes && 0 != conditionTypes.size()){
			for(ConditionType conditionType : conditionTypes){
				conditionType.setRuleType(MarketingCouponConstance.CONDITIONTYPE_COUPONUSE);
				conditionType.setCouponRuleId(couponRuleId);
				marketPlanDaoImpl.addMarketplanVOByConditionType(conditionType);
			}
		}
		return isInsert;
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
	@Override
	public List<Marketplan> searchMarketPlan(Marketplan marketplan,int start,int limit) {
		return marketPlanDaoImpl.searchMarketPlan(marketplan,start,limit);
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
	public Marketplan searchMarketPlanDetail(String marketplanId){
		return marketPlanDaoImpl.searchMarketPlanDetail(marketplanId);
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
		return marketPlanDaoImpl.searchRuleCouponHand(marketplanId);
	}
	/**   
	 * <p>
	 * Description:根据 营销计划ID 查询自动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param marketplanId 营销计划ID
	 * @return RuleCouponAuto
	 */
	@Override
	public RuleCouponAuto searchOnlyRuleCouponAuto(String marketplanId){
		return marketPlanDaoImpl.searchRuleCouponAuto(marketplanId);
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
	public RuleCouponAuto searchRuleCouponAuto(String marketplanId) {
		RuleCouponAuto ruleCouponAuto = marketPlanDaoImpl.searchRuleCouponAuto(marketplanId);
		// 查询使用条件信息
		List<ConditionType> conditionTypes = marketPlanDaoImpl.searchRuleCPAByConditionTypes(ruleCouponAuto.getId(),
				MarketingCouponConstance.CONDITIONTYPE_COUPONSEND);
		ruleCouponAuto.setConditionTypes(conditionTypes);
		// 查询返券金额
		List<AutoCouponCost> autoCouponCost = marketPlanDaoImpl.searchRuleCPAByautoCouponCost(ruleCouponAuto.getId());
		ruleCouponAuto.setAutoCouponCost(autoCouponCost);
		// 查询线路区域要求
		List<GoodsLine> createGoodsLine = marketPlanDaoImpl.searchRuleCPAByGoodsLine(ruleCouponAuto.getId(),
				MarketingCouponConstance.CONDITIONTYPE_COUPONSEND);
		ruleCouponAuto.setCreateGoodsLine(createGoodsLine);
		return ruleCouponAuto;
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
	public Marketplan searchCouponTypeByMarketPlanId(String marktPlanNumber) {
		return marketPlanDaoImpl.getCouponTypeByMarketPlanId(marktPlanNumber);
		
	}	
	/**   
	 * <p>
	 * Description:更新营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param marketplan 营销计划
	 * @return boolean
	 */
	@Override
	public boolean updateMarketPlan(Marketplan marketplan) {
		return marketPlanDaoImpl.updateMarketPlan(marketplan)> 0;
	}
	/**   
	 * <p>
	 * Description:更新手动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param ruleCouponHand 自动发券
	 * @return boolean
	 */
	@Override
	public boolean updateCouponHand(RuleCouponHand ruleCouponHand) {
		return marketPlanDaoImpl.updateCouponHand(ruleCouponHand)> 0;
	}
	/**   
	 * <p>
	 * Description:更新自动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param ruleCouponAuto 手动发券
	 * @return boolean
	 */
	@Override
	public boolean updateCouponAuto(RuleCouponAuto ruleCouponAuto) {
		 marketPlanDaoImpl.updateCouponAuto(ruleCouponAuto);
		 String couponAutoId = ruleCouponAuto.getId();
		 marketPlanDaoImpl.deleteConditionTypes(MarketingCouponConstance.CONDITIONTYPE_COUPONSEND,couponAutoId);
		 marketPlanDaoImpl.deleteCouponCost(MarketingCouponConstance.CONDITIONTYPE_COUPONSEND,couponAutoId);
		 marketPlanDaoImpl.deleteCreateGoodsLine(MarketingCouponConstance.CONDITIONTYPE_COUPONSEND,couponAutoId);
		// 约束条件表
		List<ConditionType> conditionTypes = ruleCouponAuto.getConditionTypes();
		for(ConditionType conditionType : conditionTypes){
			conditionType.setRuleType(MarketingCouponConstance.CONDITIONTYPE_COUPONSEND);
			conditionType.setCouponAutoId(couponAutoId);
			marketPlanDaoImpl.addMarketplanVOByConditionType(conditionType);
		}
		// 自动返券金额表
		List<AutoCouponCost> autoCouponCosts = ruleCouponAuto.getAutoCouponCost();
		if(null != autoCouponCosts && 0 != autoCouponCosts.size()){
			for(AutoCouponCost autoCouponCost : autoCouponCosts){
				autoCouponCost.setCouponAutoId(couponAutoId);
				marketPlanDaoImpl.addMarketplanVOByAutoCouponCost(autoCouponCost);
			}
		}
		// 走货线路表
		List<GoodsLine> createGoodsLines = ruleCouponAuto.getCreateGoodsLine();
		if(null != createGoodsLines && 0 != createGoodsLines.size()){
			for(GoodsLine createGoodsLine : createGoodsLines){
				createGoodsLine.setRuleType(MarketingCouponConstance.CONDITIONTYPE_COUPONSEND);
				createGoodsLine.setCouponAutoId(couponAutoId);
				marketPlanDaoImpl.addMarketplanVOByGoodsLine(createGoodsLine);
			}
		}
		 return true;
	}
	/**   
	 * <p>
	 * Description:更新优惠券使用规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param couponRule 使用规则划
	 * @return boolean
	 */
	@Override
	public boolean updateCouponRule(CouponRule couponRule) {
		marketPlanDaoImpl.updateCouponRule(couponRule);
		String couponRuleId = couponRule.getId();
		marketPlanDaoImpl.deleteConditionTypes(MarketingCouponConstance.CONDITIONTYPE_COUPONUSE,couponRuleId);
		marketPlanDaoImpl.deleteCreateGoodsLine(MarketingCouponConstance.CONDITIONTYPE_COUPONUSE,couponRuleId);
		// 走货线路表
		List<GoodsLine> goodsLines = couponRule.getGoodsLines();
		if(null != goodsLines && 0 != goodsLines.size()){
			for(GoodsLine goodsLine : goodsLines){
				goodsLine.setRuleType(MarketingCouponConstance.CONDITIONTYPE_COUPONUSE);
				goodsLine.setCouponRuleId(couponRuleId);
				marketPlanDaoImpl.addMarketplanVOByGoodsLine(goodsLine);
			}
		}
		// 使用条件信息
		List<ConditionType> conditionTypes = couponRule.getConditionTypes();
		if(null != conditionTypes && 0 != conditionTypes.size()){
			for(ConditionType conditionType : conditionTypes){
				conditionType.setRuleType(MarketingCouponConstance.CONDITIONTYPE_COUPONUSE);
				conditionType.setCouponRuleId(couponRuleId);
				marketPlanDaoImpl.addMarketplanVOByConditionType(conditionType);
			}
		}
		return true;
	}	
	public void setCouponDaoImpl(ICouponDao couponDaoImpl) {
		this.couponDaoImpl = couponDaoImpl;
	}
	public void setMarketPlanDaoImpl(IMarketPlanDao marketPlanDaoImpl) {
		this.marketPlanDaoImpl = marketPlanDaoImpl;
	}

	/**
	 * <p>
	 * Description: 批量创建优惠券<br/>
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-19
	 * @param coupon
	 * @return boolean
	 */
	@Override
	public int createCoupon(Coupon coupon) {
		return this.couponDaoImpl.createCoupon(coupon);
	}	
	/**
	 * <p>
	 * Description: 更新优惠券信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-21
	 * @param coupon
	 * @return boolean
	 */
	@Override
	public boolean updateCoupon(Coupon coupon){
		return this.couponDaoImpl.updateCoupon(coupon);
	}
	/**   
	 * <p>
	 * Description:查询手机发券前台显示<br/>
	 * </p>
	 * @author ZhouYuan 
	 * @version 0.1 2012-11-20
	 * @param marketPlanId 营销计划ID
	 * @return SendCouponVO 手机发券前台显示
	 */
	@Override
	public SendCouponVO searchSendCouponVO(Map<String,String> searchCondition) {
		return couponDaoImpl.searchSendCouponVO(searchCondition);
	}
	/**
	 * <p>
	 * Description: 插入优惠券短信发送表<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-21
	 * @param couponPhoneMsg 优惠券短信发送实体
	 * @return boolean
	 */
	@Override
	public boolean createCouponPhoneMsg(CouponCellphoneMsgInfo couponPhoneMsg) {
		
		return couponDaoImpl.createCouponPhoneMsg(couponPhoneMsg)>0;
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
	public String searchMarketPlanTotalCount(Marketplan marketplan) {
		return marketPlanDaoImpl.searchMarketPlanCount(marketplan);
	}
	/**
	 * <p>
	 * Description: 根据营销计划编码 优惠券类型查询优惠券<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-21
	 * @param coupon start limit
	 * @return Coupon
	 */
	@Override
	public List<Coupon> selectCouponByMarketPlanIdAndStatus(Coupon coupon,
			int start, int limit) {
		
		return couponDaoImpl.selectCouponByMarketPlanIdAndStatus(coupon, start, limit);
	}
	/**
	 * <p>
	 * Description: 查询优化券前台显示<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-23
	 * @param cp
	 * @return CouponReslutVO
	 */
	@Override
	public List<CouponResultVO> searchCouponResultVOByMutiCondition(
			CouponSearchCondition condition) {
		// 导出优惠券
		return couponDaoImpl.selectCouponByMutiCondition(condition, 0, 0);
		
	}

	/**
	 * <p>
	 * Description: 根据条件查询优惠券<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-22
	 * @param condition
	 * @return Map
	 */
	@Override
	public Map<String, Object> searchCouponByCondition(
			CouponSearchCondition condition,Department dept, int start, int limit) {
		if (StringUtils.isNotEmpty(condition.getUnderDept())){
			// 选择具体部门
			String deptSeq = couponDaoImpl.getDeptSeq(condition.getUnderDept());
			condition.setDeptSeq(deptSeq);
			//  如果选择具体的事业部
			if(CouponValidator.checkRightMarketingDept(dept.getDeptName())){
				String usrId = couponDaoImpl.searchUserIdByDeptSeq(dept.getDeptSeq()+"%");
				condition.setUsrId(usrId);
				condition.setDeptSeq(null);
				condition.setManagerDept("managerDept");
			}
		}
		// 查询优惠券
		List<CouponResultVO> rsList = couponDaoImpl.selectCouponByMutiCondition(condition, start, limit);
		int count = couponDaoImpl.countCouponByMutiCondition(condition);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", rsList);
		map.put("count", count);
		return map;
	}
	/**
	 * <p>
	 * Description: 根据条件查询优惠券数量<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-12-05
	 * @param condition
	 * @return int
	 */
	@Override
	public int countCouponByMutiCondition(CouponSearchCondition condition){
		return couponDaoImpl.countCouponByMutiCondition(condition);
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
		return marketPlanDaoImpl.searchHandMarketPlanByCouponTypeAndTypeId(couponType, typeId);
	}
	/**
	 * <p>
	 * Description:删除营销计划<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-23
	 * @param marketPlanId
	 * @return boolean
	 */
	@Override
	public boolean deleteMarketPlan(String marketPlanId) {
		// 删除营销计划
		marketPlanDaoImpl.deleteMarketPlan(marketPlanId);
		 return true;
	}
	/**
	 * <p>
	 * Description:删除手动发券规则<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-23
	 * @param marketPlanId
	 * @return boolean
	 */
	@Override
	public boolean deleteCouponHand(String marketPlanId, String id) {
		marketPlanDaoImpl.deleteCouponHand(marketPlanId);
		return true;
	}
	/**
	 * <p>
	 * Description:删除自动发券规则<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-23
	 * @param marketPlanId,id
	 * @return boolean
	 */
	@Override
	public boolean deleteCouponAuto(String marketPlanId, String id) {
		marketPlanDaoImpl.deleteCouponAuto(marketPlanId);
		marketPlanDaoImpl.deleteConditionTypes(MarketingCouponConstance.CONDITIONTYPE_COUPONSEND,id);
		marketPlanDaoImpl.deleteCouponCost(MarketingCouponConstance.CONDITIONTYPE_COUPONSEND,id);
		marketPlanDaoImpl.deleteCreateGoodsLine(MarketingCouponConstance.CONDITIONTYPE_COUPONSEND,id);
		return true;
	}

	@Override
	public boolean deleteCouponRule(String marketPlanId, String id) {
		marketPlanDaoImpl.deleteCouponRule(marketPlanId);
		marketPlanDaoImpl.deleteConditionTypes(MarketingCouponConstance.CONDITIONTYPE_COUPONUSE,id);
		marketPlanDaoImpl.deleteCreateGoodsLine(MarketingCouponConstance.CONDITIONTYPE_COUPONUSE,id);
		return false;
	}
	/**
	 * <p>
	 * Description: 根据发送标识查询未发送的短信<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-26
	 * @param sended
	 * @return List<CouponCellphoneMsgInfo> 未发送短信实体 
	 */
	public List<CouponCellphoneMsgInfo> searchSendCouponMsg(String sended,int start,int limit){
		return couponDaoImpl.searchSendCouponMsg(sended,start,limit);
	}
	/**
	 * <p>
	 * Description: 根据ID更新<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-26
	 * @param ccpmi
	 * @return boolean
	 */
	@Override
	public boolean updateSendCouponMsg(CouponCellphoneMsgInfo ccpmi) {
		return couponDaoImpl.updateSendCouponMsg(ccpmi)>0;
	}
	/**   
	 * <p>
	 * Description:查询所有已启用的营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-27
	 * @param 
	 * @return boolean
	 */
	@Override
	public boolean updateMarketPlanForSchedual() {
		return marketPlanDaoImpl.updateMarketPlanForSchedual()> 0;
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
	public boolean updateCouponStatesByOverdueForSchedual() {
		return marketPlanDaoImpl.updateCouponStatesByOverdueForSchedual()> 0;
	}
	/**
	 * <p>
	 * Description: 根据使用标识查询未使用的运单中间表<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-29
	 * @param status
	 * @return List<WaybillCoupon> 运单中间表
	 */
	@Override
	public List<WaybillCoupon> searchWaybillCouponByStatus(String status,int start,int limit){
		return couponDaoImpl.searchWaybillCouponByStatus(status,start,limit);
	}
	/**
	 * <p>
	 * Description: 根据ID更新运单中间表 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-29
	 * @param ccpmi
	 * @return boolean
	 */
	@Override
	public boolean updateWaybillCouponByStatus(WaybillCoupon wbCoupon){
		return couponDaoImpl.updateWaybillCouponByStatus(wbCoupon)>0;
	}
	/**
	 * <p>
	 * Description: 存储过程调用创建优惠券 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-12-13
	 * @param couponType
	 * @return String
	 */
	public String callableCreateCouponHand(String couponType){
		return couponDaoImpl.callableCreateCouponHand(couponType);
	}
	/**
	 * <p>
	 * Description: 存储过程调用自动券短信发送 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-12-13
	 * @param empCode
	 * @param deptCode
	 * @return String
	 */
	public String callableSendMsgAuto(Date startdate,Date enddate,String empCode,String deptCode){
		return couponDaoImpl.callableSendMsgAuto(startdate,enddate,empCode, deptCode);
	}
	/**
	 * <p>
	 * Description: 存储过程调用创建手动优惠券 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-12-13
	 * @param empCode
	 * @param deptCode
	 * @return String
	 */
	@Override
	public String callableCreateHandCouponImm(int size, String marketPlanId ,
			String useRuleId,String couponValue){
		return couponDaoImpl.callableCreateHandCouponImm(size, marketPlanId,
				useRuleId, couponValue);
	}
	/**
	 * <p>
	 * Description: 查询优惠券-部门查询 <br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-02-25
	 */
	@Override
	public List<Department> queryDeptListByName(String userId, String deptName,
			String shiyebuName, String isMarketingDept, int start, int limit) {
		deptName = "%"+deptName+"%";
		return couponDaoImpl.queryDeptListByName(userId, deptName,
				shiyebuName, isMarketingDept, start, limit);
	}
	/**
	 * <p>
	 * Description: 查询优惠券-部门查询-条数 <br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-02-25
	 */
	@Override
	public int counrQueryDeptListByName(String userId, String deptName,
			String shiyebuName, String isMarketingDept, int start, int limit) {
		deptName = "%"+deptName+"%";
		return couponDaoImpl.counrQueryDeptListByName(userId, deptName,
				shiyebuName, isMarketingDept, start, limit);
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
	public Coupon searchMarketPlanByDeptStandardCodeAndActivityType(CouponForInterface cfi){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("deptStCode", cfi.getDeptStandardCode());
		map.put("activityType", cfi.getActivityType());
		return marketPlanDaoImpl.searchMarketPlanByDeptStandardCodeAndActivityType(map);
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
	@Override
	public String searchMPNameMarketPlanByDeptStandardCodeAndActivityType(CouponForInterface cfi){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("deptStCode", cfi.getDeptStandardCode());
		map.put("activityType", cfi.getActivityType());
		map.put("beginTime", cfi.getBeginTime());
		return marketPlanDaoImpl.searchMPNameMarketPlanByDeptStandardCodeAndActivityType(map);
	}
}
