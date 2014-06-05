/**
*潜在客户Data层
*/

//判断是否导入测试数据
(function() {
	var potentialCustTest = "../scripts/potentialcust/test/PotentialCustDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + potentialCustTest + '"></script>');
	}
})();

/**
* 城市
*/
Ext.define('CityModel',{
	extend:'Ext.data.Model',
	fields:['id','cityName']
});




Ext.define('PotentialCustData',{
	extend:'PotentialCustBasicData',
	cityStore:null,
	savePotentialCust:function(params,saveSuccessFn,saveFailFn){		
		DpUtil.requestJsonAjax('addPotentialCust.action',params,saveSuccessFn,saveFailFn);
	},
	updatePotentialCust:function(params,saveSuccessFn,saveFailFn){
		DpUtil.requestJsonAjax('updatePotentialCust.action',params,saveSuccessFn,saveFailFn);
	},
	getCityStore:function(){
		//TODO
		return this.cityStore;
	}
});

