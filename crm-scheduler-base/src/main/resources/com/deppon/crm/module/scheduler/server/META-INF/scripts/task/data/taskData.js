/**
 * 任务查询
 */
Ext.define('TaskStore',{
	extend:'Ext.data.Store',
	model:'TaskModel',
	pageSize:10,
	proxy:{
		type:'ajax',
		url:'../scheduler/searchTaskByCondition.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'jobScheduleViewList',
			totalProperty:'totalCount'
		}
	}
});

taskData = function(){};
/**
 * 任务新增
 * @param params
 * @param successFn
 * @param failFn
 * @author zouming
 * @returns
 */
taskData.prototype.submitTask = function(params,successFn,failFn){
	var url='../scheduler/submitTask.action';
	DpUtil.requestAjax(url,params,successFn,failFn);
}


/**
 * 按照ID查询任务
 */
taskData.prototype.searchJobSchedualById = function(params,successFn,failFn){
	var url='../scheduler/searchJobSchedualById.action';
	DpUtil.requestAjax(url,params,successFn,failFn);
}
/**
 * 任务删除
 * @param params
 * @param successFn
 * @param failFn
 * @author zouming
 * @returns
 */
taskData.prototype.deleteTask = function(params,successFn,failFn){
	var url='../scheduler/deleteTask.action';
	DpUtil.requestAjax(url,params,successFn,failFn);
}

/**
 * 任务修改
 * @param params
 * @param successFn
 * @param failFn
 * @author zouming
 * @returns
 */
taskData.prototype.modifyTask = function(params,successFn,failFn){
	var url='../scheduler/modifyTask.action';
	DpUtil.requestAjax(url,params,successFn,failFn);
}

/**
 * 任务启动
 * @param params
 * @param successFn
 * @param failFn
 * @author zouming
 * @returns
 */
taskData.prototype.startupTask = function(params,successFn,failFn){
	var url='../scheduler/startupTask.action';
	DpUtil.requestAjax(url,params,successFn,failFn);
}

/**
 * 任务执行
 * @param params
 * @param successFn
 * @param failFn
 * @author zouming
 * @returns
 */
taskData.prototype.stopTask = function(params,successFn,failFn){
	var url='../scheduler/stopTask.action';
	DpUtil.requestAjax(url,params,successFn,failFn);
}

/**
 * 任务停止
 * @param params
 * @param successFn
 * @param failFn
 * @author zouming
 * @returns
 */
taskData.prototype.executeTask = function(params,successFn,failFn){
	var url='../scheduler/executeTask.action';
	DpUtil.requestAjax(url,params,successFn,failFn);
}