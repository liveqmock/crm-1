// 数据字典维护的 Store
Ext.define('datadictionaryManagementStore',{
	pageSize : 10,
	extend:'Ext.data.Store',
	model:'datadictionaryManagementModel',
	autoLoad:false,
	proxy:{
		type:'ajax',
		url:'../common/querydetailbyconditions.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'datedictionaryManagementList',
			totalProperty : 'totalCount'   //执行分组  limit  start参数
		}
	}
});

Ext.define('dHeadManagementStore',{
	pageSize : 10,
	extend:'Ext.data.Store',
	model:'datadictionaryheadModel',
	autoLoad:false,
	proxy:{
		type:'ajax',
		url:'../common/queryheadall.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'datedictionaryHeadList',
			totalProperty : 'totalCount'   //执行分组  limit  start参数
		}
	}
});