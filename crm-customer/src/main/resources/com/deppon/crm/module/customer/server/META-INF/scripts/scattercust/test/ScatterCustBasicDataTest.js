/**
*散客编辑和查询共用DataTest层
*/


/**
 * 交易信息Store
 */
Ext.define('TransactionTestStore',{
	extend:'Ext.data.Store',
	model:'TransactionModel',
	proxy:{
		type:'memory'
	}
});

Ext.define('ScatterCustBasicDataTest',{
	//行业Store
	getIndustryStore:function(){
		return Ext.create('IndustryStore',{
				data:[{code:'1',codeDesc:'服装纺织类'},
				      {code:'2',codeDesc:'工业电子类家具类'},
				      {code:'3',codeDesc:'生活电器类'},
				      {code:'4',codeDesc:'生活电子类'},
				      {code:'5',codeDesc:'生活用品化妆品类'},
				      {code:'6',codeDesc:'食品药品类'},
				      {code:'7',codeDesc:'塑料建材类'},
				      {code:'8',codeDesc:'五金仪表类'},
				      {code:'9',codeDesc:'易碎易损类'},
				      {code:'10',codeDesc:'其它类'}]});
	},
	//最近合作物流公司Store
	getCoopertationCompanyStore:function(){
		return Ext.create('CoopertationCompanyStore',{
				data:[{code:'1',codeDesc:'华语'},
				      {code:'2',codeDesc:'佳吉'},
				      {code:'3',codeDesc:'顺丰'},
				      {code:'4',codeDesc:'新邦'},
				      {code:'5',codeDesc:'其他'}]});
	},
	//合作意向Store
	getCoopertationIntensionStore:function(){
		return Ext.create('CoopertationIntensionStore',{
				data:[{code:'1',codeDesc:'高'},
				      {code:'2',codeDesc:'中'},
				      {code:'3',codeDesc:'低'}]});
	},
	//货量潜力Store
	getGoodsPotentialStore:function(){
		return  Ext.create('GoodsPotentialStore',{
				data:[{code:'1',codeDesc:'<600元/月'},
				      {code:'2',codeDesc:'600-3000元/月'},
				      {code:'3',codeDesc:'3000-10000元/月'},
				      {code:'4',codeDesc:'>10000元/月'}]});
	},
	//商机状态Store
	getBusinessOpportunityStore:function(){
		return Ext.create('BusinessOpportunityStore',{
				data:[{code:'1',codeDesc:'定位目标'},
				      {code:'2',codeDesc:'接触目标'},
				      {code:'3',codeDesc:'开始发货'},
				      {code:'4',codeDesc:'升级会员'}]});
	},
	//公司规模Store
	getCompanySizeStore:function(){
		return Ext.create('CompanySizeStore',{
			data:[{code:'A',codeDesc:'50人以下'},
			      {code:'B',codeDesc:'50-99人'},
			      {code:'C',codeDesc:'100-499人'},
			      {code:'D',codeDesc:'500-999人'},
			      {code:'E',codeDesc:'1000人以上'}]});
	},
	//公司性质Store
	getCompanyNatureStore:function(){
		return Ext.create('CompanyNatureStore',{
			data:[{code:'LTD',codeDesc:'有限责任公司'},
			      {code:'CO_LTD',codeDesc:'股份有限公司'},
			      {code:'STATE_OWNED',codeDesc:'国有独资公司'},
			      {code:'SOLE_PROPRIETORSHIP',codeDesc:'个人独资企业'},
			      {code:'PARTNERSHIP',codeDesc:'合伙企业'},
			      {code:'INDIVIDUAL_BUSINESSES',codeDesc:'个体工商户'},
			      {code:'FOREIGN_INVESTED',codeDesc:'外商投资企业'},
			      {code:'PRIVATE_SECTOR',codeDesc:'私营企业'}]});
	},
	//客户属性Store
	getCustPropertyStore:function(){
		return Ext.create('CustPropertyStore',{
			data:[{code:'ARRIVE_CUSTOMER',codeDesc:'到达'},
			      {code:'LEAVE_CUSTOMER',codeDesc:'出发'},
			      {code:'REACHE_DEPARTURE',codeDesc:'出发到达'}]});
	},
	//客户性质Store
	getCustNatureStore:function(){
		return Ext.create('CustNatureStore',{
			data:[{code:'INDIVIDUAL_CUSTOM',codeDesc:'企业客户'},
			      {code:'ENTERPRISE_CUSTOM',codeDesc:'个人客户'}]});
	},
	//获得散客交易信息store
	getTransactionStore:function(){
		return Ext.create('TransactionTestStore',{
			data:[{'id':'1','custId':'1','monthKind':'0','totalBillCount':20,'recTotalBillCount':0,
		           'totalWeight':200.21,'recTotalWeight':0.0,'totalAmount':100.25,'recTotalAmount':0.0},
		           {'id':'2','custId':'1','monthKind':'1','totalBillCount':20,'recTotalBillCount':0,
			           'totalWeight':200.21,'recTotalWeight':0.0,'totalAmount':100.25,'recTotalAmount':0.0}]
		});
	},
	//初始化散客交易信息store
	initialTransactionStore:function(beforeLoadTransactionFn){
		return this.getTransactionStore();
	}
});

