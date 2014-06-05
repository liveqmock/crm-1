
ClaimData = function(){}; 
/**
 * 查询运单信息
 * @param param
 * @param successFn
 * @param failFn
 */
getWayBillAndType = function(param,successFn,failFn){
	var url='../recompense/getWayBillAndType.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}
/**
 * 上报索赔
 * @param param
 * @param successFn
 * @param failFn
 */
saveClaim = function(param,successFn,failFn){
	var url='../recompense/saveClaim.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}
/**
 * 根据索赔ID查询索赔信息
 * @param param
 * @param successFn
 * @param failFn
 * @returns
 */
searchClaimById = function(param,successFn,failFn){
	var url='../recompense/searchClaimById.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}

/**
 * 同意受理事件
 * @param param
 * @param successFn
 * @param failFn
 * @returns
 */
acceptClaim = function(param,successFn,failFn){
	var url='../recompense/acceptClaim.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}

/**
 * 发送给对方部门处理事件
 * @param param
 * @param successFn
 * @param failFn
 * @returns
 */
sendToAnotherDept = function(param,successFn,failFn){
	var url='../recompense/sendToAnotherDept.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}

/**
 * 免赔事件
 * @param param
 * @param successFn
 * @param failFn
 * @returns
 */
remitClaimByClaimId = function(param,successFn,failFn){
	var url='../recompense/remitClaimByClaimId.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}

/**
 * 免赔解冻事件
 * @param param
 * @param successFn
 * @param failFn
 * @returns
 */
cancelRemitClaim = function(param,successFn,failFn){
	var url='../recompense/cancelRemitClaim.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}

/**
 * 保存跟进信息事件
 * @param param
 * @param successFn
 * @param failFn
 * @returns
 */
addFollowClaimMessage = function(param,successFn,failFn){
	var url='../recompense/addFollowClaimMessage.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}


/**
 * 索赔store
 */
Ext.define('ClaimStore',{
	extend:'Ext.data.Store',
	model:'ClaimModel',
	pageSize:'10',
	proxy:{
		type:'ajax',
		url:'../recompense/searchClaimByCondition.action',
		actionMethods:'post',
		reader:{
			type:'json',
			root:'claimList',
			totalProperty:'totalCount'
		}
	}
});

/**
 * 索赔操作日志store
 */
Ext.define('OperateLogGridStore',{
	extend:'Ext.data.Store',
	model:'ClaimOperateLogGridModel',
	proxy:{
		type:'ajax',
		url:'../recompense/getOperationLogListByClaimId.action',
		actionMethods:'post',
		reader:{
			type:'json',
			root:'claimOperationLogList'
		}
	}
});

/**
 * 索赔跟进信息store
 */
Ext.define('FollowUpGridStore',{
	extend:'Ext.data.Store',
	model:'ClaimFollowUpGridModel',
	proxy:{
		type:'ajax',
		url:'../recompense/getClaimMessageListByClaimId.action',
		actionMethods:'post',
		reader:{
			type:'json',
			root:'claimMessageList'
		}
	}
});

Ext.define('DeptStore', {
	extend : 'Ext.data.Store',
	model : 'DeptModel',
//	autoLoad:true,
	pageSize : 10,
	proxy : {
		type : 'ajax',
		url : '../marketing/queryDeptListByDeptName.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'executeDeptList',
			totalProperty : 'totalCount'
		}
	}
});