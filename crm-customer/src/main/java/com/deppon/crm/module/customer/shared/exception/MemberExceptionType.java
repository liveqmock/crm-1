package com.deppon.crm.module.customer.shared.exception;


public enum MemberExceptionType {
	DegreeNull("i18n.member.DegreeNull"),
	CustNameNull("i18n.member.CustNameNull"), 
	CustTypeNull("i18n.member.CustTypeNull"), 
	CompanyPropertyNull("i18n.member.CompanyPropertyNull"), 
	CustNatureNull("i18n.member.CustNatureNull"), 
	TradeIdNull("i18n.member.TradeIdNull"), 
	ProvinceNull("i18n.member.ProvinceNull"), 
	CityNull("i18n.member.CityNull"), 
	IsSpecialNull("i18n.member.IsSpecialNull"), 
	IsImportCustorNull("i18n.member.IsImportCustorNull"), 
	IsRedeempointsNull("i18n.member.IsRedeempointsNull"), 
	IsParentCompanyNull("i18n.member.IsParentCompanyNull"), 
	IsFocusPayNull("i18n.member.IsFocusPayNull"), 
	ProcReditNull("i18n.member.IsProcReditNull"), 
	EnterpriseMember_TaxregNumberNull("i18n.member.EnterpriseMember_TaxregNumberNull"), 
	IsAcceptMarketNull("i18n.member.IsAcceptMarketNull"), 
	ChannelSourceNull("i18n.member.ChannelSourceNull"), 
	ChannelNull("i18n.member.ChannelNull"), 
	PreferenceServiceNull("i18n.member.PreferenceServiceNull"),
	CompanyScopNull("i18n.member.CompanyScopNull"), 
	AreaNull("i18n.member.AreaNull"), 
	AddressTypeNull("i18n.member.AddressTypeNull"),
	AddressNull("i18n.member.AddressNull"), 
	ContactMustExistOne("i18n.member.ContactMustExistOne"), 
	MainContactCanExistOnlyOne("i18n.member.MainContactCanExistOnlyOne"), 
	ContactsHasRepeat("i18n.member.ContactsHasRepeat"), 
	BillRequestNull("i18n.member.BillRequestNull"), 
	HasStopCost("i18n.member.HasStopCost"), 
	PayType("i18n.member.PayType"), 
	IsSendToFloor("i18n.member.IsSendToFloor"),
	NumberNull("i18n.member.NumberNull"), 
	IdCardNull("i18n.member.IdCardNull"), 
	NameNull("i18n.member.NameNull"), 
	LinkmanTypeNull("i18n.member.LinkmanTypeNull"), 
	IsMainLinkManNull("i18n.member.IsMainLinkManNull"), 
	SexNull("i18n.member.SexNull"), 
	DutyNull("i18n.member.DutyNull"), 
	GainaveNull("i18n.member.GainaveNull"), 
	ContactWayNull("i18n.member.ContactWayNull"), 
	BankNull("i18n.member.BankNull"), 
	SubBanknameNull("i18n.member.SubBanknameNull"), 
	IsdefaultaccountNull("i18n.member.IsdefaultaccountNull"), 
	BankAccountNull("i18n.member.BankAccountNull"), 
	CountNameNull("i18n.member.CountNameNull"), 
	RelationNull("i18n.member.RelationNull"), 
	BankProvinceIdNull("i18n.member.BankProvinceIdNull"), 
	BankCityIdNull("i18n.member.BankCityIdNull"), 
	AccountNatureNull("i18n.member.AccountNatureNull"), 
	AccountUseNull("i18n.member.AccountUseNull"), 
	FinanceLinkmanNull("i18n.member.FinanceLinkmanNull"), 
	EnterpriseMember_Exist("i18n.member.EnterpriseMember_Exist"),
	ContactExist("i18n.member.ContactExist"), 
	StatisticsTimeNull("i18n.member.StatisticsTimeNull"),
	DeptNull("i18n.member.DeptNull"), 
	StatisticsTimeFormError("i18n.member.StatisticsTimeFormError"),
	TimeNotRight("i18n.member.TimeNotRight"),
	DataDictionaryError("i18n.member.DataDictionaryError"),
	ApproveDataHandTypeError("il8n.member.ApproveDataHandTypeError"),
	JavaReflexError("il8n.member.JavaReflexError"),
	HandlePojoError("il8n.member.HandlePojoError"),
	NotSupportDataTypeError("il8n.member.NotSupportDataTypeError"),
	DateFormartError("il8n.member.DateFormartError"),
	NoAuthority("i18n.member.NoAuthority"),
	TargetLevelNotExist("i18n.member.TargetLevelNotExist"),
	MonthlyStatementOver("i18n.member.MonthlyStatementOver"), 
	NoAccountFind("i18n.member.NoAccountFind"), 
	NoShuttleAddressFind("i18n.member.NoShuttleAddressFind"), 
	NoMainContactFind("i18n.member.NoMainContactFind"), 
	ParentCompanyNull("i18n.member.ParentCompanyNull"), 
	FocusDeptNull("i18n.member.FocusDeptNull"), 
	IsMainAddressNull("i18n.member.IsMainAddressNull"), 
	//不能修改其他部门的数据
	CANNOTMODIFYOTHERSDATA("i18n.customer.cannotModifyOthersData"),
	ContactTypeError("i18n.member.ContactTypeError"),//"联系人类型错误"
	//"联系人没找到"
	ContactNotFindError("i18n.member.ContactNotFindError"), 
	//企业税务登记号不合法
	EnterpriseMember_TaxregNumberError("i18n.member.TaxregNumberError"),
	//香港登记证号不合法!
	HongKong_TaxregNumber_Error("i18n.member.HongKong_TaxregNumber_Error"),
	//税务登记号对应的用户编码已存在
	TaxregnumerMemberExist("i18n.member.saveError"), 
	RegistrationMemberExist("i18n.member.saveRegistrationError"),
	//税务登记号正在审批中
	TaxregnumerMemberExamin("i18n.member.TaxregnumerMemberExamin"), 
	RegistrationNumerMemberExamin("i18n.member.RegistrationNumerMemberExamin"),
	//联系人电话和姓名在审批中
	TelAndNameMemberExamin("i18n.member.TelAndNameMemberExamin"), 
	//联系人电话和姓名对应的用户编码已存在
	TelAndNameMemberExist("i18n.member.TelAndNameMemberExist"), 
	//联系人手机在审批中
	PhoneMemberExamin("i18n.member.PhoneMemberExamin"), 
	//联系人手机对应的用户编码已存在
	PhoneMemberExist("i18n.member.PhoneMemberExist"), 
	//有效证件与对应的用户编码已存在
	CardMemberExist("i18n.member.CardMemberExist"), 
	//有效证件审批中
	CardMemberExamin("i18n.member.CardMemberExamin"), 
	//联系人编码与对应的用户编码已存在
	LinkManMuberMemberExist("i18n.member.LinkManMuberMemberExist"), 
	//联系人编码有效证件审批中
	LinkManMuberMemberExamin("i18n.member.LinkManMuberMemberExamin"),
	/*2013-04-10 唐亮开始新增*/
	//传入的联系人ID或者联系人地址为空，不能修改
	Data_Miss_Error("i18n.member.custLabelDataMissError"),
	//传入了空的客户id
	CustId_Miss_Error("i18n.member.custLabelCustIdMissError"),
	//传入的客户类型为空
	CustType_Miss_Error("i18n.member.custLabelCustTypeMissError"),
	//标签内容为空
	Label_Miss_Error("i18n.member.custLabelLabelMissError"),
	//客户标签内容为空
	LabelId_Miss_Error("i18n.member.custLabelLabelIdMissError"),
	//部门ID为空
	LabelDeptId_Miss_Error("i18n.member.custLabelLabelDeptIdMissError"),
	//客户Label标签的具体展示内容为空
	LabelName_Miss_Error("i18n.member.custLabelLabelNameMissError"),
	//传入的标签数量超过10个，不让保存
	Label_OutOf_Range_Error("i18n.member.custLabelLabelOutOfRangeError"),
	//部门标签已经满10个
	DeptLabel_Full_Error("i18n.member.custLabelDeptLabelFullError"),
	//客户标签名重复
	LabelName_Exist_Error("i18n.member.custLabelNameExistError"),
	//在数据库未查找到地址信息
	No_Address_Is_Found("i18n.member.No_Address_Is_Found"),
	//传入的数据不完整，操作失败
	Cust_Data_Error("i18n.member.Cust_Data_Error"),
	//客户标签基础资料有变化，请重新打开页面
	Label_source_Error("i18n.member.LabelsourceError"),
	//客户标签基础资料标签ID为空
	Label_Id_Error("i18n.member.LabelIdIsNullError"), 
	
	/**
	 *  二期新增
	 */
	//客户类别（潜散客固定客户）为空
	CustGroupNull("i18n.member.CustGroupNull"), 
	IsKeyCustorNull("i18n.member.IsKeyCustorNull"),
	PotentialChannelSourceNull("i18n.member.PotentialChannelSourceNull"),
	BussTypeNull("i18n.member.BussTypeNull"),
	NULLTRADE("i18n.member.nulltrade"), 
	taxregNumberNull("i18n.member.taxregNumberNull"), 
	createPotentialMemberFail("i18n.member.createPotentialMemberFail"),
	COPYERROR("对象复制失败"), 
	RegistAddressNull("i18n.member.RegistAddressNull"), 
	ContactTelePhoneError("i18n.MemberCustEditView.pleaseInputPhone"), 
	ContactMobileError("i18n.MemberCustEditView.pleaseInputCorrectMobilePhone"),
	DEVELOPDATANULL("i18n.MemberCustEditView.developDataNull"), 
	createScatterFail("i18n.member.createScatterFail"), 
	MemberisExaming("i18n.member.MemberisExaming"), 
	CUSTNAMETOLONG("i18n.member.custnametoolong"), 
	CONTACTNAMETOLONG("i18n.member.contactnametoolong"),
	ADDRESSTOLONG("i18n.member.addresstolong"), 
	createMemberFail("i18n.member.createMemberFail"), 
	FOSSIDNULL("i18n.member.FOSSIDNULL");
	
	private String errCode;

	private MemberExceptionType(String errCode) {
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
