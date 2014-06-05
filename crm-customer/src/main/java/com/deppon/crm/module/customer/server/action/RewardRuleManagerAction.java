package com.deppon.crm.module.customer.server.action;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.customer.server.manager.IIntegRuleManager;
import com.deppon.crm.module.customer.shared.domain.integral.RewardIntegRule;
import com.deppon.foss.framework.server.web.action.AbstractAction;
/**
 * <p>
 * 积分 奖励规则管理action<br />
 * </p>
 * @title RewardRuleManagerAction.java
 * @package com.deppon.crm.module.customer.server.action
 * @author 李学兴
 * @version 0.1 2012-4-21
 */

public class RewardRuleManagerAction extends AbstractAction {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 6258124542687218166L;
	private IIntegRuleManager integRuleManager;
	private List<RewardIntegRule> rewardIntegRuleList = new ArrayList<RewardIntegRule>();
	//前台传入的条件查询潜客信息的start参数（用于分页）
	private int start;
	//前台传入的条件查询潜客信息的limit参数（用于分页）
	private int limit;
	//传入前台的通过条件查询潜客结果总数
	private Long totalCount;
	private RewardIntegRule rewardIntegRule = new RewardIntegRule();
	/**
	 * 
	 * <p>
	 * 查询奖励规则数据<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-26
	 * @return String
	 */
	public String searchRewardIntegRuleList(){
		rewardIntegRuleList = integRuleManager.getRewardIntegRules(start,limit);
		totalCount = Long.valueOf(integRuleManager.countRewardIntegRules());
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * 新增奖励规则数据<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-26
	 * @return String
	 */
	public String createRewardIntegRule(){
		integRuleManager.saveRewardIntegRule(rewardIntegRule);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * 修改奖励规则数据<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-26
	 * @return String
	 */
	public String updateRewardIntegRule(){
		integRuleManager.updateRewardIntegRule(rewardIntegRule);
		return SUCCESS;
	}
	
	public List<RewardIntegRule> getRewardIntegRuleList() {
		return rewardIntegRuleList;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public RewardIntegRule getRewardIntegRule() {
		return rewardIntegRule;
	}
	public void setRewardIntegRule(RewardIntegRule rewardIntegRule) {
		this.rewardIntegRule = rewardIntegRule;
	}
	public void setIntegRuleManager(IIntegRuleManager integRuleManager) {
		this.integRuleManager = integRuleManager;
	}
	
}
