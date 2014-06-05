package com.deppon.crm.module.customer.shared.domain;

/**
 * 
 * Description 证件验证类型
 * 
 * @author 吴根斌
 * @version 0.1 2012-7-23
 */
public enum Certification {

	// 税务登记证
	TAX_CARD("TAX_CARD"),
	// 第一代身份证
	FIRST_GENERATION_IDCARD("FIRST_GENERATION_IDCARD"),
	// 第二代身份证
	SECOND_GENERATION_IDCARD("SECOND_GENERATION_IDCARD"),
	// 军官证
	OFFICER_IDCARD("OFFICER_IDCARD"),
	// 香港居民身份证
	HONGKONG_IDCARD("HONGKONG_IDCARD"),
	// 回乡证
	BACKHOME_CARD("BACKHOME_CARD"),
	// 台胞证
	TAIBAO_CARD("TAIBAO_CARD"),
	// 海员证
	SEAMAN_CARD("SEAMAN_CARD"),
	// 其他证，仅限外籍人员证件
	OTHER("OTHER");

	private String idType;

	/**
	 * constructer
	 * 
	 * @param idType
	 */
	private Certification(String idType) {
		this.idType = idType;
	}
	
	/**
	 * 
	 * <p>
	 * Description:获取证件类型<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-11
	 * @param idType
	 * @return
	 * Certification
	 */
	public static Certification getCertification(String idType) {
		if ("TAX_CARD".equals(idType)) {
			return TAX_CARD;
		} else if ("FIRST_GENERATION_IDCARD".equals(idType)) {
			return FIRST_GENERATION_IDCARD;
		} else if ("SECOND_GENERATION_IDCARD".equals(idType)) {
			return SECOND_GENERATION_IDCARD;
		} else if ("OFFICER_IDCARD".equals(idType)) {
			return OFFICER_IDCARD;
		} else if ("HONGKONG_IDCARD".equals(idType)) {
			return HONGKONG_IDCARD;
		} else if ("BACKHOME_CARD".equals(idType)) {
			return BACKHOME_CARD;
		} else if ("TAIBAO_CARD".equals(idType)) {
			return TAIBAO_CARD;
		} else if ("SEAMAN_CARD".equals(idType)) {
			return SEAMAN_CARD;
		} else {
			return OTHER;
		}

	}

	/**
	 * <p>
	 * Description:idType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getIdType() {
		return idType;
	}

}
