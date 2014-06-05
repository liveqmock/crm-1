/**
 * 审批金额配置
 */
AmountConfigData = {};

Ext.define('AmountConfigStore', {
	extend: 'Ext.data.Store',
	model : 'AmountConfigModel',
	pageSize:20,
	proxy:{
		type : 'ajax',
		url: '../workflow/findAmountConfigPage.action',
		actionMethods : 'POST',
		reader:{
			type:'json',
			root:'amountConfigList',
			totalProperty : 'totalCount'
		}
	}
});

Ext.define('ActivityStore', {
	extend: 'Ext.data.Store',
	model : 'ActivityModel',
	proxy:{
		type : 'ajax',
		url: '../workflow/findActivity.action',
		actionMethods : 'POST',
		reader:{
			type:'json',
			root:'activityList'
		}
	}
});





AmountConfigData.addAmountConfig = function(params,fnSuccess,fnFailure){
	var url = '../workflow/addAmountConfig.action';			
	DpUtil.requestJsonAjax(url,params,fnSuccess,fnFailure);
};

AmountConfigData.updateAmountConfig = function(params,fnSuccess,fnFailure){
	var url = '../workflow/updateAmountConfig.action';			
	DpUtil.requestJsonAjax(url,params,fnSuccess,fnFailure);
};

AmountConfigData.deleteAmountConfig = function(params,fnSuccess,fnFailure){
	var url = '../workflow/deleteAmountConfig.action';			
	DpUtil.requestJsonAjax(url,params,fnSuccess,fnFailure);
};

