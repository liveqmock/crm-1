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
		url:'../customer/findBpsWorkflow.action',
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
		url:'../customer/findToHandleBpsWorkflow.action',
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
		url:'../customer/findHandledBpsWorkflow.action',
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
		url:'../customer/queryApprovalInfo.action',
		actionMethods:'post',
		reader:{
			type:'json',
			root:'approvalInfoList'
		}
	}
});

workflowData.findContractWorkflowInfoByWorkNo = function(param,successFn,failFn){
	var url='../customer/findContractWorkflowInfoByWorkNo.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}

workflowData.workflowApprove = function(param,successFn,failFn){
	var url='../customer/workflowApprove.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}

workflowData.getCurrentApproval = function(param,successFn,failFn){
	var url='../customer/findCurrentApproval.action';
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
	var url = '../customer/findApprovePermission.action';			
	DpUtil.requestJsonAjax(url,params,fnSuccess,fnFailure);
};
