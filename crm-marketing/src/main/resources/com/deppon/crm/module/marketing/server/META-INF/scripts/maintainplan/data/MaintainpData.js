/**
 * 客户维护
 */
MaintainpData = function(){
	
};

/**
* <p>
* 根据条件查询客户列表
* <p>
* @author 张登
* @时间 2012-4-18
*/
Ext.define('CustomerGroupStore',{
	extend:'Ext.data.Store',
	model:'CustomerGroupModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		url:'../marketing/serachCustomerGroupList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'listCustomerGroup'
		}
	}
});
/**
 * 根据条件查询客户
 */
Ext.define('CycleListStore', {
	extend: 'Ext.data.Store',
	model : 'CustomerDetailGroupModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		url:'../marketing/queryCustomerGroupDetailListByCondition.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'listCustomerGroupDetail',
			totalProperty : 'totalCount'
		}
	}
});
/**
 * 查询已有分组的客户
 */
Ext.define('CycleListByGroupIdStore', {
	extend: 'Ext.data.Store',
	model : 'CustomerDetailGroupModel',
	proxy:{
		type:'ajax',
		timeout:120000,
		url:'../marketing/queryCustomerGroupDetail.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'listCustomerGroupDetail'
		}
	}
});
/**.
 * 查询发到货周期表数据
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-19
 */
MaintainpData.prototype.queryCycleListHeader = function(param,successFn,failureFn){
	var url = '../marketing/queryCycleListHeader.action';
	DButil.requestAjax(url,param,successFn,failureFn);
}

/**.
 * 查询发到货周期表数据
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-19
 */
MaintainpData.prototype.saveAllCustomerGroup = function(param,successFn,failureFn){
	var url = '../marketing/saveAllCustomerGroup.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
}

/**.
 * <p>
 * 新增客户分组<br/>
 * <p>
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-17
 */
MaintainpData.prototype.saveCustomerGroup = function(param,successFn,failureFn){
	var url = '../marketing/saveCustomerGroup.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 修改客户分组<br/>
 * <p>
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-17
 */
MaintainpData.prototype.updateCustomerGroup = function(param,successFn,failureFn){
	var url = '../marketing/updateCustomerGroup.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 删除客户分组<br/>
 * <p>
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-17
 */
MaintainpData.prototype.deleteCustomerGroup = function(param,successFn,failureFn){
	var url = '../marketing/deleteCustomerGroup.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 回去当前部门<br/>
 * <p>
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-17
 */
MaintainpData.prototype.queryUserInfo = function(param,successFn,failureFn){
	var url = '../common/queryUserInfo.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};
