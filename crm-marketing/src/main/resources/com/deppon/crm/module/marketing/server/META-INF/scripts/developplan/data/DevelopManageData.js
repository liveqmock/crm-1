/**
 * 开发计划管理数据
 */
DevelopManageData = function(){
};


/**
 * 开发计划管理删除操作
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 毛建强
 * @时间 2012-4-6
 */
DevelopManageData.prototype.deletePlan = function(param,successFn,failureFn){
	var url = '../marketing/deleteDevelopPlane.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};
