RegionPartitionStore = function(){
	
};
/**
 * 部门信息
 */
 Ext.define('DeptInfoStore',{
	extend : 'Ext.data.Store',
	model : 'DeptInfoModel',
});
RegionPartitionStore.prototype.deleteRegionPartition = function(param,successFn,failureFn){
	var url = '../marketing/deleteRegionPartition.action';
	DpUtil.requestAjax(url,param,successFn,failureFn);
};
RegionPartitionStore.prototype.saveRegionPartition = function(param,successFn,failureFn){
	var url = '../marketing/saveRegionPartition.action';
	DpUtil.requestAjax(url,param,successFn,failureFn);
};
RegionPartitionStore.prototype.searchRegionByDeptId = function(param,successFn,failureFn){
	var url = '../marketing/searchRegionByDeptId.action';
	DpUtil.requestAjax(url,param,successFn,failureFn);
};
RegionPartitionStore.prototype.initRegionPartition = function(param,successFn,failureFn){
	var url = '../marketing/initRegionPartitionPage.action';
	DpUtil.requestAjax(url,param,successFn,failureFn);
};