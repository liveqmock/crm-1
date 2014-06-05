/**
 * 工单基础资料Data
 */
BaseInfoData = {};

Ext.define('BaseIntoTreeStore', {
	extend: 'Ext.data.TreeStore',
	autoSync:true,
	proxy : {
		type : 'ajax',
		actionMethods:'POST',
		url : '../complaint/loadTree.action'
	},
	root : {
		id : '0',
		text : i18n('i18n.baseInfo.rootNode'),
		expanded : true
	},
	sorters : [ {
		property : 'leaf',
		direction : 'ASC'
	}]
});


Ext.define('BaseInfoStore', {
	extend: 'Ext.data.Store',
	model : 'BaseInfoModel',
	proxy:{
		type : 'ajax',
		url: '../complaint/findBaseInfoList.action',
		actionMethods : 'POST',
		reader:{
			type:'json',
			root:'list'
		}
	}
});


BaseInfoData.addBaseInfo = function(params,fnSuccess,fnFailure){
	var url = '../complaint/addBaseInfo.action';			
	DpUtil.requestJsonAjax(url,params,fnSuccess,fnFailure);
};

BaseInfoData.updateBaseInfo = function(params,fnSuccess,fnFailure){
	var url = '../complaint/updateBaseInfo.action';			
	DpUtil.requestJsonAjax(url,params,fnSuccess,fnFailure);
};

BaseInfoData.deleteBaseInfo = function(params,fnSuccess,fnFailure){
	var url = '../complaint/deleteBaseInfo.action';			
	DpUtil.requestJsonAjax(url,params,fnSuccess,fnFailure);
};

Ext.define('BaseInfoLevelStore', {
	extend: 'Ext.data.Store',
	model : 'BaseInfoModel',
	proxy:{
		type : 'ajax',
		url: '../complaint/findBaseInfoLevelList.action',
		actionMethods : 'POST',
		reader:{
			type:'json',
			root:'list'
		}
	}
});