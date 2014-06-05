/**
 * 开发日程管理数据
 */
DevelopScheduleData = function(){
};


/**
 * 开发日程管理删除操作
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 毛建强
 * @时间 2012-4-6
 */
DevelopScheduleData.prototype.deleteSchedule = function(param,successFn,failureFn){
	var url = '../marketing/deleteDevelopSchedule.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};
/**
 * 开发日程管理修改日程操作
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 毛建强
 * @时间 2012-4-6
 */
DevelopScheduleData.prototype.updateSchedule = function(param,successFn,failureFn){
	var url = '../marketing/updateDevelopSchedule.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};

/**
 * 弹出回访页面
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 毛建强
 * @时间 2012-4-6
 */
DevelopScheduleData.prototype.setRetureVisit = function(param,successFn,failureFn){
	var url = '../marketing/initCreateReturnvisitPage.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};

/**
 * 回访(客户)
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 毛建强
 * @时间 2012-4-6
 */
DevelopScheduleData.prototype.setRetureVisitByCust = function(param,successFn,failureFn){
	var url = '../marketing/initCreateReturnvisitPageByCust.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 根据条件查询日程
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
Ext.define('ScheDuleStore',{
	extend:'Ext.data.Store',
	model:'ScheDuleModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		url:'../marketing/searchScheduleCustomerList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'scheduleList',
			totalProperty : 'totalCount'
		}
	}
});

/**.
 * <p>
 * 日程新增<br/>
 * <p>
 * @param param 
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-6
 */
DevelopScheduleData.prototype.saveSchedule = function(param,successFn,failureFn){
	var url = '../marketing/saveSchedule.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 查询客户详细<br/>
 * <p>
 * @param param 
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-6
 */
DevelopScheduleData.prototype.searchPcScDetail = function(param,successFn,failureFn){
	var url = '../marketing/searchPcScDetail.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};