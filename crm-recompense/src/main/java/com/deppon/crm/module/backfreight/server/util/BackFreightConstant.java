 package com.deppon.crm.module.backfreight.server.util;

/**
 * Description: Constant.java Create on 2012-11-5 下午4:07:11
 * 
 * @author 华龙
 * @version 1.0
 */
public class BackFreightConstant {
	// 已提交
	public static final String BACK_FREIGHT_STATUS_WAIT_ACCEPT = "APPLY_SUBMIT";
	// 已同意
	public static final String BACK_FREIGHT_STATUS_ACCEPTED = "APPLY_AGREED";
	// 未同意
	public static final String BACK_FREIGHT_STATUS_NOT_ACCEPT = "UNAGREE";

	/*
	 * 运输性质(三级产品)： 精准汽运（长途）LRF 精准卡航 FLF 精准汽运（短途） SRF 精准城运 FSF 汽运偏线 PLF 精准空运 AF
	 * 整车 MVH
	 */
	// 运输性质
	// 精准空运
	public static final String BACK_FREIGHT_TRAN_TYPE_AIR = "ACCURATE_AIR";
	// 精准城运
	public static final String BACK_FREIGHT_TRAN_TYPE_CITY = "ACCURATE_CITY";
	public static final String BACK_FREIGHT_TRAN_TYPE_LRF = "LRF";
	public static final String BACK_FREIGHT_TRAN_TYPE_FLF = "FLF";
	public static final String BACK_FREIGHT_TRAN_TYPE_SRF = "SRF";
	public static final String BACK_FREIGHT_TRAN_TYPE_PLF = "PLF";
	public static final String BACK_FREIGHT_TRAN_TYPE_FV = "MVH";
	//快递
	public static final String BACK_FREIGHT_TRAN_TYPE_PACKAGE = "PACKAGE";

	// 付款方式:到付
	public static final String BACK_FREIGHT_PAYMENT_ARRIVE = "PAY_ARRIVE";
	// 付款方式:现金
	public static final String BACK_FREIGHT_PAYMENT_CASH = "PAY_CASH";
	// 付款方式:银行卡
	public static final String BACK_FREIGHT_PAYMENT_CARD = "PAY_CARD";
	// 付款方式:临时欠款
	public static final String BACK_FREIGHT_PAYMENT_ARREARS = "PAY_ARREARS";
	// 付款方式:月结
	public static final String BACK_FREIGHT_PAYMENT_MONTHLY = "PAY_MONTHLY";
	// 付款方式:网上支付
	public static final String BACK_FREIGHT_PAYMENT_ONLINE = "PAY_ONLINE";


	// 付款方式:全部
	public static final String BACK_FREIGHT_PAYMENT__ALL = "PAY_ALL";

	public static final String BACK_FREIGHT_SOURCE_TYPE = "BACKFREIGHT";
	public static final String LEAVE = "leave";
	public static final String ARRIVE = "arrive";


}
