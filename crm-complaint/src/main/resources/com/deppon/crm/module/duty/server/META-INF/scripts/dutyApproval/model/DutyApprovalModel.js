/**
 * 责任查询结果列表model
 */
Ext.define('DutyQueryResultModel', {
	extend : 'Ext.data.Model',
	fields : [{//工单责任Id
		name:'dutyId'
	},{//责任划分Id
		name:'detailId'
	},{
		// 凭证号
		name : 'voucherNumber'
	},{
		//工单id
		name:'complaintId'
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
	},{//业务模式
		name:'businessModel'
	},{//反馈ID
		name:'feedbackId'
	}]
});