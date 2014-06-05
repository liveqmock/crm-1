package com.deppon.crm.module.customer.server.manager;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.integral.ChannelIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.Gift;
import com.deppon.crm.module.customer.shared.domain.integral.IntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.ProductIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.RewardIntegRule;
import com.deppon.crm.module.customer.shared.domain.integral.WiringIntegRule;

/**
 * 
 * <p>
 * 积分规则的业务逻辑层<br />
 * </p>
 * 
 * @title IItegManager.java
 * @package com.deppon.crm.module.customer.server.manager
 * @author bxj
 * @version 0.2 2012-4-19
 */
public interface IIntegRuleManager {
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integRule
	 * @param type
	 * void
	 */
	public void createIntegRule(IntegRule integRule, String type);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integRule
	 * void
	 */
	public void saveChannelIntegRule(ChannelIntegRule integRule);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integRule
	 * void
	 */
	public void saveProductIntegRule(ProductIntegRule integRule);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integRule
	 * void
	 */
	public void saveWiringIntegRule(WiringIntegRule integRule);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integRule
	 * void
	 */
	public void updateChannelIntegRule(ChannelIntegRule integRule);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integRule
	 * void
	 */
	public void updateProductIntegRule(ProductIntegRule integRule);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param integRule
	 * void
	 */
	public void updateWiringIntegRule(WiringIntegRule integRule);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param start
	 * @param limit
	 * @return
	 * List<ChannelIntegRule>
	 */
	public List<ChannelIntegRule> getChannelIntegRules(int start, int limit);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param start
	 * @param limit
	 * @return
	 * List<ProductIntegRule>
	 */
	public List<ProductIntegRule> getProductIntegRules(int start, int limit);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param start
	 * @param limit
	 * @return
	 * List<WiringIntegRule>
	 */
	public List<WiringIntegRule> getWiringIntegRules(int start, int limit);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * @return
	 * String
	 */
	public String searchIntegRule(String id);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * long
	 */
	public long countGetWiringIntegRules();
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * long
	 */
	public long countGetProductIntegRules();
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * long
	 */
	public long countGetChannelIntegRules();
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * void
	 */
	public void deleteChannelIntegRule(String id);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * void
	 */
	public void deleteProductIntegRule(String id);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param id
	 * void
	 */
	public void deleteWiringIntegRule(String id);
	
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
	public void saveRewardIntegRule(RewardIntegRule rewardIntegral);
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
	public void updateRewardIntegRule(RewardIntegRule rewardIntegral);
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
	public List<RewardIntegRule> getRewardIntegRules(int start,int limit);
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
	public int countRewardIntegRules();
	/**
	 * 
	 * <p>新增礼品兑换规则<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-28
	 * @param gift
	 * void
	 */
	public void insertGift(Gift gift);

	/**
	 * 
	 * <p>修改礼品兑换规则<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-28
	 * @param gift
	 * void
	 */
	public void updateGift(Gift gift);
	
	/**
	 * 
	 * <p>审批不同意时恢复库存<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-6-8
	 * @param gift
	 * void
	 */
	public void updateGiftApproval(Gift gift);

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
	public Gift getGiftById(String id);

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
	public List<Gift> getGifts(Gift gift, int start, int limit);
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
	public List<Gift> getCanUseGiftsByGiftName(String giftName, int start, int limit);
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
	public long countCanUseGistsByGiftName(String giftName);

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
	public long countGetGifts(Gift gift);

	/**
	 * 
	 * <p>删除礼品兑换规则<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-28
	 * @param id
	 * void
	 */
	public void deleteGift(String id);
}
