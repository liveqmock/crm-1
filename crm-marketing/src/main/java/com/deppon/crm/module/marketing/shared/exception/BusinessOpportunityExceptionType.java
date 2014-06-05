package com.deppon.crm.module.marketing.shared.exception;

public enum BusinessOpportunityExceptionType {
	// 录入商机名称，文本格式，字符≤50，包含特殊字符以及非法字符空格等。必填项
	boNameTooLong("i18n.businessopportunity.boNameTooLong"),
	// 商机描述，文本格式，字符≤200，包含特殊字符以及非法字符空格等。必填项
	boDescTooLong("i18n.businessopportunity.boDescTooLong"),
	// 预计成功几率范围：0%~100%，预计成功几率可修改，只有商机的执行人可以修改商机的预计成功几率。默认为空，必填项
	expectSuccessOddsOverRange(
			"i18n.businessopportunity.expectSuccessOddsOverRange"),
	// 预计发货金额为空，必填项，金额必须≥10000
	expectDeliveryAmountOverRange(
			"i18n.businessopportunity.expectDeliveryAmountOverRange"),
	// 查询日期范围日期不能为空！
	queryRangeDateIsNull("i18n.businessopportunity.queryRangeDateIsNull"),
	// 查询日期范围只能在一年内！
	queryRangeMoreOneYear("i18n.businessopportunity.queryRangeMoreOneYear"),
	// 预计成功时间距离商机创建时间必须小于等于6个自然月！
	expectSuccessDateMustSixMonth(
			"i18n.businessopportunity.expectSuccessDateMustSixMonth"),
	// 已经存在该客户的商机信息！
	existedCustActiveBO("i18n.businessopportunity.existedCustActiveBO"),
	// 需求分析 客户行业信息、确认商机、是否招投标项目
	stepAnalyzeIsNull("i18n.businessopportunity.stepAnalyzeIsNull"),
	// 制定方案 客户需求简介
	stepSchemeIsNull("i18n.businessopportunity.stepSchemeIsNull"),
	// 报价/竞标 解决方案简述
	stepOfferIsNull("i18n.businessopportunity.stepOfferIsNull"),
	// 持续发货 竞争情况信息
	stepDeliverIsNull("i18n.businessopportunity.stepDeliverIsNull"),
	// 商机客户查询部门、客户编码、手机号码、固定电话与联系人姓名不能都为空！
	queryCustMustConditionIsNull(
			"i18n.businessopportunity.queryCustMustConditionIsNull"),
	// 商机客户查询条件不能都为空！
	queryCustConditionIsNull(
			"i18n.businessopportunity.queryCustConditionIsNull");

	private String errCode;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	private BusinessOpportunityExceptionType(String errorCode) {
		this.errCode = errorCode;
	}
}
