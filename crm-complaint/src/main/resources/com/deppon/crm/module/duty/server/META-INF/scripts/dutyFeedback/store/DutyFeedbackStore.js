DutyFeedbackStore = function(){};

/**
 * 责任反馈分页Store
 */
Ext.define('DutyFeedbackPagerStore',{
	extend:'Ext.data.Store',
	model:'DutyFeedbackModel',
	pageSize:20,autoLoad:false,
	proxy:{
		type:'ajax'
		,api:{read:'../duty/searchDutyFeedbackPager.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json',root:'searchDutyResultVOList',totalProperty:'totalCount'
		}
	}
});

/**
 *  ActuaryDepartmentStore 部门 store
 *  查询统计员所在部门
 */
Ext.define('ActuaryDepartmentStore',{
	extend:'Ext.data.Store'
	,fields: [
		{name:'id',type:'String'}/*编号*/
		,{name:'deptName',type:'String'}/*部门名称*/
	]
	,pageSize:40
	,autoLoad:true
	,proxy:{
		type:'ajax'
		,url:'../duty/searchActuaryDeptList.action'
		,actionMethods:'POST'
		,reader:{
			type:'json',root:'departmentList'
		}
	}
});



/**
 * 责任反馈 - 责任认领
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
DutyFeedbackStore.prototype.dutyClaim = function(param,successFn,failureFn){
	var url = '../duty/dutyClaim.action';
	DutyUtil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * 责任反馈 - 责任反馈
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
DutyFeedbackStore.prototype.dutyFeedback = function(param,successFn,failureFn){
	var url = '../duty/dutyFeedbackProcess.action';
	DutyUtil.requestJsonAjax(url,param,successFn,failureFn);
};