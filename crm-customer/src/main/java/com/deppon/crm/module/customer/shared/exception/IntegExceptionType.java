package com.deppon.crm.module.customer.shared.exception;


public enum IntegExceptionType {
	IntegIszero("i18n.integ.IntegIszero"),//积分为零
	IntegWKidNoData("i18n.integ.IntegWKidNoData"),//工作流ID找不到数据
	IntegGiftNotEnough("i18n.integ.IntegGiftNotEnough"),//库存不够
	IntegNotEnough("i18n.integ.IntegNotEnough"),//积分不够
	IntegRuleDateError("i18n.integ.IntegRuleDateError"),//当前时间不在奖励规则生效时间内
	ContactIntegralNotExist("i18n.integ.ContactIntegralNotExist"),//联系人积分不存在
	MemberIntegralNotExist("i18n.integ.MemberIntegralNotExist"),//会员积分不存在
	MainLinkManNotChangeMember("i18n.integ.MainLinkManNotChangeMember"),//主联系人不能变更挂靠关系
	IntegGiftChangeInterFaces("i18n.integ.IntegGiftChangeInterFaces"), //接口错误
	IntegRuleExist("i18n.integ.IntegRuleExist"),//积分规则已近存在
	SameMemberCanNotContact("i18n.integ.SameMemberCanNotContact")//同一个会员不能互相变更
	;
	
	private String errCode;

	private IntegExceptionType(String errCode) {
		this.errCode = errCode;
	}

	/**
	 * <p>
	 * Description:errCode<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getErrCode() {
		return errCode;
	}

	/**
	 * <p>
	 * Description:errCode<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
}
