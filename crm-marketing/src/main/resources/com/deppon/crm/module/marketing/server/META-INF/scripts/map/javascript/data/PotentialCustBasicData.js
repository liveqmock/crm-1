/**
*潜在客户编辑和查询共用Data层
*/

//判断是否导入测试数据
(function() {
	var potentialCustBasicTest = "../scripts/potentialcust/test/PotentialCustBasicDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + potentialCustBasicTest + '"></script>');
	}
})();
//当前登录用户所属部门所在城市
CurrentCity = {
	id:'',
	name:''
};

/**
* 数据字典模型
*/
Ext.define('DataDictionaryModel',{
	extend:'Ext.data.Model',
	fields:['code','codeDesc']
});

/**
* 客户来源Store
*/
Ext.define('CustSourceStore',{
	extend:'Ext.data.Store',
	model:'DataDictionaryModel',
	data:null
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
 * 潜在客户Model
 */
Ext.define('PotentialCustModel', {
	extend : 'Ext.data.Model',
	fields : [ 'id', 'custName', 'trade', 'custbase', 'linkManName',
			'linkManMobile', 'linkManPhone', 'post', 'bussinesState', 'city','cityName',
			'address', 'recentcoop', 'coopIntention', 'volumePotential',
			'recentGoods', 'custNeed', 'createUser', 
			{name:'createDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:null}, 
			{name:'modifyDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:null},
			'modifyUser', {
				name : 'reviTimes',
				type : 'int'
			}, {
				name : 'finalreviTime',
				type : 'date',
				convert: DpUtil.changeLongToDate,
				defaultValue:null
			}, 'developState', {
				name : 'programmeTime',
				type : 'date',
				convert: DpUtil.changeLongToDate,
				defaultValue:null
			},'custLabels'
			]
});

/**
 * 潜在客户Store
 */
Ext.define('PotentialCustStore',{
	extend:'Ext.data.Store',
	model:'PotentialCustModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		api:{
			read:'searchPotentialCustomerList.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'potentialCustlist',
			totalProperty:'totalCount'
		},
		writer:{
			type:'json',
			root:'potentialCust'
		}
	}
});


Ext.define('PotentialCustBasicData',{
	custSourceStore:null,
	industryStore:null,
	coopertationCompanyStore:null,
	coopertationIntensionStore:null,
	goodsPotentialStore:null,
	businessOpportunityStore:null,
	cityStore:null,
	//客户来源Store
	getCustSourceStore:function(){
		if(this.custSourceStore==null){
			this.custSourceStore = Ext.create('CustSourceStore',{
				data : DataDictionary.CUST_SOURCE
			});			
		}
		return this.custSourceStore;
	},
	//行业Store
	getIndustryStore:function(){
		if(this.industryStore==null){
			this.industryStore = Ext.create('IndustryStore',{
				data : DataDictionary.TRADE
			});
		}
		return this.industryStore;
	},
	//最近合作物流公司Store
	getCoopertationCompanyStore:function(){
		if(this.coopertationCompanyStore==null){
			this.coopertationCompanyStore = Ext.create('CoopertationCompanyStore',{
				data : DataDictionary.COOPERATION_LOGISTICS
			});			
		}
		return this.coopertationCompanyStore;
	},
	//合作意向Store
	getCoopertationIntensionStore:function(){
		if(this.coopertationIntensionStore==null){
			this.coopertationIntensionStore = Ext.create('CoopertationIntensionStore',{
				data : DataDictionary.COOPERATION_INTENTION
			});			
		}
		return this.coopertationIntensionStore;
	},
	//货量潜力Store
	getGoodsPotentialStore:function(){
		if(this.goodsPotentialStore==null){
			this.goodsPotentialStore = Ext.create('GoodsPotentialStore',{
				data : DataDictionary.CARGO_POTENTIAL
			});			
		}
		return this.goodsPotentialStore;
	},
	//商机状态Store
	getBusinessOpportunityStore:function(){
		if(this.businessOpportunityStore==null){
			this.businessOpportunityStore = Ext.create('BusinessOpportunityStore',{
				data : DataDictionary.BUSINESS_STATUS
			});			
		}
		return this.businessOpportunityStore;
	},
	//潜在客户Store
	getPotentialCustStore:function(){
		return this.potentialCustStore;
	},
	//初始化潜在客户Store
	initialPotentialCustStore:function(beforeLoadPotentialFn){
		if(this.potentialCustStore == null){
			this.potentialCustStore = Ext.create('PotentialCustStore',{
				listeners:{
					beforeload:beforeLoadPotentialFn
				}
			});
		}
		return this.potentialCustStore;
	},
	//获得当前登陆用户所在城市
	acquireDeptCity:function(params,successFn,failFn){
		DpUtil.requestJsonAjaxSync('../common/acquireDeptCity.action',params,successFn,failFn);
	}
});
