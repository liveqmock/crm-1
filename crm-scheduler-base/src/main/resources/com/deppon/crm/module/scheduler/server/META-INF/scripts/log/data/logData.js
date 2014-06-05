Ext.define('QueryLogStore',{
	extend:'Ext.data.Store',
	model:'QueryLogModel',
	pageSize: 30,
	proxy:{
		type:'ajax',
		url:'../scheduler/searchLogByCondition.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'logList',
			totalProperty:'totalCount'
		}
	}
});