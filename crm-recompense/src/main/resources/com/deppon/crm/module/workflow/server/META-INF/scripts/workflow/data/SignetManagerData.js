/**
 * 印章管理员设置
 */
SignetManagerData = {};

Ext.define('SignetManagerStore', {
	extend: 'Ext.data.Store',
	model : 'SignetManagerModel',
	pageSize:20,
	proxy:{
		type : 'ajax',
		url: '../workflow/findSignetManager.action',
		actionMethods : 'POST',
		reader:{
			type:'json',
			root:'signetManagerList',
			totalProperty : 'totalCount'
		}
	}
});

SignetManagerData.addSignetManager = function(params,fnSuccess,fnFailure){
	var url = '../workflow/addSignetManager.action';			
	DpUtil.requestJsonAjax(url,params,fnSuccess,fnFailure);
};

SignetManagerData.deleteSignetManager = function(params, successFn, failureFn){
	var url = '../workflow/deleteSignetManager.action';	
	DpUtil.requestJsonAjax(url,params, successFn, failureFn);
};

SignetManagerData.queryBizAndHubCenterDept = function(param,successFn,failureFn)
{
  var url = '../workflow/queryBizAndHubCenterDept.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};