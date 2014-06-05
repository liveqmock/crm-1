/**
*潜在客户编辑和查询共用DataTest层
*/
Ext.define('PotentialCustBasicDataTest',{
	//客户来源Store
	getCustSourceStore:function(){
		return Ext.create('CustSourceStore',{
				data:[{id:'1',custSource:'陌生来电'},
				      {id:'2',custSource:'会展'},
				      {id:'3',custSource:'五公里'},
				      {id:'4',custSource:'派单'},
				      {id:'5',custSource:'黄页'},
				      {id:'6',custSource:'阿里巴巴'},
				      {id:'7',custSource:'到达散客'},
				      {id:'8',custSource:'订单潜客'},
				      {id:'9',custSource:'其他'}]});
	},
	//行业Store
	getIndustryStore:function(){
		return Ext.create('IndustryStore',{
				data:[{id:'1',industry:'服装纺织类'},
				      {id:'2',industry:'工业电子类家具类'},
				      {id:'3',industry:'生活电器类'},
				      {id:'4',industry:'生活电子类'},
				      {id:'5',industry:'生活用品化妆品类'},
				      {id:'6',industry:'食品药品类'},
				      {id:'7',industry:'塑料建材类'},
				      {id:'8',industry:'五金仪表类'},
				      {id:'9',industry:'易碎易损类'},
				      {id:'10',industry:'其它类'}]});
	},
	//最近合作物流公司Store
	getCoopertationCompanyStore:function(){
		return Ext.create('CoopertationCompanyStore',{
				data:[{id:'1',companyName:'华语'},
				      {id:'2',companyName:'佳吉'},
				      {id:'3',companyName:'顺丰'},
				      {id:'4',companyName:'新邦'},
				      {id:'5',companyName:'其他'}]});
	},
	//合作意向Store
	getCoopertationIntensionStore:function(){
		return Ext.create('CoopertationIntensionStore',{
				data:[{id:'1',intention:'高'},
				      {id:'2',intention:'中'},
				      {id:'3',intention:'低'}]});
	},
	//货量潜力Store
	getGoodsPotentialStore:function(){
		return  Ext.create('GoodsPotentialStore',{
				data:[{id:'1',goodsPotential:'<600元/月'},
				      {id:'2',goodsPotential:'600-3000元/月'},
				      {id:'3',goodsPotential:'3000-10000元/月'},
				      {id:'4',goodsPotential:'>10000元/月'}]});
	},
	//商机状态Store
	getBusinessOpportunityStore:function(){
		return Ext.create('BusinessOpportunityStore',{
				data:[{id:'1',busiOpportunity:'定位目标'},
				      {id:'2',busiOpportunity:'接触目标'},
				      {id:'3',busiOpportunity:'开始发货'},
				      {id:'4',busiOpportunity:'升级会员'}]});
	}
});

