package com.deppon.crm.module.customer.server.manager;

import com.deppon.crm.module.customer.server.manager.impl.AdjustIntegralRule;
import com.deppon.crm.module.customer.server.manager.impl.IntegralChangeRule;
import com.deppon.crm.module.customer.server.manager.impl.RewardIntegralRule;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGift;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.RewardIntegRule;

public class RuleFactory {

	//积分调整,联系人调出
	public static final int ADJUST_INTEGRAL = 1;
	//礼品兑换
	public static final int CONVERTGIFT_INTEGRAL = 2;
	//手动奖励积分
	public static final int REWARD_INTEGRAL = 3;
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-23
	 * @param type 积分类型
	 * @param ContactIntegral contactIntegral 主联系人积分
	 * @param contactIntegral contactIntegrals 联系人积分数组
	 * @param memberIntegral memberIntegral 会员积分数组
	 * @param integralConvertGifts 礼品数组
	 * @param rewardIntegral 奖励规则
	 * @param integralConvertGifts 奖励基数
	 * //积分奖励规则
	private RewardIntegral rewardIntegral;
	//奖励积分基数
	private int integralBasicNumber;
	 * @return 
	 * IIntegralRule
	 */
	public static IIntegralRule getRule(
			int type, //类型
			ContactIntegral contactIntegral,//主联系人积分
			ContactIntegral[] contactIntegrals,//联系人积分
			MemberIntegral[] memberIntegrals,//会员积分
			RewardIntegRule rewardIntegRule,//手动奖励规则
			int integralBasicNumber,//手动奖励积分基数
			IntegralConvertGift[] integralConvertGifts  //礼品兑换列表
			){
		/**积分调整规则--联系人调整*/
		if (type == ADJUST_INTEGRAL){
			//参数一被调联系人，2是主联系人
			if(contactIntegrals !=null && contactIntegrals[0] != null
					&& contactIntegral != null){
				return new AdjustIntegralRule(contactIntegral,contactIntegrals[0]);
			}
			return null;
			
		} else if(type == CONVERTGIFT_INTEGRAL){
			return new IntegralChangeRule(contactIntegral,
					                       contactIntegrals,
					                       integralConvertGifts,
					                       memberIntegrals[0]);
		} else if(type == REWARD_INTEGRAL){
			return new RewardIntegralRule(rewardIntegRule,
					                      integralBasicNumber,
					                      contactIntegrals,
					                      memberIntegrals);
		}
		return null;
	}
}
