/**.
 * <p>
 * 监控计划data
 * <p>
 * @author 张登
 * @时间 2012-4-10
 */
MonitorManageData = function(){
	
};


/**.
 * <p>
 * 根据条件查询监控计划列表
 * <p>
 * @author 张登
 * @时间 2012-4-10
 */
Ext.define('MonitorPlanStore',{
	extend:'Ext.data.Store',
	model:'MonitorPlanModel',
	proxy:{
		type:'ajax',
		url:'../marketing/monitorList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'monitorPlanList'
		}
	}
});


/**.
 * <p>
 * 根据条件查询监控计划列表详细
 * <p>
 * @author 张登
 * @时间 2012-4-10
 */
Ext.define('MonitorPlanDetailStore',{
	extend:'Ext.data.Store',
	model:'MonitorPlanDetailModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		url:'../marketing/monitorDetail.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'monitorPlanDetailList',
			totalProperty : 'totalCount'
		}
	}
});

/**.
 * <p>
 * 查询用户所拥有部门数量<br/>
 * <p>
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-6
 */
MonitorManageData.prototype.searchDeptCount = function(param,successFn,failureFn){
	var url = '../marketing/searchDeptCount.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};