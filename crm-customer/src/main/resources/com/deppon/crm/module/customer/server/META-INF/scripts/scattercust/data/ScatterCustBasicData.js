/**
*散客编辑和查询共用Data层
*/

//判断是否导入测试数据
(function() {
	var scatterCustBasicTest = "../scripts/scattercust/test/ScatterCustBasicDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + scatterCustBasicTest + '"></script>');
	}
})();

/**
* 数据字典模型
*/
Ext.define('DataDictionaryModel',{
	extend:'Ext.data.Model',
	fields:['code','codeDesc']
});

/**
* 行业Store
*/
Ext.define('IndustryStore',{
	extend:'Ext.data.Store',
	model:'DataDictionaryModel',
	data:null
});
/**
* 最近合作物流公司Store
*/
Ext.define('CoopertationCompanyStore',{
	extend:'Ext.data.Store',
	model:'DataDictionaryModel',
	data:null
});

/**
* 合作意向Store
*/
Ext.define('CoopertationIntensionStore',{
	extend:'Ext.data.Store',
	model:'DataDictionaryModel',
	data:null
});

/**
* 货量潜力Store
*/
Ext.define('GoodsPotentialStore',{
	extend:'Ext.data.Store',
	model:'DataDictionaryModel',
	data:null
});

/**
* 商机状态Store
*/
Ext.define('BusinessOpportunityStore',{
	extend:'Ext.data.Store',
	model:'DataDictionaryModel',
	data:null
});

/**
* 公司规模Store
*/
Ext.define('CompanySizeStore',{
	extend:'Ext.data.Store',
	model:'DataDictionaryModel',
	data:null
});


/**
* 公司性质Store
*/
Ext.define('CompanyNatureStore',{
	extend:'Ext.data.Store',
	model:'DataDictionaryModel',
	data:null
});

/**
* 客户属性Store
*/
Ext.define('CustPropertyStore',{
	extend:'Ext.data.Store',
	model:'DataDictionaryModel',
	data:null
});


/**
* 客户性质Store
*/
Ext.define('CustNatureStore',{
	extend:'Ext.data.Store',
	model:'DataDictionaryModel',
	data:null
});

/**
 * 业务类别Store
 */
Ext.define('BusinessTypeStore',{
	extend:'Ext.data.Store',
	model:'DataDictionaryModel',
	data:null
});
/**
 * 散客Model
 */
Ext.define('ScatterCustModel', {
	extend : 'Ext.data.Model',
	fields:[
		 {name:'id',type:'string'},
		// 客户姓名
		 {name:'custName',type:'string'},
		 // 行业
		 {name:'trade',type:'string'},
		 // 二级行业
		 {name:'secondTrade',type:'string'},
		 // 客户来源
		 {name:'custbase',type:'string'},
		 // 会员编码
		 {name:'memberNo',type:'string'},
		 // 联系人姓名
		 {name:'linkManName',type:'string'},
		 // 联系人手机
		 {name:'linkManMobile',type:'string'},
		 // 联系人固话
		 {name:'linkManPhone',type:'string'},
		 // 职位
		 {name:'post',type:'string'},
		 // 商机状态
		 {name:'bussinesState',type:'string'},
		 // 城市Id
		 {name:'city',type:'string'},
		 //城市名称
		 {name:'cityName',type:'string'},
		 // 地址
		 {name:'address',type:'string'},
		 // 最近合作物流公司
		 {name:'recentcoop',type:'string'},
		 // 合作意向
		 {name:'coopIntention',type:'string'},
		 // 货量潜力
		 {name:'volumePotential',type:'string'},
		 // 走货情况(最近合作物流公司)
		 {name:'recentGoods',type:'string'},
		 // 客户需求
		 {name:'custNeed',type:'string'},
		 // 回访次数
		 {name:'reviTimes',type:'int'},
		 // 最后回访时间
		 {name:'finalreviTime',type:'date',convert: DpUtil.changeLongToDate,defaultValue:null},
		 // 开发状态
		 {name:'developState',type:'string'},
		 // 是否失效 0为启用，1为失效，默认为0
		 {name:'isCancel',type:'int'},
		 // 日程时间
		 {name:'programmeTime',type:'date',convert: DpUtil.changeLongToDate,defaultValue:null},
		 // 潜客信息状态
		 {name:'infoState',type:'string'},
		 // 所属部门
		 {name:'deptId',type:'string'},
		 // 所属部门
		 {name:'deptName',type:'string'},
		 // 会员ID
		 {name:'memberId',type:'string'},
		 // 散客ID
		 {name:'scatterId',type:'string'},
		 // 客户类型  潜客：PC_CUSTOMER，散客：SC_CUSTOMER，潜散客：PC_SC
		 {name:'custType',type:'string'},
		 //增加版本控制
		 {name:'version',type:'string'},
		 //是否锁定,如果散客或者潜在客户有存在审批中的工作流，则isLock为1，否则为0
		 //pojo该属性为isLock
		 {name:'lock',type:'boolean'},
	     // 身份证号
	     {name:'idNumber',type:'string'},
	     // 公司规模
	     {name:'companySize',type:'string'},
	     // 公司性质
	     {name:'companyNature',type:'string'},
	     // 税务登记号
	     {name:'taxregistNo',type:'string'},
	     // 客户性质
	     {name:'custProperty',type:'string'},
	     // 客户属性
	     {name:'custNature',type:'string'},
	     // 备注
	     {name:'remark',type:'string'},
	     // 出发部门
	     {name:'leaDeptId',type:'string'},
	     // 出发部门名称
	     {name:'leaDeptName',type:'string'},
	     // 到达部门名称
	     {name:'arrDeptName',type:'string'},
	     // 到达部门
	     {name:'arrDeptId',type:'string'},
	     // 维护人
	     {name:'prehuMan',type:'string'},
	     //散客类型：普通散客"ORDINARY_SCATTER" 临代散客"FOSS_SCATTER"
	     {name:'scatterCustType',type:'string'},
	     //散客账号信息
	     {name:'accounts',defaultValue:null},
	     //临欠额度
	     {name:'velocityAmount',type:'number'},
	     //工作流号
	     {name:'scatterOperatorLogs',defaultValue:null},
	     //是否财务完结
	     {name:'finOver',type:'boolean'},
	     //潜客来源
	     {name:'potenSource',type:'string'},
	     //散客编码
	     {name:'scatterNum',type:'string'},
	      //业务类别
	     {name:'businessType',type:'string'},
		{name:'createUser',type:'string'},
		{name:'createDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:null}, 
		{name:'modifyDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:null},
		{name:'modifyUser',type:'string'},'custLabels'
		],
	hasMany:{
		model:'TransactionModel',
		name:'transactions',
		associationKey : 'transactions',
		primaryKey:'id',
		foreignKey:'custId'
	}
});
/**
 * 散客账号Model
 * @author 唐亮
 * @date 2012.11.2
 */
Ext.define('ScatterCustAccountModel',{
	extend:'Ext.data.Model',
	fields:[
	     // 开户行
	     {name:'bank',type:'string'},
	     // 开户行ID
	     {name:'bankId',type:'string'},
	     // 支行名称
	     {name:'subBankname',type:'string'},
	     // 支行Id
	     {name:'subBankId',type:'string'},
	     // 是否默认账户 1为是，0为否
	     {name:'isDefaultAccount',type:'string'},
	     // 银行账号
	     {name:'bankAccount',type:'string'},
	     // 开户姓名
	     {name:'accountName',type:'string'},
	     // 账号与客户关系
	     {name:'relation',type:'string'},
	     // 开户省份
	     {name:'bankProvinceId',type:'string'}, 
	     // 开户省份名称
	     {name:'bankProvinceName',type:'string'},
	     // 账户性质
	     {name:'accountNature',type:'string'},
	     // 账户用途
	     {name:'accountUse',type:'string'},
	     // 开户城市
	     {name:'bankCityId',type:'string'},
	     // 开户城市名称
	     {name:'bankCityName',type:'string'},
	     // 财务联系人姓名
	     {name:'financeLinkmanName',type:'string'},
	     // 联系手机
	     {name:'linkmanMobile',type:'string'},
	     // 联系人固话
	     {name:'linkmanPhone',type:'string'},
	     // 状态 正常：0；  审批中：1  ；无效 ：2；
	     {name:'status',type:'string'},
	     //散客ID
	     {name:'scatterId',type:'string'}, 
		{name:'createUser',type:'string'},
		{name:'createDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:null}, 
		{name:'modifyDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:null},
		{name:'modifyUser',type:'string'}
	    ]
});

/**
 * 交易信息
 */
Ext.define('TransactionModel',{
	extend:'Ext.data.Model',
	fields:['id','custId','monthKind',{name:'totalBillCount',type:'int'},
	        {name:'recTotalBillCount',type:'int'},
	        'totalWeight','recTotalWeight','totalAmount','recTotalAmount',
	        {name:'exTotalBillCount',type:'int'},{name:'exRecTotalBillCount',type:'int'},
	        'exTotalWeight','exRecTotalWeight','exTotalAmount','exRecTotalAmount']
});
/**
 * 散客账号store
 */
Ext.define('ScatterCustAccountStore',{
	extend:'Ext.data.Store',
	model:'ScatterCustAccountModel',
	pageSize:15,
	proxy:{
		type:'ajax',
		api:{
			read:'searchScatterCustAccountList.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'ScatterCustAccountList',
			totalProperty:'totalCount'
		}
	}
});

/**
 * 散客Store
 */
Ext.define('ScatterCustStore',{
	extend:'Ext.data.Store',
	model:'ScatterCustModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		api:{
			read:'searchScatterCustList.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'scatterCustlist',
			totalProperty:'totalCount'
		}
	}
});


/**
 * 交易信息Store
 */
Ext.define('TransactionStore',{
	extend:'Ext.data.Store',
	model:'TransactionModel',
	proxy:{
		type:'ajax',
		actionMethods:'POST',
		url:'searchTransaction.action',
		reader:{
			type:'json',
			root:'transactions'
		}
	}
});


Ext.define('ScatterCustBasicData',{
	industryStore:null,
	coopertationCompanyStore:null,
	coopertationIntensionStore:null,
	goodsPotentialStore:null,
	businessOpportunityStore:null,
	cityStore:null,
	scatterCustStore:null,
	companySizeStore:null,
	companyNatureStore:null,
	custPropertyStore:null,
	custNatureStore:null,
	businessTypeStore:null,
	transactionStore:null,
	custSourceStore:null,
	accountNatureSC:null,
	scatterCustTypeStore:null,
	addCustAccountStore:null,
	beforeModifyAccountStore:null,
	afterModifyAccountStore:null,
	deleteAccountStore:null,
	booleanStore:null,
	getBooleanStore:function(){
		if(this.booleanStore == null){
			this.booleanStore = Ext.create('Ext.data.Store',{
				fields:['name','value'],
				data:[{'name':'是','value':1},
				      {'name':'否','value':0}]
			});
		}
		return this.booleanStore;
	},
	//新增账号store
	getAddAccountStore:function(){
		if(this.addCustAccountStore==null){
			this.addCustAccountStore = Ext.create('ScatterCustAccountStore');
		}
		return this.addCustAccountStore;
	},
	//账号修改前store
	getBeforeModifyAccountStore:function(){
		if(this.beforeModifyAccountStore==null){
			this.beforeModifyAccountStore = Ext.create('ScatterCustAccountStore');
		}
		return this.beforeModifyAccountStore;
	},
	//账号修改后store
	getAfterModifyAccountStore:function(){
		if(this.afterModifyAccountStore==null){
			this.afterModifyAccountStore = Ext.create('ScatterCustAccountStore');
		}
		return this.afterModifyAccountStore;
	},
	//删除账号store
	getDeleteAccountStore:function(){
		if(this.deleteAccountStore==null){
			this.deleteAccountStore = Ext.create('ScatterCustAccountStore');
		}
		return this.deleteAccountStore;
	},
	//散客 账号性质Store
	getAccountNatureSC:function(){
		if(this.accountNatureSC==null){
			this.accountNatureSC = getDataDictionaryByName(DataDictionary,'ACCOUNT_NATURE_SC');			
		}
		return this.accountNatureSC;
	},
	//散客类型Store
	getScatterCustTypeStore:function(){
		if(this.scatterCustTypeStore==null){
			this.scatterCustTypeStore = getDataDictionaryByName(DataDictionary,'SCATTERCUST_TYPE');
		}
		return this.scatterCustTypeStore;
	},
	//潜客客户来源Store
	getCustSourceStore:function(){
		if(this.custSourceStore==null){
			this.custSourceStore = getDataDictionaryByName(DataDictionary,'CUST_SOURCE');
		}
		return this.custSourceStore;
	},
	//散客客户来源Store
	sCSourceStore:null,
	getSCCourceStore:function(){
		if(this.sCSourceStore==null){
			this.sCSourceStore = getDataDictionaryByName(DataDictionary,'PREFERENCE_CHANNEL');
		}
		return this.sCSourceStore;
	},
	//行业Store
	getIndustryStore:function(){
		if(this.industryStore==null){
			this.industryStore = getDataDictionaryByName(DataDictionary,'TRADE');
		}
		return this.industryStore;
	},
	//最近合作物流公司Store
	getCoopertationCompanyStore:function(){
		if(this.coopertationCompanyStore==null){
			this.coopertationCompanyStore = getDataDictionaryByName(DataDictionary,'COOPERATION_LOGISTICS');
		}
		return this.coopertationCompanyStore;
	},
	//合作意向Store
	getCoopertationIntensionStore:function(){
		if(this.coopertationIntensionStore==null){
			this.coopertationIntensionStore = getDataDictionaryByName(DataDictionary,'COOPERATION_INTENTION');
		}
		return this.coopertationIntensionStore;
	},
	//货量潜力Store
	getGoodsPotentialStore:function(){
		if(this.goodsPotentialStore==null){
			this.goodsPotentialStore = getDataDictionaryByName(DataDictionary,'CARGO_POTENTIAL');
		}
		return this.goodsPotentialStore;
	},
	//商机状态Store
	getBusinessOpportunityStore:function(){
		if(this.businessOpportunityStore==null){
			this.businessOpportunityStore = getDataDictionaryByName(DataDictionary,'BUSINESS_STATUS');
		}
		return this.businessOpportunityStore;
	},
	//公司规模Store
	getCompanySizeStore:function(){
		if(this.companySizeStore==null){
			this.companySizeStore = getDataDictionaryByName(DataDictionary,'FIRM_SIZE');
		}
		return this.companySizeStore;
	},
	//公司性质Store
	getCompanyNatureStore:function(){
		if(this.companyNatureStore==null){
			this.companyNatureStore = getDataDictionaryByName(DataDictionary,'COMP_NATURE');
		}
		return this.companyNatureStore;
	},
	//客户属性Store
	getCustPropertyStore:function(){
		if(this.custPropertyStore==null){
			this.custPropertyStore = getDataDictionaryByName(DataDictionary,'CUSTOMER_NATURE');
		}
		return this.custPropertyStore;
	},
	//客户性质Store
	getCustNatureStore:function(){
		if(this.custNatureStore==null){
			this.custNatureStore = getDataDictionaryByName(DataDictionary,'CUSTOMER_TYPE');
		}
		return this.custNatureStore;
	},
	//业务类别Store
	getBusinessTypeStore:function(){
		if(this.businessTypeStore==null){
			this.businessTypeStore = getDataDictionaryByName(DataDictionary,'BUSINESS_TYPE');
		}
		return this.businessTypeStore;
	},
	//散客Store
	getScatterCustStore:function(){
		return this.scatterCustStore;
	},
	//初始化散客Store
	initialScatterCustStore:function(beforeLoadScatterFn){
		if(this.scatterCustStore == null){
			if(beforeLoadScatterFn != null){
				this.scatterCustStore = Ext.create('ScatterCustStore',{
					listeners:{
						beforeload:beforeLoadScatterFn
					}
				});
			}else{
				this.scatterCustStore = Ext.create('ScatterCustStore');
			}
		}
		return this.scatterCustStore;
	},
	//初始化散客交易信息store
	initialTransactionStore:function(beforeLoadTransactionFn){
		if(this.transactionStore == null){
			if(beforeLoadTransactionFn != null){
				this.transactionStore = Ext.create('TransactionStore',{
					listeners:{
						beforeload:beforeLoadTransactionFn
					}
				});
			}else{
				this.transactionStore = Ext.create('TransactionStore');
			}
		}
		return this.transactionStore;
	},
	//获得散客交易信息store
	getTransactionStore:function(){
		if(this.transactionStore == null){
			this.transactionStore = Ext.create('TransactionStore');
		}
		return this.transactionStore;
	},
	scatterCustAccountStore:null,
	//散客账户store
	getScatterCustAccountStore:function(){
		if(this.scatterCustAccountStore == null){
			this.scatterCustAccountStore = Ext.create('ScatterCustAccountStore');
		}
		return this.scatterCustAccountStore;
	}
});
