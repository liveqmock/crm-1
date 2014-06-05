/**.
 * <p>
 * 开发计划新增、修改页面data
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
CustomerDevelopPlaneData = function(){
};

/**.
 * <p>
 * 根据条件查询客户列表
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
Ext.define('SearchCustomerStore',{
	extend:'Ext.data.Store',
	model:'SearchConditionModel',
	pageSize:10,
	proxy:{
		type:'ajax',
		url:'../marketing/searchDevelopCustomer.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'listCustomerInfo',
			totalProperty : 'totalCount'
		}
	}
});

/**.
 * <p>
 * 根据计划id查询关联客户
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
Ext.define('SearchCustomerbyPlanIdStore',{
	extend:'Ext.data.Store',
	model:'SearchConditionModel',
	proxy:{
		type:'ajax',
		url:'../marketing/searchCustomerbyPlanId.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'listScatterCustomerInfo'
		}
	}
});

/**.
 * <p>
 * 根据条件查询客户列表
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
Ext.define('SearchMemberStore',{
	extend:'Ext.data.Store',
	model:'SearchConditionModel',
	pageSize:10,
	proxy:{
		type:'ajax',
		url:'../marketing/searchMemberList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'listCustomer',
			totalProperty : 'totalCount'
		}
	}
});

/**.
 * <p>
 * 根据计划id查询关联客户
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
Ext.define('SearchMemberListByPlanIdStore',{
	extend:'Ext.data.Store',
	model:'SearchConditionModel',
	proxy:{
		type:'ajax',
		url:'../marketing/searchMemberListByPlanId.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'listCustomer'
		}
	}
});
/**
 * 查询维护人
 */
Ext.define('PrehumanStore', {
	extend : 'Ext.data.Store',
	model : 'UserModel',
	autoLoad:true,
	proxy : {
		type : 'ajax',
		url : '../marketing/serachPrehumanByDeptId.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'listEmployee'
		}
	}
});

/**.
 * 根据分组取维护人ID
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-19
 */
CustomerDevelopPlaneData.prototype.serachCustomerGroupByPrehuman = function(param,successFn,failureFn){
	var url = '../marketing/serachCustomerGroupByPrehuman.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};

/**.
 * 根据维护人取分组ID
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-19
 */
CustomerDevelopPlaneData.prototype.searchPrehumanByGroupId = function(param,successFn,failureFn){
	var url = '../marketing/searchPrehumanByGroupId.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 制定、修改开发<br/>
 * <p>
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-6
 */
CustomerDevelopPlaneData.prototype.savePlan = function(param,successFn,failureFn){
	var url = '../marketing/savePlan.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};
	
/**.
 * <p>
 * 根据计划id查询开发计划<br/>
 * <p>
 * @param param  计划id
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-6
 */
CustomerDevelopPlaneData.prototype.searchPlanById = function(param,successFn,failureFn){
	var url = '../marketing/searchPlanById.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 *判断是否离职<br/>
 * <p>
 * @param param  计划id
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-6
 */
CustomerDevelopPlaneData.prototype.isOutOrLeave = function(param,successFn,failureFn){
	var url = '../marketing/isOutOrLeave.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 查询用户所拥有部门数量<br/>
 * <p>
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-6
 */
CustomerDevelopPlaneData.prototype.searchDeptCount = function(param,successFn,failureFn){
	var url = '../marketing/searchDeptCount.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 根据问卷id查找问卷对应问题信息<br/>
 * <p>
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 盛诗庆
 * @时间 2014-03-28
 */
CustomerDevelopPlaneData.prototype.searchQuestionnaireInfoList = function(param,successFn,failureFn){
	var url = '../marketing/searchQuestionnaireInfoList.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};