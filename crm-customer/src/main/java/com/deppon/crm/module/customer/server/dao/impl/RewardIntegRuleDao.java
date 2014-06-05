package com.deppon.crm.module.customer.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.customer.server.dao.IRewardIntegRuleDao;
import com.deppon.crm.module.customer.server.utils.DataUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.integral.RewardIntegRule;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
/**
 * 
 * <p>
 * Description: 积分奖励规则dao<br />
 * </p>
 * @title RewardIntegRuleDao.java
 * @package com.deppon.crm.module.customer.server.dao.impl 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class RewardIntegRuleDao extends iBatis3DaoImpl implements IRewardIntegRuleDao{
	//命名空间
	private static final String NAME_SPACE = "com.deppon.crm.module.customer.shared.domain.integral.RewardIntegRule.";
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
		Map<String,Object> map = DataUtil.changeObjectToMap(rewardIntegral);
		map.put("pointtype",Constant.RewardIntegRule);
		//保存奖励规则
		this.getSqlSession().insert(NAME_SPACE+"saveRewardIntegRule", map);
		rewardIntegral.setId((String) map.get("id"));
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
		//修改奖励规则
		this.getSqlSession().insert(NAME_SPACE+"updateRewardIntegRule", rewardIntegral);
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
	@SuppressWarnings("unchecked")
	@Override
	public List<RewardIntegRule> getRewardIntegRules(int start, int limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pointtype",Constant.RewardIntegRule);
		//分页
		RowBounds row = new RowBounds(start,limit);
		// 查询全部奖励规则
		return this.getSqlSession().selectList(NAME_SPACE+"getRewardIntegRules",map,row);
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
		Map<String,Object> map = new HashMap<String,Object>();
		//设置奖励规则
		map.put("pointtype",Constant.RewardIntegRule);
		//得到全部奖励规则总
		return (Integer) this.getSqlSession().selectOne(NAME_SPACE+"countRewardIntegRules",map);
	}

}
