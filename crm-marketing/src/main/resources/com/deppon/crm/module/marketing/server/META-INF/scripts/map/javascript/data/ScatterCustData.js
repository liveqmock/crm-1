/**
*潜在客户Data层
*/

//判断是否导入测试数据
(function() {
	var scatterCustTest = "../scripts/scattercust/test/ScatterCustDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + scatterCustTest + '"></script>');
	}
})();

/**
* 城市
*/
Ext.define('CityModel',{
	extend:'Ext.data.Model',
	fields:['id','cityName']
});




Ext.define('ScatterCustData',{
	extend:'ScatterCustBasicData',
	cityStore:null,
	updateScatterCust:function(params,saveSuccessFn,saveFailFn){
		DpUtil.requestJsonAjax('updateScatterCust.action',params,saveSuccessFn,saveFailFn);
	},
	//保存新增散客信息
	saveScatterCust:function(params,saveSuccessFn,saveFailFn){
		DpUtil.requestJsonAjax('saveScatterCust.action',params,saveSuccessFn,saveFailFn);
	},
	//实时验证税务登记号[身份证号] 是否合法
	validateTaxOrIdCardIsLegal:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('validateTaxOrIdCardIsLegal.action',params,successFn,failFn);
	},
	//校验散客固话加姓名[手机]
	validateSCMobileOrTelIsExist:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('validateSCMobileOrTelIsExist.action',params,successFn,failFn);
	},	
	getCityStore:function(){
		//TODO
		return this.cityStore;
	}
});

