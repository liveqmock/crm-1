
DutySearchDetailStore=function(){
	
};


DutySearchDetailStore.prototype.searchDutyDetail = function(param,successFn,failureFn){
	var url = '../duty/searchDutyDetail.action';
	DutyUtil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * 查看统计员所在部门
 * @param {Object} param
 * @param {Function} successFn
 * @param {Function} failureFn
 */
DutySearchDetailStore.prototype.searchStatDeptName=function(params,successFn,failFn){
	var url = '../duty/searchStatDeptName.action';
	DutyUtil.requestJsonAjax(url,params,successFn,failFn);
};