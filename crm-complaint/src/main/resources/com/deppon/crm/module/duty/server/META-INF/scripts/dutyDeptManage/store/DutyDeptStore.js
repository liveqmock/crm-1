DutyDeptOperateStore = function(){
	
};
/**
 * LiuY
 * 工单责任部门配置Store
 */
Ext.define('DutyDeptStore',{
	extend:'Ext.data.Store',
	model:'DutyDeptModel',
	autoLoad:true,
	proxy:{
		type:'ajax'
		,api:{read:'searchDutyDeptList.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'dutyDeptList'
		}
	}
});

/**
 * 保存工单特殊责任部门--LiuY
 */
DutyDeptOperateStore.prototype.saveDutyDept = function(param,successFn,failureFn){
	var url = '../duty/saveDutyDept.action';
	DutyUtil.requestJsonAjax(url,param,successFn,failureFn);
}
/**
 * 删除工单特殊责任部门--LiuY
 */
DutyDeptOperateStore.prototype.deleteDutyDept = function(param,successFn,failureFn){
	var url = '../duty/deleteDutyDept.action';
	DutyUtil.requestJsonAjax(url,param,successFn,failureFn);
}
