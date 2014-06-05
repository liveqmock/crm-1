// 信用较差客户报表 Store
Ext.define('WorsecustReportStore',{
	pageSize : 10,
	extend:'Ext.data.Store',
	model:'WorsecustReportModel',
	autoLoad:false,
	proxy:{
		type:'ajax',
		url:'../custReport/queryWorsecustReport.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'custCreditList',
			totalProperty : 'totalCount'   //执行分组  limit  start参数
		}
	}
});