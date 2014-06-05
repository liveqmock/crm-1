
Ext.define('InformationTopLeftTopFormModel',{	//最内左上角form
	extend:'Ext.data.Model',
	fields:[{name:CONFIGNAME.get('Info_customerCode')},				//客户编码 
	        {name:CONFIGNAME.get('Info_custName')},				//客户名称
	        {name:CONFIGNAME.get('Info_custType'),convert: function(value){
	        	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.CUSTOMER_TYPE);
	        }},					//客户类型
	        {name:CONFIGNAME.get('Info_taxRegistrationNumber')},	//税务登记号
	        {name:CONFIGNAME.get('Info_customerRating'),convert: function(value){
	        	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.MEMBER_GRADE);
	        }},			//客户等级
	        {name:CONFIGNAME.get('Info_memberIntegral')},			//客户总积分
	        {name:CONFIGNAME.get('Info_customerAttribute'),convert: function(value){
	        	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.CUSTOMER_NATURE);
	        }},		//客户属性
	        {name:CONFIGNAME.get('Info_customerIndustry'),convert: function(value){
	        	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.TRADE);
	        }},
	        {name:CONFIGNAME.get('Info_IDCardNumber')}	]			//客户行业
});

Ext.define('InformationTopLeftbtmModel',{		//最内左上Grid
	extend:'Ext.data.Model',
	fields:[{name:CONFIGNAME.get('InfoTLB_contactPerson')},			//联系人
	        {name:CONFIGNAME.get('InfoTLB_sex'),convert: function(value){
	        	if(value=='MAN'){
	        		return '男';
	        	}else if(value=='WOMAN'){
	        		return '女';
	        	}else{
	        		return null;
	        	}
	        	
	        	
	        }},					//性别
	        {name:CONFIGNAME.get('InfoTLB_post')},					//职务
	        {name:CONFIGNAME.get('InfoTLB_integral')},				//积分
	        {name:CONFIGNAME.get('InfoTLB_telephoneNumber')},		//固定电话
	        {name:CONFIGNAME.get('InfoTLB_mobileNumber')},			//手机号码
	        {name:CONFIGNAME.get('InfoTLB_isMainLinkMan')}	            //地址
	]			
});
Ext.define('InformationTopRighTopModel',{		//InformationTopRighTopForm
	extend:'Ext.data.Model',
	fields:[{name:CONFIGNAME.get('InfoTLT_LastTransactionDate'),convert: function(value){
		return new Date(value).format('yyyy-MM-dd');
    }},	//最近一次交易日期
	        {name:CONFIGNAME.get('InfoTLT_durnTheLastTransaction')}]//距离上次交易时长
});

Ext.define('InformationTopRighCtnModel',{		//客户货量分析
	extend:'Ext.data.Model',
	fields:[{name:CONFIGNAME.get('month1')},
	        {name:CONFIGNAME.get('month2')},
	        {name:CONFIGNAME.get('month3')},
	        {name:CONFIGNAME.get('month4')},
	        {name:CONFIGNAME.get('month5')},
	        {name:CONFIGNAME.get('month6')}]
});
/////////////////////

Ext.define('HistyComplaintStatisticsModel',{	//历史投诉统计
	extend:'Ext.data.Model',
	fields:[
	        { name: CONFIGNAME.get('hsyCpt_insuranceClaims') },		//保险理赔
	        { name: CONFIGNAME.get('hsyCpt_agencyProblem') },		//代理问题
	        { name: CONFIGNAME.get('hsyCpt_nonComplaints') },		//非投诉
	        { name: CONFIGNAME.get('hsyCpt_costComplaint') },		//费用投诉
	        { name: CONFIGNAME.get('hsyCpt_Service') },				//服务
	        { name: CONFIGNAME.get('hsyCpt_billingErrors') },		//开单差错
	        { name: CONFIGNAME.get('hsyCpt_customerReminder') },	//客户催单
	        { name: CONFIGNAME.get('hsyCpt_customerReason') },		//客户原因
	        { name: CONFIGNAME.get('hsyCpt_internalComplaints') },	//内部投诉
	        { name: CONFIGNAME.get('hsyCpt_personalityProblem') },	//人品问题
	        { name: CONFIGNAME.get('hsyCpt_Prescription') },		//时效
	        { name: CONFIGNAME.get('hsyCpt_serviceError') }]		//业务差错
});

Ext.define('ARecentComplaintListModel',{		//最后一次投诉明细
	extend:'Ext.data.Model',
	fields:[{ name: CONFIGNAME.get('RC_treatmentNumber') },			//处理编号
	        { name: CONFIGNAME.get('RC_waybillNumber') },			//运单号
	        { name: CONFIGNAME.get('RC_complainant') },				//投诉人
	        { name: CONFIGNAME.get('RC_mobileNumber') },			//手机号码
	        { name: CONFIGNAME.get('RC_telephoneNumber') },			//固定电话
	        { name: CONFIGNAME.get('RC_complaintTime') },			//投诉时间
	        { name: CONFIGNAME.get('RC_complaintsType') },			//投诉类型
	        { name: CONFIGNAME.get('RC_processingStatus') }]		//处理状态
});

Ext.define('HistoricalClaimsStatisticsModel',{	//历史理赔统计
	extend:'Ext.data.Model',
	fields:[
	        { name: CONFIGNAME.get('HCS_impersonator') },				// 冒领
	        { name: CONFIGNAME.get('HCS_pieceslost') },				// 整件丢失
	        { name: CONFIGNAME.get('HCS_billlost') },				// 整票丢失
	        { name: CONFIGNAME.get('HCS_goodslack') },				// 内务极少
	        { name: CONFIGNAME.get('HCS_damaged') },				//破损
	        { name: CONFIGNAME.get('HCS_wet') },					//潮湿
	        { name: CONFIGNAME.get('HCS_pollution') },				//污染
	        { name: CONFIGNAME.get('HCS_miscellaneous') },			//其他
	        { name: CONFIGNAME.get('HCS_total') }]					//合计
});

Ext.define('ARecentClaimsListModel',{			//最近一次理赔明细
	extend:'Ext.data.Model',
	fields:[{ name: CONFIGNAME.get('RCL_waybillNumber') },			//'运单号'
	        { name: CONFIGNAME.get('RCL_startingSector') },			//出发部门
	        { name: CONFIGNAME.get('RCL_accidentType') },			//出险类型
	        { name: CONFIGNAME.get('RCL_claimTypes') },				//理赔类型
	        { name: CONFIGNAME.get('RCL_claimAmount') },			//理赔金额
	        { name: CONFIGNAME.get('RCL_theState') }]				//所处状态
});
Ext.define('InformationMidBtmModel',{			//综合信息中下部表格
	extend:'Ext.data.Model',
	fields:[{ name: CONFIGNAME.get('IMB_visitingTime') },			//维护时间
	        { name: CONFIGNAME.get('IMB_investigators') },			//联系人姓名
	        { name: CONFIGNAME.get('IMB_accessObject') },			//手机号码
	        { name: CONFIGNAME.get('IMB_customerRequirType') },		//固定电话
	        { name: CONFIGNAME.get('IMB_customerRequirDescrip') },	//维护主题
	        { name: CONFIGNAME.get('IMB_solution') },				//客户意见
	        { name: CONFIGNAME.get('IMB_customerAdvice') }]			//具体维护方式
});
Ext.define('InformationBtmModel',{				//综合信息下部表格
	extend:'Ext.data.Model',
	fields:[{ name: CONFIGNAME.get('IBG_auditNumber') },			//审核编号
	        { name: CONFIGNAME.get('IBG_termsOfPayment') },			//付款方式
	        { name: CONFIGNAME.get('IBG_preferentialType') },		//优惠类型
	        { name: CONFIGNAME.get('IBG_effectiveDate') },			//生效日期
	        { name: CONFIGNAME.get('IBG_expirationDate') },			//失效日期
	        { name: CONFIGNAME.get('IBG_monthlyAmount') },			//月结额度
	        { name: CONFIGNAME.get('IBG_protocol') },				//协议联系人
	        { name: CONFIGNAME.get('IBG_contactMobilePhone') }]		//联系人手机
});
Ext.define('ItemBasiInforModel',{				//基础信息ItemBasiInfor
	extend:'Ext.data.Model',
	fields:[{ name:'id' },		//id
	        { name: CONFIGNAME.get('BI_customerCode') },			//客户编码
	        { name: CONFIGNAME.get('BI_Info_customerRating') },		//客户等级
	        { name: CONFIGNAME.get('BI_Info_customerName') },		//客户名称
	        { name: CONFIGNAME.get('BI_customerType') },			//客户类型
	        { name: CONFIGNAME.get('BI_taxRegistrationNumber') },	//企业税务登记号
	        { name: CONFIGNAME.get('BI_Info_businessType') },		//业务类型
	        { name: CONFIGNAME.get('BI_Info_customerIndustry') },	//客户行业
	        { name: CONFIGNAME.get('BI_provincesAndCities') },		//所在省市
	        { name: CONFIGNAME.get('BI_natureOfCompany') },			//公司性质
	        { name: CONFIGNAME.get('BI_whetherSpecialcustomers') },	//是否特殊客户
	        { name: CONFIGNAME.get('BI_AllowedContactExchangePoints') },//允许联系人兑换积分
	        { name: CONFIGNAME.get('BI_whetherImportantCustomers') },//是否部门重要客户
	        { name: CONFIGNAME.get('BI_customerPotentialType') },	//客户潜力类型
	        { name: CONFIGNAME.get('BI_whetherAcceptMarketingInfo') },//是否接受营销信息
	        { name: CONFIGNAME.get('BI_brandValue') },				//品牌价值
	        { name: CONFIGNAME.get('BI_sourceChannel') },			//来源渠道
	        { name: CONFIGNAME.get('BI_preferenceChannels') },		//偏好渠道
	        { name: CONFIGNAME.get('BI_preferenceService') },		//偏好服务
	        { name: CONFIGNAME.get('BI_companyscaleTheLastYear') },	//上一年公司规模
	        { name: CONFIGNAME.get('BI_profitsOfCompanyTheLastYear') },//上一年公司利润
	        { name: CONFIGNAME.get('BI_aYearIncome') },				//上一年公司收入
	        { name: CONFIGNAME.get('BI_doesCentralizedReceiving') },//是否集中接货
	        { name: CONFIGNAME.get('BI_centralizedBillingDepartment') },//集中结算部门
	        { name: CONFIGNAME.get('BI_invoiceTo') },				//发票抬头
	        { name: CONFIGNAME.get('BI_ifTheParentCompany') },		//是否母公司
	        { name: CONFIGNAME.get('BI_parentCompany') },			//所属母公司
	        { name: CONFIGNAME.get('BI_registeredCapital') },		//注册资金
	        { name: CONFIGNAME.get('BI_lineOfCredit') },			//信用额度
	        { name: CONFIGNAME.get('BI_canUseMonthlyAmount') },		//可使用月结额度
	        { name: CONFIGNAME.get('BI_recommended') },				//推荐人
	        { name: CONFIGNAME.get('BI_remarks') },					//备注
	        { name: CONFIGNAME.get('BI_areaName') },				//所属区域
	        { name: CONFIGNAME.get('BI_deptName') },				//所属部门
	        { name: CONFIGNAME.get('BI_isForce') },					//信息是否生效
	        { name: CONFIGNAME.get('BI_createPeople') },			//创建人
	        { name: CONFIGNAME.get('BI_createBranch') },			//创建部门
	        { name: CONFIGNAME.get('BI_createTime') },				//创建时间     
	        ]		
});

/**.
 * <p>
 * 联系人MODEL<br/>
 * <p>
 * @returns {ContactModel}
 */
Ext.define('ContactModel',{				//ContactInforTopGrid
	extend:'Ext.data.Model',
	fields:[{ name:'id' },			//id
	        { name: CONFIGNAME.get('CITG_contactCoding') },			//联系人编码
	        { name: CONFIGNAME.get('CITG_contactName') },			//联系人名称
	        { name: CONFIGNAME.get('CITG_contactType') },			//联系人类型
	        { name: CONFIGNAME.get('CITG_sex') },					//性别
	        { name: CONFIGNAME.get('CITG_ContactModel'),convert: function(value){
	        	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.CARDTYPECON);
	        } },			//证件类型——新增
	        { name:	CONFIGNAME.get('CITF_IDCardNumber') },			//'身份证号',
	        { name: CONFIGNAME.get('CITG_post') },					//职务
	        { name: CONFIGNAME.get('CITG_mobileNumber') },			//手机号码
	        { name:	CONFIGNAME.get('CITF_post') },               	//'职务',
			{ name:	CONFIGNAME.get('CITF_theOffice') },          	//'任职部门',
			{ name:	CONFIGNAME.get('CITF_birthDate') },          	//'出生日期',
			{ name:	CONFIGNAME.get('CITF_learnCompanyWay') },   	//'获知公司途径',
			{ name:	CONFIGNAME.get('CITF_logisticsDecision') },		//'物流决定权',
			{ name:	CONFIGNAME.get('CITF_placeOfOrigin') },     	//'籍贯',
			{ name:	CONFIGNAME.get('CITF_personalHobby') },      	//'个人爱好',
			{ name: CONFIGNAME.get('CITF_email') },//email
			{ name: CONFIGNAME.get('CITF_qqNumber') },//QQ
			{ name: CONFIGNAME.get('CITF_preferenceAddressList') },//偏好地址
			{ name: CONFIGNAME.get('CITF_msn') },//msn
			{ name:	CONFIGNAME.get('CITF_wangwang') },           	//'旺旺',
			{ name:	CONFIGNAME.get('CITF_aliID') },               	//'阿里ID',
			{ name:	CONFIGNAME.get('CITF_tennisCampID') },       	//'网营ID',
			{ name:	CONFIGNAME.get('CITF_taobaoID' ) }, 			//'淘宝ID',
			{ name:	CONFIGNAME.get('CITF_kingdeeFriendsBusinessGroup' )},//'金蝶友商ID',
			{ name:	CONFIGNAME.get('CITF_Nation') }, 				//'民族',
	        { name: CONFIGNAME.get('CITG_telephoneNumber') },		//固定电话 
	        { name: CONFIGNAME.get('CIF_createPeople') },			//创建人
	        { name: CONFIGNAME.get('CIF_createBranch') },			//创建部门
	        { name: CONFIGNAME.get('CIF_createTime') },				//创建时间
	        { name: CONFIGNAME.get('CITG_whetherTheMainContact') }]	//是否主联系人
});
/**.
 * <p>
 * 偏好地址MODEL<br/>
 * <p>
 * @returns {PreferenceAddressModel}
 */
Ext.define('PreferenceAddressModel',{				
	extend:'Ext.data.Model',
	fields:[{ name: CONFIGNAME.get('CIG_addressType') },			//地址类型
	        { name: CONFIGNAME.get('CIG_address') },				//地址
	        { name: CONFIGNAME.get('CIG_startingTime') },			//起始时间
	        { name: CONFIGNAME.get('CIG_endTime') },				//结束时间
	        { name: CONFIGNAME.get('CIG_invoice') },				//发票要求
	        { name: CONFIGNAME.get('CIG_toFare') },					//提车费
	        { name: CONFIGNAME.get('CIG_termsOfPayment') },			//付款方式
	        { name: CONFIGNAME.get('CIG_DoorToDoor') },				//送货上楼
	        { name: CONFIGNAME.get('CIG_otherRequirements') }]		//其他要求
});
/**.
 * <p>
 * 订单MODEL<br/>
 * <p>
 * @returns {OrderModel}
 */
Ext.define('OrderModel', {
    extend: 'Ext.data.Model',//托运人?受理网点?
    fields : [{
    	    //货物名称*
			name : CONFIGNAME.get('order_goodsName')
		},{
    	    //货物总件数*
			name : CONFIGNAME.get('order_goodsTotalNumber'),
			type:'int'
		},{
    	    //货物总重量*
			name : CONFIGNAME.get('order_goodsTotalWeight'),
			type:'float'
		},{
			//货物总体积*
			name : CONFIGNAME.get('order_goodsTotalVolume'),
			type:'float'
		},{
    	    //发货人姓名1*//托运人
			name : CONFIGNAME.get('order_consignorName')
		},{
	    	//订单号*
			name :  CONFIGNAME.get('order_orderNum')
		},{
	    	//运单号*
			name :  CONFIGNAME.get('order_waybillNum')
		},{
			//订单来源*
			name :  CONFIGNAME.get('order_resource')
		},{
		   // 受理部门Name*
			name :  CONFIGNAME.get('order_acceptDeptName')
		},{
		   // 订单状态*
			name :  CONFIGNAME.get('order_orderStatus')
		},{
		   // 始发网点名称*
			name :  CONFIGNAME.get('order_startStationName')
		}]
});

Ext.define('IntegrationBtmFormModel',{			//选定会员详细展示表哥
	extend:'Ext.data.Model',
	fields:[{ name: CONFIGNAME.get('IBF_customerAvailablePoints') },		//客户可用积分
	        { name: CONFIGNAME.get('IBF_availableIntegralThisPeriod') },	//本期可用积分
	        { name: CONFIGNAME.get('IBF_currentAccumulatedPoints') },		//当前累计积分
	        { name: CONFIGNAME.get('IBF_onOfAccumulatedPoints') },			//上期累计积分
	        { name: CONFIGNAME.get('IBF_theCumulativeTotalScore') },		//累计总积分
	        { name: CONFIGNAME.get('IBF_cumulativeTotalDemeritPoints') },	//累计总扣分
	        { name: CONFIGNAME.get('IBF_freezeDeduction') },				//冻结扣分
	        { name: CONFIGNAME.get('IBF_finallyIntegralTime') },			//最后积分时间
	        { name: CONFIGNAME.get('IBF_integralExpirationTime') } ]		//积分到期时间
});
/**.
 * <p>
// * 理赔APP MODEL<br/>
 * <p>
 * @returns {RecompenseApp}
 */
Ext.define('RecompenseApp', {
    extend: 'Ext.data.Model',
    fields : [{
    	//运单对象*
    	name : CONFIGNAME.get('recompense_waybill')
	},{
    	//理赔类型
    	name : CONFIGNAME.get('recompense_recompenseType')
	},{
    	//理赔方式
    	name : CONFIGNAME.get('recompense_recompenseMethod')
	},{
    	//实际理赔金额
    	name : CONFIGNAME.get('recompense_realAmount'),
    	type:'float'
	},{
    	//出险类型
    	name : CONFIGNAME.get('recompense_insurType')
	},{
    	//状态
    	name : CONFIGNAME.get('recompense_status')
	},{
    	//运单对象
    	name : CONFIGNAME.get('recompense_waybill')
	},{
		//运单号/差错编号
		name :  CONFIGNAME.get('recompense_waybillNumber')
    },{
		//始发站
		name : CONFIGNAME.get('recompense_receiveDept')
	}]
});

/**.
 * <p>
 * 计划或日程///维护记录MODEL<br/>
 * <p>
 * @returns {ReturnVisit}
 */
Ext.define('ReturnVisit', {
    extend: 'Ext.data.Model',
    fields : [
          {
		    //计划制定人
			name :  CONFIGNAME.get('returnVisit_formulater')
		},{
			//执行人
				name :  CONFIGNAME.get('returnVisit_userName')
			},{
				//联系人姓名
				name :  CONFIGNAME.get('returnVisit_linkName')
			},{
				//手机号码
				name :  CONFIGNAME.get('returnVisit_linkManMobile')
			},{
				//固定电话
				name :  CONFIGNAME.get('returnVisit_linkManPhone')
			},{
				//维护主题
				name :  CONFIGNAME.get('returnVisit_theme')
			},{
				//维护时间 
				name :  CONFIGNAME.get('returnVisit_schedule')
			},{
				//需求分类
				name :  CONFIGNAME.get('returnVisit_needType')
			},{
				//需求及问题(客户意见)
				name :  CONFIGNAME.get('returnVisit_problem')
			},{
				//需求及问题解决办法
				name :  CONFIGNAME.get('returnVisit_solution')
			}]
});

/**.
 * <p>
 * 投诉MODEL<br/>
 * <p>
 * @returns {Complaint}
 */
Ext.define('Complaint', {
    extend: 'Ext.data.Model',
    fields : [
          {
		//处理编号
			name :  CONFIGNAME.get('complaint_dealbill')
		},{
		//运单号
			name :  CONFIGNAME.get('complaint_bill')
		},{
			//投诉人
			name :  CONFIGNAME.get('complaint_compman')
		},{
			//联系方式
			name :  CONFIGNAME.get('complaint_tel')
		},{
			//投诉时间（上报时间）
			name :  CONFIGNAME.get('complaint_reporttime')
		},{
			//投诉类型
			name :  CONFIGNAME.get('complaint_reporttype')
		},{
			//处理状态
			name :  CONFIGNAME.get('complaint_prostatus')
		},{
			//解决情况
			name :  CONFIGNAME.get('complaint_dealstatus')
		},{
			//客户满意度
			name :  CONFIGNAME.get('complaint_cussatisfaction')
		}]
});


/**.
 * <p>
 * 积分MODEL<br/>
 * <p>
 * @returns {IntegralModel}
 */

Ext.define('IntegralFormModel', {			// form
    extend: 'Ext.data.Model',
    fields : [  { name: CONFIGNAME.get('I_memberIntegral') },				//客户总积分
				{ name: CONFIGNAME.get('I_customerAvailablePoints') },		//'客户可用积分
				{ name: CONFIGNAME.get('I_AllowedContactExchangePoints') }]//是否允许联系人兑换积分
});

Ext.define('IntegralModel', {				//Grid 详细form
    extend: 'Ext.data.Model',
    fields : [  
                { name:CONFIGNAME.get('IF_contact') },					// 联系人对象
				{ name:CONFIGNAME.get('IF_contactName') },					// 联系人名称
			    { name: CONFIGNAME.get('IF_contactCoding') },				// 联系人编码
			    { name: CONFIGNAME.get('IF_availablePoints') },				//本期可用积分
			    { name: CONFIGNAME.get('IF_thisIssueHasBeenWithTheIntegral') },//本期已用积分
			    { name: CONFIGNAME.get('IF_currentAccumulatedPoints') },	//本期累计积分
			    { name: CONFIGNAME.get('IF_cumulativeTotalScore') },		//累计积分
			    { name: CONFIGNAME.get('IF_totalAlreadyIntegral') },		//累计已用积分
			    { name: CONFIGNAME.get('IF_finalIntegrationTime') }]		//最后积分时间			
});

/**.
 * <p>
 * 合同MODEL<br/>
 * <p>
 * @returns {ContractModel}
 */
Ext.define('ContractModel', {
    extend: 'Ext.data.Model',
    fields : [
          {	//id
			name :'id'
		},
		{	name :  CONFIGNAME.get('contract_deptName') },					//1.部门
		{	name :  CONFIGNAME.get('contract_beforeContractNum') },			//2.原合同编号
		{	name :  CONFIGNAME.get('contract_contractSubject') },			//3.合同主体
		{	name :  CONFIGNAME.get('contract_custCompany') },				//4.客户全称
		{	name :  CONFIGNAME.get('contract_regicapital') },				//5.注册资金

		{	name :  CONFIGNAME.get('contract_contractStatus') },			//6.合同状态
		{	name :  CONFIGNAME.get('contract_goodsName') },					//7.走货名称
		{	name :  CONFIGNAME.get('contract_agentgathRate') },				//代收费率
		{	name :  CONFIGNAME.get('contract_postageDiscounts') },			//运费折扣
		{	name :  CONFIGNAME.get('contract_linkManPhone') },				//8.固定电话
		{ //9.审核编号
			name :  CONFIGNAME.get('contract_contractNum')
		},{
			//10.付款方式
			name :  CONFIGNAME.get('contract_payWay')
		},{
			//11.优惠类型
			name :  CONFIGNAME.get('contract_preferentialType')
		},{
			//12.申请欠款额度改为结算限额
			name :  CONFIGNAME.get('contract_arrearaMount')
		},{
			//13.协议联系人
			name :  CONFIGNAME.get('contract_linkManName')
		},{
			//14.联系人手机
			name :  CONFIGNAME.get('contract_linkManMobile')
		},{
			//15.对账日期
			name :  CONFIGNAME.get('contract_reconDate')
		},{
			//16.开发票日期
			name :  CONFIGNAME.get('contract_invoicDate')
		},{
			//17.结款日期
			name :  CONFIGNAME.get('contract_resultDates')
		},{
			//18.合同起始日期
			name :  CONFIGNAME.get('contract_contractBeginDate')
		},{
			//19.合同到期日期
			name :  CONFIGNAME.get('contract_contractendDate')
		},{
			//20.申请事由
			name :  CONFIGNAME.get('contract_application')
		},{
			//21.创建人
			name :  CONFIGNAME.get('contract_createUser')
		},{
			//22.创建部门
			name :  CONFIGNAME.get('contract_createDeptName')
		},{
			//23.创建时间
			name :  CONFIGNAME.get('contract_createDate'),
			convert:function(value){
				return new Date(value).format('yyyy-MM-dd hh:mm:ss');
			}
		},{
			//24.合同优惠信息
			name :  CONFIGNAME.get('contract_preferential')
		},{
			//25.保价费率
			name :  CONFIGNAME.get('contract_insuredPriceRate')
		},{
			//接货费率
			name :  CONFIGNAME.get('contract_receivePriceRate')
		},{
			//送货费率
			name :  CONFIGNAME.get('contract_deliveryPriceRate')
		},{
			//近三个月发货金额
			name:CONFIGNAME.get("contract_threeMonthAmount")
		},{
			//19.最后修改人
			name:CONFIGNAME.get("contract_lastModifyUser")
		},{
			//20最后修改部门
			name:CONFIGNAME.get("contract_lastModifyDept")
		},{
			//21.最后修改时间
			name:CONFIGNAME.get("contract_lastModifyDate")
		}]
});
/**.
 * <p>
 * 财务信息MODEL<br/>
 * <p>
 * @returns {AccountModel}
 */
Ext.define('AccountModel', {
    extend: 'Ext.data.Model',
    fields : [
          {
      			//id
			name :'id'
		},{
      			//开户行
			name :  CONFIGNAME.get('account_bank')
		},{
			//支行名称
			name :  CONFIGNAME.get('account_subBankname')
		},{
			//是否默认账号
			name :  CONFIGNAME.get('account_isdefaultaccount')
		},{
			//银行账号
			name :  CONFIGNAME.get('account_bankAccount')
		},{
			//开户人姓名
			name :  CONFIGNAME.get('account_countName')
		},{
			//账号与客户关系
			name :  CONFIGNAME.get('account_relation')
		},{
			//开户省
			name :  CONFIGNAME.get('account_bankProvinceId')
		},{
			//账户性质(业务)
			name :  CONFIGNAME.get('account_accountNature')
		},{
			//账户用途（业务）
			name :  CONFIGNAME.get('account_accountUse')
		},{
			//开户城市
			name :  CONFIGNAME.get('account_bankCityId')
		},{
			//财务联系人姓名
			name :  CONFIGNAME.get('account_financeLinkman')
		},{
			//联系手机
			name :  CONFIGNAME.get('account_linkManMobile')
		},{
			//固定电话
			name :  CONFIGNAME.get('account_linkManPhone')
		},{
			//创建人
			name :  CONFIGNAME.get('account_createUser')
		},{
			//创建时间
			name :  CONFIGNAME.get('account_createDate')
		},{
			//创建部门
			name :  CONFIGNAME.get('account_createDeptId')
		}]
});
/**.
 * <p>
 * 运营分析MODEL<br/>
 * <p>
 * @returns {OperationAnalysisModel}
 */
Ext.define('OperationAnalysisModel', {
    extend: 'Ext.data.Model',
    fields : [
          {
      			//ID
			name :'ID'
		},{
			// 年月
			name :'yearMonth'
		},{
			// 出发货量
			name :'leaveMoney'
			,type:'float'
		},{
			// 到达货量
			name :'arriveMoney'
				,type:'float'
		},{
			// 出发票数
			name :'leaveBill'
				,type:'float'
		},{
			// 到达票数
			name :'arriveBill'
				,type:'float'
		},{
			// 优势业务占比
			name :'advantageBusiRate'
				,type:'float'
		},{
			// 汽运占比
			name :'motorRate'
				,type:'float'
		},{
			// 发货总金额
			name :'leaveMoney'
				,type:'float'
		},{
			// 保险费
			name :'insurance'
				,type:'float'
		},{
			// 保营比
			name :'insuranceRate'
				,type:'float'
		},{
			// 理赔金额
			name :'claimsMoney'
				,type:'float'
		},{
			// 包装费
			name :'fpackage'
				,type:'float'
		},{
			// 包营比
			name :'packageRate'
				,type:'float'
		},{
			// 代收货款
			name :'agentreceivePay'
				,type:'float'
		},{
			// 代收比
			name :'agentreceivepayRate'
				,type:'float'
		},{
			// 网上营业厅票数占比
			name :'wtvoteRate'
				,type:'float'
		},{
			// 呼叫中心票数占比
			name :'callvoteRate'
				,type:'float'
		},{
			// 营业厅票数占比
			name :'yytvoteRate'
				,type:'float'
		},{
			// 第三方渠道票数占比
			name :'othervoteRate'
				,type:'float'
		},{
			// 到货金额
			name :'arriveMoney'
				,type:'float'
		}]
});