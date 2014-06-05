var DataDictionary={};
var User = {};
var Area = {};
var Areas = null;
var AllAreas = [];
/**
 * 初始化数据字典
 * @param keys 需要初始化数据字典的name
 * @author 张登
 * @时间 2012-3-29
 */
function initDataDictionary(keys){
	Ext.Ajax.request({
		url:'../common/queryAllDataDictionaryByKeys.action',
		async:false,
		jsonData:{'dataDictionaryKeys':keys},
		success:function(response){
			var result = Ext.decode(response.responseText);
			DataDictionary=result.dataDictionary;
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			MessageUtil.showErrorMes(result.message);
		}
	});
}

/**
 * 根据数据字典名称获取对应的store,如果没有则返回[],不影响整个页面的渲染
 * @param data
 * @param name
 * @returns
 * @author 张登
 * @时间 2012-3-29
 */
function getDataDictionaryByName(data,name){
	if(!Ext.isEmpty(data)){
		if(!Ext.isEmpty(data[name])){
			return Ext.create('Ext.data.Store', {
				fields:['code','codeDesc'],
			    data : data[name]
			});
		}
	}
	return [];
}

/**
 * 获取登录用户信息
 * @returns
 * @author 张登
 * @时间 2012-4-11
 */
function initUser(){
	Ext.Ajax.request({
		url : '../common/queryUserInfo.action',
		async:false,
		method:'POST',
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				User = result.user;
			}else{
				User = {};
			}
		}
	});
}
/**
 * 获取登录用户所在大区
 * @returns
 * @author 张登
 * @时间 2012-4-11
 */
function initArea(){
	Ext.Ajax.request({
		url : '../recompense/initArea.action',
		async:false,
		method:'POST',
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				Area = result.dept;
			}else{
				Area = {};
			}
		}
	});
}
/**
 * 获取登录用户所拥有的大区权限
 * @returns
 * @author 张斌
 * @时间 2012-6-16
 */
function initAreas(){
	Ext.Ajax.request({
		url : '../recompense/searchUserBlongArea.action',
		async:false,
		method:'POST',
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				Areas = result.userRoleDeptRelationList;
			}else{
				Areas = null;
			}
		}
	});
}

/**
 * 获取所有大区
 * @returns
 * @author 张斌
 * @时间 2012-6-28
 */
function initAllAreas(){
	Ext.Ajax.request({
		url : '../recompense/searchAreas.action',
		async:false,
		method:'POST',
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				AllAreas = result.deptList;
			}else{
				AllAreas = [];
			}
		}
	});
}