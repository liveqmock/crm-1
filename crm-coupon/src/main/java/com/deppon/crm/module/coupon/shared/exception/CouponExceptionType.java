package com.deppon.crm.module.coupon.shared.exception;
/**   
 * <p>
 * Description:优惠券异常类<br />
 * </p>
 * @author 钟琼
 * @version 0.1 2012-11-13
 */ 
public enum CouponExceptionType {
	/********************************** 新增营销计划校验规则  start ***********************************/
    // 营销计划名称为空 不满足
	couponPlanNameIsNull("i18n.coupon.couponPlanNameIsNull"),
	// marketPlaneVO为空 不满足(i18n)
	marketPlaneVOIsNull("i18n.coupon.marketPlaneVOIsNull"),
	// 营销计划为空 不满足
	marketPlanIsNull("i18n.coupon.marketPlanIsNull"),
	// 营销编码名称为空 不满足
	marketPlanNumberIsNull("i18n.coupon.marketPlanNumberIsNull"),
	// 使用金额为空 不满足
	couponUseValueIsNull("i18n.coupon.couponUseValueIsNull"),
	// 优惠券手动发券 发券数量为空 不满足
	couponHandQuantityIsNull("i18n.coupon.couponHandQuantityIsNull"),
	// 优惠券手动发券 发券数量为空 不满足
	couponHandQuantityIsLarger("i18n.coupon.couponHandQuantityIsLarger"),
	// 优惠券手动发券 为空 不满足
	couponHandIsNull("i18n.coupon.couponHandIsNull"),
	// 优惠券手动发券 为空 不满足
	couponAutoIsNull("i18n.coupon.couponAutoIsNull"),
	// 返券开始日期必须不早于当前日期，返券结束日期不早于返券开始日期
	couponAutoDateIsWrong("i18n.coupon.couponAutoDateIsWrong"),
	// 优惠券使用规则-为空
	couponRuleIsNull("i18n.coupon.couponRuleIsNull"),
	// 自动返券的返券金额为空 不满足
	AutoCouponCostIsNull("i18n.coupon.AutoCouponCostIsNull"),
	// 自动返券的返券金额最多为10条
	AutoCouponCostIsLarger("i18n.coupon.AutoCouponCostIsLarger"),
	// 使用条件的金额要求为空 不满足
	AutoCouponCostTypeIsNull("i18n.coupon.AutoCouponCostTypeIsNull"),
	// 使用规则开始日期必须不早于当前日期，结束日期不早于开始日期；
	couponRuleDateIsWrong("i18n.coupon.couponRuleDateIsWrong"),
	// 短信输入过长
	couponSmsContentIsLong("i18n.coupon.couponSmsContentIsLong"),
	// 优惠券描述输入过长
	couponDescribeIsLong("i18n.coupon.couponDescribeIsLong"),
	// 营销计划发券规则类型为空
	couponPlanTypeIsNull("i18n.coupon.couponPlanTypeIsNull"),
	// 优惠券 营销编码为空
	couponMarketPlanIdIsNull("i18n.coupon.couponMarketPlanIdIsNull"),
	// 优惠券 营销编码为空
	couponTypeIdIsNull("i18n.coupon.couponTypeIdIsNull"),
	// 优惠券使用条件不为空
	couponCouponRuleIsNull("i18n.coupon.couponRuleIsNull"), 
	// 营销计划发券规则 为空
	ruleCouponIsNull("i18n.coupon.ruleCouponIsNull"),
	// 营销计划查询最大范围为3个月
	searchMarketPlanDateIsLong("i18n.coupon.searchMarketPlanDateIsLong"),
	// 优惠券使用规则 分级抵扣 抵扣金额错误
	couponRuleCostDiscountIsWrong("i18n.coupon.couponRuleCostDiscountIsWrong"),
	// 优惠券使用规则 增值费错误
	couponRuleValueIsWrong("i18n.coupon.couponRuleValueIsWrong"),
	// 营销计划更新 id错误
	marketPlanIdsIsWrong("i18n.coupon.idIsWrong"),
	// 操作人为空
	operatorUserIsNull("i18n.coupon.operatorUserIsNull"),
	//营销计划未启用
	marketPlanStatusUnuse("i18n.coupon.marketPlanStatusUnuse"),
	//数量或金额填写有误
	couponValueOrQuantityIsNull("i18n.coupon.couponValueOrQuantityIsNull"),
	
	/********************************** 新增营销计划校验规则 end ***********************************/
	
	/********************************** 优惠券使用校验规则  start ***********************************/
	// 优惠券不存在
	couponIsntExsit("i18n.coupon.couponIsntExsit"),
	// 优惠券编码不存在
	couponNumberIsntExsit("i18n.coupon.couponNumberIsntExsit"),
	// 运单不存在
	wbInfoIsntExsit("i18n.coupon.wbInfoIsntExsit"),
	// 运单号不存在
	waybillNumberIsntExsit("i18n.coupon.waybillNumberIsntExsit"),
	// 订单号不存在
	orderNumberIsntExsit("i18n.coupon.orderNumberIsntExsit"),
	// 订单来源不存在
	orderSourceIsntExsit("i18n.coupon.orderSourceIsntExsit"),
	// 订单来源不存在
	produceTypeIsntExsit("i18n.coupon.produceTypeIsntExsit"),
	// 运单总额不存在
	totalAmountIsntExsit("i18n.coupon.totalAmountIsntExsit"),
	//发到货部门有误
	wbInfoDeptIsntExsit("i18n.coupon.wbInfoDeptIsntExsit"),
	//发到货外场有误
	wbInfoOutDeptIsntExsit("i18n.coupon.wbInfoOutDeptIsntExsit"),
	//运单金额明细有误
	amountListExsit("i18n.coupon.amountListExsit"),
	// 优惠券已过期
	couponIsOverdue("i18n.coupon.couponIsOverdue"),
	// 优惠券已使用
	couponIsUsed("i18n.coupon.couponIsUsed"),
	// 优惠券尚未生效（日期）
	couponIneffectiveDate("i18n.coupon.couponIneffectiveDate"),
	// 金额要求不满足
	couponAmountType("i18n.coupon.couponAmountType"),
	// 金额要求异常
	couponAmountTypeException("i18n.coupon.couponAmountTypeException"),
	// 增值费要求不满足
	couponAppreciationType("i18n.coupon.couponAppreciationType"),
	// 产品类型不满足
	couponProductType("i18n.coupon.couponProductType"),
	// 订单来源不满足
	couponOrderSource("i18n.coupon.couponOrderSource"),
	// 客户等级不满足
	couponCustLevel("i18n.coupon.couponCustLevel"),
	// 客户行业不满足
	couponCustTrade("i18n.coupon.couponCustTrade"),
	// 线路区域要求不满足
	couponLineAreaType("i18n.coupon.couponLineAreaType"),
	// 使用校验规则异常
	couponUnknow("i18n.coupon.couponUnknow"),
	//优惠券数量少于手机号码数量
	couponCountAndPhonesNotMatch("i18n.coupon.couponCountAndPhonesNotMatch"),
	//优惠券还未生成
	couponNotCreated("i18n.coupon.couponNotCreated"),
	//优惠券生成Excel数量大于20000条
	maxCouponExcelNumOverflow("i18n.coupon.maxCouponExcelNumOverflow"),
	/********************************** 优惠券使用校验规则  end ***********************************/
	// 只有未启用的营销计划 才允许编辑
	onlyNoUseMarketPlanCanEdit("i18n.coupon.onlyNoUseMarketPlanCanEdit"),
	// 发券规则 不能修改
	couponTypeCanNotChange("i18n.coupon.couponTypeCanNotChange"),
	// 无计划名称时，使用时间范围与发送时间范围必填其一
	couponSearchTimeError("i18n.coupon.couponSearchTimeError"),
	// 优惠券查询-使用时间范围错误
	couponSearchUseTimeExceed("i18n.coupon.couponSearchUseTimeExceed"),
	// 优惠券查询-发送时间范围错误
	couponSearchSendTimeExceed("i18n.coupon.couponSearchSendTimeExceed"),
	//运单数量与优惠券数量不匹配
	waybillAndCouponNumberNotMatch("i18n.coupon.waybillAndCouponNumberNotMatch"),
	//营销计划还未开始
	couponMarketPlanStartLater("i18n.coupon.couponMarketPlanStartLater"),
	//营销计划已经结束
	couponMarketPlanEnded("i18n.coupon.couponMarketPlanEnded"),
	//营销计划不是手动发券 
	couponCouponTypeNotHanded("i18n.coupon.couponCouponTypeNotHanded"), 
	// 营销计划当前状态不允许 此操作
	marketPlanStateCanNotOperate("i18n.coupon.marketPlanStateCanNotOperate"),
	// 只有处于已发送状态的优惠券，才能重新发送短信；
	reSendCouponErrorStatus("i18n.coupon.reSendCouponErrorStatus"),
	// 已达到短信发送最大次数限制
	reSendCouponErrorTimes("i18n.coupon.reSendCouponErrorTimes"),
	// 短信发送过于频繁，请10分钟后再试。
	reSendCouponErrorInterval("i18n.coupon.reSendCouponErrorInterval"),
	//短信发送失败
	messageSendError("i18n.coupon.messageSendError"),
	//短信发券中有无效手机号码
	couponCellphoneInvalidity("i18n.coupon.couponCellphoneInvalidity"),
	//短信发券时最大发券数量溢出
	maxSendCouponNumberOverflow("i18n.coupon.maxSendCouponNumberOverflow"),
	//生成优惠券号码数量已超最大允许值
	maxCouponNumberOverflow("i18n.coupon.maxCouponNumberOverflow"),
	/********************************* Excel文件异常  *************************************/
	excelTypeError("i18n.coupon.excelTypeError"),
	//excel报表文件导出失败
	failureToExport("i18n.coupon.failureToExport"),
	
	//未找到下载模板
	noDownloadTemplate("i18n.coupon.noDownloadTemplate"),
	fileAnalyticError("i18n.coupon.fileAnalyticError"),
	
	/***********************************接口创建优惠券**********************************************/
	//接口实体为空
	couponForInterfaceIsNull("i18n.coupon.couponInterface.isNull"),
	//部门标杆编码为空
	couponForInterfaceStandardCodeIsNull("i18n.coupon.couponInterface.standardCodeIsNull"),
	//接口实体的活动类型为空
	couponForInterfaceActivityTypeIsNull("i18n.coupon.couponInterface.activityTypeIsNull"),
	//优惠券部门标杆编码为空
	couponHandDeptStandardCodeIsNull("i18n.coupon.couponInterface.activityTypeIsNull"),
	//走货线路不合法
	couponRuleGoodsLineIsIllegal("i18n.coupon.couponInterface.goodsLineIsIllegal"),
	//优惠券失效时间不合法
	couponForInterfaceEndTimeIsIllegal("i18n.coupon.couponInterface.endTimeIsIllegal"),
	//优惠券生效时间不合法
	couponForInterfaceBeginTimeIsIllegal("i18n.coupon.couponInterface.beginTimeIsIllegal"),
	//优惠券失效时间不能早于开始时间 
	couponForInterfaceEndTimeEarlierThanBeginTime("i18n.coupon.couponInterface.endTimeCantEarlerThanBeginTime"),
	//走货线路不能大于10条
	couponRuleGoodsLineBeyondMaxSize("i18n.coupon.maxTenItem"),
	//产品类型不合法
	couponForInterfaceProductType("i18n.coupon.couponInterface.productTypeIsIlegal"),
	//订单来源不合法
	couponForInterfaceOrderType("i18n.coupon.couponInterface.orderTypeIsIlegal"),
	//客户等级不合法
	couponForInterfaceCustLevel("i18n.coupon.couponInterface.custLevelIsIlegal"),
	//客户行业不合法
	couponForInterfaceCustTrade("i18n.coupon.couponInterface.custTradeIsIlegal"),
	//线路区域要求不合法
	couponForInterfaceRegdemand("i18n.coupon.couponInterface.regdemandIsIlegal"),
	//走货线路不能重复
	couponForInterfaceGoodslineRepeat("i18n.coupon.couponInterface.goodsLineCannotRepeat"),
	//20131204版本新增
	//业务类型为空
	marketPlanBustypeIsNull("i18n.marketplan.bustype.isNull"),
	//优惠类型不合法
	marketPlanDiscountIsIllegal("i18n.marketplan.discountType.isIllegal");
	private String errCode;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	private CouponExceptionType(String errorCode) {
		this.errCode = errorCode;
	}
}
