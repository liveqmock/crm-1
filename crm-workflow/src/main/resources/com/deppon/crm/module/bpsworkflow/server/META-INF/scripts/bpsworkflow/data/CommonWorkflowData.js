var workflowData={};

/**
 * 工作流查询store
 */
Ext.define('FindWorkflowStore',{
	extend:'Ext.data.Store',
	model:'WorkflowModel',
	pageSize:'20',
	proxy:{
		type:'ajax',
		url:'../bpsworkflow/findWorkflow.action',
		actionMethods:'post',
		reader:{
			type:'json',
			root:'workflowList',
			totalProperty:'totalCount'
		}
	}
});

/**
 * 未处理的工作流store
 */
Ext.define('ToHandleWorkflowStore',{
	extend:'Ext.data.Store',
	model:'WorkflowModel',
	pageSize:'20',
	proxy:{
		type:'ajax',
		url:'../bpsworkflow/findToHandleWorkflow.action',
		actionMethods:'post',
		reader:{
			type:'json',
			root:'workflowList',
			totalProperty:'totalCount'
		}
	}
});


/**
 * 已处理的工作流store
 */
Ext.define('HandledWorkflowStore',{
	extend:'Ext.data.Store',
	model:'WorkflowModel',
	pageSize:'20',
	proxy:{
		type:'ajax',
		url:'../bpsworkflow/findHandledWorkflow.action',
		actionMethods:'post',
		reader:{
			type:'json',
			root:'workflowList',
			totalProperty:'totalCount'
		}
	}
});


/**
 * 工作流审批记录store
 */
Ext.define('ApprovalInfoStore',{
	extend:'Ext.data.Store',
	model:'ApprovalInfoModel',
	pageSize:'100',
	proxy:{
		type:'ajax',
		url:'../bpsworkflow/queryApprovalInfo.action',
		actionMethods:'post',
		reader:{
			type:'json',
			root:'approvalInfoList'
		}
	}
});

workflowData.findContractWorkflowInfoByWorkNo = function(param,successFn,failFn){
	var url='../bpsworkflow/findContractWorkflowInfoByWorkNo.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}
/**
 * 大客户工作流
 * @param {} param 工作流号、工作流类型
 * @param {} successFn
 * @param {} failFn
 */
workflowData.findBigCustomerWorkflowInfoByWorkNo = function(param,successFn,failFn){
	var url='../bpsworkflow/findBigCustomerWorkflowInfoByWorkNo.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}
/**
 * 疑似重复客户工作流
 * @param {} param
 * @param {} successFn
 * @param {} failFn
 */
workflowData.findRepetitiveCustByWorkNo = function(param,successFn,failFn){
	var url='../bpsworkflow/findRepetitiveCustByWorkNo.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}
workflowData.findMarketActivityByWorkNo = function(param,successFn,failFn){
	var url='../bpsworkflow/findMarketActivityByWorkNo.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}

workflowData.workflowApprove = function(param,successFn,failFn){
	var url='../bpsworkflow/workflowApprove.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}

workflowData.getCurrentApproval = function(param,successFn,failFn){
	var url='../bpsworkflow/findCurrentApproval.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}

workflowData.workflowUpload=function(form,waitMsg,params,successFn,failureFn){
	form.submit({
        url: '../common/fileUpload.action',
        waitMsg: waitMsg,
        params:params,
        success: successFn,
        failure:failureFn
	 });	
}
workflowData.findApprovePermission = function(params,fnSuccess,fnFailure){
	var url = '../bpsworkflow/findApprovePermission.action';			
	DpUtil.requestJsonAjax(url,params,fnSuccess,fnFailure);
};
workflowData.findOneKeyForApproveWorkFlow = function(params,fnSuccess,fnFailure){
	var url = '../bpsworkflow/findOneKeyForApproveWorkFlow.action';
	DpUtil.requestJsonAjax(url,params,fnSuccess,fnFailure);
}
workflowData.oneKeyForApprove = function (workflowApprove,fnSuccess,fnFailure){
	var url = '../bpsworkflow/oneKeyForApprove.action';
	DpUtil.requestJsonAjax(url,workflowApprove,fnSuccess,fnFailure);
}

workflowData.getNormalClaimByProNum = function(param,successFn,failFn){
	var url='../bpsworkflow/findNormalClaimByProNum.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}
workflowData.getServiceByProNum = function(param,successFn,failFn){
	var url='../bpsworkflow/findServiceByProNum.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}
workflowData.getOverpayByProNum = function(param,successFn,failFn){
	var url='../bpsworkflow/findOverpayByProNum.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}
/**
 * 查询详情用查询二级行业

 */
workflowData.searchSecondTradesToString = function(param,successFn,failureFn){
    var url = '../marketing/searchSecondTradesToString.action';
    DButil.requestJsonAjax(url,param,successFn,failureFn);
};  