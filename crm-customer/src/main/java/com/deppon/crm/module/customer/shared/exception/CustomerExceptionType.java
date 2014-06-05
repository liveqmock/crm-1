package com.deppon.crm.module.customer.shared.exception;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title PotentialCustomerExceptionType.java
 * @package com.deppon.crm.module.customer.shared.exception
 * @author Administrator
 * @version 0.1 2012-3-2
 */

public enum CustomerExceptionType {
	// 日期范围太大
	DateOutOfRange("i18n.customer.DateOutofRange"),
	// 查询条件为空异常
	QueryConditionIsNull(
			"i18n.customer.queryConditionInvalid"),
	// 潜客删除条件为空
	DeletePotentialCustError(
			"i18n.customer.deleteConditionIsNull"),
	// 未查询到揽货失败潜客列表
	NoPotentialCustomerListForCargo("i18n.customer.NoPcListForCargo"),
	// 姓名为空
	NameIsNull("i18n.customer.nameIsNull"),
	// 联系方式为空
	ContactWayIsNull("i18n.customer.contactWayIsNull"),
	// 会员已经存在
	MemberExist("i18n.customer.memberExist"),
	// 散客已经存在
	ScattercuExist("i18n.customer.scattercuExist"),
	// 潜客已经存在
	PotentialExist("i18n.customer.potentialExist"),
	// 存在新联系方式会员
	ExistNewContactMember("i18n.customer.existNewContactMember"),
	// 存在新联系方式散客
	ExistNewContactScattercustomer(
			"i18n.customer.existNewContactScatterCustomer"),
	// 存在新联系方式潜客
	ExistNewContactPotentialcustomer(
			"i18n.customer.existNewContactPotentialCustomer"),
	// 揽货失败潜客生成有失败数据
	HasFailureListWhenBatchBySys(
			"i18n.customer.HasFailureListWhenBatchForCargo"),
	// 未生成到达客户数据
	NoDateForArraiveCustomerList(
			"i18n.customer.HasFailureListWhenBatchForArrive"),
	// 没有失效一年的潜客
	NoDataForLoseOneYear("i18n.customer.NoDataForLoseYear"),
	// 没有需要系统失效的潜客
	NoDataForLoseBySys("i18n.customer.NoDataForLoseBySys"),
	// 潜客为空
	PotentialCustomerIsNull("i18n.customer.potentialCustomerIsNull"), 
	// id为空
	IdIsNull("i18n.customer.idIsNull"),
	//潜客列表为空
	PotentialCustomerListNull("i18n.customer.potentialCustomerListNull"),
	//不能选择一个日期
	cannotSelectOneDate("i18n.customer.cannotSelectOneDate"), 
	//客户属性不能为空
	CustNatureIsNull("i18n.customer.CustNatureIsNull"), 
	//手机格式错误
	PhoneFormError("i18n.customer.PhoneFormError"), 
	//电话格式错误
	TelFormError("i18n.customer.TelFormError"), 
	//联系人证件类型不完整
	CardInfomationIncomplete("i18n.customer.CardInfomationIncomplete"), 
	//会员在审批中
	MemberIsExamin("i18n.customer.MemberIsExamin"), 
	//会员在审批中
	MemberIsExaminCantInvilid("i18n.customer.MemberIsExaminCantInvilid"),
	//该会员审批中，但未找到审批中的工作流号，数据异常，请联系IT服务中心
	DataError("i18n.customer.DataError"),
	//会员操作时数据已经改动
	OperateDateChange("i18n.customer.OperateDateChange"), 
	//会员联系人解绑报错
	ContactUnBoundError("i18n.customer.ContactUnBoundError"), 
	//会员作废只有归属部门才可以执行操作
	OnlyOwnDeptCanInvalid("i18n.customer.OnlyOwnDeptCanInvalid"), 
	//合同在审批中
	ContactIsExamin("i18n.customer.ContactIsExamin"), 
	//合同生效或待生效
	ContactEffect("i18n.customer.ContactEffect"),
	//联系人变更到固定客户审批中
	ConVaryIsExamin("i18n.customer.ConVaryIsExamin"), 
	//会员查询条件不能为空！
	ConditionCanNotNull("i18n.customer.ConditionCanNotNull"),
	//联系人变更到固定客户审批中，但未找到审批中的工作流号，数据异常，请联系IT服务中心
	ConVaryDataError("i18n.customer.ConVaryDataError"),
	/*2013-04-15 唐亮开始添加*/
	//潜客标签数据异常
	CustLabel_Data_Error("潜客标签数据异常，操作失败"),
	/*2013-04-15 唐亮添加完毕*/
	//客户标注方法,所传参数有误!
	CustomerLocation_Is_Null("i18n.customer.CustomerLocationIsNull"),
	//失效客户财务已完结
	CUST_FIN_OVER("i18n.customer.customerFinOver"), 
	ADDRESSLENTHIVALID("i18n.customer.addresslenthivalid"), 
	LINKMAMNAMEINVALID("i18n.customer.linkmamnameinvalid"), 
	CUSTNAMEINVALID("i18n.customer.custnameinvalid"), 
	POSTINVALID("i18n.customer.postinvalid"), 
	CUSTNEEDINVALID("i18n.customer.custneedinvalid"), 
	CUSTBASEISNULL("i18n.customer.custbaseisnull"),
	BUSINESSTYPEISNULL("i18n.customer.businessTypeisnull"),
	//渠道客户数据空异常
	ChannelCustomer_DATANULL("i18n.customer.ChanenCustomerDataNull"),
	//不止一个联系人
	MORETHANONECONTACT("i18n.customer.moreThanOneContact"), 
	//客户性质为空
	CUSTGROUPISNULL("i18n.customer.custGroupIsNull"), 
	//联系方式有对应的客户存在
	contactExitsCustomer("i18n.customer.contactExitsCustomer"), 
	potentialExistsAccounts("i18n.customer.potentialExistsAccounts"), 
	MemberInRepeats("i18n.customer.memberInRepeats"), 
	PotentialCustomerCannotChangeDept("i18n.customer.potentialCustomerCannotChangeDept"),
	PotentialCustomerCannotContactVary("i18n.customer.potentialCustomerCannotContactVary"),
	CAN_NOT_SEARCH360("i18n.custview.cannotSearch360"),
	CustCreditWithoutPermission("i18n.customer.custCredit.withoutPermission"), 
	isExtistsReapeated("i18n.customer.isExtistsReapeated"), 
	isExtistsUnCloseBussiness("i18n.customer.isExtistsUnCloseBussiness"), 
	NotSameCustGroupCannotContactVary("i18n.customer.notSameCustGroupCannotContactVary"),
	//客户状态为审批中
	custIsExamin("i18n.customer.custIsExamin"), 
	CUST_INFO_NOTMODIFY("未修改客户任何信息，不能提交！"),
	LINKMANECODE_NOTFOUND("i18n.Integral.linkmanCodeNotFound"), 
	CUSTCODE_NOTFOUND("i18n.Integral.custCodeNotFound"),
	//未找到客户
	CANNOTFINDMEMBER("i18n.customer.cannotFindMember"), 
	//存在编码为{}的{}，不允许创建，只有有效的散客才允许创建！
	NOTSCATTERNOTAllOWED("i18n.customer.notScatterNotAllowed");
	
	
	private String errCode;

	private CustomerExceptionType(String errCode) {
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
