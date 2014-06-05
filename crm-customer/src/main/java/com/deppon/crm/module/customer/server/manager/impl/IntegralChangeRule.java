package com.deppon.crm.module.customer.server.manager.impl;

import java.util.Date;

import com.deppon.crm.module.customer.server.manager.IIntegralRule;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGift;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralOperation;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;
import com.deppon.crm.module.customer.shared.exception.IntegException;
import com.deppon.crm.module.customer.shared.exception.IntegExceptionType;

/**
 * 
 * <p>
 * 积分兑换规则<br />
 * 把积分和礼品兑换进行操作
 * </p>
 * 
 * @title IntegralConvertRule.java
 * @package com.deppon.crm.module.customer.server.manager.impl
 * @author Administrator
 * @version 0.1 2012-4-19
 */
public class IntegralChangeRule extends MainContactInvokeIntegralRule
		implements IIntegralRule {

	//会员积分对象
	private MemberIntegral memberIntegral = null;

	/**
	 * 
	 * constructer
	 * @param mainContactIntegral 兑换礼品联系人积分
	 * @param contactIntegrals 联系人积分列表--给主联系人调用积分使用
	 * @param integralConvertGifts 礼品列表
	 * @param memberIntegral 会员积分对象
	 */
	public IntegralChangeRule(ContactIntegral mainContactIntegral,
			ContactIntegral[] contactIntegrals,
			IntegralConvertGift[] integralConvertGifts,
			MemberIntegral memberIntegral) {
		super(mainContactIntegral, contactIntegrals, integralConvertGifts,
				memberIntegral.getCurrentUsableScore());
		this.memberIntegral = memberIntegral;
	}

	@Override
	public boolean isValidate() {
		if (memberIntegral.getMember().getIsRedeempoints() && contactIntegrals != null && contactIntegrals.length > 0){
			return super.isValidate();
		}
		if(mainContactIntegral != null && mainContactIntegral.getCurrentUsableScore()>giftTotScore 
				&& giftTotScore > 0 && integralConvertGifts != null && integralConvertGifts.length > 0){
			return true;
		}
		return false;
	}

	@Override
	public IntegralOperation getBizIntegralOperation() {
		// 返回值设定
		IntegralOperation integralOperation = null;
		if (memberIntegral.getMember().getIsRedeempoints() && contactIntegrals != null && contactIntegrals.length > 0){
			integralOperation = super.getBizIntegralOperation();
			if (integralOperation == null){
				throw new IntegException(IntegExceptionType.IntegNotEnough);
			}
		}
		if (!isValidate()){
			throw new IntegException(IntegExceptionType.IntegNotEnough);
		}
		if (integralOperation == null){
			integralOperation = new IntegralOperation();
		}
		for (IntegralConvertGift integralConvertGift : integralConvertGifts) {
			// 兑换联系人
			integralConvertGift.setContact(mainContactIntegral.getContact());

			// 单个礼品所需积分
			integralConvertGift.setScore(integralConvertGift.getConvertNumber()
					* integralConvertGift.getGift().getNeedPoints());

			// 放入到插入数据
			integralOperation.getIntegral().add(integralConvertGift);
		}
		// 更新数据
		// 本期可用积分
		mainContactIntegral.setCurrentUsableScore(mainContactIntegral
				.getCurrentUsableScore() - giftTotScore);
		// 本期已用积分
		mainContactIntegral.setCurrentToUsesScore(mainContactIntegral
				.getCurrentToUsesScore() + giftTotScore);
		// 累计已用积分
		mainContactIntegral.setTotalToUsesScore(mainContactIntegral
				.getTotalToUsesScore() + giftTotScore);
		// 最后积分时间
		mainContactIntegral.setLastDateScore(new Date());
		// 设置要修改的联系人积分对象
		integralOperation.getContactIntegral().add(mainContactIntegral);
		// 设置会员积分
		memberIntegral.setCurrentUsableScore(memberIntegral
				.getCurrentUsableScore() - giftTotScore);// 本期可用积分
		memberIntegral.setCurrentToUsesScore(memberIntegral
				.getCurrentToUsesScore() + giftTotScore);// 本期已用积分
		memberIntegral.setTotalToUsesScore(memberIntegral.getTotalToUsesScore()
				+ giftTotScore);// 累计已用积分
		memberIntegral.setLastDateScore(new Date());// 最后积分时间

		integralOperation.getMemberIntegral().add(memberIntegral);
		return integralOperation;
	}

}
