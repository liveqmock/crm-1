DutyApprovalStore = function(){
	
};
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
		url:'../duty/queryDutyApprovalList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'dutyList',
			totalProperty : 'totalCount'
		}
	}
});
/**
 * 根据工单责任ID查询工单详情、处理经过、划分结果等
 */
DutyApprovalStore.prototype.searchDutyDetailByDutyId = function(param,successFn,failureFn){
	var url = '../duty/searchDutyDetail.action';
	DutyUtil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * 工单责任审批操作
 */
DutyApprovalStore.prototype.dutyApprovalOperation = function(param,successFn,failureFn){
	var url = '../duty/dutyApprovalOperation.action';
	DutyUtil.requestJsonAjax(url,param,successFn,failureFn);
};