package com.deppon.crm.module.customer.server.manager.impl;

import java.math.BigDecimal;

import com.deppon.crm.module.customer.server.manager.IIntegralRule;
import com.deppon.crm.module.customer.shared.domain.integral.AdjustIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGift;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralOperation;

/**
 * 
 * <p>
 * 主联系人调用其他联系人的规则<br />
 * </p>
 * 
 * @title MainContactInvokeIntegralRule.java
 * @package com.deppon.crm.module.customer.server.manager.impl
 * @author Administrator
 * @version 0.1 2012-4-23
 */
public class MainContactInvokeIntegralRule implements IIntegralRule {

	// 主联系人积分
	protected ContactIntegral mainContactIntegral = null;
	// 其他联系人积分
	protected ContactIntegral[] contactIntegrals = null;
	// 礼品兑换所需总积分数
	protected int giftTotScore = 0;
	// 礼品组
	protected IntegralConvertGift[] integralConvertGifts = null;
	// 会员本期可用积分
	protected int currentUsableMemberScore = 0;

	/**
	 * 
	 * constructer
	 * 
	 * @param mainContactIntegral
	 *            主联系人积分
	 * @param contactIntegrals
	 *            其它联系人积分
	 * @param integralConvertGifts
	 *            礼品数组
	 * @param currentUsableMemberScore
	 *            会员可用积分
	 */
	public MainContactInvokeIntegralRule(ContactIntegral mainContactIntegral,
			ContactIntegral[] contactIntegrals,
			IntegralConvertGift[] integralConvertGifts,
			int currentUsableMemberScore) {
		this.mainContactIntegral = mainContactIntegral;
		this.contactIntegrals = contactIntegrals;
		this.integralConvertGifts = integralConvertGifts;
		this.currentUsableMemberScore = currentUsableMemberScore;
		// 获得礼品所需积分
		if (integralConvertGifts != null){
			for (IntegralConvertGift integralConvertGift : integralConvertGifts) {
				giftTotScore += integralConvertGift.getGift().getNeedPoints()
						* integralConvertGift.getConvertNumber();
			}
		}
	}

	@Override
	public boolean isValidate() {
		if (mainContactIntegral != null && contactIntegrals != null && contactIntegrals.length > 0
				&& currentUsableMemberScore >= mainContactIntegral.getCurrentUsableScore()
				&& mainContactIntegral.getCurrentUsableScore() >= 0
				&& currentUsableMemberScore > 0) {
			return true;
		}
		return false;
	}

	@Override
	public IntegralOperation getBizIntegralOperation() {
		if(!isValidate()){
			return null; 
		}
		// 联系人被调用积分
		AdjustIntegral adjustIntegral;
		// 主联系人调用积分
		AdjustIntegral mainAdjustIntegral;
		// 返回值设定
		IntegralOperation integralOperation = new IntegralOperation();
		// 调用联系人积分和
		int score = 0;
		// 每个联系人消化积分
		int conScore = 0;
		// 计算每个联系人所需积分
		for (ContactIntegral contactIntegral : contactIntegrals) {

			adjustIntegral = new AdjustIntegral();// 一般联系人
			// 联系人
			adjustIntegral.setContact(contactIntegral.getContact());
			// 调整类型
			adjustIntegral.setAdjustType("兑换调用");// TODO
			// 联系人把积分调到主联系人--调整去向
			adjustIntegral.setContactTo(mainContactIntegral.getContact());
			// 调用积分数
			conScore = getScore(contactIntegral.getCurrentUsableScore());

			adjustIntegral.setScore(-conScore);

			// 把调用联系人放入返回
			integralOperation.getIntegral().add(adjustIntegral);

			// 主联系人
			mainAdjustIntegral = new AdjustIntegral();
			// 联系人
			mainAdjustIntegral.setContact(mainContactIntegral.getContact());
			// 调整类型
			mainAdjustIntegral.setAdjustType("兑换调用");// TODO
			// 主联系人调用谁的积分--调整来源
			mainAdjustIntegral.setContactFrom(contactIntegral.getContact());
			//主联系人积分数添加
			mainAdjustIntegral.setScore(conScore);
			// 把对应的主联系人放入返回
			integralOperation.getIntegral().add(mainAdjustIntegral);

			// 联系人积分对象的操作
			// 本期可用积分更新
			contactIntegral.setCurrentUsableScore(contactIntegral
					.getCurrentUsableScore() - conScore);
			// 本期已用积分
			contactIntegral.setCurrentToUsesScore(contactIntegral
					.getCurrentToUsesScore() + conScore);
			// 累计已用积分
			contactIntegral.setTotalToUsesScore(contactIntegral
					.getTotalToUsesScore() + conScore);
			// 把联系人积分对象放到修改集合中
			integralOperation.getContactIntegral().add(contactIntegral);

			score = score + conScore;
		}
		// 把主联系人放到修改集合中
		mainContactIntegral.setCurrentUsableScore(mainContactIntegral
				.getCurrentUsableScore() + score);// 本期可用积分
		return integralOperation;
	}

	/**
	 * 
	 * <p>
	 * 当前联系所扣的积分为主联系人调用积分的加权平均数<br />
	 * 如：所需积分为1000分，由3个联系人提供，A有500分，B有1000分，C有1500分
	 * 那A提供的积分=(500/(500+1000+1500))*1000
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2012-4-23
	 * @param contactUsableScore
	 * @return 每个联系人所花积分 int
	 */
	private int getScore(int contactUsableScore) {
		BigDecimal ba = new BigDecimal(contactUsableScore);
		BigDecimal bb = new BigDecimal(currentUsableMemberScore);
		BigDecimal bc = new BigDecimal(giftTotScore);
		return ba.divide(bb,5,BigDecimal.ROUND_HALF_UP).multiply(bc).setScale(0, BigDecimal.ROUND_HALF_UP)
				.intValue();

	}

}
