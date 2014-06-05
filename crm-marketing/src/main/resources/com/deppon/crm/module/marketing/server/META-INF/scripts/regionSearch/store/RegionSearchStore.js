/**
 * @description 区域划分查询数据集
 * @author 盛诗庆
 * @date 2013-12-13
 * @revision none
 */
/**
 * @description 区域划分交互类，主要对原型函数进行扩充，主要扩充见具体方法说明
 * @author 盛诗庆
 * @date 2013-12-13
 * @revision none
 */
RegionSearchStore = function(){
	
};
/**
 * @description 根据部门id查找部门所辖营销区域和所辖部门的营销区域
 * @author 盛诗庆
 * @date 2013-12-13
 * @ deptId 部门id,successFn 返回成功调用的函数，failureFn 返回失败调用的函数
 */
RegionSearchStore.prototype.searchRegionsByDeptId = function(param,successFn,failureFn){
	var url = '../marketing/searchRegionsByDeptId.action';//请求的action地址
	DpUtil.requestAjax(url,param,successFn,failureFn);//参见util目录下DpUtil.requestAjax方法
};
/**
 * @description 初始化区域查询界面
 * @author 盛诗庆
 * @date 2013-12-13
 * @ deptId 部门id,successFn 返回成功调用的函数，failureFn 返回失败调用的函数
 */
RegionSearchStore.prototype.initSearchRegion = function(param,successFn,failureFn){
	var url = '../marketing/initRegionPartitionPage.action';
	DpUtil.requestAjax(url,param,successFn,failureFn);
};
/**
* @description 部门信息store
* @author 盛诗庆
* @date 2013-12-14
* @revision nonea
**/
Ext.define('DeptInfoStore', {
	extend : 'Ext.data.Store',
	model : 'DeptInfoModel',
	
});
/**
 * @description 部门store
 * @author 盛诗庆
 * @date 2013-12-16
 * @revision none
 */
Ext.define('DeptStore', {
	extend : 'Ext.data.Store',
	model : 'DeptModel',
//	autoLoad:true,
	pageSize : 10,
	proxy : {
		type : 'ajax',
		url : '../marketing/queryDeptListByDeptName.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'executeDeptList',
			totalProperty : 'totalCount'
		}
	}
});