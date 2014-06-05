/**
 * 使用说明：ModifyMemberControl 
 * 数据字典之后加这段代码 new ModifyMemberControl().getModifyMember();
 * 'KEY':类似ID的字段：不用显示
 * 'ISTXT':文本，直接显示
 * 'ISDATE':时间，需要格式转换
 * 'LIST':集合
 * 'OBJ':类
 */
 
var ModifyMember = null;

Ext.define('ModifyMemberControl', {
	getModifyMember : function() {
		if (Ext.isEmpty(ModifyMember)) {
			ModifyMember = (function() {
				var judgeStatus = {
					'isOrNot' : [{
								'code' : '1',
								'codeDesc' : i18n('i18n.ChangeContactAffiliatedRelationView.yes')
							}, {
								'code' : '2',
								'codeDesc' : i18n('i18n.ChangeContactAffiliatedRelationView.no')
							}],
					'examStatus' : [{
								'code' : '0',
								'codeDesc' : i18n('i18n.ModifyMemberView.m_normal')
							}, {
								'code' : '1',
								'codeDesc' : i18n('i18n.ContractEditView.approvaling')
							}, {
								'code' : '2',
								'codeDesc' : i18n('i18n.ContractManagerView.expirationD')
							}],
					'trueOrFalse' : [{
								'code' : 'true',
								'codeDesc' : i18n('i18n.ChangeContactAffiliatedRelationView.yes')
							}, {
								'code' : 'false',
								'codeDesc' : i18n('i18n.ChangeContactAffiliatedRelationView.no')
							}]
				};
				var member = {
					//创建时间
					'createDate' : 'ISDATE',
					//修改时间
					'modifyDate' : 'ISDATE',
					//创建人
					'createUser' : '',
					//修改人
					'modifyUser' : '',
					// 客户编码
					'custNumber' : i18n('i18n.MemberCustEditView.custNo'),
					// 客户等级
					'degree' : i18n('i18n.MemberCustEditView.custLevel'),
					// 客户姓名
					'custName' : i18n('i18n.ModifyMemberView.nameOfClient'),
					// 所属行业
					'tradeId' : i18n('i18n.ModifyMemberView.belongIndustry'),
					// 所属行业
					'secondTrade' : i18n('i18n.secondTrade.secondTrade'),
					//客户类别
					'custGroup':i18n('i18n.ScatterCustManagerView.custProperty'),
					// 客户类型
					'custType' : i18n('i18n.MemberCustEditView.custType'),
					// 税务登记号 证件类型
					'cardTypeCust' : 'KEY',
					// 税务登记号
					'taxregNumber' : i18n('i18n.ScatterCustManagerView.taxId'),
					// 公司性质
					'companyProperty' : i18n('i18n.ScatterCustManagerView.companyProperties'),
					// 客户属性
					'custNature' : i18n('i18n.ScatterCustManagerView.custAttribute'),
					// 所在省份
					'province' : i18n('i18n.MemberCustEditView.belongProvince'),
					//客户业务类别
					'custCategory':i18n('i18n.member.custCategory'),
					// 所在省份ID
//					'provinceId' : i18n('i18n.ModifyMemberView.belongProvinceID'),
					'provinceId' : 'KEY',
					// 所在城市
					'city' : i18n('i18n.MemberCustEditView.belongCity'),
					// 所在城市ID
//					'cityId' : i18n('i18n.ModifyMemberView.belongCityID'),
					'cityId' : 'KEY',
					// 客户注册地址
					'registAddress' : i18n('i18n.MemberCustEditView.registAddress'),
					// 是否特殊客户 1为特殊客户，2为一般客户
					'isSpecial' : i18n('i18n.MemberCustEditView.isSpecial'),
					// 仅限主联系人兑换积分
					'isRedeempoints' : i18n('i18n.Integral.isOnlyMainExchage'),
					// 是否部门重要客户1为是，2为否
					'isImportCustor' : i18n('i18n.ModifyMemberView.whetherImportantSectorCustomers'),
					// 客户潜力类型
					'custPotentialType' : i18n('i18n.MemberCustEditView.custPotentialType'),
					// 是否接受德邦营销信息
					'isAcceptMarket' : i18n('i18n.ModifyMemberView.isAcceptNaduMarketingInformation'),
					// 品牌价值
					'brandWorth' : i18n('i18n.MemberCustEditView.brandWorth'),
					// 来源渠道
					'channelSource' : i18n('i18n.MemberCustEditView.channelSource'),
					// 偏好渠道
					'channel' : i18n('i18n.MemberCustEditView.channel'),
					// 偏好服务
					'preferenceService' : i18n('i18n.MemberCustEditView.preferenceService'),
					// 上一年公司规模
					'companyScop' : i18n('i18n.ModifyMemberView.previousYarCompanySize'),
					// 上一年公司利润
					'lastYearProfit' : i18n('i18n.ModifyMemberView.thePreviousCorporateProfits'),
					// 上一年财务公司收入
					'lastYearIncome' : i18n('i18n.ModifyMemberView.thePreviousYearTinancialCompanyRevenue'),
					// 是否集中结算1为是，2为否
					'isFocusPay' : i18n('i18n.MemberCustEditView.isFocusPay'),
					// 集中结算部门
//					'focusDeptId' : i18n('i18n.MemberCustEditView.focusDeptId'),
					'focusDeptId' : 'KEY',
					// 集中结算部门名称
					'focusDeptName' : i18n('i18n.ModifyMemberView.centralizedSettlementDepartmentName'),
					// 发票抬头
					'billTitle' : i18n('i18n.MemberCustEditView.billTitle'),
					// 是否母公司1为是，2为否
					'isParentCompany' : i18n('i18n.MemberCustEditView.isParentCompany'),
					// 客户所属母公司名称
					'parentNumber' : i18n('i18n.ModifyMemberView.customer-ownedParentCompanyName'),
					// 客户所属母公司ID
//					'parentCompanyId' : i18n('i18n.ModifyMemberView.customer-ownedParentCompanyID'),
					'parentCompanyId' : 'KEY',
					// 注册资金
					'registerFund' : i18n('i18n.MemberCustEditView.registerFund'),
					// 临欠额度
					'procRedit' : i18n('i18n.MemberCustEditView.procRedit'),
					// 推荐人
					'recommendCust' : i18n('i18n.MemberCustEditView.recommendCust'),
					// 备注
					'remark' : i18n('i18n.ScatterCustManagerView.remark'),
					// 主联系人
					'mainContact' : 'OBJ',
					// 联系人集合
					'contactList' : 'LIST',
					// 接送货地址接货
					'shuttleAddressList' : 'LIST',
					// 银行账号
					'accountList' : 'LIST',
					// 接送货主地址
					'jshAddress' : i18n('i18n.ModifyMemberView.shuttleShippersAddress'),
					// 客户简称
					'simpleName' : i18n('i18n.ModifyMemberView.customersReferredTo'),
					// 客户业务类型
					'bussType' : i18n('i18n.ModifyMemberView.customerTypeOfBusiness'),
					// 是否异地调货 1为是，2为否
					'isTranGoods' : i18n('i18n.ModifyMemberView.whetherTheSiteTransferGoods'),
					// 所属区域
//					'areaId' : i18n('i18n.ModifyMemberView.belongArea'),
					'areaId' : 'KEY',
					// 所属部门
//					'deptId' : i18n('i18n.ScatterUpgradeView.belongdeptName'),
					'deptId' : 'KEY',
					// 客户ID
//					'custId' : i18n('i18n.ModifyMemberView.memberID'),
					'custId':'KEY',
					// 主要联系人ID
//					'contactId' : i18n('i18n.ModifyMemberView.primaryContactID'),
					'contactId' :'KEY',
					// 升级来源
					'upgradeSource' : i18n('i18n.ModifyMemberView.upgradeSource'),
					// 客户状态 正常：0； 审批中：1 ；无效 ：2；
					'custStatus' : i18n('i18n.ModifyMemberView.customerStatus'),
					// 成为会员时间
					'becomeMemTime' : i18n('i18n.ModifyMemberView.becomeMemberOfTime'),
					// 责任人(维护人)ID
					'responsibillity' : i18n('i18n.ModifyMemberView.responsiblePersonID'),
					// 版本号
					'versionNumber' : i18n('i18n.ModifyMemberView.versionNumber'),
					// 客户上一等级
					'nextLevel' : i18n('i18n.ModifyMemberView.ratingOnCustomer'),
					// 上次升降级时间
					'lastChangTime' : i18n('i18n.ModifyMemberView.lastRelegationTime'),
					// 可使用月结额度
					'surplusMonthlyStatementMoney' : i18n('i18n.MemberCustEditView.canUseAmountOfMonthlyStatement'),
					/**
					 * 会员联系人
					 */
					// 联系人编码
					'number' : i18n('i18n.MemberCustEditView.contactNo'),
					// 联系人姓名
					'name' : i18n('i18n.PotentialCustManagerView.contactName'),
					// 联系人 证件类型
					'cardTypeCon' : '证件类型',
					// 身份证号
					'idCard' : i18n('i18n.ScatterCustManagerView.idNumber'),
					// 联系人类型
					'linkmanType' : i18n('i18n.MemberCustEditView.contactType'),
					// 是否主联系人1为是，0为否
					'isMainLinkMan' : i18n('i18n.MemberCustEditView.isMainContact'),
					// 性别
					'sex' : i18n('i18n.MemberCustEditView.gender'),
					// 固定电话
					'telPhone' : i18n('i18n.MemberCustEditView.telephoneNo'),
					// 手机号码
					'mobilePhone' : i18n('i18n.MemberCustEditView.mobileNo'),
					// 职务
					'duty' : i18n('i18n.MemberCustEditView.post'),
					// 任职部门
					'dutyDept' : i18n('i18n.MemberCustEditView.officeholdingDept'),
					// 出生日期
					'bornDate' : i18n('i18n.MemberCustEditView.birthDate'),
					// 获知公司途径
					'gainave' : i18n('i18n.MemberCustEditView.companyWay'),
					// 物流决定权
					'decisionRight' : i18n('i18n.MemberCustEditView.logisticsDecision'),
					// 籍贯
					'nativePlace' : i18n('i18n.MemberCustEditView.nativeAddress'),
					// 个人爱好
					'personLove' : i18n('i18n.MemberCustEditView.hobby'),
					// 民族
					'folk' : i18n('i18n.MemberCustEditView.nation'),
					// Email地址
					'email' : i18n('i18n.ModifyMemberView.EmailAddress'),
					// QQ号码
					'qqNumber' : i18n('i18n.ModifyMemberView.QQnum'),
					// MSN
					'msn' : 'MSN',
					// 旺旺号
					'ww' : i18n('i18n.ModifyMemberView.WWnum'),
					// 阿里ID
					'alid' : i18n('i18n.MemberCustEditView.aLiId'),
					// 网营ID
					'onlineBusinessId' : i18n('i18n.MemberCustEditView.netsCampId'),
					// 淘宝ID
					'taobId' : i18n('i18n.MemberCustEditView.taoBaoId'),
					// 金蝶友商ID
					'youshangId' : i18n('i18n.MemberCustEditView.kingDeeId'),

					// 账号信息ID
//					'accountId' : i18n('i18n.ModifyMemberView.accountInformationID'),
					'accountId' : 'KEY',
					// 受理部门ID
//					'acceptDeptid' : i18n('i18n.ModifyMemberView.theDepartmentHandlingID'),
					'acceptDeptid' : 'KEY',
					// 状态 正常：0； 审批中：1 ；失效 2；
					'status' : 'ISSTATUS',
					// 默认账户ID
					'defaultId' : i18n('i18n.ModifyMemberView.defaultsAccountID'),
					// 版本号ID
					'versionId' : i18n('i18n.ModifyMemberView.versionID'),
					// 客户ID
//					'custId' : i18n('i18n.ModifyMemberView.memberID'),
					'custId':'KEY',
					// 会员信息
//					'member' : i18n('i18n.IntegralRuleEdit.custInfo'),
					'member' : 'OBJ',
					// 偏好地址
//					'preferenceAddressList' : i18n('i18n.ModifyMemberView.preferenceAddress'),
					'preferenceAddressList' : 'LIST',
					/**
					 * 接送货地址
					 */
					// 地址类型
					'addressType' : i18n('i18n.MemberCustEditView.addressType'),
					// 详细地址
					'address' : i18n('i18n.ModifyMemberView.infoAddress'),
					// 省份ID
//					'province' : i18n('i18n.ModifyMemberView.proviceID'),
					'province' : 'KEY',
					// 省份名称
					'provinceName' : i18n('i18n.ModifyMemberView.proviceName'),
					// 城市ID
//					'city' : i18n('i18n.ModifyMemberView.cityID'),
					'city' : 'KEY',
					// 城市名称
					'cityName' : i18n('i18n.ModifyMemberView.cityName'),
					// 区县
//					'area' : i18n('i18n.ModifyMemberView.smallArea'),
					'area' : 'KEY',
					// 区县名称
					'areaName' : i18n('i18n.ModifyMemberView.smallAreaName'),
					// 邮编
					'postcode' : i18n('i18n.ModifyMemberView.zipCode'),
					// 会员id
//					'memberId' : i18n('i18n.ModifyMemberView.memberid'),
					'memberId' :'KEY',
					// 状态 正常：0； 审批中：1 ；无效 ：2；
					'status' : 'ISSTATUS',
					/**
					 * 会员账户
					 */
					// 开户行
					'bank' : i18n('i18n.MemberCustEditView.openBank'),
					// 开户行ID
//					'bankId' : i18n('i18n.ModifyMemberView.bankID'),
					'bankId' : 'KEY',
					// 支行名称
					'subBankname' : i18n('i18n.MemberCustEditView.branchBankName'),
					// 支行名称Id
//					'subBanknameId' : i18n('i18n.ModifyMemberView.branchNameId'),
					'subBanknameId' : 'KEY',
					// 是否默认账户 1为是，0为否
					'isdefaultaccount' : i18n('i18n.ModifyMemberView.isDefaultsAccount'),
					// 银行账号
					'bankAccount' : i18n('i18n.ModifyMemberView.bankAccount'),
					// 开户姓名
					'countName' : i18n('i18n.MemberCustEditView.openName'),
					// 账号与客户关系
					'relation' : i18n('i18n.ModifyMemberView.accountAndCustomerRelationship'),
					// 开户省份
					'bankProvinceId' : i18n('i18n.ModifyMemberView.accountProvince'),
					'bankProvinceId' : 'KEY',
					// 开户省份名称
					'bankProvinceName' : i18n('i18n.ModifyMemberView.accountProvinceName'),
					// 账户性质
					'accountNature' : i18n('i18n.MemberCustEditView.accountNature'),
					// 账户用途
					'accountUse' : i18n('i18n.MemberCustEditView.accountPurpose'),
					// 开户城市
//					'bankCityId' : i18n('i18n.ModifyMemberView.accountTheCity'),
					'bankCityId' : 'KEY',
					// 开户城市名称
					'bankCityName' : i18n('i18n.ModifyMemberView.accountTheCityName'),
					// 财务联系人姓名
					'financeLinkman' : i18n('i18n.MemberCustEditView.accountContactName'),
					// 财务联系人ID
//					'financeLinkmanId' : i18n('i18n.ModifyMemberView.financialContactID'),
					'financeLinkmanId' : 'KEY',
					// 联系手机
					'linkManMobile' : i18n('i18n.MemberCustEditView.contactPhone'),
					// 联系人固话
					'linkManPhone' : i18n('i18n.ModifyMemberView.contactPhone'),
					// 联系方式
					'contactType' : i18n('i18n.MemberCustEditView.linkWay'),
					// 开户区县
					'bankArea' : i18n('i18n.ModifyMemberView.accountCounties'),
					// 最后更新部门ID
//					'lastUpdateDeptId' : i18n('i18n.ModifyMemberView.lastModifyDeptID'),
					'lastUpdateDeptId' : 'KEY',
					// 创建部门ID
//					'createDeptId' : i18n('i18n.ModifyMemberView.createDepartmentID'),
					'createDeptId' : 'KEY',
					// 所属客户
					'belongcustom' : 'KEY',
					// 状态 正常：0； 审批中：1 ；无效 ：2；
					'status' : i18n('i18n.ModifyMemberView.statusNormal'),
					/**
					 * 偏好地址
					 */
					// 地址类型
					'addressType' : i18n('i18n.MemberCustEditView.addressType'),
					// 地址
					'address' : i18n('i18n.PotentialCustEditView.address'),
					// 偏好起始时间
					'startTime' : i18n('i18n.ModifyMemberView.preferenceForTheStartTime'),
					// 偏好截止时间
					'endTime' : i18n('i18n.ModifyMemberView.preferenceForTheCut-offTime'),
					// 发票要求
					'billRequest' : i18n('i18n.MemberCustEditView.invoiceRequirements'),
					// 停车费用
					'hasStopCost' : i18n('i18n.ModifyMemberView.parkingFees'),
					// 付款方式
					'payType' : i18n('i18n.MemberCustEditView.payWay'),
					// 是否送货上楼 1为是，0为否
					'isSendToFloor' : i18n('i18n.ModifyMemberView.whetherDeliveryUpstairs'),
					// 其它要求
					'otherNeed' : i18n('i18n.ModifyMemberView.otherRequirements'),
					// 是否主地址
					'isMainAddress' : i18n('i18n.MemberCustEditView.ifThePrimaryAddress'),
					// 状态 正常：0； 审批中：1 ；无效 ：2；
					'status' : 'ISSTATUS',
					// 接送货地址ID
//					'shuttleAddressId' : i18n('i18n.ModifyMemberView.accessDeliveryAddressID'),
					'shuttleAddressId' :'KEY',
					// 关联的联系人ID
//					'linkManId' : i18n('i18n.ModifyMemberView.associatedContactsID')
					'linkManId' :'KEY'
				};
				var memberModify = { // 客户编码
					'custNumber' : '',
					// 客户等级
					'degree' : DataDictionary.MEMBER_GRADE,
					// 客户姓名
					'custName' : '',
					// 所属行业
					'tradeId' : DataDictionary.TRADE,
					// 二级行业
					'secondTrade' : DataDictionary.SECOND_TRADE,
					// 客户类型
					'custType' : DataDictionary.CUSTOMER_TYPE,
					// 税务登记号 证件类型
					'cardTypeCust' : DataDictionary.CARDTYPECUST,
					//客户类别
					'custCategory':DataDictionary.CUST_CATEGORY,
					// 税务登记号
					'taxregNumber' : '',
					// 公司性质
					'companyProperty' : DataDictionary.COMP_NATURE,
					//客户性质
					'custGroup':DataDictionary.CUST_TYPE,
					// 客户属性
					'custNature' : DataDictionary.CUSTOMER_NATURE,
					// 所在省份
					'province' : '',
					// 所在省份ID
					'provinceId' : '',
					// 所在城市
					'city' : '',
					// 所在城市ID
					'cityId' : '',
					// 客户注册地址
					'registAddress' : '',
					// 是否特殊客户 1为特殊客户，2为一般客户
					'isSpecial' : judgeStatus.trueOrFalse,
					// 仅限主联系人兑换积分
					'isRedeempoints' : judgeStatus.trueOrFalse,
					// 是否部门重要客户1为是，2为否
					'isImportCustor' : judgeStatus.isOrNot,
					// 客户潜力类型
					'custPotentialType' : DataDictionary.CUST_POTENTIAL,
					// 是否接受德邦营销信息
					'isAcceptMarket' : DataDictionary.MARKETINFO,
					// 品牌价值
					'brandWorth' : '',
					// 来源渠道
					'channelSource' : DataDictionary.PREFERENCE_CHANNEL,
					// 偏好渠道
					'channel' : DataDictionary.PREFERENCE_CHANNEL,
					// 偏好服务
					'preferenceService' : DataDictionary.PREFERENCE_SERVICE,
					// 上一年公司规模
					'companyScop' : DataDictionary.FIRM_SIZE,
					// 上一年公司利润
					'lastYearProfit' : '',
					// 上一年财务公司收入
					'lastYearIncome' : '',
					// 是否集中结算1为是，2为否
					'isFocusPay' : judgeStatus.isOrNot,
					// 集中结算部门
					'focusDeptId' : judgeStatus.isOrNot,
					// 集中结算部门名称
					'focusDeptName' : '',
					// 发票抬头
					'billTitle' : '',
					// 是否母公司1为是，2为否
					'isParentCompany' : judgeStatus.isOrNot,
					// 客户所属母公司名称
					'parentNumber' : '',
					// 客户所属母公司ID
					'parentCompanyId' : '',
					// 注册资金
					'registerFund' : '',
					// 临欠额度
					'procRedit' : '',
					// 推荐人
					'recommendCust' : '',
					// 备注
					'remark' : '',
					// 主联系人
					'mainContact' : '',
					// 联系人集合
					'contactList' : '',
					// 接送货地址接货
					'shuttleAddressList' : '',
					// 银行账号
					'accountList' : '',
					// 接送货主地址
					'jshAddress' : '',
					// 客户简称
					'simpleName' : '',
					// 客户业务类型
					'bussType' : '',
					// 是否异地调货 1为是，2为否
					'isTranGoods' : judgeStatus.isOrNot,
					// 所属区域
					'areaId' : '',
					// 所属部门
					'deptId' : '',
					// 客户ID
					'custId' : '',
					// 主要联系人ID
					'contactId' : '',
					// 升级来源
					'upgradeSource' : '',
					// 客户状态 正常：0； 审批中：1 ；无效 ：2；
					'custStatus' : judgeStatus.examStatus,
					// 成为会员时间
					'becomeMemTime' : '',
					// 责任人(维护人)ID
					'responsibillity' : '',
					// 版本号
					'versionNumber' : '',
					// 客户上一等级
					'nextLevel' : DataDictionary.MEMBER_GRADE,
					// 上次升降级时间
					'lastChangTime' : '',
					// 可使用月结额度
					'surplusMonthlyStatementMoney' : '',
					/**
					 * 会员联系人
					 */
					// 联系人编码
					'number' : '',
					// 联系人姓名
					'name' : '',
					// 联系人 证件类型
					'cardTypeCon' : DataDictionary.CARDTYPECON,
					// 身份证号
					'idCard' : '',
					// 联系人类型
					'linkmanType' : DataDictionary.LINKMANTYPE,
					// 是否主联系人1为是，0为否
					'isMainLinkMan' : judgeStatus.trueOrFalse,
					// 性别
					'sex' : DataDictionary.GENDER,
					// 固定电话
					'telPhone' : '',
					// 手机号码
					'mobilePhone' : '',
					// 职务
					'duty' : '',
					// 任职部门
					'dutyDept' : '',
					// 出生日期
					'bornDate' : 'ISDATE',
					// 获知公司途径
					'gainave' : '',
					// 物流决定权
					'decisionRight' : DataDictionary.LOGIST_DECI,
					// 籍贯
					'nativePlace' : '',
					// 个人爱好
					'personLove' : '',
					// 民族
					'folk' : '',
					// Email地址
					'email' : '',
					// QQ号码
					'qqNumber' : '',
					// MSN
					'msn' : '',
					// 旺旺号
					'ww' : '',
					// 阿里ID
					'alid' : '',
					// 网营ID
					'onlineBusinessId' : '',
					// 淘宝ID
					'taobId' : '',
					// 金蝶友商ID
					'youshangId' : '',

					// 账号信息ID
					'accountId' : '',
					// 受理部门ID
					'acceptDeptid' : '',
					// 状态 正常：0； 审批中：1 ；失效 2；
					'status' : judgeStatus.examStatus,
					// 默认账户ID
					'defaultId' : '',
					// 版本号ID
					'versionId' : '',
					// 客户ID
					'custId' : '',
					// 会员信息
					'member' : '',
					// 偏好地址
					'preferenceAddressList' : '',
					/**
					 * 接送货地址
					 */
					// 地址类型
					'addressType' : DataDictionary.ADDRESS_TYPE,
					// 详细地址
					'address' : '',
					// 省份ID
					'province' : '',
					// 省份名称
					'provinceName' : '',
					// 城市ID
					'city' : '',
					// 城市名称
					'cityName' : '',
					// 区县
					'area' : '',
					// 区县名称
					'areaName' : '',
					// 邮编
					'postcode' : '',
					// 会员id
					'memberId' : '',
					// 状态 正常：0； 审批中：1 ；无效 ：2；
					'status' : judgeStatus.examStatus,
					/**
					 * 会员账户
					 */
					// 开户行
					'bank' : '',
					// 开户行ID
					'bankId' : '',
					// 支行名称
					'subBankname' : '',
					// 支行名称Id
					'subBanknameId' : '',
					// 是否默认账户 1为是，0为否
					'isdefaultaccount' : judgeStatus.trueOrFalse,
					// 银行账号
					'bankAccount' : '',
					// 开户姓名
					'countName' : '',
					// 账号与客户关系
					'relation' : '',
					// 开户省份
					'bankProvinceId' : '',
					// 开户省份名称
					'bankProvinceName' : '',
					// 账户性质
					'accountNature' : DataDictionary.ACCOUNT_NATURE,
					// 账户用途
					'accountUse' : DataDictionary.ACCOUNT_USE,
					// 开户城市
					'bankCityId' : '',
					// 开户城市名称
					'bankCityName' : '',
					// 财务联系人姓名
					'financeLinkman' : '',
					// 财务联系人ID
					'financeLinkmanId' : '',
					// 联系手机
					'linkManMobile' : '',
					// 联系人固话
					'linkManPhone' : '',
					// 联系方式
					'contactType' : '',
					// 开户区县
					'bankArea' : '',
					// 最后更新部门ID
					'lastUpdateDeptId' : '',
					// 创建部门ID
					'createDeptId' : '',
					// 所属客户
					'belongcustom' : '',
					// 状态 正常：0； 审批中：1 ；无效 ：2；
					'status' : judgeStatus.examStatus,
					/**
					 * 偏好地址
					 */
					// 地址类型
//					'addressType' : DataDictionary.ACCOUNT_USE,
					'addressType' : DataDictionary.ADDRESS_TYPE,
					// 地址
					'address' : '',
					// 偏好起始时间
					'startTime' : 'ISDATE',
					// 偏好截止时间
					'endTime' : 'ISDATE',
					// 发票要求
					'billRequest' : DataDictionary.BILL_REQUIRE,
					// 停车费用
					'hasStopCost' : judgeStatus.trueOrFalse,
					// 付款方式
					'payType' : DataDictionary.PAY_WAY,
					// 是否送货上楼 1为是，0为否
					'isSendToFloor' : judgeStatus.trueOrFalse,
					// 其它要求
					'otherNeed' : '',
					// 是否主地址
					'isMainAddress' : judgeStatus.trueOrFalse,
					//状态 正常：0；  审批中：1  ；无效 ：2；
					'status' : judgeStatus.examStatus,
					// 接送货地址ID
					'shuttleAddressId' : '',
					// 关联的联系人ID 
					'linkManId' : ''
				};
				return {
					//获得会员修改字段
					getField : function(name) {
						return member[name];
					},
					//获得会员修改字段修改内容
					getFieldModify : function(name, value) {
						var fieldValue = memberModify[name];
						//文本显示为"",时间显示为"ISDATE"，数据字典显示[]
						if ('' == fieldValue) {
							return 'ISTXT';
						}else if ('ISDATE' == fieldValue) {
							return 'ISDATE';
						}else {
							if (name != 'linkmanType') {
								if('secondTrade'==name){
										var params =['SECOND_TRADE'];
										 initDataDictionary(params);
								}
								return DpUtil.changeDictionaryCodeToDescrip(
										value, fieldValue);
							} else if (name == 'linkmanType') {
								if(!Ext.isEmpty(value)){
									var linkmanTypeValue = '';
									var linkmanTypeArray = value.split(',');
									if (!Ext.isEmpty(linkmanTypeArray)) {
										for (var i = 0; i < linkmanTypeArray.length; i++) {
											if (linkmanTypeArray[i] == 'true'
													|| linkmanTypeArray[i] == '1') {
												if (i != 0
														&& !DpUtil
																.isEmpty(linkmanTypeValue)) {
													linkmanTypeValue += ',';
												}
												linkmanTypeValue += DpUtil
														.changeDictionaryCodeToDescrip(
																i, fieldValue);
											}
										}
									}
									return linkmanTypeValue;
								}
								return value;
							}
						}
					},
					//处理会员维护修改数据，对ID类的数据不进行显示 alterInfo:修改的信息记录
					processingData:function(alterInfo){
						var me = this;
						var returnValue = [];
						for (var i = 0; i < alterInfo.length; i++){
							var fieldName = ModifyMember.getField(alterInfo[i].fieldName);
							if('KEY' != fieldName && 'LIST' != fieldName && 'OBJ' != fieldName ){
								returnValue.push(alterInfo[i]);
							}
						}
						return returnValue;
					}
				};
			})();
		}
		return ModifyMember;
	}
});


