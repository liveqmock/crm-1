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
								'codeDesc' : i18n('i18n.memberView.yes')
							}, {
								'code' : '2',
								'codeDesc' : i18n('i18n.memberView.no')
							}],
					'examStatus' : [{
								'code' : '0',
								'codeDesc' : i18n('i18n.custview.m_normal')
							}, {
								'code' : '1',
								'codeDesc' : i18n('i18n.memberView.approvalIn')
							}, {
								'code' : '2',
								'codeDesc' : i18n('i18n.custview.expirationD')
							}],
					'trueOrFalse' : [{
								'code' : 'true',
								'codeDesc' : i18n('i18n.memberView.yes')
							}, {
								'code' : 'false',
								'codeDesc' : i18n('i18n.memberView.no')
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
					'custNumber' : i18n('i18n.memberView.customerCode'),
					// 客户等级
					'degree' : i18n('i18n.memberView.customerRating'),
					// 客户姓名
					'custName' : i18n('i18n.memberView.customerName'),
					// 所属行业
//					'tradeId' : i18n('i18n.custview.belongIndustry'),
					'tradeId' : 'KEY',
					//客户类别
					'custGroup':i18n('i18n.custview.custCategory'),
					// 客户类型
					'custType' : i18n('i18n.memberView.customerType'),
					// 税务登记号 证件类型
					'cardTypeCust' : i18n('i18n.memberView.cardTypeCon'),
					// 税务登记号
					'taxregNumber' : i18n('i18n.memberView.taxRegistrationNumber'),
					// 公司性质
					'companyProperty' : i18n('i18n.memberView.natureOfCompany'),
					// 客户属性
					'custNature' : i18n('i18n.memberView.customerAttribute'),
					// 所在省份
					'province' : i18n('i18n.custview.belongProvince'),
					// 所在省份ID
//					'provinceId' : i18n('i18n.custview.belongProvinceId'),
					'provinceId' : 'KEY',
					// 所在城市
					'city' : i18n('i18n.custview.belongCity'),
					// 所在城市ID
					'cityId' : 'KEY',
//					'cityId' : i18n('i18n.custview.belongCityId'),
					// 客户注册地址
					'registAddress' : i18n('i18n.custview.registAddress'),
					// 是否特殊客户 1为特殊客户，2为一般客户
					'isSpecial' : i18n('i18n.memberView.whetherSpecialcustomers'),
					// 仅限主联系人兑换积分
					'isRedeempoints' : i18n('i18n.memberView.AllowedContactExchangePoints'),
					// 是否部门重要客户1为是，2为否
					'isImportCustor' : i18n('i18n.custview.whetherImportantSectorCustomers'),
					// 客户潜力类型
					'custPotentialType' : i18n('i18n.memberView.customerPotentialType'),
					// 是否接受德邦营销信息
					'isAcceptMarket' : i18n('i18n.memberView.whetherAcceptMarketingInfo'),
					// 品牌价值
					'brandWorth' : i18n('i18n.memberView.brandValue'),
					// 来源渠道
					'channelSource' : i18n('i18n.memberView.sourceChannel'),
					// 偏好渠道
					'channel' : i18n('i18n.memberView.preferenceChannels'),
					// 偏好服务
					'preferenceService' : i18n('i18n.memberView.preferenceService'),
					// 上一年公司规模
					'companyScop' : i18n('i18n.custview.previousYarCompanySize'),
					// 上一年公司利润
					'lastYearProfit' : i18n('i18n.custview.isAcceptNaduMarketingInformation'),
					// 上一年财务公司收入
					'lastYearIncome' : i18n('i18n.custview.thePreviousYearTinancialCompanyRevenue'),
					// 是否集中结算1为是，2为否
					'isFocusPay' : i18n('i18n.custview.isFocusPay'),
					// 集中结算部门
//					'focusDeptId' : i18n('i18n.MemberCustEditView.focusDeptId'),
					'focusDeptId' : i18n('i18n.custview.centralizedSettlementDepartmentID'),
					// 集中结算部门名称
					'focusDeptName' : i18n('i18n.custview.centralizedSettlementDepartmentName'),
					// 发票抬头
					'billTitle' : i18n('i18n.memberView.invoiceTo'),
					// 是否母公司1为是，2为否
					'isParentCompany' : i18n('i18n.memberView.ifTheParentCompany'),
					// 客户所属母公司名称
					'parentNumber' : i18n('i18n.memberView.parentCompany'),
					// 客户所属母公司ID
					'parentCompanyId' :'KEY',
//					'parentCompanyId' : i18n('i18n.custview.parentCompanyID'),
					// 注册资金
					'registerFund' : i18n('i18n.memberView.registeredCapital'),
					// 临欠额度
					'procRedit' : i18n('i18n.memberView.lineOfCredit'),
					// 推荐人
					'recommendCust' : i18n('i18n.memberView.recommended'),
					// 备注
					'remark' : i18n('i18n.memberView.remarks'),
					// 主联系人
					'mainContact' : 'OBJ',
					// 联系人集合
					'contactList' : 'LIST',
					// 接送货地址接货
					'shuttleAddressList' : 'LIST',
					// 银行账号
					'accountList' : 'LIST',
					// 接送货主地址
					'jshAddress' : i18n('i18n.custview.shuttleShippersAddress'),
					// 客户简称
					'simpleName' : i18n('i18n.custview.customersReferredTo'),
					// 客户业务类型
					'bussType' : i18n('i18n.custview.customerTypeOfBusiness'),
					// 是否异地调货 1为是，2为否
					'isTranGoods' : i18n('i18n.custview.ifForeignGoods'),
					// 所属区域
					'areaId' : 'KEY',
//					'areaId' : i18n('i18n.custview.blongAreaID'),
					// 所属部门
					'deptId' : 'KEY',
//					'deptId' : i18n('i18n.custview.blongDeptID'),
					// 客户ID
					'custId' : 'KEY',
//					'custId':i18n('i18n.SearchMember.custId'),
					// 主要联系人ID
					'contactId' :'KEY',
//					'contactId' :i18n('i18n.custview.primaryContactID'),
					// 升级来源
					'upgradeSource' : i18n('i18n.custview.upgradeSource'),
					// 客户状态 正常：0； 审批中：1 ；无效 ：2；
					'custStatus' : i18n('i18n.memberView.isForce'),
					// 成为会员时间
					'becomeMemTime' : i18n('i18n.custview.becomeMemberOfTime'),
					// 责任人(维护人)ID
//					'responsibillity' : i18n('i18n.custview.responsiblePersonID'),
					'responsibillity' :'KEY',
					// 版本号
					'versionNumber' : i18n('i18n.custview.versionNumber'),
					// 客户上一等级
					'nextLevel' : i18n('i18n.custview.ratingOnCustomer'),
					// 上次升降级时间
					'lastChangTime' : i18n('i18n.custview.lastRelegationTime'),
					// 可使用月结额度
					'surplusMonthlyStatementMoney' : i18n('i18n.memberView.canUseMonthlyAmount'),
					/**
					 * 会员联系人
					 */
					// 联系人编码
					'number' : i18n('i18n.memberView.contactCoding'),
					// 联系人姓名
					'name' : i18n('i18n.memberView.contactName'),
					// 联系人 证件类型
					'cardTypeCon' : i18n('i18n.memberView.cardTypeCon'),
					// 身份证号
					'idCard' : i18n('i18n.ScatterCustManagerView.idNumber'),
					// 联系人类型
					'linkmanType' : i18n('i18n.memberView.contactType'),
					// 是否主联系人1为是，0为否
					'isMainLinkMan' : i18n('i18n.memberView.whetherTheMainContact'),
					// 性别
					'sex' : i18n('i18n.memberView.sex'),
					// 固定电话
					'telPhone' : i18n('i18n.memberView.telephoneNumber'),
					// 手机号码
					'mobilePhone' : i18n('i18n.memberView.mobileNumber'),
					// 职务
					'duty' : i18n('i18n.memberView.post'),
					// 任职部门
					'dutyDept' : i18n('i18n.memberView.theOffice'),
					// 出生日期
					'bornDate' : i18n('i18n.memberView.birthDate'),
					// 获知公司途径
					'gainave' : i18n('i18n.memberView.learnCompanyWay'),
					// 物流决定权
					'decisionRight' : i18n('i18n.memberView.logisticsDecision'),
					// 籍贯
					'nativePlace' : i18n('i18n.memberView.placeOfOrigin'),
					// 个人爱好
					'personLove' : i18n('i18n.memberView.personalHobby'),
					// 民族
					'folk' : i18n('i18n.memberView.Nation'),
					// Email地址
					'email' : i18n('i18n.memberView.email'),
					// QQ号码
					'qqNumber' : i18n('i18n.memberView.qq'),
					// MSN
					'msn' : i18n('i18n.memberView.msn'),
					// 旺旺号
					'ww' : i18n('i18n.memberView.wangwang'),
					// 阿里ID
//					'alid' : i18n('i18n.memberView.aliID'),
					'alid' : 'KEY',
					// 网营ID
//					'onlineBusinessId' : i18n('i18n.memberView.tennisCampID'),
					'onlineBusinessId' : 'KEY',
					// 淘宝ID
//					'taobId' : i18n('i18n.memberView.taobaoID'),
					'taobId' : 'KEY',
					// 金蝶友商ID
//					'youshangId' : i18n('i18n.memberView.kingdeeFriendsBusinessGroup'),
					'youshangId' : 'KEY',
					// 账号信息ID
					'accountId' : 'KEY',
//					'accountId' : i18n('i18n.custview.financialInformationID'),
					// 受理部门ID
					'acceptDeptid' : 'KEY',
//					'acceptDeptid' : i18n('i18n.custview.theDepartmentHandlingID'),
					// 状态 正常：0； 审批中：1 ；失效 2；
					'status' : 'ISSTATUS',
//					'status' : i18n('i18n.custview.status'),
					// 默认账户ID
//					'defaultId' : i18n('i18n.custview.defaultsAccountID'),
					'defaultId' : 'KEY',
					// 版本号ID
//					'versionId' : i18n('i18n.custview.versionID'),
					'versionId' : 'KEY',
					// 客户ID
					'custId' : 'KEY',
//					'custId':i18n('i18n.SearchMember.custId'),
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
					'addressType' : i18n('i18n.memberView.addressType'),
					// 详细地址
					'address' : i18n('i18n.custview.infoAddress'),
					// 省份ID
					'province' : 'KEY',
//					'province' : i18n('i18n.custview.provinceId'),
					// 省份名称
					'provinceName' : i18n('i18n.custview.proviceName'),
					// 城市ID
					'city' : 'KEY',
//					'city' : i18n('i18n.custview.cityId'),
					// 城市名称
					'cityName' : i18n('i18n.custview.cityName'),
					// 区县
					'area' : 'KEY',
//					'area' : i18n('i18n.custview.smallAreaID'),
					// 区县名称
					'areaName' : i18n('i18n.custview.smallAreaName'),
					// 邮编
					'postcode' : i18n('i18n.custview.zipCode'),
					// 会员id
					'memberId' : 'KEY',
//					'memberId' :i18n('i18n.custview.memberid'),
					// 状态 正常：0； 审批中：1 ；无效 ：2；
					'status' : 'ISSTATUS',
//					'status' : i18n('i18n.custview.status'),
					/**
					 * 会员账户
					 */
					// 开户行
					'bank' : i18n('i18n.memberView.bankAccount'),
					// 开户行ID
					'bankId' : 'KEY',
//					'bankId' : i18n('i18n.custview.bankAccountID'),
					// 支行名称
					'subBankname' : i18n('i18n.memberView.branchName'),
					// 支行名称Id
					'subBanknameId' : 'KEY',
//					'subBanknameId' : i18n('i18n.custview.branchID'),
					// 是否默认账户 1为是，0为否
					'isdefaultaccount' : i18n('i18n.memberView.ifADefaultAccount'),
					// 银行账号
					'bankAccount' : i18n('i18n.memberView.bankAccountnumber'),
					// 开户姓名
					'countName' : i18n('i18n.memberView.accountName'),
					// 账号与客户关系
					'relation' : i18n('i18n.memberView.accountAndCustomerRelationship'),
					// 开户省份
					'bankProvinceId' : 'KEY',
//					'bankProvinceId' : i18n('i18n.custview.openingProvinceID'),
					// 开户省份名称
					'bankProvinceName' : i18n('i18n.memberView.openingProvince'),
					// 账户性质
					'accountNature' : i18n('i18n.memberView.natureOfAccount'),
					// 账户用途
					'accountUse' : i18n('i18n.memberView.accountApplication'),
					// 开户城市
					'bankCityId' : 'KEY',
//					'bankCityId' : i18n('i18n.custview.openCityID'),
					// 开户城市名称
					'bankCityName' : i18n('i18n.memberView.openCity'),
					// 财务联系人姓名
					'financeLinkman' : i18n('i18n.memberView.financialContact'),
					// 财务联系人ID
					'financeLinkmanId' : 'KEY',
//					'financeLinkmanId' : i18n('i18n.custview.financialContactID'),
					// 联系手机
					'linkManMobile' : i18n('i18n.memberView.contactMobilePhone'),
					// 联系人固话
					'linkManPhone' : i18n('i18n.custview.contactPhone'),
					// 联系方式
					'contactType' : i18n('i18n.memberView.linkMethod'),
					// 开户区县
					'bankArea' : i18n('i18n.custview.accountCounties'),
					// 最后更新部门ID
					'lastUpdateDeptId' : 'KEY',
//					'lastUpdateDeptId' : i18n('i18n.custview.lastUpdateDepartmentID'),
					// 创建部门ID
					'createDeptId' : 'KEY',
//					'createDeptId' : i18n('i18n.custview.createDeptID'),
					// 所属客户
					'belongcustom' : 'KEY',
					// 状态 正常：0； 审批中：1 ；无效 ：2；
					'status' : 'ISSTATUS',
//					'status' : i18n('i18n.custview.status'),
					/**
					 * 偏好地址
					 */
					// 地址类型
					'addressType' : i18n('i18n.memberView.addressType'),
					// 地址
					'address' : i18n('i18n.memberView.address'),
					// 偏好起始时间
					'startTime' : i18n('i18n.memberView.startingTime'),
					// 偏好截止时间
					'endTime' : i18n('i18n.memberView.endTime'),
					// 发票要求
					'billRequest' : i18n('i18n.memberView.invoice'),
					// 停车费用
					'hasStopCost' : i18n('i18n.memberView.toFare'),
					// 付款方式
					'payType' : i18n('i18n.memberView.termsOfPayment'),
					// 是否送货上楼 1为是，0为否
					'isSendToFloor' : i18n('i18n.memberView.DoorToDoor'),
					// 其它要求
					'otherNeed' : i18n('i18n.memberView.otherRequirements'),
					// 是否主地址
					'isMainAddress' : i18n('i18n.custview.ifThePrimaryAddress'),
					// 状态 正常：0； 审批中：1 ；无效 ：2；
//					'status' : i18n('i18n.custview.status'),
					'status' : 'ISSTATUS',
					// 接送货地址ID
					'shuttleAddressId' : 'KEY',
//					'shuttleAddressId' :i18n('i18n.custview.accessDeliveryAddressID'),
					// 关联的联系人ID
					'linkManId' :'KEY'
//					'linkManId' :i18n('i18n.custview.associatedContactsID')
				};
				var memberModify = { // 客户编码
					'custNumber' : '',
					// 客户等级
					'degree' : DataDictionary.MEMBER_GRADE,
					// 客户姓名
					'custName' : '',
					// 所属行业
					'tradeId' : DataDictionary.TRADE,
					// 客户类型
					'custType' : DataDictionary.CUSTOMER_TYPE,
					// 税务登记号 证件类型
					'cardTypeCust' : DataDictionary.CARDTYPECUST,
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
				var linkType = {
					1:'财务联系人',
					2:'业务联系人',
					3:'发货联系人',
					4:'收货联系人',
					5:'协议联系人'
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
												switch(i){ 
												case 0:linkmanTypeValue += linkType[1] ; break;
												case 1:
												Ext.isEmpty(linkmanTypeValue)?(linkmanTypeValue += linkType[2]):(linkmanTypeValue += ','+linkType[2]) ;break;
												case 2:
												Ext.isEmpty(linkmanTypeValue)?(linkmanTypeValue += linkType[3]):(linkmanTypeValue += ','+linkType[3]) ;break;
												case 3:
												Ext.isEmpty(linkmanTypeValue)?(linkmanTypeValue += linkType[4]):(linkmanTypeValue += ','+linkType[4]) ;break;
												case 4:
												Ext.isEmpty(linkmanTypeValue)?(linkmanTypeValue += linkType[5]):(linkmanTypeValue += ','+linkType[5]) ;break;
												} 
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
							if('KEY' != fieldName && 'LIST' != fieldName && 'OBJ' != fieldName && 'ISSTATUS'!=fieldName){
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


