/**
 * 责任查询结果列表Store
 */
Ext.define('DutyQueryResultStore',{
	extend:'Ext.data.Store',
	model:'DutyQueryResultModel',
	pageSize:20,
	autoLoad:false,
	proxy:{
		type:'ajax',
		url:'../duty/queryDutyList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'dutyList',
			totalProperty : 'totalCount'
		}
	}
});
