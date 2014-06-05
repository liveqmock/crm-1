package com.deppon.crm.module.customer.server.service;

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
 * 积分规则服务层<br />
 * </p>
 * 
 * @title IItegService.java
 * @package com.deppon.crm.module.customer.server.service
 * @author bxj
 * @version 0.2 2012-4-19
 */
public interface IIntegRuleService {

	/**
	 * <p>
	 * Description:保存渠道规则<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param integRule
	 * void
	 */
	public void saveChannelIntegRule(ChannelIntegRule integRule);

	/**
	 * <p>
	 * Description:保存产品积分规则<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param integRule
	 * void
	 */
	public void saveProductIntegRule(ProductIntegRule integRule);

	/**
	 * <p>
	 * Description:保存线路积分规则<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param integRule
	 * void
	 */
	public void saveWiringIntegRule(WiringIntegRule integRule);

	/**
	 * <p>
	 * Description:根据ID修改渠道规则信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param integRule
	 * void
	 */
	public void updateChannelIntegRule(ChannelIntegRule integRule);

	/**
	 * <p>
	 * Description:根据ID修改 产品积分规则信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param integRule
	 * void
	 */
	public void updateProductIntegRule(ProductIntegRule integRule);

	/**
	 * <p>
	 * Description:根据ID修改 线路积分规则信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param integRule
	 * void
	 */
	public void updateWiringIntegRule(WiringIntegRule integRule);

	/**
	 * <p>
	 * Description:根据ID 得到渠道规则 信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param id
	 * @return
	 * ChannelIntegRule
	 */
	public ChannelIntegRule getChannelIntegRuleById(String id);

	/**
	 * <p>
	 * Description:根据ID 得到 产品积分规则 信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param id
	 * @return
	 * ProductIntegRule
	 */
	public ProductIntegRule getProductIntegRuleById(String id);

	/**
	 * <p>
	 * Description:根据ID 得到 线路积分规则 信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param id
	 * @return
	 * WiringIntegRule
	 */
	public WiringIntegRule getWiringIntegRuleById(String id);

	/**
	 * <p>
	 * Description:分页查询 线路积分规则信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param start
	 * @param limit
	 * @return
	 * List<WiringIntegRule>
	 */
	List<WiringIntegRule> getWiringIntegRules(int start, int limit);

	/**
	 * <p>
	 * Description:分页查询 产品积分规则信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param start
	 * @param limit
	 * @return
	 * List<ProductIntegRule>
	 */
	List<ProductIntegRule> getProductIntegRules(int start, int limit);

	/**
	 * <p>
	 * Description:统计线路积分规则总数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param start
	 * @param limit
	 * @return
	 * List<ChannelIntegRule>
	 */
	List<ChannelIntegRule> getChannelIntegRules(int start, int limit);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @return
	 * long
	 */
	public long countGetWiringIntegRules();

	/**
	 * <p>
	 * Description:统计产品积分规则的总数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @return
	 * long
	 */
	public long countGetProductIntegRules();

	/**
	 * <p>
	 * Description:统计渠道规则的总数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @return
	 * long
	 */
	public long countGetChannelIntegRules();

	/**
	 * <p>
	 * Description:根据ID查询积分规则的fnumber<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param id
	 * @return
	 * String
	 */
	public String searchIntegRule(String id);

	/**
	 * <p>
	 * Description:根据ID 删除渠道规则信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param id
	 * void
	 */
	public void deleteChannelIntegRule(String id);

	/**
	 * <p>
	 * Description:根据ID删除产品积分规则信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param id
	 * void
	 */
	public void deleteProductIntegRule(String id);

	/**
	 * <p>
	 * Description:根据ID 删除线路积分规则信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param id
	 * void
	 */
	public void deleteWiringIntegRule(String id);

	/**
	 * <p>
	 * 保存奖励规则<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-20
	 * @param rewardIntegral
	 *            void
	 */
	public void saveRewardIntegRule(RewardIntegRule rewardIntegral);

	/**
	 * <p>
	 * 修改奖励规则<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-20
	 * @param rewardIntegral
	 *            void
	 */
	public void updateRewardIntegRule(RewardIntegRule rewardIntegral);

	/**
	 * <p>
	 * 查询全部奖励规则<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-20
	 * @param start
	 * @param limit
	 * @return List<RewardIntegral>
	 */
	public List<RewardIntegRule> getRewardIntegRules(int start, int limit);

	/**
	 * 
	 * <p>
	 * 得到全部奖励规则总数<br />
	 * </p>
	 * 
	 * @author bxj
	 * @version 0.2 2012-4-20
	 * @return int
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
	 * <p>删除礼品兑换规则<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-28
	 * @param id
	 * void
	 */
	public void deleteGift(String id);

	/**
	 * @description 查询积分规则是否存在.  
	 * @author 潘光均
	 * @version 0.1 2012-7-5
	 * @param 
	 * @date 2012-7-5
	 * @return boolean
	 * @update 2012-7-5 下午9:44:17
	 */
	public List<IntegRule> queryRuleByCondition(IntegRule rule, String type);
}
