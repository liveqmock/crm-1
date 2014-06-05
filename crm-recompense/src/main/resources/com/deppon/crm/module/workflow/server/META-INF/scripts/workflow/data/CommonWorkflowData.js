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
		url:'../workflow/findWorkflow.action',
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
		url:'../workflow/findToHandleWorkflow.action',
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
		url:'../workflow/findHandledWorkflow.action',
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
		url:'../workflow/queryApprovalInfo.action',
		actionMethods:'post',
		reader:{
			type:'json',
			root:'approvalInfoList'
		}
	}
});

workflowData.getNormalClaimByProNum = function(param,successFn,failFn){
	var url='../workflow/findNormalClaimByProNum.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}

workflowData.workflowApprove = function(param,successFn,failFn){
	var url='../workflow/workflowApprove.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}

workflowData.getCurrentApproval = function(param,successFn,failFn){
	var url='../workflow/findCurrentApproval.action';
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
	var url = '../workflow/findApprovePermission.action';			
	DpUtil.requestJsonAjax(url,params,fnSuccess,fnFailure);
};
workflowData.getServiceByProNum = function(param,successFn,failFn){
	var url='../workflow/findServiceByProNum.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}
workflowData.getOverpayByProNum = function(param,successFn,failFn){
	var url='../workflow/findOverpayByProNum.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}