package com.deppon.crm.module.customer.server.manager.impl;

import java.util.Date;

import com.deppon.crm.module.customer.server.manager.IIntegralRule;
import com.deppon.crm.module.customer.shared.domain.integral.AdjustIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralOperation;

/**
 * 
 * <p>
 * 积分调整规则,联系人调出<br />
 * </p>
 * 
 * @title AdjustIntegralRule.java
 * @package com.deppon.crm.module.customer.server.manager.impl
 * @author Administrator
 * @version 0.1 2012-4-19
 */
public class AdjustIntegralRule implements IIntegralRule {

	private ContactIntegral main = null;// 主联系人积分对象
	private ContactIntegral other = null;// 转出联系人积分对象

	/**
	 * 
	 * constructer
	 * 
	 * @param ContactIntegral
	 *            otherContact 联系人积分对象
	 * @param ContactIntegral
	 *            mainContact 主联系人积分对象
	 */
	public AdjustIntegralRule(ContactIntegral mainContact,
			ContactIntegral otherContact) {
		this.main = mainContact;
		this.other = otherContact;
	}
	/**
	 * 
	 * <p>
	 * Description:检测是否有当前可用积分<br />
	 *    当前可用积分为0  返回 true，否则返回false 
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-2-2
	 * */
	@Override
	public boolean isValidate() {
		if (other.getCurrentUsableScore() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * Description:把调出联系人的积分全部转移到主联系人积分上
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-2-2
	 * */
	@Override
	public IntegralOperation getBizIntegralOperation() {
		if (isValidate()) {
			return null;
		}
		// 转出积分
		AdjustIntegral otherIntegral = null;
		// 转入积分
		AdjustIntegral mainIntegral = null;
		// 返回对象
		IntegralOperation integralOperation = new IntegralOperation();

		int score = other.getCurrentUsableScore();// 转出积分

		main.setCurrentUsableScore(main.getCurrentUsableScore() + score);// 本期可用积分
		main.setCurrentTotalScore(main.getCurrentTotalScore() + score);// 本期累计积分
		main.setTotalScore(main.getTotalScore() + score);// 累计积分
		main.setLastDateScore(new Date());// 最后积分时间

		other.setCurrentUsableScore(0);// 本期可用积分

		other.setLastDateScore(new Date());// 最后积分时间

		// 插入积分
		otherIntegral = new AdjustIntegral();
		// 调整类型
		otherIntegral.setAdjustType("联系人调整");// TODO
		// 调整来源
		otherIntegral.setContactFrom(other.getContact());
		// 调整去向
		otherIntegral.setContactTo(main.getContact());
		// 联系人对象
		otherIntegral.setContact(other.getContact());
		// 积分数
		otherIntegral.setScore(-score);

		// 把积分明细进行保存
		mainIntegral = new AdjustIntegral();
		// 调整类型
		mainIntegral.setAdjustType("联系人调整");// TODO
		// 调整来源
		mainIntegral.setContactFrom(other.getContact());
		// 调整去向
		mainIntegral.setContactTo(main.getContact());
		// 联系人对象
		mainIntegral.setContact(main.getContact());
		// 积分数
		mainIntegral.setScore(score);
		// 需要插入数据的积分对象
		integralOperation.getIntegral().add(otherIntegral);
		integralOperation.getIntegral().add(mainIntegral);
		// 需要修改的联系人积分对象
		// 主联系人积分修改
		integralOperation.getContactIntegral().add(main);
		// 被掉联系人积分修改
		integralOperation.getContactIntegral().add(other);

		return integralOperation;
	}

}
