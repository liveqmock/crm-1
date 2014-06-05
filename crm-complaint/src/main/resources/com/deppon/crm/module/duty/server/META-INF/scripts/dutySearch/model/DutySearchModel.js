/**
 * 责任查询结果列表model
 */
Ext.define('DutyQueryResultModel', {
	extend : 'Ext.data.Model',
	fields : [{
		name:'dutyId'
	},{
		name:'complaintId'
	},{
		// 凭证号
		name : 'voucherNumber'
	},{
		// 处理编号
		name : 'dealNumber'
	},{
		// 上报类型
		name : 'reportType'
	},{
		// 来电人
		name : 'caller'
	},{
		// 调查结果
		name : 'surveyResult'
	},{
		// 责任划分人
		name : 'appDutyUser'
	},{
		// 责任划分时间
		name : 'appDutyTime'
	},{
		// 操作人
		name : 'operateName'
	},{
		// 操作时间
		name : 'operateDate'
	},{//业务模式
		name:'businessModel'
	}]
});

