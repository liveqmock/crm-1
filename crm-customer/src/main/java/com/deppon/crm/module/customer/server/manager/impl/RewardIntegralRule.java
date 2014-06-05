package com.deppon.crm.module.customer.server.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.deppon.crm.module.customer.server.manager.IIntegralRule;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.HandRewardIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralOperation;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.RewardIntegRule;
import com.deppon.crm.module.customer.shared.exception.IntegException;
import com.deppon.crm.module.customer.shared.exception.IntegExceptionType;

/**
 * 
 * <p>
 * 手动奖励积分规则<br />
 * </p>
 * 
 * @title RewardIntegralRule.java
 * @package com.deppon.crm.module.customer.server.manager.impl
 * @author Administrator
 * @version 0.1 2012-4-19
 */
public class RewardIntegralRule implements IIntegralRule {
	// 奖励名称
	private RewardIntegRule rewardIntegRule;
	// 奖励积分基数
	private int integralBasicNumber;
	// 奖励联系人积分
	private ContactIntegral[] contactIntegrals;
	// 会员积分
	private MemberIntegral[] memberIntegrals;

	private Map<MemberIntegral, List<ContactIntegral>> map = new HashMap<MemberIntegral, List<ContactIntegral>>();
	// 奖励积分数
	private int score = 0;
	/**
	 * 
	 * constructer
	 * @param rewardIntegRule
	 * @param integralBasicNumber
	 * @param contactIntegrals
	 * @param memberIntegrals
	 */
	public RewardIntegralRule(RewardIntegRule rewardIntegRule,
			int integralBasicNumber, ContactIntegral[] contactIntegrals,
			MemberIntegral[] memberIntegrals) {
		this.rewardIntegRule = rewardIntegRule;
		this.integralBasicNumber = integralBasicNumber;
		this.contactIntegrals = contactIntegrals;
		this.memberIntegrals = memberIntegrals;
	}
	/**
	 * 
	 * <p>
	 * Description:是否相等<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @return
	 *
	 */
	@Override
	public boolean isValidate() {
		if(rewardIntegRule == null || !(rewardIntegRule.getPointvalue() >0 || rewardIntegRule.getFraction() >0)){
			return false;
		}
		if(!(integralBasicNumber>0)){
			return false;
		}
		if(contactIntegrals == null || !(contactIntegrals.length > 0)){
			return false;
		}
		if(memberIntegrals == null || !(memberIntegrals.length > 0)) {
			return false;
		}
		return true;
	}
	/**
	 * 
	 * <p>
	 * Description:获得积分操作<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月18日
	 * @return
	 *
	 */
	@Override
	public IntegralOperation getBizIntegralOperation() {
		//规则是否在有效时间内
		chechRuleDate();
		if(!isValidate()) {
			throw new IntegException(IntegExceptionType.IntegIszero);
		}
		// 获得奖励积分值
		getScore();
		//会员和联系人关联
		membetAndContact();
		// 返回值设定
		IntegralOperation integralOperation = new IntegralOperation();

		for (Entry<MemberIntegral, List<ContactIntegral>> entry : map
				.entrySet()) {
			MemberIntegral memberIntegral = entry.getKey();
			
			List<ContactIntegral> list = entry.getValue();
			
			HandRewardIntegral handRewardIntegral;
			//会员累计积分数
			int memberScore = score * list.size();
			
			for (ContactIntegral contactIntegral : list) {
				// 先把数据插入
				handRewardIntegral = new HandRewardIntegral();
				// 奖励联系人
				handRewardIntegral.setContact(contactIntegral.getContact());
				// 奖励分数
				handRewardIntegral.setScore(score);
				// 奖励名称
				handRewardIntegral.setRewardIntegral(rewardIntegRule);
				// 奖励类型
				handRewardIntegral.setType("手动奖励积分");// TODO
				// 奖励基数
				handRewardIntegral.setIntegralBasicNumber(integralBasicNumber);

				integralOperation.getIntegral().add(handRewardIntegral);
				// 更新积分联系人
				// 本期可用积分
				contactIntegral.setCurrentUsableScore(contactIntegral
						.getCurrentUsableScore() + score);
				// 本期累计积分
				contactIntegral.setCurrentTotalScore(contactIntegral
						.getCurrentTotalScore() + score);
				// 累计积分
				contactIntegral.setTotalScore(contactIntegral.getTotalScore()
						+ score);
				// 最后积分时间
				contactIntegral.setLastDateScore(new Date());

				integralOperation.getContactIntegral().add(contactIntegral);
			}
			memberIntegral.setCurrentUsableScore(memberIntegral.getCurrentUsableScore()
					+memberScore);//本期可用积分
			memberIntegral.setCurrentTotalScore(memberIntegral.getCurrentTotalScore()
					+memberScore);//本期累计积分
			memberIntegral.setTotalScore(memberIntegral.getTotalScore()
					+memberScore);//累计积分
			memberIntegral.setLastDateScore(new Date());//最后积分时间
			
			integralOperation.getMemberIntegral().add(memberIntegral);
		}
		return integralOperation;
	}

	/**
	 * 
	 * <p>
	 * 获得奖励积分值<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2012-4-25 void
	 */
	private void getScore() {
		if (Constant.RULETYPE_SCORE.equals(rewardIntegRule.getIntegType())) {
			score = rewardIntegRule.getPointvalue();
		} else {
			score = (int) Math.round(integralBasicNumber
					* (rewardIntegRule.getFraction()));
		}
	}

	/**
	 * 
	 * <p>
	 * 把联系人列表和会员列表建立关联<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2012-4-25 void
	 */
	private void membetAndContact() {
		String memberId = null;
		for (MemberIntegral memberIntegral : memberIntegrals) {
			memberId = memberIntegral.getMember().getId();
			List<ContactIntegral> list = new ArrayList<ContactIntegral>();
			for (ContactIntegral contactIntegral : contactIntegrals) {
				if (memberId.equals(contactIntegral.getContact().getCustId())) {
					list.add(contactIntegral);
				}
			}
			map.put(memberIntegral, list);
		}
	}

	/**
	 * 
	 * <p>该规则是否在有效时间内<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-5-14
	 * void
	 */
	private void chechRuleDate(){
		if(!(rewardIntegRule.getPointbegintime().before(new Date())
				&& rewardIntegRule.getPointendtime().after(new Date()))){
			throw new IntegException(IntegExceptionType.IntegRuleDateError);
		}
	}
}
