var GTUtil = function(){
}
/**
*ajax请求
*/
GTUtil.requestJsonAjax = function(url,params,successFn,failFn)
{
	Ext.Ajax.request({
		url:url,
		jsonData:params,
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				successFn(result);
			}else{
				failFn(result);
			}
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			failFn(result);
		}
	});
};
GTUtil.requestAjax = function(url,params,successFn,failFn)
{
	Ext.Ajax.request({
		url:url,
		params:params,
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				successFn(result);
			}else{
				failFn(result);
			}
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			failFn(result);
		}
	});
};
GTUtil.goodTradeMapping = function(params,successFn,failFn){
	var url = '../customer/updateGoodTradeMapping.action'
	GTUtil.requestJsonAjax(url,params,successFn,failFn);
}
/**
*获取数据字典的store
*/
GTUtil.getStore = function(storeId,fields,data) {
	var store = Ext.data.StoreManager.lookup(storeId);
	if(Ext.isEmpty(data)){
		data = [];
	}
	if(!Ext.isEmpty(fields)){
		if(store===undefined){
			store = Ext.create('Ext.data.Store', {
				storeId:storeId,
			    fields:fields,
			    data:data
		     });
		}
	}
	return store;
};
 

GTUtil.changeCodeToCodeDesc = function(value, dataDictionary) {
	if (value != null && dataDictionary != null) {
		for ( var i = 0; i < dataDictionary.length; i++) {
			if (value == dataDictionary[i].code) {
			     return dataDictionary[i].codeDesc;
			}
		}
	} else {
	   return null;
	}
}; 
 
 Ext.define('GoodTradeMappingStore',{
	pageSize : 20,
	extend:'Ext.data.Store',
	model:'GoodTradeMappingModel',
	autoLoad:false,
	proxy:{
		type:'ajax',
		url:'../customer/searchGoodTradeMapping.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'list',
			totalProperty : 'totalCount'
		}
	}
});