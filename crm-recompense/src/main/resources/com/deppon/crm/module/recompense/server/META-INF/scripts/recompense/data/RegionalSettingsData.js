/**
 * 大区设置	RegionalSettings
 * @author rock
 */

(function() {
	var RegionalSettingsDataTest = "../scripts/recompense/test/RegionalSettingsDataTest.js";
	if (CONFIG.get('TEST')) {
		document.write('<script type="text/javascript" class="RegionalSettingsDataTest" src="'
						+ RegionalSettingsDataTest + '"></script>');
	}
})();

RegionalSettingsData = function() {};

Ext.define('RegionalSettingsModel', {		// 大区设置model
    extend: 'Ext.data.Model',
	fields :[ {
				name : CONFIGNAME.get('id')					//1.id
			},{
				name : CONFIGNAME.get('RegionalSett_user')	//2.用户名	user
			},{
				name : CONFIGNAME.get('RegionalSett_dept')	//3.所属大区	 dept
			}
	  	]
});

Ext.define('RegionalSettingsStore', {
	extend: 'Ext.data.Store',
	model : 'RegionalSettingsModel',
	autoLoad:true,
	proxy:{
		type : 'ajax',
		url: '../recompense/findUserRoleDeptRelationByUserId.action',
		actionMethods : 'POST',
		reader:{
			type:'json',
			root:'userRoleDeptRelationList'
		}
	}
});
RegionalSettingsData.prototype.getStore = function(){					// 获得Store
	return new RegionalSettingsStore();
};
RegionalSettingsData.prototype.insertUserRoleDepartment = function(params,fnSuccess,fnFailure){
	var url = '../recompense/insertUserRoleDepartment.action';			// 大区设置提交(userId、roleId、deptId)
	DpUtil.requestJsonAjax(url,params,fnSuccess,fnFailure);
};
RegionalSettingsData.prototype.deleteUserRoleDeptRelationById = function(params, successFn, failureFn){
	var url = '../recompense/deleteUserRoleDeptRelationById.action';	// 删除
	DpUtil.requestJsonAjax(url,params, successFn, failureFn);
};
//--------------------------------------------------------------------
/**.
 * <p>
 * 根据大区名称查询大区<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-4-06
 */
RegionalSettingsData.prototype.searchAreas = function(param,successFn,failureFn)
{
  var url = '../recompense/searchAreas.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 获取根据工号获取职员信息<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-4-11
 */
RegionalSettingsData.prototype.searchEmployeeByCode = function(param,successFn,failureFn)
{
  var url = '../recompense/searchEmployeesByEmpCode.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};