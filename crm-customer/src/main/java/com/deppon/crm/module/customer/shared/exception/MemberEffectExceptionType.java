package com.deppon.crm.module.customer.shared.exception;


public enum MemberEffectExceptionType {
	//没有对应的证件存在
	No_IDCARD_Exist("i18n.membereffect.No_IDCARD_Exist"),
	//请输入合法的税务登记号
	TaxNumber_ERROR("i18n.membereffect.TaxNumber_ERROR"),
	//请输入合法的第一代身份证
	FirstGenerateIdcard_ERROR("i18n.membereffect.FirstGenerateIdcard_ERROR"),
	//请输入合法的第二代身份证
	SecondGenerateIdcard_ERROR("i18n.membereffect.SecondGenerateIdcard_ERROR"),
	//请输入合法的军官证
	Officer_ERROR("i18n.membereffect.Officer_ERROR"),
	//请输入合法的香港居民身份证
	HKidcard_ERROR("i18n.membereffect.HKidcard_ERROR"), 
	//请输入合法的台胞证
	TAIBAO_ERROR("i18n.membereffect.TAIBAO_ERROR"), 
	//请输入合法的回乡证
	BACKHOME_ERROR("i18n.membereffect.BACKHOME_ERROR"), 
	//请输入合法的海员证
	SEAMAN_ERROR("i18n.membereffect.SEAMAN_ERROR")
	;
	
	private String errCode;

	private MemberEffectExceptionType(String errCode) {
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
