/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisitExceptionType.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author ZhuPJ
 * @version 0.1 2012-3-26
 */
package com.deppon.crm.module.marketing.shared.exception;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title RegionPartitionExceptionType.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author ZhouYuan
 * @version 2013-04-19
 */

public enum MarketActivityExceptionType {
	//ongl表达式错误
	ognlExpressionException("i18n.marketActivity.ognlExpression.exception"),
	//校验部门性质是否为空
	deptCharacterIsEmpty("i18n.marketActivity.deptCharacter.isEmpty"),
	//活动类型不能为空！
	activityTypeIsNull("i18n.marketActivity.activityType.isEmpty"),
	//该用户不能建立活动类型为全国市场推广活动的活动
	activityTypeIsNationWide("i18n.marketActivity.activityType.isNationWide"),
	//该用户不能建立活动类型为全国市场推广活动的活动
	activityTypeIsRegion("i18n.marketActivity.activityType.isRegion"),
	//该用户不能建立活动类别为快递的市场推广活动
	activityCategoryIsExpress("i18n.marketActivity.activityCategory.isExpress"),
	//申请人工号或姓名不能为空
	activityProposerIsNull("i18n.marketActivity.activityProposer.isNull"),
	//活动开始时间不能为空！
	activityStartTimeIsNull("i18n.marketActivity.activityStartTime.isNull"),
	//活动结束时间不能为空！
	activityEndTimeIsNull("i18n.marketActivity.activityEndTime.isNull"),
	//活动开始时间不能大于活动结束时间！
	activityEndTimeLessThanStartTime("i18n.marketActivity.activityStartEndTime.Illegal"),
	//活动主题不能为空！
	activityTopicIsIllegal("i18n.marketActivity.activityTopic.isIllegal"),
	//活动内容不能为空！
	activityContentIsIllegal("i18n.marketActivity.activityContent.isIllegal"),
	//外宣传语不能为空！
	activitySloganIsIllegal("i18n.marketActivity.activitySlogan.isIllegal"),
	//申请事由不能为空！
	activityApplyReasonIsIllegal("i18n.marketActivity.activityApplyReason.isIllegal"),
	//第一个月目标不能为空,最多不能超过20个字符
	activityFirstTargetIsIllegal("i18n.marketActivity.activityFirstTarget.isIllegal"),
	//申请物品信息不能超过10条！
	activityMaterialInfoBeyondMaxSize("i18n.marketActivity.materialInfo.beyondMaxSize"),
	//对接人不能为空
	activityContactsIsNull("i18n.marketActivity.contacts.isNull"),
	//联系电话不合法
	activityContactsTelIsIllegal("i18n.marketActivity.contactsTel.isIllegal"),
	//开单金额最小值或结束值为空
	activityBillAmtIsNull("i18n.marketActivity.billAmt.isNull"),
	//开单金额最小值或结束值不合法
	activityBillAmtIsIllegal("i18n.marketActivity.billAmt.isIllegal"),
	//开单金额最大值小于最小值
	activityMaxBillAmtLessThanMinBillAmt("i18n.marketActivity.billAmt.maxLessThanMin"),
	//货物重量最小值或结束值为空
	activityCargoWeightIsNull("i18n.marketActivity.cargoWeight.isNull"),
	//货物重量最小值或结束值不合法
	activityCargoWeightIsIllegal("i18n.marketActivity.cargoWeight.isIllegal"),
	//货物重量最大值小于最小值
	activityMaxCargoWeightLessThanMinCargoWeight("i18n.marketActivity.cargoWeight.maxLessThanMin"),
	//货物体积最小值或结束值为空
	activityCargoVolumeIsNull("i18n.marketActivity.CargoVolume.isNull"),
	//货物体积最小值或结束值不合法
	activityCargoVolumeIsIllegal("i18n.marketActivity.CargoVolume.isIllegal"),
	//货物体积最大值小于最小值
	activityMaxCargoVolumeLessThanMinCargoVolume("i18n.marketActivity.cargoVolume.maxLessThanMin"),
	//开展部门不能为空！
	activityDeptsIsNull("i18n.marketActivity.activityDepts.isNull"),
	//优惠方式不能为空
	activityPreferTypeIsNull("i18n.marketActivity.preferType.isNull"),
	//优惠券数量最多添加10条！
	activityCouponInfoIsIllegal("i18n.marketActivity.couponInfo.isIllegal"),
	//请添加至少一条优惠券信息！
	activityCouponInfoIsNull("i18n.marketActivity.couponInfo.isNull"),
	//抵扣类型或优惠券数量不合法
	activityCouponTypeOrNumIsIllegal("i18n.marketActivity.couponTypeOrNum.isIllegal"),
	//使用条件不能为空！且不能超过100个字!
	activityUseRuleIsIllegal("i18n.marketActivity.useRule.isIllegal"),
	//生成条件不能为空！且不能超过100个字!
	activityCreateRuleIsIllegal("i18n.marketActivity.createRule.isIllegal"),
	//折扣比例最多添加10条！
	activityDiscountInfoIsIllegal("i18n.marketActivity.discountInfo.isIllegal"),
	//请添加至少一条折扣信息！
	activityDiscountInfoIsNull("i18n.marketActivity.discountInfo.isNull"),
	//优惠类型或折扣不合法
	activityDiscountTypeOrNumIsIllegal("i18n.marketActivity.discountTypeOrNum.isIllegal"),
	//折扣工作流号不能为空
	activityWorkFlowNumIsNull("i18n.marketActivity.workFlowNum.isNull"),
	//客户群列表不能为空！
	activityClientBaseIsNull("i18n.marketActivity.clientBase.isNull"),
	//客户群最多添加十条！
	activityClientBaseIsIllegal("i18n.marketActivity.clientBase.isIllegal"),
	//附件不能为空，请上传至少一个附件！
	activityFilesIsNull("i18n.marketActivity.files.isNull"),
	//校验市场推广活动是否保存成功
	activitySaveFail("i18n.marketActivity.activity.saveFail"),
	//出发区域填写不合法
	activitySaveLineDeptLeaveDeptIsIllegal("i18n.marketActivity.leaveDept.isIllegal"),
	//到达区域填写不合法
	activitySaveLineDeptArriveDeptIsIllegal("i18n.marketActivity.arriveDept.isIllegal"),
	//走货线路填写不合法
	activitySaveLineDeptLevOrArrDeptIsIllegal("i18n.marketActivity.LevOrArrDept.isIllegal"),
	//有客户群已被删除
	activityClientBaseIsDelete("i18n.marketActivity.clientBase.isDelete"),
	//有客户群已被使用
	activityClientBaseStatusIsIllegal("i18n.marketActivity.clientBaseStatus.isIllegal"),
	//用户不为市场推广活动创建人或该部门负责人
	activityUpdateAuthIsIllegal("i18n.marketActivity.updateAuth.isIllegal"),
	//该客户群已下发，不能删除！
	activityClientBaseStatusCanNotDelete("i18n.marketActivity.clientBase.canNotDelete"),
	//新增的客户群或已被删除
	activityClientBaseAlreadyDeleted("i18n.marketActivity.clientBase.alreadyDeleted"),
	//新增的客户群ID为空
	activityClientBaseIdIsNull("i18n.marketActivity.clientBaseId.isNull"),
	//客户群添加或删除
	activityClientBaseAddOrDelFail("i18n.marketActivity.clientBase.addOrDelFail"),
	//区域市场推广活动不能被修改
	updateActivityMarketActivityIsRegion("i18n.marketActivity.updateActivity.isRegion"),
	//市场推广活动状态不为已制定\折扣已生效\下发完成
	updateActivityMarketActivityStatusIsIllegal("i18n.marketActivity.updateActivityStatus.isIllegal"),
	//下发客户群,该客户群状态不合法
	createClientBasePlanClientStatusIsIllegal("i18n.marketActivity.createBasePlannClientStatus.isIllegal"),
	//参与部门的市场推广活动ID不能为空
	activityDeptsActivityIdIsNull("i18n.marketActivity.ActivityDeptActivityId.isNull"),
	//参与部门的部门标杆编码不能为空
	activityDeptsCodeIsNull("i18n.marketActivity.ActivityDeptCode.isNull"),
	//该价格折扣工作流尚未审批通过！
	discountWorkflowUnavailable("i18n.marketActivity.discountWorkflow.Unavailable"),
	//该工作流已被其他市场推广活动使用，不可重复！
	discountWorkflowRepeat("i18n.marketActivity.discountWorkflow.Repeat"),
	//OA无对应的折扣工作流，请核对
	discountWorkflowIsNone("i18n.marketActivity.discountWorkflow.None"),
	//回访计划开始时间或结束时间为空
	activityClientBasePlanTimeIsNull("i18n.marketActivity.clientBasePlanTime.isNull"),
	//起始时间不能大于结束时间！
	activityClientBasePlanTimeIsIllegal("i18n.marketActivity.clientBasePlanTime.isIllegal"),
	//活动名称已存在！
	haveSamNameMarketActivity("i18n.marketActivity.activityName.repeat"),
	//市场推广活动为空
	marketActivityIsNull("i18n.marketActivity.activity.isNull"),
	//客户数量为0，不允许下发！
	marketActivityClientNumIsZero("i18n.marketActivity.clientNum.isZero"),
	//客户数量尚未计算成功，不允许下发！
	marketActivityClientNumIsNull("i18n.marketActivity.clientNum.isNull"),
	//线路区域不能超过100条!
	activitySaveLineDeptLeaveDeptBeyondMaxSize("i18n.marketActivity.deliveryDept.beyondMax");
	private String errCode;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	private MarketActivityExceptionType(String errorCode) {
		this.errCode = errorCode;
	}

}
