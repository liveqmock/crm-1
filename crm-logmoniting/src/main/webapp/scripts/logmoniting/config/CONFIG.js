/**.
 * <p>
 * 存放全局变量（1）控制页面调用那个DATA.JS（2）定义字段名称的全局变量<br/>
 * </p>
 * @author  张斌
 * @时间    2012-03-27
 * </p>
 */

/**.
 * <p>
 * 规范如此要求，判断'file'设置TEST为true/false<br/>
 * </p>
 * @author  张斌
 * @时间    2012-03-27
 * </p>
 */
CONFIG = (function(){
	var constants = {
			'TEST':false
	};
	if(window.location.protocol === 'file:')
	{
		constants.TEST = true;
	}
	
	return {
		get:function(name) {return constants[name];}
	};
})();


/**.
 * <p>
 * 如果要修改name或者发现与后台定义的不一样，那么在这里修改一次就OK，不用再区代码里面找很多很多了<br/>
 * </p>
 * @author  张斌
 * @时间    2012-03-08
 * </p>
 */
var CONFIGNAME = (function() {
    var constants = {
    		//----------------------订单-----------------------
            //货物名称*
            'order_goodsName':'goodsName',
            //货物总件数*
            'order_goodsTotalNumber':'goodsNumber',
            //货物总重量*
            'order_goodsTotalWeight':'totalWeight',
            //货物总体积*
            'order_goodsTotalVolume':'totalVolume',
            // 订单号
        	'order_orderNum':'orderNumber',
             //发货人姓名(托运人)
            'order_consignorName':'contactName',
            //运单号
            'order_waybillNum':'waybillNumber',
            //订单来源
            'order_resource':'resource',
            // 受理部门名称
        	'order_acceptDeptName':'acceptDeptName',
        	//订单状态
            'order_orderStatus':'orderStatus',
            //始发网点名称
            'order_startStationName':'startStationName',
            //--------------------------------------------------------------
    		//InformationTopLeftTopForm
    		'Info_customerCode'				:'custNumber',				//客户编码
		    'Info_custName'					:'custName',				//客户名称
		    'Info_custType'					:'custType',				//客户类型
		    'Info_taxRegistrationNumber'	:'taxregNumber',			//税务登记号
		    'Info_customerRating'			:'degree',					//客户等级
		    'Info_memberIntegral'			:'memberIntegral',			//客户总积分*		MemberPointsView.memberIntegral
		    'Info_customerAttribute'		:'custNature',				//客户属性
		    'Info_customerIndustry'			:'tradeId',					//客户行业
		    'Info_IDCardNumber'				:'idCard',					//证件号码
    		
    		//InformationTopLeftbtm
    		'InfoTLB_contactPerson'			:'name',					//联系人
    		'InfoTLB_sex'					:'sex',						//性别
    		'InfoTLB_post'					:'duty',					//职务
    		'InfoTLB_integral'				:'integral',				//积分现有
    		'InfoTLB_telephoneNumber'		:'telPhone', 				//固定电话 
    		'InfoTLB_mobileNumber'			:'mobilePhone',				//手机号码
//    		'InfoTLB_address'				:'preferenceAddressList',	//地址	偏好地址的主地址
    		'InfoTLB_isMainLinkMan'			:'isMainLinkMan',			//是否主联系人
    		//InformationTopRighTopForm
    		'InfoTLT_LastTransactionDate'	:'latelyTradeDate',			//最近一次交易日期*
    		'InfoTLT_durnTheLastTransaction':'duration',				//距离上次交易时长*
    		//InformationTopRighCtn    		
    		
    		//fnInformationTopRighCtnStore
    		'month1'						:'month1',					//'1月',
    		'month2'						:'month2',					//'二月',
    		'month3'						:'month3',					//'三月',
			'month4'						:'month4',					//'四月',
			'month5'						:'month5',					//'五月',
			'month6'						:'month6',					//'六月'},
			
			//HistyComplaintStatistics历史投诉统计	在理赔里面
			// 投诉类型	对应的投诉次数（超重超方、货物破损、时效延迟、合计）
			'hsyCpt_insuranceClaims'		:'insuranceclaims',			//保险理赔
			'hsyCpt_agencyProblem'			:'agencyproblem',			//代理问题
			'hsyCpt_nonComplaints'			:'notcomplaint',			//非投诉
			'hsyCpt_costComplaint'			:'costcomplaint',			//费用投诉
			'hsyCpt_Service'				:'service',					//服务
			'hsyCpt_billingErrors'			:'billingerror',			//开单差错
			'hsyCpt_customerReminder'		:'customerReminder',		//客户催单
			'hsyCpt_customerReason'			:'customerReason',			//客户原因
			'hsyCpt_internalComplaints'		:'innerComplaints',			//内部投诉
			'hsyCpt_personalityProblem'		:'personalityProblem',		//人品问题
			'hsyCpt_Prescription'			:'timeLiness',				//时效
			'hsyCpt_serviceError'			:'businessError',			//业务差错
			
			//ARecentComplaintList最后一次投诉明细
			'RC_treatmentNumber'			:'dealbill',				//'处理编号'
  	        'RC_waybillNumber'				:'bill',					//单号
  	        'RC_complainant'				:'compman',					//'投诉人'
    		'RC_telephoneNumber'			:'tel', 		  			//联系方式
  	        'RC_complaintTime'				:'reporttime',				//投诉时间
  	        'RC_complaintsType'				:'reporttype',				//业务类型
  	        'RC_processingStatus'			:'prostatus',				//处理状态

  	        //HistoricalClaimsStatistics历史理赔统计
  	        'HCS_impersonator'              :'impersonator',         	//冒领
  	        'HCS_pieceslost'				:'pieceslost',				//整件丢失
  	        'HCS_billlost'					:'billlost',				//整票丢失
  	        'HCS_goodslack'					:'goodslack',				//内务极少
  	    
  	        'HCS_damaged'					:'breakageforpay',			//破损breakageforpay
  	        'HCS_wet'						:'dampforpay',				//潮湿dampforpay
  	        'HCS_pollution'					:'polluteforpay',			//污染polluteforpay
  	        'HCS_miscellaneous'				:'othersforpay',			//其他othersforpay
  	        'HCS_total'						:'total',					//合计
  	        
  	        //ARecentClaimsList最近一次理赔明细
  	        'RCL_waybillNumber'				:'waybill',					//凭证号
  	        'RCL_startingSector'			:'waybill',					//出发部门
  	        'RCL_accidentType'				:'insurType',				//出险类型
  	        'RCL_claimTypes'				:'recompenseType',			//理赔类型
  	        'RCL_claimAmount'				:'recompenseAmount',		//理赔金额
  	        'RCL_theState'					:'status',				    //处理状态
  	        
  	        //InformationMidBtmGrid综合信息中下部表格（维护）
  	        'IMB_investigators'				:'linkName',				//联系人姓名
  	        'IMB_accessObject'				:'linkManMobile',			//手机号码
  	        'IMB_customerRequirType'		:'linkManPhone',			//固定电话
  	        'IMB_customerRequirDescrip'		:'theme',					//维护主题
  	        'IMB_solution'					:'needType',				//客户意见
  	        'IMB_customerAdvice'			:'way',						//具体维护方式
  	        'IMB_visitingTime'			    :'schedule',				//维护时间
  	     
  	        //InformationBtmGrid综合信息下部表格（合同）
  	        'IBG_auditNumber'				:'contractNum',				//审核编号
  			'IBG_termsOfPayment'			:'payWay',			        //付款方式
  	        'IBG_preferentialType'			:'preferentialType',		//优惠类型
  	        'IBG_effectiveDate'				:'contractBeginDate',	    //生效日期
  	        'IBG_expirationDate'			:'contractendDate',			//失效日期
  	        'IBG_monthlyAmount'				:'arrearaMount',			//月结额度
  	        'IBG_protocol'					:'linkManName',				//协议联系人,使用了联系人姓名
  	        'IBG_contactMobilePhone'		:'linkManMobile',			//联系人手机
  	        
  	        //ItemBasiInfor基础信息
  	        
    		'BI_customerCode'				:'custNumber',				//客户编码
		    'BI_Info_customerRating'		:'degree',					//客户等级(业务字典)
		    'BI_Info_customerName'			:'custName',				//客户名称
  	        'BI_customerType'				:'custType',				//客户类型(业务字典)
  	        'BI_taxRegistrationNumber'		:'taxregNumber',			//企业税务登记号
		    'BI_Info_businessType'			:'custNature',				//客户属性(业务字典)
		    'BI_Info_customerIndustry'		:'tradeId',					//客户行业(业务字典)
		    'BI_provincesAndCities'			:'city',					//所在省市  
  	    	'BI_natureOfCompany'			:'companyProperty',			//公司性质(业务字典)
  	    	'BI_whetherSpecialcustomers'	:'isSpecial',				//是否特殊客户(t/f)
//  	    	'BI_AllowedContactExchangePoints':'isRedeempoints',			//允许联系人兑换积分(t/f)
  	    	'BI_whetherImportantCustomers':'isImportCustor',			//是否部门重要客户(t/f)
  			'BI_customerPotentialType'		:'custPotentialType',		//客户潜力类型(业务字典)
  			'BI_whetherAcceptMarketingInfo' :'isAcceptMarket',			//是否接受营销信息(t/f)
  			'BI_brandValue'					:'brandWorth',				//品牌价值
  			'BI_sourceChannel'				:'channelSource',			//来源渠道(业务字典)
  			'BI_preferenceChannels'			:'channel',					//偏好渠道(业务字典)
  			'BI_preferenceService'			:'preferenceService',		//偏好服务(业务字典)
  			'BI_companyscaleTheLastYear'	:'companyScop',				//上一年公司规模(业务字典)
  			'BI_profitsOfCompanyTheLastYear':'lastYearProfit',			//上一年公司利润
  			'BI_aYearIncome'				:'lastYearIncome',			//上一年公司收入
//  			'BI_doesCentralizedReceiving'	:'isFocusPay',				//是否集中结算(t/f)
//  			'BI_centralizedBillingDepartment':'focusDeptId',			//集中结算部门
  			'BI_invoiceTo'					:'billTitle',				//发票抬头
//  			'BI_ifTheParentCompany'			:'isParentCompany',			//是否母公司（t/f）
//  			'BI_parentCompany'				:'parentNumber',			//所属母公司
  			'BI_registeredCapital'			:'registerFund',			//注册资金
  			'BI_lineOfCredit'				:'procRedit',				//临欠额度
//  			'BI_canUseMonthlyAmount'		:'surplusMonthlyStatementMoney',//可使用月结额度
//  			'BI_recommended'				:'recommendCust',			//推荐人
  			'BI_remarks'					:'remark',					//备注
  			
  			'BI_areaName'					:'areaName',				//所属区域
  			'BI_deptName'					:'deptName',				//所属部门
  			'BI_isForce'					:'custStatus',				//信息是否生效
  			'BI_createPeople'				:'createUser',				//创建人
  			'BI_createBranch'				:'createBranch',			//创建部门
  			'BI_createTime'					:'createDate',				//创建时间
  			'BI_lastUpdateMan'				:'modifyUser',				//最后更新人
  			'BI_lastUpdateDepartment'		:'lastUpdateDepartment',	//最后更新部门
  			'BI_lastUpdateTime'				:'modifyDate',				//最后更新时间
  			
  			//-------------------------------------联系人信息-------------------------------------
  			//ContactInforTopGrid
  			'CITG_contactCoding'			:'number',					//联系人编码
  			'CITG_contactName'				:'name',					//联系人名称
  			'CITG_contactType'				:'linkmanType',				//联系人类型
  			'CITG_sex'						:'sex',						//性别
  			'CITG_post'						:'duty',					//职务
    		'CITG_mobileNumber'				:'mobilePhone',				//手机号码
    		'CITG_telephoneNumber'			:'telPhone', 				//固定电话 
    		'CITG_whetherTheMainContact'	:'isMainLinkMan',			//是否主联系人()
    		'CITG_ContactModel'				:'cardTypeCon',				//证件类型——新增
  			'CITF_IDCardNumber'				:'idCard',					//身份证号
  			'CITF_theOffice'				:'dutyDept',				//任职部门
  			'CITF_birthDate'				:'bornDate',				//出生日期
  			'CITF_learnCompanyWay'			:'gainave',					//获知公司途径
  			'CITF_logisticsDecision'		:'decisionRight',			//物流决定权
  			'CITF_placeOfOrigin'			:'nativePlace',				//籍贯
  			'CITF_personalHobby'			:'personLove',				//个人爱好
  			'CITF_Nation'					:'folk',					//民族
  			'CITF_email'                    :'email',					//邮件
  			'CITF_qqNumber'                 :'qqNumber',				//QQ
  			'CITF_msn'                      :'msn',						//邮件
  			'CITF_wangwang'					:'ww',						//旺旺
  			'CITF_aliID'					:'onlineBusinessId',		//阿里ID
  			'CITF_tennisCampID'				:'alid',					//网营ID
  			'CITF_taobaoID'					:'taobId',					//淘宝ID
  			'CITF_kingdeeFriendsBusinessGroup':'youshangId',			//金蝶友商ID
  			'CITF_preferenceAddressList':'preferenceAddressList',		//偏好地址

  			//ContactInforGrid
  			'CIG_addressType'				:'addressType',				//地址类型(ADDRESS_TYPE)
  			'CIG_address'					:'address',					//地址
  			'CIG_startingTime'				:'startTime',				//偏起始时间
  			'CIG_endTime'					:'endTime',					//偏结束时间
  			'CIG_invoice'					:'billRequest',				//发票要求
  			'CIG_toFare'					:'hasStopCost',				//提车费
  			'CIG_termsOfPayment'			:'payType',					//付款方式(业务)PAY_WAY
  			'CIG_DoorToDoor'				:"isSendToFloor",			//送货上楼
  			'CIG_otherRequirements'			:'otherNeed',				//其他要求
  			
  			//ContactInforForm
  			'CIF_createPeople'				:'createPeople',			//创建人
  			'CIF_createBranch'				:'createBranch',			//创建部门
  			'CIF_createTime'				:'createDate',				//创建时间
  			'CIF_lastUpdateMan'				:'lastUpdateMan',			//最后更新人
  			'CIF_lastUpdateDepartment'		:'lastUpdateDepartment',	//最后更新部门
  			'CIF_lastUpdateTime'			:'modifyDate',				//最后更新时间
  			
  			//-------------------------------------联系人-------------------------------------

  			//选定会员详细展示表哥IntegrationBtmForm
  			'IBF_customerAvailablePoints'	:'customerAvailablePoints',	//'客户可用积分
  			'IBF_availableIntegralThisPeriod':'availableIntegralThisPeriod',//本期可用积分
  			'IBF_currentAccumulatedPoints'	:'currentAccumulatedPoints',//当前累计积分
  			'IBF_onOfAccumulatedPoints'		:'onOfAccumulatedPoints',	//上期累计积分
  			'IBF_theCumulativeTotalScore'	:'theCumulativeTotalScore',	//累计总积分
  			'IBF_cumulativeTotalDemeritPoints':'cumulativeTotalDemeritPoints',//累计总扣分
  			'IBF_freezeDeduction'			:'freezeDeduction',			//冻结扣分
  			'IBF_finallyIntegralTime'		:'finallyIntegralTime',		//最后积分时间
  			'IBF_integralExpirationTime'	:'integralExpirationTime',	//积分到期时间
  			
  			//---------------------------------计划/日程--------------------------------
  			'returnVisit_formulater'		:'formulater',				//计划制定人
  			'returnVisit_userName'			:'userName',				//执行人
  			'returnVisit_linkName'			:'linkName',				//联系人姓名
  			'returnVisit_linkManMobile'		:'linkManMobile',			//手机号码
  			'returnVisit_linkManPhone'		:'linkManPhone',			//固定电话
  			'returnVisit_theme'				:'theme',					//维护主题
  			'returnVisit_problem'			:'problem',					//客户意见(//需求及问题)
  			'returnVisit_way'				:'way',						//具体维护方式(开发方式)（业务）
  			'returnVisit_schedule'			:'schedule',				//维护时间（开发时间）
  			'returnVisit_needType'			:'needType',				//需求分类（业务）
  			'returnVisit_solution'			:'solution',				//需求及问题解决办法
  			//--------------------------维护记录(与计划日程一样)--------------------------------------------
  			//---------------------------------理赔---------------------------------------------
  			'recompense_waybill'			:'waybill',					//运单对象
  			'recompense_waybillNumber'		:'waybillNumber',			//凭证号
  			'recompense_receiveDept'		:'receiveDept',				//出发部门
  			'recompense_insurType'			:'insurType',				//出险类型
  			'recompense_recompenseType'		:'recompenseType',			//理赔类型
  			'recompense_recompenseMethod'	:'recompenseMethod',		//理赔方式
  			'recompense_realAmount'			:'realAmount',				//理赔金额(实际赔金额)
  			'recompense_status'				:'status',					//处理状态
  			//---------------------------投诉--------------------------------------------
  			'complaint_dealbill'			:'dealbill',				//处理编号
  			'complaint_bill'				:'bill',					//运单号
  			'complaint_compman'				:'compman',					//投诉人
  			'complaint_tel'					:'tel',						//联系方式
  			'complaint_reporttime'			:'reporttime',				//投诉时间（上报时间）
  			'complaint_reporttype'			:'reporttype',				//投诉类型
  			'complaint_prostatus'			:'prostatus',				//处理状态
  			'complaint_dealstatus'			:'dealstatus',				//解决情况
  			'complaint_cussatisfaction'		:'cussatisfaction',			//客户满意度
  			//---------------------------------积分-----------------------------------
  			//IntegrationForm
		    'I_memberIntegral'				:'totalScore',				//客户总积分
  			'I_customerAvailablePoints'		:'currentUsableScore',		//'客户可用积分
  			'I_AllowedContactExchangePoints':'AllowedContactExchangePoints',//是否允许联系人兑换积分
  			
            'IF_contact'                    :'contact',					//联系人对象
  			'IF_contactName'				:'name',					//联系人姓名
  			'IF_contactCoding'				:'number',					//联系人编码
  			'IF_availablePoints'			:'currentUsableScore',		//可用积分
  			'IF_thisIssueHasBeenWithTheIntegral':'currentToUsesScore', 	//本期已用积分
  			'IF_currentAccumulatedPoints'	:'currentTotalScore',		//本期累计积分
  			'IF_cumulativeTotalScore'		:'totalScore',				//累计总积分
  			'IF_totalAlreadyIntegral'		:'totalToUsesScore',		//累计已用积分
  			'IF_finalIntegrationTime'		:'lastDateScore',			//最后积分时间
  			//-------------------------------合同----------------------------------------
  			'contract_contractNum'			:'contractNum',				//审核编号
  			'contract_deptName'				:'deptName',				//部门（对应部门名称）
  			'contract_payWay'				:'payWay',					//结款方式
  			'contract_beforeContractNum'	:'beforeContractNum',		//原合同编号
  			'contract_contractSubject'		:'contractSubject',			//合同主体 即子公司
  			'contract_custCompany'			:'custCompany',				//客户全称
  			'contract_regicapital'			:'regicapital',				//注册资金
  			'contract_preferentialType'		:'preferentialType',		//优惠类型
  			'contract_insuredPriceRate'		:'insuredPriceRate',		//保价费率
  			'contract_receivePriceRate'		:'receivePriceRate',		//接货费率
  			'contract_deliveryPriceRate'	:'deliveryPriceRate',		//送货费率
  			'contract_postageDiscounts'		:'chargeRebate',			//运费折扣
  			'contract_preferential'			:'preferential',			//合同优惠信息
  			'contract_arrearaMount'			:'arrearaMount',			//申请欠款额度，改为结算限额
  			'contract_linkManName'			:'linkManName',				//协议联系人
  			'contract_linkManMobile'		:'linkManMobile',			//联系人手机
  			'contract_reconDate'			:'reconDate',				//对账日期
  			'contract_invoicDate'			:'invoicDate',				//开发票日期
  			'contract_resultDates'			:'resultDate',				//结款日期
  			'contract_contractBeginDate'	:'contractBeginDate',		//合同起始日期
  			'contract_contractendDate'		:'contractendDate',			//合同到期日期
  			'contract_application'			:'application',				//申请事由
  			'contract_createUser'			:'createUser',				//创建人
  			'contract_createDeptName'		:'createDeptName',			//创建部门
  			'contract_createDate'			:'createDate',				//创建时间
  			
  			'contract_contractStatus'		:'contractStatus',			//合同状态
  			'contract_goodsName'			:'goodsName',				//走货名称
  			'contract_agentgathRate'		:'agentgathRate',			//代收费率
  			'contract_linkManPhone'			:'linkManPhone',			//固定电话
  			
  			'contract_threeMonthAmount'		:'arrAmount',				//近三个月发货金额
  			'contract_lastModifyUser'		:'modifyUser',				//最后修改人					
  			'contract_lastModifyDept'		:'lastModifyDept',			//最后修改部门
  			'contract_lastModifyDate'		:'modifyDate',				//最后修改时间
  			'contract_fileInfoList'			:'fileInfoList',				//合同附件
  			
  			
  			//------------------------------财务---------------------------------------
  			'account_bank'					:'bank',					//开户行
  			'account_subBankname'			:'subBankname',				//支行名称
  			'account_isdefaultaccount'		:'isdefaultaccount',		//是否默认账号
  			'account_bankAccount'			:'bankAccount',				//银行账号
  			'account_countName'				:'countName',				//开户人姓名
  			'account_relation'				:'relation',				//账号与客户关系
  			'account_bankProvinceId'		:'bankProvinceName',		//开户省
  			'account_accountNature'			:'accountNature',			//账户性质
  			'account_accountUse'			:'accountUse',				//账户用途
  			'account_bankCityId'			:'bankCityName',			//开户城市
  			'account_financeLinkman'		:'financeLinkman',			//财务联系人姓名
  			'account_linkManMobile'			:'linkManMobile',			//联系手机
  			'account_linkManPhone'			:'linkManPhone',			//固定电话
  			'account_createUser'			:'createUser',				//创建人
  			'account_createDate'			:'createDate',				//创建时间
  			'account_createDeptId'			:'createDeptId',			//创建部门
  			'account_modifyDate'			:'modifyDate',				//最后修改时间
//  		'account_modifyUser'			:'updaterName',				//最后修改人
//  		'account_lastUpdateDeptId'		:'updateDeptName',			//最后修改部门
  			
    	};

        return {
           get: function(name) { return constants[name]; }
       };
   })();
