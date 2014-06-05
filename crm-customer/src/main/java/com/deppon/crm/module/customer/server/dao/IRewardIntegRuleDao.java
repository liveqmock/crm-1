package com.deppon.crm.module.customer.server.dao;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.integral.RewardIntegRule;
/**
 * 
 * <p>
 * 奖励规则<br />
 * </p>
 * @title IRewardIntegralDao.java
 * @package com.deppon.crm.module.customer.server.dao 
 * @author bxj
 * @version 0.2 2012-4-20
 */
public interface IRewardIntegRuleDao {
	
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
}
