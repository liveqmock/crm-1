/**
 * @description
 * @author 赵斌
 * @2012-4-17
 * @return
 */
package com.deppon.crm.module.customer.shared.exception;

/**
 * @author 赵斌
 *
 */
public enum ContractExceptionType 
{
	//查询条件全空
	QueryConditionIsNull("i18n.contract.queryConditionInvalid"),
	//合同ID为空
	ContractIsNull("i18n.contract.contractIsNull"),
	//合同数据出现异常
	ContractDataError("i18n.contract.date_error"),
	//日期范围太大
	DateOutOfRange("i18n.contract.DateOutofRange"),
	//归属部门不是当前部门
	DeptIsError("i18n.contract.deptIsError"),
	//当前部门不能变更为归属部门
	NowDeptIsError("i18n.contract.nowDeptIsError"),
	//当前部门不能创建合同
	NotIsBusinessDept("i18n.contract.notIsBusinessDept"),
	//合同编号重复
	ContractNumIsRepeat("i18n.contract.contractNumIsRepeat"),
	//部门已经绑定
	DEPTHASHBOUNDED("i18n.contact.deptHaveBound"),
	//该部门已经解绑
	DEPTHASEDBOUNDED("i18n.contact.deptHavedBound"),
	//合同不能进行绑定
	ContractIsNotBound("i18n.contract.contractIsNotBound"),
	//此客户已成存在有效的合同信息
	ContractCustIsError("i18n.contract.contractCustIsError"),
	//工作流正在审批中，请稍后
	ContractStateIsError("i18n.contract.contractStateIsError"),
	//该状态合同不能进行此操作！
	ContractCannotOperate("i18n.contact.ContractWaitEffectCannotOperate"),
	//当前部门非归属部门，无此合同作废权限
	ContractCanNotCancell("i18n.contract.contractCanNotCancell"),
	//归属部门不允许解绑
	OwnerDeptIsError("i18n.contract.ownerDeptIsError"), 
	//申请额度出错
	ArrearaMount("i18n.contract.ArrearaMount"), 
	//验证码时间失效
	EffectTimePassed("i18n.contact.EffectTimePassed"), 
	//输入的验证码不正确
	RandomNumberNotSame("i18n.contact.RandomNumberException"),
	//电话号码不匹配
	PHONENOTMATCH("i18n.contact.phoneNotMatch"),
	//发送短信失败
	SENDMESSAGEFAIL("i18n.contact.sendMassageFail"),
	MOBILECONNOTNULL("i18n.contact.mobileIsNull"),
	DeptIsNotBondingDept("i18n.contract.deptIsNotBondingDept"),
	//合同到期日期不能为{0},只能为某月最后一天
	MONTH_END_MUST_LASTDAY("i18n.contract.month_end_must_lastday"),
	/**
	 * 绑定联系人
	 */
	// 此订单来源有误
	ORDERSOURCEERROR("i18n.contact.orderSourceError"),
	// 此联系人的官网渠道已绑定
	ONLINEHAVEBOUND("i18n.contact.onlineHaveBound"),
	// 此联系人的淘宝渠道已绑定
	TAOBAOHAVEBOUND("i18n.contact.taobaoHaveBound"),
	// 此联系人的阿里渠道已绑定
	ALIBABAHAVEBOUND("i18n.contact.alibabaHaveBound"),
	// 此联系人的友商渠道已绑定
	YOUSHANGHAVEBOUND("i18n.contact.youshangHaveBound"),
	// 此联系人的商城渠道已绑定
	SHANGCHENGHAVEBOUND("i18n.contact.shangchengHaveBound"),
	//手机号不一致无法绑定
	MOBILEPHONENOTSAME("i18n.contact.mobilePhoneNotSame"),
	//该客户存在合同序号为{0}的待生效合同，不能新增
	HAS_WAIT_EFFECT_CONTRACT("i18n.contact.haswaiteffectcontract"),	
	//该部门不是客户的归属部门，不能进行合同新签操作，若有业务往来，请变更客户归属部门或使用绑定合同功能
	Not_Member_Dept("i18n.contact.notMemberDept"), 
	//对不起，数据异常，请刷新重试，多次还不行，请联系IT服务中心
	Data_Error("i18n.contact.dataError"), 
	//该客户存在合同序号为{0}的审批中合同，不能新增
	HAS_INPROCESS_CONTRACT("i18n.contact.hasInprocessContract"),
	NEWCONTRACT_BEGINDATE_MUST_AFTER_OLDCONTRACT("i18n.contact.newcontract_begindate_must_after_oldcontract"), 
	ContractWaitEffectCannotOperate("i18n.contact.ContractWaitEffectCannotOperate"), 
	WorkflowStateIsError	("i18n.contract.workflowStatusErr"), 
	onlyCanDeleteUneffectContract("i18n.contract.onlyCanDeleteUneffectContract"), 
	DeptIsBelongDept("i18n.ContractManagerView.attributableDeptHasNowDeptConNotChange"),
	//创建时间不能早于今天
	CREATE_DATA_CANNOT_BEFORE_TODAY("i18n.contract.create_data_cannot_before_today"),
	//创建时间不能早于结束时间
	CREATE_DATA_CANNOT_BEFORE_ENDDATE("i18n.contract.create_data_cannot_before_enddate"), 
	CUSTOMERINVALIDE("i18n.contract.customerIsNotBelongCustomer"), 
	CUSTOMERHASTWOWAITEFFECTCONRACT("i18n.contract.customer_has_two_wait_effect_conract"), 
	CONTRACT_INFO_INCOMPLETE("i18n.contract.contract_info_incomplete"), 
	CUSTOMER_HAS_INPROCESS_CONTRACT("i18n.contract.customer_has_inprocess_contract"),
		//合同参数数据异常
	CONTRACT_DATA_ERROR("i18n.contract.contract_data_error"),
	//合同税务标记为空的异常  @chenaichun
	CONTRACT_CONTRACTTAX_NULL("i18n.contract.contract_contracttax_null"),
	//合同税务标记异常：合同终止时间在下个月1号前 不允许变更税务信息
	CONTRACT_CONTRACTTAX_NOCHANGE("i18n.contract.contract_contracttax_nochange"),
	//注意,此功能只能在当日20:00——次日8:00进行操作,谢谢!
	HANDLE_CONTRACTDEBTDAYS_TIMEERROR("i18n.contract.handle_contractDebtDays_timeError"),
	ContractIsNotMonthEnd("i18n.ContractManagerView.contractIsNotMonthEnd"),
	/* 2013-03-09 唐亮开始添加 */
	//每一行的最下金额必须小于等于最大金额
	MINAMOUNTOUTOFRANGE("i18n.ContractManagerView.minAmountOutOfRange"),
	//生效时间不得小于当前时间和失效时间
	BEGINTIMEOUTOFRANGE("i18n.ContractManagerView.beginTimeOutOfRange"),
	//不能新增两套生效时间一模一样的方案
	PLANEXIST("i18n.ContractManagerView.planExist"),
	//等级不允许相同
	DGREEEXIST("i18n.ContractManagerView.dgreeExist"),
	//失效优惠方案不能做此操作
	DEALSTATUSERROR("i18n.ContractManagerView.dealStatusError"),
	//非有效优惠方案不能做此操作
	NOTEFFECTDEALERROR("i18n.ContractManagerView.notEffectDealError"),
	//只有待生效记录可以激活，生效和失效的记录不可激活
	DEALCANNOTACTIVE("i18n.ContractManagerView.dealCannotActive"),
	//未查询到符合条件的优惠方案
	NODEALISFOUNDERROR("i18n.ContractManagerView.noDealIsFoundError"),
	//只能修改条目折扣！
	RATEISNOTCHANGED("i18n.ContractManagerView.rateIsNotChanged"),
	//基础资料等级不能修改
	CANNOTUPDATEDEGREE("i18n.ContractManagerView.cannotUpdateDegree"),
	//基础资料最小金额不能修改
	CANNOTUPDATEMIN("i18n.ContractManagerView.cannotUpdateMin"),
	//基础资料最大金额不能修改
	CANNOTUPDATEMAX("i18n.ContractManagerView.cannotUpdateMax"),
	//基础资料描述信息不能修改
	CANNOTUPDATEDES("i18n.ContractManagerView.cannotUpdateDes"),
	//方案之间条目金额不能交叉!
	CANNOTMIXAMOUNT("i18n.ContractManagerView.cannotMixAmount"),
	//传入数据有误，传入了空的折扣方案
	NULLDEALERROR("i18n.ContractManagerView.nullDealError"),
	//传入数据有误，传入了空的ID
	NULLIDERROR("i18n.ContractManagerView.nullIdError"),
	//优惠折扣信息错误!
	PREFRENTIALDEAL_DATA_ERROR("i18n.ContractManagerView.prefrentialDeal_Data_Error"), 
	//只能修改有效合同
	ONLYCANMODIFYEFFECTCONTRACT("i18n.ContractManagerView.onlyCanModifyEffectContract"), 
	NOTCANMODIFYMONTHSENDCONTRACT("i18n.contract.onlyCanModifyMonthSendContract1"),
	contractCallOA_deleteInterface_error("i18n.contract.contractCallOA_deleteInterface_error"),
	//该客户不满足月结客户条件，不能签订月结合同。
	CUSTOMER_NOT_ALLOW_CREATE_CONTRRACT("i18n.contract.customer_not_allow_create_contract"),
	 //新签合同的失效日期必须和原合同的结束日期相同
	CREATE_DATA_NOT_EQUALS_ENDDATE("i18n.contract.create_data_not_equals_enddate"),
	//所属子公司为空
	CONTRACTSUBJECT_IS_NULL("i18n.contract.contractSubject_is_null"),
	//不同子公司 不能绑定
	CONTRACTSUBJECTS_IS_DIFFERENTCOMPANIES("i18n.contract.contractSubject_is_DifferentCompanies"),
	CONTRACTSUBJECTS_IS_DIFFERENTCOMPANIES_FOR_CHANGEDEPT("i18n.contract.contractSubject_is_DifferentCompanies_for_changeDept"),
    //上传附件不完全
	CONTRACT_FILE_IS_INCOMPLETE("i18n.contract.file.is.incomplete"),
	//合同协议联系人为空
	CUSTLINKMAN_IS_NULL_FOR_CONTRACT("i18n.contract.custLinkMan.is_null_for_contract"),
	/* 2013-05-08 唐亮开始添加 */
	DUNINGDEPT_DATA_ERROR("修改失败，错误原因：传入的修改数据不完整或者有误"),
	NO_CHANGE_ERROR("未修改任何数据，请修改后再提交!"),
	//月结合同,其价格版本时间不能为空！
	Contract_PriceVersionDate_isNull("i18n.contract.Contract_PriceVersionDate_isNull"), 
	CANNOTONLYSIGNLTT("i18n.contract.Contract.cannotonlysignltt"),
	CANNOTFINDCONTRACT("i18n.contract.cannotfindoldcontract");
	/* 2013-05-08 唐亮添加完毕 */

	private String errCode;

	private ContractExceptionType(String errCode) {
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
 