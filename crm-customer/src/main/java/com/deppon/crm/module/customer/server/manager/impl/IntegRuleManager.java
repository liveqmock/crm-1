package com.deppon.crm.module.customer.server.manager.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.deppon.crm.module.customer.server.manager.IBaseDataManager;
import com.deppon.crm.module.customer.server.manager.IIntegRuleManager;
import com.deppon.crm.module.customer.server.service.IIntegRuleService;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.integral.ChannelIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.Gift;
import com.deppon.crm.module.customer.shared.domain.integral.IntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.ProductIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.RewardIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.WiringIntegRule;
import com.deppon.crm.module.customer.shared.exception.IntegException;
import com.deppon.crm.module.customer.shared.exception.IntegExceptionType;
import com.deppon.crm.module.organization.shared.domain.Department;

public class IntegRuleManager implements IIntegRuleManager {
	// 积分规则--产品
	public static final String INTEGRAL_PRODUCT = "product";
	// 积分规则--线路
	public static final String INTEGRAL_WIRING = "wiring";
	// 积分规则--渠道
	public static final String INTEGRAL_CHANNEL = "channel";
	
	private IIntegRuleService integRuleService;
	
	private IBaseDataManager baseDataManager;
	
	
	public void setBaseDataManager(IBaseDataManager baseDataManager) {
		this.baseDataManager = baseDataManager;
	}

	public void setIntegRuleService(IIntegRuleService integRuleService) {
		this.integRuleService = integRuleService;
	}

	/**
	 * 积分规则保存 积分规则分为3种 1：产品规则 2：线路规则 3：渠道规则
	 */
	@Override
	public void createIntegRule(IntegRule integRule, String type) {
		integRule.setCreateDate(new Date());
		integRule.setCreateUser(ContextUtil.getCurrentUserEmpId());
		if(INTEGRAL_PRODUCT.equals(type) && integRule instanceof ProductIntegRule){
			integRuleService.saveProductIntegRule((ProductIntegRule)integRule);
		} else if(INTEGRAL_WIRING.equals(type) && integRule instanceof WiringIntegRule){
			integRuleService.saveWiringIntegRule((WiringIntegRule)integRule);
		} else if(INTEGRAL_CHANNEL.equals(type) && integRule instanceof ChannelIntegRule){
			integRuleService.saveChannelIntegRule((ChannelIntegRule)integRule);
		}

	}
	 /***
     * <p>
     * Description:保存渠道积分规则<br />
     * </p>
     * @author 王明明
     * @version 0.1 2013-2-2
     * @param integRule 渠道积分规则
     */
	public void saveChannelIntegRule(ChannelIntegRule integRule) {
		checkRuleExist(integRule,Constant.ChannelRule);
		integRule.setCreateDate(new Date());
		integRule.setCreateUser(ContextUtil.getCurrentUserEmpId());
		integRuleService.saveChannelIntegRule(integRule);
	}

	/**
	 * @description 校验积分规则是否存在.  
	 * @author 潘光均
	 * @version 0.1 2012-7-6
	 * @param 
	 *@date 2012-7-6
	 * @return void
	 * @update 2012-7-6 上午9:59:57
	 */
	private void checkRuleExist(IntegRule integRule,String type) {
		List<IntegRule> list = integRuleService.queryRuleByCondition(integRule, type);
		if (CollectionUtils.isNotEmpty(list)) {
			throw new IntegException(IntegExceptionType.IntegRuleExist);
		}
	}
	 /***
     * <p>
     * Description:保存产品积分规则<br />
     * </p>
     * @author 王明明
     * @version 0.1 2013-2-2
     * @param integRule 产品积分规则
     */
	@Override
	public void saveProductIntegRule(ProductIntegRule integRule) {
		checkRuleExist(integRule,Constant.ProductRule);
		integRule.setCreateDate(new Date());
		integRule.setCreateUser(ContextUtil.getCurrentUserEmpId());
		integRuleService.saveProductIntegRule(integRule);
	}
	 /***
     * <p>
     * Description:保存线路积分规则<br />
     * </p>
     * @author 王明明
     * @version 0.1 2013-2-2
     * @param integRule 线路积分规则
     */
	@Override
	public void saveWiringIntegRule(WiringIntegRule integRule) {
		checkRuleExist(integRule,Constant.WiringRule);
		integRule.setCreateDate(new Date());
		integRule.setCreateUser(ContextUtil.getCurrentUserEmpId());
		integRuleService.saveWiringIntegRule(integRule);
	}
	 /***
     * <p>
     * Description:更新渠道积分规则<br />
     * </p>
     * @author 王明明
     * @version 0.1 2013-2-2
     * @param integRule 渠道积分规则
     */
	@Override
	public void updateChannelIntegRule(ChannelIntegRule integRule) {
		integRule.setModifyDate(new Date());
		integRule.setModifyUser(ContextUtil.getCurrentUserEmpId());
		integRuleService.updateChannelIntegRule(integRule);
	}
	 /***
     * <p>
     * Description:更新产品积分规则<br />
     * </p>
     * @author 王明明
     * @version 0.1 2013-2-2
     * @param integRule 产品积分规则
     */
	@Override
	public void updateProductIntegRule(ProductIntegRule integRule) {
		integRule.setModifyDate(new Date());
		integRule.setModifyUser(ContextUtil.getCurrentUserEmpId());
		integRuleService.updateProductIntegRule(integRule);
	}
	 /***
     * <p>
     * Description:更新线路积分规则<br />
     * </p>
     * @author 王明明
     * @version 0.1 2013-2-2
     * @param integRule 线路积分规则
     */
	@Override
	public void updateWiringIntegRule(WiringIntegRule integRule) {
		integRule.setModifyDate(new Date());
		integRule.setModifyUser(ContextUtil.getCurrentUserEmpId());
		integRuleService.updateWiringIntegRule(integRule);
	}
	 /***
     * <p>
     * Description:查询渠道积分规则<br />
     * </p>
     * @author 王明明
     * @version 0.1 2013-2-2
     * @param start 起始值
     * @param limit 取多少个
     */
	@Override
	public List<ChannelIntegRule> getChannelIntegRules(int start, int limit) {
		return integRuleService.getChannelIntegRules(start, limit);
	}
	 /***
     * <p>
     * Description:查询产品积分规则<br />
     * </p>
     * @author 王明明
     * @version 0.1 2013-2-2
     * @param start 起始值
     * @param limit 取多少个
     */
	@Override
	public List<ProductIntegRule> getProductIntegRules(int start, int limit) {
		return integRuleService.getProductIntegRules(start, limit);
	}
	 /***
     * <p>
     * Description:查询线路积分规则<br />
     * </p>
     * @author 王明明
     * @version 0.1 2013-2-2
     * @param start 起始值
     * @param limit 取多少个
     */
	@Override
	public List<WiringIntegRule> getWiringIntegRules(int start, int limit) {
		return integRuleService.getWiringIntegRules(start, limit);
	}

	/**
	 * 验证积分规则是否重复,根据编码
	 */
	@Override
	public String searchIntegRule(String id) {
		return integRuleService.searchIntegRule(id);
	}
	 /***
     * <p>
     * Description:获取线路积分规则总数量<br />
     * </p>
     * @author 王明明
     * @version 0.1 2013-2-2
     */
	@Override
	public long countGetWiringIntegRules() {
		return integRuleService.countGetWiringIntegRules();
	}
	 /***
     * <p>
     * Description:获取产品积分规则总数量<br />
     * </p>
     * @author 王明明
     * @version 0.1 2013-2-2
     */
	@Override
	public long countGetProductIntegRules() {
		return integRuleService.countGetProductIntegRules();
	}
	 /***
     * <p>
     * Description:获取渠道积分规则总数量<br />
     * </p>
     * @author 王明明
     * @version 0.1 2013-2-2
     */
	@Override
	public long countGetChannelIntegRules() {
		return integRuleService.countGetChannelIntegRules();
	}
	 /***
     * <p>
     * Description:根据ID删除渠道积分规则<br />
     * </p>
     * @author 王明明
     * @version 0.1 2013-2-2
     * @param id  渠道积分规则ID
     */
	@Override
	public void deleteChannelIntegRule(String id) {
		integRuleService.deleteChannelIntegRule(id);
		
	}
	/***
     * <p>
     * Description:根据ID删除产品积分规则<br />
     * </p>
     * @author 王明明
     * @version 0.1 2013-2-2
     * @param id  产品积分规则ID
     */
	@Override
	public void deleteProductIntegRule(String id) {
		integRuleService.deleteProductIntegRule(id);
		
	}
	/***
     * <p>
     * Description:根据ID删除线路积分规则<br />
     * </p>
     * @author 王明明
     * @version 0.1 2013-2-2
     * @param id  线路积分规则ID
     */
	@Override
	public void deleteWiringIntegRule(String id) {
		integRuleService.deleteWiringIntegRule(id);
		
	}
	/**
	 * 
	 * <p>
	 * 保存奖励规则<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-20
	 * @param rewardIntegral
	 * void
	 */
	@Override
	public void saveRewardIntegRule(RewardIntegRule rewardIntegral) {
		rewardIntegral.setCreateDate(new Date());
		rewardIntegral.setCreateUser(ContextUtil.getCurrentUserEmpId());
		integRuleService.saveRewardIntegRule(rewardIntegral);
		
	}
	/**
	 * 
	 * <p>
	 * 修改奖励规则<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-20
	 * @param rewardIntegral
	 * void
	 */
	@Override
	public void updateRewardIntegRule(RewardIntegRule rewardIntegral) {
		rewardIntegral.setModifyDate(new Date());
		rewardIntegral.setModifyUser(ContextUtil.getCurrentUserEmpId());
		integRuleService.updateRewardIntegRule(rewardIntegral);
		
	}
	/**
	 * 
	 * <p>
	 * 查询全部奖励规则<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-20
	 * @param start
	 * @param limit
	 * @return
	 * List<RewardIntegral>
	 */
	@Override
	public List<RewardIntegRule> getRewardIntegRules(int start, int limit) {
		return integRuleService.getRewardIntegRules(start, limit);
	}
	/**
	 * 
	 * <p>
	 * 得到全部奖励规则总数<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-20
	 * @return
	 * int
	 */
	@Override
	public int countRewardIntegRules() {
		return integRuleService.countRewardIntegRules();
	}
	/**
	 * 
	 * <p>新增礼品兑换规则<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-28
	 * @param gift
	 * void
	 */
	@Override
	public void insertGift(Gift gift) {
		gift.setCreateUser(ContextUtil.getCurrentUserEmpId());
		integRuleService.insertGift(gift);
	}
	/**
	 * 
	 * <p>修改礼品兑换规则<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-28
	 * @param gift
	 * void
	 */
	@Override
	public void updateGift(Gift gift) {
		gift.setModifyUser(ContextUtil.getCurrentUserEmpId());
		gift.setModifyDate(new Date());
		integRuleService.updateGift(gift);
	}
	/**
	 * 
	 * <p>审批不同意时恢复库存<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-6-8
	 * @param gift
	 * void
	 */
	@Override
	public void updateGiftApproval(Gift gift) {
		integRuleService.updateGift(gift);
	}
	/**
	 * 
	 * <p>获得礼品兑换规则详细<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-28
	 * @param id
	 * @return
	 * Gift
	 */
	@Override
	public Gift getGiftById(String id) {
		return integRuleService.getGiftById(id);
	}
	/**
	 * 
	 * <p>礼品规则列表<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-28
	 * @param gift
	 * @param start
	 * @param limit
	 * @return
	 * List<Gift>
	 */
	@Override
	public List<Gift> getGifts(Gift gift, int start, int limit) {
		return integRuleService.getGifts(gift, start, limit);
	}
	/**
	 * 
	 * <p>获得总记录数<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-28
	 * @param gift
	 * @return
	 * long
	 */
	@Override
	public long countGetGifts(Gift gift) {
		return integRuleService.countGetGifts(gift);
	}
	/**
	 * 
	 * <p>删除礼品兑换规则<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-28
	 * @param id
	 * void
	 */
	@Override
	public void deleteGift(String id) {
		integRuleService.deleteGift(id);
	}
	/**
	 * 
	 * <p>
	 * 根据礼物名称模糊查询   可以使用的兑换礼品<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-19
	 * @param gift
	 * @param start
	 * @param limit
	 * @return
	 * List<Gift>
	 */
	@Override
	public List<Gift> getCanUseGiftsByGiftName(String giftName, int start,
			int limit) {
		Gift gift = getCanUserGistsCondition(giftName);
		return integRuleService.getGifts(gift, start, limit);
	}
	/**
	 * 
	 * <p>
	 * 统计数量  根据礼物名称模糊查询   可以使用的兑换礼品<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-5-19
	 * @param giftName
	 * @return
	 * long
	 */
	@Override
	public long countCanUseGistsByGiftName(String giftName) {
		Gift gift = getCanUserGistsCondition(giftName);
		return integRuleService.countGetGifts(gift);
	}
	
	private Gift getCanUserGistsCondition(String giftName){
		Gift gift = new Gift();
		gift.setGiftName(giftName);
		//1  可以启用
		gift.setIsStart(true);
		//2  时间未过期
		gift.setBeginTime(new Date());
		gift.setEndTime(new Date());
		//3 部门 
		Department department = baseDataManager.getCauseDepartment(ContextUtil.getCurrentUserDeptId());
		gift.setDepartment(department);
		return gift;
	}
}
