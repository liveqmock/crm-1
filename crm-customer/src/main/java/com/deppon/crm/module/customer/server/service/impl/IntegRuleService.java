package com.deppon.crm.module.customer.server.service.impl;

import java.util.List;

import com.deppon.crm.module.customer.server.dao.IGiftDao;
import com.deppon.crm.module.customer.server.dao.IIntegRuleDao;
import com.deppon.crm.module.customer.server.dao.IRewardIntegRuleDao;
import com.deppon.crm.module.customer.server.service.IIntegRuleService;
import com.deppon.crm.module.customer.shared.domain.integral.ChannelIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.Gift;
import com.deppon.crm.module.customer.shared.domain.integral.IntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.ProductIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.RewardIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.WiringIntegRule;

/**
 * 
 * <p>
 * Description:IntegRuleService<br />
 * </p>
 * 
 * @title IntegRuleService.java
 * @package com.deppon.crm.module.customer.server.service.impl
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class IntegRuleService implements IIntegRuleService {
	/**
	 * integRuleDao
	 */
	private IIntegRuleDao integRuleDao;
	/**
	 * rewardIntegRuleDao
	 */
	private IRewardIntegRuleDao rewardIntegRuleDao;
	/**
	 * giftDao
	 */
	private IGiftDao giftDao;

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param giftDao
	 *            void
	 */
	public void setGiftDao(IGiftDao giftDao) {
		this.giftDao = giftDao;
	}

	public void setRewardIntegRuleDao(IRewardIntegRuleDao rewardIntegRuleDao) {
		this.rewardIntegRuleDao = rewardIntegRuleDao;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return IIntegRuleDao
	 */
	public IIntegRuleDao getIntegRuleDao() {
		return integRuleDao;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integRuleDao
	 *            void
	 */
	public void setIntegRuleDao(IIntegRuleDao integRuleDao) {
		this.integRuleDao = integRuleDao;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integRule
	 * 
	 */
	@Override
	public void saveChannelIntegRule(ChannelIntegRule integRule) {
		integRuleDao.saveChannelIntegRule(integRule);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integRule
	 * 
	 */
	@Override
	public void saveProductIntegRule(ProductIntegRule integRule) {
		integRuleDao.saveProductIntegRule(integRule);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integRule
	 * 
	 */
	@Override
	public void saveWiringIntegRule(WiringIntegRule integRule) {
		integRuleDao.saveWiringIntegRule(integRule);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integRule
	 * 
	 */
	@Override
	public void updateChannelIntegRule(ChannelIntegRule integRule) {
		integRuleDao.updateChannelIntegRule(integRule);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integRule
	 * 
	 */
	@Override
	public void updateProductIntegRule(ProductIntegRule integRule) {
		integRuleDao.updateProductIntegRule(integRule);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integRule
	 * 
	 */
	@Override
	public void updateWiringIntegRule(WiringIntegRule integRule) {
		integRuleDao.updateWiringIntegRule(integRule);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * @return
	 * 
	 */
	@Override
	public ChannelIntegRule getChannelIntegRuleById(String id) {
		return integRuleDao.getChannelIntegRuleById(id);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * @return
	 * 
	 */
	@Override
	public ProductIntegRule getProductIntegRuleById(String id) {
		return integRuleDao.getProductIntegRuleById(id);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * @return
	 * 
	 */
	@Override
	public WiringIntegRule getWiringIntegRuleById(String id) {
		return integRuleDao.getWiringIntegRuleById(id);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param start
	 * @param limit
	 * @return
	 * 
	 */
	@Override
	public List<WiringIntegRule> getWiringIntegRules(int start, int limit) {
		return integRuleDao.getWiringIntegRules(start, limit);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param start
	 * @param limit
	 * @return
	 * 
	 */
	@Override
	public List<ProductIntegRule> getProductIntegRules(int start, int limit) {
		return integRuleDao.getProductIntegRules(start, limit);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param start
	 * @param limit
	 * @return
	 * 
	 */
	@Override
	public List<ChannelIntegRule> getChannelIntegRules(int start, int limit) {
		return integRuleDao.getChannelIntegRules(start, limit);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * @return
	 * 
	 */
	@Override
	public String searchIntegRule(String id) {
		return integRuleDao.searchIntegRule(id);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * 
	 */
	@Override
	public long countGetWiringIntegRules() {
		return integRuleDao.countGetWiringIntegRules();
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * 
	 */
	@Override
	public long countGetProductIntegRules() {
		return integRuleDao.countGetProductIntegRules();
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * 
	 */
	@Override
	public long countGetChannelIntegRules() {
		return integRuleDao.countGetChannelIntegRules();
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * 
	 */
	@Override
	public void deleteChannelIntegRule(String id) {
		integRuleDao.deleteChannelIntegRule(id);

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * 
	 */
	@Override
	public void deleteProductIntegRule(String id) {
		integRuleDao.deleteProductIntegRule(id);

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * 
	 */
	@Override
	public void deleteWiringIntegRule(String id) {
		integRuleDao.deleteWiringIntegRule(id);

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param rewardIntegral
	 * 
	 */
	@Override
	public void saveRewardIntegRule(RewardIntegRule rewardIntegral) {
		rewardIntegRuleDao.saveRewardIntegRule(rewardIntegral);

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param rewardIntegral
	 * 
	 */
	@Override
	public void updateRewardIntegRule(RewardIntegRule rewardIntegral) {
		rewardIntegRuleDao.updateRewardIntegRule(rewardIntegral);

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param start
	 * @param limit
	 * @return
	 * 
	 */
	@Override
	public List<RewardIntegRule> getRewardIntegRules(int start, int limit) {
		return rewardIntegRuleDao.getRewardIntegRules(start, limit);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * 
	 */
	@Override
	public int countRewardIntegRules() {
		return rewardIntegRuleDao.countRewardIntegRules();
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param gift
	 * 
	 */
	@Override
	public void insertGift(Gift gift) {
		giftDao.insertGift(gift);

	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param gift
	 * 
	 */
	@Override
	public void updateGift(Gift gift) {
		giftDao.updateGift(gift);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * @return
	 * 
	 */
	@Override
	public Gift getGiftById(String id) {
		return giftDao.getGiftById(id);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param gift
	 * @param start
	 * @param limit
	 * @return
	 * 
	 */
	@Override
	public List<Gift> getGifts(Gift gift, int start, int limit) {
		return giftDao.getGifts(gift, start, limit);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param gift
	 * @return
	 * 
	 */
	@Override
	public long countGetGifts(Gift gift) {
		return giftDao.countGetGifts(gift);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * 
	 */
	@Override
	public void deleteGift(String id) {
		giftDao.deleteGift(id);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param rule
	 * @param type
	 * @return
	 * 
	 */
	@Override
	public List<IntegRule> queryRuleByCondition(IntegRule rule, String type) {
		return integRuleDao.queryRuleByCondition(rule, type);
	}

}
